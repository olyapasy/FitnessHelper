package com.olyapasy.fitnesshelper.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.olyapasy.fitnesshelper.Fragments.DishFragment;
import com.olyapasy.fitnesshelper.R;

public class DishMealActivity extends FragmentActivity {
    DishFragment dishFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fragment fragment = new DishFragment();
        setContentView(R.layout.activity_dish_meal);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.output, fragment);
        transaction.commit();



    }
}
