package com.home.vod.ui.activity;


import static com.home.vod.preferences.LanguagePreference.APP_SELECT_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_APP_SELECT_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NOTIFICATION_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PROFILE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PURCHASE_HISTORY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.LOGIN;
import static com.home.vod.preferences.LanguagePreference.LOGOUT;
import static com.home.vod.preferences.LanguagePreference.LOGOUT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.NOTIFICATION_TITLE;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.OOPS;
import static com.home.vod.preferences.LanguagePreference.PROFILE;
import static com.home.vod.preferences.LanguagePreference.PURCHASE_HISTORY;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.TRY_AGAIN;
import static com.home.vod.service.MusicService.mediaPlayer;
import static com.home.vod.ui.fragment.HomeFragment.swipeRefreshLayout;
import static com.home.vod.util.Constant.PERMALINK_INTENT_KEY;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.Util.RC_FORCE_UPDATE;
import static com.home.vod.util.Util.app_should_refreshed;
import static com.home.vod.util.Util.forceUpdateDialog;
import static com.home.vod.util.Util.languageModel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.home.apisdk.apiController.GetAppMenuAsync;
import com.home.apisdk.apiController.GetCategoryListAsyncTask;
import com.home.apisdk.apiController.GetGenreListAsynctask;
import com.home.apisdk.apiController.GetLanguageListAsynTask;
import com.home.apisdk.apiController.GetTranslateLanguageAsync;
import com.home.apisdk.apiController.HeaderConstants;
import com.home.apisdk.apiController.LogoutAsynctask;
import com.home.apisdk.apiModel.CategoryOutputModel;
import com.home.apisdk.apiModel.GenreListInput;
import com.home.apisdk.apiModel.GenreListOutput;
import com.home.apisdk.apiModel.HomeFeaturePageBannerModel;
import com.home.apisdk.apiModel.LanguageListInputModel;
import com.home.apisdk.apiModel.LanguageListOutputModel;
import com.home.apisdk.apiModel.SortByModel;
import com.home.vod.ObservableObject;
import com.home.vod.R;
import com.home.vod.handlers.EpisodeListOptionMenuHandler;
import com.home.vod.handlers.FooterMenuHandler;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.model.LanguageModel;
import com.home.vod.model.NavDrawerItem;
import com.home.vod.model.QueueModel;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.service.MusicService;
import com.home.vod.ui.MediaHelper;
import com.home.vod.ui.adapter.LanguageCustomAdapter;
import com.home.vod.ui.fragment.HomeFragment;
import com.home.vod.ui.fragment.NavigationDrawerFragment;
import com.home.vod.ui.widgets.AudioCustomMiniController;
import com.home.vod.ui.widgets.CustomTextSliderView;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.Constant;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks,
        LogoutAsynctask.LogoutListener,
        CustomTextSliderView.PlaybuttonclickListener,
        GetLanguageListAsynTask.GetLanguageListListener,
        GetTranslateLanguageAsync.GetTranslateLanguageInfoListener, GetGenreListAsynctask.GenreListListener,
        Observer, MediaHelper, GetCategoryListAsyncTask.GetCategoryListListener {


    public static TextView badge_text;
    public static boolean chromecast_completed = false, doSilently = false;
    public static ToolbarTitleHandler toolbarTitleHandler;
    public static TextView noti;
    public static FrameLayout noti_layout;
    public static int vertical = 0;
    public static ProgressBarHandler progressBarHandler;
    public static ProgressBarHandler internetSpeedDialog;

    private NavigationDrawerFragment mNavigationDrawerFragment;
    public DrawerLayout mDrawerLayout;
    private TextView text;
    private Timer timerAppConfig;
    private Menu menu;
    public Toolbar mToolbar;
    private RelativeLayout noInternetLayout;
    private TextView noInternetTextView;
    private AlertDialog alert;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private TextView oopsTextView;
    private Button btnRetry;
    private LinearLayout mini_controller_layout;
    public ImageView toolbarimage;
    private ActionBarDrawerToggle toggle;
    private  CoordinatorLayout minicontroller;
    private  SeekBar seekbar_botomSht, seekBar;
    private  ImageView albumArt_player;
    private  LinearLayout open_bottomSheet_controller, miniControllerLayout;
    private  TextView song_p_name, totalduration, artist_p_name, songname_player, curent_duration, total_duration;
    private ImageView equalizer, background_player, player_close, player_image_main, player_play_ic, player_next_icc, player_prev_ic;
    private  MediaRouteButton mediaRouteButton;
    private  ProgressBar recylerview_progress, musicProgress;
    private ViewPager squizeViewPager;
    private  View bottomSheet;
    public BottomSheetBehavior mBottomSheetBehavior;

    int adaptorPosition;
    public static AudioCustomMiniController audioCustomMiniController;
    private Button clearQueue;

    private GetTranslateLanguageAsync translateLanguageTask;
    private GetGenreListAsynctask genreListTask;
    private GetAppMenuAsync asynLoadMenuItems = null;

    private LanguagePreference languagePreference;
    private FooterMenuHandler fooerMenuHandler;
    private EpisodeListOptionMenuHandler episodeListOptionMenuHandler;
    private LanguageCustomAdapter languageCustomAdapter;
    private ProgressBarHandler pDialog = null;
    private PreferenceManager preferenceManager;
    private FeatureHandler featureHandler;

    public CharSequence mTitle;
    private int check = 0;
    private String Default_Language = "";
    private String imageUrlStr;
    public static String internetSpeed = "0";
    private String email, id;
    private int isLogin = 0;
    private int prevPosition = 0;
    private String Previous_Selected_Language = "";
    private boolean[] visibility;
    private String[] lang;
    private int Count = 0;
    private boolean getLink;

    private ArrayList<CategoryOutputModel> categoryOutputModelArrayList;
    public ArrayList<NavDrawerItem> menuList = new ArrayList<>();
    public ArrayList<LanguageModel> languageModels = new ArrayList<>();
    private int[] option_menu_id = {R.id.login, R.id.register, R.id.language_popup, R.id.profile, R.id.purchase, R.id.logout, R.id.notification};
    private LinearLayout[] linearLayout;

    private int corePoolSize = 60;
    private int maximumPoolSize = 200;
    private int keepAliveTime = 10;
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    private Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

    /*
     * @Desc: This override method is used to call before the onCreate()
     *       which help the resources to update.
     * */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleLanguageHelper.onAttach(base));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        View navigation_drawer = findViewById(R.id.navigation_drawer);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);
        toolbarimage.setVisibility(View.GONE);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        getWindow().setBackgroundDrawableResource(R.drawable.app_background);
        Util.drawer_collapse_expand_imageview.clear();
        ObservableObject.getInstance().addObserver(this);

        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.start, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        /**
         * No need to refresh the app in onResume, if it is already refreshed.
         */
        if (app_should_refreshed) {
            app_should_refreshed = false;
        }


        /*
         * @desc:The below line is for getting content sharing info here & navigating page accordingly */

        try {
            if (getIntent().getStringExtra("share_permalink") != null && getIntent().getStringExtra("share_content_types_id") != null) {

                if (getIntent().getStringExtra("share_content_types_id").equalsIgnoreCase("1") ||
                        getIntent().getStringExtra("share_content_types_id").equalsIgnoreCase("2") ||
                        getIntent().getStringExtra("share_content_types_id").equalsIgnoreCase("4")) {
                    //single part
                    final Intent detailsIntent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                    detailsIntent.putExtra(PERMALINK_INTENT_KEY, getIntent().getStringExtra("share_permalink"));
                    detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(detailsIntent);
                } else if (getIntent().getStringExtra("share_content_types_id").equalsIgnoreCase("3")) {
                    // multipart video
                    final Intent detailsIntent = new Intent(MainActivity.this, ShowWithEpisodesActivity.class);
                    detailsIntent.putExtra(PERMALINK_INTENT_KEY, getIntent().getStringExtra("share_permalink"));
                    detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(detailsIntent);
                } else if (getIntent().getStringExtra("share_content_types_id").equalsIgnoreCase("5")
                        || getIntent().getStringExtra("share_content_types_id").equalsIgnoreCase("6")) {
                    // multipart/singlepart audio
                    final Intent detailsIntent = new Intent(MainActivity.this, AudioContentDetailsActivity.class);
                    detailsIntent.putExtra("PERMALINK", getIntent().getStringExtra("share_permalink"));
                    detailsIntent.putExtra("CONTENT_TYPE", getIntent().getStringExtra("share_content_types_id"));
                    detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(detailsIntent);
                }
            } else {
                getLink = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        /*Author:chitra
         * @desc:The function will execute for force update dialog */
        forceUpdateDialog(MainActivity.this);

        /*
         *
         * @Desc: This is called for updating those resouces which are not able to recreate the activity but
         *        we need the resorce to be updated.
         **/
        LocaleLanguageHelper.onAttach(MainActivity.this);

        if (menuList != null && menuList.size() > 0) {
            menuList.clear();
        }

        fooerMenuHandler = new FooterMenuHandler(this);
        languagePreference = LanguagePreference.getLanguagePreference(this);

        episodeListOptionMenuHandler = new EpisodeListOptionMenuHandler(this);
        featureHandler = FeatureHandler.getFeaturePreference(this);

        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        setIdToActionBarBackButton(mToolbar);


        Default_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);


        initMinicontroller();

        preferenceManager = PreferenceManager.getPreferenceManager(this);
        isLogin = preferenceManager.getLoginFeatureFromPref();

        noInternetLayout = findViewById(R.id.noInternet);
        noInternetTextView = findViewById(R.id.noInternetTextView);
        oopsTextView = findViewById(R.id.oopsTextView); // Added by Debashish
        btnRetry = findViewById(R.id.btnRetry); // Added by Debashish

        /**
         * @description : two keys added (1) Try Again ! (2) Oops !
         */

        btnRetry.setText("          " + languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN) + "       ");
        oopsTextView.setText(languagePreference.getTextofLanguage(OOPS, DEFAULT_OOPS));

        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_NO_DATA, DEFAULT_NO_INTERNET_NO_DATA));
        noInternetLayout.setVisibility(View.GONE);


        setUpNavigationDrawer();
        getCategoryList();

        /*
         * @desc: auto-initiated textTranslation api call
         * */
        // Call For Language Translation.
        LanguageListInputModel languageListInputModel = new LanguageListInputModel();
        languageListInputModel.setAuthToken(authTokenStr);
        languageListInputModel.setLangCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
        doSilently = true;
        translateLanguageTask = new GetTranslateLanguageAsync(languageListInputModel, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(MainActivity.this));
        translateLanguageTask.executeOnExecutor(threadPoolExecutor);
        /*
         *
         * @desc: API call for updating FCM token to the server.
         * */




        LanguageListInputModel languageListInputModell = new LanguageListInputModel();
        languageListInputModell.setAuthToken(authTokenStr);
        languageListInputModell.setCountryCode(preferenceManager.getCountryCodeFromPref());
        languageListInputModell.setLangCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));

        GetLanguageListAsynTask asynGetLanguageList = new GetLanguageListAsynTask(languageListInputModell, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
        asynGetLanguageList.executeOnExecutor(threadPoolExecutor);

        /**
         * @author : Debashish
         * @description : onclicking Retry button
         */
        btnRetry.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            reload();
        });


        /**
         * @description : Show minicontroller if media is playing otherwise close it
         */
        if (mediaPlayer != null && mediaPlayer.getPlayWhenReady()) {
            preferenceManager.setSongStatustoPref("playing");
        } else {
            preferenceManager.setSongStatustoPref("close");

        }
    }


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


    public void reload() {
        try {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpNavigationDrawer() {
        if (NetworkStatus.getInstance().isConnected(MainActivity.this)) {
            mNavigationDrawerFragment = (NavigationDrawerFragment)
                    getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
            mTitle = getTitle();


            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    findViewById(R.id.drawer_layout));

            mDrawerLayout = findViewById(R.id.drawer_layout);


        } else {
            noInternetLayout.setVisibility(View.VISIBLE);
            DrawerLayout dl = findViewById(R.id.drawer_layout);
            dl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
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
                DEFAULT_LOGOUT,
                DEFAULT_NOTIFICATION_TITLE};
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
                final Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                searchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(searchIntent);
                // Not implemented here
                return false;

            default:
                break;
        }

        return false;
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


    @Override
    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(CLOSE_NOTIFICATION);
            unregisterReceiver(SongStatusReciver);
            unregisterReceiver(PLAYER_DETAILS);
            unregisterReceiver(SONG_STATUS_NEXT);
            unregisterReceiver(SONG_STATUS_PREVIOUS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            player.utils.Util.playerView = findViewById(R.id.player_screen);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (app_should_refreshed) {
            app_should_refreshed = false;
            Intent mIntent = new Intent(MainActivity.this, MainActivity.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(mIntent);
            finish();
            return;
        }


        /**
         *
         * desc: Intialize view for audio minicontroller*/
        initAudioCustomMiniController();
        try {
            registerReceiver(SongStatusReciver, new IntentFilter(Constant.SONG_STATUS));
            registerReceiver(CLOSE_NOTIFICATION, new IntentFilter(Constant.CLOSE_NOTIFICATION));
            registerReceiver(SONG_STATUS_NEXT, new IntentFilter(Constant.SONG_STATUS_NEXT));
            registerReceiver(SONG_STATUS_PREVIOUS, new IntentFilter(Constant.SONG_STATUS_PREVIOUS));
            registerReceiver(PLAYER_DETAILS, new IntentFilter("PLAYER_DETAILS"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        invalidateOptionsMenu();

        if (preferenceManager.getLanguageChangeStatus().equals("1")) {

            Intent mIntent = new Intent(MainActivity.this, MainActivity.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(mIntent);
            finish();

            preferenceManager.setLanguageChangeStatus("0");
        }


        invalidateOptionsMenu();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        String guestUserld = null;

        if (preferenceManager != null) {
            guestUserld = preferenceManager.getGuestUseridFromPref();
        }

        if (guestUserld != null) {
            preferenceManager.setGuestUserIdToPref(guestUserld);
        }


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        check = position;

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (audioCustomMiniController.bootomsheet_open) {
            audioCustomMiniController.collapse_buttomsheet();
            return;
        }

        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
            return;
        }

        if (asynLoadMenuItems != null) {
            asynLoadMenuItems.cancel(true);
        }



        super.onBackPressed();
        preferenceManager.setFRAGMENTS_CHANGED("home");
    }


    @Override
    public void onLogoutPreExecuteStarted() {
        pDialog = new ProgressBarHandler(MainActivity.this);
        pDialog.show();
    }

    @Override
    public void onLogoutPostExecuteCompleted(int code, String status, String message) {
        Util.check_for_subscription = 0;
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }
        if (code != 200) {
            Toast.makeText(MainActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code == 0) {
            Toast.makeText(MainActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code > 0) {
            if (code == 200) {
                preferenceManager.clearLoginPref();


                if ((featureHandler.getFeatureStatus(FeatureHandler.SIGNUP_STEP, FeatureHandler.DEFAULT_SIGNUP_STEP))) {
                    final Intent startIntent = new Intent(MainActivity.this, LoginActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(MainActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();

                            finish();

                        }
                    });
                } else {
                    final Intent startIntent = new Intent(MainActivity.this, MainActivity.class);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(startIntent);
                            Toast.makeText(MainActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();

                            finish();

                        }
                    });
                }


            } else {
                Toast.makeText(MainActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();
            }
        }
    }


    public void ShowLanguagePopup() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogStyle);
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

        languageCustomAdapter = new LanguageCustomAdapter(MainActivity.this, languageModel, null);

        recyclerView.setAdapter(languageCustomAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener1(MainActivity.this, recyclerView, new ClickListener1() {
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

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();


                if (!Previous_Selected_Language.equals(Default_Language)) {


                    LanguageListInputModel languageListInputModel = new LanguageListInputModel();
                    languageListInputModel.setLangCode(Default_Language);
                    languageListInputModel.setAuthToken(authTokenStr);
                    //code start: country code added mantish id# 17373
                    languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());

                    stopAutoTranslation();
                    GetTranslateLanguageAsync asynGetTransalatedLanguage = new GetTranslateLanguageAsync(languageListInputModel, MainActivity.this, MainActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(MainActivity.this));
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
    public void onGetLanguageListPreExecuteStarted() {
        pDialog = new ProgressBarHandler(MainActivity.this);
        pDialog.show();
    }

    @Override
    public void onGetLanguageListPostExecuteCompleted(ArrayList<LanguageListOutputModel> languageListOutputArray, int status, String message, String defaultLanguage) {

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }


        for (int i = 0; i < languageListOutputArray.size(); i++) {
            String language_id = languageListOutputArray.get(i).getLanguageCode();
            String language_name = languageListOutputArray.get(i).getLanguageName();


            LanguageModel languageModel = new LanguageModel();
            languageModel.setLanguageId(language_id);
            languageModel.setLanguageName(language_name);

            if (Default_Language.equalsIgnoreCase(language_id)) {
                preferenceManager.setSelectedLanguageTexttoPref(languageListOutputArray.get(i).getLanguageName());
            }
        }
    }

    /*
     * @authorSubhadarshani
     * Description: This method is to call the API "getCategoryList" for getting categories.
     * **/
    public void getCategoryList() {
        LinkedHashMap<String, String> inputMap = new LinkedHashMap<>();
        inputMap.put(HeaderConstants.AUTH_TOKEN, authTokenStr);
        inputMap.put(HeaderConstants.LANG_CODE, Default_Language);
        inputMap.put(HeaderConstants.COUNTRY, preferenceManager.getCountryCodeFromPref());
        GetCategoryListAsyncTask getCategoryListAsyncTask = new GetCategoryListAsyncTask(this, MainActivity.this);
        getCategoryListAsyncTask.execute(inputMap);
    }

    @Override
    public void onGetCategoryListPreExecuteStarted() {

    }

    @Override
    public void onGetCategoryListPostExecuteCompleted(String response, int status) {
        if (status == 200) {
            categoryOutputModelArrayList = new ArrayList<>();
            if (response != null) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("category_list");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject categoryObj = jsonArray.getJSONObject(i);
                        CategoryOutputModel categoryOutputModel = new CategoryOutputModel();
                        categoryOutputModel.setCategoryId(categoryObj.getString("category_id"));
                        categoryOutputModel.setCategoryName(categoryObj.getString("category_name"));
                        categoryOutputModel.setCategoryImgUrl(categoryObj.getString("category_img_url"));
                        categoryOutputModel.setPermalink(categoryObj.getString("permalink"));
                        categoryOutputModelArrayList.add(categoryOutputModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
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

        if (!doSilently) {
            pDialog = new ProgressBarHandler(MainActivity.this);
            pDialog.show();
        }
    }

    @Override
    public void onGetTranslateLanguagePostExecuteCompleted(String jsonResponse, int status) {

        if (pDialog != null && pDialog.isShowing() && !doSilently) {
            pDialog.hide();
            pDialog = null;

        }

        if (status > 0 && status == 200) {

            try {

                Util.parseLanguage(languagePreference, jsonResponse, Default_Language);
                genre_call(Default_Language);


            } catch (Exception e) {
                e.printStackTrace();
                noInternetLayout.setVisibility(View.GONE);
            }
            // Call For Other Methods.


        } else {
            noInternetLayout.setVisibility(View.GONE);
        }

    }




    //Added by Abhishek For Language Translation for Filter Activity

    @Override
    public void onGetGenreListPreExecuteStarted() {

        if (!doSilently) {
            pDialog = new ProgressBarHandler(MainActivity.this);
            pDialog.show();
        }
    }

    @Override
    public void onGetGenreListPostExecuteCompleted(ArrayList<GenreListOutput> genreListOutput, ArrayList<SortByModel> sortByModelArrayList, int code, String status) {

        if (pDialog != null && pDialog.isShowing() && !doSilently) {
            pDialog.hide();
            pDialog = null;

        }

        if (doSilently) {
            doSilently = false;
        } else {
            if (languageCustomAdapter != null) languageCustomAdapter.notifyDataSetChanged();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }

    }

    private String getTag(int value) {
        return getResources().getString(value);
    }


    public void genre_call(String default_Language) {

        GenreListInput genreListInput = new GenreListInput();
        genreListInput.setAuthToken(authTokenStr);
        genreListInput.setLang_code(default_Language);


       {
            genreListInput.setIs_specific("0"); // for others
        }

        genreListInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        genreListTask = new GetGenreListAsynctask(genreListInput, MainActivity.this, MainActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(MainActivity.this));
        genreListTask.executeOnExecutor(threadPoolExecutor);

    }

    /*
     * @desc: To stop auto-initiated textTranslation api
     * */
    private void stopAutoTranslation() {
        try {
            if (translateLanguageTask != null) {
                translateLanguageTask.cancel(true);
            }
            if (genreListTask != null) {
                genreListTask.cancel(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        doSilently = false;
    }

    /*
    Kushal - To set id to back button in Action Bar
     */
    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.menu);

            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                t.setId(R.id.page_title);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_FORCE_UPDATE) {
            finish();
            startActivity(getIntent());
        }

    }

    @Override
    public void playbuttonclick(HomeFeaturePageBannerModel homeFeaturePageBannerModel) {
    }

    /*
     * desc:Intialize audio minicontroller
     * */
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
        mediaRouteButton = findViewById(R.id.media_route_button);
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
        audioCustomMiniController = new AudioCustomMiniController(MainActivity.this, "", getSupportFragmentManager());
        audioCustomMiniController.init(minicontroller, seekbar_botomSht, albumArt_player, open_bottomSheet_controller, song_p_name, artist_p_name, equalizer, background_player, player_close
                , songname_player,  player_image_main, recylerview_progress, squizeViewPager, curent_duration, player_play_ic, player_next_icc, player_prev_ic, total_duration, seekBar, bottomSheet, mToolbar, miniControllerLayout, musicProgress, clearQueue);
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
            //
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(getTag(R.string.HOME_FRAGMENT_TAG));
            if (fragment != null && fragment instanceof HomeFragment) {
                setMargin(minicontroller, MainActivity.this);
            } else {
                Util.setMargin(minicontroller, Util.recyclerView, MainActivity.this);
            }

            Util.minicontroller = minicontroller;

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
                Intent stopServiceIntent = new Intent(MainActivity.this, MusicService.class);
                stopService(stopServiceIntent);
            }
            runOnUiThread(() -> {
                if (song_status.equals("next")) {
                    audioCustomMiniController.Next();
                }
                if (song_status.contains("@@@@@")) {

                    final String[] data = song_status.split("@@@@@");
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
            });
        }
    };




    @Override
    public void update(Observable observable, Object data) {
        Intent intent = (Intent) data;
        if (intent.hasExtra("IntentFor")) {
            //updating UI for Audio Play

            audioCustomMiniController.playerDetails(intent);

        }
    }

    private BroadcastReceiver PLAYER_DETAILS = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            audioCustomMiniController.playerDetails(intent);
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

    /*
     * @desc:this method is used to set margin to the parent layout if audio minicontroller is visible*/
    public void setMargin(CoordinatorLayout minicontroller, Context context) {
        if (minicontroller.getVisibility() == View.VISIBLE) {
            try {
                int bottomMargin = (int) (context.getResources().getDimension(R.dimen.list_bottom_margin) / context.getResources().getDisplayMetrics().density);
                ViewGroup.MarginLayoutParams marginLayoutParams =
                        (ViewGroup.MarginLayoutParams) swipeRefreshLayout.getLayoutParams();
                marginLayoutParams.setMargins(0, 6, 0, bottomMargin);
                swipeRefreshLayout.setLayoutParams(marginLayoutParams);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ViewGroup.MarginLayoutParams params =
                        (ViewGroup.MarginLayoutParams) swipeRefreshLayout.getLayoutParams();
                params.setMargins(0, 6, 0, 0);
                swipeRefreshLayout.setLayoutParams(params);
            } catch (Exception e) {
                e.printStackTrace();
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


}


