package com.fitnesshelper.view.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.Sport;
import com.fitnesshelper.entity.SportType;
import com.fitnesshelper.view.activity.SportActivity;

public class SportDialogFragment extends DialogFragment {
    private EditText minutes;
    private EditText km;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.sport_dialog, null);
        minutes = view.findViewById(R.id.minutesInputDialog);
        km = view.findViewById(R.id.kmInputDialog);

        builder.setView(view)
                .setTitle("Enter values")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String minutesValue = minutes.getText().toString();
                String kmValue = km.getText().toString();

                if (minutesValue.isEmpty() || kmValue.isEmpty()) {
                    Toast toast = Toast.makeText(getActivity(), "Fill all fields",
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
//                    sport.setMeasureValue(Integer.parseInt(minutesValue));
//                    sport.setMeasureType(kmValue);
                }
            }
        });

        return builder.create();
    }
}