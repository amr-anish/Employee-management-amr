import { Component, OnDestroy, OnInit } from '@angular/core';
import { map, share, Subscription, timer } from 'rxjs';
import { EmployeeServicesService } from '../services/employee-services.service';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent implements OnInit, OnDestroy {

  attendanceList: any;
  rxTime = new Date();
  displayedColumns: string[] = ['attendance_id', 'day', 'login', 'logout'];
  loginButtonActive: boolean = false;
  logoutButtonActive: boolean = false;



  subscription: Subscription;
  constructor(private employeeService: EmployeeServicesService
  ) { this.getAttendanceList() }

  ngOnInit(): void {
    // Using RxJS Timer
    this.subscription = timer(0, 1000)
      .pipe(
        map(() => new Date()),
        share()
      )
      .subscribe(time => {
        this.rxTime = time;
      });
  }

  ngOnDestroy() {

    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }



  async getAttendanceList() {
    (await this.employeeService.getAttendanceByEmpId()).subscribe(
      res => {
        this.attendanceList = res;

        this.attendanceList.sort((a, b) => b.attendance_id - a.attendance_id)
        if (this.attendanceList[0].logout == null) {
          this.loginButtonActive = true
          this.logoutButtonActive = false


        }
        else {
          this.logoutButtonActive = true
          this.loginButtonActive = false
        }

      }
    )
  }

  async login() {
    (await this.employeeService.loginAttendance()).subscribe(res => {
      console.log(res);
      this.getAttendanceList()
    })

  }

  async logout() {
    (await this.employeeService.logoutAttendance()).subscribe(res => {
      console.log(res);
      this.getAttendanceList()
    })

  }



}
