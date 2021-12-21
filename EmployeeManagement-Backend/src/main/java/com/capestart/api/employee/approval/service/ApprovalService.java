package com.capestart.api.employee.approval.service;

import com.capestart.api.employee.model.ApprovalForm;
import com.capestart.api.employee.model.LeaveApproval;

import java.util.List;

public interface ApprovalService {



    String createLeaveForm(LeaveApproval leaveApproval);
    List<LeaveApproval>  getLeaveListForApproval(int emp_id);
    void ApproveLeave(ApprovalForm approvalForm);
    List<LeaveApproval>  getLeaveList(int emp_id);
    void delete(int leave_id);




}
