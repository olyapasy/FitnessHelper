package com.fitnesshelper.service.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.fitnesshelper.data.dao.SportDAO;
import com.fitnesshelper.data.dao.impl.SportDAOImpl;
import com.fitnesshelper.entity.Sport;
import com.fitnesshelper.entity.SportType;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SportServiceImpl {
    private SportDAO sportDAO;
    private SharedPreferences mySettings;

    public SportServiceImpl(Context context) {
        mySettings = context.getSharedPreferences("mySettings", MODE_PRIVATE);
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

    public long calculateCalories(Date date) {
        List<Sport> sportsByDate = getSportsByDate(date);
        long cal = 0;

        if (CollectionUtils.isNotEmpty(sportsByDate)) {
            String myWeight = mySettings.getString("myWeight", "0");
            long weight = Long.parseLong(myWeight);

            for (Sport sport : sportsByDate) {
                if (sport.getSportType().getId() == 1) {
                    if (sport.getMeasureType().equals("Meters")) {
                        cal += weight * sport.getMeasureValue() / 1000;
                    } else {
                        cal += 4.4f * sport.getMeasureValue();
                    }
                } else if (sport.getSportType().getId() == 3) {
                    if (sport.getMeasureType().equals("Meters")) {
                        cal += 3 * weight * sport.getMeasureValue() / 1000;
                    } else {
                        cal += 7f * weight * sport.getMeasureValue() / 60;
                    }
                } else if (sport.getSportType().getId() == 2) {
                    if (sport.getMeasureType().equals("Meters")) {
                        cal += sport.getMeasureValue() * 3;
                    } else {
                        cal += 4 * weight * sport.getMeasureValue() / 60;
                    }
                } else if (sport.getSportType().getId() == 4) {
                    if (sport.getMeasureType().equals("Meters")) {
                        cal += (5 / 15f) * weight * (sport.getMeasureValue() / 1000);
                    } else {
                        cal += 5 * weight * sport.getMeasureValue() / 60;
                    }
                }
            }
        }

        return cal;
    }
}
