package com.softserve.academy.electronicservice.service;

import com.softserve.academy.electronicservice.model.Device;

import java.util.List;

public interface DeviceService {

        Device save(Device device);

        Device get(long id);

        List<Device> getAll();

        Device update(long id, Device device);

        void delete(long id);

}
