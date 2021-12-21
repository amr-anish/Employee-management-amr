package com.capestart.api.employee.details.controller;


import com.capestart.api.employee.details.service.DetailsService;
import com.capestart.api.employee.model.Employee;
import com.capestart.api.employee.model.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DetailsController {


    private DetailsService detailsService;

    public DetailsController(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    /*---Get all Employee---*/
    @GetMapping("/api/employees")
    public ResponseEntity<List<Employee>> list() {
        List<Employee> employee = detailsService.list();
        return ResponseEntity.ok().body(employee);
    }

    /*---Get Employee by Id*/
    @GetMapping("/api/employee/{eid}")
    public ResponseEntity<Employee> get(@PathVariable("eid") int eid){
        Employee employee = detailsService.get(eid);
        return ResponseEntity.ok().body(employee);
    }

    /*---Add new Employee----*/
    @PostMapping("/api/employee")
    public ResponseEntity<?> save(@RequestBody RegisterForm registerForm){
        int eid = detailsService.createEmployee(registerForm);
        return ResponseEntity.ok().body("New Employee has been saved with ID:" +eid);
    }

    /*Update a Employee by id*/
    @PutMapping("/api/employee/{eid}")
    public ResponseEntity<?> update(@PathVariable("eid") int emp_id, @RequestBody Employee employee){
        detailsService.update(emp_id, employee);

        return ResponseEntity.ok().body("Employee has been update successfully");

    }

    /*Delete a Employee by id*/
    @DeleteMapping("/api/employee/{eid}")
    public ResponseEntity<?>delete(@PathVariable("eid") int eid)
    {
        detailsService.delete(eid);
        return ResponseEntity.ok().body("Employee has been deleted successfully");
    }

}
