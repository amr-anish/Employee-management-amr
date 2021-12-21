package com.capestart.api.employee.approval.dao;


import com.capestart.api.employee.model.LeaveApproval;
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
public class ApprovalDaoImp implements ApprovalDao{


    private SessionFactory sessionFactory;

    public ApprovalDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int save(LeaveApproval leaveApproval) {
        sessionFactory.getCurrentSession().save(leaveApproval);
        return leaveApproval.getLeave_id();

    }

    @Override
    public List<LeaveApproval> list() {
        Session session =sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<LeaveApproval> cq = cb.createQuery(LeaveApproval.class);
        Root<LeaveApproval> root = cq.from(LeaveApproval.class);
        cq.select(root);
        Query<LeaveApproval> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void update(int leave_id, LeaveApproval leaveApproval)
    {
        Session session =sessionFactory.getCurrentSession();
        LeaveApproval leaveApprovalObj = session.byId(LeaveApproval.class).load(leave_id);
        leaveApprovalObj.setApproval(leaveApproval.getApproval());
        session.flush();


    }

    @Override
    public void delete(int leave_id)
    {
        Session session = sessionFactory.getCurrentSession();
        LeaveApproval leaveApprovalObj = session.byId(LeaveApproval.class).load(leave_id);
        session.delete(leaveApprovalObj);
    }

    @Override
    public LeaveApproval get(int leave_id) {
        return sessionFactory.getCurrentSession().get(LeaveApproval.class, leave_id);
    }
}
