package com.apps.ngenge.bakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.app_widget.WidgetUpdateIntentService;
import com.apps.ngenge.bakingapp.models.Recipe;
import com.apps.ngenge.bakingapp.models.Step;
import com.apps.ngenge.bakingapp.ui.fragments.RecipeStepDetailFragment;
import com.apps.ngenge.bakingapp.ui.fragments.RecipeStepsFragment;
import com.apps.ngenge.bakingapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.apps.ngenge.bakingapp.utils.Utils.RECIPE_TAG;


public class RecipeDetailsActivity extends AppCompatActivity implements
RecipeStepsFragment.OnStepSelectedListener{
    private boolean isTablet;
    private Recipe recipe;
    private List<Step> stepList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recipe_details_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        isTablet = getResources().getBoolean(R.bool.isTablet);
        Intent intent = getIntent();
        if(intent.hasExtra(Utils.RECIPE_TAG))
        {
            recipe = intent.getParcelableExtra(Utils.RECIPE_TAG);
            stepList = recipe.getSteps();
            getSupportActionBar().setTitle(recipe.getName());
        }
        RecipeStepsFragment fragment = RecipeStepsFragment.newInstance(recipe);
        if(!isTablet) {
            if (intent.hasExtra(Utils.RECIPE_TAG)) {

                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction().add(R.id.recipeStepsContainer, fragment).commit();
                }


            }
        }
        else {

            if(savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.recipeStepsContainer, fragment).commit();
            }

        }

        updateService();
    }


    @Override
    public void onStepSelected(Step step) {
        if(isTablet)
        {
            RecipeStepDetailFragment fragment = RecipeStepDetailFragment.newInstance(step);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recipeStepDetailContainer,fragment)
                    .commit();

        }

        else {
            Intent intent = new Intent(this,RecipeStepDetailActivity.class);
            intent.putParcelableArrayListExtra(Utils.STEP_LIST_TAG,new ArrayList<>(stepList));

            intent.putExtra(Utils.CURRENT_POS_TAG,step.getId());
            intent.putExtra(Utils.NAME_TAG,recipe.getName());
            startActivity(intent);
        }
    }

    void updateService() {
        //Toast.makeText(this,"Service Started",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, WidgetUpdateIntentService.class);
        intent.putExtra(RECIPE_TAG,recipe);
        intent.setAction(WidgetUpdateIntentService.ACTION_RECIPE_UPDATE);
        startService(intent);

    }
}
