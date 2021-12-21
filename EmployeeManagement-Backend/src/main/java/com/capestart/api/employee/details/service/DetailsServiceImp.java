package com.capestart.api.employee.details.service;


import com.capestart.api.employee.desigination.service.DesiginationService;
import com.capestart.api.employee.details.dao.DetailsDao;
import com.capestart.api.employee.leave.service.LeaveService;
import com.capestart.api.employee.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DetailsServiceImp implements DetailsService{


    private DetailsDao detailsDao;


    private DesiginationService desiginationService;


    private LeaveService leaveService;

    public DetailsServiceImp(DetailsDao detailsDao, DesiginationService desiginationService, LeaveService leaveService) {
        this.detailsDao = detailsDao;
        this.desiginationService = desiginationService;
        this.leaveService = leaveService;
    }

    @Override
    public int save(Employee employee) {
        return detailsDao.save(employee);
    }

    @Override
    public List<Employee> list() {
        return detailsDao.list();
    }

    @Override
    public void update(int emp_id, Employee employee) {
        detailsDao.update(emp_id,employee);

    }

    @Override
    public void delete(int emp_id) {
        detailsDao.delete(emp_id);

    }

    @Override
    public Employee get(int emp_id) {
        return detailsDao.get(emp_id);
    }


    @Override
    public int createEmployee(RegisterForm registerForm)
    {

        Employee employee = new Employee();

        Leave leaveObj = new Leave();

        Desigination desigination = desiginationService.get(registerForm.getDesigination_id());

        employee.setManager_id(registerForm.getManager_id());
        employee.setName(registerForm.getName());
        employee.setDesigination(desigination);
        employee.setEmail(registerForm.getEmail());
        employee.setPhone_number(registerForm.getPhone_number());
        employee.setDob(registerForm.getDob());
        employee.setPassword(registerForm.getPassword());

        int emp_id= detailsDao.save(employee);

        leaveObj.setEmp_id(emp_id);
        leaveObj.setCasual(2);
        leaveObj.setSick(2);
        leaveService.save(leaveObj);

        return emp_id;
    }
}

