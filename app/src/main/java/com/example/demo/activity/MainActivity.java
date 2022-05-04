package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.adapter.WeatherAdapter;
import com.example.demo.api.ApiManager;
import com.example.demo.model.Weather;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvDemo;
    TextView tvTemp, tvStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvStatus = findViewById(R.id.tvStatus);
        tvTemp = findViewById(R.id.tvTemp);
        rvDemo = findViewById(R.id.rvDemo);

        getHour();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvDemo = findViewById(R.id.rvDemo);
        rvDemo.setLayoutManager(layoutManager);
    }

    private void getHour(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.getHour().enqueue(new Callback<List<Weather>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                if(response.body() == null)
                    return;
                List<Weather> listWeather = response.body();
                WeatherAdapter adapter = new WeatherAdapter(MainActivity.this, listWeather);
                rvDemo.setAdapter(adapter);
                rvDemo.setHasFixedSize(true);

                Weather weather = listWeather.get(0);
                tvTemp.setText(weather.getTemperature().getValue().intValue() + "°");
                tvStatus.setText(weather.getIconPhrase());
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}