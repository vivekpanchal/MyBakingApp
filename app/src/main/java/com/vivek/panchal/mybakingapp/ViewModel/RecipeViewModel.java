package com.vivek.panchal.mybakingapp.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.vivek.panchal.mybakingapp.Models.Recipe;
import com.vivek.panchal.mybakingapp.repository.Repository;

import java.util.List;

public class RecipeViewModel extends ViewModel {

    private static String TAG = RecipeViewModel.class.getSimpleName();
    //live data of recipes
    private LiveData<List<Recipe>> recipes;

    public RecipeViewModel() {

        if (recipes != null) {
            return;
        }
        loadRecipes();

    }

    private void loadRecipes() {
        //getting recipes
        recipes = Repository.getRepositoryInstance().getRecipeData();
    }

    public LiveData<List<Recipe>> getRecipes() {
        Log.d(TAG, "getRecipes: " + recipes);
        return recipes;
    }
}
