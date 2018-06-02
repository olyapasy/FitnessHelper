package com.olyapasy.fitnesshelper.entity;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class CompositeDish extends AbstractDish {
    private Set<SimpleDish> setOfSimpleDish;

    public CompositeDish(long id, String name, long calories, Date date, Set<SimpleDish> setOfSimpleDish) {
        super(id, name, new DishType(DishType.Existed.COMPOSITE), calories, date);
        this.setOfSimpleDish = setOfSimpleDish;
    }

    public CompositeDish(String name, long calories, Date date, Set<SimpleDish> setOfSimpleDish) {
        super(name, new DishType(DishType.Existed.COMPOSITE), calories, date);
        this.setOfSimpleDish = setOfSimpleDish;
    }



    public Set<SimpleDish> getSetOfSimpleDish() {
        return setOfSimpleDish;
    }

    public void setSetOfSimpleDish(Set<SimpleDish> setOfSimpleDish) {
        this.setOfSimpleDish = setOfSimpleDish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeDish)) return false;
        if (!super.equals(o)) return false;
        CompositeDish that = (CompositeDish) o;
        return Objects.equals(setOfSimpleDish, that.setOfSimpleDish);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), setOfSimpleDish);
    }
}
