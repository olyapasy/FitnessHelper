package com.olyapasy.fitnesshelper.entity;

import java.util.Date;

public class SimpleDish extends AbstractDish {

    public SimpleDish(long id, String name, long calories, Date date) {
        super(id, name, new DishType(DishType.Existed.SIMPLE), calories, date);
    }
}
