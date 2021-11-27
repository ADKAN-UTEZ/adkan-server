package com.adkan.adkan.histories;

import com.adkan.adkan.common.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserHistoryService implements ServiceInterface<UserHistory> {

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Override
    public List<UserHistory> getAll() {
        return (List<UserHistory>) userHistoryRepository.findAll();
    }

    @Override
    public Optional<UserHistory> getById(int id) {
        return userHistoryRepository.findById(id);
    }

    @Override
    public UserHistory save(UserHistory entity) {
        return userHistoryRepository.save(entity);
    }

    @Override
    public Optional<UserHistory> update(UserHistory entity) {
        Optional<UserHistory> updatedEntity = Optional.empty();
        updatedEntity = userHistoryRepository.findById(entity.getId());
        if (!updatedEntity.isEmpty())
            userHistoryRepository.save(entity);
        return updatedEntity;
    }

    @Override
    public Optional<UserHistory> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            UserHistory entity = getById(id).get();
            if (entity == null) {
                return Optional.empty();
            }
            Optional<UserHistory> updatedEntity = Optional.empty();
            // Map key is field name, v is value
            fields.forEach((updatedField, value) -> {
                // use reflection to get fields updatedField on manager and set it to value updatedField
                Field field = ReflectionUtils.findField(UserHistory.class, (String) updatedField);
                field.setAccessible(true);
                ReflectionUtils.setField(field, entity, value);
            });
            userHistoryRepository.save(entity);
            updatedEntity = Optional.of(entity);
            return updatedEntity;
        } catch (Exception exception) {
            System.err.println(exception);
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserHistory> delete(int id) {
        Optional<UserHistory> entity = getById(id);
        if(entity.isPresent()){
            userHistoryRepository.delete(entity.get());
        }
        return entity;
    }
}