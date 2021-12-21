package com.capestart.api.employee.leave.dao;

import com.capestart.api.employee.model.Employee;
import com.capestart.api.employee.model.Leave;
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
public class LeaveDaoImp implements LeaveDao {


        private SessionFactory sessionFactory;

    public LeaveDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int save(Leave leave) {
        sessionFactory.getCurrentSession().save(leave);
        return leave.getEmp_leave_id();
    }

    @Override
    public List<Leave> list()
    {
        Session session =sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Leave> cq = cb.createQuery(Leave.class);
        Root<Leave> root = cq.from(Leave.class);
        cq.select(root);
        Query<Leave> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void update(int emp_leave_id, Leave leave)
    {
        Session session =sessionFactory.getCurrentSession();
        Leave leaveObj = session.byId(Leave.class).load(emp_leave_id);
        leaveObj.setCasual(leave.getCasual());
        leaveObj.setSick(leave.getSick());
        session.flush();

    }

    @Override
    public void delete(int emp_leave_id)
    {
        Session session =sessionFactory.getCurrentSession();
        Leave leaveObj = session.byId(Leave.class).load(emp_leave_id);
        session.delete(leaveObj);

    }

    @Override
    public Leave get(int emp_leave_id)
    {
        //System.out.println("output" + sessionFactory.getCurrentSession().get(Leave.class,id));

        return sessionFactory.getCurrentSession().get(Leave.class,emp_leave_id);
    }
}
