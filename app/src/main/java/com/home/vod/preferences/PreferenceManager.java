package com.home.vod.preferences;

import static com.home.vod.util.Constant.DEFAULT_BANNER_HEIGHT;
import static com.home.vod.util.Constant.DEFAULT_BANNER_WIDTH;
import static com.home.vod.util.Constant.DEFAULT_POSTER_HEIGHT;
import static com.home.vod.util.Constant.DEFAULT_POSTER_WIDTH;

import android.content.Context;
import android.content.SharedPreferences;

import com.home.apisdk.apiModel.IsRegistrationEnabledOutputModel;

/**
 * Created by muvi on 20/6/17.
 */

public class PreferenceManager {

    private static final String IS_PLAYALL_CLICKED = "IS_PLAYALL_CLICKED";
    private static PreferenceManager preferenceManager;
    private static final String PREFERENCE_KEY = "vod_pref";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public static final String BIOMETRIC_PREFERENCES = "BiometricPreferences";
    private final String PREFS_LOGGEDIN_ID_KEY = "pref_loggedin_id_key";
    private final String PREFS_GROUPARRAY_KEY = "pref_grouparray_key";
    private final String PREFS_GUESTUSER_ID_KEY = "pref_guestuser_id_key";
    private final String PREFS_LOGIN_EMAIL_ID_KEY = "pref_login_email_id_key";
    private final String PREF_COUNTRY_CODE_KEY = "countryCode";
    private final String PREF_STATE_KEY = "state";
    private final String PREF_CITY_KEY = "city";
    private final String IS_LOGIN_PREF_KEY = "VishwamisLogin";
    private final String IS_SSO_LOGIN_PREF_KEY = "ssologin";
    private final String SSO_LOGIN_URL_PREF_KEY = "ssologinurl";
    private final String GENRE_ARRAY_PREF_KEY = "genreArray";
    public final String GENRE_VALUES_ARRAY_PREF_KEY = "genreValueArray";
    public final String LANGUAGE_LIST_PREF = "VishwamLanguageListPref";
    public final String PREFS_LOGIN_ISSUBSCRIBED_KEY = "isSubscribed";
    public final String PREFS_LOGGEDIN_KEY = "pref_loged_in";
    public final String PREFS_LOGGEDIN_PASSWORD_KEY = "password";
    public final String PREFS_LOGIN_DISPLAY_NAME_KEY = "displayName";
    public final String PREFS_LOGIN_DISPLAY_PHONE_KEY = "mobilenumber";
    public final String PREFS_LOGIN_PROFILE_IMAGE_KEY = "loginProfImg";
    public final String PREFS_LOGIN_HISTORYID_KEY = "loginHistId";
    public final String PREFS_SOCIAL_LOGIN_KEY = "socialloginpref";
    public final String PREFS_GROUP_TYPE = "group_type";
    public final String PREFS_FOR_FREE_USER = "freeUser";
    public final String PREFS_LOGIN_DATE = "date";
    public final String PREFS_LANGUAGE_CHANGED = "language_changed";
    public final String FRAGMENTS_CHANGED = "fragments_changed";
    public final String FRAGMENTS_TITLE = "fragments_title";
    public final String FRAGMENTS_PERMALINK = "fragments_permalink";
    public final String FRAGMENTS_SELECTED_PERMALINK = "fragments_selected_permalink";
    public final String FRAGMENTS_POSITION = "fragments_position";
    public final String NOTI_COUNT = "noti_count";
    public static final String authToken = "authToken";

    public static final String POSTER_WIDTH = "poster_width";
    public static final String POSTER_HEIGHT = "poster_height";
    public static final String BANNER_WIDTH = "banner_width";
    public static final String BANNER_HEIGHT = "banner_height";

