package com.olyapasy.fitnesshelper.view.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.entity.SimpleDish;
import com.olyapasy.fitnesshelper.service.impl.DishServiceImpl;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleDishFragment extends Fragment {
    ComplexDishFragment complexDishFragment;
    private EditText name;
    private EditText kcal;
    DishServiceImpl dishService;

    public SimpleDishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dishService = new DishServiceImpl(getContext());
        View rootView = inflater.inflate(R.layout.fragment_dish, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageButton addDish = (ImageButton) view.findViewById(R.id.doneAddDishButton);
        final EditText inputDishName = (EditText) view.findViewById(R.id.dishNameEdit);
        final EditText inputAmountOfKcal = (EditText) view.findViewById(R.id.kcalAmountEdit);


        final Spinner typeSpinner = (Spinner) view.findViewById(R.id.spinnerType);

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

        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dishName = inputDishName.getText().toString();
                String amountKcal = inputAmountOfKcal.getText().toString();
                inputDishName.setText(dishName);
                inputAmountOfKcal.setText(amountKcal);
                dishService.create(new SimpleDish(0, dishName, Long.parseLong(amountKcal), new Date()));

//                Bundle bundle = new Bundle();
//                intent.putExtra("name",dishName);
//                intent.putExtra("kcal", amountKcal);
//
//                startActivity(intent);


//                Intent intent = new Intent(getContext(),DishMealActivity.class);
//                intent.putExtra("name",dishName);
//                intent.putExtra("kcal",amountKcal);
//                startActivity(intent);

            }


        });


    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    public void setMenuFragment(ComplexDishFragment complexDishFragment) {
//        this.complexDishFragment = complexDishFragment;
//    }
}
