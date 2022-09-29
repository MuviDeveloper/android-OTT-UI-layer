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
import com.home.apisdk.apiModel.AppHomeFeatureInputModel;
import com.home.apisdk.apiModel.AppHomeFeatureOutputModel;
import com.home.apisdk.apiModel.HomeFeaturePageBannerModel;
import com.home.apisdk.apiModel.HomeFeaturePageSectionDetailsModel;
import com.home.apisdk.apiModel.HomeFeaturePageSectionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This Class gives a short note about the company/organisation to its users.
 *
 * @author MUVI
 */

public class GetAppHomeFeatureAsyncTask extends AsyncTask<AppHomeFeatureInputModel, Void, Void> {



    private AppHomeFeatureInputModel appHomeFeatureInputModel;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private String responseStr = "";
    private String cacheresponseStr ="";
    private AppHomeFeature listener;
    private Context context;
    private int code = 0;
    private int total_section = 0;
    private boolean isCacheEnabled;
    private APIController apiController;

    /**
     * Interface used to allow the caller of a AboutUsAsync to run some code when get
     * responses.
     */

    public interface AppHomeFeature {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */
        void AppHomeFeaturePreExecute();

        /**
         * @param appHomeFeatureOutputModel
         * @param status
         */
        void AppHomeFeaturePostExecute(AppHomeFeatureOutputModel appHomeFeatureOutputModel, int status);
    }

    AppHomeFeatureOutputModel appHomeFeatureOutputModel = new AppHomeFeatureOutputModel();
    ArrayList<HomeFeaturePageSectionModel> homePageSectionModelArrayList = new ArrayList<HomeFeaturePageSectionModel>();
    ArrayList<HomeFeaturePageBannerModel> homePageBannerModelArrayList = new ArrayList<HomeFeaturePageBannerModel>();


    /**
     * Constructor to initialise the private data members.
     *
     * @param appHomeFeatureInputModel
     * @param listener
     * @param context
     */

    public GetAppHomeFeatureAsyncTask(AppHomeFeatureInputModel appHomeFeatureInputModel, AppHomeFeature listener, Context context, boolean isCacheEnabled, APIController controller) {
        this.listener = listener;
        this.context = context;
        this.isCacheEnabled = isCacheEnabled;
        this.apiController = controller;
        this.appHomeFeatureInputModel = appHomeFeatureInputModel;
        PACKAGE_NAME = context.getPackageName();
    }

    /**
     * Background thread to execute.
     *
     * @return Null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException,JSONException
     */

    @Override
    protected Void doInBackground(AppHomeFeatureInputModel... params) {
        responseStr = getAPPHomeData();
        parseAPIData();

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.AppHomeFeaturePreExecute();
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.AppHomeFeaturePostExecute(appHomeFeatureOutputModel, code);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.AppHomeFeaturePostExecute(appHomeFeatureOutputModel, code);
            return;
        }


        if (this.apiController.getAPIData(APIUrlConstant.GET_APP_HOME_FEATURE, "", isCacheEnabled) == null && (Integer.parseInt(this.appHomeFeatureInputModel.getGetFeatureSectionOffset()) == 1)) {
            //network call
//            responseStr = getAPPHomeData();

        } else if (Integer.parseInt(this.appHomeFeatureInputModel.getGetFeatureSectionOffset()) > 1) {
            // network call
//            responseStr = getAPPHomeData();

        } else {

            responseStr = this.apiController.getAPICacheData(APIUrlConstant.GET_APP_HOME_FEATURE, "");
            cacheresponseStr = responseStr;
            parseAPIData();
            appHomeFeatureOutputModel.setCacheData(true);
            listener.AppHomeFeaturePostExecute(appHomeFeatureOutputModel, code);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(cacheresponseStr.equals(responseStr))
            appHomeFeatureOutputModel.setGetNextSectionData(true);

        appHomeFeatureOutputModel.setCacheData(false);
        listener.AppHomeFeaturePostExecute(appHomeFeatureOutputModel, code);


    }

