package com.olyapasy.fitnesshelper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.data.dao.impl.DishDAOImpl;
import com.olyapasy.fitnesshelper.entity.SimpleDish;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button rationButton = (Button) findViewById(R.id.rationButton);
        final Button sportButton = (Button) findViewById(R.id.sportButton);
        rationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DishDAOImpl(getApplicationContext()).create(new SimpleDish(1, "name", 101, new Date()));
                new DishDAOImpl(getApplicationContext()).create(new SimpleDish(1, "nameNAME", 01, new Date()));
                new DishDAOImpl(getApplicationContext()).create(new SimpleDish(1, "namNAMEe", 121, new Date()));
                Intent i = new Intent(MainActivity.this,RationActivity.class);
                startActivity(i);
            }
        });
        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SportActivity.class);
                startActivity(i);
            }
        });

    }
}
