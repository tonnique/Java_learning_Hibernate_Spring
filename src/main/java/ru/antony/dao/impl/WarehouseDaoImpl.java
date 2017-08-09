package ru.antony.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.antony.dao.WarehouseDao;
import ru.antony.model.Warehouse;

@Component
public class WarehouseDaoImpl implements WarehouseDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = false)
    public Warehouse addWarehouse(Warehouse warehouse) throws SQLException, Exception {
        sessionFactory.getCurrentSession().save(warehouse);
        return warehouse;
    }

    @Transactional(readOnly = false)
    public void updateWarehouse(Warehouse warehouse) throws SQLException, Exception {
        sessionFactory.getCurrentSession().update(warehouse);
    }

    @Transactional(readOnly = true)
    public Warehouse getWarehouseById(Long id) throws SQLException, Exception {
        return (Warehouse) sessionFactory.getCurrentSession().createQuery("from Warehouse where id = " + id).uniqueResult();
    }

    @Transactional(readOnly = true)
    public Collection<Warehouse> getAllWarehouses() throws SQLException, Exception {
        return sessionFactory.getCurrentSession().createQuery("from Warehouse w").list();
    }

    @Transactional(readOnly = false)
    public void deleteWarehouse(Warehouse warehouse) throws SQLException, Exception {
        sessionFactory.getCurrentSession().delete(warehouse);
    }
}