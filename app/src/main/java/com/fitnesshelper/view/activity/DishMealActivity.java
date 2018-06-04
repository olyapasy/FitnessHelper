package com.fitnesshelper.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.fitnesshelper.entity.DishType;
import com.fitnesshelper.R;
import com.fitnesshelper.view.fragments.ComplexDishFragment;
import com.fitnesshelper.view.fragments.SimpleDishFragment;

public class DishMealActivity extends AppCompatActivity {
    Fragment dishFragment;
    Fragment fragment;
    ActionBar actionBar;

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
        } else {
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

        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(intent);

    }
}
