package com.capestart.api.employee.authentication.service;


import com.capestart.api.employee.details.service.DetailsService;
import com.capestart.api.employee.model.AuthenticationForm;
import com.capestart.api.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthServiceImp implements AuthService
{

    private DetailsService detailsService;

    public AuthServiceImp(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @Override
    public int getAuthenticated(AuthenticationForm authenticationForm)
    {
            List <Employee> validData = detailsService.list().stream().
                    filter(item->item.getEmail().equals(authenticationForm.getEmail())).
                    collect(Collectors.toList());

            if (validData.size() == 1)
            {
                //if authenticated we will get employee id
                if (validData.get(0).getPassword().equals(authenticationForm.getPassword()))
                {
                    return validData.get(0).getEmp_id();
                }

            }


        return 0;
    }
}

