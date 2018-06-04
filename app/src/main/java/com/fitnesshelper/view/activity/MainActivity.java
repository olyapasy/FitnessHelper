package com.fitnesshelper.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fitnesshelper.R;
import com.fitnesshelper.data.dao.impl.DishDAOImpl;
import com.fitnesshelper.data.dao.impl.RationDAOImpl;
import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.entity.CompositeDish;
import com.fitnesshelper.entity.Ration;
import com.fitnesshelper.entity.SimpleDish;
import com.fitnesshelper.service.impl.RationServiceImpl;
import com.fitnesshelper.view.fragments.PersonParamsDialogFragment;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String myWeight;
    String myHeight;
    String myAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);
        myAge = sharedPref.getString("myAge", null);
        if (myAge == null) {
            openDialog();
        } else {
            myWeight = sharedPref.getString("myWeight", null);
            myHeight = sharedPref.getString("myHeight", null);
        }

        final Button rationButton = (Button) findViewById(R.id.rationButton);
        final Button sportButton = (Button) findViewById(R.id.sportButton);
        rationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDish simpleDish = new SimpleDish(1, "name", 101, new Date());
                long l = new DishDAOImpl(getApplicationContext()).create(simpleDish);
                new DishDAOImpl(getApplicationContext()).create(new SimpleDish(1, "nameNAME", 01, new Date()));
                HashMap<SimpleDish, Float> simpleDishFloatHashMap = new HashMap<>();
                simpleDishFloatHashMap.put(new SimpleDish(l, "name", 101, new Date()), 0.2f);
                long namNAMEe = new DishDAOImpl(getApplicationContext()).create(new CompositeDish(1, "namNAMEe", 121, new Date(), simpleDishFloatHashMap));
                Ration ration = new Ration(0, "Breakfast", new Date());
                ration.setListOfDish(Arrays.<AbstractDish>asList(new SimpleDish(l, "name", 101, new Date())
                        , new SimpleDish(namNAMEe, "name", 101, new Date())));
                new RationDAOImpl(getApplicationContext()).create(ration);

                Intent i = new Intent(MainActivity.this, RationActivity.class);
                startActivity(i);
                finish();
            }
        });

        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SportActivity.class);
                startActivity(i);
            }
        });

        calculateCal();
    }

    private void openDialog() {
        PersonParamsDialogFragment personParamsDialogFragment = new PersonParamsDialogFragment();
        personParamsDialogFragment.setSharedPreferences(getSharedPreferences("mySettings", MODE_PRIVATE));
        personParamsDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    private void calculateCal() {
        float value = 88.36f + (13.4f * Integer.parseInt(myWeight)) + (4.8f
                * Integer.parseInt(myHeight)) - (5.7f * Integer.parseInt(myAge));
        ((TextView) findViewById(R.id.mainRecommendKcal)).setText(String.valueOf(value));

        long totalAmountOfCal = new RationServiceImpl(getApplicationContext()).getTotalAmountOfCal(new Date());
        ((TextView) findViewById(R.id.enterPlusKcal)).setText(String.valueOf(totalAmountOfCal));

        long sportAmountOfCal;
        ((TextView) findViewById(R.id.enterMinusKcal)).setText("");
    }
}
