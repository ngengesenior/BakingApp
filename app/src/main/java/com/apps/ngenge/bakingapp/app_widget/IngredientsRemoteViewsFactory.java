package com.apps.ngenge.bakingapp.app_widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.models.Ingredient;

import java.util.List;

public class IngredientsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private List<Ingredient> mIngredients;

    public IngredientsRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        mIngredients = BakingWidget.mIngredients;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (mIngredients == null) {
            return 0;
        }

        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_text_view_item);
        remoteViews.setTextViewText(R.id.recipe_text,
                mIngredients.get(position).getQuantity() + " "
                        + mIngredients.get(position).getMeasure() + " "
                        + mIngredients.get(position).getIngredient());
        return remoteViews;
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
        return true;
    }
}
