package com.softserve.academy.electronicservice.service;

import com.softserve.academy.electronicservice.model.Device;

import java.util.List;

public interface DeviceService {

        Device save(Device device);

        Device get(Long id);

        List<Device> getAll();

        Device update(Long id, Device device);

        void delete(Long id);

}
