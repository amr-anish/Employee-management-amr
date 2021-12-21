package com.capestart.api.employee.model;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name="attendance")
public class Attendance {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendance_id;
    private int emp_id;
    private LocalTime login;
    private LocalTime  logout;
    private  Date day;



    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(int attendance_id) {
        this.attendance_id = attendance_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public LocalTime getLogin() {
        return login;
    }

    public void setLogin(LocalTime login) {
        this.login = login;
    }

    public LocalTime getLogout() {
        return logout;
    }

    public void setLogout(LocalTime logout) {
        this.logout = logout;
    }
}
