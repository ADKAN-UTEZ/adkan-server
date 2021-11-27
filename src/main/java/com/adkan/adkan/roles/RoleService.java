package com.adkan.adkan.roles;


import com.adkan.adkan.common.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class RoleService implements ServiceInterface<Role> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Optional<Role> getById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public Optional<Role> update(Role entity) {
        Optional<Role> updatedEntity = Optional.empty();
        updatedEntity = roleRepository.findById(entity.getId());
        if (!updatedEntity.isEmpty())
            roleRepository.save(entity);
        return updatedEntity;
    }

    @Override
    public Optional<Role> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Role entity = getById(id).get();
            if (entity == null) {
                return Optional.empty();
            }
            Optional<Role> updatedEntity = Optional.empty();
            // Map key is field name, v is value
            fields.forEach((updatedField, value) -> {
                // use reflection to get fields updatedField on manager and set it to value updatedField
                Field field = ReflectionUtils.findField(Role.class, (String) updatedField);
                field.setAccessible(true);
                ReflectionUtils.setField(field, entity, value);
            });
            roleRepository.save(entity);
            updatedEntity = Optional.of(entity);
            return updatedEntity;
        } catch (Exception exception) {
            System.err.println(exception);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Role> delete(int id) {
        Optional<Role> entity = getById(id);
        if(entity.isPresent()){
            roleRepository.delete(entity.get());
        }
        return entity;
    }
}
