package com.enuke.unicon.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.enuke.unicon.R;
import com.enuke.unicon.model.VideoData;
import com.enuke.unicon.model.VideoPlayerConfig;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class VideoPlayerActivity extends AppCompatActivity implements Player.EventListener, View.OnClickListener {

    private final int MINUTES = 60;
    private final int SECONDS = 60;
    private final int MILLI_SECONDS = 1000;

    private static final String TAG = "ExoPlayerActivity";

    private static final String KEY_VIDEO_URI = "video_uri";
    private static final String KEY_VIDEO_DATA = "videoData";

    PlayerView videoFullScreenPlayer;
    ProgressBar spinnerVideoDetails;
    TextView tvRemainingTime;
    TextView tvCurrentPositionTime;
    VideoPlayerConfig videoPlayerConfig;

    ImageView imgLandscape;
    ImageView imgPortrait;
    ImageView imgVolumeOn;
    ImageView imgMute;

    LinearLayout btnClose, btnOrientation, btnVolume;

    String videoUri;
    SimpleExoPlayer player;
    Handler mHandler = new Handler();
    float currentVolume;
    private VideoData videoData;
    private boolean vPortrait = true;
    private boolean vol = true;

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (player != null) {
                long current_position_value = (((player.getCurrentPosition() / 500) + 1) / 2);
                long duration_value = (((player.getDuration() / 500) + 1) / 2);
                String currentTime = getMyTime(current_position_value, 1);
                String remainingTime = getMyTime(duration_value - current_position_value, 2);
                tvCurrentPositionTime.setText(currentTime);
                tvRemainingTime.setText(remainingTime);
            }
            mHandler.postDelayed(this, 1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoPlayerConfig = new VideoPlayerConfig();
        setContentView(R.layout.activity_video_player);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            Log.d("ACTIONBAR_", e.toString());
        }

        findViews();
        getDataFromIntent();
        intializelisteners();
        setUp();
    }

    /***
     * this method initialize listeners
     */
    private void intializelisteners() {
        btnClose.setOnClickListener(this);
        btnVolume.setOnClickListener(this);
        btnOrientation.setOnClickListener(this);
    }

    /***
     * this method find views
     */
    private void findViews() {
        videoFullScreenPlayer = findViewById(R.id.videoFullScreenPlayer);
        spinnerVideoDetails = findViewById(R.id.spinnerVideoDetails);
        btnClose = findViewById(R.id.btnClose);
        btnVolume = findViewById(R.id.btnVolume);
        btnOrientation = findViewById(R.id.btnOrientation);
        tvRemainingTime = videoFullScreenPlayer.findViewById(R.id.exo_remaining);
        tvCurrentPositionTime = videoFullScreenPlayer.findViewById(R.id.exo_current_position);
        imgLandscape = findViewById(R.id.imgLandscape);
        imgPortrait = findViewById(R.id.imgPortrait);
        imgVolumeOn = findViewById(R.id.imgVolumeOn);
        imgMute = findViewById(R.id.imgMute);
    }

    /***
     * this method parse data from previous activity
     */
    private void getDataFromIntent() {
        if (getIntent().hasExtra(KEY_VIDEO_URI)) {
            videoUri = getIntent().getStringExtra(KEY_VIDEO_URI);
        }
        if (getIntent().hasExtra(KEY_VIDEO_DATA)) {
            videoData = getIntent().getParcelableExtra(KEY_VIDEO_DATA);
        }
        if (getIntent().hasExtra(KEY_VIDEO_URI)) {
            videoUri = getIntent().getStringExtra(KEY_VIDEO_URI);
        }
        if (getIntent().hasExtra(KEY_VIDEO_DATA)) {
            videoData = getIntent().getParcelableExtra(KEY_VIDEO_DATA);
        }
    }

    /***
     * this method mute and unmute player
     */
    private void changeVolume() {
        if (vol) {
            currentVolume = player.getVolume();
            player.setVolume(0f);
            imgVolumeOn.setVisibility(View.GONE);
            imgMute.setVisibility(View.VISIBLE);
            vol = !vol;
        } else {
            player.setVolume(currentVolume);
            imgVolumeOn.setVisibility(View.VISIBLE);
            imgMute.setVisibility(View.GONE);
            vol = !vol;
        }

    }

    /***
     * this method handle orientation
     */
    private void changeOrientation() {
        if (vPortrait) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            imgPortrait.setVisibility(View.VISIBLE);
            imgLandscape.setVisibility(View.GONE);
            setFullscreen(true);
            vPortrait = !vPortrait;
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            imgPortrait.setVisibility(View.GONE);
            imgLandscape.setVisibility(View.VISIBLE);
            setFullscreen(false);
            vPortrait = !vPortrait;
        }
    }

    /***
     * this method set up player
     */
    private void setUp() {
        initializePlayer();
        if (videoData == null) {
            return;
        }
        buildMediaSource(videoData.getVideoUri());
    }

    /**
     * *
     * this method initialize the player
     */
    private void initializePlayer() {
        if (player == null) {
            // 1. Create a default TrackSelector
            LoadControl loadControl = new DefaultLoadControl(
                    new DefaultAllocator(true, 16),
                    videoPlayerConfig.getMIN_BUFFER_DURATION(),
                    videoPlayerConfig.getMAX_BUFFER_DURATION(),
                    videoPlayerConfig.getMIN_PLAYBACK_START_BUFFER(),
                    videoPlayerConfig.getMIN_PLAYBACK_RESUME_BUFFER(), -1, true);

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);
            // 2. Create the player
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this), trackSelector, loadControl);
            videoFullScreenPlayer.setPlayer(player);
            tvCurrentPositionTime.setText(getMyTime(0L, 1));
            tvRemainingTime.setText(getMyTime(0L, 2));
        }


    }

    /***
     * this method get media from given uri
     */
    private void buildMediaSource(Uri mUri) {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mUri);
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
        player.addListener(this);
    }

    /***
     * this method called when player released
     */
    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    /***
     * this method called when player paused
     */
    private void pausePlayer() {
        if (player != null) {
            player.setPlayWhenReady(false);
            player.getPlaybackState();
        }
    }

    /***
     * this method called when player resumed
     */
    private void resumePlayer() {
        if (player != null) {
            player.setPlayWhenReady(true);
            player.getPlaybackState();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pausePlayer();
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        resumePlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        switch (playbackState) {

            case Player.STATE_BUFFERING:
                spinnerVideoDetails.setVisibility(View.VISIBLE);
                break;
            case Player.STATE_ENDED:
                // Activate the force enable
                break;
            case Player.STATE_IDLE:

                break;
            case Player.STATE_READY:
                spinnerVideoDetails.setVisibility(View.GONE);
                mHandler.removeCallbacks(mRunnable);
                mHandler.post(mRunnable);
                break;
            default:

                break;
        }
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    /***
     * this method parse time from given value
     * @param value
     * @param type
     * @return
     */
    private String getMyTime(long value, int type) {
        value = value * MILLI_SECONDS;
        String hourValue = "", minuteValue = "", secondValue = "", time = "";
        long combineTime = MINUTES * SECONDS * MILLI_SECONDS;
        long hour = value / combineTime;
        long min = (value - (hour * combineTime)) / (combineTime / SECONDS);
        long sec = (value - (hour * combineTime) - (min * (combineTime / SECONDS))) / MILLI_SECONDS;
        if (hour == 0)
            hourValue = "";
        else if (hour < 10)
            hourValue = "0" + hour + ":";
        else if (hour < 100)
            hourValue = hour + ":";

        if (min < 10)
            minuteValue = "0" + min + ":";
        else if (min < 100) minuteValue = min + ":";

        if (sec < 10)
            secondValue = "0" + sec;
        else if (sec < 100) secondValue = sec + "";

        if (type == 2) {
            time = "-" + hourValue + minuteValue + secondValue;
        } else time = hourValue + minuteValue + secondValue;
        return time;

    }

    /***
     * this method called to start this activity
     * @param context
     * @param videoUri
     * @param videoData
     * @return
     */
    public static Intent getStartIntent(Context context, String videoUri, VideoData videoData) {
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra(KEY_VIDEO_URI, videoUri);
        intent.putExtra(KEY_VIDEO_DATA, videoData);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClose:
                finish();
                break;

            case R.id.btnVolume:
                changeVolume();
                break;

            case R.id.btnOrientation:
                changeOrientation();
                break;
        }
    }

    /**
     * set full screen in landscape mode
     *
     * @param fullscreen
     */
    private void setFullscreen(boolean fullscreen) {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        if (fullscreen) {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else {
            attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        }
        getWindow().setAttributes(attrs);
    }

    @Override
    public void onBackPressed () {
//        super.onBackPressed();
        finish();
    }
}
