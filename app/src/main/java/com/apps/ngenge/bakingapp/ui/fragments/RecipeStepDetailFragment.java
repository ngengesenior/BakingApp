package com.apps.ngenge.bakingapp.ui.fragments;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.models.Step;
import com.apps.ngenge.bakingapp.utils.Utils;
import com.google.android.exoplayer2.C;
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

public class RecipeStepDetailFragment extends Fragment {

    private static final String PLAYER_POS ="PLAYER_POS";
    private static final String KEY_AUTO_PLAY = "AUTO_PLAY" ;
    private static final String KEY_WINDOW = "WINDOW" ;
    private static final String KEY_POSITION = "POSITION";
    private static  long playerPos;
    //static ArrayList<Step> stepArrayList;
    private Step step;
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
    private long startPosition;
    private int startWindow;
    private boolean startAutoPlay;







    public RecipeStepDetailFragment() {
    }

//    public static RecipeStepDetailFragment newInstance(List<Step> steps, int currentStepPosition) {
//         stepArrayList = new ArrayList<>(steps);
//        RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
//        Bundle args = new Bundle();
//        args.putParcelableArrayList(Utils.STEP_LIST_TAG, stepArrayList);
//        args.putInt(Utils.CURRENT_POS_TAG, currentStepPosition);
//        fragment.setArguments(args);
//        return fragment;
//
//    }


    public static RecipeStepDetailFragment newInstance(Step step) {

        RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Utils.STEP_TAG,step);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            step = args.getParcelable(Utils.STEP_TAG);
            //currentStepPosition = args.getInt(Utils.CURRENT_POS_TAG, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_recipe_step_details, container, false);
        ButterKnife.bind(this,view);
        stepsDescriptionView.setText(step.getDescription());
        return view;

    }


    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this.getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(startAutoPlay);
        //player.seekTo(currentWindow, playbackPosition);

        togglePlayerViewVisibility(step.getVideoURL());
        Uri uri = Uri.parse(step.getVideoURL());
        MediaSource mediaSource = buildMediaSource(uri);
        boolean haveStartPosition = startWindow != C.INDEX_UNSET;
        if (haveStartPosition) {
            player.seekTo(startWindow, startPosition);
        }
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

            playerView.setVisibility(View.GONE);
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
            /*playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();*/
            updateStartPosition();
            player.release();
            player = null;
        }
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            startAutoPlay = savedInstanceState.getBoolean(KEY_AUTO_PLAY);
            startWindow = savedInstanceState.getInt(KEY_WINDOW);
            startPosition = savedInstanceState.getLong(KEY_POSITION);
        }
        else {
            clearStartPosition();
        }
    }

    private void updateStartPosition() {
        if (player != null) {
            startAutoPlay = player.getPlayWhenReady();
            startWindow = player.getCurrentWindowIndex();
            startPosition = Math.max(0, player.getContentPosition());
        }
    }


    private void clearStartPosition() {
        startAutoPlay = true;
        startWindow = C.INDEX_UNSET;
        startPosition = C.TIME_UNSET;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        updateStartPosition();
        outState.putBoolean(KEY_AUTO_PLAY, startAutoPlay);
        outState.putInt(KEY_WINDOW, startWindow);
        outState.putLong(KEY_POSITION, startPosition);
    }

}
