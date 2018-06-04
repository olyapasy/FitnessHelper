package com.fitnesshelper.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.Sport;
import com.fitnesshelper.entity.SportType;
import com.fitnesshelper.view.fragments.SportDialogFragment;

public class SportActivity extends AppCompatActivity {
    Sport sport;
    Sport type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        fillData();
        TextView runMin = (TextView) findViewById(R.id.sportMinRuningTextView);
        TextView workoutMin = (TextView) findViewById(R.id.sportMinWorkoutTextView);
        TextView swimMin = (TextView) findViewById(R.id.sportMinSwimmingTextView);
        TextView cycleMin = (TextView) findViewById(R.id.sportMinCycleTextView);

        TextView runKm = (TextView) findViewById(R.id.sportKmRunTextView);
        TextView workoutKm = (TextView) findViewById(R.id.sportKmWorkoutTextView);
        TextView swimKm = (TextView) findViewById(R.id.sportKmSwimmingTextView);
        TextView cycleKm = (TextView) findViewById(R.id.sportKmCycleTextView);

//        runMin.setText(sport.getMeasureValue());
//        runMin.setText(sport.getMeasureValue());






//        EditText runningM = (EditText) findViewById(R.id.runningMin);
//        EditText runningKm = (EditText) findViewById(R.id.runningKm);
//        EditText workountM = (EditText) findViewById(R.id.workoutMin);
//
//        String runM = runningM.getText().toString();
//        String runKm = runningKm.getText().toString();
//        String workout = workountM.getText().toString();
//        runningM.setText(runM);
//        runningKm.setText(runKm);
//        workountM.setText(workout);



        // do something else

    }

    public void itemClicked(View view) {
        CheckBox checkRun = (CheckBox) findViewById(R.id.runningCheckBox);
        CheckBox checkWorkout = (CheckBox) findViewById(R.id.workoutCheckBox);
        CheckBox checkSwim = (CheckBox) findViewById(R.id.swimmingCheckBox2);
        CheckBox checkCycle = (CheckBox) findViewById(R.id.cycleCheckBox);
        if(checkRun.isChecked()){
            SportDialogFragment sportDialog = new SportDialogFragment();
            sportDialog.show(getFragmentManager(), "Результат");


        }
    }

    public void fillData() {
    }
}
