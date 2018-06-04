package com.fitnesshelper.data.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fitnesshelper.data.dao.SportTypeDAO;
import com.fitnesshelper.entity.SportType;
import com.fitnesshelper.service.util.DataBaseHandler;
import com.fitnesshelper.service.util.EntityConverter;

import java.util.List;

public class SportTypeDAOImpl implements SportTypeDAO {
    private DataBaseHandler dataBaseHandler;
    private SQLiteDatabase sqLiteDatabase;
    private final String TABLE_NAME = "sport_type";

    public SportTypeDAOImpl(Context context) {
        this.dataBaseHandler = new DataBaseHandler(context);
    }

    @Override
    public List<SportType> getAll() {
        sqLiteDatabase = getWritableDatabase();
        List<SportType> sportTypeList;

        try (Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null)) {
            sportTypeList = EntityConverter.convertToSportTypeList(cursor);
        } finally {
            sqLiteDatabase.close();
        }

        return sportTypeList;
    }

    public SportType getTypeById(long id) {
        sqLiteDatabase = getWritableDatabase();
        SportType sportType;

        try (Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null)) {
            sportType = EntityConverter.convertToSportType(cursor);
        } finally {
            sqLiteDatabase.close();
        }


        return sportType;
    }

    private SQLiteDatabase getWritableDatabase() {
        return dataBaseHandler.getWritableDatabase();
    }
}
