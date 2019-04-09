package com.midtermmad3125.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.midtermmad3125.Adapters.WeatherListAdapter;
import com.midtermmad3125.Modals.List;
import com.midtermmad3125.Modals.WeatherModal;
import com.midtermmad3125.R;

import java.util.ArrayList;

public class WeatherListActivity extends AppCompatActivity {
    private Gson gson;
    private WeatherModal weatherModal;
    private ArrayList<List>  weatherDataList;
    private WeatherListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        mRecyclerView = findViewById(R.id.rvWeatherList);
        weatherDataList =  new ArrayList<>();
        fetchList();
        initRecyclerView();
    }

    private void fetchList() {
        String jsonString = getIntent().getExtras().getString("weatherDetails");
        gson = new Gson();
        weatherModal = gson.fromJson(jsonString, WeatherModal.class);
        weatherDataList.addAll(weatherModal.getList());
    }

    private void initRecyclerView() {
        mAdapter = new WeatherListAdapter(this, weatherDataList);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
