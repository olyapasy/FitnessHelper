package com.fitnesshelper.entity;

public class SportType {
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

    public SportType(Existed existed) {
        this.id = existed.getId();
        this.name = existed.getName();
    }

    public SportType() {
    }

    public enum Existed {
        RUNNING(1, "Running"), SWIMMING(2, "Swimming"), WORKOUT(3, "Workout"),
        CYCLING(4, "Cycling");

        private long id;
        private String name;

        Existed(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
