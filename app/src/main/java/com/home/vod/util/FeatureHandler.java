package com.home.vod.util;

import static com.home.vod.preferences.LanguagePreference.DEFAULT_ISPLAYLIST;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ISQUEUE;
import static com.home.vod.preferences.LanguagePreference.ISPLAYLIST;
import static com.home.vod.preferences.LanguagePreference.ISQUEUE;

import android.content.Context;
import android.content.SharedPreferences;

import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.IsRegistrationEnabledOutputModel;

import org.json.JSONObject;

/**
 * Created by MUVI on 08-Jan-18.
 */

public class FeatureHandler {

    private static SharedPreferences fetureSharedPref;
    private static SharedPreferences fetureSharedPref_splash_image;
    private static FeatureHandler featureHandler;
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor editor_splash_image;
    Context context;

    public static final String PrefName = "FEATURE_HANDLER";
    public static final String PrefName_splash_image = "SPLASH_IMAGE";

    public static final String IS_LOGIN_REQUIRED_FOR_FREECONTENT = "isLoginRequiredForFreeContent";
    public static final String IS_RATING = "isRating";
    public static final String IS_LOGIN = "is_login";
    public static final String SIGNUP_STEP = "signup_step";
    public static final String IS_RESTRICTIVE_DEVICE = "isRestrictDevice";
    public static final String IS_STREAMING_RESTRICTION = "is_streaming_restriction";

    public static final String IS_OFFLINE = "is_offline";
    public static final String IS_TITLE_ENABLED = "istitleenabled";
    public static final String IS_GUESTUSER_ENABLED = "isguestuser";
    public static final String IS_OVERLAY_DISPLAY = "isoverlaydisplay";
    public static final String IS_QUEUE = "isQueue";
    public static final String IS_REGISTRATION_ENABLED = "is_registration_enabled";

    public static final String IS_SUBTITLE = "isSubtitle";
    public static final String IS_RESOLUTION = "isResolution";
    public static final String IS_PURCHASEHISTORY = "isPurchaseHistory";
    public static final String MYPLAN_STATUS = "my_plan_status";
    public static final String DEFAULT_MYPLAN_STATUS = "0";
    public static final String IS_FILTER = "isFilter";
    public static final String IS_SEARCH = "isSearch";
    public static final String IS_LOGIN_REGISTRATION_REQUIRE = "is_login_registration_require";
    public static final String ENABLE_MULTI_PERMISSION_POPUP = "enable_multi_permission_popup";
    public static final String IS_FORCE_UPDATE_ENABLE = "is_force_update_enable";
    public static final String IS_FORCE_UPDATE_MANDATORY = "is_force_update_mandatory";
    public static final String IS_CHANGE_PASSWORD_ENABLED = "is_change_password";
    public static final String IS_TERMS_PRIVACY_AVAILABLE = "is_terms_privacy_available";
    public static final String IS_ABOUT_US_AVAILABLE = "is_about_us_available";
    public static final String IS_SEASON_PAGE_AVAILABLE = "is_season_page_available";


    public static final String DEFAULT_IS_SEASON_PAGE_AVAILABLE = "0";
    public static final String DEFAULT_IS_TERMS_PRIVACY_AVAILABLE = "0";
    public static final String DEFAULT_IS_ABOUT_US_AVAILABLE = "0";

    public static final String DEFAULT_IS_REGISTRATION_ENABLED = "0";
    public static final String DEFAULT_IS_LOGIN_REQUIRED_FOR_FREECONTENT = "0";
    public static final String DEFAULT_IS_RATING = "0";
    public static final String DEFAULT_IS_LOGIN = "1";
    public static final String DEFAULT_SIGNUP_STEP = "1";
    public static final String DEFAULT_IS_RESTRICTIVE_DEVICE = "0";
    public static final String DEFAULT_IS_STREAMING_RESTRICTION = "0";
    public static final String DEFAULT_IS_OFFLINE = "0";
    public static final String DEFAULT_IS_TITLE_ENABLED = "0";
    public static final String DEFAULT_IS_GUESTUSER_ENABLED = "0";
    public static final String DEFAULT_IS_OVERLAY_DISPLAY = "0";
    public static final String DEFAULT_IS_QUEUE = "0";

