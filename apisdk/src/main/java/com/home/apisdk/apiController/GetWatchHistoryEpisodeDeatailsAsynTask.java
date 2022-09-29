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
import com.home.apisdk.apiModel.APVModel;
import com.home.apisdk.apiModel.CurrencyModel;
import com.home.apisdk.apiModel.PPVModel;
import com.home.apisdk.apiModel.Watch_History_Episode_Details_input;
import com.home.apisdk.apiModel.Watch_History_Episode_Details_output;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This Class gives all the details about the Episode to the users which they are looking for.
 * Details like Poster, Duration, Price for subscription etc.
 *
 * @author MUVI
 */
public class GetWatchHistoryEpisodeDeatailsAsynTask extends AsyncTask<Watch_History_Episode_Details_input, Void, Void> {

    private Watch_History_Episode_Details_input watch_history_episode_details_input;
    private String responseStr = "";
    private String cacheresponseStr;
    private String movieUniqueId = "";
    private int status;
    private int is_ppv;
    private int item_count;
    private int isAPV;
    private String message = "";
    private String permalink = "";
    private String PACKAGE_NAME;
    private GetWatchHistoryEpisodeDetailsListener listener;
    private Context context;
    private boolean isCacheEnabled;
    private APIController apiController;

    /**
     * Interface used to allow the caller of a GetEpisodeDeatailsAsynTask to run some code when get
     * responses.
     */

    public interface GetWatchHistoryEpisodeDetailsListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onGetWatchHistoryEpisodeDetailsPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param episode_details_output A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status                 Response Code From The Server
         * @param message                On Success Message
         * @param movieUniqueId          For Movie Unique ID
         */

