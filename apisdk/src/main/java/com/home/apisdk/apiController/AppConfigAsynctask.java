package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;

import com.home.apisdk.APIController;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.AppConfigInputModel;
import com.home.apisdk.apiModel.AppConfigOutputModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;


public class AppConfigAsynctask extends AsyncTask<AppConfigInputModel,Void,Void> {

    private APIController apiController;
    private AppConfigListener listener;
    private Context context;
    private AppConfigInputModel app_ConfigInputModel ;
    private AppConfigOutputModel app_ConfigOutputModel ;
    int code;
    private String message="";
    private String status="";
    String force_update_version;
    private String responseStr;

    /**
     * Interface used to allow the caller of a AppConfigAsynctask to run some code when get
     * responses.
     */

    public interface AppConfigListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onAppConfigPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param appConfigOutputModel   A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param code                   Response Code From The Server
         * @param status                 Response Status : Ok/Failure
         * @param message                On Success Message
         * @param force_update_version   version number
         */

        void onAppConfigPostExecuteCompleted(AppConfigOutputModel appConfigOutputModel, int code,String status, String message, String force_update_version);
    }

    /**
     * Constructor to initialise the private data members.
     *
     * @param appConfigInputModel   A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                              For Example: to use this API we have to set following attributes:
     *                              setAuthToken(),setEmail() etc.
     * @param listener              Get_UserProfile Listener
     * @param context               android.content.Context
     */

    public AppConfigAsynctask(AppConfigInputModel appConfigInputModel, AppConfigListener listener, Context context, APIController apiController) {
        this.listener = listener;
        this.context = context;
        this.apiController = apiController;
        this.app_ConfigInputModel = appConfigInputModel;
    }

    /**
     * Background thread to execute.
     *
     * @return Null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(AppConfigInputModel... appConfigInputModels) {

        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put(HeaderConstants.AUTH_TOKEN, this.app_ConfigInputModel.getAuthToken());
            parameters.put(HeaderConstants.LANG_CODE, this.app_ConfigInputModel.getLanguageCode());
            parameters.put(HeaderConstants.DEVICE_TYPE, this.app_ConfigInputModel.getDeviceType());

            try {
                responseStr = Utils.requester.multiPartPostRequest(parameters, APIUrlConstant.getAppConfigUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject myJson = null;
        if (responseStr != null) {
            try {
                myJson = new JSONObject(responseStr);
                app_ConfigOutputModel = new AppConfigOutputModel();

                code = Integer.parseInt(myJson.optString("code"));
                status = myJson.optString("status");
                message = myJson.optString("msg");
                force_update_version = myJson.optString("force_update_version");

                if (code == 200) {
                    app_ConfigOutputModel.setCode(myJson.optString("code"));
                    app_ConfigOutputModel.setStatus(myJson.optString("status"));
                    app_ConfigOutputModel.setMsg(myJson.optString("msg"));

                    if ((myJson.has("force_update_version")) && myJson.optString("force_update_version").trim() != null && !myJson.optString("force_update_version").trim().isEmpty() && !myJson.optString("force_update_version").trim().equals("null") && !myJson.optString("force_update_version").trim().matches("")) {
                        app_ConfigOutputModel.setForce_update_version(myJson.optString("force_update_version"));
                    } else {
                        app_ConfigOutputModel.setForce_update_version("");
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        this.listener.onAppConfigPreExecuteStarted();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        this.listener.onAppConfigPostExecuteCompleted(app_ConfigOutputModel,code,status,message,force_update_version);
    }

}
