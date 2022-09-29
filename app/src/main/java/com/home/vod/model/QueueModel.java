package com.home.vod.model;

/**
 * Created by Muvi on 8/24/2017.
 */

public class QueueModel {

    private String SongName = "", AlbumArt = "", SongUrl = "", Artist_Name = "";
    private String episodeId = "", movieId = "";
    private String ParentName = "";
    public int getIsConverted() {
        return isConverted;
    }



    private int isConverted;

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public void setIsConverted(int isConverted) {
        this.isConverted = isConverted;
    }

    public QueueModel(String episode_title, String poster_url, String video_url, String mutiArtist, String status, String muviUniqId, String movie_stream_uniq_id, int isconverted) {
        this.SongName = episode_title;
        this.AlbumArt = poster_url;
        this.SongUrl = video_url;
        this.Artist_Name = mutiArtist;
        this.songStatus = status;
        this.movieId = muviUniqId;
        this.episodeId = movie_stream_uniq_id;
        this.isConverted = isconverted;
    }

    public QueueModel(String episode_title, String poster_url, String video_url, String mutiArtist,
                      String status, String muviUniqId, String movie_stream_uniq_id, int isconverted,String ParentName) {
        this.SongName = episode_title;
        this.AlbumArt = poster_url;
        this.SongUrl = video_url;
        this.Artist_Name = mutiArtist;
        this.songStatus = status;
        this.movieId = muviUniqId;
        this.episodeId = movie_stream_uniq_id;
        this.isConverted = isconverted;
        this.ParentName=ParentName;
    }

    public String getSongStatus() {
        return songStatus;
    }

    public void setSongStatus(String songStatus) {
        this.songStatus = songStatus;
    }

    private String songStatus;

    public String getArtist_Name() {
        return Artist_Name;
    }

    public void setArtist_Name(String artist_Name) {
        Artist_Name = artist_Name;
    }

    public QueueModel(String SongName, String AlbumArt, String SongUrl, String Artist_Name) {
        this.SongName = SongName;
        this.AlbumArt = AlbumArt;
        this.SongUrl = SongUrl;
        this.Artist_Name = Artist_Name;


    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getAlbumArt() {
        return AlbumArt;
    }

    public void setAlbumArt(String albumArt) {
        AlbumArt = albumArt;
    }

    public String getSongUrl() {
        return SongUrl;
    }

    public void setSongUrl(String songUrl) {
        SongUrl = songUrl;
    }
}
