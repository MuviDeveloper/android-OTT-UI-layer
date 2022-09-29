package com.home.apisdk.apiModel;

import java.util.ArrayList;

/**
 * This Model Class Holds All The Output Attributes For VideoDetailsAsynctask
 *
 * @author MUVI
 */

public class Video_Details_Output {

    String videoResolution = "";
    String videoUrl;
    String emed_url;
    private String vast_ad_tag;

    public int getNo_of_row() {
        return no_of_row;
    }

    public void setNo_of_row(int no_of_row) {
        this.no_of_row = no_of_row;
    }

    public int getNo_of_column() {
        return no_of_column;
    }

    public void setNo_of_column(int no_of_column) {
        this.no_of_column = no_of_column;
    }

    public String getTotal_no_thumbs() {
        return total_no_thumbs;
    }

    public void setTotal_no_thumbs(String total_no_thumbs) {
        this.total_no_thumbs = total_no_thumbs;
    }

    public int getThumb_interval() {
        return thumb_interval;
    }

    public void setThumb_interval(int thumb_interval) {
        this.thumb_interval = thumb_interval;
        this.thumb_interval = this.thumb_interval * 1000;

    }

    public String getVtt_with_sprite() {
        return vtt_with_sprite;
    }

    public void setVtt_with_sprite(String vtt_with_sprite) {
        this.vtt_with_sprite = vtt_with_sprite;
    }

    public String getVtt_without_sprite() {
        return vtt_without_sprite;
    }

    public void setVtt_without_sprite(String vtt_without_sprite) {
        this.vtt_without_sprite = vtt_without_sprite;
    }

    public String getMultiple_sprite_HostPrefix() {
        return multiple_sprite_HostPrefix;
    }

    public void setMultiple_sprite_HostPrefix(String multiple_sprite_HostPrefix) {
        this.multiple_sprite_HostPrefix = multiple_sprite_HostPrefix;
    }

    public String getSprite_image() {
        return sprite_image;
    }

    public void setSprite_image(String sprite_image) {
        this.sprite_image = sprite_image;
    }

    int no_of_row = 0;
    int no_of_column = 0;
    String total_no_thumbs = "";
    String vtt_with_sprite = "";
    String vtt_without_sprite = "";
    String multiple_sprite_HostPrefix = "";
    String sprite_image = "";


    String is_livestream_enabled;

    String livestream_resume_time;

    public String getIs_livestream_enabled() {
        return is_livestream_enabled;
    }

    public void setIs_livestream_enabled(String is_livestream_enabled) {
        this.is_livestream_enabled = is_livestream_enabled;
    }

    public String getLivestream_resume_time() {
        return livestream_resume_time;
    }

    public void setLivestream_resume_time(String livestream_resume_time) {
        this.livestream_resume_time = livestream_resume_time;
    }

    int thumb_interval = 0;
    private long skipIntroStartPosition = 0, skipIntroEndPosition = 0;

    public long getSkipIntroStartPosition() {
        return skipIntroStartPosition;
    }

    public void setSkipIntroStartPosition(long skipIntroStartPosition) {
        this.skipIntroStartPosition = skipIntroStartPosition;
        this.skipIntroStartPosition = this.skipIntroStartPosition * 1000;

    }

    public long getSkipIntroEndPosition() {
        return skipIntroEndPosition;
    }

    public void setSkipIntroEndPosition(long skipIntroEndPosition) {
        this.skipIntroEndPosition = skipIntroEndPosition;
        this.skipIntroEndPosition = this.skipIntroEndPosition * 1000;

    }


    public String getHas_video_card() {
        return has_video_card;
    }

    public void setHas_video_card(String has_video_card) {
        this.has_video_card = has_video_card;
    }

    String has_video_card;

    public boolean isWatermark_status() {
        return watermark_status;
    }

    public void setWatermark_status(boolean watermark_status) {
        this.watermark_status = watermark_status;
    }

    ArrayList<String> SubTitleName = new ArrayList<>();
    String studio_approved_url, licenseUrl;
    private String offlineLicenseURL = "";
    private String offlineLicenseToken = "";


    String is_offline;
    String is_free = "";

    public String getIs_free() {
        return is_free;
    }

    public void setIs_free(String is_free) {
        this.is_free = is_free;
    }

    String channel_id;
    int midRoll;

    boolean watermark_status = false;
    boolean watermark_email = false;
    boolean watermark_mobile = false;

    public boolean isWatermark_mobile() {
        return watermark_mobile;
    }

