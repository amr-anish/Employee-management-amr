import { Component, OnInit, ElementRef, ViewChild, Output, } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EmployeeServicesService } from '../services/employee-services.service'
import { Observable } from 'rxjs';
import { Globals } from '../common/global-variables';
import { MyErrorStateMatcher } from './error_state_matcher';
import { Router, Routes } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  authenticationData: any = {};
  output: Observable<any>;
  buttonActive: boolean = true;
  formError: string;



  constructor(
    private employeeService: EmployeeServicesService,
    private router: Router
  ) {
    sessionStorage.clear();
  }

  handleChange(e) {
    this.authenticationData[e.target.id] = e.target.value;
    this.formError = ""
    if (this.authenticationData.email != undefined && this.authenticationData.password != undefined) {
      this.buttonActive = false;
    }
  }
  async doLogin() {
    // Checking the credentials and getting the employee id
    await this.employeeService.authenticate(this.authenticationData)
      .then((response) => {
        response.subscribe((response) => {
          if (response != 0) {
            sessionStorage.setItem("employeeId", response)

// getting the employee details
            this.employeeService.getEmployeeDetails(response).then(
              (response) => {
                response.subscribe((response) => {
                  response.password = "*******"
                  sessionStorage.setItem("employeeDetails", JSON.stringify(response))
                  if (response.manager_id != null) {
  //  Getting the Manager Details
                    this.employeeService.getEmployeeDetails(response.manager_id).then((response) => {
                      response.subscribe((response) => {
                        sessionStorage.setItem("managerDetails", JSON.stringify(response))
                          this.router.navigateByUrl('/home', { skipLocationChange: true }).then(() => {
                          this.router.navigate(['this.location.path()']);
                        });
                      })
                    })
                  }
                  else {
                    this.router.navigate(['/home']);
                  }
                })
              }
            )
          }
          else {
            this.formError = "Invalid Credentials";
          }
        },
          (error) => { console.log(error); });
      }
      );
  }

  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  passwordFormControl = new FormControl('', [Validators.required]);

  matcher = new MyErrorStateMatcher();
  register() {
    this.router.navigate(['/register']);
  }
}





