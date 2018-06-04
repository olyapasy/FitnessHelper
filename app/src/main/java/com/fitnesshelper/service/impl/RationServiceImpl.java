package com.fitnesshelper.service.impl;

import android.content.Context;

import com.fitnesshelper.data.dao.RationDAO;
import com.fitnesshelper.data.dao.impl.RationDAOImpl;
import com.fitnesshelper.entity.Ration;
import com.fitnesshelper.service.DishService;
import com.fitnesshelper.service.RationService;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Date;
import java.util.List;

public class RationServiceImpl implements RationService {
    RationDAO rationDAO;
    DishService dishService;

    public RationServiceImpl(Context context) {
        rationDAO = new RationDAOImpl(context);
        dishService = new DishServiceImpl(context);
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

    public long getTotalAmountOfCal(Date date) {
        List<Ration> rationByDate = getRationByDate(date);
        long amountOfCAl = 0;

        if (CollectionUtils.isNotEmpty(rationByDate)) {
            for (Ration ration : rationByDate) {
                amountOfCAl += dishService.getDishCalories(ration.getListOfDish());
            }
        }

        return amountOfCAl;
    }
}
