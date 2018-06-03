package com.olyapasy.fitnesshelper.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.olyapasy.fitnesshelper.R;
import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.SimpleDish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DishAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    Map<AbstractDish, Float> dishes;
    List<AbstractDish> keys;
    Context context;
    LayoutInflater inflater;


    public DishAdapter(Map<AbstractDish, Float> dishes, Context context) {
        this.dishes = dishes;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return dishes.keySet().size();
    }

    @Override
    public Object getItem(int position) {
        return keys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;


        if (view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        keys = new ArrayList<>(dishes.keySet());
        final SimpleDish simpleDish = (SimpleDish) getItem(position);
        ((TextView) view.findViewById(R.id.dishName)).setText(simpleDish.getName());
        ((TextView) view.findViewById(R.id.amountKg))
                .setText(String.valueOf(dishes.get(simpleDish)));

        view.findViewById(R.id.deleteDishButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishes.remove(simpleDish);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
