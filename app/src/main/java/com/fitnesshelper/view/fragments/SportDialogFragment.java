package com.fitnesshelper.view.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.Sport;
import com.fitnesshelper.entity.SportType;
import com.fitnesshelper.service.impl.SportServiceImpl;

import java.util.Arrays;
import java.util.Date;

public class SportDialogFragment extends DialogFragment {
    private EditText value;
    private Spinner spinner;
    private CheckBox check;
    private SportType.Existed sportType;
    private Sport sport;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.sport_dialog, null);
        value = view.findViewById(R.id.sportInputValue);
        spinner = view.findViewById(R.id.sportInputSpiner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity()
                .getApplicationContext(), android.R.layout.simple_expandable_list_item_1,
                Arrays.asList(Sport.Measure.METERS.getName(), Sport.Measure.MINUTES.getName()));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        final SportServiceImpl sportService = new SportServiceImpl(getActivity().getApplicationContext());

        builder.setView(view)
                .setTitle("Enter values")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (value.getText().toString().isEmpty()) {
                            check.setChecked(false);
                        }
                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sportValue = value.getText().toString();

                if (sportValue.isEmpty()) {
                    Toast toast = Toast.makeText(getActivity(), "Fill the value",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    check.setChecked(false);
                } else {
                    sport = new Sport(0, new SportType(sportType), spinner.getSelectedItem().toString(),
                            Integer.parseInt(sportValue), new Date());
                    sportService.createSport(sport);
                    getActivity().recreate();
                }
            }
        });

        return builder.create();
    }

    public void setcheck(CheckBox check, SportType.Existed sportType) {
        this.check = check;
        this.sportType = sportType;
    }
}