package com.home.apisdk.apiModel;

/**
 * Created by MUVI on 19-Sep-18.
 */

public class UploadContentOutputModel {
    private String code;
    private String status;
    private String msg;
    private String feed_url;
    private String stream_url;
    private String stream_key;
    private String movie_id;
    private String uniq_id;
    private String movie_stream_uniq_id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFeed_url() {
        return feed_url;
    }

    public void setFeed_url(String feed_url) {
        this.feed_url = feed_url;
    }

    public String getStream_url() {
        return stream_url;
    }

    public void setStream_url(String stream_url) {
        this.stream_url = stream_url;
    }

    public String getStream_key() {
        return stream_key;
    }

    public void setStream_key(String stream_key) {
        this.stream_key = stream_key;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getUniq_id() {
        return uniq_id;
    }

    public void setUniq_id(String uniq_id) {
        this.uniq_id = uniq_id;
    }

    public String getMovie_stream_uniq_id() {
        return movie_stream_uniq_id;
    }

    public void setMovie_stream_uniq_id(String movie_stream_uniq_id) {
        this.movie_stream_uniq_id = movie_stream_uniq_id;
    }

    public String getUpload_video_path() {
        return upload_video_path;
    }

    public void setUpload_video_path(String upload_video_path) {
        this.upload_video_path = upload_video_path;
    }

    private String upload_video_path;

}
