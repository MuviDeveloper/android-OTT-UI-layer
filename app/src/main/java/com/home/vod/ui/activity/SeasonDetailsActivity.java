package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.APP_SELECT_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_APP_SELECT_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_DOWNLOAD_INTERRUPTED;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_DOWNLOAD_ON_WIFI_ONLY_MSG;
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
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_WARNING;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIDEO_NOT_PUBLISHED;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIEW_MORE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_YES;
import static com.home.vod.preferences.LanguagePreference.DOWNLOAD_INTERRUPTED;
import static com.home.vod.preferences.LanguagePreference.DOWNLOAD_ON_WIFI_ONLY_MSG;
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
import static com.home.vod.util.Util.languageModel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
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
import androidx.core.widget.NestedScrollView;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
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
import com.home.apisdk.apiController.GetEpisodeDeatailsAsynTask;
import com.home.apisdk.apiController.GetGenreListAsynctask;
import com.home.apisdk.apiController.GetLanguageListAsynTask;
import com.home.apisdk.apiController.GetSeasonDetailsAsyncTask;
import com.home.apisdk.apiController.GetTranslateLanguageAsync;
import com.home.apisdk.apiController.LogoutAsynctask;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
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
import com.home.apisdk.apiModel.SeasonDetailsInputModel;
import com.home.apisdk.apiModel.SeasonDetailsOutputModel;
import com.home.apisdk.apiModel.SortByModel;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.vod.BuildConfig;
import com.home.vod.ObservableObject;
import com.home.vod.R;
import com.home.vod.async.GetImageSize;
import com.home.vod.handlers.CheckVoucherOrPpvPaymentHandler;
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


