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

public class SportDialogFragment extends DialogFragment {
    private EditText dialogMinutes;
    private EditText dialogKm;
    private TextView min;
    private TextView km;
    private CheckBox check;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.sport_dialog, null);
        dialogMinutes = view.findViewById(R.id.minutesInputDialog);
        dialogKm = view.findViewById(R.id.kmInputDialog);

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
                    Toast toast = Toast.makeText(getActivity(), "Fill at least one field",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    check.setChecked(false);
                } else {
                    if (minutesValue.isEmpty()) {
                        min.setText("0");
                        km.setText(kmValue);
                    } else {
                        min.setText(minutesValue);
                        km.setText("0");
                    }

                }
            }
        });

        return builder.create();
    }

    public void setcheck(CheckBox check, TextView min, TextView km) {
        this.check = check;
        this.min = min;
        this.km = km;
    }
}