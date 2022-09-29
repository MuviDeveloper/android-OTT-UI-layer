package com.home.apisdk.apiModel;

public class getMediaQueueInput {
    String authToken;
    String stream_uniq_id;
    String user_id ="";
    String country;
    String lang_code;
    String content_info;
    String content_field;
    String episode_no;
    String season_no;
    String description;
    String duration;
    String state,city;
    String kids_mode = "0";
    public String getKids_mode() {
        return kids_mode;
    }

    public void setKids_mode(String kids_mode) {
        this.kids_mode = kids_mode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getStream_uniq_id() {
        return stream_uniq_id;
    }

    public void setStream_uniq_id(String stream_uniq_id) {
        this.stream_uniq_id = stream_uniq_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLang_code() {
        return lang_code;
    }

    public void setLang_code(String lang_code) {
        this.lang_code = lang_code;
    }

    public String getContent_info() {
        return content_info;
    }

    public void setContent_info(String content_info) {
        this.content_info = content_info;
    }

    public String getContent_field() {
        return content_field;
    }

    public void setContent_field(String content_field) {
        this.content_field = content_field;
    }

    public String getEpisode_no() {
        return episode_no;
    }

    public void setEpisode_no(String episode_no) {
        this.episode_no = episode_no;
    }

    public String getSeason_no() {
        return season_no;
    }

    public void setSeason_no(String season_no) {
        this.season_no = season_no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
