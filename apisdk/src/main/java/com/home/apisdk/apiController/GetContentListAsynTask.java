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
import com.home.apisdk.apiModel.ContentListInput;
import com.home.apisdk.apiModel.ContentListOutput;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This Class is use to show the content list to the users.
 * Among the movies they can select their favorite movies/series and can check all the details about that particular movie/series.
 *
 * @author MUVI
 */
public class GetContentListAsynTask extends AsyncTask<ContentListInput, Void, Void> {

    private ContentListInput contentListInput;
    private String responseStr;
    private String cacheresponseStr;
    private int status;
    private int totalItems;
    private String message, category_id, isFollowed;
    private String PACKAGE_NAME;
    private GetContentListListener listener;
    private Context context;
    private boolean isCacheEnabled;
    private APIController apiController;



    /**
     * Interface used to allow the caller of a GetContentListAsynTask to run some code when get
     * responses.
     */

    public interface GetContentListListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */


        void onGetContentListPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param contentListOutputArray A Model Class which which contain responses. To get that responses we need to call the respective getter methods.
         * @param status                 Response Code From the Server
         * @param totalItems             Total Item Present
         * @param message                On Success Message
         */

        void onGetContentListPostExecuteCompleted(ArrayList<ContentListOutput> contentListOutputArray, int status, int totalItems, String message,
                                                  String category_id, String isFollowed, boolean isCacheData, String offset);
    }


    ArrayList<ContentListOutput> contentListOutput = new ArrayList<ContentListOutput>();

    /**
     * Constructor to initialise the private data members.
     *
     * @param contentListInput A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                         For Example: to use this API we have to set following attributes:
     *                         setAuthToken(),setOffset() etc.
     * @param listener         GetContentList Listener
     * @param context          android.content.Context
     */

    public GetContentListAsynTask(ContentListInput contentListInput, GetContentListListener listener, Context context, boolean isCacheEnabled, APIController apiController) {
        this.listener = listener;
        this.context = context;
        this.contentListInput = contentListInput;
        PACKAGE_NAME = context.getPackageName();
        this.isCacheEnabled = isCacheEnabled;
        this.apiController = apiController;
    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException
     */

    @Override
    protected Void doInBackground(ContentListInput... params) {
        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put(HeaderConstants.AUTH_TOKEN, this.contentListInput.getAuthToken());
            parameters.put(HeaderConstants.LIMIT, this.contentListInput.getLimit());
            parameters.put(HeaderConstants.OFFSET, this.contentListInput.getOffset());
            parameters.put(HeaderConstants.COUNTRY, this.contentListInput.getCountry());
            parameters.put(HeaderConstants.STATE, this.contentListInput.getState());
            parameters.put(HeaderConstants.CITY, this.contentListInput.getCity());
            parameters.put(HeaderConstants.LANG_CODE, this.contentListInput.getLanguage());
            parameters.put(HeaderConstants.ORDER_BY, this.contentListInput.getOrderby());
            parameters.put(HeaderConstants.USER_ID, this.contentListInput.getUserId());
            parameters.put(HeaderConstants.KIDS_MODE, this.contentListInput.getKids_mode());

            responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getGetContentListUrl()+"?permalink="+this.contentListInput.getPermalink());
            parseAPIData();


        } catch (Exception e) {
            status = 0;
            totalItems = 0;
            message = "";
        }
        return null;
    }

    public void parseAPIData() {

        try {

            contentListOutput.clear();

            int offset = Integer.parseInt(this.contentListInput.getOffset());
            JSONObject myJson = null;
            myJson = new JSONObject(responseStr);
            status = Integer.parseInt(myJson.optString("status"));

            message = myJson.optString("msg");
            category_id = myJson.optString("category_id");
            isFollowed = myJson.optString("isFollowed");

            if (status == 200 && offset == 1) {
                this.apiController.insertCachedDataToDB(APIUrlConstant.GET_CONTENT_LIST_URL, this.contentListInput.getPermalink(), responseStr, Utils.getCurrentTimeStamp());
            }
            try {
                totalItems = Integer.parseInt(myJson.optString("item_count", "0"));
            } catch (Exception e) {
                totalItems = 0;
            }

            if (status == 200) {

                JSONArray jsonMainNode = myJson.getJSONArray("movieList");

                int lengthJsonArr = jsonMainNode.length();

                    for (int i = 0; i < lengthJsonArr; i++) {
                        JSONObject jsonChildNode;
                        try {
                            jsonChildNode = jsonMainNode.getJSONObject(i);
                            ContentListOutput content = new ContentListOutput();

                            if ((jsonChildNode.has("genre")) && jsonChildNode.optString("genre").trim() != null && !jsonChildNode.optString("genre").trim().isEmpty() && !jsonChildNode.optString("genre").trim().equals("null") && !jsonChildNode.optString("genre").trim().matches("")) {
                                content.setGenre(jsonChildNode.optString("genre"));

                            }
                            if ((jsonChildNode.has("name")) && jsonChildNode.optString("name").trim() != null && !jsonChildNode.optString("name").trim().isEmpty() && !jsonChildNode.optString("name").trim().equals("null") && !jsonChildNode.optString("name").trim().matches("")) {
                                content.setName(jsonChildNode.optString("name"));
                            }
                            if ((jsonChildNode.has("poster_url")) && jsonChildNode.optString("poster_url").trim() != null && !jsonChildNode.optString("poster_url").trim().isEmpty() && !jsonChildNode.optString("poster_url").trim().equals("null") && !jsonChildNode.optString("poster_url").trim().matches("")) {
                                content.setPosterUrl(jsonChildNode.optString("poster_url"));

                            }
                            if ((jsonChildNode.has("permalink")) && jsonChildNode.optString("permalink").trim() != null && !jsonChildNode.optString("permalink").trim().isEmpty() && !jsonChildNode.optString("permalink").trim().equals("null") && !jsonChildNode.optString("permalink").trim().matches("")) {
                                content.setPermalink(jsonChildNode.optString("permalink"));
                            }
                            if ((jsonChildNode.has("content_types_id")) && jsonChildNode.optString("content_types_id").trim() != null && !jsonChildNode.optString("content_types_id").trim().isEmpty() && !jsonChildNode.optString("content_types_id").trim().equals("null") && !jsonChildNode.optString("content_types_id").trim().matches("")) {
                                content.setContentTypesId(jsonChildNode.optString("content_types_id"));
                            }

                            /*Author:Subham
                             * Desc:video_duration added for nike customer
                             * */

                            if ((jsonChildNode.has("video_duration")) && jsonChildNode.optString("video_duration").trim() != null && !jsonChildNode.optString("video_duration").trim().isEmpty() && !jsonChildNode.optString("video_duration").trim().equals("null") && !jsonChildNode.optString("video_duration").trim().matches("")) {
                                content.setVideo_duration(jsonChildNode.optString("video_duration"));

                            }
                            //videoTypeIdStr = "1";

                            try {
                                if ((jsonChildNode.has("is_converted")) && jsonChildNode.optString("is_converted").trim() != null && !jsonChildNode.optString("is_converted").trim().isEmpty() && !jsonChildNode.optString("is_converted").trim().equals("null") && !jsonChildNode.optString("is_converted").trim().matches("")) {
                                    content.setIsConverted(Integer.parseInt(jsonChildNode.optString("is_converted")));

                                } else {
                                    content.setIsConverted(0);
                                }
                            } catch (Exception e) {
                                content.setIsConverted(0);
                            }

                            try {
                                if ((jsonChildNode.has("is_advance")) && jsonChildNode.optString("is_advance").trim() != null && !jsonChildNode.optString("is_advance").trim().isEmpty() && !jsonChildNode.optString("is_advance").trim().equals("null") && !jsonChildNode.optString("is_advance").trim().matches("")) {
                                    content.setIsAPV(Integer.parseInt(jsonChildNode.optString("is_advance")));

                                } else {
                                    content.setIsAPV(0);
                                }
                            } catch (Exception e) {
                                content.setIsAPV(0);
                            }

                            try {
                                if ((jsonChildNode.has("is_ppv")) && jsonChildNode.optString("is_ppv").trim() != null && !jsonChildNode.optString("is_ppv").trim().isEmpty() && !jsonChildNode.optString("is_ppv").trim().equals("null") && !jsonChildNode.optString("is_ppv").trim().matches("")) {
                                    content.setIsPPV(Integer.parseInt(jsonChildNode.optString("is_ppv")));

                                } else {
                                    content.setIsPPV(0);
                                }
                            } catch (Exception e) {
                                content.setIsPPV(0);
                            }

                            if ((jsonChildNode.has("is_episode")) && jsonChildNode.optString("is_episode").trim() != null && !jsonChildNode.optString("is_episode").trim().isEmpty() && !jsonChildNode.optString("is_episode").trim().equals("null") && !jsonChildNode.optString("is_episode").trim().matches("")) {
                                content.setIsEpisodeStr(jsonChildNode.optString("is_episode"));

                            }
                            // added for UGC
                            if ((jsonChildNode.has("muvi_uniq_id")) && jsonChildNode.optString("muvi_uniq_id").trim() != null && !jsonChildNode.optString("muvi_uniq_id").trim().isEmpty() && !jsonChildNode.optString("muvi_uniq_id").trim().equals("null") && !jsonChildNode.optString("muvi_uniq_id").trim().matches("")) {
                                content.setMovieUniqueid(jsonChildNode.optString("muvi_uniq_id"));

                            }
                            if ((jsonChildNode.has("story")) && jsonChildNode.optString("story").trim() != null && !jsonChildNode.optString("story").trim().isEmpty() && !jsonChildNode.optString("story").trim().equals("null") && !jsonChildNode.optString("story").trim().matches("")) {
                                content.setStory(jsonChildNode.optString("story"));

                            }

                            contentListOutput.add(content);
                        } catch (Exception e) {
                            status = 0;
                            totalItems = 0;
                            message = "";
                        }
                    }

            }

        } catch (Exception e) {
            e.toString();
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetContentListPreExecuteStarted();
        responseStr = "0";
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGetContentListPostExecuteCompleted(contentListOutput, status, totalItems, message, category_id, isFollowed, false, this.contentListInput.getOffset());
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGetContentListPostExecuteCompleted(contentListOutput, status, totalItems, message, category_id, isFollowed, false, this.contentListInput.getOffset());
        }

    }


    @Override
    protected void onPostExecute(Void result) {

        if (cacheresponseStr == null || responseStr == null) {
            listener.onGetContentListPostExecuteCompleted(contentListOutput, status, totalItems, message, category_id, isFollowed, false, this.contentListInput.getOffset());
            return;
        }

        if (!cacheresponseStr.equals(responseStr)) {
            listener.onGetContentListPostExecuteCompleted(contentListOutput, status, totalItems, message, category_id, isFollowed, false, this.contentListInput.getOffset());
        }
    }

}
