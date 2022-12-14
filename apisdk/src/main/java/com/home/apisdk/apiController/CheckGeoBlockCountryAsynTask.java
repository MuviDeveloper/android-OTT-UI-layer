/**
 * SDK initialization, platform and device information classes.
 */


package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.CheckGeoBlockInputModel;
import com.home.apisdk.apiModel.CheckGeoBlockOutputModel;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * This Class is use to find out the user's current country from where he/she is logging in.
 *
 * @author MUVI
 */
public class CheckGeoBlockCountryAsynTask extends AsyncTask<CheckGeoBlockInputModel, Void, Void> {

    private CheckGeoBlockInputModel checkGeoBlockInputModel;
    private String responseStr;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private String countryCode,state,city;
    private CheckGeoBlockForCountryListener listener;
    private Context context;

    /**
     * Interface used to allow the caller of a CheckGeoBlockCountryAsynTask to run some code when get
     * responses.
     */

    public interface CheckGeoBlockForCountryListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */


        void onCheckGeoBlockCountryPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param checkGeoBlockOutputModel A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status                   Response Code From The Server
         * @param message                  On Success Message
         */

        void onCheckGeoBlockCountryPostExecuteCompleted(CheckGeoBlockOutputModel checkGeoBlockOutputModel, int status, String message);
    }


    CheckGeoBlockOutputModel checkGeoBlockOutputModel = new CheckGeoBlockOutputModel();

    /**
     * Constructor to initialise the private data members.
     *
     * @param checkGeoBlockInputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                                For Example: to use this API we have to set following attributes:
     *                                setAuthToken(),setIp() etc.
     * @param listener                CheckGeoBlockForCountry Listener
     * @param context                 android.content.Context
     */

    public CheckGeoBlockCountryAsynTask(CheckGeoBlockInputModel checkGeoBlockInputModel, CheckGeoBlockForCountryListener listener, Context context) {
        this.listener = listener;
        this.context = context;

        this.checkGeoBlockInputModel = checkGeoBlockInputModel;
        PACKAGE_NAME = context.getPackageName();
    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(CheckGeoBlockInputModel... params) {

        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

            parameters.put(HeaderConstants.AUTH_TOKEN, this.checkGeoBlockInputModel.getAuthToken());
            parameters.put(HeaderConstants.IP, this.checkGeoBlockInputModel.getIp());

            // Execute HTTP Post Request
            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getCheckGeoBlockUrl());

            } catch (Exception e) {
                status = 0;
                countryCode = "";
                message = "";
            }

            JSONObject myJson = null;
            if (responseStr != null) {
                Object json = new JSONTokener(responseStr).nextValue();
                if (json instanceof JSONObject) {
                    String statusStr = ((JSONObject) json).optString("code");
                    status = Integer.parseInt(statusStr);
                    if (status == 200) {
                        countryCode = ((JSONObject) json).optString("country");
                        state = ((JSONObject) json).optString("state");
                        city = ((JSONObject) json).optString("city");

                        checkGeoBlockOutputModel.setCountrycode(countryCode);
                        checkGeoBlockOutputModel.setState(state);
                        checkGeoBlockOutputModel.setCity(city);

                    }
                }
            }


        }catch (Exception e) {
            status = 0;
            message = "";
            countryCode = "";
            state = "";
            city = "";
        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onCheckGeoBlockCountryPreExecuteStarted();
        responseStr = "0";
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onCheckGeoBlockCountryPostExecuteCompleted(checkGeoBlockOutputModel, status, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onCheckGeoBlockCountryPostExecuteCompleted(checkGeoBlockOutputModel, status, message);
        }

    }


    @Override
    protected void onPostExecute(Void result) {
        listener.onCheckGeoBlockCountryPostExecuteCompleted(checkGeoBlockOutputModel, status, message);

    }
}
