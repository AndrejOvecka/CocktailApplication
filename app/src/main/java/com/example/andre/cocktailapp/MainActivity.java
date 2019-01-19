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

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
private Button Button;
String message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button = (Button) findViewById(R.id.Button);
        final TextView textView = findViewById(R.id.textView2);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRandomDrink();




            AsyncTask asyncTask = new AsyncTask() {
                @Override

                protected Object doInBackground(Object[] objects) {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.thecocktaildb.com/api/json/v1/1/random.php")
                            .build();

                    Response response = null;

                    try {
                        response = client.newCall(request).execute();
                        return response.body().string();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;

                    }
                @Override
                protected void onPostExecute(Object o) {
                    String json = o.toString();
                    Gson gson = new Gson();
                    DrinkList drinkList = gson.fromJson(json,DrinkList.class);
                    System.out.println(drinkList.drinks.get(0).strDrink);
                    message = o.toString();


                }


            }.execute();
                Intent intent = new Intent(MainActivity.this, RandomDrink.class);
                intent.putExtra("RESPONSE", message);
                startActivity(intent);

        }



        });
    }

    public void openRandomDrink(){
        Intent intent  = new Intent(this, RandomDrink.class);
        startActivity(intent);
    }
    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        startActivity(intent);

    }
}
