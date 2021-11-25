package com.adkan.adkan.common;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ControllerInterface <T>{
    /** Controller Interface
     *  This interface helps to implement all repeated method each class
     * methods of a CRUD.
     *  In this part we don't use classes such as Optional because we handle
     * the response through the Object ResponseEntity accompanied by an HTTP status.
     */
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> getById(Integer id);
    ResponseEntity<T> save(T entity);
    ResponseEntity<T> update(T entity);
    ResponseEntity<T> partialUpdate(Integer id, Map<Object,Object> fields);
    ResponseEntity<T> delete(Integer id);

}

