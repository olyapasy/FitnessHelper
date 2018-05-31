package com.olyapasy.fitnesshelper.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.olyapasy.fitnesshelper.Fragments.DishFragment;
import com.olyapasy.fitnesshelper.R;

public class RationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ration);
        final Button back = (Button) findViewById(R.id.backButton);
        //сделать кнопку назад
        final ImageButton addDish = (ImageButton) findViewById(R.id.addDishButton);
        final ImageButton calendarButton = (ImageButton) findViewById(R.id.calenderButton);
        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RationActivity.this,DishMealActivity.class);
                startActivity(i);

            }
        });




    }
}
