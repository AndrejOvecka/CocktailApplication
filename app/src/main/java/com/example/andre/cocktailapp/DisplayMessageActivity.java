package com.example.andre.cocktailapp;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.*;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.google.gson.*;
import com.squareup.picasso.Picasso;


import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;


public class DisplayMessageActivity extends AppCompatActivity {
    Button hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        final Button button = findViewById(R.id.button2);
        final EditText editTextName = findViewById(R.id.editTextName);
        final TextView textView3 = findViewById(R.id.textView4);
        final TextView textView2 = findViewById(R.id.textView3);
        final TextView textView4 = findViewById(R.id.textViewI);
        final TextView textView5 = findViewById(R.id.textView5);
        final ImageView imageView = findViewById(R.id.imageView);



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
                        String json = o.toString();
                        Gson gson = new Gson();
                        DrinkList drinkList = gson.fromJson(json,DrinkList.class);
                        System.out.println(drinkList.drinks.get(0).strDrink);
                        textView2.setText("Name: " + drinkList.drinks.get(0).strDrink);
                        textView3.setText("Category: " + drinkList.drinks.get(0).strCategory);
                        textView4.setText("Instructions: " + drinkList.drinks.get(0).strInstructions);
                        textView5.setText("Ingredients: ");
                        Picasso.get().load(drinkList.drinks.get(0).strDrinkThumb).into(imageView);
                        closeKeyboard();
                        if(((drinkList.drinks.get(0).strIngredient1 != "") || (drinkList.drinks.get(0).strIngredient1 != " ")) && ((drinkList.drinks.get(0).strIngredient1 != "") || (drinkList.drinks.get(0).strIngredient1 != " ")))
                        {
                            textView5.append("\n" + drinkList.drinks.get(0).strMeasure1 + " - " + drinkList.drinks.get(0).strIngredient1);
                        }
                        if(((drinkList.drinks.get(0).strIngredient2 != "") || (drinkList.drinks.get(0).strIngredient2 != " ")) && ((drinkList.drinks.get(0).strIngredient2 != "") || (drinkList.drinks.get(0).strIngredient2 != " ")))
                        {
                            textView5.append("\n" + drinkList.drinks.get(0).strMeasure2 + " - " + drinkList.drinks.get(0).strIngredient2);
                        }
                        if(((drinkList.drinks.get(0).strIngredient3 != "") || (drinkList.drinks.get(0).strIngredient3 != " ")) && ((drinkList.drinks.get(0).strIngredient3 != "") || (drinkList.drinks.get(0).strIngredient3 != " ")))
                        {
                            textView5.append("\n" + drinkList.drinks.get(0).strMeasure3 + " - " + drinkList.drinks.get(0).strIngredient3);
                        }
                        if(((drinkList.drinks.get(0).strIngredient4 != "") || (drinkList.drinks.get(0).strIngredient4 != " ")) && ((drinkList.drinks.get(0).strIngredient4 != "") || (drinkList.drinks.get(0).strIngredient4 != " ")))
                        {
                            textView5.append("\n" + drinkList.drinks.get(0).strMeasure4 + " - " + drinkList.drinks.get(0).strIngredient4);
                        }
                        if(((drinkList.drinks.get(0).strIngredient5 != "") || (drinkList.drinks.get(0).strIngredient5 != " ")) && ((drinkList.drinks.get(0).strIngredient5 != "") || (drinkList.drinks.get(0).strIngredient5 != " ")))
                        {
                            textView5.append("\n" + drinkList.drinks.get(0).strMeasure5 + " - " + drinkList.drinks.get(0).strIngredient5);
                        }
                        if(((drinkList.drinks.get(0).strIngredient6 != "") || (drinkList.drinks.get(0).strIngredient6 != " ")) && ((drinkList.drinks.get(0).strIngredient6 != "") || (drinkList.drinks.get(0).strIngredient6 != " ")))
                        {
                            textView5.append("\n" + drinkList.drinks.get(0).strMeasure6 + " - " + drinkList.drinks.get(0).strIngredient6);
                        }
                        if(((drinkList.drinks.get(0).strMeasure7 != "") || (drinkList.drinks.get(0).strMeasure7 != " ")) && ((drinkList.drinks.get(0).strIngredient7 != "") || (drinkList.drinks.get(0).strIngredient7 != " ")))
                        {
                            textView5.append("\n" + drinkList.drinks.get(0).strMeasure7 + " - " + drinkList.drinks.get(0).strIngredient7);
                        }
                        if(((drinkList.drinks.get(0).strMeasure8 != "") || (drinkList.drinks.get(0).strMeasure8 != " ")) && ((drinkList.drinks.get(0).strIngredient8 != "") || (drinkList.drinks.get(0).strIngredient8 != " ")))
                        {
                            textView5.append("\n" + drinkList.drinks.get(0).strMeasure8 + " - " + drinkList.drinks.get(0).strIngredient8 + "ahoj");
                        }

                    }


                }.execute();

            }

            public void closeKeyboard(){
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                }

            }


        });


    }
}
