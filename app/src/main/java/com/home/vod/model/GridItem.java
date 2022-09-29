package com.home.vod.model;


public class GridItem {
    private String title;
    private String videoType;
    private String imageId;
    private String permalink;
    private int isConverted;
    private int isPPV;
    private int isAPV;
    private String movieUniqueId;
    private String movieStreamUniqueId;
    private String fullMovie;
    private String story;
    private boolean isClicked;
    private boolean isSelected;
    private String bannerUrl, selectedCategories;
    private String isEpisode;
    private String videoUrl;
    private String videoTypeId;
    private String movieGenre;
    private String language;
    private int contentPublishStatus;
    private String season_no = "";
    private String episode_no = "";
    private String parent_title = "";
    private String parent_poster = "";
    private String video_duration = "";

    public String getSeason_no() {
        return season_no;
    }

    public void setSeason_no(String season_no) {
        this.season_no = season_no;
    }

    public String getEpisode_no() {
        return episode_no;
    }

    public void setEpisode_no(String episode_no) {
        this.episode_no = episode_no;
    }

    public String getParent_title() {
        return parent_title;
    }

    public void setParent_title(String parent_title) {
        this.parent_title = parent_title;
    }

    public String getParent_poster() {
        return parent_poster;
    }

    public void setParent_poster(String parent_poster) {
        this.parent_poster = parent_poster;
    }

    public String getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(String video_duration) {
        this.video_duration = video_duration;
    }



    public String getSeason_permalink() {
        return season_permalink;
    }

    public void setSeason_permalink(String season_permalink) {
        this.season_permalink = season_permalink;
    }

    private String season_permalink = "";

    public String getIs_media_published() {
        return is_media_published;
    }

    public void setIs_media_published(String is_media_published) {
        this.is_media_published = is_media_published;
    }

    private String langCode;
    private String is_media_published;
    private String play_list_type;
    private String is_play_list;

    public String getPlay_list_type() {
        return play_list_type;
    }

    public void setPlay_list_type(String play_list_type) {
        this.play_list_type = play_list_type;
    }

    public String getIs_play_list() {
        return is_play_list;
    }

    public void setIs_play_list(String is_play_list) {
        this.is_play_list = is_play_list;
    }

    public String getList_id() {
        return list_id;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    private String list_id = "";
    private String poster_url = "";
    private String name = "";


    public int getContentPublishStatus() {
        return contentPublishStatus;
    }


    public String getLanguage() {
        return language;
    }

    public String getLangCode() {
        return langCode;
    }


    public String getFullMovie() {
        return fullMovie;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }


    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public int getIsAPV() {
        return isAPV;
    }

    public void setIsAPV(int isAPV) {
        this.isAPV = isAPV;
    }

    public int getIsPPV() {
        return isPPV;
    }

    public void setIsPPV(int isPPV) {
        this.isPPV = isPPV;
    }

    public int getIsConverted() {
        return isConverted;
    }

    public void setIsConverted(int isConverted) {
        this.isConverted = isConverted;
    }


    public String getMovieUniqueId() {
        return movieUniqueId;
    }

    public void setMovieUniqueId(String movieUniqueId) {
        this.movieUniqueId = movieUniqueId;
    }

    public String getMovieStreamUniqueId() {
        return movieStreamUniqueId;
    }


    public String getIsEpisode() {
        return isEpisode;
    }

    public void setIsEpisode(String isEpisode) {
        this.isEpisode = isEpisode;
    }


    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


    public String getVideoTypeId() {
        return videoTypeId;
    }

    public void setVideoTypeId(String videoTypeId) {
        this.videoTypeId = videoTypeId;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }


    public String getVideoType() {

        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }


    //	public GridItem(int imageId, String title,String rating) {
//		super();
//		this.imageId = imageId;
//		this.title = title;
//		this.rating = rating;
//	}
    public GridItem(String imageId, String title, String videoType, String videoTypeId, String movieGenre, String videoUrl, String permalink, String isEpisode, String movieUniqueId, String movieStreamUniqueId, int isConverted, int isPPV, int isAPV, String is_media_published,String season_permalink) {
        super();
        this.imageId = imageId;
        this.title = title;
        this.videoType = videoType;
        this.videoTypeId = videoTypeId;
        this.movieGenre = movieGenre;
        this.videoUrl = videoUrl;
        this.permalink = permalink;
        this.isEpisode = isEpisode;
        this.movieUniqueId = movieUniqueId;
        this.movieStreamUniqueId = movieStreamUniqueId;
        this.isConverted = isConverted;
        this.isPPV = isPPV;
        this.isAPV = isAPV;
        this.is_media_published = is_media_published;
        this.season_permalink = season_permalink;


    }
//////////
    public GridItem(String imageId, String title, String videoType, String videoTypeId, String movieGenre, String videoUrl, String permalink, String isEpisode, String movieUniqueId, String movieStreamUniqueId, int isConverted, int isPPV, int isAPV, String is_media_published,String play_list_type, String is_play_list, String list_id,String poster_url,String name, String season_permalink,
                    String season_no, String parent_title, String parent_poster, String video_duration, String story, String episode_no) {
        super();
        this.video_duration = video_duration;
        this.episode_no = episode_no;
        this.story = story;
        this.season_no = season_no;
        this.parent_title = parent_title;
        this.parent_poster = parent_poster;
        this.imageId = imageId;
        this.title = title;
        this.videoType = videoType;
        this.videoTypeId = videoTypeId;
        this.movieGenre = movieGenre;
        this.videoUrl = videoUrl;
        this.permalink = permalink;
        this.isEpisode = isEpisode;
        this.movieUniqueId = movieUniqueId;
        this.movieStreamUniqueId = movieStreamUniqueId;
        this.isConverted = isConverted;
        this.isPPV = isPPV;
        this.isAPV = isAPV;
        this.is_media_published = is_media_published;
        this.play_list_type = play_list_type;
        this.is_play_list = is_play_list;
        this.list_id = list_id;
        this.poster_url = poster_url;
        this.name = name;
        this.season_permalink = season_permalink;
    }

    /*
    * Desc: This constructor is used to intialize other paarmeters required for UGC
    * */
    public GridItem(String movieImageStr, String movieName, String videoTypeIdStr, String moviePermalinkStr, String movieUniqueid, String desc,
                    String bannerUrl, String categories, String fullMovie, int isConverted, String language_name, String language_code,int status) {
        super();
        this.imageId = movieImageStr;
        this.title = movieName;
        this.videoTypeId = videoTypeIdStr;
        this.permalink = moviePermalinkStr;
        this.movieStreamUniqueId = movieUniqueid;
        this.story = desc;
        this.bannerUrl = bannerUrl;
        this.selectedCategories = categories;
        this.isConverted = isConverted;
        this.fullMovie = fullMovie;
        this.language = language_name;
        this.langCode = language_code;
        this.contentPublishStatus = status;
    }

    public String getImage() {
        return imageId;
    }

    public void setImage(String imageId) {

        this.imageId = imageId;
    }
    /*public String getImage() {
        return imageId;
	}

	public void setImage(String imageId) {
		this.imageId = imageId;
	}*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategories() {
        return selectedCategories;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }
}
