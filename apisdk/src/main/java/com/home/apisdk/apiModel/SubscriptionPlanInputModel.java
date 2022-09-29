package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Input Attributes For GetPlanListAsynctask
 *
 * @author MUVI
 */

public class SubscriptionPlanInputModel {
    String authToken;
    String current_plan_id = "";

    public String getCurrent_plan_id() {
        return current_plan_id;
    }

    public void setCurrent_plan_id(String current_plan_id) {
        this.current_plan_id = current_plan_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * This Method  is use to Get the Language
     *
     * @return lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * This Method is use to Set the Language
     *
     * @param lang For Setting The Language
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    String lang;
    String user_id = "";

    /**
     * This Method is use to Get the Auth Token
     *
     * @return authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * This Method is use to Set the Auth Token
     *
     * @param authToken For Setting The Auth Token
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String country = "";
}
