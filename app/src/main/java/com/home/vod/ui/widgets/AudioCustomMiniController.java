package com.home.vod.ui.widgets;

import static android.content.Context.MODE_PRIVATE;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.ui.fragment.HomeFragment.swipeRefreshLayout;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.Util.MultiAndSingParentnameGlobal;
import static com.home.vod.util.Util.QUEUE_ARRAY;
import static com.home.vod.util.Util.SongNameGlobal;
import static com.home.vod.util.Util.adapterposition;
import static com.home.vod.util.Util.addplaylist_btn_layout;
import static player.utils.Util.DEFAULT_NO_DETAILS_AVAILABLE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
import com.home.apisdk.apiModel.GetVideoDetailsInput;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.vod.R;
import com.home.vod.model.QueueModel;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.service.MusicService;
import com.home.vod.ui.PlayerViewPagerAdapter;
import com.home.vod.ui.activity.MainActivity;
import com.home.vod.util.Constant;
import com.home.vod.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class AudioCustomMiniController implements VideoDetailsAsynctask.VideoDetailsListener {
    private static Context mContext;
    private CoordinatorLayout minicontroller;
    private SeekBar seekbar_botomSht, seekBar;
    private ImageView albumArt_player;
    private LinearLayout open_bottomSheet_controller, miniControllerLayout;
    private TextView song_p_name, artist_p_name, songname_player, curent_duration, total_duration;
    private ImageView equalizer, background_player, player_close, player_image_main, player_play_ic, player_next_icc, player_prev_ic;
    private ProgressBar recylerview_progress, musicProgress;
    private ViewPager squizeViewPager;
    private View bottomSheet;
    private Toolbar mActionBarToolbar;
    public BottomSheetBehavior mBottomSheetBehavior;
    public static boolean bootomsheet_open = false;
    private ProgressBarHandler pDialog;
    private Button clearQueueBtn;
    private String album_name = "", song_url, song_imageUrl, song_name, artistName, permalink, AlbumArtUrl, movie_unique_id, movie_stream_unique_id;
    private SQLiteDatabase DB;
    private static LanguagePreference languagePreference;
    private Timer durationTimer;
    private boolean isRunning = false;
    private FragmentManager supportFragmentManager;
    private static final int STATE_PLAYING = 1;
    private static final int STATE_PAUSED = 2;
    private PreferenceManager preferenceManager;
    private boolean isPlaying = true;
    private String movieId, episodeId;
    private QueueModel tempQueueModel;
    private String played_length = "0";
    private boolean isClickPreviousNext = false;
    private int audioPlayerCurrentPosition;
    private String clickNextPrevious;


    public AudioCustomMiniController(Context ctx, String desiredString, FragmentManager supportFragmentManager) {

        mContext = ctx;
        DB = mContext.openOrCreateDatabase(Util.DATABASE_NAME, MODE_PRIVATE, null);
        permalink = desiredString;
        languagePreference = LanguagePreference.getLanguagePreference(mContext);
        this.supportFragmentManager = supportFragmentManager;
        preferenceManager = PreferenceManager.getPreferenceManager(mContext);
        pDialog = new ProgressBarHandler(mContext);

    }

    public AudioCustomMiniController(Context ctx) {
        mContext = ctx;
        languagePreference = LanguagePreference.getLanguagePreference(mContext);
        preferenceManager = PreferenceManager.getPreferenceManager(mContext);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void init(CoordinatorLayout minicontroller, SeekBar seekbar_botomSht, ImageView albumArt_player, LinearLayout open_bottomSheet_controller, TextView song_p_name, TextView artist_p_name, ImageView equalizer, ImageView background_player, ImageView player_close, TextView songname_player, ImageView player_image_main, ProgressBar recylerview_progress,
                     ViewPager squizeViewPager, TextView curent_duration, ImageView player_play_ic, ImageView player_next_icc, ImageView player_prev_ic, TextView total_duration, SeekBar seekBar, View bottomSheet, Toolbar mActionBarToolbar, LinearLayout miniControllerLayout, ProgressBar musicProgress, Button clearQueue) {


        this.minicontroller = minicontroller;
        this.seekbar_botomSht = seekbar_botomSht;
        this.albumArt_player = albumArt_player;
        this.open_bottomSheet_controller = open_bottomSheet_controller;
        this.song_p_name = song_p_name;
        this.artist_p_name = artist_p_name;
        this.equalizer = equalizer;
        this.background_player = background_player;
        this.player_close = player_close;
        this.songname_player = songname_player;
        this.player_image_main = player_image_main;
        this.recylerview_progress = recylerview_progress;
        this.squizeViewPager = squizeViewPager;
        this.curent_duration = curent_duration;
        this.player_play_ic = player_play_ic;
        this.player_next_icc = player_next_icc;
        this.player_prev_ic = player_prev_ic;
        this.total_duration = total_duration;
        this.seekBar = seekBar;
        this.bottomSheet = bottomSheet;
        this.mActionBarToolbar = mActionBarToolbar;
        this.miniControllerLayout = miniControllerLayout;
        this.musicProgress = musicProgress;
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        this.clearQueueBtn = clearQueue;

        enabledisablenextprevious_btn_from_other_page(adapterposition, QUEUE_ARRAY);

        if (preferenceManager.getSongStatusFromPref() != null && preferenceManager.getSongStatusFromPref().equals("playing")
                || preferenceManager.getSongStatusFromPref().equals("pause")) {
            this.minicontroller.setVisibility(View.VISIBLE);
            initAudioPlayer();
        } else if (preferenceManager.getSongStatusFromPref() != null && preferenceManager.getSongStatusFromPref().equals("close")) {
            this.minicontroller.setVisibility(View.GONE);
            preferenceManager.setSongStatustoPref(null);
        } else {
            this.minicontroller.setVisibility(View.GONE);

        }

        try {
            mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    newState = BottomSheetBehavior.STATE_DRAGGING;
                }

                @Override
                public void onSlide(@NonNull final View bottomSheet, float slideOffset) {
                    if (slideOffset == 1) {
                        bootomsheet_open = true;
                        songname_player.setText(SongNameGlobal);
                        player_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            }
                        });

                        ((AppCompatActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mActionBarToolbar.setVisibility(View.GONE);
                            }
                        });
                    }
                    if (slideOffset > .7) {
                        ((AppCompatActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mActionBarToolbar.setVisibility(View.GONE);
                            }
                        });
                    }
                    if (slideOffset < 1) {
                        ((AppCompatActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mActionBarToolbar.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                    if (slideOffset > .5) {
                        miniControllerLayout.setVisibility(View.GONE);
                        ((AppCompatActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mActionBarToolbar.setVisibility(View.GONE);
                            }
                        });
                    } else {
                        ((AppCompatActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mActionBarToolbar.setVisibility(View.VISIBLE);
                            }
                        });

                        miniControllerLayout.setVisibility(View.VISIBLE);
                        bootomsheet_open = false;
                    }
                }
            });
        } catch (Exception e) {
            e.toString();
        }

        try {
            open_bottomSheet_controller.setOnClickListener(view -> mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED));
        } catch (Exception e) {
            e.printStackTrace();
        }

        albumArt_player.setOnClickListener(view -> {
            Intent Pintent = new Intent("SERVICE_ACTION_NEXT");
            mContext.sendBroadcast(Pintent);
        });
        player_play_ic.setOnClickListener(view -> {

            Intent Pintent = new Intent("SERVICE_ACTION_NEXT");
            mContext.sendBroadcast(Pintent);
            StartTimer();
        });

        player_next_icc.setOnClickListener(view -> Next());
        player_prev_ic.setOnClickListener(view -> previous());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                StartTimer();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (MusicService.mediaPlayer != null) {
                    MusicService.mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });

        seekBar.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                Intent SEEK_ACTION_DOWN = new Intent("SEEK_ACTION_DOWN");
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(SEEK_ACTION_DOWN);

            } else if (event.getAction() == MotionEvent.ACTION_UP) {

                Intent ACTION_UP = new Intent("SEEK_ACTION_UP");
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(ACTION_UP);

            }
            return false;
        });


        clearQueueBtn.setOnClickListener(view -> {

            QUEUE_ARRAY.clear();
            adapterposition = 0;
            preferenceManager.setCurrentAudioPosition(adapterposition);
            PlayerViewPagerAdapter testPagerAdapter = new PlayerViewPagerAdapter(supportFragmentManager);
            squizeViewPager.setAdapter(testPagerAdapter);
            testPagerAdapter.notifyDataSetChanged();
            if (bootomsheet_open) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                bootomsheet_open = false;
            }
            preferenceManager.setSongStatustoPref("clear");
            testPagerAdapter.notifyDataSetChanged();
            minicontroller.setVisibility(View.GONE);
            if (MusicService.mediaPlayer != null) {
                MusicService.mediaPlayer.setPlayWhenReady(false);
            }
            /*
             * @desc: set bottom margin to normal as after clear queue the mini controller will not be visible.
             * */
            try {
                if (Util.recyclerView != null) {
                    ViewGroup.MarginLayoutParams params =
                            (ViewGroup.MarginLayoutParams) Util.recyclerView.getLayoutParams();
                    params.setMargins(0, 6, 0, 0);
                    Util.recyclerView.setLayoutParams(params);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (swipeRefreshLayout != null) {
                    ViewGroup.MarginLayoutParams params =
                            (ViewGroup.MarginLayoutParams) swipeRefreshLayout.getLayoutParams();
                    params.setMargins(0, 6, 0, 0);
                    swipeRefreshLayout.setLayoutParams(params);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (addplaylist_btn_layout != null) {
                    ViewGroup.MarginLayoutParams params =
                            (ViewGroup.MarginLayoutParams) addplaylist_btn_layout.getLayoutParams();
                    params.setMargins(0, 6, 0, 0);
                    addplaylist_btn_layout.setLayoutParams(params);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    /*
     * @desc: This method is used to initialize all player details if the song is already playing.
     * */
    private void initAudioPlayer() {
        Intent in = new Intent();
        in.putExtra("SongName", preferenceManager.getAudioDetailsFromPref(PreferenceManager.SONG_NAME));
        in.putExtra("Artist", preferenceManager.getAudioDetailsFromPref(PreferenceManager.ARTIST_NAME));
        in.putExtra("songStatus", preferenceManager.getAudioDetailsFromPref(PreferenceManager.SONG_STATUS_UI_UPDATE));
        in.putExtra("songImageUrl", preferenceManager.getAudioDetailsFromPref(PreferenceManager.SONG_IMAGE_URL));
        in.putExtra("song_url", preferenceManager.getAudioDetailsFromPref(PreferenceManager.SONG_URL));
        String songStatus = preferenceManager.getAudioDetailsFromPref(PreferenceManager.SONG_STATUS);
        if (songStatus != null) {
            if (songStatus.equals("play")) {
                albumArt_player.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                player_play_ic.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
            }
            if (songStatus.equals("pause")) {
                albumArt_player.setImageResource(R.drawable.play_icon);
                player_play_ic.setImageResource(R.drawable.play_icon);
            }
        }

        Picasso.with(mContext)
                .load(preferenceManager.getAudioDetailsFromPref(PreferenceManager.SONG_IMAGE_URL))
                .resize(500, 500) // resizes the image to these dimensions (in pixel)
                .centerCrop()
                .into(background_player);

        Glide.with(mContext).asBitmap().load(preferenceManager.getAudioDetailsFromPref(PreferenceManager.SONG_IMAGE_URL)).into(new BitmapImageViewTarget(player_image_main) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                player_image_main.setImageDrawable(circularBitmapDrawable);
            }
        });

        song_p_name.setText(preferenceManager.getAudioDetailsFromPref(PreferenceManager.SONG_NAME));

        if (preferenceManager.getAudioDetailsFromPref(PreferenceManager.ARTIST_NAME) == null ||
                preferenceManager.getAudioDetailsFromPref(PreferenceManager.ARTIST_NAME).isEmpty()) {
            artist_p_name.setVisibility(View.GONE);
        } else {
            artist_p_name.setVisibility(View.VISIBLE);
            artist_p_name.setText(preferenceManager.getAudioDetailsFromPref(PreferenceManager.ARTIST_NAME));
        }

        Typeface Artist_p_name_tf = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.light_fonts));
        artist_p_name.setTypeface(Artist_p_name_tf);
        try {

            PlayerViewPagerAdapter testPagerAdapter = new PlayerViewPagerAdapter(supportFragmentManager);
            squizeViewPager.setAdapter(testPagerAdapter);
            testPagerAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.v("MUVI", e.toString());
        }

    }

    /*
     * @desc: This method is used to play the next content from Queue.
     **/
    public void Next() {
        try {

            isClickPreviousNext = true;
            clickNextPrevious = "next";
            audioPlayerCurrentPosition = adapterposition;

            if (adapterposition == QUEUE_ARRAY.size() - 1) {
                return;
            }
            final QueueModel queueModel = QUEUE_ARRAY.get(adapterposition + 1);
            adapterposition = adapterposition + 1;

            preferenceManager.setCurrentAudioPosition(adapterposition);
            /*
             * @desc: check if the next song is paid or unpaid*/

            if ((preferenceManager.getLoginFeatureFromPref() == 1)) {
                if (queueModel.getSongStatus() != null && queueModel.getSongStatus().equals("episode")) {
                    tempQueueModel = queueModel;

                    if (queueModel.getIsConverted() == 1) {
                        movieId = queueModel.getMovieId();
                        episodeId = queueModel.getEpisodeId();
                        getVideoInfo();
                    } else {
                        if (MusicService.mediaPlayer.getCurrentPosition() >= MusicService.mediaPlayer.getDuration()) {
                            MusicService.mediaPlayer.seekTo(0);
                        }
                        showNoDetailsPopup();
                        Intent Pintent = new Intent("SERVICE_ACTION_NEXT");
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(Pintent);
                    }

                } else {
                    try {
                        tempQueueModel = queueModel;
                        movieId = queueModel.getMovieId();
                        episodeId = queueModel.getEpisodeId();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    getVideoInfo();
                }
            } else {
                try {
                    tempQueueModel = queueModel;
                    movieId = queueModel.getMovieId();
                    episodeId = queueModel.getEpisodeId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getVideoInfo();
            }

        } catch (Exception e) {
            Log.v("MUVI::", "called" + e.toString());
        }
    }

    private void showNoDetailsPopup() {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(languagePreference.getTextofLanguage(NO_DETAILS_AVAILABLE, LanguagePreference.DEFAULT_NO_DETAILS_AVAILABLE));
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
    }

    /*
     * @desc: This method is used to play the previous content from Queue.
     * */
    public void previous() {
        try {

            isClickPreviousNext = true;
            clickNextPrevious = "previous";
            audioPlayerCurrentPosition = adapterposition;

            final QueueModel queueModel = QUEUE_ARRAY.get(adapterposition - 1);
            adapterposition = adapterposition - 1;

            preferenceManager.setCurrentAudioPosition(adapterposition);
            /*
             * @desc: check if the next song is paid or unpaid*/

            if ((preferenceManager.getLoginFeatureFromPref() == 1)) {
                if (queueModel.getSongStatus() != null && queueModel.getSongStatus().equals("episode")) {
                    tempQueueModel = queueModel;
                    if (queueModel.getIsConverted() == 1) {
                        movieId = queueModel.getMovieId();
                        episodeId = queueModel.getEpisodeId();
                        getVideoInfo();
                    } else {
                        if (MusicService.mediaPlayer.getCurrentPosition() >= MusicService.mediaPlayer.getDuration()) {
                            MusicService.mediaPlayer.seekTo(0);
                        }
                        showNoDetailsPopup();
                        Intent Pintent = new Intent("SERVICE_ACTION_NEXT");
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(Pintent);
                    }

                } else {

                    try {
                        tempQueueModel = queueModel;
                        movieId = queueModel.getMovieId();
                        episodeId = queueModel.getEpisodeId();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    getVideoInfo();
                }
            } else {
                try {
                    tempQueueModel = queueModel;
                    movieId = queueModel.getMovieId();
                    episodeId = queueModel.getEpisodeId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getVideoInfo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void enabledisablenextprevious_btn_from_other_page(int adapterposition, ArrayList<QueueModel> queueArray) {
        try {
            if (queueArray != null && queueArray.size() == 1) {
                disableView(player_prev_ic);
                disableView(player_next_icc);
                return;
            }
            if (queueArray != null) {
                if (adapterposition == 0 && queueArray.size() > 0) {
                    disableView(player_prev_ic);
                    enableView(player_next_icc);
                } else if (adapterposition == queueArray.size() - 1) {
                    disableView(player_next_icc);
                    enableView(player_prev_ic);
                } else {
                    enableView(player_prev_ic);
                    enableView(player_next_icc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enableView(ImageView view) {
        view.setEnabled(true);
        view.setClickable(true);
        ((ImageView) view).setColorFilter(ContextCompat.getColor(mContext, R.color.next_prev_color), android.graphics.PorterDuff.Mode.SRC_IN);
    }


    public void disableView(ImageView view) {
        view.setEnabled(false);
        view.setClickable(false);
        ((ImageView) view).setColorFilter(ContextCompat.getColor(mContext, R.color.hint_color), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public void PlayQueue(QueueModel itemsList, boolean itemClicked) {
        if (itemClicked) {
            MultiAndSingParentnameGlobal = itemsList.getParentName();
            song_url = itemsList.getSongUrl();
            song_imageUrl = itemsList.getAlbumArt();
            song_name = itemsList.getSongName();
            artistName = itemsList.getArtist_Name();
            movie_unique_id = tempQueueModel.getMovieId();
            movie_stream_unique_id = tempQueueModel.getEpisodeId();
            Player_State(1);
        }


    }

    /*
     * @desc: this function is used to paly song
     * */
    public void Player_State(int funId) {
        if (song_url == null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
            builder1.setMessage(Html.fromHtml("<font color='" + mContext.getResources().getColor(R.color.colorPrimary) + "'>" + languagePreference.getTextofLanguage(NO_DETAILS_AVAILABLE, DEFAULT_NO_DETAILS_AVAILABLE) + "</font>"));
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
                String name = (song_name).replace("\'", "");
                Intent j = new Intent(mContext, MusicService.class);
                j.putExtra("ALBUM", song_url);
                j.putExtra("PLAYED_LENGTH", played_length);
                j.putExtra("PERMALINK", permalink);
                j.putExtra("POSITION", 0);
                j.putExtra("ALBUM_ART", song_imageUrl);
                j.putExtra("ALBUM_NAME", MultiAndSingParentnameGlobal);
                j.putExtra("Artist", artistName);
                j.putExtra("ALBUM_SONG_NAME", song_name);
                j.putExtra("STATE", funId);
                j.putExtra("MOVIE_UNIQUE_ID", movieId);
                j.putExtra("MOVIE_STREAM_UNIQUE_ID", episodeId);
                j.setAction(Constant.ACTION.STARTFOREGROUND_ACTION);
                mContext.startService(j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    //this timer is used for seekbar with duration functionality(*for now its only 3 sec if u want to change do 3000=(ur time)).
    public void StartTimer() {
        if (isRunning) {
            durationTimer.cancel();
            isRunning = false;
        }


        durationTimer = new Timer();
        durationTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                durationTimer.cancel();
                isRunning = false;

            }
        }, 3000, 3000);
        isRunning = true;
    }

    public void playerDetails(Intent intent) {
        try {
            String Song_name = intent.getStringExtra("SongName");
            String Artist = intent.getStringExtra("Artist");
            songname_player.setText(Song_name);
            if (intent.getStringExtra("songImageUrl") != null) {
                AlbumArtUrl = intent.getStringExtra("songImageUrl");
            }
            if (intent.getStringExtra("song_url") != null) {
                song_url = intent.getStringExtra("song_url");
            }
            Picasso.with(mContext)
                    .load(AlbumArtUrl)
                    .resize(500, 500) // resizes the image to these dimensions (in pixel)
                    .centerCrop()
                    .into(background_player);

            Glide.with(mContext).asBitmap().load(AlbumArtUrl).into(new BitmapImageViewTarget(player_image_main) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    player_image_main.setImageDrawable(circularBitmapDrawable);
                }
            });
            song_p_name.setText(Song_name);
            if (Artist == null || Artist.isEmpty()) {
                artist_p_name.setVisibility(View.GONE);
            } else {
                artist_p_name.setVisibility(View.VISIBLE);
                artist_p_name.setText(Artist);
            }


            Typeface Artist_p_name_tf = Typeface.createFromAsset(mContext.getAssets(), mContext.getString(R.string.light_fonts));
            artist_p_name.setTypeface(Artist_p_name_tf);
            try {

                PlayerViewPagerAdapter testPagerAdapter = new PlayerViewPagerAdapter(supportFragmentManager);
                squizeViewPager.setAdapter(testPagerAdapter);
                testPagerAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                Log.v("MUVI", e.toString());
            }

            albumArt_player.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
            player_play_ic.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
            minicontroller.setVisibility(View.VISIBLE);
            //insert all the details in database
            preferenceManager.setAudioDetailsToPref(PreferenceManager.SONG_NAME, Song_name);
            preferenceManager.setAudioDetailsToPref(PreferenceManager.ARTIST_NAME, Artist);
            preferenceManager.setAudioDetailsToPref(PreferenceManager.SONG_IMAGE_URL, AlbumArtUrl);
            preferenceManager.setAudioDetailsToPref(PreferenceManager.SONG_URL, song_url);
        } catch (Exception e) {

        }

    }


    public static Drawable getDrawableByState(Context context, int state) {
        switch (state) {
            case STATE_PLAYING:
                AnimationDrawable animation = (AnimationDrawable)
                        ContextCompat.getDrawable(context, R.drawable.ic_equalizer_white_36dp);

                animation.start();
                return animation;
            case STATE_PAUSED:
                Drawable playDrawable = ContextCompat.getDrawable(context,
                        R.drawable.ic_equalizer1_white_36dp);

                return playDrawable;
            default:
                return null;
        }
    }

    //its used convertng milisec to mm:ss time format
    public static String timeC(long dur) {
        @SuppressLint("DefaultLocale") String strDate = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(dur) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(dur)),
                TimeUnit.MILLISECONDS.toSeconds(dur) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(dur)));
        return strDate;
    }

    /*
     * @desc: this function is used to close minicontroller
     * */
    public void closeMiniController() {
        try {
            if (Util.recyclerView != null) {
                ViewGroup.MarginLayoutParams params =
                        (ViewGroup.MarginLayoutParams) Util.recyclerView.getLayoutParams();
                params.setMargins(0, 6, 0, 0);
                Util.recyclerView.setLayoutParams(params);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (swipeRefreshLayout != null) {
                ViewGroup.MarginLayoutParams params =
                        (ViewGroup.MarginLayoutParams) swipeRefreshLayout.getLayoutParams();
                params.setMargins(0, 6, 0, 0);
                swipeRefreshLayout.setLayoutParams(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (addplaylist_btn_layout != null) {
                ViewGroup.MarginLayoutParams params =
                        (ViewGroup.MarginLayoutParams) addplaylist_btn_layout.getLayoutParams();
                params.setMargins(0, 6, 0, 0);
                addplaylist_btn_layout.setLayoutParams(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        minicontroller.setVisibility(View.GONE);
        QUEUE_ARRAY.clear();
        adapterposition = 0;


        if (mActionBarToolbar != null && mActionBarToolbar.getVisibility() == View.GONE)
            mActionBarToolbar.setVisibility(View.VISIBLE);
        Intent stopServiceIntent = new Intent(mContext, MusicService.class);
        mContext.stopService(stopServiceIntent);
    }

    /*
     * @desc:This method is used to play the content if a song is clicked from the player page*/
    public void playSongFromPlayList(QueueModel queueModel, int position) {

        isClickPreviousNext = false;
        clickNextPrevious = "playlist";
        audioPlayerCurrentPosition = position;

        if (queueModel != null) {
            tempQueueModel = queueModel;
            adapterposition = position;
        }

        if ((preferenceManager.getLoginFeatureFromPref() == 1)) {
            if (queueModel.getSongStatus() != null && queueModel.getSongStatus().equals("episode")) {
                if (queueModel.getIsConverted() == 1) {
                    movieId = queueModel.getMovieId();
                    episodeId = queueModel.getEpisodeId();
                    getVideoInfo();

                } else {
                    if (MusicService.mediaPlayer.getCurrentPosition() >= MusicService.mediaPlayer.getDuration()) {
                        MusicService.mediaPlayer.seekTo(0);
                    }
                    showNoDetailsPopup();
                    Intent Pintent = new Intent("SERVICE_ACTION_NEXT");
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(Pintent);
                }
            } else {
                try {
                    movieId = queueModel.getMovieId();
                    episodeId = queueModel.getEpisodeId();
                    getVideoInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                movieId = queueModel.getMovieId();
                episodeId = queueModel.getEpisodeId();
                getVideoInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    public void getVideoInfo() {
        GetVideoDetailsInput getVideoDetailsInput = new GetVideoDetailsInput();
        getVideoDetailsInput.setAuthToken(authTokenStr);
        getVideoDetailsInput.setUser_id(preferenceManager.getUseridFromPref());
        getVideoDetailsInput.setContent_uniq_id(movieId);
        getVideoDetailsInput.setStream_uniq_id(episodeId);
        getVideoDetailsInput.setInternetSpeed(MainActivity.internetSpeed.trim());
        getVideoDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        getVideoDetailsInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        getVideoDetailsInput.setAdParameter(Util.getVastAdParameter());
        VideoDetailsAsynctask asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, this, mContext);
        asynLoadVideoUrls.execute();
    }

    @Override
    public void onVideoDetailsPreExecuteStarted() {
        if (pDialog != null) {
            pDialog.show();
        }
    }

    @Override
    public void onVideoDetailsPostExecuteCompleted(Video_Details_Output video_details_output, int code, String status, String message) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }

        if (code == 200) {

            if (clickNextPrevious.equals("next")) {

                if (audioPlayerCurrentPosition < QUEUE_ARRAY.size()) {

                    if (player_next_icc != null && player_prev_ic != null) {
                        if (audioPlayerCurrentPosition + 1 == QUEUE_ARRAY.size() - 1) {
                            disableView(player_next_icc);
                            enableView(player_prev_ic);
                        } else {
                            enableView(player_prev_ic);
                            enableView(player_next_icc);
                        }
                    }
                } else {
                    audioPlayerCurrentPosition = 0;
                    disableView(player_next_icc);
                }
            } else if (clickNextPrevious.equals("previous")) {
                if (audioPlayerCurrentPosition > 0) {

                    if (player_prev_ic != null && player_next_icc != null) {
                        if (audioPlayerCurrentPosition == 1) {
                            disableView(player_prev_ic);
                            enableView(player_next_icc);
                        } else {
                            enableView(player_prev_ic);
                            enableView(player_next_icc);
                        }
                    }
                } else {
                    disableView(player_prev_ic);
                    enableView(player_next_icc);
                }
            } else if (clickNextPrevious.equals("playlist")) {
                if (audioPlayerCurrentPosition == 0 && QUEUE_ARRAY.size() > 0) {
                    disableView(player_prev_ic);
                    enableView(player_next_icc);
                } else if (audioPlayerCurrentPosition == QUEUE_ARRAY.size() - 1) {
                    disableView(player_next_icc);
                    enableView(player_prev_ic);
                } else {
                    enableView(player_prev_ic);
                    enableView(player_next_icc);
                }
            }

            song_url = video_details_output.getVideoUrl();
            MultiAndSingParentnameGlobal = tempQueueModel.getParentName();
            song_imageUrl = tempQueueModel.getAlbumArt();
            song_name = tempQueueModel.getSongName();
            artistName = tempQueueModel.getArtist_Name();
            movie_unique_id = tempQueueModel.getMovieId();
            movie_stream_unique_id = tempQueueModel.getEpisodeId();

            try {
                if (!isClickPreviousNext) {
                    played_length = video_details_output.getPlayed_length();
                } else {
                    played_length = "0";
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            Player_State(1);
        } else if (code == 436) {
            showNoDetailsPopup();
        }
    }


    public void collapse_buttomsheet() {
        if (bootomsheet_open) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

}
