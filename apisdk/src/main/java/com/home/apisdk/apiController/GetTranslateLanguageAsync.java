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
import com.home.apisdk.apiModel.LanguageListInputModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * This Class Translate the language according to the user's choice.
 *
 * @author MUVI
 */

public class GetTranslateLanguageAsync extends AsyncTask<Void, Void, String> {

    private GetTranslateLanguageInfoListener listener;
    private Context context;
    private LanguageListInputModel languageListInputModel;
    private String PACKAGE_NAME;
    private String message = "";
    private String responseStr;
    private int code;
    private String resultJsonString = "";
    private boolean isCacheEnabled;
    private APIController apiController;

    /**
     * Interface used to allow the caller of a GetTranslateLanguageAsync to run some code when get
     * responses.
     */

    public interface GetTranslateLanguageInfoListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onGetTranslateLanguagePreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param jsonResponse For Getting The Result Of JSON Response
         * @param status       Response Code From The Server
         */

        void onGetTranslateLanguagePostExecuteCompleted(String jsonResponse, int status);
    }

    /**
     * Constructor to initialise the private data members.
     *
     * @param languageListInputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                               For Example: to use this API we have to set following attributes:
     *                               setAuthToken(),setLangCode() etc.
     * @param listener               GetTranslateLanguageInfo Listener
     * @param context                android.content.Context
     */

    public GetTranslateLanguageAsync(LanguageListInputModel languageListInputModel,
                                     GetTranslateLanguageInfoListener listener, Context context, boolean isCacheEnabled, APIController apiController) {
        this.listener = listener;
        this.context = context;
        this.languageListInputModel = languageListInputModel;
        this.isCacheEnabled = isCacheEnabled;
        this.apiController = apiController;
        PACKAGE_NAME = context.getPackageName();
        Log.v("MUVISDK", "pkgnm :" + PACKAGE_NAME);
        Log.v("MUVISDK", "register user payment");
    }

    /**
     * Background thread to execute.
     *
     * @return resultJsonString
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected String doInBackground(Void... params) {

        String urlRouteList = APIUrlConstant.getLanguageTranslation();
        if (this.apiController.getAPIData(APIUrlConstant.LanguageTranslation, languageListInputModel.getLangCode(), isCacheEnabled) == null) {
            try{

                LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

                parameters.put(HeaderConstants.AUTH_TOKEN, languageListInputModel.getAuthToken());
                parameters.put(HeaderConstants.LANG_CODE, languageListInputModel.getLangCode());
                parameters.put(HeaderConstants.COUNTRY, languageListInputModel.getCountryCode());



                // Execute HTTP Post Request
                try {
                    responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getLanguageTranslation());

                    if(responseStr!=null){
                        this.apiController.insertCachedDataToDB(APIUrlConstant.LanguageTranslation,languageListInputModel.getLangCode(), responseStr, Utils.getCurrentTimeStamp());

                /*if(this.isCachAEnabled){
                    this.apiController.insertCachedDataToDB(APIUrlConstant.WATCH_HISTORY,"",null, Utils.getCurrentTimeStamp());
                    this.apiController.insertCachedDataToDB(APIUrlConstant.ViewFavorite,"",null, Utils.getCurrentTimeStamp());
                }*/
                    }
                } catch (Exception e) {
                }
            }catch(Exception e){

            }
        }else{
            responseStr = this.apiController.getAPICacheData(APIUrlConstant.LanguageTranslation,languageListInputModel.getLangCode());
        }
        try {

            if (responseStr != null) {
                JSONObject parent_json = new JSONObject(responseStr);
                JSONObject resultJson = parent_json.getJSONObject("translation");
                resultJsonString = resultJson.toString();
                try {
                    code = Integer.parseInt(parent_json.optString("code"));

                } catch (Exception e) {
                    code = 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJsonString;
    }

    @Override
    protected void onPostExecute(String resultJsonString) {
        listener.onGetTranslateLanguagePostExecuteCompleted(resultJsonString, code);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetTranslateLanguagePreExecuteStarted();
        code = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGetTranslateLanguagePostExecuteCompleted(resultJsonString, code);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGetTranslateLanguagePostExecuteCompleted(resultJsonString, code);
        }
    }
}
