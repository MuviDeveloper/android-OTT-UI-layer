package com.home.apisdk.apiModel;

import java.util.ArrayList;

/**
 * This Model Class Holds All The Output Attributes For GetEpisodeDeatailsAsynTask
 *
 * @author Abhishek
 */

public class Episode_Details_output {
    ArrayList<Episode> EpisodeArray = new ArrayList<>();
    private String name, code, msg, muvi_uniq_id, permalink, item_count, limit, offset;
    private int is_ppv = 0;
    APVModel apvDetails;
    String episode_title,video_url,poster_url,id;
    String isFree="";
    String purchaseStatus="";
    String is_downloadable= "0";
    String movie_stream_uniq_id="";
    String cast="";

    public boolean isIs_cache_data() {
        return is_cache_data;
    }

    public void setIs_cache_data(boolean is_cache_data) {
        this.is_cache_data = is_cache_data;
    }

    boolean is_cache_data = true;





    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getMovie_stream_id() {
        return movie_stream_id;
    }

    public void setMovie_stream_id(String movie_stream_id) {
        this.movie_stream_id = movie_stream_id;
    }

    String movie_stream_id="";
    String episodeNumber,seriesNumber;
    private int downloadStatus;
    private int downloadPercentage;
    private String is_media_published = "";


    public String getContent_types_id() {
        return content_types_id;
    }

    public void setContent_types_id(String content_types_id) {
        this.content_types_id = content_types_id;
    }

    private String content_types_id;

    public String getIs_media_published() {
        return is_media_published;
    }

    public void setIs_media_published(String is_media_published) {
        this.is_media_published = is_media_published;
    }

    public String getIsEpisode() {
        return isEpisode;
    }

    public void setIsEpisode(String isEpisode) {
        this.isEpisode = isEpisode;
    }

    String isEpisode="";
    public int getIs_converted() {
        return is_converted;
    }

    public void setIs_converted(int is_converted) {
        this.is_converted = is_converted;
    }

    private int is_converted;

