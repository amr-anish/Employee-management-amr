import { Component, OnInit, ElementRef, ViewChild, Output, } from '@angular/core';
import { FormBuilder, FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EmployeeServicesService } from '../services/employee-services.service'
import { Observable } from 'rxjs';
import { Globals } from '../common/global-variables';
import { MyErrorStateMatcher } from '../login/error_state_matcher';
import { Router, Routes } from '@angular/router';
import { ListKeyManager } from '@angular/cdk/a11y';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registrationData: any = {};
  output: Observable<any>;
  buttonActive: boolean = true;
  formError: string;
  formVar: any;
  ManagerList: any;
  ResultManagerList: any;
  validation: boolean = true;


  constructor(
    private formBuilder: FormBuilder,
    private employeeService: EmployeeServicesService,
    private router: Router
  ) {
    sessionStorage.clear();
    this.getManagerList();
    this.formVar = this.formBuilder.group({
      desiginationId: [""],
      managerId: [""],
      dob: [""]
    })
  }

  handleChange(e) {
    this.registrationData[e.target.id] = e.target.value;
    this.formError = ""
    if (e.target.id == "phone_number") {


      this.ValidatePhoneNumber(e.target.value) ? this.formError = "" : this.formError = "Enter Correct 10 digit Number";
    }
  }

  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  passwordFormControl = new FormControl('', [Validators.required]);
  nameFormControl = new FormControl('', [Validators.required]);
  phoneNoFormControl = new FormControl('', [Validators.required]);

  matcher = new MyErrorStateMatcher();

  async validate() {
    this.formError = ""
    if (this.registrationData.email == undefined || this.registrationData.email == "")
      this.formError = "Enter the Email"
    else if (this.registrationData.password == undefined || this.registrationData.password == "")
      this.formError = "Enter the Password"
    else if (this.registrationData.name == undefined || this.registrationData.name == "")
      this.formError = "Enter the Name"
    else if (this.formVar.value.dob == undefined || this.formVar.value.dob == "")
      this.formError = "Enter the Date of Birth"
    else if (this.registrationData.phone_number == undefined || this.registrationData.phone_number == "")
      this.formError = "Enter the Phone Number"

    else if (this.formVar.value.desiginationId == undefined || this.formVar.value.desiginationId == "")
      this.formError = "Select the Desigination"
    else if (this.formVar.value.desiginationId > 1 && this.formVar.value.managerId == "")
      this.formError = "Select the Manager"
    else {
      if (await this.checkExistingData() == true) {
        this.doRegister();
      }
    }
  }

  ValidatePhoneNumber(inputtxt) {
    const phoneno = /^\d{10}$/;
    if ((inputtxt.match(phoneno))) {
      return true
    } else {
      return false
    }
  }

  async checkExistingData() {
    this.formError = ""
    this.validation = true

    await this.ManagerList.filter(ele => {
      if (ele.email == this.registrationData.email) {
        this.formError = "Email id is Already Present ";
        this.validation = false;
      }
    })
    await this.ManagerList.filter(ele => {
      if (ele.phone_number == this.registrationData.phone_number) {
        this.formError = this.formError + "Phone Number id is Already Present";
        this.validation = false;
      }
    })
    return this.validation;
  }

  async doRegister() {
    this.registrationData.desigination_id = this.formVar.value.desiginationId;
    this.registrationData.manager_id = ""
    this.registrationData.dob = this.formVar.value.dob;
    if (this.formVar.value.desiginationId != 1) {
      this.registrationData.manager_id = this.formVar.value.managerId;
    }
    // console.log(this.registrationData);
    (await (this.employeeService.registerEmployee(this.registrationData))).subscribe
      (res => {
        alert(res)
        this.router.navigate(['/']);
      })

  }

  goLogin() {
    this.router.navigate(['/']);
  }
  async ListKeyManager() {
    // console.log(this.formVar.value);
    this.ResultManagerList = this.ManagerList.filter(element => {
      return (element.desigination.desigination_id < this.formVar.value.desiginationId);
    })
    // console.log(this.ResultManagerList);



  }
  async getManagerList() {
    (await this.employeeService.getAllEmployeeDetails()).subscribe(res => {
      this.ManagerList = res;
      // console.log(res);
    })
  }
}


