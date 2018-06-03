package com.olyapasy.fitnesshelper.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.entity.SimpleDish;
import com.olyapasy.fitnesshelper.service.impl.DishServiceImpl;
import com.olyapasy.fitnesshelper.view.activity.AllDishActivity;
import com.olyapasy.fitnesshelper.view.activity.RationActivity;

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
        Bundle extras = getArguments();

        if (extras != null) {
            ImageButton addDish = (ImageButton) view.findViewById(R.id.doneAddDishButton);
            final EditText inputDishName = (EditText) view.findViewById(R.id.dishNameEdit);
            final EditText inputAmountOfKcal = (EditText) view.findViewById(R.id.kcalAmountEdit);
            final Spinner typeSpinner = (Spinner) view.findViewById(R.id.spinnerType);

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Types));
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeSpinner.setAdapter(spinnerAdapter);

            final int activity = extras.getInt("activity");
            boolean create = extras.getBoolean("create");

            typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (String.valueOf(typeSpinner.getSelectedItem()).equals("Complex")) {
                        ComplexDishFragment complexDishFragment = new ComplexDishFragment();
                        Bundle extras = new Bundle();
                        extras.putBoolean("create", true);
                        extras.putInt("activity", activity);
                        complexDishFragment.setArguments(extras);
                        getFragmentManager().beginTransaction().replace(R.id.output,
                                complexDishFragment).commit();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            if (create) {
                addDish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dishName = inputDishName.getText().toString();
                        String amountKcal = inputAmountOfKcal.getText().toString();
                        dishService.create(new SimpleDish(0, dishName,
                                Integer.parseInt(amountKcal), new Date()));

                        getActivity().finish();
//                        startActivity(formIntent(activity));
                    }


                });
            } else {
                long id = extras.getLong("id");
                final SimpleDish simpleDish = (SimpleDish) dishService.getDishById(id);

                inputDishName.setText(simpleDish.getName());
                inputAmountOfKcal.setText(String.valueOf(simpleDish.getCalories()));
                addDish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        simpleDish.setName(inputDishName.getText().toString());
                        simpleDish.setCalories(Integer.parseInt(inputAmountOfKcal.getText()
                                .toString()));
                        dishService.update(simpleDish);

                        getActivity().finish();
                        startActivity(formIntent(activity));
                    }


                });
            }
            ImageButton addCompositeDish = view.findViewById(R.id.backAddDishButton);
            addCompositeDish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                    startActivity(formIntent(activity));
                }
            });

        }
    }

    private Intent formIntent(int activity) {
        Class _class = null;
        if (activity == 1) {
            _class = AllDishActivity.class;
        }
        if (activity == 2) {
            _class = RationActivity.class;
        }

        Intent intent = new Intent(getActivity(), _class);
        return intent;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    public void setMenuFragment(ComplexDishFragment complexDishFragment) {
//        this.complexDishFragment = complexDishFragment;
//    }
}
