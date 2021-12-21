package com.capestart.api.employee.model;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="leave_approval")
public class LeaveApproval
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     leave_id;
    private int     emp_id;
    private String  leave_type;
    private int     no_of_days;
    private String  reason;
    private int     manager_id;
    private int     approval; //0 means pending 1 means approved 2 means Rejected
    private Date    from_date;
    private Date    to_date;

    public int getLeave_id() {
        return leave_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public int getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(int no_of_days) {
        this.no_of_days = no_of_days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getApproval() {
        return approval;
    }

    public void setApproval(int approval) {
        this.approval = approval;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }
}
