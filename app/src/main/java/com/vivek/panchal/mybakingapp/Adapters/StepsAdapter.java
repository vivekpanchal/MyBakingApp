package com.vivek.panchal.mybakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {


    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class StepsViewHolder extends RecyclerView.ViewHolder {

        public StepsViewHolder(View itemView) {
            super(itemView);
        }
    }


}
