package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;

import com.home.apisdk.APIUrlConstant;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Subhadarshani on 15-Sep-18.
 */

public class GetCategoryListAsyncTask extends AsyncTask<LinkedHashMap<String,String>,Void,Void> {
    private String responseStr;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private GetCategoryListAsyncTask.GetCategoryListListener listener;
    private Context context;


    public interface GetCategoryListListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onGetCategoryListPreExecuteStarted();



        void onGetCategoryListPostExecuteCompleted(String result, int status);
    }


    public GetCategoryListAsyncTask(GetCategoryListAsyncTask.GetCategoryListListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        PACKAGE_NAME = context.getPackageName();

    }
    @Override
    protected Void doInBackground(LinkedHashMap<String, String>[] params) {
        try {
            HashMap<String,String> inputParam = params[0];
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(APIUrlConstant.getCategoryListUrl());
            httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");

            httppost.addHeader(HeaderConstants.AUTH_TOKEN, inputParam.get(HeaderConstants.AUTH_TOKEN));
            httppost.addHeader(HeaderConstants.COUNTRY, inputParam.get(HeaderConstants.COUNTRY));
            httppost.addHeader(HeaderConstants.LANG_CODE, inputParam.get(HeaderConstants.LANG_CODE));
            HttpResponse response = httpclient.execute(httppost);
            responseStr = EntityUtils.toString(response.getEntity());
            if(responseStr!=null){
                JSONObject jsonObject = new JSONObject(responseStr);
                if(Integer.parseInt(jsonObject.getString("code") )== 200){
                    status = Integer.parseInt(jsonObject.getString("code") );
                }else{
                    status =0;
                    responseStr = "";
                }

            }else{
                status =0;
                responseStr = "";
            }

        }catch(Exception e){

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onGetCategoryListPostExecuteCompleted(responseStr,status);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetCategoryListPreExecuteStarted();
        responseStr = "0";
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGetCategoryListPostExecuteCompleted(responseStr,status);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGetCategoryListPostExecuteCompleted(responseStr,status);
        }
    }
}
