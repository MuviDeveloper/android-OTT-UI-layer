package player.model;

import java.io.Serializable;

public class ContactModel1 implements Serializable {


    private String ID;
    private String poster;
    private String censor_rating;
    private String downloadURI;
    private String localURI;
    private String drmToken;
    private String licenceURL;

    public String getDownloadURI() {
        return downloadURI;
    }

    public void setDownloadURI(String downloadURI) {
        this.downloadURI = downloadURI;
    }

    public String getLocalURI() {
        return localURI;
    }

    public void setLocalURI(String localURI) {
        this.localURI = localURI;
    }

    public String getDrmToken() {
        return drmToken;
    }

    public void setDrmToken(String drmToken) {
        this.drmToken = drmToken;
    }

    public String getLicenceURL() {
        return licenceURL;
    }

    public void setLicenceURL(String licenceURL) {
        this.licenceURL = licenceURL;
    }

    public String getSreamId() {
        return SreamId;
    }

    public void setSreamId(String sreamId) {
        SreamId = sreamId;
    }

    public String getParent_poster() {
        return parent_poster;
    }

    public void setParent_poster(String parent_poster) {
        this.parent_poster = parent_poster;
    }

    public boolean cast_crew;
    private String release_date;
    private String story;
    private String parent_poster = "";

    public String getContent_types_id() {
        return content_types_id;
    }

    public void setContent_types_id(String content_types_id) {
        this.content_types_id = content_types_id;
    }

    public String getDownload_content_type() {
        return download_content_type;
    }

    public void setDownload_content_type(String download_content_type) {
        this.download_content_type = download_content_type;
    }

    private String content_types_id = "";

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;
    private String SreamId = "";

    public String getStreamId() {
        return SreamId;
    }

    public void setStreamId(String SreamId) {
        this.SreamId = SreamId;
    }

    public String getCensor_rating() {
        return censor_rating;
    }

    public void setCensor_rating(String censor_rating) {
        this.censor_rating = censor_rating;
    }

    public boolean isCast_crew() {
        return cast_crew;
    }

    public void setCast_crew(boolean cast_crew) {
        this.cast_crew = cast_crew;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getMuviid() {
        return muviid;
    }

    public void setMuviid(String muviid) {
        this.muviid = muviid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    private String token;
    private String path;
    private String contentid;
    private String genere;
    private String muviid;
    private String duration;

    private String download_content_type = "";

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

    private String episode_number = "";
    private String series_number = "";


    private String parent_title = "";

    public String getSeason_title() {
        return season_title;
    }

    public void setSeason_title(String season_title) {
        this.season_title = season_title;
    }

    private String download_file_size = "";
    private String season_title = "";

    public String getDownload_file_size() {
        return download_file_size;
    }

    public void setDownload_file_size(String download_file_size) {
        this.download_file_size = download_file_size;
    }

    public String getParent_title() {
        return parent_title;
    }

    public void setParent_title(String parent_title) {
        this.parent_title = parent_title;
    }

    public String getDownloadContentType() {
        return download_content_type;
    }

    public void setDownloadContentType(String download_content_type) {
        this.download_content_type = download_content_type;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    private String MUVIID;
    private String USERNAME;
    private String UniqueId;
    private int DOWNLOADID;

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    private int DSTATUS;
    private int progress;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getMUVIID() {
        return MUVIID;
    }

    public void setMUVIID(String MUVIID) {
        this.MUVIID = MUVIID;
    }

    public int getDSTATUS() {
        return DSTATUS;
    }

    public void setDSTATUS(int DSTATUS) {
        this.DSTATUS = DSTATUS;
    }

    public int getDOWNLOADID() {
        return DOWNLOADID;
    }

    public void setDOWNLOADID(int DOWNLOADID) {
        this.DOWNLOADID = DOWNLOADID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    @Override
    public String toString() {
        return "ID : " + ID + ",poster: " + poster + ",token:" + token + ",path:" + path + ",contentid:" + contentid + ",genere:" + genere + "," +
                ",duration:" + duration + ",censor_rating:" + censor_rating + ",cast_crew:" + cast_crew + ",release_date:" + release_date + "," +
                ",story:" + story + ",MUVIID" + MUVIID + ",USERNAME:" + USERNAME + ",UniqueId:" + UniqueId + ",DOWNLOADID:" + DOWNLOADID + "," +
                ",DSTATUS:" + DSTATUS + ",progress" + progress;
    }

    private String videoUrl;

    private String resolutionUrl;
    private int downloadPosition;

    public String getSingleFile() {
        return singleFile;
    }

    public void setSingleFile(String singleFile) {
        this.singleFile = singleFile;
    }

    private String singleFile;

    public String getDrmUrl() {
        return drmUrl;
    }

    public void setDrmUrl(String drmUrl) {
        this.drmUrl = drmUrl;
    }

    private String drmUrl;

    public int getIsDrm() {
        return isDrm;
    }

    public void setIsDrm(int isDrm) {
        this.isDrm = isDrm;
    }

    private int isDrm;
    private String mlvFile;
    private String ipAddress;
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }


    public String getMlvFile() {
        return mlvFile;
    }

    public void setMlvFile(String mlvFile) {
        this.mlvFile = mlvFile;
    }




    public int getDownloadPosition() {
        return downloadPosition;
    }

    public void setDownloadPosition(int downloadPosition) {
        this.downloadPosition = downloadPosition;
    }


    public String getResolutionUrl() {
        return resolutionUrl;
    }

    public void setResolutionUrl(String url) {
        resolutionUrl = url;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


}
