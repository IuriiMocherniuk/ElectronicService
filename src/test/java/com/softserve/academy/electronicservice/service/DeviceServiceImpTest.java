package com.softserve.academy.electronicservice.service;

import com.softserve.academy.electronicservice.dao.DeviceDao;
import com.softserve.academy.electronicservice.model.Device;

import com.softserve.academy.electronicservice.model.Owner;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class DeviceServiceImpTest {

    @Mock
    private DeviceDao deviceDao;
    private DeviceService deviceService;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        deviceService = new DeviceServiceImp(deviceDao);
        Owner owner1 = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Owner owner2 = new Owner("Petro", "Ivanov", UUID.randomUUID().toString());
        Device device = new Device("Phone", "iPhone", "1111_222_333_444", owner1, "used");
    }

    @AfterMethod
    public void after() {

        verifyNoMoreInteractions(deviceDao);
    }

    @Test
    public void saveTest() {
        long expectedId = 1;
        Owner owner1 = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Owner owner2 = new Owner("Petro", "Ivanov", UUID.randomUUID().toString());
        Device device = new Device("Phone", "iPhone", "1111_222_333_444", owner1, "used");
        when(deviceDao.save(device)).thenReturn(device);
        Device actualDevice = deviceService.save(device);
        assertEquals(device, actualDevice);
        verify(deviceDao, times(1)).save(device);
    }

    @Test
    public void updateTest() {
        long expectedId = 1;
        Owner owner1 = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Device device = new Device("Phone", "iPhone", "1111_222_333_444", owner1, "used");
        when(deviceDao.update(expectedId, device)).thenReturn(device);
        when(deviceDao.update(expectedId, device)).thenReturn(device);
        assertEquals(device, deviceDao.update(expectedId, device));
        verify(deviceDao, times(1)).update(expectedId, device);
    }

    @Test
    public void deleteTest() {
        long expectedId = 1;
        Owner owner1 = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Device device = new Device("Phone", "iPhone", "1111_222_333_444", owner1, "used");
        when(deviceDao.save(device)).thenReturn(device);
        doNothing().when(deviceDao).delete(expectedId);
        deviceService.delete(expectedId);
        verify(deviceDao, times(1)).delete(expectedId);
    }

    @Test
    public void getAllTest() {
        Owner owner1 = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Device device1 = new Device("Phone", "iPhone10", "1111_222_333_444", owner1, "used");
        Device device2 = new Device("Phone", "iPhone11", "1111_222_333_444", owner1, "used");
        List<Device> actualList = Arrays.asList(device1, device2);
        when(deviceDao.getAll()).thenReturn(actualList);
        List<Device> expectedList = deviceService.getAll();
        assertThat(expectedList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .contains(device1, device2);
        verify(deviceDao, times(1)).getAll();
    }

    @Test
    public void getTest() {
        long expectedId = 1;
        Owner owner1 = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Device device = new Device("Phone", "iPhone10", "1111_222_333_444", owner1, "used");
        when(deviceDao.get(expectedId)).thenReturn(device);
        Device expectedDevice = deviceService.get(expectedId);
        assertEquals(device, expectedDevice);
        verify(deviceDao, times(1)).get(expectedId);
    }
}
