package com.apps.ngenge.bakingapp.ui.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apps.ngenge.bakingapp.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class MainActivity extends AppCompatActivity {
    private String videoURL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/590129ad_17-frost-all-around-cake-yellow-cake/17-frost-all-around-cake-yellow-cake.mp4";


    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleExoPlayerView = findViewById(R.id.playerView);

         exoPlayer = ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());
        simpleExoPlayerView.setPlayer(exoPlayer);
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(videoURL),new DefaultDataSourceFactory(this,"BakingApp"),
                new DefaultExtractorsFactory(),null,null);

        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);

        //exoPlayer.release();


    }


    @Override
    protected void onPause() {
        super.onPause();
        exoPlayer.release();


    }
}
