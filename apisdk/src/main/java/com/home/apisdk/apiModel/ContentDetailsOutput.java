package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Output Attributes For GetContentDetailsAsynTask
 *
 * @author Abhishek
 */

public class ContentDetailsOutput {
    String movieStreamUniqId = "";
    String muviUniqId = "";
    String censorRating = "";
    String releaseDate = "";
    String story = "";
    String videoDuration = "";
    String isFreeContent = "";
    String isAdvance = "";
    String actor = "";
    String director;
    String cast_detail ="";
    String trailerUrl;
    String movieUrlForTv;
    String movieUrl;
    String embeddedUrl;
    String banner;
    String poster;
    String comments;
    String epDetails;
    String name;

    int is_preorder;

    public int getIs_preorder() {
        return is_preorder;
    }

    public void setIs_preorder(int is_preorder) {
        this.is_preorder = is_preorder;
    }

    public int getIs_preorder_taken() {
        return is_preorder_taken;
    }

    public void setIs_preorder_taken(int is_preorder_taken) {
        this.is_preorder_taken = is_preorder_taken;
    }

    int is_preorder_taken;

    String preorder_success_msg;

    public String getPreorder_success_msg() {
        return preorder_success_msg;
    }

    public void setPreorder_success_msg(String preorder_success_msg) {
        this.preorder_success_msg = preorder_success_msg;
    }

    String permalink;
    int is_livestream_enabled;

    public int getIs_livestream_enabled() {
        return is_livestream_enabled;
    }

    public void setIs_livestream_enabled(int is_livestream_enabled) {
        this.is_livestream_enabled = is_livestream_enabled;
    }

    public String getContent_publish_date() {
        return content_publish_date;
    }

    public void setContent_publish_date(String content_publish_date) {
        this.content_publish_date = content_publish_date;
    }

    String content_publish_date;

    int trailer_status = 0;
    String genre = "";
    String rating = "";

    public String getTotal_series() {
        return total_series;
    }

    public void setTotal_series(String total_series) {
        this.total_series = total_series;
    }

    String review = "";
    String total_series = "";
    String id = "";
    String isFollowed = "";
    String is_media_published = "";
    //Additional parameters for AOD
    int purchaseStatus = 0;
    String isDownloadable;

    public String getSubtitles1() {
        return subtitles1;
    }

    public void setSubtitles1(String subtitles1) {
        this.subtitles1 = subtitles1;
    }

    public String getRent_duration() {
        return rent_duration;
    }

    public void setRent_duration(String rent_duration) {
        this.rent_duration = rent_duration;
    }

    public String getPurchase_type() {
        return purchase_type;
    }

    public void setPurchase_type(String purchase_type) {
        this.purchase_type = purchase_type;
    }

    public String getTitle_language() {
        return title_language;
    }

    public void setTitle_language(String title_language) {
        this.title_language = title_language;
    }

    //Additional parameters for bejod customer
    String subtitles1;
    String rent_duration;
    String purchase_type;
    String title_language;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    String artist;

    public String getMovieStreamId() {
        return movieStreamId;
    }

    public String getIs_media_published() {
        return is_media_published;
    }

    public void setIs_media_published(String is_media_published) {
        this.is_media_published = is_media_published;
    }
    public void setMovieStreamId(String movieStreamId) {
        this.movieStreamId = movieStreamId;
    }

    String movieStreamId;
    public String getMovie_stream_id() {
        return movie_stream_id;
    }

    public void setMovie_stream_id(String movie_stream_id) {
        this.movie_stream_id = movie_stream_id;
    }

    String movie_stream_id;

