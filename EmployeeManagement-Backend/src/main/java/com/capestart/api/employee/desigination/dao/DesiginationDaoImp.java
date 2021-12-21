package com.capestart.api.employee.desigination.dao;

import com.capestart.api.employee.model.Desigination;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public class DesiginationDaoImp implements DesiginationDao
{

    private SessionFactory sessionFactory;

    public DesiginationDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Desigination> list() {
        Session session =sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Desigination> cq = cb.createQuery(Desigination.class);
        Root<Desigination> root = cq.from(Desigination.class);
        cq.select(root);
        Query<Desigination> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Desigination get(int desigination_id) {
        return sessionFactory.getCurrentSession().get(Desigination.class, desigination_id);
    }
}
