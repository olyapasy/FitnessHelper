package com.olyapasy.fitnesshelper.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.Ration;
import com.olyapasy.fitnesshelper.service.impl.DishServiceImpl;

import java.util.List;

public class EditRationAdapter extends BaseAdapter {
    private List<AbstractDish> dishes;
    private LayoutInflater inflater;

    public EditRationAdapter(Ration ration,Context context) {
        dishes = ration.getListOfDish();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dishes.size();
    }

    @Override
    public Object getItem(int position) {
        return dishes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.edit_ration_list_item, parent, false);
        }

        AbstractDish dish = (AbstractDish) getItem(position);
        ((TextView) view.findViewById(R.id.editRationDishName)).setText(dish.getName());
        ((TextView) view.findViewById(R.id.amountOfKcalEditRation)).setText(String.valueOf(dish.getCalories()));
        ((TextView) view.findViewById(R.id.amountOfKgEditlRation)).setText(String.valueOf("Kg here"));

        return view;
    }
}
