package com.capestart.api.employee.approval.controller;


import com.capestart.api.employee.approval.service.ApprovalService;

import com.capestart.api.employee.model.ApprovalForm;
import com.capestart.api.employee.model.Employee;
import com.capestart.api.employee.model.LeaveApproval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@CrossOrigin
@RestController
public class ApprovalController
{

    private ApprovalService approvalService;

    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    //Apply Leave
    @PostMapping("/api/employee/leave")
    public ResponseEntity<?> save(@RequestBody LeaveApproval leaveApproval)
    {

        return ResponseEntity.ok().body(  approvalService.createLeaveForm(leaveApproval)  );
    }

    //Get approval list
    @GetMapping("/api/employee/leave/{manager-id}")
    public ResponseEntity<List<LeaveApproval>> getApprovalList(@PathVariable("manager-id") int manager_id) {
        List<LeaveApproval> obj = approvalService.getLeaveListForApproval(manager_id);
        return ResponseEntity.ok().body(obj);
    }

    //approve leave
    @PostMapping("/api/employee/leave/approve")
    public ResponseEntity<?> getApproved(@RequestBody ApprovalForm approvalForm)
    {
        approvalService.ApproveLeave(approvalForm);
        String output="";

        if(approvalForm.getApprovalId() == 0) output="Leave Approval Pending";
        if(approvalForm.getApprovalId() == 1) output="Leave Approved";
        if(approvalForm.getApprovalId() == 2) output="Leave Rejected";
        else{
            output="Enter the correct approval id";
        }
        return ResponseEntity.ok().body(output);
    }
    //List our Leave
    @GetMapping("/api/employee/{eid}/leave/")
    public ResponseEntity<List<LeaveApproval>> getleaveList(@PathVariable("eid") int eid) {
        List<LeaveApproval> obj = approvalService.getLeaveList(eid);
        return ResponseEntity.ok().body(obj);
    }


}
