package com.ramkiopt.main.services.utils;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ObjectMapper {

    private final static Logger LOGGER = LoggerFactory.getLogger(ObjectMapper.class);

    public static void mapCustom(Object src, Object dest) {
        if (src == null || dest == null) {
            return;
        }
        Class srcClass = src.getClass();
        Class destClass = dest.getClass();
        Field[] fields = srcClass.getDeclaredFields();
        for (Field field : fields) {
            Field destField;
            try {
                destField = destClass.getDeclaredField(field.getName());
            } catch (NoSuchFieldException e) {
                continue;
            }
            field.setAccessible(true);
            destField.setAccessible(true);
            try {
                destField.set(dest, field.get(src));
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public static void mapCustomWithoutNulls(Object src, Object dest) {
        if (src == null || dest == null) {
            return;
        }
        Class srcClass = src.getClass();
        Class destClass = dest.getClass();
        Field[] fields = srcClass.getDeclaredFields();
        for (Field field : fields) {
            Field destField;
            try {
                destField = destClass.getDeclaredField(field.getName());
            } catch (NoSuchFieldException e) {
                continue;
            }
            field.setAccessible(true);
            destField.setAccessible(true);
            try {
                if (field.get(src) != null) {
                    destField.set(dest, field.get(src));
                }
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public static void map(Object src, Object dest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(src, dest);
    }

    public static void map(List src, List dest) {
        for (int i = 0; i < src.size(); i++) {
            map(src.get(i), dest.get(i));
        }
    }

    public static void mapListCustom(List src, List dest) {
        for (int i = 0; i < src.size(); i++) {
            mapCustom(src.get(i), dest.get(i));
        }
    }

    public static <T> List<T> mapList(List src, Class<T> dtoClass) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        List<T> dest = new ArrayList<>();
        for (Object item : src) {
            T destItem = dtoClass.getConstructor().newInstance();
            mapCustom(item, destItem);
            dest.add(destItem);
        }
        return dest;
    }

    public static <T, V> List<V> mapListLambda(List<T> src, Class<V> dtoClass) {
        return src
                .parallelStream()
                .collect(ArrayList::new,
                        (result, item) -> {
                            V destItem = null;
                            try {
                                destItem = dtoClass.newInstance();
                            } catch (InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            mapCustom(item, destItem);
                            result.add(destItem);
                        }, ArrayList::addAll);
    }

    public static void mapListMapToDto(List<Map<String, Object>> src, List dest, Class dtoClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (int i = 0; i < src.size(); i++) {
            Map<String, Object> map = src.get(i);
            Object objectDto = dtoClass.getConstructor().newInstance();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String parsedField = DbFieldsParser.getDtoFieldFromDb(entry.getKey());
                String dtoField = parsedField == null ? entry.getKey() : parsedField;
                Field field = null;
                try {
                    field = dtoClass.getDeclaredField(dtoField);
                } catch (NoSuchFieldException e) {
                    continue;
                }
                field.setAccessible(true);
                boolean notSet = true;
                Long idLong = DbFieldsParser.bigIntIdtoLong(entry.getValue());
                if (idLong != null) {
                    field.set(objectDto, idLong);
                    notSet = false;
                }
                Byte bool = DbFieldsParser.booleanToByte(entry.getValue());
                if (bool != null) {
                    field.set(objectDto, bool);
                    notSet = false;
                }
                if (notSet) {
                    field.set(objectDto, entry.getValue());
                }

            }
            dest.add(objectDto);
        }
    }

    public static List prepareAndMapList(List list, Class objectClass) throws InstantiationException, IllegalAccessException {
        List dtoList = null;
        dtoList = prepareForMap(list.size(), objectClass);
        mapListCustom(list, dtoList);
        return dtoList;
    }

    public static List prepareForMap(int size, Class objectClass) throws IllegalAccessException, InstantiationException {
        List dtoList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            dtoList.add(objectClass.newInstance());
        }
        return dtoList;
    }
}
