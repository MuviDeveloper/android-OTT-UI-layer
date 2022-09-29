package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Input Attributes For LogoutAllAsync
 *
 * @author MUVI
 */

public class SimultaneousLogoutInput {

    String authToken;
    String device_type;
    String email_id;
    String countryCode;
    String laguageCode;
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLaguageCode() {
        return laguageCode;
    }

    public void setLaguageCode(String laguageCode) {
        this.laguageCode = laguageCode;
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

    /**
     * This Method is use to Get the Device Type
     *
     * @return device_type
     */

    public String getDevice_type() {
        return device_type;
    }

    /**
     * This Method is use to Set the Device Type
     *
     * @param device_type For Setting The Device Type
     */

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    /**
     * This Method is use to Get the Email Id
     *
     * @return email_id
     */

    public String getEmail_id() {
        return email_id;
    }

    /**
     * This Method is use to Set the Email Id
     *
     * @param email_id For Setting The Email Id
     */

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }


}
