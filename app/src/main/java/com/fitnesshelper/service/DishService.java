package com.fitnesshelper.service;

import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.entity.SimpleDish;

import java.util.List;
import java.util.Map;

public interface DishService {
    public List<AbstractDish> getAllDish();

    public List<AbstractDish> getAllSimpleDish();

    public List<String> getAllDishNames(List<AbstractDish> allDishes);

    public void remove(AbstractDish abstractDish);

    public void create(AbstractDish abstractDish);

    public AbstractDish getDishById(long id);

    public void update(AbstractDish abstractDish);

    public long getCompositeDishCalories(Map<SimpleDish, Float> simpleDishMap);

    public long getDishCalories(List<AbstractDish> abstractDishes);

    public boolean chekIfDishIsInUse(long id);

    public AbstractDish getAnyDish();

}