package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIController;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.Episode_Details_output;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetUserPlayListDetailsAsyntask extends AsyncTask<String, Void, Void> {
    JSONObject myJson = null;
    String responseStr;
    private String cacheresponseStr;
    String sucessMsg;
    String playlist_name;
    int statusCode = 0;
    Context mContext;
    private String playlist_poster, authToken, userId, playlistID,artistName;
    int count;
    private String title;
    UserPlaylistDetailsListner userPlaylistDetailsListner;
    ArrayList<Episode_Details_output> ItemListDetails = new ArrayList<>();
    String country;
    String state;
    String city;
    String kids_mode = "0";
    private boolean isCacheEnabled;
    private APIController apiController;

    public interface UserPlaylistDetailsListner {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onUserPlaylistDetailsPreExecuteStarted();

        /**
         * This method will be invoked after controller finish its task.
         * This method to handle post-execution work.
         */

        void onUserPlaylistDetailsPostExecuteCompleted(ArrayList<Episode_Details_output> ItemListDetails,String playListName,String artistName);
    }

    public GetUserPlayListDetailsAsyntask(Context context, UserPlaylistDetailsListner listner, String token, String playlist_id, String userId,String country,String state,String city, boolean isCacheEnabled,
                                          APIController dataController) {
        this.mContext = context;
        this.userPlaylistDetailsListner = listner;
        this.authToken = token;
        this.userId = userId;
        this.playlistID = playlist_id;
        this.country = country;
        this.state = state;
        this.city = city;
        this.isCacheEnabled = isCacheEnabled;
        this.apiController = dataController;
    }

    @Override
    protected void onPreExecute() {
        this.userPlaylistDetailsListner.onUserPlaylistDetailsPreExecuteStarted();
        if (this.apiController.getAPIData(APIUrlConstant.GetAudioUserPlayListDetailURL(), playlistID, isCacheEnabled) == null) {

        } else {

            responseStr = this.apiController.getAPICacheData(APIUrlConstant.GetAudioUserPlayListDetailURL(), playlistID);
            cacheresponseStr = responseStr;
            parseAPIData();

            this.userPlaylistDetailsListner.onUserPlaylistDetailsPostExecuteCompleted(ItemListDetails,playlist_name,artistName);
        }
    }

    private void parseAPIData() {
        ItemListDetails.clear();
        if (responseStr != null) {
            try {
                myJson = new JSONObject(responseStr);
                if (myJson.has("code")) {
                    statusCode = Integer.parseInt(myJson.optString("code"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                if (statusCode == 200) {
                    this.apiController.insertCachedDataToDB(APIUrlConstant.GetAudioUserPlayListDetailURL(), playlistID, responseStr, Utils.getCurrentTimeStamp());

                    JSONObject jsonObject = myJson.getJSONObject("data");
                    try {

                        if ((jsonObject.has("poster_playlist")) && jsonObject.getString("poster_playlist").trim() != null && !jsonObject.getString("poster_playlist").trim().isEmpty() && !jsonObject.getString("poster_playlist").trim().equals("null") && !jsonObject.getString("poster_playlist").trim().matches("")) {
                            playlist_poster = jsonObject.getString("poster_playlist");
                        }
                        if ((jsonObject.has("list_name")) && jsonObject.getString("list_name").trim() != null && !jsonObject.getString("list_name").trim().isEmpty() && !jsonObject.getString("list_name").trim().equals("null") && !jsonObject.getString("list_name").trim().matches("")) {
                            playlist_name = jsonObject.getString("list_name");
                        }
                        if ((jsonObject.has("list_id")) && jsonObject.getString("list_id").trim() != null && !jsonObject.getString("list_id").trim().isEmpty() && !jsonObject.getString("list_id").trim().equals("null") && !jsonObject.getString("list_id").trim().matches("")) {
                            playlistID = jsonObject.getString("list_id");
                        }
                        if ((jsonObject.has("counts")) && jsonObject.getString("counts").trim() != null && !jsonObject.getString("counts").trim().isEmpty() && !jsonObject.getString("counts").trim().equals("null") && !jsonObject.getString("counts").trim().matches("")) {
                            count = Integer.parseInt(jsonObject.getString("counts"));
                        }

                        JSONArray jsonListArray = jsonObject.optJSONArray("lists");

                        int jsonListArrayLength = jsonListArray.length();
                        if (ItemListDetails != null && ItemListDetails.size() > 0) {
                            ItemListDetails.clear();
                        }

                        for (int j = 0; j < jsonListArrayLength; j++) {
                            Episode_Details_output episode_details_output_model = new Episode_Details_output();
                            JSONObject jsonChildNode;
                            try {
                                jsonChildNode = jsonListArray.optJSONObject(j);

                                if ((jsonChildNode.has("title")) && jsonChildNode.getString("title").trim() != null && !jsonChildNode.getString("title").trim().isEmpty() && !jsonChildNode.getString("title").trim().equals("null") && !jsonChildNode.getString("title").trim().matches("")) {
                                    episode_details_output_model.setEpisode_title(jsonChildNode.getString("title"));
                                    episode_details_output_model.setName(jsonChildNode.getString("title"));
                                }
                                if ((jsonChildNode.has("cast")) && jsonChildNode.getString("cast").trim() != null && !jsonChildNode.getString("cast").trim().isEmpty() && !jsonChildNode.getString("cast").trim().equals("null") && !jsonChildNode.getString("cast").trim().matches("")) {
//                                    artistName = jsonChildNode.getString("cast");
                                    episode_details_output_model.setCast(jsonChildNode.getString("cast"));
                                }
                                if ((jsonChildNode.has("url")) && jsonChildNode.getString("url").trim() != null && !jsonChildNode.getString("url").trim().isEmpty() && !jsonChildNode.getString("url").trim().equals("null") && !jsonChildNode.getString("url").trim().matches("")) {
                                    episode_details_output_model.setVideo_url(jsonChildNode.getString("url"));
                                }
                                if ((jsonChildNode.has("audio_poster")) && jsonChildNode.getString("audio_poster").trim() != null && !jsonChildNode.getString("audio_poster").trim().isEmpty() && !jsonChildNode.getString("audio_poster").trim().equals("null") && !jsonChildNode.getString("audio_poster").trim().matches("")) {
                                    episode_details_output_model.setPoster_url(jsonChildNode.getString("audio_poster"));
                                }
                                if ((jsonChildNode.has("movie_id")) && jsonChildNode.getString("movie_id").trim() != null && !jsonChildNode.getString("movie_id").trim().isEmpty() && !jsonChildNode.getString("movie_id").trim().equals("null") && !jsonChildNode.getString("movie_id").trim().matches("")) {
                                    episode_details_output_model.setId(jsonChildNode.getString("movie_id"));
                                }
                                if ((jsonChildNode.has("stream_uniq_id")) && jsonChildNode.getString("stream_uniq_id").trim() != null && !jsonChildNode.getString("stream_uniq_id").trim().isEmpty() && !jsonChildNode.getString("stream_uniq_id").trim().equals("null") && !jsonChildNode.getString("stream_uniq_id").trim().matches("")) {
                                    episode_details_output_model.setMovie_stream_uniq_id(jsonChildNode.getString("stream_uniq_id"));
                                }

                                if ((jsonChildNode.has("movie_uniq_id")) && jsonChildNode.getString("movie_uniq_id").trim() != null && !jsonChildNode.getString("movie_uniq_id").trim().isEmpty() && !jsonChildNode.getString("movie_uniq_id").trim().equals("null") && !jsonChildNode.getString("movie_uniq_id").trim().matches("")) {
                                    episode_details_output_model.setMuvi_uniq_id(jsonChildNode.getString("movie_uniq_id"));
                                }
                                if ((jsonChildNode.has("movie_stream_id")) && jsonChildNode.optString("movie_stream_id").trim() != null && !jsonChildNode.optString("movie_stream_id").trim().isEmpty() && !jsonChildNode.optString("movie_stream_id").trim().equals("null") && !jsonChildNode.optString("movie_stream_id").trim().matches("")) {
                                    episode_details_output_model.setMovie_stream_id(jsonChildNode.optString("movie_stream_id"));
                                }
                                if ((jsonChildNode.has("is_episode")) && jsonChildNode.getString("is_episode").trim() != null && !jsonChildNode.getString("is_episode").trim().isEmpty() && !jsonChildNode.getString("is_episode").trim().equals("null") && !jsonChildNode.getString("is_episode").trim().matches("")) {
                                    episode_details_output_model.setIsEpisode(jsonChildNode.getString("is_episode"));
                                }
                                if ((jsonChildNode.has("is_media_published")) && jsonChildNode.getString("is_media_published").trim() != null && !jsonChildNode.getString("is_media_published").trim().isEmpty() && !jsonChildNode.getString("is_media_published").trim().equals("null") && !jsonChildNode.getString("is_media_published").trim().matches("")) {
                                    episode_details_output_model.setIs_media_published(jsonChildNode.getString("is_media_published"));
                                }
                                if ((jsonChildNode.has("content_types_id")) && jsonChildNode.getString("content_types_id").trim() != null && !jsonChildNode.getString("content_types_id").trim().isEmpty() && !jsonChildNode.getString("content_types_id").trim().equals("null") && !jsonChildNode.getString("content_types_id").trim().matches("")) {
                                    episode_details_output_model.setContent_types_id(jsonChildNode.getString("content_types_id"));
                                }

                                ItemListDetails.add(episode_details_output_model);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (Exception e) {

                    }
                } else {
                    if (ItemListDetails != null && ItemListDetails.size() > 0) {
                        ItemListDetails.clear();
                    }
                }
            } catch (JSONException e) {

                e.printStackTrace();

            }
            sucessMsg = myJson.optString("msg");
        }
    }

    @Override
    protected Void doInBackground(String... strings) {

        String urlRouteList = APIUrlConstant.GetAudioUserPlayListDetailURL();
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(urlRouteList);
            httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
            httppost.addHeader("authToken", authToken.trim());
            httppost.addHeader("user_id", userId);
            httppost.addHeader("list_id", playlistID);
            httppost.addHeader(HeaderConstants.COUNTRY, country);
            httppost.addHeader(HeaderConstants.STATE, state);
            httppost.addHeader(HeaderConstants.CITY, city);
            httppost.addHeader(HeaderConstants.KIDS_MODE, kids_mode);

            try {
                HttpResponse response = httpclient.execute(httppost);
                responseStr = EntityUtils.toString(response.getEntity());
                parseAPIData();

            } catch (org.apache.http.conn.ConnectTimeoutException e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        if (cacheresponseStr == null || responseStr == null) {
            this.userPlaylistDetailsListner.onUserPlaylistDetailsPostExecuteCompleted(ItemListDetails, playlist_name, artistName);
            return;
        }

        if (!cacheresponseStr.equals(responseStr)) {
            this.userPlaylistDetailsListner.onUserPlaylistDetailsPostExecuteCompleted(ItemListDetails, playlist_name, artistName);
        }

    }
}