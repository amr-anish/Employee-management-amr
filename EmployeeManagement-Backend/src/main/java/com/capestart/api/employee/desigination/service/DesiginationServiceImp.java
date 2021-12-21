package com.capestart.api.employee.desigination.service;

import com.capestart.api.employee.desigination.dao.DesiginationDao;
import com.capestart.api.employee.model.Desigination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DesiginationServiceImp implements DesiginationService{


    private DesiginationDao desiginationDao;

    public DesiginationServiceImp(DesiginationDao desiginationDao) {
        this.desiginationDao = desiginationDao;
    }

    @Override
    public List<Desigination> list() {
        return desiginationDao.list();
    }

    @Override
    public Desigination get(int desigination_id) {
        return desiginationDao.get(desigination_id);
    }
}