    private final String PREFS_SEASON_ID_KEY = "pref_season_id_key";
    //Settings
    public static final String PLAY_BEST_RESOLUTION = "play_best_resolution";
    public static final String DOWNLOAD_ON_WIFI_ONLY = "download_on_wifi";
    public static final String NOTIFY_STREAMING_ON_MOBILE_DATA = "notify_streaming_on_mobile_data";
    public static final String SELECTED_LANGUAGE_TEXT = "selected_language_text";
    public static final String IPADDRESS = "ipaddress";
    public static final String IS_SONG_PLAYING = "is_song_playing";
    public static final String SONG_NAME = "song_name";
    public static final String ARTIST_NAME = "artist_name";
    public static final String SONG_STATUS_UI_UPDATE = "song_status_ui_update";
    public static final String SONG_IMAGE_URL = "songImageUrl";
    public static final String SONG_URL = "song_url";
    public static final String SONG_STATUS = "song_status";
    public static final String SEEKBAR_DETAILS = "seekbar_details";
    public static final String TEMPORARY_PLAYERLEENGTH = "temporary_playerlength";

    //key added to store the event of clicking info icon for polling.

    public static final String IS_INFO_CLICKED = "is_info_clicked";

    public static final String PREVIOUS_POSITION = "previous_item_position";
    public static final String AUDIO_CURRENT_POSITION = "AUDIO_CURRENT_POSITION";

    public static final String AUDIO_PLAYBACK_SPEED = "AUDIO_PLAYBACK_SPEED";



    /*
     * @Desc: To store temporary playlength of player
     * */
    public void setTemporaryPlayLeangth(int leangth) {
        mEditor.putInt(TEMPORARY_PLAYERLEENGTH, leangth);
        mEditor.commit();
    }

    public int getTemporaryPlayLeangth() {
        return mSharedPreferences.getInt(TEMPORARY_PLAYERLEENGTH, 0);
    }

    /*
     * @Desc: To store Current Audio Position
     * */
    public void setCurrentAudioPosition(int leangth) {
        mEditor.putInt(AUDIO_CURRENT_POSITION, leangth);
        mEditor.commit();
    }

    public int getCurrentAudioPosition() {
        return mSharedPreferences.getInt(AUDIO_CURRENT_POSITION, 0);
    }


    public int getFRAGMENTS_POSITION() {
        return mSharedPreferences.getInt(FRAGMENTS_POSITION, 0);
    }

    public void setFRAGMENTS_POSITION(int position) {
        mEditor.putInt(FRAGMENTS_POSITION, position);
        mEditor.commit();
    }


    public String getFRAGMENTS_TITLE() {
        return mSharedPreferences.getString(FRAGMENTS_TITLE, "");
    }

    public String getFRAGMENTS_PERMALINK() {
        return mSharedPreferences.getString(FRAGMENTS_PERMALINK, "");
    }

    public void setFRAGMENTS_TITLE(String fragments_title) {
        mEditor.putString(FRAGMENTS_TITLE, fragments_title);
        mEditor.commit();
    }

    public void setFRAGMENTS_PERMALINK(String fragments_permalink) {
        mEditor.putString(FRAGMENTS_PERMALINK, fragments_permalink);
        mEditor.commit();
    }

    /*
     * Author:Subham
     * Desc:The selected data will be saved here
     * */

    public String getFRAGMENTS_SELECTED_PERMALINK() {
        return mSharedPreferences.getString(FRAGMENTS_SELECTED_PERMALINK, "");
    }

    public void setFRAGMENTS_SELECTED_PERMALINK(String fragments_selected_permalink) {
        mEditor.putString(FRAGMENTS_SELECTED_PERMALINK, fragments_selected_permalink);
        mEditor.commit();
    }


    public String getFRAGMENTS_CHANGED() {
        return mSharedPreferences.getString(FRAGMENTS_CHANGED, "home");
    }

    public void setFRAGMENTS_CHANGED(String fragments_changed) {
        mEditor.putString(FRAGMENTS_CHANGED, fragments_changed);
        mEditor.commit();
    }


