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
import com.olyapasy.fitnesshelper.service.impl.DishServiceImpl;

import java.util.List;

public class AllDishAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private LayoutInflater inflater;
    private DishServiceImpl dishService;
    private List<AbstractDish> dishArrayList;

    public AllDishAdapter(Context context) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dishService = new DishServiceImpl(context);
        dishArrayList = dishService.getAllDish();
    }

    @Override
    public int getCount() {
        return dishArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dishArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.all_dishes_list_item, parent, false);
        }

        final AbstractDish dish = (AbstractDish) getItem(position);
        ((TextView) view.findViewById(R.id.dishNameAllDish)).setText(dish.getName());
        ((TextView) view.findViewById(R.id.kcalAmountAllDish))
                .setText(String.valueOf(dish.getCalories()));

        view.findViewById(R.id.deleteDishButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishArrayList.remove(dish);
                notifyDataSetChanged();
                dishService.remove(dish);
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
