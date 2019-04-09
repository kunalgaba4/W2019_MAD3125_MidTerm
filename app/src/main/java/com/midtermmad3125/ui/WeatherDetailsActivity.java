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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waether_details);
        String jsonString = getIntent().getExtras().getString("details");
        gson = new Gson();
        description = gson.fromJson(jsonString, List.class);
    }
}
