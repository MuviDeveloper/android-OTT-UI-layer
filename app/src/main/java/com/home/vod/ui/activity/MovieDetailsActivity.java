package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_RELATED_CONTENT_AUDIO_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_RELATED_CONTENT_VIDEO_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIEW_MORE;
import static com.home.vod.preferences.LanguagePreference.LOGOUT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS;
import static com.home.vod.preferences.LanguagePreference.RELATED_CONTENT_AUDIO_TITLE;
import static com.home.vod.preferences.LanguagePreference.RELATED_CONTENT_VIDEO_TITLE;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.VIEW_MORE;
import static com.home.vod.util.Constant.PERMALINK_INTENT_KEY;
import static com.home.vod.util.Constant.authTokenStr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.home.apisdk.apiController.GetContentDetailsAsynTask;
import com.home.apisdk.apiController.GetGenreListAsynctask;
import com.home.apisdk.apiController.GetRelatedContentAsynTask;
import com.home.apisdk.apiController.GetTranslateLanguageAsync;
import com.home.apisdk.apiController.LogoutAsynctask;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
import com.home.apisdk.apiModel.ContentDetailsInput;
import com.home.apisdk.apiModel.ContentDetailsOutput;
import com.home.apisdk.apiModel.GenreListInput;
import com.home.apisdk.apiModel.GenreListOutput;
import com.home.apisdk.apiModel.GetVideoDetailsInput;
import com.home.apisdk.apiModel.RelatedContentInput;
import com.home.apisdk.apiModel.RelatedContentOutput;
import com.home.apisdk.apiModel.SortByModel;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.vod.BuildConfig;
import com.home.vod.ObservableObject;
import com.home.vod.R;
import com.home.vod.async.GetImageSize;
import com.home.vod.handlers.LoginRegistrationOnContentClickHandler;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.model.DataModel;
import com.home.vod.model.QueueModel;
import com.home.vod.model.RelatedContentModel;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.service.MusicService;
import com.home.vod.ui.MediaHelper;
import com.home.vod.ui.adapter.RelatedContentAdapter;
import com.home.vod.ui.widgets.AudioCustomMiniController;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.ui.widgets.ResizableCustomView;
import com.home.vod.util.AppThreadPoolExecutor;
import com.home.vod.util.AutoPlayUtil;
import com.home.vod.util.Constant;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;

import player.activity.AdPlayerActivity;
import player.activity.Player;
import player.utils.DBHelper;


