package com.example.restoapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        int resId = context.getResources().getIdentifier("item_" + meal.getImageURL() + "_icon", "drawable",context.getPackageName());
        viewHolder.itemIconView.setImageDrawable(context.getResources().getDrawable(resId));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductFragment productFragment = new ProductFragment(meal);
                ((Home)context).getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, productFragment).commit();
            }
        });
        return convertView;
    }
}
