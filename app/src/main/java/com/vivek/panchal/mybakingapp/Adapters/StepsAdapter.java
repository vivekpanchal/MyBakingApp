package com.vivek.panchal.mybakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vivek.panchal.mybakingapp.Models.Steps;
import com.vivek.panchal.mybakingapp.R;
import com.vivek.panchal.mybakingapp.UI.Activities.ViewStepsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private final Context context;
    private final List<Steps> mSteps;

    public StepsAdapter(Context context, List<Steps> mSteps) {
        this.context = context;
        this.mSteps = mSteps;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.steps_list, parent, false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        if (mSteps.get(position).getDescription() != null) {
            holder.steps_text.setText((position + 1) + ": " + mSteps.get(position).getShortDescription());
        }

        holder.stepCard.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewStepsActivity.class);
            intent.putParcelableArrayListExtra("videosteps", new ArrayList<Parcelable>(mSteps));
            intent.putExtra("videoposition", holder.getAdapterPosition());
            Log.d("videsteps", "" + mSteps);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (mSteps == null) {
            return 0;
        } else {
            return mSteps.size();
        }

    }


    public class StepsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.steps_text)
        TextView steps_text;
        @BindView(R.id.step_Card)
        CardView stepCard;

        public StepsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
