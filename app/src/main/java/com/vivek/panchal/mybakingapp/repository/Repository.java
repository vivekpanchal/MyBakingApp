package com.vivek.panchal.mybakingapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.vivek.panchal.mybakingapp.Models.Recipe;
import com.vivek.panchal.mybakingapp.Network.ApiInterface;
import com.vivek.panchal.mybakingapp.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is the Repository where all the Network and Database query are done
 */
public class Repository {
    private static String TAG = Repository.class.getSimpleName();

    private static Repository repository;
    private MutableLiveData<List<Recipe>> recipeList;

    /**
     * This method used to get the instance of the class
     *
     * @return instance of the repository class
     */
    public static Repository getRepositoryInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }


    public LiveData<List<Recipe>> getRecipeData() {

        //if list is null
        if (recipeList == null) {
            recipeList = new MutableLiveData<List<Recipe>>();
            //we will load it asynchronously from server in this method
            ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
            Call<List<Recipe>> listCall = apiInterface.getRecipe();

            listCall.enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    recipeList.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    Log.d(TAG, "onFailure: Failed fetching data");
                }
            });

        }
        return recipeList;
    }


}
