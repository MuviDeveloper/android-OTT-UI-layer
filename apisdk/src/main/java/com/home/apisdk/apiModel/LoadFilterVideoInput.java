package com.home.apisdk.apiModel;

import java.util.ArrayList;

/**
 * Created by MUVI on 11/8/2017.
 */

public class LoadFilterVideoInput {

    String limit = "10", offset = "0";
    String orderby;
    String authToken;
    String permalink;
    String country;
    String Language;
    String state;
    String city;

    String kids_mode = "0";
    public String getKids_mode() {
        return kids_mode;
    }

    public void setKids_mode(String kids_mode) {
        this.kids_mode = kids_mode;
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


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    String userId ="";


    public ArrayList<String> getGenreArray() {
        return genreArray;
    }

    public void setGenreArray(ArrayList<String> genreArray) {
        this.genreArray = genreArray;
    }

    public  ArrayList<String> genreArray;


    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}
