package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.model.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

//@Transactional
// TODO Change test dao layer
@Repository
public class OwnerDaoImp implements OwnerDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public OwnerDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Owner save(Owner owner) {
       long id = (long) sessionFactory.getCurrentSession().save(owner);
        return sessionFactory.getCurrentSession().get(Owner.class, id);
    }

    @Override
    public Owner get(long id) {
        return sessionFactory.getCurrentSession().get(Owner.class, id);
    }

    @Override
    public List<Owner> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Owner> cq = cb.createQuery(Owner.class);
        Root<Owner> root = cq.from(Owner.class);
        cq.select(root);
        Query<Owner> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Owner update(long id, Owner owner) {
        Session session = sessionFactory.getCurrentSession();
        Owner owner2 = session.byId(Owner.class).load(id);
        owner2.setFirstName(owner.getFirstName());
        owner2.setLastName(owner.getLastName());
        owner2.setPassword(owner.getPassword());
        session.flush();
//        return owner;
        return sessionFactory.getCurrentSession().get(Owner.class, id);
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Owner owner = session.byId(Owner.class).load(id);
        session.delete(owner);
        session.flush();
    }
}
