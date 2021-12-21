package com.capestart.api.employee.desigination.dao;


import com.capestart.api.employee.model.Desigination;

import java.util.List;

public interface DesiginationDao
{

    List<Desigination>  list();

      Desigination  get(int desigination_id);


}
