package com.adkan.adkan.dates;

import com.adkan.adkan.common.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class AdkanDateService implements ServiceInterface<AdkanDate> {

    @Autowired
    private AdkanDateRepository adkanDateRepository;

    @Override
    public List<AdkanDate> getAll() {
        return (List<AdkanDate>) adkanDateRepository.findAll();
    }

    @Override
    public Optional<AdkanDate> getById(int id) {
        return adkanDateRepository.findById(id);
    }

    @Override
    public AdkanDate save(AdkanDate entity) {
        return adkanDateRepository.save(entity);
    }

    @Override
    public Optional<AdkanDate> update(AdkanDate entity) {
        Optional<AdkanDate> updatedEntity = Optional.empty();
        updatedEntity = adkanDateRepository.findById(entity.getId());
        if (!updatedEntity.isEmpty())
            adkanDateRepository.save(entity);
        return updatedEntity;
    }

    @Override
    public Optional<AdkanDate> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            AdkanDate entity = getById(id).get();
            if (entity == null) {
                return Optional.empty();
            }
            Optional<AdkanDate> updatedEntity = Optional.empty();
            // Map key is field name, v is value
            fields.forEach((updatedField, value) -> {
                // use reflection to get fields updatedField on manager and set it to value updatedField
                Field field = ReflectionUtils.findField(AdkanDate.class, (String) updatedField);
                field.setAccessible(true);
                ReflectionUtils.setField(field, entity, value);
            });
            adkanDateRepository.save(entity);
            updatedEntity = Optional.of(entity);
            return updatedEntity;
        } catch (Exception exception) {
            System.err.println(exception);
            return Optional.empty();
        }
    }

    @Override
    public Optional<AdkanDate> delete(int id) {
        Optional<AdkanDate> entity = getById(id);
        if(entity.isPresent()){
            adkanDateRepository.delete(entity.get());
        }
        return entity;
    }
}