package com.midtermmad3125.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.midtermmad3125.Modals.List;
import com.midtermmad3125.Modals.Weather;
import com.midtermmad3125.R;
import com.midtermmad3125.ui.WeatherDetailsActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {
    private Context mContext;
    private  ArrayList<List> weatherList = new ArrayList<>();
    public WeatherListAdapter(Context context, ArrayList<List> weatherDataList){
        this.mContext = context;
        this.weatherList.addAll(weatherDataList);

    }
    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_weather_row, viewGroup, false);
        WeatherViewHolder vh = new WeatherViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {
        weatherViewHolder.tvDateTime.setText(getDateFromTimeStamp(weatherList.get(i).getDt()));
        weatherViewHolder.tvMin.setText(String.valueOf(weatherList.get(i).getTemp().getMin()+ (char) 0x00B0));
        weatherViewHolder.tvMax.setText(String.valueOf(weatherList.get(i).getTemp().getMax())+(char) 0x00B0);
        weatherViewHolder.tvWeather.setText(weatherList.get(i).getWeather().get(0).getMain());


    }

    public static String getDateFromTimeStamp(long time)
    {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000L);
        String date = DateFormat.format("EEEE", cal).toString();
        date += "\n" + DateFormat.format("dd MMM yyyy", cal).toString();
        date += "\n" + DateFormat.format("hh:mm a", cal).toString();
        return date;
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDateTime,tvMin,tvMax,tvWeather;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvMin = itemView.findViewById(R.id.tvMin);
            tvMax = itemView.findViewById(R.id.tvMax);
            tvWeather = itemView.findViewById(R.id.tvWeather);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =  new Intent(mContext, WeatherDetailsActivity.class);
                    List desc =weatherList.get(getAdapterPosition());
                    i.putExtra("details",new Gson().toJson(desc));
                    mContext.startActivity(i);
                }
            });
        }

    }
}
