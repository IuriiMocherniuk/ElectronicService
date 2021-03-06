package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.configuration.AppConfig;
import com.softserve.academy.electronicservice.configuration.WebConfig;
import com.softserve.academy.electronicservice.model.Owner;


//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

// -------------test with  @Transactional in DAO class-------------------
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {
//        AppConfig.class,
//        WebConfig.class
//})
//@WebAppConfiguration
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@RunWith(SpringRunner.class)

    public class OwnerDaoImpTest {
        @Autowired
        private static OwnerDao ownerDao;

        @BeforeClass //TestNG
//        @BeforeAll // Junit
        public static void init() {
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
            ownerDao = applicationContext.getBean(OwnerDao.class);
        }

        @Test
        public void saveTest() {
            Owner owner = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
            Owner owner1 = ownerDao.save(owner);
            assertEquals(owner, owner1);
            delete(owner1.getId());
        }

        @Test
        public void updateTest() {
            Owner owner = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
            Owner actualOwner = ownerDao.save(owner);
           // Owner actualOwner = ownerDao.get(id);
            actualOwner.setFirstName("Pepro");
            Owner ownerUpdate = ownerDao.update(actualOwner.getId(), actualOwner);
            assertEquals(actualOwner, ownerUpdate);
            delete(actualOwner.getId());
        }

        @Test
        public void deleteTest() {
            Owner owner = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());

            Owner actualOwner = ownerDao.save(owner);
            assertEquals(owner, actualOwner);
            delete(actualOwner.getId());
            Owner deleteOwner = ownerDao.get(actualOwner.getId());
            assertNull(deleteOwner);
        }

        @Test
        public void getAllTest() {
            Owner owner1 = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
            Owner owner2 = new Owner("Ivan", "Ivanov", UUID.randomUUID().toString());
            Owner ownerSaved1 = ownerDao.save(owner1);
            Owner ownerSaved2  = ownerDao.save(owner2);
            List<Owner> actualList = ownerDao.getAll();
            assertThat(actualList)
                    .isNotNull()
                    .isNotEmpty()
                    .hasSizeGreaterThan(1)
                    .contains(owner1, owner2);
            delete(ownerSaved1.getId(), ownerSaved2.getId());
        }

        private void delete(long... ids) {
            Arrays.stream(ids).forEach(ownerDao::delete);
        }

    }