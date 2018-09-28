package com.vivek.panchal.mybakingapp.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vivek.panchal.mybakingapp.Adapters.StepsAdapter;
import com.vivek.panchal.mybakingapp.Models.Ingredients;
import com.vivek.panchal.mybakingapp.Models.Steps;
import com.vivek.panchal.mybakingapp.R;
import com.vivek.panchal.mybakingapp.UI.Fragments.ViewStepsFragment;

import java.util.List;

import butterknife.BindView;

public class StepsActivity extends AppCompatActivity {
    private static String TAG = StepsActivity.class.getSimpleName();
    private List<Steps> stepsList;
    private boolean twoPane;
    private StepsAdapter stepsAdapter;
    private int position;
    private String recipeName;
    private List<Ingredients> mIngredientsList;

    @BindView(R.id.rv_steps)
    RecyclerView mSelectRecylerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        recipeName = getIntent().getStringExtra("recipeName");
        stepsList = getIntent().getParcelableArrayListExtra("stepsList");
        position = getIntent().getIntExtra("position", -1);
        mIngredientsList = getIntent().getParcelableArrayListExtra("ingredientsList");

        mSelectRecylerView.setHasFixedSize(true);
        mSelectRecylerView.setLayoutManager(new LinearLayoutManager(this));

        twoPane = false;

        if (findViewById(R.id.view_step_Framelayout) != null) {
            twoPane = true;
            //Show default fragment in tablet
            Bundle arguments = new Bundle();
            Steps step = stepsList.get(0);
            arguments.putParcelable("stepInfo", step);

            ViewStepsFragment fragment = new ViewStepsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.view_step_Framelayout, fragment)
                    .commit();
        }
        stepsAdapter = new StepsAdapter(twoPane, this, stepsList);
        mSelectRecylerView.setAdapter(stepsAdapter);

    }


}

