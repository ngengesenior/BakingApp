package com.apps.ngenge.bakingapp.ui.fragments;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.models.Step;
import com.apps.ngenge.bakingapp.utils.Utils;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;

import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepDetailFragmentTablet extends Fragment {

    private static final String PLAYER_POS ="PLAYER_POS";
    private static  long playerPos;
    static ArrayList<Step> stepArrayList;
    @BindView(R.id.exoPlayerView)
    PlayerView playerView;
    @BindView(R.id.stepDescription)
    TextView stepsDescriptionView;
    ImageButton previousButton;

    private ExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow;
    private long playbackPosition;
    private int currentStepPosition;





    public RecipeStepDetailFragmentTablet() {
    }

    public static RecipeStepDetailFragmentTablet newInstance(List<Step> steps, int currentStepPosition) {
         stepArrayList = new ArrayList<>(steps);
        RecipeStepDetailFragmentTablet fragment = new RecipeStepDetailFragmentTablet();
        Bundle args = new Bundle();
        args.putParcelableArrayList(Utils.STEP_LIST_TAG, stepArrayList);
        args.putInt(Utils.CURRENT_POS_TAG, currentStepPosition);
        fragment.setArguments(args);
        return fragment;

    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            stepArrayList = args.getParcelable(Utils.STEP_LIST_TAG);
            currentStepPosition = args.getInt(Utils.CURRENT_POS_TAG, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_recipe_step_details, container, false);
        ButterKnife.bind(this,view);
        stepsDescriptionView.setText(stepArrayList.get(currentStepPosition).getDescription());
        return view;

    }


    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this.getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        togglePlayerViewVisibility(stepArrayList.get(currentStepPosition).getVideoURL());
        Uri uri = Uri.parse(stepArrayList.get(currentStepPosition).getVideoURL());
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }


    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("BakingApp")).
                createMediaSource(uri);
    }



    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }


    private void togglePlayerViewVisibility(String videoUrl)
    {
        if (videoUrl.equals("") || videoUrl.isEmpty())
        {
            playerView.setVisibility(View.INVISIBLE);
        }

        else playerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }


    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }


    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }



}
