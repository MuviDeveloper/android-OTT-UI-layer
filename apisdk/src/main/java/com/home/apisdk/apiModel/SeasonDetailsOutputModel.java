package com.home.apisdk.apiModel;

import java.util.ArrayList;

/**
 * This Model Class Holds All The Input Attributes For the AboutUsAsync
 *
 * @author MUVI
 */

public class SeasonDetailsOutputModel {
    String title = "";
    String description = "";
    String mobile_poster = "";
    String poster_for_tv = "";
    String mobile_banner = "";
    String tv_banner = "";
    String release_date = "";
    String censor_rating = "";
    String permalink = "";
    String has_trailer = "";
    String muvi_uniq_id = "";

    public int getContent_types_id() {
        return content_types_id;
    }

    public void setContent_types_id(int content_types_id) {
        this.content_types_id = content_types_id;
    }

    String id = "";
    String season_no = "";
    int content_types_id;
    String parent_title = "";
    String parent_muvi_unique_id = "";
    ArrayList<String> genre = new ArrayList<>();

    ArrayList<String> season_no_array = new ArrayList<>();
    ArrayList<String> season_title_array = new ArrayList<>();
    ArrayList<String> season_permalink_array = new ArrayList<>();


    public ArrayList<String> getSeason_permalink_array() {
        return season_permalink_array;
    }

    public void setSeason_permalink_array(ArrayList<String> season_permalink_array) {
        this.season_permalink_array = season_permalink_array;
    }

    public ArrayList<String> getSeason_no_array() {
        return season_no_array;
    }

    public void setSeason_no_array(ArrayList<String> season_no_array) {
        this.season_no_array = season_no_array;
    }

    public ArrayList<String> getSeason_title_array() {
        return season_title_array;
    }

    public void setSeason_title_array(ArrayList<String> season_title_array) {
        this.season_title_array = season_title_array;
    }


    public String getSeason_no() {
        return season_no;
    }

    public void setSeason_no(String season_no) {
        this.season_no = season_no;
    }

    public String getParent_title() {
        return parent_title;
    }

    public void setParent_title(String parent_title) {
        this.parent_title = parent_title;
    }

    public String getParent_muvi_unique_id() {
        return parent_muvi_unique_id;
    }

    public void setParent_muvi_unique_id(String parent_muvi_unique_id) {
        this.parent_muvi_unique_id = parent_muvi_unique_id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getHas_trailer() {
        return has_trailer;
    }

    public void setHas_trailer(String has_trailer) {
        this.has_trailer = has_trailer;
    }

    public String getMuvi_uniq_id() {
        return muvi_uniq_id;
    }

    public void setMuvi_uniq_id(String muvi_uniq_id) {
        this.muvi_uniq_id = muvi_uniq_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobile_poster() {
        return mobile_poster;
    }

    public void setMobile_poster(String mobile_poster) {
        this.mobile_poster = mobile_poster;
    }

    public String getPoster_for_tv() {
        return poster_for_tv;
    }

    public void setPoster_for_tv(String poster_for_tv) {
        this.poster_for_tv = poster_for_tv;
    }

    public String getMobile_banner() {
        return mobile_banner;
    }

    public void setMobile_banner(String mobile_banner) {
        this.mobile_banner = mobile_banner;
    }

    public String getTv_banner() {
        return tv_banner;
    }

    public void setTv_banner(String tv_banner) {
        this.tv_banner = tv_banner;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getCensor_rating() {
        return censor_rating;
    }

    public void setCensor_rating(String censor_rating) {
        this.censor_rating = censor_rating;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

}
