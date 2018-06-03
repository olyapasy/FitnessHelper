package com.olyapasy.fitnesshelper.view.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.entity.SimpleDish;
import com.olyapasy.fitnesshelper.service.impl.DishServiceImpl;
import com.olyapasy.fitnesshelper.view.adapter.DishAdapter;

import java.util.List;
import java.util.Map;

public class ComplexDishElementDialogFragment extends AppCompatDialogFragment {
    private Spinner dishSpinner;
    private EditText amountOfKg;
    private DishServiceImpl dishService;
    private List<String> dishNames;
    private ArrayAdapter<String> spinnerAdapter;
    private SimpleDish simpleDish;
    private Map fromAdapter;
    private DishAdapter dishAdapter;

    public void setDishService(DishServiceImpl dishService) {
        this.dishService = dishService;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final List<SimpleDish> allSimpleDish = dishService.getAllSimpleDish();
        dishNames = dishService.getAllDishNames(allSimpleDish);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_simple_dish_dialog,null);

        builder.setView(view)
                .setTitle("Create")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String amount = amountOfKg.getText().toString();
                String name = dishSpinner.getSelectedItem().toString();

                for (SimpleDish sDish : allSimpleDish) {
                    if (sDish.getName().equals(name)) {
                        simpleDish = sDish;
                    }
                }

                fromAdapter.put(simpleDish, Float.parseFloat(amount));
                dishAdapter.notifyDataSetChanged();
            }
        });

        amountOfKg = view.findViewById(R.id.dialogAmountOfKg);
        dishSpinner = (Spinner) view.findViewById(R.id.dialogSpinner);
        spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_expandable_list_item_1, dishNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dishSpinner.setAdapter(spinnerAdapter);

        return builder.create();
    }

    public void setAdapterAndArraylist(Map<SimpleDish, Float> dishArrayList, DishAdapter dishAdapter) {
        this.fromAdapter = dishArrayList;
        this.dishAdapter = dishAdapter;
    }

}
