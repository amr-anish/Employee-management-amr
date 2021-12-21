import { Component, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Subscription } from 'rxjs';
import { EmployeeServicesService } from '../services/employee-services.service';




@Component({
  selector: 'app-leave',
  templateUrl: './leave.component.html',
  styleUrls: ['./leave.component.scss']
})
export class LeaveComponent implements OnInit ,OnDestroy
{
  formVar:any;

  constructor(
    private formBuilder:FormBuilder,
    private employeeService: EmployeeServicesService
  ) 
  {
    
    this.formVar= this.formBuilder.group({ 
      selectLeavetype:[""],
      fromdate:[""],
      todate:[""],
      reason:[""],
      days:[""],    })
      

   }

   leaveBalanceSubscribe:Subscription;
  ngOnDestroy(): void {
  }


  ngOnInit(): void { this.getLeaveList();
    this.getLeaveBalance();}



  leaveForm:any={};
  leaveBalance:any={
    sick:0,
    casual:0
  };

  applyLeaveButtonActive:boolean=false;
  leaveApplySuccessOutput="";
  leaveApplyErrorOutput="";
  leaveList:any;
  displayedColumns: string[] = ['leave_id', 'leave_type', 'no_of_days', 'reason', 'fromdate', 'todate','manager', 'status','action'];
  managerName=JSON.parse((sessionStorage.getItem("managerDetails")))
  empId=sessionStorage.getItem('employeeId')


async getLeaveList(){
  (await this.employeeService.getListofLeave()).subscribe(res=>{
      this.leaveList=res;
      
      
     
  })
}
async getLeaveBalance(){
  this.leaveBalanceSubscribe=(await this.employeeService.getLeaveBalance()).subscribe(res=>{
      this.leaveBalance=res;
     
  })
}

async deleteLeaveForm(id)
{

  (await this.employeeService.deleteLeaveForm(id)).subscribe(res=>{

     //console.log(res);
      this.getLeaveBalance();
      this.getLeaveList();
        })
 // console.log(this.leaveList);
  
  
  
}

  handleChange(e) 
  {
    this.leaveForm[e.target.id] = e.target.value; 

    //console.log(this.leaveForm);
    
  }



 async applyLeave()
  {
    this.leaveApplySuccessOutput="";
    this.leaveApplyErrorOutput="";
    // console.log(this.formVar.value);
    
    if( this.formVar.get("selectLeavetype").value !== ""  &&
     this.formVar.get("fromdate").value !== ""  &&
     this.formVar.get("todate").value !== ""  &&
     this.formVar.get("days").value !== ""  &&
     this.formVar.get("reason").value !== "" &&
     this.formVar.get("selectLeavetype").value !== null  &&
     this.formVar.get("fromdate").value !== null  &&
     this.formVar.get("todate").value !== null  &&
     this.formVar.get("days").value !== null  &&
     this.formVar.get("reason").value !== null )
    {
        this.leaveForm.emp_id     = sessionStorage.getItem("employeeId")
        this.leaveForm.leave_type = this.formVar.get("selectLeavetype").value
        this.leaveForm.reason     = this.formVar.get("reason").value
        this.leaveForm.no_of_days = this.formVar.get("days").value
        this.leaveForm.from_date  = this.formVar.get("fromdate").value
        this.leaveForm.to_date    = this.formVar.get("todate").value
        
    
        await this.employeeService.applyLeave( (this.leaveForm) ).then((response)=>{
          response.subscribe((response)=>{         
            if(response=="Leave Applied")
            {
              this.leaveApplySuccessOutput=response
              this.getLeaveList();
              this.getLeaveBalance();
    
            }else
            {
              this.leaveApplyErrorOutput=response
            }
          })
        })
        this.leaveForm=[]
        this.formVar.reset();
    }else{
      this.leaveApplyErrorOutput="Enter All Details ";
     
   }
     
    }


   
}
