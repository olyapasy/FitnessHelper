package com.olyapasy.fitnesshelper.service.dao;

import com.olyapasy.fitnesshelper.entity.Ration;

import java.util.Date;
import java.util.List;

public interface RationDAO {
    Ration getById(long id);
    List<Ration> getByDate(Date date);
    Ration create(Ration ration);
    Ration update(Ration ration);
    void delete(long id);
    void deleteAllByDate(Date date);
}
