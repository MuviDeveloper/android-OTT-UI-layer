package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Attributes For HomePageAsynTask
 *
 * @author MUVI
 */

public class HomeFeaturePageBannerModel {

    private String image_path = "";
    private String banner_url = "";
    private String banner_permalink = "";
    private String banner_type = "";
    private String other_sub_type = "";
    private String web_link = "";
    private String banner_text = "";
    private String content_types_id = "";
    private String title = "";
    private String is_converted = "";
    private String video_on_banner = "";
    private String feed_url = "";

    public String getIs_livestream_enabled() {
        return is_livestream_enabled;
    }

    public void setIs_livestream_enabled(String is_livestream_enabled) {
        this.is_livestream_enabled = is_livestream_enabled;
    }

    private String is_livestream_enabled = "";

    public String getFeed_url() {
        return feed_url;
    }

    public void setFeed_url(String feed_url) {
        this.feed_url = feed_url;
    }

    public String getParent_title() {
        return parent_title;
    }

    public void setParent_title(String parent_title) {
        this.parent_title = parent_title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getMuvi_uniq_id() {
        return muvi_uniq_id;
    }

    public void setMuvi_uniq_id(String muvi_uniq_id) {
        this.muvi_uniq_id = muvi_uniq_id;
    }

    public String getMovie_stream_uniq_id() {
        return movie_stream_uniq_id;
    }

    public void setMovie_stream_uniq_id(String movie_stream_uniq_id) {
        this.movie_stream_uniq_id = movie_stream_uniq_id;
    }

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

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    private String parent_title = "";
    private String story = "";
    private String muvi_uniq_id = "";
    private String movie_stream_uniq_id = "";
    private String season_no = "";
    private String episode_no = "";
    private String video_duration = "";
    private String poster_url = "";

    private String button_type = "";
    private String button_text = "";

    public String getIs_converted() {
        return is_converted;
    }

    public void setIs_converted(String is_converted) {
        this.is_converted = is_converted;
    }

    public String getVideo_on_banner() {
        return video_on_banner;
    }

    public void setVideo_on_banner(String video_on_banner) {
        this.video_on_banner = video_on_banner;
    }

    public String getButton_type() {
        return button_type;
    }

    public void setButton_type(String button_type) {
        this.button_type = button_type;
    }

    public String getButton_text() {
        return button_text;
    }

    public void setButton_text(String button_text) {
        this.button_text = button_text;
    }

    public HomeFeaturePageBannerModel() {}

    public HomeFeaturePageBannerModel(String image_path, String banner_url, String banner_permalink, String banner_type, String other_sub_type, String web_link, String banner_text, String content_types_id, String title, String buttontype, String video_on_banner, String is_converted,String buttontext,
                                      String parent_title,String story, String muvi_uniq_id, String movie_stream_uniq_id,String season_no, String episode_no,String video_duration,String poster_url) {
        this.image_path = image_path;
        this.banner_url = banner_url;
        this.banner_permalink = banner_permalink;
        this.banner_type = banner_type;
        this.other_sub_type = other_sub_type;
        this.web_link = web_link;
        this.banner_text = banner_text;
        this.content_types_id = content_types_id;
        this.title = title;
        this.button_type = buttontype;
        this.video_on_banner = video_on_banner;
        this.is_converted = is_converted;
        this.button_text = buttontext;
        this.parent_title = parent_title;
        this.story = story;
        this.muvi_uniq_id = muvi_uniq_id;
        this.movie_stream_uniq_id = movie_stream_uniq_id;
        this.season_no = season_no;
        this.episode_no = episode_no;
        this.video_duration = video_duration;
        this.poster_url = poster_url;

    }

    /**
     * This Method is use to Get the Image Path
     *
     * @return image_path
     */

    public String getImage_path() {
        return image_path;
    }

    /**
     * This Method is use to Set the Image Path
     *
     * @param image_path For Setting The Image Path
     */

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    /**
     * This Method is use to Get the Banner URL
     *
     * @return banner_url
     */
    public String getBanner_url() {
        return banner_url;
    }

    /**
     * This Method is use to Set the Banner URL
     *
     * @param banner_url For Setting The Banner URL
     */

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getBanner_permalink() {
        return banner_permalink;
    }

    public void setBanner_permalink(String banner_permalink) {
        this.banner_permalink = banner_permalink;
    }

    public String getBanner_type() {
        return banner_type;
    }

    public void setBanner_type(String banner_type) {
        this.banner_type = banner_type;
    }

    public String getOther_sub_type() {
        return other_sub_type;
    }

    public void setOther_sub_type(String other_sub_type) {
        this.other_sub_type = other_sub_type;
    }

    public String getWeb_link() {
        return web_link;
    }

    public void setWeb_link(String web_link) {
        this.web_link = web_link;
    }

    public String getBanner_text() {
        return banner_text;
    }

    public void setBanner_text(String banner_text) {
        this.banner_text = banner_text;
    }

    public String getContent_types_id() {
        return content_types_id;
    }

    public void setContent_types_id(String content_types_id) {
        this.content_types_id = content_types_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
