package com.softserve.electronicservice.dao;

import com.softserve.academy.electronicservice.configuration.AppConfig;
import com.softserve.academy.electronicservice.dao.DeviceDao;
import com.softserve.academy.electronicservice.model.Device;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

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
        Device device = new Device("Phone", "iPhone", 1111_222_333_444L, 1, "used");
        long id = deviceDao.save(device);
        Device device1 = deviceDao.get(id);
        assertEquals(device, device1);
        delete(id);
    }

    @Test
    public void updateTest() {
        Device device = new Device("Phone", "iPhone", 1111_222_333_444L, 1, "used");
        long id = deviceDao.save(device);
        Device actualDevice = deviceDao.get(id);
        actualDevice.setOwnerId(2);
        deviceDao.update(id, actualDevice);
        Device deviceUpdate = deviceDao.get(id);
        assertEquals(actualDevice, deviceUpdate);
        delete(id);
    }

    @Test
    public void deleteTest() {
        Device device = new Device("Phone", "iPhone", 1111_222_333_444L, 1, "used");
        long id = deviceDao.save(device);
        Device actualDevice = deviceDao.get(id);
        assertEquals(device, actualDevice);
        delete(id);
        Device deleteDevice = deviceDao.get(id);
        assertNull(deleteDevice);
    }

    @Test
    public void getAllTest() {


        Device device1 = new Device("Phone", "iPhone10", 1111_222_333_444L, 1, "used");
        Device device2 = new Device("Phone", "iPhone11", 1111_222_333_444L, 2, "used");
        long id1 = deviceDao.save(device1);
        long id2 = deviceDao.save(device2);
        List<Device> actualList = deviceDao.getAll();
        assertThat(actualList)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(1)
                .contains(device1, device2);
        delete(id1, id2);

    }

    private void delete(long... ids) {
        Arrays.stream(ids).forEach(deviceDao::delete);
    }
}
