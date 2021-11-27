package com.adkan.adkan.board;

import com.adkan.adkan.common.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** URLS
 * we try to keep the urls as simple as possible, using the HTTP request methods
 * (get, post, put, patch, delete)
 */
@RestController
@RequestMapping("/board")
public class BoardController implements ControllerInterface<Board> {

    @Autowired
    private BoardService boardService;


    @Override
    @GetMapping("")
    public ResponseEntity getAll() {
        Map<String, Object> response = new HashMap<>();
        List<Board> entities = boardService.getAll();
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
    public ResponseEntity<Board> getById(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Board> entity = boardService.getById(id);
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
    public ResponseEntity save(@RequestBody Board entity) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("message", "created successfully");
            response.put("error", false);
            response.put("code", HttpStatus.OK.value());
            response.put("body", boardService.save(entity));
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception exception){
            return errorResponse(exception, response);
        }
    }

    @Override
    @PutMapping("")
    public ResponseEntity update(@RequestBody Board entity) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Board> updatedEntity =  boardService.update(entity);
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
            Optional<Board> updatedEntity =  boardService.partialUpdate(id, fields);
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
            Optional<Board> deletedEntity =  boardService.delete(id);
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
