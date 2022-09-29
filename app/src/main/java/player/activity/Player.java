package player.activity;

import com.home.apisdk.apiModel.getMediaQueueOutput;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by MUVI on 15-05-2017.
 */

public class Player implements Serializable {

    boolean isLiveStream = false;
    boolean isLandScape = false;
    boolean waterMark = true;
    int playPos = 0;
    private String StoryColor;
    String movieUniqueId, streamUniqueId;
    String videoUrl = "";
    String content_title = "";
    String duration = "";
    String description = "";

    private int thumb_interval = 0;
    private long skipStartPosition = 0, skipEndPosition = 0;
    private String vastAdTag;


    public void setSkipIntro(long skipStartPosition, long skipEndPosition) {
        this.skipStartPosition = skipStartPosition;
        this.skipEndPosition = skipEndPosition;


    }

    public long getSkipStartPosition() {
        return skipStartPosition;
    }

    public long getSkipEndPosition() {
        return skipEndPosition;
    }


    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String rootUrl = "";
    String authTokenStr;
    String userId;
    String emailId;
    String movieId;
    String Episode_id;

    public String getParent_poster() {
        return parent_poster;
    }

    public void setParent_poster(String parent_poster) {
        this.parent_poster = parent_poster;
    }

    public String getParent_title() {
        return parent_title;
    }

    public void setParent_title(String parent_title) {
        this.parent_title = parent_title;
    }

    String parent_poster = "";
    String parent_title = "";
    int isFreeContent;
    String videoResolution;
    String licenseUrl, mpdVideoUrl, isOffline = "", isFree = "";
    int ContentTypesId = 1;
    boolean isThirdPartyPlayer = false;
    public static boolean player_description = true;
    public static boolean landscape = true;
    public static boolean hide_pause = false;
    public static boolean call_finish_at_onUserLeaveHint = true;
    public static String DefaultSubtitle = "Off";
    public static String VideoResolution = "Auto";
    public static final String loadIPUrl = "https://api.ipify.org/?format=json";
    boolean isTrailer = false;
    String downloadStatus = "0";
    private boolean isstreaming_restricted = false;
    //    int adNetworkId = 1;
//    String channel_id;
    boolean isEmail = false;
    boolean isDate = false;
    boolean isIp = false;
    boolean isMobile = false;
    private String videoTitle = "";
    private String episode_number = "";
    private String series_number = "";

    public int getLivestream_resume_time() {
        return livestream_resume_time;
    }

    public void setLivestream_resume_time(int livestream_resume_time) {
        this.livestream_resume_time = livestream_resume_time;
    }

    int livestream_resume_time;

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    private String parent_name = "";
    private String videoGenre = "";

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

    private String videoDuration = "";
    private String videoReleaseDate = "";
    private String videoStory = "";
    private boolean castCrew = false;
    private String censorRating = "";
    private String posterImageId = "https://d2gx0xinochgze.cloudfront.net/public/no-image-a.png";
    private getMediaQueueOutput.NextMediaInfo nextItem = null;
    private getMediaQueueOutput.NextMediaInfo prevItem = null;
    public boolean isPurchased = false;

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    public getMediaQueueOutput.NextMediaInfo getNextItem() {
        return nextItem;
    }

    public void setNextItem(getMediaQueueOutput.NextMediaInfo nextItem) {
        this.nextItem = nextItem;
    }

    public getMediaQueueOutput.NextMediaInfo getPrevItem() {
        return prevItem;
    }

    public void setPrevItem(getMediaQueueOutput.NextMediaInfo prevItem) {
        this.prevItem = prevItem;
    }

    public boolean isTrailer() {
        return isTrailer;
    }

    public void setTrailer(boolean trailer) {
        isTrailer = trailer;
    }


    public ArrayList<String> offline_url = new ArrayList<>();
    public ArrayList<String> offline_language = new ArrayList<>();
    public ArrayList<String> offline_language_code = new ArrayList<>();


    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setOfflineUrl(ArrayList<String> offline_url) {
        this.offline_url = offline_url;
    }

    public ArrayList<String> getOfflineUrl() {
        return offline_url;
    }

    public void setOfflineLanguage(ArrayList<String> offline_language) {
        this.offline_language = offline_language;
    }

    public ArrayList<String> getOfflineLanguage() {
        return offline_language;
    }


    public void setOffline_language_code(ArrayList<String> offline_language_code) {
        this.offline_language_code = offline_language_code;
    }

    public ArrayList<String> getOffline_language_code() {
        return offline_language_code;
    }



    public boolean isstreaming_restricted() {
        return isstreaming_restricted;
    }

    public void setIsstreaming_restricted(boolean isstreaming_restricted) {
        this.isstreaming_restricted = isstreaming_restricted;
    }


    public boolean isLandScape() {
        return isLandScape;
    }

    public void setLandScape(boolean landScape) {
        isLandScape = landScape;
    }

    public boolean isLiveStream() {
        return isLiveStream;
    }

