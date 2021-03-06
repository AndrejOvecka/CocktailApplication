package com.example.andre.cocktailapp;

import android.annotation.SuppressLint;
import android.app.VoiceInteractor;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.*;


import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
private Button Button;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageViewMain = findViewById(R.id.imageView2);
        Button = (Button) findViewById(R.id.Button);
        boolean check= isInternetConnecterd();

        if (check) {
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
                            DrinkList drinkList = gson.fromJson(json, DrinkList.class);
                            System.out.println(drinkList.drinks.get(0).strDrink);
                            Picasso.get().load(drinkList.drinks.get(0).strDrinkThumb).into(imageViewMain);
                        }

                    }.execute();
                }
            });
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }

    }
    public boolean isInternetConnecterd() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
        }
        return connected;
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
