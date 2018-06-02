package com.olyapasy.fitnesshelper.service.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.CompositeDish;
import com.olyapasy.fitnesshelper.entity.SimpleDish;
import com.olyapasy.fitnesshelper.service.dao.DishDAO;
import com.olyapasy.fitnesshelper.service.util.DataBaseHandler;
import com.olyapasy.fitnesshelper.service.util.EntityConverter;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DishDAOImpl implements DishDAO {
    private DataBaseHandler dataBaseHandler;
    private SQLiteDatabase sqLiteDatabase;
    private final String SELECT_ALL_QUERY = "SELECT  * FROM dish";
    private final String SELECT_DISH_BY_ID = "SELECT  * FROM dish " +
            "where " +
            "id = ?\n" +
            "union all\n" +
            "select * from dish " +
            "where " +
            "id in(SELECT dish_to_consist_of_id from dish_ref where composite_dish_id = ?)";
    private final String INSERT_DISH = "INSERT INTO dish_ref (id,name, type, calories, date)" +
            " VALUES(?,?,?,?,?)";
    private final String INSERT_COMPOSITE_REF = "INSERT INTO dish" +
            " (id,composite_dish_id, dish_to_consist_of_id) VALUES(?,?,?)";

    private final String UPDATE_DISH = "UPDATE dish SET name = ?, calories = ?, date = ? WHERE id = ?";
    private final String DELETE_DISH_REF = "DELETE FROM dish_ref WHERE composite_dish_id = ?";
    private final String DELETE_DISH = "DELETE FROM dish WHERE id = ?";

//    UPDATE table_name
//    SET column1 = value1, column2 = value2...., columnN = valueN
//    WHERE [condition];

    //    private final String SELECT_DISH_BY_ID = "SELECT  * FROM dish where id = ?\n";
//    INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)
//    VALUES (2, 'Allen', 25, 'Texas', 15000.00 );
//    private final String SELECT_COMPOSITE_DISH_REF = "SELECT * FROM dish where id " +
//            "in(SELECT dish_to_consist_of_id from dish_ref where composite_dish_id = ?)";


    public DishDAOImpl(Context context) {
        this.dataBaseHandler = new DataBaseHandler(context);
    }

    @Override
    public List<AbstractDish> getAll() {
        sqLiteDatabase = getWritableDatabase();
        List<AbstractDish> abstractDishList = Collections.emptyList();

        try (Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_QUERY, null)) {
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
    public AbstractDish create(AbstractDish abstractDish) {
        sqLiteDatabase = getWritableDatabase();

        try {
            sqLiteDatabase.rawQuery(INSERT_DISH, new String[]{abstractDish.getName(),
                    String.valueOf(abstractDish.getDishType().getId()),
                    String.valueOf(abstractDish.getCalories()),
                    String.valueOf(abstractDish.getDate())});

            if (abstractDish instanceof CompositeDish) {
                Set<SimpleDish> setOfSimpleDish = ((CompositeDish) abstractDish).getSetOfSimpleDish();

                for (SimpleDish dish : setOfSimpleDish) {
                    sqLiteDatabase.rawQuery(INSERT_COMPOSITE_REF, new String[]{
                            String.valueOf(abstractDish.getId()), String.valueOf(dish.getId())});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }

        return null;
    }

    @Override
    public AbstractDish update(AbstractDish abstractDish) {
        sqLiteDatabase = getWritableDatabase();

        try {
            sqLiteDatabase.rawQuery(DELETE_DISH_REF, new String[]{String.valueOf(abstractDish.getId())});
            sqLiteDatabase.rawQuery(UPDATE_DISH, new String[]{abstractDish.getName(),
                    String.valueOf(abstractDish.getCalories()),
                    String.valueOf(abstractDish.getDate()),
                    String.valueOf(abstractDish.getId())});

            if (abstractDish instanceof CompositeDish) {
                Set<SimpleDish> setOfSimpleDish = ((CompositeDish) abstractDish).getSetOfSimpleDish();

                for (SimpleDish dish : setOfSimpleDish) {
                    sqLiteDatabase.rawQuery(INSERT_COMPOSITE_REF, new String[]{
                            String.valueOf(abstractDish.getId()), String.valueOf(dish.getId())});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }

        return null;
    }

    @Override
    public void delete(long id) {
        sqLiteDatabase = getWritableDatabase();

        try {
            sqLiteDatabase.rawQuery(DELETE_DISH, new String[]{String.valueOf(id)});
            sqLiteDatabase.rawQuery(DELETE_DISH_REF, new String[]{String.valueOf(id)});
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
