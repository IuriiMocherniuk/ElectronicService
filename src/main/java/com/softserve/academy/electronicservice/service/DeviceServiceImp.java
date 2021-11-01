package com.softserve.academy.electronicservice.service;

import com.softserve.academy.electronicservice.dao.DeviceDao;
import com.softserve.academy.electronicservice.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeviceServiceImp implements DeviceService {

    private final DeviceDao deviceDao;

    @Autowired
    public DeviceServiceImp(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Override
    @Transactional
    public Device save(Device device) {
        deviceDao.save(device);
        return device;
    }

    @Override
    public Device get(long id) {
        return deviceDao.get(id);
    }

    @Override
    public List<Device> getAll() {
        return deviceDao.getAll();
    }

    @Transactional
    @Override
    public Device update(long id, Device device) {
        deviceDao.update(id, device);
        return device;
    }

    @Transactional
    @Override
    public void delete(long id) {
        deviceDao.delete(id);
    }
}
