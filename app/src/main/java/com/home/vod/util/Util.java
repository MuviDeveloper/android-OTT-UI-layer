package com.home.vod.util;

import static android.content.Context.MODE_PRIVATE;
import static com.home.vod.preferences.LanguagePreference.*;
import static com.home.vod.ui.activity.SplashScreen.force_update_called;
import static player.utils.Util.DEFAULT_PLAYER_AUDIO;
import static player.utils.Util.DEFAULT_PLAYER_RESOLUTION;
import static player.utils.Util.DEFAULT_PLAYER_SUBTITLE;
import static player.utils.Util.PLAYER_AUDIO;
import static player.utils.Util.PLAYER_RESOLUTION;
import static player.utils.Util.PLAYER_SUBTITLE;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ui.PlayerView;
import com.home.apisdk.apiModel.APVModel;
import com.home.apisdk.apiModel.ContentDetailsOutput;
import com.home.apisdk.apiModel.CurrencyModel;
import com.home.apisdk.apiModel.PPVModel;
import com.home.vod.BadgeDrawable;
import com.home.vod.BuildConfig;
import com.home.vod.R;
import com.home.vod.model.DataModel;
import com.home.vod.model.LanguageModel;
import com.home.vod.model.MiniControllerModel;
import com.home.vod.model.QueueModel;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.ui.PlayerAdapter;
import com.home.vod.ui.activity.LoginActivity;
import com.home.vod.ui.activity.MainActivity;
import com.home.vod.ui.activity.RegisterActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.regex.Pattern;

import player.activity.PlayerActivity;

/**
 * Created by User on 24-07-2015.
 */
public class Util {
    public static PlayerView videoSurfaceView;
    public static boolean flag = true;
    public static boolean app_should_refreshed = false;
    public static boolean restrict_showing_default_error_msg = false;
    public static boolean transaction_cancelled_by_user = false;
    public static boolean last_seen_button_clicked = false;
    public static boolean drawer_line_visibility = true;
    public static boolean itemclicked = false;
    public static String DEFAULT_IS_ONE_STEP_REGISTRATION = "0";
    public static final String UpdateGoogleid = "UpdateGoogleid";
    public static boolean review_instance_available = false;

    public static int main_menu_list_size = -2;
    public static PPVModel ppvModel = null;
    public static APVModel apvModel = null;
    public static CurrencyModel currencyModel = null;
    public static DataModel dataModel = null;
    public static ArrayList<LanguageModel> languageModel = null;


    public static ArrayList<Integer> image_orentiation = new ArrayList<>();
    public static int geoBloackSuccessStatus = 0;


    public static String GOOGLE_FCM_TOKEN = "GOOGLE_FCM_TOKEN";
    public static String DEFAULT_GOOGLE_FCM_TOKEN = "0";
    public static boolean favorite_clicked = false;
    public static boolean followed_clicked = false;
    public static boolean playButtonOnBanner = false;


    public static int check_for_subscription = 0;
    public static int playDuration_enabled = 0;
    public static int playDuration_enabled_auttoplay = 0;
    public static String complete_status_of_resume_lastseen = "0";

    public static String selected_season_id = "0";
    public static String selected_episode_id = "0";
    public static String selected_id = "0";
    public static String selected_content_title = "";

    public static String VideoResolution = "Auto";
    public static String DefaultSubtitle = "Off";
    public static boolean player_description = true;
    public static boolean landscape = true;

    public static boolean hide_pause = false;
    public static boolean call_finish_at_onUserLeaveHint = true;
    public static int ERROR_CODE_EXPIRED_AUTHTOKEN = 410;
    public static int UPDATE_INTERVAL = 1000;

    // force update
    public static boolean appConfigCalled = false;
    public static String forceUpdate_version = "";
    public static String playstore_version = "";
    public static final int RC_FORCE_UPDATE = 6969;

    public static int preorder_status = 0;

    public static boolean app_is_in_player_context = false;
    public static ArrayList<String> drawer_collapse_expand_imageview = new ArrayList<>();
    public static int image_compressed = 3;
    public static boolean hideBcakIcon = false;
    public static boolean login_registration_require = false;

    public static final int VOD = 1, AOD = 2, POD = 3, HYBRIDE = 4;

    public static int APP_TYPE = VOD;

    static String imageType = "";


    /**
     * @return
     */
    public static String getVastAdParameter() {
        JSONObject j = new JSONObject();

        try {
            JSONObject requiredJsonObj = new JSONObject();
            requiredJsonObj.put("device[make]", "Android");
            requiredJsonObj.put("device[model]", Build.MODEL);
            requiredJsonObj.put("app[bundle]", BuildConfig.APPLICATION_ID);
            requiredJsonObj.put("app[name]", "Muvi sample project");

            try {
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class);
                String serial_no = (String) get.invoke(c, "ro.serialno");
                requiredJsonObj.put("device[ifa]", serial_no);
            } catch (Exception e) {
                requiredJsonObj.put("device[ifa]", "");
                e.printStackTrace();
            }

            requiredJsonObj.put("device[devicetype]", "2");

            JSONObject optionalJsonObj = new JSONObject();
            optionalJsonObj.put("cb", "");
            optionalJsonObj.put("device[dnt]", "");
            optionalJsonObj.put("device[Imt]", "");


            j.put("required", requiredJsonObj);
            j.put("optional", optionalJsonObj);
        } catch (Exception e) {

        }


