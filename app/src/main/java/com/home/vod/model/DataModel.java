package com.home.vod.model;

/**
 * Created by Muvi on 2/10/2017.
 */
public class DataModel {

    String episode_series_no ="";
    String episode_title="";
    String episode_no ="";
    String is_converted ="";
    String is_free ="";
    String id ="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_free() {
        return is_free;
    }

    public void setIs_free(String is_free) {
        this.is_free = is_free;
    }

    public String getIs_converted() {
        return is_converted;
    }

    public void setIs_converted(String is_converted) {
        this.is_converted = is_converted;
    }

    public int getAdNetworkId() {
        return adNetworkId;
    }

    public void setAdNetworkId(int adNetworkId) {
        this.adNetworkId = adNetworkId;
    }

    int adNetworkId = 1;
    String channel_id;



    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    int midRoll = 0;
    int postRoll = 0;

    public String getAdDetails() {
        return adDetails;
    }

    public void setAdDetails(String adDetails) {
        this.adDetails = adDetails;
    }

    String adDetails="";
    public int getMidRoll() {
        return midRoll;
    }

    public void setMidRoll(int midRoll) {
        this.midRoll = midRoll;
    }

    public int getPostRoll() {
        return postRoll;
    }

    public void setPostRoll(int postRoll) {
        this.postRoll = postRoll;
    }

    public int getPreRoll() {
        return preRoll;
    }

    public void setPreRoll(int preRoll) {
        this.preRoll = preRoll;
    }

    int preRoll = 0;





    int playPos = 0;
    int isFreeContent;
    int isAPV;
    int isPPV;
    int isConverted;

    public int getIsSubscriptionBundle() {
        return isSubscriptionBundle;
    }

    public void setIsSubscriptionBundle(int isSubscriptionBundle) {
        this.isSubscriptionBundle = isSubscriptionBundle;
    }

    int isSubscriptionBundle;



    public int getIsPPVBundle() {
        return isPPVBundle;
    }

    public void setIsPPVBundle(int isPPVBundle) {
        this.isPPVBundle = isPPVBundle;
    }

    int isPPVBundle;

    public int getIsVoucher() {
        return isVoucher;
    }

    public void setIsVoucher(int isVoucher) {
        this.isVoucher = isVoucher;
    }

    int isVoucher;
    String season_id = "0";
    String episode_id = "0";
    String purchase_type = "show";
    String thirdPartyUrl = "";
    String movieUniqueId,streamUniqueId;
    String videoResolution = "";
    String videoUrl = "";
    private String videoTitle = "";
    private String videoGenre = "";
    private String videoDuration = "00:00:00";
    private String videoReleaseDate = "00/00/0000";
    private String videoStory = "";
    private boolean castCrew = false;
    private String censorRating = "";
    private String posterImageId = "https://d2gx0xinochgze.cloudfront.net/public/no-image-a.png";


    public String getEpisode_series_no() {
        return episode_series_no;
    }

    public void setEpisode_series_no(String episode_series_no) {
        this.episode_series_no = episode_series_no;
    }

    public String getEpisode_title() {
        return episode_title;
    }

    public void setEpisode_title(String episode_title) {
        this.episode_title = episode_title;
    }

    public String getEpisode_no() {
        return episode_no;
    }

    public void setEpisode_no(String episode_no) {
        this.episode_no = episode_no;
    }


    public String getPosterImageId() {
        return posterImageId;
    }

    public void setPosterImageId(String posterImageId) {
        this.posterImageId = posterImageId;
    }

    public int getPlayPos() {
        return playPos;
    }

    public void setPlayPos(int playPos) {
        this.playPos = playPos;
    }

    public String getCensorRating() {
        return censorRating;
    }

    public void setCensorRating(String censorRating) {
        this.censorRating = censorRating;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoGenre() {
        return videoGenre;
    }

    public void setVideoGenre(String videoGenre) {
        this.videoGenre = videoGenre;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoReleaseDate() {
        return videoReleaseDate;
    }

    public void setVideoReleaseDate(String videoReleaseDate) {
        this.videoReleaseDate = videoReleaseDate;
    }

    public String getVideoStory() {
        return videoStory;
    }

    public void setVideoStory(String videoStory) {
        this.videoStory = videoStory;
    }

    public boolean isCastCrew() {
        return castCrew;
    }

    public void setCastCrew(boolean castCrew) {
        this.castCrew = castCrew;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    int ContentTypesId = 1;

    public int getContentTypesId() {
        return ContentTypesId;
    }

    public void setContentTypesId(int contentTypesId) {
        ContentTypesId = contentTypesId;
    }

    public String getVideoResolution() {
        return videoResolution;
    }

    public void setVideoResolution(String videoResolution) {
        this.videoResolution = videoResolution;
    }

    public String getThirdPartyUrl() {
        return thirdPartyUrl;
    }

    public void setThirdPartyUrl(String thirdPartyUrl) {
        this.thirdPartyUrl = thirdPartyUrl;
    }


    public String getSeason_id() {
        return season_id;
    }

    public void setSeason_id(String season_id) {
        this.season_id = season_id;
    }

    public String getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(String episode_id) {
        this.episode_id = episode_id;
    }

    public String getPurchase_type() {
        return purchase_type;
    }

    public void setPurchase_type(String purchase_type) {
        this.purchase_type = purchase_type;
    }

    public String getMovieUniqueId() {
        return movieUniqueId;
    }

    public void setMovieUniqueId(String movieUniqueId) {
        this.movieUniqueId = movieUniqueId;
    }

    public String getStreamUniqueId() {
        return streamUniqueId;
    }

    public void setStreamUniqueId(String streamUniqueId) {
        this.streamUniqueId = streamUniqueId;
    }

    public int getIsFreeContent() {
        return isFreeContent;
    }

    public void setIsFreeContent(int isFreeContent) {
        this.isFreeContent = isFreeContent;
    }

    public void setIsAPV(int isAPV){ this.isAPV = isAPV; }
    public void setIsPPV(int isPPV){ this.isPPV = isPPV; }
    public void setIsConverted(int isConverted){ this.isConverted = isConverted; }

    public int getIsAPV(){return isAPV;}
    public int getIsPPV(){return isPPV;}
    public int getIsConverted(){return isConverted;}

}
