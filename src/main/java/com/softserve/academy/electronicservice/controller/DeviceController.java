package com.softserve.academy.electronicservice.controller;

import com.softserve.academy.electronicservice.model.Device;
import com.softserve.academy.electronicservice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {

        this.deviceService = deviceService;
    }

    /*---Add new device---*/
    @PostMapping("/device/add")
    public ResponseEntity<Device> save(@RequestBody Device device) {
        System.out.println("the json value of device is :::::: " + device);
        Device deviceAdd = deviceService.save(device);
        return ResponseEntity.ok().body(deviceAdd);
    }

    /*---Get a owner by id---*/
    @GetMapping("/device/{id}")
    public ResponseEntity<Device> get(@PathVariable("id") long id) {
        Device device = deviceService.get(id);
        return ResponseEntity.ok().body(device);
    }

    /*---get all owners---*/
    @GetMapping("/device")
    public ResponseEntity<List<Device>> getAll() {
        List<Device> devices = deviceService.getAll();
        return ResponseEntity.ok().body(devices);
    }

    /*---Update a owner by id---*/
    @PutMapping("/device/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Device device) {
//       deviceService.update(id, device);
       Device deviceUpdate =  deviceService.update(id, device);
//        return ResponseEntity.ok().body(device);
        return ResponseEntity.ok().body("Device has been updated: "+ deviceUpdate);
    }

    /*---Delete a owner by id---*/
    @DeleteMapping("/device/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        deviceService.delete(id);
        return ResponseEntity.ok().body("Device with Id = "+ id+" has been deleted successfully.");
    }


}