    public void setWatermark_mobile(boolean watermark_mobile) {
        this.watermark_mobile = watermark_mobile;
    }

    public boolean isWatermark_email() {
        return watermark_email;
    }

    public void setWatermark_email(boolean watermark_email) {
        this.watermark_email = watermark_email;
    }

    public boolean isWatermark_ip() {
        return watermark_ip;
    }

    public void setWatermark_ip(boolean watermark_ip) {
        this.watermark_ip = watermark_ip;
    }

    public boolean isWatermark_date() {
        return watermark_date;
    }

    public void setWatermark_date(boolean watermark_date) {
        this.watermark_date = watermark_date;
    }

    boolean watermark_ip = false;
    boolean watermark_date = false;

    /**
     * This Method is use to Get the Download Status
     *
     * @return download_status
     */

    public String getDownload_status() {
        return download_status;
    }

    /**
     * This Method is use to Set the Download Status
     *
     * @param download_status For Setting The Download Status
     */

    public void setDownload_status(String download_status) {
        this.download_status = download_status;
    }

    String download_status;

    /**
     * This Method is use to Get the Mid Roll
     *
     * @return midRoll
     */

    public int getMidRoll() {
        return midRoll;
    }

    /**
     * This Method is use to Set the Mid Roll
     *
     * @param midRoll For Setting The Mid Roll
     */

    public void setMidRoll(int midRoll) {
        this.midRoll = midRoll;
    }

    /**
     * This Method is use to Get the Post Roll
     *
     * @return postRoll
     */

    public int getPostRoll() {
        return postRoll;
    }

    /**
     * This Method is use to Set the Post Roll
     *
     * @param postRoll For Setting The Post Roll
     */

    public void setPostRoll(int postRoll) {
        this.postRoll = postRoll;
    }

    /**
     * This Method is use to Get the Pre Roll
     *
     * @return preRoll
     */

    public int getPreRoll() {
        return preRoll;
    }

    /**
     * This Method is use to Set the Pre Roll
     *
     * @param preRoll For Setting The Pre Roll
     */

    public void setPreRoll(int preRoll) {
        this.preRoll = preRoll;
    }

    int postRoll;
    int preRoll;

    /**
     * This Method is use to Get the Ad Network Id
     *
     * @return adNetworkId
     */

    public int getAdNetworkId() {
        return adNetworkId;
    }

    /**
     * This Method is use to Set the Ad Network Id
     *
     * @param adNetworkId For Setting The Ad Network Id
     */

    public void setAdNetworkId(int adNetworkId) {
        this.adNetworkId = adNetworkId;
    }

    int adNetworkId = 1;
    String no_streaming_device;

    /**
     * This Method is use to Get the No Streaming Device
     *
     * @return no_streaming_device
     */

    public String getNo_streaming_device() {
        return no_streaming_device;
    }

    /**
     * This Method is use to Set the No Streaming Device
     *
     * @param no_streaming_device For Setting The No Streaming Device
     */

    public void setNo_streaming_device(String no_streaming_device) {
        this.no_streaming_device = no_streaming_device;
    }

    /**
     * This Method is use to Get the Number Of Views
     *
     * @return no_of_views
     */

    public String getNo_of_views() {
        return no_of_views;
    }

    /**
     * This Method is use to Set the Number Of Views
     *
     * @param no_of_views For Setting The Number Of Views
     */

    public void setNo_of_views(String no_of_views) {
        this.no_of_views = no_of_views;
    }

    /**
     * This Method is use to Get the Trailer Third Party URL
     *
     * @return trailerThirdpartyUrl
     */

    public String getTrailerThirdpartyUrl() {
        return trailerThirdpartyUrl;
    }

    /**
     * This Method is use to Set the Trailer Third Party URL
     *
     * @param trailerThirdpartyUrl For Setting The Trailer Third Party URL
     */

    public void setTrailerThirdpartyUrl(String trailerThirdpartyUrl) {
        this.trailerThirdpartyUrl = trailerThirdpartyUrl;
    }

    /**
     * This Method is use to Get the Embedded Trailer URL
     *
     * @return embedTrailerUrl
     */

    public String getEmbedTrailerUrl() {
        return embedTrailerUrl;
    }

    /**
     * This Method is use to Set the Embedded Trailer URL
     *
     * @param embedTrailerUrl For Setting The Embedded URL
     */

    public void setEmbedTrailerUrl(String embedTrailerUrl) {
        this.embedTrailerUrl = embedTrailerUrl;
    }

    /**
     * This Method is use to Get the Trailer URL
     *
     * @return trailerUrl
     */

