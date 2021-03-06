package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.model.Device;
import com.softserve.academy.electronicservice.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDao {

    Device save(Device device);

    Device get(long id);

    List<Device> getAll();

//    List<Device> getByOwner(Owner owner);

    Device update(long id, Device device);

    void delete(long id);

}
