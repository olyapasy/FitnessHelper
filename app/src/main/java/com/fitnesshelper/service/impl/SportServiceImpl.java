package com.fitnesshelper.service.impl;

import android.content.Context;

import com.fitnesshelper.data.dao.SportDAO;
import com.fitnesshelper.data.dao.impl.SportDAOImpl;
import com.fitnesshelper.entity.Sport;
import com.fitnesshelper.entity.SportType;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Date;
import java.util.List;

public class SportServiceImpl {
    private SportDAO sportDAO;

    public SportServiceImpl(Context context) {
        sportDAO = new SportDAOImpl(context);
    }

    public void createSport(Sport sport) {
        sportDAO.create(sport);
    }

    public List<Sport> getSportsByDate(Date date) {
        return sportDAO.getByDate(date);
    }

    public void removeSport(Sport sport) {
        sportDAO.delete(sport.getId());
    }

    public void removeSportByType(SportType.Existed sportType) {
        List<Sport> sportsByDate = getSportsByDate(new Date());

        if (CollectionUtils.isNotEmpty(sportsByDate)) {
            for (Sport sport : sportsByDate) {
                if (sport.getSportType().getId() == sportType.getId()) {
                    removeSport(sport);
                }
            }
        }
    }

    public long getSportCalories(Date date) {
        List<Sport> sportsByDate = getSportsByDate(new Date());
        long value = 1;

        return value;
    }
}