public class SeasonDetailsActivity extends AppCompatActivity implements
        GetTranslateLanguageAsync.GetTranslateLanguageInfoListener,
        LogoutAsynctask.LogoutListener, GetLanguageListAsynTask.GetLanguageListListener,
        GetEpisodeDeatailsAsynTask.GetEpisodeDetailsListener,
        VideoDetailsAsynctask.VideoDetailsListener,
        GetSeasonDetailsAsyncTask.GetSeasonDetailsListener,
        GetGenreListAsynctask.GenreListListener, Observer
        , MediaHelper, AsyncLastSeenDetails.AsyncLastSeenDetailsListener {

    private static final int MAX_LINES = 3;
    public static final int VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE = 8888;
    public static final int VIDEO_DOWNLOAD_BUTTON_CLICK_LOGIN_REG_REQUESTCODE = 1212;
    public static final int PAYMENT_REQUESTCODE = 8889;
    public static final int RESUME_WATCH_LASTSEEN_REQUESTCODE = 5673;
    private static final int REQUEST_STORAGE = 11;
    public static TextView badge_text;
    public static TextView noti;
    public static FrameLayout noti_layout;


    public Toolbar mActionBarToolbar;
    public ImageView toolbarimage;
    public ToolbarTitleHandler toolbarTitleHandler;
    private LanguagePreference languagePreference;
    private FeatureHandler featureHandler;
    private ProgressBarHandler pageNaviagtionLoader;
    private ProgressBarHandler pDialog;
    private DBHelper dbHelper;
    private EpisodesListAdapter mAdapter;
    private EpisodesListModel clickItem;
    private LanguageCustomAdapter languageCustomAdapter;
    private VideoDetailsAsynctask asynLoadVideoUrls;
    private GetEpisodeDeatailsAsynTask asynEpisodeDetails;
    private AlertDialog alert;
    private Player playerModel;
    private PreferenceManager preferenceManager;

    private boolean loaderIsShowing = false;
    private boolean stopItemClick = false;
    private Timer UI_FREEZE_TIMER;
    private String loggedInIdStr, guestUserld;

    private int NO_IMAGE_HEIGHT = 500;
    private int NO_IMAGE_WIDTH = 300;
    private int lastApiCallPoaition = 0;
    private int videoHeight = 185;
    private int videoWidth = 256;
    private int offset = 1;
    private int limit = 4;
    private int itemCount = -1;
    private boolean scroll = true;
    private String isLivestream_enabled;
    private String livestream_resume_time;
    private boolean update_season_layout = false;
    private int isLogin = 0;
    private String movieUniqueId = "";
    private String censorRatingStr = "";
    private boolean castStr = false;
    private String Season_Value = "";
    private String posterImageId, permalinkStr, season_permalink_str = "";
    private String movieReleaseDateStr = "";
    private String loggedInStr, planid;
    private int isFreeContent = 0, isPPV, contentTypesId, isAPV;
    private String movieNameStr;
    private String selected_season_no = "";
    private String episodeVideoUrlStr;
    private String email, id;
    private String Default_Language = "";
    private String Previous_Selected_Language = "";
    private int prevPosition = 0;

    private TextView episodesTv;
    private RecyclerView mRecyclerView;
    private TextView add_to_Playlist, add_to_Queue;
    private View sheetView;
    private BottomSheetDialog mBottomSheetDialog;
    private View year_bar, censor_bar;
    private Snackbar snackbar = null;
    private Menu menu;
    private TextView oopsTextView;
    private CoordinatorLayout minicontroller;
    private SeekBar seekbar_botomSht, seekBar;
    private ImageView albumArt_player;
    private Button clearQueue;
    private LinearLayout open_bottomSheet_controller, miniControllerLayout;
    private TextView song_p_name, totalduration, artist_p_name, songname_player, curent_duration, total_duration;
    private ImageView equalizer, background_player, player_close, player_image_main, player_play_ic, player_next_icc, player_prev_ic;
    private MediaRouteButton mediaRouteButton;
    private ProgressBar recylerview_progress, musicProgress;
    private ViewPager squizeViewPager;
    private View bottomSheet;
    public BottomSheetBehavior mBottomSheetBehavior;
    private TextView noDataTextView;
    private TextView noInternetTextView;
    private TextView videoStoryTextView;
    private Button storyViewMoreButton;
    private ImageView moviePoster;
    private ImageView playButton;
    private ImageButton offlineImageButton;
    private Button btnmore;
    private TextView videoTitle, videoGenreTextView, videoDurationTextView, videoCensorRatingTextView, videoCensorRatingTextView1, videoReleaseDateTextView, videoCastCrewTitleTextView;
    private SharedPreferences pref;
    private Spinner season_spinner;
    private RelativeLayout noInternetConnectionLayout, noDataLayout;
    private RecyclerView seasontiveLayout;
    private NestedScrollView nestedScrollView;
    private Button btnRetry;

    private ArrayList<String> SubTitleName = new ArrayList<>();
    private ArrayList<String> SubTitlePath = new ArrayList<>();
    private ArrayList<String> ResolutionFormat = new ArrayList<>();
    private ArrayList<String> ResolutionUrl = new ArrayList<>();
    private ArrayList<String> SubTitleLanguage = new ArrayList<>();
    private ArrayList<Thread> allDownloadThread = new ArrayList<>();
    private ArrayList<Timer> allDownloadThreadTimer = new ArrayList<>();
    private ArrayList<TextView> percenatgeList = new ArrayList<>();
    private ArrayList<ProgressBar> progressList = new ArrayList<>();
    private ArrayList<RelativeLayout> downLoadRelativeLayoutList = new ArrayList<>();
    private ArrayList<ImageView> downLoadImageViewList = new ArrayList<>();
    private ArrayList<String> season_title_array;
    private ArrayList<String> season_no_array;
    private ArrayList<String> season_permalink_array;
    private ArrayList<String> season;

    private String[] lang;

    private Context context = this;
    private LinearLayoutManager mLayoutManager;


    private int corePoolSize = 60;
    private int maximumPoolSize = 200;
    private int keepAliveTime = 10;
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    private Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
    private ArrayList<EpisodesListModel> itemData = new ArrayList<>();


    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    private AudioCustomMiniController audioCustomMiniController;

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

        if (status == 200) {

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
        pDialog = new ProgressBarHandler(SeasonDetailsActivity.this);
        pDialog.show();

    }

    @Override
    public void onGetGenreListPostExecuteCompleted(ArrayList<GenreListOutput> genreListOutput, ArrayList<SortByModel> sortByModelArrayList, int code, String status) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }
        languageCustomAdapter.notifyDataSetChanged();

        final Intent detailsIntent = new Intent(SeasonDetailsActivity.this, SeasonDetailsActivity.class);
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
            Toast.makeText(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();
        }
        if (code == 0) {
            Toast.makeText(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();
        }
        if (code > 0) {
            if (code == 200) {
                preferenceManager.clearLoginPref();

                if ((featureHandler.getFeatureStatus(FeatureHandler.SIGNUP_STEP, FeatureHandler.DEFAULT_SIGNUP_STEP))) {
                    final Intent startIntent = new Intent(SeasonDetailsActivity.this, LoginActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });
                } else {
                    final Intent startIntent = new Intent(SeasonDetailsActivity.this, MainActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });
                }

            } else {
                Toast.makeText(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();
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
    public void onGetEpisodeDetailsPreExecuteStarted() {
        try {
            pDialog = new ProgressBarHandler(SeasonDetailsActivity.this);
            pDialog.show();
        } catch (Exception e) {
            e.toString();
        }

    }

    @Override
    public void onGetEpisodeDetailsPostExecuteCompleted(Episode_Details_output episode_details_output, int status, int item_count, String message, String movieUniqueId) {

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
        } catch (IllegalArgumentException ex) {
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
        }


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

            if (offset < 2) {

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
                        new GetImageSize(SeasonDetailsActivity.this, itemData.get(0).getEpisodeThumbnailImageView(), new GetImageSize.ImageSizeInterface() {
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
                    progressList.clear();
                    percenatgeList.clear();
                    downLoadImageViewList.clear();
                    downLoadRelativeLayoutList.clear();

                    clearAllDataList();
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

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SeasonDetailsActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(message);
                dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                        (dialog, id) -> dialog.cancel());
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
            isLivestream_enabled = _video_details_output.getIs_livestream_enabled();
            livestream_resume_time = _video_details_output.getLivestream_resume_time();
            if (isLivestream_enabled.equals("1")) {
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
                Util.showNoDataAlert(SeasonDetailsActivity.this);

            } else {
                {
                    // condition for checking if the response has third party url or not.
                    if (_video_details_output.getThirdparty_url() == null || _video_details_output.getThirdparty_url().matches("")) {
                        {
                            playerModel.setThirdPartyPlayer(false);
                            final Intent playVideoIntent;
                            if (Util.dataModel.getAdNetworkId() == 3) {
                                playVideoIntent = Util.getPlayerIntent(SeasonDetailsActivity.this);

                            } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                                if (Util.dataModel.getPlayPos() <= 0) {
                                    playVideoIntent = new Intent(SeasonDetailsActivity.this, AdPlayerActivity.class);
                                } else {
                                    playVideoIntent = Util.getPlayerIntent(SeasonDetailsActivity.this);
                                }
                            } else {
                                playVideoIntent = Util.getPlayerIntent(SeasonDetailsActivity.this);
                            }

                            stopLoader();
                            playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            playVideoIntent.putExtra("PlayerModel", playerModel);
                            startActivity(playVideoIntent);

                        }
                    } else {
                        stopLoader();
                        final Intent playVideoIntent = Util.getPlayerIntent(SeasonDetailsActivity.this);
                        playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        playVideoIntent.putExtra("PlayerModel", playerModel);
                        startActivity(playVideoIntent);
                    }
                }
            }

        } else if (statusCode == 436) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SeasonDetailsActivity.this, R.style.MyAlertDialogStyle);
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
            Util.showNoDataAlert(SeasonDetailsActivity.this);
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
    public void Transporter(QueueModel queueModel, int position) {
        if (audioCustomMiniController != null) {
            audioCustomMiniController.playSongFromPlayList(queueModel, position);
        }
    }

    @Override
    public void BottomOptionMenu(Context context, String SongName, String ArtistName, int Position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    /**
     * Variables for Enhanced Mini Controller UI
     */

    LinearLayout mini_controller_layout;


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
        pDialog = new ProgressBarHandler(SeasonDetailsActivity.this);
        setContentView(R.layout.activity_season_details);


        season = new ArrayList<String>();
        languagePreference = LanguagePreference.getLanguagePreference(SeasonDetailsActivity.this);
        featureHandler = FeatureHandler.getFeaturePreference(SeasonDetailsActivity.this);
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

        // Kushal - To set Id to action bar back button
        setIdToActionBarBackButton(mActionBarToolbar);
        dbHelper = new DBHelper(SeasonDetailsActivity.this);
        dbHelper.getWritableDatabase();
        moviePoster = (ImageView) findViewById(R.id.bannerImageView);
        btnmore = (Button) findViewById(R.id.viewall);

        FontUtls.loadFont(SeasonDetailsActivity.this, getResources().getString(R.string.regular_fonts), btnmore);

        btnmore.setVisibility(View.GONE);
        playButton = (ImageView) findViewById(R.id.play);
        playButton.setVisibility(View.INVISIBLE);


        offlineImageButton = (ImageButton) findViewById(R.id.offlineImageButton);
        videoTitle = (TextView) findViewById(R.id.content_title);
        videoGenreTextView = (TextView) findViewById(R.id.genre);
        videoDurationTextView = (TextView) findViewById(R.id.video_duration);
        videoCensorRatingTextView = (TextView) findViewById(R.id.videoCensorRatingTextView);
        videoReleaseDateTextView = (TextView) findViewById(R.id.video_release_date);
        videoStoryTextView = (TextView) findViewById(R.id.videoStoryTextView);
        videoCastCrewTitleTextView = (TextView) findViewById(R.id.cast_crew);
        playButton.setVisibility(View.GONE);
        episodesTv = findViewById(R.id.episodesTv);
        censor_bar = findViewById(R.id.censor_bar);
        year_bar = findViewById(R.id.year_bar);

        videoCastCrewTitleTextView.setVisibility(View.GONE);
        season_spinner = (Spinner) findViewById(R.id.seasonSpinner);

        if (Build.VERSION.SDK_INT < 23) {
            season_spinner.setBackgroundResource(R.drawable.lollipop_spinner_theme);
        }

        season_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (update_season_layout) {
                    final Intent detailsIntent = new Intent(getApplicationContext(), SeasonDetailsActivity.class);
                    detailsIntent.putExtra(Constant.PERMALINK_INTENT_KEY, permalinkStr);
                    detailsIntent.putExtra("season_permalink", season_permalink_array.get(position));
                    detailsIntent.putExtra("parent_permalink", permalinkStr);
                    detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(detailsIntent);
                    finish();
                }

                if (season != null && season.size() > 0 && getResources().getString(R.string.season_status).equals("1")) {
                    season_spinner.setVisibility(View.VISIBLE);
                    Configuration configuration = getResources().getConfiguration();
                    if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                        season_spinner.setRotation(360);
                    }
                } else {
                    season_spinner.setVisibility(View.GONE);
                }


                clearAllDataList();

                scroll = true;
                progressList.clear();
                percenatgeList.clear();
                downLoadImageViewList.clear();
                downLoadRelativeLayoutList.clear();
                lastApiCallPoaition = 0;

                if (asynEpisodeDetails != null) {
                    asynEpisodeDetails.cancel(true);
                }
                btnmore.setVisibility(View.GONE);

                Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                episodeDetailsInput.setAuthtoken(authTokenStr);

                episodeDetailsInput.setPermalink(permalinkStr.trim());

                episodeDetailsInput.setLimit(String.valueOf(limit));


                offset = 1;
                episodeDetailsInput.setOffset(String.valueOf(offset));// episodeDetailsInput.setOffset("1");
                episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());

                episodeDetailsInput.setSeries_number(selected_season_no.trim());
                Season_Value = selected_season_no.trim();

                asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(SeasonDetailsActivity.this), false);
                asynEpisodeDetails.execute(); //threadPoolExecutor

                update_season_layout = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        videoCensorRatingTextView1 = (TextView) findViewById(R.id.videoCensorRatingTextView1);
        seasontiveLayout = (RecyclerView) findViewById(R.id.featureContent);
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

        mLayoutManager = new LinearLayoutManager(SeasonDetailsActivity.this, LinearLayoutManager.VERTICAL, false); // LinearLayoutManager.HORIZONTAL
        seasontiveLayout.setVisibility(View.GONE);
        episodeVideoUrlStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);


        /**
         * @description For margin between layouts
         *
         **/

        RecyclerViewMargin decoration = new RecyclerViewMargin(10, 2);
        seasontiveLayout.addItemDecoration(decoration);
        ((SimpleItemAnimator) seasontiveLayout.getItemAnimator()).setSupportsChangeAnimations(false);
        seasontiveLayout.setItemAnimator(null);

        videoStoryTextView = (TextView) findViewById(R.id.videoStoryTextView);
        storyViewMoreButton = (Button) findViewById(R.id.storyViewMoreButton);
        nestedScrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

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
            episodeDetailsInput.setSeries_number(selected_season_no.trim());
            Season_Value = selected_season_no.trim();

            episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());

            asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(SeasonDetailsActivity.this), false);
            asynEpisodeDetails.execute();


        });

        /**
         * @author : Debashish
         * @description : onclicking Retry button
         */
        btnRetry.setOnClickListener(view -> {
            noInternetConnectionLayout.setVisibility(View.GONE);
            view.startAnimation(buttonClick);
            restartActivity();
        });


        //  pref = getSharedPreferences(Util.LOGIN_PREF, 0);
        loggedInStr = preferenceManager.getLoginStatusFromPref();
        planid = preferenceManager.getUseridFromPref();

        if (planid == null)
            planid = "0";

        if (loggedInStr == null)
            loggedInStr = "0";


        btnmore.setOnClickListener(v -> {

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
                episodeDetailsInput.setSeries_number(selected_season_no.trim());
                Season_Value = selected_season_no.trim();
                episodeDetailsInput.setSeries_number(selected_season_no.trim());
                Season_Value = selected_season_no.trim();

                episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());

                asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(SeasonDetailsActivity.this), false);
                asynEpisodeDetails.execute(); //threadPoolExecutor

            }

        });


        try {
            permalinkStr = getIntent().getStringExtra("parent_permalink").trim();
            season_permalink_str = getIntent().getStringExtra("season_permalink").trim();
        } catch (Exception e) {
            e.toString();
        }

        /**
         * Call the API for season details.
         */

        if (NetworkStatus.getInstance().isConnected(SeasonDetailsActivity.this)) {

            try {
                noDataLayout.setVisibility(View.GONE);
                noDataTextView.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.toString();
            }

            SeasonDetailsInputModel seasonDetailsInputModel = new SeasonDetailsInputModel();
            seasonDetailsInputModel.setAuthToken(authTokenStr);
            seasonDetailsInputModel.setPermalink(season_permalink_str);
            seasonDetailsInputModel.setCountry(preferenceManager.getCountryCodeFromPref());
            seasonDetailsInputModel.setLanguageCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));

            GetSeasonDetailsAsyncTask getSeasonDetailsAsyncTask = new GetSeasonDetailsAsyncTask(seasonDetailsInputModel, SeasonDetailsActivity.this, SeasonDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
            getSeasonDetailsAsyncTask.execute();

        } else {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
        }

        playButton.setOnClickListener(view -> {
            String loggedInStr = "";
            String guestUserStr = preferenceManager.getGuestUseridFromPref();
            if (preferenceManager.getLoginFeatureFromPref() == 1) {
                loggedInStr = preferenceManager.getUseridFromPref();
            }

            if (loggedInStr != null) {
                getLastSeenDetails();
            } else {
                Util.check_for_subscription = 1;
                Intent registerActivity = new LoginRegistrationOnContentClickHandler(SeasonDetailsActivity.this).handleClickOnContent();
                registerActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                registerActivity.putExtra("PlayerModel", playerModel);
                startActivityForResult(registerActivity, RESUME_WATCH_LASTSEEN_REQUESTCODE);
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

        AsyncLastSeenDetails asyncLastSeenDetails = new AsyncLastSeenDetails(getlastSeenDetailsInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this);
        asyncLastSeenDetails.execute();
    }


    /**
     * @description Button text translation
     */

    private void setBtnmoreTranslationMore() {
        if (btnmore != null) {

            btnmore.setText(languagePreference.getTextofLanguage(VIEW_MORE, DEFAULT_VIEW_MORE));
            btnmore.setBackground(null);
            // btnmore.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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

            scroll = false; //@desc : This indicates that after loading alldata when click scroll to top arrow , this value will check

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
        mAdapter = new EpisodesListAdapter(SeasonDetailsActivity.this, getItemLayoutId(), itemData);
        seasontiveLayout.setAdapter(mAdapter);
    }

    private @LayoutRes
    int getItemLayoutId() {
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

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();


        }

        finish();
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    public void clickItem(EpisodesListModel item) {

        if (item != null) {
            AutoPlayUtil.callAutoPlayAPI(item.getEpisodeStreamUniqueId(),
                    getApplicationContext(), playerModel);

            /*
             * @desc:check if audio is playing or not. If audio is playing stop audio player*/
            if (preferenceManager.getSongStatusFromPref() != null
                    && preferenceManager.getSongStatusFromPref().equals("playing")) {
                try {
                    Intent Pintent = new Intent(Constant.CLOSE_NOTIFICATION);
                    Pintent.putExtra("closeNotification", "close");
                    sendBroadcast(Pintent);
                    preferenceManager.setSongStatustoPref("close");
                    if (MusicService.mediaPlayer != null) {
                        MusicService.mediaPlayer.setPlayWhenReady(false);
                        MusicService.mediaPlayer.release();
                    }

                    stopService(new Intent(SeasonDetailsActivity.this, MusicService.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            try {
                if (!NetworkStatus.getInstance().isConnected(SeasonDetailsActivity.this)) {
                    noInternetConnectionLayout.setVisibility(View.VISIBLE);
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            if (item.getIs_media_published().equals("0")) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SeasonDetailsActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(languagePreference.getTextofLanguage(VIDEO_NOT_PUBLISHED, DEFAULT_VIDEO_NOT_PUBLISHED));
                dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                        (dialog, id) -> dialog.cancel());

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

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SeasonDetailsActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(languagePreference.getTextofLanguage(NO_DETAILS_AVAILABLE, DEFAULT_NO_DETAILS_AVAILABLE));
                dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                        (dialog, id) -> dialog.cancel());
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
            Util.check_for_subscription = 1;

            if (isLogin == 1) { // if login is required to play the content 1=true , 0=false
                if (loggedInStr != null) {
                    getVideoInfo();

                } else {
                    if ((playerModel.getIsFreeContent() == 1)
                            && !featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN_REQUIRED_FOR_FREECONTENT, FeatureHandler.DEFAULT_IS_LOGIN_REQUIRED_FOR_FREECONTENT)) {
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
                getVideoInfo();
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
            Util.complete_status_of_resume_lastseen = GetlastSeenDetailsOutputModel.getCompletedStatus();


            DataModel dbModel = new DataModel();


            dbModel.setIsFreeContent(isFreeContent);
            dbModel.setIsAPV(isAPV);
            dbModel.setIsPPV(isPPV);
            dbModel.setIsConverted(1);
            dbModel.setMovieUniqueId(GetlastSeenDetailsOutputModel.getLastSeenMuviUniqId());
            dbModel.setStreamUniqueId(GetlastSeenDetailsOutputModel.getLastSeenStreamUniqId());
            dbModel.setThirdPartyUrl("");
            dbModel.setVideoTitle(movieNameStr);
            dbModel.setVideoStory("");
            dbModel.setVideoGenre(videoGenreTextView.getText().toString());
            dbModel.setVideoDuration("");
            dbModel.setVideoReleaseDate("");

            dbModel.setCensorRating(censorRatingStr);
            dbModel.setCastCrew(castStr);
            dbModel.setEpisode_id("");
            dbModel.setSeason_id(Season_Value);
            dbModel.setPurchase_type("episode");
            dbModel.setPosterImageId("");
            dbModel.setContentTypesId(contentTypesId);

            dbModel.setEpisode_series_no("");
            dbModel.setEpisode_no(GetlastSeenDetailsOutputModel.getSeriesNo());
            dbModel.setEpisode_title("");
            dbModel.setIs_converted("");
            dbModel.setContentTypesId(3);

            Util.dataModel = dbModel;

            if (isLogin == 1) {
                if (preferenceManager != null) {
                    loggedInStr = preferenceManager.getUseridFromPref();
                }
                if (loggedInStr != null) {
                    if (NetworkStatus.getInstance().isConnected(this)) {
                        getVideoInfo();

                    } else {
                        Util.showToast(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
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
                    Util.showToast(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
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

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                final Intent searchIntent = new Intent(SeasonDetailsActivity.this, SearchActivity.class);
                searchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(searchIntent);
                // Not implemented here
                return false;

            case R.id.action_login:

                Intent loginIntent = new Intent(SeasonDetailsActivity.this, LoginActivity.class);
                Util.check_for_subscription = 0;
                startActivity(loginIntent);
                // Not implemented here
                return false;

            case R.id.action_register:

                Intent registerIntent = new Intent(SeasonDetailsActivity.this, RegisterActivity.class);
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

                Intent profileIntent = new Intent(SeasonDetailsActivity.this, ProfileActivity.class);
                profileIntent.putExtra("EMAIL", email);
                profileIntent.putExtra("LOGID", id);
                startActivity(profileIntent);
                return false;


            case R.id.action_logout:

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SeasonDetailsActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(languagePreference.getTextofLanguage(SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING));
                dlgAlert.setTitle("");

                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        // dialog.cancel();
                        if (NetworkStatus.getInstance().isConnected(SeasonDetailsActivity.this)) {
                            LogoutInput logoutInput = new LogoutInput();
                            logoutInput.setAuthToken(authTokenStr);
                            logoutInput.setLogin_history_id(preferenceManager.getLoginHistIdFromPref());
                            logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                            logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                            LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(SeasonDetailsActivity.this));
                            asynLogoutDetails.execute(); //threadPoolExecutor


                            dialog.dismiss();
                        } else {
                            Toast.makeText(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
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

                return false;

            default:
                break;
        }

        return false;
    }

    private void logoutPopup() {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SeasonDetailsActivity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(languagePreference.getTextofLanguage(SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING));
        dlgAlert.setTitle("");

        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                // dialog.cancel();
                if (NetworkStatus.getInstance().isConnected(SeasonDetailsActivity.this)) {
                    LogoutInput logoutInput = new LogoutInput();
                    logoutInput.setAuthToken(authTokenStr);
                    String loginHistoryIdStr = preferenceManager.getLoginHistIdFromPref();
                    logoutInput.setLogin_history_id(loginHistoryIdStr);
                    logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(SeasonDetailsActivity.this));
                    asynLogoutDetails.execute(); //threadPoolExecutor


                    dialog.dismiss();
                } else {
                    Toast.makeText(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
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


    public void ShowLanguagePopup() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SeasonDetailsActivity.this, R.style.MyAlertDialogStyle);
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

        languageCustomAdapter = new LanguageCustomAdapter(SeasonDetailsActivity.this, Util.languageModel, null);

        recyclerView.setAdapter(languageCustomAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener1(SeasonDetailsActivity.this, recyclerView, new ClickListener1() {
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

        apply.setOnClickListener(v -> {
            alert.dismiss();

            if (!Previous_Selected_Language.equals(Default_Language)) {
                LanguageListInputModel languageListInputModel = new LanguageListInputModel();
                languageListInputModel.setAuthToken(authTokenStr);
                languageListInputModel.setLangCode(Default_Language);
                languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                GetTranslateLanguageAsync asynGetTransalatedLanguage = new GetTranslateLanguageAsync(languageListInputModel, SeasonDetailsActivity.this, SeasonDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(SeasonDetailsActivity.this));
                asynGetTransalatedLanguage.execute(); //threadPoolExecutor
            }

        });


        alert = alertDialog.show();
        alert.setOnDismissListener(dialog -> languagePreference.setLanguageSharedPrefernce(SELECTED_LANGUAGE_CODE, Previous_Selected_Language));

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
            unregisterReceiver(SONG_STATUS_NEXT);

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

            case REQUEST_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    clickItem(clickItem);
                } else {
                    Toast.makeText(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(DOWNLOAD_INTERRUPTED, DEFAULT_DOWNLOAD_INTERRUPTED), Toast.LENGTH_SHORT).show();
                }

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
        if (requestCode == VIDEO_DOWNLOAD_BUTTON_CLICK_LOGIN_REG_REQUESTCODE && resultCode == RESULT_OK) {
            getVideoInfo();
        } else if (requestCode == RESUME_WATCH_LASTSEEN_REQUESTCODE && resultCode == RESULT_OK) {
            getLastSeenDetails();
        }

    }


    public void getVideoInfo() {
        if (NetworkStatus.getInstance().isConnected(this)) {
            GetVideoDetailsInput getVideoDetailsInput = new GetVideoDetailsInput();
            getVideoDetailsInput.setAuthToken(authTokenStr);
            if (preferenceManager != null) {
                loggedInIdStr = preferenceManager.getUseridFromPref();
                guestUserld = preferenceManager.getGuestUseridFromPref();
            }
            /*desc:The below condition used for type of user(guest user or normal user)*/
            if (guestUserld != null) {
                getVideoDetailsInput.setUser_id(guestUserld.trim());
            } else {
                getVideoDetailsInput.setUser_id(loggedInIdStr);
            }
            getVideoDetailsInput.setContent_uniq_id(Util.dataModel.getMovieUniqueId().trim());
            getVideoDetailsInput.setStream_uniq_id(Util.dataModel.getStreamUniqueId().trim());
            getVideoDetailsInput.setInternetSpeed(MainActivity.internetSpeed.trim());
            getVideoDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            getVideoDetailsInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
            getVideoDetailsInput.setAdParameter(Util.getVastAdParameter());
            asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this);
            asynLoadVideoUrls.execute(); //threadPoolExecutor
        } else {
            Util.showToast(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        }

    }


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
        GetGenreListAsynctask asynGetGenreList = new GetGenreListAsynctask(genreListInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this, false, new DataController(SeasonDetailsActivity.this));
        asynGetGenreList.execute(); //threadPoolExecutor

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearAllDataList();
    }


    public void startLoaeder() {

        try {
            if (!loaderIsShowing) {
                pageNaviagtionLoader = new ProgressBarHandler(SeasonDetailsActivity.this);
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
                /**
                 * @description Added for new UI change. showing all datas of Eisode_list_Activity in SeasonDetailsACtivity.
                 */

                episodeTitleTextView = (TextView) view.findViewById(R.id.episodeTitleTextView);
                FontUtls.loadFont(context, context.getResources().getString(R.string.regular_fonts), episodeTitleTextView);

                sheetView = SeasonDetailsActivity.this.getLayoutInflater().inflate(R.layout.opt, null);
                add_to_Playlist = (TextView) sheetView.findViewById(R.id.add_to_Playlist);
                add_to_Queue = (TextView) sheetView.findViewById(R.id.add_to_Queue);
                add_to_Queue.setVisibility(View.GONE);
                mBottomSheetDialog = new BottomSheetDialog(SeasonDetailsActivity.this);
                mBottomSheetDialog.setContentView(sheetView);
                /**
                 *
                 * @description Added for new UI change. showing all datas of Eisode_list_Activity in SeasonDetailsACtivity.
                 */
                episodeImageView = (ImageView) view.findViewById(R.id.episodeImageView);
                /**
                 * Desc:Multipart offline download work
                 */
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


                if (chield_is_media_published.equals("1") && is_converted.equals("1")) {
                    ChieldplayButton.setVisibility(View.VISIBLE);
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
                downloadImageVIew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        percenatgeList.add(percentage);
                        progressList.add(mProgressBar);
                        downLoadImageViewList.add(downloadImageVIew);
                        downLoadRelativeLayoutList.add(multipartDownloadRelativeLayout);
                        if (preferenceManager.getDownloadFromPref().equals("1")) {
                            //Toast.makeText(MovieDetailsActivity.this,"Download only wi-fi is enabled",Toast.LENGTH_LONG).show();

                            if (NetworkStatus.getInstance().isWiFiConnected(SeasonDetailsActivity.this)) {
                                // clickItem(item);
                                clickItem = item;
                            } else {
                                Toast.makeText(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(DOWNLOAD_ON_WIFI_ONLY_MSG, DEFAULT_DOWNLOAD_ON_WIFI_ONLY_MSG), Toast.LENGTH_LONG).show();
                            }

                        } else {
                            //  clickItem(item);
                            clickItem = item;
                        }

                    }
                });


                list_option_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mBottomSheetDialog.show();

                        add_to_Playlist.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clickItem(item);
                                mBottomSheetDialog.dismiss();
                            }
                        });
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
            groupViewHolder.bind(data.get(position), position);
        }


        @Override
        public int getItemCount() {
            return data.size();
        }
    }


    public void clearAllDataList() {
        try {
            for (int i = 0; i < allDownloadThread.size(); i++) {
                if (allDownloadThread.get(i) != null) {
                    allDownloadThread.get(i).interrupt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            for (int i = 0; i < allDownloadThreadTimer.size(); i++) {
                if (allDownloadThreadTimer.get(i) != null) {
                    allDownloadThreadTimer.get(i).cancel();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void relatedContentItemClick(RelatedContentModel item) {
        String moviePermalink = item.getcPermalink();
        if (item.getEpisodeContentTypesId() == 3) {

            if (item.getIsEpisode() == 1) {
                playChildEpisode(item);
            } else {
                final Intent detailsIntent = new Intent(getApplicationContext(), SeasonDetailsActivity.class);
                detailsIntent.putExtra(Constant.PERMALINK_INTENT_KEY, moviePermalink);
                runOnUiThread(new Runnable() {
                    public void run() {
                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(detailsIntent);
                    }
                });
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
        dbModel.setMovieUniqueId(movieUniqueId);
        dbModel.setStreamUniqueId(item.getEpisodeStreamUniqueId());
        dbModel.setThirdPartyUrl(item.getEpisodeThirdPartyUrl());
        dbModel.setVideoTitle(movieNameStr);
        dbModel.setVideoStory(item.getEpisodeDescription());
        dbModel.setVideoGenre(videoGenreTextView.getText().toString());
        dbModel.setVideoDuration(item.getEpisodeDuration());
        // dbModel.setVideoReleaseDate(item.getEpisodeTelecastOn());
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

        // dbModel.setParentTitle(movieNameStr);
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
        playerModel.setVideoTitle(movieNameStr);
        playerModel.setIsFreeContent(isFreeContent);
        playerModel.setVideoStory(item.getEpisodeDescription());
        playerModel.setVideoGenre(videoGenreTextView.getText().toString());
        playerModel.setVideoDuration(item.getEpisodeDuration());
        playerModel.setVideoReleaseDate("");
        playerModel.setCensorRating(censorRatingStr);
        playerModel.setContentTypesId(contentTypesId);
        playerModel.setPosterImageId(posterImageId);
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
                        asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this);
                        asynLoadVideoUrls.executeOnExecutor(threadPoolExecutor);
                    } else {
                        new CheckVoucherOrPpvPaymentHandler(SeasonDetailsActivity.this).handleVoucherPaymentOrPpvPayment();
                        //callValidateUserAPI();
                    }

                } else {
                    Util.showToast(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
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
                asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this);
                asynLoadVideoUrls.executeOnExecutor(threadPoolExecutor);

            } else {
                Util.showToast(SeasonDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
            }
        }
    }


    private void initAudioCustomMiniController() {
        minicontroller = findViewById(R.id.bottomSheetLayout);
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
        mediaRouteButton = (MediaRouteButton) findViewById(R.id.media_route_button);
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
        audioCustomMiniController = new AudioCustomMiniController(SeasonDetailsActivity.this, permalinkStr, getSupportFragmentManager());
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

            mRecyclerView = seasontiveLayout;
            Util.recyclerView = mRecyclerView;
            Util.setMargin(minicontroller, mRecyclerView, SeasonDetailsActivity.this);
            String song_status = (intent.getStringExtra("songStatus")).trim();
            if (song_status.equals("play")) {
                albumArt_player.setImageResource(R.drawable.ic_pause_black_24dp);
                player_play_ic.setImageResource(R.drawable.ic_pause_black_24dp);
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
                Intent stopServiceIntent = new Intent(SeasonDetailsActivity.this, MusicService.class);
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


    /**
     * Get the response from season details API.
     */

    @Override
    public void onGetSeasonDetailsPreExecuteStarted() {
        try {
            pDialog = new ProgressBarHandler(SeasonDetailsActivity.this);
            pDialog.show();
        } catch (Exception e) {
            e.toString();
        }

    }

    @Override
    public void onGetSeasonDetailsPostExecuteCompleted(SeasonDetailsOutputModel seasonDetailsOutputModel, int status, String message) {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (status == 200) {

            try {
                String title = seasonDetailsOutputModel.getTitle().trim();
                String description = seasonDetailsOutputModel.getDescription();
                String release_date = seasonDetailsOutputModel.getRelease_date();
                String muvi_uniq_id = seasonDetailsOutputModel.getMuvi_uniq_id();

                String has_trailer = seasonDetailsOutputModel.getHas_trailer();
                String censor_rating = seasonDetailsOutputModel.getCensor_rating();
                String mobile_poster = seasonDetailsOutputModel.getMobile_poster();
                String mobile_banner = seasonDetailsOutputModel.getMobile_banner();

                String season_no = seasonDetailsOutputModel.getSeason_no();
                String parent_muvi_unique_id = seasonDetailsOutputModel.getParent_muvi_unique_id();
                movieUniqueId = parent_muvi_unique_id;

                String parent_title = seasonDetailsOutputModel.getParent_title();
                int content_types_id = seasonDetailsOutputModel.getContent_types_id();

                season_title_array = new ArrayList<>(seasonDetailsOutputModel.getSeason_title_array());
                season_no_array = new ArrayList<>(seasonDetailsOutputModel.getSeason_no_array());
                season_permalink_array = new ArrayList<>(seasonDetailsOutputModel.getSeason_permalink_array());


                String genre = "";
                if (seasonDetailsOutputModel.getGenre().size() > 0) {
                    for (int i = 0; i < seasonDetailsOutputModel.getGenre().size(); i++) {
                        if (i == (seasonDetailsOutputModel.getGenre().size()) - 1) {
                            genre = genre + seasonDetailsOutputModel.getGenre().get(i).trim();
                        } else {
                            genre = genre + seasonDetailsOutputModel.getGenre().get(i).trim() + ", ";
                        }
                    }
                }


                setSeasonData(title, description, release_date, muvi_uniq_id, has_trailer, censor_rating, mobile_poster,
                        mobile_banner, genre, season_no, parent_muvi_unique_id, parent_title, content_types_id);


            } catch (Exception e) {
                e.toString();
            }


        } else {
            noDataLayout.setVisibility(View.VISIBLE);
            noDataTextView.setText(message);
        }


    }


    /**
     * following method is used to set the season details.
     */
    public void setSeasonData(String title, String description, String release_date, String muvi_uniq_id, String has_trailer, String censor_rating, String mobile_poster,
                              String mobile_banner, String genre,
                              String season_no, String parent_muvi_unique_id, String parent_title, int content_types_id) {

        noInternetConnectionLayout.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.GONE);
        playButton.setVisibility(View.INVISIBLE);

        movieNameStr = parent_title;
        contentTypesId = content_types_id;
        selected_season_no = season_no;
        posterImageId = mobile_poster;


        videoTitle.setVisibility(View.VISIBLE);
        FontUtls.loadFont(SeasonDetailsActivity.this, getResources().getString(R.string.regular_fonts), videoTitle);
        videoTitle.setText(title);


        if (genre != null && genre.matches("") || genre.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
            videoGenreTextView.setVisibility(View.GONE);

        } else {
            videoGenreTextView.setVisibility(View.VISIBLE);
            FontUtls.loadFont(SeasonDetailsActivity.this, getResources().getString(R.string.light_fonts), videoGenreTextView);
            videoGenreTextView.setText(genre);


        }

        videoDurationTextView.setVisibility(View.GONE);
        if (description.matches("") || description.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
            videoStoryTextView.setVisibility(View.GONE);
        } else {
            videoStoryTextView.setVisibility(View.VISIBLE);
            FontUtls.loadFont(SeasonDetailsActivity.this, getResources().getString(R.string.light_fonts), videoStoryTextView);
            videoStoryTextView.setText(Util.getTextViewTextFromApi(description));
            ResizableCustomView.doResizeTextView(SeasonDetailsActivity.this, videoStoryTextView, MAX_LINES, languagePreference.getTextofLanguage(VIEW_MORE, DEFAULT_VIEW_MORE), true);

        }

        if (censor_rating.matches("") ||
                censor_rating.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
            videoCensorRatingTextView.setVisibility(View.GONE);
            videoCensorRatingTextView1.setVisibility(View.GONE);

        } else {

            if (censor_rating.contains("-")) {
                String Data[] = censor_rating.split("-");
                videoCensorRatingTextView.setVisibility(View.VISIBLE);
                videoCensorRatingTextView1.setVisibility(View.VISIBLE);

                FontUtls.loadFont(SeasonDetailsActivity.this, getResources().getString(R.string.light_fonts), videoCensorRatingTextView);
                FontUtls.loadFont(SeasonDetailsActivity.this, getResources().getString(R.string.light_fonts), videoCensorRatingTextView1);
                if (Data.length > 0) {
                    videoCensorRatingTextView.setText(Data[0]);
                    videoCensorRatingTextView1.setText(Data[1]);
                }
            } else {
                videoCensorRatingTextView.setVisibility(View.VISIBLE);
                videoCensorRatingTextView1.setVisibility(View.GONE);
                FontUtls.loadFont(SeasonDetailsActivity.this, getResources().getString(R.string.light_fonts), videoCensorRatingTextView);
                videoCensorRatingTextView.setText(censor_rating);
            }
        }


        if (release_date.matches("") || release_date.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
            videoReleaseDateTextView.setVisibility(View.GONE);
        } else {
            videoReleaseDateTextView.setVisibility(View.VISIBLE);
            FontUtls.loadFont(SeasonDetailsActivity.this, getResources().getString(R.string.light_fonts), videoReleaseDateTextView);
            movieReleaseDateStr = Util.formateDateFromstring("yyyy-mm-dd", "yyyy", release_date);
            videoReleaseDateTextView.setText(movieReleaseDateStr);
        }

        if ((videoDurationTextView.getVisibility() == View.VISIBLE && videoCensorRatingTextView.getVisibility() == View.VISIBLE) ||
                (videoDurationTextView.getVisibility() == View.VISIBLE && videoCensorRatingTextView1.getVisibility() == View.VISIBLE))
            censor_bar.setVisibility(View.VISIBLE);

        if (season != null && season.size() > 0) {
            season.clear();
        }

        if (season_title_array.size() > 0) {
            for (int j = 0; j < season_title_array.size(); j++) {

                season.add(season_title_array.get(j));
            }
        }
        if (season.size() == 0) {
            Episode_Details_input episodeDetailsInput = new Episode_Details_input();
            episodeDetailsInput.setAuthtoken(authTokenStr);
            episodeDetailsInput.setPermalink(permalinkStr.trim());
            episodeDetailsInput.setLimit(String.valueOf(limit));
            offset = 1;
            episodeDetailsInput.setOffset(String.valueOf(offset));
            episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
            episodeDetailsInput.setSeries_number("");

            asynEpisodeDetails = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, SeasonDetailsActivity.this, SeasonDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(SeasonDetailsActivity.this), false);
            asynEpisodeDetails.execute(); //threadPoolExecutor
        }


        // Kushal - set id to spinner adapter Seasons
        ArrayAdapter adapter = new ArrayAdapter(SeasonDetailsActivity.this, R.layout.dropdownlist, season);
        season_spinner.setAdapter(adapter);

        for (int j = 0; j < season_no_array.size(); j++) {
            if (season_no_array.get(j).trim().equals(selected_season_no.trim())) {
                season_spinner.setSelection(j);
            }
        }

        try {
            if (mobile_banner != null && !mobile_banner.equals("")) {
                Picasso.with(SeasonDetailsActivity.this)
                        .load(mobile_banner.trim())
                        .error(R.drawable.logo)
                        .into(moviePoster);

            } else if (mobile_poster != null && !mobile_poster.equals("")) {
                Picasso.with(SeasonDetailsActivity.this)
                        .load(mobile_poster)
                        .error(R.drawable.logo)
                        .into(moviePoster);
            } else {
                moviePoster.setImageResource(R.drawable.logo);
            }
        } catch (Exception e) {
            moviePoster.setImageResource(R.drawable.logo);
        }

    }
}