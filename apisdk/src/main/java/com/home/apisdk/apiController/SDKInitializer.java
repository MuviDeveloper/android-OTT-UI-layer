package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by muvi on 25/7/17.
 */

public class SDKInitializer {

    private static SDKInitializer _instance;
    public static String hashKey = "";
    private SDKInitializerListner sdkInitializerListner;
    private Context context;
    private String authToken;
    private String message, responseStr;
    private int status;
    private static SDKInitializerPreference sdkInitializerPreference;


    public static String getHashKey(Context context) {
        sdkInitializerPreference = SDKInitializerPreference.getSdkInitializerPreference(context);
        return sdkInitializerPreference.getHash_KeyFromPreference();
    }

//    public static void setHashKey(String hashKey) {
//        SDKInitializer.hashKey = hashKey;
//    }

    public static String getUser_Package_Name_At_Api(Context context) {
        sdkInitializerPreference = SDKInitializerPreference.getSdkInitializerPreference(context);
        //commondate();
        return sdkInitializerPreference.getPackage_nameFromPreference();
    }

    public static String getServerTime() {
        return sdkInitializerPreference.getTimeFromPreference();
    }

//    public static void setUser_Package_Name_At_Api(String user_Package_Name_At_Api) {
//        SDKInitializer.user_Package_Name_At_Api = user_Package_Name_At_Api;
//    }

    public static String user_Package_Name_At_Api = "";

    private SDKInitializer() {

    }

    public static SDKInitializer getInstance() {
        if (_instance == null) {
            return new SDKInitializer();
        }
        return _instance;
    }


    public void init(SDKInitializerListner sdkInitializerListner,
                     Context context,
                     String authToken, boolean byPassCall) {
        this.sdkInitializerListner = sdkInitializerListner;
        this.context = context;
        sdkInitializerPreference = SDKInitializerPreference.getSdkInitializerPreference(context);
        this.authToken = authToken;
        new InitAsync().execute(byPassCall);
    }

    public void init(Context context,
                     String authToken) {
        this.context = context;
        this.authToken = authToken;


        new InitAsync().execute();
    }

    public static void commondate() {

        String loggedinDateStr = sdkInitializerPreference.getTimeFromPreference();

        if (!TextUtils.isEmpty(loggedinDateStr)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date loggedInDate = null;
            try {
                loggedInDate = formatter.parse(loggedinDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date today = new Date();
            long differenceInDays = (int)calculateDays(loggedInDate, today);
            if (differenceInDays >= 1) {

                sdkInitializerPreference.clearSDKPref();

            }
        }
    }


    public interface SDKInitializerListner {
        void onPreExexuteListner();

        void onPostExecuteListner(int status);
    }


    private class InitAsync extends AsyncTask<Boolean, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sdkInitializerListner.onPreExexuteListner();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            sdkInitializerListner.onPostExecuteListner(status);


        }

        @Override
        protected Void doInBackground(Boolean... params) {
            try {
                boolean byPassCall = false;
                if (params.length>0) {
                    byPassCall = params[0];
                }
                if (byPassCall) {
                    status = 200;
                    return null;
                }

                LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
                parameters.put(HeaderConstants.AUTH_TOKEN, authToken);
                parameters.put(HeaderConstants.PACKAGE_NAME, context.getPackageName());

                String x= context.getPackageName();


                // Execute HTTP Post Request
                try {
                    responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getInitializationUrl());



                }  catch (Exception e) {
                    status = 0;
                    message = "Error";
                }

                JSONObject myJson = null;
                if (responseStr != null) {
                    myJson = new JSONObject(responseStr);
                    status = Integer.parseInt(myJson.optString("code"));
                }

                if (status > 0) {

                    if (status == 200) {

//                        sdkInitializerPreference.clearSDKPref();

                        if ((myJson.has("pkgname")) && myJson.optString("pkgname").trim() != null && !myJson.optString("pkgname").trim().isEmpty() && !myJson.optString("pkgname").trim().equals("null") && !myJson.optString("pkgname").trim().matches("")) {
                            String PNAME = myJson.optString("pkgname");
//                            sdkInitializerPreference.setPackage_namePref(PNAME);
//                            Log.v("MUVI", "pkgname at class=" + (myJson.optString("pkgname")));
                        }


                        if ((myJson.has("hashkey")) && myJson.optString("hashkey").trim() != null && !myJson.optString("hashkey").trim().isEmpty() && !myJson.optString("hashkey").trim().equals("null") && !myJson.optString("hashkey").trim().matches("")) {
                            String hashKey = (myJson.optString("hashkey"));
//                            sdkInitializerPreference.setHash_KeyPref(hashKey);
//                            Log.v("MUVI", "Hash Key at class=" + (myJson.optString("hashkey")));
                        }

                        if ((myJson.has("server_time")) && myJson.optString("server_time").trim() != null && !myJson.optString("server_time").trim().isEmpty() && !myJson.optString("server_time").trim().equals("null") && !myJson.optString("server_time").trim().matches("")) {
                            String time = (myJson.optString("server_time"));
                            sdkInitializerPreference.setTimePref(time);
                            Log.v("MUVI", "Time at class=" + (myJson.optString("server_time")));
                        }

                        Log.v("MUVI", "Package Name" + sdkInitializerPreference.getPackage_nameFromPreference());
                        Log.v("MUVI", "Server Time" + sdkInitializerPreference.getTimeFromPreference());
                        Log.v("MUVI", "Hash Key" + sdkInitializerPreference.getHash_KeyFromPreference());
                    }
                } else {

                    responseStr = "0";
                    status = 0;
                    message = "Error";
                }
            } catch (Exception e) {

                responseStr = "0";
                status = 0;
                message = "Error";
            }
            return null;
        }
    }

    public static long calculateDays(Date dateEarly, Date dateLater) {
        return (dateLater.getTime() - dateEarly.getTime()) / (24 * 60 * 60 * 1000);
    }

    public static void setData(Context ctx){
        sdkInitializerPreference = SDKInitializerPreference.getSdkInitializerPreference(ctx);
        sdkInitializerPreference.setPackage_namePref(ctx.getPackageName());
        sdkInitializerPreference.setHash_KeyPref("NN");
    }

}


