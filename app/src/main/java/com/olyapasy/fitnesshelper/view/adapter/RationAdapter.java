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
import com.olyapasy.fitnesshelper.entity.Ration;

import java.util.ArrayList;
import java.util.List;

public class RationAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private List<Ration> rations;
    private Context context;
    private LayoutInflater inflater;

    public RationAdapter(List<Ration> rations, Context context) {
        this.rations = rations;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        ((TextView)view.findViewById(R.id.amountOfDishesRation)).setText("диши");
        ((TextView) view.findViewById(R.id.amountOfKcalRation)).setText("каллории");

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}
