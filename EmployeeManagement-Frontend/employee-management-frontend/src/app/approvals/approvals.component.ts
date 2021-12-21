import { Component, OnInit } from '@angular/core';
import { EmployeeServicesService } from '../services/employee-services.service';


@Component({
  selector: 'app-approvals',
  templateUrl: './approvals.component.html',
  styleUrls: ['./approvals.component.css']
})
export class ApprovalsComponent implements OnInit {

  approvalList: any;
  approvalForm: any = {
    "managerId": "",
    "leaveFormId": "",
    "approvalId": ""
  }

  displayedColumns: string[] = ['leave_id', 'emp_id', 'leave_type', 'no_of_days', 'reason', 'fromdate', 'todate', 'status', 'action'];

  constructor(private employeeService: EmployeeServicesService,) {
    this.getApprovalList();

  }

  ngOnInit(): void {
  }


  async getApprovalList() {
    (await this.employeeService.getApprovalList()).subscribe(
      (res) => {
        this.approvalList = res;
      }
    )
  }

  async setApproval(leaveId: any, status) {
    this.approvalForm.leaveFormId = leaveId
    this.approvalForm.approvalId = status
    this.approvalForm.managerId = sessionStorage.getItem("employeeId");
    
    (await this.employeeService.approveLeave(this.approvalForm)).subscribe(
      res => {
        
        this.getApprovalList();

      }
    )

  }



}
