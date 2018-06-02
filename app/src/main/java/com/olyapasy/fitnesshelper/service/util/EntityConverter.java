package com.olyapasy.fitnesshelper.service.util;

import android.database.Cursor;

import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.CompositeDish;
import com.olyapasy.fitnesshelper.entity.Ration;
import com.olyapasy.fitnesshelper.entity.SimpleDish;
import com.olyapasy.fitnesshelper.entity.Sport;
import com.olyapasy.fitnesshelper.entity.SportType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class EntityConverter {

    private static SimpleDish convertToSimple(Cursor cursor) {
        if (cursor.getCount() != 0) {
            SimpleDish simpleDish;

            int id = cursor.getInt(1);
            String name = cursor.getString(2);
            int calories = cursor.getInt(4);
            Date date = new Date(cursor.getLong(5) * 1000);
            simpleDish = new SimpleDish(id, name, calories, date);

            return simpleDish;
        }

        return null;
    }

    private static CompositeDish convertToCompositeDish(Cursor cursor, Type type) {
        if (cursor.getCount() != 0) {
            CompositeDish compositeDish;

            int id = cursor.getInt(1);
            String name = cursor.getString(2);
            int calories = cursor.getInt(4);
            Date date = new Date(cursor.getLong(5) * 1000);
            Set<SimpleDish> simpleDishHashSet = Collections.emptySet();

            if (type.equals(Type.FULL)) {
                do {
                    SimpleDish simpleDish = convertToSimple(cursor);
                    simpleDishHashSet.add(simpleDish);
                } while (cursor.moveToNext());
            }
            compositeDish = new CompositeDish(id, name, calories, date, simpleDishHashSet);

            return compositeDish;
        }

        return null;
    }

    public static AbstractDish convertToDish(Cursor cursor) {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            AbstractDish abstractDish;

            int dishType = cursor.getInt(3);

            if (dishType == 1) {
                abstractDish = convertToSimple(cursor);
            } else {
                abstractDish = convertToCompositeDish(cursor, Type.FULL);
            }

            return abstractDish;
        }

        return null;
    }

    public static List<AbstractDish> convertToDishList(Cursor cursor) {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            List<AbstractDish> abstractDishList = new ArrayList<>();

            do {
                AbstractDish abstractDish;
                int dishType = cursor.getInt(3);
                if (dishType == 1) {
                    abstractDish = convertToSimple(cursor);
                } else {
                    abstractDish = convertToCompositeDish(cursor, Type.FOR_VIEW);
                }
                abstractDishList.add(abstractDish);
            } while (cursor.moveToNext());

            return abstractDishList;
        }

        return null;
    }

    public static List<Ration> convertToRationList(Cursor cursor) {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Ration ration;
            List<Ration> rationList = new ArrayList<>();

            do {
                long id = cursor.getLong(1);
                String name = cursor.getString(2);
                Date date = new Date(cursor.getLong(3) * 1000);
                ration = new Ration(id, name, date);

                rationList.add(ration);
            } while (cursor.moveToNext());

            return rationList;
        }

        return null;
    }

    public static Ration convertToRation(Cursor cursor) {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Ration ration;

            long id = cursor.getLong(1);
            String name = cursor.getString(2);
            Date date = new Date(cursor.getLong(3) * 1000);
            ration = new Ration(id, name, date);

            return ration;
        }

        return null;
    }

    public static Sport convertToSport(Cursor cursor, List<SportType> sportTypeList) {
        if (cursor.getCount() != 0) {
            Sport sport;
            SportType sportType = null;

            long id = cursor.getLong(1);
            String measureType = cursor.getString(3);
            int measureValue = cursor.getInt(4);
            Date date = new Date(cursor.getLong(5) * 1000);

            for (SportType sType : sportTypeList) {
                if (sType.getId() == sType.getId()) {
                    sportType = sType;
                    break;
                }
            }

            sport = new Sport(id, sportType, measureType, measureValue, date);

            return sport;
        }

        return null;
    }

    public static List<Sport> convertToSportList(Cursor cursor, List<SportType> sportTypeList) {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Sport sport;
            List<Sport> sportList = new ArrayList<>();

            do {
                sport = convertToSport(cursor, sportTypeList);
                sportList.add(sport);
            } while (cursor.moveToNext());

            return sportList;
        }

        return null;
    }

    public static SportType convertToSportType(Cursor cursor) {
        if (cursor.getCount() != 0) {
            SportType sportType = new SportType();

            int id = cursor.getInt(1);
            String name = cursor.getString(2);

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

    public enum Type {
        FULL, FOR_VIEW
    }
}
