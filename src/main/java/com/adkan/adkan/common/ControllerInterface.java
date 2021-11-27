package com.adkan.adkan.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public interface ControllerInterface <T>{
    /** Controller Interface
     *  This interface helps to implement all repeated method each class
     * methods of a CRUD.
     *  In this part we don't use classes such as Optional because we handle
     * the response through the Object ResponseEntity accompanied by an HTTP status.
     */
    ResponseEntity getAll();
    ResponseEntity getById(Integer id);
    ResponseEntity save(T entity);
    ResponseEntity update(T entity);
    ResponseEntity partialUpdate(Integer id, Map<Object,Object> fields);
    ResponseEntity delete(Integer id);
    ResponseEntity errorResponse(Exception exception,  Map<String, Object> response);
}

