package com.capestart.api.employee.leave.controller;


import com.capestart.api.employee.leave.service.LeaveService;
import com.capestart.api.employee.model.Employee;
import com.capestart.api.employee.model.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class LeaveController
{

    private LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/leave")
    public ResponseEntity<List<Leave>> list() {
        List<Leave> leave = leaveService.list();
        return ResponseEntity.ok().body(leave);
    }


    @GetMapping("/leave/{empid}")
    public ResponseEntity<Leave> get(@PathVariable("empid") int empid){
        Leave leave = leaveService.get(empid);
        return ResponseEntity.ok().body(leave);
    }


    @PostMapping("/leave")
    public ResponseEntity<?> save(@RequestBody Leave leave){
        int id = leaveService.save(leave);
        return ResponseEntity.ok().body("New leave has been saved with ID:" +id);
    }


    @PutMapping("/leave/{empid}")
    public ResponseEntity<?> update(@PathVariable("empid") int empid, @RequestBody Leave leave){
        leaveService.update(empid, leave);

        return ResponseEntity.ok().body("leave has been update successfully");

    }


    @DeleteMapping("/leave/{empid}")
    public ResponseEntity<?>delete(@PathVariable("empid") int empid){
        leaveService.delete(empid);
        return ResponseEntity.ok().body("leave of the employee has been deleted successfully");
    }




}
