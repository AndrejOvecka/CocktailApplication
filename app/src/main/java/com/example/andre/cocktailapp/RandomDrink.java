package com.example.andre.cocktailapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RandomDrink extends AppCompatActivity {

    TextView tv, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_drink);

        tv = (TextView) findViewById(R.id.textViewN);
        tv.setText("Name: " + getIntent().getStringExtra( "NAME"));
        tv2 = (TextView) findViewById(R.id.textViewC);
        tv2.setText("Category: " + getIntent().getStringExtra("CATEGORY"));
    }
}
