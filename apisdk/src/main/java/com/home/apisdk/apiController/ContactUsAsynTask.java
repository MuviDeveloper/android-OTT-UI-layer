/**
 * SDK initialization, platform and device information classes.
 */


package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.ContactUsInputModel;
import com.home.apisdk.apiModel.ContactUsOutputModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * This Class provides users to contact the company if they have any feedback, suggestions or Issue.
 * They can share anything by contacting the company via Email.
 *
 * @author MUVI
 */

public class ContactUsAsynTask extends AsyncTask<ContactUsInputModel, Void, Void> {

    private ContactUsInputModel contactUsInputModel;
    private String PACKAGE_NAME;
    private String message ="";
    private String responseStr;
    private String status;
    private int code;
    private ContactUsOutputModel contactUsOutputModel;
    private ContactUsListener listener;
    private Context context;

    /**
     * Interface used to allow the caller of a ContactUsAsynTask to run some code when get
     * responses.
     */

    public interface ContactUsListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */


        void onContactUsPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param contactUsOutputModel A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param code                 Response Code From The Server
         * @param message              On Success Message
         * @param status               For Getting the status
         */

        void onContactUsPostExecuteCompleted(ContactUsOutputModel contactUsOutputModel, int code, String message, String status);
    }

    /**
     * Constructor to initialise the private data members.
     *
     * @param contactUsInputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                            For Example: to use this API we have to set following attributes:
     *                            setAuthToken(),setName() etc.
     * @param listener            ContactUs Listener
     * @param context             android.content.Context
     */

    public ContactUsAsynTask(ContactUsInputModel contactUsInputModel, ContactUsListener listener, Context context) {
        this.listener = listener;
        this.context = context;

        this.contactUsInputModel = contactUsInputModel;
        PACKAGE_NAME = context.getPackageName();
    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(ContactUsInputModel... params) {

        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
           parameters.put(HeaderConstants.AUTH_TOKEN, this.contactUsInputModel.getAuthToken());
           parameters.put(HeaderConstants.EMAIL, this.contactUsInputModel.getEmail());
           parameters.put(HeaderConstants.NAME, this.contactUsInputModel.getName());
           parameters.put(HeaderConstants.MESSAGE, this.contactUsInputModel.getMessage());
           parameters.put(HeaderConstants.LANG_CODE, this.contactUsInputModel.getLang_code());
           parameters.put(HeaderConstants.COUNTRY, this.contactUsInputModel.getCountryCode());

            // Execute HTTP Post Request
            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getContactUsUrl());

            }catch (Exception e) {
                code = 0;
                message = "";
                status = "";
                e.printStackTrace();
            }
            JSONObject myJson = null;
            if (responseStr != null) {
                myJson = new JSONObject(responseStr);
                code = Integer.parseInt(myJson.optString("code"));
                message = myJson.optString("msg");
                status = myJson.optString("status");
            }
            if (code == 200) {
                contactUsOutputModel = new ContactUsOutputModel();
                contactUsOutputModel.setSuccess_msg(myJson.optString("success_msg"));
                contactUsOutputModel.setError_msg(myJson.optString("error_msg"));
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
        listener.onContactUsPreExecuteStarted();
        code = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onContactUsPostExecuteCompleted(contactUsOutputModel, code, message, status);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onContactUsPostExecuteCompleted(contactUsOutputModel, code, message, status);
        }


    }

    @Override
    protected void onPostExecute(Void result) {
        listener.onContactUsPostExecuteCompleted(contactUsOutputModel, code, message, status);
    }
}
