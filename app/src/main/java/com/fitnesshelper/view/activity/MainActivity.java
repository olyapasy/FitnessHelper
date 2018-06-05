package com.fitnesshelper.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.SimpleDish;
import com.fitnesshelper.service.impl.DishServiceImpl;
import com.fitnesshelper.service.impl.RationServiceImpl;
import com.fitnesshelper.service.impl.SportServiceImpl;
import com.fitnesshelper.view.fragments.PersonParamsDialogFragment;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private String myWeight;
    private String myHeight;
    private String myAge;
    private SharedPreferences sharedPref;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            actionBar = getSupportActionBar();
        }

        sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);
        myAge = sharedPref.getString("myAge", null);
        if (myAge == null) {
            openDialog();

            new DishServiceImpl(getApplicationContext()).create(new SimpleDish(0,
                    "Toast", 100, new Date()));
        } else {
            myWeight = sharedPref.getString("myWeight", null);
            myHeight = sharedPref.getString("myHeight", null);

            final Button rationButton = (Button) findViewById(R.id.rationButton);
            final Button sportButton = (Button) findViewById(R.id.sportButton);
            rationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                    finish();
                }
            });
            calculateCal();
        }
    }

    private void openDialog() {
        PersonParamsDialogFragment personParamsDialogFragment = new PersonParamsDialogFragment();
        personParamsDialogFragment.setSharedPreferences(sharedPref);
        personParamsDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    private void calculateCal() {
        long value = (long) (88.36f + (13.4f * Integer.parseInt(myWeight)) + (4.8f
                * Integer.parseInt(myHeight)) - (5.7f * Integer.parseInt(myAge)));
        ((TextView) findViewById(R.id.mainRecommendKcal)).setText(String.valueOf(value));

        long totalAmountOfCal = new RationServiceImpl(getApplicationContext())
                .getTotalAmountOfCal(new Date());
        ((TextView) findViewById(R.id.enterPlusKcal)).setText(String.valueOf(totalAmountOfCal));

        long sportAmountOfCal = new SportServiceImpl(getApplicationContext())
                .calculateCalories(new Date());
        ((TextView) findViewById(R.id.enterMinusKcal)).setText(String.valueOf(sportAmountOfCal));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.edit:
                openDialog();

        }
        return super.onOptionsItemSelected(item);
    }
}


