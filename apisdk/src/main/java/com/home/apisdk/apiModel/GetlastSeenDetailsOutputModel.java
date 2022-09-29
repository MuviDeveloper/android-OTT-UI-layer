package com.home.apisdk.apiModel;

import java.util.ArrayList;

/**
 * This Model Class Holds All The Input Attributes For the AboutUsAsync
 *
 * @author MUVI
 */

public class GetlastSeenDetailsOutputModel {

    String code = "";
    String status = "";
    String msg = "";
    String lastSeenStreamUniqId = "";
    String lastSeenMuviUniqId = "";
    String parent_title = "";
    String parent_poster = "";
    String default_poster = "";

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    String content_title = "";
    String story = "";

    public String getDefault_poster() {
        return default_poster;
    }

    public void setDefault_poster(String default_poster) {
        this.default_poster = default_poster;
    }

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
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

    public String getEpisode_no() {
        return episode_no;
    }

    public String getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(String video_duration) {
        this.video_duration = video_duration;
    }

    public void setEpisode_no(String episode_no) {
        this.episode_no = episode_no;
    }

    String episode_no = "";
    String video_duration = "";
    String seriesNo = "";
    String completedStatus = "" ;

    ArrayList<NextEpisode> nextEpisodes = new ArrayList<>();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLastSeenStreamUniqId() {
        return lastSeenStreamUniqId;
    }

    public void setLastSeenStreamUniqId(String lastSeenStreamUniqId) {
        this.lastSeenStreamUniqId = lastSeenStreamUniqId;
    }

    public String getLastSeenMuviUniqId() {
        return lastSeenMuviUniqId;
    }

    public void setLastSeenMuviUniqId(String lastSeenMuviUniqId) {
        this.lastSeenMuviUniqId = lastSeenMuviUniqId;
    }

    public String getSeriesNo() {
        return seriesNo;
    }

    public void setSeriesNo(String seriesNo) {
        this.seriesNo = seriesNo;
    }

    public String getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
    }


    public ArrayList<NextEpisode> getNextEpisodes() {
        return nextEpisodes;
    }

    public void setNextEpisodes(ArrayList<NextEpisode> nextEpisodes) {
        this.nextEpisodes = nextEpisodes;
    }



}



