package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.model.Device;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDao {

       long save(Device device);

        Device get(long id);

        List<Device> getAll();

        void update(long id, Device device);

        void delete(long id);

}
