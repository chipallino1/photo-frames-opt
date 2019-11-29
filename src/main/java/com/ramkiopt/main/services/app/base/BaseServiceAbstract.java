package com.ramkiopt.main.services.app.base;

import com.ramkiopt.main.services.utils.EntitiesGetterService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import com.ramkiopt.main.services.utils.ReflectionUtilsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceAbstract<T /* entity */, V /* dto */> implements CrudService<T, V>,
        EntitiesGetterService<T> {

    private static final String STATUS_FIELD_NAME = "status";
    protected JpaRepository<T, Long> jpaRepository;

    protected void setJpaRepository(JpaRepository<T, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public V createInDb(T t, V v) {
        ObjectMapper.mapCustom(v, t);
        jpaRepository.save(t);
        ObjectMapper.mapCustom(t, v);
        return v;
    }

    @Override
    public V updateInDb(Long id, V v) {
        Optional<T> optFromDb = jpaRepository.findById(id);
        if (optFromDb.isPresent()) {
            T tFromDb = optFromDb.get();
            ObjectMapper.mapCustomWithoutNulls(v, tFromDb);
            jpaRepository.save(tFromDb);
            ObjectMapper.mapCustom(tFromDb, v);
            return v;
        }
        return null;
    }

    @Override
    public V readFromDb(Long id, V v) {
        T entity = jpaRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        ObjectMapper.mapCustom(entity, v);
        return v;
    }

    @Override
    public List<T> getAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<T> getAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).getContent();
    }
}
