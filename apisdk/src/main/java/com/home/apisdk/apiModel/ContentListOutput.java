package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Output Attributes For GetContentListAsynTask
 *
 * @author Abhishek
 */

public class ContentListOutput {
    String movieId = "";
    String permalink = "";
    String name = "";
    String story = "";
    String releaseDate = "";
    String contentTypesId = "";
    String posterUrl = "";
    String bannerUrl = "";
    String categories = "";
    String genre = "";
    String movieUniqueid = "";
    String fullMovie = "";
    String languageCode = "";
    String language = "";
    int contentPublishStatus;
    String poster_playlist = "";
    String list_id = "";
    String list_name = "";
    String type = "";
    String movieStreamUniqueId = "";
    String isEpisodeStr;
    String video_duration = ""; //added for nike customer
    int isConverted, isPPV, isAPV;

    public String getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(String video_duration) {
        this.video_duration = video_duration;
    }


    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster_playlist() {
        return poster_playlist;
    }

    public void setPoster_playlist(String poster_playlist) {
        this.poster_playlist = poster_playlist;
    }

    public int getContentPublishStatus() {
        return contentPublishStatus;
    }

    public void setContentPublishStatus(int contentPublishStatus) {
        this.contentPublishStatus = contentPublishStatus;
    }



    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFullMovie() {
        return fullMovie;
    }

    public void setFullMovie(String fullMovie) {
        this.fullMovie = fullMovie;
    }

    public String getMovieStreamUniqueId() {
        return movieStreamUniqueId;
    }

    public void setMovieStreamUniqueId(String movieStreamUniqueId) {
        this.movieStreamUniqueId = movieStreamUniqueId;
    }



    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }


    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }


    public String getMovieUniqueid() {
        return movieUniqueid;
    }

    public void setMovieUniqueid(String movieUniqueid) {
        this.movieUniqueid = movieUniqueid;
    }


    /**
     * This Method is use to Get the Is Episode Details
     *
     * @return isEpisodeStr
     */
    public String getIsEpisodeStr() {
        return isEpisodeStr;
    }

    /**
     * This Method is use to Set the Is Episode Details
     *
     * @param isEpisodeStr For Setting The Is Episode Details
     */
    public void setIsEpisodeStr(String isEpisodeStr) {
        this.isEpisodeStr = isEpisodeStr;
    }


    /**
     * This Method is use to Get the Is PPV Details
     *
     * @return isPPV
     */
    public int getIsPPV() {
        return isPPV;
    }

    /**
     * This Method is use to Set the Is PPV Details
     *
     * @param isPPV For Setting The Is PPV Details
     */
    public void setIsPPV(int isPPV) {
        this.isPPV = isPPV;
    }

    /**
     * This Method is use to Get the Is APV Details
     *
     * @return isAPV
     */
    public int getIsAPV() {
        return isAPV;
    }

    /**
     * This Method is use to Set the Is APV Details
     *
     * @param isAPV For Setting The Is APV Details
     */
    public void setIsAPV(int isAPV) {
        this.isAPV = isAPV;
    }

    /**
     * This Method is use to Get the Movie ID
     *
     * @return movieId
     */
    public String getMovieId() {
        return movieId;
    }

    /**
     * This Method is use to Set the Movie ID
     *
     * @param movieId For Setting The Movie ID
     */
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    /**
     * This Method is use to Get the Permalink
     *
     * @return permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * This Method is use to Set the Permalink
     *
     * @param permalink For Setting The Permalink
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     * This Method is use to Get the Name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This Method is use to Set the Name
     *
     * @param name For Setting The Name
     */
    public void setName(String name) {
        this.name = name;
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
     * This Method is use to Set the Story
     *
     * @param story For Setting The Story
     */
    public void setStory(String story) {
        this.story = story;
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
     * This Method is use to Get the Content ID Type
     *
     * @return contentTypesId
     */
    public String getContentTypesId() {
        return contentTypesId;
    }

    /**
     * This Method is use to Set the Content ID Type
     *
     * @param contentTypesId For Setting The Content ID Type
     */
    public void setContentTypesId(String contentTypesId) {
        this.contentTypesId = contentTypesId;
    }

    /**
     * This Method is use to Get the Poster URL
     *
     * @return posterUrl
     */
    public String getPosterUrl() {
        return posterUrl;
    }

    /**
     * This Method is use to Set the Poster URL
     *
     * @param posterUrl For Setting The Poster URL
     */
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    /**
     * This Method is use to Get the Genre List
     *
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * This Method is use to Set the Genre List
     *
     * @param genre For Setting The Genre List
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * This Method is use to Get the Is Converted Details
     *
     * @return isConverted
     */
    public int getIsConverted() {
        return isConverted;
    }

    /**
     * This Method is use to Set the Is Converted Details
     *
     * @param isConverted For Setting The Is Converted Details
     */
    public void setIsConverted(int isConverted) {
        this.isConverted = isConverted;
    }
}
