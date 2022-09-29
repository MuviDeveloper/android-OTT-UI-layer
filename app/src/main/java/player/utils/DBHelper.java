package player.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import player.model.ContactModel1;
import player.model.SubtitleModel;

/**
 * Created by Nikunj on 27-08-2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "DOWNLOADMANAGER_ONDEMAND.db";
    public static final String TABLE_NAME = "DOWNLOADMANAGER_ONDEMAND";
    public static final String COLUMN_ID = "ID";

    public static final String TABLE_NAME_SUBTITLE_LUIMERE = "SUBTITLE_ONDEMAND";
    public static final String COLUMN_UID = "UID";
    public static final String COLUMN_LANGUAGE = "LANGUAGE";
    public static final String COLUMN_PATH = "PATH";


    // This code is done for bandwidth log of download contnet separately.......

    public static final String DOWNLOAD_CONTENT_INFO = "DOWNLOAD_CONTENT_INFO";


    private static final String DOWNLOAD_CONTENT_INFO_TABLE = "CREATE TABLE IF NOT EXISTS " + DOWNLOAD_CONTENT_INFO
            + " (download_contnet_id TEXT,log_id TEXT,authtoken TEXT,email TEXT,ipaddress TEXT,"+ "movie_id TEXT,"+"episode_id TEXT,"
            + "device_type TEXT," +"download_status TEXT," + "server_sending_final_status TEXT)";




    // This code is only responsible for Access period and Watch Period feature on Download Contnet
    public static final String WATCH_ACCESS_INFO = "WATCH_ACCESS_INFO";

    public static final String WATCH_ACCESS_INFO_TABLE = "CREATE TABLE IF NOT EXISTS " + WATCH_ACCESS_INFO
            + " (download_id TEXT,stream_unique_id TEXT,server_current_time INTEGER,watch_period INTEGER,access_period INTEGER," +
            "initial_played_time INTEGER," + "updated_server_current_time INTEGER,email TEXT)";


    // This code is only responsible for Resume Watch Feature
    public static final String RESUME_WATCH = "RESUME_WATCH";

    public static final String RESUME_WATCH_TABLE = "CREATE TABLE IF NOT EXISTS " + RESUME_WATCH
            + " (UniqueId TEXT,PlayedDuration TEXT,LatestMpdUrl TEXT,Flag TEXT,LicenceUrl TEXT)";




    private static final String CREATE_SOL_SUBTITLE_LUIMERE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_SUBTITLE_LUIMERE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_UID + " VARCHAR, " +
            COLUMN_LANGUAGE + " VARCHAR, " +

            COLUMN_PATH + " VARCHAR)";



    private SQLiteDatabase database;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SOL_SUBTITLE_LUIMERE);
        db.execSQL(DOWNLOAD_CONTENT_INFO_TABLE);
        db.execSQL(WATCH_ACCESS_INFO_TABLE);
        db.execSQL(RESUME_WATCH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SUBTITLE_LUIMERE);
        db.execSQL("DROP TABLE IF EXISTS " + WATCH_ACCESS_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + RESUME_WATCH);
        db.execSQL("DROP TABLE IF EXISTS " + DOWNLOAD_CONTENT_INFO);
        onCreate(db);
    }


    public long insertRecordSubtittel(SubtitleModel contact) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_UID, contact.getUID());
        contentValues.put(COLUMN_LANGUAGE, contact.getLanguage());
        contentValues.put(COLUMN_PATH, contact.getPath());
        long status = database.insert(TABLE_NAME_SUBTITLE_LUIMERE, null, contentValues);
        database.close();
        return status;
    }

}
