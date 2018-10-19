package com.vivek.panchal.mybakingapp.UI.Fragments;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;
import com.vivek.panchal.mybakingapp.Models.Steps;
import com.vivek.panchal.mybakingapp.R;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewStepsFragment extends Fragment {

    @BindView(R.id.fab_next)
    FloatingActionButton mNextStep_btn;
    @BindView(R.id.fab_prev)
    FloatingActionButton mPrevStep_btn;
    @BindView(R.id.playerView)
    PlayerView playerView;
    @BindView(R.id.txt_step_label)
    TextView stepLabel;
    @BindView(R.id.tv_description_view_step)
    TextView StepDescription;

    @BindView(R.id.thumbnail_url)
    ImageView mThumbnail;
    private SimpleExoPlayer player;

    public List<Steps> steps;
    public int currentPosition;
    public long playbackPosition = 0;
    public int currentWindow = 0;
    public boolean playWhenReady = true;

    private static final String SAVED_INSTANCE_POSITION = "position";
    private static final String SAVED_PLAYBACK_POSITION = "playback_position";
    private static final String SAVED_PLAYBACK_WINDOW = "current_window";
    private static final String SAVED_PLAY_WHEN_READY = "play_when_ready";


    public ViewStepsFragment() {
    }

    public void setCurrentStep(int currentStepPosition) {

        this.currentPosition = currentStepPosition;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(SAVED_INSTANCE_POSITION, 0);
            playbackPosition = savedInstanceState.getLong(SAVED_PLAYBACK_POSITION, 0);
            currentWindow = savedInstanceState.getInt(SAVED_PLAYBACK_WINDOW, 0);
            playWhenReady = savedInstanceState.getBoolean(SAVED_PLAY_WHEN_READY, false);

        }


        View view = inflater.inflate(R.layout.fragment_view_step, container, false);
        ButterKnife.bind(this, view);
        steps = getArguments().getParcelableArrayList("videosteps");


        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
//

    //region Instanstiating views
    private void initViews() {


        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideSystemUi();
        } else {
            stepLabel.setText(steps.get(currentPosition).getShortDescription());
            StepDescription.setText(steps.get(currentPosition).getDescription());
        }
        setUpNxtPrevListeners();


    }
    //endregion


    //region setting up next and Previous listner for video
    private void setUpNxtPrevListeners() {
        mNextStep_btn.setOnClickListener(v -> {
            if (currentPosition < steps.size() - 1) {
                setCurrentStep(currentPosition + 1);

                if (steps.get(currentPosition).getVideoURL() != null) {
                    releasePlayer();
                    initViews();
                    initilizePlayer();
                } else {
                    playerView.setVisibility(View.GONE);

                    initViews();
                    if (steps.get(currentPosition).getThumbnailURL() == null) {
                        Picasso.get().load(R.drawable.recipe).into(mThumbnail);
                    } else {
                        Picasso.get().load(steps.get(currentPosition).getThumbnailURL()).into(mThumbnail);
                    }
                }


            }
        });
        mPrevStep_btn.setOnClickListener(v -> {
            if (currentPosition > 0) {
                setCurrentStep(currentPosition - 1);


                if (steps.get(currentPosition).getVideoURL() != null) {
                    releasePlayer();
                    initViews();
                    initilizePlayer();
                    mThumbnail.setVisibility(View.GONE);
                } else {
                    playerView.setVisibility(View.GONE);
                    mThumbnail.setVisibility(View.VISIBLE);
                    initViews();
                    if (steps.get(currentPosition).getThumbnailURL() == null) {
                        Picasso.get().load(R.drawable.recipe).into(mThumbnail);
                    } else {
                        Picasso.get().load(steps.get(currentPosition).getThumbnailURL()).into(mThumbnail);
                    }

                }

            }
        });
    }


    //endregion

    @Override
    public void onResume() {
        super.onResume();

        if (Util.SDK_INT < 23 || player == null) {

            if (steps.get(currentPosition).getVideoURL() != null) {
                initilizePlayer();
                initViews();
//                thumnail.setVisibility(View.GONE);
            } else {
                playerView.setVisibility(View.GONE);
//                thumnail.setVisibility(View.VISIBLE);
                initViews();
                if (steps.get(currentPosition).getThumbnailURL() == null) {
//                    Picasso.get().load(R.drawable.ic_launcher_background).into(thumnail);
                } else {
//                    Picasso.get().load(steps.get(currentPosition).getThumbnailURL()).into(thumnail);
                }
            }

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

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        playerView.setLayoutParams(params);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {

            if (steps.get(currentPosition).getVideoURL() != null) {
                initilizePlayer();

            } else {
                playerView.setVisibility(View.GONE);
//                thumnail.setVisibility(View.VISIBLE);
                if (steps.get(currentPosition).getThumbnailURL() == null) {
//                    Picasso.get().load(R.drawable.ic_launcher_background).into(thumnail);
                } else {
//                    Picasso.get().load(steps.get(currentPosition).getThumbnailURL()).into(thumnail);
                }
            }


        }

    }

    //region Player initialisation
    private void initilizePlayer() {
        Steps step = steps.get(currentPosition);
        Uri mediaUri = Uri.parse(step.getVideoURL());
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());
        playerView.setPlayer(player);
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(
                Objects.requireNonNull(getContext()), Util.getUserAgent(getContext(), getResources().getResourceName(R.string.app_name)));
        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mediaUri);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
    }

    //endregion

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {

            releasePlayer();
        }
    }

    //region Release player
    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    //endregion


    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }

    }


    //region onsavedInstance Related
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_INSTANCE_POSITION, currentPosition);
        outState.putLong(SAVED_PLAYBACK_POSITION, playbackPosition);
        outState.putInt(SAVED_PLAYBACK_WINDOW, currentWindow);
        outState.putBoolean(SAVED_PLAY_WHEN_READY, playWhenReady);

    }

    //endregion
}
