package com.fitnesshelper.service.util;

import android.database.Cursor;

import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.entity.CompositeDish;
import com.fitnesshelper.entity.Ration;
import com.fitnesshelper.entity.SimpleDish;
import com.fitnesshelper.entity.Sport;
import com.fitnesshelper.entity.SportType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityConverter {

    private static SimpleDish convertToSimple(Cursor cursor) throws ParseException {
        if (cursor.getCount() != 0) {
            SimpleDish simpleDish;

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int calories = cursor.getInt(3);
            Date date = SimpleDateFormat.getDateInstance(3).parse(cursor.getString(4));
            simpleDish = new SimpleDish(id, name, calories, date);

            return simpleDish;
        }

        return null;
    }

    private static CompositeDish convertToCompositeDish(Cursor cursor) throws ParseException {
        if (cursor.getCount() != 0) {
            CompositeDish compositeDish;

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int calories = cursor.getInt(3);
            Date date = SimpleDateFormat.getDateInstance(3).parse(cursor.getString(4));
            compositeDish = new CompositeDish(id, name, calories, date,
                    Collections.<SimpleDish, Float>emptyMap());

            return compositeDish;
        }

        return null;
    }

    public static AbstractDish convertToDish(Cursor cursor) throws ParseException {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            AbstractDish abstractDish;

            int dishType = cursor.getInt(2);

            if (dishType == 1) {
                abstractDish = convertToSimple(cursor);
            } else {
                abstractDish = convertToCompositeDish(cursor);
            }

            return abstractDish;
        }

        return null;
    }

    public static List<AbstractDish> convertToDishList(Cursor cursor) throws ParseException {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            List<AbstractDish> abstractDishList = new ArrayList<>();

            do {
                AbstractDish abstractDish;
                int dishType = cursor.getInt(2);

                if (dishType == 1) {
                    abstractDish = convertToSimple(cursor);
                } else {
                    abstractDish = convertToCompositeDish(cursor);
                }
                abstractDishList.add(abstractDish);
            } while (cursor.moveToNext());

            return abstractDishList;
        }

        return Collections.emptyList();
    }

    public static Map<SimpleDish, Float> convertToDishMap(Cursor cursor) throws ParseException {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Map<SimpleDish, Float> dishMap = new HashMap<>();

            do {
                SimpleDish simpleDish = convertToSimple(cursor);
                float kgAmount = cursor.getFloat(5);
                dishMap.put(simpleDish, kgAmount);
            } while (cursor.moveToNext());

            return dishMap;
        }

        return Collections.emptyMap();
    }

    public static List<Ration> convertToRationList(Cursor cursor) throws ParseException {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Ration ration;
            List<Ration> rationList = new ArrayList<>();

            do {
                ration = convertToRation(cursor);

                rationList.add(ration);
            } while (cursor.moveToNext());

            return rationList;
        }

        return Collections.emptyList();
    }

    public static Ration convertToRation(Cursor cursor) throws ParseException {
        if (cursor.getCount() != 0) {
            Ration ration;

            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            Date date = SimpleDateFormat.getDateInstance(3).parse(cursor.getString(2));
            ration = new Ration(id, name, date);

            return ration;
        }

        return null;
    }

    public static Sport convertToSport(Cursor cursor) throws ParseException {
        if (cursor.getCount() != 0) {
            Sport sport;
            SportType sportType = null;

            long id = cursor.getLong(0);
            String measureType = cursor.getString(2);
            int measureValue = cursor.getInt(3);
            Date date = SimpleDateFormat.getDateInstance(3).parse(cursor.getString(4));

            int cursorInt = cursor.getInt(1);
            if (SportType.Existed.RUNNING.getId() == cursorInt) {
                sportType = new SportType(SportType.Existed.RUNNING);
            } else if (SportType.Existed.SWIMMING.getId() == cursorInt) {
                sportType = new SportType(SportType.Existed.SWIMMING);
            } else if (SportType.Existed.WORKOUT.getId() == cursorInt) {
                sportType = new SportType(SportType.Existed.WORKOUT);
            } else if (SportType.Existed.CYCLING.getId() == cursorInt) {
                sportType = new SportType(SportType.Existed.CYCLING);
            }

            sport = new Sport(id, sportType, measureType, measureValue, date);

            return sport;
        }

        return null;
    }

    public static List<Sport> convertToSportList(Cursor cursor) throws ParseException {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Sport sport;
            List<Sport> sportList = new ArrayList<>();

            do {
                sport = convertToSport(cursor);
                sportList.add(sport);
            } while (cursor.moveToNext());

            return sportList;
        }

        return null;
    }

    public static SportType convertToSportType(Cursor cursor) {
        if (cursor.getCount() != 0) {
            SportType sportType = new SportType();

            int id = cursor.getInt(0);
            String name = cursor.getString(1);

            sportType.setId(id);
            sportType.setName(name);

            return sportType;
        }

        return null;
    }

    public static List<SportType> convertToSportTypeList(Cursor cursor) {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SportType sportType;
            List<SportType> sportList = new ArrayList<>();

            do {
                sportType = convertToSportType(cursor);
                sportList.add(sportType);
            } while (cursor.moveToNext());

            return sportList;
        }

        return null;
    }
}
