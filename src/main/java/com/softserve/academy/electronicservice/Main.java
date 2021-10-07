package com.softserve.academy.electronicservice;

import com.softserve.academy.electronicservice.dao.OwnerDao;
import com.softserve.academy.electronicservice.dao.OwnerDaoImp;
import com.softserve.academy.electronicservice.model.Owner;

public class Main {
    public static void main(String[] args){
        Owner owner = new Owner("Ivan", "Ivanov", "password1");
       // System.out.println(owner.toString());

        OwnerDao ownerDao = new OwnerDaoImp();
        System.out.println(ownerDao.get(1));

    }
}
