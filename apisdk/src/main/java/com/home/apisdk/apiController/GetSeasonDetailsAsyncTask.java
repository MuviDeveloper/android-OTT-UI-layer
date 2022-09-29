
package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;

import com.home.apisdk.APIController;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.SeasonDetailsInputModel;
import com.home.apisdk.apiModel.SeasonDetailsOutputModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This Class gives all the information of season(s) associated with a multipart content such as season name, story, poster, Release Date etc.
 *
 * @author MUVI
 */
public class GetSeasonDetailsAsyncTask extends AsyncTask<SeasonDetailsInputModel, Void, Void> {

    private SeasonDetailsInputModel seasonDetailsInputModel;
    private String responseStr;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private GetSeasonDetailsListener listener;
    private Context context;
    private boolean isCacheEnabled;
    private APIController apiController;

    /**
     * Interface used to allow the caller of a GetSeasonDetailsAsynTask to run some code when get
     * responses.
     */

    public interface GetSeasonDetailsListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */


        void onGetSeasonDetailsPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param seasonDetailsOutputModel A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status                Response Code From The Server
         * @param message               On Success Message
         */

        void onGetSeasonDetailsPostExecuteCompleted(SeasonDetailsOutputModel seasonDetailsOutputModel, int status, String message);
    }


    /**
     * Constructor to initialise the private data members.
     *
     * @param seasonDetailsInputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                             For Example: to use this API we have to set following attributes:
     *                             setAuthToken(),setPermalink() etc.
     * @param listener             GetContentDetails Listener
     * @param context              android.content.Context
     */
    SeasonDetailsOutputModel seasonDetailsOutputModel = new SeasonDetailsOutputModel();

    public GetSeasonDetailsAsyncTask(SeasonDetailsInputModel seasonDetailsInputModel, GetSeasonDetailsListener listener, Context context, boolean isCacheEnabled, APIController dataController) {
        this.listener = listener;
        this.context = context;

        this.seasonDetailsInputModel = seasonDetailsInputModel;
        PACKAGE_NAME = context.getPackageName();
        this.isCacheEnabled = isCacheEnabled;
        this.apiController = dataController;
    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */
    @Override
    protected Void doInBackground(SeasonDetailsInputModel... params) {
        JSONObject parentJson = null;

        if (this.apiController.getAPIData(APIUrlConstant.seasonDetails, this.seasonDetailsInputModel.getPermalink()+"_season", isCacheEnabled) == null) {
            try {

                LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

                parameters.put(HeaderConstants.AUTH_TOKEN, this.seasonDetailsInputModel.getAuthToken());
             //   parameters.put(HeaderConstants.PERMALINK, this.seasonDetailsInputModel.getPermalink());
                parameters.put(HeaderConstants.COUNTRY, this.seasonDetailsInputModel.getCountry());
                parameters.put(HeaderConstants.LANG_CODE, this.seasonDetailsInputModel.getLanguageCode());

                // Execute HTTP Post Request
                try {
                    responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getSeasonDetils()+"?"+HeaderConstants.PERMALINK+"="+this.seasonDetailsInputModel.getPermalink());

                    if (responseStr != null) {
                        parentJson = new JSONObject(responseStr);
                        status = Integer.parseInt(parentJson.optString("code"));
                        message = parentJson.optString("msg");
                        if (status == 200) {
                            this.apiController.insertCachedDataToDB(APIUrlConstant.seasonDetails, this.seasonDetailsInputModel.getPermalink()+"_season", responseStr, Utils.getCurrentTimeStamp());
                        }
                    }

                } catch (Exception e) {
                    status = 0;
                }

            } catch (Exception e1) {

                responseStr = "0";
                status = 0;
                message = "Error";
            }
        } else {
            responseStr = this.apiController.getAPICacheData(APIUrlConstant.seasonDetails, this.seasonDetailsInputModel.getPermalink()+"_season");
            if (responseStr != null) {
                try {
                    parentJson = new JSONObject(responseStr);
                    status = Integer.parseInt(parentJson.optString("code"));
                    message = parentJson.optString("msg");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try {

            if (status == 200) {

                JSONObject myJson = null;
                myJson = parentJson.optJSONObject("movie");


                        if (Utils.checkForNull("season_number", myJson)) {
                            seasonDetailsOutputModel.setSeason_no(myJson.optString("season_number"));

                        } else {
                            seasonDetailsOutputModel.setSeason_no("");
                        }

                        if (Utils.checkForNull("content_types_id", myJson)) {
                            seasonDetailsOutputModel.setContent_types_id(myJson.optInt("content_types_id"));
                        } else {
                            seasonDetailsOutputModel.setContent_types_id(0);
                        }

                        if (Utils.checkForNull("parent_title", myJson)) {
                            seasonDetailsOutputModel.setParent_title(myJson.optString("parent_title"));

                        } else {
                            seasonDetailsOutputModel.setParent_title("");
                        }

                        if (Utils.checkForNull("parent_muvi_uniqe_id", myJson)) {
                            seasonDetailsOutputModel.setParent_muvi_unique_id(myJson.optString("parent_muvi_uniqe_id"));

                        } else {
                            seasonDetailsOutputModel.setParent_muvi_unique_id("");
                        }

                        if (Utils.checkForNull("name", myJson)) {
                            seasonDetailsOutputModel.setTitle(myJson.optString("name"));

                        } else {
                            seasonDetailsOutputModel.setTitle("");
                        }


                        if (Utils.checkForNull("permalink", myJson)) {
                            seasonDetailsOutputModel.setPermalink(myJson.optString("permalink"));

                        } else {
                            seasonDetailsOutputModel.setPermalink("");
                        }


                        if (Utils.checkForNull("description", myJson)) {
                            seasonDetailsOutputModel.setDescription(myJson.optString("description"));

                        } else {
                            seasonDetailsOutputModel.setDescription("");
                        }

                        if (Utils.checkForNull("release_date", myJson)) {
                            seasonDetailsOutputModel.setRelease_date(myJson.optString("release_date"));

                        } else {
                            seasonDetailsOutputModel.setRelease_date("");
                        }

                        if (Utils.checkForNull("muvi_uniq_id", myJson)) {
                            seasonDetailsOutputModel.setMuvi_uniq_id(myJson.optString("muvi_uniq_id"));

                        } else {
                            seasonDetailsOutputModel.setMuvi_uniq_id("");
                        }

                        if (Utils.checkForNull("has_trailer", myJson)) {
                            seasonDetailsOutputModel.setHas_trailer(myJson.optString("has_trailer"));

                        } else {
                            seasonDetailsOutputModel.setHas_trailer("");
                        }
                        if (Utils.checkForNull("censor_rating", myJson)) {
                            seasonDetailsOutputModel.setCensor_rating(myJson.optString("censor_rating"));

                        } else {
                            seasonDetailsOutputModel.setCensor_rating("");
                        }
                        if (Utils.checkForNull("mobile_poster", myJson)) {
                            seasonDetailsOutputModel.setMobile_poster(myJson.optString("mobile_poster"));

                        } else {
                            seasonDetailsOutputModel.setMobile_poster("");
                        }
                        if (Utils.checkForNull("poster_for_tv", myJson)) {
                            seasonDetailsOutputModel.setPoster_for_tv(myJson.optString("poster_for_tv"));

                        } else {
                            seasonDetailsOutputModel.setPoster_for_tv("");
                        }
                        if (Utils.checkForNull("mobile_banner", myJson)) {
                            seasonDetailsOutputModel.setMobile_banner(myJson.optString("mobile_banner"));

                        } else {
                            seasonDetailsOutputModel.setMobile_banner("");
                        }
                        if (Utils.checkForNull("tv_banner", myJson)) {
                            seasonDetailsOutputModel.setTv_banner(myJson.optString("tv_banner"));

                        } else {
                            seasonDetailsOutputModel.setTv_banner("");
                        }

                        if (Utils.checkForNull("id", myJson)) {
                            seasonDetailsOutputModel.setId(myJson.optString("id"));

                        } else {
                            seasonDetailsOutputModel.setId("");
                        }

                        if (myJson.has("genre")) {
                            JSONArray genreArray = myJson.optJSONArray("genre");

                            if(genreArray != null){
                                ArrayList<String> genreList = new ArrayList<>();
                                for (int j = 0; j < genreArray.length(); j++) {
                                    genreList.add(genreArray.getString(j).trim());
                                }

                                seasonDetailsOutputModel.setGenre(genreList);
                            }

                        }

                        JSONArray jsonArray = myJson.optJSONArray("all_season_info");
                        if(jsonArray.length() >0){

                            ArrayList<String> season_title_array = new ArrayList<>();
                            ArrayList<String> season_no_array = new ArrayList<>();
                            ArrayList<String> season_permalink_array = new ArrayList<>();


                            for(int i=0; i<jsonArray.length(); i++){
                                season_title_array.add(jsonArray.getJSONObject(i).optString("title"));
                                season_no_array.add(jsonArray.getJSONObject(i).optString("season_no"));
                                season_permalink_array.add(jsonArray.getJSONObject(i).optString("season_permalink"));
                            }

                            seasonDetailsOutputModel.setSeason_title_array(season_title_array);
                            seasonDetailsOutputModel.setSeason_no_array(season_no_array);
                            seasonDetailsOutputModel.setSeason_permalink_array(season_permalink_array);
                        }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetSeasonDetailsPreExecuteStarted();

        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGetSeasonDetailsPostExecuteCompleted(seasonDetailsOutputModel, status, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGetSeasonDetailsPostExecuteCompleted(seasonDetailsOutputModel, status, message);
        }


    }

    /**
     * @param result
     */
    @Override
    protected void onPostExecute(Void result) {
        listener.onGetSeasonDetailsPostExecuteCompleted(seasonDetailsOutputModel, status, message);

    }


}
