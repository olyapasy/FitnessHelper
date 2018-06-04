package com.fitnesshelper.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Ration {
    private long id;
    private String name;
    private List<AbstractDish> listOfDish;
    private Date date;

    public Ration(long id, String name, Date date) {
        this.id = id;
        this.name = name;
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

    public List<AbstractDish> getListOfDish() {
        return listOfDish;
    }

    public void setListOfDish(List<AbstractDish> listOfDish) {
        this.listOfDish = listOfDish;
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
        if (!(o instanceof Ration)) return false;
        Ration ration = (Ration) o;
        return id == ration.id &&
                Objects.equals(name, ration.name) &&
                Objects.equals(listOfDish, ration.listOfDish) &&
                Objects.equals(date, ration.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, listOfDish, date);
    }

    @Override
    public String toString() {
        return "Ration{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listOfDish=" + listOfDish +
                ", date=" + date +
                '}';
    }
}
