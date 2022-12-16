package com.example.restoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restoapp.models.Meal;
import com.example.restoapp.models.Order;

import java.util.ArrayList;

public class OrderFragment extends Fragment {

    ListView listView;
    Button orderButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        listView = view.findViewById(R.id.order_list_view);
        orderButton = view.findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Order sent", Toast.LENGTH_SHORT).show();
            }
        });
        //Bundle bundle = new Bundle();
        //Order order = bundle.getParcelable("order");
        //ArrayList<Meal> orderList = order.getMeals();
        //if (orderList.size() > 0) {
        //    Custom custom = new Custom(orderList, getActivity(), R.layout.adapter_item);
        //    listView.setAdapter(custom);
        //} else {
        //    Toast.makeText(getActivity(), "No order yet", Toast.LENGTH_SHORT).show();
        //}
        return view;
    }
}

