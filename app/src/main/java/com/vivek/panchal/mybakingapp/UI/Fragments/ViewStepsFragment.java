package com.vivek.panchal.mybakingapp.UI.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.vivek.panchal.mybakingapp.Models.Steps;
import com.vivek.panchal.mybakingapp.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewStepsFragment extends Fragment {

    private static final String STEP_KEY = "stepInfoFromActivity";
    private Steps step;
    @BindView(R.id.tv_description_view_step)
    TextView longDescTextView;
    @BindView(R.id.playerView)
    PlayerView simpleExoPlayerView;
    @BindView(R.id.toolbarViewStep)
    Toolbar toolbar;
    private SimpleExoPlayer exoPlayer;
    private String bundleKey;

    public static ViewStepsFragment getInstance(Steps step) {
        ViewStepsFragment stepsFragment = new ViewStepsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(STEP_KEY, step);
        stepsFragment.setArguments(bundle);
        return stepsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            step = bundle.getParcelable(STEP_KEY);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_step, container, false);
        ButterKnife.bind(this, view);

        initView();
        initPlayer();

        //region savedInstance for exoPlayer
        if (savedInstanceState != null && exoPlayer != null) {
            exoPlayer.seekTo(savedInstanceState.getLong("exoPlayerPosition"));
            exoPlayer.setPlayWhenReady(savedInstanceState.getBoolean("exoPlayerState"));
        }
        //endregion

        return view;
    }

    //region initialisation player
    private void initPlayer() {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());

        simpleExoPlayerView.requestFocus();
        simpleExoPlayerView.setPlayer(exoPlayer);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getActivity(), Util.getUserAgent(getActivity(), "exo-demo"));
        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(step.getVideoURL()));

        if (TextUtils.isEmpty(step.getVideoURL())) {
            Toast.makeText(getActivity(), "Video is Not available", Toast.LENGTH_SHORT).show();
        }

        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    //endregion

    //region init views
    private void initView() {
        if (getArguments() != null) {
            if (getArguments().containsKey("stepInfoFromActivity")) {
                bundleKey = "stepInfoFromActivity";
            } else if (getArguments().containsKey("stepInfo")) {
                bundleKey = "stepInfo";
            }
        }
        if (getArguments() != null) {
            step = getArguments().getParcelable(bundleKey);
        }

        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(step.getShortDescription());
        longDescTextView.setText(step.getDescription());
    }

    //endregion

    //region OnsavedInstance
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("exoPlayerPosition", exoPlayer.getCurrentPosition());
        outState.putBoolean("exoPlayerState", exoPlayer.getPlayWhenReady());
    }

    //endregion

    @Override
    public void onStop() {
        super.onStop();
        simpleExoPlayerView.setPlayer(null);
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
            exoPlayer.stop();
            exoPlayer.release();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        simpleExoPlayerView.setPlayer(null);
    }
}
