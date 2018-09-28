package com.vivek.panchal.mybakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vivek.panchal.mybakingapp.Models.Steps;
import com.vivek.panchal.mybakingapp.R;
import com.vivek.panchal.mybakingapp.UI.Activities.StepsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {
    private final boolean twoPane;
    private final Context context;
    private final List<Steps> mStepsList;

    public StepsAdapter(boolean twoPane, Context context, List<Steps> mStepsList) {
        this.twoPane = twoPane;
        this.context = context;
        this.mStepsList = mStepsList;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.steps_list, parent, false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        if (mStepsList.get(position).getDescription() != null) {
            holder.steps_text.setText(mStepsList.get(position).getShortDescription());
        }


    }

    @Override
    public int getItemCount() {
        if (mStepsList == null) {
            return 0;
        } else {
            return mStepsList.size();
        }

    }


    public class StepsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.steps_text)
        TextView steps_text;

        public StepsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
