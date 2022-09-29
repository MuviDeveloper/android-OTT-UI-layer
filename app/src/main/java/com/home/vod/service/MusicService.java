package com.home.vod.service;

import static com.home.vod.util.Util.QUEUE_ARRAY;
import static com.home.vod.util.Util.adapterposition;
import static com.home.vod.util.Util.updateProgressTimer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.media.session.MediaButtonReceiver;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.home.vod.ObservableObject;
import com.home.vod.R;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.activity.Blank;
import com.home.vod.ui.activity.MainActivity;
import com.home.vod.ui.widgets.AudioCustomMiniController;
import com.home.vod.util.Constant;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MusicService extends Service  {

    public static SimpleExoPlayer mediaPlayer;

    private String notify_image, desire_string;
    public String SongName, AlbumName, url, Artist;
    private Notification status;
    public boolean mediaChanged = true;
    public SimpleExoPlayer player;
    private ExtractorsFactory extractorsFactory;
    private DefaultTrackSelector trackSelector;
    private DefaultTrackSelector.Parameters trackSelectorParameters;

    private DefaultBandwidthMeter defaultBandwidthMeter;
    private DataSource.Factory dataSourceFactory;
    private MediaSource mediaSource;
    private PreferenceManager preferenceManager;
    private LanguagePreference languagePreference;

    private  FeatureHandler featureHandler;
    private  SharedPreferences Ipaddress_pref;
    private int playerPosition = 0;
    private boolean seek_status = false;
    private boolean is_resumeplay_enable = false;
    private  int seekplayerPosition = 0;
    private  boolean is_click_playPause = false;
    private  int played_length;
    private  Handler timerHandler = new Handler();


    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(SERVICE_ACTION, new IntentFilter("SERVICE_ACTION_NEXT"));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(SEEK_ACTION_UP, new IntentFilter("SEEK_ACTION_UP"));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(CLEAR_QUEUE,new IntentFilter(Constant.ACTION.QUEUE_CLEAR));

        extractorsFactory = new DefaultExtractorsFactory();

        trackSelectorParameters = new DefaultTrackSelector.ParametersBuilder(MusicService.this).build();
        trackSelector = new DefaultTrackSelector(MusicService.this);
        trackSelector.setParameters(trackSelectorParameters);

        defaultBandwidthMeter = new DefaultBandwidthMeter();
        languagePreference=LanguagePreference.getLanguagePreference(MusicService.this);
        Ipaddress_pref = getApplicationContext().getSharedPreferences("ipaddress", MODE_PRIVATE);
        featureHandler = FeatureHandler.getFeaturePreference(MusicService.this);
        dataSourceFactory = new DefaultDataSourceFactory(this,
                com.google.android.exoplayer2.util.Util.getUserAgent(this, "aeonaudio"), defaultBandwidthMeter);
        preferenceManager = PreferenceManager.getPreferenceManager(getApplicationContext());

    }

    private BroadcastReceiver CLEAR_QUEUE = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            player.setPlayWhenReady(false);
            stopForeground(true);
            StopTimer();
            stopSelf();


            /*
             * desc:The below code used for calling audio log */
            int total__player_duration = millisecondsToString((int) player.getDuration());
            int player_difference = total__player_duration - playerPosition;

            playerPosition = 0;
        }
    };

    private BroadcastReceiver SEEK_ACTION_UP = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            seek_status = true;

        }
    };

    private BroadcastReceiver SERVICE_ACTION = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
             is_click_playPause = true;

            if (!player.getPlayWhenReady()) {
                // play
                StartTimer();
                player.setPlayWhenReady(true);
                Intent Sintent = new Intent(Constant.SONG_STATUS);
                Sintent.putExtra("songStatus", "play");
                showNotification(1);
                sendBroadcast(Sintent);
            } else {
                // pause
                StopTimer();
                player.setPlayWhenReady(false);
                Intent Pintent = new Intent(Constant.SONG_STATUS);
                Pintent.putExtra("songStatus", "pause");
                showNotification(0);
                sendBroadcast(Pintent);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            preferenceManager.setSongStatustoPref("close");
            unregisterReceiver(SERVICE_ACTION);
            unregisterReceiver(SEEK_ACTION_UP);
            player.setPlayWhenReady(false);
            player.release();
            StopTimer();
            stopForeground(true);
            stopSelf();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        try {
            preferenceManager.setSongStatustoPref("close");
            adapterposition = 0;
            Util.QUEUE_ARRAY.clear();
            unregisterReceiver(SERVICE_ACTION);
            player.setPlayWhenReady(false);
            player.release();
            StopTimer();
            stopForeground(true);
            stopSelf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {

            /*
            * @Desc: This is used to handle one at time e.g. when chromcast video is playing we cannot play audio and vice versa.
            **/
            if (intent.getAction().equals(Constant.ACTION.STARTFOREGROUND_ACTION)) {
                if (updateProgressTimer != null) {
                    try {
                        StopTimer();
                    } catch (Exception g) {
                    }
                }

                url = intent.getStringExtra("ALBUM");
                SongName = intent.getStringExtra("ALBUM_SONG_NAME");
                Artist = intent.getStringExtra("Artist");
                desire_string = intent.getStringExtra("PERMALINK");
                AlbumName = intent.getStringExtra("ALBUM_NAME");
                notify_image = intent.getStringExtra("ALBUM_ART");
                int func = intent.getIntExtra("STATE", 0);
                try {
                    played_length =  Util.isDouble(intent.getStringExtra("PLAYED_LENGTH"));
                } catch (NumberFormatException e) {
                    played_length = 0;
                    e.printStackTrace();
                }


                Intent playerData = new Intent();
                playerData.putExtra("SongName", SongName);
                playerData.putExtra("PERMALINK", desire_string);
                playerData.putExtra("songImageUrl", notify_image);
                playerData.putExtra("Artist", Artist);
                playerData.putExtra("song_url", url);
                playerData.putExtra("IntentFor", "IntentFor");
                Util.ArtistName = Artist;
                Util.SongNameGlobal = SongName;

                showNotification(1);

                switch (func) {
                    case 1:
                        try {

                            if (player != null) {
                                player.stop();
                                player.release();
                            }
                            if (mediaPlayer != null) {
                                mediaPlayer.stop();
                                mediaPlayer.release();
                            }
                            try {
                                mediaSource = new ExtractorMediaSource(Uri.parse(url),
                                        dataSourceFactory, extractorsFactory, null, null);

                                preferenceManager.setSongStatustoPref("");
                                Uri uri = Uri.parse(url);
                                player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
                                mediaPlayer = player;
                                player.setRepeatMode(Player.REPEAT_MODE_OFF);
                                player.setPlayWhenReady(false);
                                player.prepare(mediaSource);
                                player.seekTo((int) player.getCurrentPosition(), C.TIME_UNSET);
                                player.setPlayWhenReady(true);
                                mediaChanged = true;
                                showNotification(1);
                                Util.Duration = (int) player.getDuration();

                                StartTimer();

                                player.addListener(new Player.EventListener() {
                                    @Override
                                    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                                        if (playbackState == Player.STATE_READY) {
                                            if (preferenceManager.getSongStatusFromPref().equals("close")) {
                                                preferenceManager.setSongStatustoPref("");
                                                preferenceManager.setPreviousSongFromPref("");
                                                preferenceManager.setIsPlayAllClicked(false);
                                            }
                                            else if (preferenceManager.getSongStatusFromPref().equals("clear")) {
                                                // clear queue
                                                preferenceManager.setSongStatustoPref("");
                                                preferenceManager.setPreviousSongFromPref("");
                                                preferenceManager.setIsPlayAllClicked(false);
                                                player.setPlayWhenReady(false);
                                                stopForeground(true);
                                                StopTimer();



                                                /*Author:Chitra
                                                 * desc:The below code used for calling audio log */

                                                int total__player_duration = millisecondsToString((int) player.getDuration());
                                                int player_difference = total__player_duration - playerPosition;

                                                if (player_difference <= 0 || player_difference == 1) {
                                                }

                                                playerPosition = 0;

                                            }

                                            else if (!(preferenceManager.getSongStatusFromPref().equals("pause"))) {
                                                preferenceManager.setSongStatustoPref("playing");
                                                /*Author:Chitra
                                                * @desc: The below condition is execute when user trying to seek*/

                                                if(!seek_status) {

                                                    if (is_click_playPause) {
                                                        is_click_playPause = false;
                                                       return;
                                                    }
                                                    if(is_resumeplay_enable){
                                                        is_resumeplay_enable = false;
                                                        return;
                                                    }

                                                    playerPosition = 0;
                                                } else {

                                                    seek_status = false;
                                                    if (seekplayerPosition == playerPosition) {
                                                        int total__player_duration = millisecondsToString((int) player.getDuration());
                                                        int player_difference = total__player_duration - playerPosition;


                                                    } else {

                                                        if (NetworkStatus.getInstance().isConnected(MusicService.this)) {
                                                            int total__player_duration = millisecondsToString((int) player.getDuration());
                                                            int player_difference = total__player_duration - playerPosition;

                                                        }

                                                    }

                                                }

                                            } else if (preferenceManager.getSongStatusFromPref().length() > 0 && preferenceManager.getSongStatusFromPref().equals("pause")) {
                                                stopForeground(true);
                                            }

                                        } else if (playbackState == Player.STATE_ENDED && QUEUE_ARRAY.size() == 1) {

                                            mediaChanged = false;
                                            player.setPlayWhenReady(false);
                                            player.seekTo(0);
                                            Intent Pintent = new Intent(Constant.SONG_STATUS);
                                            Pintent.putExtra("songStatus", "pause");
                                            preferenceManager.setSongStatustoPref(null);
                                            sendBroadcast(Pintent);
                                        } else if (playbackState == Player.STATE_ENDED && QUEUE_ARRAY.size() > 0) {
                                            mediaChanged = true;
                                        }
                                    }
                                });

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        ObservableObject.getInstance().updateValue(playerData);

                        Intent Str_intent = new Intent(Constant.SONG_STATUS);
                        Str_intent.putExtra("songStatus", "play");
                        sendBroadcast(Str_intent);

                        break;

                }


            } else if (intent.getAction().equals(Constant.ACTION.QUEUE_CLEAR)) {

                player.setPlayWhenReady(false);
                stopForeground(true);
                StopTimer();
                stopSelf();

            } else if (intent.getAction().equals(Constant.ACTION.PREV_ACTION)) {

                if (adapterposition==0){
                    Toast.makeText(getApplicationContext(), languagePreference.getTextofLanguage(LanguagePreference.FIRST_SONG,LanguagePreference.DEFAULT_FIRST_SONG), Toast.LENGTH_SHORT).show();
                }

                if (!Util.isRunning(getApplicationContext())) {
                    AudioCustomMiniController audioCustomMiniController = new AudioCustomMiniController(getApplicationContext());
                    audioCustomMiniController.previous();
                } else {
                    Intent previntent = new Intent(Constant.SONG_STATUS_PREVIOUS);
                    previntent.putExtra("songstaus_previous", "previous");
                    sendBroadcast(previntent);
                }



            } else if (intent.getAction().equals(Constant.ACTION.PLAY_ACTION)) {
                is_click_playPause = true;
                if (!player.getPlayWhenReady()) {
                    preferenceManager.setAudioDetailsToPref(PreferenceManager.SONG_STATUS, "play");
                    showNotification(1);
                    player.setPlayWhenReady(true);
                    Intent Sintent = new Intent(Constant.SONG_STATUS);
                    Sintent.putExtra("songStatus", "play");
                    sendBroadcast(Sintent);
                } else {
                    preferenceManager.setAudioDetailsToPref(PreferenceManager.SONG_STATUS, "pause");
                    showNotification(0);
                    player.setPlayWhenReady(false);
                    Intent Pintent = new Intent(Constant.SONG_STATUS);
                    Pintent.putExtra("songStatus", "pause");
                    sendBroadcast(Pintent);
                }
            } else if (intent.getAction().equals(Constant.ACTION.NEXT_ACTION)) {
                if (adapterposition==QUEUE_ARRAY.size()-1){
                    Toast.makeText(getApplicationContext(), languagePreference.getTextofLanguage(LanguagePreference.LAST_SONG,LanguagePreference.DEFAULT_LAST_SONG), Toast.LENGTH_SHORT).show();
                }

                if (!Util.isRunning(getApplicationContext())) {
                    AudioCustomMiniController audioCustomMiniController = new AudioCustomMiniController(getApplicationContext());
                    audioCustomMiniController.Next();
                } else {
                    Intent nextintent = new Intent(Constant.SONG_STATUS_NEXT);
                    nextintent.putExtra("songstaus_next", "next");
                    sendBroadcast(nextintent);
                }

            } else if (intent.getAction().equals(
                    Constant.ACTION.STOPFOREGROUND_ACTION)) {


                /*
                 * desc:The below code used for calling audio log */

                int total__player_duration = millisecondsToString((int) player.getDuration());
                int player_difference = total__player_duration - playerPosition;

                playerPosition = 0;
                player.release();

                Intent Pintent = new Intent(Constant.CLOSE_NOTIFICATION);
                Pintent.putExtra("closeNotification", "close");
                sendBroadcast(Pintent);
                preferenceManager.setSongStatustoPref("close");
                if (mediaPlayer != null) {
                    mediaPlayer.setPlayWhenReady(false);
                }

                StopTimer();
                stopForeground(true);
                stopSelf();


                if (((MainActivity) getApplicationContext()).mToolbar.getVisibility() == View.GONE) {
                    ((MainActivity) getApplicationContext()).mToolbar.setVisibility(View.VISIBLE);
                }
            }

        } catch (Exception e) {
            Log.v("", "" + e.getMessage());
        }
        return START_STICKY;
    }






    public static int millisecondsToString(int milliseconds) {
        int seconds = (int) (milliseconds / 1000);
        return seconds;
    }

    private void StartTimer() {
        StopTimer();
        timerHandler.postDelayed(timerRunnable, 1000);

    }

    private void StopTimer() {
        timerHandler.removeCallbacks(timerRunnable);
    }

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            /*Author:Chitra
             * @desc:The below condition is used to check Resume Watch feature*/
            timerHandler.postDelayed(this, 1000);

            if (player != null) {
                if (featureHandler.getFeatureStatus(FeatureHandler.IS_RESUME_WATCH_STATUS, FeatureHandler.DEFAULT_IS_RESUME_WATCH_STATUS) && played_length != 0 && played_length > 0 && player != null) {
                    is_resumeplay_enable = true;
                    playerPosition = played_length;
                    mediaPlayer.seekTo(playerPosition * 1000);
                    mediaPlayer.setPlayWhenReady(true);
                    played_length = 0;

                    StopTimer();
                    StartTimer();

                } else {

                    try {
                        int currentPositionStr = millisecondsToString((int) player.getCurrentPosition());
                        playerPosition = currentPositionStr;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            if (player.getCurrentPosition() ==0 && !player.getPlayWhenReady()){
                mHandler.obtainMessage(1).sendToTarget();
            }


            if (player.getCurrentPosition() > 0 && player.getCurrentPosition() >= player.getDuration() && mediaChanged) {
                mediaChanged = false;
                if (!QUEUE_ARRAY.get(QUEUE_ARRAY.size() - 1).getSongUrl().equalsIgnoreCase(url)) {
                    /*
                     * @desc:this is written for: when next item is played if the application is called.
                     * */
                    if (!Util.isRunning(getApplicationContext())) {
                        StopTimer();
                        callNextItemtoPlay();

                    } else {
                        Intent intentnext = new Intent(Constant.SONG_STATUS);
                        intentnext.putExtra("songStatus", "next");
                        sendBroadcast(intentnext);
                    }
                } else {

                    player.setPlayWhenReady(false);
                    player.seekTo(0);
                    Intent Pintent = new Intent(Constant.SONG_STATUS);
                    Pintent.putExtra("songStatus", "pause");
                    sendBroadcast(Pintent);
                    mHandler.obtainMessage(1).sendToTarget();
                }

            }

            Util.GetCurrentPosition = (int) player.getCurrentPosition();
            Intent Str_intent = new Intent(Constant.SONG_STATUS);
            Str_intent.putExtra("songStatus", player.getCurrentPosition() + "@@@@@" + player.getDuration());
            sendBroadcast(Str_intent);
        }
    };


    private void callNextItemtoPlay() {
        AudioCustomMiniController audioCustomMiniController = new AudioCustomMiniController(getApplicationContext());
        audioCustomMiniController.Next();
    }


    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            showNotification(0);
        }
    };

    public void showNotification(int val) {
        Intent notificationIntent;
        notificationIntent = new Intent(this, Blank.class);
        notificationIntent.setAction(Constant.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent,   PendingIntent.FLAG_IMMUTABLE);

        Intent previousIntent = new Intent(this, MusicService.class);
        previousIntent.setAction(Constant.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent playIntent = new Intent(this, MusicService.class);
        playIntent.setAction(Constant.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent nextIntent = new Intent(this, MusicService.class);
        nextIntent.setAction(Constant.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent closeIntent = new Intent(this, MusicService.class);
        closeIntent.setAction(Constant.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0,
                closeIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notificationBuilder = null;


        try {

            MediaSessionCompat mediaSession = new MediaSessionCompat(this, "media");

            notificationBuilder = new NotificationCompat.Builder(
                    getApplicationContext(),                      // Context
                    getResources().getString(R.string.app_name)   // NotificationActivity Channel Id
            );

            /*
            * @Desc:This is used to check the android 11 and above for notification visibility
            *       in foreground service.
            * */
            if(android.os.Build.VERSION.SDK_INT >Build.VERSION_CODES.Q){
                notificationBuilder
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)//lock screen visibility
                        .setSmallIcon(R.drawable.small_notification_icon)
                        .setLargeIcon(Util.is_contain_noimage(notify_image) ?
                                BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher)
                                : getBitmapFromURL(notify_image))
                        .setContentTitle(SongName)
                        .setContentText(!SongName.equals(AlbumName)?
                                AlbumName==null || AlbumName.isEmpty()?
                                        ((Artist==null ||Artist.isEmpty() )?
                                                "" : Artist): Artist==null|| Artist.isEmpty()?
                                        AlbumName:AlbumName+" - "+Artist:
                                Artist==null|| Artist.isEmpty()?
                                        "":Artist)
                        .setContentIntent(pendingIntent)
                        .setDeleteIntent(pcloseIntent)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .setCategory(NotificationCompat.CATEGORY_TRANSPORT)
                        .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                                .setShowActionsInCompactView(0, 1, 2)
                                .setShowCancelButton(true)
                                .setCancelButtonIntent(
                                        MediaButtonReceiver.buildMediaButtonPendingIntent(
                                                this, PlaybackStateCompat.ACTION_STOP))

                        );// Add media control buttons that invoke intents in your media service

            }else {
                notificationBuilder
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)//lock screen visibility
                        .setSmallIcon(R.drawable.small_notification_icon)
                        .setLargeIcon(Util.is_contain_noimage(notify_image) ?
                                BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher) :
                                getBitmapFromURL(notify_image))
                        .setContentTitle(SongName)
                        .setContentText(!SongName.equals(AlbumName)?
                                AlbumName==null || AlbumName.isEmpty()?
                                        ((Artist==null ||Artist.isEmpty() )?
                                                "" : Artist): Artist==null|| Artist.isEmpty()?
                                        AlbumName:AlbumName+" - "+Artist:
                                Artist==null|| Artist.isEmpty()?
                                        "":Artist)
                        .setContentIntent(pendingIntent)
                        .setDeleteIntent(pcloseIntent)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .setCategory(NotificationCompat.CATEGORY_TRANSPORT)
                        .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                                .setShowActionsInCompactView(0, 1, 2)
                                .setMediaSession(mediaSession.getSessionToken())
                                .setShowCancelButton(true)
                                .setCancelButtonIntent(
                                        MediaButtonReceiver.buildMediaButtonPendingIntent(
                                                this, PlaybackStateCompat.ACTION_STOP))

                        );// Add media control buttons that invoke intents in your media service

            }

            if (adapterposition>0){
                notificationBuilder.addAction(R.drawable.ic_skip_previous_black_24dp, "Previous", ppreviousIntent);//// #0
            }else {
                notificationBuilder.addAction(R.drawable.ic_skip_previous_gray, null, ppreviousIntent);//// #0
            }
            if (val == 1 ) {
                notificationBuilder.addAction(R.drawable.ic_pause_black_24dp, "Pause", pplayIntent);// #1
            }
            if (val == 0) {
                notificationBuilder.addAction(R.drawable.ic_play_arrow_black_24dp, "Play", pplayIntent); // #1
            }

            if (adapterposition==QUEUE_ARRAY.size()-1){
                notificationBuilder.addAction(R.drawable.ic_skip_next_gray, null, pnextIntent); // #2
            }else {
                notificationBuilder.addAction(R.drawable.ic_skip_next_black_24dp, "Next", pnextIntent); // #2
            }


            status = notificationBuilder.build();

            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        getResources().getString(R.string.app_name), //Channel Id
                        getResources().getString(R.string.app_name), //Channel Name
                        NotificationManager.IMPORTANCE_LOW);// Priority
                channel.setSound(null, null);
                notificationManager.createNotificationChannel(channel);
                notificationBuilder.setChannelId(getResources().getString(R.string.app_name));
            }

            if (val==1){
                startForeground(Constant.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
            }else {
                notificationBuilder.setOngoing(false);
                stopForeground(false);
                notificationManager.notify(Constant.NOTIFICATION_ID.FOREGROUND_SERVICE,status);
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("whenPause","last played..."+e.toString());
        }

    }



    public Bitmap getBitmapFromURL(String strURL) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        try {
            if (strURL == null) {
                strURL = "https://d2gx0xinochgze.cloudfront.net/public/no-image-a.png";
            }
            URL url = new URL(strURL);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            Bitmap myBitmap = null;
            try {
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);

            } catch (Exception e) {
                e.printStackTrace();
            }


            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