public class MovieDetailsActivity extends AppCompatActivity implements
        LogoutAsynctask.LogoutListener,
        VideoDetailsAsynctask.VideoDetailsListener,
        GetContentDetailsAsynTask.GetContentDetailsListener,

        GetTranslateLanguageAsync.GetTranslateLanguageInfoListener,
        GetGenreListAsynctask.GenreListListener,
        Observer,
        GetRelatedContentAsynTask.GetRelatedContentListener, MediaHelper {

    private static final int MAX_LINES = 3;
    public static final int VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE = 8888;
    public static TextView badge_text;
    public static TextView noti;
    public static FrameLayout noti_layout;

    public ImageView toolbarimage;
    public Toolbar mActionBarToolbar;
    private ImageView moviePoster;
    private ImageView playButton, share_view;
    private RelativeLayout downloadRelativeLayout;
    private View year_bar, censor_bar, type_bar;
    private TextView videoStoryTextView;
    private TextView videoTitle, videoGenreTextView, videoDurationTextView, videoCensorRatingTextView, videoCensorRatingTextView1,
            videoReleaseDateTextView;
    private TextView noDataTextView;
    private TextView noInternetTextView;
    private RelativeLayout noInternetConnectionLayout, noDataLayout, iconImageRelativeLayout, bannerImageRelativeLayout;
    private LinearLayout story_layout;
    private Button btnRentNow;
    private TextView purchasetype;
    private TextView oopsTextView;
    private RecyclerView relatedContent, relatedAudioContentRecyclerview;
    private TextView RelatedContentTitle, relatedAudioContentTitle;
    private LinearLayoutManager mLayoutManager1;
    private Snackbar snackbar = null;
    private LinearLayout mini_controller_layout;
    private AlertDialog alertDialog = null;
    private ListView listViewItems;
    private TextView new_playlist;

    public ToolbarTitleHandler toolbarTitleHandler;
    private PreferenceManager preferenceManager;
    private ProgressBarHandler pDialog;
    private LanguagePreference languagePreference;
    private FeatureHandler featureHandler;
    private DBHelper dbHelper;

    private Player playerModel;

    private VideoDetailsAsynctask asynLoadVideoUrls;
    private GetContentDetailsAsynTask asynLoadMovieDetails;

    private String loggedInIdStr, guestUserld;
    private String movieNameStr = "";
    private String censorRatingStr = "";
    private String movieDetailsStr = "";
    private String movieThirdPartyUrl = "";
    private String bannerUrl;
    private String default_Language = "";
    private String movieIdStr;
    private String permalinkStr;
    private String movieReleaseDateStr = "";
    private String movieStreamUniqueId, posterImageId;
    private String movieUniqueId = "", isEpisode = "";
    private int isFreeContent, isPPV, contentTypesId, isAPV;
    private String play_list_id, PlayListName;
    private String is_livestreamEnabled;
    private String livestream_resume_time;
    private int is_converted;
    private int isLogin = 0;
    private String episodeVideoUrlStr;
    private String movieStreamId;

    private ArrayList<String> SubTitleName = new ArrayList<>();
    private ArrayList<String> SubTitlePath = new ArrayList<>();
    private ArrayList<String> FakeSubTitlePath = new ArrayList<>();
    private ArrayList<String> ResolutionFormat = new ArrayList<>();
    private ArrayList<String> ResolutionUrl = new ArrayList<>();
    private ArrayList<String> SubTitleLanguage = new ArrayList<>();
    private ArrayList<RelatedContentModel> audioRelatedContentList, videoRelatedContentList;

    private Executor threadPoolExecutor = AppThreadPoolExecutor.getInstance().getThreadPoolExecutor();


    private Context context = this;
    private Button btnRetry;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    /*
     * @Desc: This override method is used to called before the onCreate(),
     *       which help the resources to update Activity.
     * */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleLanguageHelper.onAttach(base));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_layout); // details_page
        preferenceManager = PreferenceManager.getPreferenceManager(this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(AutoPlayUtil.togglePlayback);
        mIntentFilter.addAction(AutoPlayUtil.enableView);
        mIntentFilter.addAction(AutoPlayUtil.disableView);

        /* desc:This code is implemented to get the device resolution*/
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        ObservableObject.getInstance().addObserver(this);

        playerModel = new Player();
        pDialog = new ProgressBarHandler(MovieDetailsActivity.this);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(DELETE_ACTION, new IntentFilter("ITEM_STATUS"));

        audioRelatedContentList = new ArrayList<>();
        videoRelatedContentList = new ArrayList<>();
        languagePreference = LanguagePreference.getLanguagePreference(this);
        featureHandler = FeatureHandler.getFeaturePreference(MovieDetailsActivity.this);
        playerModel.setIsstreaming_restricted(Util.getStreamingRestriction(languagePreference));
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);

        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        setSupportActionBar(mActionBarToolbar);

        purchasetype = findViewById(R.id.purchasetype);

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

        setIdToActionBarBackButton(mActionBarToolbar);

        dbHelper = new DBHelper(MovieDetailsActivity.this);
        dbHelper.getWritableDatabase();

        /**
         * Desc : Instance creation for minicontroller.
         */
        initMinicontroller();

        moviePoster = (ImageView) findViewById(R.id.bannerImageView);
        playButton = (ImageView) findViewById(R.id.play);
        btnRentNow = (Button) findViewById(R.id.btnRentNow);
        share_view = (ImageView) findViewById(R.id.share);
        videoTitle = (TextView) findViewById(R.id.content_title);
        videoGenreTextView = (TextView) findViewById(R.id.genre);
        videoDurationTextView = (TextView) findViewById(R.id.video_duration);
        videoCensorRatingTextView = (TextView) findViewById(R.id.videoCensorRatingTextView);
        videoCensorRatingTextView1 = (TextView) findViewById(R.id.videoCensorRatingTextView1);


        if (!Util.isColorDark(getResources().getColor(R.color.appBackgroundColor))) {
            videoCensorRatingTextView.setBackgroundColor(ContextCompat.getColor(MovieDetailsActivity.this, R.color.censorRatingBackground_white_theme));
        }

        videoReleaseDateTextView = (TextView) findViewById(R.id.video_release_date);
        videoStoryTextView = (TextView) findViewById(R.id.videoStoryTextView);
        censor_bar = findViewById(R.id.censor_bar);
        year_bar = findViewById(R.id.year_bar);
        type_bar = findViewById(R.id.type_bar);

        downloadRelativeLayout = findViewById(R.id.download);
        noInternetConnectionLayout = (RelativeLayout) findViewById(R.id.noInternet);
        noDataLayout = (RelativeLayout) findViewById(R.id.noData);
        noInternetTextView = (TextView) findViewById(R.id.noInternetTextView);
        noDataTextView = (TextView) findViewById(R.id.noDataTextView);
        oopsTextView = (TextView) findViewById(R.id.oopsTextView); // Added by Debashish
        btnRetry = (Button) findViewById(R.id.btnRetry); // Added by Debashish
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noDataTextView.setText(languagePreference.getTextofLanguage(NO_CONTENT, DEFAULT_NO_CONTENT));

        btnRetry.setText("          " + languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN) + "       ");
        oopsTextView.setText(languagePreference.getTextofLanguage(OOPS, DEFAULT_OOPS));

        iconImageRelativeLayout = (RelativeLayout) findViewById(R.id.iconImageRelativeLayout);
        bannerImageRelativeLayout = (RelativeLayout) findViewById(R.id.bannerImageRelativeLayout);
        story_layout = (LinearLayout) findViewById(R.id.story_layout);
        permalinkStr = getIntent().getStringExtra(PERMALINK_INTENT_KEY);
        isLogin = preferenceManager.getLoginFeatureFromPref();

        btnRetry.setOnClickListener(view -> {
            noInternetConnectionLayout.setVisibility(View.GONE);
            view.startAnimation(buttonClick);
            restartActivity();
        });


        share_view.setOnClickListener(v -> {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String link2 = "https://" + getResources().getString(R.string.host_name) + "/" + languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE) + "/" + permalinkStr + "?content_types_id=" + contentTypesId + "&permalink=" + permalinkStr;
            String shareBodyText = "Hey I'm watching " + movieNameStr + ". Check it out on " + getResources().getString(R.string.app_name) + "\n\n" + link2;
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.host_name));
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(Intent.createChooser(sharingIntent, "Share"));
        });
        playButton.setOnClickListener(v -> actionPlay());
        btnRentNow.setOnClickListener(v -> actionPlay());

        //Related  content
        relatedContent = (RecyclerView) findViewById(R.id.related_content);
        relatedAudioContentRecyclerview = (RecyclerView) findViewById(R.id.related_audio_content);
        RelatedContentTitle = (TextView) findViewById(R.id.related_content_title);
        relatedAudioContentTitle = (TextView) findViewById(R.id.related_audio_content_title);
        RelatedContentTitle.setText(languagePreference.getTextofLanguage(RELATED_CONTENT_VIDEO_TITLE, DEFAULT_RELATED_CONTENT_VIDEO_TITLE));
        try {
            registerReceiver(SongStatusReciver, new IntentFilter(Constant.SONG_STATUS));
            registerReceiver(CLOSE_NOTIFICATION, new IntentFilter(Constant.CLOSE_NOTIFICATION));
            registerReceiver(SONG_STATUS_NEXT, new IntentFilter(Constant.SONG_STATUS_NEXT));
            registerReceiver(SONG_STATUS_PREVIOUS, new IntentFilter(Constant.SONG_STATUS_PREVIOUS));

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!NetworkStatus.getInstance().isConnected(MovieDetailsActivity.this)) {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
            (findViewById(R.id.detailsRelativeLayout)).setVisibility(View.GONE);
        } else {
            callContentDetailsApi();
        }
    }

    /**
     * Sending data to server to get content details.
     */
    private void callContentDetailsApi() {
        ContentDetailsInput contentDetailsInput = new ContentDetailsInput();
        contentDetailsInput.setAuthToken(authTokenStr);
        contentDetailsInput.setPermalink(permalinkStr);
        contentDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
        contentDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
        contentDetailsInput.setState(preferenceManager.getStateFromPref());
        contentDetailsInput.setCity(preferenceManager.getCityFromPref());
        contentDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        asynLoadMovieDetails = new GetContentDetailsAsynTask(contentDetailsInput, MovieDetailsActivity.this, MovieDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
        asynLoadMovieDetails.execute(); //threadPoolExecutor
    }



    public void actionPlay() {
        AutoPlayUtil.callAutoPlayAPI(movieStreamUniqueId, getApplicationContext(), playerModel);

        if (!NetworkStatus.getInstance().isConnected(MovieDetailsActivity.this)) {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
            (findViewById(R.id.detailsRelativeLayout)).setVisibility(View.GONE);
            return;
        }

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

                stopService(new Intent(MovieDetailsActivity.this, MusicService.class));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        getVideoDetailsForPlayOrDownload();
    }


    private void getVideoDetailsForPlayOrDownload() {
        if (!NetworkStatus.getInstance().isConnected(MovieDetailsActivity.this)) {
            Toast.makeText(MovieDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
            return;
        }

        playerModel.setStreamUniqueId(movieStreamUniqueId);
        playerModel.setMovieUniqueId(movieUniqueId);
        playerModel.setUserId(preferenceManager.getUseridFromPref());
        playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
        playerModel.setAuthTokenStr(authTokenStr.trim());
        playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH);
        playerModel.setEpisode_id("0");
        playerModel.setIsFreeContent(isFreeContent);
        playerModel.setVideoTitle(movieNameStr);
        playerModel.setVideoStory(movieDetailsStr);
        playerModel.setVideoGenre(videoGenreTextView.getText().toString());
        playerModel.setVideoDuration(videoDurationTextView.getText().toString());
        playerModel.setVideoReleaseDate(videoReleaseDateTextView.getText().toString());
        playerModel.setCensorRating(censorRatingStr);
        playerModel.setContentTypesId(contentTypesId);
        // playerModel.setContentTypesId(4);  // was Added by Debashish Temporarily
        playerModel.setPosterImageId(posterImageId);

        DataModel dbModel = new DataModel();
        dbModel.setIsFreeContent(isFreeContent);
        dbModel.setIsAPV(isAPV);
        dbModel.setIsPPV(isPPV);
        dbModel.setMovieUniqueId(movieUniqueId);
        dbModel.setStreamUniqueId(movieStreamUniqueId);
        dbModel.setThirdPartyUrl(movieThirdPartyUrl);
        dbModel.setVideoTitle(movieNameStr);
        dbModel.setVideoStory(movieDetailsStr);
        dbModel.setVideoGenre(videoGenreTextView.getText().toString());
        dbModel.setVideoDuration(videoDurationTextView.getText().toString());
        dbModel.setVideoReleaseDate(videoReleaseDateTextView.getText().toString());
        dbModel.setCensorRating(censorRatingStr);
        dbModel.setEpisode_id("0");
        dbModel.setSeason_id("0");
        dbModel.setPurchase_type("show");
        dbModel.setPosterImageId(posterImageId);
        dbModel.setContentTypesId(contentTypesId);
        //dbModel.setContentTypesId(4); // Added by Debashish temporarily

        Util.dataModel = dbModel;
        SubTitleName.clear();
        SubTitlePath.clear();
        ResolutionUrl.clear();
        ResolutionFormat.clear();
        Util.check_for_subscription = 1;

        if ((preferenceManager.getLoginFeatureFromPref() == 1)) {

            String loggedInStr = preferenceManager.getUseridFromPref();
            if (loggedInStr == null) {

                if ((playerModel.getIsFreeContent() == 1)
                        && !featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN_REQUIRED_FOR_FREECONTENT, FeatureHandler.DEFAULT_IS_LOGIN_REQUIRED_FOR_FREECONTENT)) {
                    getVideoInfo();
                } else {

                    Util.check_for_subscription = 1;
                    Intent registerActivity = new LoginRegistrationOnContentClickHandler(MovieDetailsActivity.this)
                            .handleClickOnContent();

                    registerActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    registerActivity.putExtra("PlayerModel", playerModel);
                    startActivityForResult(registerActivity, VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE);

                }
            } else {
                getVideoInfo();
            }

        } else {
            getVideoInfo();
        }
    }


    /**
     *
     * description: reload activity
     */
    public void restartActivity() {
        try {

            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);

        } catch (Exception e) {
        }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE && resultCode == RESULT_OK) {
            getVideoInfo();

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(SongStatusReciver);
            unregisterReceiver(CLOSE_NOTIFICATION);
            unregisterReceiver(SONG_STATUS_NEXT);
            unregisterReceiver(SONG_STATUS_PREVIOUS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Util.preorder_status = 0;

        try {
            if (preferenceManager.getFreeUserPref().equals("1")) {
                playButton.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        // init audio minicontroller
        initAudioCustomMiniController();
        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                final Intent searchIntent = new Intent(MovieDetailsActivity.this, SearchActivity.class);
                searchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(searchIntent);
                // Not implemented here
            default:
                break;
        }

        return false;
    }

    @Override
    public void onLogoutPostExecuteCompleted(int code, String status, String message) {
        Util.check_for_subscription = 0;

        if (status == null) {
            Toast.makeText(MovieDetailsActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code == 0) {
            Toast.makeText(MovieDetailsActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code > 0) {
            if (code == 200) {
                preferenceManager.clearLoginPref();

                if ((featureHandler.getFeatureStatus(FeatureHandler.SIGNUP_STEP, FeatureHandler.DEFAULT_SIGNUP_STEP))) {
                    final Intent startIntent = new Intent(MovieDetailsActivity.this, LoginActivity.class);

                    startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(startIntent);
                    Toast.makeText(MovieDetailsActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    final Intent startIntent = new Intent(MovieDetailsActivity.this, MainActivity.class);

                    startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(startIntent);
                    Toast.makeText(MovieDetailsActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                    finish();

                }

            } else {
                Toast.makeText(MovieDetailsActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

            }
        }

    }

    @Override
    public void update(Observable o, Object data) {
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


        audioRelatedContentList = new ArrayList<RelatedContentModel>();
        videoRelatedContentList = new ArrayList<RelatedContentModel>();

        if (relatedContentOutput.getContentData() == null)
            return;

        if (status == 200) {
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

                episodeVideoUrlStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
                if (relatedContentOutput.getContentData().get(a).getContent_types_id().equals("1") || relatedContentOutput.getContentData().get(a).getContent_types_id().equals("3")) {
                    videoRelatedContentList.add(new RelatedContentModel(episodeNoStr, episodeStoryStr, episodeDateStr, episodeImageStr, episodeTitleStr, episodeVideoUrlStr, episodeSeriesNoStr,
                            movie_unique_id, episodeMovieStreamUniqueIdStr, episodeThirdParty, videodurationStr, Integer.parseInt(episodeContenTTypesId), Integer.parseInt(is_episode), c_permalink, season_permalink));
                } else if (relatedContentOutput.getContentData().get(a).getContent_types_id().equals("5") || relatedContentOutput.getContentData().get(a).getContent_types_id().equals("6")) {
                    audioRelatedContentList.add(new RelatedContentModel(episodeNoStr, episodeStoryStr, episodeDateStr, episodeImageStr, episodeTitleStr, episodeVideoUrlStr, episodeSeriesNoStr,
                            movie_unique_id, episodeMovieStreamUniqueIdStr, episodeThirdParty, videodurationStr, Integer.parseInt(episodeContenTTypesId), Integer.parseInt(is_episode), c_permalink, ""));
                }

            }


            relatedContent.setVisibility(View.VISIBLE);
            RelatedContentTitle.setVisibility(View.VISIBLE);
            mLayoutManager1 = new LinearLayoutManager(MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
            relatedContent.setLayoutManager(mLayoutManager1);
            relatedContent.setItemAnimator(new DefaultItemAnimator());

            if (videoRelatedContentList.size() > 0) {
                RelatedContentTitle.setVisibility(View.VISIBLE);
                RelatedContentTitle.setText(languagePreference.getTextofLanguage(RELATED_CONTENT_VIDEO_TITLE, DEFAULT_RELATED_CONTENT_VIDEO_TITLE));
                LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
                relatedContent.setLayoutManager(mLayoutManager1);
                relatedContent.setItemAnimator(new DefaultItemAnimator());

                new GetImageSize(MovieDetailsActivity.this, videoRelatedContentList.get(0).getEpisodeThumbnailImageView(), new GetImageSize.ImageSizeInterface() {
                    @Override
                    public void onImageLoaded(int width, int height) {
                        videoHeight = height;
                        videoWidth = width;
                        setVideoRelatedContentAdapter("video");

                    }
                });

            }
            if (audioRelatedContentList.size() > 0) {
                relatedAudioContentTitle.setVisibility(View.VISIBLE);
                relatedAudioContentTitle.setText(languagePreference.getTextofLanguage(RELATED_CONTENT_AUDIO_TITLE, DEFAULT_RELATED_CONTENT_AUDIO_TITLE));
                LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
                relatedAudioContentRecyclerview.setLayoutManager(mLayoutManager1);
                relatedAudioContentRecyclerview.setItemAnimator(new DefaultItemAnimator());

                new GetImageSize(MovieDetailsActivity.this, audioRelatedContentList.get(0).getEpisodeThumbnailImageView(), new GetImageSize.ImageSizeInterface() {
                    @Override
                    public void onImageLoaded(int width, int height) {
                        videoHeight = height;
                        videoWidth = width;
                        setVideoRelatedContentAdapter("audio");
                    }
                });

            }

        }

    }

    private void setVideoRelatedContentAdapter(String contentType) {
        if (videoRelatedContentList.size() > 0 && contentType.equals("video")) {
            RelatedContentAdapter relatedContentAdapter = new RelatedContentAdapter(MovieDetailsActivity.this, getItemLayoutId(), videoRelatedContentList, new RelatedContentAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(RelatedContentModel item) {
                    relatedContentItemClick(item);

                }
            });
            relatedContent.setAdapter(relatedContentAdapter);
        }
        if (audioRelatedContentList.size() > 0 && contentType.equals("audio")) {
            RelatedContentAdapter relatedContentAdapter = new RelatedContentAdapter(MovieDetailsActivity.this, R.layout.list_card_related_portrait, audioRelatedContentList, new RelatedContentAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(RelatedContentModel item) {
                    relatedContentItemClick(item);

                }
            });

            relatedAudioContentRecyclerview.setAdapter(relatedContentAdapter);
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
    public void onGetContentDetailsPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onGetContentDetailsPostExecuteCompleted(ContentDetailsOutput contentDetailsOutput, int status, String message) {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }
        } catch (IllegalArgumentException ex) {
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
            playButton.setEnabled(false);
            bannerImageRelativeLayout.setVisibility(View.GONE);

        }

        if (status == 200) {

            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.GONE);
            isPPV = contentDetailsOutput.getIsPpv();
            isAPV = contentDetailsOutput.getIsApv();
            movieUniqueId = contentDetailsOutput.getMuviUniqId();
            isEpisode = contentDetailsOutput.getIsEpisode();
            movieStreamUniqueId = contentDetailsOutput.getMovieStreamUniqId();
            movieNameStr = contentDetailsOutput.getName();
            is_converted = contentDetailsOutput.getIsConverted();

            try {
                isFreeContent = Integer.parseInt(contentDetailsOutput.getIsFreeContent());
            } catch (Exception e) {
                e.printStackTrace();
            }

            censorRatingStr = contentDetailsOutput.getCensorRating();
            movieDetailsStr = contentDetailsOutput.getStory();
            movieStreamId = contentDetailsOutput.getMovieStreamId();
            movieIdStr = contentDetailsOutput.getId();
            posterImageId = contentDetailsOutput.getPoster();
            contentTypesId = contentDetailsOutput.getContentTypesId();

            //contentTypesId = 4;
            Util.currencyModel = contentDetailsOutput.getCurrencyDetails();
            Util.apvModel = contentDetailsOutput.getApvDetails();
            Util.ppvModel = contentDetailsOutput.getPpvDetails();


            if (Util.getPlayButtonStatus(contentDetailsOutput, MovieDetailsActivity.this)) {
                playButton.setVisibility(View.VISIBLE);
            } else {
                playButton.setVisibility(View.GONE);
            }

            if (contentDetailsOutput.getIsApv() == 1) {
                playButton.setVisibility(View.INVISIBLE);
                contentDetailsOutput.setContentDownLoadStatus(false);
                playButton.setVisibility(View.GONE);

            }


            videoTitle.setVisibility(View.VISIBLE);
            Typeface castDescriptionTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.regular_fonts));
            videoTitle.setTypeface(castDescriptionTypeface);
            videoTitle.setText(contentDetailsOutput.getName());


            if (contentDetailsOutput.getGenre() != null && contentDetailsOutput.getGenre().matches("") || contentDetailsOutput.getGenre().matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoGenreTextView.setVisibility(View.GONE);

            } else {
                videoGenreTextView.setVisibility(View.VISIBLE);
                Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                videoGenreTextView.setTypeface(videoGenreTextViewTypeface);
                videoGenreTextView.setText(contentDetailsOutput.getGenre());

            }

            if (contentDetailsOutput.getIs_livestream_enabled() == 1 || contentDetailsOutput.getVideoDuration().matches("") || contentDetailsOutput.getVideoDuration().matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoDurationTextView.setVisibility(View.GONE);
            } else {
                Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                videoDurationTextView.setTypeface(videoGenreTextViewTypeface);
                videoDurationTextView.setText(contentDetailsOutput.getVideoDuration());
            }
            if (contentDetailsOutput.getStory().matches("") || contentDetailsOutput.getStory().matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoStoryTextView.setVisibility(View.GONE);

            } else {
                videoStoryTextView.setVisibility(View.VISIBLE);
                Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                videoStoryTextView.setTypeface(videoGenreTextViewTypeface);
                videoStoryTextView.setText(Util.getTextViewTextFromApi(contentDetailsOutput.getStory().trim()));
                ResizableCustomView.doResizeTextView(MovieDetailsActivity.this, videoStoryTextView, MAX_LINES, languagePreference.getTextofLanguage(VIEW_MORE, DEFAULT_VIEW_MORE), true);
            }

            if (contentDetailsOutput.getCensorRating().matches("") || contentDetailsOutput.getCensorRating().matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoCensorRatingTextView.setVisibility(View.GONE);
                videoCensorRatingTextView1.setVisibility(View.GONE);

            } else {

                if (contentDetailsOutput.getCensorRating().contains("-")) {
                    String Data[] = contentDetailsOutput.getCensorRating().split("-");
                    videoCensorRatingTextView.setVisibility(View.VISIBLE);
                    videoCensorRatingTextView1.setVisibility(View.VISIBLE);
                    Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                    videoCensorRatingTextView.setTypeface(videoGenreTextViewTypeface);
                    videoCensorRatingTextView1.setTypeface(videoGenreTextViewTypeface);
                    if (Data.length > 0) {
                        videoCensorRatingTextView.setText(Data[0]);
                        videoCensorRatingTextView1.setText(Data[1]);
                    }


                } else {
                    videoCensorRatingTextView.setVisibility(View.VISIBLE);
                    videoCensorRatingTextView1.setVisibility(View.GONE);
                    Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                    videoCensorRatingTextView.setTypeface(videoGenreTextViewTypeface);
                    videoCensorRatingTextView.setText(contentDetailsOutput.getCensorRating());
                }
            }


            if (contentDetailsOutput.getReleaseDate().matches("") || contentDetailsOutput.getReleaseDate().matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoReleaseDateTextView.setVisibility(View.GONE);

            } else {

                videoReleaseDateTextView.setVisibility(View.VISIBLE);
                Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                videoReleaseDateTextView.setTypeface(videoGenreTextViewTypeface);
                movieReleaseDateStr = Util.formateDateFromstring("yyyy-mm-dd", "yyyy", contentDetailsOutput.getReleaseDate());
                videoReleaseDateTextView.setText(movieReleaseDateStr);
            }

            if ((videoDurationTextView.getVisibility() == View.VISIBLE && videoCensorRatingTextView.getVisibility() == View.VISIBLE) ||
                    (videoDurationTextView.getVisibility() == View.VISIBLE && videoCensorRatingTextView1.getVisibility() == View.VISIBLE))
                censor_bar.setVisibility(View.VISIBLE);

            if ((videoDurationTextView.getVisibility() == View.VISIBLE || videoCensorRatingTextView.getVisibility() == View.VISIBLE
                    || videoCensorRatingTextView1.getVisibility() == View.VISIBLE) && videoReleaseDateTextView.getVisibility() == View.VISIBLE)
                year_bar.setVisibility(View.VISIBLE);

            if ((videoDurationTextView.getVisibility() == View.VISIBLE || videoCensorRatingTextView.getVisibility() == View.VISIBLE
                    || videoDurationTextView.getVisibility() == View.VISIBLE) && purchasetype.getVisibility() == View.VISIBLE)
                type_bar.setVisibility(View.VISIBLE);


            bannerUrl = contentDetailsOutput.getBanner().trim().equals("") ? contentDetailsOutput.getPoster().trim() : contentDetailsOutput.getBanner().trim();
            Picasso.with(MovieDetailsActivity.this)
                    .load(bannerUrl)
                    .error(R.drawable.logo)
                    .into(moviePoster);

            relatedContentAPICall();

            if (contentDetailsOutput.getIs_livestream_enabled() == 1) {
                downloadRelativeLayout.setVisibility(View.INVISIBLE);
            }

        } else if (status == 414) {
            noDataTextView.setText(languagePreference.getTextofLanguage(CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY, DEFAULT_CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY));
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.VISIBLE);
            story_layout.setVisibility(View.GONE);
            bannerImageRelativeLayout.setVisibility(View.GONE);
            iconImageRelativeLayout.setVisibility(View.GONE);
        } else {
            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.bringToFront();
            noDataLayout.setVisibility(View.VISIBLE);
            playButton.setEnabled(false);
            bannerImageRelativeLayout.setVisibility(View.GONE);
        }

    }


    @Override
    public void onGetGenreListPreExecuteStarted() {
        pDialog = new ProgressBarHandler(MovieDetailsActivity.this);
        pDialog.show();
    }

    @Override
    public void onGetGenreListPostExecuteCompleted(ArrayList<GenreListOutput> genreListOutput, ArrayList<SortByModel> sortByListOutput, int code, String status) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }

        final Intent detailsIntent = new Intent(MovieDetailsActivity.this, MovieDetailsActivity.class);
        detailsIntent.putExtra(PERMALINK_INTENT_KEY, permalinkStr);
        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(detailsIntent);
        finish();

        preferenceManager.setLanguageChangeStatus("1");

    }

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

        if (jsonResponse != null) {
            if (status == 200) {
                Util.parseLanguage(languagePreference, jsonResponse, default_Language);
                genre_call(default_Language);
            }

        }
    }


    @Override
    public void onVideoDetailsPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onVideoDetailsPostExecuteCompleted(Video_Details_Output _video_details_output,
                                                   int code, String status, String message) {

        try {
            if (pDialog != null && pDialog.isShowing())
                pDialog.hide();
        } catch (IllegalArgumentException ex) {
        }


        boolean play_video = true;

        if (featureHandler.getFeatureStatus(FeatureHandler.IS_STREAMING_RESTRICTION, FeatureHandler.DEFAULT_IS_STREAMING_RESTRICTION) && preferenceManager.getUseridFromPref() != null) {
            play_video = (_video_details_output != null && _video_details_output.getStreaming_restriction() != null && !_video_details_output.getStreaming_restriction().trim().equals("0"));
        } else {
            play_video = true;
        }
        if (!play_video) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(MovieDetailsActivity.this, R.style.MyAlertDialogStyle);
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

        if (code == 200) {

            playerModel.setIsOffline(_video_details_output.getIs_offline());
            playerModel.setSkipIntro(_video_details_output.getSkipIntroStartPosition(), _video_details_output.getSkipIntroEndPosition());
            playerModel.setDownloadStatus(_video_details_output.getDownload_status());
            playerModel.setHas_video_card(Integer.parseInt(_video_details_output.getHas_video_card()));


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

            /**
             * Following code is responsible for multiple subtitle support for cast&play
             */
            SubTitleName = _video_details_output.getSubTitleName();
            SubTitleLanguage = _video_details_output.getSubTitleLanguage();

            //dependency for datamodel
            Util.dataModel.setVideoUrl(playerModel.getVideoUrl());
            Util.dataModel.setVideoResolution(_video_details_output.getVideoResolution());
            Util.dataModel.setThirdPartyUrl(_video_details_output.getThirdparty_url());
            Util.dataModel.setAdNetworkId(_video_details_output.getAdNetworkId());
            Util.dataModel.setChannel_id(_video_details_output.getChannel_id());
            Util.dataModel.setPreRoll(_video_details_output.getPreRoll());
            Util.dataModel.setPostRoll(_video_details_output.getPostRoll());
            Util.dataModel.setMidRoll(_video_details_output.getMidRoll());
            Util.dataModel.setPlayPos(Util.isDouble(_video_details_output.getPlayed_length()));
            Util.dataModel.setAdDetails(_video_details_output.getAdDetails());


            //player model set
            playerModel.setVastAdTag(_video_details_output.getVast_ad_tag());
            playerModel.setSubTitleName(_video_details_output.getSubTitleName());
            playerModel.setSubTitlePath(_video_details_output.getSubTitlePath());
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
            is_livestreamEnabled = _video_details_output.getIs_livestream_enabled();
            livestream_resume_time = _video_details_output.getLivestream_resume_time();
            if (is_livestreamEnabled.equals("1")) {
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
                Util.showNoDataAlert(MovieDetailsActivity.this);
            } else {

                // condition for checking if the response has third party url or not.
                if (_video_details_output.getThirdparty_url() == null ||
                        _video_details_output.getThirdparty_url().matches("")
                ) {


                    {
                        playerModel.setThirdPartyPlayer(false);

                        final Intent playVideoIntent;
                        if (Util.dataModel.getAdNetworkId() == 3) {
                            playVideoIntent = Util.getPlayerIntent(MovieDetailsActivity.this);

                        } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                            if (Util.dataModel.getPlayPos() <= 0) {
                                playVideoIntent = new Intent(MovieDetailsActivity.this, AdPlayerActivity.class);
                            } else {
                                playVideoIntent = Util.getPlayerIntent(MovieDetailsActivity.this);
                            }

                        } else {

                            playVideoIntent = Util.getPlayerIntent(MovieDetailsActivity.this);
                        }
                        playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        playVideoIntent.putExtra("PlayerModel", playerModel);
                        startActivity(playVideoIntent);
                        return;

                    }
                } else {
                    final Intent playVideoIntent = Util.getPlayerIntent(MovieDetailsActivity.this);
                    playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    playVideoIntent.putExtra("PlayerModel", playerModel);
                    startActivity(playVideoIntent);
                }
            }

        } else if (code == 436) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(MovieDetailsActivity.this, R.style.MyAlertDialogStyle);
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
            Util.showNoDataAlert(MovieDetailsActivity.this);
        }

    }


    private IntentFilter mIntentFilter;
    private BroadcastReceiver DELETE_ACTION = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    /**
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
        }


    }


    @Override
    public void onLogoutPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }


    public void genre_call(String default_Language) {
        GenreListInput genreListInput = new GenreListInput();
        genreListInput.setAuthToken(authTokenStr);
        genreListInput.setLang_code(default_Language);
        {
            genreListInput.setIs_specific("0"); // for others
        }
        genreListInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        GetGenreListAsynctask asynGetGenreList = new GetGenreListAsynctask(genreListInput, MovieDetailsActivity.this, MovieDetailsActivity.this, false, new DataController(MovieDetailsActivity.this));
        asynGetGenreList.execute(); //threadPoolExecutor

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
        asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, MovieDetailsActivity.this, MovieDetailsActivity.this);
        asynLoadVideoUrls.execute(); //threadPoolExecutor
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

            }
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
                    runOnUiThread(new Runnable() {
                        public void run() {
                            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            detailsIntent.putExtra("season_permalink", item.getSeason_permalink());
                            detailsIntent.putExtra("parent_permalink", item.getcPermalink());
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
        playerModel.setContentTypesId(item.getEpisodeContentTypesId());
        playerModel.setPosterImageId(item.getEpisodeThumbnailImageView());

        String loggedInStr = preferenceManager.getLoginStatusFromPref();
        Util.check_for_subscription = 1;
        if (isLogin == 1) {
            if (loggedInStr != null) {
                if (NetworkStatus.getInstance().isConnected(this)) {

                    GetVideoDetailsInput getVideoDetailsInput = new GetVideoDetailsInput();
                    getVideoDetailsInput.setAuthToken(authTokenStr);
                    getVideoDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
                    getVideoDetailsInput.setContent_uniq_id(Util.dataModel.getMovieUniqueId().trim());
                    getVideoDetailsInput.setStream_uniq_id(Util.dataModel.getStreamUniqueId().trim());
                    getVideoDetailsInput.setInternetSpeed(MainActivity.internetSpeed.trim());
                    getVideoDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, MovieDetailsActivity.this, MovieDetailsActivity.this);
                    asynLoadVideoUrls.executeOnExecutor(threadPoolExecutor);


                } else {
                    Util.showToast(MovieDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
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
                asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, MovieDetailsActivity.this, MovieDetailsActivity.this);
                asynLoadVideoUrls.executeOnExecutor(threadPoolExecutor);

            } else {
                Util.showToast(MovieDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));

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

        GetRelatedContentAsynTask asyncRelatedContent = new GetRelatedContentAsynTask(relatedContentInput, MovieDetailsActivity.this, MovieDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(MovieDetailsActivity.this));
        asyncRelatedContent.execute();

    }


    int getItemLayoutId() {

        /**
         * @author: Debashish
         * @description : New UI Change mantis: 19619
         */
        if (videoHeight > videoWidth) {
            return R.layout.list_card_related_portrait;
        }
        return R.layout.list_card_related_landscape;
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
        audioCustomMiniController = new AudioCustomMiniController(MovieDetailsActivity.this, permalinkStr, getSupportFragmentManager());
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
                    //Util.setMargin(minicontroller,relatedAudioRecyclerview);
                    mRecyclerView = relatedAudioContentRecyclerview;
                } else if (videoRelatedContentList.size() > 0 && audioRelatedContentList.size() == 0) {
                    mRecyclerView = relatedContent;
                } else {
                    mRecyclerView = relatedAudioContentRecyclerview;
                }

                Util.setMargin(minicontroller, mRecyclerView, MovieDetailsActivity.this);
            }
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
                Intent stopServiceIntent = new Intent(MovieDetailsActivity.this, MusicService.class);
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

    CoordinatorLayout minicontroller;
    SeekBar seekbar_botomSht, seekBar;
    ImageView albumArt_player;
    LinearLayout open_bottomSheet_controller, miniControllerLayout;
    TextView song_p_name, totalduration, artist_p_name, songname_player, curent_duration, total_duration;
    ImageView equalizer, background_player, player_close, player_image_main, player_play_ic, player_next_icc, player_prev_ic;
    MediaRouteButton mediaRouteButton;
    ProgressBar recylerview_progress, musicProgress;
    ViewPager squizeViewPager;
    View bottomSheet;
    public BottomSheetBehavior mBottomSheetBehavior;
    private Button clearQueue;
    private AudioCustomMiniController audioCustomMiniController;
    int videoHeight = 185;
    int videoWidth = 256;
    RecyclerView mRecyclerView;

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
