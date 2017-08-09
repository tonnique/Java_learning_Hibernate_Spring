package ru.antony.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.antony.dao.ProviderDao;
import ru.antony.model.Provider;

@Component
public class ProviderDaoImpl implements ProviderDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = false)
    public Provider addProvider(Provider provider) throws SQLException, Exception {
        sessionFactory.getCurrentSession().save(provider);
        return provider;
    }

    @Transactional(readOnly = false)
    public void updateProvider(Provider provider) throws SQLException, Exception {
        sessionFactory.getCurrentSession().update(provider);
    }

    @Transactional(readOnly = true)
    public Provider getProviderById(Long id) throws SQLException, Exception {
        return (Provider) sessionFactory.getCurrentSession().createQuery("from Provider where id = " + id).uniqueResult();
    }

    @Transactional(readOnly = true)
    public Collection<Provider> getAllProviders() throws SQLException, Exception {
        return sessionFactory.getCurrentSession().createQuery("from Provider p").list();
    }

    @Transactional(readOnly = false)
    public void deleteProvider(Provider provider) throws SQLException, Exception {
        sessionFactory.getCurrentSession().delete(provider);
    }
}
