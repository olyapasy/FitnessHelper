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

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RationDAOImpl implements RationDAO {
    private DataBaseHandler dataBaseHandler;
    private SQLiteDatabase sqLiteDatabase;
    private final String SELECT_BY_DATE = "SELECT  * FROM ration where date = ?";
    private final String SELECT_BY_ID = "SELECT  * FROM ration where id = ?";
    private final String SELECT_RATION_DISH = "SELECT  * FROM dish where id " +
            "in(SELECT dish_id FROM dish_ration WHERE ration_id = ?)";
    private final String INSERT_RATION = "INSERT INTO ration (id,name, date) VALUES(?,?,?)";
    private final String INSERT_RATION_DISH = "INSERT INTO dish_ration (id,dish_id, ration_id)" +
            " VALUES(?,?,?)";

    public RationDAOImpl(Context context) {
        this.dataBaseHandler = new DataBaseHandler(context);
    }

    @Override
    public Ration getById(long id) {
        sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_BY_ID,
                new String[]{String.valueOf(id)});
        Ration ration = null;

        try {
            ration = EntityConverter.convertToRation(cursor);
            cursor = sqLiteDatabase.rawQuery(SELECT_RATION_DISH,
                    new String[]{String.valueOf(ration.getId())});
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
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_BY_DATE,
                new String[]{String.valueOf(date)});
        List<Ration> rationList = Collections.emptyList();

        try {
            rationList = EntityConverter.convertToRationList(cursor);

            for (Ration ration : rationList) {
                cursor = sqLiteDatabase.rawQuery(SELECT_RATION_DISH,
                        new String[]{String.valueOf(ration.getId())});
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
    public Ration create(Ration ration) {
        sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", ration.getName());
        values.put("date", String.valueOf(ration.getDate()));

        sqLiteDatabase.insert("ration", null, values);
        List<AbstractDish> listOfDish = ration.getListOfDish();

        if (CollectionUtils.isNotEmpty(listOfDish)) {

        }

        return null;
    }

    @Override
    public Ration update(Ration ration) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void deleteAllByDate(Date date) {

    }

    private SQLiteDatabase getWritableDatabase() {
        return dataBaseHandler.getWritableDatabase();
    }
}
