package com.example.andre.cocktailapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RandomDrink extends AppCompatActivity {

    TextView tv, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_drink);

        final TextView textViewN = findViewById(R.id.textViewNazev);
        final TextView textViewK = findViewById(R.id.textViewKategorie);
        final TextView textViewI = findViewById(R.id.textViewIngredience);
        final TextView textViewIN = findViewById(R.id.textViewInstrukce);
        final ImageView imageViewIM = findViewById(R.id.imageViewI);
        AsyncTask asyncTask = new AsyncTask() {
            @Override

            protected Object doInBackground(Object[] objects) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://www.thecocktaildb.com/api/json/v1/1/random.php")
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
                textViewN.setText("Name: " + drinkList.drinks.get(0).strDrink);
                textViewK.setText("Category: " + drinkList.drinks.get(0).strCategory);
                textViewI.setText("Instructions: " + drinkList.drinks.get(0).strInstructions);
                textViewIN.setText("Ingredients: " + "\n");
                Picasso.get().load(drinkList.drinks.get(0).strDrinkThumb).into(imageViewIM);
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
                        textViewIN.append(measure + " - " +ingredient + "\n");
                    }
                    else if(measure.trim().isEmpty() && !ingredient.trim().isEmpty()){
                        textViewIN.append(ingredient + "\n");
                    }

                }

            }


        }.execute();
    }
}
