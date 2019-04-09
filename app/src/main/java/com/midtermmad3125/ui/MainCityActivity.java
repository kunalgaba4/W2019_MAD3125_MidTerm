package com.midtermmad3125.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.midtermmad3125.Modals.WeatherModal;
import com.midtermmad3125.R;
import com.midtermmad3125.utils.ReadJSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class MainCityActivity extends AppCompatActivity {


    private TextView tvCityName,tvLong,tvLat,tvPopulation;
    private Button btnDisplayWeather;
    private String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadDatafromJson();

        btnDisplayWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MainCityActivity.this, WeatherListActivity.class);
                intent.putExtra("weatherDetails",jsonString);
                startActivity(intent);
            }
        });
    }

    private void loadDatafromJson() {

        jsonString = ReadJSONUtils.loadJSONFromAsset(this,"moscow_weather.json");

        Gson gson = new Gson();
        WeatherModal weatherModal = gson.fromJson(jsonString,WeatherModal.class);

        if (weatherModal!=null){
            tvCityName.setText(weatherModal.getCity().getName());
            tvLat.setText(String.valueOf(weatherModal.getCity().getCoord().getLat()));
            tvLong.setText(String.valueOf(weatherModal.getCity().getCoord().getLon()));
            DecimalFormat formatter = new DecimalFormat("#,###,###");
            String yourFormattedString = formatter.format(weatherModal.getCity().getPopulation());
            tvPopulation.setText(yourFormattedString);

        }

    }

    private void initView() {
        tvCityName = findViewById(R.id.tvCityName);
        tvLong = findViewById(R.id.tvLong);
        tvLat = findViewById(R.id.tvLat);
        tvPopulation = findViewById(R.id.tvPopulation);
        btnDisplayWeather= findViewById(R.id.btnDisplayWeather);
    }
}
