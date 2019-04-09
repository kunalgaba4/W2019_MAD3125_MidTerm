package com.midtermmad3125.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.midtermmad3125.Modals.List;
import com.midtermmad3125.Modals.WeatherModal;
import com.midtermmad3125.R;

import java.util.ArrayList;

public class WeatherDetailsActivity extends AppCompatActivity {


    private List description;
    private Gson gson;
    private TextView degree, temp, pressure, humidity,speed, rain, tvDescription,tvClouds ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waether_details);
        String jsonString = getIntent().getExtras().getString("details");
        gson = new Gson();
        description = gson.fromJson(jsonString, List.class);

        initView();
    }

    private void initView() {
        degree = findViewById(R.id.tvDegree);
        temp = findViewById(R.id.tvTemp);
        pressure = findViewById(R.id.tvPressure);
        humidity = findViewById(R.id.tvHumidity);
        speed = findViewById(R.id.tvSpeed);
        rain = findViewById(R.id.tvRain);
        tvDescription = findViewById(R.id.tvDescription);
        tvClouds = findViewById(R.id.tvClouds);
        tvDescription.setText(description.getWeather().get(0).getDescription());

        degree.setText(String.valueOf("Degree       " +description.getDeg()+(char) 0x00B0));
        temp.setText(String.valueOf("Clouds         " +description.getClouds()));
        pressure.setText(String.valueOf("Pressure   " +description.getPressure() + "pha"));
        humidity.setText(String.valueOf("Humidity   " +description.getHumidity()+ " %"));
        speed.setText(String.valueOf("Speed        " +description.getSpeed()));
        rain.setText(String.valueOf("Rain          " +description.getRain()));
        tvClouds.setText(String.valueOf("Clouds     "+description.getClouds()));


    }
}
