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
import com.home.apisdk.apiModel.Get_UserProfile_Input;
import com.home.apisdk.apiModel.Get_UserProfile_Output;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * This Class shows the profile detail of the user.
 *
 * @author MUVI
 */

public class GetUserProfileAsynctask extends AsyncTask<Get_UserProfile_Input, Void, Void> {

    private Get_UserProfile_Input get_userProfile_input;
    private String PACKAGE_NAME;
    private String message;
    private String responseStr;
    private String status;
    private int code;
    private Get_UserProfile_Output get_userProfile_output;
    private Get_UserProfileListener listener;
    private Context context;
    private boolean isCacheEnabled;
    private APIController apiController;

    /**
     * Interface used to allow the caller of a GetUserProfileAsynctask to run some code when get
     * responses.
     */

    public interface Get_UserProfileListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onGet_UserProfilePreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param get_userProfile_output A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param code                   Response Code From The Server
         * @param message                On Success Message
         * @param status                 For Getting The Status
         */

        void onGet_UserProfilePostExecuteCompleted(Get_UserProfile_Output get_userProfile_output, int code, String message, String status);
    }

    /**
     * Constructor to initialise the private data members.
     *
     * @param get_userProfile_input A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                              For Example: to use this API we have to set following attributes:
     *                              setAuthToken(),setEmail() etc.
     * @param listener              Get_UserProfile Listener
     * @param context               android.content.Context
     */

    public GetUserProfileAsynctask(Get_UserProfile_Input get_userProfile_input, Get_UserProfileListener listener, Context context, boolean iscacheEnabled, APIController apiController) {
        this.listener = listener;
        this.context = context;
        this.isCacheEnabled = iscacheEnabled;
        this.apiController = apiController;

        this.get_userProfile_input = get_userProfile_input;
        PACKAGE_NAME = context.getPackageName();


    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(Get_UserProfile_Input... params) {
        JSONObject myJson = null;
        if (this.apiController.getAPIData(APIUrlConstant.GET_PROFILE_DETAILS_URL, "", isCacheEnabled) == null) {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

            parameters.put(HeaderConstants.AUTH_TOKEN, this.get_userProfile_input.getAuthToken());
            parameters.put(HeaderConstants.EMAIL, this.get_userProfile_input.getEmail());
            parameters.put(HeaderConstants.MOBILE_NO, this.get_userProfile_input.getPhone());
            parameters.put(HeaderConstants.USER_ID, this.get_userProfile_input.getUser_id());
            parameters.put(HeaderConstants.LANG_CODE, this.get_userProfile_input.getLang_code());
            parameters.put(HeaderConstants.COUNTRY, this.get_userProfile_input.getCountryCode());

            // Execute HTTP Post Request
            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getGetProfileDetailsUrl());

                Log.v("MUVISDK", "RES" + responseStr);
                if (responseStr != null) {
                    myJson = new JSONObject(responseStr);
                    code = Integer.parseInt(myJson.optString("code"));
                    message = myJson.optString("msg");
                    if(code ==200){
                        this.apiController.insertCachedDataToDB(APIUrlConstant.GET_PROFILE_DETAILS_URL,"", responseStr, Utils.getCurrentTimeStamp());
                    }
                }

            } catch (Exception e) {
                code = 0;
                message = "";
                status = "";
            }
        } else {
            responseStr = this.apiController.getAPICacheData(APIUrlConstant.GET_PROFILE_DETAILS_URL,"");
            if (responseStr != null) {
                try {
                    myJson = new JSONObject(responseStr);
                    code = Integer.parseInt(myJson.optString("code"));
                    message = myJson.optString("msg");
                } catch (JSONException e) {
                    e.printStackTrace();
                    code = 0;
                    message = "";
                    status = "";
                }

            }
        }
        try {


            if (code == 200) {
                try {

                    get_userProfile_output = new Get_UserProfile_Output();
                    get_userProfile_output.setId(myJson.optString("id"));
                    get_userProfile_output.setEmail(myJson.optString("email"));
                    get_userProfile_output.setDisplay_name(myJson.optString("display_name"));
                    get_userProfile_output.setStudio_id(myJson.optString("studio_id"));
                    get_userProfile_output.setProfile_image(myJson.optString("original_profile_image"));
                    get_userProfile_output.setCustom_last_name(myJson.optString("custom_last_name"));
                    get_userProfile_output.setIsSubscribed(myJson.optString("isSubscribed"));


                    if ((myJson.has("kids_mode_status")) && myJson.optString("kids_mode_status").trim() != null && !myJson.optString("kids_mode_status").trim().isEmpty() && !myJson.optString("kids_mode_status").trim().equals("null") && !myJson.optString("kids_mode_status").trim().matches("")) {
                        get_userProfile_output.setKids_mode_status(myJson.optString("kids_mode_status"));
                    } else {
                        get_userProfile_output.setKids_mode_status("");
                    }

                    if ((myJson.has("mobile_number")) && myJson.optString("mobile_number").trim() != null && !myJson.optString("mobile_number").trim().isEmpty() && !myJson.optString("mobile_number").trim().equals("null") && !myJson.optString("mobile_number").trim().matches("")) {
                        get_userProfile_output.setPhone(myJson.optString("mobile_number"));
                    } else {
                        get_userProfile_output.setPhone("");
                    }

                    if ((myJson.has("mobile_country_code")) && myJson.optString("mobile_country_code").trim() != null && !myJson.optString("mobile_country_code").trim().isEmpty() && !myJson.optString("mobile_country_code").trim().equals("null") && !myJson.optString("mobile_country_code").trim().matches("")) {
                        get_userProfile_output.setMobile_country_code(myJson.optString("mobile_country_code"));
                    } else {
                        get_userProfile_output.setMobile_country_code("");
                    }

                    if ((myJson.has("isFree")) && myJson.optString("isFree").trim() != null && !myJson.optString("isFree").trim().isEmpty() && !myJson.optString("isFree").trim().equals("null") && !myJson.optString("isFree").trim().matches("")) {
                        get_userProfile_output.setIsFree(myJson.optString("isFree"));
                    } else {
                        get_userProfile_output.setIsFree("");
                    }

                    if (myJson.has("custom_languages")) {
                        get_userProfile_output.setCustom_languages("custom_languages");
                    }
                    if (myJson.has("custom_country")) {
                        get_userProfile_output.setCustom_country("custom_country");
                    }

                    if ((myJson.has("user_group_type")) && myJson.optString("user_group_type").trim() != null && !myJson.optString("user_group_type").trim().isEmpty() && !myJson.optString("user_group_type").trim().equals("null") && !myJson.optString("user_group_type").trim().matches("")) {
                        get_userProfile_output.setGroup_type(myJson.optString("user_group_type"));
                    } else {
                        get_userProfile_output.setGroup_type("");
                    }

                } catch (Exception e) {
                    code = 0;
                    message = "";
                    status = "";
                }

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
        listener.onGet_UserProfilePreExecuteStarted();
        code = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGet_UserProfilePostExecuteCompleted(get_userProfile_output, code, message, status);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGet_UserProfilePostExecuteCompleted(get_userProfile_output, code, message, status);
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        listener.onGet_UserProfilePostExecuteCompleted(get_userProfile_output, code, message, status);
    }
}
