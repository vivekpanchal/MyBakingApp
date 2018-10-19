package com.vivek.panchal.mybakingapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.vivek.panchal.mybakingapp.Models.Ingredients;
import com.vivek.panchal.mybakingapp.Models.Recipe;

public class Prefs {
    public static final String APP_PREFERENCE = "current_recipe_preference";
    public static final String RECIPE_KEY = "current_recipe";


    public static Recipe retrieveWidgetItemFromPrefs(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(RECIPE_KEY, "");
        return gson.fromJson(json, Recipe.class);
    }


    public static String IngrendientTextString(Ingredients ingredient, int position) {
        return (position + 1)
                + ". " + ingredient.getQuantity()
                + " " + ingredient.getMeasure()
                + " " + ingredient.getIngredient();
    }


}
