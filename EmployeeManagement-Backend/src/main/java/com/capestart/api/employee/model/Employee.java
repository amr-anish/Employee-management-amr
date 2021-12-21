package com.capestart.api.employee.model;




import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Employee")
public class Employee
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int emp_id;

        @ManyToOne
        @JoinColumn(name = "desigination_id")
        private Desigination desigination;
        private String name;
        private Date dob;
        private String email;
        private long phone_number;
        private Integer manager_id;
        private Integer attendance_id;
        private String password;



    public void setDesigination(Desigination desigination) {
        this.desigination = desigination;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public void setAttendance_id(Integer attendance_id) {
        this.attendance_id = attendance_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public Desigination getDesigination() {
        return desigination;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public Integer getAttendance_id() {
        return attendance_id;
    }

    public String getPassword() {
        return password;
    }
}
