package com.home.vod.model;

/**
 * Created by MUVI on 31-Dec-18.
 */

public class MultipartDownloadModel {
    private int percentage;
    private int downloadStatus;


    public long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(long downloadId) {
        this.downloadId = downloadId;
    }

    private long downloadId;

    public String getStreamUniqueId() {
        return streamUniqueId;
    }

    public void setStreamUniqueId(String streamUniqueId) {
        this.streamUniqueId = streamUniqueId;
    }

    private String streamUniqueId;

    public int getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(int downloadStatus) {
        this.downloadStatus = downloadStatus;
    }



    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }


}
