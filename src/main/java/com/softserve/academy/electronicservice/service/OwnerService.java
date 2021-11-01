package com.softserve.academy.electronicservice.service;

import com.softserve.academy.electronicservice.model.Owner;

import java.util.List;

public interface OwnerService {

    Owner save(Owner owner);

    Owner get(Long id);

    List<Owner> getAll();

    Owner update(Long id, Owner owner);

    void delete(Long id);
}
