package com.fitnesshelper.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.AbstractDish;
import com.fitnesshelper.entity.Ration;

import java.util.List;

public class EditRationAdapter extends BaseAdapter {
    private List<AbstractDish> dishes;
    private LayoutInflater inflater;

    public EditRationAdapter(List<AbstractDish> dishes, Context context) {
        this.dishes = dishes;
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
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.edit_ration_list_item, parent, false);
        }

        final AbstractDish dish = (AbstractDish) getItem(position);
        ((TextView) view.findViewById(R.id.editRationDishName)).setText(dish.getName());
        ((TextView) view.findViewById(R.id.amountOfKcalEditRation)).setText(String.valueOf(dish.getCalories()) + " calories");
        ((ImageButton) view.findViewById(R.id.deleteEditRationItem)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCount() > 1) {
                    dishes.remove(dish);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(v.getContext(), "It should be at least one dish in ration",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void addDish(AbstractDish abstractDish) {
        dishes.add(abstractDish);
    }
}
