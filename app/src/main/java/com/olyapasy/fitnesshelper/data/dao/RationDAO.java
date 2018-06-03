package com.olyapasy.fitnesshelper.data.dao;

import com.olyapasy.fitnesshelper.entity.Ration;

import java.util.Date;
import java.util.List;

public interface RationDAO {
    Ration getById(long id);

    List<Ration> getByDate(Date date);

    int getCaloriesById(long id);

    int getDishAmountById(long id);

    void create(Ration ration);

    void update(Ration ration);

    void delete(long id);

    void deleteAllByDate(Date date);
}
