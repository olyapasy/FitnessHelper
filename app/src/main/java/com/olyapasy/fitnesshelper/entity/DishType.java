package com.olyapasy.fitnesshelper.entity;

public class DishType {
    private long id;
    private String name;

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

    public DishType(Existed existedDishType) {
        this.id = existedDishType.getId();
        this.name = existedDishType.getName();
    }


    enum Existed{
        SIMPLE(1, "simple"), COMPOSITE(2, "composite");

        private int id;
        private String name;

        Existed(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
