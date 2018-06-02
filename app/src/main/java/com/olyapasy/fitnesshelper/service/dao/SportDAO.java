package com.olyapasy.fitnesshelper.service.dao;

import com.olyapasy.fitnesshelper.entity.Sport;

import java.util.Date;
import java.util.List;

public interface SportDAO {
    Sport getById(long id);
    List<Sport> getByDate(Date date);
    Sport create(Sport sport);
    Sport update(Sport sport);
    void delete(long id);
}
