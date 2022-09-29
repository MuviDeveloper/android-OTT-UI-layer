package com.home.apisdk.apiModel;

/**
 * Created by MUVI on 1/20/2017.
 */

public class IsRegistrationEnabledOutputModel {

    int isMylibrary = 0;
    int is_login = 0;
    int signup_step = 0;
    int has_favourite = 0;
    int rating = 0;
    int isRestrictDevice = 0;
    int isUGCEnabled = 0;
    int posterHeight, posterWidth;
    int bannerHeight, bannerWidth;
    int is_autoplay_enabled = 0;
    int is_seasonpage_enabled = 0;
    int is_registration_enabled = 0;
    int is_playback_speed_enabled = 1;
    int mobile_no_required=0;
    int register_through =0;
    int is_otp_enabled =0;
    int allow_add_email =0;
    int otp_expiry_duration =0;
    int allow_add_phone_no =0;
    String sso_identity_provider ="";

    public String getSso_identity_provider() {
        return sso_identity_provider;
    }

    public void setSso_identity_provider(String sso_identity_provider) {
        this.sso_identity_provider = sso_identity_provider;
    }

    public int getRegister_through() {
        return register_through;
    }

    public void setRegister_through(int register_through) {
        this.register_through = register_through;
    }

    public int getIs_otp_enabled() {
        return is_otp_enabled;
    }

    public void setIs_otp_enabled(int is_otp_enabled) {
        this.is_otp_enabled = is_otp_enabled;
    }

    public int getAllow_add_email() {
        return allow_add_email;
    }

    public void setAllow_add_email(int allow_add_email) {
        this.allow_add_email = allow_add_email;
    }

    public int getOtp_expiry_duration() {
        return otp_expiry_duration;
    }

    public void setOtp_expiry_duration(int otp_expiry_duration) {
        this.otp_expiry_duration = otp_expiry_duration;
    }

    public int getAllow_add_phone_no() {
        return allow_add_phone_no;
    }

    public void setAllow_add_phone_no(int allow_add_phone_no) {
        this.allow_add_phone_no = allow_add_phone_no;
    }

    public int getMobile_no_required() {
        return mobile_no_required;
    }

    public void setMobile_no_required(int mobile_no_required) {
        this.mobile_no_required = mobile_no_required;
    }

    public int getIs_registration_enabled() {
        return is_registration_enabled;
    }

    public void setIs_registration_enabled(int is_registration_enabled) {
        this.is_registration_enabled = is_registration_enabled;
    }

    public int getIs_seasonpage_enabled() {
        return is_seasonpage_enabled;
    }

    public void setIs_seasonpage_enabled(int is_seasonpage_enabled) {
        this.is_seasonpage_enabled = is_seasonpage_enabled;
    }


    public int getIs_autoplay_enabled() {
        return is_autoplay_enabled;
    }

    public void setIs_autoplay_enabled(int is_autoplay_enabled) {
        this.is_autoplay_enabled = is_autoplay_enabled;
    }

    public void setIsPlaybackSpeedEnabled(int is_playback_speed_enabled) {
        this.is_playback_speed_enabled = is_playback_speed_enabled;
    }

    public int getIsPlaybackSpeedEnabled() {
        return this.is_playback_speed_enabled;
    }

    /**
     * @author Debashish
     * @description Added for watch history
     */
    int watch_history;

    public int getWatch_history() {
        return watch_history;
    }

    public void setWatch_history(int watch_history) {
        this.watch_history = watch_history;
    }


    public int getPosterHeight() {
        return posterHeight;
    }

    public void setPosterHeight(int posterHeight) {
        this.posterHeight = posterHeight;
    }

    public int getPosterWidth() {
        return posterWidth;
    }

    public void setPosterWidth(int posterWidth) {
        this.posterWidth = posterWidth;
    }

    public int getBannerHeight() {
        return bannerHeight;
    }

