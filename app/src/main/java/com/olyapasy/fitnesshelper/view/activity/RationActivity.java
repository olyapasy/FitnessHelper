package com.olyapasy.fitnesshelper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.entity.Ration;
import com.olyapasy.fitnesshelper.view.adapter.RationAdapter;
import com.olyapasy.fitnesshelper.view.fragments.RationEditFragment;

import java.util.ArrayList;

public class RationActivity extends AppCompatActivity {
    ArrayList<Ration> rations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ration);
        ListView rationList = (ListView) findViewById(R.id.rationList);
        final Button back = (Button) findViewById(R.id.backButton);
        //сделать кнопку назад
        final ImageButton addDish = (ImageButton) findViewById(R.id.addDishButton);
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

        final RationAdapter rationAdapter = new RationAdapter(getApplicationContext());
        rationList.setAdapter(rationAdapter);
        rationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Ration ration = (Ration) parent.getAdapter().getItem(position);
                RationEditFragment rationEditFragment = new RationEditFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("create", false);
                bundle.putLong("id", ration.getId());
                rationEditFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, rationEditFragment).commit();

            }
        });

        long totalAmountOfCal = rationAdapter.getTotalAmountOfCal();
        TextView totalAmountTextView = findViewById(R.id.totalAmountValue);
        totalAmountTextView.setText(String.valueOf(totalAmountOfCal));
    }
}
