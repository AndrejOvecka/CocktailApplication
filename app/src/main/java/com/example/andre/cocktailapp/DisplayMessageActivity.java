package com.example.andre.cocktailapp;

import android.app.VoiceInteractor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;


public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Button button = findViewById(R.id.button2);
        final EditText editTextName = findViewById(R.id.editTextName);

        final TextView textView2 = findViewById(R.id.textView3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        OkHttpClient client = new OkHttpClient();
                        String cocktail_name = editTextName.getText().toString().replace(" ", "+");
                        Request request = new Request.Builder()
                                .url("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + cocktail_name)
                                .build();
                        JSONObject obj_JSONObject = new JSONObject()
                        Response response = null;

                        try{
                            response = client.newCall(request).execute();
                            return response.body().string();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        textView2.setText(o.toString());
                    }
                }.execute();
            }
        });
    }
}