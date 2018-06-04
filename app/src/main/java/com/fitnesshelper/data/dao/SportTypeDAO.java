package com.fitnesshelper.data.dao;

import com.fitnesshelper.entity.SportType;

import java.util.List;

public interface SportTypeDAO {
    List<SportType> getAll();

    SportType getTypeById(long id);
}
