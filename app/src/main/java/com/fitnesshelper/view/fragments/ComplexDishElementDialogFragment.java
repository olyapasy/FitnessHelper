package com.fitnesshelper.view.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.fitnesshelper.entity.SimpleDish;
import com.fitnesshelper.service.impl.DishServiceImpl;
import com.fitnesshelper.view.adapter.DishAdapter;
import com.fitnesshelper.R;

import java.util.List;
import java.util.Map;

public class ComplexDishElementDialogFragment extends AppCompatDialogFragment {
    private Spinner dishSpinner;
    private EditText amountOfKg;
    private DishServiceImpl dishService;
    private SimpleDish simpleDish;
    private Map<SimpleDish, Float> fromAdapter;
    private DishAdapter dishAdapter;

    public void setDishService(DishServiceImpl dishService) {
        this.dishService = dishService;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final List<SimpleDish> allSimpleDish = dishService.getAllSimpleDish();
        List<String> dishNames = dishService.getAllDishNames(allSimpleDish);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_simple_dish_dialog, null);

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
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
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
