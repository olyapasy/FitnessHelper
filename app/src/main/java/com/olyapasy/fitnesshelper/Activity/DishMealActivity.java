package com.olyapasy.fitnesshelper.Activity;

import android.support.constraint.Placeholder;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;

import com.olyapasy.fitnesshelper.Fragments.DishFragment;
import com.olyapasy.fitnesshelper.R;

public class DishMealActivity extends FragmentActivity {
    Fragment dishFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_meal);
        dishFragment = getSupportFragmentManager().findFragmentById(R.id.output);
//        Fragment fragment = new DishFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.output, fragment);
//        transaction.commit();




    }
}
