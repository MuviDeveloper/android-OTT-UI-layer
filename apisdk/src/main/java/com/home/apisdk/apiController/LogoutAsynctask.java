/**
 * SDK initialization, platform and device information classes.
 */


package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIController;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.LogoutInput;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * This Class helps user to successfully logout from the application whenever they want.
 *
 * @author MUVI
 */

public class LogoutAsynctask extends AsyncTask<LogoutInput, Void, Void> {

    private LogoutInput logoutInput;
    private String PACKAGE_NAME;
    private String message;
    private String responseStr;
    private String status;
    private int code;
    private LogoutListener listener;
    private Context context;
    private boolean isCachAEnabled;
    private APIController apiController;

    /**
     * Interface used to allow the caller of a LogoutAsynctask to run some code when get
     * responses.
     */

    public interface LogoutListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onLogoutPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param code    Response Code from the server
         * @param status  For Getting The Status
         * @param message On Success Message
         */

        void onLogoutPostExecuteCompleted(int code, String status, String message);

    }

    /**
     * Constructor to initialise the private data members.
     *
     * @param logoutInput A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                    For Example: to use this API we have to set following attributes:
     *                    setAuthToken(),setLogin_history_id() etc.
     * @param listener    LogoutListener
     * @param context     android.content.Context
     */

    public LogoutAsynctask(LogoutInput logoutInput, LogoutListener listener, Context context, boolean isCacheEnabled, APIController controller) {
        this.listener = listener;
        this.context = context;
        this.isCachAEnabled = isCacheEnabled;
        this.apiController = controller;

        this.logoutInput = logoutInput;
        PACKAGE_NAME = context.getPackageName();


    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(LogoutInput... params) {

        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put(HeaderConstants.AUTH_TOKEN, this.logoutInput.getAuthToken());
            parameters.put(HeaderConstants.LOGIN_HISTORY_ID, this.logoutInput.getLogin_history_id());
            parameters.put(HeaderConstants.LANG_CODE, this.logoutInput.getLang_code());
            parameters.put(HeaderConstants.COUNTRY, this.logoutInput.getCountryCode());


            // Execute HTTP Post Request
            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getLogoutUrl());

                Log.v("LOGINACYN", "==========" + responseStr);

            } catch (Exception e) {
                code = 0;
                message = "";
                status = "";
            }
            JSONObject myJson = null;
            if (responseStr != null) {
                myJson = new JSONObject(responseStr);
                code = Integer.parseInt(myJson.optString("code"));
                message = myJson.optString("msg");
                status = myJson.optString("status");
                /*try{
                    this.apiController.deleteAPICacheData();
                }catch (Exception e){}*/
                /*if(this.isCachAEnabled){
                    this.apiController.insertCachedDataToDB(APIUrlConstant.WATCH_HISTORY,"",null, Utils.getCurrentTimeStamp());
                    this.apiController.insertCachedDataToDB(APIUrlConstant.ViewFavorite,"",null, Utils.getCurrentTimeStamp());
                }*/
            }


        } catch (Exception e) {
            code = 0;
            message = "";
            status = "";
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onLogoutPreExecuteStarted();
        code = 0;
        status = "";
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onLogoutPostExecuteCompleted(code, status, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onLogoutPostExecuteCompleted(code, status, message);
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        listener.onLogoutPostExecuteCompleted(code, status, message);
    }
}
