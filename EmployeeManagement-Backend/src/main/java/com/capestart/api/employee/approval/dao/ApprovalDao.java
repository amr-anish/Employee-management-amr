package com.capestart.api.employee.approval.dao;

import com.capestart.api.employee.model.LeaveApproval;

import java.util.List;

public interface ApprovalDao
{
    int save(LeaveApproval leaveApproval);
    List<LeaveApproval> list();
    void update(int leave_id, LeaveApproval leaveApproval );
    void delete(int leave_id);
    LeaveApproval get(int leave_id);
}
