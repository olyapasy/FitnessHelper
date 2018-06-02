package com.olyapasy.fitnesshelper.service.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.CompositeDish;
import com.olyapasy.fitnesshelper.entity.SimpleDish;
import com.olyapasy.fitnesshelper.service.dao.DishDAO;
import com.olyapasy.fitnesshelper.service.util.DataBaseHandler;
import com.olyapasy.fitnesshelper.service.util.EntityConverter;

import org.apache.commons.collections4.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DishDAOImpl implements DishDAO {
    private DataBaseHandler dataBaseHandler;
    private SQLiteDatabase sqLiteDatabase;
    private final String TABLE_NAME = "dish";
    private final String TABLE_REF_NAME = "dish_ref";
    private final String SELECT_DISH_BY_ID = "SELECT  * FROM dish " +
            "where " +
            "id = ?\n" +
            "union all\n" +
            "select * from dish " +
            "where " +
            "id in(SELECT dish_to_consist_of_id from dish_ref where composite_dish_id = ?)";

    public DishDAOImpl(Context context) {
        this.dataBaseHandler = new DataBaseHandler(context);
    }

    @Override
    public List<AbstractDish> getAll() {
        sqLiteDatabase = getWritableDatabase();
        List<AbstractDish> abstractDishList = Collections.emptyList();

        try (Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null)) {
            abstractDishList = EntityConverter.convertToDishList(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }

        return abstractDishList;
    }

    @Override
    public AbstractDish getById(long id) {
        sqLiteDatabase = getWritableDatabase();
        AbstractDish abstractDish = null;

        try (Cursor cursor = sqLiteDatabase.rawQuery(SELECT_DISH_BY_ID,
                new String[]{String.valueOf(id)})) {
            abstractDish = EntityConverter.convertToDish(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }

        return abstractDish;
    }

    @Override
    public void create(AbstractDish abstractDish) {
        sqLiteDatabase = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("name", abstractDish.getName());
            values.put("type", abstractDish.getDishType().getId());
            values.put("calories", abstractDish.getCalories());
            values.put("date", SimpleDateFormat.getDateInstance(3).format(abstractDish.getDate()));
            sqLiteDatabase.insert(TABLE_NAME, null, values);

            if (abstractDish instanceof CompositeDish) {
                Set<SimpleDish> setOfSimpleDish = ((CompositeDish) abstractDish).getSetOfSimpleDish();
                if (CollectionUtils.isNotEmpty(setOfSimpleDish)) {
                    for (SimpleDish dish : setOfSimpleDish) {
                        values.clear();
                        values.put("composite_dish_id", abstractDish.getId());
                        values.put("dish_to_consist_of_id", dish.getId());

                        sqLiteDatabase.insert(TABLE_REF_NAME, null, values);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void update(AbstractDish abstractDish) {
        sqLiteDatabase = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("name", abstractDish.getName());
            values.put("calories", abstractDish.getCalories());
            values.put("date", SimpleDateFormat.getDateInstance(3).format(abstractDish.getDate()));

            sqLiteDatabase.update(TABLE_NAME, values, "id = ?",
                    new String[]{String.valueOf(abstractDish.getId())});

            if (abstractDish instanceof CompositeDish) {
                sqLiteDatabase.delete(TABLE_REF_NAME, "composite_dish_id = ?",
                        new String[]{String.valueOf(abstractDish.getId())});
                Set<SimpleDish> setOfSimpleDish = ((CompositeDish) abstractDish).getSetOfSimpleDish();

                for (SimpleDish dish : setOfSimpleDish) {
                    values.clear();
                    values.put("composite_dish_id", abstractDish.getId());
                    values.put("dish_to_consist_of_id", dish.getId());

                    sqLiteDatabase.insert(TABLE_REF_NAME, null, values);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            sqLiteDatabase.delete(TABLE_REF_NAME, "composite_dish_id = ?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }
    }

    private SQLiteDatabase getWritableDatabase() {
        return dataBaseHandler.getWritableDatabase();
    }
}
