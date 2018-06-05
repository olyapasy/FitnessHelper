package com.fitnesshelper.view.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fitnesshelper.R;

public class PersonParamsDialogFragment extends AppCompatDialogFragment {
    private EditText age;
    private EditText height;
    private EditText weight;
    SharedPreferences sharedPreferences;

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.custom_person_params_dialog, null);
        age = view.findViewById(R.id.ageInput);
        height = view.findViewById(R.id.heightInput);
        weight = view.findViewById(R.id.weightInput);

        final String ageVal = sharedPreferences.getString("myAge", null);
        String heightVal = sharedPreferences.getString("myHeight", null);
        String weightVal = sharedPreferences.getString("myWeight", null);

        if (ageVal != null) {
            age.setText(ageVal);
            height.setText(heightVal);
            weight.setText(weightVal);
        }

        final Toast toast = Toast.makeText(getContext(), "Fill all fields",
                Toast.LENGTH_SHORT);
        builder.setView(view)
                .setTitle("Personal data")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ageVal != null) {

                        } else {
                            toast.show();
                            showFragment();
                        }
                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ageValue = age.getText().toString();
                String heightValue = height.getText().toString();
                String weightValue = weight.getText().toString();

                if (ageValue.isEmpty() || heightValue.isEmpty() || weightValue.isEmpty()) {
                    toast.show();
                    showFragment();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("myAge", ageValue);
                    editor.putString("myHeight", heightValue);
                    editor.putString("myWeight", weightValue);
                    editor.commit();

                    getFragmentManager().beginTransaction().remove(PersonParamsDialogFragment.this).commit();
                    getActivity().recreate();
                }
            }
        });

        return builder.create();
    }

    private void showFragment() {
        PersonParamsDialogFragment personParamsDialogFragment = new PersonParamsDialogFragment();
        personParamsDialogFragment.setSharedPreferences(sharedPreferences);
        personParamsDialogFragment.show(getActivity().getSupportFragmentManager(), "dialog");
    }

}
