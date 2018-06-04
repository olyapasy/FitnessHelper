package com.fitnesshelper.data.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fitnesshelper.data.dao.DishDAO;
import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.entity.CompositeDish;
import com.fitnesshelper.entity.SimpleDish;
import com.fitnesshelper.service.util.DataBaseHandler;
import com.fitnesshelper.service.util.EntityConverter;

import org.apache.commons.collections4.MapUtils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DishDAOImpl implements DishDAO {
    private DataBaseHandler dataBaseHandler;
    private SQLiteDatabase sqLiteDatabase;
    private final String TABLE_NAME = "dish";
    private final String TABLE_REF_NAME = "dish_ref";
    private final String SELECT_FIRST = "SELECT  * from dish WHERE min(id);";
    private final String SELECT_DISH_FROM_RATION_REF_TABLE = "SELECT id from dish_ration where dish_id = ?";
    private final String SELECT_DISH_FROM_DISH_REF_TABLE = "SELECT id from dish_ref where dish_to_consist_of_id = ?";
    private final String SELECT_DISH_OF_COMPOSTE_DISH = "SELECT d.*, dr.kg_amount FROM dish d," +
            " dish_ref dr where d.id = dr.dish_to_consist_of_id and dr.composite_dish_id = ?";

    public DishDAOImpl(Context context) {
        this.dataBaseHandler = new DataBaseHandler(context);
    }

    @Override
    public List<AbstractDish> getAll() {
        sqLiteDatabase = getWritableDatabase();
        List<AbstractDish> abstractDishList = Collections.emptyList();

        try (Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null)) {
            System.out.println(cursor.getColumnCount());
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
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        try {
            abstractDish = EntityConverter.convertToDish(cursor);

            if (abstractDish instanceof CompositeDish) {
                cursor = sqLiteDatabase
                        .rawQuery(SELECT_DISH_OF_COMPOSTE_DISH, new String[]{String.valueOf(id)});
                Map<SimpleDish, Float> dishMap = EntityConverter.convertToDishMap(cursor);
                ((CompositeDish) abstractDish).setSimpleDishMap(dishMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            sqLiteDatabase.close();
        }

        return abstractDish;
    }

    @Override
    public long create(AbstractDish abstractDish) {
        sqLiteDatabase = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("name", abstractDish.getName());
            values.put("type", abstractDish.getDishType().getId());
            values.put("calories", abstractDish.getCalories());
            values.put("date", SimpleDateFormat.getDateInstance(3).format(abstractDish.getDate()));
            long insert = sqLiteDatabase.insert(TABLE_NAME, null, values);

            if (abstractDish instanceof CompositeDish) {
                Map<SimpleDish, Float> setOfSimpleDish = ((CompositeDish) abstractDish).getSimpleDishMap();

                if (MapUtils.isNotEmpty(setOfSimpleDish)) {
                    Set<SimpleDish> simpleDishes = setOfSimpleDish.keySet();
                    for (SimpleDish dish : simpleDishes) {
                        values.clear();
                        values.put("composite_dish_id", insert);
                        values.put("dish_to_consist_of_id", dish.getId());
                        values.put("kg_amount", setOfSimpleDish.get(dish));

                        sqLiteDatabase.insert(TABLE_REF_NAME, null, values);
                    }
                }
            }

            return insert;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }

        return 0;
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
                Map<SimpleDish, Float> setOfSimpleDish = ((CompositeDish) abstractDish).getSimpleDishMap();

                if (MapUtils.isNotEmpty(setOfSimpleDish)) {
                    Set<SimpleDish> simpleDishes = setOfSimpleDish.keySet();
                    for (SimpleDish dish : simpleDishes) {
                        values.clear();
                        values.put("composite_dish_id", abstractDish.getId());
                        values.put("dish_to_consist_of_id", dish.getId());
                        values.put("kg_amount", setOfSimpleDish.get(dish));

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

    @Override
    public boolean checkIdInUse(long id) {
        sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_DISH_FROM_RATION_REF_TABLE,
                new String[]{String.valueOf(id)});

        try {
            if (cursor.getCount() > 0) {
                return true;
            }
            cursor = sqLiteDatabase.rawQuery(SELECT_DISH_FROM_DISH_REF_TABLE,
                    new String[]{String.valueOf(id)});

            return cursor.getCount() > 0;
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            sqLiteDatabase.close();
        }

        return true;
    }

    @Override
    public AbstractDish getFirst() {
        sqLiteDatabase = getWritableDatabase();
        AbstractDish abstractDish = null;

        try (Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null,
                null, null, null, null, String.valueOf(1))) {
            abstractDish = EntityConverter.convertToDish(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }

        return abstractDish;
    }

    private SQLiteDatabase getWritableDatabase() {
        return dataBaseHandler.getWritableDatabase();
    }
}
