package com.home.vod.ui.activity;

import static android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_XLARGE;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_RESULT_FOUND_REFINE_YOUR_SEARCH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SEARCH_PLACEHOLDER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_SEARCH_PLACEHOLDER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.NO_RESULT_FOUND_REFINE_YOUR_SEARCH;
import static com.home.vod.preferences.LanguagePreference.OOPS;
import static com.home.vod.preferences.LanguagePreference.SEARCH_PLACEHOLDER;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.TEXT_SEARCH_PLACEHOLDER;
import static com.home.vod.preferences.LanguagePreference.TRY_AGAIN;
import static com.home.vod.util.Constant.PERMALINK_INTENT_KEY;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.GridLayout.getGridLayout;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.home.apisdk.apiController.GetIpAddressAsynTask;
import com.home.apisdk.apiController.SearchDataAsynTask;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
import com.home.apisdk.apiModel.GetVideoDetailsInput;
import com.home.apisdk.apiModel.Search_Data_input;
import com.home.apisdk.apiModel.Search_Data_otput;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.vod.BuildConfig;
import com.home.vod.R;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.model.DataModel;
import com.home.vod.model.GridItem;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.adapter.VideoFilterAdapter;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.AutoPlayUtil;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.SearchProgressHandler;
import com.home.vod.util.Util;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import player.activity.AdPlayerActivity;
import player.activity.Player;

/**
 * Created by user on 28-06-2015.
 */

