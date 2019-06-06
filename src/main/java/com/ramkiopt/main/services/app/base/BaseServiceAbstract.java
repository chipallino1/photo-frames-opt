package com.ramkiopt.main.services.app.base;

import com.ramkiopt.main.services.utils.EntitiesGetterService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import com.ramkiopt.main.services.utils.ReflectionUtilsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceAbstract<T, Id> implements CrudService<T, Id>, EntitiesGetterService<T, Id>,
        ObjectMapper, ReflectionUtilsService {

    protected JpaRepository<T, Id> jpaRepository;
    private final String GET_ID_METHOD = "getId";
    protected Class<T> tClass;
    protected Class dtoClass;

    public JpaRepository<T, Id> getJpaRepository() {
        return jpaRepository;
    }

    protected abstract void setJpaRepository(JpaRepository<T, Id> jpaRepository);

    protected abstract void setClass(Class<T> tClass);

    protected abstract void setClassDto(Class dtoClass);

    @Override
    public T createInDb(T t) {
        return jpaRepository.save(t);
    }

    @Override
    public T updateInDb(Id id, T t) {
        Optional<T> optFromDb = jpaRepository.findById(id);
        if (optFromDb.isPresent()) {
            T tFromDb = optFromDb.get();
            map(t, tFromDb);
            return jpaRepository.save(tFromDb);
        }
        return null;
    }

    @Override
    public T readInDb(Id id) {
        Optional<T> optFromDb = jpaRepository.findById(id);
        return optFromDb.orElse(null);
    }

    @Override
    public void deleteInDb(Id id) {
        Optional<T> optFromDb = jpaRepository.findById(id);
        optFromDb.ifPresent(t -> jpaRepository.delete(t));
    }

    @Override
    public List<T> getAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<T> getAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).getContent();
    }

    @Override
    public T getById(Id id) {
        Optional<T> optFromDb = jpaRepository.findById(id);
        return optFromDb.orElse(null);
    }

    protected Object tryCreate(Object dto) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        T entity = createNewInstance(tClass);
        mapCustom(dto, entity);
            createInDb(entity);
        Object id = invokeMethod(GET_ID_METHOD, tClass, entity);
        if (id == null) {
            return null;
        }
        mapCustom(entity, dto);
        return dto;
    }

    protected Object tryGetById(Id id) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        T entity = getById(id);
        Object dto = createNewInstance(dtoClass);
        mapCustom(dto, entity);
        return dto;
    }
}
