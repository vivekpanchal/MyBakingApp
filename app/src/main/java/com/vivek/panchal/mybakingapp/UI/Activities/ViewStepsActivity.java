package com.vivek.panchal.mybakingapp.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vivek.panchal.mybakingapp.Models.Steps;
import com.vivek.panchal.mybakingapp.R;
import com.vivek.panchal.mybakingapp.UI.Fragments.ViewStepsFragment;

import java.util.List;
import java.util.Objects;

public class ViewStepsActivity extends AppCompatActivity {

    private Bundle arguments;
    public List<Steps> steps;
    public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_steps);

        arguments = new Bundle();
        arguments.putParcelable("stepInfoFromActivity", Objects.requireNonNull(getIntent().getExtras()).getParcelable("stepInfo"));
//        if (getIntent().hasExtra("videoposition") && getIntent().hasExtra("videosteps"))
//
//            position = getIntent().getIntExtra("videoposition", 0);
//        steps = getIntent().getParcelableArrayListExtra("videosteps");

        setUpFragment();

    }

    private void setUpFragment() {
//        ViewStepsFragment stepsFragment = new ViewStepsFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("videosteps", new ArrayList<Parcelable>(steps));
//        stepsFragment.setArguments(bundle);
//
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.view_step_Framelayout, stepsFragment)
//                .commit();

        ViewStepsFragment stepsFragment = new ViewStepsFragment();
        stepsFragment.setArguments(arguments);
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.view_step_Framelayout, stepsFragment)
                .commit();


    }
}
