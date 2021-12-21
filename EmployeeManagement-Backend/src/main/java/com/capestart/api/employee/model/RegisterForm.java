package com.capestart.api.employee.model;

import java.sql.Date;

public class RegisterForm
{
    private int desigination_id;
    private String name;
    private Date dob;
    private String email;
    private long phone_number;
    private Integer manager_id;
    private String password;

    public int getDesigination_id() {
        return desigination_id;
    }

    public void setDesigination_id(int desigination_id) {
        this.desigination_id = desigination_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
