package com.fitnesshelper.view.fragments;


import android.content.Intent;
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
import android.widget.Toast;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.CompositeDish;
import com.fitnesshelper.entity.SimpleDish;
import com.fitnesshelper.service.impl.DishServiceImpl;
import com.fitnesshelper.view.activity.AllDishActivity;
import com.fitnesshelper.view.activity.RationActivity;
import com.fitnesshelper.view.adapter.DishAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplexDishFragment extends Fragment {
    Map<SimpleDish, Float> simpleDishHashMap = new HashMap<>();
    private Spinner typeSpinner;
    private DishAdapter dishAdapter;
    private DishServiceImpl dishService;
    private View rootView;

    public ComplexDishFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dishService = new DishServiceImpl(getActivity());
        rootView = inflater.inflate(R.layout.fragment_complex_dish, container, false);
        Bundle extras = getArguments();
        typeSpinner = (Spinner) rootView.findViewById(R.id.spinnerType2);

        if (extras != null) {
            final EditText inputDishName = (EditText) rootView.findViewById(R.id.dishNameEditComplex);
            boolean createAction = extras.getBoolean("create");
            final int activity = extras.getInt("activity");

            if (createAction) {
                simpleDishHashMap.put((SimpleDish) dishService.getAnyDish(), 0.5f);

                ImageButton addCompositeDish = rootView.findViewById(R.id.doneAddDishButton3);
                addCompositeDish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dishAdapter.notifyDataSetChanged();

                        String dishNameComplex = inputDishName.getText().toString();


                        if (dishNameComplex.isEmpty()) {
                            Toast.makeText(getActivity(), "Dish should have the name!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            dishService.create(new CompositeDish(0, dishNameComplex,
                                    dishService.getCompositeDishCalories(simpleDishHashMap),
                                    new Date(), simpleDishHashMap));
                            getActivity().finish();
                            startActivity(formIntent(activity));
                        }
                    }
                });

                ImageButton button = rootView.findViewById(R.id.addSimpleDishToCompositeBut);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialog();
                    }
                });
            } else {
                long id = extras.getLong("id");
                final CompositeDish compositeDish = (CompositeDish) dishService.getDishById(id);
                inputDishName.setText(compositeDish.getName());
                simpleDishHashMap = compositeDish.getSimpleDishMap();


                ImageButton addCompositeDish = rootView.findViewById(R.id.doneAddDishButton3);
                addCompositeDish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dishNameComplex = inputDishName.getText().toString();
                        if (dishNameComplex.isEmpty()) {
                            Toast.makeText(getActivity(), "Dish should have the name!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            compositeDish.setSimpleDishMap(simpleDishHashMap);
                            compositeDish.setName(dishNameComplex);
                            compositeDish.setCalories(dishService
                                    .getCompositeDishCalories(simpleDishHashMap));
                            dishService.update(compositeDish);

                            getActivity().finish();
                            startActivity(formIntent(activity));
                        }
                    }
                });
            }

            ImageButton addCompositeDish = rootView.findViewById(R.id.backAddDishButton);
            addCompositeDish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                    startActivity(formIntent(activity));
                }
            });

            ArrayAdapter<String> spinnerAdapterComplex = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Types2));
            spinnerAdapterComplex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeSpinner.setAdapter(spinnerAdapterComplex);
            typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (String.valueOf(typeSpinner.getSelectedItem()).equals("Simple")) {
                        SimpleDishFragment simpleDishFragment = new SimpleDishFragment();
                        Bundle extras = new Bundle();
                        extras.putBoolean("create", true);
                        extras.putInt("activity", activity);
                        simpleDishFragment.setArguments(extras);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.output, simpleDishFragment).commit();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            ListView listView = (ListView) rootView.findViewById(R.id.dishesListView);
            dishAdapter = new DishAdapter(simpleDishHashMap, getContext(), dishService);
            listView.setAdapter(dishAdapter);
        }

        return rootView;
    }

    private void openDialog() {
        ComplexDishElementDialogFragment complexDishElementDialogFragment = new ComplexDishElementDialogFragment();
        complexDishElementDialogFragment.setDishService(dishService);
        complexDishElementDialogFragment.setAdapterAndArraylist(simpleDishHashMap, dishAdapter);
        complexDishElementDialogFragment.show(getFragmentManager(), "dialog");
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
}
