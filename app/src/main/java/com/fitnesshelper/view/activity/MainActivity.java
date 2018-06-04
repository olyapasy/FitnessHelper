package com.fitnesshelper.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fitnesshelper.R;
import com.fitnesshelper.data.dao.impl.DishDAOImpl;
import com.fitnesshelper.data.dao.impl.RationDAOImpl;
import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.entity.CompositeDish;
import com.fitnesshelper.entity.Ration;
import com.fitnesshelper.entity.SimpleDish;
import com.fitnesshelper.view.fragments.PersonParamsDialogFragment;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);
        String myAge = sharedPref.getString("myAge", null);
        if (myAge == null) {
            openDialog();
        } else {
            String myWeight = sharedPref.getString("myWeight", null);
            String myHeight = sharedPref.getString("myHeight", null);
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
            }
        });

        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SportActivity.class);
                startActivity(i);
            }
        });
    }

    private void openDialog() {
        PersonParamsDialogFragment personParamsDialogFragment = new PersonParamsDialogFragment();
        personParamsDialogFragment.setSharedPreferences(getSharedPreferences("mySettings", MODE_PRIVATE));
        personParamsDialogFragment.show(getSupportFragmentManager(), "dialog");
    }
}
