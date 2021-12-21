package com.capestart.api.employee.authentication.controller;


import com.capestart.api.employee.authentication.service.AuthService;
import com.capestart.api.employee.model.AuthenticationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthController
{

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/employee/authenticate")
    public ResponseEntity<?> save(@RequestBody AuthenticationForm authenticationForm)
    {
        return ResponseEntity.ok().body( authService.getAuthenticated(authenticationForm) );
    }


}
