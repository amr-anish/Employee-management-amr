package com.capestart.api.employee.details.service;


import com.capestart.api.employee.model.Employee;
import com.capestart.api.employee.model.RegisterForm;

import java.util.List;

public interface DetailsService
{
    int save(Employee employee);
    int createEmployee(RegisterForm registerForm);
    List<Employee> list();
    void update(int emp_id,Employee employee);
    void delete(int emp_id);
    Employee get(int emp_id);


}
