package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Input Attributes For RegistrationAsynTask
 *
 * @author MUVI
 */

public class Registration_input {
    String authToken;
    String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String phone="";
    String name = "";
    String password = "";
    String lang_code = "";

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    String custom_country = "";
    String countryCode = "";
    String group_type = "";
    String otp = "";

    public String getMobile_country_code() {
        return mobile_country_code;
    }

    public void setMobile_country_code(String mobile_country_code) {
        this.mobile_country_code = mobile_country_code;
    }

    String mobile_country_code;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    int is_mobile_no_required_mandatory=0;
    public int getIs_mobile_no_required_mandatory() {
        return is_mobile_no_required_mandatory;
    }

    public void setIs_mobile_no_required_mandatory(int is_mobile_no_required_mandatory) {
        this.is_mobile_no_required_mandatory = is_mobile_no_required_mandatory;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }



    public String getCustom_last_name() {
        return custom_last_name;
    }

    public void setCustom_last_name(String custom_last_name) {
        this.custom_last_name = custom_last_name;
    }

    String custom_last_name;

    /**
     * This Method  is use to Get the Language Code
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

    /**
     * This Method is use to Get the Custom Country
     *
     * @return custom_country
     */

    public String getCustom_country() {
        return custom_country;
    }

    /**
     * This Method is use to Set the Custom Country
     *
     * @param custom_country For Setting The Custom Country
     */

    public void setCustom_country(String custom_country) {
        this.custom_country = custom_country;
    }

    /**
     * This Method is use to Get the Custom Language
     *
     * @return custom_languages
     */

    public String getCustom_languages() {
        return custom_languages;
    }

    /**
     * This Method is use to Set the Custom Language
     *
     * @param custom_languages For Setting The Custom Language
     */

    public void setCustom_languages(String custom_languages) {
        this.custom_languages = custom_languages;
    }

    /**
     * This Method is use to Get the Device Id
     *
     * @return device_id
     */

    public String getDevice_id() {
        return device_id;
    }

    /**
     * This Method is use to Set the Device Id
     *
     * @param device_id For Setting The Device Id
     */

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    /**
     * This Method is use to Get the Google Id
     *
     * @return google_id
     */

    public String getGoogle_id() {
        return google_id;
    }

    /**
     * This Method is use to Set the Google Id
     *
     * @param google_id For Setting The Google ID
     */

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
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

    String custom_languages;
    String device_id;
    String google_id;
    String device_type;

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
     * This Method is use to Get the Email
     *
     * @return email
     */

    public String getEmail() {
        return email;
    }

    /**
     * This Method is use to Set the Email
     *
     * @param email For Setting The Email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This Method is use to Get the Name
     *
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * This Method is use to Set the Name
     *
     * @param name For Setting The Name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This Method is use to Get the Password
     *
     * @return password
     */

    public String getPassword() {
        return password;
    }

    /**
     * This Method is use to Set the Password
     *
     * @param password For Setting The Password
     */

    public void setPassword(String password) {
        this.password = password;
    }
}
