package com.olyapasy.fitnesshelper.service.impl;

import android.content.Context;

import com.olyapasy.fitnesshelper.data.dao.DishDAO;
import com.olyapasy.fitnesshelper.data.dao.impl.DishDAOImpl;
import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.SimpleDish;
import com.olyapasy.fitnesshelper.service.DishService;

import java.util.ArrayList;
import java.util.List;

public class DishServiceImpl implements DishService {
    private DishDAO dishDAO;

    public DishServiceImpl(Context context) {
        dishDAO = new DishDAOImpl(context);
    }

    public List<SimpleDish> getAllSimpleDish() {
        List<SimpleDish> simpleDishes = new ArrayList<>();
        List<AbstractDish> all = dishDAO.getAll();

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
}

