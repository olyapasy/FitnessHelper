package com.fitnesshelper.entity;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CompositeDish extends AbstractDish {
    private Map<SimpleDish, Float> simpleDishMap;

    public CompositeDish(long id, String name, long calories, Date date,  Map<SimpleDish, Float> simpleDishMap) {
        super(id, name, new DishType(DishType.Existed.COMPOSITE), calories, date);
        this.simpleDishMap = simpleDishMap;
    }

    public Map<SimpleDish, Float> getSimpleDishMap() {
        return simpleDishMap;
    }

    public void setSimpleDishMap(Map<SimpleDish, Float> simpleDishMap) {
        this.simpleDishMap = simpleDishMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeDish)) return false;
        if (!super.equals(o)) return false;
        CompositeDish that = (CompositeDish) o;
        return Objects.equals(simpleDishMap, that.simpleDishMap);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), simpleDishMap);
    }
}
