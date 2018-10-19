package com.vivek.panchal.mybakingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.vivek.panchal.mybakingapp.Models.Ingredients;
import com.vivek.panchal.mybakingapp.Models.Recipe;
import com.vivek.panchal.mybakingapp.R;
import com.vivek.panchal.mybakingapp.Utils.Prefs;

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    Context mContext;
    Intent mIntent;
    private Recipe mRecipe;


    ///now we need to populate data in listitem arraylist
    public WidgetDataProvider(Context context, Intent intent) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        mRecipe = Prefs.retrieveWidgetItemFromPrefs(mContext);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (mRecipe == null) return 0;
        return mRecipe.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        if (mRecipe == null)
            return null;
        Ingredients ingredient = mRecipe.getIngredients().get(position);
        String content = Prefs.IngrendientTextString(ingredient, position);
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.recipe_widget);
        views.setTextViewText(R.id.widget_recipeName, content);
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
