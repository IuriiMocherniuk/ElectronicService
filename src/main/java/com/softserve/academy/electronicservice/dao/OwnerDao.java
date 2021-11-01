package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerDao {

    Owner save(Owner owner);

    Owner get(Long id);

    List<Owner> getAll();

    Owner update(Long id, Owner owner);

    void delete(Long id);

}