public class SearchActivity extends AppCompatActivity implements SearchDataAsynTask.SearchDataListener,
        GetIpAddressAsynTask.IpAddressListener, VideoDetailsAsynctask.VideoDetailsListener {

    public final int CHILD_CONTENT_CLICK = 1011;
    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";
    public static final int PAYMENT_REQUESTCODE = 8889;

    public ToolbarTitleHandler toolbarTitleHandler;
    private ProgressBarHandler videoPDialog;
    private SearchProgressHandler searchProgressBarHandler;
    private GridItem itemToPlay;
    private LanguagePreference languagePreference;
    private SearchDataAsynTask searchDataAsynTask;
    private FeatureHandler featureHandler;

    public Toolbar mActionBarToolbar;
    public ImageView toolbarimage;
    private RelativeLayout noInternetConnectionLayout;
    private  RelativeLayout noDataLayout;

    private boolean scrolling;
    private Toast toast;

    private String videoImageStrToHeight;
    private int videoHeight = 185;
    private int videoWidth = 256;
    boolean firstTime = false;

    //search
    private String searchTextStr;
    private boolean isSearched = false;
    private boolean startSearch = true;
    private SearchView.SearchAutoComplete theTextArea;
    private SearchView searchView;

    private Player playerModel;
    private String loggedInIdStr, guestUserld;
    private String is_livestreamEnabled;
    private String livestream_resume_time;
    private String ipAddres = "";
    private int totalItemCount;



    /*The Data to be posted*/
    int offset = 1;
    int limit = 10;
    int listSize = 0;
    int itemsInServer = 0;

    /*Asynctask on background thread*/
    int corePoolSize = 60;
    int maximumPoolSize = 80;
    int keepAliveTime = 10;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

    //Set Context

    //Adapter for GridView
    private VideoFilterAdapter customGridAdapter;

    //Model for GridView
    private ArrayList<GridItem> itemData = new ArrayList<GridItem>();


    // UI
    private RecyclerView gridView;
    private RelativeLayout footerView;
    private TextView noDataTextView;
    private TextView noInternetTextView;
    private PreferenceManager preferenceManager;
    private boolean isLoading;

    public SearchActivity() {
        // Required empty public constructor

    }

    private int sampleSize = 100;

    /**
     * @description : Retry Button when connection fails and animation on button click
     */

    private Button btnRetry;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private TextView oopsTextView;


    private Configuration config;

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
        setContentView(R.layout.activity_search);

        featureHandler = FeatureHandler.getFeaturePreference(SearchActivity.this);

        languagePreference = LanguagePreference.getLanguagePreference(SearchActivity.this);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);
        mActionBarToolbar = findViewById(R.id.toolbar);

        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        setSupportActionBar(mActionBarToolbar);

        /*
         * @Desc: This for setting up the toolbar back arrow icon change
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            config = getResources().getConfiguration();
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

        GetIpAddressAsynTask asynGetIpAddress = new GetIpAddressAsynTask(SearchActivity.this, SearchActivity.this);
        asynGetIpAddress.execute();

        // Kushal - To set Id to action bar back button
        setIdToActionBarBackButton(mActionBarToolbar);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        searchProgressBarHandler = new SearchProgressHandler(SearchActivity.this);
        gridView = findViewById(R.id.imagesGridView);
        footerView = findViewById(R.id.loadingPanel);
        noInternetConnectionLayout = findViewById(R.id.noInternet);
        noDataLayout = findViewById(R.id.noData);
        noInternetTextView = findViewById(R.id.noInternetTextView);
        noDataTextView = findViewById(R.id.noDataTextView);
        oopsTextView = findViewById(R.id.oopsTextView); // Added by Debashish
        btnRetry = findViewById(R.id.btnRetry); // Added by Debashish
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noDataTextView.setText(languagePreference.getTextofLanguage(NO_RESULT_FOUND_REFINE_YOUR_SEARCH, DEFAULT_NO_RESULT_FOUND_REFINE_YOUR_SEARCH));

        /**
         * @description : two keys added (1) Try Again ! (2) Oops !
         */

        btnRetry.setText("          " + languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN) + "       ");
        oopsTextView.setText(languagePreference.getTextofLanguage(OOPS, DEFAULT_OOPS));
        noInternetConnectionLayout.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.GONE);
        footerView.setVisibility(View.GONE);

        if (!NetworkStatus.getInstance().isConnected(SearchActivity.this)) {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.GONE);
            footerView.setVisibility(View.GONE);
        }

        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT; //this is in pixels
        gridView.setLayoutParams(layoutParams);

        resetData();


        gridView.addOnItemTouchListener(new RecyclerTouchListener1(getApplicationContext(), gridView, new ClickListener1() {
            @Override
            public void onClick(View view, int position) {

                searchView.clearFocus();
                itemToPlay = itemData.get(position);
                String moviePermalink = itemToPlay.getPermalink();
                String movieTypeId = itemToPlay.getVideoTypeId();

                if (movieTypeId != null && movieTypeId != "" && moviePermalink != null && moviePermalink != "") {
                    if ((movieTypeId.equalsIgnoreCase("3"))) {
                        if (moviePermalink.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SearchActivity.this, R.style.MyAlertDialogStyle);
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


                            if (itemToPlay.getIsEpisode().equals("2")) {
                                final Intent detailsIntent = new Intent(SearchActivity.this, SeasonDetailsActivity.class);
                                detailsIntent.putExtra(PERMALINK_INTENT_KEY, moviePermalink);
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        detailsIntent.putExtra("season_permalink", itemToPlay.getSeason_permalink());
                                        detailsIntent.putExtra("parent_permalink", itemToPlay.getPermalink());
                                        startActivity(detailsIntent);
                                    }
                                });
                            }
                            if (itemToPlay.getIsEpisode().equals("1")) {
                                //child content
                                playerModel = new Player();
                                playerModel.setStreamUniqueId(itemToPlay.getMovieStreamUniqueId());
                                playerModel.setMovieUniqueId(itemToPlay.getMovieUniqueId());
                                playerModel.setUserId(preferenceManager.getUseridFromPref());
                                playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
                                playerModel.setAuthTokenStr(authTokenStr.trim());
                                playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH);
                                playerModel.setVideoTitle(itemToPlay.getTitle());
                                playerModel.setParent_name(itemToPlay.getParent_title());
                                playerModel.setVideoStory(itemToPlay.getStory());
                                playerModel.setVideoDuration(itemToPlay.getVideo_duration());
                                playerModel.setContentTypesId(Integer.parseInt(itemToPlay.getVideoTypeId()));
                                playerModel.setPosterImageId(itemToPlay.getPoster_url());

                                playerModel.setSeries_number(itemToPlay.getSeason_no());
                                playerModel.setEpisode_number(itemToPlay.getEpisode_no());


                                DataModel dbModel = new DataModel();
                                dbModel.setIsConverted(itemToPlay.getIsConverted());
                                dbModel.setMovieUniqueId(itemToPlay.getMovieUniqueId());
                                dbModel.setStreamUniqueId(itemToPlay.getMovieStreamUniqueId());
                                dbModel.setVideoTitle(itemToPlay.getParent_title());
                                dbModel.setEpisode_title(itemToPlay.getTitle());
                                dbModel.setVideoStory(itemToPlay.getStory());
                                dbModel.setVideoDuration(itemToPlay.getVideo_duration());
                                dbModel.setEpisode_id(itemToPlay.getMovieStreamUniqueId());
                                dbModel.setSeason_id(itemToPlay.getSeason_no());
                                dbModel.setPurchase_type("episode");
                                dbModel.setPosterImageId(itemToPlay.getPoster_url());
                                dbModel.setContentTypesId(Integer.parseInt(itemToPlay.getVideoTypeId()));
                                dbModel.setEpisode_series_no(itemToPlay.getSeason_no());
                                dbModel.setEpisode_no(itemToPlay.getEpisode_no());

                                Util.check_for_subscription = 1;
                                Util.dataModel = dbModel;
                                AutoPlayUtil.callAutoPlayAPI(itemToPlay.getMovieStreamUniqueId(), SearchActivity.this, playerModel);

                                if (preferenceManager != null) {
                                    loggedInIdStr = preferenceManager.getUseridFromPref();
                                    guestUserld = preferenceManager.getGuestUseridFromPref();
                                }

                                if ((preferenceManager.getLoginFeatureFromPref() == 1)) {
                                    if (loggedInIdStr == null && guestUserld == null) {
                                        Intent intent = new Intent(SearchActivity.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        intent.putExtra("PlayerModel", playerModel);
                                        intent.putExtra("is_a_guest_user", "is_a_guest_user");
                                        startActivityForResult(intent, CHILD_CONTENT_CLICK);
                                    } else {
                                        if (NetworkStatus.getInstance().isConnected(SearchActivity.this)) {
                                            callValidateUserAPI();
                                        } else {
                                            Toast.makeText(SearchActivity.this, languagePreference.getTextofLanguage(player.utils.Util.NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                } else {
                                    if (NetworkStatus.getInstance().isConnected(SearchActivity.this)) {
                                        getVideoInfo();
                                    } else {
                                        Toast.makeText(SearchActivity.this, languagePreference.getTextofLanguage(player.utils.Util.NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                                    }
                                }

                            } else {
                                final Intent detailsIntent = new Intent(SearchActivity.this, ShowWithEpisodesActivity.class);
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

                    // for single clips and movies
                    else if ((movieTypeId.trim().equalsIgnoreCase("1")) || (movieTypeId.trim().equalsIgnoreCase("2"))
                            || (movieTypeId.trim().equalsIgnoreCase("4"))) {
                        final Intent detailsIntent = new Intent(SearchActivity.this, MovieDetailsActivity.class);

                        if (moviePermalink.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SearchActivity.this, R.style.MyAlertDialogStyle);
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
                    } else if (movieTypeId.equals("5") || movieTypeId.equals("6")) {
                        //for audio single and multipart content
                        final Intent detailsIntent = new Intent(SearchActivity.this, AudioContentDetailsActivity.class);
                        detailsIntent.putExtra("PERMALINK", moviePermalink);
                        detailsIntent.putExtra("CONTENT_TYPE", movieTypeId);
                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(detailsIntent);
                    }
                } else {
                    Toast.makeText(SearchActivity.this, languagePreference.getTextofLanguage(NO_CONTENT, DEFAULT_NO_CONTENT), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        gridView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager layoutManager = ((GridLayoutManager) gridView.getLayoutManager());
                int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if ((lastVisibleItemPosition == itemData.size() - 1) && ((itemsInServer > totalItemCount)) &&
                        (itemsInServer > limit) && !scrolling) {

                    scrolling = true;
                    offset += 1;
                    getSearchData();
                }

            }
        });

        /**
         * @description : onclicking Retry button
         */
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(buttonClick);
                if (NetworkStatus.getInstance().isConnected(SearchActivity.this))
                    retryActivity();


            }
        });
    }

    public void retryActivity() {
        try {
            searchTextStr = theTextArea.getText().toString();
            getSearchData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        finish();
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    @Override
    public void onSearchDataPreexecute() {

        if (searchProgressBarHandler != null && searchProgressBarHandler.isShowing()) {
        } else {
            searchProgressBarHandler = new SearchProgressHandler(SearchActivity.this);
        }
        searchProgressBarHandler.show();
        if (listSize == 0) {
            footerView.setVisibility(View.GONE);
        } else {
            footerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSearchDataPostExecuteCompleted(ArrayList<Search_Data_otput> contentListOutputArray, int status, int totalItems, String message) {

        scrolling = false;
        itemsInServer = totalItems;
        totalItemCount = totalItemCount + contentListOutputArray.size();

        String videoGenreStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String videoName = "";
        String videoImageStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String videoPermalinkStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String videoTypeStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String videoTypeIdStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String videoUrlStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String isEpisodeStr = "";
        String movieUniqueIdStr = "";
        String movieStreamUniqueIdStr = "";
        String series_no = "";
        String parent_title = "";
        String episode_title = "";
        String story = "";
        String poster = "";
        String episode_no = "";
        int isConverted = 0;
        int isAPV = 0;
        int isPPV = 0;
        String movieThirdPartyUrl = "";


        try {
            if (searchProgressBarHandler != null && searchProgressBarHandler.isShowing()) {
                searchProgressBarHandler.hide();
            }
        } catch (IllegalArgumentException ex) {

        }

        if (status > 0) {
            if (status == 200) {

                gridView.setVisibility(View.VISIBLE);
                noInternetConnectionLayout.setVisibility(View.GONE);
                noDataLayout.setVisibility(View.GONE);

                if (contentListOutputArray.size() > 0) {

                    gridView.setVisibility(View.VISIBLE);
                    noInternetConnectionLayout.setVisibility(View.GONE);
                    noDataLayout.setVisibility(View.GONE);

                    /**
                     * Adding first content as layout cooser
                     */

                    boolean add_to_list_orientation_checker = true;

                    for (int i = 0; i < contentListOutputArray.size(); i++) {
                        videoImageStr = contentListOutputArray.get(i).getPoster_url();
                        videoName = contentListOutputArray.get(i).getEpisode_title();
                        videoTypeIdStr = contentListOutputArray.get(i).getContent_types_id();
                        videoGenreStr = contentListOutputArray.get(i).getGenre();
                        videoPermalinkStr = contentListOutputArray.get(i).getPermalink();
                        String videoDuration = contentListOutputArray.get(i).getVideo_duration();
                        String season_permalink = contentListOutputArray.get(i).getSeason_permalink();
                        isEpisodeStr = contentListOutputArray.get(i).getIs_episode();
                        isConverted = contentListOutputArray.get(i).getIs_converted();
                        isPPV = contentListOutputArray.get(i).getIs_ppv();
                        isAPV = contentListOutputArray.get(i).getIs_advance();
                        movieStreamUniqueIdStr = contentListOutputArray.get(i).getMovie_stream_uniq_id();
                        movieUniqueIdStr = contentListOutputArray.get(i).getMovie_id();
                        series_no = contentListOutputArray.get(i).getSeries_no();
                        episode_no = contentListOutputArray.get(i).getEpisode_number();
                        parent_title = contentListOutputArray.get(i).getParent_name();
                        episode_title = contentListOutputArray.get(i).getName();
                        story = contentListOutputArray.get(i).getStory();
                        poster = contentListOutputArray.get(i).getPoster_url();

                        /**
                         * desc This method will check wheather the application is of VOD type and the content comming from server is also of video type
                         */
                        if (Util.isVideoContent(videoTypeIdStr)) {
                            Util.APP_TYPE = Util.VOD;
                        } else if (Util.isAudioContent(videoTypeIdStr)) {
                            Util.APP_TYPE = Util.AOD;
                        }
                        if (Util.getFilterContent(Util.APP_TYPE, videoTypeIdStr)) {


                            itemData.add(new GridItem(videoImageStr, videoName, "", videoTypeIdStr, "", "", videoPermalinkStr, isEpisodeStr, movieUniqueIdStr, movieStreamUniqueIdStr, isConverted, 0, 0, "", "", "", "", poster, episode_title, "", series_no, parent_title, "", videoDuration, story, episode_no));


                            if (add_to_list_orientation_checker) {
                                add_to_list_orientation_checker = false;
                                videoImageStrToHeight = videoImageStr;
                            }
                        }
                    }
                    if (itemData.size() < 1) {
                        noDataLayout.setVisibility(View.VISIBLE);
                        noInternetConnectionLayout.setVisibility(View.GONE);
                        gridView.setVisibility(View.GONE);
                        footerView.setVisibility(View.GONE);
                        return;
                    }

                    if (firstTime) {

                        new RetrieveFeedTask().execute(videoImageStrToHeight);

                    } else {
                        AsynLOADUI loadUI = new AsynLOADUI();
                        loadUI.executeOnExecutor(threadPoolExecutor);
                    }

                } else {

                    noDataLayout.setVisibility(View.VISIBLE);
                    noInternetConnectionLayout.setVisibility(View.GONE);
                    gridView.setVisibility(View.GONE);
                    footerView.setVisibility(View.GONE);

                }

            } else {

                noDataLayout.setVisibility(View.VISIBLE);
                noInternetConnectionLayout.setVisibility(View.GONE);
                gridView.setVisibility(View.GONE);
                footerView.setVisibility(View.GONE);

            }
        } else {

            noDataLayout.setVisibility(View.VISIBLE);
            noInternetConnectionLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.GONE);
            footerView.setVisibility(View.GONE);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        /*
         * @Desc: In the below section, checking for search view layout direction and setting up view accordingly.
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            config = getResources().getConfiguration();
            if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                //in Right To Left layout
                searchView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setImeOptions(0);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(languagePreference.getTextofLanguage(SEARCH_PLACEHOLDER, DEFAULT_SEARCH_PLACEHOLDER));
        searchView.requestFocus();

        int searchImgId = getResources().getIdentifier(String.valueOf(R.id.search_button), null, null);
        ImageView v = searchView.findViewById(searchImgId);
        searchView.setMaxWidth(10000);


        ImageView closeButton = searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_close_24dp);

        ImageView imgView = searchView.findViewById(R.id.search_mag_icon);
        imgView.setImageResource(R.drawable.ic_search);

        ImageView voicesearchicon = searchView.findViewById(R.id.search_voice_btn);
        voicesearchicon.setImageResource(R.drawable.voicesearch_button);

        theTextArea = searchView.findViewById(R.id.search_src_text);
        theTextArea.setBackgroundResource(R.drawable.edit);

        theTextArea.setHint(languagePreference.getTextofLanguage(TEXT_SEARCH_PLACEHOLDER, DEFAULT_TEXT_SEARCH_PLACEHOLDER));
        theTextArea.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

        theTextArea.setHintTextColor(getResources().getColor(R.color.search_hint_color));//or any color that you want
        theTextArea.setTextColor(getResources().getColor(R.color.search_text_color));

        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(theTextArea, R.drawable.cursor); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception e) {
        }

        theTextArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (NetworkStatus.getInstance().isConnected(SearchActivity.this)) {

                    try {
                        searchTextStr = theTextArea.getText().toString().trim();
                        String LastCharacter = getLastCharacter(searchTextStr);

                        if (theTextArea.getText().toString().trim().equals("")) {
                            if (searchDataAsynTask != null) {
                                searchDataAsynTask.cancel(true);
                            }

                            if (searchProgressBarHandler != null && searchProgressBarHandler.isShowing()) {
                                searchProgressBarHandler.hide();
                            }
                            if (toast != null) {
                                toast.cancel();
                            }

                            noDataLayout.setVisibility(View.GONE);
                            noInternetConnectionLayout.setVisibility(View.GONE);
                            gridView.setVisibility(View.GONE);
                            footerView.setVisibility(View.GONE);
                            resetData();
                            return;
                        }
                        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        InputMethodSubtype inputMethodSubtype = inputMethodManager.getCurrentInputMethodSubtype();
                        String localeString = inputMethodSubtype.getLocale();
                        Locale locale = new Locale(localeString);
                        String currentLang = locale.getDisplayLanguage();

                           if (!startSearch) {
                            startSearch = true;
                            return;
                        }

                        if (theTextArea.getText().toString().length() > 0) {

                            if (searchDataAsynTask != null) {
                                searchDataAsynTask.cancel(true);
                            }

                            getSearchData();

                            if (toast != null) {
                                toast.cancel();
                            }
                        } else {
                            try {
                                if (searchDataAsynTask != null) {
                                    searchDataAsynTask.cancel(true);
                                }

                                if (searchProgressBarHandler != null && searchProgressBarHandler.isShowing()) {
                                    searchProgressBarHandler.hide();
                                }
                                if (toast != null) {
                                    toast.cancel();
                                }

                                noDataLayout.setVisibility(View.GONE);
                                noInternetConnectionLayout.setVisibility(View.GONE);
                                gridView.setVisibility(View.VISIBLE);
                                footerView.setVisibility(View.GONE);
                                return;
                            } catch (IllegalArgumentException ex) {

                            } catch (Exception e) {
                            }
                        }
                        resetData();
                        noDataLayout.setVisibility(View.GONE);

                    } catch (Exception e) {
                    }
                } else {
                    noInternetConnectionLayout.setVisibility(View.VISIBLE);
                    noDataLayout.setVisibility(View.VISIBLE);
                    noDataTextView.setVisibility(View.GONE);
                    gridView.setVisibility(View.GONE);
                    resetData();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return true;
    }


    //Author : Chitra,desc: when click on voice search icon onNewIntent block will execute

    @Override
    protected void onNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchTextStr = query;
            final SearchView searchView = findViewById(R.id.search);
            final SearchView.SearchAutoComplete theTextArea = searchView.findViewById(R.id.search_src_text);
            theTextArea.setText(searchTextStr);
            // theTextArea.setSelection(searchTextStr.length());
            searchView.clearFocus();
        }
    }


    private void videoDetailsPostExecute(Video_Details_Output _video_details_output, int statusCode, String status, String message) {
        searchProgressBarHandler.hide();

        if (_video_details_output != null) {

            boolean play_video = true;

            if (featureHandler.getFeatureStatus(FeatureHandler.IS_STREAMING_RESTRICTION, FeatureHandler.DEFAULT_IS_STREAMING_RESTRICTION) && preferenceManager.getUseridFromPref() != null) {

                play_video = !_video_details_output.getStreaming_restriction().trim().equals("0");
            } else {
                play_video = true;
            }
            if (!play_video) {

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SearchActivity.this, R.style.MyAlertDialogStyle);
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


            if (!playerModel.isThirdPartyPlayer()) {
            }


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

                Util.showNoDataAlert(SearchActivity.this);

            } else {
                // condition for checking if the response has third party url or not.
                if (_video_details_output.getThirdparty_url() == null || _video_details_output.getThirdparty_url().matches("")) {


                    {


                        playerModel.setThirdPartyPlayer(false);

                        final Intent playVideoIntent;
                        if (Util.dataModel.getAdNetworkId() == 3) {

                            playVideoIntent = Util.getPlayerIntent(SearchActivity.this);

                        } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                            if (Util.dataModel.getPlayPos() <= 0) {
                                playVideoIntent = new Intent(SearchActivity.this, AdPlayerActivity.class);
                            } else {
                                playVideoIntent = Util.getPlayerIntent(SearchActivity.this);

                            }


                        } else {
                            playVideoIntent = Util.getPlayerIntent(SearchActivity.this);
                        }

                        playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        playVideoIntent.putExtra("PlayerModel", playerModel);
                        startActivity(playVideoIntent);
                    }
                } else {
                    final Intent playVideoIntent = Util.getPlayerIntent(SearchActivity.this);
                    playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    playVideoIntent.putExtra("PlayerModel", playerModel);
                    startActivity(playVideoIntent);
                }
            }

        } else if (statusCode == 436) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SearchActivity.this, R.style.MyAlertDialogStyle);
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
            Util.showNoDataAlert(SearchActivity.this);
        }
    }


    @Override
    public void onIPAddressPreExecuteStarted() {

    }

    @Override
    public void onIPAddressPostExecuteCompleted(String message, int statusCode, String ipAddressStr) {
        ipAddres = ipAddressStr;
        AutoPlayUtil.ipAddress = ipAddres;
        return;
    }

    @Override
    public void onVideoDetailsPreExecuteStarted() {
        searchProgressBarHandler = new SearchProgressHandler(SearchActivity.this);
        searchProgressBarHandler.show();
    }

    @Override
    public void onVideoDetailsPostExecuteCompleted(Video_Details_Output _video_details_output, int statusCode, String status, String message) {
        if (searchProgressBarHandler != null && searchProgressBarHandler.isShowing()) {
            searchProgressBarHandler.hide();
            searchProgressBarHandler = null;
        }

        videoDetailsPostExecute(_video_details_output, statusCode, status, message);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHILD_CONTENT_CLICK && resultCode == RESULT_OK) {
            callValidateUserAPI();
        } else if (requestCode == PAYMENT_REQUESTCODE && resultCode == RESULT_OK) {
            callValidateUserAPI();
        }

    }

    private class AsynLOADUI extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        protected void onPostExecute(Void result) {
            float density = getResources().getDisplayMetrics().density;

            if (firstTime == true) {
                try {
                    if (videoPDialog != null && videoPDialog.isShowing()) {
                        videoPDialog.hide();
                        videoPDialog = null;
                    }
                } catch (IllegalArgumentException ex) {
                    noDataLayout.setVisibility(View.VISIBLE);
                    noInternetConnectionLayout.setVisibility(View.GONE);
                    gridView.setVisibility(View.GONE);
                    footerView.setVisibility(View.GONE);
                }

                gridView.smoothScrollToPosition(0);
                firstTime = false;
                ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
                layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT; //this is in pixels
                gridView.setLayoutParams(layoutParams);
                  int mNoOfColumns = Util.calculateNoOfColumns(getApplicationContext());
                if (videoWidth > videoHeight) {
                    if (!Util.isTablet(getApplicationContext())) {
                        mNoOfColumns = 2;
                    }
                } else {
                    if (!Util.isTablet(getApplicationContext())) {
                        mNoOfColumns = 3;
                    } else {
                        if (mNoOfColumns <= 3) {
                            mNoOfColumns = 4;
                        }
                    }
                }

                gridView.setLayoutManager(new GridLayoutManager(getApplicationContext(), mNoOfColumns));

                customGridAdapter = new VideoFilterAdapter(getApplicationContext(), getGridLayout(videoWidth, videoHeight, density), itemData, Util.posterType(videoWidth, videoHeight));
                gridView.setAdapter(customGridAdapter);
            } else {
                customGridAdapter.notifyDataSetChanged();

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
        VideoDetailsAsynctask asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, SearchActivity.this, SearchActivity.this);
        asynLoadVideoUrls.execute(); //threadPoolExecutor
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    public void resetData() {
        totalItemCount = 0;
        if (itemData != null && itemData.size() > 0) {
            itemData.clear();
            try {
                customGridAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        isSearched = false;
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Void> {

        private Exception exception;
        private ProgressBarHandler phandler;

        protected Void doInBackground(String... urls) {
            try {

                URL url = new URL(urls[0]);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = sampleSize;
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options);
                //  Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                videoHeight = bmp.getHeight() * sampleSize;
                videoWidth = bmp.getWidth() * sampleSize;
                return null;
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(Void feed) {
            AsynLOADUI loadUI = new AsynLOADUI();
            loadUI.executeOnExecutor(threadPoolExecutor);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    /*
        This method is responsible for getting searching data form server .
     */
    public void getSearchData() {
        Search_Data_input search_data_input = new Search_Data_input();
        search_data_input.setAuthToken(authTokenStr);
        search_data_input.setLimit(String.valueOf(limit));
        search_data_input.setOffset(String.valueOf(offset));
        search_data_input.setQ(searchTextStr.trim());

        search_data_input.setUserId(preferenceManager.getUseridFromPref());

        String countryCodeStr = preferenceManager.getCountryCodeFromPref();
        if (countryCodeStr != null) {
            search_data_input.setCountry(countryCodeStr);
        } else {
            search_data_input.setCountry("IN");
        }
        search_data_input.setState(preferenceManager.getStateFromPref());
        search_data_input.setCity(preferenceManager.getCityFromPref());
        search_data_input.setLanguage_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));


        if (featureHandler.getFeatureStatus(FeatureHandler.IS_SEASON_PAGE_AVAILABLE, FeatureHandler.DEFAULT_IS_SEASON_PAGE_AVAILABLE)) {
            search_data_input.setSeason_info("1");
        }


        searchDataAsynTask = new SearchDataAsynTask(search_data_input, SearchActivity.this, SearchActivity.this);
        searchDataAsynTask.executeOnExecutor(threadPoolExecutor);
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


    /**
     * This method is responsible to return last character of a string.
     *
     * @param searchData
     */
    private String getLastCharacter(String searchData) {
        try {
            return (searchData.substring(searchData.length() - 1)).trim();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * callValidateUserAPI
     */
    public void callValidateUserAPI() {
        getVideoInfo();
    }


}

