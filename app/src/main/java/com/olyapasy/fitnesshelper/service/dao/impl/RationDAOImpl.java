package com.olyapasy.fitnesshelper.service.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.Ration;
import com.olyapasy.fitnesshelper.service.dao.RationDAO;
import com.olyapasy.fitnesshelper.service.util.DataBaseHandler;
import com.olyapasy.fitnesshelper.service.util.EntityConverter;

import org.apache.commons.collections4.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RationDAOImpl implements RationDAO {
    private DataBaseHandler dataBaseHandler;
    private SQLiteDatabase sqLiteDatabase;
    private final String TABLE_NAME = "ration";
    private final String TABLE_REF_NAME = "dish_ration";

    public RationDAOImpl(Context context) {
        this.dataBaseHandler = new DataBaseHandler(context);
    }

    @Override
    public Ration getById(long id) {
        sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        Ration ration = null;

        try {
            ration = EntityConverter.convertToRation(cursor);
            cursor = sqLiteDatabase.query(TABLE_REF_NAME, null,
                    "composite_dish_id = ?",
                    new String[]{String.valueOf(ration.getId())}, null, null,
                    null);
            List<AbstractDish> abstractDishList = EntityConverter.convertToDishList(cursor);
            ration.setListOfDish(abstractDishList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            sqLiteDatabase.close();
        }

        return ration;
    }

    @Override
    public List<Ration> getByDate(Date date) {
        sqLiteDatabase = getWritableDatabase();
        String formattedDate = SimpleDateFormat.getDateInstance(3).format(date);
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, "date = ?",
                new String[]{formattedDate}, null, null, null);
        List<Ration> rationList = Collections.emptyList();

        try {
            rationList = EntityConverter.convertToRationList(cursor);

            for (Ration ration : rationList) {
                cursor = sqLiteDatabase.query(TABLE_REF_NAME, null,
                        "composite_dish_id = ?",
                        new String[]{String.valueOf(ration.getId())}, null, null,
                        null);
                List<AbstractDish> abstractDishList = EntityConverter.convertToDishList(cursor);
                ration.setListOfDish(abstractDishList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            sqLiteDatabase.close();
        }

        return rationList;
    }

    @Override
    public void create(Ration ration) {
        sqLiteDatabase = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("name", ration.getName());
            values.put("date", SimpleDateFormat.getDateInstance(3).format(ration.getDate()));

            sqLiteDatabase.insert(TABLE_NAME, null, values);
            List<AbstractDish> listOfDish = ration.getListOfDish();

            if (CollectionUtils.isNotEmpty(listOfDish)) {
                for (AbstractDish dish : listOfDish) {
                    values.clear();

                    values.put("dish_id", dish.getId());
                    values.put("ration_id", ration.getId());
                    sqLiteDatabase.insert(TABLE_REF_NAME, null, values);
                }
            }
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void update(Ration ration) {
        sqLiteDatabase = getWritableDatabase();

        try {
            List<AbstractDish> listOfDish = ration.getListOfDish();
            ContentValues values = new ContentValues();

            values.put("name", ration.getName());
            sqLiteDatabase.update(TABLE_NAME, values, "id = ?",
                    new String[]{String.valueOf(ration.getId())});
            sqLiteDatabase.delete(TABLE_REF_NAME, "ration_id = ?",
                    new String[]{String.valueOf(ration.getId())});

            if (CollectionUtils.isNotEmpty(listOfDish)) {
                for (AbstractDish dish : listOfDish) {
                    values.clear();

                    values.put("dish_id", dish.getId());
                    values.put("ration_id", ration.getId());
                    sqLiteDatabase.insert(TABLE_REF_NAME, null, values);
                }
            }
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void delete(long id) {
        sqLiteDatabase = getWritableDatabase();

        try {
            sqLiteDatabase.delete(TABLE_NAME, "id = ?",
                    new String[]{String.valueOf(id)});
            sqLiteDatabase.delete(TABLE_REF_NAME, "ration_id = ?",
                    new String[]{String.valueOf(id)});
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void deleteAllByDate(Date date) {
        sqLiteDatabase = getWritableDatabase();
        String formattedDate = SimpleDateFormat.getDateInstance(3).format(date);

        try (Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{"id"},
                "date = ?", new String[]{formattedDate}, null,
                null, null)) {
            cursor.moveToFirst();
            do {
                long id = cursor.getLong(1);
                delete(id);
            } while (cursor.moveToNext());

        } finally {
            sqLiteDatabase.close();
        }
    }

    private SQLiteDatabase getWritableDatabase() {
        return dataBaseHandler.getWritableDatabase();
    }
}
