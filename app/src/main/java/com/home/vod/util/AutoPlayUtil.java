package com.home.vod.util;

import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.util.Constant.authTokenStr;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.home.apisdk.apiController.GetMediaQueueAsyncTask;
import com.home.apisdk.apiModel.getMediaQueueInput;
import com.home.apisdk.apiModel.getMediaQueueOutput;
import com.home.vod.R;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;

import player.activity.Player;

public class AutoPlayUtil {
    public static String ipAddress = null;

    public static final String togglePlayback = "togglePlayback";
    public static final String enableView = "enableView";
    public static final String disableView = "disableView";
    public static final String cc = "cc";


    public static void callAutoPlayAPI(String streamUniqueId, Context mContext, Player playerModel) {
        PreferenceManager preferenceManager;
        LanguagePreference languagePreference;
        languagePreference = LanguagePreference.getLanguagePreference(mContext);
        preferenceManager = PreferenceManager.getPreferenceManager(mContext);
        FeatureHandler featureHandler;
        featureHandler = FeatureHandler.getFeaturePreference(mContext);
        if (featureHandler.getFeatureStatus(FeatureHandler.IS_AUTOPLAY_ENABLED, FeatureHandler.DEFAULT_IS_AUTOPLAY_ENABLED)) {

            getMediaQueueInput mediaQueueInput = new getMediaQueueInput();
            mediaQueueInput.setAuthToken(authTokenStr);
            mediaQueueInput.setStream_uniq_id(streamUniqueId);
            mediaQueueInput.setCountry(preferenceManager.getCountryCodeFromPref());
            mediaQueueInput.setState(preferenceManager.getStateFromPref());
            mediaQueueInput.setCity(preferenceManager.getCityFromPref());
            mediaQueueInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            mediaQueueInput.setContent_info("1");
            mediaQueueInput.setUser_id(preferenceManager.getUseridFromPref());
            GetMediaQueueAsyncTask mediaQueueAsyncTask = new GetMediaQueueAsyncTask(mediaQueueInput, new GetMediaQueueAsyncTask.GetMediaQueue() {
                @Override
                public void GetMediaQueuePreExecute() {

                }

                @Override
                public void GetMediaQueuePostExecute(getMediaQueueOutput getMediaQueueOutput, int status) {
                    if (status == 200) {
                        playerModel.setNextItem(getMediaQueueOutput.getNextMediaInfo());
                        playerModel.setPrevItem(getMediaQueueOutput.getPreviousMediaInfo());
                    }
                }
            }, mContext);
            mediaQueueAsyncTask.execute();
        } else {
            playerModel.setNextItem(null);
            playerModel.setPrevItem(null);
        }
    }


    public static void enableView(View view) {
        view.setEnabled(true);
        view.setClickable(true);
        ((ImageView) view).setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public static void disableView(View view) {
        view.setEnabled(false);
        view.setClickable(false);
        ((ImageView) view).setColorFilter(ContextCompat.getColor(view.getContext(), R.color.hint_color), android.graphics.PorterDuff.Mode.SRC_IN);
    }








}
