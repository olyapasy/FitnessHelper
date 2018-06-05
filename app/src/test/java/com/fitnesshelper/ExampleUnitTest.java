package com.fitnesshelper;

import android.content.Context;

import com.fitnesshelper.data.dao.DishDAO;
import com.fitnesshelper.service.DishService;
import com.fitnesshelper.service.impl.DishServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Mock
    Context context;

    @Mock
    DishDAO dishDAO;

    @Before
    public void setUp() throws Exception {
        DishService dishService = spy(new DishServiceImpl(context));


    }

    @Test
    public void addition_isCorrect() {

    }
}