package com.pierfrancescosoffritti.youtubeplayer;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayerControls implements View.OnClickListener, YouTubePlayerFullScreenListener, YouTubePlayer.YouTubeListener {
    @NonNull private final YouTubePlayerView youTubePlayerView;
    @NonNull private final View controlsView;

    @NonNull private final View panel;

    @NonNull private final View controlsRoot;

    @NonNull private final TextView videoTitle;
    @NonNull private final TextView videoCurrentTime;
    @NonNull private final TextView videoDuration;

    @NonNull private final ImageView playButton;
    @NonNull private final ImageView youTubeButton;
    @NonNull private final ImageView fullScreenButton;

    @NonNull private final SeekBar seekBar;

    @NonNull private final View dropShadowTop;
    @NonNull private final View dropShadowBottom;

    private boolean isPlaying;
    private boolean isVisible;

    public PlayerControls(@NonNull YouTubePlayerView youTubePlayerView, @NonNull View controlsView) {
        isPlaying = false;
        isVisible = true;

        this.youTubePlayerView = youTubePlayerView;
        this.controlsView = controlsView;

        panel = controlsView.findViewById(R.id.panel);

        controlsRoot = controlsView.findViewById(R.id.controls_root);

        videoTitle = (TextView) controlsView.findViewById(R.id.video_title);
        videoCurrentTime = (TextView) controlsView.findViewById(R.id.video_current_time);
        videoDuration = (TextView) controlsView.findViewById(R.id.video_duration);

        playButton = (ImageView) controlsView.findViewById(R.id.play_button);
        youTubeButton = (ImageView) controlsView.findViewById(R.id.youtube_button);
        fullScreenButton = (ImageView) controlsView.findViewById(R.id.fullscreen_button);

        seekBar = (SeekBar) controlsView.findViewById(R.id.seek_bar);

        dropShadowTop = controlsView.findViewById(R.id.drop_shadow_top);
        dropShadowBottom = controlsView.findViewById(R.id.drop_shadow_bottom);

        panel.setOnClickListener(this);
        playButton.setOnClickListener(this);
        fullScreenButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == panel)
            fadeControls();
        else if(view == playButton)
            onPlayButtonPressed();
        else if(view == fullScreenButton)
            onFullScreenPressed();

    }

    private void onFullScreenPressed() {
        youTubePlayerView.toggleFullScreen();
    }

    private void onPlayButtonPressed() {
        isPlaying = !isPlaying;

        int img = isPlaying ? R.drawable.ic_play_36dp : R.drawable.ic_pause_36dp;

        playButton.setImageResource(img);
    }

    private void fadeControls() {
        final float finalAlpha = isVisible ? 0f : 1f;
        isVisible = !isVisible;
        controlsRoot.animate().alpha(finalAlpha).setDuration(300).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if(finalAlpha == 1f)
                    controlsRoot.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(finalAlpha == 0f)
                    controlsRoot.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).start();
    }

    @Override
    public void onYouTubePlayerEnterFullScreen() {
        fullScreenButton.setImageResource(R.drawable.ic_fullscreen_exit_24dp);
    }

    @Override
    public void onYouTubePlayerExitFullScreen() {
        fullScreenButton.setImageResource(R.drawable.ic_fullscreen_24dp);
    }

    @Override
    public void onReady(@NonNull YouTubePlayer youTubePlayer) {

    }

    @Override
    public void onStateChange(@YouTubePlayer.State.YouTubePlayerState int state, @NonNull YouTubePlayer youTubePlayer) {

    }

    @Override
    public void onPlaybackQualityChange(@YouTubePlayer.PlaybackQuality.Quality int playbackQuality, @NonNull YouTubePlayer youTubePlayer) {

    }

    @Override
    public void onPlaybackRateChange(double rate, @NonNull YouTubePlayer youTubePlayer) {

    }

    @Override
    public void onError(String arg, @NonNull YouTubePlayer youTubePlayer) {

    }

    @Override
    public void onApiChange(@NonNull YouTubePlayer youTubePlayer) {

    }

    @Override
    public void onCurrentSecond(float second, @NonNull YouTubePlayer youTubePlayer) {
        videoCurrentTime.setText(Utils.formatTime(second));
    }

    @Override
    public void onDuration(float duration, @NonNull YouTubePlayer youTubePlayer) {
        videoDuration.setText(Utils.formatTime(duration));
    }

    @Override
    public void onLog(String log, @NonNull YouTubePlayer youTubePlayer) {

    }
}
