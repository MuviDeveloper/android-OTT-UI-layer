package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.BLANK_PLAYLIST_NAME;
import static com.home.vod.preferences.LanguagePreference.BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.CAST_CREW_BUTTON_TITLE;
import static com.home.vod.preferences.LanguagePreference.CREATE_YOUR_PLAYLIST;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BLANK_PLAYLIST_NAME;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CAST_CREW_BUTTON_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CREATE_YOUR_PLAYLIST;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_DOWNLOAD_ON_WIFI_ONLY_MSG;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ERROR_IN_DATA_FETCHING;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NEW_PLAYLIST;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NOTIFICATION_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PLAYLIST_NAME;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PLAY_ALL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PLAY_LIST;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PROFILE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PURCHASE_HISTORY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_RELATED_CONTENT_AUDIO_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_RELATED_CONTENT_VIDEO_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRACK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRACKS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIEW_MORE;
import static com.home.vod.preferences.LanguagePreference.DOWNLOAD_ON_WIFI_ONLY_MSG;
import static com.home.vod.preferences.LanguagePreference.ERROR_IN_DATA_FETCHING;
import static com.home.vod.preferences.LanguagePreference.LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.LOGIN;
import static com.home.vod.preferences.LanguagePreference.LOGOUT;
import static com.home.vod.preferences.LanguagePreference.NEW_PLAYLIST;
import static com.home.vod.preferences.LanguagePreference.NOTIFICATION_TITLE;
import static com.home.vod.preferences.LanguagePreference.NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS;
import static com.home.vod.preferences.LanguagePreference.PLAYLIST_NAME;
import static com.home.vod.preferences.LanguagePreference.PLAY_ALL;
import static com.home.vod.preferences.LanguagePreference.PLAY_LIST;
import static com.home.vod.preferences.LanguagePreference.PROFILE;
import static com.home.vod.preferences.LanguagePreference.PURCHASE_HISTORY;
import static com.home.vod.preferences.LanguagePreference.RELATED_CONTENT_AUDIO_TITLE;
import static com.home.vod.preferences.LanguagePreference.RELATED_CONTENT_VIDEO_TITLE;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.TRACK;
import static com.home.vod.preferences.LanguagePreference.TRACKS;
import static com.home.vod.preferences.LanguagePreference.TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.VIEW_MORE;
import static com.home.vod.ui.widgets.AudioCustomMiniController.bootomsheet_open;
import static com.home.vod.util.Constant.PERMALINK_INTENT_KEY;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.Util.MultiAndSingParentnameGlobal;
import static com.home.vod.util.Util.QUEUE_ARRAY;
import static com.home.vod.util.Util.adapterposition;
import static com.home.vod.util.Util.check_for_subscription;
import static player.utils.Util.DEFAULT_NO_DETAILS_AVAILABLE;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.apiController.AsyncLastSeenDetails;
import com.home.apisdk.apiController.GetContentDetailsAsynTask;
import com.home.apisdk.apiController.GetEpisodeDeatailsAsynTask;
import com.home.apisdk.apiController.GetIpAddressAsynTask;
import com.home.apisdk.apiController.GetRelatedContentAsynTask;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
import com.home.apisdk.apiModel.ContentDetailsInput;
import com.home.apisdk.apiModel.ContentDetailsOutput;
import com.home.apisdk.apiModel.Episode_Details_input;
import com.home.apisdk.apiModel.Episode_Details_output;
import com.home.apisdk.apiModel.GetVideoDetailsInput;
import com.home.apisdk.apiModel.GetlastSeenDetailsInput;
import com.home.apisdk.apiModel.GetlastSeenDetailsOutputModel;
import com.home.apisdk.apiModel.RelatedContentInput;
import com.home.apisdk.apiModel.RelatedContentOutput;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.vod.BuildConfig;
import com.home.vod.ObservableObject;
import com.home.vod.R;
import com.home.vod.async.GetImageSize;
import com.home.vod.handlers.EpisodeListOptionMenuHandler;
import com.home.vod.handlers.HandleRatingbar;
import com.home.vod.handlers.LoginRegistrationOnContentClickHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.model.DataModel;
import com.home.vod.model.ListModel;
import com.home.vod.model.PlayListModel;
import com.home.vod.model.QueueModel;
import com.home.vod.model.RelatedContentModel;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.service.MusicService;
import com.home.vod.ui.MediaHelper;
import com.home.vod.ui.PlayerViewPagerAdapter;
import com.home.vod.ui.adapter.AudioMultiPartAdapter;
import com.home.vod.ui.adapter.RelatedContentAdapter;
import com.home.vod.ui.adapter.SinglePartAudioAdapter;
import com.home.vod.ui.widgets.AudioCustomMiniController;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.ui.widgets.ResizableCustomView;
import com.home.vod.util.Constant;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import player.activity.Player;
import player.utils.DBHelper;

