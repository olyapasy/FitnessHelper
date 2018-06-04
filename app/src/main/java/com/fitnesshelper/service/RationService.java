package com.fitnesshelper.service;

import com.fitnesshelper.entity.Ration;

import java.util.Date;
import java.util.List;

public interface RationService {
    public Ration getRationById(long id);

    public void createRation(Ration ration);

    public void updateRation(Ration ration);

    public List<Ration> getRationByDate(Date date);

    public void removeRation(Ration ration);

    public long getTotalAmountOfCal(Date date);
}
