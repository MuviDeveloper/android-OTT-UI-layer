package com.home.vod.ui.activity;

import static android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_NORMAL;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_SMALL;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_XLARGE;
import static com.home.vod.preferences.LanguagePreference.APP_SELECT_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_APP_SELECT_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NOTIFICATION_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;

import static com.home.vod.preferences.LanguagePreference.DEFAULT_PROFILE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PURCHASE_HISTORY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SEASON;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_WARNING;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_YES;
import static com.home.vod.preferences.LanguagePreference.LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.LOGIN;
import static com.home.vod.preferences.LanguagePreference.LOGOUT;
import static com.home.vod.preferences.LanguagePreference.LOGOUT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.NO;
import static com.home.vod.preferences.LanguagePreference.NOTIFICATION_TITLE;
import static com.home.vod.preferences.LanguagePreference.NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.PROFILE;
import static com.home.vod.preferences.LanguagePreference.PURCHASE_HISTORY;
import static com.home.vod.preferences.LanguagePreference.SEASON;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.SIGN_OUT_WARNING;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.YES;
import static com.home.vod.util.Constant.CAST_INTENT_KEY;
import static com.home.vod.util.Constant.CENSOR_RATING_INTENT_KEY;
import static com.home.vod.util.Constant.GENRE_INTENT_KEY;
import static com.home.vod.util.Constant.PERMALINK_INTENT_KEY;
import static com.home.vod.util.Constant.SEASON_INTENT_KEY;
import static com.home.vod.util.Constant.VIDEO_TITLE_INTENT_KEY;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.Util.languageModel;
import static com.home.vod.util.Util.resetSubtitleBundle;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.home.apisdk.apiController.GetEpisodeDeatailsAsynTask;
import com.home.apisdk.apiController.GetGenreListAsynctask;
import com.home.apisdk.apiController.GetIpAddressAsynTask;
import com.home.apisdk.apiController.GetLanguageListAsynTask;
import com.home.apisdk.apiController.GetTranslateLanguageAsync;
import com.home.apisdk.apiController.LogoutAsynctask;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
import com.home.apisdk.apiModel.APVModel;
import com.home.apisdk.apiModel.CurrencyModel;
import com.home.apisdk.apiModel.Episode_Details_input;
import com.home.apisdk.apiModel.Episode_Details_output;
import com.home.apisdk.apiModel.GenreListInput;
import com.home.apisdk.apiModel.GenreListOutput;
import com.home.apisdk.apiModel.GetVideoDetailsInput;
import com.home.apisdk.apiModel.LanguageListInputModel;
import com.home.apisdk.apiModel.LanguageListOutputModel;
import com.home.apisdk.apiModel.LogoutInput;
import com.home.apisdk.apiModel.PPVModel;
import com.home.apisdk.apiModel.SortByModel;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.vod.BuildConfig;
import com.home.vod.R;
import com.home.vod.handlers.EpisodeListOptionMenuHandler;
import com.home.vod.handlers.LoginRegistrationOnContentClickHandler;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.model.DataModel;
import com.home.vod.model.EpisodesListModel;
import com.home.vod.model.LanguageModel;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.adapter.EpisodesListViewMoreAdapter;
import com.home.vod.ui.adapter.LanguageCustomAdapter;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import player.activity.AdPlayerActivity;
import player.activity.Player;


/**
 * Created by Muvi on 2/6/2017.
 */
