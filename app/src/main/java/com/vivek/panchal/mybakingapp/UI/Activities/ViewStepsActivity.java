package com.vivek.panchal.mybakingapp.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vivek.panchal.mybakingapp.R;
import com.vivek.panchal.mybakingapp.UI.Fragments.ViewStepsFragment;

import java.util.Objects;

public class ViewStepsActivity extends AppCompatActivity {

    private Bundle arguments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_steps);

        arguments = new Bundle();
        arguments.putParcelable("stepInfoFromActivity", Objects.requireNonNull(getIntent().getExtras()).getParcelable("stepInfo"));

        ViewStepsFragment fragment = new ViewStepsFragment();
        fragment.setArguments(arguments);
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_step_layout, fragment)
                .commit();
    }
}
