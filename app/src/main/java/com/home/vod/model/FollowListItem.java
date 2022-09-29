package com.home.vod.model;

public class FollowListItem {
    String id = "";
    String poster = "";
    String title = "";
    String no_of_followings = "";
    String is_followed = "";
    String follow_type = "";
    String unique_id = "";
    String description = "";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    String permalink = "";

    public String getFollow_type() {
        return follow_type;
    }

    public void setFollow_type(String follow_type) {
        this.follow_type = follow_type;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public FollowListItem(String id, String poster, String title, String no_of_followings, String is_followed, String follow_type, String unique_id ,String description,String permalink) {
        super();
        this.id = id;
        this.poster = poster;
        this.title = title;
        this.no_of_followings = no_of_followings;
        this.is_followed = is_followed;
        this.follow_type = follow_type;
        this.unique_id = unique_id;
        this.description = description;
        this.permalink = permalink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNo_of_followings() {
        return no_of_followings;
    }

    public void setNo_of_followings(String no_of_followings) {
        this.no_of_followings = no_of_followings;
    }

    public String getIs_followed() {
        return is_followed;
    }

    public void setIs_followed(String is_followed) {
        this.is_followed = is_followed;
    }
}