    public String getAPPHomeData() {
        try {

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

            parameters.put(HeaderConstants.AUTH_TOKEN, this.appHomeFeatureInputModel.getAuthToken());
            parameters.put(HeaderConstants.FEATURE_SECTION_LIMIT, this.appHomeFeatureInputModel.getFeatureSectionLimit());
            parameters.put(HeaderConstants.FEATURE_SECTION_OFFSET, this.appHomeFeatureInputModel.getGetFeatureSectionOffset());
            parameters.put(HeaderConstants.USER_ID, this.appHomeFeatureInputModel.getUserId());
            parameters.put(HeaderConstants.LANG_CODE, this.appHomeFeatureInputModel.getLang_code());
            parameters.put(HeaderConstants.FEATURE_SECTION_CONTENT_LIMIT, this.appHomeFeatureInputModel.getFeatureSectionContentLimit());
            parameters.put(HeaderConstants.FEATURE_SECTION_CONTENT_OFFSET, this.appHomeFeatureInputModel.getGetFeatureSectionContentOffset());
            parameters.put(HeaderConstants.PLATFORM_TYPE, this.appHomeFeatureInputModel.getPlatform_type());
            parameters.put(HeaderConstants.COUNTRY, this.appHomeFeatureInputModel.getCountryCode());
            parameters.put(HeaderConstants.STATE, this.appHomeFeatureInputModel.getState());
            parameters.put(HeaderConstants.CITY, this.appHomeFeatureInputModel.getCity());
            parameters.put(HeaderConstants.RESULT_TYPE, this.appHomeFeatureInputModel.getResult_type());
            parameters.put(HeaderConstants.SEASON_INFO, this.appHomeFeatureInputModel.getSeason_info());
            parameters.put(HeaderConstants.KIDS_MODE, this.appHomeFeatureInputModel.getKids_mode());


            appHomeFeatureOutputModel.setOffset(Integer.parseInt(this.appHomeFeatureInputModel.getGetFeatureSectionOffset()));


            //Execute HTTP request

            responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getGetAppHomeFeature());
            JSONObject myJson = null;


            if (responseStr != null) {
                try {
                    myJson = new JSONObject(responseStr);
                    code = Integer.parseInt(myJson.optString("code"));
                    if (isCacheEnabled && code == 200 && Integer.parseInt(this.appHomeFeatureInputModel.getGetFeatureSectionOffset()) == 1) {
                        this.apiController.insertCachedDataToDB(APIUrlConstant.GET_APP_HOME_FEATURE, "", responseStr, Utils.getCurrentTimeStamp());
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseStr;
    }

    public void parseAPIData() {
        homePageSectionModelArrayList.clear();
        homePageBannerModelArrayList.clear();

        JSONObject myJson = null;

        if (responseStr != null) {

            try {
                myJson = new JSONObject(responseStr);
                code = Integer.parseInt(myJson.optString("code"));
                try {
                    total_section = Integer.parseInt(myJson.optString("section_count"));
                } catch (Exception e) {
                    total_section = 0;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            if (code == 200) {
                try {


                    HomeFeaturePageBannerModel homeFeaturePageBannerModel = null;
                    if (myJson.has("banner_section_list")) {
                        JSONArray jsonMainNode = myJson.getJSONArray("banner_section_list");
                        int lengthJsonArr = jsonMainNode.length();
                        if (lengthJsonArr > 0) {
                            for (int i = 0; i < lengthJsonArr; i++) {

                                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                                String image_path = jsonChildNode.optString("image_path");
                                String banner_url = jsonChildNode.optString("banner_url");
                                String banner_permalink = jsonChildNode.optString("banner_permalink");
                                String banner_type = jsonChildNode.optString("banner_type");
                                String other_sub_type = jsonChildNode.optString("other_sub_type");
                                String web_link = jsonChildNode.optString("web_link");
                                String banner_text = jsonChildNode.optString("banner_text");
                                String content_types_id = jsonChildNode.optString("content_types_id");
                                String title = jsonChildNode.optString("title");
                                String button_text = jsonChildNode.optString("button_text");
                                String button_type = jsonChildNode.optString("button_type");
                                String is_converted = jsonChildNode.optString("is_converted");
                                String video_on_banner = jsonChildNode.optString("video_on_banner");
                                String feed_url = jsonChildNode.optString("feed_url");

                                String parent_title = jsonChildNode.optString("parent_title");
                                String story = jsonChildNode.optString("story");
                                String muvi_uniq_id = jsonChildNode.optString("muvi_uniq_id");
                                String movie_stream_uniq_id = jsonChildNode.optString("movie_stream_uniq_id");
                                String season_no = jsonChildNode.optString("season_no");
                                String episode_no = jsonChildNode.optString("episode_no");
                                String video_duration = jsonChildNode.optString("video_duration");
                                String poster_url = jsonChildNode.optString("poster_url");
                                String is_livestream_enabled = jsonChildNode.optString("is_livestream_enabled");

                                homeFeaturePageBannerModel = new HomeFeaturePageBannerModel();
                                homeFeaturePageBannerModel.setImage_path(image_path);
                                homeFeaturePageBannerModel.setBanner_url(banner_url);
                                homeFeaturePageBannerModel.setBanner_permalink(banner_permalink);
                                homeFeaturePageBannerModel.setBanner_type(banner_type);
                                homeFeaturePageBannerModel.setOther_sub_type(other_sub_type);
                                homeFeaturePageBannerModel.setWeb_link(web_link);
                                homeFeaturePageBannerModel.setBanner_text(banner_text);
                                homeFeaturePageBannerModel.setContent_types_id(content_types_id);
                                homeFeaturePageBannerModel.setTitle(title);
                                homeFeaturePageBannerModel.setButton_text(button_text);
                                homeFeaturePageBannerModel.setButton_type(button_type);
                                homeFeaturePageBannerModel.setIs_converted(is_converted);
                                homeFeaturePageBannerModel.setVideo_on_banner(video_on_banner);
                                homeFeaturePageBannerModel.setFeed_url(feed_url);

                                homeFeaturePageBannerModel.setParent_title(parent_title);
                                homeFeaturePageBannerModel.setStory(story);
                                homeFeaturePageBannerModel.setMuvi_uniq_id(muvi_uniq_id);
                                homeFeaturePageBannerModel.setMovie_stream_uniq_id(movie_stream_uniq_id);
                                homeFeaturePageBannerModel.setSeason_no(season_no);
                                homeFeaturePageBannerModel.setEpisode_no(episode_no);
                                homeFeaturePageBannerModel.setVideo_duration(video_duration);
                                homeFeaturePageBannerModel.setPoster_url(poster_url);
                                homeFeaturePageBannerModel.setIs_livestream_enabled(is_livestream_enabled);

                                homePageBannerModelArrayList.add(homeFeaturePageBannerModel);
                            }
                        } else {
                            homeFeaturePageBannerModel = new HomeFeaturePageBannerModel();
                            homeFeaturePageBannerModel.setImage_path("https://d2gx0xinochgze.cloudfront.net/public/no-image-a.png");
                            homePageBannerModelArrayList.add(homeFeaturePageBannerModel);
                        }
                    }


                    if (myJson.has("section_name")) {
                        JSONArray sectionName = myJson.getJSONArray("section_name");
                        HomeFeaturePageSectionModel homeFeaturePageSectionModel;

                        if (sectionName.length() > 0) {
                            for (int j = 0; j < sectionName.length(); j++) {
                                homeFeaturePageSectionModel = new HomeFeaturePageSectionModel();
                                JSONObject jsonChildNode = sectionName.getJSONObject(j);

                                homeFeaturePageSectionModel.setStudio_id(jsonChildNode.optString("studio_id"));
                                homeFeaturePageSectionModel.setLanguage_id(jsonChildNode.optString("language_id"));
                                homeFeaturePageSectionModel.setSection_id(jsonChildNode.optString("section_id"));
                                homeFeaturePageSectionModel.setSection_type(jsonChildNode.optString("section_type"));
                                homeFeaturePageSectionModel.setTitle(jsonChildNode.optString("title"));
                                homeFeaturePageSectionModel.setTotal(jsonChildNode.optString("total"));


                                JSONArray section_item_list = jsonChildNode.optJSONArray("data");
                                ArrayList<HomeFeaturePageSectionDetailsModel> arrayList = new ArrayList<>();

                                if (section_item_list.length() > 0) {
                                    for (int k = 0; k < section_item_list.length(); k++) {

                                        HomeFeaturePageSectionDetailsModel homeFeaturePageSectionDetailsModel = new HomeFeaturePageSectionDetailsModel();
                                        JSONObject object = section_item_list.getJSONObject(k);

                                        homeFeaturePageSectionDetailsModel.setIs_episode(object.optString("is_episode"));
                                        homeFeaturePageSectionDetailsModel.setMovie_stream_uniq_id(object.optString("movie_stream_uniq_id"));
                                        homeFeaturePageSectionDetailsModel.setMovie_id(object.optString("movie_id"));
                                        homeFeaturePageSectionDetailsModel.setMovie_stream_id(object.optString("movie_stream_id"));
                                        homeFeaturePageSectionDetailsModel.setMuvi_uniq_id(object.optString("muvi_uniq_id"));

                                        homeFeaturePageSectionDetailsModel.setPpv_plan_id(object.optString("ppv_plan_id"));
                                        homeFeaturePageSectionDetailsModel.setPermalink(object.optString("permalink"));

                                        homeFeaturePageSectionDetailsModel.setParent_poster_for_mobile_apps(object.optString("parent_poster_for_mobile_apps"));
                                        homeFeaturePageSectionDetailsModel.setParent_title(object.optString("parent_title"));
                                        homeFeaturePageSectionDetailsModel.setVideo_duration(object.optString("video_duration"));

                                        try {
                                            homeFeaturePageSectionDetailsModel.setSeason_permalink(object.optString("season_permalink"));
                                        } catch (Exception e) {
                                            e.toString();
                                        }

                                        homeFeaturePageSectionDetailsModel.setName(object.optString("name"));
                                        homeFeaturePageSectionDetailsModel.setStory(object.optString("story"));
                                        homeFeaturePageSectionDetailsModel.setContent_types_id(object.optString("content_types_id"));

                                        homeFeaturePageSectionDetailsModel.setIs_converted(object.optString("is_converted"));
                                        homeFeaturePageSectionDetailsModel.setIs_media_published(object.optString("is_media_published"));
                                        homeFeaturePageSectionDetailsModel.setPoster_url(object.optString("poster_url"));
                                        homeFeaturePageSectionDetailsModel.setEmbeddedUrl(object.optString("embeddedUrl"));
                                        homeFeaturePageSectionDetailsModel.setFreeContnet(object.optString("isFreeContent"));
                                        homeFeaturePageSectionDetailsModel.setBanner(object.optString("banner"));
                                        homeFeaturePageSectionDetailsModel.setEpisodeNo(object.optString("episode_no"));
                                        homeFeaturePageSectionDetailsModel.setSeasonNo(object.optString("season_no"));
                                        homeFeaturePageSectionDetailsModel.setTotalSeasons(object.optString("total_season"));
                                        homeFeaturePageSectionDetailsModel.setIs_livestream_enabled(object.optString("is_livestream_enabled"));



                                        if (object.has("is_play_list")) {
                                            homeFeaturePageSectionDetailsModel.setIs_play_list(object.getString("is_play_list"));
                                        } else {
                                            homeFeaturePageSectionDetailsModel.setIs_play_list("0");
                                        }
                                        if (object.has("play_list_type")) {
                                            homeFeaturePageSectionDetailsModel.setPlay_list_type(object.getString("play_list_type"));
                                        } else {
                                            homeFeaturePageSectionDetailsModel.setPlay_list_type("0");
                                        }
                                        if (object.has("list_id")) {
                                            homeFeaturePageSectionDetailsModel.setList_id(object.getString("list_id"));
                                        }


                                        arrayList.add(homeFeaturePageSectionDetailsModel);
                                    }
                                }

                                homeFeaturePageSectionModel.setHomeFeaturePageSectionDetailsModel(arrayList);
                                homePageSectionModelArrayList.add(homeFeaturePageSectionModel);
                            }
                        }

                    }


                    appHomeFeatureOutputModel.setHomePageBannerModelArrayList(homePageBannerModelArrayList);
                    appHomeFeatureOutputModel.setHomePageSectionModelArrayList(homePageSectionModelArrayList);
                    appHomeFeatureOutputModel.setTotal_section(total_section);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}