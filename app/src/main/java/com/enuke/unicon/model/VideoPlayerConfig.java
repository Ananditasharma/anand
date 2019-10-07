package com.enuke.unicon.model;

public class VideoPlayerConfig {

    //Minimum Video you want to buffer while Playing
    private int  MIN_BUFFER_DURATION = 3000;
    //Max Video you want to buffer during PlayBack
    private int MAX_BUFFER_DURATION = 5000;
    //Min Video you want to buffer before start Playing it
    private int MIN_PLAYBACK_START_BUFFER = 1500;
    //Min video You want to buffer when user resumes video
    private int MIN_PLAYBACK_RESUME_BUFFER = 5000;

    private String VIDEO_URL;


    public int getMIN_BUFFER_DURATION() {
        return MIN_BUFFER_DURATION;
    }

    public void setMIN_BUFFER_DURATION(int MIN_BUFFER_DURATION) {
        this.MIN_BUFFER_DURATION = MIN_BUFFER_DURATION;
    }

    public int getMAX_BUFFER_DURATION() {
        return MAX_BUFFER_DURATION;
    }

    public void setMAX_BUFFER_DURATION(int MAX_BUFFER_DURATION) {
        this.MAX_BUFFER_DURATION = MAX_BUFFER_DURATION;
    }

    public int getMIN_PLAYBACK_START_BUFFER() {
        return MIN_PLAYBACK_START_BUFFER;
    }

    public void setMIN_PLAYBACK_START_BUFFER(int MIN_PLAYBACK_START_BUFFER) {
        this.MIN_PLAYBACK_START_BUFFER = MIN_PLAYBACK_START_BUFFER;
    }

    public int getMIN_PLAYBACK_RESUME_BUFFER() {
        return MIN_PLAYBACK_RESUME_BUFFER;
    }

    public void setMIN_PLAYBACK_RESUME_BUFFER(int MIN_PLAYBACK_RESUME_BUFFER) {
        this.MIN_PLAYBACK_RESUME_BUFFER = MIN_PLAYBACK_RESUME_BUFFER;
    }

    public String getVIDEO_URL() {
        return VIDEO_URL;
    }

    public void setVIDEO_URL(String VIDEO_URL) {
        this.VIDEO_URL = VIDEO_URL;
    }

    public VideoPlayerConfig(int MIN_BUFFER_DURATION, int MAX_BUFFER_DURATION, int MIN_PLAYBACK_START_BUFFER, int MIN_PLAYBACK_RESUME_BUFFER, String VIDEO_URL) {
        this.MIN_BUFFER_DURATION = MIN_BUFFER_DURATION;
        this.MAX_BUFFER_DURATION = MAX_BUFFER_DURATION;
        this.MIN_PLAYBACK_START_BUFFER = MIN_PLAYBACK_START_BUFFER;
        this.MIN_PLAYBACK_RESUME_BUFFER = MIN_PLAYBACK_RESUME_BUFFER;
        this.VIDEO_URL = VIDEO_URL;
    }

    public VideoPlayerConfig() {
    }
}
