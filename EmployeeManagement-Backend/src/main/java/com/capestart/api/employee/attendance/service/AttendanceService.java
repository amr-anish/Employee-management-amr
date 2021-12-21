package com.capestart.api.employee.attendance.service;

import com.capestart.api.employee.model.Attendance;

import java.util.List;

public interface AttendanceService
{

    int save(Attendance attendance);
    List<Attendance> list();
    void empLogin(int emp_id);
    void empLogout(int emp_id);

    void delete(int attendance_id);
    List<Attendance> getByEid(int emp_id);

    Attendance get(int attendance_id);


}
