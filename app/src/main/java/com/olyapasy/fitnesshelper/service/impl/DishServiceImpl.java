package com.olyapasy.fitnesshelper.service.impl;

import android.content.Context;

import com.olyapasy.fitnesshelper.data.dao.DishDAO;
import com.olyapasy.fitnesshelper.data.dao.impl.DishDAOImpl;
import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.SimpleDish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DishServiceImpl {
    private DishDAO dishDAO;

    public DishServiceImpl(Context context) {
        dishDAO = new DishDAOImpl(context);
    }

    public List<AbstractDish> getAllDish() {
        return dishDAO.getAll();
    }

    public List<SimpleDish> getAllSimpleDish() {
        List<SimpleDish> simpleDishes = new ArrayList<>();
        List<AbstractDish> all = getAllDish();

        for (AbstractDish aDish : all) {
            if (aDish.getDishType().getId() == 1) {
                simpleDishes.add((SimpleDish) aDish);
            }
        }

        return simpleDishes;
    }


    public List<String> getAllDishNames(List<SimpleDish> allDishes) {
        List<String> simpleDishes = new ArrayList<>();

        for (AbstractDish aDish : allDishes) {
            simpleDishes.add(aDish.getName());
        }

        return simpleDishes;
    }

    public void remove(AbstractDish abstractDish) {
        dishDAO.delete(abstractDish.getId());
    }

    public void create(AbstractDish abstractDish) {
        dishDAO.create(abstractDish);
    }

    public AbstractDish getDishById(long id) {
        return dishDAO.getById(id);
    }

    public void update(AbstractDish abstractDish) {
        dishDAO.update(abstractDish);
    }

    public long getCompositeDishCalories(Map<SimpleDish, Float> simpleDishMap) {
        Set<SimpleDish> simpleDishes = simpleDishMap.keySet();
        long calories = 0;

        for (SimpleDish sDish : simpleDishes) {
            calories += (sDish.getCalories() / 100) * simpleDishMap.get(sDish);
        }

        return calories;
    }
}

