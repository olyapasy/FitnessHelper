package com.olyapasy.fitnesshelper.data.dao;

import com.olyapasy.fitnesshelper.entity.AbstractDish;

import java.util.List;

public interface DishDAO {
    List<AbstractDish> getAll();

    AbstractDish getById(long id);

    void create(AbstractDish abstractDish);

    void update(AbstractDish abstractDish);

    void delete(long id);
}
