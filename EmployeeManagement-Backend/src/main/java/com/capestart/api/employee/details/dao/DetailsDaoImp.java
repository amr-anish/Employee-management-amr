package com.capestart.api.employee.details.dao;

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
public class DetailsDaoImp implements DetailsDao
{

    private SessionFactory sessionFactory;



    public DetailsDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int save(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
        return employee.getEmp_id();
    }

    @Override
    public List<Employee> list()
    {
        Session session =sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root);
        Query<Employee> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void update(int emp_id, Employee employee) {

        Session session =sessionFactory.getCurrentSession();
        Employee employeeObj = session.byId(Employee.class).load(emp_id);

        employeeObj.setName(employee.getName());
        employeeObj.setDob(employee.getDob());
        employeeObj.setEmail(employee.getEmail());
        employeeObj.setPhone_number(employee.getPhone_number());
        employeeObj.setManager_id(employee.getManager_id());
        employeeObj.setAttendance_id(employee.getAttendance_id());
        employeeObj.setPassword(employee.getPassword());

        session.flush();
    }

    @Override
    public void delete(int eid) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.byId(Employee.class).load(eid);
        session.delete(employee);

    }

    @Override
    public Employee get(int eid) {

        return sessionFactory.getCurrentSession().get(Employee.class, eid);
    }
}
