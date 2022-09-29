package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Input Attributes For GetFeatureContentAsynTask
 *
 * @author MUVI
 */

public class FeatureContentInputModel {
    String authToken;
    String section_id;
    String Limit;
    String userId="";
    String countryCode;
    String result_type = "";
    String season_info = "0";
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



    public String getSeason_info() {
        return season_info;
    }

    public void setSeason_info(String season_info) {
        this.season_info = season_info;
    }

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
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
    /**
     * This Method is use to Get the Limit
     *
     * @return Limit
     */
    public String getLimit() {
        return Limit;
    }

    /**
     * This Method is use to Set the Limit
     *
     * @param limit For Setting The Limit
     */
    public void setLimit(String limit) {
        Limit = limit;
    }

    /**
     * This Method is use to Get the OffSet
     *
     * @return offset
     */
    public String getOffset() {
        return offset;
    }

    /**
     * This Method is use to Set the OffSet
     *
     * @param offset For Setting The OffSet
     */
    public void setOffset(String offset) {
        this.offset = offset;
    }

    /**
     * This Method is use to Get the Order By
     *
     * @return orderby
     */
    public String getOrderby() {
        return orderby;
    }

    /**
     * This Method is use to Set the Order By
     *
     * @param orderby For Setting The Order By
     */
    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    String offset;
    String orderby;

    /**
     * This Method is use to Get the Language Code
     *
     * @return lang_code
     */
    public String getLang_code() {
        return lang_code;
    }

    /**
     * This Method is use to Set the Language Code
     *
     * @param lang_code For Setting The Language Code
     */
    public void setLang_code(String lang_code) {
        this.lang_code = lang_code;
    }

    String lang_code;

    /**
     * This Method is use to Get the Section ID
     *
     * @return section_id
     */
    public String getSection_id() {
        return section_id;
    }

    /**
     * This Method is use to Set the Section ID
     *
     * @param section_id For Setting The Section ID
     */
    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

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


}
