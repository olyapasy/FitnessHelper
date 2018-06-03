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
import com.olyapasy.fitnesshelper.entity.CompositeDish;
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
    Map<SimpleDish, Float> simpleDishHashMap = new HashMap<>();
    private Spinner typeSpinner;
    private EditText inputDishNameComplex;
    private ArrayAdapter<String> spinnerAdapterComplex;
    private DishAdapter dishAdapter;
    private ListView listView;
    private DishServiceImpl dishService;

    public ComplexDishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dishService = new DishServiceImpl(getContext());
        View rootView = inflater.inflate(R.layout.fragment_complex_dish, container, false);
        boolean createAction = true;
        typeSpinner = (Spinner) rootView.findViewById(R.id.spinnerType2);
        inputDishNameComplex = (EditText) rootView.findViewById(R.id.dishNameEditComplex);
        String dishNameComplex = inputDishNameComplex.getText().toString();

        if (createAction) {
            inputDishNameComplex.setText(dishNameComplex);
            //TODO add name and cal for fragment
            final String name = dishNameComplex;
            final int calories = 0;
            simpleDishHashMap.put(new SimpleDish(0, name, calories, new Date()), 0.0f);

            ImageButton addCompositeDish = rootView.findViewById(R.id.doneAddDishButton3);
            addCompositeDish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dishService.create(new CompositeDish(0, name, calories, new Date(), simpleDishHashMap));
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
            //TODO add id and new name from another fragment
            inputDishNameComplex.setText(dishNameComplex);
            long id = 0;
            final CompositeDish compositeDish = (CompositeDish) dishService.getDishById(id);
            simpleDishHashMap = compositeDish.getSimpleDishMap();
            final String name = dishNameComplex;

            ImageButton addCompositeDish = rootView.findViewById(R.id.doneAddDishButton3);
            addCompositeDish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    compositeDish.setSimpleDishMap(simpleDishHashMap);
                    compositeDish.setName(name);
                    dishService.update(compositeDish);
                }
            });

        }

        spinnerAdapterComplex = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Types2));
        spinnerAdapterComplex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(spinnerAdapterComplex);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (String.valueOf(typeSpinner.getSelectedItem()).equals("Simple")) {
                    SimpleDishFragment simpleDishFragment = new SimpleDishFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.output, simpleDishFragment).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        listView = (ListView) rootView.findViewById(R.id.dishesListView);
        dishAdapter = new DishAdapter(simpleDishHashMap, getContext(), dishService);
        listView.setAdapter(dishAdapter);

        return rootView;
    }

    private void openDialog() {
        ComplexDishElementDialogFragment complexDishElementDialogFragment = new ComplexDishElementDialogFragment();
        complexDishElementDialogFragment.setDishService(dishService);
        complexDishElementDialogFragment.setAdapterAndArraylist(simpleDishHashMap, dishAdapter);
        complexDishElementDialogFragment.show(getFragmentManager(), "dialog");
    }

}
