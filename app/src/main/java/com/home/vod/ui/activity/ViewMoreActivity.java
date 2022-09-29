
package com.home.vod.ui.activity;

import static android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK;
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
import static com.home.vod.preferences.LanguagePreference.DEFAULT_YES;
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
import static com.home.vod.preferences.LanguagePreference.YES;
import static com.home.vod.util.Constant.PERMALINK_INTENT_KEY;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.GridLayout.getGridLayout;
import static com.home.vod.util.Util.languageModel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.home.apisdk.apiController.GetFeatureContentAsynTask;
import com.home.apisdk.apiController.GetGenreListAsynctask;
import com.home.apisdk.apiController.GetLanguageListAsynTask;
import com.home.apisdk.apiController.GetTranslateLanguageAsync;
import com.home.apisdk.apiController.LogoutAsynctask;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
import com.home.apisdk.apiModel.FeatureContentInputModel;
import com.home.apisdk.apiModel.FeatureContentOutputModel;
import com.home.apisdk.apiModel.GenreListInput;
import com.home.apisdk.apiModel.GenreListOutput;
import com.home.apisdk.apiModel.GetVideoDetailsInput;
import com.home.apisdk.apiModel.LanguageListInputModel;
import com.home.apisdk.apiModel.LanguageListOutputModel;
import com.home.apisdk.apiModel.LogoutInput;
import com.home.apisdk.apiModel.SortByModel;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.vod.BuildConfig;
import com.home.vod.ObservableObject;
import com.home.vod.R;
import com.home.vod.async.GetImageSize;
import com.home.vod.handlers.EpisodeListOptionMenuHandler;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.model.DataModel;
import com.home.vod.model.GridItem;
import com.home.vod.model.LanguageModel;
import com.home.vod.model.QueueModel;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.service.MusicService;
import com.home.vod.ui.MediaHelper;
import com.home.vod.ui.adapter.LanguageCustomAdapter;
import com.home.vod.ui.adapter.VideoFilterAdapter;
import com.home.vod.ui.widgets.AudioCustomMiniController;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.AppThreadPoolExecutor;
import com.home.vod.util.AutoPlayUtil;
import com.home.vod.util.Constant;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;

import player.activity.AdPlayerActivity;
import player.activity.Player;


