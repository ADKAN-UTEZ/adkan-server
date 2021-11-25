package com.adkan.adkan.roles;

import com.adkan.adkan.common.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/role")
public class RoleController implements ControllerInterface<Role> {

    @Autowired
    private RoleService roleService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<Role>> getAll() {
            return new ResponseEntity(roleService.getAll(), HttpStatus.OK);
        }
        @Override
        @GetMapping("/{id}")
        public ResponseEntity<Role> getById(@PathVariable("id") Integer id) {
            return roleService.getById(id)
                    .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }


        @Override
        @PostMapping("")
        public ResponseEntity<Role> save(@RequestBody Role entity) {
            return new ResponseEntity<>(roleService.save(entity), HttpStatus.CREATED);
        }

        @Override
        @PutMapping("")
        public ResponseEntity<Role> update(@RequestBody Role entity) {
            return roleService.update(entity)
                    .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @Override
        @PatchMapping("/{id}")
        public ResponseEntity<Role> partialUpdate(@PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
            return roleService.partialUpdate(id, fields)
                    .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }


        @Override
        @DeleteMapping("/{id}")
        public ResponseEntity<Role> delete(@PathVariable("id") Integer id) {
            return roleService.delete(id).map(entity -> new ResponseEntity(entity, HttpStatus.OK))
                    .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
        }
}
