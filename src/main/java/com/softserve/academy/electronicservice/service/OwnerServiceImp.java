package com.softserve.academy.electronicservice.service;

import com.softserve.academy.electronicservice.dao.OwnerDao;
import com.softserve.academy.electronicservice.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OwnerServiceImp implements OwnerService{

    @Autowired
    private OwnerDao ownerDao;

    @Transactional
    @Override
    public long save(Owner owner) {
        return ownerDao.save(owner);
    }

    @Override
    public Owner get(long id) {
        return ownerDao.get(id);
    }

    @Override
    public List<Owner> list() {
        return ownerDao.list();
    }

    @Transactional
    @Override
    public void update(long id, Owner owner) {
        ownerDao.update(id, owner);

    }

    @Transactional
    @Override
    public void delete(long id) {
        ownerDao.delete(id);

    }
}