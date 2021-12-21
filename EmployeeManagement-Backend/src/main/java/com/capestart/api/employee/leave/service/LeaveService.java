package com.capestart.api.employee.leave.service;

import com.capestart.api.employee.model.Leave;

import java.util.List;

public interface  LeaveService
{
    int save(Leave leave);
    List<Leave> list();
    void update(int emp_id,Leave leave);
    void delete(int emp_id);
    Leave get(int emp_id);

}
