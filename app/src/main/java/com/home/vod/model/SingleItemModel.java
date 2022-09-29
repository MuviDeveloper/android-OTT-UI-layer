package com.home.vod.model;

/**
 * Created by Muvi on 12/15/2016.
 */

public class SingleItemModel {

    private String title = "";
    private String videoType = "";
    private String imageId = "";
    private String permalink = "";
    private int isConverted;
    private int is_media_published;
    private int isPPV;
    private int isAPV;
    private String movieUniqueId = "";
    private String movieStreamUniqueId = "";
    private String isEpisode = "";
    private String videoUrl = "";
    private String videoTypeId = "";
    private String movieGenre = "";
    private String play_list_type = "";
    private String is_play_list = "";
    private String list_id = "";
    private String poster_url = "";
    private String name = "";
    private String season_permalink = "";
    private String parent_poster_for_mobile_apps = "";
    private String parent_title = "";
    private String video_duration = "";
    private String story = "";


    public String getSeason_permalink() {
        return season_permalink;
    }

    public void setSeason_permalink(String season_permalink) {
        this.season_permalink = season_permalink;
    }


    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlay_list_type() {
        return play_list_type;
    }

    public void setPlay_list_type(String play_list_type) {
        this.play_list_type = play_list_type;
    }

    public String getIs_play_list() {
        return is_play_list;
    }

    public void setIs_play_list(String is_play_list) {
        this.is_play_list = is_play_list;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public int getIs_media_published() {
        return is_media_published;
    }

    public void setIs_media_published(int is_media_published) {
        this.is_media_published = is_media_published;
    }

    public String getSeasonNo() {
        return seasonNo;
    }

    public void setSeasonNo(String seasonNo) {
        this.seasonNo = seasonNo;
    }

    private String seasonNo;
    private String episodeNo;

    public String getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(String episodeNo) {
        this.episodeNo = episodeNo;
    }

    public int getIsAPV() {
        return isAPV;
    }

    public void setIsAPV(int isAPV) {
        this.isAPV = isAPV;
    }

    public int getIsPPV() {
        return isPPV;
    }

    public void setIsPPV(int isPPV) {
        this.isPPV = isPPV;
    }

    public int getIsConverted() {
        return isConverted;
    }

    public void setIsConverted(int isConverted) {
        this.isConverted = isConverted;
    }


    public String getMovieUniqueId() {
        return movieUniqueId;
    }

    public void setMovieUniqueId(String movieUniqueId) {
        this.movieUniqueId = movieUniqueId;
    }

    public String getMovieStreamUniqueId() {
        return movieStreamUniqueId;
    }

    public void setMovieStreamUniqueId(String movieStreamUniqueId) {
        this.movieStreamUniqueId = movieStreamUniqueId;
    }

    public String getIsEpisode() {
        return isEpisode;
    }

    public void setIsEpisode(String isEpisode) {
        this.isEpisode = isEpisode;
    }


    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


    public String getVideoTypeId() {
        return videoTypeId;
    }

    public void setVideoTypeId(String videoTypeId) {
        this.videoTypeId = videoTypeId;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }


    public String getVideoType() {

        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

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

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public SingleItemModel(String imageId, String title, String videoType, String videoTypeId, String movieGenre, String videoUrl, String permalink, String isEpisode, String seasonNo, String episodeNo, String movieUniqueId, String movieStreamUniqueId, int isConverted, int isPPV, int isAPV, int ismediapublish, String play_list_type,
                           String is_play_list, String list_id, String poster_url, String name, String season_permalink, String parent_poster_for_mobile_apps, String parent_title, String video_duration, String story) {
        super();
        this.imageId = imageId;
        this.title = title;
        this.videoType = videoType;
        this.videoTypeId = videoTypeId;
        this.movieGenre = movieGenre;
        this.videoUrl = videoUrl;
        this.permalink = permalink;
        this.isEpisode = isEpisode;
        this.movieUniqueId = movieUniqueId;
        this.movieStreamUniqueId = movieStreamUniqueId;
        this.isConverted = isConverted;
        this.isPPV = isPPV;
        this.isAPV = isAPV;
        this.seasonNo = seasonNo;
        this.episodeNo = episodeNo;
        this.is_media_published = ismediapublish;
        this.play_list_type = play_list_type;
        this.is_play_list = is_play_list;
        this.list_id = list_id;
        this.poster_url = poster_url;
        this.name = name;
        this.season_permalink = season_permalink;
        this.parent_poster_for_mobile_apps = parent_poster_for_mobile_apps;
        this.parent_title = parent_title;
        this.video_duration = video_duration;
        this.story = story;

    }
    public String getImage() {
        return imageId;
    }

    public void setImage(String imageId) {

        this.imageId = imageId;
    }
	/*public String getImage() {
		return imageId;
	}

	public void setImage(String imageId) {
		this.imageId = imageId;
	}*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
