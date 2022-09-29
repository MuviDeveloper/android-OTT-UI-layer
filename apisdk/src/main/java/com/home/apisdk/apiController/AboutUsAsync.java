/**
 * SDK initialization, platform and device information classes.
 */

package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.AboutUsInput;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * This Class gives a short note about the company/organisation to its users.
 *
 * @author MUVI
 */

public class AboutUsAsync extends AsyncTask<AboutUsInput, Void, Void> {


    private AboutUsInput aboutUsInput;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private String about;
    private String responseStr;
    private AboutUsListener listener;
    private Context context;
    private int code;

    /**
     * Interface used to allow the caller of a AboutUsAsync to run some code when get
     * responses.
     */
    public interface AboutUsListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */
        void onAboutUsPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param about Holds content of "About US"
         */
        void onAboutUsPostExecuteCompleted(int status, String about);
    }


    /**
     * Constructor to initialise the private data members.
     *
     * @param contactUsInputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                            For Example: to use this API we have to set following attributes:
     *                            setAuthToken() ,setPermalink() etc.
     * @param listener            AboutUs Listener
     * @param context             android.content.Context
     */
    public AboutUsAsync(AboutUsInput contactUsInputModel, AboutUsListener listener, Context context) {
        this.listener = listener;
        this.context = context;

        this.aboutUsInput = contactUsInputModel;
        PACKAGE_NAME = context.getPackageName();
    }

    /**
     * Background thread to execute.
     *
     * @return Null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(AboutUsInput... params) {

        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

            parameters.put(HeaderConstants.AUTH_TOKEN, this.aboutUsInput.getAuthToken());
            parameters.put(HeaderConstants.PERMALINK, this.aboutUsInput.getPermalink());
            parameters.put(HeaderConstants.LANG_CODE, this.aboutUsInput.getLang_code());
            parameters.put(HeaderConstants.COUNTRY, this.aboutUsInput.getCountryCode());

            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getAboutUs());

            }catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject myJson = null;
            if (responseStr != null) {
                try {
                    myJson = new JSONObject(responseStr);
                    code=Integer.parseInt(myJson.optString("code"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (code == 200) {
                try {
                    JSONObject jsonMainNode = myJson.getJSONObject("page_details");
                    about = jsonMainNode.optString("content");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {


        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onAboutUsPreExecuteStarted();
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onAboutUsPostExecuteCompleted(code,about);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onAboutUsPostExecuteCompleted(code,about);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onAboutUsPostExecuteCompleted(code,about);
    }
}