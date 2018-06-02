package com.olyapasy.fitnesshelper.Activity;

import android.support.constraint.Placeholder;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;

import com.olyapasy.fitnesshelper.Fragments.ComplexDishFragment;
import com.olyapasy.fitnesshelper.Fragments.DishFragment;
import com.olyapasy.fitnesshelper.R;

public class DishMealActivity extends FragmentActivity {
    Fragment dishFragment;
    Fragment complexDishFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_meal);
        dishFragment = getSupportFragmentManager().findFragmentById(R.id.output);
        //Bundle extras = getIntent().getExtras();
        //String name = "Its nothing here";


//        if (extras != null) {
//            name = extras.getString("name");
//        }
//        Bundle bundle = new Bundle();
//        bundle.putString("name", name);
//        ComplexDishFragment complexDishFragment = new ComplexDishFragment();
//        complexDishFragment.setArguments(bundle);
//        Fragment fragment = new DishFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.output, fragment);
//        transaction.commit();




    }
}
