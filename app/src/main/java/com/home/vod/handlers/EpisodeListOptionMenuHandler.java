package com.home.vod.handlers;

import static com.home.vod.preferences.LanguagePreference.BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MY_DOWNLOAD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PROFILE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PURCHASE_HISTORY;
import static com.home.vod.preferences.LanguagePreference.LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.LOGIN;
import static com.home.vod.preferences.LanguagePreference.LOGOUT;
import static com.home.vod.preferences.LanguagePreference.MY_DOWNLOAD;
import static com.home.vod.preferences.LanguagePreference.PROFILE;
import static com.home.vod.preferences.LanguagePreference.PURCHASE_HISTORY;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.home.vod.R;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;


/**
 * Created by Abhishek on 9/25/2017.
 */

public class EpisodeListOptionMenuHandler {

    Activity activity;
    FeatureHandler featureHandler;

    // Kushal
    boolean[] visibility;
    private int LOGIN_INDEX = 0;
    private int REGISTER_INDEX = 1;
    private int LANGUAGE_INDEX = 2;
    private int PROFILE_INDEX = 3;
    private int PURCHASE_INDEX = 4;
    private int LOGOUT_INDEX = 5;
    private int NOTIFICATION_INDEX = 6;

    MenuItem  profile_menu, purchage_menu, logout_menu,
            login_menu, register_menu, mydownload_menu,  mediaRouteMenuItem, menu_language,notification_menu, action_searchmenu, submenu;
//


    public EpisodeListOptionMenuHandler(Activity activity) {
        this.activity = activity;

    }


    public boolean[] createOptionMenu(Menu menu, PreferenceManager preferenceManager, LanguagePreference languagePreference, FeatureHandler featureHandler) {

        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);



        String loggedInStr = preferenceManager.getLoginStatusFromPref();
        int isLogin = preferenceManager.getLoginFeatureFromPref();


        menu_language = menu.findItem(R.id.menu_item_language);
        login_menu = menu.findItem(R.id.action_login);
        profile_menu = menu.findItem(R.id.menu_item_profile);
        purchage_menu = menu.findItem(R.id.action_purchage);
        logout_menu = menu.findItem(R.id.action_logout);
        register_menu = menu.findItem(R.id.action_register);
        mydownload_menu = menu.findItem(R.id.action_mydownload);
        action_searchmenu = menu.findItem(R.id.search);
        submenu = menu.findItem(R.id.option);


        login_menu.setTitle(languagePreference.getTextofLanguage(LOGIN, DEFAULT_LOGIN));
        register_menu.setTitle(languagePreference.getTextofLanguage(BTN_REGISTER, DEFAULT_BTN_REGISTER));
        profile_menu.setTitle(languagePreference.getTextofLanguage(PROFILE, DEFAULT_PROFILE));
        purchage_menu.setTitle(languagePreference.getTextofLanguage(PURCHASE_HISTORY, DEFAULT_PURCHASE_HISTORY));
        logout_menu.setTitle(languagePreference.getTextofLanguage(LOGOUT, DEFAULT_LOGOUT));
        mydownload_menu.setTitle(languagePreference.getTextofLanguage(MY_DOWNLOAD, DEFAULT_MY_DOWNLOAD));
        purchage_menu.setTitle(languagePreference.getTextofLanguage(PURCHASE_HISTORY, DEFAULT_PURCHASE_HISTORY));
        menu_language.setTitle(languagePreference.getTextofLanguage(LANGUAGE_POPUP_LANGUAGE, DEFAULT_LANGUAGE_POPUP_LANGUAGE));




        BitmapDrawable iconBitmap = (BitmapDrawable) submenu.getIcon();
        LayerDrawable iconLayer = new LayerDrawable(new Drawable [] { iconBitmap });
       // LayerDrawable iconLayer = (LayerDrawable) submenu.getIcon();
        // Update LayerDrawable's BadgeDrawable
        Util.setBadgeCount(activity, iconLayer, 5);
        // Kushal
        visibility = new boolean[7];

        action_searchmenu.setVisible(true);

        // Kushal
        if (preferenceManager.getLanguageListFromPref().equals("1")) {
            menu_language.setVisible(false);
            visibility[LANGUAGE_INDEX] = false;
        } else {
            menu_language.setVisible(true);
            visibility[LANGUAGE_INDEX] = true;
        }


        if (loggedInStr != null) {

            login_menu.setVisible(false);
            register_menu.setVisible(false);
            profile_menu.setVisible(true);

            // Kushal
            visibility[LOGIN_INDEX] = false;
            visibility[REGISTER_INDEX] = false;
            visibility[PROFILE_INDEX] = true;


            purchage_menu.setVisible(true);
            logout_menu.setVisible(true);

            //chitra
            if (featureHandler.getFeatureStatus(FeatureHandler.IS_PURCHASEHISTORY, FeatureHandler.DEFAULT_IS_PURCHASEHISTORY)) {
                visibility[PURCHASE_INDEX] = true;
            }else{
                visibility[PURCHASE_INDEX]= false;
            }


            visibility[LOGOUT_INDEX] = true;

            if ((featureHandler.getFeatureStatus(FeatureHandler.IS_OFFLINE, FeatureHandler.DEFAULT_IS_OFFLINE)))
                mydownload_menu.setVisible(true);
            else
                mydownload_menu.setVisible(false);


        } else if (loggedInStr == null) {

            if (isLogin == 1) {

                login_menu.setVisible(true);
                register_menu.setVisible(true);
                // Kushal
                visibility[LOGIN_INDEX] = true;
                if(featureHandler.getFeatureStatus(FeatureHandler.IS_REGISTRATION_ENABLED, FeatureHandler.DEFAULT_IS_REGISTRATION_ENABLED)) {
                    visibility[REGISTER_INDEX] = true;
                }else{
                    visibility[REGISTER_INDEX] = false;
                }



            } else {
                login_menu.setVisible(false);
                register_menu.setVisible(false);
                //Kushal
                visibility[LOGIN_INDEX] = false;
                visibility[REGISTER_INDEX] = false;

            }

            profile_menu.setVisible(false);
            purchage_menu.setVisible(false);
            logout_menu.setVisible(false);
            mydownload_menu.setVisible(false);

            visibility[PROFILE_INDEX] = false;
            visibility[PURCHASE_INDEX] = false;
            visibility[LOGOUT_INDEX] = false;

        }
        makeOldMenuInvisible();
        return visibility;
    }
    private void makeOldMenuInvisible() {
        login_menu.setVisible(false);
        register_menu.setVisible(false);
        profile_menu.setVisible(false);
        purchage_menu.setVisible(false);
        logout_menu.setVisible(false);
        mydownload_menu.setVisible(false);
        menu_language.setVisible(false);
    }
}
