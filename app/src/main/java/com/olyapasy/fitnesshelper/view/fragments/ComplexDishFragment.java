package com.olyapasy.fitnesshelper.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.SimpleDish;
import com.olyapasy.fitnesshelper.service.impl.DishServiceImpl;
import com.olyapasy.fitnesshelper.view.adapter.DishAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplexDishFragment extends Fragment {
    Map<AbstractDish, Float> dishArrayList = new HashMap<>();
    private Spinner typeSpinner;
    private EditText inputDishNameComplex;
    private ArrayAdapter<String> spinnerAdapterComplex;
    private DishAdapter dishAdapter;
    private ListView listView;

    public ComplexDishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (savedInstanceState)
        View rootView = inflater.inflate(R.layout.fragment_complex_dish, container, false);

        typeSpinner = (Spinner) rootView.findViewById(R.id.spinnerType2);
        inputDishNameComplex = (EditText) rootView.findViewById(R.id.dishNameEditComplex);
        String dishNameComplex = inputDishNameComplex.getText().toString();
        inputDishNameComplex.setText(dishNameComplex);

        spinnerAdapterComplex = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Types2));
        spinnerAdapterComplex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(spinnerAdapterComplex);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (String.valueOf(typeSpinner.getSelectedItem()).equals("Simple")) {
                    DishFragment dishFragment = new DishFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.output, dishFragment).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        dishArrayList.put(new SimpleDish(1, "name", 10, new Date()), 0.1f);
        dishArrayList.put(new SimpleDish(2, "NAMENAME", 101, new Date()), 0.133f);
        dishArrayList.put(new SimpleDish(3, "PUPALUPA", 100, new Date()), 0.12f);
        listView = (ListView) rootView.findViewById(R.id.dishesListView);
        dishAdapter = new DishAdapter(dishArrayList, getContext());
        ImageButton button = (ImageButton) rootView.findViewById(R.id.addSimpleDishToCompositeBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        listView.setAdapter(dishAdapter);

        return rootView;
    }

    private void openDialog() {
        ComplexDishElementDialogFragment complexDishElementDialogFragment = new ComplexDishElementDialogFragment();
        complexDishElementDialogFragment.setDishService(new DishServiceImpl(getContext()));
        complexDishElementDialogFragment.setAdapterAndArraylist(dishArrayList, dishAdapter);
        complexDishElementDialogFragment.show(getFragmentManager(), "create dialog");
    }

}
