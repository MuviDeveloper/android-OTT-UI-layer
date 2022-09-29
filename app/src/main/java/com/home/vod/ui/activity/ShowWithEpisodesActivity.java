package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.APP_SELECT_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_APP_SELECT_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_EPISODES;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ERROR_IN_DATA_FETCHING;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NOTIFICATION_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PROFILE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PURCHASE_HISTORY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_RELATED_CONTENT_AUDIO_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_RELATED_CONTENT_VIDEO_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SEASON;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_WARNING;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIDEO_NOT_PUBLISHED;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIEW_MORE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_YES;
import static com.home.vod.preferences.LanguagePreference.EPISODES;
import static com.home.vod.preferences.LanguagePreference.ERROR_IN_DATA_FETCHING;
import static com.home.vod.preferences.LanguagePreference.LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.LOGIN;
import static com.home.vod.preferences.LanguagePreference.LOGOUT;
import static com.home.vod.preferences.LanguagePreference.LOGOUT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.NO;
import static com.home.vod.preferences.LanguagePreference.NOTIFICATION_TITLE;
import static com.home.vod.preferences.LanguagePreference.NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS;
import static com.home.vod.preferences.LanguagePreference.PROFILE;
import static com.home.vod.preferences.LanguagePreference.PURCHASE_HISTORY;
import static com.home.vod.preferences.LanguagePreference.RELATED_CONTENT_AUDIO_TITLE;
import static com.home.vod.preferences.LanguagePreference.RELATED_CONTENT_VIDEO_TITLE;
import static com.home.vod.preferences.LanguagePreference.SEASON;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.SIGN_OUT_WARNING;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.VIDEO_NOT_PUBLISHED;
import static com.home.vod.preferences.LanguagePreference.VIEW_MORE;
import static com.home.vod.preferences.LanguagePreference.YES;
import static com.home.vod.util.Constant.PERMALINK_INTENT_KEY;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.GridLayout.getSeasonGridLayout;
import static com.home.vod.util.Util.languageModel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.home.apisdk.apiController.AsyncLastSeenDetails;
import com.home.apisdk.apiController.GetContentDetailsAsynTask;
import com.home.apisdk.apiController.GetEpisodeDeatailsAsynTask;
import com.home.apisdk.apiController.GetGenreListAsynctask;
import com.home.apisdk.apiController.GetLanguageListAsynTask;
import com.home.apisdk.apiController.GetRelatedContentAsynTask;
import com.home.apisdk.apiController.GetSeasonListAsyncTask;
import com.home.apisdk.apiController.GetTranslateLanguageAsync;
import com.home.apisdk.apiController.LogoutAsynctask;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
import com.home.apisdk.apiModel.ContentDetailsInput;
import com.home.apisdk.apiModel.ContentDetailsOutput;
import com.home.apisdk.apiModel.Episode_Details_input;
import com.home.apisdk.apiModel.Episode_Details_output;
import com.home.apisdk.apiModel.GenreListInput;
import com.home.apisdk.apiModel.GenreListOutput;
import com.home.apisdk.apiModel.GetVideoDetailsInput;
import com.home.apisdk.apiModel.GetlastSeenDetailsInput;
import com.home.apisdk.apiModel.GetlastSeenDetailsOutputModel;
import com.home.apisdk.apiModel.LanguageListInputModel;
import com.home.apisdk.apiModel.LanguageListOutputModel;
import com.home.apisdk.apiModel.LogoutInput;
import com.home.apisdk.apiModel.RelatedContentInput;
import com.home.apisdk.apiModel.RelatedContentOutput;
import com.home.apisdk.apiModel.SeasonListInputModel;
import com.home.apisdk.apiModel.SeasonListOutputModel;
import com.home.apisdk.apiModel.SortByModel;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.vod.BuildConfig;
import com.home.vod.ObservableObject;
import com.home.vod.R;
import com.home.vod.async.GetImageSize;
import com.home.vod.handlers.EpisodeListOptionMenuHandler;
import com.home.vod.handlers.LoginRegistrationOnContentClickHandler;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.model.DataModel;
import com.home.vod.model.EpisodesListModel;
import com.home.vod.model.LanguageModel;
import com.home.vod.model.QueueModel;
import com.home.vod.model.RelatedContentModel;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.service.MusicService;
import com.home.vod.ui.MediaHelper;
import com.home.vod.ui.adapter.LanguageCustomAdapter;
import com.home.vod.ui.adapter.RelatedContentAdapter;
import com.home.vod.ui.adapter.SeasonListAdapter;
import com.home.vod.ui.widgets.AudioCustomMiniController;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.ui.widgets.ResizableCustomView;
import com.home.vod.util.AutoPlayUtil;
import com.home.vod.util.Constant;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.RecyclerViewMargin;
import com.home.vod.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import player.activity.AdPlayerActivity;
import player.activity.Player;
import player.utils.DBHelper;


