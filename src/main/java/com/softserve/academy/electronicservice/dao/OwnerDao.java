package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.model.Owner;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OwnerDao {

    Owner save(Owner owner);

    Owner get(long id);

    List<Owner> getAll();

    Owner update(long id, Owner owner);

    void delete(long id);

}
