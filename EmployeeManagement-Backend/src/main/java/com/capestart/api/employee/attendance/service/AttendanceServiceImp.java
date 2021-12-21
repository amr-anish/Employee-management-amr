package com.capestart.api.employee.attendance.service;

import com.capestart.api.employee.attendance.dao.AttendanceDao;
import com.capestart.api.employee.details.dao.DetailsDao;
import com.capestart.api.employee.model.Attendance;
import com.capestart.api.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttendanceServiceImp implements AttendanceService{


    private AttendanceDao attendanceDao;


    private DetailsDao detailsDao;

    public AttendanceServiceImp(AttendanceDao attendanceDao, DetailsDao detailsDao) {
        this.attendanceDao = attendanceDao;
        this.detailsDao = detailsDao;
    }

    @Override
    public int save(Attendance attendance) {
        return attendanceDao.save(attendance);
    }

    @Override
    public List<Attendance> list() {
        return attendanceDao.list();
    }

    @Override
    public void empLogin(int emp_id)
    {
        long millis=System.currentTimeMillis();
        java.sql.Date todayDate=new java.sql.Date(millis);
        LocalTime currentTime = LocalTime.now();

        Attendance attendanceObj = new Attendance();
        attendanceObj.setEmp_id(emp_id);
        attendanceObj.setLogin(currentTime);
        attendanceObj.setDay(todayDate);
        Integer attendanceId=attendanceDao.save(attendanceObj);

        Employee empObj = detailsDao.get(emp_id);


        empObj.setAttendance_id(attendanceId);

        detailsDao.update(emp_id,empObj);


    }

    @Override
    public void empLogout(int emp_id)
    {
        int attendance_id = detailsDao.get(emp_id).getAttendance_id();
        Attendance attendanceObj = attendanceDao.get(attendance_id);
        LocalTime currentTime = LocalTime.now();
        attendanceObj.setLogout(currentTime);
        attendanceDao.updateLogout(attendance_id,attendanceObj);


    }

    @Override
    public void delete(int attendance_id) {
        attendanceDao.delete(attendance_id);

    }

    @Override
    public List<Attendance> getByEid(int emp_id)
    {

        List<Attendance> attendanceList = (attendanceDao.list()).stream().filter(item -> item.getEmp_id()==emp_id).
                collect(Collectors.toList());
        return attendanceList;
    }

    @Override
    public Attendance get(int attendance_id) {
        return attendanceDao.get(attendance_id);
    }
}
