package com.capestart.api.employee.desigination.service;

import com.capestart.api.employee.model.Desigination;

import java.util.List;

public interface DesiginationService
{
    List<Desigination> list();
    Desigination  get(int desigination_id);
}
