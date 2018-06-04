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
import android.widget.Toast;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.entity.Ration;
import com.fitnesshelper.service.impl.DishServiceImpl;
import com.fitnesshelper.service.impl.RationServiceImpl;
import com.fitnesshelper.view.adapter.EditRationAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RationFragment extends Fragment {
    RationServiceImpl rationService;
    Ration ration;
    EditRationAdapter adapter;
    List<AbstractDish> listOfDish;

    public RationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rationService = new RationServiceImpl(getContext());

        View view = inflater.inflate(R.layout.fragment_ration_edit, container, false);
        ListView editRations = (ListView) view.findViewById(R.id.rationDishes);

        Bundle bundle = getArguments();
        if (bundle != null) {
            boolean create = bundle.getBoolean("create");
            ImageButton addDishToRationBut = view.findViewById(R.id.addDishToRationBut);
            ImageButton doneAddRationButton = view.findViewById(R.id.doneAddRationButton);
            final EditText nameEditRation = (EditText) view.findViewById(R.id.rationEditName);

            if (create) {
                AbstractDish abstractDish = new DishServiceImpl(getContext()).getAnyDish();
                ArrayList<AbstractDish> abstractDishes = new ArrayList<>();
                abstractDishes.add(abstractDish);
                TextView viewById = view.findViewById(R.id.rationFragmentTitle);
                viewById.setText("Create Ration");
                ration = new Ration(0, "new ration", new Date());
                ration.setListOfDish(abstractDishes);

                doneAddRationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (nameEditRation.getText().toString().isEmpty()) {
                            Toast.makeText(v.getContext(), "fill the name",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            ration.setName(nameEditRation.getText().toString());
                            ration.setListOfDish(listOfDish);
                            rationService.createRation(ration);

                            getFragmentManager().beginTransaction().remove(RationFragment.this).commit();
                            getActivity().recreate();
                        }
                    }
                });

            } else {
                long id = bundle.getLong("id");

                TextView viewById = view.findViewById(R.id.rationFragmentTitle);
                viewById.setText("Edit Ration");
                ration = rationService.getRationById(id);
                nameEditRation.setText(ration.getName());

                doneAddRationButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (nameEditRation.getText().toString().isEmpty()) {
                            Toast.makeText(v.getContext(), "fill the name",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            ration.setName(nameEditRation.getText().toString());
                            ration.setListOfDish(listOfDish);
                            rationService.updateRation(ration);

                            getFragmentManager().beginTransaction().remove(RationFragment.this).commit();
                            getActivity().recreate();
                        }
                    }
                });

            }
            listOfDish = ration.getListOfDish();
            adapter = new EditRationAdapter(listOfDish, getActivity());

            addDishToRationBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDialog();
                }
            });

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

    private void openDialog() {
        RationAddDishDialogFragment rationAddDishDialogFragment = new RationAddDishDialogFragment();
        rationAddDishDialogFragment.setDishService(new DishServiceImpl(getContext()));
        rationAddDishDialogFragment.setAdapterAndArraylist(listOfDish, adapter);
        rationAddDishDialogFragment.show(getFragmentManager(), "dialog");
    }


}
