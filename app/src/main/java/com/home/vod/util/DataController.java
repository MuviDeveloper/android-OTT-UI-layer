package com.home.vod.util;

import android.content.Context;

import com.home.apisdk.APIController;
import com.home.apisdk.Utils;
import com.home.vod.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Subhadarshani on 16-Aug-18.
 */

public class DataController extends APIController {
    private DatabaseHelper databaseHelper;
    FeatureHandler featureHandler;
    public DataController(Context context){
        databaseHelper = new DatabaseHelper(context);
        featureHandler = FeatureHandler.getFeaturePreference(context);
    }


    /*
       * Description First Check for API Caching is enabled or not. If enabled then it will fetch data from local
       * databse.If cached data is null then it will fetch api and store response in local database.
       * If data is not null it will check for time interval.If time difference is greater than 24 then it will fetch api
       * and store data in db and if not then it will fetch data from local databse.
       * */
    @Override
    public String getAPIData(String apiName, String permalink, boolean isCachingEnabled) {
        String response = null;

        String lastFetchedTimeStamp = databaseHelper.getLastFetchedAPITime(apiName,permalink);
        if(isCachingEnabled){
            if(databaseHelper.getDataFromDB(apiName,permalink) == null){
                response = null;
            }else if (getTimeDifference(lastFetchedTimeStamp)> Constant.DEFAULT_TIME_CACHE_DIFFERENECE){
                //server timestamp is within 5 minutes of current system time 86400000
                response = null;

            }else{
                response = databaseHelper.getDataFromDB(apiName,permalink);
            }
        }

        return response;
    }

    @Override
    public String getAPICacheData(String apiName, String permalink) {
        return databaseHelper.getDataFromDB(apiName,permalink);
    }

    @Override
    public void insertCachedDataToDB(String apiName, String permalink, String response, String time) {
        databaseHelper.insertData(apiName,permalink,response,time);
    }

    @Override
    public void deleteAPICacheData() {
    databaseHelper.deleteData();
    }


    public long getTimeDifference(String lastFetchedTime){
        long diff = 0;
        if(lastFetchedTime !=null){

            String lastFetchedTimeStamp = lastFetchedTime;
            String currentTimeStamp = Utils.getCurrentTimeStamp();

            //HH converts hour in 24 hours format (0-23), day calculation
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            Date d1 = null;
            Date d2 = null;

            try {
                d1 = format.parse(lastFetchedTimeStamp);
                d2 = format.parse(currentTimeStamp);

                //in milliseconds
                 diff = d2.getTime() - d1.getTime();

               // diffHours = diff / (60 * 60 * 1000) % 24;

                System.out.print(diff + " hours, ");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            diff = 0;
        }

        return diff;
    }
}
