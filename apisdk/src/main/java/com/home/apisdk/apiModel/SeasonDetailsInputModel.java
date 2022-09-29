package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Input Attributes For the AboutUsAsync
 *
 * @author MUVI
 */

public class SeasonDetailsInputModel {
    String authToken = "";
    String season_permalink = "";
    String languageCode = "";
    String country = "";

    /**
     * Desc: This is used to get the authToken.
     * @return
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Desc: This is used to set the authToken.
     * @return
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * Desc: This is used to get the permalink.
     * @return
     */
    public String getPermalink() {
        return season_permalink;
    }

    /**
     * Desc: This is used to set the permalink.
     * @return
     */
    public void setPermalink(String season_permalink) {
        this.season_permalink = season_permalink;
    }

    /**
     * Desc: This is used to get the languageCode.
     * @return
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * Desc: This is used to set the languageCode.
     * @return
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * Desc: This is used to get the countyInfo.
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Desc: This is used to set the countryInfo.
     * @return
     */
    public void setCountry(String country) {
        this.country = country;
    }


}
