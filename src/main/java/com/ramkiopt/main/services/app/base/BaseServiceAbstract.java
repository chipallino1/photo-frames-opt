package com.ramkiopt.main.services.app.base;

import com.ramkiopt.main.services.utils.EntitiesGetterService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import com.ramkiopt.main.services.utils.ReflectionUtilsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceAbstract<T, Id> implements CrudService<T, Id>, EntitiesGetterService<T, Id> {

    protected JpaRepository<T, Id> jpaRepository;
    protected Class<T> tClass;
    protected Class<Object> dtoClass;

    protected abstract void setJpaRepository(JpaRepository<T, Id> jpaRepository);

    protected abstract void setClass(Class<T> tClass);

    protected abstract void setClassDto(Class<Object> dtoClass);

    @Override
    public T createInDb(T t) {
        return jpaRepository.save(t);
    }

    @Override
    public T updateInDb(Id id, T t) {
        Optional<T> optFromDb = jpaRepository.findById(id);
        if (optFromDb.isPresent()) {
            T tFromDb = optFromDb.get();
            ObjectMapper.map(t, tFromDb);
            return jpaRepository.save(tFromDb);
        }
        return null;
    }

    @Override
    public T readFromDb(Id id) {
        return jpaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteInDb(Id id) {
        jpaRepository.findById(id).ifPresent(t -> jpaRepository.delete(t));
    }

    @Override
    public List<T> getAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<T> getAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).getContent();
    }

    protected Object tryCreate(Object dto) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        T entity = ReflectionUtilsService.createNewInstance(tClass);
        ObjectMapper.mapCustom(dto, entity);
        createInDb(entity);
        ObjectMapper.mapCustom(entity, dto);
        return dto;
    }

    protected Object tryGetById(Id id) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        T entity = readFromDb(id);
        Object dto = ReflectionUtilsService.createNewInstance(dtoClass);
        ObjectMapper.mapCustom(dto, entity);
        return dto;
    }
}
