package com.home.apisdk.apiModel;

public class GenerateOTPInputModel {

    String authToken;
    String email;
    String mobile_number;
    String lang_code;

    public String getMobile_country_code() {
        return mobile_country_code;
    }

    public void setMobile_country_code(String mobile_country_code) {
        this.mobile_country_code = mobile_country_code;
    }

    String mobile_country_code;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getLang_code() {
        return lang_code;
    }

    public void setLang_code(String lang_code) {
        this.lang_code = lang_code;
    }
}
