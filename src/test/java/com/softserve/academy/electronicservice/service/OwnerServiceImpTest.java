package com.softserve.academy.electronicservice.service;

import com.softserve.academy.electronicservice.dao.OwnerDao;
import com.softserve.academy.electronicservice.model.Owner;
import com.softserve.academy.electronicservice.service.OwnerService;
import com.softserve.academy.electronicservice.service.OwnerServiceImp;
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


public class OwnerServiceImpTest {

    @Mock
    private OwnerDao ownerDao;
    private OwnerService ownerService;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        ownerService = new OwnerServiceImp(ownerDao);
    }

    @AfterMethod
    public void after() {
        verifyNoMoreInteractions(ownerDao);
    }

    @Test
    public void saveTest() {
        long expectedId = 1;
        Owner owner = new Owner("Ivan", "Ivanov", "password");
        when(ownerDao.save(owner)).thenReturn(owner);
        Owner actualOwner = ownerService.save(owner);
        assertEquals(owner, actualOwner);
        verify(ownerDao, times(1)).save(owner);
    }

    @Test
    public void updateTest() {
        long expectedId = 1;
        Owner owner = new Owner("Ivan", "Ivanov", "password");
        when(ownerDao.update(expectedId, owner)).thenReturn(owner);
        assertEquals(owner, ownerDao.update(expectedId, owner));
        verify(ownerDao, times(1)).update(expectedId, owner);
    }

    @Test
    public void deleteTest() {
        long expectedId = 1;
        Owner owner = new Owner("Ivan", "Ivanov", "password");
        when(ownerDao.save(owner)).thenReturn(owner);
        doNothing().when(ownerDao).delete(expectedId);
        ownerService.delete(expectedId);
        verify(ownerDao, times(1)).delete(expectedId);
    }

    @Test
    public void getAllTest() {
        Owner owner1 = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        Owner owner2 = new Owner("Petro", "Ivanov", UUID.randomUUID().toString());
        List<Owner> actualList = Arrays.asList(owner1, owner2);
        when(ownerDao.getAll()).thenReturn(actualList);
        List<Owner> expectedList = ownerService.getAll();
        assertThat(expectedList)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(1)
                .contains(owner1, owner2);
        verify(ownerDao, times(1)).getAll();
    }

    @Test
    public void getTest() {
        long expectedId = 1;
        Owner owner = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
        when(ownerDao.get(expectedId)).thenReturn(owner);
        Owner expectedOwner = ownerService.get(expectedId);
        assertEquals(owner, expectedOwner);
        verify(ownerDao, times(1)).get(expectedId);
    }

}
