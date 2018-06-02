package com.olyapasy.fitnesshelper.service.dao;

import com.olyapasy.fitnesshelper.entity.AbstractDish;

import java.util.List;

public interface DishDAO {
    List<AbstractDish> getAll();
    AbstractDish getById(long id);
    AbstractDish create(AbstractDish abstractDish);
    AbstractDish update(AbstractDish abstractDish);
    void delete(long id);
}
