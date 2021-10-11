package com.softserve.academy.electronicservice.controller;

import com.softserve.academy.electronicservice.model.Device;
import com.softserve.academy.electronicservice.model.Owner;
import com.softserve.academy.electronicservice.service.DeviceService;
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
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /*---Add new device---*/
    @PostMapping("/device")
    public ResponseEntity<?> save(@RequestBody Device device) {
        System.out.println("the json value of device is :::::: "+device);
        long id = deviceService.save(device);
        return ResponseEntity.ok().body("New Device has been saved with ID:" + id);
    }

    /*---Get a owner by id---*/
    @GetMapping("/device/{id}")
    public ResponseEntity<Device> get(@PathVariable("id") long id) {
        Device device = deviceService.get(id);
        return ResponseEntity.ok().body(device);
    }

    /*---get all owners---*/
    @GetMapping("/device")
    public ResponseEntity<List<Device>> list() {
        List<Device> devices = deviceService.list();
        return ResponseEntity.ok().body(devices);
    }

    /*---Update a owner by id---*/
    @PutMapping("/device/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Device device) {
        deviceService.update(id, device);
        return ResponseEntity.ok().body("Device has been updated successfully.");
    }

    /*---Delete a owner by id---*/
    @DeleteMapping("/device/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        deviceService.delete(id);
        return ResponseEntity.ok().body("Device has been deleted successfully.");
    }


}

