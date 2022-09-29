package com.home.vod.model;

import java.io.Serializable;

/**
 * Created by User on 16-01-2017.
 */
public class MiniControllerModel implements Serializable {
    private String contentTiltle = "";
    private String seasonTitle = "";
    private String posterImage = "";
    private String castRouteName = "";


    public String getCastRouteName() { return castRouteName; }

    public void setCastRouteName(String castRouteName) { this.castRouteName = castRouteName; }

    public String getContentTiltle() {
        return contentTiltle;
    }

    public void setContentTiltle(String contentTiltle) {
        this.contentTiltle = contentTiltle;
    }

    public String getSeasonTitle() {
        return seasonTitle;
    }

    public void setSeasonTitle(String seasonTitle) {
        this.seasonTitle = seasonTitle;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

}
