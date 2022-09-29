package player.activity;

import static android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_XLARGE;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.google.android.exoplayer2.util.Util.SDK_INT;
import static com.google.android.exoplayer2.util.Util.getDrmUuid;
import static com.google.android.exoplayer2.util.Util.inferContentType;
import static com.home.vod.preferences.LanguagePreference.CANCEL_BUTTON;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CANCEL_BUTTON;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PLAYBACK_FAILED_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VIEW_MORE;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.PLAYBACK_FAILED_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.VIEW_MORE;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.FeatureHandler.APP_LOGO_ON_PLAYERPAGE_ENABLED;
import static com.home.vod.util.FeatureHandler.IS_DEFAULT_APP_LOGO_ON_PLAYERPAGE_ENABLED;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.HttpMediaDrmCallback;
import com.google.android.exoplayer2.drm.UnsupportedDrmException;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.DefaultTrackNameProvider;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.TrackNameProvider;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ErrorMessageProvider;
import com.google.android.exoplayer2.util.EventLogger;
import com.google.android.exoplayer2.util.MimeTypes;
import com.home.apisdk.apiController.GetMediaQueueAsyncTask;
import com.home.apisdk.apiModel.VideoPlaylistOutputModel;
import com.home.apisdk.apiModel.getMediaQueueInput;
import com.home.apisdk.apiModel.getMediaQueueOutput;
import com.home.vod.R;
import com.home.vod.base.VodApplication;
import com.home.vod.handlers.HandleOfflineInExoplayer;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.network.NetworkChangeReceiver;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.ui.widgets.ResizableCustomView;
import com.home.vod.util.FeatureHandler;
import com.intertrust.wasabi.media.PlaylistProxyListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import player.model.SubtitleModel;
import player.subtitle_support.Caption;
import player.subtitle_support.FormatSRT;
import player.subtitle_support.FormatSRT_WithoutCaption;
import player.subtitle_support.TimedTextObject;
import player.utils.DBHelper;
import player.utils.SensorOrientationChangeNotifier;
import player.utils.Util;


