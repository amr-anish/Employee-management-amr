package com.capestart.api.employee.details.dao;

import com.capestart.api.employee.model.Employee;

import java.util.List;

public interface DetailsDao {

    int save(Employee employee);
    List<Employee> list();
    void update(int emp_id,Employee employee);
    void delete(int emp_id);
    Employee get(int emp_id);



}
