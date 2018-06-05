package com.fitnesshelper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.Sport;
import com.fitnesshelper.entity.SportType;
import com.fitnesshelper.service.SportService;
import com.fitnesshelper.service.impl.SportServiceImpl;
import com.fitnesshelper.view.fragments.SportDialogFragment;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Date;
import java.util.List;

public class SportActivity extends AppCompatActivity {
    private TextView valueRun;
    private TextView valueSwim;
    private TextView valueWork;
    private TextView valueCycle;
    private SportService sportService;
    private CheckBox checkRun;
    private CheckBox checkWorkout;
    private CheckBox checkSwim;
    private CheckBox checkCycle;
    private TextView measureWork;
    private TextView measureRun;
    private TextView measureCycle;
    private TextView measureSwim;
    private TextView caloriesValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        sportService = new SportServiceImpl(getApplicationContext());

        checkRun = (CheckBox) findViewById(R.id.runningCheckBox);
        checkWorkout = (CheckBox) findViewById(R.id.workoutCheckBox);
        checkSwim = (CheckBox) findViewById(R.id.swimmingCheckBox2);
        checkCycle = (CheckBox) findViewById(R.id.cycleCheckBox);

        valueRun = (TextView) findViewById(R.id.valueRun);
        valueWork = (TextView) findViewById(R.id.valueWork);
        valueSwim = (TextView) findViewById(R.id.valueSwim);
        valueCycle = (TextView) findViewById(R.id.valueCycle);

        measureRun = (TextView) findViewById(R.id.measureRun);
        measureWork = (TextView) findViewById(R.id.measureWork);
        measureSwim = (TextView) findViewById(R.id.measureSwim);
        measureCycle = (TextView) findViewById(R.id.measureCycle);
        caloriesValue = findViewById(R.id.totalAmountSport);

        List<Sport> sportsByDate = sportService.getSportsByDate(new Date());
        calculateActivity(sportsByDate);

        long calculateCalories = sportService.calculateCalories(new Date());
        caloriesValue.setText(String.valueOf(calculateCalories));
    }

    public void itemClickedRun(View view) {
        if (checkRun.isChecked()) {
            SportDialogFragment sportDialog = new SportDialogFragment();
            sportDialog.setcheck(checkRun, SportType.Existed.RUNNING);
            sportDialog.show(getFragmentManager(), "res");
        } else {
            sportService.removeSportByType(SportType.Existed.RUNNING);
            recreate();
        }
    }

    public void itemClickedWorkout(View view) {
        if (checkWorkout.isChecked()) {
            SportDialogFragment sportDialog = new SportDialogFragment();
            sportDialog.setcheck(checkWorkout, SportType.Existed.WORKOUT);
            sportDialog.show(getFragmentManager(), "res");
        } else {
            sportService.removeSportByType(SportType.Existed.WORKOUT);
            recreate();
        }
    }

    public void itemClickedSwim(View view) {
        if (checkSwim.isChecked()) {
            SportDialogFragment sportDialog = new SportDialogFragment();
            sportDialog.setcheck(checkSwim, SportType.Existed.SWIMMING);
            sportDialog.show(getFragmentManager(), "res");
        } else {
            sportService.removeSportByType(SportType.Existed.SWIMMING);
            recreate();
        }
    }

    public void itemClickedCycle(View view) {
        if (checkCycle.isChecked()) {
            SportDialogFragment sportDialog = new SportDialogFragment();
            sportDialog.setcheck(checkCycle, SportType.Existed.CYCLING);
            sportDialog.show(getFragmentManager(), "res");
        } else {
            sportService.removeSportByType(SportType.Existed.CYCLING);
            recreate();
        }
    }

    private void calculateActivity(List<Sport> sportsByDate) {
        if (CollectionUtils.isNotEmpty(sportsByDate)) {
            for (Sport sport : sportsByDate) {
                if (sport.getSportType().getId() == SportType.Existed.RUNNING.getId()) {
                    valueRun.setText(String.valueOf(sport.getMeasureValue()));
                    measureRun.setText(sport.getMeasureType());
                    checkRun.setChecked(true);
                } else if (sport.getSportType().getId() == SportType.Existed.SWIMMING.getId()) {
                    valueSwim.setText(String.valueOf(sport.getMeasureValue()));
                    measureSwim.setText(sport.getMeasureType());
                    checkSwim.setChecked(true);
                } else if (sport.getSportType().getId() == SportType.Existed.WORKOUT.getId()) {
                    valueWork.setText(String.valueOf(sport.getMeasureValue()));
                    measureWork.setText(sport.getMeasureType());
                    checkWorkout.setChecked(true);
                } else if (sport.getSportType().getId() == SportType.Existed.CYCLING.getId()) {
                    valueCycle.setText(String.valueOf(sport.getMeasureValue()));
                    measureCycle.setText(sport.getMeasureType());
                    checkCycle.setChecked(true);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
