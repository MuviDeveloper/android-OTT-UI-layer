package com.home.vod.model;


/**
 * Created by User on 02-07-2015.
 */
public class EpisodesListModel {

    private String episodeTitle;
    private String episodeNumber;
    private String episodeDescription;
    private String episodeTelecastOn;
    private String episodeThumbnailImageView;
    private String episodeVideoUrl;
    private String episodeStreamUniqueId;
    private String episodeImageButton;
    private String thirdPartyUrl;
    private String is_converted;
    private String is_media_published;

    public String getIs_converted() {
        return is_converted;
    }

    public void setIs_converted(String is_converted) {
        this.is_converted = is_converted;
    }

    private int episodeContentTypesId;
    private int downloadStatus;
    private int downloadPercentage;
    private int visibilityStatus;
    private int seasonWiseDownloadStatus;

    public String getIs_livestream_enabled() {
        return is_livestream_enabled;
    }

    public void setIs_livestream_enabled(String is_livestream_enabled) {
        this.is_livestream_enabled = is_livestream_enabled;
    }

    private  String is_livestream_enabled;

    public int getSeasonWiseDownloadStatus() {
        return seasonWiseDownloadStatus;
    }

    public void setSeasonWiseDownloadStatus(int seasonWiseDownloadStatus) {
        this.seasonWiseDownloadStatus = seasonWiseDownloadStatus;
    }

    public String getIs_media_published() {
        return is_media_published;
    }

    public void setIs_media_published(String is_media_published) {
        this.is_media_published = is_media_published;
    }

    public int getDownloadPercentage() {
        return downloadPercentage;
    }

    public void setDownloadPercentage(int downloadPercentage) {
        this.downloadPercentage = downloadPercentage;
    }

    public int getVisibilityStatus() {
        return visibilityStatus;
    }

    public void setVisibilityStatus(int visibilityStatus) {
        this.visibilityStatus = visibilityStatus;
    }

    public int getDownloadStatus() {
        return downloadStatus;
    }


    public String getIsFreeContnet() {
        return isFreeContnet;
    }

    public void setIsFreeContnet(String isFreeContnet) {
        this.isFreeContnet = isFreeContnet;
    }

    private String isFreeContnet="";


    public int getEpisodeContentTypesId() {
        return episodeContentTypesId;
    }

    public void setEpisodeContentTypesId(int episodeContentTypesId) {
        this.episodeContentTypesId = episodeContentTypesId;
    }

    public String getEpisodeThirdPartyUrl() {
        return thirdPartyUrl;
    }

    public void setEpisodeThirdPartyUrl(String thirdPartyUrl) {
        this.thirdPartyUrl = thirdPartyUrl;
    }


    public String getEpisodeImageButton() {
        return episodeImageButton;
    }

    public void setEpisodeImageButton(String episodeImageButton) {
        this.episodeImageButton = episodeImageButton;
    }



    public String getEpisodeStreamUniqueId() {
        return episodeStreamUniqueId;
    }

    public void setEpisodeStreamUniqueId(String episodeStreamUniqueId) {
        this.episodeStreamUniqueId = episodeStreamUniqueId;
    }

    public String getEpisodeMuviUniqueId() {
        return episodeMuviUniqueId;
    }

    public void setEpisodeMuviUniqueId(String episodeMuviUniqueId) {
        this.episodeMuviUniqueId = episodeMuviUniqueId;
    }

    private String episodeMuviUniqueId;
    public String getEpisodeSeriesNo() {
        return episodeSeriesNo;
    }

    public void setEpisodeSeriesNo(String episodeSeriesNo) {
        this.episodeSeriesNo = episodeSeriesNo;
    }

    private String episodeSeriesNo;
    private String episodeDuration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getEpisodeDuration() {
        return episodeDuration;
    }

    public void setEpisodeDuration(String episodeDuration) {
        this.episodeDuration = episodeDuration;
    }

    public EpisodesListModel(String episodeNumber, String episodeDescription, String episodeTelecastOn,
                             String episodeThumbnailImageView, String episodeTitle, String episodeVideoUrl,
                             String episodeSeriesNo, String episodeMuviUniqueId, String episodeStreamUniqueId,
                             String thirdPartyUrl, String episodeDuration,int episodeContenTTypesId ,String isFreeContnet,int download_Status,String is_converted, String is_media_published,String id ,String is_livestreamEnabled) {
        this.episodeNumber = episodeNumber;
        this.episodeDescription = episodeDescription;
        this.episodeTelecastOn = episodeTelecastOn;
        this.episodeThumbnailImageView = episodeThumbnailImageView;
        this.episodeTitle = episodeTitle;
        this.episodeVideoUrl = episodeVideoUrl;
        this.episodeSeriesNo = episodeSeriesNo;
        this.episodeMuviUniqueId = episodeMuviUniqueId;
        this.episodeStreamUniqueId = episodeStreamUniqueId;
        this.thirdPartyUrl = thirdPartyUrl;
        this.episodeDuration = episodeDuration;
        this.episodeContentTypesId=episodeContenTTypesId;
        this.isFreeContnet=isFreeContnet;
        this.downloadStatus=download_Status;
        this.is_converted=is_converted;
        this.is_media_published=is_media_published;
        this.id = id;
        this.is_livestream_enabled = is_livestreamEnabled;

    }
    public EpisodesListModel(){

    }
    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    public String getEpisodeVideoUrl() {
        return episodeVideoUrl;
    }

    public void setEpisodeVideoUrl(String episodeVideoUrl) {
        this.episodeVideoUrl = episodeVideoUrl;
    }

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(String episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getEpisodeDescription() {
        return episodeDescription;
    }

    public void setEpisodeDescription(String episodeDescription) {
        this.episodeDescription = episodeDescription;
    }

    public String getEpisodeTelecastOn() {
        return episodeTelecastOn;
    }

    public void setEpisodeTelecastOn(String episodeTelecastOn) {
        this.episodeTelecastOn = episodeTelecastOn;
    }

    public String getEpisodeThumbnailImageView() {
        return episodeThumbnailImageView;
    }

    public void setEpisodeThumbnailImageView(String episodeThumbnailImageView) {
        this.episodeThumbnailImageView = episodeThumbnailImageView;
    }
}
