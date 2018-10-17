package com.vivek.panchal.mybakingapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vivek.panchal.mybakingapp.Models.Ingredients;
import com.vivek.panchal.mybakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.RecipeIngredientViewholder> {

    private List<Ingredients> ingredientList;
    private Context context;

    public IngredientAdapter(List<Ingredients> ingredientList, Context context) {
        this.ingredientList = ingredientList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeIngredientViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_list_item, parent, false);
        return new RecipeIngredientViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientViewholder holder, int position) {
        Ingredients ingredient = ingredientList.get(position);
        Double qty = ingredient.getQuantity();
        //  holder.qtyMeasuretxtView.setText();

        holder.ingredientDetailTextview.setText((position+1)+":  "+String.valueOf(qty) + " " + ingredient.getMeasure() + " " + ingredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        if (ingredientList == null) {
            return 0;
        } else {
            return ingredientList.size();
        }

    }

    public void setIngredients(List<Ingredients> ingredientList) {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    public class RecipeIngredientViewholder extends RecyclerView.ViewHolder {


        @BindView(R.id.ingredientdetail)
        TextView ingredientDetailTextview;

        public RecipeIngredientViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
