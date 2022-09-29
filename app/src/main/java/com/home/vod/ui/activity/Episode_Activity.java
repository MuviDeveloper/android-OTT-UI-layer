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
import static com.home.vod.preferences.LanguagePreference.DEFAULT_EPISODE_TITLE;
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
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_WARNING;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_YES;
import static com.home.vod.preferences.LanguagePreference.EPISODE_TITLE;
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
import static com.home.vod.util.Constant.SEASON_INTENT_TITLE_KEY;
import static com.home.vod.util.Constant.VIDEO_TITLE_INTENT_KEY;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.Util.languageModel;
import static com.home.vod.util.Util.resetSubtitleBundle;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.home.apisdk.apiController.GetGenreListAsynctask;
import com.home.apisdk.apiController.GetIpAddressAsynTask;
import com.home.apisdk.apiController.GetLanguageListAsynTask;
import com.home.apisdk.apiController.GetTranslateLanguageAsync;
import com.home.apisdk.apiController.GetWatchHistoryEpisodeDeatailsAsynTask;
import com.home.apisdk.apiController.LogoutAsynctask;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
import com.home.apisdk.apiModel.APVModel;
import com.home.apisdk.apiModel.CurrencyModel;
import com.home.apisdk.apiModel.GenreListInput;
import com.home.apisdk.apiModel.GenreListOutput;
import com.home.apisdk.apiModel.GetVideoDetailsInput;
import com.home.apisdk.apiModel.LanguageListInputModel;
import com.home.apisdk.apiModel.LanguageListOutputModel;
import com.home.apisdk.apiModel.LogoutInput;
import com.home.apisdk.apiModel.PPVModel;
import com.home.apisdk.apiModel.SortByModel;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.apisdk.apiModel.Watch_History_Episode_Details_input;
import com.home.apisdk.apiModel.Watch_History_Episode_Details_output;
import com.home.vod.BuildConfig;
import com.home.vod.R;
import com.home.vod.handlers.CheckVoucherOrPpvPaymentHandler;
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
import com.home.vod.util.AutoPlayUtil;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.RecyclerViewMargin;
import com.home.vod.util.Util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import player.activity.AdPlayerActivity;
import player.activity.Player;


