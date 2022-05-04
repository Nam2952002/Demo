package com.example.demo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.R;
import com.example.demo.model.Weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Weather> listWeather;

    public WeatherAdapter(Activity activity, List<Weather> listWeather){
        this.activity = activity;
        this.listWeather = listWeather;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.item_weather, parent, false);
        WeatherHoler holer = new WeatherHoler(itemView);
        return holer;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WeatherHoler wh = (WeatherHoler) holder;
        Weather weather = listWeather.get(position);
        wh.tvTime.setText(convertTime(weather.getDateTime()));
        wh.tvTem.setText(weather.getTemperature().getValue()+"");
        String url = "";
        if (weather.getWeatherIcon() <10){
            url = "http://developer.accuweather.com/sites/default/files/0" + weather.getWeatherIcon()+ "-s.png";
        } else
        {
            url = "http://developer.accuweather.com/sites/default/files/" + weather.getWeatherIcon()+ "-s.png";
        }
        Glide.with(activity).load(url).into(wh.ivIcon);
    }

    @Override
    public int getItemCount() {
        return listWeather.size();
    }

    public static class WeatherHoler extends RecyclerView.ViewHolder {
        TextView tvTime, tvTem;
        ImageView ivIcon;
        public WeatherHoler(@NonNull View itemView) {
            super(itemView);
            tvTem = itemView.findViewById(R.id.tvTem);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }

    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }

}
