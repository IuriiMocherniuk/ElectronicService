package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.model.Device;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface DeviceDao {

       long save(Device device);

        Device get(long id);

        List<Device> list();

        void update(long id, Device device);

        void delete(long id);

}
