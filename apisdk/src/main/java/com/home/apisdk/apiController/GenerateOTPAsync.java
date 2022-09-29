package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.GenerateOTPInputModel;
import com.home.apisdk.apiModel.GenerateOTPOutputModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;

public class GenerateOTPAsync extends AsyncTask<GenerateOTPInputModel, Void, Void> {

    private GenerateOTPInputModel generateOTPInputModel;
    private String responseStr;
    private int code;
    private String message;
    private String status;
    private String otp;
    private int msgid;
    private String PACKAGE_NAME;
    private GenerateOTPAsync.GenerateOtpListener listener;
    private Context context;

    /**
     * Interface used to allow the caller of a IsRegistrationEnabledAsynTask to run some code when get
     * responses.
     */

    public interface GenerateOtpListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onGenerateOtpPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param generateOTPOutputModel A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status                 Response Code From The Server
         * @param message                On Success Message
         */

        void onGenerateOTPPostExecuteCompleted(GenerateOTPOutputModel generateOTPOutputModel, String status, String message, String response,int code,String otp);
    }


    GenerateOTPOutputModel generateOTPOutputModel = new GenerateOTPOutputModel();

    /**
     * Constructor to initialise the private data members.
     *
     * @param generateOTPInputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                              For Example: to use this API we have to set following attributes:
     *                              setAuthToken() etc.
     * @param listener              IsRegistrationenabled Listener
     * @param context               android.content.Context
     */

    public GenerateOTPAsync(GenerateOTPInputModel generateOTPInputModel, GenerateOTPAsync.GenerateOtpListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        this.generateOTPInputModel = generateOTPInputModel;
        PACKAGE_NAME = context.getPackageName();
    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(GenerateOTPInputModel... params) {

        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put(HeaderConstants.AUTH_TOKEN, this.generateOTPInputModel.getAuthToken());
            parameters.put(HeaderConstants.EMAIL, this.generateOTPInputModel.getEmail());
            parameters.put(HeaderConstants.MOBILE_NO, this.generateOTPInputModel.getMobile_number());
            parameters.put(HeaderConstants.LANG_CODE, this.generateOTPInputModel.getLang_code());
            parameters.put(HeaderConstants.MOBILE_COUNTRY_CODE, this.generateOTPInputModel.getMobile_country_code());


            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getGenerateOtpUrl());
                JSONObject myJson = null;
                if (responseStr != null) {
                    myJson = new JSONObject(responseStr);

                    code = Integer.parseInt(myJson.optString("code"));
                    message = myJson.optString("msg");
                    status = myJson.optString("status");
                    otp =  myJson.optString("otp");

                }

                if (code == 200) {
                    try {
                        if ((myJson.has("msgid")) && myJson.optString("msgid").trim() != null && !myJson.optString("msgid").trim().isEmpty() && !myJson.optString("msgid").trim().equals("null") && !myJson.optString("msgid").trim().matches("")) {
                            generateOTPOutputModel.setMsgid(String.valueOf(myJson.optString("msgid")));
                        } else {
                            generateOTPOutputModel.setMsgid("");
                        }
                    } catch (NumberFormatException e) {
                        generateOTPOutputModel.setMsgid("");
                        e.printStackTrace();
                    }

                    try {
                        if ((myJson.has("otp")) && myJson.optString("otp").trim() != null && !myJson.optString("otp").trim().isEmpty() && !myJson.optString("otp").trim().equals("null") && !myJson.optString("otp").trim().matches("")) {
//                            generateOTPOutputModel.setOtp(String.valueOf(myJson.optString("otp")));
                            generateOTPOutputModel.setOtp("");
                        } else {
                            generateOTPOutputModel.setOtp("");
                        }
                    } catch (NumberFormatException e) {
                        generateOTPOutputModel.setOtp("");
                        e.printStackTrace();
                    }


                }

            } catch (Exception e) {
                message = "Error";
            }

        } catch (Exception e) {
            responseStr = "0";
        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGenerateOtpPreExecuteStarted();

        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGenerateOTPPostExecuteCompleted(generateOTPOutputModel, status, message, responseStr,code,otp);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGenerateOTPPostExecuteCompleted(generateOTPOutputModel, status, message, responseStr,code,otp);
            return;
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        listener.onGenerateOTPPostExecuteCompleted(generateOTPOutputModel, status, message, responseStr,code,otp);
    }

}
