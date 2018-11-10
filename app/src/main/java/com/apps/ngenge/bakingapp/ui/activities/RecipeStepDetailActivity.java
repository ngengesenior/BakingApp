package com.apps.ngenge.bakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.adapters.StepsPagerAdapter;
import com.apps.ngenge.bakingapp.models.Step;
import com.apps.ngenge.bakingapp.ui.fragments.RecipeStepDetailFragment;
import com.apps.ngenge.bakingapp.utils.Utils;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Available only for regular phones
 */
public class RecipeStepDetailActivity extends AppCompatActivity {

    private static final String CURRENT_POS = "CURRENT_POS";
    private static final String STEP_LIST = "STEP_LIST";
    private List<Step> stepList;
    private int currentStepPosition;
    private List<String> titles;
    private List<Fragment> fragments;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.sliding_tabs)
    TabLayout slidingTabs;

    private StepsPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recipe_step_detail_activity);
        ButterKnife.bind(this);

        pagerAdapter = new StepsPagerAdapter(getSupportFragmentManager());

        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(intent.hasExtra(Utils.CURRENT_POS_TAG))
        {
            stepList = intent.getParcelableArrayListExtra(STEP_LIST);
            currentStepPosition = intent.getIntExtra(Utils.CURRENT_POS_TAG,0);
            getSupportActionBar().setTitle(intent.getStringExtra(Utils.NAME_TAG));
            setupFragmentsAndTitles();
            pagerAdapter.setTitles(titles);
            pagerAdapter.setFragments(fragments);




        }

        viewPager.setAdapter(pagerAdapter);
        slidingTabs.setupWithViewPager(viewPager);

        /** Check that we do not destroy activity each time and recreate on
         config changes**/
//        if(savedInstanceState == null)
//        {
//            RecipeStepDetailFragment fragment = RecipeStepDetailFragment.newInstance(stepList,currentStepPosition);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.recipeStepDetailContainer,fragment)
//                    .commit();
//
//        }
    }

    private void setupFragmentsAndTitles()
    {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        for (int i = 0; i< stepList.size(); i++)

        {
            fragments.add(i,RecipeStepDetailFragment.newInstance(stepList.get(i)));
            titles.add(i,"Step "+ i);
        }
    }

}
