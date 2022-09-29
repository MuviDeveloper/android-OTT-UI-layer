package com.home.apisdk.apiModel;

import java.io.Serializable;

public class getMediaQueueOutput  implements Serializable {
    private Integer code;
    private String status;
    private String msg;
    private NextMediaInfo nextMediaInfo;
    private NextMediaInfo previousMediaInfo;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public NextMediaInfo getNextMediaInfo() {
        return nextMediaInfo;
    }

    public void setNextMediaInfo(NextMediaInfo nextMediaInfo) {
        this.nextMediaInfo = nextMediaInfo;
    }

    public NextMediaInfo getPreviousMediaInfo() {
        return previousMediaInfo;
    }

    public void setPreviousMediaInfo(NextMediaInfo previousMediaInfo) {
        this.previousMediaInfo = previousMediaInfo;
    }

    public static class NextMediaInfo implements Serializable {

        private String contentTitle;
        private String contentTypesId;
        private String contentUniqId;
        private String streamUniqId;
        private String defaultPoster;
        private String description;
        private String duration;
        private String episodeNo;
        private String seasonNo;

        public String getContentTitle() {
            return contentTitle;
        }

        public void setContentTitle(String contentTitle) {
            this.contentTitle = contentTitle;
        }

        public String getContentTypesId() {
            return contentTypesId;
        }

        public void setContentTypesId(String contentTypesId) {
            this.contentTypesId = contentTypesId;
        }

        public String getContentUniqId() {
            return contentUniqId;
        }

        public void setContentUniqId(String contentUniqId) {
            this.contentUniqId = contentUniqId;
        }

        public String getStreamUniqId() {
            return streamUniqId;
        }

        public void setStreamUniqId(String streamUniqId) {
            this.streamUniqId = streamUniqId;
        }

        public String getDefaultPoster() {
            return defaultPoster;
        }

        public void setDefaultPoster(String defaultPoster) {
            this.defaultPoster = defaultPoster;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getEpisodeNo() {
            return episodeNo;
        }

        public void setEpisodeNo(String episodeNo) {
            this.episodeNo = episodeNo;
        }

        public String getSeasonNo() {
            return seasonNo;
        }

        public void setSeasonNo(String seasonNo) {
            this.seasonNo = seasonNo;
        }

    }

}

