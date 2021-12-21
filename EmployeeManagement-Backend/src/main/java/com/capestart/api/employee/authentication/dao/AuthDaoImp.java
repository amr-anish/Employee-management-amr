package com.capestart.api.employee.authentication.dao;

import com.capestart.api.employee.model.Employee;
import com.capestart.api.employee.model.UserForm;
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
public class AuthDaoImp implements AuthDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int save(UserForm userForm) {
        sessionFactory.getCurrentSession().save(userForm);
        return userForm.getId();
    }

    @Override
    public List<UserForm> list() {
        Session session =sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserForm> cq = cb.createQuery(UserForm.class);
        Root<UserForm> root = cq.from(UserForm.class);
        cq.select(root);
        Query<UserForm> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void update(int id, UserForm userForm) {

        Session session =sessionFactory.getCurrentSession();
        UserForm userFormObj = session.byId(UserForm.class).load(id);
        userFormObj.setEmployee(userForm.getEmployee());
        userFormObj.setUsername(userForm.getUsername());
        userFormObj.setPassword(userForm.getPassword());

    }

    @Override
    public void delete(int id) {
        Session session =sessionFactory.getCurrentSession();
        UserForm userFormObj = session.byId(UserForm.class).load(id);
        session.delete(userFormObj);

    }

    @Override
    public UserForm get(int id) {
        return sessionFactory.getCurrentSession().get(UserForm.class,id);
    }
}
