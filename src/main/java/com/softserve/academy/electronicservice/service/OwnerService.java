package com.softserve.academy.electronicservice.service;

import com.softserve.academy.electronicservice.model.Owner;

import java.util.List;

public interface OwnerService {
    long save(Owner book);

    Owner get(long id);

    List<Owner> getAll();

    void update(long id, Owner owner);

    void delete(long id);
}
