package com.softserve.electronicservice.service;

import com.softserve.academy.electronicservice.dao.OwnerDao;
import com.softserve.academy.electronicservice.model.Owner;
import com.softserve.academy.electronicservice.service.OwnerService;
import com.softserve.academy.electronicservice.service.OwnerServiceImp;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;


public class OwnerServiceImpTest {

    @Mock
    private OwnerDao ownerDao;
    private OwnerService ownerService;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);
        ownerService = new OwnerServiceImp(ownerDao);
    }

    @AfterMethod
    public void after(){
        verifyNoMoreInteractions(ownerDao);
    }

    @Test
    public void saveTest() {
        long expectedId = 1;
        Owner owner = new Owner("Ivan", "Ivanov","password");
        when(ownerDao.save(owner)).thenReturn(expectedId);
        long actualId = ownerService.save(owner);
        assertEquals(expectedId, actualId);
        verify(ownerDao,times(1)).save(owner);
    }

    @Test
    public void updateTest() {
        long expectedId = 1;
        Owner owner = new Owner("Ivan", "Ivanov","password");
        when(ownerDao.save(owner)).thenReturn(expectedId);
        doNothing().when(ownerDao).update(expectedId, owner);
        ownerService.update(expectedId, owner);
        verify(ownerDao,times(1)).update(expectedId, owner);
    }



}