    public String getTrailerUrl() {
        return trailerUrl;
    }

    /**
     * This Method is use to Set the Trailer URL
     *
     * @param trailerUrl For Setting The Trailer URL
     */

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    String no_of_views;
    String trailerThirdpartyUrl;
    String embedTrailerUrl;
    String trailerUrl;

    /**
     * This Method is use to Get the Channel Id
     *
     * @return channel_id
     */

    public String getChannel_id() {
        return channel_id;
    }

    /**
     * This Method is use to Set the Channel Id
     *
     * @param channel_id For Setting The Channel Id
     */

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    /**
     * This Method is use to Get the Ad Details
     *
     * @return adDetails
     */

    public String getAdDetails() {
        return adDetails;
    }

    /**
     * This Method is use to Set the Ad Details
     *
     * @param adDetails For Setting The Ad Details
     */

    public void setAdDetails(String adDetails) {
        this.adDetails = adDetails;
    }

    /**
     * This Method is use to Get the Streaming Restriction
     *
     * @return streaming_restriction
     */

    public String getStreaming_restriction() {
        return streaming_restriction;
    }

    /**
     * This Method is use to Set the Streaming Restriction
     *
     * @param streaming_restriction For Setting The Streaming Restriction
     */

    public void setStreaming_restriction(String streaming_restriction) {
        this.streaming_restriction = streaming_restriction;
    }


    String adDetails = "";
    String streaming_restriction = "";

    /**
     * This Method is use to Get the Offline Details
     *
     * @return is_offline
     */

    public String getIs_offline() {
        return is_offline;
    }

    /**
     * This Method is use to Set the Offline Details
     *
     * @param is_offline For Setting The Offline Details
     */

    public void setIs_offline(String is_offline) {
        this.is_offline = is_offline;
    }

    /**
     * This Method is use to Get the Studio Approved URL
     *
     * @return studio_approved_url
     */

    public String getStudio_approved_url() {
        return studio_approved_url;
    }

    /**
     * This Method is use to Set the Studio Approved URL
     *
     * @param studio_approved_url For Setting The Studio Approved URL
     */

    public void setStudio_approved_url(String studio_approved_url) {
        this.studio_approved_url = studio_approved_url;
    }

    /**
     * This Method is use to Get the License URL
     *
     * @return licenseUrl
     */

    public String getLicenseUrl() {
        return licenseUrl;
    }

    /**
     * This Method is use to Set the License URL
     *
     * @param licenseUrl For Setting The License URL
     */

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    /**
     * This Method is use to Get the Subtitle Name
     *
     * @return SubTitleName
     */

    public ArrayList<String> getSubTitleName() {
        return SubTitleName;
    }

    /**
     * This Method is use to Set the Subtitle Name
     *
     * @param subTitleName For Setting The Subtitle Name
     */

    public void setSubTitleName(ArrayList<String> subTitleName) {
        SubTitleName = subTitleName;
    }

    /**
     * This Method is use to Get the Subtitle Path
     *
     * @return SubTitlePath
     */

    public ArrayList<String> getSubTitlePath() {
        return SubTitlePath;
    }

    /**
     * This Method is use to Set the Subtitle Path
     *
     * @param subTitlePath For Setting The Subtitle Path
     */

    public void setSubTitlePath(ArrayList<String> subTitlePath) {
        SubTitlePath = subTitlePath;
    }

    /**
     * This Method is use to Get the Fake Subtitle Path
     *
     * @return FakeSubTitlePath
     */

    public ArrayList<String> getFakeSubTitlePath() {
        return FakeSubTitlePath;
    }

    /**
     * This Method is use to Set the Fake Subtitle Path
     *
     * @param fakeSubTitlePath For Setting The Fake Subtitle Path
     */

    public void setFakeSubTitlePath(ArrayList<String> fakeSubTitlePath) {
        FakeSubTitlePath = fakeSubTitlePath;
    }

    /**
     * This Method is use to Get the Resolution Format
     *
     * @return ResolutionFormat
     */

    public ArrayList<String> getResolutionFormat() {
        return ResolutionFormat;
    }

    /**
     * This Method is use to Set the Resolution Format
     *
     * @param resolutionFormat For Setting The Resolution Format
     */

    public void setResolutionFormat(ArrayList<String> resolutionFormat) {
        ResolutionFormat = resolutionFormat;
    }

    /**
     * This Method is use to Get the Resolution URL
     *
     * @return ResolutionUrl
     */

    public ArrayList<String> getResolutionUrl() {
        return ResolutionUrl;
    }

