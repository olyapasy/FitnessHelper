package com.fitnesshelper.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fitnesshelper.data.dao.RationDAO;
import com.fitnesshelper.data.dao.impl.RationDAOImpl;
import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.entity.Ration;
import com.fitnesshelper.entity.SimpleDish;
import com.fitnesshelper.view.adapter.EditRationAdapter;
import com.fitnesshelper.R;

import java.util.Collections;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RationFragment extends Fragment {
    RationDAO rationDAO;

    public RationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rationDAO = new RationDAOImpl(getContext());

        View view = inflater.inflate(R.layout.fragment_ration_edit, container, false);
        ListView editRations = (ListView) view.findViewById(R.id.rationDishes);
        final EditRationAdapter adapter;

        Bundle bundle = getArguments();
        if (bundle != null) {
            boolean create = bundle.getBoolean("create");


            if (create) {
                TextView viewById = view.findViewById(R.id.rationFragmentTitle);
                viewById.setText("Create Ration");
                Ration ration = new Ration(0, "new ration", new Date());
                ration.setListOfDish(Collections.<AbstractDish>singletonList(new SimpleDish(0, "Dish", 100, new Date())));


                adapter = new EditRationAdapter(ration, getActivity());

            } else {
                long id = bundle.getLong("id");

                TextView viewById = view.findViewById(R.id.rationFragmentTitle);
                viewById.setText("Edit Ration");

                final EditText nameEditRation = (EditText) view.findViewById(R.id.rationEditName);

                adapter = new EditRationAdapter(rationDAO.getById(id), getActivity());

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
