package com.fitnesshelper.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.Sport;
import com.fitnesshelper.entity.SportType;
import com.fitnesshelper.service.impl.SportServiceImpl;
import com.fitnesshelper.view.fragments.SportDialogFragment;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Date;
import java.util.List;

public class SportActivity extends AppCompatActivity {
    private TextView runMin;
    private TextView workoutMin;
    private TextView swimMin;
    private TextView cycleMin;
    private TextView runKm;
    private TextView workoutKm;
    private TextView swimKm;
    private TextView cycleKm;
    private SportServiceImpl sportService;
    CheckBox checkRun;
    CheckBox checkWorkout;
    CheckBox checkSwim;
    CheckBox checkCycle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        sportService = new SportServiceImpl(getApplicationContext());

        checkRun = (CheckBox) findViewById(R.id.runningCheckBox);
        checkWorkout = (CheckBox) findViewById(R.id.workoutCheckBox);
        checkSwim = (CheckBox) findViewById(R.id.swimmingCheckBox2);
        checkCycle = (CheckBox) findViewById(R.id.cycleCheckBox);

        runMin = (TextView) findViewById(R.id.sportMinRuningTextView);
        workoutMin = (TextView) findViewById(R.id.sportMinWorkoutTextView);
        swimMin = (TextView) findViewById(R.id.sportMinSwimmingTextView);
        cycleMin = (TextView) findViewById(R.id.sportMinCycleTextView);

        runKm = (TextView) findViewById(R.id.sportKmRunTextView);
        workoutKm = (TextView) findViewById(R.id.sportKmWorkoutTextView);
        swimKm = (TextView) findViewById(R.id.sportKmSwimmingTextView);
        cycleKm = (TextView) findViewById(R.id.sportKmCycleTextView);

        List<Sport> sportsByDate = sportService.getSportsByDate(new Date());

        if (CollectionUtils.isNotEmpty(sportsByDate)) {
            for (Sport sport : sportsByDate) {
                if (sport.getSportType().getId() == SportType.Existed.RUNNING.getId()) {
                    if (sport.getMeasureType().equals(Sport.Measure.METERS.getName())) {
                        runKm.setText(String.valueOf(sport.getMeasureValue()));
                    } else {
                        runMin.setText(String.valueOf(sport.getMeasureValue()));
                    }
                    checkRun.setChecked(true);
                } else if (sport.getSportType().getId() == SportType.Existed.SWIMMING.getId()) {
                    if (sport.getMeasureType().equals(Sport.Measure.METERS.getName())) {
                        swimKm.setText(String.valueOf(sport.getMeasureValue()));
                    } else {
                        swimMin.setText(String.valueOf(sport.getMeasureValue()));
                    }
                    checkSwim.setChecked(true);
                } else if (sport.getSportType().getId() == SportType.Existed.WORKOUT.getId()) {
                    if (sport.getMeasureType().equals(Sport.Measure.METERS.getName())) {
                        workoutKm.setText(String.valueOf(sport.getMeasureValue()));
                    } else {
                        workoutMin.setText(String.valueOf(sport.getMeasureValue()));
                    }
                    checkWorkout.setChecked(true);
                } else if (sport.getSportType().getId() == SportType.Existed.CYCLING.getId()) {
                    if (sport.getMeasureType().equals(Sport.Measure.METERS.getName())) {
                        cycleKm.setText(String.valueOf(sport.getMeasureValue()));
                    } else {
                        cycleMin.setText(String.valueOf(sport.getMeasureValue()));
                    }
                    checkCycle.setChecked(true);
                }
            }
        }

    }

    public void itemClickedRun(View view) {
        if (checkRun.isChecked()) {
            SportDialogFragment sportDialog = new SportDialogFragment();
            sportDialog.setcheck(checkRun, runMin, runKm, SportType.Existed.RUNNING);
            sportDialog.show(getFragmentManager(), "res");
        } else {
            runMin.setText("0");
            runKm.setText("0");
            sportService.removeSportByType(SportType.Existed.RUNNING);
        }
    }

    public void itemClickedWorkout(View view) {
        if (checkWorkout.isChecked()) {
            SportDialogFragment sportDialog = new SportDialogFragment();
            sportDialog.setcheck(checkWorkout, workoutMin, workoutKm, SportType.Existed.WORKOUT);
            sportDialog.show(getFragmentManager(), "res");
        } else {
            workoutMin.setText("0");
            workoutKm.setText("0");
            sportService.removeSportByType(SportType.Existed.RUNNING);
        }
    }

    public void itemClickedSwim(View view) {
        if (checkSwim.isChecked()) {
            SportDialogFragment sportDialog = new SportDialogFragment();
            sportDialog.setcheck(checkSwim, swimMin, swimKm, SportType.Existed.SWIMMING);
            sportDialog.show(getFragmentManager(), "res");
        } else {
            swimMin.setText("0");
            swimKm.setText("0");
            sportService.removeSportByType(SportType.Existed.RUNNING);
        }
    }

    public void itemClickedCycle(View view) {
        if (checkCycle.isChecked()) {
            SportDialogFragment sportDialog = new SportDialogFragment();
            sportDialog.setcheck(checkCycle, cycleMin, cycleKm, SportType.Existed.CYCLING);
            sportDialog.show(getFragmentManager(), "res");
        } else {
            cycleMin.setText("0");
            cycleKm.setText("0");
            sportService.removeSportByType(SportType.Existed.RUNNING);
        }
    }
}
