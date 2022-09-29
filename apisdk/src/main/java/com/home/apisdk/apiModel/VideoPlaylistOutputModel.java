package com.home.apisdk.apiModel;

import java.io.Serializable;

public class VideoPlaylistOutputModel  implements Serializable {

    String movie_stream_id = "";
    String movie_id = "";
    String movie_uniqe_id = "";
    String movie_stream_uniq_id = "";
    String title = "";
    String story = "";
    String is_episode = "";
    String content_types_id = "";
    String episode_number = "";
    String series_number = "";
    String poster_url = "";
    String posterForTv = "";
    String permalink = "";
    String is_media_published = "";
    String content_publish_status = "";
    String is_converted = "";
    String is_secondary_converted = "";
    String parent_name = "";

    public String getIs_livestream_enabled() {
        return is_livestream_enabled;
    }

    public void setIs_livestream_enabled(String is_livestream_enabled) {
        this.is_livestream_enabled = is_livestream_enabled;
    }

    String is_livestream_enabled ="";

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getMovie_stream_id() {
        return movie_stream_id;
    }

    public void setMovie_stream_id(String movie_stream_id) {
        this.movie_stream_id = movie_stream_id;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_uniqe_id() {
        return movie_uniqe_id;
    }

    public void setMovie_uniqe_id(String movie_uniqe_id) {
        this.movie_uniqe_id = movie_uniqe_id;
    }

    public String getMovie_stream_uniq_id() {
        return movie_stream_uniq_id;
    }

    public void setMovie_stream_uniq_id(String movie_stream_uniq_id) {
        this.movie_stream_uniq_id = movie_stream_uniq_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getIs_episode() {
        return is_episode;
    }

    public void setIs_episode(String is_episode) {
        this.is_episode = is_episode;
    }

    public String getContent_types_id() {
        return content_types_id;
    }

    public void setContent_types_id(String content_types_id) {
        this.content_types_id = content_types_id;
    }

    public String getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(String episode_number) {
        this.episode_number = episode_number;
    }

    public String getSeries_number() {
        return series_number;
    }

    public void setSeries_number(String series_number) {
        this.series_number = series_number;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getPosterForTv() {
        return posterForTv;
    }

    public void setPosterForTv(String posterForTv) {
        this.posterForTv = posterForTv;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getIs_media_published() {
        return is_media_published;
    }

    public void setIs_media_published(String is_media_published) {
        this.is_media_published = is_media_published;
    }

    public String getContent_publish_status() {
        return content_publish_status;
    }

    public void setContent_publish_status(String content_publish_status) {
        this.content_publish_status = content_publish_status;
    }

    public String getIs_converted() {
        return is_converted;
    }

    public void setIs_converted(String is_converted) {
        this.is_converted = is_converted;
    }

    public String getIs_secondary_converted() {
        return is_secondary_converted;
    }

    public void setIs_secondary_converted(String is_secondary_converted) {
        this.is_secondary_converted = is_secondary_converted;
    }


}
