package com.olyapasy.fitnesshelper.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.data.dao.impl.RationDAOImpl;
import com.olyapasy.fitnesshelper.view.adapter.EditRationAdapter;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RationEditFragment extends Fragment {
    RationDAOImpl rationDAO;

    public RationEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rationDAO = new RationDAOImpl(getContext());

        View view = inflater.inflate(R.layout.fragment_ration_edit, container, false);
        long id = 0;

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getLong("id", 0);
        }

        final EditText nameEditRation = (EditText) view.findViewById(R.id.rationEditName);
        ListView editRations = (ListView) view.findViewById(R.id.rationDishes);
        final EditRationAdapter adapter = new EditRationAdapter(rationDAO.getById(id),getActivity());
        editRations.setAdapter(adapter);
        return view;
    }

}
