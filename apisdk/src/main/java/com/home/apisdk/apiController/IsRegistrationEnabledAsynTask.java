/**
 * SDK initialization, platform and device information classes.
 */


package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.IsRegistrationEnabledInputModel;
import com.home.apisdk.apiModel.IsRegistrationEnabledOutputModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * This Class checks whether the registration is enable or not.
 *
 * @author MUVI
 */
public class IsRegistrationEnabledAsynTask extends AsyncTask<IsRegistrationEnabledInputModel, Void, Void> {

    private IsRegistrationEnabledInputModel isRegistrationEnabledInputModel;
    private String responseStr;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private IsRegistrationenabledListener listener;
    private Context context;

    /**
     * Interface used to allow the caller of a IsRegistrationEnabledAsynTask to run some code when get
     * responses.
     */

    public interface IsRegistrationenabledListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onIsRegistrationenabledPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param isRegistrationEnabledOutputModel A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status                           Response Code From The Server
         * @param message                          On Success Message
         */

        void onIsRegistrationenabledPostExecuteCompleted(IsRegistrationEnabledOutputModel isRegistrationEnabledOutputModel, int status, String message, String response);
    }


    IsRegistrationEnabledOutputModel isRegistrationEnabledOutputModel = new IsRegistrationEnabledOutputModel();

    /**
     * Constructor to initialise the private data members.
     *
     * @param isRegistrationEnabledInputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                                        For Example: to use this API we have to set following attributes:
     *                                        setAuthToken() etc.
     * @param listener                        IsRegistrationenabled Listener
     * @param context                         android.content.Context
     */

    public IsRegistrationEnabledAsynTask(IsRegistrationEnabledInputModel isRegistrationEnabledInputModel, IsRegistrationenabledListener listener, Context context) {
        this.listener = listener;
        this.context = context;

        this.isRegistrationEnabledInputModel = isRegistrationEnabledInputModel;
        PACKAGE_NAME = context.getPackageName();
        Log.v("MUVISDK", "pkgnm :" + PACKAGE_NAME);
        Log.v("MUVISDK", "GetContentListAsynTask");


    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(IsRegistrationEnabledInputModel... params) {

        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put(HeaderConstants.AUTH_TOKEN, this.isRegistrationEnabledInputModel.getAuthToken());
            parameters.put(HeaderConstants.COUNTRY, this.isRegistrationEnabledInputModel.getCountryCode());
            parameters.put(HeaderConstants.LANG_CODE, this.isRegistrationEnabledInputModel.getLanguageCode());

            // Execute HTTP Post Request
            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getIsRegistrationenabledUrl());
               //Log.d("isregistrationEnabled",responseStr);

            } catch (Exception e) {
                status = 0;
                message = "Error";
            }

            JSONObject myJson = null;
            if (responseStr != null) {
                myJson = new JSONObject(responseStr);
                status = Integer.parseInt(myJson.optString("code"));
                message = myJson.optString("status");
            }


            if (status == 200) {

                /**
                 * @author Biswajit
                 * Check for mobile_no_required , Value depends on the backend confuguration.
                 */

                try {
                    if ((myJson.has("mobile_no_required")) && myJson.optString("mobile_no_required").trim() != null && !myJson.optString("mobile_no_required").trim().isEmpty() && !myJson.optString("mobile_no_required").trim().equals("null") && !myJson.optString("mobile_no_required").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setMobile_no_required(Integer.parseInt(myJson.optString("mobile_no_required")));
                    } else {
                        isRegistrationEnabledOutputModel.setMobile_no_required(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setMobile_no_required(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("sso_identity_provider"))
                            && myJson.optString("sso_identity_provider").trim() != null &&
                            !myJson.optString("sso_identity_provider").trim().isEmpty() &&
                            !myJson.optString("sso_identity_provider").trim().equals("null") &&
                            !myJson.optString("sso_identity_provider").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setSso_identity_provider(myJson.optString("sso_identity_provider"));
                    } else {
                        isRegistrationEnabledOutputModel.setSso_identity_provider("");
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setSso_identity_provider("");
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("register_through")) && myJson.optString("register_through").trim() != null && !myJson.optString("register_through").trim().isEmpty() && !myJson.optString("register_through").trim().equals("null") && !myJson.optString("register_through").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setRegister_through(Integer.parseInt(myJson.optString("register_through")));
                    } else {
                        isRegistrationEnabledOutputModel.setRegister_through(1);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setRegister_through(1);
                    e.printStackTrace();
                }


                try {
                    if ((myJson.has("is_otp_enabled")) && myJson.optString("is_otp_enabled").trim() != null && !myJson.optString("is_otp_enabled").trim().isEmpty() && !myJson.optString("is_otp_enabled").trim().equals("null") && !myJson.optString("is_otp_enabled").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setIs_otp_enabled(Integer.parseInt(myJson.optString("is_otp_enabled")));
                    } else {
                        isRegistrationEnabledOutputModel.setIs_otp_enabled(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setIs_otp_enabled(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("allow_add_email")) && myJson.optString("allow_add_email").trim() != null && !myJson.optString("allow_add_email").trim().isEmpty() && !myJson.optString("allow_add_email").trim().equals("null") && !myJson.optString("allow_add_email").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setAllow_add_email(Integer.parseInt(myJson.optString("allow_add_email")));
                    } else {
                        isRegistrationEnabledOutputModel.setAllow_add_email(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setAllow_add_email(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("otp_expiry_duration")) && myJson.optString("otp_expiry_duration").trim() != null && !myJson.optString("otp_expiry_duration").trim().isEmpty() && !myJson.optString("otp_expiry_duration").trim().equals("null") && !myJson.optString("otp_expiry_duration").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setOtp_expiry_duration(Integer.parseInt(myJson.optString("otp_expiry_duration")));
                    } else {
                        isRegistrationEnabledOutputModel.setOtp_expiry_duration(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setOtp_expiry_duration(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("allow_add_phone_no")) && myJson.optString("allow_add_phone_no").trim() != null && !myJson.optString("allow_add_phone_no").trim().isEmpty() && !myJson.optString("allow_add_phone_no").trim().equals("null") && !myJson.optString("allow_add_phone_no").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setAllow_add_phone_no(Integer.parseInt(myJson.optString("allow_add_phone_no")));
                    } else {
                        isRegistrationEnabledOutputModel.setAllow_add_phone_no(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setAllow_add_phone_no(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("is_login")) && myJson.optString("is_login").trim() != null && !myJson.optString("is_login").trim().isEmpty() && !myJson.optString("is_login").trim().equals("null") && !myJson.optString("is_login").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setIs_login(Integer.parseInt(myJson.optString("is_login")));
                    } else {
                        isRegistrationEnabledOutputModel.setIs_login(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setIs_login(0);
                    e.printStackTrace();
                }
                try {
                    if ((myJson.has("isMylibrary")) && myJson.optString("isMylibrary").trim() != null && !myJson.optString("isMylibrary").trim().isEmpty() && !myJson.optString("isMylibrary").trim().equals("null") && !myJson.optString("isMylibrary").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setIsMylibrary(Integer.parseInt(myJson.optString("isMylibrary")));
                    } else {
                        isRegistrationEnabledOutputModel.setIsMylibrary(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setIsMylibrary(0);
                    e.printStackTrace();
                }
                try {
                    if ((myJson.has("signup_step")) && myJson.optString("signup_step").trim() != null && !myJson.optString("signup_step").trim().isEmpty() && !myJson.optString("signup_step").trim().equals("null") && !myJson.optString("signup_step").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setSignup_step(Integer.parseInt(myJson.optString("signup_step")));
                    } else {
                        isRegistrationEnabledOutputModel.setSignup_step(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setSignup_step(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("has_favourite")) && myJson.optString("has_favourite").trim() != null && !myJson.optString("has_favourite").trim().isEmpty() && !myJson.optString("has_favourite").trim().equals("null") && !myJson.optString("has_favourite").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setHas_favourite(Integer.parseInt(myJson.optString("has_favourite")));
                    } else {
                        isRegistrationEnabledOutputModel.setHas_favourite(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setHas_favourite(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("isRating")) && myJson.optString("isRating").trim() != null && !myJson.optString("isRating").trim().isEmpty() && !myJson.optString("isRating").trim().equals("null") && !myJson.optString("isRating").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setRating(Integer.parseInt(myJson.optString("isRating")));
                    } else {
                        isRegistrationEnabledOutputModel.setRating(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setRating(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("isRestrictDevice")) && myJson.optString("isRestrictDevice").trim() != null && !myJson.optString("isRestrictDevice").trim().isEmpty()
                            && !myJson.optString("isRestrictDevice").trim().equals("null") && !myJson.optString("isRestrictDevice").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setIsRestrictDevice(Integer.parseInt(myJson.optString("isRestrictDevice")));
                    } else {
                        isRegistrationEnabledOutputModel.setIsRestrictDevice(0);

                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setIsRestrictDevice(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("chromecast")) && myJson.optString("chromecast").trim() != null && !myJson.optString("chromecast").trim().isEmpty()
                            && !myJson.optString("chromecast").trim().equals("null") && !myJson.optString("chromecast").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setChromecast(Integer.parseInt(myJson.optString("chromecast")));
                    } else {
                        isRegistrationEnabledOutputModel.setChromecast(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setChromecast(0);
                    e.printStackTrace();
                }
                try {
                    if ((myJson.has("is_streaming_restriction")) && myJson.optString("is_streaming_restriction").trim() != null && !myJson.optString("is_streaming_restriction").trim().isEmpty()
                            && !myJson.optString("is_streaming_restriction").trim().equals("null") && !myJson.optString("is_streaming_restriction").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setIs_streaming_restriction(Integer.parseInt(myJson.optString("is_streaming_restriction")));
                    } else {
                        isRegistrationEnabledOutputModel.setIs_streaming_restriction(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setIs_streaming_restriction(0);
                    e.printStackTrace();
                }

                /**
                 * @author Debashish
                 * @description Added to handle watch history key coming from cms
                 */
                try {
                    if ((myJson.has("watch_history")) && myJson.optString("watch_history").trim() != null && !myJson.optString("watch_history").trim().isEmpty()
                            && !myJson.optString("watch_history").trim().equals("null") && !myJson.optString("watch_history").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setWatch_history(Integer.parseInt(myJson.optString("watch_history")));
                    } else {
                        isRegistrationEnabledOutputModel.setWatch_history(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setWatch_history(0);
                    e.printStackTrace();
                }


                try {
                    if ((myJson.has("is_offline")) && myJson.optString("is_offline").trim() != null && !myJson.optString("is_offline").trim().isEmpty()
                            && !myJson.optString("is_offline").trim().equals("null") && !myJson.optString("is_offline").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setIs_offline(Integer.parseInt(myJson.optString("is_offline")));
                    } else {
                        isRegistrationEnabledOutputModel.setIs_offline(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setIs_offline(0);
                    e.printStackTrace();
                }


                try {
                    if ((myJson.has("is_registration_enabled")) && myJson.optString("is_registration_enabled").trim() != null && !myJson.optString("is_registration_enabled").trim().isEmpty()
                            && !myJson.optString("is_registration_enabled").trim().equals("null") && !myJson.optString("is_registration_enabled").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setIs_registration_enabled(Integer.parseInt(myJson.optString("is_registration_enabled")));
                    } else {
                        isRegistrationEnabledOutputModel.setIs_registration_enabled(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setIs_registration_enabled(0);
                    e.printStackTrace();
                }

                /*@Subhadarshani
                 *Added to handle is_ugc_enabled key is coming or not from csm
                 */

                try {
                    if ((myJson.has("is_ugc_enabled")) && myJson.optString("is_ugc_enabled").trim() != null && !myJson.optString("is_ugc_enabled").trim().isEmpty() && !myJson.optString("is_ugc_enabled").trim().equals("null") && !myJson.optString("is_ugc_enabled").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setIsUGCEnabled(Integer.parseInt(myJson.optString("is_ugc_enabled")));
                    } else {
                        isRegistrationEnabledOutputModel.setIsUGCEnabled(0);
                    }
                } catch (NumberFormatException e) {
                    isRegistrationEnabledOutputModel.setIsUGCEnabled(0);
                    e.printStackTrace();
                }
                /*@Subhadarshani
                 *Added to handle the banner and poster image width and height for cropping which is required for uploading UGC content.
                 */
                try {
                    if ((myJson.has("vertical_poster_height")) && myJson.optString("vertical_poster_height").trim() != null && !myJson.optString("vertical_poster_height").trim().isEmpty() && !myJson.optString("vertical_poster_height").trim().equals("null") && !myJson.optString("vertical_poster_height").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setPosterHeight(Integer.parseInt(myJson.optString("vertical_poster_height")));
                    } else {
                        isRegistrationEnabledOutputModel.setPosterHeight(0);
                    }

                } catch (Exception e) {
                    isRegistrationEnabledOutputModel.setPosterHeight(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("vertical_poster_width")) && myJson.optString("vertical_poster_width").trim() != null && !myJson.optString("vertical_poster_width").trim().isEmpty() && !myJson.optString("vertical_poster_width").trim().equals("null") && !myJson.optString("horizontal_poster_width").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setPosterWidth(Integer.parseInt(myJson.optString("vertical_poster_width")));
                    } else {
                        isRegistrationEnabledOutputModel.setPosterWidth(0);
                    }

                } catch (Exception e) {
                    isRegistrationEnabledOutputModel.setPosterWidth(0);
                    e.printStackTrace();
                }

                try {
                    if ((myJson.has("banner_height")) && myJson.optString("banner_height").trim() != null && !myJson.optString("banner_height").trim().isEmpty() && !myJson.optString("banner_height").trim().equals("null") && !myJson.optString("banner_height").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setBannerHeight(Integer.parseInt(myJson.optString("banner_height")));
                    } else {
                        isRegistrationEnabledOutputModel.setBannerHeight(0);
                    }

                } catch (Exception e) {
                    isRegistrationEnabledOutputModel.setBannerHeight(0);
                    e.printStackTrace();
                }
                try {
                    if ((myJson.has("banner_width")) && myJson.optString("banner_width").trim() != null && !myJson.optString("banner_width").trim().isEmpty() && !myJson.optString("banner_width").trim().equals("null") && !myJson.optString("banner_width").trim().matches("")) {
                        isRegistrationEnabledOutputModel.setBannerWidth(Integer.parseInt(myJson.optString("banner_width")));
                    } else {
                        isRegistrationEnabledOutputModel.setBannerWidth(0);
                    }
                } catch (Exception e) {
                    isRegistrationEnabledOutputModel.setBannerWidth(0);
                    e.printStackTrace();
                }

                if (Utils.getNotNullValue("is_autoplay_enabled", myJson).equalsIgnoreCase("")) {
                    isRegistrationEnabledOutputModel.setIs_autoplay_enabled(0);
                } else {
                    isRegistrationEnabledOutputModel.setIs_autoplay_enabled(Integer.parseInt(Utils.getNotNullValue("is_autoplay_enabled", myJson)));
                }

                if (Utils.getNotNullValue("is_season_available", myJson).equalsIgnoreCase("")) {
                    isRegistrationEnabledOutputModel.setIs_seasonpage_enabled(0);
                } else {
                    isRegistrationEnabledOutputModel.setIs_seasonpage_enabled(Integer.parseInt(Utils.getNotNullValue("is_season_available", myJson)));
                }



                /*@BISHAL
                 *Added to handle 455 status when need login is 0 the 455 status come
                 */
            } else if (status == 455) {
                status = Integer.parseInt(myJson.optString("code"));
                message = myJson.optString("status");
            } else {

                responseStr = "0";
                status = 0;
                message = "Error";
            }
        } catch (Exception e) {

            responseStr = "0";
            status = 0;
            message = "Error";
        }
        return null;


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onIsRegistrationenabledPreExecuteStarted();

        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onIsRegistrationenabledPostExecuteCompleted(isRegistrationEnabledOutputModel, status, message, responseStr);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onIsRegistrationenabledPostExecuteCompleted(isRegistrationEnabledOutputModel, status, message, responseStr);
            return;
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        listener.onIsRegistrationenabledPostExecuteCompleted(isRegistrationEnabledOutputModel, status, message, responseStr);

    }

}
