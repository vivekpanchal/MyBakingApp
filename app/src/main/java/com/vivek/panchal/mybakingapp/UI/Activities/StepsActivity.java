package com.vivek.panchal.mybakingapp.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vivek.panchal.mybakingapp.Adapters.IngredientAdapter;
import com.vivek.panchal.mybakingapp.Adapters.StepsAdapter;
import com.vivek.panchal.mybakingapp.Models.Ingredients;
import com.vivek.panchal.mybakingapp.Models.Steps;
import com.vivek.panchal.mybakingapp.R;
import com.vivek.panchal.mybakingapp.UI.Fragments.ViewStepsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsActivity extends AppCompatActivity {
    private static String TAG = StepsActivity.class.getSimpleName();
    private List<Steps> stepsList;
    private boolean mTwoPane;

    private StepsAdapter stepsAdapter;
    private IngredientAdapter ingredientAdapter;
    private int position;
    private String recipeName;
    private List<Ingredients> mIngredientsList;


    @BindView(R.id.rv_steps)
    RecyclerView mSelectRecylerView;


    @BindView(R.id.ingredientsrecyclerview)
    RecyclerView mIngredientRecylerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);

        setUpActivity();
    }

    /**
     * A method that initializes the activity
     */
    private void setUpActivity() {


        Intent intent = getIntent();
        if (intent.hasExtra("stepsList") && intent.hasExtra("position")) {

            stepsList = getIntent().getParcelableArrayListExtra("stepsList");
            position = intent.getIntExtra("position", 0);
            recipeName = getIntent().getStringExtra("recipeName");
            mIngredientsList = getIntent().getParcelableArrayListExtra("ingredientsList");

            Log.d(TAG, "[StepsList]: " + stepsList.get(position).getId());

        }

        //checking if the its tablet  or simple phone mode
        if (findViewById(R.id.view_step_Framelayout) != null) {
            mTwoPane = true;
            setUpTabletFragment();
        } else {
            mTwoPane = false;
        }

        mSelectRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mIngredientRecylerView.setLayoutManager(new LinearLayoutManager(this));
        stepsAdapter = new StepsAdapter(this, stepsList);
        ingredientAdapter = new IngredientAdapter(mIngredientsList, this);
        mSelectRecylerView.setAdapter(stepsAdapter);
        mIngredientRecylerView.setAdapter(ingredientAdapter);

    }


    private void setUpTabletFragment() {

        ViewStepsFragment stepsFragment = new ViewStepsFragment();

        //Show default fragment in tablet
        Bundle arguments = new Bundle();
        Steps step = stepsList.get(0);
        arguments.putParcelable("stepInfo", step);

        stepsFragment.setArguments(arguments);
        stepsFragment.setCurrentStep(position);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.view_step_Framelayout, stepsFragment)
                .commit();


    }

//    @Override
//    public void OnClickednstep(int position) {
//
//        ViewStepsFragment stepsFragment = new ViewStepsFragment();
//        stepsFragment.setCurrentStep(position);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, stepsFragment)
//                .commit();
//    }


}

