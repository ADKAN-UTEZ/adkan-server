package com.adkan.adkan.employee;


import com.adkan.adkan.common.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeService implements ServiceInterface<Employee> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public Optional<Employee> update(Employee entity) {
        Optional<Employee> updatedEntity = Optional.empty();
        updatedEntity = employeeRepository.findById(entity.getId());
        if (!updatedEntity.isEmpty())
            employeeRepository.save(entity);
        return updatedEntity;
    }

    @Override
    public Optional<Employee> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Employee entity = getById(id).get();
            if (entity == null) {
                return Optional.empty();
            }
            Optional<Employee> updatedEntity = Optional.empty();
            // Map key is field name, v is value
            fields.forEach((updatedField, value) -> {
                // use reflection to get fields updatedField on manager and set it to value updatedField
                Field field = ReflectionUtils.findField(Employee.class, (String) updatedField);
                field.setAccessible(true);
                ReflectionUtils.setField(field, entity, value);
            });
            employeeRepository.save(entity);
            updatedEntity = Optional.of(entity);
            return updatedEntity;
        } catch (Exception exception) {
            System.err.println(exception);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> delete(int id) {
        Optional<Employee> entity = getById(id);
        if(entity.isPresent()){
            employeeRepository.delete(entity.get());
        }
        return entity;
    }
}