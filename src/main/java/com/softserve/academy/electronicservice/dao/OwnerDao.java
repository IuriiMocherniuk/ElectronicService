package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.model.Owner;

import java.util.List;

public interface OwnerDao {

       long save(Owner owner);

        Owner get(long id);

        List<Owner> list();

        void update(long id, Owner owner);

        void delete(long id);

}
