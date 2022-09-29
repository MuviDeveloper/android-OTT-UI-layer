package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Input Attributes For GetContentDetailsAsynTask
 *
 * @author MUVI
 */
public class RelatedContentInput {
    String authtoken;
    String contentId;
    String content_stream_id;
    String user_id;

    String kids_mode = "0";
    public String getKids_mode() {
        return kids_mode;
    }

    public void setKids_mode(String kids_mode) {
        this.kids_mode = kids_mode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String country;
    String state;
    String city;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSeason_info() {
        return season_info;
    }

    public void setSeason_info(String season_info) {
        this.season_info = season_info;
    }

    String season_info="0";

    /**
     * This Method is use to Get the Content Id
     *
     * @return content Id
     */

    public String getContentId() {
        return contentId;
    }

    /**
     * This Method is use to Get the Content Id
     *
     * @param contentId For Setting The Content Id
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * This Method is use to Get the Stream Id
     *
     * @return Stream Id
     */

    public String getContent_stream_id() {
        return content_stream_id;
    }

    /**
     * This Method is use to Get the Stream Id
     *
     * @param content_stream_id For Setting The Stream Id
     */

    public void setContent_stream_id(String content_stream_id) {
        this.content_stream_id = content_stream_id;
    }

    /**
     * This Method is use to Get the Language
     *
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * This Method is use to Set the Language
     *
     * @param language For Setting The Language
     */

    public void setLanguage(String language) {
        this.language = language;
    }

    String language;


    /**
     * This Method is use to Get the Auth Token
     *
     * @return authtoken
     */
    public String getAuthToken() {
        return authtoken;
    }

    /**
     * This Method is use to Set the Auth Token
     *
     * @param authtoken For Setting The Auth Token
     */
    public void setAuthToken(String authtoken) {
        this.authtoken = authtoken;
    }
}
