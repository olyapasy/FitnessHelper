package com.olyapasy.fitnesshelper.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.view.adapter.AllDishAdapter;

public class AllDishActivity extends AppCompatActivity {
    ListView listView;
    private AllDishAdapter allDishAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_dish_activity);

        listView = (ListView) findViewById(R.id.allDishListView);
        allDishAdapter = new AllDishAdapter(getApplicationContext());
        listView.setAdapter(allDishAdapter);
    }
}
