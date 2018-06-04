package com.fitnesshelper.data.dao;

import com.fitnesshelper.entity.Ration;

import java.util.Date;
import java.util.List;

public interface RationDAO {
    Ration getById(long id);

    List<Ration> getByDate(Date date);

    long getCaloriesById(long id);

    void create(Ration ration);

    void update(Ration ration);

    void delete(long id);

    void deleteAllByDate(Date date);
}
