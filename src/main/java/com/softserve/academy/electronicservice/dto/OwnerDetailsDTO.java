package com.softserve.academy.electronicservice.dto;

import com.softserve.academy.electronicservice.model.Device;
import com.softserve.academy.electronicservice.model.Owner;

import java.time.LocalDateTime;
import java.util.List;


public class OwnerDetailsDTO {

    private long id;


    private String firstName;


    private String lastName;




    private LocalDateTime createdDate;



    private List<Device> devices;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }


    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public static OwnerDetailsDTO toOwnerDetail (Owner owner){
        OwnerDetailsDTO ownerDetailsDTO = new OwnerDetailsDTO();
        ownerDetailsDTO.setFirstName(owner.getFirstName());
        ownerDetailsDTO.setLastName(owner.getLastName());
        ownerDetailsDTO.setId(owner.getId());
        ownerDetailsDTO.setDevices(owner.getDeviceList());
        return ownerDetailsDTO;
    }
}
