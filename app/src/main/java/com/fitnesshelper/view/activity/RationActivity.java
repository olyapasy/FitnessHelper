package com.fitnesshelper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.Ration;
import com.fitnesshelper.service.impl.RationServiceImpl;
import com.fitnesshelper.view.adapter.RationAdapter;
import com.fitnesshelper.view.fragments.RationFragment;

import java.util.ArrayList;
import java.util.Date;

public class RationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ration);
        ListView rationList = (ListView) findViewById(R.id.rationList);
        TextView totalAmountTextView = findViewById(R.id.totalAmountValue);

        final ImageButton addDish = (ImageButton) findViewById(R.id.addDishButton);
        final ImageButton addRation = (ImageButton) findViewById(R.id.addRationButton);
        final ImageButton allDishButton = (ImageButton) findViewById(R.id.allDishBut);
        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RationActivity.this, DishMealActivity.class);
                i.putExtra("create", true);
                i.putExtra("activity", 2);

                finish();
                startActivity(i);
            }
        });

        allDishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RationActivity.this, AllDishActivity.class);

                finish();
                startActivity(i);
            }
        });

        addRation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RationFragment rationFragment = new RationFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("create", true);
                rationFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, rationFragment).commit();
            }
        });

        final RationAdapter rationAdapter = new RationAdapter(getApplicationContext(), totalAmountTextView);
        rationList.setAdapter(rationAdapter);
        long totalAmountOfCal = new RationServiceImpl(getApplicationContext()).getTotalAmountOfCal(new Date());

        totalAmountTextView.setText(String.valueOf(totalAmountOfCal));
        rationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ration ration = (Ration) parent.getAdapter().getItem(position);
                RationFragment rationFragment = new RationFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("create", false);
                bundle.putLong("id", ration.getId());
                rationFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, rationFragment).commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
