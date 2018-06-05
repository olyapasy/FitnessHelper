package com.fitnesshelper.service;

import com.fitnesshelper.entity.Sport;
import com.fitnesshelper.entity.SportType;

import java.util.Date;
import java.util.List;

public interface SportService {
    void createSport(Sport sport);

    public List<Sport> getSportsByDate(Date date);

    public void removeSport(Sport sport);

    public void removeSportByType(SportType.Existed sportType);

    public long calculateCalories(Date date);
}
