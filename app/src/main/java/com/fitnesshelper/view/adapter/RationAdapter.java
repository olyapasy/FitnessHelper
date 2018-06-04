package com.fitnesshelper.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fitnesshelper.R;
import com.fitnesshelper.entity.Ration;
import com.fitnesshelper.service.DishService;
import com.fitnesshelper.service.impl.DishServiceImpl;
import com.fitnesshelper.service.impl.RationServiceImpl;

import java.util.Date;
import java.util.List;

public class RationAdapter extends BaseAdapter {
    private final TextView totalAmountTextView;
    private List<Ration> rations;
    private LayoutInflater inflater;
    private RationServiceImpl rationService;
    private DishService dishService;

    public RationAdapter(Context context, TextView totalAmountTextView) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rationService = new RationServiceImpl(context);
        dishService = new DishServiceImpl(context);
        this.rations = rationService.getRationByDate(new Date());
        this.totalAmountTextView = totalAmountTextView;
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
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.ration_list_item, parent, false);
        }

        final Ration ration = (Ration) getItem(position);
        ((TextView) view.findViewById(R.id.rationName)).setText(ration.getName());
        ((TextView) view.findViewById(R.id.amountOfDishesRation)).setText(String.valueOf(ration.getListOfDish()
                .size()));
        ((TextView) view.findViewById(R.id.amountOfKcalRation)).setText(String.valueOf(dishService
                .getDishCalories(ration.getListOfDish())));

        view.findViewById(R.id.deleteRationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCount() > 1) {
                    rations.remove(ration);
                    notifyDataSetChanged();
                    rationService.removeRation(ration);
                    totalAmountTextView.setText(String.valueOf(getTotalAmountOfCal()));
                } else {
                    Toast.makeText(v.getContext(), "It should be at least one ration",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private long getTotalAmountOfCal() {
        long amountOfCAl = 0;

        for (Ration ration : rations) {
            amountOfCAl += dishService.getDishCalories(ration.getListOfDish());
        }

        return amountOfCAl;
    }
}
