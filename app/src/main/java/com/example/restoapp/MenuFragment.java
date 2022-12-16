package com.example.restoapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restoapp.models.Meal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuFragment extends Fragment {

    private ArrayList<Meal>mealsArrayList;
    private MyAPI myAPI;
    private ListView listView;
    private String BaseUrl = "https://my-json-server.typicode.com/IchemMous/test/";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        listView = view.findViewById(R.id.list_view_meal);
        mealsArrayList = new ArrayList<>();
        DisplayRetrofitData();
        return view;
    }

    private void DisplayRetrofitData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        myAPI = retrofit.create(MyAPI.class);
        Call<ArrayList<Meal>> arrayListCall = myAPI.callMeals();
        arrayListCall.enqueue(new Callback<ArrayList<Meal>>() {
            @Override
            public void onResponse(Call<ArrayList<Meal>> call, Response<ArrayList<Meal>> response) {
                mealsArrayList = response.body();
                //for (int i = 0; i < mealsArrayList.size(); i++);
                Custom custom = new Custom(mealsArrayList, getActivity(), R.layout.adapter_item);
                listView.setAdapter(custom);
            }

            @Override
            public void onFailure(Call<ArrayList<Meal>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
