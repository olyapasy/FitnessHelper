package com.olyapasy.fitnesshelper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.olyapasy.fitnesshelper.entity.AbstractDish;
import com.olyapasy.fitnesshelper.entity.SimpleDish;

import java.util.ArrayList;

public class DishAdapter extends BaseAdapter {
    ArrayList<AbstractDish> dishes;
    ArrayList<String> names = new ArrayList<String>();
    Context context;
    LayoutInflater inflater;


    public DishAdapter(ArrayList<AbstractDish> dishes, Context context) {
        this.dishes = dishes;
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_item,parent,false);
            holder.editText = (EditText) convertView.findViewById(R.id.editTextamountKg);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Spinner spinner = (Spinner) convertView.findViewById(R.id.componentSpinner);
        for(AbstractDish aDish : dishes){
            names.add(aDish.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1,names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        holder.editText.setText(String.valueOf(dishes.get(position).getCalories()));
        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dishes.get(position).setCalories(Long.parseLong(holder.editText.getText().toString()));


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return convertView;
    }

    private class ViewHolder{
        protected EditText editText;
    }
}
