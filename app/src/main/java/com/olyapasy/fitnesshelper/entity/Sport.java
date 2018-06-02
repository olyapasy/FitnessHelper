package com.olyapasy.fitnesshelper.entity;

import java.util.Date;
import java.util.Objects;

public class Sport {
    private long id;
    private SportType sportType;
    private String measureType;
    private int measureValue;
    private Date date;

    public Sport(long id, SportType sportType, String measureType, int measureValue, Date date) {
        this.id = id;
        this.sportType = sportType;
        this.measureType = measureType;
        this.measureValue = measureValue;
        this.date = date;
    }

    public Sport(SportType sportType, String measureType, int measureValue, Date date) {
        this.sportType = sportType;
        this.measureType = measureType;
        this.measureValue = measureValue;
        this.date = date;
    }

    public Sport() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    public int getMeasureValue() {
        return measureValue;
    }

    public void setMeasureValue(int measureValue) {
        this.measureValue = measureValue;
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
        if (!(o instanceof Sport)) return false;
        Sport sport = (Sport) o;
        return id == sport.id &&
                measureValue == sport.measureValue &&
                Objects.equals(sportType, sport.sportType) &&
                Objects.equals(measureType, sport.measureType) &&
                Objects.equals(date, sport.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sportType, measureType, measureValue, date);
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", sportType=" + sportType +
                ", measureType='" + measureType + '\'' +
                ", measureValue=" + measureValue +
                ", date=" + date +
                '}';
    }
}
