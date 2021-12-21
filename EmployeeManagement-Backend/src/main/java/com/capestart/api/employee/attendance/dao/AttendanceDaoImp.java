package com.capestart.api.employee.attendance.dao;

import com.capestart.api.employee.model.Attendance;
import com.capestart.api.employee.model.Employee;
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
public class AttendanceDaoImp implements AttendanceDao{


    private SessionFactory sessionFactory;

    public AttendanceDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int save(Attendance attendance) {
        sessionFactory.getCurrentSession().save(attendance);

        return attendance.getAttendance_id();
    }

    @Override
    public List<Attendance> list() {
        Session session =sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Attendance> cq = cb.createQuery(Attendance.class);
        Root<Attendance> root = cq.from(Attendance.class);
        cq.select(root);
        Query<Attendance> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void updateLogout(int attendance_id, Attendance attendance)
    {
        Session session =sessionFactory.getCurrentSession();
        Attendance attendanceObj = session.byId(Attendance.class).load(attendance_id);

        attendanceObj.setLogout(attendance.getLogout());
        session.flush();

    }

    @Override
    public void delete(int attendance_id)
    {
        Session session = sessionFactory.getCurrentSession();
        Attendance attendance  = session.byId(Attendance.class).load(attendance_id);
        session.delete(attendance);

    }

    @Override
    public Attendance get(int attendance_id) {
        return sessionFactory.getCurrentSession().get(Attendance.class, attendance_id);

    }
}
