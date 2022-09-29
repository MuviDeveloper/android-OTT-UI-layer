package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Output Attributes For GetFeatureContentAsynTask
 *
 * @author MUVI
 */
public class FeatureContentOutputModel {
    String genre = "";
    String name = "";
    String poster_url = "";
    String permalink = "";
    String content_types_id = "";
    String is_episode = "";



    public String getSeason_no() {
        return season_no;
    }

    public void setSeason_no(String season_no) {
        this.season_no = season_no;
    }

    public String getEpisode_no() {
        return episode_no;
    }

    public void setEpisode_no(String episode_no) {
        this.episode_no = episode_no;
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

    int is_converted;
    int is_ppv;
    int is_advance;
    String season_permalink ="";
    String season_no ="";
    String episode_no ="";

    public String getIs_livestream_enabled() {
        return is_livestream_enabled;
    }

    public void setIs_livestream_enabled(String is_livestream_enabled) {
        this.is_livestream_enabled = is_livestream_enabled;
    }

    String is_livestream_enabled ="";

    public String getMuvi_unique_id() {
        return muvi_unique_id;
    }

    public void setMuvi_unique_id(String muvi_unique_id) {
        this.muvi_unique_id = muvi_unique_id;
    }

    public String getStream_unique_id() {
        return stream_unique_id;
    }

    public void setStream_unique_id(String stream_unique_id) {
        this.stream_unique_id = stream_unique_id;
    }

    String muvi_unique_id ="";
    String stream_unique_id ="";
    String video_duration ="";
    String story ="";
    String parent_poster_for_mobile_apps ="";
    String parent_title ="";


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


    /**
     * This Method is use to Get the Is Advance Details
     *
     * @return is_advance
     */
    public int getIs_advance() {
        return is_advance;
    }

    /**
     * This Method is use to Set the Is Advance Details
     *
     * @param is_advance For Setting The Is Advance Details
     */
    public void setIs_advance(int is_advance) {
        this.is_advance = is_advance;
    }

    /**
     * This Method is use to Get the Is Converted Details
     *
     * @return is_converted
     */
    public int getIs_converted() {
        return is_converted;
    }

    /**
     * This Method is use to Set the Is Converted Details
     *
     * @param is_converted For Setting The Is Converted Details
     */
    public void setIs_converted(int is_converted) {
        this.is_converted = is_converted;
    }

    /**
     * This Method is use to Get the Is PPV Details
     *
     * @return is_ppv
     */
    public int getIs_ppv() {
        return is_ppv;
    }

    /**
     * This Method is use to Set the Is PPV Details
     *
     * @param is_ppv For Setting The Is PPV Details
     */
    public void setIs_ppv(int is_ppv) {
        this.is_ppv = is_ppv;
    }

    /**
     * This Method is use to Get the Genre
     *
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * This Method is use to Set the Genre
     *
     * @param genre For Setting The Genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * This Method is use to Get the Name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This Method is use to Set the Name
     *
     * @param name For Setting The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This Method is use to Get the Poste URL
     *
     * @return poster_url
     */
    public String getPoster_url() {
        return poster_url;
    }

    /**
     * This Method is use to Set the Poste URL
     *
     * @param poster_url For Setting the Poste URL
     */
    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    /**
     * This Method is use to Get the Permalink
     *
     * @return permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * This Method is use to Set the Permalink
     *
     * @param permalink For Setting the Permalink
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     * This Method is use to Get the Content Type ID
     *
     * @return content_types_id
     */
    public String getContent_types_id() {
        return content_types_id;
    }

    /**
     * This Method is use to Set the Content Type ID
     *
     * @param content_types_id For Setting the Content Type ID
     */
    public void setContent_types_id(String content_types_id) {
        this.content_types_id = content_types_id;
    }

    /**
     * This Method is use to Get the Is Episode Details
     *
     * @return is_episode
     */
    public String getIs_episode() {
        return is_episode;
    }

    /**
     * This Method is use to Set the Is Episode Details
     *
     * @param is_episode For Setting the Is Episode Details
     */
    public void setIs_episode(String is_episode) {
        this.is_episode = is_episode;
    }


}
