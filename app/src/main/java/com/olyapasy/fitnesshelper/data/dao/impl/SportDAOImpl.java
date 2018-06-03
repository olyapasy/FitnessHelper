package com.olyapasy.fitnesshelper.data.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.olyapasy.fitnesshelper.entity.Sport;
import com.olyapasy.fitnesshelper.entity.SportType;
import com.olyapasy.fitnesshelper.data.dao.SportDAO;
import com.olyapasy.fitnesshelper.service.util.DataBaseHandler;
import com.olyapasy.fitnesshelper.service.util.EntityConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SportDAOImpl implements SportDAO {
    private DataBaseHandler dataBaseHandler;
    private SQLiteDatabase sqLiteDatabase;
    private final String TABLE_NAME = "sport";
    private List<SportType> sportTypeList;

    public SportDAOImpl(Context context) {
        this.dataBaseHandler = new DataBaseHandler(context);
        sportTypeList = new SportTypeDAOImpl(context).getAll();
    }

    public Sport getById(long id) {
        sqLiteDatabase = getWritableDatabase();
        Sport sport = null;

        try (Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null)) {
            sport = EntityConverter.convertToSport(cursor, sportTypeList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }

        return sport;
    }

    public List<Sport> getByDate(Date date) {
        sqLiteDatabase = getWritableDatabase();
        List<Sport> sportList = null;
        String formattedDate = SimpleDateFormat.getDateInstance(3).format(date);
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, "date = ?",
                new String[]{formattedDate}, null, null, null);

        try {
            sportList = EntityConverter.convertToSportList(cursor, sportTypeList);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }

        return sportList;
    }

    public void create(Sport sport) {
        sqLiteDatabase = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put("type", sport.getSportType().getId());
            values.put("measure_type", sport.getMeasureType());
            values.put("measure_value", sport.getMeasureValue());
            values.put("date", SimpleDateFormat.getDateInstance(3).format(sport.getDate()));

            sqLiteDatabase.insert(TABLE_NAME, null, values);
        } finally {
            sqLiteDatabase.close();
        }
    }

    public void update(Sport sport) {
        sqLiteDatabase = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put("type", sport.getSportType().getId());
            values.put("measure_type", sport.getMeasureType());
            values.put("measure_value", sport.getMeasureValue());
            values.put("date", SimpleDateFormat.getDateInstance(3).format(sport.getDate()));

            sqLiteDatabase.update(TABLE_NAME, values, "id = ?",
                    new String[]{String.valueOf(sport.getId())});
        } finally {
            sqLiteDatabase.close();
        }
    }

    public void delete(long id) {
        sqLiteDatabase = getWritableDatabase();

        try {
            sqLiteDatabase.delete(TABLE_NAME, "id = ?",
                    new String[]{String.valueOf(id)});
        } finally {
            sqLiteDatabase.close();
        }
    }

    private SQLiteDatabase getWritableDatabase() {
        return dataBaseHandler.getWritableDatabase();
    }
}
