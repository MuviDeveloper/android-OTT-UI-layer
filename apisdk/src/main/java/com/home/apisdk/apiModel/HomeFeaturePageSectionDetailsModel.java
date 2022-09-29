package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Attributes For HomePageAsynTask
 *
 * @author MUVI
 */

public class HomeFeaturePageSectionDetailsModel {

    String is_episode ="";
    String movie_stream_uniq_id ="";
    String movie_id ="";
    String movie_stream_id ="";
    String muvi_uniq_id ="";
    String ppv_plan_id ="";
    String permalink ="";
    String name ="";
    String story ="";
    String genre ="";
    String content_types_id ="";
    String is_converted ="";
    String is_media_published ="";
    String poster_url ="";
    String embeddedUrl ="";
    String seasonNo ="";
    String episodeNo = "";
    String totalSeasons = "";
    String season_permalink ="";
    String parent_poster_for_mobile_apps ="";
    String parent_title ="";

    public String getIs_livestream_enabled() {
        return is_livestream_enabled;
    }

    public void setIs_livestream_enabled(String is_livestream_enabled) {
        this.is_livestream_enabled = is_livestream_enabled;
    }

    String is_livestream_enabled="";

    public String getParent_poster_for_mobile_apps() {
        return parent_poster_for_mobile_apps;
    }

    public void setParent_poster_for_mobile_apps(String parent_poster_for_mobile_apps) {
        this.parent_poster_for_mobile_apps = parent_poster_for_mobile_apps;
    }

    public String getParent_title() {
        return parent_title;
    }

    public void setParent_title(String parent_title) {
        this.parent_title = parent_title;
    }

    public String getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(String video_duration) {
        this.video_duration = video_duration;
    }

    String video_duration ="";


    public String getSeason_permalink() {
        return season_permalink;
    }

    public void setSeason_permalink(String season_permalink) {
        this.season_permalink = season_permalink;
    }


    public String getIs_play_list() {
        return is_play_list;
    }

    public void setIs_play_list(String is_play_list) {
        this.is_play_list = is_play_list;
    }

    public String getPlay_list_type() {
        return play_list_type;
    }

    public void setPlay_list_type(String play_list_type) {
        this.play_list_type = play_list_type;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    //admin playlist
    String is_play_list;// 0, // New Key // The value will be "0" if it's not a playlist
    String play_list_type;// 0, // New key // The value will be "0" if it's not a playlist
    String list_id;


    public String getIs_media_published() {
        return is_media_published;
    }

    public void setIs_media_published(String is_media_published) {
        this.is_media_published = is_media_published;
    }


    public String getSeasonNo() {
        return seasonNo;
    }

    public void setSeasonNo(String seasonNo) {
        this.seasonNo = seasonNo;
    }

    public String getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(String episodeNo) {
        this.episodeNo = episodeNo;
    }

    public String getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(String totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public String getFreeContnet() {
        return freeContnet;
    }

    public void setFreeContnet(String freeContnet) {
        this.freeContnet = freeContnet;
    }

    String freeContnet ="";
    String banner ="";

    public String getIs_episode() {
        return is_episode;
    }

    public void setIs_episode(String is_episode) {
        this.is_episode = is_episode;
    }

    public String getMovie_stream_uniq_id() {
        return movie_stream_uniq_id;
    }

    public void setMovie_stream_uniq_id(String movie_stream_uniq_id) {
        this.movie_stream_uniq_id = movie_stream_uniq_id;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_stream_id() {
        return movie_stream_id;
    }

    public void setMovie_stream_id(String movie_stream_id) {
        this.movie_stream_id = movie_stream_id;
    }

    public String getMuvi_uniq_id() {
        return muvi_uniq_id;
    }

    public void setMuvi_uniq_id(String muvi_uniq_id) {
        this.muvi_uniq_id = muvi_uniq_id;
    }

    public String getPpv_plan_id() {
        return ppv_plan_id;
    }

    public void setPpv_plan_id(String ppv_plan_id) {
        this.ppv_plan_id = ppv_plan_id;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getContent_types_id() {
        return content_types_id;
    }

    public void setContent_types_id(String content_types_id) {
        this.content_types_id = content_types_id;
    }

    public String getIs_converted() {
        return is_converted;
    }

    public void setIs_converted(String is_converted) {
        this.is_converted = is_converted;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getEmbeddedUrl() {
        return embeddedUrl;
    }

    public void setEmbeddedUrl(String embeddedUrl) {
        this.embeddedUrl = embeddedUrl;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }






}
