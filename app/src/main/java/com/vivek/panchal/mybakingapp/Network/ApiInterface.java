package com.vivek.panchal.mybakingapp.Network;

import com.vivek.panchal.mybakingapp.Models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface for retrofit client
 */
public interface ApiInterface {

    @GET("/baking.json")
    Call<List<Recipe>> getRecipe();


}
