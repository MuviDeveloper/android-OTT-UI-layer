/**
 * SDK initialization, platform and device information classes.
 */


package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;

import com.home.apisdk.APIController;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.FeatureContentInputModel;
import com.home.apisdk.apiModel.FeatureContentOutputModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by User on 12-12-2016.
 * Class to get Feature Content details.
 */
public class GetFeatureContentAsynTask extends AsyncTask<FeatureContentInputModel, Void, Void> {

    private FeatureContentInputModel featureContentInputModel;
    private String responseStr;
    private String cacheresponseStr;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private GetFeatureContentListener listener;
    private Context context;
    private boolean isCacheEnabled;
    private APIController apiController;
    /**
     * Interface used to allow the caller of a GetFeatureContentAsynTask to run some code when get
     * responses.
     */

    public interface GetFeatureContentListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onGetFeatureContentPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param featureContentOutputModel A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status                    Response Code From The Server
         * @param message                   On Success Message
         */

        void onGetFeatureContentPostExecuteCompleted(ArrayList<FeatureContentOutputModel> featureContentOutputModel, int status, String message, boolean isCache);
    }


    ArrayList<FeatureContentOutputModel> featureContentOutputModel = new ArrayList<FeatureContentOutputModel>();

    /**
     * Constructor to initialise the private data members.
     *
     * @param featureContentInputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                                 For Example: to use this API we have to set following attributes:
     *                                 setAuthToken(),setPermalink() etc.
     * @param listener                 GetFeatureContentListener
     * @param context                  android.content.Context
     */

    public GetFeatureContentAsynTask(FeatureContentInputModel featureContentInputModel, GetFeatureContentListener listener, Context context, boolean isCacheEnabled, APIController dataController) {
        this.listener = listener;
        this.context = context;
        this.isCacheEnabled = isCacheEnabled;
        this.apiController = dataController;
        this.featureContentInputModel = featureContentInputModel;
        PACKAGE_NAME = context.getPackageName();

    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException
     */

    @Override
    protected Void doInBackground(FeatureContentInputModel... params) {
        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

            parameters.put(HeaderConstants.AUTH_TOKEN, this.featureContentInputModel.getAuthToken());
            parameters.put(HeaderConstants.SECTION_ID, this.featureContentInputModel.getSection_id());
            parameters.put(HeaderConstants.LANG_CODE, this.featureContentInputModel.getLang_code());
            parameters.put(HeaderConstants.USER_ID, this.featureContentInputModel.getUserId());
            parameters.put(HeaderConstants.COUNTRY, this.featureContentInputModel.getCountryCode());
            parameters.put(HeaderConstants.STATE, this.featureContentInputModel.getState());
            parameters.put(HeaderConstants.CITY, this.featureContentInputModel.getCity());
            parameters.put(HeaderConstants.PLATFORM_TYPE, "mobile");
            parameters.put(HeaderConstants.RESULT_TYPE, this.featureContentInputModel.getResult_type());
            parameters.put(HeaderConstants.SEASON_INFO, this.featureContentInputModel.getSeason_info());
            parameters.put(HeaderConstants.KIDS_MODE, this.featureContentInputModel.getKids_mode());



            // Execute HTTP Post Request
            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getGetFeatureContentUrl());

                if (responseStr != null) {
                    JSONObject myJson = null;
                    myJson = new JSONObject(responseStr);
                    status = Integer.parseInt(myJson.optString("code"));
                    message = myJson.optString("status");
                    if (status == 200) {
                        this.apiController.insertCachedDataToDB(APIUrlConstant.GET_FEATURE_CONTENT_URL, this.featureContentInputModel.getSection_id(), responseStr, Utils.getCurrentTimeStamp());
                        parseAPIData(responseStr);
                    }
                }
            } catch (Exception e) {
                status = 0;
                message = "";
            }
        } catch (Exception e) {
            status = 0;
            message = "";
        }

        return null;
    }


    private void parseAPIData(String responseStr) {

        featureContentOutputModel.clear();

        JSONObject myJson = null;
        try {
            if (responseStr != null) {
                try {
                    myJson = new JSONObject(responseStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                status = Integer.parseInt(myJson.optString("code"));
                message = myJson.optString("status");
            } else {
                status = 0;
                message = "";
            }
        } catch (Exception e) {
            e.toString();
        }


        if (status == 200) {
            JSONArray jsonMainNode = null;
            try {
                jsonMainNode = myJson.getJSONArray("section");
                int lengthJsonArr = jsonMainNode.length();
                for (int i = 0; i < lengthJsonArr; i++) {
                    JSONObject jsonChildNode;
                    try {
                        jsonChildNode = jsonMainNode.getJSONObject(i);
                        FeatureContentOutputModel content = new FeatureContentOutputModel();

                        if ((jsonChildNode.has("genre")) && jsonChildNode.optString("genre").trim() != null && !jsonChildNode.optString("genre").trim().isEmpty() && !jsonChildNode.optString("genre").trim().equals("null") && !jsonChildNode.optString("genre").trim().matches("")) {
                            content.setGenre(jsonChildNode.optString("genre"));
                        }
                        if ((jsonChildNode.has("name")) && jsonChildNode.optString("name").trim() != null && !jsonChildNode.optString("name").trim().isEmpty() && !jsonChildNode.optString("name").trim().equals("null") && !jsonChildNode.optString("name").trim().matches("")) {
                            content.setName(jsonChildNode.optString("name"));
                        }
                        if ((jsonChildNode.has("poster_url")) && jsonChildNode.optString("poster_url").trim() != null && !jsonChildNode.optString("poster_url").trim().isEmpty() && !jsonChildNode.optString("poster_url").trim().equals("null") && !jsonChildNode.optString("poster_url").trim().matches("")) {
                            content.setPoster_url(jsonChildNode.optString("poster_url"));

                        }
                        if ((jsonChildNode.has("permalink")) && jsonChildNode.optString("permalink").trim() != null && !jsonChildNode.optString("permalink").trim().isEmpty() && !jsonChildNode.optString("permalink").trim().equals("null") && !jsonChildNode.optString("permalink").trim().matches("")) {
                            content.setPermalink(jsonChildNode.optString("permalink"));
                        }

                        try {
                            if ((jsonChildNode.has("season_permalink")) && jsonChildNode.optString("season_permalink").trim() != null && !jsonChildNode.optString("season_permalink").trim().isEmpty() && !jsonChildNode.optString("season_permalink").trim().equals("null") && !jsonChildNode.optString("season_permalink").trim().matches("")) {
                                content.setSeason_permalink(jsonChildNode.optString("season_permalink"));
                            }
                        } catch (Exception e) {
                            e.toString();
                        }


                        if ((jsonChildNode.has("content_types_id")) && jsonChildNode.optString("content_types_id").trim() != null && !jsonChildNode.optString("content_types_id").trim().isEmpty() && !jsonChildNode.optString("content_types_id").trim().equals("null") && !jsonChildNode.optString("content_types_id").trim().matches("")) {
                            content.setContent_types_id(jsonChildNode.optString("content_types_id"));

                        }
                        //videoTypeIdStr = "1";

                        if ((jsonChildNode.has("video_duration")) && jsonChildNode.optString("video_duration").trim() != null && !jsonChildNode.optString("video_duration").trim().isEmpty() && !jsonChildNode.optString("video_duration").trim().equals("null") && !jsonChildNode.optString("video_duration").trim().matches("")) {
                            content.setVideo_duration(jsonChildNode.optString("video_duration"));

                        }

                        try {
                            if ((jsonChildNode.has("is_converted")) && jsonChildNode.optString("is_converted").trim() != null && !jsonChildNode.optString("is_converted").trim().isEmpty() && !jsonChildNode.optString("is_converted").trim().equals("null") && !jsonChildNode.optString("is_converted").trim().matches("")) {
                                content.setIs_converted(Integer.parseInt(jsonChildNode.optString("is_converted")));
                            } else {
                                content.setIs_converted(0);
                            }
                        } catch (Exception e) {
                            content.setIs_converted(0);
                        }

                        try {
                            if ((jsonChildNode.has("is_advance")) && jsonChildNode.optString("is_advance").trim() != null && !jsonChildNode.optString("is_advance").trim().isEmpty() && !jsonChildNode.optString("is_advance").trim().equals("null") && !jsonChildNode.optString("is_advance").trim().matches("")) {
                                content.setIs_advance(Integer.parseInt(jsonChildNode.optString("is_advance")));

                            } else {
                                content.setIs_advance(0);
                            }
                        } catch (Exception e) {
                            content.setIs_advance(0);
                        }

                        try {
                            if ((jsonChildNode.has("is_ppv")) && jsonChildNode.optString("is_ppv").trim() != null && !jsonChildNode.optString("is_ppv").trim().isEmpty() && !jsonChildNode.optString("is_ppv").trim().equals("null") && !jsonChildNode.optString("is_ppv").trim().matches("")) {
                                content.setIs_ppv(Integer.parseInt(jsonChildNode.optString("is_ppv")));

                            } else {
                                content.setIs_ppv(0);
                            }
                        } catch (Exception e) {
                            content.setIs_ppv(0);
                        }

                        if ((jsonChildNode.has("is_episode")) && jsonChildNode.optString("is_episode").trim() != null && !jsonChildNode.optString("is_episode").trim().isEmpty() && !jsonChildNode.optString("is_episode").trim().equals("null") && !jsonChildNode.optString("is_episode").trim().matches("")) {
                            content.setIs_episode(jsonChildNode.optString("is_episode"));

                        }

                        if (jsonChildNode.has("is_play_list")) {
                            content.setIs_play_list(jsonChildNode.getString("is_play_list"));
                        } else {
                            content.setIs_play_list("0");
                        }
                        if (jsonChildNode.has("play_list_type")) {
                            content.setPlay_list_type(jsonChildNode.getString("play_list_type"));
                        } else {
                            content.setPlay_list_type("0");
                        }
                        if (jsonChildNode.has("list_id")) {
                            content.setList_id(jsonChildNode.getString("list_id"));
                        }
                        if ((jsonChildNode.has("season_no")) && jsonChildNode.optString("season_no").trim() != null && !jsonChildNode.optString("season_no").trim().isEmpty() && !jsonChildNode.optString("season_no").trim().equals("null") && !jsonChildNode.optString("season_no").trim().matches("")) {
                            content.setSeason_no(jsonChildNode.optString("season_no"));

                        }
                        if ((jsonChildNode.has("parent_title")) && jsonChildNode.optString("parent_title").trim() != null && !jsonChildNode.optString("parent_title").trim().isEmpty() && !jsonChildNode.optString("parent_title").trim().equals("null") && !jsonChildNode.optString("parent_title").trim().matches("")) {
                            content.setParent_title(jsonChildNode.optString("parent_title"));

                        }
                        if ((jsonChildNode.has("muvi_uniq_id")) && jsonChildNode.optString("muvi_uniq_id").trim() != null && !jsonChildNode.optString("muvi_uniq_id").trim().isEmpty() && !jsonChildNode.optString("muvi_uniq_id").trim().equals("null") && !jsonChildNode.optString("muvi_uniq_id").trim().matches("")) {
                            content.setMuvi_unique_id(jsonChildNode.optString("muvi_uniq_id"));
                        }

                        if ((jsonChildNode.has("movie_stream_uniq_id")) && jsonChildNode.optString("movie_stream_uniq_id").trim() != null && !jsonChildNode.optString("movie_stream_uniq_id").trim().isEmpty() && !jsonChildNode.optString("movie_stream_uniq_id").trim().equals("null") && !jsonChildNode.optString("movie_stream_uniq_id").trim().matches("")) {
                            content.setStream_unique_id(jsonChildNode.optString("movie_stream_uniq_id"));
                        }

                        if ((jsonChildNode.has("parent_poster_for_mobile_apps")) && jsonChildNode.optString("parent_poster_for_mobile_apps").trim() != null && !jsonChildNode.optString("parent_poster_for_mobile_apps").trim().isEmpty() && !jsonChildNode.optString("parent_poster_for_mobile_apps").trim().equals("null") && !jsonChildNode.optString("parent_poster_for_mobile_apps").trim().matches("")) {
                            content.setParent_poster_for_mobile_apps(jsonChildNode.optString("parent_poster_for_mobile_apps"));
                        }

                        if ((jsonChildNode.has("video_duration")) && jsonChildNode.optString("video_duration").trim() != null && !jsonChildNode.optString("video_duration").trim().isEmpty() && !jsonChildNode.optString("video_duration").trim().equals("null") && !jsonChildNode.optString("video_duration").trim().matches("")) {
                            content.setVideo_duration(jsonChildNode.optString("video_duration"));
                        }
                        if ((jsonChildNode.has("story")) && jsonChildNode.optString("story").trim() != null && !jsonChildNode.optString("story").trim().isEmpty() && !jsonChildNode.optString("story").trim().equals("null") && !jsonChildNode.optString("story").trim().matches("")) {
                            content.setStory(jsonChildNode.optString("story"));
                        }
                        if ((jsonChildNode.has("episode_no")) && jsonChildNode.optString("episode_no").trim() != null && !jsonChildNode.optString("episode_no").trim().isEmpty() && !jsonChildNode.optString("episode_no").trim().equals("null") && !jsonChildNode.optString("episode_no").trim().matches("")) {
                            content.setEpisode_no(jsonChildNode.optString("episode_no"));
                        }
                        if ((jsonChildNode.has("is_livestream_enabled")) && jsonChildNode.optString("is_livestream_enabled").trim() != null && !jsonChildNode.optString("is_livestream_enabled").trim().isEmpty() && !jsonChildNode.optString("is_livestream_enabled").trim().equals("null") && !jsonChildNode.optString("is_livestream_enabled").trim().matches("")) {
                            content.setIs_livestream_enabled(jsonChildNode.optString("is_livestream_enabled"));
                        }
                        featureContentOutputModel.add(content);
                    } catch (Exception e) {
                        status = 0;
                        message = "";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetFeatureContentPreExecuteStarted();
        responseStr = "0";
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGetFeatureContentPostExecuteCompleted(featureContentOutputModel, status, message,false);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGetFeatureContentPostExecuteCompleted(featureContentOutputModel, status, message,false);
            return;
        }

        if (this.apiController.getAPIData(APIUrlConstant.GET_FEATURE_CONTENT_URL, this.featureContentInputModel.getSection_id(), isCacheEnabled) == null) {

        } else {
            responseStr = this.apiController.getAPICacheData(APIUrlConstant.GET_FEATURE_CONTENT_URL, this.featureContentInputModel.getSection_id());
            cacheresponseStr = responseStr;
            parseAPIData(responseStr);
            listener.onGetFeatureContentPostExecuteCompleted(featureContentOutputModel, status, message,true);
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        if (cacheresponseStr == null || responseStr == null) {
            listener.onGetFeatureContentPostExecuteCompleted(featureContentOutputModel, status, message,false);
            return;
        }

        if (!cacheresponseStr.equals(responseStr)) {
            listener.onGetFeatureContentPostExecuteCompleted(featureContentOutputModel, status, message,false);
        }


    }
}