    /**
     * This Method is use to Set the Resolution URL
     *
     * @param resolutionUrl For Setting The Resolution URL
     */

    public void setResolutionUrl(ArrayList<String> resolutionUrl) {
        ResolutionUrl = resolutionUrl;
    }

    ArrayList<String> SubTitlePath = new ArrayList<>();
    ArrayList<String> FakeSubTitlePath = new ArrayList<>();
    ArrayList<String> ResolutionFormat = new ArrayList<>();
    ArrayList<String> ResolutionUrl = new ArrayList<>();
    ArrayList<String> offlineUrl = new ArrayList<>();
    ArrayList<String> offlineLanguage = new ArrayList<>();
    ArrayList<String> SubTitleLanguage = new ArrayList<>();

    /**
     * This Method is use to Get the Offline URL
     *
     * @return offlineUrl
     */

    public ArrayList<String> getOfflineUrl() {
        return offlineUrl;
    }

    /**
     * This Method is use to Set the Offline URL
     *
     * @param offlineUrl For Setting The Offline URL
     */

    public void setOfflineUrl(ArrayList<String> offlineUrl) {
        this.offlineUrl = offlineUrl;
    }

    /**
     * This Method is use to Get the Offline Language
     *
     * @return offlineLanguage
     */

    public ArrayList<String> getOfflineLanguage() {
        return offlineLanguage;
    }

    /**
     * This Method is use to Set the Offline Language
     *
     * @param offlineLanguage For Setting The Offline Language
     */

    public void setOfflineLanguage(ArrayList<String> offlineLanguage) {
        this.offlineLanguage = offlineLanguage;
    }

    /**
     * This Method is use to Get the Subtitle Language
     *
     * @return SubTitleLanguage
     */

    public ArrayList<String> getSubTitleLanguage() {
        return SubTitleLanguage;
    }

    /**
     * This Method is use to Set the Subtitle Language
     *
     * @param subTitleLanguage For Setting The Subtitle Language
     */

    public void setSubTitleLanguage(ArrayList<String> subTitleLanguage) {
        SubTitleLanguage = subTitleLanguage;
    }

    /**
     * This Method is use to Get the Third Party URL
     *
     * @return thirdparty_url
     */

    public String getThirdparty_url() {
        return thirdparty_url;
    }

    /**
     * This Method is use to Set the Third Party URL
     *
     * @param thirdparty_url For Setting The Third Party URL
     */

    public void setThirdparty_url(String thirdparty_url) {
        this.thirdparty_url = thirdparty_url;
    }

    /**
     * This Method is use to Get the Played Length
     *
     * @return played_length
     */

    public String getPlayed_length() {
        return played_length;
    }

    /**
     * This Method is use to Set the Played Length
     *
     * @param played_length For Setting The Played Length
     */

    public void setPlayed_length(String played_length) {
        this.played_length = played_length;
    }

    String thirdparty_url;
    String played_length;

    /**
     * This Method is use to Set the Video Resolution
     *
     * @param videoResolution For Setting The Video Resolution
     */

    public void setVideoResolution(String videoResolution) {

        this.videoResolution = videoResolution;
    }

    /**
     * This Method is use to Set the Video Resolution
     *
     * @return videoResolution
     */

    public String getVideoResolution() {
        return videoResolution;
    }

    /**
     * This Method is use to Set the Video URL
     *
     * @param videoUrl For Setting The Video URL
     */

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * This Method is use to Get the Video URL
     *
     * @return videoUrl
     */

    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * This Method is use to Set the Embedded URL
     *
     * @param emed_url For Setting The Embedded URL
     */

    public void setEmed_url(String emed_url) {
        this.emed_url = emed_url;
    }

    /**
     * This Method is use to Get the Embedded URL
     *
     * @return emed_url
     */

    public String getEmed_url() {
        return emed_url;
    }

    public String getOfflineLicenseURL() {
        return offlineLicenseURL;
    }

    public void setOfflineLicenseURL(String offlineLicenseURL) {
        this.offlineLicenseURL = offlineLicenseURL;
    }

    public String getOfflineLicenseToken() {
        return offlineLicenseToken;
    }

    public void setOfflineLicenseToken(String offlineLicenseToken) {
        this.offlineLicenseToken = offlineLicenseToken;
    }

    public void setVastAdTag(String vast_tag_fhd) {
        this.vast_ad_tag = vast_tag_fhd;
    }

    public String getVast_ad_tag() {
        return vast_ad_tag;
    }
}
