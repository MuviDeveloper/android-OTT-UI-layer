/**
 * SDK initialization, platform and device information classes.
 */


package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.GetUserGroupInputModel;
import com.home.apisdk.apiModel.GetUserGroupOutputModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This Class allow user to successfully login using their email and password.
 *
 * @authstatusor MUVI
 */
public class GetUserGroupAsynTask extends AsyncTask<GetUserGroupInputModel, Void, Void> {

    private GetUserGroupInputModel userGroupInputModel;
    private String responseStr;
    private int code;
    private String message, status;
    private String PACKAGE_NAME;
    private GetUserGroupListener listener;
    private Context context;
    /**
     * Interface used to allow the caller of a LoginAsynTask to run some code when get
     * responses.
     */

    public interface GetUserGroupListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onGetUserGroupPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param responseStr A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param code       Response Code from the server
         * @param message      On Success Message
         */

        void onGetUserGroupPostExecuteCompleted(String responseStr, int code, String message);
    }

    ArrayList<GetUserGroupOutputModel> groups = new ArrayList<GetUserGroupOutputModel>();

    /**
     * Constructor to initialise the private data members.
     *
     * @param listener A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                    For Example: to use this API we have to set following attributes:
     *                    setAuthToken(),setPassword() etc.
     * @param listener    GetUserGroupListener
     * @param context     android.content.Context
     */

    public GetUserGroupAsynTask(GetUserGroupInputModel userGroupInputModel, GetUserGroupListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        this.userGroupInputModel = userGroupInputModel;
        PACKAGE_NAME = context.getPackageName();
    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(GetUserGroupInputModel... params) {


        try {
            /*
             * Add by PIYUSH on 20-Aug-2018
             * */
            //Setup parameters
            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put(HeaderConstants.AUTH_TOKEN, this.userGroupInputModel.getAuthToken());

             responseStr = Utils.requester.multiPartPostRequest(parameters, APIUrlConstant.getUserGroup());

            if (responseStr==null) {
                code = 0;
                message = "Error";
            }

            JSONObject mainJson = null;
            if (responseStr != null) {

                mainJson = new JSONObject(responseStr);
                code = Integer.parseInt(mainJson.optString("code"));
                status = mainJson.optString("status");
                message = mainJson.optString("msg");
                if (code > 0) {

                    if (code == 200) {

                        JSONArray jsonArray = mainJson.getJSONArray("data");
                        int lengthJsonArray = jsonArray.length();

                        for (int i = 0; i < lengthJsonArray; i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            GetUserGroupOutputModel groupOutputModel = new GetUserGroupOutputModel();
                            if ((jsonObject.has("id")) && jsonObject.optString("id").trim() != null && !jsonObject.optString("id").trim().isEmpty() && !jsonObject.optString("id").trim().equals("null") && !jsonObject.optString("id").trim().matches("")) {
                                groupOutputModel.setId(jsonObject.optString("id"));
                            }
                            if ((jsonObject.has("group_name")) && jsonObject.optString("group_name").trim() != null && !jsonObject.optString("group_name").trim().isEmpty() && !jsonObject.optString("group_name").trim().equals("null") && !jsonObject.optString("group_name").trim().matches("")) {
                                groupOutputModel.setGroup_name(jsonObject.optString("group_name"));
                            }
                            if ((jsonObject.has("user_group_type")) && jsonObject.optString("user_group_type").trim() != null && !jsonObject.optString("user_group_type").trim().isEmpty() && !jsonObject.optString("user_group_type").trim().equals("null") && !jsonObject.optString("user_group_type").trim().matches("")) {
                                groupOutputModel.setUser_group_type(jsonObject.optString("user_group_type"));
                            }

                            groups.add(groupOutputModel);

                        }

                        }
                }

            } else {
                responseStr = "0";
                code = 0;
                message = "Error";
            }
        } catch (final JSONException e1) {

            responseStr = "0";
            code = 0;
            message = "Error";
        } catch (Exception e) {

            responseStr = "0";
            code = 0;
            message = "Error";
        }
        return null;


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetUserGroupPreExecuteStarted();

        code = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGetUserGroupPostExecuteCompleted(responseStr, code, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGetUserGroupPostExecuteCompleted(responseStr, code, message);
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        listener.onGetUserGroupPostExecuteCompleted(responseStr, code, message);

    }

}
