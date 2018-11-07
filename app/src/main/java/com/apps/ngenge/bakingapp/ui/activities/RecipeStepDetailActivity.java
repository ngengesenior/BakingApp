package com.apps.ngenge.bakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.models.Step;
import com.apps.ngenge.bakingapp.ui.fragments.RecipeStepDetailFragment;
import com.apps.ngenge.bakingapp.utils.Utils;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

import java.util.List;

/**
 * Available only for regular phones
 */
public class RecipeStepDetailActivity extends AppCompatActivity {

    private static final String CURRENT_POS = "CURRENT_POS";
    private static final String STEP_LIST = "STEP_LIST";
    private List<Step> stepList;
    private int currentStepPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.layout_recipe_step_detail_activity);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(intent.hasExtra(Utils.CURRENT_POS_TAG))
        {
            stepList = intent.getParcelableArrayListExtra(STEP_LIST);
            currentStepPosition = intent.getIntExtra(Utils.CURRENT_POS_TAG,0);
            getSupportActionBar().setTitle(intent.getStringExtra(Utils.NAME_TAG));




        }

        /** Check that we do not destroy activity each time and recreate on
         config changes**/
        if(savedInstanceState == null)
        {
            RecipeStepDetailFragment fragment = RecipeStepDetailFragment.newInstance(stepList,currentStepPosition);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipeStepDetailContainer,fragment)
                    .commit();

        }
    }


}
