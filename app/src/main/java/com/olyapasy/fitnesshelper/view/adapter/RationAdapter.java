package com.olyapasy.fitnesshelper.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.data.dao.RationDAO;
import com.olyapasy.fitnesshelper.data.dao.impl.RationDAOImpl;
import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.Ration;
import com.olyapasy.fitnesshelper.service.impl.DishServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RationAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private List<Ration> rations;
    private Context context;
    private LayoutInflater inflater;
    private RationDAO rationDAO;

    public RationAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rationDAO = new RationDAOImpl(context);
        this.rations = rationDAO.getByDate(new Date());
    }

    @Override
    public int getCount() {
        return rations.size();
    }

    @Override
    public Object getItem(int position) {
        return rations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.ration_list_item, parent, false);
        }

        Ration ration = (Ration) getItem(position);

        ((TextView) view.findViewById(R.id.rationName)).setText(ration.getName());
        ((TextView) view.findViewById(R.id.amountOfDishesRation)).setText(String.valueOf(ration.getListOfDish()
                .size()));
        ((TextView) view.findViewById(R.id.amountOfKcalRation)).setText(String.valueOf(rationDAO
                .getCaloriesById(ration.getId())));

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}
