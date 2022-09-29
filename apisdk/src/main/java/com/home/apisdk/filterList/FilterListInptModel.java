package com.home.apisdk.filterList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterListInptModel {

    String limit = "10", offset = "0";
    String orderby;
    String authToken;
    String country;
    String Language;
    String state;
    String city;

    private List<String> catagoryQuery;
    private List<String> sortByQuery;
    private List<String> genreQuery;
    private HashMap<String, List<String>> metadataQuery;


    public List<String> getCatagoryQuery() {
        return catagoryQuery;
    }

    public List<String> getSortByQuery() {
        return sortByQuery;
    }

    public List<String> getGenreQuery() {
        return genreQuery;
    }

    public HashMap<String, List<String>> getMetadataQuery() {
        return metadataQuery;
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

    String userId = "";


    public ArrayList<String> getGenreArray() {
        return genreArray;
    }

    public void setGenreArray(ArrayList<String> genreArray) {
        this.genreArray = genreArray;
    }

    public ArrayList<String> genreArray;


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

    public void setCategoryList(List<String> catagoryQuery) {
        this.catagoryQuery = catagoryQuery;
    }

    public void setSortByList(List<String> sortByQuery) {
        this.sortByQuery = sortByQuery;

    }

    public void setGenreList(List<String> genreQuery) {
        this.genreQuery = genreQuery;

    }

    public void setMetadataList(HashMap<String, List<String>> metadataQuery) {
        this.metadataQuery = metadataQuery;
    }
}
