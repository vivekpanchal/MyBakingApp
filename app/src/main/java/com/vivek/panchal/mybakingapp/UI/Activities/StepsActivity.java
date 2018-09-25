package com.vivek.panchal.mybakingapp.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vivek.panchal.mybakingapp.Adapters.StepsAdapter;
import com.vivek.panchal.mybakingapp.Models.Ingredients;
import com.vivek.panchal.mybakingapp.Models.Steps;
import com.vivek.panchal.mybakingapp.R;

import java.util.List;

public class StepsActivity extends AppCompatActivity {
    private static String TAG = StepsActivity.class.getSimpleName();
    private List<Steps> stepsList;
    private boolean twoPane;
    private StepsAdapter stepsAdapter;
    private int position;
    private String recipeName;
    private List<Ingredients> mIngredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
    }
}
