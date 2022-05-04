package com.example.demo.api;

import com.example.demo.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {

    String SERVER_URL = "https://dataservice.accuweather.com";
//    String SERVER_URL = "https://dataservice.accuweather.com";

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=ZmecrJlyFY85CLokBp3VhlRisXQk8i8l&language=vi-vn&metric=true")
    Call<List<Weather>> getHour();


    @GET("forecasts/v1/daily/5day/353412?apikey=ZmecrJlyFY85CLokBp3VhlRisXQk8i8l&language=vi-vn&metric=true")
   Call<List<Weather>> getDay();
}
