package com.softserve.academy.electronicservice.service;

import com.softserve.academy.electronicservice.model.Owner;

import java.util.List;

public interface OwnerService {

    Owner save(Owner owner);

    Owner get(long id);

    List<Owner> getAll();

    Owner update(long id, Owner owner);

    void delete(long id);
}