    public static final String DEFAULT_IS_SUBTITLE = "1";
    public static final String DEFAULT_IS_RESOLUTION = "1";
    public static final String DEFAULT_IS_PURCHASEHISTORY = "1";
    public static final String DEFAULT_IS_FILTER = "1";
    public static final String DEFAULT_IS_SEARCH = "1";
    public static final String DEFAULT_ENABLE_MULTI_PERMISSION_POPUP = "0";
    public static final String DEFAULT_IS_FORCE_UPDATE_ENABLE = "1";
    public static final String DEFAULT_IS_FORCE_UPDATE_MANDATORY = "0";
    public static final String DEFAULT_IS_CHANGE_PASSWORD_ENABLED = "1";


    public static final String IS_CACHING_ENABLED = "isCachingEnabled";
    public static final String DEFAULT_IS_CACHING_ENABLED = "1";
    public static final String DEFAULT_IS_CACHING_DISABLED = "0";
    public static final String PAYMENT_INTIGRATION_PHASE = "PAYIMENT_INTIGRATION_PHASE";
    public static final String DEFAULT_PAYMENT_INTIGRATION_PHASE = "0";

    public static final String IS_AUTOPLAY_ENABLED = "is_autoplay_enabled";
    public static final String DEFAULT_IS_AUTOPLAY_ENABLED = "0";


    /**
     * Following keys are responsible for resume watch from last seen.
     */
    public static final String IS_RESUME_WATCH_STATUS = "resume_watch_status";
    public static final String IS_RESUME_WATCH_LAST_SEEN_STATUS = "resume_watch_last_seen_status";

    public static final String DEFAULT_IS_RESUME_WATCH_STATUS = "1";
    public static final String DEFAULT_IS_RESUME_WATCH_LAST_SEEN_STATUS = "1";
    /*
     * @desc: this variable is used to skip subscription process if required. it's for Netgigs customer.
     * */
    public static final String SKIP_SUBSCRIPTION_PROCESS = "SKIP_SUBSCRIPTION_PROCESS";
    public static final String DEFAULT_SKIP_SUBSCRIPTION_PROCESS = "0";


    /*
     * @desc: this variables declared for Application log display in player page
     * */
    public static final String APP_LOGO_ON_PLAYERPAGE_ENABLED = "app_logo_on_plaerpage_enabled";
    public static final String IS_DEFAULT_APP_LOGO_ON_PLAYERPAGE_ENABLED = "0";

    /*
     * @desc: ER -36302 | For fullscreen play of content when clicked play button in Content details page .
     * By default it will play in non-fullscreen mode.
     * */
    public static final String FULL_SCREEN_MODE = "full_screen_mode";
    public static final String IS_FULL_SCREEN_MODE = "0";



    // this variable declared for SCA users
    public static final String SCA_USER_ENABLED = "sca_user_enabled";
    public static final String IS_DEFAULT_SCA_USER_ENABLED = "0";


    public static String IS_PLAYBACK_SPEED_ENABLED = "IS_PLAYBACK_SPEED";
    public static String DEFAULT_PLAYBACK_SPEED = "0";


    public static final String IS_EMULATOR_RESTRICTION_ENABLED = "is_emulator_restriction_enabled";
    public static String DEFAULT_IS_EMULATOR_RESTRICTION_ENABLED = "1";


    public static final String ASSIGN_CONTENT_TO_USER_ENABLED = "ASSIGN_CONTENT_TO_USER_ENABLED";
    public static final String IS_DEFAULT_ASSIGN_CONTENT_TO_USER_ENABLED = "0";

    public static final String SHOW_ASSIGN_CONTENT_ONLY_ENABLED = "SHOW_ASSIGN_CONTENT_ONLY_ENABLED";
    public static final String IS_DEFAULT_SHOW_ASSIGN_CONTENT_ONLY_ENABLED = "0";

    public static final String MAKE_LOGIN_AS_HOMEPAGE_ENABLED = "MAKE_LOGIN_AS_HOMEPAGE_ENABLED";
    public static final String IS_DEFAULT_MAKE_LOGIN_AS_HOMEPAGE_ENABLED = "0";

    public static final String GROUP_CATEGORY = "GROUP_CATEGORY";
    public static final String DEFAULT_GROUP_CATEGORY = "0";


    public FeatureHandler(Context context) {
        this.context = context;
        fetureSharedPref = context.getSharedPreferences(PrefName, Context.MODE_PRIVATE);
        editor = fetureSharedPref.edit();
    }

    public SharedPreferences.Editor FeatureHandlerSplashImage() {
        fetureSharedPref_splash_image = context.getSharedPreferences(PrefName_splash_image, Context.MODE_PRIVATE);
        return editor_splash_image = fetureSharedPref_splash_image.edit();
    }


