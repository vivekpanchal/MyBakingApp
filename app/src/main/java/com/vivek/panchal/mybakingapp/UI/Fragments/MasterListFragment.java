package com.vivek.panchal.mybakingapp.UI.Fragments;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.vivek.panchal.mybakingapp.Adapters.RecipeListAdapter;
import com.vivek.panchal.mybakingapp.Models.Recipe;
import com.vivek.panchal.mybakingapp.R;
import com.vivek.panchal.mybakingapp.ViewModel.RecipeViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MasterListFragment extends Fragment {
    @BindView(R.id.recipe_list)
    RecyclerView recipeRecyclerView;
    @BindView(R.id.recipe_pb)
    ProgressBar progressBar;

    private RecipeListAdapter recipeAdapter;
    RecipeViewModel recipeViewModel;
    private Context context;


    // Mandatory empty constructor
    public MasterListFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        ButterKnife.bind(this, rootView);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidth = displayMetrics.widthPixels / displayMetrics.density;

        recipeRecyclerView.setHasFixedSize(true);
        if (screenWidth >= 600) {
            recipeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            recipeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        }

        recipeViewModel = new RecipeViewModel();

        if (recipeViewModel.getRecipes() != null) {
            recipeViewModel.getRecipes().observe((LifecycleOwner) context, new Observer<List<Recipe>>() {
                @Override
                public void onChanged(@Nullable List<Recipe> recipes) {
                    recipeAdapter = new RecipeListAdapter(context, recipes);
                    recipeRecyclerView.setAdapter(recipeAdapter);
                }
            });
        } else {
            recipeAdapter = new RecipeListAdapter(context, null);
            recipeRecyclerView.setAdapter(recipeAdapter);
        }


        return rootView;
    }
}