    public int getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(int downloadStatus) {
        this.downloadStatus = downloadStatus;
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

    private int visibilityStatus;

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(String episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(String video_duration) {
        this.video_duration = video_duration;
    }

    String video_duration;
    public String getEmbeddedUrl() {
        return embeddedUrl;
    }

    public void setEmbeddedUrl(String embeddedUrl) {
        this.embeddedUrl = embeddedUrl;
    }

    String embeddedUrl="";

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public String getIs_downloadable() {
        return is_downloadable;
    }

    public void setIs_downloadable(String is_downloadable) {
        this.is_downloadable = is_downloadable;
    }

    public String getMovie_stream_uniq_id() {
        return movie_stream_uniq_id;
    }

    public void setMovie_stream_uniq_id(String movie_stream_uniq_id) {
        this.movie_stream_uniq_id = movie_stream_uniq_id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    String genre="";

    public String getEpisode_title() {
        return episode_title;
    }

    public void setEpisode_title(String episode_title) {
        this.episode_title = episode_title;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsAPV() {
        return isAPV;
    }

    public void setIsAPV(int isAPV) {
        this.isAPV = isAPV;
    }

    private int isAPV = 0;
    PPVModel ppvDetails;
    CurrencyModel currencyDetails;

    /**
     * This Method is use to Get the PPV Details
     *
     * @return is_ppv
     */

    public int getIs_ppv() {
        return is_ppv;
    }

    /**
     * This Method is use to Set the PPV Details
     *
     * @param is_ppv For Setting The PPV Details
     */

    public void setIs_ppv(int is_ppv) {
        this.is_ppv = is_ppv;
    }

    /**
     * This Method is use to Get the Episode Array
     *
     * @return EpisodeArray
     */

    public ArrayList<Episode> getEpisodeArray() {
        return EpisodeArray;
    }

    /**
     * This Method is use to Set the Episode Array
     *
     * @param episodeArray For Setting The Episode Array
     */

    public void setEpisodeArray(ArrayList<Episode> episodeArray) {
        EpisodeArray = episodeArray;
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
     * This Method is use to Get the Code
     *
     * @return code
     */

    public String getCode() {
        return code;
    }

    /**
     * This Method is use to Set the Code
     *
     * @param code For Setting The Code
     */

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This Method is use to Get the Message
     *
     * @return msg
     */

    public String getMsg() {
        return msg;
    }

    /**
     * This Method is use to Set the Message
     *
     * @param msg For Setting The Message
     */

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * This Method is use to Get the Movie Unique Id
     *
     * @return muvi_uniq_id
     */

    public String getMuvi_uniq_id() {
        return muvi_uniq_id;
    }

    /**
     * This Method is use to Set the Movie Unique Id
     *
     * @param muvi_uniq_id For Setting The Movie Unique Id
     */

    public void setMuvi_uniq_id(String muvi_uniq_id) {
        this.muvi_uniq_id = muvi_uniq_id;
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
     * This Method is use to Get the Item Count
     *
     * @return item_count
     */

    public String getItem_count() {
        return item_count;
    }

    /**
     * This Method is use to Set the Item Count
     *
     * @param item_count For Setting The Item Count
     */

    public void setItem_count(String item_count) {
        this.item_count = item_count;
    }

    /**
     * This Method is use to Get the Limit
     *
     * @return limit
     */

    public String getLimit() {
        return limit;
    }

    /**
     * This Method is use to Set the Limit
     *
     * @param limit For Setting The Limit
     */

    public void setLimit(String limit) {
        this.limit = limit;
    }

    /**
     * This Method is use to Get the Offset
     *
     * @return offset
     */

    public String getOffset() {
        return offset;
    }

    /**
     * This Method is use to Set the Offset
     *
     * @param offset For Setting The Offset
     */

    public void setOffset(String offset) {
        this.offset = offset;
    }

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

    public class Episode {
        String id="";
        String movie_stream_uniq_id = "";
        String full_movie = "";
        String episode_number = "";
        String video_resolution = "";
        String episode_title = "";
        String series_number = "";
        String episode_date = "";
        String episode_story = "";
        String video_url = "";
        String thirdparty_url = "";
        String rolltype = "";

        public String getIs_livestream_enabled() {
            return is_livestream_enabled;
        }

        public void setIs_livestream_enabled(String is_livestream_enabled) {
            this.is_livestream_enabled = is_livestream_enabled;
        }

        String roll_after = "";
        String video_duration = "";
        String ikäraja = "";
        String embeddedUrl = "";
        String poster_url = "";
        String movieUrlForTv = "";
        String is_converted="";
        String is_media_published = "";
        String is_livestream_enabled="";


        public String getIs_converted() {
            return is_converted;
        }

        public void setIs_converted(String is_converted) {
            this.is_converted = is_converted;
        }

        public String getIs_media_published() {
            return is_media_published;
        }

        public void setIs_media_published(String is_media_published) {
            this.is_media_published = is_media_published;
        }

        /**
         * This Method is use to Get the Content Type Id
         *
         * @return content_types_id
         */


        String isFree= "";
        private int downloadStatus;

        public int getDownloadStatus() {
            return downloadStatus;
        }

        public void setDownloadStatus(int downloadStatus) {
            this.downloadStatus = downloadStatus;
        }

        public String getIsFree() {
            return isFree;
        }

        public void setIsFree(String isFree) {
            this.isFree = isFree;
        }

        public int getContent_types_id() {
            return content_types_id;
        }

        /**
         * This Method is use to Set the Content Type Id
         *
         * @param content_types_id For Setting The Content Type Id
         */

        public void setContent_types_id(int content_types_id) {
            this.content_types_id = content_types_id;
        }

        int content_types_id;

        /**
         * This Method is use to Get the Id
         *
         * @return id
         */

        public String getId() {
            return id;
        }

        /**
         * This Method is use to Set the Id
         *
         * @param id For Setting The Id
         */

        public void setId(String id) {
            this.id = id;
        }

        /**
         * This Method is use to Get the Movie Stream Unique Id
         *
         * @return movie_stream_uniq_id
         */

        public String getMovie_stream_uniq_id() {
            return movie_stream_uniq_id;
        }

        /**
         * This Method is use to Set the Movie Stream Unique Id
         *
         * @param movie_stream_uniq_id For Setting The Movie Stream Unique Id
         */

        public void setMovie_stream_uniq_id(String movie_stream_uniq_id) {
            this.movie_stream_uniq_id = movie_stream_uniq_id;
        }

        /**
         * This Method is use to Get the Full Movie
         *
         * @return full_movie
         */

        public String getFull_movie() {
            return full_movie;
        }

        /**
         * This Method is use to Set the Full Movie
         *
         * @param full_movie For Setting The Full Movie
         */

        public void setFull_movie(String full_movie) {
            this.full_movie = full_movie;
        }

        /**
         * This Method is use to Get the Episode Number
         *
         * @return episode_number
         */

        public String getEpisode_number() {
            return episode_number;
        }

        /**
         * This Method is use to Set the Episode Number
         *
         * @param episode_number For Setting The Episode Number
         */

        public void setEpisode_number(String episode_number) {
            this.episode_number = episode_number;
        }

        /**
         * This Method is use to Get the Video Resolution
         *
         * @return video_resolution
         */

        public String getVideo_resolution() {
            return video_resolution;
        }

        /**
         * This Method is use to Set the Video Resolution
         *
         * @param video_resolution For Setting The Video Resolution
         */

        public void setVideo_resolution(String video_resolution) {
            this.video_resolution = video_resolution;
        }

        /**
         * This Method is use to Get the Episode Title
         *
         * @return episode_title
         */

        public String getEpisode_title() {
            return episode_title;
        }

        /**
         * This Method is use to Set the Episode Title
         *
         * @param episode_title For Setting The Episode Title
         */

        public void setEpisode_title(String episode_title) {
            this.episode_title = episode_title;
        }

        /**
         * This Method is use to Get the Series Number
         *
         * @return series_number
         */

        public String getSeries_number() {
            return series_number;
        }

        /**
         * This Method is use to Set the Series Number
         *
         * @param series_number For Setting The Series Number
         */

        public void setSeries_number(String series_number) {
            this.series_number = series_number;
        }

        /**
         * This Method is use to Get the Episode Date
         *
         * @return episode_date
         */

        public String getEpisode_date() {
            return episode_date;
        }

        /**
         * This Method is use to Set the Episode Date
         *
         * @param episode_date For Setting The Episode Date
         */

        public void setEpisode_date(String episode_date) {
            this.episode_date = episode_date;
        }

        /**
         * This Method is use to Get the Episode Story
         *
         * @return episode_story
         */

        public String getEpisode_story() {
            return episode_story;
        }

        /**
         * This Method is use to Set the Episode Story
         *
         * @param episode_story For Setting The Episode Story
         */

        public void setEpisode_story(String episode_story) {
            this.episode_story = episode_story;
        }

        /**
         * This Method is use to Get the Video URL
         *
         * @return video_url
         */

        public String getVideo_url() {
            return video_url;
        }

        /**
         * This Method is use to Set the Video URL
         *
         * @param video_url For Setting The Video URL
         */

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
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
         * This Method is use to Get the Roll Type
         *
         * @return rolltype
         */

        public String getRolltype() {
            return rolltype;
        }

        /**
         * This Method is use to Set the Roll Type
         *
         * @param rolltype For Setting The Roll Type
         */

        public void setRolltype(String rolltype) {
            this.rolltype = rolltype;
        }

        /**
         * This Method is use to Get the Roll After
         *
         * @return roll_after
         */

        public String getRoll_after() {
            return roll_after;
        }

        /**
         * This Method is use to Set the Roll After
         *
         * @param roll_after For Setting The Roll After
         */

        public void setRoll_after(String roll_after) {
            this.roll_after = roll_after;
        }

        /**
         * This Method is use to Get the Video Duration
         *
         * @return video_duration
         */

        public String getVideo_duration() {
            return video_duration;
        }

        /**
         * This Method is use to Set the Video Duration
         *
         * @param video_duration For Setting The Video Duration
         */

        public void setVideo_duration(String video_duration) {
            this.video_duration = video_duration;
        }

        /**
         * This Method is use to Get the Ikäraja
         *
         * @return ikäraja
         */

        public String getIkäraja() {
            return ikäraja;
        }

        /**
         * This Method is use to Set the Ikäraja
         *
         * @param ikäraja For Setting The Ikäraja
         */

        public void setIkäraja(String ikäraja) {
            this.ikäraja = ikäraja;
        }

        /**
         * This Method is use to Get the Embedded URL
         *
         * @return embeddedUrl
         */

        public String getEmbeddedUrl() {
            return embeddedUrl;
        }

        /**
         * This Method is use to Set the Embedded URL
         *
         * @param embeddedUrl For Setting The Embedded URL
         */

        public void setEmbeddedUrl(String embeddedUrl) {
            this.embeddedUrl = embeddedUrl;
        }

        /**
         * This Method is use to Get the Poster URL
         *
         * @return poster_url
         */

        public String getPoster_url() {
            return poster_url;
        }

        /**
         * This Method is use to Set the Poster URL
         *
         * @param poster_url For Setting The Poster URL
         */

        public void setPoster_url(String poster_url) {
            this.poster_url = poster_url;
        }

        /**
         * This Method is use to Get the Movie URL For TV
         *
         * @return movieUrlForTv
         */

        public String getMovieUrlForTv() {
            return movieUrlForTv;
        }

        /**
         * This Method is use to Set the Movie URL For TV
         *
         * @param movieUrlForTv For Setting The Movie URL For TV
         */

        public void setMovieUrlForTv(String movieUrlForTv) {
            this.movieUrlForTv = movieUrlForTv;
        }

        ArrayList<Resolution> resolutionArray = new ArrayList<>();

        /**
         * This Method is use to Get the Resolution Array
         *
         * @return resolutionArray
         */

        public ArrayList<Resolution> getResolutionArray() {
            return resolutionArray;
        }

        /**
         * This Method is use to Set the Resolution Array
         *
         * @param resolutionArray For Setting The Resolution Array
         */

        public void setResolutionArray(ArrayList<Resolution> resolutionArray) {
            this.resolutionArray = resolutionArray;
        }

        public class Resolution {

            String BEST, secondBest, thirdBest, fourthBest;

            /**
             * This Method is use to Get the Best Resolution
             *
             * @return BEST
             */

            public String getBEST() {
                return BEST;
            }

            /**
             * This Method is use to Set the Best Resolution
             *
             * @param BEST For Setting Best Resolution
             */

            public void setBEST(String BEST) {
                this.BEST = BEST;
            }

            /**
             * This Method is use to Get the Second Best Resolution
             *
             * @return secondBest
             */

            public String getSecondBest() {
                return secondBest;
            }

            /**
             * This Method is use to Set the Second Best Resolution
             *
             * @param secondBest For Setting The Second Best Resolution
             */

            public void setSecondBest(String secondBest) {
                this.secondBest = secondBest;
            }

            /**
             * This Method is use to Get the Third Best Resolution
             *
             * @return thirdBest
             */

            public String getThirdBest() {
                return thirdBest;
            }

            /**
             * This Method is use to Get the Third Best Resolution
             *
             * @param thirdBest For Setting The Third Best Resolution
             */

            public void setThirdBest(String thirdBest) {
                this.thirdBest = thirdBest;
            }

            /**
             * This Method is use to Get the Fourth Best Resolution
             *
             * @return fourthBest
             */

            public String getFourthBest() {
                return fourthBest;
            }

            /**
             * This Method is use to Get the Fourth Best Resolution
             *
             * @param fourthBest For Setting The Fourth Best Resolution
             */

            public void setFourthBest(String fourthBest) {
                this.fourthBest = fourthBest;
            }
        }

    }
}
