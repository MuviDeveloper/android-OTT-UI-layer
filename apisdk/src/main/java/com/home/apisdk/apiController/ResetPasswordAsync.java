/**
 * SDK initialization, platform and device information classes.
 */

package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.ResetPasswordInputModel;
import com.home.apisdk.apiModel.ResetPasswordOutputModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * This Class helps user to add their favorite series/Movie to their favorite list section. Users
 * can watch their favorite series/movies without wasting much time in searching them.
 *
 * @author MUVI
 */

public class ResetPasswordAsync extends AsyncTask<Void, Void, Void> {

    private ResetPasswordListener listener;
    private ResetPasswordInputModel resetPasswordInputModel;
    private String PACKAGE_NAME;
    private String responseStr;
    private String sucessMsg;
    private int status=0;
    private Context context;
    private String message="";

    /**
     * Interface used to allow the caller of a AddToFollowAsync to run some code when get
     * responses.
     */

    public interface ResetPasswordListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onResetPasswordPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param addToFollowOutputModel A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status              Response Code from the server
         * @param sucessMsg           On Success Message
         */

        void onResetPasswordPostExecuteCompleted(ResetPasswordOutputModel addToFollowOutputModel, int status, String sucessMsg);
    }

    ResetPasswordOutputModel AddToFollowOutputModel = new ResetPasswordOutputModel();

    /**
     * Constructor to initialise the private data members.
     *
     * @param resetPasswordinputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                           For Example: to use this API we have to set following attributes:
     *                           setAuthToken(),setMovie_uniq_id() etc.
     * @param listener           AddToFavorite Listener
     * @param context            android.content.Context
     */
    public ResetPasswordAsync(ResetPasswordInputModel resetPasswordinputModel, ResetPasswordListener listener,Context context) {

        this.listener = listener;
        this.context = context;
        this.resetPasswordInputModel = resetPasswordinputModel;
        PACKAGE_NAME = context.getPackageName();
    }


    /**
     * Background thread to execute.
     * @return Null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(Void... params) {

        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

            parameters.put(HeaderConstants.AUTH_TOKEN, this.resetPasswordInputModel.getAuthToken());
            parameters.put(HeaderConstants.EMAIL, this.resetPasswordInputModel.getEmail());
            parameters.put(HeaderConstants.PASSWORD, this.resetPasswordInputModel.getPassword());
            parameters.put(HeaderConstants.COUNTRY, this.resetPasswordInputModel.getCountry());
            parameters.put(HeaderConstants.LANG_CODE, this.resetPasswordInputModel.getLang_code());

            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getResetPassword());

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
                status = Integer.parseInt(myJson.optString("code"));
                sucessMsg = myJson.optString("msg");

                /*if (status == 200)
                if(this.isCacheEnabled){
                  this.apiController.insertCachedDataToDB(APIUrlConstant.CONTENT_DETAILS_URL,permaLinkStr, null, Utils.getCurrentTimeStamp());
                  this.apiController.insertCachedDataToDB(APIUrlConstant.GetCastDetails, permaLinkStr,null, Utils.getCurrentTimeStamp());
                }*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onResetPasswordPreExecuteStarted();
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onResetPasswordPostExecuteCompleted(AddToFollowOutputModel, status, sucessMsg);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onResetPasswordPostExecuteCompleted(AddToFollowOutputModel, status, sucessMsg);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onResetPasswordPostExecuteCompleted(AddToFollowOutputModel, status, sucessMsg);
    }
}