    public String getAuthToken() {
        return mSharedPreferences.getString(authToken, "");
    }

    public void setAuthToken(String s) {
        mEditor.putString(authToken, s);
        mEditor.commit();
    }

    private PreferenceManager(Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static PreferenceManager getPreferenceManager(Context mContext) {
        if (preferenceManager == null) {
            return new PreferenceManager(mContext);
        }
        return preferenceManager;
    }

    public String getLanguageChangeStatus() {
        return mSharedPreferences.getString(PREFS_LANGUAGE_CHANGED, "0");
    }

    public void setLanguageChangeStatus(String languageChangeStatus) {
        mEditor.putString(PREFS_LANGUAGE_CHANGED, languageChangeStatus);
        mEditor.commit();
    }


    public String getUseridFromPref() {
        return mSharedPreferences.getString(PREFS_LOGGEDIN_ID_KEY, null);
    }

    public String getGuestUseridFromPref() {
        return mSharedPreferences.getString(PREFS_GUESTUSER_ID_KEY, null);
    }

    public String getEmailIdFromPref() {
        return mSharedPreferences.getString(PREFS_LOGIN_EMAIL_ID_KEY, null);
    }


    public void setUserIdToPref(String loginId) {
        mEditor.putString(PREFS_LOGGEDIN_ID_KEY, loginId);
        mEditor.commit();
    }
    public void setGroupArrayToPref(String groupPref) {
        mEditor.putString(PREFS_GROUPARRAY_KEY, groupPref);
        mEditor.commit();
    }
    public String getGroupArrayFromPref() {
        return mSharedPreferences.getString(PREFS_GROUPARRAY_KEY, null);
    }

    public void setGuestUserIdToPref(String guestUserId) {
        mEditor.putString(PREFS_GUESTUSER_ID_KEY, guestUserId);
        mEditor.commit();
    }



    public void setEmailIdToPref(String emailId) {
        mEditor.putString(PREFS_LOGIN_EMAIL_ID_KEY, emailId);
        mEditor.commit();
    }


    public String getCountryCodeFromPref() {
        return mSharedPreferences.getString(PREF_COUNTRY_CODE_KEY, null);
    }

    public void setCountryCodeToPref(String code) {
        mEditor.putString(PREF_COUNTRY_CODE_KEY, code);
        mEditor.commit();
    }


    public String getStateFromPref() {
        return mSharedPreferences.getString(PREF_STATE_KEY, null);
    }

    public void setStateFromPref(String code) {
        mEditor.putString(PREF_STATE_KEY, code);
        mEditor.commit();
    }


    public String getCityFromPref() {
        return mSharedPreferences.getString(PREF_CITY_KEY, null);
    }

    public void setCityFromPref(String code) {
        mEditor.putString(PREF_CITY_KEY, code);
        mEditor.commit();
    }

    public int getLoginFeatureFromPref() {
        return mSharedPreferences.getInt(IS_LOGIN_PREF_KEY, 0);
    }


    public void setLoginFeatureToPref(int status) {
        mEditor.putInt(IS_LOGIN_PREF_KEY, status);
        mEditor.commit();
    }

    public void setSSOLoginRedirectionURLToPref(String status) {
        mEditor.putString(SSO_LOGIN_URL_PREF_KEY, status);
        mEditor.commit();
    }
    public String getssoSSOLoginRedirectionURLFromPref() {
        return mSharedPreferences.getString(SSO_LOGIN_URL_PREF_KEY, "");
    }

    public String getssoLoginFeatureFromPref() {
        return mSharedPreferences.getString(IS_SSO_LOGIN_PREF_KEY, "");
    }


    public void setssoLoginFeatureToPref(String status) {
        mEditor.putString(IS_SSO_LOGIN_PREF_KEY,status);
        mEditor.commit();
    }


    public String getGenreArrayFromPref() {
        return mSharedPreferences.getString(GENRE_ARRAY_PREF_KEY, null);
    }

    public void setGenreArrayToPref(String status) {
        mEditor.putString(GENRE_ARRAY_PREF_KEY, status);
        mEditor.commit();
    }


    public String getGenreValuesArrayFromPref() {
        return mSharedPreferences.getString(GENRE_VALUES_ARRAY_PREF_KEY, null);
    }

    public void setGenreValuesArrayToPref(String status) {
        mEditor.putString(GENRE_VALUES_ARRAY_PREF_KEY, status);
        mEditor.commit();
    }

    public String getLanguageListFromPref() {
        return mSharedPreferences.getString(LANGUAGE_LIST_PREF, "0");
    }

    public void setLanguageListToPref(String lang) {
        mEditor.putString(LANGUAGE_LIST_PREF, lang);
        mEditor.commit();
    }


    public String getIsSubscribedFromPref() {
        return mSharedPreferences.getString(PREFS_LOGIN_ISSUBSCRIBED_KEY, "0");
    }

    public void setIsSubscribedToPref(String isSubscribed) {
        mEditor.putString(PREFS_LOGIN_ISSUBSCRIBED_KEY, isSubscribed);
        mEditor.commit();
    }


    public String getLoginStatusFromPref() {
        return mSharedPreferences.getString(PREFS_LOGGEDIN_KEY, null);
    }

    public void setLogInStatusToPref(String logIn) {
        mEditor.putString(PREFS_LOGGEDIN_KEY, logIn);
        mEditor.commit();
    }

    public String getPwdFromPref() {
        return mSharedPreferences.getString(PREFS_LOGGEDIN_PASSWORD_KEY, "");
    }

    public void setPwdToPref(String pwd) {
        mEditor.putString(PREFS_LOGGEDIN_PASSWORD_KEY, pwd);
        mEditor.commit();
    }

    public String getDispNameFromPref() {
        return mSharedPreferences.getString(PREFS_LOGIN_DISPLAY_NAME_KEY, "");
    }

    public void setDispNameToPref(String name) {
        mEditor.putString(PREFS_LOGIN_DISPLAY_NAME_KEY, name);
        mEditor.commit();
    }

    public String getDispPhoneFromPref() {
        return mSharedPreferences.getString(PREFS_LOGIN_DISPLAY_PHONE_KEY, "");
    }

    public void setDispPhoneToPref(String phone) {
        mEditor.putString(PREFS_LOGIN_DISPLAY_PHONE_KEY, phone);
        mEditor.commit();
    }

    public String getLoginProfImgFromPref() {
        return mSharedPreferences.getString(PREFS_LOGIN_PROFILE_IMAGE_KEY, "");
    }

    public void setLoginProfImgoPref(String name) {
        mEditor.putString(PREFS_LOGIN_PROFILE_IMAGE_KEY, name);
        mEditor.commit();
    }

    public String getLoginHistIdFromPref() {
        return mSharedPreferences.getString(PREFS_LOGIN_HISTORYID_KEY, "");
    }

    public void setLoginHistIdPref(String name) {
        mEditor.putString(PREFS_LOGIN_HISTORYID_KEY, name);
        mEditor.commit();
    }

    public void setSocialLoginPref(String name) {
        mEditor.putString(PREFS_SOCIAL_LOGIN_KEY, name);
        mEditor.commit();
    }
    public void setGroupTypePref(String name) {
        mEditor.putString(PREFS_GROUP_TYPE, name);
        mEditor.commit();
    }
    public String getGroupTypePref() {
        return mSharedPreferences.getString(PREFS_GROUP_TYPE, "");
    }

    public String getSocialLoginFromPref() {
        return mSharedPreferences.getString(PREFS_SOCIAL_LOGIN_KEY, "");
    }

    public String getFreeUserPref() {
        return mSharedPreferences.getString(PREFS_FOR_FREE_USER, "");
    }

    public void setFreeUserPref(String name) {
        mEditor.putString(PREFS_FOR_FREE_USER, name);
        mEditor.commit();
    }

    public String getLoginDateFromPref() {
        return mSharedPreferences.getString(PREFS_LOGIN_DATE, null);
    }

    public void setLoginDatePref(String date) {
        mEditor.putString(PREFS_LOGIN_DATE, date);
        mEditor.commit();
    }

    public void setImageWidthAndHeightForUGC(IsRegistrationEnabledOutputModel isRegistrationEnabledOutputModel) {

        if (isRegistrationEnabledOutputModel.getPosterWidth() == 0) {
            mEditor.putInt(POSTER_WIDTH, DEFAULT_POSTER_WIDTH);
        } else {
            mEditor.putInt(POSTER_WIDTH, isRegistrationEnabledOutputModel.getPosterWidth());
        }
        //store poster height
        if (isRegistrationEnabledOutputModel.getPosterHeight() == 0) {
            mEditor.putInt(POSTER_HEIGHT, DEFAULT_POSTER_HEIGHT);
        } else {
            mEditor.putInt(POSTER_HEIGHT, isRegistrationEnabledOutputModel.getPosterHeight());
        }
        //store banner width
        if (isRegistrationEnabledOutputModel.getBannerWidth() == 0) {
            mEditor.putInt(BANNER_WIDTH, DEFAULT_BANNER_WIDTH);
        } else {
            mEditor.putInt(BANNER_WIDTH, isRegistrationEnabledOutputModel.getBannerWidth());
        }

        //store banner height
        if (isRegistrationEnabledOutputModel.getBannerHeight() == 0) {
            mEditor.putInt(BANNER_HEIGHT, DEFAULT_BANNER_HEIGHT);
        } else {
            mEditor.putInt(BANNER_HEIGHT, isRegistrationEnabledOutputModel.getBannerHeight());
        }
        mEditor.commit();

    }

    public int getBannerHeight(String key) {
        return mSharedPreferences.getInt(key, DEFAULT_BANNER_HEIGHT);
    }

    public int getBannerWidth(String key) {
        return mSharedPreferences.getInt(key, DEFAULT_BANNER_WIDTH);
    }

    public int getPosterWidth(String key) {
        return mSharedPreferences.getInt(key, DEFAULT_POSTER_WIDTH);
    }

    public int getPosterHeight(String key) {
        return mSharedPreferences.getInt(key, DEFAULT_POSTER_HEIGHT);
    }

    // Settings - Stream (1)
    public void setPlayBestResolutiontoPref(String strPlayBest) {
        mEditor.putString(PLAY_BEST_RESOLUTION, strPlayBest);
        mEditor.commit();

    }

    public String getPlayBestResolutionFromPref() {
        return mSharedPreferences.getString(PLAY_BEST_RESOLUTION, "1");
    }

    // Settings - Stream (2)
    public void setNotifyStreamingOnMobileDatatoPref(String strNotifyStreamingOnMobileData) {
        mEditor.putString(NOTIFY_STREAMING_ON_MOBILE_DATA, strNotifyStreamingOnMobileData);
        mEditor.commit();

    }

    public String getNotifyStreamingOnMobileDataFromPref() {
        return mSharedPreferences.getString(NOTIFY_STREAMING_ON_MOBILE_DATA, "1");
    }




    // Settings - Download
    public void setDownloadtoPref(String strDownload) {
        mEditor.putString(DOWNLOAD_ON_WIFI_ONLY, strDownload);
        mEditor.commit();

    }

    public String getDownloadFromPref() {
        return mSharedPreferences.getString(DOWNLOAD_ON_WIFI_ONLY, "0");
    }

    // Settings - set Selected Language
    public String setSelectedLanguageTexttoPref(String strSelectedLanguageText) {
        mEditor.putString(SELECTED_LANGUAGE_TEXT, strSelectedLanguageText);
        mEditor.commit();

        return strSelectedLanguageText;
    }

    public String getSelectedLanguageTextFromPref() {
        return mSharedPreferences.getString(SELECTED_LANGUAGE_TEXT, "English");
    }

    public void clearLoginPref() {
        mEditor.remove(PREFS_LOGGEDIN_KEY);
        mEditor.remove(PREFS_LOGGEDIN_ID_KEY);
        mEditor.remove(PREFS_LOGGEDIN_PASSWORD_KEY);
        mEditor.remove(PREFS_LOGIN_EMAIL_ID_KEY);
        mEditor.remove(PREFS_LOGIN_DISPLAY_NAME_KEY);
        mEditor.remove(PREFS_LOGIN_PROFILE_IMAGE_KEY);
        mEditor.remove(PREFS_LOGIN_ISSUBSCRIBED_KEY);
        mEditor.remove(PREFS_LOGIN_DATE);
        mEditor.remove(PREFS_LOGIN_HISTORYID_KEY);
        mEditor.remove(PREFS_LANGUAGE_CHANGED);
        mEditor.remove(FRAGMENTS_CHANGED);
        mEditor.remove(FRAGMENTS_TITLE);
        mEditor.remove(FRAGMENTS_PERMALINK);
        mEditor.remove(PLAY_BEST_RESOLUTION);
        mEditor.remove(DOWNLOAD_ON_WIFI_ONLY);
        mEditor.remove(SELECTED_LANGUAGE_TEXT);
        mEditor.remove(PREFS_FOR_FREE_USER);
        mEditor.remove(PREFS_SOCIAL_LOGIN_KEY);
        mEditor.remove(PREFS_LOGIN_DISPLAY_PHONE_KEY);
        mEditor.apply();
        mEditor.commit();
    }

    /*
     * desc: saving ipaddress in preference*/
    // Settings - Notification
    public void setIpaddresstoPref(String strNotification) {
        mEditor.putString(IPADDRESS, strNotification);
        mEditor.commit();

    }

    public String getIpAddressFromPref() {
        return mSharedPreferences.getString(IPADDRESS, null);
    }

    public void setSongStatustoPref(String status) {
        mEditor.putString(IS_SONG_PLAYING, status);
        mEditor.commit();

    }

    public String getSongStatusFromPref() {
        return mSharedPreferences.getString(IS_SONG_PLAYING, "");
    }

    public void setAudioDetailsToPref(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getAudioDetailsFromPref(String key) {
        return mSharedPreferences.getString(key, null);
    }

    public void setIsPlayAllClicked(boolean value) {
        mEditor.putBoolean(IS_PLAYALL_CLICKED, value);
        mEditor.commit();
    }

    public boolean getIsPlayAllClicked() {
        return mSharedPreferences.getBoolean(IS_PLAYALL_CLICKED, false);
    }

    /*
     * @author:Subhadarshani
     * @desc:This method is used to store the status if info icon is clicked to see the polling
     * */
    public void setClickInfoToPref(boolean isInfoClicked) {
        mEditor.putBoolean(IS_INFO_CLICKED, isInfoClicked);
        mEditor.commit();

    }

    /*
     * @desc:This method is used to get the status if info icon is clicked or not.
     * */
    public boolean getClickInfoFromPref() {
        return mSharedPreferences.getBoolean(IS_INFO_CLICKED, false);
    }


    public void setPreviousSongFromPref(String item) {
        mEditor.putString(PREVIOUS_POSITION, item);
        mEditor.commit();
    }

    public String getPreviousSongFromPref() {
        return mSharedPreferences.getString(PREVIOUS_POSITION, "");
    }


    public String getAudioPlaybackSpeed() {
        return mSharedPreferences.getString(AUDIO_PLAYBACK_SPEED, "Normal");

    }
}
