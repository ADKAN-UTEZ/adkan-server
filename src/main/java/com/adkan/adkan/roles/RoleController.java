package com.adkan.adkan.roles;

import com.adkan.adkan.common.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController implements ControllerInterface<Role> {

    @Autowired
    private RoleService roleService;


    @Override
    @GetMapping("")
    public ResponseEntity getAll() {
        Map<String, Object> response = new HashMap<>();
        List<Role> entities = roleService.getAll();
        try {
            response.put("message", "objects found successfully");
            response.put("error", false);
            response.put("code", HttpStatus.OK.value());
            response.put("body", entities);
            response.put("count", entities.size());
            if(entities.isEmpty()){
                response.put("message", "list is empty");
            }
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception exception){
            return errorResponse(exception, response);
        }

    }
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Role> entity = roleService.getById(id);
            response.put("message", "object found successfully");
            response.put("error", false);
            response.put("code", HttpStatus.OK.value());
            response.put("body", entity);
            if(!entity.isPresent()){
                response.put("message", "object not found ");
                response.put("code", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity(response,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception exception){
            return errorResponse(exception, response);
        }
    }


    @Override
    @PostMapping("")
    public ResponseEntity save(@RequestBody Role entity) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("message", "created successfully");
            response.put("error", false);
            response.put("code", HttpStatus.OK.value());
            response.put("body", roleService.save(entity));
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception exception){
            return errorResponse(exception, response);
        }
    }

    @Override
    @PutMapping("")
    public ResponseEntity update(@RequestBody Role entity) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Role> updatedEntity =  roleService.update(entity);
            response.put("message", "updated successfully");
            response.put("error", false);
            response.put("code", HttpStatus.OK.value());
            response.put("body",updatedEntity);
            if (!updatedEntity.isPresent()){
                response.put("message", "object not found");
                response.put("code", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception exception) {
            return errorResponse(exception, response);
        }
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity partialUpdate(@PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Role> updatedEntity =  roleService.partialUpdate(id, fields);
            response.put("message", "partial updated successfully");
            response.put("error", false);
            response.put("code", HttpStatus.OK.value());
            response.put("body",updatedEntity);
            if (!updatedEntity.isPresent()){
                response.put("message", "object not found");
                response.put("code", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception exception) {
            return errorResponse(exception, response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Role> deletedEntity =  roleService.delete(id);
            response.put("message", "deleted successfully");
            response.put("error", false);
            response.put("code", HttpStatus.OK.value());
            response.put("body",deletedEntity);
            if (!deletedEntity.isPresent()){
                response.put("message", "object not found");
                response.put("code", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception exception) {
            return errorResponse(exception, response);
        }
    }

    @Override
    public ResponseEntity errorResponse(Exception exception, Map<String, Object> response) {
        response.put("message", exception.getCause().getCause().getMessage());
        response.put("error", true);
        response.put("code", HttpStatus.CONFLICT.value());
        response.put("body", null);
        return new ResponseEntity(response, HttpStatus.CONFLICT);
    }


}
