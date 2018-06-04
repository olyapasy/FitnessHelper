package com.fitnesshelper.view.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.Sport;
import com.fitnesshelper.entity.SportType;
import com.fitnesshelper.service.impl.SportServiceImpl;

import java.util.Date;

public class SportDialogFragment extends DialogFragment {
    private EditText dialogMinutes;
    private EditText dialogKm;
    private TextView min;
    private TextView km;
    private CheckBox check;
    private SportType.Existed sportType;
    private Sport sport;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.sport_dialog, null);
        dialogMinutes = view.findViewById(R.id.minutesInputDialog);
        dialogKm = view.findViewById(R.id.kmInputDialog);
        final SportServiceImpl sportService = new SportServiceImpl(getActivity().getApplicationContext());

        builder.setView(view)
                .setTitle("Enter values")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialogMinutes.getText().toString().isEmpty()) {
                            check.setChecked(false);
                        }
                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String minutesValue = dialogMinutes.getText().toString();
                String kmValue = dialogKm.getText().toString();

                if (minutesValue.isEmpty() && kmValue.isEmpty()) {
                    Toast toast = Toast.makeText(getActivity(), "Fill one field",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    check.setChecked(false);
                } else {
                    if (minutesValue.isEmpty()) {
                        min.setText("0");
                        km.setText(kmValue);
                        sport = new Sport(0, new SportType(sportType), Sport.Measure.METERS.getName(),
                                Integer.parseInt(kmValue), new Date());

                    } else {
                        min.setText(minutesValue);
                        km.setText("0");
                        sport = new Sport(0, new SportType(sportType), Sport.Measure.MINUTES.getName(),
                                Integer.parseInt(minutesValue), new Date());
                    }
                    sportService.createSport(sport);
                }
            }
        });

        return builder.create();
    }

    public void setcheck(CheckBox check, TextView min, TextView km, SportType.Existed sportType) {
        this.check = check;
        this.min = min;
        this.km = km;
        this.sportType = sportType;
    }
}