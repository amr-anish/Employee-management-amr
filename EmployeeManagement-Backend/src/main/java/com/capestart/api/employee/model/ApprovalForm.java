package com.capestart.api.employee.model;

public class ApprovalForm {

    private int managerId;
    private int leaveFormId;
    private  int approvalId;

    public int getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(int approvalId) {
        this.approvalId = approvalId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getLeaveFormId() {
        return leaveFormId;
    }

    public void setLeaveFormId(int leaveFormId) {
        this.leaveFormId = leaveFormId;
    }
}
