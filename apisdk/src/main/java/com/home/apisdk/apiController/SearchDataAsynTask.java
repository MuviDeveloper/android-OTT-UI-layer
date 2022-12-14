/**
 * SDK initialization, platform and device information classes.
 */


package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.Search_Data_input;
import com.home.apisdk.apiModel.Search_Data_otput;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This Class helps user in searching data like movies, episode, series etc.
 * They can search using search box.
 *
 * @author MUVI
 */
public class SearchDataAsynTask extends AsyncTask<Search_Data_input, Void, Void> {

    private Search_Data_input search_data_input;
    private String responseStr;
    private int status;
    private int totalItems;
    private String message;
    private String PACKAGE_NAME;
    private SearchDataListener listener;
    private Context context;

    /**
     * Interface used to allow the caller of a SearchDataAsynTask to run some code when get
     * responses.
     */

    public interface SearchDataListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onSearchDataPreexecute();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param contentListOutputArray A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status                 Response Code from the server
         * @param totalItems             For Getting The Total Item
         * @param message                On Success Message
         */

        void onSearchDataPostExecuteCompleted(ArrayList<Search_Data_otput> contentListOutputArray, int status, int totalItems, String message);
    }

    ArrayList<Search_Data_otput> search_data_otputs = new ArrayList<Search_Data_otput>();

    /**
     * Constructor to initialise the private data members.
     *
     * @param search_data_input A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                          For Example: to use this API we have to set following attributes:
     *                          setAuthToken(),setOffset() etc.
     * @param listener          SearchData Listener
     * @param context           android.content.Context
     */

    public SearchDataAsynTask(Search_Data_input search_data_input, SearchDataListener listener, Context context) {
        this.listener = listener;
        this.context = context;

        this.search_data_input = search_data_input;
        PACKAGE_NAME = context.getPackageName();
    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(Search_Data_input... params) {

        try {


            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

            parameters.put(HeaderConstants.AUTH_TOKEN, this.search_data_input.getAuthToken());
            parameters.put(HeaderConstants.LIMIT, this.search_data_input.getLimit());
            parameters.put(HeaderConstants.OFFSET, this.search_data_input.getOffset());
           // parameters.put(HeaderConstants.Q, this.search_data_input.getQ());
        //    parameters.put(HeaderConstants.COUNTRY, this.search_data_input.getCountry());
            parameters.put(HeaderConstants.COUNTRY, this.search_data_input.getCountry());
            parameters.put(HeaderConstants.STATE, this.search_data_input.getState());
            parameters.put(HeaderConstants.CITY, this.search_data_input.getCity());
            parameters.put(HeaderConstants.LANG_CODE, this.search_data_input.getLanguage_code());
            parameters.put(HeaderConstants.SEASON_INFO, this.search_data_input.getSeason_info());
            parameters.put(HeaderConstants.IS_CHILD_REQUIRED, "1");
            parameters.put(HeaderConstants.USER_ID, this.search_data_input.getUserId());
            parameters.put(HeaderConstants.KIDS_MODE, this.search_data_input.getKids_mode());


            // Execute HTTP Post Request
            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getSearchDataUrl()+"?q="+this.search_data_input.getQ());
            }  catch (Exception e) {
                status = 0;
                totalItems = 0;
                message = "";
            }

            JSONObject myJson = null;
            if (responseStr != null) {
                myJson = new JSONObject(responseStr);
                status = Integer.parseInt(myJson.optString("code"));
                try {
                    totalItems = Integer.parseInt(myJson.optString("item_count"));
                }catch (Exception e){
                    totalItems=0;
                }
                message = myJson.optString("msg");
            }

            if (status > 0) {
                if (status == 200) {

                    JSONArray jsonMainNode = myJson.getJSONArray("search");

                    int lengthJsonArr = jsonMainNode.length();
                    for (int i = 0; i < lengthJsonArr; i++) {
                        JSONObject jsonChildNode;
                        try {
                            jsonChildNode = jsonMainNode.getJSONObject(i);
                            Search_Data_otput content = new Search_Data_otput();

                            if ((jsonChildNode.has("genre")) && jsonChildNode.optString("genre").trim() != null && !jsonChildNode.optString("genre").trim().isEmpty() && !jsonChildNode.optString("genre").trim().equals("null") && !jsonChildNode.optString("genre").trim().matches("")) {
                               String movieTypeStr = jsonChildNode.optString("genre");
                                movieTypeStr = movieTypeStr.replaceAll("\\[", "");
                                movieTypeStr = movieTypeStr.replaceAll("\\]","");
                                movieTypeStr = movieTypeStr.replaceAll(","," , ");
                                movieTypeStr = movieTypeStr.replaceAll("\"", "");
                                content.setGenre(movieTypeStr);

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

                            try{
                                if ((jsonChildNode.has("season_permalink")) && jsonChildNode.optString("season_permalink").trim() != null && !jsonChildNode.optString("season_permalink").trim().isEmpty() && !jsonChildNode.optString("season_permalink").trim().equals("null") && !jsonChildNode.optString("season_permalink").trim().matches("")) {
                                    content.setSeason_permalink(jsonChildNode.optString("season_permalink"));
                                }
                            }catch (Exception e){e.toString();}



                            if ((jsonChildNode.has("content_types_id")) && jsonChildNode.optString("content_types_id").trim() != null && !jsonChildNode.optString("content_types_id").trim().isEmpty() && !jsonChildNode.optString("content_types_id").trim().equals("null") && !jsonChildNode.optString("content_types_id").trim().matches("")) {
                                content.setContent_types_id(jsonChildNode.optString("content_types_id"));

                            }

                            if ((jsonChildNode.has("episode_number")) && jsonChildNode.optString("episode_number").trim() != null && !jsonChildNode.optString("episode_number").trim().isEmpty() && !jsonChildNode.optString("episode_number").trim().equals("null") && !jsonChildNode.optString("episode_number").trim().matches("")) {
                                content.setEpisode_number(jsonChildNode.optString("episode_number"));
                            }

                            // parent name
                            if ((jsonChildNode.has("parent_content_title")) && jsonChildNode.optString("parent_content_title").trim() != null && !jsonChildNode.optString("parent_content_title").trim().isEmpty() && !jsonChildNode.optString("parent_content_title").trim().equals("null") && !jsonChildNode.optString("parent_content_title").trim().matches("")) {
                                content.setParent_name(jsonChildNode.optString("parent_content_title"));
                            }
                            if ((jsonChildNode.has("content_title")) && jsonChildNode.optString("content_title").trim() != null && !jsonChildNode.optString("content_title").trim().isEmpty() && !jsonChildNode.optString("content_title").trim().equals("null") && !jsonChildNode.optString("content_title").trim().matches("")) {
                                content.setEpisode_title(jsonChildNode.optString("content_title"));
                            }

                            if ((jsonChildNode.has("story")) && jsonChildNode.optString("story").trim() != null && !jsonChildNode.optString("story").trim().isEmpty() && !jsonChildNode.optString("story").trim().equals("null") && !jsonChildNode.optString("story").trim().matches("")) {
                                content.setStory(jsonChildNode.optString("story"));
                            }


                            if ((jsonChildNode.has("season_number")) && jsonChildNode.optString("season_number").trim() != null && !jsonChildNode.optString("season_number").trim().isEmpty() && !jsonChildNode.optString("season_number").trim().equals("null") && !jsonChildNode.optString("season_number").trim().matches("")) {
                                content.setSeries_no(jsonChildNode.optString("season_number"));
                            }

                            if ((jsonChildNode.has("video_duration")) && jsonChildNode.optString("video_duration").trim() != null && !jsonChildNode.optString("video_duration").trim().isEmpty() && !jsonChildNode.optString("video_duration").trim().equals("null") && !jsonChildNode.optString("video_duration").trim().matches("")) {
                                content.setVideo_duration(jsonChildNode.optString("video_duration"));

                            }

                            try {
                                if ((jsonChildNode.has("is_converted")) && jsonChildNode.optString("is_converted").trim() != null && !jsonChildNode.optString("is_converted").trim().isEmpty() && !jsonChildNode.optString("is_converted").trim().equals("null") && !jsonChildNode.optString("is_converted").trim().matches("")) {
                                    content.setIs_converted(Integer.parseInt(jsonChildNode.optString("is_converted")));

                                }else {
                                    content.setIs_converted(0);
                                }
                            }catch (Exception e){
                                content.setIs_converted(0);
                            }

                            try {
                                if ((jsonChildNode.has("is_advance")) && jsonChildNode.optString("is_advance").trim() != null && !jsonChildNode.optString("is_advance").trim().isEmpty() && !jsonChildNode.optString("is_advance").trim().equals("null") && !jsonChildNode.optString("is_advance").trim().matches("")) {
                                    content.setIs_advance(Integer.parseInt(jsonChildNode.optString("is_advance")));

                                }else {
                                    content.setIs_advance(0);
                                }
                            }catch (Exception e){
                                content.setIs_advance(0);
                            }

                            try {
                                if ((jsonChildNode.has("is_ppv")) && jsonChildNode.optString("is_ppv").trim() != null && !jsonChildNode.optString("is_ppv").trim().isEmpty() && !jsonChildNode.optString("is_ppv").trim().equals("null") && !jsonChildNode.optString("is_ppv").trim().matches("")) {
                                    content.setIs_ppv(Integer.parseInt(jsonChildNode.optString("is_ppv")));

                                }else {
                                    content.setIs_ppv(0);
                                }
                            }catch (Exception e){
                                content.setIs_ppv(0);
                            }

                            if ((jsonChildNode.has("is_episode")) && jsonChildNode.optString("is_episode").trim() != null && !jsonChildNode.optString("is_episode").trim().isEmpty() && !jsonChildNode.optString("is_episode").trim().equals("null") && !jsonChildNode.optString("is_episode").trim().matches("")) {
                                content.setIs_episode(jsonChildNode.optString("is_episode"));

                            }
                            if ((jsonChildNode.has("thirdparty_url")) && jsonChildNode.optString("thirdparty_url").trim() != null && !jsonChildNode.optString("thirdparty_url").trim().isEmpty() && !jsonChildNode.optString("thirdparty_url").trim().equals("null") && !jsonChildNode.optString("thirdparty_url").trim().matches("")) {
                                content.setThirdparty_url(jsonChildNode.optString("thirdparty_url"));

                            }
                           /* if ((jsonChildNode.has("episode_title")) && jsonChildNode.optString("episode_title").trim() != null && !jsonChildNode.optString("episode_title").trim().isEmpty() && !jsonChildNode.optString("episode_title").trim().equals("null") && !jsonChildNode.optString("episode_title").trim().matches("")) {
                                content.setEpisode_title(jsonChildNode.optString("episode_title"));
                            }else {
                                if ((jsonChildNode.has("name")) && jsonChildNode.optString("name").trim() != null && !jsonChildNode.optString("name").trim().isEmpty() && !jsonChildNode.optString("name").trim().equals("null") && !jsonChildNode.optString("name").trim().matches(""))
                                    content.setEpisode_title(jsonChildNode.optString("name"));
                            }*/

                            if ((jsonChildNode.has("display_name")) && jsonChildNode.optString("display_name").trim() != null && !jsonChildNode.optString("display_name").trim().isEmpty() && !jsonChildNode.optString("display_name").trim().equals("null") && !jsonChildNode.optString("display_name").trim().matches("")) {
                                content.setDisplay_name(jsonChildNode.optString("display_name"));

                            }
                            if ((jsonChildNode.has("embeddedUrl")) && jsonChildNode.optString("embeddedUrl").trim() != null && !jsonChildNode.optString("embeddedUrl").trim().isEmpty() && !jsonChildNode.optString("embeddedUrl").trim().equals("null") && !jsonChildNode.optString("embeddedUrl").trim().matches("")) {
                                content.setEmbeddedUrl(jsonChildNode.optString("embeddedUrl"));

                            }
                            if ((jsonChildNode.has("muvi_uniq_id")) && jsonChildNode.optString("muvi_uniq_id").trim() != null && !jsonChildNode.optString("muvi_uniq_id").trim().isEmpty() && !jsonChildNode.optString("muvi_uniq_id").trim().equals("null") && !jsonChildNode.optString("muvi_uniq_id").trim().matches("")) {
                                content.setMovie_id(jsonChildNode.optString("muvi_uniq_id"));

                            }

                            if ((jsonChildNode.has("movie_stream_uniq_id")) && jsonChildNode.optString("movie_stream_uniq_id").trim() != null && !jsonChildNode.optString("movie_stream_uniq_id").trim().isEmpty() && !jsonChildNode.optString("movie_stream_uniq_id").trim().equals("null") && !jsonChildNode.optString("movie_stream_uniq_id").trim().matches("")) {
                                content.setMovie_stream_uniq_id(jsonChildNode.optString("movie_stream_uniq_id"));

                            }
                            search_data_otputs.add(content);
                        } catch (Exception e) {
                            status = 0;
                            totalItems = 0;
                            message = "";
                        }
                    }
                } else {
                    responseStr = "0";
                    status = 0;
                    totalItems = 0;
                    message = "";
                }
            }
        } catch (Exception e) {
            status = 0;
            totalItems = 0;
            message = "";
        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onSearchDataPreexecute();

        status = 0;
        totalItems = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onSearchDataPostExecuteCompleted(search_data_otputs, status, totalItems, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onSearchDataPostExecuteCompleted(search_data_otputs, status, totalItems, message);
        }
    }


    @Override
    protected void onPostExecute(Void result) {
        listener.onSearchDataPostExecuteCompleted(search_data_otputs, status, totalItems, message);

    }

    //}
}

