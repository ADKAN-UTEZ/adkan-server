package com.adkan.adkan.board;

import com.adkan.adkan.common.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/")
    public ResponseEntity<List<Board>> getAll() {
        return new ResponseEntity(boardService.getAll(), HttpStatus.OK);
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Board> getById(@PathVariable("id") Integer id) {
        return boardService.getById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Override
    @PostMapping("/")
    public ResponseEntity<Board> save(@RequestBody Board entity) {
        return new ResponseEntity<>(boardService.save(entity), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Board> update(@RequestBody Board entity) {
        return boardService.update(entity)
                .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Board> partialUpdate(@PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
        return boardService.partialUpdate(id, fields)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Board> delete(@PathVariable("id") Integer id) {
        return boardService.delete(id).map(entity -> new ResponseEntity(entity, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
