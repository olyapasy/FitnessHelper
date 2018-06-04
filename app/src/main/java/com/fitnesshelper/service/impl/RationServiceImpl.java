package com.fitnesshelper.service.impl;

import android.content.Context;

import com.fitnesshelper.data.dao.RationDAO;
import com.fitnesshelper.data.dao.impl.RationDAOImpl;
import com.fitnesshelper.entity.Ration;

import java.util.Date;
import java.util.List;

public class RationServiceImpl {
    RationDAO rationDAO;

    public RationServiceImpl(Context context) {
        rationDAO = new RationDAOImpl(context);
    }

    public Ration getRationById(long id) {
        return rationDAO.getById(id);
    }

    public void createRation(Ration ration) {
        rationDAO.create(ration);
    }

    public void updateRation(Ration ration) {
        rationDAO.update(ration);
    }

    public List<Ration> getRationByDate(Date date) {
        return rationDAO.getByDate(date);
    }

    public void removeRation(Ration ration) {
        rationDAO.delete(ration.getId());
    }
}