    public void setBannerHeight(int bannerHeight) {
        this.bannerHeight = bannerHeight;
    }

    public int getBannerWidth() {
        return bannerWidth;
    }

    public void setBannerWidth(int bannerWidth) {
        this.bannerWidth = bannerWidth;
    }

    public int getIsUGCEnabled() {
        return isUGCEnabled;
    }

    public void setIsUGCEnabled(int isUGCEnabled) {
        this.isUGCEnabled = isUGCEnabled;
    }

    /**
     * This Method is use to get the Restrict Device Details
     *
     * @return isRestrictDevice
     */

    public int getIsRestrictDevice() {
        return isRestrictDevice;
    }

    /**
     * This Method is use to set the Restrict Device
     *
     * @param isRestrictDevice For Setting The Retricted Device Details
     */

    public void setIsRestrictDevice(int isRestrictDevice) {
        this.isRestrictDevice = isRestrictDevice;
    }

    /**
     * This Method is use to get the Streaming Restriction Details
     *
     * @return is_streaming_restriction
     */

    public int getIs_streaming_restriction() {
        return is_streaming_restriction;
    }

    /**
     * This Method is use to set the Streaming Restriction Details
     *
     * @param is_streaming_restriction For Setting The Streaming Restriction
     */

    public void setIs_streaming_restriction(int is_streaming_restriction) {
        this.is_streaming_restriction = is_streaming_restriction;
    }

    /**
     * This Method is use to get the Chromecast Details
     *
     * @return chromecast
     */
    public int getChromecast() {
        return chromecast;
    }

    /**
     * This Method is use to set the Chromecast Details
     *
     * @param chromecast For Setting The Chromecast
     */
    public void setChromecast(int chromecast) {
        this.chromecast = chromecast;
    }

    /**
     * This Method is use to get the is_offline Details
     *
     * @return is_offline
     */
    public int getIs_offline() {
        return is_offline;
    }

    /**
     * This Method is use to set the is_offline Details
     *
     * @param is_offline For Setting The Is Offline Details
     */
    public void setIs_offline(int is_offline) {
        this.is_offline = is_offline;
    }

    int is_streaming_restriction = 0;
    int chromecast = 0;
    int is_offline = 0;


    /**
     * This Method is use to get the Rating Details
     *
     * @return rating
     */

    public int getRating() {
        return rating;
    }

    /**
     * This Method is use to set the Rating Details
     *
     * @param rating For Setting The Rating Details
     */

    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * This Method is use to get the My Library Details
     *
     * @return isMylibrary
     */

    public int getIsMylibrary() {
        return isMylibrary;
    }

    /**
     * This Method is use to set the My Library Details
     *
     * @param isMylibrary For Setting The My Library Details
     */

    public void setIsMylibrary(int isMylibrary) {
        this.isMylibrary = isMylibrary;
    }

    /**
     * This Method is use to get the Is Login Details
     *
     * @return is_login
     */

    public int getIs_login() {
        return is_login;
    }

    /**
     * This Method is use to set the Is Login Details
     *
     * @param is_login For Setting The Is Login Details
     */

    public void setIs_login(int is_login) {
        this.is_login = is_login;
    }

    /**
     * This Method is use to get the Sign Up Step Details
     *
     * @return signup_step
     */

    public int getSignup_step() {
        return signup_step;
    }

    /**
     * This Method is use to set the Sign Up Step Details
     *
     * @param signup_step For Setting The Sign Up Step Details
     */

    public void setSignup_step(int signup_step) {
        this.signup_step = signup_step;
    }

    /**
     * This Method is use to get the Has Favorite Details
     *
     * @return has_favourite
     */

    public int getHas_favourite() {
        return has_favourite;
    }

    /**
     * This Method is use to set the Has Favorite Details
     *
     * @param has_favourite For Setting The Has Favorite Details
     */

    public void setHas_favourite(int has_favourite) {
        this.has_favourite = has_favourite;
    }


}
