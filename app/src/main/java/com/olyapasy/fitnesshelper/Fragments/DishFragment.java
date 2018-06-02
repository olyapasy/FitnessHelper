package com.olyapasy.fitnesshelper.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.olyapasy.fitnesshelper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DishFragment extends Fragment {
    ComplexDishFragment complexDishFragment;
    private EditText name;



    public DishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dish, container, false);



//        EditText inputDishName = (EditText) rootView.findViewById(R.id.dishNameEdit);
//        EditText inputAmountOfKcal = (EditText) rootView.findViewById(R.id.kcalAmountEdit);
//
//        String dishName = inputDishName.getText().toString();
//        String amountKcal = inputAmountOfKcal.getText().toString();
//        inputDishName.setText(dishName);
//        inputAmountOfKcal.setText(amountKcal);

        final Spinner typeSpinner = (Spinner) rootView.findViewById(R.id.spinnerType);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.Types));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(spinnerAdapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(String.valueOf(typeSpinner.getSelectedItem()).equals("Complex")){
                    ComplexDishFragment complexDishFragment = new ComplexDishFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    getFragmentManager().beginTransaction().replace(R.id.output,complexDishFragment).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        name = (EditText) view.findViewById(R.id.dishNameEdit);
        String dishName = name.getText().toString();
        name.setText(dishName);

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