public class Episode_Activity extends AppCompatActivity implements VideoDetailsAsynctask.VideoDetailsListener,
        GetWatchHistoryEpisodeDeatailsAsynTask.GetWatchHistoryEpisodeDetailsListener, GetLanguageListAsynTask.GetLanguageListListener, LogoutAsynctask.LogoutListener,
        GetTranslateLanguageAsync.GetTranslateLanguageInfoListener, GetIpAddressAsynTask.IpAddressListener
        , GetGenreListAsynctask.GenreListListener {

    public static final int VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE = 8888;
    public static final int VIDEO_PLAY_BUTTON_CLICK_SUBSCRIPTION_REQUESTCODE = 9898;
    public static final int PAYMENT_REQUESTCODE = 8889;
    static File mediaStorageDir;
    public static boolean isLoading = false;
    public static TextView badge_text;
    public static TextView noti;
    public static FrameLayout noti_layout;

    public Toolbar mActionBarToolbar;
    public ToolbarTitleHandler toolbarTitleHandler;
    ProgressBarHandler pDialog;
    private PreferenceManager preferenceManager;
    LanguagePreference languagePreference;
    FeatureHandler featureHandler;
    private EpisodeListOptionMenuHandler episodeListOptionMenuHandler;
    ProgressBarHandler pageNaviagtionLoader;

    ArrayList<Watch_History_Episode_Details_output.Episode> episodeArray;
    ArrayList<String> SubTitleName = new ArrayList<>();
    ArrayList<String> SubTitlePath = new ArrayList<>();
    ArrayList<String> FakeSubTitlePath = new ArrayList<>();
    ArrayList<String> ResolutionFormat = new ArrayList<>();
    ArrayList<String> ResolutionUrl = new ArrayList<>();
    ArrayList<String> SubTitleLanguage = new ArrayList<>();
    ArrayList<EpisodesListModel> itemData = new ArrayList<EpisodesListModel>();
    ArrayList<String> seasonList = new ArrayList<>();
    ArrayList<String> seasonNo = new ArrayList<>();
    String[] lang;
    boolean[] visibility;
    LinearLayout[] linearLayout;
    int[] option_menu_id = {R.id.login, R.id.register, R.id.language_popup, R.id.profile, R.id.purchase, R.id.logout, R.id.notification};

    int previousTotal = 0;
    private String filename = "";
    String permalinkStr;
    String video_title;
    int isVoucher = 0;
    boolean firstTime = false;
    String seek_status = "";
    int Played_Length = 0;
    String watch_status_String = "start";
    String resume_time = "0";
    int offset = 1;
    int limit = 100;
    int listSize = 0;
    int itemsInServer = 0;
    int isLogin = 0;
    int isPPV = 0;
    int isAPV = 0;
    int isConverted = 0;
    int isFreeContent = 0;
    int Count = 0;
    int videoHeight = 185;
    int videoWidth = 256;
    int sampleSize = 100;
    String is_livestream_enable;
    String livestream_resume_time;
    String default_Language = "";
    String Previous_Selected_Language = "";
    int prevPosition = 0;
    private String ipAddressStr = "";
    String selected_Season = "";
    String Default_Language = "";
    String movieNameStr = "";
    String email, id;

    VideoDetailsAsynctask asynLoadVideoUrls;
    GetWatchHistoryEpisodeDeatailsAsynTask asynEpisodeDetails;
    EpisodesListModel itemToPlay;

    LinearLayoutManager mLayoutManager;
    private EpisodesListViewMoreAdapter customListAdapter;

    RecyclerView episodelist;
    private RelativeLayout noInternetConnectionLayout;
    RelativeLayout noDataLayout;
    TextView noDataTextView;
    TextView noInternetTextView;
    RelativeLayout footerView;

    Player playerModel, nextPlayerModel, prevPlayerModel;

    int corePoolSize = 60;
    int maximumPoolSize = 80;
    int keepAliveTime = 10;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);


    PPVModel ppvmodel;
    APVModel advmodel;
    CurrencyModel currencymodel;

    LanguageCustomAdapter languageCustomAdapter;
    AlertDialog alert;

    DataModel dbModel;

    boolean loaderIsShowing = false;
    boolean stopItemClick = false;
    Timer UI_FREEZE_TIMER;
    Spinner season_spinner;
    PopupWindow changeSortPopUp;
    Menu menu;


    /**
     * Variables for Enhanced Mini Controller UI
     */

    ImageView previous, play_pause, next, cc_button;
    LinearLayout mini_controller_layout;


    // This method is to reset all item data
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
        startLoaeder();

    }

    @Override
    public void onVideoDetailsPostExecuteCompleted(Video_Details_Output _video_details_output, int statusCode, String stus, String message) {

        if (_video_details_output != null) {
            if (_video_details_output.getFakeSubTitlePath().size() <= 0)
                stopLoader();
            boolean play_video = true;

            if (featureHandler.getFeatureStatus(FeatureHandler.IS_STREAMING_RESTRICTION, FeatureHandler.DEFAULT_IS_STREAMING_RESTRICTION)) {

                play_video = !_video_details_output.getStreaming_restriction().trim().equals("0");
            } else {
                play_video = true;
            }
            if (!play_video) {

                stopLoader();
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Episode_Activity.this, R.style.MyAlertDialogStyle);
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
        } else {
            stopLoader();
        }


        if (statusCode == 200) {


            isFreeContent = Integer.parseInt(_video_details_output.getIs_free());
            dbModel.setIsFreeContent(isFreeContent);

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
            playerModel.setHas_video_card(Integer.parseInt(_video_details_output.getHas_video_card()));
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

                    if (_video_details_output.getOfflineLicenseToken() != null) {
                        playerModel.setOfflineLicenseToken(_video_details_output.getOfflineLicenseToken());
                    }

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

                resetSubtitleBundle(Episode_Activity.this);
                if (cc_button != null) {
                    cc_button.setVisibility(View.GONE);
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

                    Util.showNoDataAlert(Episode_Activity.this);

                } else {

                    // condition for checking if the response has third party url or not.
                    if (_video_details_output.getThirdparty_url() == null ||
                            _video_details_output.getThirdparty_url().matches("")
                    ) {


                        {

                            playerModel.setThirdPartyPlayer(false);
                            final Intent playVideoIntent;


                            if (Util.dataModel.getAdNetworkId() == 3) {
                                playVideoIntent = Util.getPlayerIntent(Episode_Activity.this);

                            } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                                if (Util.dataModel.getPlayPos() <= 0) {
                                    playVideoIntent = new Intent(Episode_Activity.this, AdPlayerActivity.class);
                                } else {
                                    playVideoIntent = Util.getPlayerIntent(Episode_Activity.this);

                                }

                            } else {
                                playVideoIntent = Util.getPlayerIntent(Episode_Activity.this);

                            }

                            stopLoader();
                            playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            playVideoIntent.putExtra("PlayerModel", playerModel);
                            startActivity(playVideoIntent);
                            //final Intent playVideoIntent = new Intent(Episode_list_Activity.this, PlayerActivity.class);

                        }
                    } else {
                        stopLoader();
                        final Intent playVideoIntent = Util.getPlayerIntent(Episode_Activity.this);
                        playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        playVideoIntent.putExtra("PlayerModel", playerModel);
                        startActivity(playVideoIntent);

                    }
                }

            } else if (statusCode == 436) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Episode_Activity.this, R.style.MyAlertDialogStyle);
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
                Util.showNoDataAlert(Episode_Activity.this);
            }

        }
    }

    @Override
    public void onGetWatchHistoryEpisodeDetailsPreExecuteStarted() {
        pDialog = new ProgressBarHandler(Episode_Activity.this);
        pDialog.show();
    }

    @Override
    public void onGetWatchHistoryEpisodeDetailsPostExecuteCompleted(Watch_History_Episode_Details_output episode_details_output, int status, int i, String message, String movieUniqueId, boolean isCacheData, String apiOffset) {

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }

        } catch (IllegalArgumentException ex) {
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }

        if (apiOffset.trim().equals("1") && !isCacheData) {
            resetData();
        }


        isAPV = episode_details_output.getIsAPV();
        isPPV = episode_details_output.getIs_ppv();
        movieNameStr = episode_details_output.getName();
        itemData.clear();

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
            String is_media_published = episode_details_output.getEpisodeArray().get(a).getIs_media_published();
            String id = episode_details_output.getEpisodeArray().get(a).getId();

            String free_content = episode_details_output.getEpisodeArray().get(a).getIsFree();
            String is_livestream_enabled = episode_details_output.getEpisodeArray().get(a).getIs_livestream_enabled();

            itemData.add(new EpisodesListModel(episodeNoStr, episodeStoryStr, episodeDateStr,
                    episodeImageStr, episodeTitleStr, episodeVideoUrlStr, episodeSeriesNoStr,
                    movieUniqueId, episodeMovieStreamUniqueIdStr, episodeThirdParty, videodurationStr, episodeContenTTypesId, free_content, 0, "", is_media_published, id, is_livestream_enabled));

        }


        Util.currencyModel = episode_details_output.getCurrencyDetails();
        Util.apvModel = episode_details_output.getApvDetails();
        Util.ppvModel = episode_details_output.getPpvDetails();

        if (itemData.size() <= 0) {
            footerView.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
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
        pDialog = new ProgressBarHandler(Episode_Activity.this);
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
        pDialog = new ProgressBarHandler(Episode_Activity.this);
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
            Toast.makeText(Episode_Activity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code == 0) {
            Toast.makeText(Episode_Activity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code > 0) {
            if (code == 200) {
                preferenceManager.clearLoginPref();
                if ((featureHandler.getFeatureStatus(FeatureHandler.SIGNUP_STEP, FeatureHandler.DEFAULT_SIGNUP_STEP))) {
                    final Intent startIntent = new Intent(Episode_Activity.this, LoginActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(Episode_Activity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });
                } else {
                    final Intent startIntent = new Intent(Episode_Activity.this, MainActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(Episode_Activity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });
                }

            } else {
                Toast.makeText(Episode_Activity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

            }
        }
    }


    /**
     * @author Bibhu Prasad Jena
     * Desc: Following method is used to initialize the minicontroller.
     */
    public void initMinicontroller() {
        try {
            previous = findViewById(R.id.previous);
            play_pause = findViewById(R.id.play_pause);
            next = findViewById(R.id.next);
            cc_button = findViewById(R.id.cc_button);
            mini_controller_layout = findViewById(R.id.mini_controller_layout);
        } catch (Exception e) {
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_list);
        Util.check_for_subscription = 0;
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        languagePreference = LanguagePreference.getLanguagePreference(this);
        featureHandler = FeatureHandler.getFeaturePreference(Episode_Activity.this);
        isLogin = preferenceManager.getLoginFeatureFromPref();
        playerModel = new Player();
        nextPlayerModel = new Player();
        prevPlayerModel = new Player();
        playerModel.setIsstreaming_restricted(Util.getStreamingRestriction(languagePreference));
        episodeListOptionMenuHandler = new EpisodeListOptionMenuHandler(this);
        dbModel = new DataModel();
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Configuration config = getResources().getConfiguration();
            if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                //in Right To Left layout
                mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_right));
            } else {
                mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
            }

        } else {
            mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        }

        TextView sectionTitle = findViewById(R.id.sectionTitle);
        FontUtls.loadFont(Episode_Activity.this, getResources().getString(R.string.regular_fonts), sectionTitle);
        sectionTitle.setText(languagePreference.getTextofLanguage(EPISODE_TITLE, DEFAULT_EPISODE_TITLE));

        episodelist = findViewById(R.id.episodelist);
        noInternetConnectionLayout = findViewById(R.id.noInternet);
        noDataLayout = findViewById(R.id.noData);
        noInternetTextView = findViewById(R.id.noInternetTextView);
        noDataTextView = findViewById(R.id.noDataTextView);
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noDataTextView.setText(languagePreference.getTextofLanguage(NO_CONTENT, DEFAULT_NO_CONTENT));


        mLayoutManager = new LinearLayoutManager(Episode_Activity.this, LinearLayoutManager.VERTICAL, false);
        permalinkStr = getIntent().getStringExtra(PERMALINK_INTENT_KEY);
        video_title = getIntent().getStringExtra(VIDEO_TITLE_INTENT_KEY);

        mActionBarToolbar.setTitle(video_title);
        setSupportActionBar(mActionBarToolbar);
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        /**
         * Desc : Instance creation for minicontroller.
         */

        initMinicontroller();

        for (int i = 0; i < (getIntent().getStringArrayListExtra(SEASON_INTENT_KEY)).size(); i++) {
            seasonNo.add((getIntent().getStringArrayListExtra(SEASON_INTENT_KEY)).get(i));
        }

        for (int i = 0; i < (getIntent().getStringArrayListExtra(SEASON_INTENT_TITLE_KEY)).size(); i++) {
            seasonList.add(getIntent().getStringArrayListExtra(SEASON_INTENT_TITLE_KEY).get(i));
        }

        if ((getIntent().getStringArrayListExtra(SEASON_INTENT_KEY)).size() > 0) {
            selected_Season = seasonNo.get(0);
        }


        season_spinner = (Spinner) findViewById(R.id.seasonSpinner);
        if (Build.VERSION.SDK_INT < 23) {
            season_spinner.setBackgroundResource(R.drawable.lollipop_spinner_theme);
        }

        if (seasonList != null && seasonList.size() > 0) {
            season_spinner.setVisibility(View.VISIBLE);
            sectionTitle.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);
            ArrayAdapter adapter = new ArrayAdapter(Episode_Activity.this, R.layout.dropdownlist, seasonList);
            season_spinner.setAdapter(adapter);
        } else {
            season_spinner.setVisibility(View.GONE);
            sectionTitle.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }

        season_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {
                    selected_Season = seasonNo.get(position);

                    resetData();
                    if (NetworkStatus.getInstance().isConnected(Episode_Activity.this)) {
                        getEpisodeList();

                    } else {
                        noInternetConnectionLayout.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        footerView = (RelativeLayout) findViewById(R.id.loadingPanel);
        footerView.setVisibility(View.GONE);

        ppvmodel = new PPVModel();
        advmodel = new APVModel();
        currencymodel = new CurrencyModel();

        resetData();

        RecyclerViewMargin decoration = new RecyclerViewMargin(10, 2);
        episodelist.addItemDecoration(decoration);


        episodelist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                if (isLoading) {
                    if (totalItemCount > previousTotal) {
                        isLoading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!isLoading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem)) {
                    // End has been reached
                    listSize = itemData.size();
                    if (mLayoutManager.findLastVisibleItemPosition() >= itemsInServer - 1) {
                        isLoading = true;
                        footerView.setVisibility(View.GONE);
                        return;

                    }
                    offset += 1;
                    if (NetworkStatus.getInstance().isConnected(Episode_Activity.this)) {
                        // default data
                        Watch_History_Episode_Details_input watch_history_episodeDetailsInput = new Watch_History_Episode_Details_input();
                        watch_history_episodeDetailsInput.setAuthtoken(authTokenStr);
                        watch_history_episodeDetailsInput.setPermalink(permalinkStr);
                        watch_history_episodeDetailsInput.setSeries_number(selected_Season);
                        watch_history_episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
                        watch_history_episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                        watch_history_episodeDetailsInput.setUserId(preferenceManager.getUseridFromPref());


                        asynEpisodeDetails = new GetWatchHistoryEpisodeDeatailsAsynTask(watch_history_episodeDetailsInput, Episode_Activity.this, Episode_Activity.this, true, new DataController(Episode_Activity.this));
                        asynEpisodeDetails.executeOnExecutor(threadPoolExecutor);
                    }

                }

            }
        });


    }

    public void clickItem(EpisodesListModel item) {

        try {

            if (stopItemClick)
                return;


            isVoucher = 0;
            Util.check_for_subscription = 1;

            itemToPlay = item;
            dbModel.setIsAPV(isAPV);
            dbModel.setIsPPV(isPPV);
            dbModel.setIsConverted(1);
            dbModel.setMovieUniqueId(item.getEpisodeMuviUniqueId());
            dbModel.setStreamUniqueId(item.getEpisodeStreamUniqueId());
            dbModel.setThirdPartyUrl(item.getEpisodeThirdPartyUrl());
            dbModel.setVideoTitle(movieNameStr);
            // dbModel.setVideoStory(getIntent().getStringExtra(Util.STORY_INTENT_KEY));
            dbModel.setVideoStory(item.getEpisodeDescription());

            dbModel.setVideoGenre(getIntent().getStringExtra(GENRE_INTENT_KEY));
            dbModel.setVideoDuration(item.getEpisodeDuration());
            // dbModel.setVideoReleaseDate(item.getEpisodeTelecastOn());
            dbModel.setVideoReleaseDate("");

            dbModel.setCensorRating(getIntent().getStringExtra(CENSOR_RATING_INTENT_KEY));
            dbModel.setCastCrew(getIntent().getBooleanExtra(CAST_INTENT_KEY, false));
            dbModel.setEpisode_id(item.getEpisodeStreamUniqueId());
            dbModel.setPosterImageId(item.getEpisodeThumbnailImageView());

            dbModel.setContentTypesId(Integer.parseInt(getIntent().getStringExtra("content_types_id")));


            dbModel.setEpisode_series_no(item.getEpisodeSeriesNo());
            dbModel.setEpisode_no(item.getEpisodeNumber());
            dbModel.setEpisode_title(item.getEpisodeTitle());

            //set the required data in playermodel
            AutoPlayUtil.callAutoPlayAPI(item.getEpisodeStreamUniqueId(), Episode_Activity.this, playerModel);

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

            if (!selected_Season.equals("")) {
                dbModel.setSeason_id(selected_Season);

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
                        Util.showToast(Episode_Activity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
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
                    Util.showToast(Episode_Activity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
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
                    mLayoutManager = new GridLayoutManager(Episode_Activity.this, 1);
                    episodelist.setLayoutManager(mLayoutManager);

                } else if ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_NORMAL) {
                    mLayoutManager = new GridLayoutManager(Episode_Activity.this, 1);
                    episodelist.setLayoutManager(mLayoutManager);

                } else if ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_SMALL) {

                    mLayoutManager = new GridLayoutManager(Episode_Activity.this, 1);
                    episodelist.setLayoutManager(mLayoutManager);

                } else {
                    mLayoutManager = new GridLayoutManager(Episode_Activity.this, 1);
                    episodelist.setLayoutManager(mLayoutManager);
                }

                customListAdapter = new EpisodesListViewMoreAdapter(Episode_Activity.this, getItemLayoutId(), itemData, new EpisodesListViewMoreAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(EpisodesListModel item) {
                        clickItem(item);
                    }
                });
                episodelist.setAdapter(customListAdapter);

            } else {
                customListAdapter = new EpisodesListViewMoreAdapter(Episode_Activity.this, getItemLayoutId(), itemData, new EpisodesListViewMoreAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(EpisodesListModel item) {
                        clickItem(item);
                    }
                });
                episodelist.setAdapter(customListAdapter);

            }
        }

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

    private @LayoutRes
    int getItemLayoutId() {
        if (videoHeight > videoWidth) {
            return R.layout.episode_list_item_portrait;
        }
        return R.layout.episode_list_item;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        id = preferenceManager.getUseridFromPref();
        email = preferenceManager.getEmailIdFromPref();
        // Kushal

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
                final Intent searchIntent = new Intent(Episode_Activity.this, SearchActivity.class);
                searchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(searchIntent);
                // Not implemented here
                return false;

            case R.id.action_login:

                final Intent loginIntent = new Intent(Episode_Activity.this, LoginActivity.class);
                Util.check_for_subscription = 0;
                startActivity(loginIntent);
                return false;
            case R.id.action_register:

                Intent registerIntent = new Intent(Episode_Activity.this, RegisterActivity.class);
                Util.check_for_subscription = 0;
                startActivity(registerIntent);
                // Not implemented here
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
                    //code start: country code and language code added mantish id# 17373 @author :subhadarshani
                    languageListInputMode.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    languageListInputMode.setLangCode(default_Language);
                    //code  end
                    GetLanguageListAsynTask asynGetLanguageList = new GetLanguageListAsynTask(languageListInputMode, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
                    asynGetLanguageList.executeOnExecutor(threadPoolExecutor);
                }
                return false;
            case R.id.menu_item_profile:

                Intent profileIntent = new Intent(Episode_Activity.this, ProfileActivity.class);
                profileIntent.putExtra("EMAIL", email);
                profileIntent.putExtra("LOGID", id);
                startActivity(profileIntent);
                // Not implemented here
                return false;

            case R.id.action_logout:

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Episode_Activity.this, R.style.MyAlertDialogStyle);
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
                        LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, Episode_Activity.this, Episode_Activity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(Episode_Activity.this));
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
               /*
               Show to popup menu
                */
                showPopupMenu(findViewById(R.id.option));
                return false;
            default:
                break;
        }

        return false;
    }

    private void showPopupMenu(View viewById) {
        CardView viewGroup = findViewById(R.id.option_menu_layout);
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
                Intent loginIntent = new Intent(Episode_Activity.this, LoginActivity.class);
                Util.check_for_subscription = 0;
                startActivity(loginIntent);
                changeSortPopUp.dismiss();
                break;
            case R.id.register:
                Intent registerIntent = new Intent(Episode_Activity.this, RegisterActivity.class);
                Util.check_for_subscription = 0;
                startActivity(registerIntent);
                changeSortPopUp.dismiss();
                break;
            case R.id.language_popup:
                Default_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);
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
                Intent profileIntent = new Intent(Episode_Activity.this, ProfileActivity.class);
                profileIntent.putExtra("EMAIL", email);
                profileIntent.putExtra("LOGID", id);
                startActivity(profileIntent);
                changeSortPopUp.dismiss();
                break;

            case R.id.logout: {
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
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Episode_Activity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(languagePreference.getTextofLanguage(SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING));
        dlgAlert.setTitle("");

        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                if (NetworkStatus.getInstance().isConnected(Episode_Activity.this)) {
                    // dialog.cancel();
                    LogoutInput logoutInput = new LogoutInput();
                    logoutInput.setAuthToken(authTokenStr);
                    logoutInput.setLogin_history_id(preferenceManager.getLoginHistIdFromPref());
                    logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, Episode_Activity.this, Episode_Activity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(Episode_Activity.this));
                    asynLogoutDetails.executeOnExecutor(threadPoolExecutor);


                    dialog.dismiss();
                } else {
                    Toast.makeText(Episode_Activity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                }
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
    }

    private void initLayouts(View layout) {
        linearLayout = new LinearLayout[option_menu_id.length];
        for (int i = 0; i < option_menu_id.length; i++) {
            linearLayout[i] = layout.findViewById(option_menu_id[i]);
            setLanguageToTextViews(linearLayout[i], i);

        }
        //Added for notification count in option menu
        noti = layout.findViewById(R.id.count_notification);
        noti_layout = layout.findViewById(R.id.counterValuePanel);
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

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Episode_Activity.this, R.style.MyAlertDialogStyle);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View convertView = inflater.inflate(R.layout.language_pop_up, null);
        TextView titleTextView = convertView.findViewById(R.id.languagePopupTitle);
        titleTextView.setText(languagePreference.getTextofLanguage(APP_SELECT_LANGUAGE, DEFAULT_APP_SELECT_LANGUAGE));

        alertDialog.setView(convertView);
        alertDialog.setTitle("");

        RecyclerView recyclerView = convertView.findViewById(R.id.language_recycler_view);
        Button apply = convertView.findViewById(R.id.apply_btn);
        apply.setText(languagePreference.getTextofLanguage(BUTTON_APPLY, DEFAULT_BUTTON_APPLY));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        languageCustomAdapter = new LanguageCustomAdapter(Episode_Activity.this, Util.languageModel, null);
        recyclerView.setAdapter(languageCustomAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener1(Episode_Activity.this, recyclerView, new ClickListener1() {
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
                    GetTranslateLanguageAsync asynGetTransalatedLanguage = new GetTranslateLanguageAsync(languageListInputModel, Episode_Activity.this, Episode_Activity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(Episode_Activity.this));
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
        pDialog = new ProgressBarHandler(Episode_Activity.this);
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

    //Added by Abhishek For Language Translation for Filter Activity

    @Override
    public void onGetGenreListPreExecuteStarted() {

        pDialog = new ProgressBarHandler(Episode_Activity.this);
        pDialog.show();
    }

    @Override
    public void onGetGenreListPostExecuteCompleted(ArrayList<GenreListOutput> genreListOutput, ArrayList<SortByModel> sortByModelArrayList, int code, String status) {

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }
        languageCustomAdapter.notifyDataSetChanged();
        final Intent detailsIntent = new Intent(Episode_Activity.this, Episode_Activity.class);
        detailsIntent.putExtra(PERMALINK_INTENT_KEY, permalinkStr);
        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(detailsIntent);
        finish();
        preferenceManager.setLanguageChangeStatus("1");
    }

    public void genre_call(String default_Language) {
        GenreListInput genreListInput = new GenreListInput();
        genreListInput.setAuthToken(authTokenStr);
        genreListInput.setLang_code(default_Language);

        {
            genreListInput.setIs_specific("0"); // for others
        }

        genreListInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        GetGenreListAsynctask asynGetGenreList = new GetGenreListAsynctask(genreListInput, Episode_Activity.this, Episode_Activity.this, false, new DataController(Episode_Activity.this));
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
    protected void onPause() {
        super.onPause();
        try {

            cc_button.setVisibility(View.INVISIBLE);

        } catch (Exception e) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        GetIpAddressAsynTask asynGetIpAddress = new GetIpAddressAsynTask(this, this);
        asynGetIpAddress.executeOnExecutor(threadPoolExecutor);
        invalidateOptionsMenu();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1007) {

            if (data.getStringExtra("yes").equals("1002")) {
                watch_status_String = "halfplay";
                seek_status = "first_time";
                Played_Length = Util.dataModel.getPlayPos() * 1000;

            } else {
                watch_status_String = "strat";
                Played_Length = 0;
            }
        } else if (requestCode == VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE && resultCode == RESULT_OK) {
            new CheckVoucherOrPpvPaymentHandler(Episode_Activity.this).handleVoucherPaymentOrPpvPayment();
            // callValidateUserAPI();
        } else if (requestCode == PAYMENT_REQUESTCODE && resultCode == RESULT_OK) {
            getVideoInfo();
        } else if (requestCode == VIDEO_PLAY_BUTTON_CLICK_SUBSCRIPTION_REQUESTCODE && resultCode == RESULT_OK) {
            new CheckVoucherOrPpvPaymentHandler(Episode_Activity.this).handleVoucherPaymentOrPpvPayment();
            // callValidateUserAPI();
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
        startLoaeder();
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

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

                byte[] data = new byte[1024];
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
                stopLoader();
                playerModel.setSubTitlePath(SubTitlePath);
                Intent playVideoIntent;

                if (Util.dataModel.getAdNetworkId() == 3) {

                    playVideoIntent = Util.getPlayerIntent(Episode_Activity.this);

                } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                    if (Util.dataModel.getPlayPos() <= 0) {
                        playVideoIntent = new Intent(Episode_Activity.this, AdPlayerActivity.class);
                    } else {
                        playVideoIntent = Util.getPlayerIntent(Episode_Activity.this);

                    }
                } else {
                    playVideoIntent = Util.getPlayerIntent(Episode_Activity.this);

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
        asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, Episode_Activity.this, Episode_Activity.this);
        asynLoadVideoUrls.executeOnExecutor(threadPoolExecutor);
    }

    public void getEpisodeList() {
        Watch_History_Episode_Details_input watch_history_episodeDetailsInput = new Watch_History_Episode_Details_input();

        watch_history_episodeDetailsInput.setAuthtoken(authTokenStr);
        watch_history_episodeDetailsInput.setPermalink(permalinkStr);
        watch_history_episodeDetailsInput.setSeries_number(selected_Season);
        watch_history_episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
        watch_history_episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        watch_history_episodeDetailsInput.setUserId(preferenceManager.getUseridFromPref());

        asynEpisodeDetails = new GetWatchHistoryEpisodeDeatailsAsynTask(watch_history_episodeDetailsInput, Episode_Activity.this, Episode_Activity.this, true, new DataController(this));
        asynEpisodeDetails.executeOnExecutor(threadPoolExecutor);
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
            loadui.execute();
        }
    }

    public void startLoaeder() {
        try {
            if (!loaderIsShowing) {
                pageNaviagtionLoader = new ProgressBarHandler(Episode_Activity.this);
                pageNaviagtionLoader.show();
                loaderIsShowing = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopLoader() {
        try {
            startUiFreezeTimer();
            if (pageNaviagtionLoader != null && pageNaviagtionLoader.isShowing()) {
                pageNaviagtionLoader.hide();
                loaderIsShowing = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startUiFreezeTimer() {
        stopUiFreezeTimer();
        stopItemClick = true;

        try {
            UI_FREEZE_TIMER = new Timer();
            UI_FREEZE_TIMER.schedule(new TimerTask() {
                @Override
                public void run() {
                    stopUiFreezeTimer();
                }
            }, 1500, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stopUiFreezeTimer() {
        try {
            UI_FREEZE_TIMER.cancel();
            UI_FREEZE_TIMER = null;
            stopItemClick = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