public class ShowWithEpisodesActivity extends AppCompatActivity implements
        GetTranslateLanguageAsync.GetTranslateLanguageInfoListener,
        LogoutAsynctask.LogoutListener, GetLanguageListAsynTask.GetLanguageListListener,
        GetContentDetailsAsynTask.GetContentDetailsListener,
        GetEpisodeDeatailsAsynTask.GetEpisodeDetailsListener, GetSeasonListAsyncTask.GetSeasonListListener,
        VideoDetailsAsynctask.VideoDetailsListener,
        GetGenreListAsynctask.GenreListListener, Observer
        , GetRelatedContentAsynTask.GetRelatedContentListener, MediaHelper, AsyncLastSeenDetails.AsyncLastSeenDetailsListener {

    public Toolbar mActionBarToolbar;
    public ImageView toolbarimage;
    public ToolbarTitleHandler toolbarTitleHandler;

    private String movieDetailsStr = "", movieStreamId;
    private View year_bar, censor_bar;

    private static final int MAX_LINES = 3;
    private LanguagePreference languagePreference;
    private FeatureHandler featureHandler;
    private ProgressBarHandler pageNaviagtionLoader;
    private boolean loaderIsShowing = false;
    private boolean stopItemClick = false;
    private Timer UI_FREEZE_TIMER;
    private String loggedInIdStr, guestUserld;
    private ArrayList<RelatedContentModel> audioRelatedContentList, videoRelatedContentList;

    private int NO_IMAGE_HEIGHT = 500;
    private int NO_IMAGE_WIDTH = 300;
    private ArrayList<SeasonListOutputModel> seasonListOutputModels;

    private ProgressBarHandler pDialog;

    private TextView episodesTv;
    private String movieIdStr;
    private RecyclerView mRecyclerView;

    private int lastApiCallPoaition = 0;
    private EpisodeListOptionMenuHandler episodeListOptionMenuHandler;
    public static final int VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE = 8888;
    public static final int RESUME_WATCH_LASTSEEN_REQUESTCODE = 5673;

    private ArrayList<String> SubTitleName = new ArrayList<>();
    private ArrayList<String> SubTitlePath = new ArrayList<>();
    private ArrayList<String> ResolutionFormat = new ArrayList<>();
    private ArrayList<String> ResolutionUrl = new ArrayList<>();
    private ArrayList<String> SubTitleLanguage = new ArrayList<>();

    private boolean[] visibility;
    private String[] lang;
    private Snackbar snackbar = null;
    private Menu menu;
    public static TextView badge_text;
    public static TextView noti;
    public static FrameLayout noti_layout;

    private Context context = this;
    private EpisodesListAdapter mAdapter;
    private SeasonListAdapter seasonListAdapter;
    private int videoHeight = 185;
    private int videoWidth = 256;
    private int season_poster_height = 185;
    private int season_poster_width = 256;


    private int offset = 1;
    private int limit = 4;
    private int itemCount = -1;
    private boolean scroll = true;


    private DBHelper dbHelper;
    public Handler cancelDownloadHandler;
    private static final int REQUEST_STORAGE = 112;

    private TextView RelatedContentTitle, relatedAudioContentTitle;

    private TextView seasonTitle;
    private RecyclerView seasonList;
    private int mNoOfColumns;

    private View sheetView;
    private BottomSheetDialog mBottomSheetDialog;
    private TextView add_to_Queue;
    private String is_livestream_enable;
    private String livestream_resume_time;

    private CoordinatorLayout minicontroller;
    private SeekBar seekbar_botomSht, seekBar;
    private ImageView albumArt_player;
    private Button clearQueue;
    private LinearLayout open_bottomSheet_controller;
    private TextView song_p_name, totalduration, artist_p_name, songname_player, curent_duration, total_duration;
    private ImageView equalizer, background_player, player_close, player_image_main, player_play_ic, player_next_icc, player_prev_ic;
    private ProgressBar recylerview_progress, musicProgress;
    private ViewPager squizeViewPager;
    private View bottomSheet;
    public BottomSheetBehavior mBottomSheetBehavior;
    private AudioCustomMiniController audioCustomMiniController;

    private String email, id;
    private LanguageCustomAdapter languageCustomAdapter;
    private String Default_Language = "";
    private String Previous_Selected_Language = "";
    private int prevPosition = 0;


    private TextView videoStoryTextView;

    private VideoDetailsAsynctask asynLoadVideoUrls;
    private GetContentDetailsAsynTask asynLoadMovieDetails;
    private int isLogin = 0;
    private String movieUniqueId = "";

    private String censorRatingStr = "";
    private GetEpisodeDeatailsAsynTask asynEpisodeDetails;
    private int spinnerPosition = 0;
    private ImageView moviePoster, share_view;
    private ImageView playButton;
    private ImageButton offlineImageButton;
    private Button btnmore;
    private TextView videoTitle, videoGenreTextView, videoDurationTextView, videoCensorRatingTextView, videoCensorRatingTextView1, videoReleaseDateTextView;
    private Spinner season_spinner;
    private ArrayList<String> season;
    private EpisodesListModel itemToPlay;
    private boolean castStr = false;
    private int isFavorite;
    private String Season_Value = "";

    private AlertDialog alert;
    private Player playerModel;

    private LinearLayoutManager mLayoutManager;
    private String  bannerImageId, posterImageId, permalinkStr;
    private String movieTypeStr = "";
    private String videoduration = "";
    private String movieReleaseDateStr = "";
    private String name, loggedInStr, planid;
    private RelativeLayout noInternetConnectionLayout, noDataLayout, iconImageRelativeLayout, bannerImageRelativeLayout;
    private LinearLayout story_layout;
    private int isFreeContent = 0, isPPV, isConverted, contentTypesId, isAPV;
    private RecyclerView seasontiveLayout, relatedContent, relatedAudioContentRecyclerview;
    private NestedScrollView nestedScrollView;
    private int corePoolSize = 60;
    private int maximumPoolSize = 200;
    private int keepAliveTime = 10;
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    private Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
    private ArrayList<EpisodesListModel> itemData = new ArrayList<>();
    private String movieNameStr;
    private String episodeVideoUrlStr;
    private TextView noDataTextView;
    private TextView noInternetTextView;
    private PreferenceManager preferenceManager;

    /**
     * @description : Retry Button when connection fails and animation on button click
     */

    private Button btnRetry;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private TextView oopsTextView;
    private LinearLayout mini_controller_layout;


    @Override
    public void onGetTranslateLanguagePreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onGetTranslateLanguagePostExecuteCompleted(String jsonResponse, int status) {

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }

        if (status > 0 && status == 200) {

            try {
                Util.parseLanguage(languagePreference, jsonResponse, Default_Language);
                genre_call(Default_Language);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    public void onGetGenreListPreExecuteStarted() {

        pDialog = new ProgressBarHandler(ShowWithEpisodesActivity.this);
        pDialog.show();

    }

    @Override
    public void onGetGenreListPostExecuteCompleted(ArrayList<GenreListOutput> genreListOutput, ArrayList<SortByModel> sortByModelArrayList, int code, String status) {

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }
        languageCustomAdapter.notifyDataSetChanged();

        final Intent detailsIntent = new Intent(ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.class);
        detailsIntent.putExtra(PERMALINK_INTENT_KEY, permalinkStr);
        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(detailsIntent);
        finish();

        preferenceManager.setLanguageChangeStatus("1");


    }

    @Override
    public void onLogoutPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onLogoutPostExecuteCompleted(int code, String status, String message) {
        try {
            Util.check_for_subscription = 0;
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();

            }
        } catch (IllegalArgumentException ex) {
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }
        if (status == null) {
            Toast.makeText(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code == 0) {
            Toast.makeText(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();
        }
        if (code > 0) {
            if (code == 200) {
                preferenceManager.clearLoginPref();

                if ((featureHandler.getFeatureStatus(FeatureHandler.SIGNUP_STEP, FeatureHandler.DEFAULT_SIGNUP_STEP))) {
                    final Intent startIntent = new Intent(ShowWithEpisodesActivity.this, LoginActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });
                } else {
                    final Intent startIntent = new Intent(ShowWithEpisodesActivity.this, MainActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });
                }

            } else {
                Toast.makeText(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();
            }
        }

    }


    @Override
    public void onGetLanguageListPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onGetLanguageListPostExecuteCompleted(ArrayList<LanguageListOutputModel> languageListOutputArray, int status, String message, String defaultLanguage) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }

        ArrayList<LanguageModel> languageModels = new ArrayList<LanguageModel>();

        for (int i = 0; i < languageListOutputArray.size(); i++) {
            String language_id = languageListOutputArray.get(i).getLanguageCode();
            String language_name = languageListOutputArray.get(i).getLanguageName();


            LanguageModel languageModel = new LanguageModel();
            languageModel.setLanguageId(language_id);
            languageModel.setLanguageName(language_name);

            if (Default_Language.equalsIgnoreCase(language_id)) {
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
    public void onGetContentDetailsPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onGetContentDetailsPostExecuteCompleted(ContentDetailsOutput contentDetailsOutput, int status, String message) {
        String loggedInStr = preferenceManager.getUseridFromPref();

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }
        } catch (IllegalArgumentException ex) {
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }


        if (status == 200) {

            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.GONE);
            castStr = contentDetailsOutput.getCastStr();
            movieUniqueId = contentDetailsOutput.getMuviUniqId();
            movieStreamId = contentDetailsOutput.getMovieStreamId();
            movieNameStr = contentDetailsOutput.getName();

            videoduration = contentDetailsOutput.getVideoDuration();
            censorRatingStr = contentDetailsOutput.getCensorRating();
            movieTypeStr = contentDetailsOutput.getGenre();
            isFavorite = contentDetailsOutput.getIs_favorite();
            movieIdStr = contentDetailsOutput.getId();
            movieDetailsStr = contentDetailsOutput.getStory();
            bannerImageId = contentDetailsOutput.getBanner();
            posterImageId = contentDetailsOutput.getPoster();
            movieReleaseDateStr = contentDetailsOutput.getReleaseDate();
            isPPV = contentDetailsOutput.getIsPpv();
            isConverted = contentDetailsOutput.getIsConverted();
            contentTypesId = contentDetailsOutput.getContentTypesId();

            isAPV = contentDetailsOutput.getIsApv();

            try {
                isFreeContent = Integer.parseInt(contentDetailsOutput.getIsFreeContent());
                DataModel dbModel = new DataModel();
                dbModel.setIsFreeContent(isFreeContent);
                Util.dataModel = dbModel;
            } catch (NumberFormatException e) {
            } catch (ArithmeticException e) {
            } catch (Exception e) {
            }

            Util.currencyModel = contentDetailsOutput.getCurrencyDetails();
            Util.apvModel = contentDetailsOutput.getApvDetails();
            Util.ppvModel = contentDetailsOutput.getPpvDetails();

            String guestuser_login_status = null;
            if (preferenceManager != null)
                guestuser_login_status = preferenceManager.getGuestUseridFromPref();

            if ((guestuser_login_status == null) && (preferenceManager.getLoginFeatureFromPref() == 1) && Util.getPlayButtonStatus(contentDetailsOutput, ShowWithEpisodesActivity.this)) {
                playButton.setVisibility(View.VISIBLE);
            } else {
                playButton.setVisibility(View.GONE);
            }

            videoTitle.setVisibility(View.VISIBLE);
            FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.regular_fonts), videoTitle);
            videoTitle.setText(movieNameStr);


            if (movieTypeStr != null && movieTypeStr.matches("") || movieTypeStr.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoGenreTextView.setVisibility(View.GONE);

            } else {
                videoGenreTextView.setVisibility(View.VISIBLE);
                FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.light_fonts), videoGenreTextView);
                videoGenreTextView.setText(movieTypeStr);
            }

            if (contentDetailsOutput.getIs_livestream_enabled() == 1 || videoduration.matches("") || videoduration.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoDurationTextView.setVisibility(View.GONE);
            } else {
                videoDurationTextView.setVisibility(View.VISIBLE);
                if (getResources().getString(R.string.app_name).equals("Yesflix")) {
                    FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.regular_fonts), videoDurationTextView);
                } else {
                    FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.light_fonts), videoDurationTextView);
                }
                videoDurationTextView.setText(videoduration);
                videoDurationTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.demo, 0);
                videoDurationTextView.setCompoundDrawablePadding(0);
                iconImageRelativeLayout.setVisibility(View.VISIBLE);
            }
            if (movieDetailsStr.matches("") || movieDetailsStr.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoStoryTextView.setVisibility(View.GONE);
            } else {
                videoStoryTextView.setVisibility(View.VISIBLE);
                FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.light_fonts), videoStoryTextView);

                videoStoryTextView.setText(Util.getTextViewTextFromApi(contentDetailsOutput.getStory()));

                ResizableCustomView.doResizeTextView(ShowWithEpisodesActivity.this, videoStoryTextView, MAX_LINES, languagePreference.getTextofLanguage(VIEW_MORE, DEFAULT_VIEW_MORE), true);
            }


            if (censorRatingStr.matches("") ||
                    censorRatingStr.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoCensorRatingTextView.setVisibility(View.GONE);
                videoCensorRatingTextView1.setVisibility(View.GONE);

            } else {


                if (censorRatingStr.contains("-")) {
                    String Data[] = censorRatingStr.split("-");
                    videoCensorRatingTextView.setVisibility(View.VISIBLE);
                    videoCensorRatingTextView1.setVisibility(View.VISIBLE);

                    FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.light_fonts), videoCensorRatingTextView);
                    FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.light_fonts), videoCensorRatingTextView1);
                    if (Data.length > 0) {
                        videoCensorRatingTextView.setText(Data[0]);
                        videoCensorRatingTextView1.setText(Data[1]);
                    }
                } else {
                    videoCensorRatingTextView.setVisibility(View.VISIBLE);
                    videoCensorRatingTextView1.setVisibility(View.GONE);
                    FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.light_fonts), videoCensorRatingTextView);
                    videoCensorRatingTextView.setText(censorRatingStr);
                }


            }


            if (movieReleaseDateStr.matches("") || movieReleaseDateStr.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoReleaseDateTextView.setVisibility(View.GONE);
            } else {


                videoReleaseDateTextView.setVisibility(View.VISIBLE);
                if (getResources().getString(R.string.app_name).equals("Yesflix")) {
                    FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.regular_fonts), videoReleaseDateTextView);
                } else {
                    FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.light_fonts), videoReleaseDateTextView);
                }
                movieReleaseDateStr = Util.formateDateFromstring("yyyy-mm-dd", "yyyy", contentDetailsOutput.getReleaseDate());
                videoReleaseDateTextView.setText(movieReleaseDateStr);
            }

            if ((videoDurationTextView.getVisibility() == View.VISIBLE && videoCensorRatingTextView.getVisibility() == View.VISIBLE) ||
                    (videoDurationTextView.getVisibility() == View.VISIBLE && videoCensorRatingTextView1.getVisibility() == View.VISIBLE))
                censor_bar.setVisibility(View.VISIBLE);

            if ((videoDurationTextView.getVisibility() == View.VISIBLE || videoCensorRatingTextView.getVisibility() == View.VISIBLE
                    || videoCensorRatingTextView1.getVisibility() == View.VISIBLE) && videoReleaseDateTextView.getVisibility() == View.VISIBLE)
                year_bar.setVisibility(View.VISIBLE);


            if (season != null && season.size() > 0) {
                season.clear();
            }

            if (contentDetailsOutput.getSeason() != null && contentDetailsOutput.getSeason().length > 0) {
                for (int j = 0; j < contentDetailsOutput.getSeason().length; j++) {

                    season.add(languagePreference.getTextofLanguage(SEASON, DEFAULT_SEASON) + " " + contentDetailsOutput.getSeason()[j]);
                }
            }


            /**
             * Season page Feature is added here.
             */

            if (featureHandler.getFeatureStatus(FeatureHandler.IS_SEASON_PAGE_AVAILABLE, FeatureHandler.DEFAULT_IS_SEASON_PAGE_AVAILABLE)) {

                SeasonListInputModel seasonListInputModel = new SeasonListInputModel();
                seasonListInputModel.setAuthToken(authTokenStr);
                seasonListInputModel.setPermalink(permalinkStr.trim());
                seasonListInputModel.setLanguageCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                seasonListInputModel.setCountry(preferenceManager.getCountryCodeFromPref());
                GetSeasonListAsyncTask getSeasonListAsyncTask = new GetSeasonListAsyncTask(seasonListInputModel, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, true, new DataController(ShowWithEpisodesActivity.this));
                getSeasonListAsyncTask.execute();


            } else {


                if (contentDetailsOutput.getSeason() == null) {
                    Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                    episodeDetailsInput.setAuthtoken(authTokenStr);
                    episodeDetailsInput.setPermalink(permalinkStr.trim());
                    episodeDetailsInput.setLimit(String.valueOf(limit));
                    offset = 1;
                    episodeDetailsInput.setOffset(String.valueOf(offset));// episodeDetailsInput.setOffset("1");
                    episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
                    episodeDetailsInput.setSeries_number("");

                    asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ShowWithEpisodesActivity.this), false);
                    asynEpisodeDetails.execute(); //threadPoolExecutor
                }


                // Kushal - set id to spinner adapter Seasons
                ArrayAdapter adapter = new ArrayAdapter(ShowWithEpisodesActivity.this, R.layout.dropdownlist, season);
                season_spinner.setAdapter(adapter);
            }


            try {
                if (bannerImageId != null && !bannerImageId.equals("")) {
                    Picasso.with(ShowWithEpisodesActivity.this)
                            .load(bannerImageId.trim())
                            .error(R.drawable.logo)
                            .into(moviePoster);

                } else if (posterImageId != null && !posterImageId.equals("")) {
                    Picasso.with(ShowWithEpisodesActivity.this)
                            .load(posterImageId)
                            .error(R.drawable.logo)
                            .into(moviePoster);
                } else {
                    moviePoster.setImageResource(R.drawable.logo);
                }
            } catch (Exception e) {
                moviePoster.setImageResource(R.drawable.logo);
            }


        } else {
            noDataTextView.setText(languagePreference.getTextofLanguage(CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY, DEFAULT_CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY));
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.bringToFront();
            noDataLayout.setVisibility(View.VISIBLE);

            story_layout.setVisibility(View.GONE);
            bannerImageRelativeLayout.setVisibility(View.GONE);
            iconImageRelativeLayout.setVisibility(View.GONE);
            return;
        }


    }

    @Override
    public void onGetEpisodeDetailsPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onGetEpisodeDetailsPostExecuteCompleted(Episode_Details_output episode_details_output, int status, int item_count, String message, String movieUniqueId) {

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }
        } catch (IllegalArgumentException ex) {
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }
        // Related content API
        if (audioRelatedContentList.size() == 0 || videoRelatedContentList.size() == 0) {
            relatedContentAPICall();

        }

        String loggedInStr = preferenceManager.getLoginStatusFromPref();
        if (status == 200) {
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.GONE);
            isAPV = episode_details_output.getIsAPV();
            isPPV = episode_details_output.getIs_ppv();


            if (offset < 2)
                itemData.clear();

            Util.currencyModel = episode_details_output.getCurrencyDetails();
            Util.apvModel = episode_details_output.getApvDetails();
            Util.ppvModel = episode_details_output.getPpvDetails();
            for (int a = 0; a < episode_details_output.getEpisodeArray().size(); a++) {

                String episodeNoStr = episode_details_output.getEpisodeArray().get(a).getEpisode_number();
                String episodeStoryStr = episode_details_output.getEpisodeArray().get(a).getEpisode_story();
                String episodeDateStr = episode_details_output.getEpisodeArray().get(a).getEpisode_date();
                String episodeImageStr = episode_details_output.getEpisodeArray().get(a).getPoster_url();
                String episodeTitleStr = episode_details_output.getEpisodeArray().get(a).getEpisode_title();
                String episodeSeriesNoStr = episode_details_output.getEpisodeArray().get(a).getSeries_number();
                String episodeMovieStreamUniqueIdStr = episode_details_output.getEpisodeArray().get(a).getMovie_stream_uniq_id();
                String episodeThirdParty = episode_details_output.getEpisodeArray().get(a).getThirdparty_url();
                String is_converted = episode_details_output.getEpisodeArray().get(a).getIs_converted();
                String is_media_published = episode_details_output.getEpisodeArray().get(a).getIs_media_published();
                int episodeContenTTypesId = episode_details_output.getEpisodeArray().get(a).getContent_types_id();
                String videodurationStr = episode_details_output.getEpisodeArray().get(a).getVideo_duration();
                String free_content = episode_details_output.getEpisodeArray().get(a).getIsFree();
                String id = episode_details_output.getEpisodeArray().get(a).getId();
                String is_livestream_enabled = episode_details_output.getEpisodeArray().get(a).getIs_livestream_enabled();
                int downloadStatus;
                if (episodeThirdParty != null && episodeThirdParty.length() > 0) {
                    downloadStatus = 0;
                } else {
                    downloadStatus = episode_details_output.getEpisodeArray().get(a).getDownloadStatus();

                }

                itemData.add(new EpisodesListModel(episodeNoStr, episodeStoryStr, episodeDateStr, episodeImageStr, episodeTitleStr, episodeVideoUrlStr, episodeSeriesNoStr,
                        movieUniqueId, episodeMovieStreamUniqueIdStr, episodeThirdParty, videodurationStr, episodeContenTTypesId, free_content, downloadStatus, is_converted, is_media_published, id, is_livestream_enabled));
            }

            itemCount = item_count;
            seasontiveLayout.setVisibility(View.VISIBLE);
            seasontiveLayout.setLayoutManager(mLayoutManager);
            seasontiveLayout.setItemAnimator(new DefaultItemAnimator());


            /**
             * @description Added for calling api and setting adapter on click More.. button
             */


            if (offset < 2) {

                // todo image check

                /*
                 * desc: if first image appears no-image,then all the image will be in vertical size.*/
                if (itemData.size() > 0) {
                    episodesTv.setText(languagePreference.getTextofLanguage(EPISODES, DEFAULT_EPISODES));
                    if (Util.is_contain_noimage(itemData.get(0).getEpisodeThumbnailImageView())) {

                        videoHeight = NO_IMAGE_HEIGHT;
                        videoWidth = NO_IMAGE_WIDTH;

                        resetAdapter();

                        if (itemCount > itemData.size()) {
                            btnmore.setVisibility(View.VISIBLE);
                            setBtnmoreTranslationMore();
                        }

                    } else {

                        new GetImageSize(ShowWithEpisodesActivity.this, itemData.get(0).getEpisodeThumbnailImageView(), new GetImageSize.ImageSizeInterface() {
                            @Override
                            public void onImageLoaded(int width, int height) {
                                videoHeight = height;
                                videoWidth = width;

                                resetAdapter();

                                if (itemCount > itemData.size()) {
                                    btnmore.setVisibility(View.VISIBLE);
                                    setBtnmoreTranslationMore();

                                }

                            }

                            @Override
                            public void onImageLoadFailed(int width, int height) {

                            }
                        });
                    }

                }

            } else {

                btnmore.setVisibility(View.GONE);
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }

                if (itemData.size() == itemCount) {
                    setBtnmoreTranslationLess();
                }
            }


        }


    }


    @Override
    public void onVideoDetailsPreExecuteStarted() {
        startLoaeder();
    }

    @Override
    public void onVideoDetailsPostExecuteCompleted(Video_Details_Output _video_details_output,
                                                   int statusCode, String stus, String message) {


        if (_video_details_output != null) {
            if (_video_details_output.getFakeSubTitlePath().size() <= 0)
                stopLoader();

            boolean play_video = true;

            if (featureHandler.getFeatureStatus(FeatureHandler.IS_STREAMING_RESTRICTION, FeatureHandler.DEFAULT_IS_STREAMING_RESTRICTION) && preferenceManager.getUseridFromPref() != null) {

                play_video = !_video_details_output.getStreaming_restriction().trim().equals("0");
            } else {
                play_video = true;
            }
            if (!play_video) {
                stopLoader();

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ShowWithEpisodesActivity.this, R.style.MyAlertDialogStyle);
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

            playerModel.setHas_video_card(Integer.parseInt(_video_details_output.getHas_video_card()));
            playerModel.setIsOffline(_video_details_output.getIs_offline());
            playerModel.setSkipIntro(_video_details_output.getSkipIntroStartPosition(), _video_details_output.getSkipIntroEndPosition());
            playerModel.setDownloadStatus(_video_details_output.getDownload_status());


            playerModel.setNo_of_row(_video_details_output.getNo_of_row());
            playerModel.setNo_of_column(_video_details_output.getNo_of_column());
            playerModel.setThumb_interval(_video_details_output.getThumb_interval());
            playerModel.setVtt_with_sprite(_video_details_output.getVtt_with_sprite());
            playerModel.setVtt_without_sprite(_video_details_output.getVtt_without_sprite());
            playerModel.setMultiple_sprite_HostPrefix(_video_details_output.getMultiple_sprite_HostPrefix());
            playerModel.setSprite_image(_video_details_output.getSprite_image());


            if (_video_details_output.getThirdparty_url() == null || _video_details_output.getThirdparty_url().matches("")) {

                /**
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
            /*
             * @desc:parse offline_license_url and offline_license_token for pallycon drm content
             * */
            if (_video_details_output.getOfflineLicenseToken() != null) {
                playerModel.setOfflineLicenseToken(_video_details_output.getOfflineLicenseToken());
            }
            Util.dataModel.setVideoResolution(_video_details_output.getVideoResolution());

            playerModel.setVideoResolution(_video_details_output.getVideoResolution());
            if (_video_details_output.getPlayed_length() != null && !_video_details_output.getPlayed_length().equals(""))

                playerModel.setPlayPos((Util.isDouble(_video_details_output.getPlayed_length())));


            SubTitleName = _video_details_output.getSubTitleName();
            SubTitleLanguage = _video_details_output.getSubTitleLanguage();

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
            playerModel.setResolutionFormat(_video_details_output.getResolutionFormat());
            playerModel.setResolutionUrl(_video_details_output.getResolutionUrl());
            playerModel.setFakeSubTitlePath(_video_details_output.getFakeSubTitlePath());
            playerModel.setVideoResolution(_video_details_output.getVideoResolution());
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

                Util.showNoDataAlert(ShowWithEpisodesActivity.this);

            } else {
                {
                    // condition for checking if the response has third party url or not.
                    if (_video_details_output.getThirdparty_url() == null || _video_details_output.getThirdparty_url().matches("")) {


                        {


                            playerModel.setThirdPartyPlayer(false);

                            final Intent playVideoIntent;
                            if (Util.dataModel.getAdNetworkId() == 3) {

                                playVideoIntent = Util.getPlayerIntent(ShowWithEpisodesActivity.this);

                            } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                                if (Util.dataModel.getPlayPos() <= 0) {
                                    playVideoIntent = new Intent(ShowWithEpisodesActivity.this, AdPlayerActivity.class);
                                } else {
                                    playVideoIntent = Util.getPlayerIntent(ShowWithEpisodesActivity.this);

                                }


                            } else {
                                playVideoIntent = Util.getPlayerIntent(ShowWithEpisodesActivity.this);
                            }

                            stopLoader();
                            playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            playVideoIntent.putExtra("PlayerModel", playerModel);
                            startActivity(playVideoIntent);

                        }
                    } else {
                        stopLoader();
                        final Intent playVideoIntent = Util.getPlayerIntent(ShowWithEpisodesActivity.this);
                        playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        playVideoIntent.putExtra("PlayerModel", playerModel);
                        startActivity(playVideoIntent);
                    }
                }
            }

        } else if (statusCode == 436) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ShowWithEpisodesActivity.this, R.style.MyAlertDialogStyle);
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

            Util.showNoDataAlert(ShowWithEpisodesActivity.this);
        }


    }


    @Override
    public void update(Observable observable, Object data) {
        Intent intent = (Intent) data;
        if (intent.hasExtra("IntentFor")) {
            audioCustomMiniController.playerDetails(intent);

        }


    }


    @Override
    public void onGetRelatedContentPreExecuteStarted() {
    }

    @Override
    public void onGetRelatedContentPostExecuteCompleted(RelatedContentOutput relatedContentOutput, int status, String message) {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();

            }
        } catch (IllegalArgumentException ex) {
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }

        String loggedInStr = preferenceManager.getLoginStatusFromPref();


        if (relatedContentOutput.getContentData() == null)
            return;

        if (status == 200) {
            audioRelatedContentList = new ArrayList<RelatedContentModel>();
            videoRelatedContentList = new ArrayList<RelatedContentModel>();
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.GONE);
            for (int a = 0; a < relatedContentOutput.getContentData().size(); a++) {

                String episodeNoStr = relatedContentOutput.getContentData().get(a).getEpisode_number();
                String episodeStoryStr = relatedContentOutput.getContentData().get(a).getStory();
                String episodeDateStr = relatedContentOutput.getContentData().get(a).getRelease_date();
                String episodeImageStr = relatedContentOutput.getContentData().get(a).getPoster();
                String episodeTitleStr = relatedContentOutput.getContentData().get(a).getContent_title();
                String episodeSeriesNoStr = relatedContentOutput.getContentData().get(a).getSeason_number();
                String episodeMovieStreamUniqueIdStr = relatedContentOutput.getContentData().get(a).getMovie_stream_uniq_id();
                String episodeThirdParty = "";
                String episodeContenTTypesId = relatedContentOutput.getContentData().get(a).getContent_types_id();
                String videodurationStr = relatedContentOutput.getContentData().get(a).getVideo_duration();
                String movie_unique_id = relatedContentOutput.getContentData().get(a).getMovie_id();
                String is_episode = relatedContentOutput.getContentData().get(a).getIs_episode();
                String c_permalink = relatedContentOutput.getContentData().get(a).getC_permalink();
                String season_permalink = relatedContentOutput.getContentData().get(a).getSeason_permalink();


                if (relatedContentOutput.getContentData().get(a).getContent_types_id().equals("1") || relatedContentOutput.getContentData().get(a).getContent_types_id().equals("3")) {
                    videoRelatedContentList.add(new RelatedContentModel(episodeNoStr, episodeStoryStr, episodeDateStr, episodeImageStr, episodeTitleStr, episodeVideoUrlStr, episodeSeriesNoStr,
                            movie_unique_id, episodeMovieStreamUniqueIdStr, episodeThirdParty, videodurationStr, Integer.parseInt(episodeContenTTypesId), Integer.parseInt(is_episode), c_permalink, season_permalink));
                } else if (relatedContentOutput.getContentData().get(a).getContent_types_id().equals("5") || relatedContentOutput.getContentData().get(a).getContent_types_id().equals("6")) {
                    audioRelatedContentList.add(new RelatedContentModel(episodeNoStr, episodeStoryStr, episodeDateStr, episodeImageStr, episodeTitleStr, episodeVideoUrlStr, episodeSeriesNoStr,
                            movie_unique_id, episodeMovieStreamUniqueIdStr, episodeThirdParty, videodurationStr, Integer.parseInt(episodeContenTTypesId), Integer.parseInt(is_episode), c_permalink, ""));
                }

            }
            if (videoRelatedContentList.size() > 0) {
                RelatedContentTitle.setVisibility(View.VISIBLE);
                RelatedContentTitle.setText(languagePreference.getTextofLanguage(RELATED_CONTENT_VIDEO_TITLE, DEFAULT_RELATED_CONTENT_VIDEO_TITLE));
                LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(ShowWithEpisodesActivity.this, LinearLayoutManager.HORIZONTAL, false);
                relatedContent.setLayoutManager(mLayoutManager1);
                relatedContent.setItemAnimator(new DefaultItemAnimator());

                new GetImageSize(ShowWithEpisodesActivity.this, videoRelatedContentList.get(0).getEpisodeThumbnailImageView(), new GetImageSize.ImageSizeInterface() {
                    @Override
                    public void onImageLoaded(int width, int height) {
                        videoHeight = height;
                        videoWidth = width;
                        if (videoRelatedContentList.size() > 0) {
                            RelatedContentAdapter relatedContentAdapter = new RelatedContentAdapter(ShowWithEpisodesActivity.this, getItemLayoutIdForRelatedContent(), videoRelatedContentList, new RelatedContentAdapter.OnItemClickListener() {

                                @Override
                                public void onItemClick(RelatedContentModel item) {
                                    relatedContentItemClick(item);

                                }
                            });
                            relatedContent.setAdapter(relatedContentAdapter);
                        }
                    }

                    @Override
                    public void onImageLoadFailed(int width, int height) {

                    }
                });


            }
            if (audioRelatedContentList.size() > 0) {
                relatedAudioContentTitle.setVisibility(View.VISIBLE);
                relatedAudioContentTitle.setText(languagePreference.getTextofLanguage(RELATED_CONTENT_AUDIO_TITLE, DEFAULT_RELATED_CONTENT_AUDIO_TITLE));
                LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(ShowWithEpisodesActivity.this, LinearLayoutManager.HORIZONTAL, false);
                relatedAudioContentRecyclerview.setLayoutManager(mLayoutManager1);
                relatedAudioContentRecyclerview.setItemAnimator(new DefaultItemAnimator());

                new GetImageSize(ShowWithEpisodesActivity.this, audioRelatedContentList.get(0).getEpisodeThumbnailImageView(), new GetImageSize.ImageSizeInterface() {
                    @Override
                    public void onImageLoaded(int width, int height) {
                        videoHeight = height;
                        videoWidth = width;
                        if (audioRelatedContentList.size() > 0) {

                            RelatedContentAdapter relatedContentAdapter = new RelatedContentAdapter(ShowWithEpisodesActivity.this, getItemLayoutIdForRelatedContent(), audioRelatedContentList, new RelatedContentAdapter.OnItemClickListener() {

                                @Override
                                public void onItemClick(RelatedContentModel item) {
                                    relatedContentItemClick(item);

                                }
                            });

                            relatedAudioContentRecyclerview.setAdapter(relatedContentAdapter);
                        }
                    }

                    @Override
                    public void onImageLoadFailed(int width, int height) {

                    }
                });

            }
        }

    }

    @Override
    public void Transporter(QueueModel queueModel, int position) {
        if (audioCustomMiniController != null) {
            audioCustomMiniController.playSongFromPlayList(queueModel, position);

        }

    }

    @Override
    public void BottomOptionMenu(Context context, String SongName, String ArtistName, int Position) {

    }

    @Override
    public void onGetSeasonListPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onGetSeasonListPostExecuteCompleted(ArrayList<SeasonListOutputModel> arrayOfSeasonListOutputModel, int status, String message) {

        pDialog.hide();

        if (audioRelatedContentList.size() == 0 || videoRelatedContentList.size() == 0) {
            relatedContentAPICall();

        }
        seasonListOutputModels = arrayOfSeasonListOutputModel;

        try {
            if (arrayOfSeasonListOutputModel.size() > 0) {

                seasonTitle.setText(languagePreference.getTextofLanguage(SEASON, DEFAULT_SEASON));
                if (Util.is_contain_noimage(arrayOfSeasonListOutputModel.get(0).getMobile_poster())) {

                    season_poster_height = 300;
                    season_poster_width = 500;

                    setSeasonAdapter();

                } else {
                    if (arrayOfSeasonListOutputModel.get(0).getMobile_poster().trim().equals("")) {
                        setSeasonAdapter();
                    } else {

                        new GetImageSize(ShowWithEpisodesActivity.this, arrayOfSeasonListOutputModel.get(0).getMobile_poster(), new GetImageSize.ImageSizeInterface() {
                            @Override
                            public void onImageLoaded(int width, int height) {
                                season_poster_height = height;
                                season_poster_width = width;
                                setSeasonAdapter();
                            }

                            @Override
                            public void onImageLoadFailed(int width, int height) {

                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.toString();
        }
    }


    /**
     * @author Bibhu Prasad Jena
     * Desc: Following method is used to initialize the minicontroller.
     */
    public void initMinicontroller() {
        try {


            mini_controller_layout = findViewById(R.id.mini_controller_layout);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Configuration config = getResources().getConfiguration();
                if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                    //in Right To Left layout
                    mini_controller_layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

                }

            } else {
                mini_controller_layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * @Desc: This override method is used to called before the onCreate(),
     *       which help the resources to update Activity.
     * */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleLanguageHelper.onAttach(base));

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Util.check_for_subscription = 0;
        ObservableObject.getInstance().addObserver(this);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        pDialog = new ProgressBarHandler(ShowWithEpisodesActivity.this);
        setContentView(R.layout.activity_show_with_episodes);

        audioRelatedContentList = new ArrayList<>();
        videoRelatedContentList = new ArrayList<>();

        permalinkStr = getIntent().getStringExtra(PERMALINK_INTENT_KEY);
        seasonTitle = findViewById(R.id.seasonList_title);
        seasonList = findViewById(R.id.seasonList);


        seasonList.setNestedScrollingEnabled(false);
        seasonList.hasFixedSize();

        seasonList.addOnItemTouchListener(new RecyclerTouchListener1(ShowWithEpisodesActivity.this, seasonList, new ClickListener1() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(ShowWithEpisodesActivity.this, SeasonDetailsActivity.class);
                intent.putExtra("season_permalink", seasonListOutputModels.get(position).getPermalink());
                intent.putExtra("parent_permalink", permalinkStr);
                intent.putExtra("parent_poster", posterImageId);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        season = new ArrayList<String>();
        episodeListOptionMenuHandler = new EpisodeListOptionMenuHandler(this);
        languagePreference = LanguagePreference.getLanguagePreference(ShowWithEpisodesActivity.this);
        featureHandler = FeatureHandler.getFeaturePreference(ShowWithEpisodesActivity.this);
        playerModel = new Player();

        playerModel.setIsstreaming_restricted(Util.getStreamingRestriction(languagePreference));
        isLogin = preferenceManager.getLoginFeatureFromPref();
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);
        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*
         *
         * @Desc: This for setting up the toolbar back arrow icon change
         * */
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

        //To set Id to action bar back button
        setIdToActionBarBackButton(mActionBarToolbar);
        cancelDownloadHandler = new Handler();
        dbHelper = new DBHelper(ShowWithEpisodesActivity.this);
        dbHelper.getWritableDatabase();
        moviePoster = (ImageView) findViewById(R.id.bannerImageView);
        btnmore = (Button) findViewById(R.id.viewall);
        share_view = (ImageView) findViewById(R.id.share);

        FontUtls.loadFont(ShowWithEpisodesActivity.this, getResources().getString(R.string.regular_fonts), btnmore);


        btnmore.setVisibility(View.GONE);
        playButton = (ImageView) findViewById(R.id.play);


        offlineImageButton = (ImageButton) findViewById(R.id.offlineImageButton);
        videoTitle = (TextView) findViewById(R.id.content_title);
        videoGenreTextView = (TextView) findViewById(R.id.genre);
        videoDurationTextView = (TextView) findViewById(R.id.video_duration);
        videoCensorRatingTextView = (TextView) findViewById(R.id.videoCensorRatingTextView);
        videoReleaseDateTextView = (TextView) findViewById(R.id.video_release_date);
        videoStoryTextView = (TextView) findViewById(R.id.videoStoryTextView);
        episodesTv = findViewById(R.id.episodesTv);
        censor_bar = findViewById(R.id.censor_bar);
        year_bar = findViewById(R.id.year_bar);

        season_spinner = (Spinner) findViewById(R.id.seasonSpinner);
        RelatedContentTitle = (TextView) findViewById(R.id.related_content_title);
        relatedAudioContentTitle = (TextView) findViewById(R.id.related_audio_content_title);

        share_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                String link2 = "https://" + getResources().getString(R.string.host_name) + "/" + languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE) + "/" + permalinkStr + "?content_types_id=" + contentTypesId + "&permalink=" + permalinkStr;

                String shareBodyText = "Hey I'm watching " + movieNameStr + ". Check it out on " + getResources().getString(R.string.app_name) + "\n\n" + link2;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.host_name));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Intent.createChooser(sharingIntent, "Share"));
            }
        });
        if (Build.VERSION.SDK_INT < 23) {
            season_spinner.setBackgroundResource(R.drawable.lollipop_spinner_theme);
        }


        season_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (season != null && season.size() > 0 && getResources().getString(R.string.season_status).equals("1")) {
                    {
                        season_spinner.setVisibility(View.VISIBLE);
                    }
                    Configuration configuration = getResources().getConfiguration();
                    if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                        season_spinner.setRotation(360);
                    }
                } else {
                    season_spinner.setVisibility(View.GONE);
                }


                scroll = true; //@desc : This indicates that after selected spinner item, when click more button it will load more data.

                lastApiCallPoaition = 0;
                spinnerPosition = position;

                if (asynEpisodeDetails != null) {
                    asynEpisodeDetails.cancel(true);
                }
                btnmore.setVisibility(View.GONE);

                Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                episodeDetailsInput.setAuthtoken(authTokenStr);
                episodeDetailsInput.setPermalink(permalinkStr);

                episodeDetailsInput.setLimit(String.valueOf(limit)); //episodeDetailsInput.setLimit("4");

                /**
                 * @description Commented this because . we need to load 4 items on each view more click
                 */

                offset = 1;
                episodeDetailsInput.setOffset(String.valueOf(offset));// episodeDetailsInput.setOffset("1");
                episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());

                String data = season.get(spinnerPosition);
                String[] data1 = data.split(" ");

                if (data1.length > 0) {

                    episodeDetailsInput.setSeries_number(data1[1].trim());

                    Season_Value = data1[1].trim();

                }

                asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ShowWithEpisodesActivity.this), false);
                asynEpisodeDetails.execute(); //threadPoolExecutor


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        videoCensorRatingTextView1 = (TextView) findViewById(R.id.videoCensorRatingTextView1);
        if (!Util.isColorDark(getResources().getColor(R.color.appBackgroundColor))) {
            videoCensorRatingTextView.setBackgroundColor(ContextCompat.getColor(ShowWithEpisodesActivity.this, R.color.censorRatingBackground_white_theme));
        }
        seasontiveLayout = (RecyclerView) findViewById(R.id.featureContent);
        relatedContent = (RecyclerView) findViewById(R.id.related_content);


        relatedAudioContentRecyclerview = (RecyclerView) findViewById(R.id.related_audio_content);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        seasontiveLayout.setNestedScrollingEnabled(false);
        seasontiveLayout.hasFixedSize();

        noInternetConnectionLayout = (RelativeLayout) findViewById(R.id.noInternet);
        noDataLayout = (RelativeLayout) findViewById(R.id.noData);
        //noDataLayout.setVisibility(View.GONE);
        noInternetTextView = (TextView) findViewById(R.id.noInternetTextView);
        noDataTextView = (TextView) findViewById(R.id.noDataTextView);
        oopsTextView = (TextView) findViewById(R.id.oopsTextView); // Added by Debashish
        btnRetry = (Button) findViewById(R.id.btnRetry); // Added by Debashish

        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noDataTextView.setText(languagePreference.getTextofLanguage(NO_CONTENT, DEFAULT_NO_CONTENT));
        /**
         * @description : two keys added (1) Try Again ! (2) Oops !
         */

        btnRetry.setText("          " + languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN) + "       ");
        oopsTextView.setText(languagePreference.getTextofLanguage(OOPS, DEFAULT_OOPS));


        mLayoutManager = new LinearLayoutManager(ShowWithEpisodesActivity.this, LinearLayoutManager.VERTICAL, false); // LinearLayoutManager.HORIZONTAL
        iconImageRelativeLayout = (RelativeLayout) findViewById(R.id.iconImageRelativeLayout);
        bannerImageRelativeLayout = (RelativeLayout) findViewById(R.id.bannerImageRelativeLayout);
        story_layout = (LinearLayout) findViewById(R.id.story_layout);
        seasontiveLayout.setVisibility(View.GONE);
        episodeVideoUrlStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);


        /**
         * @description For margin between layouts
         *
         **/

        RecyclerViewMargin decoration = new RecyclerViewMargin(10, 2);
        seasontiveLayout.addItemDecoration(decoration);


        videoStoryTextView = (TextView) findViewById(R.id.videoStoryTextView);
        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if ((itemData.size() == itemCount) || (mLayoutManager.findLastVisibleItemPosition() != itemData.size() - 1)
                        || offset == 1) {
                    return;
                }

                if (lastApiCallPoaition == mLayoutManager.findLastVisibleItemPosition())
                    return;

                lastApiCallPoaition = mLayoutManager.findLastVisibleItemPosition();

                offset += 1;
                Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                episodeDetailsInput.setAuthtoken(authTokenStr);
                episodeDetailsInput.setPermalink(permalinkStr.trim());

                episodeDetailsInput.setLimit(String.valueOf(limit)); //episodeDetailsInput.setLimit("4");
                episodeDetailsInput.setOffset(String.valueOf(offset));// episodeDetailsInput.setOffset("1");

                try {
                    String data = season.get(spinnerPosition);
                    String[] data1 = data.split(" ");

                    if (data1.length > 0) {
                        episodeDetailsInput.setSeries_number(data1[1].trim());
                        Season_Value = data1[1].trim();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());

                asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ShowWithEpisodesActivity.this), false);
                asynEpisodeDetails.execute();


            }
        });


        /**
         * @description : onclicking Retry button
         */
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noInternetConnectionLayout.setVisibility(View.GONE);
                view.startAnimation(buttonClick);
                restartActivity();
            }
        });


        loggedInStr = preferenceManager.getLoginStatusFromPref();
        planid = preferenceManager.getUseridFromPref();

        if (planid == null)
            planid = "0";

        if (loggedInStr == null)
            loggedInStr = "0";


        permalinkStr = getIntent().getStringExtra(PERMALINK_INTENT_KEY);

        btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * @description After loading all data when click scroll to top arrow, it set the position to top
                 */


                if (scroll == false) {
                    nestedScrollView.scrollTo(0, season_spinner.getBottom());

                } else {
                    /**
                     * @description When clicking more 4 items will be added in recyclerview , if present
                     */

                    offset += 1;
                    Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                    episodeDetailsInput.setAuthtoken(authTokenStr);
                    episodeDetailsInput.setPermalink(permalinkStr.trim());

                    episodeDetailsInput.setLimit(String.valueOf(limit)); //episodeDetailsInput.setLimit("4");
                    episodeDetailsInput.setOffset(String.valueOf(offset));// episodeDetailsInput.setOffset("1");

                    try {
                        String data = season.get(spinnerPosition);
                        String[] data1 = data.split(" ");

                        if (data1.length > 0) {
                            episodeDetailsInput.setSeries_number(data1[1].trim());
                            Season_Value = data1[1].trim();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());

                    asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ShowWithEpisodesActivity.this), false);
                    asynEpisodeDetails.execute(); //threadPoolExecutor

                }


            }
        });

        if (NetworkStatus.getInstance().isConnected(ShowWithEpisodesActivity.this)) {
            ContentDetailsInput contentDetailsInput = new ContentDetailsInput();
            contentDetailsInput.setAuthToken(authTokenStr);
            contentDetailsInput.setPermalink(permalinkStr);
            contentDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
            contentDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
            contentDetailsInput.setState(preferenceManager.getStateFromPref());
            contentDetailsInput.setCity(preferenceManager.getCityFromPref());
            contentDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));

            asynLoadMovieDetails = new GetContentDetailsAsynTask(contentDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
            asynLoadMovieDetails.execute(); //threadPoolExecutor

        } else {

            noInternetConnectionLayout.setVisibility(View.VISIBLE);

        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.check_for_subscription = 1;
                String loggedInStr = "";
                String guestUserStr = preferenceManager.getGuestUseridFromPref();
                if (preferenceManager.getLoginFeatureFromPref() == 1) {
                    loggedInStr = preferenceManager.getUseridFromPref();
                }
                if (loggedInStr != null) {
                    getLastSeenDetails();
                } else {
                    Intent registerActivity = new LoginRegistrationOnContentClickHandler(ShowWithEpisodesActivity.this).handleClickOnContent();
                    registerActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    registerActivity.putExtra("PlayerModel", playerModel);
                    startActivityForResult(registerActivity, RESUME_WATCH_LASTSEEN_REQUESTCODE);
                }
            }
        });
    }

    /**
     * @decc: following method is responsible to get last seen content details.
     */
    public void getLastSeenDetails() {

        GetlastSeenDetailsInput getlastSeenDetailsInput = new GetlastSeenDetailsInput();
        getlastSeenDetailsInput.setAuthToken(authTokenStr);
        getlastSeenDetailsInput.setMuviUniqId(movieUniqueId);
        getlastSeenDetailsInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        getlastSeenDetailsInput.setLangCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        getlastSeenDetailsInput.setUserId(preferenceManager.getUseridFromPref());

        AsyncLastSeenDetails asyncLastSeenDetails = new AsyncLastSeenDetails(getlastSeenDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this);
        asyncLastSeenDetails.execute();
    }


    /**
     * @description Button text translation
     */

    private void setBtnmoreTranslationMore() {
        if (btnmore != null) {

            btnmore.setText(languagePreference.getTextofLanguage(VIEW_MORE, DEFAULT_VIEW_MORE));
            btnmore.setBackground(null);
            btnmore.getLayoutParams().height = (int) context.getResources().getDimension(R.dimen.viewmore_btn_height);
            btnmore.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
        }

    }

    private void setBtnmoreTranslationLess() {
        if (btnmore != null) {
            btnmore.setText("");
            btnmore.setVisibility(View.VISIBLE);
            btnmore.setBackground(context.getResources().getDrawable(R.drawable.arrow_up_seasons));
            btnmore.getLayoutParams().height = 100;
            btnmore.getLayoutParams().width = 100;

            scroll = false; //  @desc : This indicates that after loading alldata when click scroll to top arrow , this value will check

        }
    }


    public void restartActivity() {

        try {

            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);

        } catch (Exception e) {
        }
    }

    private void resetAdapter() {

        mAdapter = new EpisodesListAdapter(ShowWithEpisodesActivity.this, getItemLayoutId(), itemData);
        seasontiveLayout.setAdapter(mAdapter);
    }

    private void setSeasonAdapter() {

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        mNoOfColumns = Util.calculateNoOfColumns(getApplicationContext());


        if (season_poster_width > season_poster_height) {
            if (!Util.isTablet(ShowWithEpisodesActivity.this))
                mNoOfColumns = 2;
            else
                mNoOfColumns = 3;
        }

        if (season_poster_width < season_poster_height) {
            if (!Util.isTablet(ShowWithEpisodesActivity.this))
                mNoOfColumns = 2;
            else
                mNoOfColumns = 4;

        }


        seasonList.setLayoutManager(new GridLayoutManager(getApplicationContext(), mNoOfColumns));

        float density = context.getResources().getDisplayMetrics().density;
        seasonListAdapter = new SeasonListAdapter(getApplicationContext(), getSeasonGridLayout(ShowWithEpisodesActivity.this, season_poster_width, season_poster_height), seasonListOutputModels);
        seasonList.setAdapter(seasonListAdapter);

        seasonList.setVisibility(View.VISIBLE);
        seasonTitle.setVisibility(View.VISIBLE);
    }

    private @LayoutRes
    int getItemLayoutId() {

        /**
         * @description : New UI Change mantis: 19619
         */
        if (videoHeight > videoWidth) {
            return R.layout.episode_list_item_portrait;
        }
        return R.layout.episode_list_item;
    }


    @Override
    public void onBackPressed() {

        if (audioCustomMiniController.bootomsheet_open) {
            audioCustomMiniController.collapse_buttomsheet();
            return;
        }

        if (asynLoadVideoUrls != null) {
            asynLoadVideoUrls.cancel(true);
        }
        if (asynLoadMovieDetails != null) {
            asynLoadMovieDetails.cancel(true);
        }

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();


        }

        finish();
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    public void clickItem(EpisodesListModel item) {
        if (item != null) {
            AutoPlayUtil.callAutoPlayAPI(item.getEpisodeStreamUniqueId(), getApplicationContext(), playerModel);

            /*
             * @desc:check if audio is playing or not. If audio is playing stop audio player*/
            if (preferenceManager.getSongStatusFromPref() != null && preferenceManager.getSongStatusFromPref().equals("playing")) {
                try {
                    Intent Pintent = new Intent(Constant.CLOSE_NOTIFICATION);
                    Pintent.putExtra("closeNotification", "close");
                    sendBroadcast(Pintent);
                    preferenceManager.setSongStatustoPref("close");
                    if (MusicService.mediaPlayer != null) {
                        MusicService.mediaPlayer.setPlayWhenReady(false);
                        MusicService.mediaPlayer.release();
                    }

                    stopService(new Intent(ShowWithEpisodesActivity.this, MusicService.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            if (stopItemClick)
                return;

            try {
                if (!NetworkStatus.getInstance().isConnected(ShowWithEpisodesActivity.this)) {
                    noInternetConnectionLayout.setVisibility(View.VISIBLE);
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            if (item.getIs_media_published().equals("0")) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ShowWithEpisodesActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(languagePreference.getTextofLanguage(VIDEO_NOT_PUBLISHED, DEFAULT_VIDEO_NOT_PUBLISHED));
                dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
                //dlgAlert.create().show();
                AlertDialog alertDialog = dlgAlert.create();

                // show it
                alertDialog.show();
                TextView tv = (TextView) alertDialog.findViewById(android.R.id.message);

                /*
                 * @Desc: This for checking dialog messsage alignments for RTL support
                 * */
                Configuration configuration = getResources().getConfiguration();
                if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                    if (tv != null) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

                    }
                }
                return;
            }

            if (item.getIs_converted().equals("0")) {

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ShowWithEpisodesActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(languagePreference.getTextofLanguage(NO_DETAILS_AVAILABLE, DEFAULT_NO_DETAILS_AVAILABLE));
                dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
                AlertDialog alertDialog = dlgAlert.create();

                // show it
                alertDialog.show();
                TextView tv = (TextView) alertDialog.findViewById(android.R.id.message);

                /*
                 * @Desc: This for checking dialog messsage alignments for RTL support
                 * */
                Configuration configuration = getResources().getConfiguration();
                if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                    if (tv != null) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

                    }
                }
                return;
            }


            itemToPlay = item;

            DataModel dbModel = new DataModel();
            dbModel.setIsFreeContent(isFreeContent);
            dbModel.setIsAPV(isAPV);
            dbModel.setIsPPV(isPPV);
            dbModel.setIsConverted(1);
            dbModel.setMovieUniqueId(movieUniqueId);
            dbModel.setStreamUniqueId(item.getEpisodeStreamUniqueId());
            dbModel.setThirdPartyUrl(item.getEpisodeThirdPartyUrl());
            dbModel.setVideoTitle(movieNameStr);
            dbModel.setVideoStory(item.getEpisodeDescription());
            dbModel.setVideoGenre(videoGenreTextView.getText().toString());
            dbModel.setVideoDuration(item.getEpisodeDuration());
            dbModel.setVideoReleaseDate("");

            dbModel.setCensorRating(censorRatingStr);
            dbModel.setCastCrew(castStr);
            dbModel.setEpisode_id(item.getEpisodeStreamUniqueId());
            dbModel.setSeason_id(Season_Value);
            dbModel.setPurchase_type("episode");
            dbModel.setPosterImageId(item.getEpisodeThumbnailImageView());
            dbModel.setContentTypesId(contentTypesId);

            dbModel.setEpisode_series_no(item.getEpisodeSeriesNo());
            dbModel.setEpisode_no(item.getEpisodeNumber());
            dbModel.setEpisode_title(item.getEpisodeTitle());
            dbModel.setIs_converted(item.getIs_converted());
            dbModel.setId(item.getId());

            Util.dataModel = dbModel;

            SubTitleName.clear();
            SubTitlePath.clear();
            ResolutionUrl.clear();
            ResolutionFormat.clear();
            SubTitleLanguage.clear();

            playerModel.setStreamUniqueId(item.getEpisodeStreamUniqueId());
            playerModel.setMovieUniqueId(item.getEpisodeMuviUniqueId());
            playerModel.setUserId(preferenceManager.getUseridFromPref());
            playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
            playerModel.setAuthTokenStr(authTokenStr.trim());
            playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH.trim());
            playerModel.setEpisode_id(item.getEpisodeStreamUniqueId());
            playerModel.setVideoTitle(item.getEpisodeTitle());

            playerModel.setSeries_number(item.getEpisodeSeriesNo());
            playerModel.setEpisode_number(item.getEpisodeNumber());
            playerModel.setParent_name(movieNameStr);

            try {
                playerModel.setIsFreeContent(Integer.parseInt(item.getIsFreeContnet()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            playerModel.setVideoStory(item.getEpisodeDescription());
            playerModel.setVideoGenre(videoGenreTextView.getText().toString());
            playerModel.setVideoDuration(item.getEpisodeDuration());
            playerModel.setVideoReleaseDate("");
            playerModel.setCensorRating(censorRatingStr);
            playerModel.setContentTypesId(contentTypesId);
            try {
                playerModel.setPosterImageId(item.getEpisodeThumbnailImageView());
            } catch (Exception e) {
                playerModel.setPosterImageId("https://d2gx0xinochgze.cloudfront.net/public/no-image-a.png");
            }
            playerModel.setCastCrew(castStr);

            String loggedInStr = preferenceManager.getLoginStatusFromPref();
            String guestUserStr = preferenceManager.getGuestUseridFromPref();
            Util.check_for_subscription = 1;
            if (isLogin == 1) {
                if (loggedInStr != null) {
                    if (NetworkStatus.getInstance().isConnected(this)) {
                        getVideoInfo();
                    } else {
                        Util.showToast(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
                    }

                } else {
                    if ((playerModel.getIsFreeContent() == 1) && !featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN_REQUIRED_FOR_FREECONTENT, FeatureHandler.DEFAULT_IS_LOGIN_REQUIRED_FOR_FREECONTENT)) {
                        getVideoInfo();

                    } else {

                        if (guestUserStr != null) {
                            if (NetworkStatus.getInstance().isConnected(ShowWithEpisodesActivity.this)) {
                                getVideoInfo();

                            } else {
                                Toast.makeText(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Util.check_for_subscription = 1;
                            Intent registerActivity = new LoginRegistrationOnContentClickHandler(this).handleClickOnContent();
                            registerActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            registerActivity.putExtra("PlayerModel", playerModel);
                            startActivityForResult(registerActivity, VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE);

                        }
                    }
                }
            } else {
                if (NetworkStatus.getInstance().isConnected(this)) {
                    getVideoInfo();

                } else {
                    Util.showToast(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
                }
            }
        }


    }


    @Override
    public void onLastSeenDetailsPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onLastSeenDetailsPostExecuteCompleted(GetlastSeenDetailsOutputModel GetlastSeenDetailsOutputModel, int status, String message) {

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (status == 200) {

            AutoPlayUtil.callAutoPlayAPI(GetlastSeenDetailsOutputModel.getLastSeenStreamUniqId(), getApplicationContext(), playerModel);

            playerModel.setStreamUniqueId(GetlastSeenDetailsOutputModel.getLastSeenStreamUniqId());
            playerModel.setEpisode_id(GetlastSeenDetailsOutputModel.getLastSeenStreamUniqId());
            playerModel.setMovieUniqueId(GetlastSeenDetailsOutputModel.getLastSeenMuviUniqId());
            playerModel.setVideoTitle(GetlastSeenDetailsOutputModel.getContent_title());
            playerModel.setParent_poster(GetlastSeenDetailsOutputModel.getParent_poster());
            playerModel.setEpisode_number(GetlastSeenDetailsOutputModel.getEpisode_no());
            playerModel.setParent_name(GetlastSeenDetailsOutputModel.getParent_title());
            Util.complete_status_of_resume_lastseen = GetlastSeenDetailsOutputModel.getCompletedStatus();

            playerModel.setVideoStory(GetlastSeenDetailsOutputModel.getStory());
            playerModel.setContentTypesId(contentTypesId);
            playerModel.setPosterImageId(GetlastSeenDetailsOutputModel.getDefault_poster());
            playerModel.setSeries_number(GetlastSeenDetailsOutputModel.getSeriesNo());
            playerModel.setEpisode_number(GetlastSeenDetailsOutputModel.getEpisode_no());

            playerModel.setVideoDuration(GetlastSeenDetailsOutputModel.getVideo_duration());
            playerModel.setVideoStory(GetlastSeenDetailsOutputModel.getStory());

            DataModel dbModel = new DataModel();
            dbModel.setIsFreeContent(isFreeContent);
            dbModel.setIsAPV(isAPV);
            dbModel.setIsPPV(isPPV);
            dbModel.setIsConverted(1);
            dbModel.setMovieUniqueId(GetlastSeenDetailsOutputModel.getLastSeenMuviUniqId());
            dbModel.setStreamUniqueId(GetlastSeenDetailsOutputModel.getLastSeenStreamUniqId());
            dbModel.setThirdPartyUrl("");
            dbModel.setVideoTitle(movieNameStr);
            dbModel.setVideoStory(GetlastSeenDetailsOutputModel.getStory());
            dbModel.setVideoGenre(videoGenreTextView.getText().toString());
            dbModel.setVideoDuration(GetlastSeenDetailsOutputModel.getVideo_duration());
            dbModel.setVideoReleaseDate("");
            dbModel.setCensorRating(censorRatingStr);
            dbModel.setCastCrew(castStr);
            dbModel.setEpisode_id(GetlastSeenDetailsOutputModel.getLastSeenStreamUniqId());
            dbModel.setSeason_id(GetlastSeenDetailsOutputModel.getSeriesNo());
            dbModel.setPurchase_type("episode");
            dbModel.setPosterImageId(GetlastSeenDetailsOutputModel.getDefault_poster());
            dbModel.setContentTypesId(contentTypesId);
            dbModel.setEpisode_series_no(GetlastSeenDetailsOutputModel.getSeriesNo());
            dbModel.setEpisode_no(GetlastSeenDetailsOutputModel.getEpisode_no());
            dbModel.setEpisode_title(GetlastSeenDetailsOutputModel.getContent_title());
            dbModel.setIs_converted("");
            dbModel.setContentTypesId(contentTypesId);

            Util.dataModel = dbModel;

            if (isLogin == 1) {
                if (preferenceManager != null) {
                    loggedInStr = preferenceManager.getUseridFromPref();
                }
                if (loggedInStr != null) {
                    getVideoInfo();
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

                if (featureHandler.getFeatureStatus(FeatureHandler.IS_REGISTRATION_ENABLED, FeatureHandler.DEFAULT_IS_REGISTRATION_ENABLED)) {
                    Util.check_for_subscription = 1;
                    Intent registerActivity = new LoginRegistrationOnContentClickHandler(this).handleClickOnContent();
                    registerActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    registerActivity.putExtra("PlayerModel", playerModel);
                    startActivityForResult(registerActivity, VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE);
                } else {

                    if (NetworkStatus.getInstance().isConnected(this)) {
                        getVideoInfo();
                    } else {
                        Util.showToast(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
                    }
                }
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        id = preferenceManager.getUseridFromPref();
        email = preferenceManager.getEmailIdFromPref();

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
                final Intent searchIntent = new Intent(ShowWithEpisodesActivity.this, SearchActivity.class);
                searchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(searchIntent);
                // Not implemented here
                return false;


            case R.id.action_login:

                Intent loginIntent = new Intent(ShowWithEpisodesActivity.this, LoginActivity.class);
                Util.check_for_subscription = 0;
                startActivity(loginIntent);
                // Not implemented here
                return false;

            case R.id.action_register:

                Intent registerIntent = new Intent(ShowWithEpisodesActivity.this, RegisterActivity.class);
                Util.check_for_subscription = 0;
                startActivity(registerIntent);
                // Not implemented here
                return false;
            case R.id.menu_item_language:

                // Not implemented here
                Default_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);
                Previous_Selected_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);

                if (Util.languageModel != null && Util.languageModel.size() > 0) {


                    ShowLanguagePopup();

                } else {
                    LanguageListInputModel languageListInputModel = new LanguageListInputModel();
                    languageListInputModel.setAuthToken(authTokenStr);
                    //code start: country code and language code added mantish id# 17373 @author :subhadarshani
                    languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    languageListInputModel.setLangCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    //code  end
                    GetLanguageListAsynTask asynGetLanguageList = new GetLanguageListAsynTask(languageListInputModel, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
                    asynGetLanguageList.execute(); //threadPoolExecutor
                }
                return false;


            case R.id.menu_item_profile:

                Intent profileIntent = new Intent(ShowWithEpisodesActivity.this, ProfileActivity.class);
                profileIntent.putExtra("EMAIL", email);
                profileIntent.putExtra("LOGID", id);
                startActivity(profileIntent);
                // Not implemented here
                return false;


            case R.id.action_logout:

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ShowWithEpisodesActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(languagePreference.getTextofLanguage(SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING));
                dlgAlert.setTitle("");

                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        // dialog.cancel();
                        if (NetworkStatus.getInstance().isConnected(ShowWithEpisodesActivity.this)) {
                            LogoutInput logoutInput = new LogoutInput();
                            logoutInput.setAuthToken(authTokenStr);
                            logoutInput.setLogin_history_id(preferenceManager.getLoginHistIdFromPref());
                            logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                            logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                            LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ShowWithEpisodesActivity.this));
                            asynLogoutDetails.execute(); //threadPoolExecutor


                            dialog.dismiss();
                        } else {
                            Toast.makeText(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
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

                return false;

            default:
                break;
        }

        return false;
    }


    private void logoutPopup() {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ShowWithEpisodesActivity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(languagePreference.getTextofLanguage(SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING));
        dlgAlert.setTitle("");

        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                // dialog.cancel();
                if (NetworkStatus.getInstance().isConnected(ShowWithEpisodesActivity.this)) {
                    LogoutInput logoutInput = new LogoutInput();
                    logoutInput.setAuthToken(authTokenStr);
                    String loginHistoryIdStr = preferenceManager.getLoginHistIdFromPref();
                    logoutInput.setLogin_history_id(loginHistoryIdStr);
                    logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ShowWithEpisodesActivity.this));
                    asynLogoutDetails.execute(); //threadPoolExecutor


                    dialog.dismiss();
                } else {
                    Toast.makeText(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
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

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShowWithEpisodesActivity.this, R.style.MyAlertDialogStyle);
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

        languageCustomAdapter = new LanguageCustomAdapter(ShowWithEpisodesActivity.this, Util.languageModel, null);

        recyclerView.setAdapter(languageCustomAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener1(ShowWithEpisodesActivity.this, recyclerView, new ClickListener1() {
            @Override
            public void onClick(View view, int position) {
                Util.itemclicked = true;
                Util.languageModel.get(position).setSelected(true);

                if (prevPosition != position) {
                    Util.languageModel.get(prevPosition).setSelected(false);
                    prevPosition = position;
                }

                Default_Language = Util.languageModel.get(position).getLanguageId();
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


                if (!Previous_Selected_Language.equals(Default_Language)) {

                    LanguageListInputModel languageListInputModel = new LanguageListInputModel();
                    languageListInputModel.setAuthToken(authTokenStr);
                    languageListInputModel.setLangCode(Default_Language);
                    //code start: country code added mantish id# 17373
                    languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    GetTranslateLanguageAsync asynGetTransalatedLanguage = new GetTranslateLanguageAsync(languageListInputModel, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ShowWithEpisodesActivity.this));
                    asynGetTransalatedLanguage.execute(); //threadPoolExecutor
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


    @Override
    protected void onPause() {
        super.onPause();


        try {
            unregisterReceiver(CLOSE_NOTIFICATION);
            unregisterReceiver(SongStatusReciver);
            unregisterReceiver(SONG_STATUS_NEXT);
            unregisterReceiver(SONG_STATUS_PREVIOUS);


        } catch (Exception e) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            registerReceiver(SongStatusReciver, new IntentFilter(Constant.SONG_STATUS));
            registerReceiver(CLOSE_NOTIFICATION, new IntentFilter(Constant.CLOSE_NOTIFICATION));
            registerReceiver(SONG_STATUS_NEXT, new IntentFilter(Constant.SONG_STATUS_NEXT));
            registerReceiver(SONG_STATUS_PREVIOUS, new IntentFilter(Constant.SONG_STATUS_PREVIOUS));

        } catch (Exception e) {
            e.printStackTrace();
        }

        initAudioCustomMiniController();
        invalidateOptionsMenu();



    }

    public interface ClickListener1 {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 111:

                if (grantResults.length > 0) {
                    if ((grantResults.length > 0) && (grantResults[0]) == PackageManager.PERMISSION_GRANTED) {
                        //Call whatever you want

                        if (NetworkStatus.getInstance().isConnected(this)) {
                            ContentDetailsInput contentDetailsInput = new ContentDetailsInput();
                            contentDetailsInput.setAuthToken(authTokenStr);
                            contentDetailsInput.setPermalink(permalinkStr);
                            contentDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
                            contentDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
                            contentDetailsInput.setState(preferenceManager.getStateFromPref());
                            contentDetailsInput.setCity(preferenceManager.getCityFromPref());
                            contentDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));

                            asynLoadMovieDetails = new GetContentDetailsAsynTask(contentDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
                            asynLoadMovieDetails.execute(); //threadPoolExecutor

                        } else {
                            noInternetConnectionLayout.setVisibility(View.VISIBLE);
                        }

                    } else {
                        finish();
                    }
                } else {
                    finish();
                }

                break;

        }
    }


    public void showToast(String message) {
        showSnackBar(message);
    }

    private void showSnackBar(String message) {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            View v = findViewById(android.R.id.content);
            if (!message.equals(languagePreference.getTextofLanguage(ERROR_IN_DATA_FETCHING, DEFAULT_ERROR_IN_DATA_FETCHING))) {
                if (snackbar != null) {
                    if (snackbar.isShown()) {
                        snackbar.dismiss();
                        snackbar.make(v, message, Snackbar.LENGTH_LONG).show();
                    } else {
                        snackbar.make(v, message, Snackbar.LENGTH_LONG).show();
                    }
                } else
                    snackbar.make(v, message, Snackbar.LENGTH_LONG).show();
            } else {
                snackbar.make(v, message, Snackbar.LENGTH_LONG).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && requestCode == 30060) {
            if (NetworkStatus.getInstance().isConnected(this)) {

                ContentDetailsInput contentDetailsInput = new ContentDetailsInput();
                contentDetailsInput.setAuthToken(authTokenStr);
                contentDetailsInput.setPermalink(permalinkStr);
                contentDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
                contentDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
                contentDetailsInput.setState(preferenceManager.getStateFromPref());
                contentDetailsInput.setCity(preferenceManager.getCityFromPref());
                contentDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));

                asynLoadMovieDetails = new GetContentDetailsAsynTask(contentDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
                asynLoadMovieDetails.execute(); //threadPoolExecutor

            } else {
                Toast.makeText(getApplicationContext(), languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                finish();
            }
        } else if (requestCode == VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE && resultCode == RESULT_OK) {
            getVideoInfo();

        } else if (requestCode == RESUME_WATCH_LASTSEEN_REQUESTCODE && resultCode == RESULT_OK) {
            getLastSeenDetails();
        }

    }



    public void getVideoInfo() {
        GetVideoDetailsInput getVideoDetailsInput = new GetVideoDetailsInput();
        getVideoDetailsInput.setAuthToken(authTokenStr);
        if (preferenceManager != null) {
            loggedInIdStr = preferenceManager.getUseridFromPref();
            guestUserld = preferenceManager.getGuestUseridFromPref();
        }
                        /*
                    desc:The below condition used for type of user(guest user or normal user)*/

        if (guestUserld != null) {
            getVideoDetailsInput.setUser_id(guestUserld.trim());
        } else {
            getVideoDetailsInput.setUser_id(loggedInIdStr);
        }
        getVideoDetailsInput.setContent_uniq_id(Util.dataModel.getMovieUniqueId());
        getVideoDetailsInput.setStream_uniq_id(Util.dataModel.getStreamUniqueId());
        getVideoDetailsInput.setInternetSpeed(MainActivity.internetSpeed.trim());
        getVideoDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        getVideoDetailsInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        getVideoDetailsInput.setAdParameter(Util.getVastAdParameter());
        asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this);
        asynLoadVideoUrls.execute(); //threadPoolExecutor
    }


    /*
   - To set id to back button in Action Bar
    */
    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.back);

            }
        }
    }

    public void genre_call(String default_Language) {

        GenreListInput genreListInput = new GenreListInput();
        genreListInput.setAuthToken(authTokenStr);
        genreListInput.setLang_code(default_Language);
        genreListInput.setIs_specific("0"); // for others
        genreListInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        GetGenreListAsynctask asynGetGenreList = new GetGenreListAsynctask(genreListInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, false, new DataController(ShowWithEpisodesActivity.this));
        asynGetGenreList.execute(); //threadPoolExecutor

    }

    public void startLoaeder() {

        try {
            if (!loaderIsShowing) {
                pageNaviagtionLoader = new ProgressBarHandler(ShowWithEpisodesActivity.this);
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

    public class EpisodesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private String movieUniqueId;
        private int contentTypesId = 0;
        private Context context;
        private int layoutResourceId;
        private int isThirdParty = 0;


        private ArrayList<EpisodesListModel> data = new ArrayList<EpisodesListModel>();

        public EpisodesListAdapter(Context context, int layoutResourceId,
                                   ArrayList<EpisodesListModel> data) {
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
            this.movieUniqueId = movieUniqueId;


        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView episodeTitleTextView;
            public TextView percentage;
            public TextView episodeDateTextView;

            public ImageView episodeImageView, downloadImageVIew, ChieldplayButton, list_option_menu;
            RelativeLayout multipartDownloadRelativeLayout;
            ProgressBar mProgressBar;


            public ViewHolder(View view) {
                super(view);

                episodeTitleTextView = (TextView) view.findViewById(R.id.episodeTitleTextView);
                FontUtls.loadFont(context, context.getResources().getString(R.string.regular_fonts), episodeTitleTextView);

                sheetView = ShowWithEpisodesActivity.this.getLayoutInflater().inflate(R.layout.opt, null);
                add_to_Queue = (TextView) sheetView.findViewById(R.id.add_to_Queue);
                add_to_Queue.setVisibility(View.GONE);
                mBottomSheetDialog = new BottomSheetDialog(ShowWithEpisodesActivity.this);
                mBottomSheetDialog.setContentView(sheetView);
                episodeImageView = (ImageView) view.findViewById(R.id.episodeImageView);
                multipartDownloadRelativeLayout = view.findViewById(R.id.download);
                mProgressBar = view.findViewById(R.id.multipart_download_progressBar);
                percentage = view.findViewById(R.id.multipart_percentageText);
                downloadImageVIew = view.findViewById(R.id.multipart_download_img);
                ChieldplayButton = view.findViewById(R.id.playButton);
                list_option_menu = view.findViewById(R.id.list_option_menu);

            }

            public void bind(final EpisodesListModel item, final int position) {
                episodeTitleTextView.setText(item.getEpisodeTitle());
                String imageId = item.getEpisodeThumbnailImageView();
                String is_converted = item.getIs_converted();
                String chield_is_media_published = item.getIs_media_published();
                String is_livestream_enabled = item.getIs_livestream_enabled();

                /*
                 * desc: condition for vodeo publish status*/

                if (chield_is_media_published.equals("1") && is_converted.equals("1")) {
                    {
                        ChieldplayButton.setVisibility(View.VISIBLE);

                    }
                } else {
                    ChieldplayButton.setVisibility(View.GONE);
                }


                if (imageId.matches("") || imageId.matches(LanguagePreference.getLanguagePreference(context).getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                    episodeImageView.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));
                } else {

                    if (Util.is_contain_noimage(item.getEpisodeThumbnailImageView())) {

                        episodeImageView.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));

                    } else {

                        Glide.with(context)
                                .load(item.getEpisodeThumbnailImageView())
                                .thumbnail(0.1f)
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        episodeImageView.setBackgroundColor(context.getResources().getColor(R.color.no_image_bg_color));

                                        return true;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        episodeImageView.setBackgroundColor(context.getResources().getColor(R.color.transparent));
                                        return false;
                                    }
                                })
                                .into(episodeImageView);

                    }

                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickItem(item);

                    }
                });


                if (is_livestream_enabled.equals("1")) {
                    multipartDownloadRelativeLayout.setVisibility(View.INVISIBLE);
                    list_option_menu.setVisibility(View.INVISIBLE);
                }

            }
        }

        public boolean isHeader(int position) {
            return position == 0;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutResourceId, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            ViewHolder groupViewHolder = (ViewHolder) holder;
            // groupViewHolder.mImage.setText(labels.get(position - 1));
            groupViewHolder.bind(data.get(position), position);

        }


        @Override
        public int getItemCount() {
            return data.size();
        }
    }



    public void relatedContentItemClick(RelatedContentModel item) {
        String moviePermalink = item.getcPermalink();
        if (item.getEpisodeContentTypesId() == 3) {

            if (item.getIsEpisode() == 1) {
                playChildEpisode(item);
            } else {

                if (item.getIsEpisode() == 2) {
                    final Intent detailsIntent = new Intent(getApplicationContext(), SeasonDetailsActivity.class);
                    detailsIntent.putExtra(Constant.PERMALINK_INTENT_KEY, moviePermalink);
                    detailsIntent.putExtra("season_permalink", item.getSeason_permalink());
                    detailsIntent.putExtra("parent_permalink", item.getcPermalink());
                    runOnUiThread(new Runnable() {
                        public void run() {
                            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(detailsIntent);
                        }
                    });
                } else {
                    final Intent detailsIntent = new Intent(getApplicationContext(), ShowWithEpisodesActivity.class);
                    detailsIntent.putExtra(Constant.PERMALINK_INTENT_KEY, moviePermalink);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(detailsIntent);
                        }
                    });
                }


            }
        } else if (item.getEpisodeContentTypesId() == 1) {
            final Intent movieDetailsIntent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
            movieDetailsIntent.putExtra(PERMALINK_INTENT_KEY, moviePermalink);
            movieDetailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            runOnUiThread(new Runnable() {
                public void run() {
                    movieDetailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(movieDetailsIntent);
                }
            });
        } else if (item.getEpisodeContentTypesId() == 5 || item.getEpisodeContentTypesId() == 6) {
            final Intent movieDetailsIntent = new Intent(getApplicationContext(), AudioContentDetailsActivity.class);
            movieDetailsIntent.putExtra("PERMALINK", moviePermalink);
            movieDetailsIntent.putExtra("CONTENT_TYPE", "" + item.getEpisodeContentTypesId());
            runOnUiThread(new Runnable() {
                public void run() {
                    movieDetailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(movieDetailsIntent);
                }
            });
        }


    }

    private void playChildEpisode(RelatedContentModel item) {
        DataModel dbModel = new DataModel();
        dbModel.setIsFreeContent(isFreeContent);
        dbModel.setIsAPV(isAPV);
        dbModel.setIsPPV(isPPV);
        dbModel.setIsConverted(1);
        dbModel.setMovieUniqueId(item.getEpisodeMuviUniqueId());
        dbModel.setStreamUniqueId(item.getEpisodeStreamUniqueId());
        dbModel.setThirdPartyUrl(item.getEpisodeThirdPartyUrl());
        dbModel.setVideoTitle(item.getEpisodeTitle());
        dbModel.setVideoStory(item.getEpisodeDescription());
        dbModel.setVideoGenre(videoGenreTextView.getText().toString());
        dbModel.setVideoDuration(item.getEpisodeDuration());
        dbModel.setVideoReleaseDate("");

        dbModel.setCensorRating(censorRatingStr);
        dbModel.setCastCrew(castStr);
        dbModel.setEpisode_id(item.getEpisodeStreamUniqueId());
        dbModel.setSeason_id(item.getEpisodeSeriesNo());

        if (item.getEpisodeContentTypesId() == 3 || item.getEpisodeContentTypesId() == 6) {
            dbModel.setPurchase_type("episode");
        } else {
            dbModel.setPurchase_type("show");
        }

        dbModel.setPosterImageId(item.getEpisodeThumbnailImageView());
        dbModel.setContentTypesId(item.getEpisodeContentTypesId());

        dbModel.setEpisode_series_no(item.getEpisodeSeriesNo());
        dbModel.setEpisode_no(item.getEpisodeNumber());
        dbModel.setEpisode_title(item.getEpisodeTitle());

        dbModel.setContentTypesId(3);


        Util.dataModel = dbModel;
        SubTitleName.clear();
        SubTitlePath.clear();
        ResolutionUrl.clear();
        ResolutionFormat.clear();
        SubTitleLanguage.clear();

        playerModel.setStreamUniqueId(item.getEpisodeStreamUniqueId());
        playerModel.setMovieUniqueId(item.getEpisodeMuviUniqueId());
        playerModel.setUserId(preferenceManager.getUseridFromPref());
        playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
        playerModel.setAuthTokenStr(authTokenStr.trim());
        playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH.trim());
        playerModel.setEpisode_id(item.getEpisodeStreamUniqueId());
        playerModel.setVideoTitle(item.getEpisodeTitle());
        playerModel.setIsFreeContent(isFreeContent);
        playerModel.setVideoStory(item.getEpisodeDescription());
        playerModel.setVideoGenre(videoGenreTextView.getText().toString());
        playerModel.setVideoDuration(item.getEpisodeDuration());
        playerModel.setVideoReleaseDate("");
        playerModel.setCensorRating(censorRatingStr);
        playerModel.setContentTypesId(contentTypesId);
        playerModel.setPosterImageId(item.getEpisodeThumbnailImageView());
        playerModel.setCastCrew(castStr);

        String loggedInStr = preferenceManager.getLoginStatusFromPref();
        Util.check_for_subscription = 1;
        if (isLogin == 1) {
            if (loggedInStr != null) {
                if (NetworkStatus.getInstance().isConnected(this)) {
                    if (playerModel.getIsFreeContent() == 1) {

                        GetVideoDetailsInput getVideoDetailsInput = new GetVideoDetailsInput();
                        getVideoDetailsInput.setAuthToken(authTokenStr);
                        getVideoDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
                        getVideoDetailsInput.setContent_uniq_id(Util.dataModel.getMovieUniqueId().trim());
                        getVideoDetailsInput.setStream_uniq_id(Util.dataModel.getStreamUniqueId().trim());
                        getVideoDetailsInput.setInternetSpeed(MainActivity.internetSpeed.trim());
                        getVideoDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                        asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this);
                        asynLoadVideoUrls.executeOnExecutor(threadPoolExecutor);
                    }
                } else {
                    Util.showToast(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
                }

            } else {

                Util.check_for_subscription = 1;
                Intent registerActivity = new LoginRegistrationOnContentClickHandler(this).handleClickOnContent();
                registerActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                registerActivity.putExtra("PlayerModel", playerModel);
                startActivityForResult(registerActivity, VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE);

            }
        } else {
            if (NetworkStatus.getInstance().isConnected(this)) {


                GetVideoDetailsInput getVideoDetailsInput = new GetVideoDetailsInput();
                getVideoDetailsInput.setAuthToken(authTokenStr);
                getVideoDetailsInput.setContent_uniq_id(Util.dataModel.getMovieUniqueId().trim());
                getVideoDetailsInput.setStream_uniq_id(Util.dataModel.getStreamUniqueId().trim());
                getVideoDetailsInput.setInternetSpeed(MainActivity.internetSpeed.trim());
                getVideoDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
                getVideoDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this);
                asynLoadVideoUrls.executeOnExecutor(threadPoolExecutor);

            } else {
                Util.showToast(ShowWithEpisodesActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));

            }
        }
    }

    private void relatedContentAPICall() {
        RelatedContentInput relatedContentInput = new RelatedContentInput();
        relatedContentInput.setAuthToken(authTokenStr);
        relatedContentInput.setContent_stream_id(movieStreamId);
        relatedContentInput.setContentId(movieIdStr);
        relatedContentInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));

        if (preferenceManager.getUseridFromPref() != null)
            relatedContentInput.setUser_id(preferenceManager.getUseridFromPref());

        if (featureHandler.getFeatureStatus(FeatureHandler.IS_SEASON_PAGE_AVAILABLE, FeatureHandler.DEFAULT_IS_SEASON_PAGE_AVAILABLE)) {
            relatedContentInput.setSeason_info("1");
        }

        relatedContentInput.setCountry(preferenceManager.getCountryCodeFromPref());
        relatedContentInput.setState(preferenceManager.getStateFromPref());
        relatedContentInput.setCity(preferenceManager.getCityFromPref());

        GetRelatedContentAsynTask asyncRelatedContent = new GetRelatedContentAsynTask(relatedContentInput, ShowWithEpisodesActivity.this, ShowWithEpisodesActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ShowWithEpisodesActivity.this));
        asyncRelatedContent.executeOnExecutor(threadPoolExecutor);

    }

    private void initAudioCustomMiniController() {
        minicontroller = findViewById(R.id.bottomSheetLayout);
        //activityAudioPlayer = findViewById(R.id.activity_audio_player);
        seekbar_botomSht = (SeekBar) findViewById(R.id.miniController_seekbar);
        seekbar_botomSht.setPadding(0, 0, 0, 0);
        albumArt_player = (ImageView) findViewById(R.id.miniControl_play);
        open_bottomSheet_controller = (LinearLayout) findViewById(R.id.details);
        song_p_name = (TextView) findViewById(R.id.song_p_name);
        totalduration = (TextView) findViewById(R.id.totalduration);
        artist_p_name = (TextView) findViewById(R.id.song_p_Genre);
        equalizer = (ImageView) findViewById(R.id.equalizer);
        background_player = (ImageView) findViewById(R.id.background_player);
        player_close = findViewById(R.id.player_close);
        songname_player = (TextView) findViewById(R.id.songname_player);
        player_image_main = (ImageView) findViewById(R.id.player_image_main);
        recylerview_progress = (ProgressBar) findViewById(R.id.progressBar);
        squizeViewPager = (ViewPager) findViewById(R.id.viewPager1);
        curent_duration = (TextView) findViewById(R.id.curent_duration);
        player_play_ic = (ImageView) findViewById(R.id.player_play_ic);
        player_next_icc = (ImageView) findViewById(R.id.player_next_icc);
        player_prev_ic = (ImageView) findViewById(R.id.player_prev_ic);
        total_duration = (TextView) findViewById(R.id.total_duration);
        seekBar = (SeekBar) findViewById(R.id.Progress_music_sliderpanel);
        seekBar.setPadding(0, 0, 0, 0);
        bottomSheet = findViewById(R.id.bottomSheetLayout);
        musicProgress = (ProgressBar) findViewById(R.id.progressBar);
        LinearLayout miniControllerLayout = findViewById(R.id.miniControl);
        clearQueue = findViewById(R.id.ClearQueueButton);
        audioCustomMiniController = new AudioCustomMiniController(ShowWithEpisodesActivity.this, permalinkStr, getSupportFragmentManager());
        audioCustomMiniController.init(minicontroller, seekbar_botomSht, albumArt_player, open_bottomSheet_controller, song_p_name, artist_p_name, equalizer, background_player, player_close
                , songname_player, player_image_main, recylerview_progress, squizeViewPager, curent_duration, player_play_ic, player_next_icc, player_prev_ic, total_duration, seekBar, bottomSheet, mActionBarToolbar, miniControllerLayout, musicProgress, clearQueue);
    }

    private BroadcastReceiver CLOSE_NOTIFICATION = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            audioCustomMiniController.closeMiniController();

        }
    };
    private BroadcastReceiver SongStatusReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            if (videoRelatedContentList.size() > 0 || audioRelatedContentList.size() > 0) {
                if (videoRelatedContentList.size() > 0 && audioRelatedContentList.size() > 0) {
                    mRecyclerView = relatedAudioContentRecyclerview;
                } else if (videoRelatedContentList.size() > 0 && audioRelatedContentList.size() == 0) {
                    mRecyclerView = relatedContent;
                } else {
                    mRecyclerView = relatedAudioContentRecyclerview;
                }
            } else {
                mRecyclerView = seasontiveLayout;
            }
            Util.recyclerView = mRecyclerView;
            Util.setMargin(minicontroller, mRecyclerView, ShowWithEpisodesActivity.this);
            String song_status = (intent.getStringExtra("songStatus")).trim();
            if (song_status.equals("play")) {
                albumArt_player.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                player_play_ic.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                minicontroller.setVisibility(View.VISIBLE);
                equalizer.setImageDrawable(AudioCustomMiniController.getDrawableByState(context, 1));
                preferenceManager.setAudioDetailsToPref(PreferenceManager.SONG_STATUS, "play");
                if (preferenceManager.getAudioDetailsFromPref(PreferenceManager.SONG_NAME) != null) {
                    song_p_name.setText(preferenceManager.getAudioDetailsFromPref(PreferenceManager.SONG_NAME));
                }
                if (preferenceManager.getAudioDetailsFromPref(PreferenceManager.ARTIST_NAME) != null) {
                    artist_p_name.setText(preferenceManager.getAudioDetailsFromPref(PreferenceManager.ARTIST_NAME));
                }
            }
            if (song_status.equals("pause")) {
                albumArt_player.setImageResource(R.drawable.play_icon);
                player_play_ic.setImageResource(R.drawable.play_icon);
                minicontroller.setVisibility(View.VISIBLE);
                equalizer.setImageDrawable(AudioCustomMiniController.getDrawableByState(context, 2));
                preferenceManager.setAudioDetailsToPref(PreferenceManager.SONG_STATUS, "pause");


            }
            if (song_status.equals("close")) {
                minicontroller.setVisibility(View.GONE);
                Intent stopServiceIntent = new Intent(ShowWithEpisodesActivity.this, MusicService.class);
                stopService(stopServiceIntent);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (song_status.equals("next")) {
                        audioCustomMiniController.Next();
                    }
                    if (song_status.contains("@@@@@")) {

                        final String data[] = song_status.split("@@@@@");
                        long currentDuration = Long.parseLong(data[0]);
                        long totalDuration = Long.parseLong(data[1]);

                        /*
                         * added try catch for number format exception
                         */
                        try {
                            total_duration.setText(AudioCustomMiniController.timeC(Long.parseLong(data[1])));
                            curent_duration.setText(AudioCustomMiniController.timeC(Long.parseLong(data[0])));
                            total_duration.setText("-" + AudioCustomMiniController.timeC(Long.parseLong(data[1]) - Long.parseLong(data[0])));
                            totalduration.setText(AudioCustomMiniController.timeC(Long.parseLong(data[1]) - Long.parseLong(data[0])));

                            seekBar.setMax(Integer.parseInt(data[1]));
                            seekBar.setProgress(Integer.parseInt(data[0]));
                            seekbar_botomSht.setMax(Integer.parseInt(data[1]));
                            seekbar_botomSht.setProgress(Integer.parseInt(data[0]));
                            musicProgress.setMax(Integer.parseInt(data[1]));
                            musicProgress.setProgress(Integer.parseInt(data[0]));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }


                    }
                }

            });
        }
    };


    int getItemLayoutIdForRelatedContent() {

        if (videoHeight > videoWidth) {
            return R.layout.list_card_related_portrait;
        }
        return R.layout.list_card_related_landscape;
    }

    private BroadcastReceiver SONG_STATUS_NEXT = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            audioCustomMiniController.Next();
        }
    };
    private BroadcastReceiver SONG_STATUS_PREVIOUS = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            audioCustomMiniController.previous();
        }

    };

}