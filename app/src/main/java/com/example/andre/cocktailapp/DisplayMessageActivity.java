package com.example.andre.cocktailapp;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
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
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();
        String namePref = preferences.getString("EditText","");
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AsyncTask asyncTask = new AsyncTask() {
                    @Override

                    protected Object doInBackground(Object[] objects) {
                        OkHttpClient client = new OkHttpClient();
                        String tmp = editTextName.getText().toString();
                        String cocktail_name = tmp.replace(" ", "+");
                        editor.putString("EditText", cocktail_name);
                        editor.commit();
                        Request request = new Request.Builder()
                                .url("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + cocktail_name)
                                .build();


                        editor.commit();

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
                        textView5.setText("Ingredients: " + "\n");
                        Picasso.get().load(drinkList.drinks.get(0).strDrinkThumb).into(imageView);
                        closeKeyboard();
                        Drink drink = drinkList.drinks.get(0);
                        List<Pair<String,String>> ingredients = new ArrayList<Pair<String,String>>();
                        ingredients.add(new Pair(drink.strMeasure1,drink.strIngredient1));
                        ingredients.add(new Pair(drink.strMeasure2,drink.strIngredient2));
                        ingredients.add(new Pair(drink.strMeasure3,drink.strIngredient3));
                        ingredients.add(new Pair(drink.strMeasure4,drink.strIngredient4));
                        ingredients.add(new Pair(drink.strMeasure5,drink.strIngredient5));
                        ingredients.add(new Pair(drink.strMeasure6,drink.strIngredient6));
                        ingredients.add(new Pair(drink.strMeasure7,drink.strIngredient7));
                        ingredients.add(new Pair(drink.strMeasure8,drink.strIngredient8));
                        ingredients.add(new Pair(drink.strMeasure9,drink.strIngredient9));
                        ingredients.add(new Pair(drink.strMeasure10,drink.strIngredient10));

                        for(int i=0;i<10;i++){
                            String measure = ingredients.get(i).first;
                            String ingredient = ingredients.get(i).second;

                            if(!measure.trim().isEmpty() && !ingredient.trim().isEmpty()){
                                textView5.append(measure + " - " +ingredient + "\n");
                            }
                            else if(measure.trim().isEmpty() && !ingredient.trim().isEmpty()){
                                textView5.append(ingredient + "\n");
                            }

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
