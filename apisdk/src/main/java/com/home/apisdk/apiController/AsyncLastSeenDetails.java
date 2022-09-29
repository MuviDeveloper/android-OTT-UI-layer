package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.GetlastSeenDetailsInput;
import com.home.apisdk.apiModel.GetlastSeenDetailsOutputModel;
import com.home.apisdk.apiModel.NextEpisode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This Class is used for the login details of the users who all are login using Gmail account
 *
 * @author MUVI
 */


public class AsyncLastSeenDetails extends AsyncTask<GetlastSeenDetailsInput, Void, Void> {
    private GetlastSeenDetailsInput getlastSeenDetailsInput;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private String responseStr;
    private AsyncLastSeenDetailsListener listener;
    private Context context;

    /**
     * Interface used to allow the caller of a AsyncGmailReg to run some code when get
     * responses.
     */


    public interface AsyncLastSeenDetailsListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onLastSeenDetailsPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param GetlastSeenDetailsOutputModel A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status           Response Code From The Server
         * @param message          On Success Message
         */

        void onLastSeenDetailsPostExecuteCompleted(GetlastSeenDetailsOutputModel GetlastSeenDetailsOutputModel, int status, String message);
    }

    GetlastSeenDetailsOutputModel getlastSeenDetailsOutputModel = new GetlastSeenDetailsOutputModel();
    ArrayList<NextEpisode> nextEpisodesList = new ArrayList<>();




    /**
     * Constructor to initialise the private data members.
     *
     * @param getlastSeenDetailsInput A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                        For Example: to use this API we have to set following attributes:
     *                        setAuthToken(),setMovie_id() etc.
     * @param listener        AsyncGmailListener
     * @param context         android.content.Context
     */

    public AsyncLastSeenDetails(GetlastSeenDetailsInput getlastSeenDetailsInput, AsyncLastSeenDetailsListener listener, Context context) {
        this.listener = listener;
        this.context = context;

        this.getlastSeenDetailsInput = getlastSeenDetailsInput;
        PACKAGE_NAME = context.getPackageName();
    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException
     */

    @Override
    protected Void doInBackground(GetlastSeenDetailsInput... params) {
        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();


            parameters.put(HeaderConstants.AUTH_TOKEN, this.getlastSeenDetailsInput.getAuthToken());
            parameters.put(HeaderConstants.USER_ID, this.getlastSeenDetailsInput.getUserId());
            parameters.put(HeaderConstants.MOVIE_UNIQ_ID, this.getlastSeenDetailsInput.getMuviUniqId());
            parameters.put(HeaderConstants.COUNTRY, this.getlastSeenDetailsInput.getCountryCode());
            parameters.put(HeaderConstants.LANG_CODE, this.getlastSeenDetailsInput.getLangCode());

            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getLastSeenUrl());

            }  catch (Exception e) {
                status = 0;
                message = "Error";
            }

            JSONObject myJson = null;
            JSONArray nextEpisode = null;

            if (responseStr != null) {
                myJson = new JSONObject(responseStr);
                status = Integer.parseInt(myJson.optString("code"));
                message = myJson.optString("msg");

                if (status == 200)
                {
                    if (myJson.has("last_seen_stream_uniq_id") && myJson.optString("last_seen_stream_uniq_id").trim() != null && !myJson.optString("last_seen_stream_uniq_id").trim().isEmpty() && !myJson.optString("last_seen_stream_uniq_id").trim().equals("null") && !myJson.optString("last_seen_stream_uniq_id").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setLastSeenStreamUniqId(myJson.optString("last_seen_stream_uniq_id"));
                    } else {
                        getlastSeenDetailsOutputModel.setLastSeenStreamUniqId("");

                    }

                    if (myJson.has("last_seen_muvi_uniq_id") && myJson.optString("last_seen_muvi_uniq_id").trim() != null && !myJson.optString("last_seen_muvi_uniq_id").trim().isEmpty() && !myJson.optString("last_seen_muvi_uniq_id").trim().equals("null") && !myJson.optString("last_seen_muvi_uniq_id").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setLastSeenMuviUniqId(myJson.optString("last_seen_muvi_uniq_id"));
                    } else {
                        getlastSeenDetailsOutputModel.setLastSeenMuviUniqId("");
                    }


                    if (myJson.has("series_no") && myJson.optString("series_no").trim() != null && !myJson.optString("series_no").trim().isEmpty() && !myJson.optString("series_no").trim().equals("null") && !myJson.optString("series_no").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setSeriesNo(myJson.optString("series_no"));
                    } else {
                        getlastSeenDetailsOutputModel.setSeriesNo("");
                    }

                    if (myJson.has("parent_title") && myJson.optString("parent_title").trim() != null && !myJson.optString("parent_title").trim().isEmpty() && !myJson.optString("parent_title").trim().equals("null") && !myJson.optString("parent_title").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setParent_title(myJson.optString("parent_title"));
                    } else {
                        getlastSeenDetailsOutputModel.setParent_title("");
                    }
                    if (myJson.has("parent_poster") && myJson.optString("parent_poster").trim() != null && !myJson.optString("parent_poster").trim().isEmpty() && !myJson.optString("parent_poster").trim().equals("null") && !myJson.optString("parent_poster").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setParent_poster(myJson.optString("parent_poster"));
                    } else {
                        getlastSeenDetailsOutputModel.setParent_poster("");
                    }

                    if (myJson.has("default_poster") && myJson.optString("default_poster").trim() != null && !myJson.optString("default_poster").trim().isEmpty() && !myJson.optString("default_poster").trim().equals("null") && !myJson.optString("default_poster").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setDefault_poster(myJson.optString("default_poster"));
                    } else {
                        getlastSeenDetailsOutputModel.setDefault_poster("");
                    }

                    if (myJson.has("content_title") && myJson.optString("content_title").trim() != null && !myJson.optString("content_title").trim().isEmpty() && !myJson.optString("content_title").trim().equals("null") && !myJson.optString("content_title").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setContent_title(myJson.optString("content_title"));
                    } else {
                        getlastSeenDetailsOutputModel.setContent_title("");
                    }


                    if (myJson.has("episode_no") && myJson.optString("episode_no").trim() != null && !myJson.optString("episode_no").trim().isEmpty() && !myJson.optString("episode_no").trim().equals("null") && !myJson.optString("episode_no").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setEpisode_no(myJson.optString("episode_no"));
                    } else {
                        getlastSeenDetailsOutputModel.setEpisode_no("");
                    }

                    if (myJson.has("video_duration") && myJson.optString("video_duration").trim() != null && !myJson.optString("video_duration").trim().isEmpty() && !myJson.optString("video_duration").trim().equals("null") && !myJson.optString("video_duration").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setVideo_duration(myJson.optString("video_duration"));
                    } else {
                        getlastSeenDetailsOutputModel.setVideo_duration("");
                    }

                    if (myJson.has("story") && myJson.optString("story").trim() != null && !myJson.optString("story").trim().isEmpty() && !myJson.optString("story").trim().equals("null") && !myJson.optString("story").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setStory(myJson.optString("story"));
                    } else {
                        getlastSeenDetailsOutputModel.setStory("");
                    }



                    if (myJson.has("completd_status") && myJson.optString("completd_status").trim() != null && !myJson.optString("completd_status").trim().isEmpty() && !myJson.optString("completd_status").trim().equals("null") && !myJson.optString("completd_status").trim().matches("")) {
                        getlastSeenDetailsOutputModel.setCompletedStatus(myJson.optString("completd_status"));
                    } else {
                        getlastSeenDetailsOutputModel.setCompletedStatus("");
                    }

                    try{
                        if (myJson.has("next_episodes") && myJson.optString("next_episodes").trim() != null && !myJson.optString("next_episodes").trim().isEmpty() && !myJson.optString("next_episodes").trim().equals("null") && !myJson.optString("next_episodes").trim().matches("")) {
                            nextEpisode = myJson.optJSONArray("next_episodes");
                        }

                        if(nextEpisode != null && nextEpisode.length() >0){

                            for(int l=0; l<nextEpisode.length(); l++){
                                NextEpisode NEXT_EPISODE = new NextEpisode();
                                NEXT_EPISODE.setStreamUniqId(nextEpisode.getJSONObject(l).optString("stream_uniq_id").toString().trim());
                                nextEpisodesList.add(NEXT_EPISODE);
                            }
                            getlastSeenDetailsOutputModel.setNextEpisodes(nextEpisodesList);
                        }




                    }catch (Exception e){e.printStackTrace();}


                }else {

                    responseStr = "";
                    status = 0;
                    message = "Error";
                }
            }
        } catch (Exception e) {
            responseStr = "";
            status = 0;
            message = "Error";
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onLastSeenDetailsPreExecuteStarted();

        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onLastSeenDetailsPostExecuteCompleted(getlastSeenDetailsOutputModel, status, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onLastSeenDetailsPostExecuteCompleted(getlastSeenDetailsOutputModel, status, message);
        }

    }


    @Override
    protected void onPostExecute(Void result) {
        listener.onLastSeenDetailsPostExecuteCompleted(getlastSeenDetailsOutputModel, status, message);

    }
}
