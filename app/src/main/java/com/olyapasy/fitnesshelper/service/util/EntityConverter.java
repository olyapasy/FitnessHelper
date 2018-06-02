package com.olyapasy.fitnesshelper.service.util;

import android.database.Cursor;

import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.CompositeDish;
import com.olyapasy.fitnesshelper.entity.Ration;
import com.olyapasy.fitnesshelper.entity.SimpleDish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class EntityConverter {

    private static SimpleDish convertToSimple(Cursor cursor) {
        SimpleDish simpleDish;
        int id = cursor.getInt(1);
        String name = cursor.getString(2);
        int calories = cursor.getInt(4);
        Date date = new Date(cursor.getLong(5) * 1000);
        simpleDish = new SimpleDish(id, name, calories, date);

        return simpleDish;
    }

    private static CompositeDish convertToCompositeDish(Cursor cursor, Type type) {
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

    public static AbstractDish convertToDish(Cursor cursor) {
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

    public static List<AbstractDish> convertToDishList(Cursor cursor) {
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

    public static List<Ration> convertToRationList(Cursor cursor) {
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

    public static Ration convertToRation(Cursor cursor) {
        cursor.moveToFirst();
        Ration ration;

        long id = cursor.getLong(1);
        String name = cursor.getString(2);
        Date date = new Date(cursor.getLong(3) * 1000);
        ration = new Ration(id, name, date);

        return ration;
    }

    public enum Type {
        FULL, FOR_VIEW
    }
}
