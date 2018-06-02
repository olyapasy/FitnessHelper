package com.olyapasy.fitnesshelper.service.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.olyapasy.fitnesshelper.R;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class DataBaseHandler extends SQLiteOpenHelper {
    private final Context context;


    public DataBaseHandler(Context context) {
        super(context, "fitness_helper_db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        InputStream is = null;
        try {
            is = context.getResources().openRawResource(R.raw.create_db);
            String createScript = IOUtils.toString(is, "utf-8");
            String[] scripts = createScript.split(";", 7);

            for (String script : scripts) {
                db.execSQL(script);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        InputStream is = null;
        try {
            is = context.getResources().openRawResource(R.raw.drop_db);
            String createScript = IOUtils.toString(is, "utf-8");
            String[] scripts = createScript.split(";");

            for (String script : scripts) {
                db.execSQL(script);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }

        onCreate(db);
    }
}

