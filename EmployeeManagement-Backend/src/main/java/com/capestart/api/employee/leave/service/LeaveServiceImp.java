package com.capestart.api.employee.leave.service;

import com.capestart.api.employee.leave.dao.LeaveDao;
import com.capestart.api.employee.model.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeaveServiceImp implements LeaveService{


    private LeaveDao leaveDao;

    public LeaveServiceImp(LeaveDao leaveDao) {
        this.leaveDao = leaveDao;
    }

    @Override
    public int save(Leave leave) {
        return   leaveDao.save(leave);

    }

    @Override
    public List<Leave> list() {
        return leaveDao.list();
    }

    @Override
    public void update(int emp_id, Leave leave)
    {
        List <Leave> data =leaveDao.list().stream().filter(leavee -> leavee.getEmp_id() == emp_id).collect(Collectors.toList());
        int id= data.get(0).getEmp_leave_id();
        leaveDao.update(id, leave);

    }

    @Override
    public void delete(int emp_id)
    {
        List <Leave> data =leaveDao.list().stream().filter(leave -> leave.getEmp_id() == emp_id).collect(Collectors.toList());
        int id= data.get(0).getEmp_leave_id();
        leaveDao.delete(id);

    }

    @Override
    public Leave get(int emp_id) {
        List <Leave> data =leaveDao.list().stream().filter(leave -> leave.getEmp_id() == emp_id).collect(Collectors.toList());
        Leave empLeave= data.get(0);
       // System.out.println(empLeave.getId());
        return empLeave;
    }








}
