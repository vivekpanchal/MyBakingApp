package com.vivek.panchal.mybakingapp.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vivek.panchal.mybakingapp.Models.Steps;
import com.vivek.panchal.mybakingapp.R;
import com.vivek.panchal.mybakingapp.UI.Fragments.ViewStepsFragment;

import java.util.List;
import java.util.Objects;

public class ViewStepsActivity extends AppCompatActivity {

    private Steps step;
    private ViewStepsFragment viewStepsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_steps);
        if (getIntent().getExtras() != null)
            step = getIntent().getExtras().getParcelable("stepInfo");
        if (savedInstanceState == null) {
            viewStepsFragment = ViewStepsFragment.getInstance(step);
            setUpFragment();
        }
    }

    private void setUpFragment() {
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_step_Framelayout, viewStepsFragment)
                .commit();
    }
}
