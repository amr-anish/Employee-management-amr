import { Injectable, Output, Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Globals } from '../common/global-variables';

@Injectable({
  providedIn: 'root'
})

export class EmployeeServicesService {
  empId: any = "";
  constructor(private http: HttpClient, private global: Globals) {
    this.empId = sessionStorage.getItem('employeeId');


  }


  async authenticate(body: any) {
    return this.http.post<any>(this.global.AuthenticationURL, body);
  }


  async applyLeave(body: any) {
    return this.http.post(this.global.LeaveURL, body, { responseType: "text" });
  }

  async getEmployeeDetails(emp_id: any) {
    return this.http.get<any>(this.global.EmployeeDetailsURL + emp_id);

  }
  async getAllEmployeeDetails() {
    return this.http.get<any>(this.global.AllEmployeeDetailsURL);

  }

  async getListofLeave() {
    return this.http.get<any>(this.global.EmployeeDetailsURL + this.empId + '/leave/');

  }
  async getLeaveBalance() {
    return this.http.get<any>(this.global.GetEmpLeaveBalanceURL + this.empId);

  }
  async deleteLeaveForm(id: any) {
    return this.http.delete(this.global.LeaveURL + id, { responseType: "text" });

  }
  async getApprovalList() {
    return this.http.get<any>(this.global.GetApporvalListURL + this.empId);

  }
  async approveLeave(body: any) {
    return this.http.post(this.global.ApproveLeaveURL, body, { responseType: "text" });

  }

  async loginAttendance() {
    return this.http.get(this.global.EmployeeDetailsURL + this.empId + "/login", { responseType: "text" });
  }
  async logoutAttendance() {
    return this.http.get(this.global.EmployeeDetailsURL + this.empId + "/logout", { responseType: "text" });
  }
  async getAttendanceById(id: any) {
    return this.http.get<any>(this.global.AttendenceURL + id);
  }
  async getAttendanceByEmpId() {
    return this.http.get<any>(this.global.EmployeeDetailsURL + this.empId + "/attendance/");
  }

  async registerEmployee(body: any) {
    return this.http.post(this.global.EmployeeDetailsURL, body, { responseType: "text" });
  }
}
