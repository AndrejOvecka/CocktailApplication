package com.example.andre.cocktailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RandomDrink extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_drink);

        tv = (TextView) findViewById(R.id.textViewR);
        tv.setText(getIntent().getStringExtra("RESPONSE"));
    }
}
