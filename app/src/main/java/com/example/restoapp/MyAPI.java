package com.example.restoapp;

import com.example.restoapp.models.Meal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPI {
    @GET("requests")
    Call<ArrayList<Meal>> callMeals();
}
