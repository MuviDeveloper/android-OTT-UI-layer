/**
 * SDK initialization, platform and device information classes.
 */

package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.getMediaQueueInput;
import com.home.apisdk.apiModel.getMediaQueueOutput;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

/**
 * This Class gives a short note about the company/organisation to its users.
 *
 * @author MUVI
 */

public class GetMediaQueueAsyncTask extends AsyncTask<getMediaQueueInput, Void, Void> {


    private getMediaQueueInput getMediaQueueInput;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private String responseStr;
    private GetMediaQueue listener;
    private Context context;
    private int code = 0;

    public interface GetMediaQueue {

        void GetMediaQueuePreExecute();

        void GetMediaQueuePostExecute(getMediaQueueOutput getMediaQueueOutput, int status);
    }

    getMediaQueueOutput getMediaQueueOutput = new getMediaQueueOutput();

    public GetMediaQueueAsyncTask(getMediaQueueInput getMediaQueueInput, GetMediaQueue listener, Context context) {
        this.listener = listener;
        this.context = context;
        this.getMediaQueueInput = getMediaQueueInput;
        PACKAGE_NAME = context.getPackageName();
    }


    @Override
    protected Void doInBackground(getMediaQueueInput... params) {
        JSONObject myJson = null;
        responseStr = getMediaQueue();
        if (responseStr != null) {
            try {
                myJson = new JSONObject(responseStr);
                code = Integer.parseInt(myJson.optString("code"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            if (code == 200) {
                try {
                    JSONObject object = new JSONObject(responseStr);
                    if(Utils.checkForNull("next_media_info",object)){
                        JSONObject next = object.optJSONObject("next_media_info");
                        getData(next, "next");
                    }else{
                        getData(null, "next");
                    }
                    if(Utils.checkForNull("previous_media_info",object)){
                        JSONObject prev = object.optJSONObject("previous_media_info");
                        getData(prev, "prev");
                    }else{
                        getData(null, "prev");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getData(JSONObject data, String type) {
        if(data != null &&  data.length() > 0) {
            com.home.apisdk.apiModel.getMediaQueueOutput.NextMediaInfo info = new getMediaQueueOutput.NextMediaInfo();
            info.setContentTitle(Utils.getNotNullValue("content_title", data));
            info.setContentTypesId(Utils.getNotNullValue("content_types_id", data));
            info.setContentUniqId(Utils.getNotNullValue("content_uniq_id", data));
            info.setStreamUniqId(Utils.getNotNullValue("stream_uniq_id", data));
            info.setDefaultPoster(Utils.getNotNullValue("default_poster", data));
            info.setDescription(Utils.getNotNullValue("description", data));
            info.setDuration(Utils.getNotNullValue("duration", data));
            info.setEpisodeNo(Utils.getNotNullValue("episode_no", data));
            info.setSeasonNo(Utils.getNotNullValue("season_no", data));
            if (type.equalsIgnoreCase("next")) {
                getMediaQueueOutput.setNextMediaInfo(info);
            } else if (type.equalsIgnoreCase("prev")) {
                getMediaQueueOutput.setPreviousMediaInfo(info);
            }
        } else {
            if (type.equalsIgnoreCase("next")) {
                getMediaQueueOutput.setNextMediaInfo(null);
            } else if (type.equalsIgnoreCase("prev")) {
                getMediaQueueOutput.setPreviousMediaInfo(null);
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.GetMediaQueuePreExecute();
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.GetMediaQueuePostExecute(getMediaQueueOutput, code);

            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.GetMediaQueuePostExecute(getMediaQueueOutput, code);

        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.GetMediaQueuePostExecute(getMediaQueueOutput, code);
    }

    public String getMediaQueue() {
            try {

                LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

                parameters.put(HeaderConstants.AUTH_TOKEN, this.getMediaQueueInput.getAuthToken());
                parameters.put(HeaderConstants.STREAM_UNIQ_ID, this.getMediaQueueInput.getStream_uniq_id());
                parameters.put(HeaderConstants.USER_ID, this.getMediaQueueInput.getUser_id());
                parameters.put(HeaderConstants.COUNTRY, this.getMediaQueueInput.getCountry());
                parameters.put(HeaderConstants.STATE, this.getMediaQueueInput.getState());
                parameters.put(HeaderConstants.CITY, this.getMediaQueueInput.getCity());
                parameters.put(HeaderConstants.LANG_CODE, this.getMediaQueueInput.getLang_code());
                parameters.put(HeaderConstants.CONTENT_INFO, this.getMediaQueueInput.getContent_info());
                parameters.put(HeaderConstants.KIDS_MODE, this.getMediaQueueInput.getKids_mode());


                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getMediaQueue());


            } catch (Exception e) {
                e.printStackTrace();
            }

        return responseStr;
    }

}