public class AudioContentDetailsActivity extends AppCompatActivity implements
        SinglePartAudioAdapter.SinglePartAudioListener,
        AudioMultiPartAdapter.MultiPartAudioListener,
        MediaHelper,
        GetRelatedContentAsynTask.GetRelatedContentListener,
        VideoDetailsAsynctask.VideoDetailsListener,
        GetContentDetailsAsynTask.GetContentDetailsListener,
        GetEpisodeDeatailsAsynTask.GetEpisodeDetailsListener,
        GetIpAddressAsynTask.IpAddressListener, Observer,
        AsyncLastSeenDetails.AsyncLastSeenDetailsListener {

    public static final int VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE = 111;
    public static TextView songname_player;
    public static final int PAYMENT_REQUESTCODE = 2222;
    public static final int VIDEO_DOWNLOAD_BUTTON_CLICK_LOGIN_REG_REQUESTCODE = 9999;
    private static final int MAX_LINES = 3;
    public static BottomSheetBehavior mBottomSheetBehavior;
    public static TextView badge_text;
    public static TextView noti;
    public static FrameLayout noti_layout;

    private ContentDetailsOutput contentDetailsOutput;
    private Episode_Details_output episode_details_output_model;
    private SinglePartAudioAdapter adapter;
    private AudioMultiPartAdapter multiAdapter;
    private ImageView banner_image, share_view, imageView_Preorder;
    private TextView textView_PreorderMessage;
    private Button play_all, btnmore, btnRetry;
    private SQLiteDatabase DB;
    private TextView SongCount, tracks;
    private TextView albumName_multipart, new_playlist;
    private ListView listViewItems;
    private RecyclerView my_recycler_view;
    private ProgressBar recylerview_progress;
    private RelativeLayout noInternetConnectionLayout;
    private RelativeLayout noData;
    private TextView noDataTextView, noInternetTextView, oopsTextView;
    private AlertDialog alertDialog = null;
    private ImageView equalizer, background_player;
    private ImageView player_image_main, player_close, albumArt_player, player_play_ic, player_prev_ic, player_next_icc;
    private TextView relatedMoviesTv, relatedAudioTV, videoStoryTextView;
    private RecyclerView relateVideoRecyclerView, relatedAudioRecyclerview;
    private Button clearQueue;
    private Player playerModel = new Player();
    private Timer UI_FREEZE_TIMER;
    private Snackbar snackbar = null;
    private HandleRatingbar handleRatingbar;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private Toolbar mActionBarToolbar;
    private TextView videoGenreTextView, videoDurationTextView, videoCensorRatingTextView, videoCensorRatingTextView1, videoReleaseDateTextView, videoCastCrewTitleTextView, profilename, option_songName, option_artist_name;
    private TextView curent_duration, total_duration, song_p_name, totalduration, Artist_p_name, song_name_toolbar, artist_name_toolbar;
    private View sheetView;
    private View year_bar, censor_bar, songcount_bar;
    private BottomSheetDialog mBottomSheetDialog;
    private PopupWindow changeSortPopUp;
    private AudioCustomMiniController audioCustomMiniController;
    private CoordinatorLayout minicontroller;
    private ViewPager SquizeViewPager;
    private SharedPreferences QueuePref;
    private SeekBar seekBar, seekbar_botomSht;
    private ProgressBar musicProgress;
    private Episode_Details_input episode_details_input;
    private PreferenceManager preferenceManager;
    private LanguagePreference languagePreference;
    private FeatureHandler featureHandler;
    public static final int RESUME_WATCH_LASTSEEN_REQUESTCODE = 5673;
    private Context context = this;
    private ImageView banner_overlay_down;
    private NestedScrollView nestedScrollView;
    private View bottomSheet;
    private LinearLayout open_bottomSheet_controller;
    private MediaRouteButton mediaRouteButton;
    private LinearLayoutManager mLayoutManager;

    private ArrayList<ListModel> customDatas = new ArrayList<>();
    private ArrayList<Episode_Details_output> ItemListDetails = new ArrayList<>();
    private ArrayList<PlayListModel> ItemList;
    private ArrayList<Episode_Details_output> episode_details_output = new ArrayList<Episode_Details_output>();
    private ArrayList<RelatedContentModel> audioRelatedContentList, videoRelatedContentList;

    public boolean isPlayAllButtonClicked;
    private String posterimage = "";
    private String desired_string, content_types_id, ImageUrl, PlayListName;
    private int Position = 0;
    private String userId;
    private String song_url, played_length, song_imageUrl = "", song_name = "", genere, artist_name = "", movie_stream_unique_id = "", movie_unique_id = "";
    private ProgressBarHandler pDialog;
    private String movieUniqueId;
    private String MutiArtist;
    private String containt_name = "";
    private String PlayListId;
    private String episodeVideoUrlStr;
    private String movieNameStr;
    private boolean isSingleTrackSongClicked = false;
    private int isPreorder;
    private String is_login_require;
    private int offset = 1;
    private int limit = 4;
    private boolean episode_api_called = false;
    private String movieReleaseDateStr;
    private String song_status = null;
    public DBHelper dbHelper;
    private int itemCount = -1;
    private boolean scroll = true, castStr;

    private int lastApiCallPosition = 0;
    private String email, id;
    private LinearLayout linearLayout[];
    private boolean[] visibility;
    private String[] lang;
    private EpisodeListOptionMenuHandler episodeListOptionMenuHandler;
    private Menu menu;

    private int option_menu_id[] = {R.id.login, R.id.register, R.id.language_popup, R.id.profile, R.id.purchase, R.id.logout, R.id.notification};
    private int videoHeight = 185;
    private int videoWidth = 256;
    private String loggedInIdStr, guestUserld;
    private Bundle arguments;

    @Override
    public void onLastSeenDetailsPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing()) {
            pDialog.show();
        }
    }

    @Override
    public void onLastSeenDetailsPostExecuteCompleted(GetlastSeenDetailsOutputModel GetlastSeenDetailsOutputModel, int status, String message) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }

        if (status == 200) {

            DataModel dbModel = new DataModel();
            dbModel.setMovieUniqueId(GetlastSeenDetailsOutputModel.getLastSeenMuviUniqId());
            dbModel.setStreamUniqueId(GetlastSeenDetailsOutputModel.getLastSeenStreamUniqId());
            dbModel.setVideoTitle(GetlastSeenDetailsOutputModel.getParent_title());
            dbModel.setEpisode_id(GetlastSeenDetailsOutputModel.getLastSeenStreamUniqId());
            dbModel.setSeason_id("0");
            dbModel.setPurchase_type("episode");
            dbModel.setPosterImageId(GetlastSeenDetailsOutputModel.getDefault_poster());
            dbModel.setContentTypesId(Integer.parseInt(content_types_id));
            dbModel.setEpisode_series_no(GetlastSeenDetailsOutputModel.getSeriesNo());
            dbModel.setEpisode_no(GetlastSeenDetailsOutputModel.getEpisode_no());
            dbModel.setEpisode_title(GetlastSeenDetailsOutputModel.getContent_title());
            dbModel.setEpisode_no(GetlastSeenDetailsOutputModel.getEpisode_no());
            Util.dataModel = dbModel;
            checkAuthenticationForPlayAll(0);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_content_details);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        registerReceiver(OPTION_RESPONSE, new IntentFilter("OPTION_RESPONSE"));
        try {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            View focusedView = AudioContentDetailsActivity.this.getCurrentFocus();
            if (focusedView != null) {
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_HIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        arguments = getIntent().getExtras();
        episodeListOptionMenuHandler = new EpisodeListOptionMenuHandler(this);

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("");
        setSupportActionBar(mActionBarToolbar);

        relatedMoviesTv = findViewById(R.id.relatedMovies_tv);
        relatedAudioTV = findViewById(R.id.relatedAudio_tv);
        relateVideoRecyclerView = findViewById(R.id.related_movie_recyclerView);
        relatedAudioRecyclerview = findViewById(R.id.related_audio_recyclerView);

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
        initAudioCustomMiniController();
        dbHelper = new DBHelper(this);
        dbHelper.getWritableDatabase();
        audioRelatedContentList = new ArrayList<RelatedContentModel>();
        videoRelatedContentList = new ArrayList<RelatedContentModel>();

        handleRatingbar = new HandleRatingbar(this);
        mLayoutManager = new LinearLayoutManager(AudioContentDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
        ObservableObject.getInstance().addObserver(this);
        desired_string = getIntent().getStringExtra("PERMALINK");
        content_types_id = getIntent().getStringExtra("CONTENT_TYPE");
        preferenceManager = PreferenceManager.getPreferenceManager(context);
        languagePreference = LanguagePreference.getLanguagePreference(context);
        featureHandler = FeatureHandler.getFeaturePreference(context);
        QueuePref = android.preference.PreferenceManager.getDefaultSharedPreferences(AudioContentDetailsActivity.this);
        Util.DownloadChange = false;
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        playerModel.setIsstreaming_restricted(Util.getStreamingRestriction(languagePreference));
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        share_view = (ImageView) findViewById(R.id.share);


        share_view.setOnClickListener(v -> {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");

            String link2 = "https://" + getResources().getString(R.string.host_name) + "/" + languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE) + "/" + desired_string + "?content_types_id=" + content_types_id + "&permalink=" + desired_string;

            String shareBodyText = "Hey I'm watching " + movieNameStr + ". Check it out on " + getResources().getString(R.string.app_name) + "\n\n" + link2;
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.host_name));
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(sharingIntent, "Share"));
        });

        String imgurl_bottom = "https://sampledesign.muvi.com/mobileaudio/testimage2.png";
        banner_overlay_down = (ImageView) findViewById(R.id.overlay2);

        Picasso.with(context)
                .load(imgurl_bottom)
                .error(R.drawable.no_image)
                .into(banner_overlay_down);
        DB = context.openOrCreateDatabase(Util.DATABASE_NAME, MODE_PRIVATE, null);

        /*
        @Bundle arguments capture from Adaptor(permalink)
         @#GetContaint details used for unique data .
         */

        if (arguments != null) {
            ImageUrl = arguments.getString("ImageUrl");
            PlayListName = arguments.getString("PlayListName");
        }

        pDialog = new ProgressBarHandler(context);

        my_recycler_view = (RecyclerView) findViewById(R.id.list_recyclerView);
        recylerview_progress = (ProgressBar) findViewById(R.id.progressBar);
        my_recycler_view.setHasFixedSize(true);

        banner_image = (ImageView) findViewById(R.id.banner_image);
        imageView_Preorder = (ImageView) findViewById(R.id.imageView_Preorder);
        textView_PreorderMessage = (TextView) findViewById(R.id.textView_PreorderMessage);
        play_all = (Button) findViewById(R.id.play_all);
        videoGenreTextView = (TextView) findViewById(R.id.genre);
        videoDurationTextView = (TextView) findViewById(R.id.video_duration);
        videoCensorRatingTextView = (TextView) findViewById(R.id.videoCensorRatingTextView);
        videoCensorRatingTextView1 = (TextView) findViewById(R.id.videoCensorRatingTextView1);
        videoReleaseDateTextView = (TextView) findViewById(R.id.video_release_date);
        videoStoryTextView = (TextView) findViewById(R.id.videoStoryTextView);
        censor_bar = findViewById(R.id.censor_bar);
        year_bar = findViewById(R.id.year_bar);
        songcount_bar = findViewById(R.id.songcount_bar);
        videoCastCrewTitleTextView = (TextView) findViewById(R.id.cast_crew);
        videoCastCrewTitleTextView.setVisibility(View.GONE);


        if (!Util.isColorDark(getResources().getColor(R.color.appBackgroundColor))) {
            videoCensorRatingTextView.setBackgroundColor(ContextCompat.getColor(AudioContentDetailsActivity.this, R.color.censorRatingBackground_white_theme));
        }


        albumName_multipart = (TextView) findViewById(R.id.albumName_multipart);
        SongCount = (TextView) findViewById(R.id.SongCount);
        tracks = (TextView) findViewById(R.id.tracks);
        Typeface albumName_multipart_tf = Typeface.createFromAsset(context.getAssets(), getResources().getString(R.string.regular_fonts));
        albumName_multipart.setTypeface(albumName_multipart_tf);
        Typeface SongCount_tf = Typeface.createFromAsset(context.getAssets(), getResources().getString(R.string.light_fonts));
        SongCount.setTypeface(SongCount_tf);
        // prefs = getActivity().getSharedPreferences(Util.LOGINPREFERENCE, MODE_PRIVATE);
        userId = preferenceManager.getUseridFromPref();

        if (arguments != null) {
            PlayListId = arguments.getString("PlayListId");
        }

        btnmore = (Button) findViewById(R.id.viewall);

        noInternetConnectionLayout = (RelativeLayout) findViewById(R.id.noInternet);
        noInternetTextView = (TextView) findViewById(R.id.noInternetTextView);
        oopsTextView = (TextView) findViewById(R.id.oopsTextView); // Added by Debashish
        btnRetry = (Button) findViewById(R.id.btnRetry); // Added by Debashish
        noData = (RelativeLayout) findViewById(R.id.noData);
        noDataTextView = (TextView) findViewById(R.id.noDataTextView);
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noDataTextView.setText(languagePreference.getTextofLanguage(NO_CONTENT, DEFAULT_NO_CONTENT));

        /**
         * @description : two keys added (1) Try Again ! (2) Oops !
         */

        btnRetry.setText("          " + languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN) + "       ");
        oopsTextView.setText(languagePreference.getTextofLanguage(OOPS, DEFAULT_OOPS));


        is_login_require = featureHandler.getFeatureFlag(FeatureHandler.IS_LOGIN, FeatureHandler.DEFAULT_IS_LOGIN);
        ItemList = new ArrayList<>();
        if (null != content_types_id) {
            switch (content_types_id) {
                case "6":
                    MultiPartView();
                    break;
                case "5":
                    SinglePartView();
                    break;
                case "playlist":
                    PlayListView();
                    break;
            }
        } else {
            // Toast.makeText(getContext(),"")
        }

        play_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferenceManager.setCurrentAudioPosition(0);
                Util.adapterposition = preferenceManager.getCurrentAudioPosition();

                if (content_types_id.equals("6")) {
                    /*
                     * @desc:call episodeDetails APi to get all the songs*/
                    isPlayAllButtonClicked = true;
                    isSingleTrackSongClicked = false;

                    Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                    episodeDetailsInput.setAuthtoken(authTokenStr);
                    episodeDetailsInput.setPermalink(desired_string.trim());
                    episodeDetailsInput.setLimit(String.valueOf(itemCount)); //episodeDetailsInput.setLimit("4");
                    episodeDetailsInput.setOffset(String.valueOf(1));// episodeDetailsInput.setOffset("1");
                    episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
                    getAllSongs(episodeDetailsInput);

                }
//                }
            }
        });
