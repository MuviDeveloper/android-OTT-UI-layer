package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.LoadFilterVideoInput;
import com.home.apisdk.apiModel.LoadFilterVideoOutput;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by MUVI on 11/8/2017.
 */

public class LoadFilterVideoAsync extends AsyncTask<LoadFilterVideoInput,Void,Void>{
    private LoadFilterVideoInput loadFilterVideoInput;
    private String responseStr;
    private int status;
    private int totalItems;
    private String message,category_id,isFollowed;
    private String PACKAGE_NAME;
    private LoadFilterVideoAsync.LoadFilterVideoListner listener;
    private Context context;
    public static ArrayList<String> genreArray;


    public interface LoadFilterVideoListner{

        void onLoadFilterVideoPreExecuteStarted();
        void onLoadFilterVideoPostExecuteCompleted(ArrayList<LoadFilterVideoOutput> loadFilterVideoOutputArrayList, int status, int totalItems, String message , String category_id, String isFollowed);

    }

    ArrayList<LoadFilterVideoOutput> loadFilterVideoOutput = new ArrayList<LoadFilterVideoOutput>();


    public LoadFilterVideoAsync(LoadFilterVideoInput loadFilterVideoInput, LoadFilterVideoAsync.LoadFilterVideoListner listener, Context context) {
        this.listener = listener;
        this.context = context;


        this.loadFilterVideoInput = loadFilterVideoInput;
        PACKAGE_NAME = context.getPackageName();


    }
    @Override
    protected Void doInBackground(LoadFilterVideoInput... params) {
        String urlRouteList = APIUrlConstant.getGetContentListUrl();
        if (loadFilterVideoInput.getGenreArray() != null && loadFilterVideoInput.getGenreArray().size() > 0) {
            String[] mStringArray = new String[loadFilterVideoInput.getGenreArray().size()];
            mStringArray = loadFilterVideoInput.getGenreArray().toArray(mStringArray);
            for (int i = 0; i < mStringArray.length; i++) {
                if (mStringArray.length <= 1) {
                    urlRouteList = (urlRouteList + "?genre[]=" + mStringArray[i].trim()).replace(" ", "%20");

                } else {
                    if (i == 0) {
                        urlRouteList = (urlRouteList + "?genre[]=" + mStringArray[i].trim()).replace(" ", "%20");
                    } else {
                        urlRouteList = (urlRouteList + "&genre[]=" + mStringArray[i].trim()).replace(" ", "%20");

                    }

                }
            }
        }


        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put(HeaderConstants.AUTH_TOKEN, this.loadFilterVideoInput.getAuthToken());
            parameters.put(HeaderConstants.PERMALINK, this.loadFilterVideoInput.getPermalink());
            parameters.put(HeaderConstants.LIMIT, loadFilterVideoInput.getLimit());
            parameters.put(HeaderConstants.OFFSET, this.loadFilterVideoInput.getOffset());
            parameters.put(HeaderConstants.COUNTRY, this.loadFilterVideoInput.getCountry());
            parameters.put(HeaderConstants.STATE, this.loadFilterVideoInput.getState());
            parameters.put(HeaderConstants.CITY, this.loadFilterVideoInput.getCity());
            parameters.put(HeaderConstants.LANG_CODE, this.loadFilterVideoInput.getLanguage());
            parameters.put(HeaderConstants.ORDER_BY, this.loadFilterVideoInput.getOrderby());
            parameters.put(HeaderConstants.USER_ID, this.loadFilterVideoInput.getUserId());
            parameters.put(HeaderConstants.KIDS_MODE, this.loadFilterVideoInput.getKids_mode());

            // Execute HTTP Post Request
            try {

                responseStr = Utils.requester.addHeaderParams(parameters, urlRouteList);

            }  catch (Exception e) {
                status = 0;
                totalItems = 0;
                message = "";
            }

            JSONObject myJson = null;
            if (responseStr != null) {
                myJson = new JSONObject(responseStr);
                status = Integer.parseInt(myJson.optString("status"));
                message = myJson.optString("msg");
                category_id = myJson.optString("category_id");
                isFollowed = myJson.optString("isFollowed");

                try {
                    totalItems = Integer.parseInt(myJson.optString("item_count"));
                }catch (Exception e){
                    totalItems=0;
                }

            }


            if (status == 200) {
                JSONArray jsonMainNode = myJson.getJSONArray("movieList");
                int lengthJsonArr = jsonMainNode.length();
                for (int i = 0; i < lengthJsonArr; i++) {
                    JSONObject jsonChildNode;
                    try {
                        jsonChildNode = jsonMainNode.getJSONObject(i);
                        LoadFilterVideoOutput filterVideo = new LoadFilterVideoOutput();

                        if ((jsonChildNode.has("genre")) && jsonChildNode.optString("genre").trim() != null && !jsonChildNode.optString("genre").trim().isEmpty() && !jsonChildNode.optString("genre").trim().equals("null") && !jsonChildNode.optString("genre").trim().matches("")) {
                            filterVideo.setGenre(jsonChildNode.optString("genre"));

                        }
                        if ((jsonChildNode.has("name")) && jsonChildNode.optString("name").trim() != null && !jsonChildNode.optString("name").trim().isEmpty() && !jsonChildNode.optString("name").trim().equals("null") && !jsonChildNode.optString("name").trim().matches("")) {
                            filterVideo.setName(jsonChildNode.optString("name"));
                        }
                        if ((jsonChildNode.has("poster_url")) && jsonChildNode.optString("poster_url").trim() != null && !jsonChildNode.optString("poster_url").trim().isEmpty() && !jsonChildNode.optString("poster_url").trim().equals("null") && !jsonChildNode.optString("poster_url").trim().matches("")) {
                            filterVideo.setPosterUrl(jsonChildNode.optString("poster_url"));

                        }
                        if ((jsonChildNode.has("permalink")) && jsonChildNode.optString("permalink").trim() != null && !jsonChildNode.optString("permalink").trim().isEmpty() && !jsonChildNode.optString("permalink").trim().equals("null") && !jsonChildNode.optString("permalink").trim().matches("")) {
                            filterVideo.setPermalink(jsonChildNode.optString("permalink"));
                        }
                        if ((jsonChildNode.has("content_types_id")) && jsonChildNode.optString("content_types_id").trim() != null && !jsonChildNode.optString("content_types_id").trim().isEmpty() && !jsonChildNode.optString("content_types_id").trim().equals("null") && !jsonChildNode.optString("content_types_id").trim().matches("")) {
                            filterVideo.setContentTypesId(jsonChildNode.optString("content_types_id"));

                        }
                        //videoTypeIdStr = "1";
                        if ((jsonChildNode.has("video_duration")) && jsonChildNode.optString("video_duration").trim() != null && !jsonChildNode.optString("video_duration").trim().isEmpty() && !jsonChildNode.optString("video_duration").trim().equals("null") && !jsonChildNode.optString("video_duration").trim().matches("")) {
                            filterVideo.setVideo_duration(jsonChildNode.optString("video_duration"));

                        }
                        try {
                            if ((jsonChildNode.has("is_converted")) && jsonChildNode.optString("is_converted").trim() != null && !jsonChildNode.optString("is_converted").trim().isEmpty() && !jsonChildNode.optString("is_converted").trim().equals("null") && !jsonChildNode.optString("is_converted").trim().matches("")) {
                                filterVideo.setIsConverted(Integer.parseInt(jsonChildNode.optString("is_converted")));

                            }else {
                                filterVideo.setIsConverted(0);
                            }
                        }catch (Exception e){
                            filterVideo.setIsConverted(0);
                        }
                        try {
                            if ((jsonChildNode.has("is_advance")) && jsonChildNode.optString("is_advance").trim() != null && !jsonChildNode.optString("is_advance").trim().isEmpty() && !jsonChildNode.optString("is_advance").trim().equals("null") && !jsonChildNode.optString("is_advance").trim().matches("")) {
                                filterVideo.setIsAPV(Integer.parseInt(jsonChildNode.optString("is_advance")));

                            }else {
                                filterVideo.setIsAPV(0);
                            }
                        }catch (Exception e){
                            filterVideo.setIsAPV(0);
                        }

                        try {
                            if ((jsonChildNode.has("is_ppv")) && jsonChildNode.optString("is_ppv").trim() != null && !jsonChildNode.optString("is_ppv").trim().isEmpty() && !jsonChildNode.optString("is_ppv").trim().equals("null") && !jsonChildNode.optString("is_ppv").trim().matches("")) {
                                filterVideo.setIsPPV(Integer.parseInt(jsonChildNode.optString("is_ppv")));

                            }else {
                                filterVideo.setIsPPV(0);
                            }
                        }catch (Exception e){
                            filterVideo.setIsPPV(0);
                        }

                        if ((jsonChildNode.has("is_episode")) && jsonChildNode.optString("is_episode").trim() != null && !jsonChildNode.optString("is_episode").trim().isEmpty() && !jsonChildNode.optString("is_episode").trim().equals("null") && !jsonChildNode.optString("is_episode").trim().matches("")) {
                            filterVideo.setIsEpisodeStr(jsonChildNode.optString("is_episode"));

                        }
                        loadFilterVideoOutput.add(filterVideo);
                    } catch (Exception e) {
                        status = 0;
                        totalItems = 0;
                        message = "";
                    }
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
        listener.onLoadFilterVideoPreExecuteStarted();
        responseStr = "0";
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onLoadFilterVideoPostExecuteCompleted(loadFilterVideoOutput, status, totalItems, message,category_id,isFollowed);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onLoadFilterVideoPostExecuteCompleted(loadFilterVideoOutput, status, totalItems, message,category_id,isFollowed);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        listener.onLoadFilterVideoPostExecuteCompleted(loadFilterVideoOutput, status, totalItems, message,category_id,isFollowed);
    }
}
