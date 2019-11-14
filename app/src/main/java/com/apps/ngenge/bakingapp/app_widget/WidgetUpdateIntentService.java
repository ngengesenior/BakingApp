package com.apps.ngenge.bakingapp.app_widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import com.apps.ngenge.bakingapp.models.Ingredient;
import com.apps.ngenge.bakingapp.models.Recipe;

import java.util.List;

import static com.apps.ngenge.bakingapp.utils.Utils.RECIPE_TAG;


public class WidgetUpdateIntentService extends IntentService {

    List<Ingredient> ingredients;
    public static String ACTION_RECIPE_UPDATE = "com.apps.ngenge.bakinapp.ACTION_RECIPE";

    public WidgetUpdateIntentService() {
        super("WidgetUpdateIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null && intent.getAction().equals(ACTION_RECIPE_UPDATE)) {
            Recipe recipe = intent.getParcelableExtra(RECIPE_TAG);

            Log.d("TAG--","onHandle "+ recipe.getName());

            ingredients = recipe.getIngredients();

            //update the widget

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,BakingWidget.class));

            BakingWidget.updateAppWidget(this,appWidgetManager,appWidgetIds,ingredients);

        }
    }


}
