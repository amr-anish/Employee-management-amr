package com.capestart.api.employee.leave.dao;

import com.capestart.api.employee.model.Leave;

import java.util.List;

public interface LeaveDao
{
    int save(Leave leave);
    List<Leave> list();
    void update(int emp_leave_id,Leave leave);
    void delete(int emp_leave_id);
    Leave get(int emp_leave_id);

}
