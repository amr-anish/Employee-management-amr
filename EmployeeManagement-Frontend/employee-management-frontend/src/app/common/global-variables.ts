import { Injectable } from '@angular/core';

@Injectable()
export class Globals {


  // API Urls
  AuthenticationURL     : string = 'http://localhost:8080/api/employee/authenticate';
  EmployeeDetailsURL    : string = 'http://localhost:8080/api/employee/';
  AllEmployeeDetailsURL : string = 'http://localhost:8080/api/employees/';
  LeaveURL              : string = 'http://localhost:8080/api/employee/leave/';  
  GetEmpLeaveBalanceURL : string = 'http://localhost:8080/api/employee/leavebalance/' ; 
  GetApporvalListURL    : string = 'http://localhost:8080/api/employee/leave/approval/' ;
  ApproveLeaveURL       : string = 'http://localhost:8080/api/employee/leave/approve';
  AttendenceURL         : string = 'http://localhost:8080/api/employee/attendance/';








}