package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Input Attributes For the AboutUsAsync
 *
 * @author MUVI
 */

public class GetlastSeenDetailsInput {

    String authToken;
    String countryCode;
    String userId;
    String langCode;
    String muviUniqId;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getMuviUniqId() {
        return muviUniqId;
    }

    public void setMuviUniqId(String muviUniqId) {
        this.muviUniqId = muviUniqId;
    }

}
