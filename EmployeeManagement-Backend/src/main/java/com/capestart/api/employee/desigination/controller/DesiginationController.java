package com.capestart.api.employee.desigination.controller;


import com.capestart.api.employee.desigination.service.DesiginationService;
import com.capestart.api.employee.model.Desigination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class DesiginationController
{

    private DesiginationService desiginationService;

    public DesiginationController(DesiginationService desiginationService) {
        this.desiginationService = desiginationService;
    }

    //get the designation list
    @GetMapping("/desigination")
    public ResponseEntity<List<Desigination>> list()
    {
        List<Desigination> desigination = desiginationService.list();
        return ResponseEntity.ok().body(desigination);
    }

    //get the designation List by id
    @GetMapping("/desigination/{id}")
    public ResponseEntity<Desigination> get(@PathVariable("id") int desigination_id)
    {
        Desigination desigination = desiginationService.get(desigination_id);
        return ResponseEntity.ok().body(desigination);
    }




}
