package com.vivek.panchal.mybakingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.vivek.panchal.mybakingapp.Models.Recipe;

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

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
