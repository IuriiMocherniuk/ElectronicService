package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.configuration.AppConfig;
import com.softserve.academy.electronicservice.dao.DeviceDao;
import com.softserve.academy.electronicservice.model.Device;
import com.softserve.academy.electronicservice.model.Owner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class DeviceDaoImpTest {

    private static DeviceDao deviceDao;

    @BeforeClass
    public static void init() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        deviceDao = applicationContext.getBean(DeviceDao.class);
    }

    @Test
    public void saveTest() {
        Owner owner = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Device device = new Device("Phone", "iPhone", "1111_222_333_444", owner, "used");
        Device device1 = deviceDao.save(device);
        assertEquals(device, device1);
        delete(device.getId());
    }

    @Test
    public void updateTest() {
        Owner owner = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Owner owner2 = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Device device = new Device("Phone", "iPhone", "1111_222_333_4445", owner, "used");
        Device actualDevice = deviceDao.save(device);
        actualDevice.setOwner(owner2);
        deviceDao.update(actualDevice.getId(), actualDevice);
        Device deviceUpdate = deviceDao.get(actualDevice.getId());
        assertEquals(actualDevice, deviceUpdate);
        delete(actualDevice.getId());
    }

    @Test
    public void deleteTest() {
        Owner owner = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Device device = new Device("Phone", "iPhone", "1111_222_333_4443", owner, "used");
        Device actualDevice = deviceDao.save(device);
        assertEquals(device, actualDevice);
        delete(actualDevice.getId());
        Device deleteDevice = deviceDao.get(actualDevice.getId());
        assertNull(deleteDevice);
    }

    @Test
    public void getAllTest() {
        Owner owner = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Device device1 = new Device("Phone", "iPhone10", "1111_222_333_4445", owner, "used");
        Device device2 = new Device("Phone", "iPhone11", "1111_222_333_444", owner, "used");
        Device savedDevice1 = deviceDao.save(device1);
        Device savedDevice2 = deviceDao.save(device2);
        List<Device> actualList = deviceDao.getAll();
        assertThat(actualList)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(1)
                .contains(device1, device2);
        delete(savedDevice1.getId(), savedDevice2.getId());

    }

    private void delete(long... ids) {
        Arrays.stream(ids).forEach(deviceDao::delete);
    }
}
