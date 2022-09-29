package com.home.apisdk.filterList;

import android.content.Context;
import android.os.AsyncTask;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiController.HeaderConstants;
import com.home.apisdk.apiController.SDKInitializer;
import com.home.apisdk.apiModel.LoadFilterVideoInput;
import com.home.apisdk.apiModel.LoadFilterVideoOutput;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class GetFilterListAsycTask extends AsyncTask<LoadFilterVideoInput, Void, Void> {
    private FilterListInptModel loadFilterVideoInput;
    private String responseStr;
    private int status;
    private int totalItems;
    private String message, category_id, isFollowed;
    private String PACKAGE_NAME;
    private LoadFilterVideoListner listener;
    private Context context;
    public static ArrayList<String> genreArray;


    public interface LoadFilterVideoListner {

        void onLoadFilterVideoPreExecuteStarted();

        void onLoadFilterVideoPostExecuteCompleted(ArrayList<LoadFilterVideoOutput> loadFilterVideoOutputArrayList, int status, int totalItems, String message);

    }

    ArrayList<LoadFilterVideoOutput> loadFilterVideoOutput = new ArrayList<LoadFilterVideoOutput>();


    public GetFilterListAsycTask(FilterListInptModel loadFilterVideoInput, LoadFilterVideoListner listener, Context context) {
        this.listener = listener;
        this.context = context;

        this.loadFilterVideoInput = loadFilterVideoInput;
        PACKAGE_NAME = context.getPackageName();


    }

    @Override
    protected Void doInBackground(LoadFilterVideoInput... params) {
        String urlRouteList = APIUrlConstant.getFilterList();

        try {
            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put(HeaderConstants.AUTH_TOKEN, this.loadFilterVideoInput.getAuthToken());
            parameters.put(HeaderConstants.USER_ID, this.loadFilterVideoInput.getUserId());
            parameters.put(HeaderConstants.OFFSET, this.loadFilterVideoInput.getOffset());
            parameters.put(HeaderConstants.LIMIT, "50");
            parameters.put(HeaderConstants.LANG_CODE, this.loadFilterVideoInput.getLanguage());
            parameters.put(HeaderConstants.COUNTRY, this.loadFilterVideoInput.getCountry());
            parameters.put(HeaderConstants.STATE, this.loadFilterVideoInput.getState());
            parameters.put(HeaderConstants.CITY, this.loadFilterVideoInput.getCity());
            parameters.put("sort_by", createSortByJSON(loadFilterVideoInput));

            if (loadFilterVideoInput.getCatagoryQuery().size() > 0)
                parameters.put("category", createCategoryJSON(loadFilterVideoInput));
            parameters.put("genre", createGenreJSON(loadFilterVideoInput));

            //Metadata
            for (int i = 0; i < loadFilterVideoInput.getMetadataQuery().size(); i++) {
                HashMap<String, List<String>> metadataQuery = loadFilterVideoInput.getMetadataQuery();
                Set<Map.Entry<String, List<String>>> metaEntrySet = metadataQuery.entrySet();
                for (Map.Entry<String, List<String>> entry : metaEntrySet) {
                    if (entry.getValue() != null) {
                        JSONArray jsonArray = new JSONArray();
                        List<String> metaList = entry.getValue();

                        for (String meta : metaList) {

                            if (meta.contains("'")) {
                                meta = meta.replace("'", "\\'");
                            }

                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put(entry.getKey() + "_data", meta);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            jsonArray.put(jsonObject);
                        }
                        parameters.put(entry.getKey(), jsonArray.toString());
                    }
                }
            }


            // Execute HTTP Post Request
            try {

                responseStr = Utils.requester.addHeaderParams(parameters, urlRouteList);

            } catch (Exception e) {
                status = 0;
                totalItems = 0;
                message = "";
            }

            JSONObject myJson = null;
            if (responseStr != null) {
                myJson = new JSONObject(responseStr);
                status = Integer.parseInt(myJson.optString("code"));
                message = myJson.optString("msg");
                category_id = myJson.optString("category_id");
                isFollowed = myJson.optString("isFollowed");

                try {
                    totalItems = Integer.parseInt(myJson.optString("item_count"));
                } catch (Exception e) {
                    totalItems = 0;
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

                            } else {
                                filterVideo.setIsConverted(0);
                            }
                        } catch (Exception e) {
                            filterVideo.setIsConverted(0);
                        }
                        try {
                            if ((jsonChildNode.has("is_advance")) && jsonChildNode.optString("is_advance").trim() != null && !jsonChildNode.optString("is_advance").trim().isEmpty() && !jsonChildNode.optString("is_advance").trim().equals("null") && !jsonChildNode.optString("is_advance").trim().matches("")) {
                                filterVideo.setIsAPV(Integer.parseInt(jsonChildNode.optString("is_advance")));

                            } else {
                                filterVideo.setIsAPV(0);
                            }
                        } catch (Exception e) {
                            filterVideo.setIsAPV(0);
                        }

                        try {
                            if ((jsonChildNode.has("is_ppv")) && jsonChildNode.optString("is_ppv").trim() != null && !jsonChildNode.optString("is_ppv").trim().isEmpty() && !jsonChildNode.optString("is_ppv").trim().equals("null") && !jsonChildNode.optString("is_ppv").trim().matches("")) {
                                filterVideo.setIsPPV(Integer.parseInt(jsonChildNode.optString("is_ppv")));

                            } else {
                                filterVideo.setIsPPV(0);
                            }
                        } catch (Exception e) {
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

    private String createGenreJSON(FilterListInptModel filterListInptModel) {
        //Genre
        JSONArray genreJsonArray = new JSONArray();
        try {
            for (String genre : filterListInptModel.getGenreQuery()) {
                if (genre.contains("'")) {
                    genre = genre.replace("'", "\\'");
                }
                JSONObject student1 = new JSONObject();
                student1.put("genre_name", genre);
                genreJsonArray.put(student1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genreJsonArray.length() == 0 ? "" : genreJsonArray.toString();
    }

    private String createCategoryJSON(FilterListInptModel filterListInptModel) {
        //Category
        JSONArray categoryJsonArray = new JSONArray();
        try {
            for (String permalink : filterListInptModel.getCatagoryQuery()) {

                if (permalink.contains("'")) {
                    permalink = permalink.replace("'", "\\'");
                }
                JSONObject student1 = new JSONObject();
                student1.put("category_permalink", permalink);
                categoryJsonArray.put(student1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryJsonArray.toString();
    }

    //sort by
    private String createSortByJSON(FilterListInptModel filterListInptModel) {
        if (filterListInptModel.getSortByQuery().size() > 0) {
            return filterListInptModel.getSortByQuery().get(0);
        }
        return "";
    }

    private JSONObject createJsonModel(LinkedHashMap<String, String> params, FilterListInptModel filterListInptModel) {

        JSONObject jsonObject = new JSONObject();
        try {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                if (entry.getValue() != null) {
                    try {
                        jsonObject.put(entry.getKey(), entry.getValue());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            //sort by
            if (filterListInptModel.getSortByQuery().size() > 0)
                jsonObject.put("sort_by", filterListInptModel.getSortByQuery().get(0));

            //Genre
            JSONArray genreJsonArray = new JSONArray();
            for (String genre : filterListInptModel.getGenreQuery()) {
                JSONObject student1 = new JSONObject();
                student1.put("genre_name", genre);
                genreJsonArray.put(student1);

            }
            jsonObject.put("genre", genreJsonArray);


            //Category
            JSONArray categoryJsonArray = new JSONArray();
            for (String permalink : filterListInptModel.getCatagoryQuery()) {
                JSONObject student1 = new JSONObject();
                student1.put("categoty_permalink", permalink);
                categoryJsonArray.put(student1);
            }
            jsonObject.put("category", categoryJsonArray);

            //Metadata
            HashMap<String, List<String>> metadataQuery = filterListInptModel.getMetadataQuery();
            Set<Map.Entry<String, List<String>>> metaEntrySet = metadataQuery.entrySet();
            for (Map.Entry<String, List<String>> entry : metaEntrySet) {
                if (entry.getValue() != null) {
                    try {

                        JSONArray jsonArray = new JSONArray();
                        List<String> metaList = entry.getValue();

                        for (String meta : metaList) {
                            JSONObject student1 = new JSONObject();
                            try {
                                student1.put(entry.getKey() + "_data", meta);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            jsonArray.put(student1);
                        }
                        jsonObject.put(entry.getKey(), jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }


        } catch (Exception e) {
        }

        return jsonObject;
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
            listener.onLoadFilterVideoPostExecuteCompleted(loadFilterVideoOutput, status, totalItems, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onLoadFilterVideoPostExecuteCompleted(loadFilterVideoOutput, status, totalItems, message);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        listener.onLoadFilterVideoPostExecuteCompleted(loadFilterVideoOutput, status, totalItems, message);
    }
}
