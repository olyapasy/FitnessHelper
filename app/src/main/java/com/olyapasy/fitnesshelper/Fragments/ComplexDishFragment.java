package com.olyapasy.fitnesshelper.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import com.olyapasy.fitnesshelper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplexDishFragment extends Fragment {
    ComplexDishFragment complexDishFragment;


    public ComplexDishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_complex_dish, container, false);
        final Spinner typeSpinner = (Spinner) rootView.findViewById(R.id.spinnerType2);

        EditText inputDishNameComplex = (EditText)rootView.findViewById(R.id.dishNameEditComplex);
        EditText inputAmountOfKg = (EditText) rootView.findViewById(R.id.amountKg);
        String dishNameComplex = inputDishNameComplex.getText().toString();
        String amountKg = inputAmountOfKg.getText().toString();
        inputDishNameComplex.setText(dishNameComplex);
        inputAmountOfKg.setText(amountKg);

        ArrayAdapter<String> spinnerAdapterComplex = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.Types2));
        spinnerAdapterComplex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(spinnerAdapterComplex);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(String.valueOf(typeSpinner.getSelectedItem()).equals("Simple")) {
                    DishFragment dishFragment = new DishFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.output, dishFragment).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        return rootView;
    }

}
