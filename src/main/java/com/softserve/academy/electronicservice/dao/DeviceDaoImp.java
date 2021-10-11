package com.softserve.academy.electronicservice.dao;

import com.softserve.academy.electronicservice.model.Device;
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
public class DeviceDaoImp implements DeviceDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public DeviceDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long save(Device device) {
        sessionFactory.getCurrentSession().save(device);
        return device.getId();
    }

    @Override
    public Device get(long id) {
        return sessionFactory.getCurrentSession().get(Device.class, id);
    }

    @Override
    public List<Device> list() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Device> cq = cb.createQuery(Device.class);
        Root<Device> root = cq.from(Device.class);
        cq.select(root);
        Query<Device> query = session.createQuery(cq);
        return query.getResultList();

    }

    @Override
    public void update(long id, Device device) {
        Session session = sessionFactory.getCurrentSession();
        Device device2 = session.byId(Device.class).load(id);
        device2.setType(device.getType());
        device2.setName(device.getName());
        device2.setCode(device.getCode());
        device2.setOwner(device.getOwner());
        device2.setStatus(device.getStatus());
        device2.setUpdateDate(device.getUpdateDate());
            session.flush();
    }

    @Override
    public void delete(long id) {
    Session session = sessionFactory.getCurrentSession();
    Device device = session.byId(Device.class).load(id);
    session.delete(device);
    }

}
