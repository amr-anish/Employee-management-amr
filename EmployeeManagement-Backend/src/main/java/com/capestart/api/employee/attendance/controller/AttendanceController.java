package com.capestart.api.employee.attendance.controller;


import com.capestart.api.employee.attendance.service.AttendanceService;
import com.capestart.api.employee.model.Attendance;
import com.capestart.api.employee.model.Employee;
import com.capestart.api.employee.model.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AttendanceController {


    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/api/employees/attendance")
    public ResponseEntity<List<Attendance>> list() {
        List<Attendance> attendance = attendanceService.list();
        return ResponseEntity.ok().body(attendance);
    }


    @GetMapping("/api/employee/attendance/{id}")
    public ResponseEntity <Attendance>getbyid(@PathVariable("id") int id) {
        Attendance attendance = attendanceService.get(id);
        return ResponseEntity.ok().body(attendance);
    }

    @GetMapping("/api/employee/{eid}/attendance")
    public ResponseEntity<List<Attendance>> getbyeid(@PathVariable("eid") int eid) {
        List<Attendance> attendance = attendanceService.getByEid(eid);
        return ResponseEntity.ok().body(attendance);
    }


    @GetMapping("/api/employee/{empid}/login")
    public ResponseEntity<?> emplogin(@PathVariable("empid") int empid){
        attendanceService.empLogin(empid);
        return ResponseEntity.ok().body("Login Successfull");
    }

    @GetMapping("/api/employee/{id}/logout")
    public ResponseEntity<?> emplogout(@PathVariable("id") int id ){
        attendanceService.empLogout(id);
        return ResponseEntity.ok().body("Employee has been Logged out successfully");

    }

    @DeleteMapping("/api/employee/attendance/{id}")
    public ResponseEntity<?>delete(@PathVariable("id") int id){
        attendanceService.delete(id);
        return ResponseEntity.ok().body("Attendance has been deleted successfully");
    }







}
