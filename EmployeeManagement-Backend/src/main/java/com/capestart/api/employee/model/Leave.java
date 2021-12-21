package com.capestart.api.employee.model;


import javax.persistence.*;

@Entity
@Table(name="emp_leave")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_leave_id;
    private int emp_id;
    private int casual;
    private int sick;

    public int getEmp_leave_id() {
        return emp_leave_id;
    }

    public void setEmp_leave_id(int emp_leave_id) {
        this.emp_leave_id = emp_leave_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getCasual() {
        return casual;
    }

    public void setCasual(int casual) {
        this.casual = casual;
    }

    public int getSick() {
        return sick;
    }

    public void setSick(int sick) {
        this.sick = sick;
    }
}