/**
 * @description : onclicking Retry button
 */
        btnRetry.setOnClickListener(view -> {
            noInternetConnectionLayout.setVisibility(View.GONE);
            view.startAnimation(buttonClick);
            restartActivity();
        });


        sheetView = this.getLayoutInflater().inflate(R.layout.opt, null);
        option_songName = (TextView) sheetView.findViewById(R.id.option_songName);
        option_artist_name = (TextView) sheetView.findViewById(R.id.option_artist_name);
        mBottomSheetDialog = new BottomSheetDialog(AudioContentDetailsActivity.this);
        mBottomSheetDialog.setContentView(sheetView);


        if (QueuePref.contains("key")) {
            String Song_Name, AlbumArt, SongUrl, ArtistUrl;

            Gson gson = new Gson();
            String jsonText = QueuePref.getString("key", null);
            try {
                JSONArray jsonArray = new JSONArray(jsonText);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Song_Name = jsonObject.getString("SongName");
                    AlbumArt = jsonObject.getString("AlbumArt");
                    ArtistUrl = jsonObject.getString("ArtistUrl");
                    SongUrl = jsonObject.getString("SongUrl");
                    QUEUE_ARRAY.add(new QueueModel(Song_Name, AlbumArt, SongUrl, ArtistUrl));
                    PlayerViewPagerAdapter testPagerAdapter = new PlayerViewPagerAdapter(getSupportFragmentManager());
                    SquizeViewPager.setAdapter(testPagerAdapter);
                    testPagerAdapter.notifyDataSetChanged();
                    minicontroller.setVisibility(View.VISIBLE);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        btnmore.setOnClickListener(v -> {
            isPlayAllButtonClicked = false;
            if (scroll == false) {
                // nestedScrollView.scrollTo(0, nestedScrollView.getTop());
                nestedScrollView.scrollTo(0, 0);

            } else {
                offset += 1;
                Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                episodeDetailsInput.setAuthtoken(authTokenStr);
                episodeDetailsInput.setPermalink(desired_string.trim());
                episodeDetailsInput.setLimit(String.valueOf(limit)); //episodeDetailsInput.setLimit("4");
                episodeDetailsInput.setOffset(String.valueOf(offset));// episodeDetailsInput.setOffset("1");
                episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
                callMultipart(episodeDetailsInput);
            }

        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    isPlayAllButtonClicked = false;
                    if ((episode_details_output.size() == itemCount) || (mLayoutManager.findLastVisibleItemPosition() != episode_details_output.size() - 1)
                            || offset == 1) {
                        return;
                    }

                    if (lastApiCallPosition == mLayoutManager.findLastVisibleItemPosition())
                        return;

                    lastApiCallPosition = mLayoutManager.findLastVisibleItemPosition();

                    offset += 1;
                    Episode_Details_input episodeDetailsInput = new Episode_Details_input();
                    episodeDetailsInput.setAuthtoken(authTokenStr);
                    episodeDetailsInput.setPermalink(desired_string.trim());

                    episodeDetailsInput.setLimit(String.valueOf(limit)); //episodeDetailsInput.setLimit("4");
                    episodeDetailsInput.setOffset(String.valueOf(offset));// episodeDetailsInput.setOffset("1");
                    episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
                    callMultipart(episodeDetailsInput);


                }
            });
        }


        imageView_Preorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaySongs(contentDetailsOutput, true);
            }
        });
    }

    private void getAllSongs(Episode_Details_input episodeDetailsInput) {
        GetEpisodeDeatailsAsynTask getEpisodeDeatailsAsynTask = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, this, AudioContentDetailsActivity.this, false, new DataController(AudioContentDetailsActivity.this), false);
        getEpisodeDeatailsAsynTask.execute();

    }

    private void checkAuthenticationForPlayAll(int isFree) {
        if (Integer.parseInt(is_login_require) == 1) {
            if (preferenceManager.getUseridFromPref() == null) {
                if (isFree == 1 && !featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN_REQUIRED_FOR_FREECONTENT, FeatureHandler.DEFAULT_IS_LOGIN_REQUIRED_FOR_FREECONTENT)) {

                    getVideoInfo();

                } else {
                    check_for_subscription = 1;
                    Intent registerActivity = new LoginRegistrationOnContentClickHandler(AudioContentDetailsActivity.this).handleClickOnContent();
                    if (isPreorder == 1) Util.preorder_status = 1;
                    registerActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    // registerActivity.putExtra("PlayerModel", null);
                    startActivityForResult(registerActivity, VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE);
                }
            } else {
                if (NetworkStatus.getInstance().isConnected(AudioContentDetailsActivity.this)) {

                    getVideoInfo();

                } else {
                    Toast.makeText(AudioContentDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            if (NetworkStatus.getInstance().isConnected(AudioContentDetailsActivity.this)) {
                getVideoInfo();
            } else {
                Toast.makeText(AudioContentDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
            }

        }
    }

    public void callMultipart(Episode_Details_input episodeDetailsInput) {
        GetEpisodeDeatailsAsynTask getEpisodeDeatailsAsynTask = new GetEpisodeDeatailsAsynTask(episodeDetailsInput, this, AudioContentDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(AudioContentDetailsActivity.this), false);
        getEpisodeDeatailsAsynTask.execute();
    }

    private BroadcastReceiver OPTION_RESPONSE = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context ctx, final Intent intent) {

            userId = preferenceManager.getUseridFromPref();
            if (intent.getStringExtra("response").equals("add_to_playlist")) {
                if (userId != null && !userId.equals("0101D")) {
                    //

                    ViewGroup viewGroup = findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(AudioContentDetailsActivity.this).inflate(R.layout.custom_dialog_view, viewGroup, false);
                    //Now we need an AlertDialog.Builder object
                    AlertDialog.Builder builder = new AlertDialog.Builder(AudioContentDetailsActivity.this);
                    //setting the view of the builder to our custom view that we already inflated
                    builder.setView(dialogView);
                    //finally creating the alert dialog and displaying it
                    alertDialog = builder.create();
                    alertDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    if (!((Activity) context).isFinishing()) {
                        //show dialog
                        alertDialog.show();
                    }

                    listViewItems = (ListView) dialogView.findViewById(R.id.playList_content_listView);
                    new_playlist = (TextView) dialogView.findViewById(R.id.new_playlist);
                    new_playlist.setText("+" + " " + languagePreference.getTextofLanguage(NEW_PLAYLIST, DEFAULT_NEW_PLAYLIST));
                    TextView myPlaylistTv = dialogView.findViewById(R.id.myPlaylistTv);
                    myPlaylistTv.setText(languagePreference.getTextofLanguage(PLAY_LIST, DEFAULT_PLAY_LIST));


                    listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            alertDialog.hide();

                        }
                    });
                    new_playlist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.hide();
                            ViewGroup viewGroup = findViewById(android.R.id.content);
                            View dialogView = LayoutInflater.from(AudioContentDetailsActivity.this).inflate(R.layout.custom_dialog, viewGroup, false);
                            //Now we need an AlertDialog.Builder object
                            AlertDialog.Builder builder = new AlertDialog.Builder(AudioContentDetailsActivity.this);
                            //setting the view of the builder to our custom view that we already inflated
                            builder.setView(dialogView);

                            AlertDialog alertDialogadd = builder.create();
                            alertDialogadd.getWindow().setBackgroundDrawableResource(R.color.transparent);
                            alertDialogadd.show();

                            Button saveButton = (Button) dialogView.findViewById(R.id.saveButton);
                            Button cancelButton = (Button) dialogView.findViewById(R.id.cancelButton);
                            TextView dialog_text = (TextView) dialogView.findViewById(R.id.dialog_text);
                            Typeface dialog_text_tf = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.regular_fonts));
                            dialog_text.setTypeface(dialog_text_tf);
                            dialog_text.setText(languagePreference.getTextofLanguage(CREATE_YOUR_PLAYLIST, DEFAULT_CREATE_YOUR_PLAYLIST));
                            final EditText userInput = (EditText) dialogView.findViewById(R.id.editTextDialogUserInput);
                            userInput.setHint(languagePreference.getTextofLanguage(PLAYLIST_NAME, DEFAULT_PLAYLIST_NAME));

                            cancelButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // alertDialog.cancel;
                                    try {
                                        alertDialogadd.hide();
                                        userInput.setError(null);
                                        alertDialog.show();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            saveButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Do some thing
                                    PlayListName = String.valueOf(userInput.getText().toString().trim());
                                    if (!PlayListName.isEmpty()) {
                                        userInput.setError(null);
                                        alertDialogadd.hide();

                                    } else {
                                        userInput.setError(languagePreference.getTextofLanguage(BLANK_PLAYLIST_NAME, DEFAULT_BLANK_PLAYLIST_NAME));
                                    }
                                }
                            });
                            alertDialogadd.show();
                        }
                    });

                } else {
                    Toast.makeText(context, "Please Register to Avail this Service", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    @Override
    public void onBackPressed() {
        if (bootomsheet_open) {
            audioCustomMiniController.collapse_buttomsheet();
            return;
        }
        super.onBackPressed();
    }

    public void PlayListView() {
        play_all.setVisibility(View.GONE);

        AsynAudioUserPlayListDetail asynAudioUserPlayListDetail = new AsynAudioUserPlayListDetail();
        asynAudioUserPlayListDetail.execute();

    }

    public void SinglePartView() {
        if (NetworkStatus.getInstance().isConnected(context)) {
            ContentDetailsInput contentDetailsInput = new ContentDetailsInput();
            contentDetailsInput.setAuthToken(authTokenStr);
            contentDetailsInput.setPermalink(desired_string);
            contentDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
            contentDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
            contentDetailsInput.setState(preferenceManager.getStateFromPref());
            contentDetailsInput.setCity(preferenceManager.getCityFromPref());
            contentDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            GetContentDetailsAsynTask asynLoadMovieDetails = new GetContentDetailsAsynTask(contentDetailsInput, this, AudioContentDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
            asynLoadMovieDetails.execute();
        } else {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
        }


    }

    public void MultiPartView() {
        if (NetworkStatus.getInstance().isConnected(context)) {
            ContentDetailsInput contentDetailsInput = new ContentDetailsInput();
            contentDetailsInput.setAuthToken(authTokenStr);
            contentDetailsInput.setPermalink(desired_string);
            contentDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
            contentDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
            contentDetailsInput.setState(preferenceManager.getStateFromPref());
            contentDetailsInput.setCity(preferenceManager.getCityFromPref());
            contentDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            GetContentDetailsAsynTask asynLoadMovieDetails = new GetContentDetailsAsynTask(contentDetailsInput, this, AudioContentDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
            asynLoadMovieDetails.execute();
        } else {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
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

    public void PlaySongs(ContentDetailsOutput item, boolean isClicked) {
        if (isClicked) {
            setDataModel();


            contentDetailsOutput = item;
            song_url = item.getMovieUrl();
            genere = item.getGenre();
            song_imageUrl = item.getPoster();
            song_name = item.getName();
            artist_name = item.getCast_detail();
            movie_stream_unique_id = item.getMovieStreamUniqId();
            movie_unique_id = item.getMuviUniqId();

            setPlayerModel();


            if (Integer.parseInt(is_login_require) == 1) {

                if (preferenceManager.getUseridFromPref() == null) {

                    if ((Integer.parseInt(item.getIsFreeContent()) == 1)
                            && !featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN_REQUIRED_FOR_FREECONTENT,
                            FeatureHandler.DEFAULT_IS_LOGIN_REQUIRED_FOR_FREECONTENT)) {

                        {
                            getVideoInfo();
                        }
                    } else {
                        if (preferenceManager.getGuestUseridFromPref() != null) {
                            getVideoInfo();

                        } else {
                            check_for_subscription = 1;
                            Intent registerActivity = new LoginRegistrationOnContentClickHandler(AudioContentDetailsActivity.this).handleClickOnContent();
                            if (isPreorder == 1) Util.preorder_status = 1;
                            registerActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivityForResult(registerActivity, VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE);
                        }
                    }

                } else {
                    if (NetworkStatus.getInstance().isConnected(AudioContentDetailsActivity.this)) {
                        getVideoInfo();
                    } else {
                        Toast.makeText(AudioContentDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                //play song directly
                if (NetworkStatus.getInstance().isConnected(AudioContentDetailsActivity.this)) {
                    getVideoInfo();

                } else {
                    Toast.makeText(AudioContentDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    @Override
    public void downloadSinglePart(ContentDetailsOutput itemsList, boolean itemClicked) {
        if (preferenceManager.getDownloadFromPref().equals("1")) {
            if (NetworkStatus.getInstance().isWiFiConnected(AudioContentDetailsActivity.this)) {
                PlaySongs(itemsList, itemClicked);
            } else {
                Toast.makeText(AudioContentDetailsActivity.this, languagePreference.getTextofLanguage(DOWNLOAD_ON_WIFI_ONLY_MSG, DEFAULT_DOWNLOAD_ON_WIFI_ONLY_MSG), Toast.LENGTH_LONG).show();
            }

        } else {
            if (NetworkStatus.getInstance().isConnected(AudioContentDetailsActivity.this)) {
                PlaySongs(itemsList, itemClicked);
            } else {
                Toast.makeText(AudioContentDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void setDataModel() {
        DataModel dbModel = new DataModel();
        dbModel.setIsFreeContent(Integer.parseInt(contentDetailsOutput.getIsFreeContent()));
        dbModel.setIsAPV(contentDetailsOutput.getIsApv());
        dbModel.setIsPPV(contentDetailsOutput.getIsPpv());
        dbModel.setIsConverted(contentDetailsOutput.getIsConverted());
        dbModel.setMovieUniqueId(contentDetailsOutput.getMuviUniqId());
        dbModel.setStreamUniqueId(contentDetailsOutput.getMovieStreamUniqId());
        dbModel.setThirdPartyUrl("");
        dbModel.setVideoTitle(contentDetailsOutput.getName());
        dbModel.setVideoStory(contentDetailsOutput.getStory());
        dbModel.setCensorRating(contentDetailsOutput.getCensorRating());
        dbModel.setCastCrew(contentDetailsOutput.getCastStr());
        dbModel.setEpisode_id("0");
        dbModel.setSeason_id("0");
        dbModel.setPurchase_type("show");
        dbModel.setPosterImageId(contentDetailsOutput.getPoster());
        dbModel.setContentTypesId(Integer.parseInt(content_types_id));
        Util.dataModel = dbModel;
    }

    public void setPlayerModel() {
        playerModel.setStreamUniqueId(contentDetailsOutput.getMovieStreamUniqId());
        playerModel.setMovieUniqueId(contentDetailsOutput.getMuviUniqId());
        playerModel.setUserId(preferenceManager.getUseridFromPref());
        playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
        playerModel.setAuthTokenStr(authTokenStr.trim());
        playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH);
        playerModel.setEpisode_id("0");
        playerModel.setIsFreeContent(Integer.parseInt(contentDetailsOutput.getIsFreeContent()));
        playerModel.setVideoTitle(contentDetailsOutput.getName());
        playerModel.setVideoStory(contentDetailsOutput.getStory());
        playerModel.setVideoGenre(contentDetailsOutput.getCast_detail());
        playerModel.setCensorRating(contentDetailsOutput.getCensorRating());
        playerModel.setContentTypesId(Integer.parseInt(content_types_id));
        playerModel.setPosterImageId(contentDetailsOutput.getPoster());
        playerModel.setCastCrew(contentDetailsOutput.getCastStr());
        playerModel.setParent_poster(contentDetailsOutput.getPoster());
        playerModel.setParent_title(contentDetailsOutput.getName());
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
            getVideoDetailsInput.setUser_id(loggedInIdStr == null ? "" : loggedInIdStr);
        }
        getVideoDetailsInput.setContent_uniq_id(Util.dataModel.getMovieUniqueId());
        getVideoDetailsInput.setStream_uniq_id(Util.dataModel.getStreamUniqueId());
        getVideoDetailsInput.setInternetSpeed(MainActivity.internetSpeed.trim());
        getVideoDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        getVideoDetailsInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        getVideoDetailsInput.setAdParameter(Util.getVastAdParameter());
        VideoDetailsAsynctask asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, this, AudioContentDetailsActivity.this);
        asynLoadVideoUrls.execute(); //threadPoolExecutor
    }

    public void PlaySongsmulti(Episode_Details_output item, boolean isClicked, int adapterPosition) {
        Util.adapterposition = adapterPosition;
        if (isClicked) {
            preferenceManager.setCurrentAudioPosition(adapterPosition);
            isSingleTrackSongClicked = true;
            Episode_Details_input episodeDetailsInput = new Episode_Details_input();
            episodeDetailsInput.setAuthtoken(authTokenStr);
            episodeDetailsInput.setPermalink(desired_string.trim());
            episodeDetailsInput.setLimit(String.valueOf(itemCount)); //episodeDetailsInput.setLimit("4");
            episodeDetailsInput.setOffset(String.valueOf(1)); //episodeDetailsInput.setOffset("1");
            episodeDetailsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            episodeDetailsInput.setCountry(preferenceManager.getCountryCodeFromPref());
            getAllSongs(episodeDetailsInput);

            //set data in data model
            episode_details_output_model = item;
            song_url = item.getVideo_url();
            song_imageUrl = item.getPoster_url();
            genere = item.getGenre();
            song_name = item.getEpisode_title();
            artist_name = MutiArtist;
            movie_stream_unique_id = item.getMovie_stream_uniq_id();
            movie_unique_id = item.getMuvi_uniq_id();

            DataModel dbModel = new DataModel();
            dbModel.setIsFreeContent(Integer.parseInt(item.getIsFree()));
            dbModel.setIsAPV(item.getIsAPV());
            dbModel.setIsPPV(item.getIs_ppv());
            dbModel.setIsConverted(1);
            dbModel.setMovieUniqueId(movieUniqueId);
            dbModel.setStreamUniqueId(item.getMovie_stream_uniq_id());
            dbModel.setVideoTitle(contentDetailsOutput.getName());
            dbModel.setVideoGenre(item.getGenre());
            dbModel.setVideoDuration(item.getVideo_duration());
            dbModel.setVideoReleaseDate("");
            dbModel.setCensorRating(contentDetailsOutput.getCensorRating());
            dbModel.setCastCrew(contentDetailsOutput.getCastStr());
            dbModel.setEpisode_id(item.getMovie_stream_uniq_id());
            dbModel.setSeason_id("0");
            dbModel.setPurchase_type("episode");
            dbModel.setPosterImageId(item.getPoster_url());
            dbModel.setContentTypesId(Integer.parseInt(content_types_id));
            dbModel.setEpisode_series_no(item.getSeriesNumber());
            dbModel.setEpisode_no(item.getEpisodeNumber());
            dbModel.setEpisode_title(item.getEpisode_title());
            dbModel.setEpisode_series_no(item.getSeriesNumber());

            Util.dataModel = dbModel;

            //set the required data in playermodel
            playerModel.setStreamUniqueId(item.getMovie_stream_uniq_id());
            playerModel.setMovieUniqueId(item.getMuvi_uniq_id());
            playerModel.setUserId(preferenceManager.getUseridFromPref());
            playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
            playerModel.setAuthTokenStr(authTokenStr.trim());
            playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH.trim());
            playerModel.setEpisode_id(item.getMovie_stream_uniq_id());
            playerModel.setVideoTitle(item.getEpisode_title());

            try {
                playerModel.setIsFreeContent(Integer.parseInt(item.getIsFree()));
            } catch (Exception e) {
            }

            // playerModel.setVideoStory(item.getEpisodeDescription());
            playerModel.setVideoGenre(item.getGenre());
            playerModel.setVideoDuration(item.getVideo_duration());
            playerModel.setVideoReleaseDate("");
            playerModel.setCensorRating(contentDetailsOutput.getCensorRating());
            playerModel.setContentTypesId(Integer.parseInt(content_types_id));
            try {
                playerModel.setPosterImageId(item.getPoster_url());
            } catch (Exception e) {
                playerModel.setPosterImageId("https://d2gx0xinochgze.cloudfront.net/public/no-image-a.png");
            }
            playerModel.setParent_poster(posterimage);
            playerModel.setParent_title(containt_name);
            playerModel.setCastCrew(contentDetailsOutput.getCastStr());
            check_for_subscription = 1;

            if (Integer.parseInt(is_login_require) == 1) {
                if (preferenceManager.getUseridFromPref() == null) {
                    String guestUserStr = preferenceManager.getGuestUseridFromPref();
                    if ((Integer.parseInt(episode_details_output_model.getIsFree()) == 1) && !featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN_REQUIRED_FOR_FREECONTENT, FeatureHandler.DEFAULT_IS_LOGIN_REQUIRED_FOR_FREECONTENT)) {
                        {
                            getVideoInfo();
                        }
                    } else {
                        if (preferenceManager.getGuestUseridFromPref() != null) {
                            getVideoInfo();
                        } else {
                            {
                                check_for_subscription = 1;
                                Intent registerActivity = new LoginRegistrationOnContentClickHandler(AudioContentDetailsActivity.this).handleClickOnContent();
                                if (isPreorder == 1) Util.preorder_status = 1;
                                registerActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                // registerActivity.putExtra("PlayerModel", null);
                                startActivityForResult(registerActivity, VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE);
                            }

                        }
                    }
                } else {
                    if (NetworkStatus.getInstance().isConnected(AudioContentDetailsActivity.this)) {
                        getVideoInfo();
                    } else {
                        Toast.makeText(AudioContentDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                // paly song directly
                if (NetworkStatus.getInstance().isConnected(AudioContentDetailsActivity.this)) {
                    getVideoInfo();
                } else {
                    Toast.makeText(AudioContentDetailsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void downloadMultipart(Episode_Details_output episode_Details_output, boolean itemClicked, int index) {
        PlaySongsmulti(episode_Details_output, itemClicked, index);
    }

    public void Player_State(int funId) {
        /*try catch added by @Author : Bishal
         */

        if (song_url == null) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
            builder1.setMessage(Html.fromHtml("<font color='" + getResources().getColor(R.color.colorPrimary) + "'>" + languagePreference.getTextofLanguage(NO_DETAILS_AVAILABLE, DEFAULT_NO_DETAILS_AVAILABLE) + "</font>"));
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        } else {
            try {
                audioCustomMiniController.enabledisablenextprevious_btn_from_other_page(adapterposition, QUEUE_ARRAY);

                /*
                 * @author:Subhadarshani
                 * @desc:check if the player is PIP mode or not. If is in PIP mode stop player and start playing audio*/
                Intent j = new Intent(context, MusicService.class);
                j.putExtra("ALBUM", song_url);
                j.putExtra("PLAYED_LENGTH", played_length);
                j.putExtra("PERMALINK", desired_string);
                j.putExtra("POSITION", Position);
                j.putExtra("ALBUM_ART", song_imageUrl);
                j.putExtra("ALBUM_NAME", MultiAndSingParentnameGlobal);
                j.putExtra("Artist", artist_name);
                j.putExtra("ALBUM_SONG_NAME", song_name);
                j.putExtra("STATE", funId);

                if (content_types_id.equals("6")) { // for multipart
                    j.putExtra("MOVIE_UNIQUE_ID", movie_unique_id);
                    j.putExtra("MOVIE_STREAM_UNIQUE_ID", movie_stream_unique_id);

                } else if (content_types_id.equals("5")) { // for single part "episode_id" value will bw "0"
                    j.putExtra("MOVIE_UNIQUE_ID", movie_unique_id);
                    j.putExtra("MOVIE_STREAM_UNIQUE_ID", "0");
                }

                j.setAction(Constant.ACTION.STARTFOREGROUND_ACTION);
                context.startService(j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Util.preorder_status = 0;
        try {
            if (preferenceManager.getFreeUserPref().equals("1")) {
                imageView_Preorder.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * desc: Intialize view for audio minicontroller*/
        initAudioCustomMiniController();

        registerReceiver(SongStatusReciver, new IntentFilter(Constant.SONG_STATUS));
        registerReceiver(CLOSE_NOTIFICATION, new IntentFilter(Constant.CLOSE_NOTIFICATION));
        registerReceiver(SONG_STATUS_NEXT, new IntentFilter(Constant.SONG_STATUS_NEXT));
        registerReceiver(SONG_STATUS_PREVIOUS, new IntentFilter(Constant.SONG_STATUS_PREVIOUS));
        registerReceiver(OPTION_RESPONSE, new IntentFilter("OPTION_RESPONSE"));
        registerReceiver(OPTION_MENU, new IntentFilter("OPTION_MENU"));
        registerReceiver(PLAY_DURATION_DIALOG, new IntentFilter(Constant.PLAY_DURATION_DIALOG));

        GetIpAddressAsynTask asynGetIpAddress = new GetIpAddressAsynTask(this, this);
        asynGetIpAddress.execute();


        if (Util.favorite_clicked == true) {
            MultiPartView();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE && resultCode == RESULT_OK) {

            {
                getVideoInfo();
            }


        } else if (requestCode == RESUME_WATCH_LASTSEEN_REQUESTCODE && resultCode == RESULT_OK) {
            getLastSeenDetails();

        } else if (requestCode == PAYMENT_REQUESTCODE && resultCode == RESULT_OK) {
            getVideoInfo();

        } else if (requestCode == VIDEO_DOWNLOAD_BUTTON_CLICK_LOGIN_REG_REQUESTCODE && resultCode == RESULT_OK) {
            getVideoInfo();
        }
    }

    private class AsynAudioUserPlayListDetail extends AsyncTask<String, Void, Void> {
        JSONObject myJson = null;
        String responseStr;
        String sucessMsg;
        String playlist_name;
        private String playlist_poster;
        int count;
        private String title;

        @Override
        protected void onPreExecute() {

            try {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.hide();
                } else {
                    if (pDialog != null) {
                        pDialog.show();
                    }
                }
            } catch (Exception e) {
            }


        }

        @Override
        protected Void doInBackground(String... strings) {

            String urlRouteList = APIUrlConstant.AudioUserPlayListDetail.trim();

            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(urlRouteList);
                httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
                httppost.addHeader("authToken", authTokenStr.trim());
                httppost.addHeader("user_id", preferenceManager.getUseridFromPref());
                httppost.addHeader("list_id", PlayListId);
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    responseStr = EntityUtils.toString(response.getEntity());
                    Log.v("Nihar_flow", "Playlist details user id" + responseStr);

                } catch (org.apache.http.conn.ConnectTimeoutException e) {
                }
            } catch (Exception e) {
            }
            if (responseStr != null) {
                try {
                    myJson = new JSONObject(responseStr);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject jsonObject = myJson.getJSONObject("data");
                    customDatas.clear();
                    try {

                        if ((jsonObject.has("poster_playlist")) && jsonObject.optString("poster_playlist").trim() != null && !jsonObject.optString("poster_playlist").trim().isEmpty() && !jsonObject.optString("poster_playlist").trim().equals("null") && !jsonObject.optString("poster_playlist").trim().matches("")) {
                            playlist_poster = jsonObject.optString("poster_playlist");
                        }
                        if ((jsonObject.has("list_name")) && jsonObject.optString("list_name").trim() != null && !jsonObject.optString("list_name").trim().isEmpty() && !jsonObject.optString("list_name").trim().equals("null") && !jsonObject.optString("list_name").trim().matches("")) {
                            playlist_name = jsonObject.optString("list_name");
                        }

                        if ((jsonObject.has("counts")) && jsonObject.optString("counts").trim() != null && !jsonObject.optString("counts").trim().isEmpty() && !jsonObject.optString("counts").trim().equals("null") && !jsonObject.optString("counts").trim().matches("")) {
                            count = Integer.parseInt(jsonObject.optString("counts"));
                        }


                        JSONArray jsonListArray = jsonObject.optJSONArray("lists");
                        ItemListDetails = new ArrayList<>();
                        int jsonListArrayLength = jsonListArray.length();
                        for (int j = 0; j < jsonListArrayLength; j++) {
                            episode_details_output_model = new Episode_Details_output();
                            JSONObject jsonChildNode;
                            try {
                                jsonChildNode = jsonListArray.optJSONObject(j);

                                if ((jsonChildNode.has("title")) && jsonChildNode.optString("title").trim() != null && !jsonChildNode.optString("title").trim().isEmpty() && !jsonChildNode.optString("title").trim().equals("null") && !jsonChildNode.optString("title").trim().matches("")) {
                                    episode_details_output_model.setEpisode_title(jsonChildNode.optString("title"));
                                    episode_details_output_model.setName(jsonChildNode.optString("title"));
                                }
                                if ((jsonChildNode.has("cast")) && jsonChildNode.optString("cast").trim() != null && !jsonChildNode.optString("cast").trim().isEmpty() && !jsonChildNode.optString("cast").trim().equals("null") && !jsonChildNode.optString("cast").trim().matches("")) {
                                    MutiArtist = jsonChildNode.optString("cast");
                                }
                                if ((jsonChildNode.has("url")) && jsonChildNode.optString("url").trim() != null && !jsonChildNode.optString("url").trim().isEmpty() && !jsonChildNode.optString("url").trim().equals("null") && !jsonChildNode.optString("url").trim().matches("")) {
                                    episode_details_output_model.setVideo_url(jsonChildNode.optString("url"));
                                }
                                if ((jsonChildNode.has("audio_poster")) && jsonChildNode.optString("audio_poster").trim() != null && !jsonChildNode.optString("audio_poster").trim().isEmpty() && !jsonChildNode.optString("audio_poster").trim().equals("null") && !jsonChildNode.optString("audio_poster").trim().matches("")) {
                                    episode_details_output_model.setPoster_url(jsonChildNode.optString("audio_poster"));
                                }
                                if ((jsonChildNode.has("movie_id")) && jsonChildNode.optString("movie_id").trim() != null && !jsonChildNode.optString("movie_id").trim().isEmpty() && !jsonChildNode.optString("movie_id").trim().equals("null") && !jsonChildNode.optString("movie_id").trim().matches("")) {
                                    episode_details_output_model.setId(jsonChildNode.optString("movie_id"));
                                }
                                if ((jsonChildNode.has("movie_stream_id")) && jsonChildNode.optString("movie_stream_id").trim() != null && !jsonChildNode.optString("movie_stream_id").trim().isEmpty() && !jsonChildNode.optString("movie_stream_id").trim().equals("null") && !jsonChildNode.optString("movie_stream_id").trim().matches("")) {
                                    episode_details_output_model.setMuvi_uniq_id(jsonChildNode.optString("movie_stream_id"));
                                }

                                ItemListDetails.add(episode_details_output_model);

                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        }

                    } catch (Exception e) {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
                sucessMsg = myJson.optString("msg");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if (ItemListDetails != null && ItemListDetails.size() > 1) {
                /**
                 * Adding Translation for Play
                 */
                play_all.setVisibility(View.VISIBLE);
                play_all.setText(languagePreference.getTextofLanguage(PLAY_ALL, DEFAULT_PLAY_ALL));
            } else {
                play_all.setVisibility(View.GONE);

            }

            SongCount.setText(ItemListDetails.size() + " " + languagePreference.getTextofLanguage(TRACKS, DEFAULT_TRACKS));
            albumName_multipart.setText(PlayListName);
            if (playlist_poster != null && !playlist_poster.equals("")) {

            }
            pDialog.hide();
            populateMultiDataList(ItemListDetails);


        }
    }


    private void relatedContentAPICall(String movieIdStr) {
        RelatedContentInput relatedContentInput = new RelatedContentInput();
        relatedContentInput.setAuthToken(authTokenStr);
        relatedContentInput.setContent_stream_id(contentDetailsOutput.getMovieStreamId());
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

        GetRelatedContentAsynTask asyncRelatedContent = new GetRelatedContentAsynTask(relatedContentInput, this, AudioContentDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(AudioContentDetailsActivity.this));
        asyncRelatedContent.execute();

    }

    public void populateSinglePartAudioContent() {
        if (context != null) {
            adapter = new SinglePartAudioAdapter(context, contentDetailsOutput, this, containt_name);
            my_recycler_view.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            my_recycler_view.setAdapter(null);
            my_recycler_view.setAdapter(adapter);
        }
    }


    public void populateMultiDataList(ArrayList<Episode_Details_output> ItemListDetails) {
        multiAdapter = new AudioMultiPartAdapter(context, this, ItemListDetails, MutiArtist,
                "MultipartContent", posterimage, containt_name);
        my_recycler_view.setAdapter(multiAdapter);
    }


    private BroadcastReceiver PLAY_DURATION_DIALOG = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                audioCustomMiniController.closeMiniController();
                bootomsheet_open = false;
                Util.showPlaydurationMessage(AudioContentDetailsActivity.this, languagePreference);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private BroadcastReceiver OPTION_MENU = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {

            final String option_songNAme = intent.getStringExtra("option");
            String artist_opt = intent.getStringExtra("artist_opt");
            final String playlist_id = intent.getStringExtra("playlist_id");

            option_songName.setText(option_songNAme);
            option_artist_name.setText(artist_opt);

            mBottomSheetDialog.show();
        }
    };

    public void relatedContentItemClick(RelatedContentModel item) {
        String moviePermalink = item.getcPermalink();

        if (item.getEpisodeContentTypesId() == 3) {

            if (item.getIsEpisode() == 1) {
                // playChildEpisode(item);
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
            desired_string = moviePermalink;
            final Intent intent = new Intent(getApplicationContext(), AudioContentDetailsActivity.class);
            intent.putExtra("PERMALINK", desired_string);
            intent.putExtra("CONTENT_TYPE", "" + item.getEpisodeContentTypesId());
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            runOnUiThread(new Runnable() {
                public void run() {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
            });
        }


    }

    @Override
    public void onVideoDetailsPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onVideoDetailsPostExecuteCompleted(Video_Details_Output video_details_output, int code, String status, String message) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }
        if (code == 200) {

            song_url = video_details_output.getVideoUrl();
            if (isPlayAllButtonClicked) {
                isPlayAllButtonClicked = false;
                played_length = "0";
            } else {
                played_length = video_details_output.getPlayed_length();
            }

            if (QUEUE_ARRAY != null) {
                for (int i = 0; i < QUEUE_ARRAY.size(); i++) {
                    if (QUEUE_ARRAY.get(i).getSongName().trim().equalsIgnoreCase(song_name.trim())) {
                        QUEUE_ARRAY.get(i).setSongUrl(song_url);
                        break;
                    }
                }
            }


            playAudio();


        } else if (code == 436) {
            //no audio available
            Toast.makeText(AudioContentDetailsActivity.this, languagePreference.getTextofLanguage(NO_DETAILS_AVAILABLE, DEFAULT_NO_DETAILS_AVAILABLE), Toast.LENGTH_LONG).show();
        }
    }


    public void playAudio() {
        audioCustomMiniController.enabledisablenextprevious_btn_from_other_page(adapterposition, QUEUE_ARRAY);
        if (song_url == null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(AudioContentDetailsActivity.this, R.style.MyAlertDialogStyle);
            builder1.setMessage(Html.fromHtml("<font color='" + getResources().getColor(R.color.colorPrimary) + "'>" + languagePreference.getTextofLanguage(NO_DETAILS_AVAILABLE, DEFAULT_NO_DETAILS_AVAILABLE) + "</font>"));
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        } else {
            try {
                /*
                 * @desc:check if the player is PIP mode or not. If is in PIP mode stop player and start playing audio*/
                Intent j = new Intent(AudioContentDetailsActivity.this, MusicService.class);
                j.putExtra("ALBUM", song_url);
                j.putExtra("PLAYED_LENGTH", played_length);
                j.putExtra("PERMALINK", desired_string);
                j.putExtra("POSITION", Position);
                j.putExtra("ALBUM_ART", song_imageUrl);
                j.putExtra("ALBUM_NAME", containt_name);
                j.putExtra("Artist", artist_name);
                j.putExtra("ALBUM_SONG_NAME", song_name);
                j.putExtra("STATE", 1);
                if (content_types_id.equals("6")) { // for multipart
                    j.putExtra("MOVIE_UNIQUE_ID", movie_unique_id);
                    j.putExtra("MOVIE_STREAM_UNIQUE_ID", movie_stream_unique_id);
                } else if (content_types_id.equals("5")) { // for single part "episode_id" value will bw "0"
                    j.putExtra("MOVIE_UNIQUE_ID", movie_unique_id);
                    j.putExtra("MOVIE_STREAM_UNIQUE_ID", "0");
                }
                j.setAction(Constant.ACTION.STARTFOREGROUND_ACTION);
                this.startService(j);
            } catch (Exception e) {
                Log.v("", "" + e.getMessage());
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

        }

        if (relatedContentOutput.getContentData() == null)
            return;


        if (status == 200) {

            audioRelatedContentList = new ArrayList<RelatedContentModel>();
            videoRelatedContentList = new ArrayList<RelatedContentModel>();

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
                String movie_unique_id = relatedContentOutput.getContentData().get(a).getMovie_uniq_id();
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
            if (videoRelatedContentList.size() > 0) {
                relatedMoviesTv = findViewById(R.id.relatedMovies_tv);
                relatedMoviesTv.setVisibility(View.VISIBLE);
                relatedMoviesTv.setText(languagePreference.getTextofLanguage(RELATED_CONTENT_VIDEO_TITLE, DEFAULT_RELATED_CONTENT_VIDEO_TITLE));
                LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(AudioContentDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
                relateVideoRecyclerView.setLayoutManager(mLayoutManager1);
                relateVideoRecyclerView.setItemAnimator(new DefaultItemAnimator());

                new GetImageSize(AudioContentDetailsActivity.this, videoRelatedContentList.get(0).getEpisodeThumbnailImageView(), new GetImageSize.ImageSizeInterface() {
                    @Override
                    public void onImageLoaded(int width, int height) {
                        videoWidth = width;
                        videoHeight = height;
                        setRelatedContentAdapter("video");
                    }
                });

            }
            if (audioRelatedContentList.size() > 0) {
                relatedAudioTV.setVisibility(View.VISIBLE);
                relatedAudioTV.setText(languagePreference.getTextofLanguage(RELATED_CONTENT_AUDIO_TITLE, DEFAULT_RELATED_CONTENT_AUDIO_TITLE));
                LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(AudioContentDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);
                relatedAudioRecyclerview.setLayoutManager(mLayoutManager1);
                relatedAudioRecyclerview.setItemAnimator(new DefaultItemAnimator());

                new GetImageSize(AudioContentDetailsActivity.this, audioRelatedContentList.get(0).getEpisodeThumbnailImageView(), new GetImageSize.ImageSizeInterface() {
                    @Override
                    public void onImageLoaded(int width, int height) {
                        videoWidth = width;
                        videoHeight = height;
                        setRelatedContentAdapter("audio");
                    }
                });


            }

        }

    }

    private void setRelatedContentAdapter(String contentType) {
        if (videoRelatedContentList.size() > 0 && contentType.equals("video")) {
            RelatedContentAdapter relatedContentAdapter = new RelatedContentAdapter(AudioContentDetailsActivity.this, getItemLayoutId(), videoRelatedContentList, new RelatedContentAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(RelatedContentModel item) {
                    relatedContentItemClick(item);

                }
            });
            relateVideoRecyclerView.setAdapter(relatedContentAdapter);
        }
        if (audioRelatedContentList.size() > 0 && contentType.equals("audio")) {
            RelatedContentAdapter relatedContentAdapter = new RelatedContentAdapter(AudioContentDetailsActivity.this, getItemLayoutId(), audioRelatedContentList, new RelatedContentAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(RelatedContentModel item) {
                    relatedContentItemClick(item);

                }
            });

            relatedAudioRecyclerview.setAdapter(relatedContentAdapter);
        }


    }


    @Override
    public void onGetContentDetailsPreExecuteStarted() {
        if (pDialog != null) {
            pDialog.show();
        }
    }

    @Override
    public void onGetContentDetailsPostExecuteCompleted(ContentDetailsOutput response, int status, String message) {
        if (pDialog.isShowing()) {
            pDialog.hide();
        }
        if (status == 200) {

            contentDetailsOutput = response;
            String bannerimage = response.getBanner();
            posterimage = response.getPoster();
            containt_name = response.getName();
            String url = response.getMovieUrl();

            castStr = contentDetailsOutput.getCastStr();
            movieUniqueId = response.getMuviUniqId();
            movieNameStr = contentDetailsOutput.getName();
            Util.currencyModel = contentDetailsOutput.getCurrencyDetails();
            Util.apvModel = contentDetailsOutput.getApvDetails();
            Util.ppvModel = contentDetailsOutput.getPpvDetails();
            MutiArtist = contentDetailsOutput.getCast_detail();
            isPreorder = contentDetailsOutput.getIs_preorder();

            try {

                if (bannerimage != null && bannerimage.length() > 0) {
                    Picasso.with(context)
                            .load(bannerimage)
                            .error(R.drawable.no_image)
                            .into(banner_image);

                } else if (posterimage != null && posterimage.length() > 0) {
                    Picasso.with(context)
                            .load(posterimage)
                            .into(banner_image);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            albumName_multipart.setText(containt_name);
            if (contentDetailsOutput.getGenre() != null && contentDetailsOutput.getGenre().matches("") || contentDetailsOutput.getGenre().matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoGenreTextView.setVisibility(View.GONE);

            } else {
                videoGenreTextView.setVisibility(View.VISIBLE);
                Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                videoGenreTextView.setTypeface(videoGenreTextViewTypeface);

                videoGenreTextView.setText(contentDetailsOutput.getGenre());

            }
            if (contentDetailsOutput.getVideoDuration().matches("") || contentDetailsOutput.getVideoDuration().matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoDurationTextView.setVisibility(View.GONE);
            } else {

                Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                videoDurationTextView.setTypeface(videoGenreTextViewTypeface);

                videoDurationTextView.setText(contentDetailsOutput.getVideoDuration());
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
                {
                    Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                    videoReleaseDateTextView.setTypeface(videoGenreTextViewTypeface);
                }

                movieReleaseDateStr = Util.formateDateFromstring("yyyy-mm-dd", "yyyy", contentDetailsOutput.getReleaseDate());
                videoReleaseDateTextView.setText(movieReleaseDateStr);
            }
            if ((SongCount.getVisibility() == View.VISIBLE && videoDurationTextView.getVisibility() == View.VISIBLE)
                    || (SongCount.getVisibility() == View.VISIBLE && videoCensorRatingTextView.getVisibility() == View.VISIBLE) ||
                    (SongCount.getVisibility() == View.VISIBLE && videoCensorRatingTextView1.getVisibility() == View.VISIBLE)
                    || (SongCount.getVisibility() == View.VISIBLE && videoReleaseDateTextView.getVisibility() == View.VISIBLE)) {
                songcount_bar.setVisibility(View.VISIBLE);
            }

            if ((videoDurationTextView.getVisibility() == View.VISIBLE && videoCensorRatingTextView.getVisibility() == View.VISIBLE) ||
                    (videoDurationTextView.getVisibility() == View.VISIBLE && videoCensorRatingTextView1.getVisibility() == View.VISIBLE))
                censor_bar.setVisibility(View.VISIBLE);

            if ((videoDurationTextView.getVisibility() == View.VISIBLE || videoCensorRatingTextView.getVisibility() == View.VISIBLE
                    || videoCensorRatingTextView1.getVisibility() == View.VISIBLE) && videoReleaseDateTextView.getVisibility() == View.VISIBLE)
                year_bar.setVisibility(View.VISIBLE);

            if (castStr) {
                videoCastCrewTitleTextView.setText(languagePreference.getTextofLanguage(CAST_CREW_BUTTON_TITLE, DEFAULT_CAST_CREW_BUTTON_TITLE));
                FontUtls.loadFont(AudioContentDetailsActivity.this, getResources().getString(R.string.regular_fonts), videoCastCrewTitleTextView);
                videoCastCrewTitleTextView.setVisibility(View.VISIBLE);
            }
            if (contentDetailsOutput.getStory().matches("") || contentDetailsOutput.getStory().matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoStoryTextView.setVisibility(View.GONE);

            } else {
                videoStoryTextView.setVisibility(View.VISIBLE);
                Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                videoStoryTextView.setTypeface(videoGenreTextViewTypeface);
                videoStoryTextView.setText(Util.getTextViewTextFromApi(contentDetailsOutput.getStory()));
                ResizableCustomView.doResizeTextView(AudioContentDetailsActivity.this, videoStoryTextView, MAX_LINES, languagePreference.getTextofLanguage(VIEW_MORE, DEFAULT_VIEW_MORE), true);
            }

            if (Integer.parseInt(content_types_id) == 5) {

                ArrayList<ContentDetailsOutput> contentDetailsOutputArrayList = new ArrayList<ContentDetailsOutput>();
                SongCount.setText("1" + " " + languagePreference.getTextofLanguage(TRACK, DEFAULT_TRACK));

                /**
                 * @desc: Adding Translation for Play
                 */
                play_all.setVisibility(View.GONE);
                tracks.setVisibility(View.GONE);
                contentDetailsOutputArrayList.add(contentDetailsOutput);
                populateSinglePartAudioContent();
                relatedContentAPICall(contentDetailsOutput.getId());
            } else {


                if (!episode_api_called) {
                    episode_api_called = true;
                    episode_details_input = new Episode_Details_input();
                    episode_details_input.setAuthtoken(authTokenStr);
                    episode_details_input.setPermalink(desired_string);
                    episode_details_input.setUserid(userId);
                    episode_details_input.setOffset(Integer.toString(offset));
                    episode_details_input.setLimit(Integer.toString(limit));
                    episode_details_input.setCountry(preferenceManager.getCountryCodeFromPref());

                    GetEpisodeDeatailsAsynTask getEpisodeDeatailsAsynTask = new GetEpisodeDeatailsAsynTask(episode_details_input, this, AudioContentDetailsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(AudioContentDetailsActivity.this), false);
                    getEpisodeDeatailsAsynTask.execute();
                }

            }

        } else if (status == 414) {
            //handle for er
        }
    }


    @Override
    public void onGetEpisodeDetailsPreExecuteStarted() {
        if (pDialog != null) {
            pDialog.show();
        }
    }

    @Override
    public void onGetEpisodeDetailsPostExecuteCompleted(Episode_Details_output episodeDetailsOutPut, int status, int item_count, String message, String movieUniqueId) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }
        ArrayList<Episode_Details_output> playAllSongList = new ArrayList<Episode_Details_output>();

        MultiAndSingParentnameGlobal = episodeDetailsOutPut.getName();
        /*
         * @Desc: here it is checked for distingush the single track play or play all clicked.
         * */
        if (isSingleTrackSongClicked) {
            isSingleTrackSongClicked = false;

            QUEUE_ARRAY.clear();

            for (int j = 0; j < episodeDetailsOutPut.getEpisodeArray().size(); j++) {
                Episode_Details_output content = new Episode_Details_output();
                Episode_Details_output.Episode episode = episodeDetailsOutPut.getEpisodeArray().get(j);

                content.setEpisode_title(episode.getEpisode_title());
                content.setId(episode.getId());
                content.setPoster_url(episode.getPoster_url());
                content.setIsFree(episode.getIsFree());
                content.setIs_downloadable("" + episode.getDownloadStatus());
                content.setMovie_stream_uniq_id(episode.getMovie_stream_uniq_id());
                content.setMuvi_uniq_id(movieUniqueId);
                content.setVideo_url(episode.getVideo_url());
                content.setEpisodeNumber(episode.getEpisode_number());
                content.setSeriesNumber(episode.getSeries_number());
                content.setVideo_duration(episode.getVideo_duration());
                content.setIs_converted(Integer.parseInt(episode.getIs_converted()));
                content.setIs_media_published(episode.getIs_media_published());
                if (Integer.parseInt(episode.getIs_converted()) == 1 && episode.getIs_media_published().equals("1")) {
                    playAllSongList.add(content);
                }

            }
            for (int i = 0; i < playAllSongList.size(); i++) {
                QUEUE_ARRAY.add(new QueueModel(playAllSongList.get(i).getEpisode_title(), playAllSongList.get(i).getPoster_url(), playAllSongList.get(i).getVideo_url(), MutiArtist, "episode", contentDetailsOutput.getMuviUniqId(),
                        playAllSongList.get(i).getMovie_stream_uniq_id(), playAllSongList.get(i).getIs_converted(), episodeDetailsOutPut.getName()));
            }
        } else {
            /*
             *
             * @desc:if play all button is clicked simply add all the playlist to the episode_details_output array
             * */

            if (isPlayAllButtonClicked) {

                if (episodeDetailsOutPut != null && episodeDetailsOutPut.getEpisodeArray().size() > 0) {
                    for (int j = 0; j < episodeDetailsOutPut.getEpisodeArray().size(); j++) {
                        Episode_Details_output content = new Episode_Details_output();
                        Episode_Details_output.Episode episode = episodeDetailsOutPut.getEpisodeArray().get(j);

                        content.setEpisode_title(episode.getEpisode_title());
                        content.setId(episode.getId());
                        content.setPoster_url(episode.getPoster_url());
                        content.setIsFree(episode.getIsFree());
                        content.setIs_downloadable("" + episode.getDownloadStatus());
                        content.setMovie_stream_uniq_id(episode.getMovie_stream_uniq_id());
                        content.setMuvi_uniq_id(movieUniqueId);
                        content.setVideo_url(episode.getVideo_url());
                        content.setEpisodeNumber(episode.getEpisode_number());
                        content.setSeriesNumber(episode.getSeries_number());
                        content.setVideo_duration(episode.getVideo_duration());
                        content.setIs_converted(Integer.parseInt(episode.getIs_converted()));
                        content.setIs_media_published(episode.getIs_media_published());
                        if (Integer.parseInt(episode.getIs_converted()) == 1 && episode.getIs_media_published().equals("1")) {
                            playAllSongList.add(content);
                        }

                    }
                    setDataForPlayAll(playAllSongList, episodeDetailsOutPut.getName());
                }
            } else {

                try {
                    if (offset < 2) {
                        episode_details_output.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (episodeDetailsOutPut != null) {
                    Util.currencyModel = episodeDetailsOutPut.getCurrencyDetails();
                    Util.apvModel = episodeDetailsOutPut.getApvDetails();
                    Util.ppvModel = episodeDetailsOutPut.getPpvDetails();
                }
                itemCount = item_count;

                for (int j = 0; j < episodeDetailsOutPut.getEpisodeArray().size(); j++) {
                    Episode_Details_output content = new Episode_Details_output();
                    Episode_Details_output.Episode episode = episodeDetailsOutPut.getEpisodeArray().get(j);

                    content.setEpisode_title(episode.getEpisode_title());
                    content.setId(episode.getId());
                    content.setPoster_url(episode.getPoster_url());
                    content.setIsFree(episode.getIsFree());
                    content.setIs_downloadable("" + episode.getDownloadStatus());
                    content.setMovie_stream_uniq_id(episode.getMovie_stream_uniq_id());
                    content.setMuvi_uniq_id(movieUniqueId);
                    content.setVideo_url(episode.getVideo_url());
                    content.setEpisodeNumber(episode.getEpisode_number());
                    content.setSeriesNumber(episode.getSeries_number());
                    content.setVideo_duration(episode.getVideo_duration());
                    content.setIs_converted(Integer.parseInt(episode.getIs_converted()));
                    content.setIs_media_published(episode.getIs_media_published());

                    episode_details_output.add(content);
                }

                if (episode_details_output.size() != 0) {
                    if (episode_details_output != null && episode_details_output.size() > 1 && !episodeDetailsOutPut.isIs_cache_data()) {

                        play_all.setVisibility(View.VISIBLE);
                        play_all.setText(languagePreference.getTextofLanguage(PLAY_ALL, DEFAULT_PLAY_ALL));
                        tracks.setVisibility(View.VISIBLE);
                        tracks.setText(languagePreference.getTextofLanguage(TRACKS, DEFAULT_TRACKS));
                    } else {
                        play_all.setVisibility(View.GONE);

                    }

                    if (itemCount == 1) {
                        SongCount.setText(itemCount + " " + languagePreference.getTextofLanguage(TRACK, DEFAULT_TRACK));

                    } else if (itemCount > 1) {
                        SongCount.setText(itemCount + " " + languagePreference.getTextofLanguage(TRACKS, DEFAULT_TRACKS));

                    }
                    albumName_multipart.setText(containt_name);
                    my_recycler_view.setVisibility(View.VISIBLE);
                    my_recycler_view.setLayoutManager(mLayoutManager);
                    if (offset < 2) {

                        populateMultiDataList(episode_details_output);
                        if (itemCount > episode_details_output.size()) {
                            btnmore.setVisibility(View.VISIBLE);
                            setBtnmoreTranslationMore();
                        }

                    } else {

                        btnmore.setVisibility(View.GONE);
                        if (multiAdapter != null) {
                            multiAdapter.notifyDataSetChanged();
                        }

                        if (episode_details_output.size() == itemCount) {
                            setBtnmoreTranslationLess();
                        }
                    }
                    relatedContentAPICall(contentDetailsOutput.getId());
                }

            }
        }
    }

    /*
     * @desc:This method is to add all the songs in queue which we get from episodedetails api.*/
    private void setDataForPlayAll(ArrayList<Episode_Details_output> playAllSongList, String ParentName) {
        song_url = playAllSongList.get(0).getVideo_url();
        song_imageUrl = playAllSongList.get(0).getPoster_url();
        genere = playAllSongList.get(0).getGenre();
        song_name = playAllSongList.get(0).getEpisode_title();
        artist_name = MutiArtist;
        QUEUE_ARRAY.clear();
        for (int i = 0; i < playAllSongList.size(); i++) {
            QUEUE_ARRAY.add(new QueueModel(playAllSongList.get(i).getEpisode_title(), playAllSongList.get(i).getPoster_url(), playAllSongList.get(i).getVideo_url(), MutiArtist, "episode", contentDetailsOutput.getMuviUniqId(),
                    playAllSongList.get(i).getMovie_stream_uniq_id(), playAllSongList.get(i).getIs_converted(), ParentName));
        }
        Episode_Details_output item = playAllSongList.get(0);
        DataModel dbModel = new DataModel();
        dbModel.setIsFreeContent(Integer.parseInt(item.getIsFree()));
        dbModel.setIsAPV(item.getIsAPV());
        dbModel.setIsPPV(item.getIs_ppv());
        dbModel.setIsConverted(1);
        dbModel.setMovieUniqueId(movieUniqueId);
        dbModel.setStreamUniqueId(item.getMovie_stream_uniq_id());
        dbModel.setVideoTitle(contentDetailsOutput.getName());
        dbModel.setVideoGenre(item.getGenre());
        dbModel.setVideoDuration(item.getVideo_duration());
        dbModel.setVideoReleaseDate("");
        dbModel.setCensorRating(contentDetailsOutput.getCensorRating());
        dbModel.setCastCrew(contentDetailsOutput.getCastStr());
        dbModel.setEpisode_id(item.getMovie_stream_uniq_id());
        dbModel.setSeason_id("0");
        dbModel.setPurchase_type("episode");
        dbModel.setPosterImageId(item.getPoster_url());
        dbModel.setContentTypesId(Integer.parseInt(content_types_id));
        dbModel.setEpisode_series_no(item.getSeriesNumber());
        dbModel.setEpisode_no(item.getEpisodeNumber());
        dbModel.setEpisode_title(item.getEpisode_title());
        dbModel.setEpisode_no(item.getEpisodeNumber());
        dbModel.setEpisode_series_no(item.getSeriesNumber());
        Util.dataModel = dbModel;

        playerModel.setStreamUniqueId(item.getMovie_stream_uniq_id());
        playerModel.setMovieUniqueId(item.getMuvi_uniq_id());
        playerModel.setUserId(preferenceManager.getUseridFromPref());
        playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
        playerModel.setAuthTokenStr(authTokenStr.trim());
        playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH.trim());
        playerModel.setEpisode_id(item.getMovie_stream_uniq_id());
        playerModel.setVideoTitle(item.getEpisode_title());

        try {
            playerModel.setIsFreeContent(Integer.parseInt(item.getIsFree()));
        } catch (Exception e) {
        }

        playerModel.setVideoGenre(item.getGenre());
        playerModel.setVideoDuration(item.getVideo_duration());
        playerModel.setVideoReleaseDate("");
        playerModel.setCensorRating(contentDetailsOutput.getCensorRating());
        playerModel.setContentTypesId(Integer.parseInt(content_types_id));
        try {
            playerModel.setPosterImageId(item.getPoster_url());
        } catch (Exception e) {
            playerModel.setPosterImageId("https://d2gx0xinochgze.cloudfront.net/public/no-image-a.png");
        }
        playerModel.setCastCrew(contentDetailsOutput.getCastStr());

        checkAuthenticationForPlayAll(Integer.parseInt(playAllSongList.get(0).getIsFree()));
    }

    private void setBtnmoreTranslationMore() {
        if (btnmore != null) {

            btnmore.setText(languagePreference.getTextofLanguage(VIEW_MORE, DEFAULT_VIEW_MORE));
            btnmore.setBackground(null);
        }

    }

    public void stopUiFreezeTimer() {
        try {
            UI_FREEZE_TIMER.cancel();
            UI_FREEZE_TIMER = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @author Chitra
     * @decc: following method is responsible to get last seen content details.
     */
    public void getLastSeenDetails() {

        GetlastSeenDetailsInput getlastSeenDetailsInput = new GetlastSeenDetailsInput();
        getlastSeenDetailsInput.setAuthToken(authTokenStr);
        getlastSeenDetailsInput.setMuviUniqId(movieUniqueId);
        getlastSeenDetailsInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        getlastSeenDetailsInput.setLangCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        getlastSeenDetailsInput.setUserId(preferenceManager.getUseridFromPref());

        AsyncLastSeenDetails asyncLastSeenDetails = new AsyncLastSeenDetails(getlastSeenDetailsInput, AudioContentDetailsActivity.this, AudioContentDetailsActivity.this);
        asyncLastSeenDetails.execute();
    }


    private void showSnackBar(String message) {
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
    }

    @Override
    public void onIPAddressPreExecuteStarted() {

    }

    @Override
    public void onIPAddressPostExecuteCompleted(String message, int statusCode, String ipAddressStr) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }
        preferenceManager.setIpaddresstoPref(ipAddressStr);
    }

    @Override
    public void update(Observable observable, Object data) {
        Intent intent = (Intent) data;
        if (intent.hasExtra("IntentFor")) {
            audioCustomMiniController.playerDetails(intent);
        }

    }

    private void setBtnmoreTranslationLess() {
        if (btnmore != null) {
            btnmore.setText("");
            btnmore.setVisibility(View.VISIBLE);
            btnmore.setBackground(getResources().getDrawable(R.drawable.arrow_up_seasons));
            btnmore.getLayoutParams().height = 100;
            btnmore.getLayoutParams().width = 100;

            scroll = false; //  @desc : This indicates that after loading alldata when click scroll to top arrow , this value will check

        }
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
                final Intent searchIntent = new Intent(AudioContentDetailsActivity.this, SearchActivity.class);
                searchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(searchIntent);
                // Not implemented here
                return false;

            case R.id.action_login:

                Intent loginIntent = new Intent(AudioContentDetailsActivity.this, LoginActivity.class);
                check_for_subscription = 0;
                startActivity(loginIntent);
                // Not implemented here
                return false;
            case R.id.action_register:

                Intent registerIntent = new Intent(AudioContentDetailsActivity.this, RegisterActivity.class);
                check_for_subscription = 0;
                startActivity(registerIntent);
                // Not implemented here
                return false;


            case R.id.menu_item_profile:

                Intent profileIntent = new Intent(AudioContentDetailsActivity.this, ProfileActivity.class);
                profileIntent.putExtra("EMAIL", email);
                profileIntent.putExtra("LOGID", id);
                startActivity(profileIntent);
                // Not implemented here
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
                Intent loginIntent = new Intent(AudioContentDetailsActivity.this, LoginActivity.class);
                check_for_subscription = 0;
                startActivity(loginIntent);
                changeSortPopUp.dismiss();
                break;
            case R.id.register:
                Intent registerIntent = new Intent(AudioContentDetailsActivity.this, RegisterActivity.class);
                check_for_subscription = 0;
                startActivity(registerIntent);
                changeSortPopUp.dismiss();
                break;

            case R.id.profile:
                Intent profileIntent = new Intent(AudioContentDetailsActivity.this, ProfileActivity.class);
                profileIntent.putExtra("EMAIL", email);
                profileIntent.putExtra("LOGID", id);
                startActivity(profileIntent);
                changeSortPopUp.dismiss();
                break;

            case R.id.logout:
                changeSortPopUp.dismiss();
                break;

            default:
                break;


        }
    }

    private void initLayouts(View layout) {
        linearLayout = new LinearLayout[option_menu_id.length];
        for (int i = 0; i < option_menu_id.length; i++) {
            linearLayout[i] = (LinearLayout) layout.findViewById(option_menu_id[i]);
            setLanguageToTextViews(linearLayout[i], i);

        }

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

    private void initAudioCustomMiniController() {
        minicontroller = findViewById(R.id.bottomSheetLayout);
        //activityAudioPlayer = findViewById(R.id.activity_audio_player);
        seekbar_botomSht = (SeekBar) findViewById(R.id.miniController_seekbar);
        seekbar_botomSht.setPadding(0, 0, 0, 0);
        albumArt_player = (ImageView) findViewById(R.id.miniControl_play);
        open_bottomSheet_controller = (LinearLayout) findViewById(R.id.details);
        song_p_name = (TextView) findViewById(R.id.song_p_name);
        totalduration = (TextView) findViewById(R.id.totalduration);
        Artist_p_name = (TextView) findViewById(R.id.song_p_Genre);
        equalizer = (ImageView) findViewById(R.id.equalizer);
        background_player = (ImageView) findViewById(R.id.background_player);
        player_close = findViewById(R.id.player_close);
        songname_player = (TextView) findViewById(R.id.songname_player);
        mediaRouteButton = (MediaRouteButton) findViewById(R.id.media_route_button);
        player_image_main = (ImageView) findViewById(R.id.player_image_main);
        recylerview_progress = (ProgressBar) findViewById(R.id.progressBar);
        SquizeViewPager = (ViewPager) findViewById(R.id.viewPager1);
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

        audioCustomMiniController = new AudioCustomMiniController(AudioContentDetailsActivity.this, desired_string, getSupportFragmentManager());
        audioCustomMiniController.init(minicontroller,
                seekbar_botomSht,
                albumArt_player,
                open_bottomSheet_controller,
                song_p_name,
                Artist_p_name,
                equalizer,
                background_player,
                player_close
                , songname_player,
                player_image_main,
                recylerview_progress,
                SquizeViewPager,
                curent_duration,
                player_play_ic,
                player_next_icc,
                player_prev_ic,
                total_duration,
                seekBar, bottomSheet, mActionBarToolbar, miniControllerLayout, musicProgress, clearQueue);
    }

    boolean firstTime = true;
    private BroadcastReceiver SongStatusReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RecyclerView recyclerView = null;
            if (videoRelatedContentList.size() > 0 || audioRelatedContentList.size() > 0) {
                if (videoRelatedContentList.size() > 0 && audioRelatedContentList.size() > 0) {
                    //Util.setMargin(minicontroller,relatedAudioRecyclerview);
                    recyclerView = relatedAudioRecyclerview;
                } else if (videoRelatedContentList.size() > 0 && audioRelatedContentList.size() == 0) {
                    recyclerView = relateVideoRecyclerView;
                } else {
                    recyclerView = relatedAudioRecyclerview;
                }
            } else if (videoRelatedContentList.size() == 0 && audioRelatedContentList.size() == 0) {
                recyclerView = my_recycler_view;
            }
            Util.setMargin(minicontroller, recyclerView, AudioContentDetailsActivity.this);
            song_status = (intent.getStringExtra("songStatus")).trim();
            Log.v("CHITRA_SONG_STATTS", song_status);

            //Log.v("SONG_STATUS_MUVI", f"" + song_status);
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
                    Artist_p_name.setText(preferenceManager.getAudioDetailsFromPref(PreferenceManager.ARTIST_NAME));
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
                Intent stopServiceIntent = new Intent(AudioContentDetailsActivity.this, MusicService.class);
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

    private BroadcastReceiver CLOSE_NOTIFICATION = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                ViewGroup.MarginLayoutParams params =
                        (ViewGroup.MarginLayoutParams) relateVideoRecyclerView.getLayoutParams();
                params.setMargins(0, 6, 0, 0);
                relateVideoRecyclerView.setLayoutParams(params);
                ViewGroup.MarginLayoutParams params1 =
                        (ViewGroup.MarginLayoutParams) relatedAudioRecyclerview.getLayoutParams();
                params1.setMargins(0, 6, 0, 0);
                relatedAudioRecyclerview.setLayoutParams(params1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            audioCustomMiniController.closeMiniController();

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(SongStatusReciver);
            unregisterReceiver(CLOSE_NOTIFICATION);
            unregisterReceiver(SONG_STATUS_NEXT);
            unregisterReceiver(SONG_STATUS_PREVIOUS);
            unregisterReceiver(OPTION_MENU);
            unregisterReceiver(PLAY_DURATION_DIALOG);
        } catch (Exception e) {

        }
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


    private int getItemLayoutId() {

        /**
         * @description : New UI Change mantis: 19619
         */
        if (videoHeight > videoWidth) {
            return R.layout.list_card_related_portrait;
        }
        return R.layout.list_card_related_landscape;
    }


    /**
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


}
