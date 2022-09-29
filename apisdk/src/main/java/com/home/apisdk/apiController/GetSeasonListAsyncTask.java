
package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;

import com.home.apisdk.APIController;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.SeasonListInputModel;
import com.home.apisdk.apiModel.SeasonListOutputModel;

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
public class GetSeasonListAsyncTask extends AsyncTask<SeasonListInputModel, Void, Void> {

    private SeasonListInputModel seasonListInputModel;
    private String responseStr;
    private String cacheresponseStr;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private GetSeasonListListener listener;
    private Context context;
    private boolean isCacheEnabled;
    private APIController apiController;

    /**
     * Interface used to allow the caller of a GetContentDetailsAsynTask to run some code when get
     * responses.
     */

    public interface GetSeasonListListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */


        void onGetSeasonListPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param arrayOfSeasonListOutputModel A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status                Response Code From The Server
         * @param message               On Success Message
         */

        void onGetSeasonListPostExecuteCompleted(ArrayList<SeasonListOutputModel> arrayOfSeasonListOutputModel, int status, String message);
    }

    ArrayList<SeasonListOutputModel> arrayOfSeasonListOutputModel = new ArrayList<>();

    /**
     * Constructor to initialise the private data members.
     *
     * @param seasonListInputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                             For Example: to use this API we have to set following attributes:
     *                             setAuthToken(),setPermalink() etc.
     * @param listener             GetContentDetails Listener
     * @param context              android.content.Context
     */

    public GetSeasonListAsyncTask(SeasonListInputModel seasonListInputModel, GetSeasonListListener listener, Context context, boolean isCacheEnabled, APIController dataController) {
        this.listener = listener;
        this.context = context;

        this.seasonListInputModel = seasonListInputModel;
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
    protected Void doInBackground(SeasonListInputModel... params) {

        JSONObject parentJson = null;
        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put(HeaderConstants.AUTH_TOKEN, this.seasonListInputModel.getAuthToken());
            //  parameters.put(HeaderConstants.PERMALINK, this.seasonListInputModel.getPermalink());
            parameters.put(HeaderConstants.COUNTRY, this.seasonListInputModel.getCountry());
            parameters.put(HeaderConstants.LANG_CODE, this.seasonListInputModel.getLanguageCode());
            parameters.put(HeaderConstants.KIDS_MODE, this.seasonListInputModel.getKids_mode());


            // Execute HTTP Post Request
            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getSeasonUrl()+"?"+HeaderConstants.PERMALINK+"="+this.seasonListInputModel.getPermalink());
                parseAPIData();

                if (responseStr != null) {
                    parentJson = new JSONObject(responseStr);
                    status = Integer.parseInt(parentJson.optString("code"));
                    message = parentJson.optString("msg");
                    if (status == 200) {
                        this.apiController.insertCachedDataToDB(APIUrlConstant.seasonList, this.seasonListInputModel.getPermalink() + "_season", responseStr, Utils.getCurrentTimeStamp());
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

        return null;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetSeasonListPreExecuteStarted();

        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGetSeasonListPostExecuteCompleted(arrayOfSeasonListOutputModel, status, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGetSeasonListPostExecuteCompleted(arrayOfSeasonListOutputModel, status, message);
        }

        if (this.apiController.getAPIData(APIUrlConstant.seasonList, this.seasonListInputModel.getPermalink() + "_season", isCacheEnabled) == null) {
        } else {
            responseStr = this.apiController.getAPICacheData(APIUrlConstant.seasonList, this.seasonListInputModel.getPermalink() + "_season");
            cacheresponseStr = responseStr;
            parseAPIData();
            listener.onGetSeasonListPostExecuteCompleted(arrayOfSeasonListOutputModel, status, message);
        }

    }

    /**
     * @param result
     */
    @Override
    protected void onPostExecute(Void result) {

        if (cacheresponseStr == null || responseStr == null) {
            listener.onGetSeasonListPostExecuteCompleted(arrayOfSeasonListOutputModel, status, message);
            return;
        }

        if (!cacheresponseStr.equals(responseStr)) {
            listener.onGetSeasonListPostExecuteCompleted(arrayOfSeasonListOutputModel, status, message);
        }

    }


    private void parseAPIData() {
        JSONObject parentJson = null;
        arrayOfSeasonListOutputModel.clear();

        try {

            if (responseStr != null) {
                try {
                    parentJson = new JSONObject(responseStr);
                    status = Integer.parseInt(parentJson.optString("code"));
                    message = parentJson.optString("msg");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (status == 200) {

                JSONArray jsonArray = parentJson.getJSONArray("data");
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject myJson = null;
                        myJson = jsonArray.getJSONObject(i);

                        SeasonListOutputModel seasonListOutputModel = new SeasonListOutputModel();

                        if (Utils.checkForNull("season_no", myJson)) {
                            seasonListOutputModel.setSeason_no(myJson.optString("season_no"));

                        } else {
                            seasonListOutputModel.setSeason_no("");
                        }

                        if (Utils.checkForNull("parent_title", myJson)) {
                            seasonListOutputModel.setParent_title(myJson.optString("parent_title"));

                        } else {
                            seasonListOutputModel.setParent_title("");
                        }

                        if (Utils.checkForNull("parent_muvi_uniqe_id", myJson)) {
                            seasonListOutputModel.setParent_muvi_unique_id(myJson.optString("parent_muvi_uniqe_id"));

                        } else {
                            seasonListOutputModel.setParent_muvi_unique_id("");
                        }

                        if (Utils.checkForNull("title", myJson)) {
                            seasonListOutputModel.setTitle(myJson.optString("title"));

                        } else {
                            seasonListOutputModel.setTitle("");
                        }


                        if (Utils.checkForNull("permalink", myJson)) {
                            seasonListOutputModel.setPermalink(myJson.optString("permalink"));

                        } else {
                            seasonListOutputModel.setPermalink("");
                        }


                        if (Utils.checkForNull("description", myJson)) {
                            seasonListOutputModel.setDescription(myJson.optString("description"));

                        } else {
                            seasonListOutputModel.setDescription("");
                        }

                        if (Utils.checkForNull("release_date", myJson)) {
                            seasonListOutputModel.setRelease_date(myJson.optString("release_date"));

                        } else {
                            seasonListOutputModel.setRelease_date("");
                        }

                        if (Utils.checkForNull("muvi_uniq_id", myJson)) {
                            seasonListOutputModel.setMuvi_uniq_id(myJson.optString("muvi_uniq_id"));

                        } else {
                            seasonListOutputModel.setMuvi_uniq_id("");
                        }

                        if (Utils.checkForNull("has_trailer", myJson)) {
                            seasonListOutputModel.setHas_trailer(myJson.optString("has_trailer"));

                        } else {
                            seasonListOutputModel.setHas_trailer("");
                        }
                        if (Utils.checkForNull("censor_rating", myJson)) {
                            seasonListOutputModel.setCensor_rating(myJson.optString("censor_rating"));

                        } else {
                            seasonListOutputModel.setCensor_rating("");
                        }
                        if (Utils.checkForNull("mobile_poster", myJson)) {
                            seasonListOutputModel.setMobile_poster(myJson.optString("mobile_poster"));

                        } else {
                            seasonListOutputModel.setMobile_poster("");
                        }
                        if (Utils.checkForNull("poster_for_tv", myJson)) {
                            seasonListOutputModel.setPoster_for_tv(myJson.optString("poster_for_tv"));

                        } else {
                            seasonListOutputModel.setPoster_for_tv("");
                        }
                        if (Utils.checkForNull("mobile_banner", myJson)) {
                            seasonListOutputModel.setMobile_banner(myJson.optString("mobile_banner"));

                        } else {
                            seasonListOutputModel.setMobile_banner("");
                        }
                        if (Utils.checkForNull("tv_banner", myJson)) {
                            seasonListOutputModel.setTv_banner(myJson.optString("tv_banner"));

                        } else {
                            seasonListOutputModel.setTv_banner("");
                        }

                        if (Utils.checkForNull("id", myJson)) {
                            seasonListOutputModel.setId(myJson.optString("id"));

                        } else {
                            seasonListOutputModel.setId("");
                        }

                        if (myJson.has("genre")) {
                            JSONArray genreArray = myJson.optJSONArray("genre");
                            if (genreArray != null) {
                                ArrayList<String> genreList = new ArrayList<>();
                                for (int j = 0; j < genreArray.length(); j++) {
                                    genreList.add(genreArray.getString(j).trim());
                                }

                                seasonListOutputModel.setGenre(genreList);
                            }

                        }

                        arrayOfSeasonListOutputModel.add(seasonListOutputModel);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
