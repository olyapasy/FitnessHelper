package com.olyapasy.fitnesshelper.entity;

import java.util.Date;
import java.util.Objects;

public abstract class AbstractDish {
    private long id;
    private String name;
    private DishType dishType;
    private long calories;
    private Date date;

    public AbstractDish(long id, String name, DishType dishType, long calories, Date date) {
        this.id = id;
        this.name = name;
        this.dishType = dishType;
        this.calories = calories;
        this.date = date;
    }

    public AbstractDish(String name, DishType dishType, long calories, Date date) {
        this.name = name;
        this.dishType = dishType;
        this.calories = calories;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDish)) return false;
        AbstractDish that = (AbstractDish) o;
        return id == that.id &&
                calories == that.calories &&
                Objects.equals(name, that.name) &&
                Objects.equals(dishType, that.dishType) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, dishType, calories, date);
    }

    @Override
    public String toString() {
        return "AbstractDish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishType=" + dishType +
                ", calories=" + calories +
                ", date=" + date +
                '}';
    }
}