public class PlayerActivity extends AppCompatActivity implements
        SensorOrientationChangeNotifier.Listener,
        PlaylistProxyListener,
        PlaybackPreparer,
        PlayerControlView.VisibilityListener {

    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private static final CookieManager DEFAULT_COOKIE_MANAGER;

    static {
        DEFAULT_COOKIE_MANAGER = new CookieManager();
        DEFAULT_COOKIE_MANAGER.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
    }

    public static PlayerActivity playerActivity;
    public static String color = "#FB5B0A";
    private static final int MAX_LINES = 3;

    int surface_width;
    int surface_height;
    int player_width;
    int player_height;
    int playlist_position = 0;
    int position = 0;
    boolean seekbar_touched = false;
    int logTimerInterval = 2000;
    long total_video_duration = 0;
    boolean isLandscape = true;
    boolean onStopCalled = false;
    String click_type = "";
    String playlist_type = "";
    private int maxVolume;
    private int currentVolume;
    private float brightness;
    String lattest_subtitle_code = "";
    boolean isResolutionChange = false;
    String changed_resolutionUrl = "";
    private boolean startAutoPlay = true;
    private int startWindow;
    private long startPosition;
    String drmSchemeExtra;
    String drmLicenseUrl;
    long previous_frame_pos;
    int played_length = 0;
    int livestream_resume_time = 0;
    boolean player_is_in_forground = true;
    int player_start_time = 0;
    int player_current_postion = 0;
    String log_temp_id = "0";
    String emailIdStr = "";
    String mobileStr = "";
    String userIdStr = "";
    String movieId = "";
    String episodeId = "0";
    String videoLogId = "0";
    String restrict_stream_id = "0";
    int playerPosition = 0;
    boolean compressed = true, isFull_screen;
    boolean potrait_fullscreen = false;
    boolean video_completed = false;
    boolean landscape_fullscreen = false;
    int player_layout_height, player_layout_width;
    int screenWidth, screenHeight;
    boolean callPostMediaEnded = true;
    public boolean downloading;
    static String filename;
    String token = "";
    boolean callWithoutCaption = true;
    int selected_download_format = 0;
    int seekBarProgress = 0;
    boolean isDrm = false;
    String purchaseError = "";
    private boolean mResumeOnFocusGain = false;
    String ipAddressStr = "";
    int content_types_id = 0;
    boolean video_prepared = false;
    long previous_matching_time = -1, current_matching_time = -1;
    int current_played_length = 0;
    long cast_disconnected_position = 0;
    boolean resumeCheck = false;
    boolean firstTimeAudioVideoGet = true;
    boolean playerPaused = false;
    boolean pauseButtonClicked = false;
    boolean mPlaybackDelayed = false;
    boolean mPlaybackNowAuthorized = false;
    String alwaysPlayBestResolution, resolutionQuality = "";
    String notifyStreamingOnMobileData = "1";
    public static boolean isExternaliLinkClicked = false;
    private PlayerView playerView;
    private LinearLayout new_detailsLayout;
    private ImageButton back;
    private LinearLayout back_layout;
    private ImageView compress_expand;
    private ProgressBar progressView;
    private LinearLayout primary_ll, last_ll, bottomLayout;
    private RelativeLayout player_main_layout_controller;
    private RelativeLayout player_layout;
    private LinearLayout applogo_layout;
    private ImageView applogo_imageview;
    private ImageView thumbnailPreview;
    TextView videoTitle, GenreTextView, videoDurationTextView, storyTextView;
    ImageView info_icon;
    TextView subtitleText;
    LinearLayout mini_controller_layout;
    View view;
    TextView exo_position, exo_duration;
    LinearLayout play_pause_layout;
    ImageView mediaPoster, mediaStatusButton, retryPlayback, live_icon;
    RelativeLayout autoplay_button_layout, playback_failed_layout;
    TextView mediaStatus, mediaTitle, mediaCancel, playback_msg;
    ProgressBar mediaProgress;
    View PlayButton, PauseButton, MediaLoadingScreen;

    private NetworkChangeReceiver networkChangeReceiver = null;
    private AudioManager audioManager;
    private DataSource.Factory mediaDataSourceFactory;
    public static SimpleExoPlayer player;
    private DrmSessionManager drmSessionManager = null;
    private FrameworkMediaDrm mediaDrm;
    private DefaultTrackSelector trackSelector;
    private DefaultTrackSelector.Parameters trackSelectorParameters;
    private Timer MovableTimer;
    private DBHelper dbHelper;
    private Player playerModel, nextPlayerModel, prevPLayerModel;

    ArrayList<VideoPlaylistOutputModel> all_playlist;
    ArrayList<String> adDetails = new ArrayList<>();
    ArrayList<String> SubTitleName = new ArrayList<>();
    ArrayList<String> SubTitlePath = new ArrayList<>();
    ArrayList<String> ResolutionFormat = new ArrayList<>();
    ArrayList<String> ResolutionUrl = new ArrayList<>();
    ArrayList<Integer> ResolutionNew = new ArrayList<>();
    public static byte[] offlineKey;
    ArrayList<String> FakeSubTitlePath = new ArrayList<>();
    Uri[] uris;

    LanguagePreference languagePreference;
    PreferenceManager preferenceManager;
    FeatureHandler featureHandler;
    HandleOfflineInExoplayer handleOfflineInExoplayer;
    ProgressBarHandler mDialog;

    Timer timer;
    private Handler threadHandler = new Handler();
    private Handler mHandler = new Handler();
    private SubtitleProcessingTask subsFetchTask;
    public TimedTextObject srt;
    public Handler subtitleDisplayHandler;
    TimerTask timerTask;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    AudioFocusRequest mFocusRequest;
    AudioManager mAudioManager;
    final Object mFocusLock = new Object();

    File mediaStorageDir1;
    AsynGetIpAddress asynGetIpAddress;
    private DefaultTimeBar seekBar;


    /**
     * Desc: Following method is used to initialize the minicontroller.
     */
    public void initMinicontroller() {
        mini_controller_layout = findViewById(R.id.mini_controller_layout);
    }

    /*
     *
     * @Desc: This override method is used to called before the onCreate(),
     *       which help the resources to update Activity.
     * */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleLanguageHelper.onAttach(base));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerActivity = this;
        player = null;
        mediaDataSourceFactory = buildDataSourceFactory(true);
        if (CookieHandler.getDefault() != DEFAULT_COOKIE_MANAGER) {
            CookieHandler.setDefault(DEFAULT_COOKIE_MANAGER);
        }

        setContentView(R.layout.activity_player_new);
        hideSystemUI();

        languagePreference = LanguagePreference.getLanguagePreference(PlayerActivity.this);
        preferenceManager = PreferenceManager.getPreferenceManager(PlayerActivity.this);
        featureHandler = FeatureHandler.getFeaturePreference(PlayerActivity.this);

        playerModel = (Player) getIntent().getSerializableExtra("PlayerModel");
        nextPlayerModel = new Player();
        prevPLayerModel = new Player();

        playerView = findViewById(R.id.player_screen);

        live_icon = findViewById(R.id.live_icon);
        MediaLoadingScreen = findViewById(R.id.media_loading_screen);
        mediaPoster = MediaLoadingScreen.findViewById(R.id.media_poster);
        mediaStatusButton = MediaLoadingScreen.findViewById(R.id.media_status_button);
        autoplay_button_layout = MediaLoadingScreen.findViewById(R.id.autoplay_button_layout);
        mediaStatus = MediaLoadingScreen.findViewById(R.id.media_status);
        mediaTitle = MediaLoadingScreen.findViewById(R.id.media_title);
        mediaProgress = MediaLoadingScreen.findViewById(R.id.media_progress);
        mediaCancel = MediaLoadingScreen.findViewById(R.id.media_cancel);
        mediaCancel.setText(languagePreference.getTextofLanguage(CANCEL_BUTTON, DEFAULT_CANCEL_BUTTON));
        seekBar = playerView.findViewById(R.id.exo_progress);
        thumbnailPreview = findViewById(R.id.imageView);


        exo_position = playerView.findViewById(R.id.exo_position);
        exo_duration = playerView.findViewById(R.id.exo_duration);
        compress_expand = playerView.findViewById(R.id.max_min);
        play_pause_layout = playerView.findViewById(R.id.play_pause_layout);
        storyTextView = findViewById(R.id.story);
        playback_failed_layout = findViewById(R.id.playback_failed_layout);
        playback_msg = findViewById(R.id.playback_msg);
        retryPlayback = findViewById(R.id.retry);
        videoTitle = (TextView) findViewById(R.id.content_title);
        GenreTextView = (TextView) findViewById(R.id.genre);
        videoDurationTextView = (TextView) findViewById(R.id.video_duration);

        autoplay_button_layout.setVisibility(VISIBLE);
        mediaCancel.setVisibility(VISIBLE);

        // PLay pause click
        PlayButton = playerView.findViewById(R.id.exo_play);
        PauseButton = playerView.findViewById(R.id.exo_pause);



        /*
         * @desc: The below condition is for play all functionality of Video Playlist & when click any content from video play list the previous next content data will set here.
         * If the video play list has more than one content so the below condition will save the next data of the content to play next.
         **/

        all_playlist = (ArrayList<VideoPlaylistOutputModel>) getIntent().getSerializableExtra("VideoPlaylist");
        click_type = getIntent().getStringExtra("click_type");
        playlist_type = getIntent().getStringExtra("playlist_type");

        if (all_playlist != null) {
            if (all_playlist.size() > 1) {

                if (click_type != null && click_type.equals("play_all")) {
                    com.home.apisdk.apiModel.getMediaQueueOutput.NextMediaInfo info = new getMediaQueueOutput.NextMediaInfo();
                    info.setContentTitle(all_playlist.get(1).getTitle());
                    info.setContentTypesId(all_playlist.get(1).getContent_types_id());
                    info.setContentUniqId(all_playlist.get(1).getMovie_uniqe_id());
                    info.setStreamUniqId(all_playlist.get(1).getMovie_stream_uniq_id());
                    info.setDefaultPoster(all_playlist.get(1).getPoster_url());
                    info.setDescription(all_playlist.get(1).getStory());
                    info.setDuration("");
                    info.setEpisodeNo(all_playlist.get(1).getEpisode_number());
                    info.setSeasonNo(all_playlist.get(1).getSeries_number());
                    playerModel.setNextItem(info);

                    nextPlayerModel.setPurchased(true);
                    nextPlayerModel.setVideoTitle(all_playlist.get(1).getTitle());
                    nextPlayerModel.setVideoStory(all_playlist.get(1).getStory());
                    nextPlayerModel.setVideoDuration("");
                    nextPlayerModel.setStreamUniqueId(all_playlist.get(1).getMovie_stream_uniq_id());
                    nextPlayerModel.setMovieUniqueId(all_playlist.get(1).getMovie_uniqe_id());

                    nextPlayerModel.setEpisode_id(all_playlist.get(1).getEpisode_number());
                    nextPlayerModel.setStreamUniqueId(all_playlist.get(1).getMovie_stream_uniq_id());

                } else if (click_type != null && click_type.equals("content")) {
                    try {
                        position = getIntent().getIntExtra("playlist_position", position);
                        playlist_position = position;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    if ((position != 0) && (position != all_playlist.size() - 1)) {
                        getMediaQueueOutput getMediaQueueOutput = new getMediaQueueOutput();
                        // set next model
                        com.home.apisdk.apiModel.getMediaQueueOutput.NextMediaInfo info = new getMediaQueueOutput.NextMediaInfo();
                        info.setContentTitle(all_playlist.get(position + 1).getTitle());
                        info.setContentTypesId(all_playlist.get(position + 1).getContent_types_id());
                        info.setContentUniqId(all_playlist.get(position + 1).getMovie_uniqe_id());
                        info.setStreamUniqId(all_playlist.get(position + 1).getMovie_stream_uniq_id());
                        info.setDefaultPoster(all_playlist.get(position + 1).getPoster_url());
                        info.setDescription(all_playlist.get(position + 1).getStory());
                        info.setDuration("");
                        info.setEpisodeNo(all_playlist.get(position + 1).getEpisode_number());
                        info.setSeasonNo(all_playlist.get(position + 1).getSeries_number());
                        playerModel.setNextItem(info);


                        // set previous model
                        com.home.apisdk.apiModel.getMediaQueueOutput.NextMediaInfo previnfo = new getMediaQueueOutput.NextMediaInfo();
                        previnfo.setContentTitle(all_playlist.get(position - 1).getTitle());
                        previnfo.setContentTypesId(all_playlist.get(position - 1).getContent_types_id());
                        previnfo.setContentUniqId(all_playlist.get(position - 1).getMovie_uniqe_id());
                        previnfo.setStreamUniqId(all_playlist.get(position - 1).getMovie_stream_uniq_id());
                        previnfo.setDefaultPoster(all_playlist.get(position - 1).getPoster_url());
                        previnfo.setDescription(all_playlist.get(position - 1).getStory());
                        previnfo.setDuration("");
                        previnfo.setEpisodeNo(all_playlist.get(position - 1).getEpisode_number());
                        previnfo.setSeasonNo(all_playlist.get(position - 1).getSeries_number());
                        getMediaQueueOutput.setPreviousMediaInfo(previnfo);
                        playerModel.setPrevItem(previnfo);

                    } else if (position == 0) {
                        com.home.apisdk.apiModel.getMediaQueueOutput.NextMediaInfo info = new getMediaQueueOutput.NextMediaInfo();
                        info.setContentTitle(all_playlist.get(1).getTitle());
                        info.setContentTypesId(all_playlist.get(1).getContent_types_id());
                        info.setContentUniqId(all_playlist.get(1).getMovie_uniqe_id());
                        info.setStreamUniqId(all_playlist.get(1).getMovie_stream_uniq_id());
                        info.setDefaultPoster(all_playlist.get(1).getPoster_url());
                        info.setDescription(all_playlist.get(1).getStory());
                        info.setDuration("");
                        info.setEpisodeNo(all_playlist.get(1).getEpisode_number());
                        info.setSeasonNo(all_playlist.get(1).getSeries_number());
                        playerModel.setNextItem(info);

                        nextPlayerModel.setPurchased(true);

                        nextPlayerModel.setVideoTitle(all_playlist.get(1).getTitle());
                        nextPlayerModel.setVideoStory(all_playlist.get(1).getStory());
                        nextPlayerModel.setVideoDuration("");
                        nextPlayerModel.setStreamUniqueId(all_playlist.get(1).getMovie_stream_uniq_id());
                        nextPlayerModel.setMovieUniqueId(all_playlist.get(1).getMovie_uniqe_id());

                        nextPlayerModel.setEpisode_id(all_playlist.get(1).getEpisode_number());
                        nextPlayerModel.setStreamUniqueId(all_playlist.get(1).getMovie_stream_uniq_id());


                    } else if (position == all_playlist.size() - 1) {

                        com.home.apisdk.apiModel.getMediaQueueOutput.NextMediaInfo info = new getMediaQueueOutput.NextMediaInfo();
                        info.setContentTitle(all_playlist.get(position - 1).getTitle());
                        info.setContentTypesId(all_playlist.get(position - 1).getContent_types_id());
                        info.setContentUniqId(all_playlist.get(position - 1).getMovie_uniqe_id());
                        info.setStreamUniqId(all_playlist.get(position - 1).getMovie_stream_uniq_id());
                        info.setDefaultPoster(all_playlist.get(position - 1).getPoster_url());
                        info.setDescription(all_playlist.get(position - 1).getStory());
                        info.setDuration("");
                        info.setEpisodeNo(all_playlist.get(position - 1).getEpisode_number());
                        info.setSeasonNo(all_playlist.get(position - 1).getSeries_number());
                        playerModel.setPrevItem(info);

                    }
                }
            }
        }


        view = findViewById(android.R.id.content);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Configuration config = getResources().getConfiguration();
            if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                //in Right To Left layout
                mediaProgress.setRotation(0);
            }

        } else {
            mini_controller_layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }


        /**
         * @description For Checking internet connection
         */

        notifyStreamingOnMobileData = preferenceManager.getNotifyStreamingOnMobileDataFromPref();


        /**
         * @description For check for sharedpreference value for  alwaysPlayBestResolution
         */
        alwaysPlayBestResolution = preferenceManager.getPlayBestResolutionFromPref();
        if (alwaysPlayBestResolution.equals("1")) {
            resolutionQuality = "BEST";
            Util.VideoResolution = "BEST";
        } else {
            resolutionQuality = playerModel.getVideoResolution() + "p";
            Util.VideoResolution = playerModel.getVideoResolution() + "p";
        }


        if (!playerModel.getVideoUrl().trim().equals("")) {
            if (playerModel.isThirdPartyPlayer()) {

                if (playerModel.getVideoUrl().contains("://www.youtube")
                        || playerModel.getVideoUrl().contains("://www.youtu.be")) {

                    return;
                } else {
                    final Intent playVideoIntent = new Intent(PlayerActivity.this, ThirdPartyPlayer.class);
                    playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    playVideoIntent.putExtra("PlayerModel", playerModel);
                    startActivity(playVideoIntent);
                    finish();
                    return;

                }
            }
        } else {
            backCalled();
        }


        initViews();

        playerView.setControllerVisibilityListener(PlayerActivity.this);
        playerView.setErrorMessageProvider(new PlayerErrorMessageProvider());
        playerView.requestFocus();


        if (pauseButtonClicked) {
            PlayButton.setVisibility(VISIBLE);
            PauseButton.setVisibility(GONE);
        } else {
            PlayButton.setVisibility(GONE);
            PauseButton.setVisibility(VISIBLE);
        }


        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekbar_touched = true;
                {
                    /*
                     *
                     * @Desc: This is added to handle the situation when user play a video content
                     *       and later pressed the power button to pause.
                     * */
                    if (onStopCalled) {

                        firstTimeAudioVideoGet = true;
                        played_length = preferenceManager.getTemporaryPlayLeangth();
                        player = null;
                        try {
                            initializePlayer();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        startPlayer();
                    }

                }
                PlayButton.setVisibility(GONE);
                PauseButton.setVisibility(VISIBLE);
            }
        });

        PauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekbar_touched = true;
                {
                    pauseButtonClicked = true;
                    playerPaused = true;
                    pausePlayer();
                }
                PlayButton.setVisibility(VISIBLE);
                PauseButton.setVisibility(GONE);

            }
        });

        retryPlayback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    playback_failed_layout.setVisibility(GONE);
                    playerView.setVisibility(VISIBLE);
                    playerView.requestFocus();
                    playerView.setEnabled(true);
                    firstTimeAudioVideoGet = true;
                    player = null;
                    initializePlayer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Register the broadcast receiver with the intent filter object.
        if (notifyStreamingOnMobileData.equals("1")) {
            IntentFilter intentFilter = new IntentFilter();

            // Add network connectivity change action.
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

            // Set broadcast receiver priority.
            intentFilter.setPriority(100);

            // Create a network change broadcast receiver.
            networkChangeReceiver = new NetworkChangeReceiver();
            try {
                registerReceiver(networkChangeReceiver, intentFilter);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            onResume();
        }
    }


    private void changeMedia(Player model, String type) {
        if (model.getVideoUrl().trim().equals("") && !type.equalsIgnoreCase("state_end")) {

            try {
                mDialog = new ProgressBarHandler(PlayerActivity.this);
                mDialog.show();
            } catch (Exception e) {
                e.toString();
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    if (!model.getVideoUrl().trim().equals("")) {
                        timer.cancel();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                allowToPlay(model, type);
                                try {
                                    mDialog.cancel();
                                } catch (Exception e) {
                                    e.toString();
                                }

                            }
                        });

                    }
                }
            }, 0, 1000);
        } else {
            allowToPlay(model, type);
        }

    }

    private void allowToPlay(Player model, String type) {

        seekbar_touched = false;
        resumeCheck = false;

        initVideoDetails(model);

        if (model.getIs_playduration_enabled() == 1) {
            com.home.vod.util.Util.playDuration_enabled = 1;
        } else {
            com.home.vod.util.Util.playDuration_enabled = 0;
        }
        if (type.equalsIgnoreCase("state_end")) {
            showMediaLoadingScreen(type);
        }
        firstTimeAudioVideoGet = true;
        Util.VideoWidth = 0;
        Util.VideoHeight = 0;
        Util.VideoResolution = "BEST";

        playerModel = new Player();
        playerModel = model;

        nextPlayerModel = new Player();
        prevPLayerModel = new Player();
        //re-initialize played length for next content
        played_length = 0;
        //code end

        if (type.equalsIgnoreCase("state_end")) {
            releasePlayer();

        } else {

            resumeCheck = false;
            playerView.setVisibility(VISIBLE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            player_layout.setLayoutParams(params);

            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            playerView.setLayoutParams(params1);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            playerView.setMinimumHeight((int) getResources().getDimension(R.dimen.player_relativlayout_height));
            player_layout.setMinimumHeight((int) getResources().getDimension(R.dimen.player_relativlayout_height));

            releasePlayer();
            try {
                initializePlayer();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        /*
         * @desc:The below condition is used for play all functionality of Video play list*/

        try {
            if (all_playlist != null && all_playlist.size() > 1) {

                if ((playlist_position < all_playlist.size()) && (playlist_position != all_playlist.size() - 1) && (playlist_position != 0)) {
                    if (type.equalsIgnoreCase("next") || type.equalsIgnoreCase("state_end")) {
                        getMediaQueueOutput getMediaQueueOutput = new getMediaQueueOutput();
                        // set next model
                        com.home.apisdk.apiModel.getMediaQueueOutput.NextMediaInfo info = new getMediaQueueOutput.NextMediaInfo();
                        info.setContentTitle(all_playlist.get(playlist_position + 1).getTitle());
                        info.setContentTypesId(all_playlist.get(playlist_position + 1).getContent_types_id());
                        info.setContentUniqId(all_playlist.get(playlist_position + 1).getMovie_uniqe_id());
                        info.setStreamUniqId(all_playlist.get(playlist_position + 1).getMovie_stream_uniq_id());
                        info.setDefaultPoster(all_playlist.get(playlist_position + 1).getPoster_url());
                        info.setDescription(all_playlist.get(playlist_position + 1).getStory());
                        info.setDuration("");
                        info.setEpisodeNo(all_playlist.get(playlist_position + 1).getEpisode_number());
                        info.setSeasonNo(all_playlist.get(playlist_position + 1).getSeries_number());
                        playerModel.setNextItem(info);

                        // set previous model
                        com.home.apisdk.apiModel.getMediaQueueOutput.NextMediaInfo previnfo = new getMediaQueueOutput.NextMediaInfo();
                        previnfo.setContentTitle(all_playlist.get(playlist_position - 1).getTitle());
                        previnfo.setContentTypesId(all_playlist.get(playlist_position - 1).getContent_types_id());
                        previnfo.setContentUniqId(all_playlist.get(playlist_position - 1).getMovie_uniqe_id());
                        previnfo.setStreamUniqId(all_playlist.get(playlist_position - 1).getMovie_stream_uniq_id());
                        previnfo.setDefaultPoster(all_playlist.get(playlist_position - 1).getPoster_url());
                        previnfo.setDescription(all_playlist.get(playlist_position - 1).getStory());
                        previnfo.setDuration("");
                        previnfo.setEpisodeNo(all_playlist.get(playlist_position - 1).getEpisode_number());
                        previnfo.setSeasonNo(all_playlist.get(playlist_position - 1).getSeries_number());
                        getMediaQueueOutput.setPreviousMediaInfo(previnfo);
                        playerModel.setPrevItem(previnfo);

                    } else {
                        getMediaQueueOutput.NextMediaInfo nextinfo = new getMediaQueueOutput.NextMediaInfo();
                        nextinfo.setContentTitle(all_playlist.get(playlist_position + 1).getTitle());
                        nextinfo.setContentTypesId(all_playlist.get(playlist_position + 1).getContent_types_id());
                        nextinfo.setContentUniqId(all_playlist.get(playlist_position + 1).getMovie_uniqe_id());
                        nextinfo.setStreamUniqId(all_playlist.get(playlist_position + 1).getMovie_stream_uniq_id());
                        nextinfo.setDefaultPoster(all_playlist.get(playlist_position + 1).getPoster_url());
                        nextinfo.setDescription(all_playlist.get(playlist_position + 1).getStory());
                        nextinfo.setDuration("");
                        nextinfo.setEpisodeNo(all_playlist.get(playlist_position + 1).getEpisode_number());
                        nextinfo.setSeasonNo(all_playlist.get(playlist_position + 1).getSeries_number());
                        playerModel.setNextItem(nextinfo);

                        // set previous model
                        getMediaQueueOutput.NextMediaInfo info = new getMediaQueueOutput.NextMediaInfo();
                        info.setContentTitle(all_playlist.get(playlist_position - 1).getTitle());
                        info.setContentTypesId(all_playlist.get(playlist_position - 1).getContent_types_id());
                        info.setContentUniqId(all_playlist.get(playlist_position - 1).getMovie_uniqe_id());
                        info.setStreamUniqId(all_playlist.get(playlist_position - 1).getMovie_stream_uniq_id());
                        info.setDefaultPoster(all_playlist.get(playlist_position - 1).getPoster_url());
                        info.setDescription(all_playlist.get(playlist_position - 1).getStory());
                        info.setDuration("");
                        info.setEpisodeNo(all_playlist.get(playlist_position - 1).getEpisode_number());
                        info.setSeasonNo(all_playlist.get(playlist_position - 1).getSeries_number());
                        playerModel.setPrevItem(info);
                    }

                } else {
                    if (playlist_position == all_playlist.size() - 1) {


                        com.home.apisdk.apiModel.getMediaQueueOutput.NextMediaInfo info = new getMediaQueueOutput.NextMediaInfo();
                        info.setContentTitle(all_playlist.get(playlist_position - 1).getTitle());
                        info.setContentTypesId(all_playlist.get(playlist_position - 1).getContent_types_id());
                        info.setContentUniqId(all_playlist.get(playlist_position - 1).getMovie_uniqe_id());
                        info.setStreamUniqId(all_playlist.get(playlist_position - 1).getMovie_stream_uniq_id());
                        info.setDefaultPoster(all_playlist.get(playlist_position - 1).getPoster_url());
                        info.setDescription(all_playlist.get(playlist_position - 1).getStory());
                        info.setDuration("");
                        info.setEpisodeNo(all_playlist.get(playlist_position - 1).getEpisode_number());
                        info.setSeasonNo(all_playlist.get(playlist_position - 1).getSeries_number());
                        playerModel.setPrevItem(info);

                    } else if (playlist_position == 0) {

                        getMediaQueueOutput.NextMediaInfo info = new getMediaQueueOutput.NextMediaInfo();
                        info.setContentTitle(all_playlist.get(playlist_position + 1).getTitle());
                        info.setContentTypesId(all_playlist.get(playlist_position + 1).getContent_types_id());
                        info.setContentUniqId(all_playlist.get(playlist_position + 1).getMovie_uniqe_id());
                        info.setStreamUniqId(all_playlist.get(playlist_position + 1).getMovie_stream_uniq_id());
                        info.setDefaultPoster(all_playlist.get(playlist_position + 1).getPoster_url());
                        info.setDescription(all_playlist.get(playlist_position + 1).getStory());
                        info.setDuration("");
                        info.setEpisodeNo(all_playlist.get(playlist_position + 1).getEpisode_number());
                        info.setSeasonNo(all_playlist.get(playlist_position + 1).getSeries_number());

                        playerModel.setNextItem(info);
                    }
                }


                if (all_playlist.size() > 1)
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getMediaQueueInput mediaQueueInput = new getMediaQueueInput();
        mediaQueueInput.setAuthToken(authTokenStr);
        mediaQueueInput.setStream_uniq_id(model.getEpisode_id());
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
                try {
                    if (status == 200) {
                        playerModel.setNextItem(getMediaQueueOutput.getNextMediaInfo());
                        playerModel.setPrevItem(getMediaQueueOutput.getPreviousMediaInfo());
                        //set content details info


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, PlayerActivity.this);
        mediaQueueAsyncTask.execute();

    }

    private void showMediaLoadingScreen(String type) {

        if (progressView != null && progressView.getVisibility() == VISIBLE) {
            progressView.setVisibility(GONE);
        }
        int maxTime = 7000;
        if (type.equalsIgnoreCase("next") || type.equalsIgnoreCase("state_end")) {
            Glide.with(getApplicationContext())
                    .load(playerModel.getNextItem().getDefaultPoster())
                    .thumbnail(0.1f).placeholder(R.drawable.logo)
                    .into(mediaPoster);
            mediaStatus.setText("Up Next");
            mediaTitle.setText(playerModel.getNextItem().getContentTitle());
            mediaStatusButton.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.player_next));
        } else if (type.equalsIgnoreCase("prev")) {
            Glide.with(getApplicationContext())
                    .load(playerModel.getPrevItem().getDefaultPoster())
                    .thumbnail(0.1f)
                    .into(mediaPoster);
            mediaStatus.setText("Up Previous");
            mediaTitle.setText(playerModel.getPrevItem().getContentTitle());
            mediaStatusButton.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.player_previous));
        }
        playerView.setVisibility(GONE);
        MediaLoadingScreen.setVisibility(VISIBLE);
        ObjectAnimator animation = ObjectAnimator.ofInt(mediaProgress, "progress", 0, 100);
        mediaProgress.setMax(100);
        animation.setDuration(maxTime);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                MediaLoadingScreen.setVisibility(GONE);
                resumeCheck = false;
                playerView.setVisibility(VISIBLE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                player_layout.setLayoutParams(params);

                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                playerView.setLayoutParams(params1);

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                playerView.setMinimumHeight((int) getResources().getDimension(R.dimen.player_relativlayout_height));
                player_layout.setMinimumHeight((int) getResources().getDimension(R.dimen.player_relativlayout_height));
                try {
                    initializePlayer();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                MediaLoadingScreen.setVisibility(GONE);
                playerView.setVisibility(VISIBLE);
                resumeCheck = false;
                try {
                    initializePlayer();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animation.start();


        mediaCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation.cancel();
                finish();
            }
        });
        mediaStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide card layout and info layout
                animation.cancel();
            }
        });
    }


    private void checkForAudioFocus() {
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int res = 0;
        mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                switch (focusChange) {
                    case AudioManager.AUDIOFOCUS_GAIN:
                        if (mPlaybackDelayed || mResumeOnFocusGain) {
                            synchronized (mFocusLock) {
                                mPlaybackDelayed = false;
                                mResumeOnFocusGain = false;
                            }
                            /**
                             * Uncomment the below line to support auto resume of the player
                             */
//                            startPlayer();
                        }
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        synchronized (mFocusLock) {
                            mResumeOnFocusGain = false;
                            mPlaybackDelayed = false;
                        }

                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        synchronized (mFocusLock) {
                            mResumeOnFocusGain = true;
                            mPlaybackDelayed = false;
                        }

                        pausePlayer();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        // ... pausing or ducking depends on your app
                        break;
                }
            }
        };

        AudioAttributes mPlaybackAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                    .setAudioAttributes(mPlaybackAttributes)
                    .setAcceptsDelayedFocusGain(true)
                    .setOnAudioFocusChangeListener(mOnAudioFocusChangeListener, mHandler)
                    .build();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            res = mAudioManager.requestAudioFocus(mFocusRequest);
        } else {
            res = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                    // Use the music stream.
                    AudioManager.STREAM_MUSIC,
                    // Request permanent focus.
                    AudioManager.AUDIOFOCUS_GAIN);
        }
        synchronized (mFocusLock) {
            if (res == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
                mPlaybackNowAuthorized = false;
            } else if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mPlaybackNowAuthorized = true;
                startPlayer();
            } else if (res == AudioManager.AUDIOFOCUS_REQUEST_DELAYED) {
                mPlaybackDelayed = true;
                mPlaybackNowAuthorized = false;
            }
        }

    }

    private void pausePlayer() {
        try {
            updateStartPosition();
            player.setPlayWhenReady(false);
            player.getPlaybackState();

            PlayButton.setVisibility(VISIBLE);
            PauseButton.setVisibility(GONE);

            playerPaused = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startPlayer() {


        try {
            {
                if (player == null)
                    initializePlayer();
                else {
                    player.setPlayWhenReady(true);
                    player.getPlaybackState();
                    playerPaused = false;
                }
            }
            if (playerView.findViewById(R.id.bottomLayout).getVisibility() == View.INVISIBLE) {
                playerView.findViewById(R.id.bottomLayout).setVisibility(VISIBLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        content_types_id = playerModel.getContentTypesId();
        /**
         * @Desc TO show prev or next as per availability of the media in queue
         */


        /**
         * @desc to hide end-time,play,pause when live stream content
         */
        if (content_types_id == 4) {

            exo_duration.setVisibility(GONE);
            play_pause_layout.setVisibility(View.INVISIBLE);

            seekBar.setPlayedColor(getResources().getColor(R.color.button_background));
            seekBar.setBufferedColor(getResources().getColor(R.color.button_background));
            seekBar.setUnplayedColor(getResources().getColor(R.color.button_background));
            seekBar.setScrubberColor(getResources().getColor(R.color.transparent));

            //Livestream resume time
            livestream_resume_time = playerModel.getLivestream_resume_time() * 1000;

        }

        played_length = playerModel.getPlayPos() * 1000;
        current_played_length = played_length;

        if (preferenceManager != null) {
            emailIdStr = preferenceManager.getEmailIdFromPref();
            mobileStr = preferenceManager.getDispPhoneFromPref();
            userIdStr = preferenceManager.getUseridFromPref();

            if (emailIdStr == null) {
                emailIdStr = "";
            }
            if (userIdStr == null) {
                userIdStr = "";
            }
            if (mobileStr == null) {
                mobileStr = "";
            }

        } else {
            emailIdStr = "";
            userIdStr = "";
            mobileStr = "";
        }

        dbHelper = new DBHelper(PlayerActivity.this);
        dbHelper.getWritableDatabase();
        new_detailsLayout = (LinearLayout) findViewById(R.id.new_detailsLayout);

        if (playerModel.getVideoUrl().matches("")) {
            backCalled();
        }
        movieId = playerModel.getMovieUniqueId();
        episodeId = playerModel.getEpisode_id();

        info_icon = findViewById(R.id.info);

        subtitleText = (TextView) findViewById(R.id.subtitle);
        if (playerModel.getSubTitleName() != null) {
            SubTitleName = playerModel.getSubTitleName();
        } else {
            SubTitleName.clear();
        }

        if (playerModel.getSubTitlePath() != null) {
            SubTitlePath = playerModel.getSubTitlePath();
        } else {
            SubTitlePath.clear();
        }


        if (!isDrm) {

            if (playerModel.getVideoResolution() != null) {
                if (playerModel.getVideoResolution().isEmpty() || playerModel.getVideoResolution().equals(""))
                    Util.VideoResolution = "BEST";
                else {
                    if (playerModel.getVideoResolution().trim().equals("BEST")) {
                        Util.VideoResolution = "BEST";
                    } else {
                        Util.VideoResolution = playerModel.getVideoResolution().trim() + "p";
                    }
                }
            } else
                Util.VideoResolution = "BEST";

            if (playerModel.getResolutionFormat() != null) {
                ResolutionFormat = playerModel.getResolutionFormat();
            } else {
                ResolutionFormat.clear();

            }

            if (playerModel.getResolutionUrl() != null) {
                ResolutionUrl = playerModel.getResolutionUrl();
            } else {
                ResolutionUrl.clear();
            }

        }

        if (!featureHandler.getFeatureStatus(FeatureHandler.IS_SUBTITLE, FeatureHandler.DEFAULT_IS_SUBTITLE)) {
            SubTitlePath.clear();
            playerModel.offline_url.clear();
        }

        if (!featureHandler.getFeatureStatus(FeatureHandler.IS_RESOLUTION, FeatureHandler.DEFAULT_IS_RESOLUTION)) {
            ResolutionUrl.clear();
        }


        try {
            if (SubTitleName.size() > 0)
                Util.DefaultSubtitle = SubTitleName.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        player_layout = (RelativeLayout) findViewById(R.id.player_layout);
        try {
            player_layout_height = player_layout.getHeight();
            player_layout_width = player_layout.getWidth();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initVideoDetails(playerModel);
        primary_ll = (LinearLayout) playerView.findViewById(R.id.primary_ll);
        player_main_layout_controller = (RelativeLayout) playerView.findViewById(R.id.player_main_layout);
        bottomLayout = (LinearLayout) playerView.findViewById(R.id.bottomLayout);
        last_ll = (LinearLayout) findViewById(R.id.last_ll);
        applogo_layout = findViewById(R.id.applogo_layout);
        applogo_imageview = findViewById(R.id.applogo_imageview);

        back = (ImageButton) findViewById(R.id.back);
        back_layout = (LinearLayout) findViewById(R.id.back_layout);
        progressView = (ProgressBar) findViewById(R.id.progress_view);


        /**
         * @desc : Play video in Fullscreen mode directly
         */

        if (featureHandler.getFeatureStatus(FeatureHandler.FULL_SCREEN_MODE, FeatureHandler.IS_FULL_SCREEN_MODE)) {
            compress_expand.setVisibility(View.INVISIBLE);
            fullscreen();
        }

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                seekbar_touched = true;

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    if (player != null) {
                        try {

                            stoptimertask();

                            playerPosition = millisecondsToString((int) player.getCurrentPosition());
                            player_current_postion = millisecondsToString((int) player.getCurrentPosition());


                        } catch (Exception e) {
                        }
                    }


                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (player != null) {
                        try {
                            player_current_postion = millisecondsToString((int) player.getCurrentPosition());

                        } catch (Exception e) {
                        }
                    }
                }

                if (content_types_id == 4) {
                    return true;
                } else {
                    return false;
                }

            }
        });
        compress_expand.setOnClickListener(view -> {
            fullscreen();

        });

        back.setOnClickListener(view -> {
            try {
                PackageManager pm = getApplicationContext().getPackageManager();

                if (featureHandler.getFeatureStatus(FeatureHandler.FULL_SCREEN_MODE, FeatureHandler.IS_FULL_SCREEN_MODE)) {
                    isFull_screen = false;
                }

                if (com.home.vod.util.Util.playButtonOnBanner == true) {
                    com.home.vod.util.Util.playButtonOnBanner = false;
                    compressed = false;
                }

                if (isFull_screen) {

                    fullscreen();
                } else {
                    new_detailsLayout.setVisibility(GONE);
                    preferenceManager.setClickInfoToPref(false);

                    if (progressView != null && progressView.isShown()) {
                        progressView = null;
                    }
                    if (asynGetIpAddress != null) {
                        asynGetIpAddress.cancel(true);
                    }

                    if (progressView != null && progressView.isShown()) {
                        progressView = null;
                    }
                    if (timer != null) {
                        stoptimertask();
                        timer = null;
                    }


                    mHandler.removeCallbacks(updateTimeTask);
                    if (player != null) {
                        player.release();
                    }
                    finish();
                    overridePendingTransition(0, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PackageManager pm = getApplicationContext().getPackageManager();
                    //this condition is to check if the player is in full screen mode then if back button is clicked it will go to potrait mode.
                    if (featureHandler.getFeatureStatus(FeatureHandler.FULL_SCREEN_MODE, FeatureHandler.IS_FULL_SCREEN_MODE)) {
                        isFull_screen = false;
                    }

                    if (com.home.vod.util.Util.playButtonOnBanner == true) {
                        com.home.vod.util.Util.playButtonOnBanner = false;
                        compressed = false;
                    }

                    if (isFull_screen) {
                        fullscreen();
                    } else {
                        new_detailsLayout.setVisibility(GONE);
                        preferenceManager.setClickInfoToPref(false);

                        if (progressView != null && progressView.isShown()) {
                            progressView = null;
                        }
                        if (asynGetIpAddress != null) {
                            asynGetIpAddress.cancel(true);
                        }

                        if (progressView != null && progressView.isShown()) {
                            progressView = null;
                        }
                        if (timer != null) {
                            stoptimertask();
                            timer = null;
                        }


                        mHandler.removeCallbacks(updateTimeTask);
                        if (player != null) {
                            player.release();
                        }
                        finish();
                        overridePendingTransition(0, 0);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


    /*
     * @desc:this function is  used to init video details. */

    private void initVideoDetails(Player playerModel) {
        try {

            new_detailsLayout.setVisibility(VISIBLE);

            if (playerModel.getVideoTitle() != null && playerModel.getVideoTitle().length() > 0) {
                videoTitle.setVisibility(VISIBLE);
                videoTitle.setText(playerModel.getVideoTitle());
            } else {
                videoTitle.setVisibility(GONE);
            }

            if (playerModel.getVideoStory() != null && playerModel.getVideoStory().length() > 0) {
                storyTextView.setVisibility(VISIBLE);
                storyTextView.setText(playerModel.getVideoStory());
                Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                storyTextView.setTypeface(videoGenreTextViewTypeface);
                storyTextView.setText(playerModel.getVideoStory());
                ResizableCustomView.doResizeTextView(PlayerActivity.this, storyTextView, MAX_LINES, languagePreference.getTextofLanguage(VIEW_MORE, DEFAULT_VIEW_MORE), true);
            } else {
                storyTextView.setVisibility(GONE);
            }

            if (content_types_id == 4 || playerModel.getVideoDuration().matches("") || playerModel.getVideoDuration().matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                videoDurationTextView.setVisibility(GONE);
            } else {
                videoDurationTextView.setVisibility(VISIBLE);
                Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
                videoDurationTextView.setTypeface(videoGenreTextViewTypeface);
                videoDurationTextView.setText(playerModel.getVideoDuration());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bottomSheetlayout(int content_types_id) {

        try {

            Util.call_finish_at_onUserLeaveHint = false;
            {
                Intent intent = new Intent(PlayerActivity.this, Subtitle_Resolution.class);
                intent.putExtra("ResolutionFormat", playerModel.getResolutionFormat());
                intent.putExtra("ResolutionUrl", playerModel.getResolutionUrl());
                intent.putExtra("SubTitleName", playerModel.getSubTitleName());
                intent.putExtra("SubTitlePath", playerModel.getSubTitleLanguage());
                intent.putExtra("ResolutionNew", ResolutionNew);
                intent.putExtra("drm", isDrm);
                intent.putExtra("is_fullscreen", isFull_screen);
                intent.putExtra("content_type_id", content_types_id);
                startActivityForResult(intent, 222);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Util.VideoWidth = 0;
        Util.VideoHeight = 0;
        Util.VideoResolution = "BEST";
        brightness = Settings.System.getFloat(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, -1);
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = brightness / 255;

        getCurrentPlayerStatus();
        checkForAudioFocus();

        if (SDK_INT > 21) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (!isInPictureInPictureMode()) {
                    try {
                        initializePlayer();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    initializePlayer();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (playerView != null) {
                playerView.onResume();
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isFull_screen) {
            hideSystemUI();
        }
        initVideoDetails(playerModel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (!isInPictureInPictureMode()) {
                onResumeCall();
            }
        } else {
            onResumeCall();
        }
        if (getWindow() != null) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        hideSystemUI();
        if (playerView != null) {
            playerView.onResume();
        }
    }

    private void onResumeCall() {
        registerReceiver(SelectedUrl, new IntentFilter("UrlPosition"));

        player_is_in_forground = true;

        AsynGetIpAddress asynGetIpAddress = new AsynGetIpAddress();
        asynGetIpAddress.execute();

        try {
            {
                if (player != null && Util.call_finish_at_onUserLeaveHint && video_prepared) {
                    primary_ll.setVisibility(VISIBLE);
                    last_ll.setVisibility(VISIBLE);
                    if (player.getPlaybackState() != com.google.android.exoplayer2.Player.STATE_IDLE) {
                        mHandler.removeCallbacks(updateTimeTask);
                        updateProgressBar();

                    }
                }
            }
            //Subhadarshani
            /*
             * @desc: the play_pause icon is not getting changed to play state is external link is clicked. and after pressing back button the play button remain as pause.
             * to show the play button this condition is added.
             * NOTE: if the  external link is any audio or vido link. then this issue occours.
             * */
            if (PlayerActivity.isExternaliLinkClicked) {
                pausePlayer();
                PlayerActivity.isExternaliLinkClicked = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private MediaSource buildMediaSource(Uri uri, DrmSessionManager drmSessionManager) {
        int type = com.google.android.exoplayer2.util.Util.inferContentType(uri.getLastPathSegment());
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this);
        switch (type) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(dataSourceFactory)
                        .setDrmSessionManager(drmSessionManager)
                        .createMediaSource(MediaItem.fromUri(uri));
            case C.TYPE_OTHER:
                return new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .setDrmSessionManager(drmSessionManager)
                        .createMediaSource(MediaItem.fromUri(uri));
            case C.TYPE_HLS:
                return new HlsMediaSource.Factory(mediaDataSourceFactory).createMediaSource(uri);
            default:
                throw new IllegalStateException("Unsupported type: " + type);
        }
    }


    private void initializePlayer(String Url) {
        releasePlayer();
        String[] extensions;
        Uri uri = null;
        if (player == null) {
            if (playerModel.getVideoUrl().contains(".mpd")) {


                UUID drmSchemeUuid = null;
                uris = new Uri[]
                        {Uri.parse(playerModel.getMpdVideoUrl())};

                isDrm = true;
                drmSchemeExtra = "widevine";
                uri = Uri.parse(playerModel.getVideoUrl());

                drmLicenseUrl = "https://license.pallycon.com/ri/licenseManager.do";

                try {
                    Uri split_url = Uri.parse(playerModel.getLicenseUrl());
                    token = split_url.getQueryParameter("pallycon-customdata-v2");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String[] keyRequestPropertiesArray = null;
                Map<String, String> keyRequestProperties;
                if (keyRequestPropertiesArray == null || keyRequestPropertiesArray.length < 2) {
                    keyRequestProperties = null;
                } else {
                    keyRequestProperties = new HashMap<>();
                    for (int i = 0; i < keyRequestPropertiesArray.length - 1; i += 2) {
                        keyRequestProperties.put(keyRequestPropertiesArray[i],
                                keyRequestPropertiesArray[i + 1]);
                    }
                }


                boolean multiSession = false;
                int errorStringId = R.string.error_drm_unknown;
                if (SDK_INT < 18) {
                    errorStringId = R.string.error_drm_not_supported;
                } else {
                    drmSchemeUuid = getDrmUuid(drmSchemeExtra);
                    if (drmSchemeUuid == null) {
                        errorStringId = R.string.error_drm_unsupported_scheme;
                    } else {
                        try {

                            if (true) {
                                drmSessionManager =
                                        buildDrmSessionManagerV18(
                                                drmSchemeUuid, token, keyRequestPropertiesArray, multiSession);
                            } else {
                                drmSessionManager =
                                        buildDrmSessionManagerV18(
                                                drmSchemeUuid, token, keyRequestPropertiesArray, multiSession);
                            }

                        } catch (Exception e) {
                        }
                    }

                }
            } else {
                drmSchemeExtra = "drm_scheme";
                uri = Uri.parse(Url);
                uris = new Uri[]{Uri.parse(Url)};
            }

            trackSelector = new DefaultTrackSelector(this);
            trackSelectorParameters = new DefaultTrackSelector.ParametersBuilder(this).build();

            if (Util.VideoResolution.equalsIgnoreCase("AUTO")) {
                trackSelector.setParameters(trackSelectorParameters);
            } else {
                trackSelector.setParameters(trackSelectorParameters);
            }

            MediaSource mediaSource = buildMediaSource(uri, drmSessionManager);

            MediaSourceFactory mediaSourceFactory = null;

            player = new SimpleExoPlayer.Builder(/* context= */ this)
                    .setTrackSelector(trackSelector)
                    .build();


            player.addListener(new PlayerEventListener());
            SurfaceView view = (SurfaceView) playerView.getVideoSurfaceView();
            playerView.setPlayer(player);

            if (isResolutionChange && lattest_subtitle_code.length() > 0) {
                isResolutionChange = false;
                trackSelector.setParameters(
                        trackSelector.buildUponParameters()
                                .setPreferredTextLanguage(lattest_subtitle_code));
            }


            player.addListener(new PlayerEventListener());
            player.addAnalyticsListener(new EventLogger(trackSelector));
            playerView.setPlayer(player);

            playerView.setPlaybackPreparer(PlayerActivity.this);

            extensions = null;
            if (extensions == null) {
                extensions = new String[uris.length];
            }

            MediaSource[] mediaSources = new MediaSource[uris.length + playerModel.getFakeSubTitlePath().size()];
            for (int i = 0; i < uris.length; i++) {
                mediaSources[i] = buildMediaSource(uris[i], extensions[i]);
            }
            for (int i = 0; i < playerModel.getFakeSubTitlePath().size(); i++) {
                Format textFormat = Format.createTextSampleFormat(null, MimeTypes.TEXT_VTT,
                        C.SELECTION_FLAG_DEFAULT, playerModel.getSubTitleLanguage().get(i));
                Uri uriSubtitle = Uri.parse(playerModel.getFakeSubTitlePath().get(i));
                MediaSource subtitleSource = (new SingleSampleMediaSource.Factory(mediaDataSourceFactory)).createMediaSource(uriSubtitle, textFormat, C.TIME_UNSET);
                mediaSources[uris.length + i] = subtitleSource;
            }

            mediaSource = mediaSources.length == 1 ? mediaSources[0] : new MergingMediaSource(mediaSources);
            player.setPlayWhenReady(true);
            player.setMediaSource(mediaSource);
            player.prepare();

        }


        boolean haveStartPosition = startWindow != C.INDEX_UNSET;
        if (cast_disconnected_position == 0) {
            if (haveStartPosition) {
                player.seekTo(startWindow, startPosition);
            }

        } else {
            player.seekTo(startWindow, cast_disconnected_position);
        }

    }


    /*
     * @Desc: This is been updated with 2.11 exoplayer and before it was 2.9.1,
     *        due to Flac extension
     * */
    @SuppressWarnings("unchecked")
    private MediaSource buildMediaSource(Uri uri, @Nullable String overrideExtension) {
        @C.ContentType int type = inferContentType(uri, overrideExtension);
        switch (type) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(
                        new DefaultDashChunkSource.Factory(mediaDataSourceFactory),
                        buildDataSourceFactory(true))
                        .setDrmSessionManager(drmSessionManager)
                        .createMediaSource(uri);
            case C.TYPE_SS:
                return new SsMediaSource.Factory(
                        new DefaultSsChunkSource.Factory(mediaDataSourceFactory),
                        buildDataSourceFactory(true))
                        .setDrmSessionManager(drmSessionManager)
                        .createMediaSource(uri);
            case C.TYPE_HLS:
                return new HlsMediaSource.Factory(mediaDataSourceFactory)
                        .createMediaSource(uri);
            case C.TYPE_OTHER:
                return new ProgressiveMediaSource.Factory(mediaDataSourceFactory)
                        .setExtractorsFactory(new DefaultExtractorsFactory())
                        .createMediaSource(uri);
            default: {
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }


    @Override
    public void preparePlayback() {
        try {
            initializePlayer();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onVisibilityChange(int visibility) {
        if (visibility == VISIBLE) {
            if (pauseButtonClicked) {
                PlayButton.setVisibility(VISIBLE);
                PauseButton.setVisibility(GONE);
            }

        }
    }

    private class PlayerEventListener implements com.google.android.exoplayer2.Player.EventListener {

        @Override
        public void onPlaybackStateChanged(@com.google.android.exoplayer2.Player.State int playbackState) {
            if (playbackState == com.google.android.exoplayer2.Player.STATE_ENDED) {

                if (callPostMediaEnded) {
                    callPostMediaEnded = false;
                    postMediaEnded();
                }

            } else if (playbackState == com.google.android.exoplayer2.Player.STATE_BUFFERING) {

                player_main_layout_controller.setVisibility(GONE);
                progressView.setVisibility(VISIBLE);
                primary_ll.setVisibility(VISIBLE);
                back_layout.setVisibility(VISIBLE);
                back.setVisibility(VISIBLE);
                last_ll.setVisibility(GONE);

            } else if (playbackState == com.google.android.exoplayer2.Player.STATE_READY) {

                if (player != null) {
                    callPostMediaEnded = true;

                    total_video_duration = player.getDuration();
                    /*
                     * @Desc: This code used to visible only when the player is ready.And for logo is enable from as 1.
                     * */
                    if (featureHandler.getFeatureStatus(APP_LOGO_ON_PLAYERPAGE_ENABLED, IS_DEFAULT_APP_LOGO_ON_PLAYERPAGE_ENABLED)) {
                        applogo_layout.setVisibility(VISIBLE);
                        applogo_imageview.setImageBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                                R.mipmap.ic_launcher));
                    }

                    if (firstTimeAudioVideoGet) {
                        getDefaultAudioAndVideo();

                        // when call comes here the code for getiing all the current values subtitle,audio and playback speed
                        if (!lattest_subtitle_code.equals("")) {
                            trackSelector.setParameters(
                                    trackSelector.buildUponParameters()
                                            .setPreferredTextLanguage(lattest_subtitle_code));
                        }

                    }


                    primary_ll.setVisibility(VISIBLE);
                    last_ll.setVisibility(VISIBLE);

                    if (progressView != null && progressView.getVisibility() == VISIBLE) {
                        progressView.setVisibility(GONE);
                    }
                    video_prepared = true;
                    if (playerModel.getPlayPos() >= total_video_duration / 1000) {

                        played_length = 0;
                    }
                    video_completed = false;

                    if (seekbar_touched || resumeCheck) {
                        seekbar_touched = false;
                        player_main_layout_controller.setVisibility(VISIBLE);
                        return;
                    }
                    Display display = getWindowManager().getDefaultDisplay();
                    screenWidth = display.getWidth();
                    screenHeight = display.getHeight();
                    try {

                        surface_width = playerView.getVideoSurfaceView().getWidth();
                        surface_height = playerView.getVideoSurfaceView().getHeight();
                        player_width = playerView.getWidth();
                        player_height = playerView.getHeight();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    /*Author:Chitra
                     * @desc: The below condition is used for when user clicked on play button on banner then player will be in full screen mode otherwise in normal flow.*/

                    if (com.home.vod.util.Util.playButtonOnBanner == true && !isFull_screen) {
                        fullscreen();
                    } else {
                        setAutoPlayPlayerView();
                    }

                    try {
                        //video log
                        if (content_types_id == 4) {
                            if (playerModel.getSubTitlePath().size() > 0) {
                                CheckSubTitleParsingType("1");
                                subtitleDisplayHandler = new Handler();
                                subsFetchTask = new SubtitleProcessingTask("1");
                                subsFetchTask.execute();
                            }

                            updateProgressBar();
                        } else {
                            startTimer();

                            if (!resumeCheck) {

                                try {
                                    if (featureHandler.getFeatureStatus(FeatureHandler.IS_RESUME_WATCH_LAST_SEEN_STATUS, FeatureHandler.DEFAULT_IS_RESUME_WATCH_LAST_SEEN_STATUS) &&
                                            com.home.vod.util.Util.complete_status_of_resume_lastseen.equals("1")) {

                                        try {
                                            com.home.vod.util.Util.complete_status_of_resume_lastseen = "0";
                                            played_length = (int) total_video_duration;
                                            if (played_length > 10000) {
                                                played_length = played_length - 10000;
                                            } else {
                                                played_length = playerModel.getPlayPos() * 1000;
                                            }
                                        } catch (Exception e) {
                                            played_length = 0;
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                if (played_length > 0) {
                                    resumeCheck = true;
                                    ((ProgressBar) findViewById(R.id.progress_view)).setVisibility(GONE);
                                    Util.call_finish_at_onUserLeaveHint = true;
                                    pausePlayer();
                                    resumeWatch();
                                } else {
                                    resumeCheck = true;
                                    startPlayer();
                                    updateProgressBar();

                                    if (playerModel.getSubTitlePath().size() > 0) {

                                        CheckSubTitleParsingType("1");
                                        subtitleDisplayHandler = new Handler();
                                        subsFetchTask = new SubtitleProcessingTask("1");
                                        subsFetchTask.execute();
                                    }
                                }
                            }

                            try {
                                if (progressView != null && progressView.getVisibility() == VISIBLE)
                                    progressView.setVisibility(GONE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                player_main_layout_controller.setVisibility(VISIBLE);

            } else if (playbackState == com.google.android.exoplayer2.Player.STATE_IDLE) {
                last_ll.setVisibility(GONE);
            }

        }

        @Override
        public void onPlayerError(@NonNull ExoPlaybackException e) {

        }

        @Override
        @SuppressWarnings("ReferenceEquality")
        public void onTracksChanged(
                @NonNull TrackGroupArray trackGroups, @NonNull TrackSelectionArray trackSelections) {

        }
    }

    private void setAutoPlayPlayerView() {
        if (potrait_fullscreen) {
            if (player_height > player_width) {
                //potrait full screen
                potrait_fullscreen = true;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                player_layout.setLayoutParams(params);
                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                playerView.setLayoutParams(params1);

                compress_expand.setImageResource(R.drawable.ic_media_fullscreen_shrink);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                });
                Util.player_description = false;
                hideSystemUI();
                Util.landscape = false;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                //set landscape full screen
                potrait_fullscreen = false;
                landscape_fullscreen = true;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                player_layout.setLayoutParams(params);
                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                playerView.setLayoutParams(params1);

                compress_expand.setImageResource(R.drawable.ic_media_fullscreen_shrink);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                });
                Util.player_description = false;
                Util.landscape = true;
                hideSystemUI();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        } else if (landscape_fullscreen) {
            if (player_width > player_height) {
                //landscape full screen
                landscape_fullscreen = true;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                player_layout.setLayoutParams(params);
                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                playerView.setLayoutParams(params1);

                compress_expand.setImageResource(R.drawable.ic_media_fullscreen_shrink);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                });
                Util.player_description = false;
                Util.landscape = true;
                hideSystemUI();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            } else if (player_height > player_width) {
                //set potrait full screen
                landscape_fullscreen = false;
                potrait_fullscreen = true;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                player_layout.setLayoutParams(params);
                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                playerView.setLayoutParams(params1);

                compress_expand.setImageResource(R.drawable.ic_media_fullscreen_shrink);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                });
                Util.player_description = false;
                hideSystemUI();
                Util.landscape = false;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                player_layout.setLayoutParams(params);
            }
        } else {
            setPlayerView();
        }
    }

    private void getDefaultAudioAndVideo() {
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo = trackSelector.getCurrentMappedTrackInfo();
        if (mappedTrackInfo != null) {
            for (int i = 0; i < mappedTrackInfo.getRendererCount(); i++) {
                TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i);
                if (trackGroups.length != 0) {
                    switch (player.getRendererType(i)) {
                        case C.TRACK_TYPE_AUDIO:
                            ArrayList<String> unq = new ArrayList<>();
                            String name = "DEFAULT", code = "def";
                            for (int j = 0; j < trackGroups.length; j++) {
                                TrackGroup group = trackGroups.get(j);
                                if (group.length > 0) {
                                    for (int k = 0; k < group.length; k++) {
                                        com.google.android.exoplayer2.Format format = group.getFormat(k);
                                        TrackNameProvider trackNameProvider = new DefaultTrackNameProvider(getResources());
                                        trackNameProvider = Assertions.checkNotNull(trackNameProvider);
                                        Log.v("kushal audio", trackNameProvider.getTrackName(format));
                                        unq.add(trackNameProvider.getTrackName(format) + ":" + format.language);
                                        if (k == 0) {
                                            String part[] = trackNameProvider.getTrackName(format).split(",");
                                            name = part[0];
                                            code = format.language;
                                        }
                                    }
                                }
                            }

                            trackSelector.setParameters(
                                    trackSelector.buildUponParameters()
                                            .setPreferredAudioLanguage(code));
                            //  AudioCode = new ArrayList<>(new LinkedHashSet<String>(unqCode));
                            break;
                        case C.TRACK_TYPE_VIDEO:
                            break;
                        case C.TRACK_TYPE_TEXT:
                            if (playerModel.getFakeSubTitlePath().size() > 0) {
                                trackSelector.setParameters(
                                        trackSelector.buildUponParameters()
                                                .setPreferredTextLanguage(playerModel.getSubTitleLanguage().get(0)));
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        firstTimeAudioVideoGet = false;

    }


    private void releasePlayer() {
        if (player != null) {
            updateTrackSelectorParameters();
            updateStartPosition();
            player.release();
            player = null;
            trackSelector = null;
        }

        releaseMediaDrm();
    }

    private void releaseMediaDrm() {
        if (mediaDrm != null) {
            mediaDrm.release();
            mediaDrm = null;
        }
    }


    private void updateTrackSelectorParameters() {
        if (trackSelector != null) {
            trackSelectorParameters = trackSelector.getParameters();
        }
    }

    private void updateStartPosition() {
        if (player != null) {
            startAutoPlay = player.getPlayWhenReady();
            startWindow = player.getCurrentWindowIndex();
            startPosition = Math.max(0, player.getContentPosition());
        }
    }

    private void clearStartPosition() {
        startAutoPlay = true;
        startWindow = C.INDEX_UNSET;
        startPosition = C.TIME_UNSET;
    }


    private class PlayerErrorMessageProvider implements ErrorMessageProvider<ExoPlaybackException> {

        @Override
        public Pair<Integer, String> getErrorMessage(ExoPlaybackException e) {
            String errorString = getString(R.string.error_generic);
            if (e.type == ExoPlaybackException.TYPE_RENDERER) {
                Exception cause = e.getRendererException();
                if (cause instanceof MediaCodecRenderer.DecoderInitializationException) {
                    // Special case for decoder initialization failures.
                    MediaCodecRenderer.DecoderInitializationException decoderInitializationException =
                            (MediaCodecRenderer.DecoderInitializationException) cause;
                    if (decoderInitializationException.codecInfo == null) {

                        if (decoderInitializationException.getCause() instanceof MediaCodecUtil.DecoderQueryException) {
                            errorString = getResources().getString(R.string.error_querying_decoders);
                        } else if (decoderInitializationException.secureDecoderRequired) {
                            errorString =
                                    getResources().getString(R.string.error_no_secure_decoder);
                        } else {
                            errorString =
                                    getResources().getString(R.string.error_no_decoder);
                        }
                    } else {
                        errorString =
                                getResources().getString(
                                        R.string.error_instantiating_decoder);
                    }

                }
            } else {
                /**
                 * @description when the stream is lost and only indicates PLAYBACK FAILED adding option to try again or auto-restart the streaming.
                 */
                try {
                    if (progressView != null && progressView.getVisibility() == VISIBLE) {
                        progressView.setVisibility(GONE);
                    }
                    playback_failed_layout.setVisibility(VISIBLE);
                    playback_failed_layout.setBackgroundColor(getResources().getColor(R.color.transparent));
                    playerView.setVisibility(GONE);
                    playback_msg.setText(languagePreference.getTextofLanguage(PLAYBACK_FAILED_MESSAGE, DEFAULT_PLAYBACK_FAILED_MESSAGE));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return Pair.create(0, errorString);
        }
    }


    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        return ((VodApplication) getApplication())
                .buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }


    @Override
    public void onErrorNotification(int i, String s) {

    }

    @Override
    public void onPause() {
        super.onPause();
        try {

            //playerView.hideController();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (!isInPictureInPictureMode()) {
                    pausePlayer();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();

        if (SDK_INT > 21) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (!isInPictureInPictureMode()) {
                    if (Util.call_finish_at_onUserLeaveHint) {
                        if (player == null)
                            Util.call_finish_at_onUserLeaveHint = true;

                        mHandler.removeCallbacks(updateTimeTask);
                        if (player != null) {
                            pausePlayer();



                            /*
                             * @Desc: To set the temporary playlength of last viewed content.
                             * */
                            preferenceManager.setTemporaryPlayLeangth(Math.toIntExact(player.getContentPosition()));

//                            player.release();
                            onStopCalled = true;
                        }
                    }
                } else {
                    onStopCalled = true;

                }
            } else {
                if (Util.call_finish_at_onUserLeaveHint) {
                    Util.call_finish_at_onUserLeaveHint = true;

                    mHandler.removeCallbacks(updateTimeTask);
                    if (player != null) {
                        player.release();
                        onStopCalled = true;

                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        onDestroyCall();
        super.onDestroy();
    }

    private void onDestroyCall() {

        try {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        com.home.vod.util.Util.playButtonOnBanner = false;


        if (asynGetIpAddress != null) {
            asynGetIpAddress.cancel(true);
        }


        if (progressView != null && progressView.isShown()) {
            progressView = null;
        }
        if (timer != null && Util.call_finish_at_onUserLeaveHint) {
            stoptimertask();
            timer = null;
        }


        if (MovableTimer != null)
            MovableTimer.cancel();

        Util.app_is_in_player_context = false;

        Util.hide_pause = false;

        try {
            if (SelectedUrl != null)
                unregisterReceiver(SelectedUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // If the broadcast receiver is not null then unregister it.
        // This action is better placed in activity onDestroy() method.
        if (this.networkChangeReceiver != null) {
            unregisterReceiver(this.networkChangeReceiver);
        }
        if (Util.call_finish_at_onUserLeaveHint) {
            Util.call_finish_at_onUserLeaveHint = true;

            mHandler.removeCallbacks(updateTimeTask);
            if (player != null) {
                player.release();
                finish();
            }
        }

        releasePlayer();
    }

    private BroadcastReceiver SelectedUrl = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            String position = intent.getStringExtra("position");
            selected_download_format = Integer.parseInt(position);

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 8001) {
                try {
                    Util.call_finish_at_onUserLeaveHint = true;
                    new_detailsLayout.setVisibility(VISIBLE);
                    played_length = playerModel.getPlayPos() * 1000;
                    startPlayer();

                    player.seekTo(played_length);

                    if (adDetails.size() > 0) {
                        for (int i = 0; i < adDetails.size(); i++) {
                            if (playerModel.getPlayPos() == Integer.parseInt(adDetails.get(i))) {
                                adDetails.remove(i);
                            }
                        }
                    }

                    updateProgressBar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (requestCode == 8002) {

                try {
                    Util.call_finish_at_onUserLeaveHint = true;
                    played_length = playerModel.getPlayPos() * 1000;
                    startPlayer();
                    player.seekTo(played_length);
                    updateProgressBar();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            if (requestCode == 1001) {

                Util.call_finish_at_onUserLeaveHint = true;
                if (data.getStringExtra("yes").equals("1002")) {

                    playerPosition = playerModel.getPlayPos();
                    player_start_time = playerPosition;
                    startPlayer();
                    player.seekTo(played_length);

                    updateProgressBar();

                } else {

                    startPlayer();
                    updateProgressBar();

                }
                if (SubTitlePath.size() > 0) {

                    CheckSubTitleParsingType("1");

                    subtitleDisplayHandler = new Handler();
                    subsFetchTask = new SubtitleProcessingTask("1");
                    subsFetchTask.execute();
                }

            }
            if (requestCode == 222) {

                seekbar_touched = true;

                if (data.getStringExtra("type").equals("resolution")) {
                    mHandler.removeCallbacks(updateTimeTask);
                    if (!data.getStringExtra("position").equals("nothing")) {

                        int position = Integer.parseInt(data.getStringExtra("position"));
                        isResolutionChange = true;
                        updateStartPosition();

                        changed_resolutionUrl = playerModel.getResolutionUrl().get(position);
                        initializePlayer(playerModel.getResolutionUrl().get(position));
                        Util.VideoResolution = playerModel.getResolutionFormat().get(position);

                    }

                } else {


                    if (!data.getStringExtra("position").equals("nothing")) {

                        if (data.getStringExtra("position").equals("0")) {
                            // Stop Showing Subtitle
                            playerView.getSubtitleView().setVisibility(GONE);

                        } else {

                            try {
                                lattest_subtitle_code = data.getStringExtra("code");
                                trackSelector.setParameters(
                                        trackSelector.buildUponParameters()
                                                .setPreferredTextLanguage(data.getStringExtra("code")));
                                playerView.getSubtitleView().setVisibility(VISIBLE);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }
            }
        }
    }


    /**
     * Method to convert playback speed String value to float
     *
     * @param speed playback speed in String
     * @return playback speed in float
     */
    private float getSpeed(String speed) {
        if (speed == null) return 1f;
        if (speed.equals("Normal"))
            return 1f;
        else {
            return Float.parseFloat(speed);
        }
    }


    public void CheckSubTitleParsingType(String path) {

        String Subtitle_Path = playerModel.getSubTitlePath().get((Integer.parseInt(path) - 1));
        callWithoutCaption = true;

        File myFile = new File(Subtitle_Path);
        BufferedReader test_br = null;
        InputStream stream = null;
        InputStreamReader in = null;
        try {
            stream = new FileInputStream(String.valueOf(myFile));
            in = new InputStreamReader(stream);
            test_br = new BufferedReader(in);

        } catch (Exception e) {
            e.printStackTrace();
        }

        int testinglinecounter = 1;
        int captionNumber = 1;


        String TestingLine = null;
        try {
            TestingLine = test_br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (testinglinecounter < 6) {
            try {

                if (Integer.parseInt(TestingLine.toString().trim()) == captionNumber) {
                    callWithoutCaption = false;
                    testinglinecounter = 6;
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    TestingLine = test_br.readLine();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                testinglinecounter++;
            }
        }
    }

    public class SubtitleProcessingTask extends AsyncTask<Void, Void, Void> {


        String Subtitle_Path = "";

        public SubtitleProcessingTask(String path) {

            Subtitle_Path = SubTitlePath.get((Integer.parseInt(path) - 1));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // int count;
            try {

                File myFile = new File(Subtitle_Path);
                InputStream fIn = new FileInputStream(String.valueOf(myFile));


                if (callWithoutCaption) {
                    FormatSRT_WithoutCaption formatSRT = new FormatSRT_WithoutCaption();
                    srt = formatSRT.parseFile("sample", fIn);
                } else {

                    FormatSRT formatSRT = new FormatSRT();
                    srt = formatSRT.parseFile("sample", fIn);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (null != srt) {
                subtitleText.setText("");
                subtitleDisplayHandler.post(subtitleProcessesor);
            }


            super.onPostExecute(result);
        }
    }


    private Runnable subtitleProcessesor = new Runnable() {

        @Override
        public void run() {
            try {
                if (player != null && player.getPlaybackState() == com.google.android.exoplayer2.Player.STATE_READY) {
                    int currentPos = (int) player.getCurrentPosition();
                    Collection<Caption> subtitles = srt.captions.values();
                    for (Caption caption : subtitles) {
                        if (currentPos >= caption.start.mseconds
                                && currentPos <= caption.end.mseconds) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                if (!isInPictureInPictureMode()) {
                                    onTimedText(caption);
                                } else {
                                    onTimedText(null);
                                }
                            } else {
                                onTimedText(caption);
                            }
                            break;
                        } else if (currentPos > caption.end.mseconds) {
                            onTimedText(null);
                        }
                    }
                }
                subtitleDisplayHandler.postDelayed(this, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    public void onTimedText(Caption text) {
        if (text == null) {
            subtitleText.setVisibility(View.INVISIBLE);
            return;
        }

        Typeface videoGenreTextViewTypeface = Typeface.createFromAsset(getAssets(), getResources().getString(R.string.light_fonts));
        subtitleText.setTypeface(videoGenreTextViewTypeface);
        subtitleText.setVisibility(VISIBLE);
    }

    private void updateProgressBar() {
        mHandler.postDelayed(updateTimeTask, 1000);
    }


    private Runnable updateTimeTask = new Runnable() {
        public void run() {
            try {
                seekBarProgress = (int) player.getCurrentPosition();
                current_played_length = (int) player.getCurrentPosition();
                mHandler.postDelayed(this, 1000);
                current_matching_time = (int) player.getCurrentPosition();
                total_video_duration = player.getDuration();

                if ((previous_matching_time == current_matching_time) && (current_matching_time < total_video_duration)) {

                } else {

                    if (content_types_id == 4) {

                    } else {
                        long dur = total_video_duration;
                        if (dur < 0)
                            return;
                        if (current_matching_time >= total_video_duration) {

                            mHandler.removeCallbacks(updateTimeTask);

                            /**
                             * Following method will handle media completion post processing.
                             */
                            if (callPostMediaEnded) {
                                callPostMediaEnded = false;
                                postMediaEnded();
                            }

                            previous_matching_time = -1;
                            current_matching_time = -1;
                            video_completed = true;
                        }
                    }


                    previous_matching_time = current_matching_time;
                    ((ProgressBar) findViewById(R.id.progress_view)).setVisibility(GONE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };


    public void Download_SubTitle(String Url) {
        new DownloadFileFromURL_Offline().execute(Url);
    }

    class DownloadFileFromURL_Offline extends AsyncTask<String, String, String> {

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
                mediaStorageDir1 = new File(root + "/Android/data/" + getApplicationContext().getPackageName().trim() + "/SubTitleList_Offline/", "");

                if (!mediaStorageDir1.exists()) {
                    if (!mediaStorageDir1.mkdirs()) {
                        Log.d("App", "failed to create directory");
                    }
                }

                SubtitleModel subtitleModel = new SubtitleModel();
                subtitleModel.setUID(playerModel.getStreamUniqueId() + emailIdStr);
                subtitleModel.setLanguage(playerModel.getOfflineLanguage().get(0));
                String filename = mediaStorageDir1.getAbsolutePath() + "/" + System.currentTimeMillis() + ".vtt";
                subtitleModel.setPath(filename);

                long rowId = dbHelper.insertRecordSubtittel(subtitleModel);
                playerModel.getOfflineLanguage().remove(0);
                OutputStream output = new FileOutputStream(filename);

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
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            return null;
        }

        protected void onProgressUpdate(String... progress) {
        }

        @Override
        protected void onPostExecute(String file_url) {
            if (playerModel.getOfflineUrl().size() > 0) {
                playerModel.getOfflineUrl().remove(0);
            }
            if (playerModel.getOfflineUrl().size() > 0) {
                Download_SubTitle(playerModel.getOfflineUrl().get(0).trim());
            }

        }
    }


    private void resumeWatch() {
        playerPosition = playerModel.getPlayPos();
        player_start_time = playerPosition;
        startPlayer();
        player.seekTo(played_length);
        updateProgressBar();

    }


    public void startTimer() {
        try {
            if (timer != null) {
                timer.cancel();
                timer.purge();
                timer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //set a new Timer
        timer = new Timer();

        initializeTimerTask();
        timer.schedule(timerTask, logTimerInterval, logTimerInterval); //
    }

    public void stoptimertask() {
        try {
            if (timer != null) {
                timer.cancel();
                timer.purge();
                timer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                threadHandler.post(new Runnable() {
                    public void run() {
                        try {
                            if (player != null) {

                                int currentPositionStr = millisecondsToString((int) player.getCurrentPosition());
                                playerPosition = currentPositionStr;
                                Log.d("TimerLog", playerPosition + "");
                                player_current_postion = millisecondsToString((int) player.getCurrentPosition());

                                if (currentPositionStr > 0) {

                                    if ((currentPositionStr % 2) != 0)
                                        currentPositionStr = currentPositionStr + 1;
                                    int duration = (int) total_video_duration / 1000;


                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
    }


    private int millisecondsToString(int milliseconds) {
        // int seconds = (int) (milliseconds / 1000) % 60 ;
        int seconds = (int) (milliseconds / 1000);

        return seconds;
    }

    @Override
    public void onOrientationChange(int orientation) {

        if (!player_is_in_forground)
            return;

        if (isFull_screen) {
            if (orientation == 270) {
                Util.player_description = false;
                Util.landscape = true;
                compressed = false;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                player_layout.setLayoutParams(params);
                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                playerView.setLayoutParams(params1);

                compress_expand.setImageResource(R.drawable.ic_media_fullscreen_shrink);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                });
                hideSystemUI();

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                // Do some landscape stuff
            } else if (orientation == 90) {

                Util.player_description = false;
                Util.landscape = false;
                compressed = false;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                player_layout.setLayoutParams(params);
                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                playerView.setLayoutParams(params1);

                compress_expand.setImageResource(R.drawable.ic_media_fullscreen_shrink);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                });
                hideSystemUI();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                //current_time.setVisibility(View.GONE);
            }
        }

    }


    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        );
    }


    private class AsynGetIpAddress extends AsyncTask<Void, Void, Void> {
        String responseStr;


        @Override
        protected Void doInBackground(Void... params) {
            try {

                // Execute HTTP Post Request
                try {
                    URL myurl = new URL(Util.loadIPUrl);
                    HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
                    InputStream ins = con.getInputStream();
                    InputStreamReader isr = new InputStreamReader(ins);
                    BufferedReader in = new BufferedReader(isr);

                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        System.out.println(inputLine);
                        responseStr = inputLine;
                    }

                    in.close();


                } catch (org.apache.http.conn.ConnectTimeoutException e) {
                    ipAddressStr = "";

                } catch (UnsupportedEncodingException e) {

                    ipAddressStr = "";

                } catch (IOException e) {
                    ipAddressStr = "";

                } catch (Exception e) {
                    e.printStackTrace();
                    ipAddressStr = "";
                }
                if (responseStr != null) {
                    Object json = new JSONTokener(responseStr).nextValue();
                    if (json instanceof JSONObject) {
                        ipAddressStr = ((JSONObject) json).getString("ip");

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                ipAddressStr = "";
            }
            return null;
        }


        protected void onPostExecute(Void result) {

            if (responseStr == null) {
                ipAddressStr = "";
            }
            return;
        }

        protected void onPreExecute() {

        }

    }


    public void backCalled() {
        try {
            if (asynGetIpAddress != null) {
                asynGetIpAddress.cancel(true);
            }


            if (progressView != null && progressView.isShown()) {
                progressView = null;
            }
            if (timer != null) {
                stoptimertask();
                timer = null;
            }


            mHandler.removeCallbacks(updateTimeTask);
            if (player != null) {
                player.release();
            }
            finish();
            overridePendingTransition(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        if (featureHandler.getFeatureStatus(FeatureHandler.FULL_SCREEN_MODE, FeatureHandler.IS_FULL_SCREEN_MODE)) {
            isFull_screen = false;
        }

        if (com.home.vod.util.Util.playButtonOnBanner == true) {
            com.home.vod.util.Util.playButtonOnBanner = false;
            compressed = false;
        }

        if (isFull_screen) {
            fullscreen();
        } else {
            preferenceManager.setClickInfoToPref(false);
            super.onBackPressed();

            if (progressView != null && progressView.isShown()) {
                progressView = null;
            }
            if (asynGetIpAddress != null) {
                asynGetIpAddress.cancel(true);
            }
            if (progressView != null && progressView.isShown()) {
                progressView = null;
            }
            if (timer != null) {
                stoptimertask();
                timer = null;
            }

            mHandler.removeCallbacks(updateTimeTask);
            if (player != null) {
                player.release();
            }
            finish();
            overridePendingTransition(0, 0);

        }
    }

    /**
     * @description For full screen play of a video
     */

    public void fullscreen() {

        /*
         * @desc:The below condition is to check if the plyer is in potrait mode then it will be potrait full screen or else
         * landscape full screen*/

        if (player_height > player_width) {
            if (compressed) {
                potrait_fullscreen = true;
                compressed = false;
                isFull_screen = true;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                player_layout.setLayoutParams(params);
                RelativeLayout.LayoutParams param1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                playerView.setLayoutParams(param1);

                compress_expand.setImageResource(R.drawable.ic_media_fullscreen_shrink);
                hideSystemUI();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                });
                Util.player_description = false;
                Util.landscape = false;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                isFull_screen = false;
                potrait_fullscreen = false;
                Util.player_description = true;
                LinearLayout.LayoutParams params1 = null;
                RelativeLayout.LayoutParams params2 = null;

                if (((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) || ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_XLARGE)) {
                    if (PlayerActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 65) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 65) / 100);
                    } else {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 65) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 65) / 100);
                    }
                } else {
                    if (PlayerActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 60) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 60) / 100);
                    } else {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 60) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 60) / 100);
                    }
                }
                player_layout.setLayoutParams(params1);
                playerView.setLayoutParams(params2);

                compressed = true;
                compress_expand.setImageResource(R.drawable.ic_media_fullscreen_stretch);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                });
                new_detailsLayout.setVisibility(VISIBLE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                showSystemUI();
            }
        } else {
            if (compressed) {
                landscape_fullscreen = true;
                compressed = false;
                isFull_screen = true;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                player_layout.setLayoutParams(params);
                RelativeLayout.LayoutParams param1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
                playerView.setLayoutParams(param1);
                compress_expand.setImageResource(R.drawable.ic_media_fullscreen_shrink);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                });
                Util.player_description = false;
                Util.landscape = true;
                hideSystemUI();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                landscape_fullscreen = false;
                compressed = true;
                isFull_screen = false;
                Util.player_description = true;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                });
                LinearLayout.LayoutParams params1 = null;
                RelativeLayout.LayoutParams params2 = null;

                if (((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) || ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_XLARGE)) {
                    if (PlayerActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 45) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 45) / 100);
                    } else {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 45) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 45) / 100);
                    }
                } else {
                    if (PlayerActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 40) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 40) / 100);
                    } else {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 40) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 40) / 100);
                    }
                }
                player_layout.setLayoutParams(params1);
                playerView.setLayoutParams(params2);
                new_detailsLayout.setVisibility(VISIBLE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                playerView.setMinimumHeight((int) getResources().getDimension(R.dimen.player_relativlayout_height));
                player_layout.setMinimumHeight((int) getResources().getDimension(R.dimen.player_relativlayout_height));
                compress_expand.setImageResource(R.drawable.ic_media_fullscreen_stretch);
                showSystemUI();
            }
        }

    }


    /**
     * @author Kushal
     * @Desc: following methods are responsible for gesture control of left and right vertical swipe and horizontal swipe
     */

    public void getCurrentPlayerStatus() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (!isInPictureInPictureMode()) {
                // Show the video controls so the video can be easily resumed.
                playerView.showController();
            }
        }
    }


    private void enableView(View view) {
        view.setVisibility(VISIBLE);
        view.setEnabled(true);
        view.setClickable(true);
        ((ImageView) view).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    private void disableView(View view) {
        view.setVisibility(GONE);
        view.setEnabled(false);
        view.setClickable(false);
        ((ImageView) view).setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.hint_color), android.graphics.PorterDuff.Mode.SRC_IN);
    }



    /*
     * @desc:This function is used to call the API to get all video cards*/


    // To animate view slide out from left to right
    public void slideToRight(View view) {
        if (view != null) {
            TranslateAnimation animate = new TranslateAnimation(0, view.getWidth(), 0, 0);
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
            view.setVisibility(GONE);
        }

    }

    // To animate view slide out from bottom to top
    public void slideToTop(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0, 0, view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(GONE);
    }

    // To animate view slide out from left to right
    public void slideToLeft(View view) {
        if (view != null) {
            TranslateAnimation animate = new TranslateAnimation(view.getWidth(), 0, 0, 0);
            animate.setDuration(500);
            animate.setFillAfter(true);
            view.startAnimation(animate);
            view.setVisibility(VISIBLE);
        }

    }

    /*
     * @desc: to set the player height according to video height.
     * */
    private void setPlayerView() {
        try {
            LinearLayout.LayoutParams params1 = null;
            RelativeLayout.LayoutParams params2 = null;
            if (player_height > player_width) {
                // 65% of scree

                if (((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) || ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_XLARGE)) {
                    if (PlayerActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 65) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 65) / 100);
                    } else {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 65) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 65) / 100);
                    }
                } else {
                    if (PlayerActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 60) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 60) / 100);
                    } else {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 60) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 60) / 100);
                    }
                }
                player_layout.setLayoutParams(params1);
                playerView.setLayoutParams(params2);

            } else {
                Log.v("setPlayerView", "widthsetPlayerView");
                if (((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) || ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_XLARGE)) {
                    if (PlayerActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 45) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 45) / 100);

                    } else {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 45) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 45) / 100);

                    }
                } else {
                    if (PlayerActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 40) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 40) / 100);

                    } else {
                        params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenHeight * 40) / 100);
                        params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (screenHeight * 40) / 100);

                    }
                }
                player_layout.setLayoutParams(params1);
                playerView.setLayoutParams(params2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initializePlayer() throws JSONException {
        if (player == null) {

            UUID drmSchemeUuid = null;
            Uri uri = null;
            String[] extensions;

            if (playerModel.getVideoUrl().contains(".mpd")) {
                isDrm = true;
                drmSchemeExtra = "widevine";
                uris = new Uri[]{Uri.parse(playerModel.getMpdVideoUrl())};

                uri = Uri.parse(playerModel.getVideoUrl());
                drmLicenseUrl = playerModel.getLicenseUrl();


                try {
                    Uri split_url = Uri.parse(playerModel.getLicenseUrl());
                    token = split_url.getQueryParameter("pallycon-customdata-v2");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String[] keyRequestPropertiesArray = null;
                Map<String, String> keyRequestProperties;
                if (keyRequestPropertiesArray == null || keyRequestPropertiesArray.length < 2) {
                    keyRequestProperties = null;
                } else {
                    keyRequestProperties = new HashMap<>();
                    for (int i = 0; i < keyRequestPropertiesArray.length - 1; i += 2) {
                        keyRequestProperties.put(keyRequestPropertiesArray[i],
                                keyRequestPropertiesArray[i + 1]);
                    }
                }

                boolean multiSession = false;
                int errorStringId = R.string.error_drm_unknown;
                if (SDK_INT < 18) {
                    errorStringId = R.string.error_drm_not_supported;
                } else {
                    drmSchemeUuid = getDrmUuid(drmSchemeExtra);
                    if (drmSchemeUuid == null) {
                        errorStringId = R.string.error_drm_unsupported_scheme;
                    } else {
                        try {
                            drmSessionManager =
                                    buildDrmSessionManagerV18(
                                            drmSchemeUuid, drmLicenseUrl, keyRequestPropertiesArray, multiSession);

                        } catch (UnsupportedDrmException | InterruptedException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                isDrm = false;
                drmSchemeExtra = "drm_scheme";
                uri = Uri.parse(playerModel.getVideoUrl());

                /**
                 * @author Debashish
                 * @description For checking and playing BEST Resolution content - Settings Feature
                 */

                if (resolutionQuality.equals("BEST") &&
                        playerModel.getResolutionUrl() != null &&
                        playerModel.getResolutionUrl().size() > 0 &&
                        !TextUtils.isEmpty(playerModel.getResolutionUrl().get(0))) {
                    uris = new Uri[]{Uri.parse(playerModel.getResolutionUrl().get(0))};
                    Util.VideoResolution = playerModel.getResolutionFormat().get(0);
                } else {
                    Util.VideoResolution = playerModel.getVideoResolution() + "p";
                    uris = new Uri[]{Uri.parse(playerModel.getVideoUrl())};
                }
            }

            trackSelector = new DefaultTrackSelector(this);
            trackSelectorParameters = new DefaultTrackSelector.ParametersBuilder(this).build();
            trackSelector.setParameters(trackSelectorParameters);
            MediaSourceFactory mediaSourceFactory = null;
            player = new SimpleExoPlayer.Builder(/* context= */ this)
                    .setTrackSelector(trackSelector)
                    .build();

            player.addListener(new PlayerEventListener());
            SurfaceView view = (SurfaceView) playerView.getVideoSurfaceView();
            playerView.setPlayer(player);
            player.setPlayWhenReady(true);


            extensions = null;
            if (extensions == null) {
                extensions = new String[uris.length];
            }

            MediaSource[] mediaSources = new MediaSource[uris.length + playerModel.getFakeSubTitlePath().size()];
            for (int i = 0; i < uris.length; i++) {
                mediaSources[i] = buildMediaSource(uris[i], extensions[i]);
            }

            for (int i = 0; i < playerModel.getFakeSubTitlePath().size(); i++) {
                Format textFormat = Format.createTextSampleFormat(null, MimeTypes.TEXT_VTT,
                        C.SELECTION_FLAG_DEFAULT, playerModel.getSubTitleLanguage().get(i));
                Uri uriSubtitle = Uri.parse(playerModel.getFakeSubTitlePath().get(i));
                MediaSource subtitleSource = (new SingleSampleMediaSource.Factory(mediaDataSourceFactory)).createMediaSource(uriSubtitle, textFormat, C.TIME_UNSET);
                mediaSources[uris.length + i] = subtitleSource;
            }
            MediaSource mediaSource = mediaSources.length == 1 ? mediaSources[0] : new MergingMediaSource(mediaSources);

            boolean haveStartPosition = startWindow != C.INDEX_UNSET;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (!isInPictureInPictureMode()) {
                    if (cast_disconnected_position == 0) {
                        if (haveStartPosition) {
                            if (!playerPaused) {
                                startPlayer();
                            }
                        }
                    } else {
                        if (!playerPaused) {
                            player.seekTo(startWindow, cast_disconnected_position);
                            startPlayer();
                        }

                    }
                }
            } else {
                if (cast_disconnected_position == 0) {
                    if (haveStartPosition) {
                        if (!playerPaused) {
                            startPlayer();
                        }
                    }

                } else {
                    if (!playerPaused) {
                        player.seekTo(startWindow, cast_disconnected_position);
                        startPlayer();
                    }

                }
            }
            if (firstTimeAudioVideoGet) {
                if (player != null) {
                    player.setMediaSource(mediaSource);
                    player.prepare();
                }

                if (onStopCalled) {
                    onStopCalled = false;
                    startPlayer();
                    if (player != null)
                        player.seekTo(preferenceManager.getTemporaryPlayLeangth());
                }
            }
            if (playerModel.getContentTypesId() == 4) {
                player.seekTo(livestream_resume_time);
            }

        }
    }

    private DefaultDrmSessionManager buildDrmSessionManagerV18(
            UUID uuid, String licenseUrl, String[] keyRequestPropertiesArray, boolean multiSession)
            throws
            UnsupportedDrmException, InterruptedException, DrmSession.DrmSessionException, IOException {
        HttpDataSource.Factory licenseDataSourceFactory =
                ((VodApplication) getApplication()).buildHttpDataSourceFactory(/* listener= */ null);
        HttpMediaDrmCallback drmCallback =
                new HttpMediaDrmCallback(licenseUrl, licenseDataSourceFactory);
        if (keyRequestPropertiesArray != null) {
            for (int i = 0; i < keyRequestPropertiesArray.length - 1; i += 2) {
                drmCallback.setKeyRequestProperty(keyRequestPropertiesArray[i],
                        keyRequestPropertiesArray[i + 1]);
            }
        }

        releaseMediaDrm();
        mediaDrm = FrameworkMediaDrm.newInstance(uuid);

        // Kushal
        DefaultDrmSessionManager drmSessionManager = new DefaultDrmSessionManager(
                uuid, mediaDrm, drmCallback, null, multiSession);
        if (offlineKey != null) {
            drmSessionManager.setMode(DefaultDrmSessionManager.MODE_PLAYBACK,
                    offlineKey);
        }
        return drmSessionManager;

    }


    /**
     * Following method is responsible two handle media completion post processing.
     */
    public void postMediaEnded() {
        preferenceManager.setClickInfoToPref(false);


        if (progressView != null && progressView.getVisibility() == VISIBLE) {
            progressView.setVisibility(GONE);
        }

        playlist_position = playlist_position + 1;

        releasePlayer();
        finish();

    }


}