        void onGetWatchHistoryEpisodeDetailsPostExecuteCompleted(Watch_History_Episode_Details_output episode_details_output, int status, int i, String message, String movieUniqueId, boolean isCacheData, String offset);


    }


    Watch_History_Episode_Details_output episode_details_output = new Watch_History_Episode_Details_output();
    Watch_History_Episode_Details_output.Episode episode;
    ArrayList<Watch_History_Episode_Details_output.Episode> episodeArray = new ArrayList<>();

    /**
     * Constructor to initialise the private data members.
     *
     * @param watch_history_episode_details_input A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                                            For Example: to use this API we have to set following attributes:
     *                                            setAuthToken(),setPermalink() etc.
     * @param listener                            GetEpisodeDetails Listener
     * @param context                             android.content.Context
     */

    public GetWatchHistoryEpisodeDeatailsAsynTask(Watch_History_Episode_Details_input watch_history_episode_details_input, GetWatchHistoryEpisodeDetailsListener listener, Context context, boolean isCacheEnabled, APIController apiController) {
        this.listener = listener;
        this.context = context;
        this.watch_history_episode_details_input = watch_history_episode_details_input;
        this.isCacheEnabled = isCacheEnabled;
        this.apiController = apiController;
        PACKAGE_NAME = context.getPackageName();
        Log.v("MUVISDK", "pkgnm :" + PACKAGE_NAME);
        Log.v("MUVISDK", "GetContentListAsynTask");

    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException
     */

    @Override
    protected Void doInBackground(Watch_History_Episode_Details_input... params) {
        JSONObject myJson_data = null;
        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

            parameters.put(HeaderConstants.AUTH_TOKEN, this.watch_history_episode_details_input.getAuthtoken());
            parameters.put(HeaderConstants.PERMALINK, this.watch_history_episode_details_input.getPermalink());
            parameters.put(HeaderConstants.SERIES_NUMBER, this.watch_history_episode_details_input.getSeries_number());
            parameters.put(HeaderConstants.USER_ID, this.watch_history_episode_details_input.getUserId());
            parameters.put(HeaderConstants.COUNTRY, this.watch_history_episode_details_input.getCountry());
            parameters.put(HeaderConstants.LANG_CODE, this.watch_history_episode_details_input.getLang_code());
            parameters.put(HeaderConstants.KIDS_MODE, this.watch_history_episode_details_input.getKids_mode());


            responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getWatchHistoryEpisodeDetailsUrl());
            Log.e("WHEApiCall", "ApiCalled");
            parseAPIData();

        } catch (Exception e) {
            status = 0;
            message = "Error";
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetWatchHistoryEpisodeDetailsPreExecuteStarted();
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGetWatchHistoryEpisodeDetailsPostExecuteCompleted(episode_details_output, status, item_count, message, movieUniqueId, false, this.watch_history_episode_details_input.getOffset());
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGetWatchHistoryEpisodeDetailsPostExecuteCompleted(episode_details_output, status, item_count, message, movieUniqueId, false, this.watch_history_episode_details_input.getOffset());
        }


        if (this.apiController.getAPIData(APIUrlConstant.GET_WATCH_HISTORY_EPISODE_DETAILS_URL, this.watch_history_episode_details_input.getPermalink() + this.watch_history_episode_details_input.getSeries_number(), isCacheEnabled) == null) {

        } else {
            responseStr = this.apiController.getAPICacheData(APIUrlConstant.GET_WATCH_HISTORY_EPISODE_DETAILS_URL, this.watch_history_episode_details_input.getPermalink() + this.watch_history_episode_details_input.getSeries_number());
            cacheresponseStr = responseStr;
            parseAPIData();
            listener.onGetWatchHistoryEpisodeDetailsPostExecuteCompleted(episode_details_output, status, item_count, message, movieUniqueId, true, this.watch_history_episode_details_input.getOffset());
        }


    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (cacheresponseStr == null || responseStr == null) {
            listener.onGetWatchHistoryEpisodeDetailsPostExecuteCompleted(episode_details_output, status, item_count, message, movieUniqueId, false, this.watch_history_episode_details_input.getOffset());
            return;
        }

        if (!cacheresponseStr.equals(responseStr)) {
            listener.onGetWatchHistoryEpisodeDetailsPostExecuteCompleted(episode_details_output, status, item_count, message, movieUniqueId, false, this.watch_history_episode_details_input.getOffset());
        }
    }

    public void parseAPIData() {
        JSONObject myJson = null;
        JSONObject myJson_data = null;

        episodeArray.clear();

        if (this.apiController.getAPIData(APIUrlConstant.GET_WATCH_HISTORY_EPISODE_DETAILS_URL, this.watch_history_episode_details_input.getPermalink(), isCacheEnabled) == null) {


            /////// Execute HTTP Post Request
            try {


                if (responseStr != null) {
                    try {
                        myJson_data = new JSONObject(responseStr);
                        status = Integer.parseInt(myJson_data.optString("code"));
                        message = myJson_data.optString("msg");
                        myJson = myJson_data.optJSONObject("data");
                        try {
                            item_count = Integer.parseInt(myJson.optString("item_count"));
                        } catch (Exception e) {
                            item_count = 0;
                        }
//                        if (status == 200 && item_count > 0) {
//                            this.apiController.insertCachedDataToDB(APIUrlConstant.GET_WATCH_HISTORY_EPISODE_DETAILS_URL, this.watch_history_episode_details_input.getPermalink() + this.watch_history_episode_details_input.getSeries_number(), responseStr, Utils.getCurrentTimeStamp());
//                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            } catch (Exception e) {
                status = 0;
                message = "Error";
            }

        } else {
            if (responseStr != null) {
                try {
                    myJson_data = new JSONObject(responseStr);
                    status = Integer.parseInt(myJson_data.optString("code"));
                    message = myJson_data.optString("msg");

                    myJson = myJson_data.optJSONObject("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        try {
            if (responseStr != null) {
                try {
                    item_count = Integer.parseInt(myJson.optString("item_count"));
                } catch (Exception e) {
                    item_count = 0;
                }
                movieUniqueId = myJson.optString("muvi_uniq_id");
                permalink = myJson.optString("permalink");
                episode_details_output.setName(myJson.optString("name"));
            }
            Log.v("SUBHA", "episode api controller" + responseStr);

            if (status > 0) {

                if (status == 200) {
                    this.apiController.insertCachedDataToDB(APIUrlConstant.GET_WATCH_HISTORY_EPISODE_DETAILS_URL, this.watch_history_episode_details_input.getPermalink() + this.watch_history_episode_details_input.getSeries_number(), responseStr, Utils.getCurrentTimeStamp());

                    try {
                        if ((myJson.has("is_ppv")) && myJson.optString("is_ppv").trim() != null && !myJson.optString("is_ppv").trim().isEmpty() && !myJson.optString("is_ppv").trim().equals("null") && !myJson.optString("is_ppv").trim().matches("")) {

                            is_ppv = Integer.parseInt(myJson.optString("is_ppv"));
                        } else {
                            is_ppv = 0;
                        }
                    } catch (Exception e) {
                        is_ppv = 0;
                    }


                    episode_details_output.setIs_ppv(is_ppv);

                    try {
                        if ((myJson.has("is_advance")) && myJson.optString("is_advance").trim() != null && !myJson.optString("is_advance").trim().isEmpty() && !myJson.optString("is_advance").trim().equals("null") && !myJson.optString("is_advance").trim().matches("")) {

                            isAPV = Integer.parseInt(myJson.optString("is_advance"));
                        } else {
                            isAPV = 0;
                        }
                    } catch (Exception e) {
                        isAPV = 0;
                    }

                    episode_details_output.setIsAPV(isAPV);

                    // Episode_Details_output payment = new Episode_Details_output();

                    if (is_ppv == 1) {
                        JSONObject ppvJson = null;
                        if ((myJson.has("ppv_pricing"))) {

                            PPVModel ppvModel = new PPVModel();
                            ppvJson = myJson.getJSONObject("ppv_pricing");

                            if ((ppvJson.has("price_for_unsubscribed")) && ppvJson.optString("price_for_unsubscribed").trim() != null && !ppvJson.optString("price_for_unsubscribed").trim().isEmpty() && !ppvJson.optString("price_for_unsubscribed").trim().equals("null") && !ppvJson.optString("price_for_unsubscribed").trim().matches("")) {
                                // priceForUnsubscribedStr = ppvJson.optString("price_for_unsubscribed");
                                ppvModel.setPPVPriceForUnsubscribedStr(ppvJson.optString("price_for_unsubscribed"));
                            } else {
                                // priceForUnsubscribedStr = "0.0";
                                ppvModel.setPPVPriceForUnsubscribedStr("0.0");


                            }
                            if ((ppvJson.has("price_for_subscribed")) && ppvJson.optString("price_for_subscribed").trim() != null && !ppvJson.optString("price_for_subscribed").trim().isEmpty() && !ppvJson.optString("price_for_subscribed").trim().equals("null") && !ppvJson.optString("price_for_subscribed").trim().matches("")) {
                                //priceFosubscribedStr = ppvJson.optString("price_for_subscribed");
                                ppvModel.setPPVPriceForUnsubscribedStr(ppvJson.optString("price_for_subscribed"));

                            } else {
                                // priceFosubscribedStr = "0.0";
                                ppvModel.setPPVPriceForUnsubscribedStr("0.0");

                            }
                            if ((ppvJson.has("id")) && ppvJson.optString("id").trim() != null && !ppvJson.optString("id").trim().isEmpty() && !ppvJson.optString("id").trim().equals("null") && !ppvJson.optString("id").trim().matches("")) {
                                //  planIdStr = ppvJson.optString("id");
                                ppvModel.setPpvPlanId(ppvJson.optString("id"));

                            } else {
                                //  planIdStr = "0";
                                ppvModel.setPpvPlanId("0");

                            }
                            //SubhaShree
                            if ((ppvJson.has("show_unsubscribed")) && ppvJson.optString("show_unsubscribed").trim() != null && !ppvJson.optString("show_unsubscribed").trim().isEmpty() && !ppvJson.optString("show_unsubscribed").trim().equals("null") && !ppvJson.optString("show_unsubscribed").trim().matches("")) {
                                //  planIdStr = ppvJson.optString("id");
                                ppvModel.setPpvShowUnsubscribedStr(ppvJson.optString("show_unsubscribed"));

                            } else {
                                //  planIdStr = "0";
                                ppvModel.setPpvShowUnsubscribedStr("0.0");


                            }
                            if ((ppvJson.has("show_subscribed")) && ppvJson.optString("show_subscribed").trim() != null && !ppvJson.optString("show_subscribed").trim().isEmpty() && !ppvJson.optString("show_subscribed").trim().equals("null") && !ppvJson.optString("show_subscribed").trim().matches("")) {
                                //  planIdStr = ppvJson.optString("id");
                                ppvModel.setPpvShowSubscribedStr(ppvJson.optString("show_subscribed"));

                            } else {
                                //  planIdStr = "0";
                                ppvModel.setPpvShowSubscribedStr("0.0");


                            }

                            if ((ppvJson.has("season_unsubscribed")) && ppvJson.optString("season_unsubscribed").trim() != null && !ppvJson.optString("season_unsubscribed").trim().isEmpty() && !ppvJson.optString("season_unsubscribed").trim().equals("null") && !ppvJson.optString("season_unsubscribed").trim().matches("")) {
                                //  planIdStr = ppvJson.optString("id");
                                ppvModel.setPpvSeasonUnsubscribedStr(ppvJson.optString("season_unsubscribed"));

                            } else {
                                //  planIdStr = "0";
                                ppvModel.setPpvSeasonUnsubscribedStr("0.0");


                            }
                            if ((ppvJson.has("season_subscribed")) && ppvJson.optString("season_subscribed").trim() != null && !ppvJson.optString("season_subscribed").trim().isEmpty() && !ppvJson.optString("season_subscribed").trim().equals("null") && !ppvJson.optString("season_subscribed").trim().matches("")) {
                                //  planIdStr = ppvJson.optString("id");
                                ppvModel.setPpvSeasonSubscribedStr(ppvJson.optString("season_subscribed"));

                            } else {
                                //  planIdStr = "0";
                                ppvModel.setPpvSeasonSubscribedStr("0.0");


                            }
                            if ((ppvJson.has("episode_unsubscribed")) && ppvJson.optString("episode_unsubscribed").trim() != null && !ppvJson.optString("episode_unsubscribed").trim().isEmpty() && !ppvJson.optString("episode_unsubscribed").trim().equals("null") && !ppvJson.optString("episode_unsubscribed").trim().matches("")) {
                                //  planIdStr = ppvJson.optString("id");
                                ppvModel.setPpvEpisodeUnsubscribedStr(ppvJson.optString("episode_unsubscribed"));

                            } else {
                                //  planIdStr = "0";
                                ppvModel.setPpvEpisodeUnsubscribedStr("0.0");


                            }
                            if ((ppvJson.has("episode_subscribed")) && ppvJson.optString("episode_subscribed").trim() != null && !ppvJson.optString("episode_subscribed").trim().isEmpty() && !ppvJson.optString("episode_subscribed").trim().equals("null") && !ppvJson.optString("episode_subscribed").trim().matches("")) {
                                //  planIdStr = ppvJson.optString("id");
                                ppvModel.setPpvEpisodeSubscribedStr(ppvJson.optString("episode_subscribed"));

                            } else {
                                //  planIdStr = "0";
                                ppvModel.setPpvEpisodeSubscribedStr("0.0");


                            }
                            if ((ppvJson.has("validity_period")) && ppvJson.optString("validity_period").trim() != null && !ppvJson.optString("validity_period").trim().isEmpty() && !ppvJson.optString("validity_period").trim().equals("null") && !ppvJson.optString("validity_period").trim().matches("")) {
                                //  planIdStr = ppvJson.optString("id");
                                ppvModel.setValidity_period(ppvJson.optString("validity_period"));

                            } else {
                                //  planIdStr = "0";
                                ppvModel.setValidity_period("0");


                            }
                            try {
                                if ((ppvJson.has("is_show")) && ppvJson.optString("is_show").trim() != null && !ppvJson.optString("is_show").trim().isEmpty() && !ppvJson.optString("is_show").trim().equals("null") && !ppvJson.optString("is_show").trim().matches("")) {
                                    ppvModel.setIsShow(Integer.parseInt(ppvJson.optString("is_show")));

                                } else {
                                    ppvModel.setIsShow(0);
                                }
                            } catch (Exception e) {
                                ppvModel.setIsShow(0);
                            }

                            try {
                                if ((ppvJson.has("is_season")) && ppvJson.optString("is_season").trim() != null && !ppvJson.optString("is_season").trim().isEmpty() && !ppvJson.optString("is_season").trim().equals("null") && !ppvJson.optString("is_season").trim().matches("")) {
                                    ppvModel.setIsSeason(Integer.parseInt(ppvJson.optString("is_season")));

                                } else {
                                    ppvModel.setIsSeason(0);
                                }
                            } catch (Exception e) {
                                ppvModel.setIsSeason(0);
                            }

                            try {
                                if ((ppvJson.has("is_episode")) && ppvJson.optString("is_episode").trim() != null && !ppvJson.optString("is_episode").trim().isEmpty() && !ppvJson.optString("is_episode").trim().equals("null") && !ppvJson.optString("is_episode").trim().matches("")) {
                                    ppvModel.setIsEpisode(Integer.parseInt(ppvJson.optString("is_episode")));

                                } else {
                                    ppvModel.setIsEpisode(0);
                                }
                            } catch (Exception e) {
                                ppvModel.setIsEpisode(0);
                            }

                            if ((ppvJson.has("pricing_id")) && ppvJson.optString("pricing_id").trim() != null && !ppvJson.optString("pricing_id").trim().isEmpty() && !ppvJson.optString("pricing_id").trim().equals("null") && !ppvJson.optString("pricing_id").trim().matches("")) {
                                //  planIdStr = ppvJson.optString("id");
                                ppvModel.setPricing_id(ppvJson.optString("pricing_id"));

                            } else {
                                //  planIdStr = "0";
                                ppvModel.setPricing_id("0");


                            }
                            if ((ppvJson.has("validity_recurrence")) && ppvJson.optString("validity_recurrence").trim() != null && !ppvJson.optString("validity_recurrence").trim().isEmpty() && !ppvJson.optString("validity_recurrence").trim().equals("null") && !ppvJson.optString("validity_recurrence").trim().matches("")) {
                                //  planIdStr = ppvJson.optString("id");
                                ppvModel.setValidity_recurrence(ppvJson.optString("validity_recurrence"));

                            } else {
                                //  planIdStr = "0";
                                ppvModel.setValidity_recurrence("0");


                            }

                            episode_details_output.setPpvDetails(ppvModel);
                        }

                    }

                    if (isAPV == 1) {
                        JSONObject advJson = null;
                        if ((myJson.has("adv_pricing"))) {
                            APVModel aPVModel = new APVModel();

                            advJson = myJson.getJSONObject("adv_pricing");
                            if ((advJson.has("price_for_unsubscribed")) && advJson.optString("price_for_unsubscribed").trim() != null && !advJson.optString("price_for_unsubscribed").trim().isEmpty() && !advJson.optString("price_for_unsubscribed").trim().equals("null") && !advJson.optString("price_for_unsubscribed").trim().matches("")) {
                                aPVModel.setAPVPriceForUnsubscribedStr(advJson.optString("price_for_unsubscribed"));

                            } else {
                                aPVModel.setAPVPriceForUnsubscribedStr("0.0");

                            }
                            if ((advJson.has("price_for_subscribed")) && advJson.optString("price_for_subscribed").trim() != null && !advJson.optString("price_for_subscribed").trim().isEmpty() && !advJson.optString("price_for_subscribed").trim().equals("null") && !advJson.optString("price_for_subscribed").trim().matches("")) {
                                aPVModel.setAPVPriceForsubscribedStr(advJson.optString("price_for_subscribed"));
                            } else {
                                aPVModel.setAPVPriceForsubscribedStr("0.0");

                            }

                            if ((advJson.has("id")) && advJson.optString("id").trim() != null && !advJson.optString("id").trim().isEmpty() && !advJson.optString("id").trim().equals("null") && !advJson.optString("id").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setApvPlanId(advJson.optString("id"));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setApvPlanId("0");

                            }
                            //SubhaShree
                            if ((advJson.has("show_unsubscribed")) && advJson.optString("show_unsubscribed").trim() != null && !advJson.optString("show_unsubscribed").trim().isEmpty() && !advJson.optString("show_unsubscribed").trim().equals("null") && !advJson.optString("show_unsubscribed").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setApvShowUnsubscribedStr(advJson.optString("show_unsubscribed"));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setApvShowUnsubscribedStr("0.0");


                            }
                            if ((advJson.has("show_subscribed")) && advJson.optString("show_subscribed").trim() != null && !advJson.optString("show_subscribed").trim().isEmpty() && !advJson.optString("show_subscribed").trim().equals("null") && !advJson.optString("show_subscribed").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setApvShowSubscribedStr(advJson.optString("show_subscribed"));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setApvShowSubscribedStr("0.0");


                            }

                            if ((advJson.has("season_unsubscribed")) && advJson.optString("season_unsubscribed").trim() != null && !advJson.optString("season_unsubscribed").trim().isEmpty() && !advJson.optString("season_unsubscribed").trim().equals("null") && !advJson.optString("season_unsubscribed").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setApvSeasonUnsubscribedStr(advJson.optString("season_unsubscribed"));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setApvSeasonUnsubscribedStr("0.0");


                            }
                            if ((advJson.has("season_subscribed")) && advJson.optString("season_subscribed").trim() != null && !advJson.optString("season_subscribed").trim().isEmpty() && !advJson.optString("season_subscribed").trim().equals("null") && !advJson.optString("season_subscribed").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setApvSeasonSubscribedStr(advJson.optString("season_subscribed"));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setApvSeasonSubscribedStr("0.0");


                            }
                            if ((advJson.has("episode_unsubscribed")) && advJson.optString("episode_unsubscribed").trim() != null && !advJson.optString("episode_unsubscribed").trim().isEmpty() && !advJson.optString("episode_unsubscribed").trim().equals("null") && !advJson.optString("episode_unsubscribed").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setApvEpisodeUnsubscribedStr(advJson.optString("episode_unsubscribed"));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setApvEpisodeUnsubscribedStr("0.0");


                            }
                            if ((advJson.has("episode_subscribed")) && advJson.optString("episode_subscribed").trim() != null && !advJson.optString("episode_subscribed").trim().isEmpty() && !advJson.optString("episode_subscribed").trim().equals("null") && !advJson.optString("episode_subscribed").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setApvEpisodeSubscribedStr(advJson.optString("episode_subscribed"));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setApvEpisodeSubscribedStr("0.0");


                            }
                            if ((advJson.has("validity_period")) && advJson.optString("validity_period").trim() != null && !advJson.optString("validity_period").trim().isEmpty() && !advJson.optString("validity_period").trim().equals("null") && !advJson.optString("validity_period").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setValidity_period(advJson.optString("validity_period"));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setValidity_period("0");


                            }
                            if ((advJson.has("is_show")) && advJson.optString("is_show").trim() != null && !advJson.optString("is_show").trim().isEmpty() && !advJson.optString("is_show").trim().equals("null") && !advJson.optString("is_show").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setIsShow(Integer.parseInt(advJson.optString("is_show")));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setIsShow(0);


                            }
                            if ((advJson.has("is_season")) && advJson.optString("is_season").trim() != null && !advJson.optString("is_season").trim().isEmpty() && !advJson.optString("is_season").trim().equals("null") && !advJson.optString("is_season").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setIsSeason(Integer.parseInt(advJson.optString("is_season")));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setIsSeason(0);


                            }
                            if ((advJson.has("is_episode")) && advJson.optString("is_episode").trim() != null && !advJson.optString("is_episode").trim().isEmpty() && !advJson.optString("is_episode").trim().equals("null") && !advJson.optString("is_episode").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setIsEpisode(Integer.parseInt(advJson.optString("is_episode")));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setIsEpisode(0);
                            }

                            if ((advJson.has("pricing_id")) && advJson.optString("pricing_id").trim() != null && !advJson.optString("pricing_id").trim().isEmpty() && !advJson.optString("pricing_id").trim().equals("null") && !advJson.optString("pricing_id").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setPricing_id(advJson.optString("pricing_id"));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setPricing_id("0");


                            }
                            if ((advJson.has("validity_recurrence")) && advJson.optString("validity_recurrence").trim() != null && !advJson.optString("validity_recurrence").trim().isEmpty() && !advJson.optString("validity_recurrence").trim().equals("null") && !advJson.optString("validity_recurrence").trim().matches("")) {
                                //  planIdStr = advJson.optString("id");
                                aPVModel.setValidity_recurrence(advJson.optString("validity_recurrence"));

                            } else {
                                //  planIdStr = "0";
                                aPVModel.setValidity_recurrence("0");


                            }
                            episode_details_output.setApvDetails(aPVModel);

                        }

                    }

                    if (is_ppv == 1 || isAPV == 1) {

                        JSONObject currencyJson = null;
                        if (myJson.has("currency") && myJson.optString("currency") != null && !myJson.optString("currency").equals("null") && !myJson.optString("currency").equals("") && !myJson.optString("currency").isEmpty()) {
                            currencyJson = myJson.getJSONObject("currency");
                            CurrencyModel currencyModel = new CurrencyModel();

                            if (currencyJson.has("id") && currencyJson.optString("id").trim() != null && !currencyJson.optString("id").trim().isEmpty() && !currencyJson.optString("id").trim().equals("null") && !currencyJson.optString("id").trim().matches("")) {
                                currencyModel.setCurrencyId(currencyJson.optString("id"));
                            } else {
                                currencyModel.setCurrencyId("");

                            }
                            if (currencyJson.has("country_code") && currencyJson.optString("country_code").trim() != null && !currencyJson.optString("country_code").trim().isEmpty() && !currencyJson.optString("country_code").trim().equals("null") && !currencyJson.optString("country_code").trim().matches("")) {
                                currencyModel.setCurrencyCode(currencyJson.optString("country_code"));
                            } else {
                                currencyModel.setCurrencyCode("");
                            }
                            if (currencyJson.has("symbol") && currencyJson.optString("symbol").trim() != null && !currencyJson.optString("symbol").trim().isEmpty() && !currencyJson.optString("symbol").trim().equals("null") && !currencyJson.optString("symbol").trim().matches("")) {
                                currencyModel.setCurrencySymbol(currencyJson.optString("symbol"));
                            } else {
                                currencyModel.setCurrencySymbol("$");
                            }
                            episode_details_output.setCurrencyDetails(currencyModel);
                        }

                    }


                    JSONArray jsonMainNode = myJson.getJSONArray("episode");

                    int lengthJsonArr = jsonMainNode.length();
                    for (int i = 0; i < lengthJsonArr; i++) {

                        Log.v("mydata", jsonMainNode.length() + "  " + i);
                        JSONObject jsonChildNode;
                        try {
                            jsonChildNode = jsonMainNode.getJSONObject(i);
                            //Episode_Details_output content = new Episode_Details_output();
                            episode = new Watch_History_Episode_Details_output().new Episode();

                            if ((jsonChildNode.has("episode_title")) && jsonChildNode.optString("episode_title").trim() != null && !jsonChildNode.optString("episode_title").trim().isEmpty() && !jsonChildNode.optString("episode_title").trim().equals("null") && !jsonChildNode.optString("episode_title").trim().matches("")) {
                                // String episode_title=jsonChildNode.optString("episode_title");
                                episode.setEpisode_title(jsonChildNode.optString("episode_title"));
                            }

                            if ((jsonChildNode.has("thirdparty_url")) && jsonChildNode.optString("thirdparty_url").trim() != null && !jsonChildNode.optString("thirdparty_url").trim().isEmpty() && !jsonChildNode.optString("thirdparty_url").trim().equals("null") && !jsonChildNode.optString("thirdparty_url").trim().matches("")) {
                                // String episode_title=jsonChildNode.optString("episode_title");
                                episode.setThirdparty_url(jsonChildNode.optString("thirdparty_url"));
                            }
                            if ((jsonChildNode.has("is_media_published")) && jsonChildNode.optString("is_media_published").trim() != null && !jsonChildNode.optString("is_media_published").trim().isEmpty() && !jsonChildNode.optString("is_media_published").trim().equals("null") && !jsonChildNode.optString("is_media_published").trim().matches("")) {
                                episode.setIs_media_published(jsonChildNode.optString("is_media_published"));
                            } else {
                                episode.setIs_media_published("1");

                            }
                            if ((jsonChildNode.has("poster_url")) && jsonChildNode.optString("poster_url").trim() != null && !jsonChildNode.optString("poster_url").trim().isEmpty() && !jsonChildNode.optString("poster_url").trim().equals("null") && !jsonChildNode.optString("poster_url").trim().matches("")) {
                                episode.setPoster_url(jsonChildNode.optString("poster_url"));

                            }
                            if ((jsonChildNode.has("episode_story")) && jsonChildNode.optString("episode_story").trim() != null
                                    && !jsonChildNode.optString("episode_story").trim().isEmpty() && !jsonChildNode.optString("episode_story").trim().equals("null") && !jsonChildNode.optString("episode_story").trim().matches("")) {
                                episode.setEpisode_story(jsonChildNode.optString("episode_story"));
                            }
                            if ((jsonChildNode.has("embeddedUrl")) && jsonChildNode.optString("embeddedUrl").trim() != null && !jsonChildNode.optString("embeddedUrl").trim().isEmpty() && !jsonChildNode.optString("embeddedUrl").trim().equals("null") && !jsonChildNode.optString("embeddedUrl").trim().matches("")) {
                                episode.setEmbeddedUrl(jsonChildNode.optString("embeddedUrl"));

                            }

                            if ((jsonChildNode.has("video_duration")) && jsonChildNode.optString("video_duration").trim() != null && !jsonChildNode.optString("video_duration").trim().isEmpty() && !jsonChildNode.optString("video_duration").trim().equals("null") && !jsonChildNode.optString("video_duration").trim().matches("")) {
                                episode.setVideo_duration(jsonChildNode.optString("video_duration"));

                            }

                            try {
                                if ((jsonChildNode.has("content_types_id")) && jsonChildNode.optString("content_types_id").trim() != null && !jsonChildNode.optString("content_types_id").trim().isEmpty() && !jsonChildNode.optString("episode_title").trim().equals("null") && !jsonChildNode.optString("content_types_id").trim().matches("")) {
                                    episode.setContent_types_id(jsonChildNode.optInt("content_types_id"));
                                } else {
                                    episode.setContent_types_id(0);
                                }
                            } catch (Exception e) {
                                episode.setContent_types_id(0);
                            }

                            try {
                                if ((jsonChildNode.has("is_free")) && jsonChildNode.optString("is_free").trim() != null && !jsonChildNode.optString("is_free").trim().isEmpty() && !jsonChildNode.optString("is_free").trim().equals("null") && !jsonChildNode.optString("is_free").trim().matches("")) {
                                    // String episode_title=jsonChildNode.optString("episode_title");
                                    episode.setIsFree(jsonChildNode.optString("is_free"));
                                } else {
                                    episode.setIsFree("0");
                                }
                            } catch (Exception e) {
                                episode.setIsFree("0");
                            }

                            if ((jsonChildNode.has("movie_stream_uniq_id")) && jsonChildNode.optString("movie_stream_uniq_id").trim() != null && !jsonChildNode.optString("movie_stream_uniq_id").trim().isEmpty() && !jsonChildNode.optString("movie_stream_uniq_id").trim().equals("null") && !jsonChildNode.optString("movie_stream_uniq_id").trim().matches("")) {
                                // String episode_title=jsonChildNode.optString("episode_title");
                                episode.setMovie_stream_uniq_id(jsonChildNode.optString("movie_stream_uniq_id"));
                            }

                            if ((jsonChildNode.has("video_url")) && jsonChildNode.optString("video_url").trim() != null && !jsonChildNode.optString("video_url").trim().isEmpty() && !jsonChildNode.optString("video_url").trim().equals("null") && !jsonChildNode.optString("video_url").trim().matches("")) {
                                // String episode_title=jsonChildNode.optString("episode_title");
                                episode.setVideo_url(jsonChildNode.optString("video_url"));
                            }

                            if ((jsonChildNode.has("episode_story")) && jsonChildNode.optString("episode_story").trim() != null && !jsonChildNode.optString("episode_story").trim().isEmpty() && !jsonChildNode.optString("episode_story").trim().equals("null") && !jsonChildNode.optString("episode_story").trim().matches("")) {
                                // String episode_title=jsonChildNode.optString("episode_title");
                                episode.setEpisode_story(jsonChildNode.optString("episode_story"));
                            } else {
                                episode.setEpisode_story("");

                            }
                            if ((jsonChildNode.has("episode_number")) && jsonChildNode.optString("episode_number").trim() != null && !jsonChildNode.optString("episode_number").trim().isEmpty() && !jsonChildNode.optString("episode_number").trim().equals("null") && !jsonChildNode.optString("episode_number").trim().matches("")) {
                                // String episode_title=jsonChildNode.optString("episode_title");
                                episode.setEpisode_number(jsonChildNode.optString("episode_number"));
                            }

                            if ((jsonChildNode.has("is_livestream_enabled")) && jsonChildNode.optString("is_livestream_enabled").trim() != null && !jsonChildNode.optString("is_livestream_enabled").trim().isEmpty() && !jsonChildNode.optString("is_livestream_enabled").trim().equals("null") && !jsonChildNode.optString("is_livestream_enabled").trim().matches("")) {
                                // String episode_title=jsonChildNode.optString("episode_title");
                                episode.setIs_livestream_enabled(jsonChildNode.optString("is_livestream_enabled"));
                            }

                            if ((jsonChildNode.has("series_number")) && jsonChildNode.optString("series_number").trim() != null && !jsonChildNode.optString("series_number").trim().isEmpty() && !jsonChildNode.optString("series_number").trim().equals("null") && !jsonChildNode.optString("series_number").trim().matches("")) {
                                episode.setSeries_number(jsonChildNode.optString("series_number"));

                            } else {
                                episode.setSeries_number("");

                            }
                            if ((jsonChildNode.has("episode_date")) && jsonChildNode.optString("episode_date").trim() != null && !jsonChildNode.optString("episode_date").trim().isEmpty() && !jsonChildNode.optString("episode_date").trim().equals("null") && !jsonChildNode.optString("episode_date").trim().matches("")) {
                                episode.setEpisode_date(jsonChildNode.optString("episode_date"));

                            } else {
                                episode.setEpisode_date("");

                            }
                            if ((jsonChildNode.has("poster_url")) && jsonChildNode.optString("poster_url").trim() != null && !jsonChildNode.optString("poster_url").trim().isEmpty() && !jsonChildNode.optString("poster_url").trim().equals("null") && !jsonChildNode.optString("poster_url").trim().matches("")) {
                                episode.setPoster_url(jsonChildNode.optString("poster_url"));

                            } else {
                                episode.setPoster_url("");

                            }

                            episodeArray.add(episode);
                        } catch (Exception e) {
                            status = 0;
                            // totalItems = 0;
                            message = "";
                        }
                    }
                    episode_details_output.setEpisodeArray(episodeArray);
                } else {
                    responseStr = "0";
                    status = 0;
                    // totalItems = 0;
                    message = "";
                }
            }
        } catch (Exception e) {
            status = 0;
            // totalItems = 0;
            message = "";
        }

    }

}
