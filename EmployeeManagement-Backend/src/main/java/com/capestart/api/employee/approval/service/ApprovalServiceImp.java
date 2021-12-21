package com.capestart.api.employee.approval.service;

import com.capestart.api.employee.approval.dao.ApprovalDao;
import com.capestart.api.employee.details.dao.DetailsDao;
import com.capestart.api.employee.details.service.DetailsService;
import com.capestart.api.employee.leave.service.LeaveService;
import com.capestart.api.employee.model.ApprovalForm;
import com.capestart.api.employee.model.Employee;
import com.capestart.api.employee.model.Leave;
import com.capestart.api.employee.model.LeaveApproval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApprovalServiceImp implements ApprovalService
        {
           
            private LeaveService leaveService;


            private DetailsService detailsService;


            private ApprovalDao approvalDao;

            public ApprovalServiceImp(LeaveService leaveService, DetailsService detailsService, ApprovalDao approvalDao) {
                this.leaveService = leaveService;
                this.detailsService = detailsService;
                this.approvalDao = approvalDao;
            }

            @Override
    public String createLeaveForm(LeaveApproval leaveApproval)
    {
        Leave leaveObj = leaveService.get(leaveApproval.getEmp_id());


        if( leaveApproval.getLeave_type().equalsIgnoreCase("sick")  )
        {
            int leaveLeft = leaveObj.getSick()-leaveApproval.getNo_of_days();
           // System.out.println( "no of leave " +leaveLeft);
            if(leaveLeft < 0)
            {
                return("Leave Submission Declined Not enough leave balance");
            }
            else
            {

                Integer managerId=detailsService.get(leaveApproval.getEmp_id()).getManager_id();
                if(managerId ==null)
                {
                    leaveApproval.setApproval(1);
                }
                else
                {
                    leaveApproval.setManager_id(managerId);
                    leaveApproval.setApproval(0);
                }
                leaveObj.setSick(leaveLeft);
                leaveService.update(leaveApproval.getEmp_id(),leaveObj);
                approvalDao.save(leaveApproval);

            }




        }else if( leaveApproval.getLeave_type().equalsIgnoreCase("casual"))
        {
            int leaveLeft =leaveObj.getCasual()-leaveApproval.getNo_of_days();
            if(leaveLeft < 0)
            {
                return("Leave Submission Declined Not enough leave balance");
            }
            else
            {

                Integer managerId=detailsService.get(leaveApproval.getEmp_id()).getManager_id();
                if(managerId ==null)
                {
                    leaveApproval.setApproval(1);
                }
                else
                {
                    leaveApproval.setManager_id(managerId);
                    leaveApproval.setApproval(0);
                }
                leaveObj.setCasual(leaveLeft);
                leaveService.update(leaveApproval.getEmp_id(),leaveObj);
                approvalDao.save(leaveApproval);
            }

        }


    return "Leave Applied";


    }

    @Override
    public List<LeaveApproval> getLeaveListForApproval(int emp_id)
    {
        List<LeaveApproval> approvalList= approvalDao.list().stream().filter(item-> item.getManager_id()==emp_id).collect(Collectors.toList());
        return approvalList;
    }

    @Override
    public void ApproveLeave(ApprovalForm approvalForm)
    {
        LeaveApproval leaveApproval= approvalDao.get(approvalForm.getLeaveFormId());
        if(approvalForm.getManagerId() == leaveApproval.getManager_id())
        {
            if(approvalForm.getApprovalId() == 3)
            {
                int days=leaveApproval.getNo_of_days();
                String leave_type = leaveApproval.getLeave_type();
                Leave leaveObj= leaveService.get(leaveApproval.getEmp_id());
                if(leave_type.equalsIgnoreCase("sick"))
                {
                    leaveObj.setSick(leaveObj.getSick() + days);
                }
                else if(leave_type.equalsIgnoreCase("casual"))
                {
                    leaveObj.setCasual(leaveObj.getCasual() + days);
                }
                leaveService.update(leaveApproval.getEmp_id(),leaveObj);

            }

            leaveApproval.setApproval(approvalForm.getApprovalId());
            approvalDao.update(approvalForm.getLeaveFormId(),leaveApproval);

        }

    }

    @Override
    public List<LeaveApproval> getLeaveList(int emp_id)
    {
        List<LeaveApproval> leaveList= approvalDao.list().stream().filter(item-> item.getEmp_id()==emp_id).collect(Collectors.toList());

        return leaveList;
    }

    @Override
    public void delete(int id) {
        approvalDao.delete(id);

    }
}
