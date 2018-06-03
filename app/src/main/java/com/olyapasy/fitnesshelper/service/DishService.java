package com.olyapasy.fitnesshelper.service;

import com.olyapasy.fitnesshelper.entity.SimpleDish;

import java.util.List;

public interface DishService {

    List<SimpleDish> getAllSimpleDish();
    List<String> getAllDishNames(List<SimpleDish> allDishes) ;
}
