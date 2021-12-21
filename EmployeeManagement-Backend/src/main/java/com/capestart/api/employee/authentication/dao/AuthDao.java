package com.capestart.api.employee.authentication.dao;



import com.capestart.api.employee.model.UserForm;

import java.util.List;

public interface AuthDao
{
    int save(UserForm userForm);
    List<UserForm> list();
    void update(int id,UserForm userForm);
    void delete(int id);
    UserForm get(int id);

}
