package com.fitnesshelper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.view.adapter.AllDishAdapter;
import com.fitnesshelper.R;

public class AllDishActivity extends AppCompatActivity {
    android.support.v4.app.Fragment dishFragment;
    ListView listView;
    private AllDishAdapter allDishAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_dish_activity);

        listView = (ListView) findViewById(R.id.allDishListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AllDishActivity.this, DishMealActivity.class);
                AbstractDish abstractDish = (AbstractDish) parent.getAdapter().getItem(position);
                intent.putExtra("create", false);
                intent.putExtra("id", abstractDish.getId());
                intent.putExtra("type", abstractDish.getDishType().getId());
                intent.putExtra("activity", 1);

                finish();
                startActivity(intent);
            }
        });
        allDishAdapter = new AllDishAdapter(getApplicationContext());
        allDishAdapter.notifyDataSetChanged();
        listView.setAdapter(allDishAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        Intent intent = new Intent(AllDishActivity.this, RationActivity.class);
        startActivity(intent);
    }
}
