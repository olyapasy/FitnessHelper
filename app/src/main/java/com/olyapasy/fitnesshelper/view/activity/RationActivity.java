package com.olyapasy.fitnesshelper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.entity.Ration;
import com.olyapasy.fitnesshelper.view.adapter.RationAdapter;

import java.util.ArrayList;
import java.util.Date;

public class RationActivity extends AppCompatActivity {
    ArrayList<Ration> rations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ration);
        ListView rationList = (ListView) findViewById(R.id.rationList);
        fillData();
        final Button back = (Button) findViewById(R.id.backButton);
        //сделать кнопку назад
        final ImageButton addDish = (ImageButton) findViewById(R.id.addDishButton);
        final ImageButton calendarButton = (ImageButton) findViewById(R.id.calenderButton);
        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RationActivity.this, DishMealActivity.class);
                i.putExtra("create", true);
                i.putExtra("activity", 2);

                startActivity(i);
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RationActivity.this, AllDishActivity.class);

                startActivity(i);
            }
        });

        final RationAdapter rationAdapter = new RationAdapter(rations, this);
        rationList.setAdapter(rationAdapter);
        rationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void fillData() {
        rations.add(new Ration(1, "Breakfast", new Date()));
        rations.add(new Ration(2, "Dinner", new Date()));
        rations.add(new Ration(3, "Supper", new Date()));
        rations.add(new Ration(1, "Break", new Date()));
    }
}
