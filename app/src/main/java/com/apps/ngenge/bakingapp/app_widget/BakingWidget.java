package com.apps.ngenge.bakingapp.app_widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.models.Ingredient;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidget extends AppWidgetProvider {

    public BakingWidget(){

    }
    public static List<Ingredient> mIngredients;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int[] appWidgetIds,List<Ingredient> ingredients) {

        mIngredients = ingredients;

        // Construct the RemoteViews object

        for (int widgetId:appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
            Intent intent = new Intent(context,ListRemoteViewsService.class);
            remoteViews.setRemoteAdapter(R.id.recipe_list, intent);
            ComponentName component = new ComponentName(context, BakingWidget.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(widgetId, R.id.recipe_list);
            appWidgetManager.updateAppWidget(component, remoteViews);
        }


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
       super.onUpdate(context,appWidgetManager,appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

