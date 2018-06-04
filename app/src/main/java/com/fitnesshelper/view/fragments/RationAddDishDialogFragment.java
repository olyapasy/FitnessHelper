package com.fitnesshelper.view.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.service.impl.DishServiceImpl;
import com.fitnesshelper.view.adapter.EditRationAdapter;

import java.util.List;

public class RationAddDishDialogFragment extends AppCompatDialogFragment {
    private DishServiceImpl dishService;
    private Spinner dishSpinner;

    private List<AbstractDish> abstractDishList;
    private EditRationAdapter editRationAdapter;
    public AbstractDish dish;
    private List<AbstractDish> rationDishes;

    public void setDishService(DishServiceImpl dishService) {
        this.dishService = dishService;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final List<AbstractDish> allSimpleDish = dishService.getAllDish();
        final List<String> dishNames = dishService.getAllDishNames(allSimpleDish);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_rationdish_dialog, null);

        builder.setView(view)
                .setTitle("Add")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = dishSpinner.getSelectedItem().toString();

                for (AbstractDish sDish : allSimpleDish) {
                    if (sDish.getName().equals(name)) {
                        dish = sDish;
                    }
                }

                rationDishes.add(dish);
                editRationAdapter.notifyDataSetChanged();
            }
        });

        dishSpinner = (Spinner) view.findViewById(R.id.rationdishDialogSpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_expandable_list_item_1, dishNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dishSpinner.setAdapter(spinnerAdapter);

        return builder.create();
    }

    public void setAdapterAndArraylist(List<AbstractDish> listOfDish, EditRationAdapter dishAdapter) {
        this.rationDishes = listOfDish;
        this.editRationAdapter = dishAdapter;
    }
}