        return j.toString();
    }

    public static int isDouble(String str) {
        Double d = Double.parseDouble(str);
        int i = d.intValue();
        return i;
    }

    /*Author:chitra
     * @desc: The below method is responsible for detecting app background is white or black*/
    public static boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        if (darkness < 0.5) {
            return false; // It's a light color
        } else {
            return true; // It's a dark color
        }
    }

    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate) {

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {

            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            e.printStackTrace();
            outputDate = "";
        }
        return outputDate;

    }


    public static void setBadgeCount(Context context, LayerDrawable icon, int count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }


    public static boolean isValidMail(String email2) {
        String address = email2.toLowerCase();
        return address.contains("@");
    }

    public static boolean isValidMobileCountryCode(String code) {
        return (code.length() >= 1) && (code.length() <= 5);
    }

    public static boolean isValidOTP(String otp) {
        return (otp.length() >= 4) && (otp.length() <= 10);
    }


    public static boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 14;
        }
        return false;
    }


    /*Author:chitra
     * desc: This method will return true if url contains no-image*/

    public static boolean is_contain_noimage(String url) {
        String imagename = "";
        try {
            imagename = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
            System.out.println(imagename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagename.equals("no-image");
    }



    /**
     * Method to check portrait orientation.
     *
     * @param context
     * @return
     */
    public static boolean isOrientationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT;
    }



    /**
     * Show toast message
     *
     * @param mContext
     * @param message
     */
    public static void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }


    /**
     * Added by ANIRUDH on 01-08-2018
     * Show Alert Dialog
     *
     * @param context
     * @param title
     * @param message
     * @param positiveText
     */
    public static void showAlertDialog(Context context, @NonNull String title, @NonNull String message, @NonNull String negativeText, DialogInterface.OnClickListener negativeListener, @NonNull String positiveText, DialogInterface.OnClickListener positiveListener) {

        if (context == null)
            return;
        if (TextUtils.isEmpty(title))
            title = "";
        if (TextUtils.isEmpty(message))
            message = "";
        if (TextUtils.isEmpty(positiveText))
            positiveText = "";
        if (TextUtils.isEmpty(negativeText))
            negativeText = "Ok";

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setNegativeButton(negativeText, negativeListener);
        builder.setPositiveButton(positiveText, positiveListener);
        builder.create().show();

    }

    /**
     * @param mContext Method to show no video available alert.
     * @auther alok
     */
    public static void showNoDataAlert(Context mContext, String Msg) {
        LanguagePreference languagePreference = LanguagePreference.getLanguagePreference(mContext);
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(Msg);
        dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
        dlgAlert.setCancelable(false);
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        dlgAlert.create().show();
    }

    /**
     * @param mContext Method to show no video available alert.
     * @auther alok
     */
    public static void showNoDataAlert(Context mContext) {
        LanguagePreference languagePreference = LanguagePreference.getLanguagePreference(mContext);
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(languagePreference.getTextofLanguage(NO_VIDEO_AVAILABLE, DEFAULT_NO_VIDEO_AVAILABLE));
        dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
        dlgAlert.setCancelable(false);
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        dlgAlert.create().show();
    }

    public static void showActivateSubscriptionWatchVideoAleart(final Activity mContext, String showMsg) {
        LanguagePreference languagePreference = LanguagePreference.getLanguagePreference(mContext);
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);

        dlgAlert.setMessage(showMsg);

        dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
        dlgAlert.setCancelable(false);
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent in = new Intent(mContext, MainActivity.class);
                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        if (mContext instanceof LoginActivity) {
                            mContext.startActivity(in);
                            mContext.onBackPressed();
                        }
                        if (mContext instanceof RegisterActivity) {
                            mContext.startActivity(in);
                            mContext.onBackPressed();
                        }

                    }
                });
        dlgAlert.create().show();
    }


    /**
     * Parse language key and store in prefernces.
     *
     * @param languagePreference
     * @param jsonResponse
     * @param default_Language
     * @throws JSONException
     */

    public static void parseLanguage(LanguagePreference languagePreference, String jsonResponse, String default_Language) {
        JSONObject json = null;
        try {
            json = new JSONObject(jsonResponse);

        setTranslationLanguageToPref(languagePreference, GMAIL_SIGNIN, DEFAULT_GMAIL_SIGNIN, "google_signin", json);
        setTranslationLanguageToPref(languagePreference, GMAIL_SIGNUP, DEFAULT_GMAIL_SIGNUP, "google_signup", json);
        setTranslationLanguageToPref(languagePreference, VIEW_LESS, DEFAULT_VIEW_LESS, "view_less", json);
        setTranslationLanguageToPref(languagePreference, ALREADY_MEMBER, DEFAULT_ALREADY_MEMBER, "already_member", json);
        setTranslationLanguageToPref(languagePreference, SUBSCRIPTION_COMPLETED, DEFAULT_SUBSCRIPTION_COMPLETED, "subscription_completed", json);

        setTranslationLanguageToPref(languagePreference, ACTIAVTE_PLAN_TITLE, DEFAULT_ACTIAVTE_PLAN_TITLE, "activate_plan_title", json);
        setTranslationLanguageToPref(languagePreference, APP_NO_LONGER_ACTIVE, DEFAULT_APP_NO_LONGER_ACTIVE, "app_expired", json);

        setTranslationLanguageToPref(languagePreference, TRANSACTION_STATUS_ACTIVE, "", "transaction_status_active", json);
        setTranslationLanguageToPref(languagePreference, ADD_TO_FAV, "", "add_to_fav", json);
        setTranslationLanguageToPref(languagePreference, ADDED_TO_FAV, DEFAULT_ADDED_TO_FAV, "added_to_fav", json);
        setTranslationLanguageToPref(languagePreference, DELETE_FROM_FAV, DEFAULT_DELETE_FROM_FAV, "content_remove_favourite", json);
        setTranslationLanguageToPref(languagePreference, ENTER_EMPTY_FIELD, DEFAULT_ENTER_EMPTY_FIELD, "enter_register_fields_data", json);

        setTranslationLanguageToPref(languagePreference, ADVANCE_PURCHASE, DEFAULT_ADVANCE_PURCHASE, "advance_purchase", json);
        setTranslationLanguageToPref(languagePreference, ALERT, DEFAULT_ALERT, "alert", json);
        //setTranslationLanguageToPref(languagePreference, GOOGLE_FCM_TOKEN, DEFAULT_GOOGLE_FCM_TOKEN, "google_fcm_token", json);

        setTranslationLanguageToPref(languagePreference, EPISODE_TITLE, DEFAULT_EPISODE_TITLE, "episodes_title", json);
        //setTranslationLanguageToPref(languagePreference, GMAIL_SIGNIN, DEFAULT_GMAIL_SIGNIN, "gmail_signin", json);
        setTranslationLanguageToPref(languagePreference, SORT_ALPHA_A_Z, DEFAULT_SORT_ALPHA_A_Z, "sort_alpha_a_z", json);
        setTranslationLanguageToPref(languagePreference, SORT_ALPHA_Z_A, DEFAULT_SORT_ALPHA_Z_A, "sort_alpha_z_a", json);
        setTranslationLanguageToPref(languagePreference, LOGIN_FACEBOOK, DEFAULT_LOGIN_FACEBOOK, "login_facebook", json);
        setTranslationLanguageToPref(languagePreference, REGISTER_FACEBOOK, DEFAULT_REGISTER_FACEBOOK, "register_facebook", json);
        setTranslationLanguageToPref(languagePreference, AMOUNT, DEFAULT_AMOUNT, "amount", json);
        setTranslationLanguageToPref(languagePreference, COUPON_CANCELLED, DEFAULT_COUPON_CANCELLED, "coupon_cancelled", json);
        setTranslationLanguageToPref(languagePreference, BUTTON_APPLY, DEFAULT_BUTTON_APPLY, "btn_apply", json);
        setTranslationLanguageToPref(languagePreference, CHK_OVER_18, DEFAULT_CHK_OVER_18, "chk_over_18", json);

        setTranslationLanguageToPref(languagePreference, SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING, "sign_out_warning", json);
        setTranslationLanguageToPref(languagePreference, DISCOUNT_ON_COUPON, DEFAULT_DISCOUNT_ON_COUPON, "discount_on_coupon", json);
        setTranslationLanguageToPref(languagePreference, MY_LIBRARY, DEFAULT_MY_LIBRARY, "my_library", json);
        setTranslationLanguageToPref(languagePreference, CREDIT_CARD_CVV_HINT, DEFAULT_CREDIT_CARD_CVV_HINT, "credit_card_cvv_hint", json);
        setTranslationLanguageToPref(languagePreference, CAST, "", "cast", json);
        setTranslationLanguageToPref(languagePreference, CAST_CREW_BUTTON_TITLE, DEFAULT_CAST_CREW_BUTTON_TITLE, "cast_crew_button_title", json);
        setTranslationLanguageToPref(languagePreference, CAST_CREW_DETAIlS_TITLE, DEFAULT_CAST_CREW_DETAIlS_TITLE, "cast_crew_details", json);
        setTranslationLanguageToPref(languagePreference, CENSOR_RATING, "", "censor_rating", json);
        setTranslationLanguageToPref(languagePreference, FREE_TRIAL, DEFAULT_FREE_TRIAL, "free_trial", json);

        if (json.optString("change_password").trim() == null || json.optString("change_password").trim().equals("")) {
            setTranslationLanguageToPref(languagePreference, CHANGE_PASSWORD, DEFAULT_CHANGE_PASSWORD, "change_password", json);
        } else {
            setTranslationLanguageToPref(languagePreference, CHANGE_PASSWORD, DEFAULT_CHANGE_PASSWORD, "change_password", json);
        }
        setTranslationLanguageToPref(languagePreference, ACCESS_PERIOD_EXPIRED, DEFAULT_ACCESS_PERIOD_EXPIRED, "access_period_expired", json);
        setTranslationLanguageToPref(languagePreference, CANCEL_BUTTON, DEFAULT_CANCEL_BUTTON, "btn_cancel", json);
        setTranslationLanguageToPref(languagePreference, CANCEL_PLAN_BUTTON, DEFAULT_CANCEL_BUTTON, "btn_cancel_plan", json);
        setTranslationLanguageToPref(languagePreference, RESUME_MESSAGE, DEFAULT_RESUME_MESSAGE, "resume_watching", json);
        setTranslationLanguageToPref(languagePreference, CONTINUE_BUTTON, DEFAULT_CONTINUE_BUTTON, "continue", json);
        setTranslationLanguageToPref(languagePreference, CONTACT_US, DEFAULT_CONTACT_US, "contact_us", json);
        setTranslationLanguageToPref(languagePreference, IS_THIRDPARTY, DEFAULT_IS_THIRDPARTY, "is_thirdparty", json);
        setTranslationLanguageToPref(languagePreference, IS_CHROMCAST_ON_WHILE_AUDIOPLAY, DEFAULT_IS_CHROMCAST_ON_WHILE_AUDIOPLAY, "is_chromecast_on_while_audioplay", json);


        setTranslationLanguageToPref(languagePreference, ENTER_VOUCHER_CODE, DEFAULT_ENTER_VOUCHER_CODE, "enter_voucher_code", json);
        setTranslationLanguageToPref(languagePreference, CONFIRM_PASSWORD, DEFAULT_CONFIRM_PASSWORD, "confirm_password", json);
        setTranslationLanguageToPref(languagePreference, CREDIT_CARD_DETAILS, DEFAULT_CREDIT_CARD_DETAILS, "credit_card_detail", json);
        setTranslationLanguageToPref(languagePreference, DIRECTOR, "", "director", json);
        setTranslationLanguageToPref(languagePreference, DOWNLOAD_BUTTON_TITLE, DEFAULT_DOWNLOAD_BUTTON_TITLE, "download_button_title", json);
        setTranslationLanguageToPref(languagePreference, DESCRIPTION, "", "description", json);
        setTranslationLanguageToPref(languagePreference, HOME, DEFAULT_HOME, "home", json);

        setTranslationLanguageToPref(languagePreference, EMAIL_EXISTS, DEFAULT_EMAIL_EXISTS, "email_exists", json);
        setTranslationLanguageToPref(languagePreference, RESUME, DEFAULT_RESUME, "resume", json);
        setTranslationLanguageToPref(languagePreference, EMAIL_DOESNOT_EXISTS, DEFAULT_EMAIL_DOESNOT_EXISTS, "email_does_not_exist", json);
        setTranslationLanguageToPref(languagePreference, EMAIL_PASSWORD_INVALID, DEFAULT_EMAIL_PASSWORD_INVALID, "email_password_invalid", json);
        setTranslationLanguageToPref(languagePreference, COUPON_CODE_HINT, DEFAULT_COUPON_CODE_HINT, "coupon_code_hint", json);
        setTranslationLanguageToPref(languagePreference, SEARCH_ALERT, DEFAULT_SEARCH_ALERT, "search_alert", json);

        setTranslationLanguageToPref(languagePreference, CREDIT_CARD_NUMBER_HINT, DEFAULT_CREDIT_CARD_NUMBER_HINT, "credit_card_number_hint", json);
        setTranslationLanguageToPref(languagePreference, TEXT_EMIAL, DEFAULT_TEXT_EMIAL, "text_email", json);
        setTranslationLanguageToPref(languagePreference, TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER, "text_mobile_number", json);
        setTranslationLanguageToPref(languagePreference, TEXT_OTP, DEFAULT_TEXT_OTP, "otp", json);
        setTranslationLanguageToPref(languagePreference, GUEST_USER_TEXT, DEFAULT_GUEST_USER_TEXT, "continue_as_a_guest_user", json);
        setTranslationLanguageToPref(languagePreference, NAME_HINT, DEFAULT_NAME_HINT, "name_hint", json);
        setTranslationLanguageToPref(languagePreference, CREDIT_CARD_NAME_HINT, DEFAULT_CREDIT_CARD_NAME_HINT, "credit_card_name_hint", json);
        setTranslationLanguageToPref(languagePreference, TEXT_PASSWORD, DEFAULT_TEXT_PASSWORD, "text_password", json);

        setTranslationLanguageToPref(languagePreference, ERROR_IN_PAYMENT_VALIDATION, DEFAULT_ERROR_IN_PAYMENT_VALIDATION, "error_in_payment_validation", json);
        setTranslationLanguageToPref(languagePreference, ERROR_IN_SUBSCRIPTION, DEFAULT_ERROR_IN_SUBSCRIPTION, "error_in_subscription", json);
        setTranslationLanguageToPref(languagePreference, ERROR_TRANSACTION_PROCESS, DEFAULT_ERROR_TRANSACTION_PROCESS, "error_transc_process", json);
        setTranslationLanguageToPref(languagePreference, ERROR_IN_REGISTRATION, DEFAULT_ERROR_IN_REGISTRATION, "error_in_registration", json);
        setTranslationLanguageToPref(languagePreference, TRANSACTION_STATUS_EXPIRED, "", "transaction_status_expired", json);
        setTranslationLanguageToPref(languagePreference, DETAILS_NOT_FOUND_ALERT, DEFAULT_DETAILS_NOT_FOUND_ALERT, "details_not_found_alert", json);

        setTranslationLanguageToPref(languagePreference, FAILURE, DEFAULT_FAILURE, "failure", json);
        setTranslationLanguageToPref(languagePreference, FILTER_BY, DEFAULT_FILTER_BY, "filter_by", json);
        setTranslationLanguageToPref(languagePreference, FORGOT_PASSWORD, DEFAULT_FORGOT_PASSWORD, "forgot_password", json);
        setTranslationLanguageToPref(languagePreference, GENRE, "", "genre", json);

        setTranslationLanguageToPref(languagePreference, AGREE_TERMS, DEFAULT_AGREE_TERMS, "agree_terms", json);
        setTranslationLanguageToPref(languagePreference, ENTER_REVIEW_HERE, DEFAULT_ENTER_REVIEW_HERE, "enter_review_here", json);
        setTranslationLanguageToPref(languagePreference, TO_LOGIN, DEFAULT_TO_LOGIN, "to_login", json);
        setTranslationLanguageToPref(languagePreference, CLICK_HERE, DEFAULT_CLICK_HERE, "click_here", json);
        setTranslationLanguageToPref(languagePreference, NEED_LOGIN_TO_REVIEW, DEFAULT_NEED_LOGIN_TO_REVIEW, "need_to_login", json);
        setTranslationLanguageToPref(languagePreference, INVALID_COUPON, DEFAULT_INVALID_COUPON, "invalid_coupon", json);
        setTranslationLanguageToPref(languagePreference, INVOICE, DEFAULT_INVOICE, "invoice", json);
        setTranslationLanguageToPref(languagePreference, TITTLE, DEFAULT_TITTLE, "tittle", json);
        setTranslationLanguageToPref(languagePreference, LANGUAGE_POPUP_LANGUAGE, DEFAULT_LANGUAGE_POPUP_LANGUAGE, "language_popup_language", json);
        setTranslationLanguageToPref(languagePreference, SORT_LAST_UPLOADED, DEFAULT_SORT_LAST_UPLOADED, "sort_last_uploaded", json);

        setTranslationLanguageToPref(languagePreference, LANGUAGE_POPUP_LOGIN, DEFAULT_LANGUAGE_POPUP_LOGIN, "language_popup_login", json);
        setTranslationLanguageToPref(languagePreference, LOGIN, DEFAULT_LOGIN, "login", json);
        setTranslationLanguageToPref(languagePreference, NO_RESULT_FOUND_REFINE_YOUR_SEARCH, DEFAULT_NO_RESULT_FOUND_REFINE_YOUR_SEARCH, "no_result_found_refine_your_search", json);
        setTranslationLanguageToPref(languagePreference, FIRST_NAME, DEFAULT_FIRST_NAME, "first_name", json);
        setTranslationLanguageToPref(languagePreference, LAST_NAME, DEFAULT_LAST_NAME, "last_name", json);
        setTranslationLanguageToPref(languagePreference, LOGOUT, DEFAULT_LOGOUT, "logout", json);
        setTranslationLanguageToPref(languagePreference, LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS, "logout_success", json);
        setTranslationLanguageToPref(languagePreference, DOWNLOAD_CANCELED, DEFAULT_DOWNLOAD_CANCELED, "download_cancelled", json);

        setTranslationLanguageToPref(languagePreference, NEW_PASSWORD, DEFAULT_NEW_PASSWORD, "new_password", json);
        setTranslationLanguageToPref(languagePreference, NEW_HERE_TITLE, DEFAULT_NEW_HERE_TITLE, "new_here_title", json);
        setTranslationLanguageToPref(languagePreference, NO, DEFAULT_NO, "no", json);
        setTranslationLanguageToPref(languagePreference, NO_DATA, DEFAULT_NO_DATA, "no_data", json);
        setTranslationLanguageToPref(languagePreference, NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION, "no_internet_connection", json);
        setTranslationLanguageToPref(languagePreference, ENTER_REGISTER_FIELDS_DATA, DEFAULT_ENTER_REGISTER_FIELDS_DATA, "enter_register_fields_data", json);

        setTranslationLanguageToPref(languagePreference, NO_INTERNET_NO_DATA, DEFAULT_NO_INTERNET_NO_DATA, "no_internet_no_data", json);
        setTranslationLanguageToPref(languagePreference, NO_DETAILS_AVAILABLE, DEFAULT_NO_DETAILS_AVAILABLE, "no_details_available", json);
        setTranslationLanguageToPref(languagePreference, BUTTON_OK, DEFAULT_BUTTON_OK, "btn_ok", json);
        setTranslationLanguageToPref(languagePreference, OLD_PASSWORD, DEFAULT_OLD_PASSWORD, "old_password", json);
        setTranslationLanguageToPref(languagePreference, OOPS_INVALID_EMAIL, DEFAULT_OOPS_INVALID_EMAIL, "oops_invalid_email", json);

        setTranslationLanguageToPref(languagePreference, ORDER, DEFAULT_ORDER, "order", json);
        setTranslationLanguageToPref(languagePreference, TRANSACTION_DETAILS_ORDER_ID, "", "transaction_detail_order_id", json);
        setTranslationLanguageToPref(languagePreference, PASSWORD_RESET_LINK, DEFAULT_PASSWORD_RESET_LINK, "password_reset_link", json);
        setTranslationLanguageToPref(languagePreference, PASSWORDS_DO_NOT_MATCH, DEFAULT_PASSWORDS_DO_NOT_MATCH, "password_donot_match", json);
        setTranslationLanguageToPref(languagePreference, PAY_BY_PAYPAL, "", "pay_by_paypal", json);

        setTranslationLanguageToPref(languagePreference, BTN_PAYNOW, DEFAULT_BTN_PAYNOW, "btn_paynow", json);
        setTranslationLanguageToPref(languagePreference, PAY_WITH_CREDIT_CARD, "", "pay_with_credit_card", json);
        setTranslationLanguageToPref(languagePreference, PAYMENT_OPTIONS_TITLE, DEFAULT_PAYMENT_OPTIONS_TITLE, "payment_options_title", json);
        setTranslationLanguageToPref(languagePreference, PLAN_NAME, DEFAULT_PLAN_NAME, "plan_name", json);
        setTranslationLanguageToPref(languagePreference, PLAN_NAME, DEFAULT_PLAN_NAME, "plan_name", json);
        setTranslationLanguageToPref(languagePreference, ACTIVATE_SUBSCRIPTION_WATCH_VIDEO, DEFAULT_ACTIVATE_SUBSCRIPTION_WATCH_VIDEO, "activate_subscription_watch_video", json);
        setTranslationLanguageToPref(languagePreference, ACTIVATE_SUBSCRIPTION_FROM_WEBSITE, DEFAULT_ACTIVATE_SUBSCRIPTION_FROM_WEBSITE, "activate_subscription_from_website", json);
        setTranslationLanguageToPref(languagePreference, SKIP_BUTTON_TITLE, DEFAULT_SKIP_BUTTON_TITLE, "skip", json); //added by Piyush on 09-Aug-2018
        setTranslationLanguageToPref(languagePreference, OOPS_SOMETHING_WENT_WRONG, DEFAULT_OOPS_SOMETHING_WENT_WRONG, "oops_something_went_wrong_try_later", json); //added by Piyush on 09-Aug-2018
        setTranslationLanguageToPref(languagePreference, BTN_POST_REVIEW, DEFAULT_BTN_POST_REVIEW, "btn_post_review", json); //added by Piyush on 09-Aug-2018
        setTranslationLanguageToPref(languagePreference, SUBMIT_YOUR_RATING_TITLE, DEFAULT_SUBMIT_YOUR_RATING_TITLE, "submit_rating", json); //added by Piyush on 09-Aug-2018
        setTranslationLanguageToPref(languagePreference, NOTIFICATION_TITLE, DEFAULT_NOTIFICATION_TITLE, "notification_title", json); //added by Piyush on 17-Aug-2018

        setTranslationLanguageToPref(languagePreference, COUPON_ALERT, "", "coupon_alert", json);
        setTranslationLanguageToPref(languagePreference, FREE_FOR_COUPON, DEFAULT_FREE_FOR_COUPON, "free_for_coupon", json);
        setTranslationLanguageToPref(languagePreference, VALID_CONFIRM_PASSWORD, "", "valid_confirm_password", json);
        setTranslationLanguageToPref(languagePreference, BTN_DISCARD, DEFAULT_BTN_DISCARD, "btn_discard", json);
        setTranslationLanguageToPref(languagePreference, BTN_KEEP, DEFAULT_BTN_KEEP, "btn_keep", json);
        setTranslationLanguageToPref(languagePreference, DOWNLOAD_CANCEL, DEFAULT_DOWNLOAD_CANCEL, "download_cancelled", json);
        setTranslationLanguageToPref(languagePreference, WANT_DOWNLOAD_CANCEL, DEFAULT_WANT_DOWNLOAD_CANCEL, "want_download_cancel", json);
        setTranslationLanguageToPref(languagePreference, TRANSACTION_ORDER_ID, DEFAULT_TRANSACTION_ORDER_ID, "transaction_detail_order_id", json);
        setTranslationLanguageToPref(languagePreference, ADD_A_REVIEW, DEFAULT_ADD_A_REVIEW, "add_a_review", json);
        setTranslationLanguageToPref(languagePreference, REVIEWS, DEFAULT_REVIEWS, "reviews", json);
        setTranslationLanguageToPref(languagePreference, VALID_CONFIRM_PASSWORD, DEFAULT_VALID_CONFIRM_PASSWORD, "valid_confirm_password", json);
        setTranslationLanguageToPref(languagePreference, PROFILE, DEFAULT_PROFILE, "profile", json);
        setTranslationLanguageToPref(languagePreference, BUTTON_RESET, DEFAULT_BUTTON_RESET, "btn_reset", json);
        setTranslationLanguageToPref(languagePreference, PROFILE_UPDATED, DEFAULT_PROFILE_UPDATED, "profile_updated", json);


        setTranslationLanguageToPref(languagePreference, PURCHASE, DEFAULT_PURCHASE, "purchase", json);
        setTranslationLanguageToPref(languagePreference, TRANSACTION_DETAIL_PURCHASE_DATE, DEFAULT_TRANSACTION_DETAIL_PURCHASE_DATE, "transaction_detail_purchase_date", json);
        setTranslationLanguageToPref(languagePreference, PURCHASE_HISTORY, DEFAULT_PURCHASE_HISTORY, "purchase_history", json);
        setTranslationLanguageToPref(languagePreference, NO_PURCHASE_HISTORY, DEFAULT_NO_PURCHASE_HISTORY, "no_purchase_history", json);
        setTranslationLanguageToPref(languagePreference, MY_DOWNLOAD, DEFAULT_MY_DOWNLOAD, "my_download", json);
        setTranslationLanguageToPref(languagePreference, BTN_REGISTER, DEFAULT_BTN_REGISTER, "btn_register", json);
        setTranslationLanguageToPref(languagePreference, SORT_RELEASE_DATE, DEFAULT_SORT_RELEASE_DATE, "sort_release_date", json);

        setTranslationLanguageToPref(languagePreference, SAVE_THIS_CARD, DEFAULT_SAVE_THIS_CARD, "save_this_card", json);
        setTranslationLanguageToPref(languagePreference, TEXT_SEARCH_PLACEHOLDER, DEFAULT_TEXT_SEARCH_PLACEHOLDER, "text_search_placeholder", json);

        setTranslationLanguageToPref(languagePreference, SELECT_OPTION_TITLE, "", "select_option_title", json);
        setTranslationLanguageToPref(languagePreference, SELECT_PLAN, DEFAULT_SELECT_PLAN, "select_plan", json);
        setTranslationLanguageToPref(languagePreference, NO_DOWNLOADED_VIDEOS, DEFAULT_NO_DOWNLOADED_VIDEOS, "no_downloaded_videos_available", json);

        setTranslationLanguageToPref(languagePreference, SIGN_UP_TITLE, DEFAULT_SIGN_UP_TITLE, "signup_title", json);
        setTranslationLanguageToPref(languagePreference, DOWNLOAD_COMPLETED, DEFAULT_DOWNLOAD_COMPLETED, "download_completed", json);
        setTranslationLanguageToPref(languagePreference, SLOW_INTERNET_CONNECTION, DEFAULT_SLOW_INTERNET_CONNECTION, "slow_internet_connection", json);
        setTranslationLanguageToPref(languagePreference, SLOW_ISSUE_INTERNET_CONNECTION, "", "slow_issue_internet_connection", json);
        setTranslationLanguageToPref(languagePreference, SORRY, DEFAULT_SORRY, "sorry", json);
        setTranslationLanguageToPref(languagePreference, DOWNLOADED_ACCESS_EXPIRED, DEFAULT_DOWNLOADED_ACCESS_EXPIRED, "downloaded_access_expired", json);
        setTranslationLanguageToPref(languagePreference, GEO_BLOCKED_ALERT, DEFAULT_GEO_BLOCKED_ALERT, "geo_blocked_alert", json);
        setTranslationLanguageToPref(languagePreference, ERROR_IN_DATA_FETCHING, DEFAULT_ERROR_IN_DATA_FETCHING, "error_data_fetching", json);

        setTranslationLanguageToPref(languagePreference, SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR, "sign_out_error", json);
        setTranslationLanguageToPref(languagePreference, ALREADY_PURCHASE_THIS_CONTENT, DEFAULT_ALREADY_PURCHASE_THIS_CONTENT, "already_purchase_this_content", json);
        setTranslationLanguageToPref(languagePreference, CROSSED_MAXIMUM_LIMIT, DEFAULT_CROSSED_MAXIMUM_LIMIT, "crossed_max_limit_of_watching", json);
        setTranslationLanguageToPref(languagePreference, SORT_BY, DEFAULT_SORT_BY, "sort_by", json);
        setTranslationLanguageToPref(languagePreference, STORY_TITLE, "", "story_title", json);

        setTranslationLanguageToPref(languagePreference, BTN_SUBMIT, DEFAULT_BTN_SUBMIT, "btn_submit", json);
        setTranslationLanguageToPref(languagePreference, TRANSACTION_STATUS, DEFAULT_TRANSACTION_STATUS, "transaction_success", json);
        setTranslationLanguageToPref(languagePreference, VIDEO_ISSUE, DEFAULT_VIDEO_ISSUE, "video_issue", json);
        setTranslationLanguageToPref(languagePreference, NO_CONTENT, DEFAULT_NO_CONTENT, "no_content", json);
        setTranslationLanguageToPref(languagePreference, NO_VIDEO_AVAILABLE, DEFAULT_NO_VIDEO_AVAILABLE, "no_video_available", json);
        setTranslationLanguageToPref(languagePreference, VIDEO_NOT_PUBLISHED, DEFAULT_VIDEO_NOT_PUBLISHED, "media_not_published", json);
        setTranslationLanguageToPref(languagePreference, PLAYLIST, DEFAULT_PLAYLIST, "playlist", json);
        setTranslationLanguageToPref(languagePreference, VERIFY_EMAIL_MESSAGE, DEFAULT_VERIFY_EMAIL_MESSAGE, "verify_from_email", json);
        setTranslationLanguageToPref(languagePreference, VERIFY_MESSAGE, DEFAULT_VERIFY_MESSAGE, "verify", json);

        setTranslationLanguageToPref(languagePreference, CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY, DEFAULT_CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY, "content_not_available_in_your_country", json);
        setTranslationLanguageToPref(languagePreference, TRANSACTION_DATE, DEFAULT_TRANSACTION_DATE, "transaction_date", json);
        setTranslationLanguageToPref(languagePreference, TRANASCTION_DETAIL, DEFAULT_TRANASCTION_DETAIL, "transaction_detail", json);
        setTranslationLanguageToPref(languagePreference, TRANSACTION_STATUS, DEFAULT_TRANSACTION_STATUS, "transaction_status", json);
        setTranslationLanguageToPref(languagePreference, TRANSACTION, DEFAULT_TRANSACTION_TITLE, "transaction", json);

        setTranslationLanguageToPref(languagePreference, TRY_AGAIN, DEFAULT_TRY_AGAIN, "try_again", json);
        setTranslationLanguageToPref(languagePreference, UNPAID, "", "unpaid", json);
        setTranslationLanguageToPref(languagePreference, USE_NEW_CARD, DEFAULT_USE_NEW_CARD, "use_new_card", json);
        setTranslationLanguageToPref(languagePreference, VIEW_MORE, DEFAULT_VIEW_MORE, "view_more", json);
        setTranslationLanguageToPref(languagePreference, VIEW_ALL, DEFAULT_VIEW_ALL, "viewall", json);
        setTranslationLanguageToPref(languagePreference, VIEW_TRAILER, DEFAULT_VIEW_TRAILER, "view_trailer", json);

        setTranslationLanguageToPref(languagePreference, WATCH, "", "watch", json);
        setTranslationLanguageToPref(languagePreference, WATCH_NOW, DEFAULT_WATCH_NOW, "watch_now", json);
        setTranslationLanguageToPref(languagePreference, SIGN_OUT_ALERT, "", "sign_out_alert", json);
        setTranslationLanguageToPref(languagePreference, UPDATE_PROFILE_ALERT, DEFAULT_UPDATE_PROFILE_ALERT, "update_profile_alert", json);
        setTranslationLanguageToPref(languagePreference, YES, DEFAULT_YES, "yes", json);

        setTranslationLanguageToPref(languagePreference, PURCHASE_SUCCESS_ALERT, DEFAULT_PURCHASE_SUCCESS_ALERT, "purchase_success_alert", json);
        setTranslationLanguageToPref(languagePreference, CARD_WILL_CHARGE, DEFAULT_CARD_WILL_CHARGE, "card_will_charge", json);
        setTranslationLanguageToPref(languagePreference, SEARCH_HINT, "", "search_hint", json);
        setTranslationLanguageToPref(languagePreference, TERMS, DEFAULT_TERMS, "terms", json);
        setTranslationLanguageToPref(languagePreference, UPDATE_PROFILE, DEFAULT_UPDATE_PROFILE, "btn_update_profile", json);

        setTranslationLanguageToPref(languagePreference, APP_ON, DEFAULT_APP_ON, "app_on", json);
        setTranslationLanguageToPref(languagePreference, APP_SELECT_LANGUAGE, DEFAULT_APP_SELECT_LANGUAGE, "app_select_language", json);
        setTranslationLanguageToPref(languagePreference, FILL_FORM_BELOW, DEFAULT_FILL_FORM_BELOW, "Fill_form_below", json);


        setTranslationLanguageToPref(languagePreference, DETAILS_TITLE, DEFAULT_DETAILS_TITLE, "details_title", json);
        setTranslationLanguageToPref(languagePreference, BENEFIT_TITLE, DEFAULT_BENEFIT_TITLE, "benefit_title", json);
        setTranslationLanguageToPref(languagePreference, DIFFICULTY_TITLE, DEFAULT_DIFFICULTY_TITLE, "difficulty_title", json);
        setTranslationLanguageToPref(languagePreference, DURATION_TITLE, DEFAULT_DURATION_TITLE, "duration_title", json);

        setTranslationLanguageToPref(languagePreference, SIMULTANEOUS_LOGOUT_SUCCESS_MESSAGE, DEFAULT_SIMULTANEOUS_LOGOUT_SUCCESS_MESSAGE, "simultaneous_logout_message", json);
        setTranslationLanguageToPref(languagePreference, LOGIN_STATUS_MESSAGE, DEFAULT_LOGIN_STATUS_MESSAGE, "login_status_message", json);
        setTranslationLanguageToPref(languagePreference, FILL_FORM_BELOW, DEFAULT_FILL_FORM_BELOW, "fill_form_below", json);
        setTranslationLanguageToPref(languagePreference, MESSAGE, DEFAULT_MESSAGE, "text_message", json);
        setTranslationLanguageToPref(languagePreference, MANAGE_DEVICE, DEFAULT_MANAGE_DEVICE, "manage_device", json);

        setTranslationLanguageToPref(languagePreference, YOUR_DEVICE, DEFAULT_YOUR_DEVICE, "your_device", json);
        setTranslationLanguageToPref(languagePreference, DEREGISTER, DEFAULT_DEREGISTER, "deregister", json);
        setTranslationLanguageToPref(languagePreference, FILMOGRAPHY, DEFAULT_FILMOGRAPHY, "filmography", json);
        setTranslationLanguageToPref(languagePreference, ENTER_YOUR_MESSAGE, DEFAULT_ENTER_YOUR_MESSAGE, "text_message_placeholder", json);
        setTranslationLanguageToPref(languagePreference, NO_DEVICE_AVAILABE, DEFAULT_NO_DEVICE_AVAILABE, "no_devices_available", json);
        setTranslationLanguageToPref(languagePreference, REMOVE_DEVICE_SUCCESS, DEFAULT_REMOVE_DEVICE_SUCCESS, "remove_device_request_succ", json);

        setTranslationLanguageToPref(languagePreference, SAVE, DEFAULT_SAVE, "btn_save", json);
        setTranslationLanguageToPref(languagePreference, SAVE_OFFLINE_VIDEO, DEFAULT_SAVE_OFFLINE_VIDEO, "save_offline_video", json);
        setTranslationLanguageToPref(languagePreference, DELETE_BTN, DEFAULT_DELETE_BTN, "delete_btn", json);
        setTranslationLanguageToPref(languagePreference, SEND, DEFAULT_SEND, "btn_send", json);
        setTranslationLanguageToPref(languagePreference, CONFIRM_DELETE_MESSAGE, DEFAULT_CONFIRM_DELETE_MESSAGE, "confirm_delete_message", json);

        setTranslationLanguageToPref(languagePreference, WATCH_HISTORY, DEFAULT_WATCH_HISTORY, "watch_history", json);
        setTranslationLanguageToPref(languagePreference, SELECT_PURCHASE_TYPE, DEFAULT_SELECT_PURCHASE_TYPE, "select_purchase_type", json);
        setTranslationLanguageToPref(languagePreference, COMPLETE_SEASON, DEFAULT_COMPLETE_SEASON, "complete_season", json);
        setTranslationLanguageToPref(languagePreference, NEXT, DEFAULT_NEXT, "btn_next", json);
        setTranslationLanguageToPref(languagePreference, VOUCHER_SUCCESS, DEFAULT_VOUCHER_SUCCESS, "voucher_applied_success", json);
        setTranslationLanguageToPref(languagePreference, CLEAR_HISTORY, DEFAULT_CLEAR_HISTORY, "clear_history", json);
        setTranslationLanguageToPref(languagePreference, SORRY_ENTER_NAME, DEFAULT_SORRY_ENTER_NAME, "sorry_enter_name", json);
        setTranslationLanguageToPref(languagePreference, CLEARED_DATA, DEFAULT_CLEARED_DATA, "watch_history_cleared", json);
        setTranslationLanguageToPref(languagePreference, STOP_CASTING_ALERT, DEFAULT_STOP_CASTING_ALERT, "stop_casting_alert", json);
        setTranslationLanguageToPref(languagePreference, STOP_CHROME_CAST_ALERT, DEFAULT_STOP_CHROME_CAST_ALERT, "stop_chromecast_alert", json);

        // Force Update
        setTranslationLanguageToPref(languagePreference, FORCE_UPDATE_DIALOG_TITLE, DEFAULT_FORCE_UPDATE_DIALOG_TITLE, "new_version_available", json);
        setTranslationLanguageToPref(languagePreference, FORCE_UPDATE_DIALOG_MESSAGE, DEFAULT_FORCE_UPDATE_DIALOG_MESSAGE, "new_version_available_update_latest_version", json);
        setTranslationLanguageToPref(languagePreference, FORCE_UPDATE_DIALOG_UPDATE_BUTTON, DEFAULT_FORCE_UPDATE_DIALOG_UPDATE_BUTTON, "update", json);
        setTranslationLanguageToPref(languagePreference, FORCE_UPDATE_DIALOG_LATER_BUTTON, DEFAULT_FORCE_UPDATE_DIALOG_LATER_BUTTON, "later", json);

        // Minimum SDk cehck
        setTranslationLanguageToPref(languagePreference, DEVICE_NOT_SUPPORTED, DEFAULT_DEVICE_NOT_SUPPORTED, "device_not_suported", json);
        setTranslationLanguageToPref(languagePreference, SEARCH_RESTRICTION_MSG, DEFAULT_SEARCH_RESTRICTION_MSG, "search_restriction_msg", json);
        setTranslationLanguageToPref(languagePreference, SEARCH_RESTRICTION_MSG, DEFAULT_SEARCH_RESTRICTION_MSG, "search_restriction_msg", json);
        setTranslationLanguageToPref(languagePreference, REVIEW_DISPLAY_NAME, DEFAULT_REVIEW_DISPLAY_NAME, "review_display_name", json);
        setTranslationLanguageToPref(languagePreference, EXPANDED_CONTROL_RESTRICTION_MSG, DEFAULT_EXPANDED_CONTROL_RESTRICTION_MSG, "expanded_control_restriction_msg", json);


        /**
         * Player page resolution, subtitle and audio
         *
         */
        setTranslationLanguageToPref(languagePreference, PLAYER_RESOLUTION, DEFAULT_PLAYER_RESOLUTION, "player_resolution", json);
        setTranslationLanguageToPref(languagePreference, PLAYER_SUBTITLE, DEFAULT_PLAYER_SUBTITLE, "player_subtitle", json);
        setTranslationLanguageToPref(languagePreference, PLAYER_AUDIO, DEFAULT_PLAYER_AUDIO, "player_audio", json);
        setTranslationLanguageToPref(languagePreference, PASSWORDS_LENGTH, DEFAULT_PASSWORDS_LENGTH, "password_atleast_6_char", json);

        /**
         * language added for UGC
         *
         */

        setTranslationLanguageToPref(languagePreference, MY_UPLOAD, DEFAULT_MY_UPLOAD, "my_uploads", json);
        setTranslationLanguageToPref(languagePreference, UPLOAD_CONTENT, DEFAULT_UPLOAD_CONTENT, "upload_content", json);
        setTranslationLanguageToPref(languagePreference, LIVE_CONTENT, DEFAULT_LIVE_CONTENT, "live_content", json);
        setTranslationLanguageToPref(languagePreference, CONTENT_NAME, DEFAULT_CONTENT_NAME, "content_name", json);
        setTranslationLanguageToPref(languagePreference, CONTENT_DESCRIPTION, DEFAULT_CONTENT_DESCRIPTION, "content_description", json);
        setTranslationLanguageToPref(languagePreference, CATEGORY, DEFAULT_CATEGORY, "content_category", json);
        setTranslationLanguageToPref(languagePreference, UPLOAD_POSTER, DEFAULT_UPLOAD_POSTER, "content_upload_poster", json);
        setTranslationLanguageToPref(languagePreference, UPLOAD_BANNER, DEFAULT_UPLOAD_BANNER, "content_upload_banner", json);
        setTranslationLanguageToPref(languagePreference, BROWSE, DEFAULT_BROWSE, "btn_browse", json);
        setTranslationLanguageToPref(languagePreference, REMOVE, DEFAULT_REMOVE, "btn_remove", json);
        setTranslationLanguageToPref(languagePreference, SAVE_AND_CONTINUE, DEFAULT_SAVE_AND_CONTINUE, "btn_save_continue", json);
        setTranslationLanguageToPref(languagePreference, SHARE, DEFAULT_SHARE, "btn_share", json);
        setTranslationLanguageToPref(languagePreference, CHANGE_VIDEO, DEFAULT_CHANGE_VIDEO, "btn_changevideo", json);
        setTranslationLanguageToPref(languagePreference, ADD_VIDEO, DEFAULT_ADD_VIDEO, "btn_addvideo", json);


        setTranslationLanguageToPref(languagePreference, UPLOAD_VIDEO_WARNING, DEFAULT_UPLOAD_VIDEO_WARNING, "upload_video_warning", json);
        setTranslationLanguageToPref(languagePreference, UPLOAD_SUCCESS, DEFAULT_UPLOAD_SUCCESS, "upload_success", json);
        setTranslationLanguageToPref(languagePreference, SELECT_CATEGORY, DEFAULT_SELECT_CATEGORY, "select_category", json);
        setTranslationLanguageToPref(languagePreference, EDIT_CATEGORY, DEFAULT_EDIT_CATEGORY, "edit_category", json);
        setTranslationLanguageToPref(languagePreference, CONTENT_NAME_REQUIRED, DEFAULT_CONTENT_NAME_REQUIRED, "content_name_required", json);
        setTranslationLanguageToPref(languagePreference, ACTION, DEFAULT_ACTION, "label_action", json);
        setTranslationLanguageToPref(languagePreference, EDIT, DEFAULT_EDIT, "btn_edit", json);
        setTranslationLanguageToPref(languagePreference, SELECT_A_CATEGORY, DEFAULT_SELECT_A_CATEGORY, "select_a_category", json);
        setTranslationLanguageToPref(languagePreference, EDIT_CONTENT, DEFAULT_EDIT_CONTENT, "edit_content", json);
        setTranslationLanguageToPref(languagePreference, ADD_VIDEO_WARNING, DEFAULT_ADD_VIDEO_WARNING, "add_video_warning", json);
        setTranslationLanguageToPref(languagePreference, CHANGE_VIDEO_WARNING, DEFAULT_CHANGE_VIDEO_WARNING, "change_video_warning", json);
        setTranslationLanguageToPref(languagePreference, UPLOAD_VIDEO, DEFAULT_UPLOAD_VIDEO, "upload_video", json);
        setTranslationLanguageToPref(languagePreference, UPLOAD_VIDEO_INTERUPT_MESSAGE, DEFAULT_UPLOAD_VIDEO_INTERUPT_MESSAGE, "upload_video_status_message", json);
        setTranslationLanguageToPref(languagePreference, UPLOAD_VIDEO_COMPLETE_MESSAGE, DEFAULT_UPLOAD_VIDEO_COMPLETE_MESSAGE, "upload_video_complete_msg", json);
        setTranslationLanguageToPref(languagePreference, SUCCESS, DEFAULT_SUCCESS, "success", json);
        setTranslationLanguageToPref(languagePreference, VIDEO_UPLOADING_CANCELLED, DEFAULT_VIDEO_UPLOADING_CANCELLED, "upload_video_cancel_msg", json);
        setTranslationLanguageToPref(languagePreference, SHARING_MESSAGE, DEFAULT_SHARING_MESSAGE, "sharing_msg", json);
        setTranslationLanguageToPref(languagePreference, PENDING, DEFAULT_PENDING, "pending", json);
        setTranslationLanguageToPref(languagePreference, LANGUAGE_MISMATCH_MESSAGE, DEFAULT_LANGUAGE_MISMATCH_MESSAGE, "language_mismatch_message", json);
        setTranslationLanguageToPref(languagePreference, VIDEOS, DEFAULT_VIDEOS, "videos", json);

        /**
         * @description: key Added for no internet
         */
        setTranslationLanguageToPref(languagePreference, OOPS, DEFAULT_OOPS, "oops", json);
        setTranslationLanguageToPref(languagePreference, OR, DEFAULT_OR, "or", json);


        /**
         * language added for Follow/Unfollow
         *
         */
        setTranslationLanguageToPref(languagePreference, FOLLOW, DEFAULT_FOLLOW, "follow", json);
        setTranslationLanguageToPref(languagePreference, FOLLOWERS, DEFAULT_FOLLOWERS, "followers", json);
        setTranslationLanguageToPref(languagePreference, UNFOLLOW, DEFAULT_UNFOLLOW, "following", json);
        setTranslationLanguageToPref(languagePreference, AUDIO, DEFAULT_AUDIO, "audio", json);
        setTranslationLanguageToPref(languagePreference, VIDEO, DEFAULT_VIDEO, "video", json);
        setTranslationLanguageToPref(languagePreference, MPP_CONTENT, DEFAULT_MPP_CONTENT, "contents", json);

        setTranslationLanguageToPref(languagePreference, NO_CONTENT_FOUND, DEFAULT_NO_CONTENT_FOUND, "no_content_found_follow", json);
        setTranslationLanguageToPref(languagePreference, NO_CATEGORY_FOUND, DEFAULT_NO_CATEGORY_FOUND, "no_category_found_follow", json);
        setTranslationLanguageToPref(languagePreference, NO_CASTCREW_FOUND, DEFAULT_NO_CASTCREW_FOUND, "no_castcrew_found_follow", json);
        setTranslationLanguageToPref(languagePreference, FOLLOWLIST_CATEGORY, DEFAULT_FOLLOWLIST_CATEGORY, "category", json);
        setTranslationLanguageToPref(languagePreference, PAYMENT_LOGOUT_MSG, DEFAULT_PAYMENT_LOGOUT_MSG, "payment_logout_msg", json);
        setTranslationLanguageToPref(languagePreference, PAYMENT_LOGOUT_BTN, DEFAULT_PAYMENT_LOGOUT_BTN, "payment_logout_btn ", json);
        setTranslationLanguageToPref(languagePreference, FOLLOWINGS, DEFAULT_FOLLOWINGS, "followings", json);
        /**
         * @description: key Added for Settings
         */
        setTranslationLanguageToPref(languagePreference, SETTINGS, DEFAULT_SETTINGS, "settings", json);
        setTranslationLanguageToPref(languagePreference, OFF, DEFAULT_OFF, "off", json);
        setTranslationLanguageToPref(languagePreference, LOGGED_IN_AS, DEFAULT_LOGGED_IN_AS, "logged_in_as", json);
        setTranslationLanguageToPref(languagePreference, SIGN_IN, DEFAULT_SIGN_IN, "sign_in", json);
        setTranslationLanguageToPref(languagePreference, STREAM, DEFAULT_STREAM, "stream", json);
        setTranslationLanguageToPref(languagePreference, MANAGE_STREAMING_SETTINGS, DEFAULT_MANAGE_STREAMING_SETTINGS, "manage_stream_settings", json);
        setTranslationLanguageToPref(languagePreference, DOWNLOAD, DEFAULT_DOWNLOAD, "download", json); // is there in translation api
        setTranslationLanguageToPref(languagePreference, MANAGE_DOWNLOADS_SETTINGS, DEFAULT_MANAGE_DOWNLOADS_SETTINGS, "manage_download_settings", json);
        setTranslationLanguageToPref(languagePreference, SEE_ALL_REGISTERED_DEVICES, DEFAULT_SEE_ALL_REGISTERED_DEVICES, "see_all_registered_devices", json);
        setTranslationLanguageToPref(languagePreference, CLEAR_WATCH_HISTORY, DEFAULT_CLEAR_WATCH_HISTORY, "clear_watch_history", json); // is there in translation api
        setTranslationLanguageToPref(languagePreference, SIGN_IN_HINT, DEFAULT_SIGN_IN_HINT, "log_in_with_a_different_account", json);
        setTranslationLanguageToPref(languagePreference, CONNECTED_TO_MOBILE_NETWORK, DEFAULT_CONNECTED_TO_MOBILE_NETWORK, "connected_mobile_data", json);
        setTranslationLanguageToPref(languagePreference, DOWNLOAD_ON_WIFI_ONLY, DEFAULT_DOWNLOAD_ON_WIFI_ONLY, "download_on_wifi_only", json);
        setTranslationLanguageToPref(languagePreference, DOWNLOAD_ON_WIFI_ONLY_MSG, DEFAULT_DOWNLOAD_ON_WIFI_ONLY_MSG, "download_on_wifi_only_msg", json);
        setTranslationLanguageToPref(languagePreference, DOWNLOAD_CANCEl_WARNING_MESSAGE, DEFAULT_DOWNLOAD_CANCEl_WARNING_MESSAGE, "download_cancel_warning_msg", json);
        setTranslationLanguageToPref(languagePreference, ALWAYS_PLAY_BEST_RESOLUTION, DEFAULT_ALWAYS_PLAY_BEST_RESOLUTION, "always_play_best_resolution", json);
        setTranslationLanguageToPref(languagePreference, NOTIFY_WHEN_STREAMING_ON_MOBILE_DATA, DEFAULT_NOTIFY_WHEN_STREAMING_ON_MOBILE_DATA, "notify_when_streaming_on_mobile_data", json);
        setTranslationLanguageToPref(languagePreference, ABOUT_US, DEFAULT_ABOUT_US, "about_us", json);
        setTranslationLanguageToPref(languagePreference, TERMS_AND_CONDITIONS, DEFAULT_TERMS_AND_CONDITIONS, "terms_and_conditions", json);
        setTranslationLanguageToPref(languagePreference, BTN_REGISTER, DEFAULT_BTN_REGISTER, "register", json);
        setTranslationLanguageToPref(languagePreference, ACTION_WATCH_HISTORY, DEFAULT_ACTION_WATCH_HISTORY, "clear_watchHistory_warning", json);
        setTranslationLanguageToPref(languagePreference, SIZE_OF, DEFAULT_SIZE_OF, "size_of", json);

        /**
         * language added for Uniform payment flow
         *
         */

        setTranslationLanguageToPref(languagePreference, EXIT, DEFAULT_EXIT, "app_exit_message", json);
        setTranslationLanguageToPref(languagePreference, PHONE_NUMBER, DEFAULT_PHONE_NUMBER, "phone_number", json);
        setTranslationLanguageToPref(languagePreference, EMAIL_ADDRESS, DEFAULT_EMAIL_ADDRESS, "email_address", json);
        setTranslationLanguageToPref(languagePreference, ENTER_FISRT_NAME, DEFAULT_ENTER_FISRT_NAME, "enter_first_name", json);
        setTranslationLanguageToPref(languagePreference, ENTER_LAST_NAME, DEFAULT_ENTER_LAST_NAME, "enter_last_name", json);
        setTranslationLanguageToPref(languagePreference, ENTER_MOBILE_NO, DEFAULT_ENTER_MOBILE_NO, "enter_mobile_no", json);
        setTranslationLanguageToPref(languagePreference, PLAN, DEFAULT_PLAN, "my_plan", json);


        /**
         * @description: key Added for Link To Banner
         */
        setTranslationLanguageToPref(languagePreference, BANNER_LOGIN, DEFAULT_BANNER_LOGIN, "banner_login", json);
        setTranslationLanguageToPref(languagePreference, BANNER_REGISTER, DEFAULT_BANNER_REGISTER, "banner_register", json);
        setTranslationLanguageToPref(languagePreference, BANNER_SUBSCRIPTION, DEFAULT_BANNER_SUBSCRIPTION, "banner_subscription", json);
        setTranslationLanguageToPref(languagePreference, APPLY_COUPON_CODE, DEFAULT_APPLY_COUPON_CODE, "apply_coupon_code", json);
        setTranslationLanguageToPref(languagePreference, TRAILER, DEFAULT_TRAILER, "trailer", json);

        setTranslationLanguageToPref(languagePreference, FIRST_SONG, DEFAULT_FIRST_SONG, "no_previous_item", json);
        setTranslationLanguageToPref(languagePreference, LAST_SONG, DEFAULT_LAST_SONG, "no_next_item", json);

        /**
         * @description: key added for prompt when user try to logout when some task are on download or in download queue | ER - 26302
         */
        setTranslationLanguageToPref(languagePreference, LOGOUT_WHILE_DOWNLOADING, DEFAULT_LOGOUT_WHILE_DOWNLOADING, "logout_while_downloading", json);
        /**
         * @description: key added for Audio
         */
        setTranslationLanguageToPref(languagePreference, ADD_TO_PLAYLIST, DEFAULT_ADD_TO_PLAYLIST, "add_to_playlist", json);
        setTranslationLanguageToPref(languagePreference, ADD_TO_QUEUE, DEFAULT_ADD_TO_QUEUE, "add_to_queue", json);
        setTranslationLanguageToPref(languagePreference, NEW_PLAYLIST, DEFAULT_NEW_PLAYLIST, "new_playList", json);
        setTranslationLanguageToPref(languagePreference, PLAY_LIST, DEFAULT_PLAY_LIST, "my_playlist", json);
        setTranslationLanguageToPref(languagePreference, CREATE_YOUR_PLAYLIST, DEFAULT_CREATE_YOUR_PLAYLIST, "create_your_playlist", json);
        setTranslationLanguageToPref(languagePreference, PLAYLIST_NAME, DEFAULT_PLAYLIST_NAME, "playlist_name_hint", json);
        setTranslationLanguageToPref(languagePreference, PLAY_ALL, DEFAULT_PLAY_ALL, "play_all", json);
        setTranslationLanguageToPref(languagePreference, TRACKS, DEFAULT_TRACKS, "tracks", json);
        setTranslationLanguageToPref(languagePreference, TRACK, DEFAULT_TRACK, "track", json);
        setTranslationLanguageToPref(languagePreference, ADDED_TO_QUEUE, DEFAULT_ADDED_TO_QUEUE, "added_to_queue", json);
        setTranslationLanguageToPref(languagePreference, ALREADY_ADDED_TO_QUEUE, DEFAULT_ALREADY_ADDED_TO_QUEUE, "already_added_to_queue", json);
        setTranslationLanguageToPref(languagePreference, BLANK_PLAYLIST_NAME, DEFAULT_BLANK_PLAYLIST_NAME, "playlist_name_not_blank", json);
        setTranslationLanguageToPref(languagePreference, DELETE_PLAYLIST, DEFAULT_DELETE_PLAYLIST, "are_you_sure_to_delete_playlist", json);
        setTranslationLanguageToPref(languagePreference, CREATE_PLAYLIST, DEFAULT_CREATE_PLAYLIST, "create_playlist", json);
        setTranslationLanguageToPref(languagePreference, DELETE_PLAYLIST_MESSAGE, DEFAULT_DELETE_PLAYLIST_MESSAGE, "playlist_deleted", json);
        setTranslationLanguageToPref(languagePreference, CLEAR_QUEUE, DEFAULT_CLEAR_QUEUE, "clear_queue", json);
        setTranslationLanguageToPref(languagePreference, RELATED_CONTENT_VIDEO_TITLE, DEFAULT_RELATED_CONTENT_VIDEO_TITLE, "related_movies", json);
        setTranslationLanguageToPref(languagePreference, RELATED_CONTENT_AUDIO_TITLE, DEFAULT_RELATED_CONTENT_AUDIO_TITLE, "related_audios", json);
        setTranslationLanguageToPref(languagePreference, PLAYLIST_WARNING_MSG, DEFAULT_PLAYLIST_WARNING_MSG, "add_to_playlist_warning_msg", json);
        setTranslationLanguageToPref(languagePreference, QUEUE_WARNING_MSG, DEFAULT_QUEUE_WARNING_MSG, "add_to_queue_warning_msg", json);
        setTranslationLanguageToPref(languagePreference, EDIT_PLAYLIST, DEFAULT_EDIT_PLAYLIST, "edit_playlist_name", json);
        setTranslationLanguageToPref(languagePreference, EPISODES, DEFAULT_EPISODES, "episodes", json);
        setTranslationLanguageToPref(languagePreference, UNAUTHORIZED_MESSAGE, DEFAULT_UNAUTHORIZED_MESSAGE, "activate_subscription_watch_video", json);

        setTranslationLanguageToPref(languagePreference, CHROMECAST_CONNECTED_MESSAGE, DEFAULT_CHROMECAST_CONNECTED_MESSAGE, "chromecast_connected_success", json);
        setTranslationLanguageToPref(languagePreference, CONTENT_NOT_PURCHASED, DEFAULT_CONTENT_NOT_PURCHASED, "content_not_purchased", json);
        setTranslationLanguageToPref(languagePreference, THIRD_PARTY_CONTENT, DEFAULT_THIRD_PARTY_CONTENT, "third_part_cannot_played", json);

        /*
         * @desc: key added for SSO login
         * */
        setTranslationLanguageToPref(languagePreference, LOGIN_WITH, DEFAULT_LOGIN_WITH, "login_with", json);
        setTranslationLanguageToPref(languagePreference, PROFILE_UPDATE_RESTRICTION_MSG, DEFAULT_PROFILE_UPDATE_RESTRICTION_MSG, "profile_update_restriction_msg", json);
        setTranslationLanguageToPref(languagePreference, PASSWORD_UPDATE_RESTRICTION_MSG, DEFAULT_PASSWORD_UPDATE_RESTRICTION_MSG, "password_update_restriction_msg", json);

        /*
         * @desc: key added for IMEI number
         * */
        setTranslationLanguageToPref(languagePreference, IMEI_NUMBER, DEFAULT_IMEI_NUMBER, "show_my_imei", json);
        setTranslationLanguageToPref(languagePreference, MY_IMEI_NUMBER, DEFAULT_MY_IMEI_NUMBER, "my_imei_number", json);
        setTranslationLanguageToPref(languagePreference, NO_IMEI_FOUND, DEFAULT_NO_IMEI_FOUND, "no_imei_found", json);
        setTranslationLanguageToPref(languagePreference, MY_SERIAL_NUMBER, DEFAULT_MY_SERIAL_NUMBER, "my_serial_number", json);

        languagePreference.setLanguageSharedPrefernce(SELECTED_LANGUAGE_CODE, default_Language);


        /**
         * @description: key Added for Guest User
         */

        setTranslationLanguageToPref(languagePreference, GUESTUSER, DEFAULT_GUESTUSER, "guest_user_details", json);
        setTranslationLanguageToPref(languagePreference, GUEST_USER_TEXT, DEFAULT_GUEST_USER_TEXT, "continue_as_a_guestuser", json);
        setTranslationLanguageToPref(languagePreference, PURCHASE_SUCCESS, DEFAULT_PURCHASE_SUCCESS, "guestuser_purchase_sucess", json);
        setTranslationLanguageToPref(languagePreference, PURCHASE_SUCCESS_DESC, DEFAULT_PURCHASE_SUCCESS_DESC, "guestuser_downloadlink_msg", json);
        setTranslationLanguageToPref(languagePreference, PURCHASE_SUCCESS_DESC2, DEFAULT_PURCHASE_SUCCESS_DESC2, "guestuser_playnow_msg", json);
        setTranslationLanguageToPref(languagePreference, PLAY_NOW, DEFAULT_PLAY_NOW, "btn_watch_now", json);
        setTranslationLanguageToPref(languagePreference, DEFAULT_PLAY_LATER, DEFAULT_PLAY_LATER, "playlater_btn_text", json);
        setTranslationLanguageToPref(languagePreference, GUESTUSER_DOWNLOAD_ALERT, DEFAULT_GUESTUSER_DOWNLOAD_ALERT, "guestuser_download_alert_msg", json);
        setTranslationLanguageToPref(languagePreference, PROCEED_PAYMENT, DEFAULT_PROCEED_PAYMENT, "proceed_to_payment_text", json);
        /**
         * @description: key Added for polling
         */
        setTranslationLanguageToPref(languagePreference, SURVEY_TITLE, DEFAULT_SURVEY_TITLE, "survey_title", json);
        setTranslationLanguageToPref(languagePreference, AUDIO_PLAY_NOW, DEFAULT_AUDIO_PLAY_NOW, "play_now", json);
        setTranslationLanguageToPref(languagePreference, FEATURE_UNSUPPORTED_MESSAGE, DEFAULT_FEATURE_UNSUPPORTED_MESSAGE, "feature_unsupported_msg", json);

        /**
         * @description: key Added Notification | ER - 32333
         */
        setTranslationLanguageToPref(languagePreference, NO_NOTIFICATION, DEFAULT_NO_NOTIFICATION, "no_notification", json);
        /**
         * @description: key Added for new plan page
         */
        setTranslationLanguageToPref(languagePreference, SHOW, DEFAULT_SHOW, "show", json);
        setTranslationLanguageToPref(languagePreference, EPISODE, DEFAULT_EPISODE, "episode", json);
        setTranslationLanguageToPref(languagePreference, SEASON, DEFAULT_SEASON, "season", json);


        setTranslationLanguageToPref(languagePreference, STREET, DEFAULT_STREET, "street", json);
        setTranslationLanguageToPref(languagePreference, STATE, DEFAULT_STATE, "state", json);
        setTranslationLanguageToPref(languagePreference, CITY, DEFAULT_CITY, "city", json);
        setTranslationLanguageToPref(languagePreference, ZIP, DEFAULT_ZIP, "zip", json);

        setTranslationLanguageToPref(languagePreference, STREET_ADDRESS_VALIDATE, DEFAULT_STREET_ADDRESS_VALIDATE, "street_address_validate", json);
        setTranslationLanguageToPref(languagePreference, CITY_ADDRESS, DEFAULT_CITY_ADDRESS, "city_address", json);
        setTranslationLanguageToPref(languagePreference, STATE_REGION_PROVINCE_MESSAGE, DEFAULT_STATE_REGION_PROVINCE_MESSAGE, "state_region_province_message", json);
        setTranslationLanguageToPref(languagePreference, ENTER_ZIPCODE, DEFAULT_ENTER_ZIPCODE, "enter_zipcode", json);

        setTranslationLanguageToPref(languagePreference, MY_PLAN, DEFAULT_MY_PLAN, "plan", json);
        setTranslationLanguageToPref(languagePreference, PRICE, DEFAULT_PRICE, "price", json);

        setTranslationLanguageToPref(languagePreference, CONTENT_ADDED_TO_PLAYLIST, DEFAULT_CONTENT_ADDED_TO_PLAYLIST, "content_added_to_playlist", json);
        setTranslationLanguageToPref(languagePreference, VIDEO_ADDED_TO_PLAYLIST, DEFAULT_VIDEO_ADDED_TO_PLAYLIST, "video_added_to_playlist", json);
        setTranslationLanguageToPref(languagePreference, CANCELLED_TRANSACTION, DEFAULT_CANCELLED_TRANSACTION, "canceled_transaction", json);

        setTranslationLanguageToPref(languagePreference, PLAY, DEFAULT_PLAY, "btn_play", json);

        setTranslationLanguageToPref(languagePreference, BUY_NOW, DEFAULT_BUY_NOW, "buy_now", json);
        setTranslationLanguageToPref(languagePreference, BTN_SKIP, DEFAULT_BTN_SKIP, "btn_skip", json);
        setTranslationLanguageToPref(languagePreference, CHOOSE_PLAN, DEFAULT_CHOOSE_PLAN, "choose_plan", json);


        setTranslationLanguageToPref(languagePreference, PLAYBACK_SPEED, DEFAULT_PLAYBACK_SPEED, "playback_speed", json);
        setTranslationLanguageToPref(languagePreference, ACTIVATE, DEFAULT_ACTIVATE, "activate", json);


        /**
        For Otp
         */

        setTranslationLanguageToPref(languagePreference, BTN_SEND_OTP, DEFAULT_BTN_SEND_OTP, "send_otp", json);
        setTranslationLanguageToPref(languagePreference, BTN_RE_SEND_OTP, DEFAULT_BTN_RE_SEND_OTP, "resend_otp", json);

        /*Chitra
         * desc:plan upgrade*/


        setTranslationLanguageToPref(languagePreference, PRICE_BREAKUP, DEFAULT_PRICE_BREAKUP, "price_breakup", json);
        setTranslationLanguageToPref(languagePreference, EFFECTIVE_PRICE, DEFAULT_EFFECTIVE_PRICE, "effective_price", json);
        setTranslationLanguageToPref(languagePreference, CURRENT_PLAN, DEFAULT_CURRENT_PLAN, "current_plan", json);
        setTranslationLanguageToPref(languagePreference, NEW_PLAN, DEFAULT_NEW_PLAN, "new_plan", json);


        setTranslationLanguageToPref(languagePreference, EXIT_KIDS_MODE, DEFAULT_EXIT_KIDS_MODE, "exit_kids_mode", json);
        setTranslationLanguageToPref(languagePreference, PREORDER, DEFAULT_PREORDER, "pre_order ", json);
        setTranslationLanguageToPref(languagePreference, CHOOSE_USER_GROUP, DEFAULT_CHOOSE_USER_GROUP, "choose_user_group", json);
    }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * d
     * This method is used to set translation language from json keyword.
     *
     * @param languagePreference
     * @param lanngKey
     * @param langValue
     * @param jsonKey
     * @param jsonObject
     */
    private static void setTranslationLanguageToPref(LanguagePreference languagePreference, String lanngKey,
                                                     String langValue, String jsonKey, JSONObject jsonObject) {
        if (jsonObject.has(jsonKey) && !(jsonObject.optString(jsonKey).trim()).equals(""))
            languagePreference.setLanguageSharedPrefernce(lanngKey, jsonObject.optString(jsonKey).trim());
        else
            languagePreference.setLanguageSharedPrefernce(lanngKey, langValue);

    }


    public static boolean isTablet(Context context) {
        boolean isTablet = false;
        if (context != null) {
            isTablet = (context.getResources().getConfiguration().screenLayout
                    & Configuration.SCREENLAYOUT_SIZE_MASK)
                    >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        }
        return isTablet;
    }


    public static boolean getStreamingRestriction(LanguagePreference languagePreference) {
        return languagePreference.getTextofLanguage(IS_STREAMING_RESTRICTION, DEFAULT_IS_IS_STREAMING_RESTRICTION).equals("1");
    }

    public static void forceUpdateDialog(Context context) {
        final String packageName = context.getApplicationContext().getPackageName();
        LanguagePreference languagePreference = LanguagePreference.getLanguagePreference(context);

        if (force_update_called) {
            return;
        }

        force_update_called = true;
        String appversion = null;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            appversion = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (playstore_version != null) {
            if (!appversion.equals(playstore_version)) {
                // force update
                ((Activity) context).runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                            builder.setCancelable(false);

                            builder.setMessage(languagePreference.getTextofLanguage(FORCE_UPDATE_DIALOG_MESSAGE, DEFAULT_FORCE_UPDATE_DIALOG_MESSAGE))
                                    .setTitle(languagePreference.getTextofLanguage(FORCE_UPDATE_DIALOG_TITLE, DEFAULT_FORCE_UPDATE_DIALOG_TITLE)); // set a title

                            builder.setPositiveButton(languagePreference.getTextofLanguage(FORCE_UPDATE_DIALOG_UPDATE_BUTTON, DEFAULT_FORCE_UPDATE_DIALOG_UPDATE_BUTTON), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ((Activity) context).startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)), RC_FORCE_UPDATE);

//                                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
                                }
                            });


                            /**
                             * If force update version is not available, then we are displaying later option.
                             */

                            if ((forceUpdate_version.trim()).equals("")) {
                                builder.setNegativeButton(languagePreference.getTextofLanguage(FORCE_UPDATE_DIALOG_LATER_BUTTON, DEFAULT_FORCE_UPDATE_DIALOG_LATER_BUTTON), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        force_update_called = true;
                                        dialog.dismiss();
                                    }
                                });
                            }


                            android.app.AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            TextView tv = (TextView) alertDialog.findViewById(android.R.id.message);

                            /*  *
                             * @Desc: This for checking dialog message alignments for RTL support
                             * */
                            Configuration configuration = context.getResources().getConfiguration();
                            if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                                if (tv != null) {
                                    tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }

    }

    public static void hideKeyboard(Context context) {

        try {
            Activity act = (Activity) context;
            InputMethodManager inputManager = (InputMethodManager)
                    act.getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method will return a string format text which comes form API end , if it contains any html contnet.
     *
     * @param input
     * @return
     */
    public static String getTextViewTextFromApi(String input) {
        return "" + (input.replace("\\r\\n", "<br>").replace("\\n", "<br>").replace("\\", ""));

    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         float reqWidth, float reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, float reqWidth, float reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    /**
     * Following has been declared because of cast & play subtitle feature.
     */


    public static RelativeLayout mini_controller_layout;
    public static final String SUBTITLE_PREF = "Subtitle_Pref";
    public static final String SELECTED_SUBTITLE = "Selected_Subtitle";
    public static int SELECTED_SUBTITLE_POSITION = -1;

    public static void resetSubtitleBundle(Context context) {
        SELECTED_SUBTITLE_POSITION = -1;
        SharedPreferences prefs = context.getSharedPreferences(SUBTITLE_PREF, MODE_PRIVATE);
        prefs.edit().clear().commit();

    }


    /**
     * @param videoTypeIdStr
     * @return boolean
     * description it will check wheather the content type is video or not
     */
    public static boolean isVideoContent(String videoTypeIdStr) {
        return null != videoTypeIdStr && (videoTypeIdStr.equals("1") || videoTypeIdStr.equals("2") || videoTypeIdStr.equals("3") || videoTypeIdStr.equals("4"));
    }

    /**
     * @param videoTypeIdStr
     * @return boolean
     * description it will check wheather the content type is audio or not
     */
    public static boolean isAudioContent(String videoTypeIdStr) {
        return null != videoTypeIdStr && (videoTypeIdStr.equals("5") || videoTypeIdStr.equals("6"));
    }

    /**
     * @param videoTypeIdStr
     * @return boolean
     * description it will check wheather the content type is physical or not
     */
    public static boolean isPhysicalContent(String videoTypeIdStr) {
        return null != videoTypeIdStr && (videoTypeIdStr.equals(" "));
    }

    /**
     * @param videoTypeIdStr
     * @return boolean
     * description it will check wheather the content type is hybride or not
     */
    public static boolean isHybrideContent(String videoTypeIdStr) {

        return false;
    }

    /**
     * @param filterType,contentType
     * @return boolean
     * description it will check wheather the content type is of its respective type from VOD,AOD,POD or Hybride
     */
    public static boolean getFilterContent(int filterType, String contentType) {
        switch (filterType) {
            case VOD:
                return isVideoContent(contentType);
            case AOD:
                return isAudioContent(contentType);
            case POD:
                return isPhysicalContent(contentType);
            case HYBRIDE:
                return isHybrideContent(contentType);
            default:
                return false;

        }

    }


    //play duration message
    public static void showPlaydurationMessage(Context context, LanguagePreference languagePreference) {
        {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
            dlgAlert.setTitle(languagePreference.getTextofLanguage(ALERT, DEFAULT_ALERT));
            dlgAlert.setMessage(languagePreference.getTextofLanguage(PLAY_DURATION_ALERT, DEFAULT_PLAY_DURATION_ALERT));
            dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                    dialog.dismiss();
                }
            });
            dlgAlert.setCancelable(false);
            dlgAlert.create().show();
        }
    }



    /*
     * Added by PIYUSH on 29-Aug-2018
     * @param context
     * */
    public static Intent getPlayerIntent(Context context) {
        Intent result = new Intent(context, PlayerActivity.class);
        return result;
    }


    public static boolean passwordlengthcheck(EditText editNewPassword) {
        return editNewPassword.getText().toString().length() >= 6;
    }

    /*
     * Desc: this method is to calculate the no of columns for showing the images according to device width
     * */
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    /**
     * @decsription: Check whether the supplied activity is running in foreground or not
     */
    public static boolean isRunning(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
        if (runningProcesses != null && runningProcesses.size() > 0) {
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            //If your app is the process in foreground, then it's not in running in background
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


    /**
     * Desc: Following method is responsible for data set in chromecast minicontroller model.
     */
    public static MiniControllerModel miniControllerModel = new MiniControllerModel();

    public static void setMiniControllerModel(Context context, String title, String seasoninfo, String poster) {

        try {
            if (miniControllerModel == null) {
                miniControllerModel = new MiniControllerModel();
            }
            miniControllerModel.setContentTiltle(title);
            miniControllerModel.setSeasonTitle(seasoninfo);
            miniControllerModel.setPosterImage(poster);

            //Getting MiniController Poster Height Width
            new DetectPosterImage_Orientation(context).execute(poster);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setMiniControllerModel(Context context, String title, String seasoninfo, String poster, String castRouteName) {

        try {
            if (miniControllerModel == null) {
                miniControllerModel = new MiniControllerModel();
            }
            miniControllerModel.setContentTiltle(title);
            miniControllerModel.setSeasonTitle(seasoninfo);
            miniControllerModel.setPosterImage(poster);
            miniControllerModel.setCastRouteName(castRouteName);

            //Getting MiniController Poster Height Width
            new DetectPosterImage_Orientation(context).execute(poster);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getCurrentTime(long currentPosition) {

        String time = "00:00";
        String minute = "00";
        String second = "00";

        long sec = currentPosition / 1000;
        long min = sec / 60;
        long hours = min / 60;


        if (min / 60 > 0) {


            String min1 = "00";
            if (("" + (min % 60)).length() == 1)
                min1 = "0" + min % 60;
            else
                min1 = "" + min % 60;

            String sec1 = "00";
            if (("" + (sec % 60)).length() == 1)
                sec1 = "0" + sec % 60;
            else
                sec1 = "" + sec % 60;

            time = hours + ":" + min1 + ":" + sec1;
        } else {


            if (sec > 59) {
                if (("" + min).length() == 1)
                    minute = "0" + min;
                else
                    minute = "" + min;

                if (("" + sec % 60).length() == 1)
                    second = "0" + sec % 60;
                else
                    second = "" + sec % 60;

                time = minute + ":" + second;
            } else {

                minute = "00";

                if (("" + sec).length() == 1)
                    second = "0" + sec;
                else
                    second = "" + sec;

                time = minute + ":" + second;
            }


        }

        return time;
    }


    public static String getRemainingTime(long remainingTime) {

        String time = "00:00";
        String minute = "00";
        String second = "00";

        long sec = remainingTime / 1000;
        long min = sec / 60;
        long hours = min / 60;

        if (min / 60 > 0) {
            String min1 = "00";
            if (("" + (min % 60)).length() == 1)
                min1 = "0" + min % 60;
            else
                min1 = "" + min % 60;

            String sec1 = "00";
            if (("" + (sec % 60)).length() == 1)
                sec1 = "0" + sec % 60;
            else
                sec1 = "" + sec % 60;

            time = hours + ":" + min1 + ":" + sec1;
        } else {

            if (sec > 59) {
                if (("" + min).length() == 1)
                    minute = "0" + min;
                else
                    minute = "" + min;

                if (("" + sec % 60).length() == 1)
                    second = "0" + sec % 60;
                else
                    second = "" + sec % 60;

                time = minute + ":" + second;
            } else {

                minute = "00";

                if (("" + sec).length() == 1)
                    second = "0" + sec;
                else
                    second = "" + sec;

                time = minute + ":" + second;
            }

        }

        return "-" + time;
    }

    public static Activity activity;


    //AOD
    public static String DATABASE_NAME = "DemoDataBase";
    public static String ADAPTOR_TABLE_NAME = "AdaptorData";
    public static String USER_TABLE_NAME = "UserData";
    public static String ArtistName;
    public static String SongNameGlobal;
    public static String MultiAndSingParentnameGlobal;
    public static String SongImage = "";
    public static boolean DownloadChange = false;
    public static boolean Is_Skip_Button = false;
    public static Timer updateProgressTimer = null;
    public static int Duration = 0;
    public static ArrayList<QueueModel> QUEUE_ARRAY = new ArrayList<>();
    public static int GetCurrentPosition = 0;
    public static boolean MianActivityDestoryed = false;
    public static ArrayList<PlayerAdapter.ViewHolder> multiholder = new ArrayList<>();
    public static int adapterposition;
    public static String playlist_type_clicked = "";
    public static RecyclerView recyclerView;
    public static RelativeLayout addplaylist_btn_layout;
    public static CoordinatorLayout minicontroller;
    public static boolean isMusicPlaying = false;

    /*
     * @desc:this method is used to set margin to the parent layout if audio minicontroller is visible*/
    public static void setMargin(CoordinatorLayout minicontroller, RecyclerView recyclerView, Context context) {
        if (minicontroller.getVisibility() == View.VISIBLE) {
            try {


                int bottomMargin = (int) (context.getResources().getDimension(R.dimen.list_bottom_margin) / context.getResources().getDisplayMetrics().density);
                ViewGroup.MarginLayoutParams marginLayoutParams =
                        (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
                marginLayoutParams.setMargins(0, 6, 0, bottomMargin);
                recyclerView.setLayoutParams(marginLayoutParams);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ViewGroup.MarginLayoutParams params =
                        (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
                params.setMargins(0, 6, 0, 0);
                recyclerView.setLayoutParams(params);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /*
     * @desc:this method is used to set margin to the parent layout if audio minicontroller is visible*/
    public static void setMargin(CoordinatorLayout minicontroller, RelativeLayout relativeLayout, Context context) {
        if (minicontroller.getVisibility() == View.VISIBLE) {
            try {
                int bottomMargin = (int) (context.getResources().getDimension(R.dimen.list_bottom_margin) / context.getResources().getDisplayMetrics().density);
                ViewGroup.MarginLayoutParams marginLayoutParams =
                        (ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams();
                marginLayoutParams.setMargins(0, 6, 0, bottomMargin);
                relativeLayout.setLayoutParams(marginLayoutParams);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ViewGroup.MarginLayoutParams params =
                        (ViewGroup.MarginLayoutParams) relativeLayout.getLayoutParams();
                params.setMargins(0, 6, 0, 0);
                relativeLayout.setLayoutParams(params);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public static String convertPrice(String price_amount) {

        String price = "";

        try {
            String price_ary[] = ("" + price_amount.trim()).split("\\.");
            if (price_ary.length == 1)
                price = price_ary[0];
            else if (price_ary.length == 2) {
                if (price_ary[1].startsWith("0")) {
                    price = price_ary[0];
                } else {
                    price = price_ary[0] + "." + price_ary[1];
                }
            }

        } catch (Exception e) {
            e.toString();
        }

        return price;
    }

    public static float roundFloat(float f, int places) {

        BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.FLOOR);
        return bigDecimal.floatValue();
    }


    static class DetectPosterImage_Orientation extends AsyncTask<String, Void, Void> {

        Context mContext;

        public DetectPosterImage_Orientation(Context mContext) {
            this.mContext = mContext;
        }

        protected Void doInBackground(String... urls) {
            try {


                if (urls[0] != null) {
                    Bitmap myImage = Picasso.with(mContext).load(urls[0]).get();
                    int width = myImage.getWidth();
                    int height = myImage.getHeight();

                    if (height > width) {
                        imageType = "potrait";
                    } else {
                        imageType = "landscape";
                    }

                }

                return null;
            } catch (Exception e) {
                return null;
            }

        }

        protected void onPostExecute(Void feed) {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    /**
     * To check if the device is an emulator or not
     *
     * @return true/false
     */
    public static boolean isEmulator() {
        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("vbox86p")
                || Build.BOARD == "QC_Reference_Phone" //bluestacks
                || Build.HOST.startsWith("Build") //MSI App Player
                || Build.PRODUCT.contains("emulator")
                || Build.BOARD.toLowerCase().contains("nox")
                || Build.BOOTLOADER.toLowerCase().contains("nox")
                || Build.HARDWARE.toLowerCase().contains("nox")
                || Build.PRODUCT.toLowerCase().contains("nox")
                || Build.SERIAL.toLowerCase().contains("nox")
                || Build.PRODUCT.contains("simulator");

    }






    /*Author:Biswajit
     * desc: Centralized code for play button Show/Hide */

    public static boolean getPlayButtonStatus(ContentDetailsOutput contentDetailsOutput, Context context) {

        FeatureHandler featureHandler = FeatureHandler.getFeaturePreference(context);
        String total_series = contentDetailsOutput.getTotal_series();

        if (contentDetailsOutput.getContentTypesId() == 4) {
            if (contentDetailsOutput.getIs_live() == 1)
                return true;
            else
                return false;

        } else if (contentDetailsOutput.getContentTypesId() == 3 && featureHandler.getFeatureStatus(FeatureHandler.IS_RESUME_WATCH_LAST_SEEN_STATUS, FeatureHandler.DEFAULT_IS_RESUME_WATCH_LAST_SEEN_STATUS)
                && contentDetailsOutput.getIsEpisode().equals("0") && !total_series.equals("0")) {
            return true;
        } else {

            if (contentDetailsOutput.getIs_preorder() == 1) {
                return false;
            } else if (contentDetailsOutput.getIs_preorder() == 0) {
                return true;
            } else if (contentDetailsOutput.getIsConverted() == 1 && contentDetailsOutput.getIs_media_published().equals("1") && (contentDetailsOutput.getIsApv() == 0)) {
                return true;
            } else {
                return false;
            }
        }


    }

    /*Author:Biswajit
     * desc: Detect Poster Type -Landscape or Potrait */

    public static String POTRAIT_TYPE = "portrait";
    public static String LANDSCAPE_TYPE = "landscape";

    public static String posterType(int posterWidth, int videoHeight) {
        if (posterWidth < videoHeight) {
            return POTRAIT_TYPE;
        } else {
            return LANDSCAPE_TYPE;
        }
    }
}