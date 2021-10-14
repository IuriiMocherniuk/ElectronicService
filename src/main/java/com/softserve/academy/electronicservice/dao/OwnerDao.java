package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerDao {

    long save(Owner owner);

    Owner get(long id);

    List<Owner> getAll();

    void update(long id, Owner owner);

    void delete(long id);

}
