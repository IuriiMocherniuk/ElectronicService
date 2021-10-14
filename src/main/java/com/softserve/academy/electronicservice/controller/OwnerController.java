package com.softserve.academy.electronicservice.controller;


import com.softserve.academy.electronicservice.model.Owner;
import com.softserve.academy.electronicservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /*---Add new owner---*/
    @PostMapping("/owner")
    public ResponseEntity<?> save(@RequestBody Owner owner) {
        System.out.println("the json value of owner is :::::: " + owner);
        long id = ownerService.save(owner);
        return ResponseEntity.ok().body("New Owner has been saved with ID:" + id);
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
        List<Owner> owners = ownerService.getAll();
        return ResponseEntity.ok().body(owners);
    }

    /*---Update a owner by id---*/
    @PutMapping("/owner/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Owner owner) {
        ownerService.update(id, owner);
        return ResponseEntity.ok().body("Owner has been updated successfully.");
    }

    /*---Delete a owner by id---*/
    @DeleteMapping("/owner/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        ownerService.delete(id);
        return ResponseEntity.ok().body("Owner has been deleted successfully.");
    }


}
