package com.vivek.panchal.mybakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vivek.panchal.mybakingapp.Models.Recipe;
import com.vivek.panchal.mybakingapp.R;
import com.vivek.panchal.mybakingapp.UI.Activities.StepsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private Context mContext;
    private final List<Recipe> mRecipeList;

    public RecipeListAdapter(Context mContext, List<Recipe> mRecipeList) {
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_lists, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        if (mRecipeList.get(position).getName() != null) {
            holder.mRecipeName.setText(mRecipeList.get(position).getName());
        }
        if (mRecipeList.get(position).getImage() != null) {
            Picasso.get().load(mRecipeList.get(position).getImage()).into(holder.mRecipeImg);
        }

        holder.mRecipeCard.setOnClickListener(v -> {

            Intent intent = new Intent(mContext, StepsActivity.class);
            intent.putExtra("position", holder.getAdapterPosition());
            intent.putExtra("recipeName", mRecipeList.get(holder.getAdapterPosition()).getName());
            intent.putParcelableArrayListExtra("stepsList", new ArrayList<Parcelable>(mRecipeList.get(holder.getAdapterPosition()).getSteps()));
            intent.putParcelableArrayListExtra("ingredientsList", new ArrayList<Parcelable>(mRecipeList.get(holder.getAdapterPosition()).getIngredients()));
            mContext.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        if (mRecipeList == null) {
            return 0;
        } else {
            return mRecipeList.size();
        }
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipe_img)
        AppCompatImageView mRecipeImg;
        @BindView(R.id.recipe_name_txt)
        TextView mRecipeName;
        @BindView(R.id.card)
        CardView mRecipeCard;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