    public void setLiveStream(boolean liveStream) {
        isLiveStream = liveStream;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

//    public int getAdNetworkId() {
//        return adNetworkId;
//    }

//    public void setAdNetworkId(int adNetworkId) {
//        this.adNetworkId = adNetworkId;
//    }

    public void useEmail(boolean isEmail) {
        this.isEmail = isEmail;
    }

    public void useIp(boolean isIp) {
        this.isIp = isIp;
    }

    public void useMobile(boolean isMobile) {
        this.isMobile = isMobile;
    }

    public void useDate(boolean isDate) {
        this.isDate = isDate;
    }

    public boolean getUseIpStatus() {
        return isIp;
    }

    public boolean getUseMbileStatus() {
        return isMobile;
    }

    public boolean getUseEmailStatus() {
        return isEmail;
    }

    public boolean getUseDateStatus() {
        return isDate;
    }

    String adDetails = "";

    public int getContentTypesId() {
        return ContentTypesId;
    }

    public void setContentTypesId(int contentTypesId) {
        ContentTypesId = contentTypesId;
    }

    public String getIsOffline() {
        return isOffline;
    }

    public void setIsOffline(String isOffline) {
        this.isOffline = isOffline;
    }

    public String getMpdVideoUrl() {
        return mpdVideoUrl;
    }

    public void setMpdVideoUrl(String mpdVideoUrl) {
        this.mpdVideoUrl = mpdVideoUrl;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getVideoResolution() {
        return videoResolution;
    }

    public void setVideoResolution(String videoResolution) {
        this.videoResolution = videoResolution;
    }

    public int getIsFreeContent() {
        return isFreeContent;
    }

    public void setIsFreeContent(int isFreeContent) {
        this.isFreeContent = isFreeContent;
    }

    public String getEpisode_id() {
        return Episode_id;
    }

    public void setEpisode_id(String episode_id) {
        Episode_id = episode_id;
    }


    ArrayList<String> SubTitleName = new ArrayList<>();

    public static boolean isPlayer_description() {
        return player_description;
    }

    public static void setPlayer_description(boolean player_description) {
        Player.player_description = player_description;
    }

    public ArrayList<String> getSubTitleName() {
        return SubTitleName;
    }

    public void setSubTitleName(ArrayList<String> subTitleName) {
        SubTitleName = subTitleName;
    }

    public ArrayList<String> getSubTitlePath() {
        return SubTitlePath;
    }

    public void setSubTitlePath(ArrayList<String> subTitlePath) {
        SubTitlePath = subTitlePath;
    }

    public ArrayList<String> getResolutionFormat() {
        return ResolutionFormat;
    }

    public void setResolutionFormat(ArrayList<String> resolutionFormat) {
        ResolutionFormat = resolutionFormat;
    }

    public ArrayList<String> getResolutionUrl() {
        return ResolutionUrl;
    }

    public void setResolutionUrl(ArrayList<String> resolutionUrl) {
        ResolutionUrl = resolutionUrl;
    }

    ArrayList<String> SubTitlePath = new ArrayList<>();
    ArrayList<String> FakeSubTitlePath = new ArrayList<>();
    ArrayList<String> ResolutionFormat = new ArrayList<>();
    ArrayList<String> ResolutionUrl = new ArrayList<>();


    public ArrayList<String> getSubTitleLanguage() {
        return SubTitleLanguage;
    }

    public void setSubTitleLanguage(ArrayList<String> subTitleLanguage) {
        SubTitleLanguage = subTitleLanguage;
    }

    ArrayList<String> SubTitleLanguage = new ArrayList<>();


    public ArrayList<String> getFakeSubTitlePath() {
        return FakeSubTitlePath;
    }

    public void setFakeSubTitlePath(ArrayList<String> fakeSubTitlePath) {
        FakeSubTitlePath = fakeSubTitlePath;
    }


    public boolean isThirdPartyPlayer() {
        return isThirdPartyPlayer;
    }

    public void setThirdPartyPlayer(boolean thirdPartyPlayer) {
        isThirdPartyPlayer = thirdPartyPlayer;
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }


    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }


    public String getAuthTokenStr() {
        return authTokenStr;
    }

    public void setAuthTokenStr(String authTokenStr) {
        this.authTokenStr = authTokenStr;
    }

    public boolean getWaterMark() {
        return waterMark;
    }

    public void setWaterMark(boolean waterMark) {
        this.waterMark = waterMark;
    }


    public int getPlayPos() {
        return playPos;
    }

    public void setPlayPos(int playPos) {
        this.playPos = playPos;
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


    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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

    public String getCensorRating() {
        return censorRating;
    }

    public void setCensorRating(String censorRating) {
        this.censorRating = censorRating;
    }

    public String getPosterImageId() {
        return posterImageId;
    }

    public void setPosterImageId(String posterImageId) {
        this.posterImageId = posterImageId;
    }


    public String getStoryColor() {
        return StoryColor;
    }

    public void setStoryColor(String storyColor) {
        StoryColor = storyColor;
    }

    public int getHas_video_card() {
        return has_video_card;
    }

    public void setHas_video_card(int has_video_card) {
        this.has_video_card = has_video_card;
    }

    private int has_video_card;

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

    private int no_of_row = 0;
    private int no_of_column = 0;
    private String total_no_thumbs = "";
    private String vtt_with_sprite = "";
    private String vtt_without_sprite = "";
    private String multiple_sprite_HostPrefix = "";
    private String sprite_image = "";


    public int getIs_playduration_enabled() {
        return is_playduration_enabled;
    }

    public void setIs_playduration_enabled(int is_playduration_enabled) {
        this.is_playduration_enabled = is_playduration_enabled;
    }

    private int is_playduration_enabled;
    //pallycon offline drm token
    private String offlineLicenseToken = "";

    public String getOfflineLicenseToken() {
        return offlineLicenseToken;
    }

    public void setOfflineLicenseToken(String offlineLicenseToken) {
        this.offlineLicenseToken = offlineLicenseToken;
    }

    public void setVastAdTag(String vastAdTag) {
        this.vastAdTag = vastAdTag;
    }

    public String getVastAdTag() {
        return vastAdTag;
    }
}