public class Episode_list_Activity extends AppCompatActivity implements
        VideoDetailsAsynctask.VideoDetailsListener,
        GetEpisodeDeatailsAsynTask.GetEpisodeDetailsListener,
        GetLanguageListAsynTask.GetLanguageListListener,
        LogoutAsynctask.LogoutListener,
        GetTranslateLanguageAsync.GetTranslateLanguageInfoListener,
        GetIpAddressAsynTask.IpAddressListener,
        GetGenreListAsynctask.GenreListListener {

    public Toolbar mActionBarToolbar;
    public ToolbarTitleHandler toolbarTitleHandler;
    String filename = "";
    static File mediaStorageDir;

    ArrayList<String> SubTitleName = new ArrayList<>();
    ArrayList<String> SubTitlePath = new ArrayList<>();
    ArrayList<String> FakeSubTitlePath = new ArrayList<>();
    ArrayList<String> ResolutionFormat = new ArrayList<>();
    ArrayList<String> ResolutionUrl = new ArrayList<>();
    ArrayList<String> SubTitleLanguage = new ArrayList<>();
    public static final int VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE = 8888;
    public static final int VIDEO_PLAY_BUTTON_CLICK_SUBSCRIPTION_REQUESTCODE = 9898;
    public static final int PAYMENT_REQUESTCODE = 8889;
    VideoDetailsAsynctask asynLoadVideoUrls;
    GetEpisodeDeatailsAsynTask asynEpisodeDetails;
    EpisodesListModel itemToPlay;
    String permalinkStr;
    ArrayList<EpisodesListModel> itemData = new ArrayList<EpisodesListModel>();
    LinearLayoutManager mLayoutManager;
    boolean firstTime = false;
    private EpisodesListViewMoreAdapter customListAdapter;
    public static boolean isLoading = false;
    int TOTAL_EPISODE_COUNT = 0;

    RecyclerView episodelist;
    private RelativeLayout noInternetConnectionLayout;
    RelativeLayout noDataLayout;
    TextView noDataTextView;
    TextView noInternetTextView;
    Player playerModel;
    int isVoucher = 0;

    int corePoolSize = 60;
    int maximumPoolSize = 80;
    int keepAliveTime = 10;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

    //for resume play
    String seek_status = "";
    int Played_Length = 0;
    String watch_status_String = "start";
    String resume_time = "0";
    int offset = 1;
    int limit = 10;
    int listSize = 0;
    int itemsInServer = 0;
    int isLogin = 0;

    int isPPV = 0;
    int isAPV = 0;
    int isConverted = 0;
    int isFreeContent = 0;

    //for title and season name
    String title = "", season_value = "";

    ProgressBarHandler pDialog;
    RelativeLayout footerView;
    private PreferenceManager preferenceManager;


    PPVModel ppvmodel;
    APVModel advmodel;
    CurrencyModel currencymodel;
    String movieNameStr = "";

    String email, id;
    LanguageCustomAdapter languageCustomAdapter;
    AlertDialog alert;
    String default_Language = "";
    String Previous_Selected_Language = "";
    int prevPosition = 0;
    ProgressBarHandler progressBarHandler;
    LanguagePreference languagePreference;
    FeatureHandler featureHandler;
    private String ipAddressStr = "";
    private EpisodeListOptionMenuHandler episodeListOptionMenuHandler;

    // Kushal
    int option_menu_id[] = {R.id.login, R.id.register, R.id.language_popup, R.id.profile, R.id.purchase, R.id.logout, R.id.notification};
    PopupWindow changeSortPopUp;
    LinearLayout linearLayout[];
    boolean[] visibility;
    String[] lang;
    boolean item_clicked = false;
    //

    Menu menu;
    public static TextView badge_text;
    public static TextView noti;
    public static FrameLayout noti_layout;
    int Count = 0;

    int videoHeight = 185;
    int videoWidth = 256;
    int sampleSize = 100;
    String is_livestream_enable;
    String livestream_resume_time;


    public void resetData() {
        if (itemData != null && itemData.size() > 0) {
            itemData.clear();
        }
        firstTime = true;

        offset = 1;
        isLoading = false;
        listSize = 0;
        if (((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) || ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_XLARGE)) {
            limit = 20;
        } else {
            limit = 15;
        }
        itemsInServer = 0;
    }



    @Override
    public void onVideoDetailsPreExecuteStarted() {
        pDialog = new ProgressBarHandler(Episode_list_Activity.this);
        pDialog.show();

    }

    @Override
    public void onVideoDetailsPostExecuteCompleted(Video_Details_Output _video_details_output, int statusCode, String stus, String message) {
        item_clicked = false;

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }
        } catch (IllegalArgumentException ex) {
            playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
            // movieThirdPartyUrl = getResources().getString(R.string.no_data_str);
        }

        boolean play_video = true;

        if (featureHandler.getFeatureStatus(FeatureHandler.IS_STREAMING_RESTRICTION, FeatureHandler.DEFAULT_IS_STREAMING_RESTRICTION) && preferenceManager.getUseridFromPref() != null) {

            play_video = !_video_details_output.getStreaming_restriction().trim().equals("0");
        } else {
            play_video = true;
        }
        if (!play_video) {


            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Episode_list_Activity.this, R.style.MyAlertDialogStyle);
            dlgAlert.setMessage(message);
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

            return;
        }


        if (statusCode == 200) {

            playerModel.setNo_of_row(_video_details_output.getNo_of_row());
            playerModel.setNo_of_column(_video_details_output.getNo_of_column());
            playerModel.setThumb_interval(_video_details_output.getThumb_interval());
            playerModel.setVtt_with_sprite(_video_details_output.getVtt_with_sprite());
            playerModel.setVtt_without_sprite(_video_details_output.getVtt_without_sprite());
            playerModel.setMultiple_sprite_HostPrefix(_video_details_output.getMultiple_sprite_HostPrefix());
            playerModel.setSprite_image(_video_details_output.getSprite_image());

            playerModel.setIsOffline(_video_details_output.getIs_offline());
            playerModel.setSkipIntro(_video_details_output.getSkipIntroStartPosition(), _video_details_output.getSkipIntroEndPosition());
            playerModel.setDownloadStatus(_video_details_output.getDownload_status());
            if (_video_details_output.getThirdparty_url() == null || _video_details_output.getThirdparty_url().matches("")) {

                /**@bishal
                 * for drm player below condition added
                 * if studio_approved_url is there in api then set the videourl from this other wise goto 2nd one
                 */

                if (_video_details_output.getStudio_approved_url() != null &&
                        !_video_details_output.getStudio_approved_url().isEmpty() &&
                        !_video_details_output.getStudio_approved_url().equals("null") &&
                        !_video_details_output.getStudio_approved_url().matches("")) {
                    playerModel.setVideoUrl(_video_details_output.getStudio_approved_url());

                    if (_video_details_output.getLicenseUrl().trim() != null && !_video_details_output.getLicenseUrl().trim().isEmpty() && !_video_details_output.getLicenseUrl().trim().equals("null") && !_video_details_output.getLicenseUrl().trim().matches("")) {
                        playerModel.setLicenseUrl(_video_details_output.getLicenseUrl());
                    }
                    if (_video_details_output.getVideoUrl().trim() != null && !_video_details_output.getVideoUrl().isEmpty() && !_video_details_output.getVideoUrl().equals("null") && !_video_details_output.getVideoUrl().trim().matches("")) {
                        playerModel.setMpdVideoUrl(_video_details_output.getVideoUrl());

                    } else {
                        playerModel.setMpdVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
                    }
                } else {
                    if (_video_details_output.getVideoUrl() != null || !_video_details_output.getVideoUrl().matches("")) {
                        playerModel.setVideoUrl(_video_details_output.getVideoUrl());
                        playerModel.setThirdPartyPlayer(false);
                    } else {
                        //  Util.dataModel.setVideoUrl(translatedLanuage.getNoData());
                        playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));

                    }
                }
            } else {
                if (_video_details_output.getThirdparty_url() != null || !_video_details_output.getThirdparty_url().matches("")) {
                    playerModel.setVideoUrl(_video_details_output.getThirdparty_url());
                    playerModel.setThirdPartyPlayer(true);

                } else {
                    //  Util.dataModel.setVideoUrl(translatedLanuage.getNoData());
                    playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));

                }
            }

            Util.dataModel.setVideoResolution(_video_details_output.getVideoResolution());

            playerModel.setVideoResolution(_video_details_output.getVideoResolution());
            if (_video_details_output.getPlayed_length() != null && !_video_details_output.getPlayed_length().equals(""))
                playerModel.setPlayPos((Util.isDouble(_video_details_output.getPlayed_length())));


            /**
             * Following code is responsible for multiple subtitle support for cast&play
             */

            if (!playerModel.isThirdPartyPlayer()) {

                resetSubtitleBundle(Episode_list_Activity.this);
            }



            SubTitleName = _video_details_output.getSubTitleName();
            SubTitleLanguage = _video_details_output.getSubTitleLanguage();

            //dependency for datamodel
            Util.dataModel.setVideoUrl(_video_details_output.getVideoUrl());
            Util.dataModel.setVideoResolution(_video_details_output.getVideoResolution());
            Util.dataModel.setThirdPartyUrl(_video_details_output.getThirdparty_url());
            Util.dataModel.setAdNetworkId(_video_details_output.getAdNetworkId());
            Util.dataModel.setChannel_id(_video_details_output.getChannel_id());
            Util.dataModel.setPreRoll(_video_details_output.getPreRoll());
            Util.dataModel.setPostRoll(_video_details_output.getPostRoll());
            Util.dataModel.setMidRoll(_video_details_output.getMidRoll());
            Util.dataModel.setAdDetails(_video_details_output.getAdDetails());
            Util.dataModel.setPlayPos(Util.isDouble(_video_details_output.getPlayed_length()));

            //player model set
            playerModel.setVastAdTag(_video_details_output.getVast_ad_tag());
            playerModel.setSubTitleName(_video_details_output.getSubTitleName());
            playerModel.setSubTitlePath(_video_details_output.getSubTitlePath());
            playerModel.setSubTitleLanguage(_video_details_output.getSubTitleLanguage());
            playerModel.setResolutionFormat(_video_details_output.getResolutionFormat());
            playerModel.setResolutionUrl(_video_details_output.getResolutionUrl());
            playerModel.setFakeSubTitlePath(_video_details_output.getFakeSubTitlePath());
            playerModel.setVideoResolution(_video_details_output.getVideoResolution());
            FakeSubTitlePath = _video_details_output.getFakeSubTitlePath();
            playerModel.setSubTitleLanguage(_video_details_output.getSubTitleLanguage());
            playerModel.setOfflineUrl(_video_details_output.getOfflineUrl());
            playerModel.setOfflineLanguage(_video_details_output.getOfflineLanguage());
            playerModel.setPlayPos(Util.isDouble(_video_details_output.getPlayed_length()));

            /**
             * Treat content as Live Stream
             */
            is_livestream_enable = _video_details_output.getIs_livestream_enabled();
            livestream_resume_time = _video_details_output.getLivestream_resume_time();
            if (is_livestream_enable.equals("1")) {
                playerModel.setContentTypesId(4);
                playerModel.setLivestream_resume_time(Integer.parseInt(livestream_resume_time));
            }

            if (_video_details_output.isWatermark_status()) {
                playerModel.setWaterMark(true);
                if (_video_details_output.isWatermark_email())
                    playerModel.useEmail(true);
                else
                    playerModel.useEmail(false);
                if (_video_details_output.isWatermark_mobile())
                    playerModel.useMobile(true);
                else
                    playerModel.useMobile(false);
                if (_video_details_output.isWatermark_ip())
                    playerModel.useIp(true);
                else
                    playerModel.useIp(false);
                if (_video_details_output.isWatermark_date())
                    playerModel.useDate(true);
                else
                    playerModel.useDate(false);
            } else {
                playerModel.setWaterMark(false);
            }

            if (playerModel.getVideoUrl() == null ||
                    playerModel.getVideoUrl().matches("")) {

                Util.showNoDataAlert(Episode_list_Activity.this);

            } else {


                // condition for checking if the response has third party url or not.
                if (_video_details_output.getThirdparty_url() == null ||
                        _video_details_output.getThirdparty_url().matches("")
                ) {

 {

                        playerModel.setThirdPartyPlayer(false);
                        final Intent playVideoIntent;

                        if (Util.dataModel.getAdNetworkId() == 3) {

                            playVideoIntent = Util.getPlayerIntent(Episode_list_Activity.this);

                        } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                            if (Util.dataModel.getPlayPos() <= 0) {
                                playVideoIntent = new Intent(Episode_list_Activity.this, AdPlayerActivity.class);
                            } else {
                                playVideoIntent = Util.getPlayerIntent(Episode_list_Activity.this);

                            }

                        } else {
                            playVideoIntent = Util.getPlayerIntent(Episode_list_Activity.this);

                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                if (FakeSubTitlePath.size() > 0) {
                                    // This Portion Will Be changed Later.

                                    File dir = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getApplicationContext().getPackageName().trim() + "/SubTitleList/");
                                    if (dir.isDirectory()) {
                                        String[] children = dir.list();
                                        for (int i = 0; i < children.length; i++) {
                                            new File(dir, children[i]).delete();
                                        }
                                    }

                                    progressBarHandler = new ProgressBarHandler(Episode_list_Activity.this);
                                    progressBarHandler.show();
                                    Download_SubTitle(FakeSubTitlePath.get(0).trim());
                                } else {
                                    playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                                    playVideoIntent.putExtra("PlayerModel", playerModel);
                                    startActivity(playVideoIntent);
                                }

                            }
                        });
                    }
                } else {
                    final Intent playVideoIntent = Util.getPlayerIntent(Episode_list_Activity.this);
                    playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                    playVideoIntent.putExtra("PlayerModel", playerModel);
                    startActivity(playVideoIntent);

                }
            }

        } else if (statusCode == 436) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Episode_list_Activity.this, R.style.MyAlertDialogStyle);
            dlgAlert.setMessage(message);
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

        } else {
            playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
            playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
            Util.showNoDataAlert(Episode_list_Activity.this);
        }


    }



    @Override
    public void onGetEpisodeDetailsPreExecuteStarted() {
        pDialog = new ProgressBarHandler(Episode_list_Activity.this);
        pDialog.show();
    }

    @Override
    public void onGetEpisodeDetailsPostExecuteCompleted(Episode_Details_output episode_details_output, int status, int total_item_count, String message, String movieUniqueId) {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
        } catch (IllegalArgumentException ex) {
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }

        TOTAL_EPISODE_COUNT = total_item_count;

        isAPV = episode_details_output.getIsAPV();
        isPPV = episode_details_output.getIs_ppv();
        movieNameStr = episode_details_output.getName();
        for (int a = 0; a < episode_details_output.getEpisodeArray().size(); a++) {

            String episodeNoStr = episode_details_output.getEpisodeArray().get(a).getEpisode_number();
            String episodeStoryStr = episode_details_output.getEpisodeArray().get(a).getEpisode_story();
            String episodeDateStr = episode_details_output.getEpisodeArray().get(a).getEpisode_date();
            String episodeImageStr = episode_details_output.getEpisodeArray().get(a).getPoster_url();
            String episodeTitleStr = episode_details_output.getEpisodeArray().get(a).getEpisode_title();
            String episodeSeriesNoStr = episode_details_output.getEpisodeArray().get(a).getSeries_number();
            String episodeMovieStreamUniqueIdStr = episode_details_output.getEpisodeArray().get(a).getMovie_stream_uniq_id();
            String episodeThirdParty = episode_details_output.getEpisodeArray().get(a).getThirdparty_url();
            int episodeContenTTypesId = episode_details_output.getEpisodeArray().get(a).getContent_types_id();
            String videodurationStr = episode_details_output.getEpisodeArray().get(a).getVideo_duration();
            String episodeVideoUrlStr = episode_details_output.getEpisodeArray().get(a).getVideo_url();
            String free_content = episode_details_output.getEpisodeArray().get(a).getIsFree();
            String id = episode_details_output.getEpisodeArray().get(a).getId();
            String is_livestream_enabled = episode_details_output.getEpisodeArray().get(a).getIs_livestream_enabled();

            itemData.add(new EpisodesListModel(episodeNoStr, episodeStoryStr, episodeDateStr,
                    episodeImageStr, episodeTitleStr, episodeVideoUrlStr, episodeSeriesNoStr,
                    movieUniqueId, episodeMovieStreamUniqueIdStr, episodeThirdParty, videodurationStr, episodeContenTTypesId, free_content, 0, "", "", id, is_livestream_enabled));

        }


        Util.currencyModel = episode_details_output.getCurrencyDetails();
        Util.apvModel = episode_details_output.getApvDetails();
        Util.ppvModel = episode_details_output.getPpvDetails();

        if (itemData.size() <= 0) {
            footerView.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.GONE);
            episodelist.setVisibility(View.VISIBLE);
            noInternetConnectionLayout.setVisibility(View.GONE);
            AsynLOADUI loadui = new AsynLOADUI();
            loadui.executeOnExecutor(threadPoolExecutor);
        } else {
            footerView.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.GONE);
            noInternetConnectionLayout.setVisibility(View.GONE);
            episodelist.setVisibility(View.VISIBLE);
            RetrieveFeedTask task = new RetrieveFeedTask();
            task.execute(itemData.get(0).getEpisodeThumbnailImageView());
        }

    }


    @Override
    public void onGetLanguageListPreExecuteStarted() {
        pDialog = new ProgressBarHandler(Episode_list_Activity.this);
        pDialog.show();
    }

    @Override
    public void onGetLanguageListPostExecuteCompleted(ArrayList<LanguageListOutputModel> languageListOutputArray, int status, String message, String defaultLanguage) {
        if (pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }

        ArrayList<LanguageModel> languageModels = new ArrayList<LanguageModel>();

        for (int i = 0; i < languageListOutputArray.size(); i++) {
            String language_id = languageListOutputArray.get(i).getLanguageCode();
            String language_name = languageListOutputArray.get(i).getLanguageName();


            LanguageModel languageModel = new LanguageModel();
            languageModel.setLanguageId(language_id);
            languageModel.setLanguageName(language_name);

            if (default_Language.equalsIgnoreCase(language_id)) {
                languageModel.setIsSelected(true);
            } else {
                languageModel.setIsSelected(false);
            }
            languageModels.add(languageModel);
        }

        languageModel = languageModels;
        ShowLanguagePopup();
    }

    @Override
    public void onLogoutPreExecuteStarted() {
        pDialog = new ProgressBarHandler(Episode_list_Activity.this);
        pDialog.show();
    }

    @Override
    public void onLogoutPostExecuteCompleted(int code, String status, String message) {
        Util.check_for_subscription = 0;
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;
        }

        if (status == null) {
            Toast.makeText(Episode_list_Activity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code == 0) {
            Toast.makeText(Episode_list_Activity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code > 0) {
            if (code == 200) {
                preferenceManager.clearLoginPref();

                if ((featureHandler.getFeatureStatus(FeatureHandler.SIGNUP_STEP, FeatureHandler.DEFAULT_SIGNUP_STEP))) {
                    final Intent startIntent = new Intent(Episode_list_Activity.this, LoginActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(Episode_list_Activity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });
                } else {
                    final Intent startIntent = new Intent(Episode_list_Activity.this, MainActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(Episode_list_Activity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });
                }

            } else {
                Toast.makeText(Episode_list_Activity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_listing);
        Util.check_for_subscription = 0;
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        languagePreference = LanguagePreference.getLanguagePreference(this);
        featureHandler = FeatureHandler.getFeaturePreference(Episode_list_Activity.this);
        isLogin = preferenceManager.getLoginFeatureFromPref();
        playerModel = new Player();
        playerModel.setIsstreaming_restricted(Util.getStreamingRestriction(languagePreference));
        episodeListOptionMenuHandler = new EpisodeListOptionMenuHandler(this);


        Intent receiveIntent = getIntent();
        if (receiveIntent.hasExtra(VIDEO_TITLE_INTENT_KEY))
            title = receiveIntent.getStringExtra(VIDEO_TITLE_INTENT_KEY);
        if (receiveIntent.hasExtra(SEASON_INTENT_KEY)) {
            season_value = languagePreference.getTextofLanguage(SEASON, DEFAULT_SEASON) + " " + receiveIntent.getStringExtra(SEASON_INTENT_KEY);
        }


        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setTitle(title);
        setSupportActionBar(mActionBarToolbar);
        mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        //mActionBarToolbar.setTitle(languagePreference.getTextofLanguage(SEASON, DEFAULT_SEASON).toString() + " " + getIntent().getStringExtra(SEASON_INTENT_KEY));
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //  To set Id to action bar back button
        setIdToActionBarBackButton(mActionBarToolbar);

        TextView sectionTitle = (TextView) findViewById(R.id.sectionTitle);
        FontUtls.loadFont(Episode_list_Activity.this, getResources().getString(R.string.regular_fonts), sectionTitle);
        //sectionTitle.setText(languagePreference.getTextofLanguage(EPISODE_TITLE, DEFAULT_EPISODE_TITLE));
        sectionTitle.setText(season_value);

        episodelist = (RecyclerView) findViewById(R.id.episodelist);
        noInternetConnectionLayout = (RelativeLayout) findViewById(R.id.noInternet);
        noDataLayout = (RelativeLayout) findViewById(R.id.noData);
        noInternetTextView = (TextView) findViewById(R.id.noInternetTextView);
        noDataTextView = (TextView) findViewById(R.id.noDataTextView);
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noDataTextView.setText(languagePreference.getTextofLanguage(NO_CONTENT, DEFAULT_NO_CONTENT));


        mLayoutManager = new LinearLayoutManager(Episode_list_Activity.this, LinearLayoutManager.VERTICAL, false);
        permalinkStr = getIntent().getStringExtra(PERMALINK_INTENT_KEY);

        footerView = (RelativeLayout) findViewById(R.id.loadingPanel);
        footerView.setVisibility(View.GONE);

        ppvmodel = new PPVModel();
        advmodel = new APVModel();
        currencymodel = new CurrencyModel();
        resetData();


        boolean isNetwork = NetworkStatus.getInstance().isConnected(Episode_list_Activity.this);
        if (isNetwork) {

            if (ContextCompat.checkSelfPermission(Episode_list_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(Episode_list_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(Episode_list_Activity.this,
                            new String[]{Manifest.permission
                                    .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS},
                            111);
                } else {
                    ActivityCompat.requestPermissions(Episode_list_Activity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            111);
                }
            } else {
                //Call whatever you want
                if (isNetwork) {
                    Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                    episodeDetailsInput.setAuthtoken(authTokenStr);
                    episodeDetailsInput.setPermalink(permalinkStr);
                    episodeDetailsInput.setSeries_number(getIntent().getStringExtra(SEASON_INTENT_KEY));
                    episodeDetailsInput.setLimit(String.valueOf(limit));
                    episodeDetailsInput.setOffset(String.valueOf(offset));
                    episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
                    episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));


                    asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, Episode_list_Activity.this, Episode_list_Activity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this), true);
                    asynEpisodeDetails.executeOnExecutor(threadPoolExecutor);
                } else {
                    noInternetConnectionLayout.setVisibility(View.VISIBLE);
                }
            }
        } else {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
        }

        episodelist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                if ((itemData.size() < TOTAL_EPISODE_COUNT) && (lastVisibleItemPosition == itemData.size() - 1) && newState == 0) {

                    offset += 1;
                    if (NetworkStatus.getInstance().isConnected(Episode_list_Activity.this)) {
                        // default data
                        Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                        episodeDetailsInput.setAuthtoken(authTokenStr);
                        episodeDetailsInput.setPermalink(permalinkStr);
                        episodeDetailsInput.setSeries_number(getIntent().getStringExtra(SEASON_INTENT_KEY));
                        episodeDetailsInput.setLimit(String.valueOf(limit));
                        episodeDetailsInput.setOffset(String.valueOf(offset));
                        episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
                        episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));


                        asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, Episode_list_Activity.this, Episode_list_Activity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(Episode_list_Activity.this), true);
                        asynEpisodeDetails.executeOnExecutor(threadPoolExecutor);
                    }
                }
            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });


    }

    public void clickItem(EpisodesListModel item) {

        try {

            isVoucher = 0;
            Util.check_for_subscription = 1;

            itemToPlay = item;
            DataModel dbModel = new DataModel();
            dbModel.setIsFreeContent(isFreeContent);
            dbModel.setIsAPV(isAPV);
            dbModel.setIsPPV(isPPV);
            dbModel.setIsConverted(1);
            dbModel.setMovieUniqueId(item.getEpisodeMuviUniqueId());
            dbModel.setStreamUniqueId(item.getEpisodeStreamUniqueId());
            dbModel.setThirdPartyUrl(item.getEpisodeThirdPartyUrl());
            dbModel.setVideoTitle(movieNameStr);
            dbModel.setVideoStory(item.getEpisodeDescription());

            dbModel.setVideoGenre(getIntent().getStringExtra(GENRE_INTENT_KEY));
            dbModel.setVideoDuration(item.getEpisodeDuration());
            dbModel.setVideoReleaseDate("");

            dbModel.setCensorRating(getIntent().getStringExtra(CENSOR_RATING_INTENT_KEY));
            dbModel.setCastCrew(getIntent().getBooleanExtra(CAST_INTENT_KEY, false));
            dbModel.setEpisode_id(item.getEpisodeStreamUniqueId());
            dbModel.setPosterImageId(item.getEpisodeThumbnailImageView());

            dbModel.setContentTypesId(Integer.parseInt(getIntent().getStringExtra("content_types_id")));


            dbModel.setEpisode_series_no(item.getEpisodeSeriesNo());
            dbModel.setEpisode_no(item.getEpisodeNumber());
            dbModel.setEpisode_title(item.getEpisodeTitle());

            playerModel.setStreamUniqueId(item.getEpisodeStreamUniqueId());
            playerModel.setMovieUniqueId(item.getEpisodeMuviUniqueId());
            playerModel.setUserId(preferenceManager.getUseridFromPref());
            playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
            playerModel.setAuthTokenStr(authTokenStr.trim());
            playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH);
            try {
                playerModel.setIsFreeContent(Integer.parseInt(item.getIsFreeContnet()));
            } catch (Exception e) {
            }

            playerModel.setEpisode_id(item.getEpisodeStreamUniqueId());
            playerModel.setVideoTitle(item.getEpisodeTitle());
            playerModel.setVideoStory(item.getEpisodeDescription());
            playerModel.setVideoGenre(getIntent().getStringExtra(GENRE_INTENT_KEY));
            playerModel.setVideoDuration(item.getEpisodeDuration());
            playerModel.setVideoReleaseDate("");
            playerModel.setCensorRating(getIntent().getStringExtra(CENSOR_RATING_INTENT_KEY));
            playerModel.setCastCrew(getIntent().getBooleanExtra(CAST_INTENT_KEY, false));
            try {
                playerModel.setPosterImageId(item.getEpisodeThumbnailImageView());
            } catch (Exception e) {
                playerModel.setPosterImageId("https://d2gx0xinochgze.cloudfront.net/public/no-image-a.png");
            }

            if (!getIntent().getStringExtra(SEASON_INTENT_KEY).equals("")) {
                dbModel.setSeason_id(getIntent().getStringExtra(SEASON_INTENT_KEY));

            } else {
                dbModel.setSeason_id("0");

            }
            dbModel.setPurchase_type("episode");
            Util.dataModel = dbModel;


            SubTitleName.clear();
            SubTitlePath.clear();
            ResolutionUrl.clear();
            ResolutionFormat.clear();
            String loggedInStr = preferenceManager.getLoginStatusFromPref();
            if (isLogin == 1) {
                if (loggedInStr != null) {
                    if (NetworkStatus.getInstance().isConnected(this)) {
                            getVideoInfo();


                    } else {
                        Util.showToast(Episode_list_Activity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));

                    }

                } else {

                    if ((playerModel.getIsFreeContent() == 1) && !featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN_REQUIRED_FOR_FREECONTENT, FeatureHandler.DEFAULT_IS_LOGIN_REQUIRED_FOR_FREECONTENT)) {
                        getVideoInfo();
                    } else {

                        Util.check_for_subscription = 1;
                        Intent registerActivity = new LoginRegistrationOnContentClickHandler(this).handleClickOnContent();
                        registerActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        registerActivity.putExtra("PlayerModel", playerModel);
                        startActivityForResult(registerActivity, VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE);
                    }


                }
            } else {
                if (NetworkStatus.getInstance().isConnected(this)) {
                    getVideoInfo();

                } else {
                    Util.showToast(Episode_list_Activity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
                }
            }


        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private class AsynLOADUI extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        protected void onPostExecute(Void result) {


            try {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.hide();
                    pDialog = null;
                }
            } catch (IllegalArgumentException ex) {

                noDataLayout.setVisibility(View.VISIBLE);
                noInternetConnectionLayout.setVisibility(View.GONE);
                episodelist.setVisibility(View.GONE);
                footerView.setVisibility(View.GONE);
            }

            if (footerView != null && listSize >= itemsInServer - 1) {
                footerView.setVisibility(View.GONE);
            }
            if (firstTime == true) {


                episodelist.smoothScrollToPosition(0);
                firstTime = false;
                ViewGroup.LayoutParams layoutParams = episodelist.getLayoutParams();
                layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT; //this is in pixels
                episodelist.setLayoutParams(layoutParams);
                if ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) {
                    mLayoutManager = new GridLayoutManager(Episode_list_Activity.this, 1);
                    episodelist.setLayoutManager(mLayoutManager);

                } else if ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_NORMAL) {
                    mLayoutManager = new GridLayoutManager(Episode_list_Activity.this, 1);
                    episodelist.setLayoutManager(mLayoutManager);

                } else if ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_SMALL) {

                    mLayoutManager = new GridLayoutManager(Episode_list_Activity.this, 1);
                    episodelist.setLayoutManager(mLayoutManager);

                } else {
                    mLayoutManager = new GridLayoutManager(Episode_list_Activity.this, 1);
                    episodelist.setLayoutManager(mLayoutManager);
                }
                customListAdapter = new EpisodesListViewMoreAdapter(Episode_list_Activity.this, getItemLayoutId(), itemData, new EpisodesListViewMoreAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(EpisodesListModel item) {
                        if (!item_clicked) {
                            clickItem(item);
                            item_clicked = true;
                        }
                    }
                });
                episodelist.setAdapter(customListAdapter);

            }

        }
    }


    private @LayoutRes
    int getItemLayoutId() {
        if (videoHeight > videoWidth) {
            return R.layout.episode_list_item_portrait;
        }
        return R.layout.episode_list_item;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }


    @Override
    public void onBackPressed() {
        if (asynLoadVideoUrls != null) {
            asynLoadVideoUrls.cancel(true);
        }
        if (asynEpisodeDetails != null) {
            asynEpisodeDetails.cancel(true);
        }
        finish();
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        id = preferenceManager.getUseridFromPref();
        email = preferenceManager.getEmailIdFromPref();
        /*
        Set translation key to array
         */
        String[] translateKey = {LOGIN,
                BTN_REGISTER,
                LANGUAGE_POPUP_LANGUAGE,
                PROFILE,
                PURCHASE_HISTORY,
                LOGOUT, NOTIFICATION_TITLE};
        /*
        Set transalation value to array
         */
        String[] translateValue = {
                DEFAULT_LOGIN,
                DEFAULT_BTN_REGISTER,
                DEFAULT_LANGUAGE_POPUP_LANGUAGE,
                DEFAULT_PROFILE,
                DEFAULT_PURCHASE_HISTORY,
                DEFAULT_LOGOUT, DEFAULT_NOTIFICATION_TITLE};
        /*
        Set the lang array with the langugePreference of key and value array
         */
        lang = new String[translateKey.length];
        for (int i = 0; i < lang.length; i++)
            lang[i] = languagePreference.getTextofLanguage(translateKey[i], translateValue[i]);

        visibility = episodeListOptionMenuHandler.createOptionMenu(menu, preferenceManager, languagePreference, featureHandler);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.search:
                final Intent searchIntent = new Intent(Episode_list_Activity.this, SearchActivity.class);
                searchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(searchIntent);
                return false;

            case R.id.action_login:

                final Intent loginIntent = new Intent(Episode_list_Activity.this, LoginActivity.class);
                Util.check_for_subscription = 0;
                startActivity(loginIntent);
                return false;
            case R.id.action_register:

                Intent registerIntent = new Intent(Episode_list_Activity.this, RegisterActivity.class);
                Util.check_for_subscription = 0;
                startActivity(registerIntent);
                return false;

            case R.id.menu_item_language:

                // Not implemented here
                default_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);
                Previous_Selected_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);

                if (Util.languageModel != null && Util.languageModel.size() > 0) {
                    ShowLanguagePopup();

                } else {
                    LanguageListInputModel languageListInputMode = new LanguageListInputModel();
                    languageListInputMode.setAuthToken(authTokenStr);
                    languageListInputMode.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    languageListInputMode.setLangCode(default_Language);
                    GetLanguageListAsynTask asynGetLanguageList = new GetLanguageListAsynTask(languageListInputMode, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
                    asynGetLanguageList.executeOnExecutor(threadPoolExecutor);
                }
                return false;
            case R.id.menu_item_profile:

                Intent profileIntent = new Intent(Episode_list_Activity.this, ProfileActivity.class);
                profileIntent.putExtra("EMAIL", email);
                profileIntent.putExtra("LOGID", id);
                startActivity(profileIntent);
                // Not implemented here
                return false;

            case R.id.action_logout:

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Episode_list_Activity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(languagePreference.getTextofLanguage(SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING));
                dlgAlert.setTitle("");

                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        // dialog.cancel();
                        LogoutInput logoutInput = new LogoutInput();
                        logoutInput.setAuthToken(authTokenStr);
                        logoutInput.setLogin_history_id(preferenceManager.getLoginHistIdFromPref());
                        logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                        logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                        LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, Episode_list_Activity.this, Episode_list_Activity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(Episode_list_Activity.this));
                        asynLogoutDetails.executeOnExecutor(threadPoolExecutor);


                        dialog.dismiss();
                    }
                });

                dlgAlert.setNegativeButton(languagePreference.getTextofLanguage(NO, DEFAULT_NO), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });
                // dlgAlert.setPositiveButton(getResources().getString(R.string.yes_str), null);
                dlgAlert.setCancelable(false);

                dlgAlert.create().show();

                return false;
            case R.id.option:
                showPopupMenu(findViewById(R.id.option));
                return false;
            default:
                break;
        }

        return false;
    }

    private void showPopupMenu(View viewById) {
        CardView viewGroup = (CardView) findViewById(R.id.option_menu_layout);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View layout = layoutInflater.inflate(R.layout.option_menu_popup_layout, viewGroup);
        initLayouts(layout);

        // Creating the PopupWindow
        changeSortPopUp = new PopupWindow(this);
        changeSortPopUp.setContentView(layout);
        changeSortPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeSortPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeSortPopUp.setFocusable(true);
        changeSortPopUp.setElevation(50);
        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = getSupportActionBar().getHeight();

        // Clear the default translucent background
        changeSortPopUp.setBackgroundDrawable(getDrawable(R.drawable.white));
        changeSortPopUp.showAsDropDown(viewById, OFFSET_X + 20, -OFFSET_Y + 20);

        for (int i = 0; i < option_menu_id.length; i++) {
            if (visibility[i])
                linearLayout[i].setVisibility(View.VISIBLE);
            else
                linearLayout[i].setVisibility(View.GONE);
        }
        for (int i = 0; i < option_menu_id.length; i++) {
            final int finalI = i;
            linearLayout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    performWork(linearLayout[finalI].getId(), changeSortPopUp);

                }
            });
        }
    }

    private void performWork(int id, PopupWindow changeSortPopUp) {
        switch (id) {
            case R.id.login:
                Intent loginIntent = new Intent(Episode_list_Activity.this, LoginActivity.class);
                Util.check_for_subscription = 0;
                startActivity(loginIntent);
                changeSortPopUp.dismiss();
                break;
            case R.id.register:
                Intent registerIntent = new Intent(Episode_list_Activity.this, RegisterActivity.class);
                Util.check_for_subscription = 0;
                startActivity(registerIntent);
                changeSortPopUp.dismiss();
                break;
            case R.id.language_popup:
                default_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);
                Previous_Selected_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);
                if (languageModel != null && languageModel.size() > 0) {
                    ShowLanguagePopup();
                } else {
                    LanguageListInputModel languageListInputModel = new LanguageListInputModel();
                    languageListInputModel.setAuthToken(authTokenStr);
                    languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    languageListInputModel.setLangCode(default_Language);
                    GetLanguageListAsynTask asynGetLanguageList = new GetLanguageListAsynTask(languageListInputModel, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
                    asynGetLanguageList.executeOnExecutor(threadPoolExecutor);
                }
                changeSortPopUp.dismiss();
                break;
            case R.id.profile:
                Intent profileIntent = new Intent(Episode_list_Activity.this, ProfileActivity.class);
                profileIntent.putExtra("EMAIL", email);
                profileIntent.putExtra("LOGID", id);
                startActivity(profileIntent);
                changeSortPopUp.dismiss();
                break;

            case R.id.logout:

               {
                    //show dialog
                    logoutPopup();
                }

                changeSortPopUp.dismiss();
                break;

            default:
                break;


        }
    }

    private void logoutPopup() {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Episode_list_Activity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(languagePreference.getTextofLanguage(SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING));
        dlgAlert.setTitle("");

        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                // dialog.cancel();
                if (NetworkStatus.getInstance().isConnected(Episode_list_Activity.this)) {
                    LogoutInput logoutInput = new LogoutInput();
                    logoutInput.setAuthToken(authTokenStr);
                    String loginHistoryIdStr = preferenceManager.getLoginHistIdFromPref();
                    logoutInput.setLogin_history_id(loginHistoryIdStr);
                    logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, Episode_list_Activity.this, Episode_list_Activity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(Episode_list_Activity.this));
                    asynLogoutDetails.executeOnExecutor(threadPoolExecutor);


                    dialog.dismiss();
                } else {
                    Toast.makeText(Episode_list_Activity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                }
            }
        });

        dlgAlert.setNegativeButton(languagePreference.getTextofLanguage(NO, DEFAULT_NO), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dlgAlert.setCancelable(false);

        dlgAlert.create().show();
    }

    private void initLayouts(View layout) {
        linearLayout = new LinearLayout[option_menu_id.length];
        for (int i = 0; i < option_menu_id.length; i++) {
            linearLayout[i] = (LinearLayout) layout.findViewById(option_menu_id[i]);
            setLanguageToTextViews(linearLayout[i], i);

        }
        //added by Bishal for Notification
        noti = (TextView) layout.findViewById(R.id.count_notification);
        noti_layout = (FrameLayout) layout.findViewById(R.id.counterValuePanel);

    }


    private void setLanguageToTextViews(LinearLayout linearLayout, int i) {
        int count = linearLayout.getChildCount();
        for (int j = 0; j < count; j++) {
            View vw = linearLayout.getChildAt(j);
            if (vw instanceof TextView) {
                ((TextView) vw).setText(lang[i]);
            }
        }
    }

    public void ShowLanguagePopup() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Episode_list_Activity.this, R.style.MyAlertDialogStyle);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View convertView = inflater.inflate(R.layout.language_pop_up, null);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.languagePopupTitle);
        titleTextView.setText(languagePreference.getTextofLanguage(APP_SELECT_LANGUAGE, DEFAULT_APP_SELECT_LANGUAGE));

        alertDialog.setView(convertView);
        alertDialog.setTitle("");

        RecyclerView recyclerView = (RecyclerView) convertView.findViewById(R.id.language_recycler_view);
        Button apply = (Button) convertView.findViewById(R.id.apply_btn);
        apply.setText(languagePreference.getTextofLanguage(BUTTON_APPLY, DEFAULT_BUTTON_APPLY));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        languageCustomAdapter = new LanguageCustomAdapter(Episode_list_Activity.this, Util.languageModel, null);
        recyclerView.setAdapter(languageCustomAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener1(Episode_list_Activity.this, recyclerView, new ClickListener1() {
            @Override
            public void onClick(View view, int position) {
                Util.itemclicked = true;

                Util.languageModel.get(position).setSelected(true);


                if (prevPosition != position) {
                    Util.languageModel.get(prevPosition).setSelected(false);
                    prevPosition = position;

                }

                default_Language = Util.languageModel.get(position).getLanguageId();
                languagePreference.setLanguageSharedPrefernce(SELECTED_LANGUAGE_CODE, Util.languageModel.get(position).getLanguageId());
                languageCustomAdapter.notifyDataSetChanged();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();


                if (!Previous_Selected_Language.equals(default_Language)) {

                    LanguageListInputModel languageListInputModel = new LanguageListInputModel();
                    languageListInputModel.setAuthToken(authTokenStr);
                    languageListInputModel.setLangCode(default_Language);
                    //code start: country code added mantish id# 17373
                    languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    GetTranslateLanguageAsync asynGetTransalatedLanguage = new GetTranslateLanguageAsync(languageListInputModel, Episode_list_Activity.this, Episode_list_Activity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(Episode_list_Activity.this));
                    asynGetTransalatedLanguage.executeOnExecutor(threadPoolExecutor);
                }

            }
        });


        alert = alertDialog.show();
        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                languagePreference.setLanguageSharedPrefernce(SELECTED_LANGUAGE_CODE, Previous_Selected_Language);
            }
        });

    }


    @Override
    public void onGetTranslateLanguagePreExecuteStarted() {
        pDialog = new ProgressBarHandler(Episode_list_Activity.this);
        pDialog.show();
    }

    @Override
    public void onGetTranslateLanguagePostExecuteCompleted(String jsonResponse, int status) {

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }

        if (jsonResponse == null) {
        } else {
            if (status > 0 && status == 200) {
                    Util.parseLanguage(languagePreference, jsonResponse, default_Language);
                    genre_call(default_Language);
            } else {
            }
        }

    }


    @Override
    public void onGetGenreListPreExecuteStarted() {
        pDialog = new ProgressBarHandler(Episode_list_Activity.this);
        pDialog.show();
    }

    @Override
    public void onGetGenreListPostExecuteCompleted(ArrayList<GenreListOutput> genreListOutput, ArrayList<SortByModel> sortByModelArrayList, int code, String status) {

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }
        languageCustomAdapter.notifyDataSetChanged();
        final Intent detailsIntent = new Intent(Episode_list_Activity.this, Episode_list_Activity.class);
        detailsIntent.putExtra(PERMALINK_INTENT_KEY, permalinkStr);
        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(detailsIntent);
        finish();

        preferenceManager.setLanguageChangeStatus("1");
    }


    public void genre_call(String default_Language){

        GenreListInput genreListInput = new GenreListInput();
        genreListInput.setAuthToken(authTokenStr);
        genreListInput.setLang_code(default_Language);
        genreListInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        GetGenreListAsynctask asynGetGenreList = new GetGenreListAsynctask(genreListInput, Episode_list_Activity.this, Episode_list_Activity.this, false, new DataController(Episode_list_Activity.this));
        asynGetGenreList.executeOnExecutor(threadPoolExecutor);

    }


    public static class RecyclerTouchListener1 implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener1 clickListener;

        public RecyclerTouchListener1(Context context, final RecyclerView recyclerView, final ClickListener1 clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface ClickListener1 {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    @Override
    public void onResume() {
        super.onResume();
        GetIpAddressAsynTask asynGetIpAddress = new GetIpAddressAsynTask(this, this);
        asynGetIpAddress.executeOnExecutor(threadPoolExecutor);
        invalidateOptionsMenu();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 111: {

                if (grantResults.length > 0) {
                    if ((grantResults.length > 0) && (grantResults[0]) == PackageManager.PERMISSION_GRANTED) {
                        //Call whatever you want
                        if (NetworkStatus.getInstance().isConnected(Episode_list_Activity.this)) {
                            Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                            episodeDetailsInput.setAuthtoken(authTokenStr);
                            episodeDetailsInput.setPermalink(permalinkStr);
                            episodeDetailsInput.setSeries_number(getIntent().getStringExtra(SEASON_INTENT_KEY));
                            episodeDetailsInput.setLimit(String.valueOf(limit));
                            episodeDetailsInput.setOffset(String.valueOf(offset));
                            episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
                            episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));


                            asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, Episode_list_Activity.this, Episode_list_Activity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this), true);
                            asynEpisodeDetails.executeOnExecutor(threadPoolExecutor);
                        } else {
                            noInternetConnectionLayout.setVisibility(View.VISIBLE);
                        }

                    } else {
                        finish();
                    }
                } else {
                    finish();
                }

                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1001) {
            if (data.getStringExtra("yes").equals("1002")) {
                watch_status_String = "halfplay";
                seek_status = "first_time";
                Played_Length = Util.dataModel.getPlayPos() * 1000;
                resume_time = "" + Util.dataModel.getPlayPos();

            } else {
                watch_status_String = "strat";
                Played_Length = 0;
            }
        } else if (resultCode == RESULT_OK && requestCode == 1007) {

            if (data.getStringExtra("yes").equals("1002")) {

                watch_status_String = "halfplay";
                seek_status = "first_time";
                Played_Length = Util.dataModel.getPlayPos() * 1000;

            } else {
                watch_status_String = "strat";
                Played_Length = 0;
            }
        } else if (requestCode == VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE && resultCode == RESULT_OK) {
            getVideoInfo();
        }  else if (requestCode == VIDEO_PLAY_BUTTON_CLICK_SUBSCRIPTION_REQUESTCODE && resultCode == RESULT_OK) {
            getVideoInfo();
        }
    }

    @Override
    public void onIPAddressPreExecuteStarted() {

    }

    @Override
    public void onIPAddressPostExecuteCompleted(String message, int statusCode, String ipAddressStr) {
        this.ipAddressStr = ipAddressStr;
        return;
    }


    public void Download_SubTitle(String Url) {
        new DownloadFileFromURL().execute(Url);
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {


        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected String doInBackground(String... f_url) {
            int count;


            try {
                URL url = new URL(f_url[0]);
                String str = f_url[0];
                filename = str.substring(str.lastIndexOf("/") + 1);
                URLConnection conection = url.openConnection();
                conection.connect();
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                File root = Environment.getExternalStorageDirectory();
                mediaStorageDir = new File(root + "/Android/data/" + getApplicationContext().getPackageName().trim() + "/SubTitleList/", "");

                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                    }
                }

                SubTitlePath.add(mediaStorageDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".vtt");
                OutputStream output = new FileOutputStream(mediaStorageDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".vtt");

                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            return null;
        }

        protected void onProgressUpdate(String... progress) {
        }

        @Override
        protected void onPostExecute(String file_url) {
            try {
                FakeSubTitlePath.remove(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (FakeSubTitlePath.size() > 0) {
                Download_SubTitle(FakeSubTitlePath.get(0).trim());
            } else {
                if (progressBarHandler != null && progressBarHandler.isShowing()) {
                    progressBarHandler.hide();
                }
                playerModel.setSubTitlePath(SubTitlePath);
                Intent playVideoIntent;
                if (Util.dataModel.getAdNetworkId() == 3) {
                    playVideoIntent = Util.getPlayerIntent(Episode_list_Activity.this);

                } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                    if (Util.dataModel.getPlayPos() <= 0) {
                        playVideoIntent = new Intent(Episode_list_Activity.this, AdPlayerActivity.class);
                    } else {
                        playVideoIntent = Util.getPlayerIntent(Episode_list_Activity.this);

                    }
                } else {
                    playVideoIntent = Util.getPlayerIntent(Episode_list_Activity.this);

                }

                playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                playVideoIntent.putExtra("PlayerModel", playerModel);
                startActivity(playVideoIntent);
            }
        }


    }

    public void getVideoInfo() {
        GetVideoDetailsInput getVideoDetailsInput = new GetVideoDetailsInput();
        getVideoDetailsInput.setAuthToken(authTokenStr);
        getVideoDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
        getVideoDetailsInput.setContent_uniq_id(Util.dataModel.getMovieUniqueId().trim());
        getVideoDetailsInput.setStream_uniq_id(Util.dataModel.getStreamUniqueId().trim());
        getVideoDetailsInput.setInternetSpeed(MainActivity.internetSpeed.trim());
        getVideoDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        getVideoDetailsInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        getVideoDetailsInput.setAdParameter(Util.getVastAdParameter());
        asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, Episode_list_Activity.this, Episode_list_Activity.this);
        asynLoadVideoUrls.executeOnExecutor(threadPoolExecutor);
    }


    /*
    Kushal- To set id to back button in Action Bar
     */
    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.back);

            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                if (t.getText().toString().contains(title)) {
                    t.setId(R.id.page_title_season);
                }
            }
        }
    }

    /*
     * Add by Piyush on 28-Aug-2018
     * */
    class RetrieveFeedTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void doInBackground(String... urls) {
            try {


                URL url = new URL(urls[0]);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = sampleSize;
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options);
                videoHeight = bmp.getHeight() * sampleSize;
                videoWidth = bmp.getWidth() * sampleSize;

                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Void feed) {
            AsynLOADUI loadui = new AsynLOADUI();
            loadui.execute(); //threadPoolExecutor
        }
    }
}
