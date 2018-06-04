package com.fitnesshelper.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.entity.Ration;
import com.fitnesshelper.entity.SimpleDish;
import com.fitnesshelper.service.impl.RationServiceImpl;
import com.fitnesshelper.view.adapter.EditRationAdapter;

import java.util.Collections;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RationFragment extends Fragment {
    RationServiceImpl rationService;

    public RationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rationService = new RationServiceImpl(getContext());

        View view = inflater.inflate(R.layout.fragment_ration_edit, container, false);
        ListView editRations = (ListView) view.findViewById(R.id.rationDishes);
        final EditRationAdapter adapter;

        Bundle bundle = getArguments();
        if (bundle != null) {
            boolean create = bundle.getBoolean("create");
            ImageButton addDishToRationBut = view.findViewById(R.id.addDishToRationBut);
            final EditText nameEditRation = (EditText) view.findViewById(R.id.rationEditName);

            if (create) {
                TextView viewById = view.findViewById(R.id.rationFragmentTitle);
                viewById.setText("Create Ration");
                final Ration ration = new Ration(0, "new ration", new Date());
                ration.setListOfDish(Collections.<AbstractDish>singletonList(new SimpleDish(0, "Dish", 100, new Date())));
                adapter = new EditRationAdapter(ration, getActivity());

                addDishToRationBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ration.setName(nameEditRation.getText().toString());
//                        rationService.createRation(ration);
                    }
                });

            } else {
                long id = bundle.getLong("id");

                TextView viewById = view.findViewById(R.id.rationFragmentTitle);
                viewById.setText("Edit Ration");
                final Ration rationById = rationService.getRationById(id);

                adapter = new EditRationAdapter(rationById, getActivity());

                addDishToRationBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rationById.setName(nameEditRation.getText().toString());
//                        rationService.updateRation(rationById);
                    }
                });

            }

            view.findViewById(R.id.backAddRationFragmentBut).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager().beginTransaction().remove(RationFragment.this).commit();
                }
            });

            editRations.setAdapter(adapter);
        }

        return view;
    }
}
