package com.softserve.academy.electronicservice.controller;


import com.softserve.academy.electronicservice.model.Owner;
import com.softserve.academy.electronicservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    /*---Add new owner---*/
    @PostMapping("/owner")
    public ResponseEntity<?> save(@RequestBody Owner owner) {
        System.out.println("the json value of owner is :::::: "+owner);
        long id = ownerService.save(owner);
        return ResponseEntity.ok().body("New Book has been saved with ID:" + id);
    }

    /*---Get a owner by id---*/
    @GetMapping("/owner/{id}")
    public ResponseEntity<Owner> get(@PathVariable("id") long id) {
        Owner owner = ownerService.get(id);
        return ResponseEntity.ok().body(owner);
    }

    /*---get all owners---*/
    @GetMapping("/owner")
    public ResponseEntity<List<Owner>> list() {
        List<Owner> owners = ownerService.list();
        return ResponseEntity.ok().body(owners);
    }

    /*---Update a owner by id---*/
    @PutMapping("/owner/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Owner owner) {
        ownerService.update(id, owner);
        return ResponseEntity.ok().body("Book has been updated successfully.");
    }

    /*---Delete a owner by id---*/
    @DeleteMapping("/owner/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        ownerService.delete(id);
        return ResponseEntity.ok().body("Book has been deleted successfully.");
    }


}
