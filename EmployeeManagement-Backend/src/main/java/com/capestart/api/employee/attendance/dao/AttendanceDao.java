package com.capestart.api.employee.attendance.dao;



import com.capestart.api.employee.model.Attendance;

import java.util.List;

public interface AttendanceDao
{
    int save(Attendance attendance);
    List<Attendance> list();

    void updateLogout(int attendance_id,Attendance attendance);

    void delete(int attendance_id);
    Attendance get(int attendance_id);
}
