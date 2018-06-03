package com.olyapasy.fitnesshelper.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.entity.DishType;
import com.olyapasy.fitnesshelper.view.fragments.ComplexDishFragment;
import com.olyapasy.fitnesshelper.view.fragments.SimpleDishFragment;

public class DishMealActivity extends FragmentActivity {
    Fragment dishFragment;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_meal);
        dishFragment = getSupportFragmentManager().findFragmentById(R.id.output);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            long typeId = extras.getLong("type");
            boolean create = extras.getBoolean("create");
            if (typeId == DishType.Existed.SIMPLE.getId() || create) {
                fragment = new SimpleDishFragment();
            } else {
                fragment = new ComplexDishFragment();
            }
        }
        else {
            extras = new Bundle();
            extras.putBoolean("create", true);
            extras.putInt("activity", 2);
            fragment = new SimpleDishFragment();
        }

        fragment.setArguments(extras);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.output, fragment);
        transaction.commit();
    }
}
