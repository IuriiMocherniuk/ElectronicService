package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.model.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository
public class OwnerDaoImp implements OwnerDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public OwnerDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long save(Owner owner) {
        sessionFactory.getCurrentSession().save(owner);
        return owner.getId();
    }

    @Override
    public Owner get(long id) {
        return sessionFactory.getCurrentSession().get(Owner.class, id);
        //return new Owner("Ivan", "Ivanov", "password1");
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
    public void update(long id, Owner owner) {
        Session session = sessionFactory.getCurrentSession();
        Owner owner2 = session.byId(Owner.class).load(id);
        owner2.setFirstName(owner.getFirstName());
        owner2.setFirstName(owner.getFirstName());
        owner2.setPassword(owner.getPassword());
        session.flush();

    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Owner book = session.byId(Owner.class).load(id);
        session.delete(book);

    }
}
