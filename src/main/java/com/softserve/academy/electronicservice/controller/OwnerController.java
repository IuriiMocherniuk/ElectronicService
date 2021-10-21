package com.softserve.academy.electronicservice.controller;


import com.softserve.academy.electronicservice.model.Owner;
import com.softserve.academy.electronicservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//@CrossOrigin(origins = "*")

@RestController
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {

        this.ownerService = ownerService;
    }

    /*---Add new owner---*/
    @PostMapping("/owner/add")
    public ResponseEntity<?> save(@RequestBody Owner owner) {
        System.out.println("the json value of owner is :::::: " + owner);
        long id = ownerService.save(owner);
//        return ResponseEntity.ok().body(owner);
        return ResponseEntity.ok().body("New Owner has been created with ID:" + id);
    }

    /*---Get a owner by id---*/
    @GetMapping("/owner/{id}")
    public ResponseEntity<Owner> get(@PathVariable("id") long id) {
        Owner owner = ownerService.get(id);
        return ResponseEntity.ok().body(owner);
    }

    /*---get all owners---*/
    @GetMapping("/owner")
    public ResponseEntity<List<Owner>> getAll() {
        List<Owner> owners = ownerService.getAll();
        return ResponseEntity.ok().body(owners);
    }
//    @GetMapping("/owner")
//    public void processDateTime(@RequestParam("start")
//                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//                                        LocalDateTime date) {
//
//    }

    /*---Update a owner by id---*/
    @PutMapping("/owner/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Owner owner) {
        ownerService.update(id, owner);
        return ResponseEntity.ok().body("Owner with Id = "+ owner.getId()+" has been updated successfully." );
    }

    /*---Delete a owner by id---*/
    @DeleteMapping("/owner/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        ownerService.delete(id);
            return ResponseEntity.ok().body("Owner with Id = "+ id+" has been deleted successfully.");
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> home() {
        // List<Owner> owners = ownerService.getAll();
        return ResponseEntity.ok().body(Arrays.asList("Hello project", "Electronic Service"));
    }



}
