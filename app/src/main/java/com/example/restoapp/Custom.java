package com.example.restoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restoapp.models.Meal;

import java.util.ArrayList;

public class Custom extends BaseAdapter {
    private ArrayList<Meal>mealsArrayList;
    private Context context;
    private int layout;

    public Custom(ArrayList<Meal> mealsArrayList, Context context, int layout) {
        this.mealsArrayList = mealsArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return mealsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mealsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView price, name, description;
        ImageView itemIconView;


    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = layoutInflater.inflate(layout, null);
        viewHolder.name = convertView.findViewById(R.id.item_name);
        viewHolder.price = convertView.findViewById(R.id.item_price);
        viewHolder.itemIconView = convertView.findViewById(R.id.item_icon);


        Meal meal = mealsArrayList.get(position);
        viewHolder.name.setText(meal.getName());
        viewHolder.price.setText(meal.getPrice() + " €");
        String resourceName= "item_" + meal.getImageURL() + "_icon";
        int resId = context.getResources().getIdentifier(resourceName, "drawable",context.getPackageName());
        viewHolder.itemIconView.setImageResource(resId);

        return convertView;
    }
}