    public int getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(int purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public String getIsDownloadable() {
        return isDownloadable;
    }

    public void setIsDownloadable(String isDownloadable) {
        this.isDownloadable = isDownloadable;
    }

    public String getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(String isFollowed) {
        this.isFollowed = isFollowed;
    }


    public boolean isContentDownLoadingAvailable() {
        return contentDownLoadStatus;
    }

    public void setContentDownLoadStatus(boolean contentDownLoadStatus) {
        this.contentDownLoadStatus = contentDownLoadStatus;
    }

    boolean contentDownLoadStatus;

    public int getTrailer_status() {
        return trailer_status;
    }

    public void setTrailer_status(int trailer_status) {
        this.trailer_status = trailer_status;
    }


    /**
     * This Method is use to Get the Season
     *
     * @return season
     */

    public String[] getSeason() {
        return season;
    }

    /**
     * This Method is use to Set the Season
     *
     * @param season For Setting The Season
     */
    public void setSeason(String[] season) {
        this.season = season;
    }

    String[] season;

    /**
     * This Method is use to Get the ID
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * This Method is use to Set the ID
     *
     * @param id For Setting The ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This Method is use to Get the Favorite
     *
     * @return is_favorite
     */

    public int getIs_favorite() {
        return is_favorite;
    }

    /**
     * This Method is use to Set the Favorite
     *
     * @param is_favorite For Setting The Favorite
     */
    public void setIs_favorite(int is_favorite) {
        this.is_favorite = is_favorite;
    }

    int is_favorite = 0;

    /**
     * This Method is use to Get the Review
     *
     * @return review
     */
    public String getReview() {
        return review;
    }

    /**
     * This Method is use to Set the Review
     *
     * @param review For Setting The Review
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * This Method is use to Get the Rating
     *
     * @return rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * This Method is use to Set the Rating
     *
     * @param rating For Setting The Rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * This Method is use to Get the Episode
     *
     * @return isEpisode
     */

    public String getIsEpisode() {
        return isEpisode;
    }

    /**
     * This Method is use to Set the Episode
     *
     * @param isEpisode or Setting The Episode
     */
    public void setIsEpisode(String isEpisode) {
        this.isEpisode = isEpisode;
    }

    String isEpisode = "";
    int isConverted;

    /**
     * This Method is use to Get the Cast Detail
     *
     * @return castStr
     */
    public Boolean getCastStr() {
        return castStr;
    }

    /**
     * This Method is use to Set the Cast Detail
     *
     * @param castStr For Setting The Cast Detail
     */

    public void setCastStr(Boolean castStr) {
        this.castStr = castStr;
    }

    Boolean castStr;

    /**
     * This Method is use to Get the PPV
     *
     * @return isPpv
     */
    public int getIsPpv() {
        return isPpv;
    }

    /**
     * This Method is use to Get the Converted
     *
     * @return isConverted
     */
    public int getIsConverted() {
        return isConverted;
    }

    int isPpv;
    int isApv;

    /**
     * This Method is use to Set the Content Type ID
     *
     * @param contentTypesId For Setting The Content Type ID
     */
    public void setContentTypesId(int contentTypesId) {
        this.contentTypesId = contentTypesId;
    }

    /**
     * This Method is use to Get the Content Type ID
     *
     * @return contentTypesId
     */
    public int getContentTypesId() {
        return contentTypesId;
    }

    int contentTypesId;
    APVModel apvDetails;
    PPVModel ppvDetails;
    CurrencyModel currencyDetails;

    /**
     * This Method is use to Get the APV Details
     *
     * @return apvDetails
     */
    public APVModel getApvDetails() {
        return apvDetails;
    }

    /**
     * This Method is use to Set the APV Details
     *
     * @param apvDetails For Setting The APV Details
     */
    public void setApvDetails(APVModel apvDetails) {
        this.apvDetails = apvDetails;
    }

    /**
     * This Method is use to Get the PPV Details
     *
     * @return ppvDetails
     */
    public PPVModel getPpvDetails() {
        return ppvDetails;
    }

    /**
     * This Method is use to Set the PPV Details
     *
     * @param ppvDetails For Setting The PPV Details
     */
    public void setPpvDetails(PPVModel ppvDetails) {
        this.ppvDetails = ppvDetails;
    }

    /**
     * This Method is use to Get the Currency Details
     *
     * @return currencyDetails
     */
    public CurrencyModel getCurrencyDetails() {
        return currencyDetails;
    }

    /**
     * This Method is use to Set the Currency Details
     *
     * @param currencyDetails For Setting The Currency Details
     */
    public void setCurrencyDetails(CurrencyModel currencyDetails) {
        this.currencyDetails = currencyDetails;
    }

    /**
     * This Method is use to Set the Is Converted Details
     *
     * @param isConverted For Setting The Is Converted Details
     */
    public void setIsConverted(int isConverted) {
        this.isConverted = isConverted;
    }

    /**
     * This Method is use to Set the PPV Details
     *
     * @param isPpv For Setting The PPV Details
     */
    public void setIsPpv(int isPpv) {
        this.isPpv = isPpv;
    }

    /**
     * This Method is use to Get the APV Details
     *
     * @return isApv
     */
    public int getIsApv() {
        return isApv;
    }

    /**
     * This Method is use to Set the APV Details
     *
     * @param isApv For Setting The APV Details
     */
    public void setIsApv(int isApv) {
        this.isApv = isApv;
    }

    /**
     * This Method is use to Get the Movie Stream Unique ID
     *
     * @return movieStreamUniqId
     */
    public String getMovieStreamUniqId() {
        return movieStreamUniqId;
    }

    /**
     * This Method is use to Set the Movie Stream Unique ID
     *
     * @param movieStreamUniqId For Setting The Movie Unique ID
     */
    public void setMovieStreamUniqId(String movieStreamUniqId) {
        this.movieStreamUniqId = movieStreamUniqId;
    }

    /**
     * This Method is use to Get the Muvi Unique ID
     *
     * @return muviUniqId
     */
    public String getMuviUniqId() {
        return muviUniqId;
    }

    /**
     * This Method is use to Set the Muvi Unique ID
     *
     * @param muviUniqId For Setting The Muvi Unique ID
     */
    public void setMuviUniqId(String muviUniqId) {
        this.muviUniqId = muviUniqId;
    }

    /**
     * This Method is use to Get the Censor Rating
     *
     * @return censorRating
     */
    public String getCensorRating() {
        return censorRating;
    }

    /**
     * This Method is use to Set the Censor Rating
     *
     * @param censorRating For Setting The Censor Rating
     */
    public void setCensorRating(String censorRating) {
        this.censorRating = censorRating;
    }

    /**
     * This Method is use to Get the Release Date
     *
     * @return releaseDate
     */

    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * This Method is use to Set the Release Date
     *
     * @param releaseDate For Setting The Release Date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * This Method is use to Get the Story
     *
     * @return story
     */
    public String getStory() {
        return story;
    }

    /**
     * Method is use to Set the Story
     *
     * @param story For Setting The Story
     */
    public void setStory(String story) {
        this.story = story;
    }

    /**
     * Method is use to Get the Video Duration
     *
     * @return videoDuration
     */
    public String getVideoDuration() {
        return videoDuration;
    }

    /**
     * Method is use to Set the Video Duration
     *
     * @param videoDuration For Setting The Video Duration
     */

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    /**
     * Method is use to Get the Free Content
     *
     * @return isFreeContent
     */
    public String getIsFreeContent() {
        return isFreeContent;
    }

    /**
     * Method is use to Set the Free Content
     *
     * @param isFreeContent For Setting The Free Content
     */
    public void setIsFreeContent(String isFreeContent) {
        this.isFreeContent = isFreeContent;
    }

    /**
     * Method is use to Get the Is Advance Details
     *
     * @return isAdvance
     */
    public String getIsAdvance() {
        return isAdvance;
    }

    /**
     * Method is use to Set the Is Advance Details
     *
     * @param isAdvance For Setting The Is Advance Details
     */

    public void setIsAdvance(String isAdvance) {
        this.isAdvance = isAdvance;
    }

    /**
     * Method is use to Get the Actor
     *
     * @return actor
     */
    public String getActor() {
        return actor;
    }

    /**
     * Method is use to Set the Actor
     *
     * @param actor For Setting The Actor
     */
    public void setActor(String actor) {
        this.actor = actor;
    }

    /**
     * Method is use to Get the Director
     *
     * @return director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Method is use to Set the Director
     *
     * @param director For Setting The Director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Method is use to Get the Cast Detail
     *
     * @return cast_detail
     */
    public String getCast_detail() {
        return cast_detail;
    }

    /**
     * Method is use to Set the Cast Detail
     *
     * @param cast_detail For Setting The Cast Detail
     */
    public void setCast_detail(String cast_detail) {
        this.cast_detail = cast_detail;
    }

    /**
     * Method is use to Get the Trailer URL
     *
     * @return trailerUrl
     */
    public String getTrailerUrl() {
        return trailerUrl;
    }

    /**
     * Method is use to Set the Trailer URL
     *
     * @param trailerUrl For Setting The Trailer URL
     */
    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    /**
     * Method is use to Get the Movie URL For TV
     *
     * @return movieUrlForTv
     */
    public String getMovieUrlForTv() {
        return movieUrlForTv;
    }

    /**
     * Method is use to Set the Movie URL For TV
     *
     * @param movieUrlForTv For Setting The Movie URL For TV
     */
    public void setMovieUrlForTv(String movieUrlForTv) {
        this.movieUrlForTv = movieUrlForTv;
    }

    /**
     * Method is use to Get the Movie URL
     *
     * @return movieUrl
     */
    public String getMovieUrl() {
        return movieUrl;
    }

    /**
     * Method is use to Set the Movie URL
     *
     * @param movieUrl For Setting The Movie URL
     */
    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    /**
     * Method is use to Get the Embedded Url
     *
     * @return embeddedUrl
     */
    public String getEmbeddedUrl() {
        return embeddedUrl;
    }

    /**
     * Method is use to Set the Embedded Url
     *
     * @param embeddedUrl For Setting The Embedded Url
     */
    public void setEmbeddedUrl(String embeddedUrl) {
        this.embeddedUrl = embeddedUrl;
    }

    /**
     * Method is use to Get the Banner
     *
     * @return banner
     */
    public String getBanner() {
        return banner;
    }

    /**
     * Method is use to Set the Banner
     *
     * @param banner For Setting The Banner
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    /**
     * Method is use to Get the Poster
     *
     * @return poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Method is use to Set the Poster
     *
     * @param poster For Setting The Poster
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * Method is use to Get the Comments
     *
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Method is use to Set the Comments
     *
     * @param comments For Setting The Comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Method is use to Get the Episode Details
     *
     * @return epDetails
     */
    public String getEpDetails() {
        return epDetails;
    }

    /**
     * Method is use to Set the Episode Details
     *
     * @param epDetails For Setting The Episode Details
     */
    public void setEpDetails(String epDetails) {
        this.epDetails = epDetails;
    }

    /**
     * Method is use to Get the Name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method is use to Set the Name
     *
     * @param name For Setting The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method is use to Get the Permalink
     *
     * @return permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * Method is use to Set the Permalink
     *
     * @param permalink For Setting The Permalink
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     * Method is use to Get the Genre
     *
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Method is use to Set the Genre
     *
     * @param genre For Setting The Genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    int is_live;

    public int getIs_live() {
        return is_live;
    }

    public void setIs_live(int is_live) {
        this.is_live = is_live;
    }
}