    public static FeatureHandler getFeaturePreference(Context mContext) {
        if (featureHandler == null) {
            return new FeatureHandler(mContext);
        }
        return featureHandler;
    }


    public void setFeatureFlag(String key, String value) {
        editor.putString(key, value);
        editor.commit();

    }

    public void setDefaultFeaturePref(String response, IsRegistrationEnabledOutputModel isRegistrationEnabledOutputModel) {
        try {

            /**
             * Feature status which are not coming from CMS .
             */
            setFeatureFlag(IS_SUBTITLE, DEFAULT_IS_SUBTITLE);
            setFeatureFlag(IS_RESOLUTION, DEFAULT_IS_RESOLUTION);
            setFeatureFlag(IS_PURCHASEHISTORY, DEFAULT_IS_PURCHASEHISTORY);
            setFeatureFlag(IS_FILTER, DEFAULT_IS_FILTER);
            setFeatureFlag(IS_SEARCH, DEFAULT_IS_SEARCH);
            setFeatureFlag(SCA_USER_ENABLED, IS_DEFAULT_SCA_USER_ENABLED);

            setFeatureFlag(ENABLE_MULTI_PERMISSION_POPUP, DEFAULT_ENABLE_MULTI_PERMISSION_POPUP);
            setFeatureFlag(IS_CACHING_ENABLED, DEFAULT_IS_CACHING_ENABLED);
            setFeatureFlag(IS_FORCE_UPDATE_ENABLE, DEFAULT_IS_FORCE_UPDATE_ENABLE);
            setFeatureFlag(IS_FORCE_UPDATE_MANDATORY, DEFAULT_IS_FORCE_UPDATE_MANDATORY);
            setFeatureFlag(IS_CHANGE_PASSWORD_ENABLED, DEFAULT_IS_CHANGE_PASSWORD_ENABLED);


            /**
             * Feature status which are coming from CMS .
             */

            JSONObject myJson1 = new JSONObject(response);




            /**
             * Following parsing is applicable for season page feature.
             */

            try {
                if (myJson1.has("is_season_available") && myJson1.optString("is_season_available").trim() != null && !myJson1.optString("is_season_available").trim().isEmpty() && !myJson1.optString("is_season_available").trim().equals("null") && !myJson1.optString("is_season_available").trim().matches("")) {
                    setFeatureFlag(IS_SEASON_PAGE_AVAILABLE, (myJson1.optString("is_season_available")));

                } else {
                    setFeatureFlag(IS_SEASON_PAGE_AVAILABLE, DEFAULT_IS_SEASON_PAGE_AVAILABLE);
                }

            } catch (Exception e) {
                setFeatureFlag(IS_SEASON_PAGE_AVAILABLE, DEFAULT_IS_SEASON_PAGE_AVAILABLE);
                e.printStackTrace();
            }

            /**
             * Following parsing is applicable for resume watch last seen status.
             */

            try {
                if (myJson1.has("resume_watch_status") && myJson1.optString("resume_watch_status").trim() != null && !myJson1.optString("resume_watch_status").trim().isEmpty() && !myJson1.optString("resume_watch_status").trim().equals("null") && !myJson1.optString("resume_watch_status").trim().matches("")) {
                    setFeatureFlag(IS_RESUME_WATCH_STATUS, (myJson1.optString("resume_watch_status")));
                } else {
                    setFeatureFlag(IS_RESUME_WATCH_STATUS, DEFAULT_IS_RESUME_WATCH_STATUS);
                }

            } catch (Exception e) {
                setFeatureFlag(IS_RESUME_WATCH_STATUS, DEFAULT_IS_RESUME_WATCH_STATUS);
                e.printStackTrace();
            }

            try {
                if (myJson1.has("resume_watch_last_seen_status") && myJson1.optString("resume_watch_last_seen_status").trim() != null && !myJson1.optString("resume_watch_last_seen_status").trim().isEmpty() && !myJson1.optString("resume_watch_last_seen_status").trim().equals("null") && !myJson1.optString("resume_watch_last_seen_status").trim().matches("")) {
                    setFeatureFlag(IS_RESUME_WATCH_LAST_SEEN_STATUS, (myJson1.optString("resume_watch_last_seen_status")));
                } else {
                    setFeatureFlag(IS_RESUME_WATCH_LAST_SEEN_STATUS, DEFAULT_IS_RESUME_WATCH_LAST_SEEN_STATUS);
                }
            } catch (Exception e) {
                setFeatureFlag(IS_RESUME_WATCH_LAST_SEEN_STATUS, DEFAULT_IS_RESUME_WATCH_LAST_SEEN_STATUS);
                e.printStackTrace();
            }


            if (myJson1.has("is_login_required_for_freecontent") && myJson1.optString("is_login_required_for_freecontent").trim() != null && !myJson1.optString("is_login_required_for_freecontent").trim().isEmpty() && !myJson1.optString("is_login_required_for_freecontent").trim().equals("null") && !myJson1.optString("is_login_required_for_freecontent").trim().matches("")) {
                setFeatureFlag(IS_LOGIN_REQUIRED_FOR_FREECONTENT, (myJson1.optString("is_login_required_for_freecontent")));
            } else {
                setFeatureFlag(IS_LOGIN_REQUIRED_FOR_FREECONTENT, DEFAULT_IS_LOGIN_REQUIRED_FOR_FREECONTENT);
            }


            if (myJson1.has("is_login") && myJson1.optString("is_login").trim() != null && !myJson1.optString("is_login").trim().isEmpty() && !myJson1.optString("is_login").trim().equals("null") && !myJson1.optString("is_login").trim().matches("")) {
                setFeatureFlag(IS_LOGIN, (myJson1.optString("is_login")));
            } else {
                setFeatureFlag(IS_LOGIN, DEFAULT_IS_LOGIN);
            }


            if (myJson1.has("group_category") && myJson1.optString("group_category").trim() != null && !myJson1.optString("group_category").trim().isEmpty() && !myJson1.optString("group_category").trim().equals("null") && !myJson1.optString("group_category").trim().matches("")) {
                setFeatureFlag(GROUP_CATEGORY, (myJson1.optString("group_category")));
            } else {
                setFeatureFlag(GROUP_CATEGORY, DEFAULT_GROUP_CATEGORY);
            }

            if (myJson1.has("is_registration_enabled") && myJson1.optString("is_registration_enabled").trim() != null && !myJson1.optString("is_registration_enabled").trim().isEmpty() && !myJson1.optString("is_registration_enabled").trim().equals("null") && !myJson1.optString("is_registration_enabled").trim().matches("")) {
                setFeatureFlag(IS_REGISTRATION_ENABLED, (myJson1.optString("is_registration_enabled")));
            } else {
                setFeatureFlag(IS_REGISTRATION_ENABLED, DEFAULT_IS_REGISTRATION_ENABLED);
            }


            if (myJson1.has("purchase_history_status") && myJson1.optString("purchase_history_status").trim() != null && !myJson1.optString("purchase_history_status").trim().isEmpty() && !myJson1.optString("purchase_history_status").trim().equals("null") && !myJson1.optString("purchase_history_status").trim().matches("")) {
                setFeatureFlag(IS_PURCHASEHISTORY, (myJson1.optString("purchase_history_status")));
            } else {
                setFeatureFlag(IS_PURCHASEHISTORY, DEFAULT_IS_PURCHASEHISTORY);
            }

            if (myJson1.has("my_plan_status") && myJson1.optString("my_plan_status").trim() != null && !myJson1.optString("my_plan_status").trim().isEmpty() && !myJson1.optString("my_plan_status").trim().equals("null") && !myJson1.optString("my_plan_status").trim().matches("")) {
                setFeatureFlag(MYPLAN_STATUS, (myJson1.optString("my_plan_status")));
            } else {
                setFeatureFlag(MYPLAN_STATUS, DEFAULT_MYPLAN_STATUS);

            }


            /*
             * Desc: this is for logo show on player cms driven
             * */

            if (myJson1.has("is_player_logo_enabled") && myJson1.optString("is_player_logo_enabled").trim() != null && !myJson1.optString("is_player_logo_enabled").trim().isEmpty() && !myJson1.optString("is_player_logo_enabled").trim().equals("null") && !myJson1.optString("is_player_logo_enabled").trim().matches("")) {
                setFeatureFlag(APP_LOGO_ON_PLAYERPAGE_ENABLED, (myJson1.optString("is_player_logo_enabled")));
            } else {
                setFeatureFlag(APP_LOGO_ON_PLAYERPAGE_ENABLED, IS_DEFAULT_APP_LOGO_ON_PLAYERPAGE_ENABLED);
            }


            if (myJson1.has("isRating") && myJson1.optString("isRating").trim() != null && !myJson1.optString("isRating").trim().isEmpty() && !myJson1.optString("isRating").trim().equals("null") && !myJson1.optString("isRating").trim().matches("")) {
                setFeatureFlag(IS_RATING, (myJson1.optString("isRating")));
            } else {
                setFeatureFlag(IS_RATING, DEFAULT_IS_RATING);
            }

            if (myJson1.has("isRestrictDevice") && myJson1.optString("isRestrictDevice").trim() != null && !myJson1.optString("isRestrictDevice").trim().isEmpty() && !myJson1.optString("isRestrictDevice").trim().equals("null") && !myJson1.optString("isRestrictDevice").trim().matches("")) {
                setFeatureFlag(IS_RESTRICTIVE_DEVICE, (myJson1.optString("isRestrictDevice")));
            } else {
                setFeatureFlag(IS_RESTRICTIVE_DEVICE, DEFAULT_IS_RESTRICTIVE_DEVICE);
            }



            if (myJson1.has("is_streaming_restriction") && myJson1.optString("is_streaming_restriction").trim() != null && !myJson1.optString("is_streaming_restriction").trim().isEmpty() && !myJson1.optString("is_streaming_restriction").trim().equals("null") && !myJson1.optString("is_streaming_restriction").trim().matches("")) {
                setFeatureFlag(IS_STREAMING_RESTRICTION, (myJson1.optString("is_streaming_restriction")));
            } else {
                setFeatureFlag(IS_STREAMING_RESTRICTION, DEFAULT_IS_STREAMING_RESTRICTION);
            }

            if (myJson1.has("is_offline") && myJson1.optString("is_offline").trim() != null && !myJson1.optString("is_offline").trim().isEmpty() && !myJson1.optString("is_offline").trim().equals("null") && !myJson1.optString("is_offline").trim().matches("")) {
                setFeatureFlag(IS_OFFLINE, (myJson1.optString("is_offline")));
            } else {
                setFeatureFlag(IS_OFFLINE, DEFAULT_IS_OFFLINE);
            }



            if (myJson1.has("isQueue") && myJson1.optString("isQueue").trim() != null && !myJson1.optString("isQueue").trim().isEmpty() && !myJson1.optString("isQueue").trim().equals("null") && !myJson1.optString("isQueue").trim().matches("")) {
                if (myJson1.has("is_login") && myJson1.optString("is_login").equals("1") && myJson1.optString("is_login").trim() != null && !myJson1.optString("is_login").trim().isEmpty() && !myJson1.optString("is_login").trim().equals("null") && !myJson1.optString("is_login").trim().matches("") ) {
                    setFeatureFlag(IS_QUEUE, (myJson1.optString("isQueue")));
                } else {
                    setFeatureFlag(IS_QUEUE, DEFAULT_IS_QUEUE);
                }
            } else {
                setFeatureFlag(IS_QUEUE, DEFAULT_IS_QUEUE);
            }


            /**
             * @author Chitra
             * @desc: visibility of guest user enable/disable as per cms
             */

            if (myJson1.has("is_a_guestuser") && myJson1.optString("is_a_guestuser").trim() != null && !myJson1.optString("is_a_guestuser").trim().isEmpty() && !myJson1.optString("is_a_guestuser").trim().equals("null") && !myJson1.optString("is_a_guestuser").trim().matches("")) {
                setFeatureFlag(IS_GUESTUSER_ENABLED, (myJson1.optString("is_a_guestuser")));
            } else {
                setFeatureFlag(IS_GUESTUSER_ENABLED, DEFAULT_IS_GUESTUSER_ENABLED);
            }

            if (myJson1.has("is_sca_user") && myJson1.optString("is_sca_user").trim() != null && !myJson1.optString("is_sca_user").trim().isEmpty() && !myJson1.optString("is_sca_user").trim().equals("null") && !myJson1.optString("is_sca_user").trim().matches("")) {
                setFeatureFlag(SCA_USER_ENABLED, (myJson1.optString("is_sca_user")));
            } else {
                setFeatureFlag(SCA_USER_ENABLED, IS_DEFAULT_SCA_USER_ENABLED);
            }




            if (myJson1.has("assign_content_to_user") && myJson1.optString("assign_content_to_user").trim() != null && !myJson1.optString("assign_content_to_user").trim().isEmpty() && !myJson1.optString("assign_content_to_user").trim().equals("null") && !myJson1.optString("assign_content_to_user").trim().matches("")) {
                setFeatureFlag(ASSIGN_CONTENT_TO_USER_ENABLED, (myJson1.optString("assign_content_to_user")));
            } else {
                setFeatureFlag(ASSIGN_CONTENT_TO_USER_ENABLED, IS_DEFAULT_ASSIGN_CONTENT_TO_USER_ENABLED);
            }

            if (myJson1.has("show_assign_content_only") && myJson1.optString("show_assign_content_only").trim() != null && !myJson1.optString("show_assign_content_only").trim().isEmpty() && !myJson1.optString("show_assign_content_only").trim().equals("null") && !myJson1.optString("show_assign_content_only").trim().matches("")) {
                setFeatureFlag(SHOW_ASSIGN_CONTENT_ONLY_ENABLED, (myJson1.optString("show_assign_content_only")));
            } else {
                setFeatureFlag(SHOW_ASSIGN_CONTENT_ONLY_ENABLED, IS_DEFAULT_SHOW_ASSIGN_CONTENT_ONLY_ENABLED);
            }

            if (myJson1.has("make_login_as_homepage") && myJson1.optString("make_login_as_homepage").trim() != null && !myJson1.optString("make_login_as_homepage").trim().isEmpty() && !myJson1.optString("make_login_as_homepage").trim().equals("null") && !myJson1.optString("make_login_as_homepage").trim().matches("")) {
                setFeatureFlag(MAKE_LOGIN_AS_HOMEPAGE_ENABLED, (myJson1.optString("make_login_as_homepage")));
            } else {
                setFeatureFlag(MAKE_LOGIN_AS_HOMEPAGE_ENABLED, IS_DEFAULT_MAKE_LOGIN_AS_HOMEPAGE_ENABLED);
            }


            /**
             * @desc: to store value for payment_intigration_phase for now by default if 1step(phase:0) if 2nd step (phase:1)
             * signup_step:1(1-step)signup_step:2(2-step)
             */

            if (myJson1.has("signup_step") && myJson1.optString("signup_step").trim() != null && !myJson1.optString("signup_step").trim().isEmpty() && !myJson1.optString("signup_step").trim().equals("null") && !myJson1.optString("signup_step").trim().matches("")) {
                setFeatureFlag(SIGNUP_STEP, (myJson1.optString("signup_step")));

                if (myJson1.optString("signup_step").equals("1")) {
                    setFeatureFlag(PAYMENT_INTIGRATION_PHASE, "0");
                } else if (myJson1.optString("signup_step").equals("2") || myJson1.optString("signup_step").equals("0")) {
                    setFeatureFlag(PAYMENT_INTIGRATION_PHASE, "1");
                }
            } else {
                setFeatureFlag(SIGNUP_STEP, DEFAULT_SIGNUP_STEP);
            }


            /**
             * @desc : ER-62156
             */
            if(context.getPackageName().equals("edu.waldenu.stream")){
                setFeatureFlag(PAYMENT_INTIGRATION_PHASE, "1");
            }




            /*
             * @desc: keys added for audio*/

            if ((myJson1.optString("isPlayList")).trim().equals("1")) {
                setFeatureFlag(ISPLAYLIST, (myJson1.optString("isPlayList")));
            } else {
                setFeatureFlag(ISPLAYLIST, DEFAULT_ISPLAYLIST);
            }
            if ((myJson1.optString("isQueue")).trim().equals("1")) {
                setFeatureFlag(ISQUEUE, (myJson1.optString("isQueue")));
            } else {
                setFeatureFlag(ISQUEUE, DEFAULT_ISQUEUE);
            }

            if (Utils.checkForNull("is_autoplay_enabled", myJson1)) {
                setFeatureFlag(IS_AUTOPLAY_ENABLED, myJson1.optString("is_autoplay_enabled"));
            } else {
                setFeatureFlag(IS_AUTOPLAY_ENABLED, DEFAULT_IS_AUTOPLAY_ENABLED);

            }


            /**
             * @description Added to handle playback speed key coming from cms
             */
            if (Utils.checkForNull("is_playbackspeed_enabled", myJson1)) {
                setFeatureFlag(IS_PLAYBACK_SPEED_ENABLED, myJson1.optString("is_playbackspeed_enabled"));
            } else {
                setFeatureFlag(IS_PLAYBACK_SPEED_ENABLED, DEFAULT_PLAYBACK_SPEED);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean getFeatureStatus(String key, String defaultValue) {
        boolean res = fetureSharedPref.getString(key, defaultValue).equals("1");
        return res;
    }

    public String getFeatureFlag(String key, String defaultValue) {
        return fetureSharedPref.getString(key, defaultValue);
    }
}