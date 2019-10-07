package com.enuke.unicon.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class VideoData implements Parcelable {
    Uri videoUri;
    String fileName;
    String fullPath ;
    String byteSize ;
    String mbSize  ;
    String kbSize ;
    Bitmap thumbnail;
    String videoTime;

    public VideoData(Uri videoUri, String fileName, String fullPath, String byteSize, String mbSize, String kbSize, Bitmap thumbnail,String videoTime) {
        this.videoUri = videoUri;
        this.fileName = fileName;
        this.fullPath = fullPath;
        this.byteSize = byteSize;
        this.mbSize = mbSize;
        this.kbSize = kbSize;
        this.thumbnail = thumbnail;
        this.videoTime = videoTime;
    }

    protected VideoData(Parcel in) {
        videoUri = in.readParcelable(Uri.class.getClassLoader());
        fileName = in.readString();
        fullPath = in.readString();
        byteSize = in.readString();
        mbSize = in.readString();
        kbSize = in.readString();
        thumbnail = in.readParcelable(Bitmap.class.getClassLoader());
        videoTime = in.readString();
    }

    public static final Creator<VideoData> CREATOR = new Creator<VideoData>() {
        @Override
        public VideoData createFromParcel(Parcel in) {
            return new VideoData(in);
        }

        @Override
        public VideoData[] newArray(int size) {
            return new VideoData[size];
        }
    };

    public Uri getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(Uri videoUri) {
        this.videoUri = videoUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getByteSize() {
        return byteSize;
    }

    public void setByteSize(String byteSize) {
        this.byteSize = byteSize;
    }

    public String getMbSize() {
        return mbSize;
    }

    public void setMbSize(String mbSize) {
        this.mbSize = mbSize;
    }

    public String getKbSize() {
        return kbSize;
    }

    public void setKbSize(String kbSize) {
        this.kbSize = kbSize;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(videoUri, flags);
        dest.writeString(fileName);
        dest.writeString(fullPath);
        dest.writeString(byteSize);
        dest.writeString(mbSize);
        dest.writeString(kbSize);
        dest.writeParcelable(thumbnail, flags);
        dest.writeString(videoTime);
    }
}