public class ViewMoreActivity extends AppCompatActivity implements
        LogoutAsynctask.LogoutListener, GetLanguageListAsynTask.GetLanguageListListener,
        GetTranslateLanguageAsync.GetTranslateLanguageInfoListener,
        GetFeatureContentAsynTask.GetFeatureContentListener,  VideoDetailsAsynctask.VideoDetailsListener, GetGenreListAsynctask.GenreListListener, MediaHelper, Observer {

    public static ProgressBarHandler progressBarHandler;
    public static final int PAYMENT_REQUESTCODE = 8889;
    public static TextView badge_text;
    public static TextView noti;
    public static FrameLayout noti_layout;

    public Toolbar mActionBarToolbar;
    public ImageView toolbarimage;
    private TextView noDataTextView;
    private TextView noInternetTextView;
    private RelativeLayout noInternetConnectionLayout;

    public ToolbarTitleHandler toolbarTitleHandler;
    private LanguageCustomAdapter languageCustomAdapter;
    private AlertDialog alert;
    private ProgressBarHandler pDialog;
    private ProgressBarHandler videoPDialog;
    private EpisodeListOptionMenuHandler episodeListOptionMenuHandler;
    private PreferenceManager preferenceManager;
    private RelativeLayout noDataLayout;

    private String email, id;
    private int Played_Length = 0;
    private String watch_status_String = "start";
    private String Default_Language = "";
    private String Previous_Selected_Language = "";
    private int prevPosition = 0;
    private int NO_IMAGE_HEIGHT = 500;
    private int NO_IMAGE_WIDTH = 300;
    private String videoImageStrToHeight;
    private boolean mIsScrollingUp;
    private int mLastFirstVisibleItem;
    private int videoHeight = 185;
    private int videoWidth = 256;
    private String loggedInIdStr, guestUserld;
    public final int CHILD_CONTENT_CLICK = 1011;
    private boolean firstTime = false;
    private String ipAddres = "";
    private int scrolledPosition = 0;
    private boolean scrolling;
    private CoordinatorLayout minicontroller;
    private SeekBar seekbar_botomSht, seekBar;
    private ImageView albumArt_player;
    private LinearLayout open_bottomSheet_controller;
    private TextView song_p_name, totalduration, artist_p_name, songname_player, curent_duration, total_duration;
    private ImageView equalizer, background_player, player_close, player_image_main, player_play_ic, player_next_icc, player_prev_ic;
    private ProgressBar recylerview_progress, musicProgress;
    private ViewPager squizeViewPager;
    private View bottomSheet;
    public BottomSheetBehavior mBottomSheetBehavior;
    private Button clearQueue;
    private AudioCustomMiniController audioCustomMiniController;
    private Player playerModel;


    /*The Data to be posted*/
    int offset = 1;
    int limit = 10;
    int listSize = 0;
    int itemsInServer = 0;


    Executor threadPoolExecutor = AppThreadPoolExecutor.getInstance().getThreadPoolExecutor();

    int isLogin = 0;

    //Adapter for GridView
    private VideoFilterAdapter customGridAdapter;

    // Async
    GetFeatureContentAsynTask asyncLoadVideos;

    LanguagePreference languagePreference;
    FeatureHandler featureHandler;

    int option_menu_id[] = {R.id.login, R.id.register, R.id.language_popup, R.id.profile, R.id.purchase, R.id.logout, R.id.notification};
    ArrayList<GridItem> itemData = new ArrayList<GridItem>();
    PopupWindow changeSortPopUp;
    LinearLayout linearLayout[];
    boolean[] visibility;
    String[] lang;

    Menu menu;

    String is_livestream_enable;
    String livestream_resume_time;
    String posterUrl;
    String sectionName;
    String sectionId;

    LinearLayout mini_controller_layout;
    TextView oopsTextView;
    Button btnRetry;
    RelativeLayout footerView;
    private RecyclerView gridView;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    /**
     * @author Bibhu Prasad Jena
     * Desc: Following method is used to initialize the minicontroller.
     */
    public void initMinicontroller() {
        mini_controller_layout = findViewById(R.id.mini_controller_layout);
    }

    /*
     * This override method is used to called before the onCreate(),
     *       which help the resources to update Activity.
     * */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleLanguageHelper.onAttach(base));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);
        languagePreference = LanguagePreference.getLanguagePreference(this);
        featureHandler = FeatureHandler.getFeaturePreference(ViewMoreActivity.this);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);

        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setTitle(getIntent().getStringExtra("sectionName"));
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        setSupportActionBar(mActionBarToolbar);
        ObservableObject.getInstance().addObserver(this);

        ipAddres = getIntent().getStringExtra("ip_address");

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

        //To set Id to action bar back button
        setIdToActionBarBackButton(mActionBarToolbar);

        /**
         * Desc : Instance creation for minicontroller.
         */

        initMinicontroller();


        if (getIntent().getStringExtra("SectionId") != null) {
            sectionId = getIntent().getStringExtra("SectionId");

        }
        if (getIntent().getStringExtra("sectionName") != null) {
            sectionName = getIntent().getStringExtra("sectionName");
        } else {
            sectionName = "";
        }

        isLogin = preferenceManager.getLoginFeatureFromPref();
        episodeListOptionMenuHandler = new EpisodeListOptionMenuHandler(this);
        posterUrl = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        gridView = (RecyclerView) findViewById(R.id.imagesGridView);
        footerView = (RelativeLayout) findViewById(R.id.loadingPanel);
        Util.recyclerView = gridView;
        noInternetConnectionLayout = (RelativeLayout) findViewById(R.id.noInternet);
        noDataLayout = (RelativeLayout) findViewById(R.id.noData);
        noInternetTextView = (TextView) findViewById(R.id.noInternetTextView);
        noDataTextView = (TextView) findViewById(R.id.noDataTextView);
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noDataTextView.setText(languagePreference.getTextofLanguage(NO_CONTENT, DEFAULT_NO_CONTENT));
        oopsTextView = (TextView) findViewById(R.id.oopsTextView);
        btnRetry = (Button) findViewById(R.id.btnRetry);
        btnRetry.setText("          " + languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN) + "       ");
        oopsTextView.setText(languagePreference.getTextofLanguage(OOPS, DEFAULT_OOPS));

        noInternetConnectionLayout.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.GONE);

        footerView.setVisibility(View.GONE);
        gridView.setVisibility(View.VISIBLE);


        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                restartActivity();
            }
        });


        gridView.setAdapter(customGridAdapter);
        gridView.addOnItemTouchListener(new RecyclerTouchListener1(getApplicationContext(), gridView, new ClickListener1() {
            @Override
            public void onClick(View view, int position) {
                GridItem item = itemData.get(position);
                String posterUrl = item.getImage();
                String movieName = item.getTitle();
                String movieGenre = item.getMovieGenre();
                String moviePermalink = item.getPermalink();
                String movieTypeId = item.getVideoTypeId();
                String is_play_list = item.getIs_play_list();
                String play_list_type = item.getPlay_list_type();
                String list_id = item.getList_id();
                String season_permalink = item.getSeason_permalink();
                String is_episode = item.getIsEpisode();
                String is_media_publish = item.getIs_media_published();
                int is_converted = item.getIsConverted();

                if (moviePermalink != null && moviePermalink.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ViewMoreActivity.this);
                    dlgAlert.setMessage(languagePreference.getTextofLanguage(NO_DETAILS_AVAILABLE, DEFAULT_NO_DETAILS_AVAILABLE));
                    dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                    dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                    dlgAlert.setCancelable(false);
                    dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                            (dialog, id) -> dialog.cancel());
                    dlgAlert.create().show();

                } else {
                    /*Author :Chitra
                     * @desc : The below condition will check wheater the content is a play list or normal content*/

                    if (is_play_list.equals("0")) {

                        if ((movieTypeId.trim().equalsIgnoreCase("1")) || (movieTypeId.trim().equalsIgnoreCase("2")) || (movieTypeId.trim().equalsIgnoreCase("4"))) {
                            final Intent movieDetailsIntent = new Intent(ViewMoreActivity.this, MovieDetailsActivity.class);
                            movieDetailsIntent.putExtra(PERMALINK_INTENT_KEY, moviePermalink);
                            movieDetailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    movieDetailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(movieDetailsIntent);
                                }
                            });


                        } else if ((movieTypeId.trim().equalsIgnoreCase("3"))) {

                            if (is_episode.equals("2")) {
                                final Intent detailsIntent = new Intent(ViewMoreActivity.this, SeasonDetailsActivity.class);
                                detailsIntent.putExtra("season_permalink", season_permalink);
                                detailsIntent.putExtra("parent_permalink", moviePermalink);
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(detailsIntent);
                                    }
                                });
                            } else if (is_episode.equals("1")) {
                                /*Condition for child content*/
                                childclickItem(getApplicationContext(), item);

                            } else {
                                final Intent detailsIntent = new Intent(ViewMoreActivity.this, ShowWithEpisodesActivity.class);
                                detailsIntent.putExtra(PERMALINK_INTENT_KEY, moviePermalink);
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(detailsIntent);
                                    }

                                });
                            }


                        } else if (movieTypeId.equals("5") || movieTypeId.equals("6")) {
                            //desired_string = moviePermalink;
                            final Intent intent = new Intent(getApplicationContext(), AudioContentDetailsActivity.class);
                            intent.putExtra("PERMALINK", moviePermalink);
                            intent.putExtra("CONTENT_TYPE", "" + movieTypeId);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }

            }


            @Override
            public void onLongClick(View view, int position) {

            }


        }));

        gridView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {
                GridLayoutManager layoutManager = (GridLayoutManager) gridView.getLayoutManager();
                int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = layoutManager.getChildCount();

                if (lastVisiblePosition >= itemsInServer - 1) {
                    footerView.setVisibility(View.GONE);
                    return;

                }

                if (view.getId() == gridView.getId()) {
                    final int currentFirstVisibleItem = firstVisiblePosition;

                    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                        mIsScrollingUp = false;

                    } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                        mIsScrollingUp = true;
                    }

                    mLastFirstVisibleItem = currentFirstVisibleItem;
                }
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    scrolling = false;

                } else if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    scrolling = true;

                }
            }

            @Override
            public void onScrolled(RecyclerView view, int dx, int dy) {
                GridLayoutManager layoutManager = (GridLayoutManager) gridView.getLayoutManager();
                int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = layoutManager.getChildCount();

                if (scrolling == true && mIsScrollingUp == false) {

                    if (firstVisiblePosition + visibleItemCount >= totalItemCount) {

                        listSize = itemData.size();
                        if (lastVisiblePosition >= itemsInServer - 1) {
                            return;

                        }
                        offset += 1;
                        if (NetworkStatus.getInstance().isConnected(ViewMoreActivity.this)) {

                            // default data
                            FeatureContentInputModel featureContentInputModel = new FeatureContentInputModel();
                            featureContentInputModel.setAuthToken(authTokenStr);
                            featureContentInputModel.setSection_id(sectionId.trim());
                            featureContentInputModel.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                            featureContentInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                            featureContentInputModel.setState(preferenceManager.getStateFromPref());
                            featureContentInputModel.setCity(preferenceManager.getCityFromPref());
                            featureContentInputModel.setResult_type("1");


                            /**
                             * We have added a new request parameter "season_info" for to get season info
                             */

                            if (featureHandler.getFeatureStatus(FeatureHandler.IS_SEASON_PAGE_AVAILABLE, FeatureHandler.DEFAULT_IS_SEASON_PAGE_AVAILABLE)) {
                                featureContentInputModel.setSeason_info("1");
                            }

                            /**
                             * @author Kushal
                             * User id in getFeatureCOntent
                             */
                            if (preferenceManager != null) {
                                String UserID = preferenceManager.getUseridFromPref();
                                if (UserID != null) {
                                    featureContentInputModel.setUserId(UserID);
                                } else {
                                    featureContentInputModel.setUserId("");

                                }
                            } else {
                                featureContentInputModel.setUserId("");
                            }
                            boolean isCacheEnabled = featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED);
                            GetFeatureContentAsynTask asyncLoadVideos = new GetFeatureContentAsynTask(featureContentInputModel, ViewMoreActivity.this, ViewMoreActivity.this, isCacheEnabled, new DataController(ViewMoreActivity.this));
                            asyncLoadVideos.executeOnExecutor(threadPoolExecutor);


                            scrolling = false;

                        } else {
                            noInternetConnectionLayout.setVisibility(View.VISIBLE);
                        }

                    }

                }
            }
        });

        //Detect Network Connection

        if (!NetworkStatus.getInstance().isConnected(ViewMoreActivity.this)) {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.GONE);
            footerView.setVisibility(View.GONE);
        }

        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT; //this is in pixels
        gridView.setLayoutParams(layoutParams);


        firstTime = true;


        //Load first 10 data items

        if (itemData != null && itemData.size() > 0) {
            itemData.clear();
        }
        offset = 1;
        scrolledPosition = 0;
        listSize = 0;
        itemsInServer = 0;
        if (((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) || ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_XLARGE)) {
            limit = 20;
        } else {
            limit = 15;
        }
        scrolling = false;
        if (NetworkStatus.getInstance().isConnected(ViewMoreActivity.this)) {
            FeatureContentInputModel featureContentInputModel = new FeatureContentInputModel();
            featureContentInputModel.setAuthToken(authTokenStr);
            featureContentInputModel.setSection_id(sectionId.trim());
            featureContentInputModel.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            featureContentInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
            featureContentInputModel.setState(preferenceManager.getStateFromPref());
            featureContentInputModel.setCity(preferenceManager.getCityFromPref());
            featureContentInputModel.setResult_type("1");

            /**
             * We have added a new request parameter "season_info" for to get season info
             */

            if (featureHandler.getFeatureStatus(FeatureHandler.IS_SEASON_PAGE_AVAILABLE, FeatureHandler.DEFAULT_IS_SEASON_PAGE_AVAILABLE)) {
                featureContentInputModel.setSeason_info("1");
            }

            /**
             * @author Kushal
             * User id in getFeatureCOntent
             */
            if (preferenceManager != null) {
                String UserID = preferenceManager.getUseridFromPref();
                if (UserID != null) {
                    featureContentInputModel.setUserId(UserID);
                } else {
                    featureContentInputModel.setUserId("");

                }
            } else {
                featureContentInputModel.setUserId("");
            }
            boolean isCacheEnabled = featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED);
            GetFeatureContentAsynTask asyncLoadVideos = new GetFeatureContentAsynTask(featureContentInputModel, ViewMoreActivity.this, ViewMoreActivity.this, isCacheEnabled, new DataController(ViewMoreActivity.this));
            asyncLoadVideos.executeOnExecutor(threadPoolExecutor);
        } else {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void update(Observable observable, Object data) {
        Intent intent = (Intent) data;
        if (intent.hasExtra("IntentFor")) {
            audioCustomMiniController.playerDetails(intent);
        }
    }

    public void restartActivity() {
        try {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickItem(GridItem item) {
        String moviePermalink = item.getPermalink();
        String movieTypeId = item.getVideoTypeId();

        // for tv shows navigate to episodes
        if ((movieTypeId.equalsIgnoreCase("3"))) {
            if (moviePermalink.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ViewMoreActivity.this, R.style.MyAlertDialogStyle);
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
                dlgAlert.create().show();
            } else {

                final Intent detailsIntent = new Intent(ViewMoreActivity.this, ShowWithEpisodesActivity.class);
                detailsIntent.putExtra(PERMALINK_INTENT_KEY, moviePermalink);
                runOnUiThread(new Runnable() {
                    public void run() {
                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(detailsIntent);
                    }
                });
            }

        }

        // for single clips and movies
        else if ((movieTypeId.trim().equalsIgnoreCase("1")) || (movieTypeId.trim().equalsIgnoreCase("2")) || (movieTypeId.trim().equalsIgnoreCase("4"))) {
            final Intent detailsIntent = new Intent(ViewMoreActivity.this, MovieDetailsActivity.class);

            if (moviePermalink.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ViewMoreActivity.this, R.style.MyAlertDialogStyle);
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
                dlgAlert.create().show();
            } else {
                detailsIntent.putExtra(PERMALINK_INTENT_KEY, moviePermalink);
                runOnUiThread(new Runnable() {
                    public void run() {
                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(detailsIntent);
                    }
                });
            }
        }

    }


    @Override
    public void onBackPressed() {
        if (asyncLoadVideos != null) {
            asyncLoadVideos.cancel(true);
        }

        finish();
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    @Override
    public void onLogoutPreExecuteStarted() {
        pDialog = new ProgressBarHandler(ViewMoreActivity.this);
        pDialog.show();
    }

    @Override
    public void onLogoutPostExecuteCompleted(int code, String status, String message) {

        try {
            Util.check_for_subscription = 0;
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;

            }
        } catch (IllegalArgumentException ex) {
            Toast.makeText(ViewMoreActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (status == null) {
            Toast.makeText(ViewMoreActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code == 0) {
            Toast.makeText(ViewMoreActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code > 0) {
            if (code == 200) {
                preferenceManager.clearLoginPref();

                if ((featureHandler.getFeatureStatus(FeatureHandler.SIGNUP_STEP, FeatureHandler.DEFAULT_SIGNUP_STEP))) {

                    final Intent startIntent = new Intent(ViewMoreActivity.this, LoginActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(ViewMoreActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });
                } else {
                    final Intent startIntent = new Intent(ViewMoreActivity.this, MainActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(ViewMoreActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });
                }

            } else {
                Toast.makeText(ViewMoreActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

            }
        }

    }

    @Override
    public void onGetLanguageListPreExecuteStarted() {
        progressBarHandler = new ProgressBarHandler(ViewMoreActivity.this);
        progressBarHandler.show();

    }

    @Override
    public void onGetLanguageListPostExecuteCompleted(ArrayList<LanguageListOutputModel> languageListOutputArray, int status, String message, String defaultLanguage) {
        if (progressBarHandler.isShowing()) {
            progressBarHandler.hide();
            progressBarHandler = null;

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
    public void onGetFeatureContentPreExecuteStarted() {

        if (MainActivity.internetSpeedDialog != null && MainActivity.internetSpeedDialog.isShowing()) {
            videoPDialog = MainActivity.internetSpeedDialog;
            footerView.setVisibility(View.GONE);

        } else {
            videoPDialog = new ProgressBarHandler(ViewMoreActivity.this);

            if (listSize == 0) {
                // hide loader for first time

                videoPDialog.show();
                footerView.setVisibility(View.GONE);
            } else {
                // show loader for first time
                videoPDialog.hide();
                footerView.setVisibility(View.VISIBLE);

            }
        }
    }

    @Override
    public void onGetFeatureContentPostExecuteCompleted(ArrayList<FeatureContentOutputModel> featureContentOutputModelArray, int status, String message, boolean isCache) {
        try {
            if (videoPDialog != null && videoPDialog.isShowing()) {
                videoPDialog.hide();

            }
        } catch (IllegalArgumentException ex) {

        }


        itemData.clear();

        String movieImageStr = "";
        /**
         * @author Bibhu
         * Adding first content as layout cooser
         */

        boolean add_to_list_orientation_checker = true;

        for (int i = 0; i < featureContentOutputModelArray.size(); i++) {
            movieImageStr = featureContentOutputModelArray.get(i).getPoster_url();
            String movieName = featureContentOutputModelArray.get(i).getName();
            String videoTypeIdStr = featureContentOutputModelArray.get(i).getContent_types_id();
            String movieGenreStr = featureContentOutputModelArray.get(i).getGenre();
            String moviePermalinkStr = featureContentOutputModelArray.get(i).getPermalink();

            String season_permalink = featureContentOutputModelArray.get(i).getSeason_permalink();

            String isEpisodeStr = featureContentOutputModelArray.get(i).getIs_episode();
            int isConverted = featureContentOutputModelArray.get(i).getIs_converted();
            int isPPV = featureContentOutputModelArray.get(i).getIs_ppv();
            int isAPV = featureContentOutputModelArray.get(i).getIs_advance();
            String play_list_type = featureContentOutputModelArray.get(i).getPlay_list_type();
            String is_play_list = featureContentOutputModelArray.get(i).getIs_play_list();
            String list_id = featureContentOutputModelArray.get(i).getList_id();
            String poster_url = featureContentOutputModelArray.get(i).getPoster_url();
            String name = featureContentOutputModelArray.get(i).getName();
            String season_no = featureContentOutputModelArray.get(i).getSeason_no();
            String parent_title = featureContentOutputModelArray.get(i).getParent_title();
            String parent_poster = featureContentOutputModelArray.get(i).getParent_poster_for_mobile_apps();
            String video_duration = featureContentOutputModelArray.get(i).getVideo_duration();
            String story = featureContentOutputModelArray.get(i).getStory();
            String episode_no = featureContentOutputModelArray.get(i).getEpisode_no();
            String muvi_unique_id = featureContentOutputModelArray.get(i).getMuvi_unique_id();
            String stream_uniqueid = featureContentOutputModelArray.get(i).getStream_unique_id();
            String getIsLivestream_enable = featureContentOutputModelArray.get(i).getIs_livestream_enabled();


            /**
             * desc This method will check wheather the application is of VOD type and the content comming from server is also of video type
             */
            if (Util.isVideoContent(videoTypeIdStr)) {
                Util.APP_TYPE = Util.VOD;
            } else if (Util.isAudioContent(videoTypeIdStr)) {
                Util.APP_TYPE = Util.AOD;
            }
            if ((Util.getFilterContent(Util.APP_TYPE, videoTypeIdStr)) || (play_list_type.equals("1")) || (play_list_type.equals("2"))) {
                itemData.add(new GridItem(movieImageStr, movieName, "", videoTypeIdStr, movieGenreStr, "", moviePermalinkStr, isEpisodeStr, muvi_unique_id, stream_uniqueid, isConverted, isPPV, isAPV, "", play_list_type, is_play_list, list_id, poster_url, name, season_permalink,
                        season_no, parent_title, parent_poster, video_duration, story, episode_no));

                if (add_to_list_orientation_checker) {
                    add_to_list_orientation_checker = false;
                    videoImageStrToHeight = movieImageStr;
                }
            }

        }
        if (message == null)
            message = "0";
        if ((message.trim().equals("0"))) {

            noDataLayout.setVisibility(View.VISIBLE);
            noInternetConnectionLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.GONE);
            footerView.setVisibility(View.GONE);
        } else {
            if (itemData.size() <= 0) {
                noDataLayout.setVisibility(View.VISIBLE);
                noInternetConnectionLayout.setVisibility(View.GONE);
                gridView.setVisibility(View.GONE);
                footerView.setVisibility(View.GONE);
            } else {
                footerView.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
                noInternetConnectionLayout.setVisibility(View.GONE);
                noDataLayout.setVisibility(View.GONE);

                if (firstTime == true) {

                    /*Author:chitra
                     * desc: if first image appears no-image,then all the image will be in vertical size.*/

                    if (Util.is_contain_noimage(videoImageStrToHeight)) {

                        videoHeight = NO_IMAGE_HEIGHT;
                        videoWidth = NO_IMAGE_WIDTH;

                        renderUI();

                    } else {
                        calculateImageOrientation(videoImageStrToHeight);
                    }

                    if (!isCache) {
                        firstTime = false;
                    }

                } else {
                    customGridAdapter.notifyDataSetChanged();
                }


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

    private void videoDetailsPostExecute(Video_Details_Output _video_details_output, int statusCode, String status, String message) {
        if (progressBarHandler != null && progressBarHandler.isShowing()) {
            progressBarHandler.hide();
            progressBarHandler = null;
        }

        if (_video_details_output != null) {

            boolean play_video = true;

            if (featureHandler.getFeatureStatus(FeatureHandler.IS_STREAMING_RESTRICTION, FeatureHandler.DEFAULT_IS_STREAMING_RESTRICTION) && preferenceManager.getUseridFromPref() != null) {

                play_video = !_video_details_output.getStreaming_restriction().trim().equals("0");
            } else {
                play_video = true;
            }
            if (!play_video) {

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ViewMoreActivity.this, R.style.MyAlertDialogStyle);
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

               /*
                 for drm player below condition added
                  if studio_approved_url is there in api then set the videourl from this other wise goto 2nd one
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

            Util.dataModel.setVideoResolution(_video_details_output.getVideoResolution());

            playerModel.setVideoResolution(_video_details_output.getVideoResolution());
            if (_video_details_output.getPlayed_length() != null && !_video_details_output.getPlayed_length().equals(""))
                playerModel.setPlayPos((Util.isDouble(_video_details_output.getPlayed_length())));

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

                Util.showNoDataAlert(ViewMoreActivity.this);

            } else {
                // condition for checking if the response has third party url or not.
                if (_video_details_output.getThirdparty_url() == null || _video_details_output.getThirdparty_url().matches("")) {
                    {
                        playerModel.setThirdPartyPlayer(false);

                        final Intent playVideoIntent;
                        if (Util.dataModel.getAdNetworkId() == 3) {

                            playVideoIntent = Util.getPlayerIntent(ViewMoreActivity.this);

                        } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                            if (Util.dataModel.getPlayPos() <= 0) {
                                playVideoIntent = new Intent(ViewMoreActivity.this, AdPlayerActivity.class);
                            } else {
                                playVideoIntent = Util.getPlayerIntent(ViewMoreActivity.this);

                            }

                        } else {
                            playVideoIntent = Util.getPlayerIntent(ViewMoreActivity.this);
                        }

                        playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        playVideoIntent.putExtra("PlayerModel", playerModel);
                        startActivity(playVideoIntent);
                    }
                } else {
                    final Intent playVideoIntent = Util.getPlayerIntent(ViewMoreActivity.this);
                    playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    playVideoIntent.putExtra("PlayerModel", playerModel);
                    startActivity(playVideoIntent);
                }
            }

        } else if (statusCode == 436) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ViewMoreActivity.this, R.style.MyAlertDialogStyle);
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
            Util.showNoDataAlert(ViewMoreActivity.this);
        }
    }

    @Override
    public void onVideoDetailsPreExecuteStarted() {
        progressBarHandler = new ProgressBarHandler(ViewMoreActivity.this);
        progressBarHandler.show();
    }

    @Override
    public void onVideoDetailsPostExecuteCompleted(Video_Details_Output _video_details_output, int statusCode, String status, String message) {
        if (progressBarHandler != null && progressBarHandler.isShowing()) {
            progressBarHandler.hide();
            progressBarHandler = null;
        }

        videoDetailsPostExecute(_video_details_output, statusCode, status, message);
    }


    /**
     * Desc: Display contents after getting the data from API.
     */
    private void renderUI() {
        float density = getResources().getDisplayMetrics().density;
        gridView.smoothScrollToPosition(0);
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;  //this is in pixels
        gridView.setLayoutParams(layoutParams);
        int mNoOfColumns = Util.calculateNoOfColumns(getApplicationContext());

        if (videoWidth > videoHeight) {
            if (!Util.isTablet(ViewMoreActivity.this)) {
                mNoOfColumns = 2;//Horizontal mobile
            }
        } else {
            if (!Util.isTablet(ViewMoreActivity.this)) {
                mNoOfColumns = 3;//vertical mobile
            } else {
                if (mNoOfColumns <= 3) {
                    mNoOfColumns = 4;
                }
            }
        }

        gridView.setLayoutManager(new GridLayoutManager(getApplicationContext(), mNoOfColumns));
        customGridAdapter = new VideoFilterAdapter(getApplicationContext(), getGridLayout(videoWidth, videoHeight, density), itemData, Util.posterType(videoWidth, videoHeight));
        gridView.setAdapter(customGridAdapter);

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
    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(CLOSE_NOTIFICATION);
            unregisterReceiver(SongStatusReciver);
            unregisterReceiver(SONG_STATUS_NEXT);
            unregisterReceiver(SONG_STATUS_PREVIOUS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        /**
         * desc: Intialize view for audio minicontroller*/

        initAudioCustomMiniController();

        try {
            registerReceiver(SongStatusReciver, new IntentFilter(Constant.SONG_STATUS));
            registerReceiver(CLOSE_NOTIFICATION, new IntentFilter(Constant.CLOSE_NOTIFICATION));
            registerReceiver(SONG_STATUS_NEXT, new IntentFilter(Constant.SONG_STATUS_NEXT));
            registerReceiver(SONG_STATUS_PREVIOUS, new IntentFilter(Constant.SONG_STATUS_PREVIOUS));
        } catch (Exception e) {
            e.printStackTrace();
        }

        invalidateOptionsMenu();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                final Intent searchIntent = new Intent(ViewMoreActivity.this, SearchActivity.class);
                searchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(searchIntent);
                // Not implemented here
                return false;

            case R.id.action_login:

                Intent loginIntent = new Intent(ViewMoreActivity.this, LoginActivity.class);
                Util.check_for_subscription = 0;
                startActivity(loginIntent);
                // Not implemented here
                return false;


            case R.id.action_register:

                Intent registerIntent = new Intent(ViewMoreActivity.this, RegisterActivity.class);
                Util.check_for_subscription = 0;
                startActivity(registerIntent);
                // Not implemented here
                return false;
            case R.id.menu_item_language:

                // Not implemented here
                Default_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);
                Previous_Selected_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);

                if (languageModel != null && languageModel.size() > 0) {

                    ShowLanguagePopup();

                } else {
                    LanguageListInputModel languageListInputModel = new LanguageListInputModel();
                    languageListInputModel.setAuthToken(authTokenStr);
                    //code start: country code and language code added mantish id# 17373 @author :subhadarshani
                    languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    languageListInputModel.setLangCode(Default_Language);
                    //code  end
                    GetLanguageListAsynTask asynGetLanguageList = new GetLanguageListAsynTask(languageListInputModel, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
                    asynGetLanguageList.executeOnExecutor(threadPoolExecutor);
                }
                return false;
            case R.id.menu_item_profile:

                Intent profileIntent = new Intent(ViewMoreActivity.this, ProfileActivity.class);
                profileIntent.putExtra("EMAIL", email);
                profileIntent.putExtra("LOGID", id);
                startActivity(profileIntent);
                // Not implemented here
                return false;

            case R.id.action_logout:

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ViewMoreActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(languagePreference.getTextofLanguage(SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING));
                dlgAlert.setTitle("");

                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        // dialog.cancel();
                        if (NetworkStatus.getInstance().isConnected(ViewMoreActivity.this)) {
                            LogoutInput logoutInput = new LogoutInput();
                            logoutInput.setAuthToken(authTokenStr);
                            logoutInput.setLogin_history_id(preferenceManager.getLoginHistIdFromPref());
                            logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                            logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                            LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, ViewMoreActivity.this, ViewMoreActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ViewMoreActivity.this));
                            asynLogoutDetails.executeOnExecutor(threadPoolExecutor);


                            dialog.dismiss();
                        } else {
                            Toast.makeText(ViewMoreActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
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
                Intent loginIntent = new Intent(ViewMoreActivity.this, LoginActivity.class);
                Util.check_for_subscription = 0;
                startActivity(loginIntent);
                changeSortPopUp.dismiss();
                break;
            case R.id.register:
                Intent registerIntent = new Intent(ViewMoreActivity.this, RegisterActivity.class);
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
                    //code start: country code and language code added mantish id# 17373 @author :subhadarshani
                    languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    languageListInputModel.setLangCode(Default_Language);
                    //code  end
                    GetLanguageListAsynTask asynGetLanguageList = new GetLanguageListAsynTask(languageListInputModel, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
                    asynGetLanguageList.executeOnExecutor(threadPoolExecutor);
                }
                changeSortPopUp.dismiss();
                break;
            case R.id.profile:
                Intent profileIntent = new Intent(ViewMoreActivity.this, ProfileActivity.class);
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
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ViewMoreActivity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(languagePreference.getTextofLanguage(SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING));
        dlgAlert.setTitle("");

        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                // dialog.cancel();
                if (NetworkStatus.getInstance().isConnected(ViewMoreActivity.this)) {
                    LogoutInput logoutInput = new LogoutInput();
                    logoutInput.setAuthToken(authTokenStr);
                    String loginHistoryIdStr = preferenceManager.getLoginHistIdFromPref();
                    logoutInput.setLogin_history_id(loginHistoryIdStr);
                    logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, ViewMoreActivity.this, ViewMoreActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ViewMoreActivity.this));
                    asynLogoutDetails.executeOnExecutor(threadPoolExecutor);


                    dialog.dismiss();
                } else {
                    Toast.makeText(ViewMoreActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
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
        dlgAlert.setCancelable(false);
        dlgAlert.create().show();
    }

    private void initLayouts(View layout) {
        linearLayout = new LinearLayout[option_menu_id.length];
        for (int i = 0; i < option_menu_id.length; i++) {
            linearLayout[i] = (LinearLayout) layout.findViewById(option_menu_id[i]);
            setLanguageToTextViews(linearLayout[i], i);

        }
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

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewMoreActivity.this, R.style.MyAlertDialogStyle);
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

        languageCustomAdapter = new LanguageCustomAdapter(ViewMoreActivity.this, languageModel, null);
        recyclerView.setAdapter(languageCustomAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener1(ViewMoreActivity.this, recyclerView, new ClickListener1() {
            @Override
            public void onClick(View view, int position) {
                Util.itemclicked = true;

                languageModel.get(position).setSelected(true);


                if (prevPosition != position) {
                    languageModel.get(prevPosition).setSelected(false);
                    prevPosition = position;
                }

                Default_Language = languageModel.get(position).getLanguageId();
                languagePreference.setLanguageSharedPrefernce(SELECTED_LANGUAGE_CODE, languageModel.get(position).getLanguageId());
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
                languageListInputModel.setLangCode(Default_Language);
                languageListInputModel.setAuthToken(authTokenStr);
                languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                GetTranslateLanguageAsync asynGetTransalatedLanguage = new GetTranslateLanguageAsync(languageListInputModel, ViewMoreActivity.this, ViewMoreActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(ViewMoreActivity.this));
                asynGetTransalatedLanguage.executeOnExecutor(threadPoolExecutor);
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


    public interface ClickListener1 {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    @Override
    public void onGetTranslateLanguagePreExecuteStarted() {
        progressBarHandler = new ProgressBarHandler(ViewMoreActivity.this);
        progressBarHandler.show();
    }

    @Override
    public void onGetTranslateLanguagePostExecuteCompleted(String jsonResponse, int status) {
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
        pDialog = new ProgressBarHandler(ViewMoreActivity.this);
        pDialog.show();
    }

    @Override
    public void onGetGenreListPostExecuteCompleted(ArrayList<GenreListOutput> genreListOutput, ArrayList<SortByModel> sortByModelArrayList, int code, String status) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }

        languageCustomAdapter.notifyDataSetChanged();
        final Intent detailsIntent = new Intent(ViewMoreActivity.this, ViewMoreActivity.class);
        detailsIntent.putExtra("SectionId", sectionId);
        detailsIntent.putExtra("sectionName", sectionName);
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
        GetGenreListAsynctask asynGetGenreList = new GetGenreListAsynctask(genreListInput, ViewMoreActivity.this, ViewMoreActivity.this, false, new DataController(ViewMoreActivity.this));
        asynGetGenreList.execute();
    }


    public void calculateImageOrientation(String Url) {
        new GetImageSize(ViewMoreActivity.this, Url, new GetImageSize.ImageSizeInterface() {
            @Override
            public void onImageLoaded(int width, int height) {
                videoHeight = height;
                videoWidth = width;
                try {
                    renderUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onImageLoadFailed(int width, int height) {

            }
        });
    }

    /*
    To set id to back button in Action Bar
     */
    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.back);
            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                t.setId(R.id.page_title_viewall);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initAudioCustomMiniController() {
        minicontroller = findViewById(R.id.bottomSheetLayout);
        //activityAudioPlayer = findViewById(R.id.activity_audio_player);
        seekbar_botomSht = findViewById(R.id.miniController_seekbar);
        seekbar_botomSht.setPadding(0, 0, 0, 0);
        albumArt_player = findViewById(R.id.miniControl_play);
        open_bottomSheet_controller = findViewById(R.id.details);
        song_p_name = findViewById(R.id.song_p_name);
        totalduration = (TextView) findViewById(R.id.totalduration);
        artist_p_name = findViewById(R.id.song_p_Genre);
        equalizer = findViewById(R.id.equalizer);
        background_player = findViewById(R.id.background_player);
        player_close = findViewById(R.id.player_close);
        songname_player = findViewById(R.id.songname_player);
        player_image_main = findViewById(R.id.player_image_main);
        recylerview_progress = findViewById(R.id.progressBar);
        squizeViewPager = findViewById(R.id.viewPager1);
        curent_duration = findViewById(R.id.curent_duration);
        player_play_ic = findViewById(R.id.player_play_ic);
        player_next_icc = findViewById(R.id.player_next_icc);
        player_prev_ic = findViewById(R.id.player_prev_ic);
        total_duration = findViewById(R.id.total_duration);
        seekBar = findViewById(R.id.Progress_music_sliderpanel);
        seekBar.setPadding(0, 0, 0, 0);
        bottomSheet = findViewById(R.id.bottomSheetLayout);
        musicProgress = findViewById(R.id.progressBar);
        LinearLayout miniControllerLayout = findViewById(R.id.miniControl);
        clearQueue = findViewById(R.id.ClearQueueButton);
        audioCustomMiniController = new AudioCustomMiniController(ViewMoreActivity.this, "", getSupportFragmentManager());
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
            // Set margin to parent layout if minicontroller is visible
            Util.setMargin(minicontroller, gridView, ViewMoreActivity.this);

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
                Intent stopServiceIntent = new Intent(ViewMoreActivity.this, MusicService.class);
                stopService(stopServiceIntent);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (song_status.equals("next")) {
                        audioCustomMiniController.Next();
                    }
                    if (song_status.contains("@@@@@")) {

                        final String[] data = song_status.split("@@@@@");
                        Log.v("MUVI", "@@@@@");
                        long currentDuration = Long.parseLong(data[0]);
                        long totalDuration = Long.parseLong(data[1]);

                        /*@Author Bishal
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == CHILD_CONTENT_CLICK && resultCode == RESULT_OK) {
            getVideoInfo();
        } else if (requestCode == PAYMENT_REQUESTCODE && resultCode == RESULT_OK) {
            getVideoInfo();
        }

    }

    private void childclickItem(Context mContext, GridItem gridItem) {

        if (gridItem.getIs_media_published().equals("0")) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
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
            AlertDialog alertDialog = dlgAlert.create();
            alertDialog.show();
            TextView tv = (TextView) alertDialog.findViewById(android.R.id.message);
            Configuration configuration = mContext.getResources().getConfiguration();
            if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                if (tv != null) {
                    tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

                }
            }
            return;
        }

        if (gridItem.getIsConverted() == 0) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
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
            alertDialog.show();
            TextView tv = (TextView) alertDialog.findViewById(android.R.id.message);
            Configuration configuration = mContext.getResources().getConfiguration();
            if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                if (tv != null) {
                    tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

                }
            }
            return;
        }

        playerModel = new Player();
        playerModel.setStreamUniqueId(gridItem.getMovieStreamUniqueId());
        playerModel.setMovieUniqueId(gridItem.getMovieUniqueId());
        playerModel.setUserId(preferenceManager.getUseridFromPref());
        playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
        playerModel.setAuthTokenStr(authTokenStr.trim());
        playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH);
        playerModel.setEpisode_id(gridItem.getMovieStreamUniqueId());
        playerModel.setVideoTitle(gridItem.getName());
        playerModel.setParent_name(gridItem.getParent_title());
        playerModel.setVideoStory(gridItem.getStory());
        playerModel.setVideoDuration(gridItem.getVideo_duration());
        playerModel.setContentTypesId(Integer.parseInt(gridItem.getVideoTypeId()));
        playerModel.setPosterImageId(gridItem.getPoster_url());

        playerModel.setSeries_number(gridItem.getSeason_no());
        playerModel.setEpisode_number(gridItem.getEpisode_no());


        DataModel dbModel = new DataModel();
        dbModel.setIsConverted(gridItem.getIsConverted());
        dbModel.setMovieUniqueId(gridItem.getMovieUniqueId());
        dbModel.setStreamUniqueId(gridItem.getMovieStreamUniqueId());
        dbModel.setVideoTitle(gridItem.getParent_title());
        dbModel.setEpisode_title(gridItem.getTitle());
        dbModel.setVideoStory(gridItem.getStory());
        dbModel.setVideoDuration(gridItem.getVideo_duration());
        dbModel.setEpisode_id(gridItem.getMovieStreamUniqueId());
        dbModel.setSeason_id(gridItem.getSeason_no());
        dbModel.setPurchase_type("episode");
        dbModel.setPosterImageId(gridItem.getPoster_url());
        dbModel.setContentTypesId(Integer.parseInt(gridItem.getVideoTypeId()));
        dbModel.setEpisode_series_no(gridItem.getSeason_no());
        dbModel.setEpisode_no(gridItem.getEpisode_no());

        Util.dataModel = dbModel;

        AutoPlayUtil.callAutoPlayAPI(gridItem.getMovieStreamUniqueId(), ViewMoreActivity.this, playerModel);

        if (preferenceManager != null) {
            loggedInIdStr = preferenceManager.getUseridFromPref();
            guestUserld = preferenceManager.getGuestUseridFromPref();
        }

        if ((preferenceManager.getLoginFeatureFromPref() == 1)) {
            if (loggedInIdStr == null && guestUserld == null) {
                Util.check_for_subscription = 1;
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("PlayerModel", playerModel);
                intent.putExtra("is_a_guest_user", "is_a_guest_user");
                startActivityForResult(intent, CHILD_CONTENT_CLICK);
            } else {
                if (NetworkStatus.getInstance().isConnected(mContext)) {
                    getVideoInfo();
                } else {
                    Toast.makeText(mContext, languagePreference.getTextofLanguage(player.utils.Util.NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            if (NetworkStatus.getInstance().isConnected(mContext)) {
                getVideoInfo();
            } else {
                Toast.makeText(mContext, languagePreference.getTextofLanguage(player.utils.Util.NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
            }
        }
    }


    public void getVideoInfo() {
        GetVideoDetailsInput getVideoDetailsInput = new GetVideoDetailsInput();
        getVideoDetailsInput.setAuthToken(authTokenStr);
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
        VideoDetailsAsynctask asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, ViewMoreActivity.this, ViewMoreActivity.this);
        asynLoadVideoUrls.execute(); //threadPoolExecutor
    }

}
