package com.softserve.academy.electronicservice.service;

import com.softserve.academy.electronicservice.dao.OwnerDao;
import com.softserve.academy.electronicservice.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OwnerServiceImp implements OwnerService {

    private final OwnerDao ownerDao;

    @Autowired
    public OwnerServiceImp(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Transactional
    @Override
    public Owner save(Owner owner) {
        return ownerDao.save(owner);
    }

    @Override
    public Owner get(long id) {
        return ownerDao.get(id);
    }

    @Override
    public List<Owner> getAll() {

        return ownerDao.getAll();
    }

    @Transactional
    @Override
    public Owner update(long id, Owner owner) {
        ownerDao.update(id, owner);
        return owner;
    }

    @Transactional
    @Override
    public void delete(long id) {
        ownerDao.delete(id);

    }
}
