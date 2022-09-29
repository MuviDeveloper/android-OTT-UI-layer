package com.home.vod.model;


import java.util.ArrayList;

public class WatchHistoryGridItem {


    String poster = "";
    String title = "";
    String genre = "";
    String permalink = "";
    String contentTypesId = "";
    String muviUniqueID = "";
    String streamUniqueID = "";
    String isEpisode = "";
    int seasonId;
    int isfreeContent;
    int isConverted;
    boolean castAndCrew = false;
    String censorRating="";
    String Stroy="";
    String video_duration = "";

    public ArrayList<String> getSeasonList() {
        return seasonList;
    }

    public void setSeasonList(ArrayList<String> seasonList) {
        this.seasonList = seasonList;
    }

    ArrayList<String> seasonList = new ArrayList<>();
    ArrayList<String> seasoninfo = new ArrayList<>();


    public ArrayList<String> getSeasoninfo() {
        return seasoninfo;
    }

    public void setSeasoninfo(ArrayList<String> seasoninfo) {
        this.seasoninfo = seasoninfo;
    }

    public WatchHistoryGridItem(String poster, String title, String genre, String permalink, String contentTypesId,
                                String muviUniqueID, String streamUniqueID, String isEpisode, int seasonId,
                                int isfreeContent, int isConverted, boolean castAndCrew, String censorRating, String Stroy,String video_duration, ArrayList<String> seasonList, ArrayList<String> seasoninfo) {
        super();
        this.poster = poster;
        this.title = title;
        this.genre = genre;
        this.permalink = permalink;
        this.contentTypesId = contentTypesId;
        this.muviUniqueID = muviUniqueID;
        this.streamUniqueID = streamUniqueID;
        this.isEpisode = isEpisode;
        this.seasonId = seasonId;
        this.isfreeContent = isfreeContent;
        this.isConverted = isConverted;
        this.castAndCrew = castAndCrew;
        this.censorRating = censorRating;
        this.Stroy = Stroy;
        this.video_duration = video_duration;
        this.seasonList = seasonList;
        this.seasoninfo = seasoninfo;

    }

    public boolean isCastAndCrew() {
        return castAndCrew;
    }

    public void setCastAndCrew(boolean castAndCrew) {
        this.castAndCrew = castAndCrew;
    }


    public String getCensorRating() {
        return censorRating;
    }

    public void setCensorRating(String censorRating) {
        this.censorRating = censorRating;
    }

    public String getStroy() {
        return Stroy;
    }

    public void setStroy(String stroy) {
        Stroy = stroy;
    }

    public String getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(String video_duration) {
        video_duration = video_duration;
    }

    public int getIsConverted() {
        return isConverted;
    }

    public void setIsConverted(int isConverted) {
        this.isConverted = isConverted;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getContentTypesId() {
        return contentTypesId;
    }

    public void setContentTypesId(String contentTypesId) {
        this.contentTypesId = contentTypesId;
    }


    public String getMuviUniqueID() {
        return muviUniqueID;
    }

    public void setMuviUniqueID(String muviUniqueID) {
        this.muviUniqueID = muviUniqueID;
    }

    public String getStreamUniqueID() {
        return streamUniqueID;
    }

    public void setStreamUniqueID(String streamUniqueID) {
        this.streamUniqueID = streamUniqueID;
    }

    public String getIsEpisode() {
        return isEpisode;
    }

    public void setIsEpisode(String isEpisode) {
        this.isEpisode = isEpisode;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getIsfreeContent() {
        return isfreeContent;
    }

    public void setIsfreeContent(int isfreeContent) {
        this.isfreeContent = isfreeContent;
    }


}
