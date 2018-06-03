package com.olyapasy.fitnesshelper.data.dao;

import com.olyapasy.fitnesshelper.entity.SportType;

import java.util.List;

public interface SportTypeDAO {
    List<SportType> getAll();

    SportType getTypeById(long id);
}
