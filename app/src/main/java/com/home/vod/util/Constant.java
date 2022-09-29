package com.home.vod.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.home.vod.BuildConfig;
import com.home.vod.model.CategoryListModel;
import com.home.vod.model.FilterListModel;
import com.home.vod.model.UGCFilterDataModel;

import java.util.HashMap;

/**
 * Created by MUVI on 1/18/2017.
 */

public class Constant {

    public static String permalinkStr;



    public static final String PERMALINK_INTENT_KEY = "PERMALINK_INTENT_KEY";
    public static final String GENRE_INTENT_KEY = "GENRE";
    public static final String CENSOR_RATING_INTENT_KEY = "CENSORRATING";
    public static final String CAST_INTENT_KEY = "CAST";
    public static final String SEASON_INTENT_TITLE_KEY = "SEASON_INTENT_TITLE_KEY";
    public static final String SEASON_INTENT_KEY = "SEASON";
    public static String authTokenStr = BuildConfig.AUTH_TOKEN; //new thought channel
    public static final String VIDEO_TITLE_INTENT_KEY = "VIDEO TITLE";
    public static final int IMAGE_PORTAIT_CONST = 1;
    public static final int IMAGE_LANDSCAPE_CONST = 0;


    //Constant for API Caching Table
    public static String ID="id";
    public static String API_NAME="api_name";
    public static String PERMALINK="permalink";
    public static String RESPONSE="response";
    public static String LAST_FETCHED="last_fetched";
    public static String TABLE_NAME="APICacheData";
    public static String DATABASE_NAME="APICacheDb";

    public static final long DEFAULT_TIME_CACHE_DIFFERENECE= (24*60*60*1000);

    //My upload filter

    public static  HashMap<String,UGCFilterDataModel> filteredMap = null;
    //generic Filter map
    public static  HashMap<String,FilterListModel> genereFilteredMap = null;
    public static  HashMap<String, CategoryListModel> genereCategoryMap = null;


    //UGC

    public static final int DEFAULT_POSTER_WIDTH = 280;
    public static final int DEFAULT_POSTER_HEIGHT = 400;
    public static final int DEFAULT_BANNER_WIDTH = 1200;
    public static final int DEFAULT_BANNER_HEIGHT = 350;
    public static final String IS_UGC_CONTENT = "is_ugc_content";
    ///AOD
    public interface ACTION {
        String MAIN_ACTION = "com.marothiatechs.customnotification.action.main";
        String INIT_ACTION = "com.marothiatechs.customnotification.action.init";
        String PREV_ACTION = "com.marothiatechs.customnotification.action.prev";
        String PLAY_ACTION = "com.marothiatechs.customnotification.action.play";
        String NEXT_ACTION = "com.marothiatechs.customnotification.action.next";
        String QUEUE_CLEAR = "com.marothiatechs.customnotification.action.queueclear";
        String STARTFOREGROUND_ACTION = "com.marothiatechs.customnotification.action.startforeground";
        String STOPFOREGROUND_ACTION = "com.marothiatechs.customnotification.action.stopforeground";

    }
    public interface NOTIFICATION_ID {
        int FOREGROUND_SERVICE = 101;
    }

    //AOD receiver constants
    public static String SONG_STATUS ="SONG_STATUS";
    public static String SONG_STATUS_PREVIOUS = "SONG_STATUS_PREVIOUS";
    public static String SONG_STATUS_NEXT = "SONG_STATUS_NEXT";
    public static String CLOSE_NOTIFICATION = "CLOSE_NOTIFICATION";
    public static String PLAY_DURATION_DIALOG = "PLAY_DURATION_DIALOG";

    public static int DOWNLOAD_COMPLETE = 1;





}
