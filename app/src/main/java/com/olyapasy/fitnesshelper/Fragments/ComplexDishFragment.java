package com.olyapasy.fitnesshelper.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.olyapasy.fitnesshelper.DishAdapter;
import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.SimpleDish;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplexDishFragment extends Fragment {
    ComplexDishFragment complexDishFragment;
    ArrayList<AbstractDish> dishArrayList = new ArrayList<>();
    ListView listView;
    ArrayList<String> allDishes = new ArrayList<String>();


    public ComplexDishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_complex_dish, container, false);
        final Spinner typeSpinner = (Spinner) rootView.findViewById(R.id.spinnerType2);

        EditText inputDishNameComplex = (EditText)rootView.findViewById(R.id.dishNameEditComplex);
        String dishNameComplex = inputDishNameComplex.getText().toString();
        inputDishNameComplex.setText(dishNameComplex);

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

        fillData();
        ListView listView = (ListView) rootView.findViewById(R.id.dishesListView);
        final DishAdapter dishAdapter = new DishAdapter(dishArrayList,getContext());
        listView.setAdapter(dishAdapter);
       // listView = (ListView) rootView.findViewById(R.id.componentSpinner);

//        ArrayList<String> mData = new ArrayList<>();
//        mData.add("Test1");
//        mData.add("Test2");
//        mData.add("Test3");
//        mData.add("Test4");
//        ArrayList<String> mSpinner = new ArrayList<>();
//        mData.add("1");
//        mData.add("3");
//        mData.add("2");



        return rootView;
    }


    public void fillData(){
        dishArrayList.add(new SimpleDish(1,"carrot",0,new Date()));
        dishArrayList.add(new SimpleDish(2,"tomato",0,new Date()));
        dishArrayList.add(new SimpleDish(3,"burito",0,new Date()));
        dishArrayList.add(new SimpleDish(4,"vitalito",0,new Date()));
    }

}
