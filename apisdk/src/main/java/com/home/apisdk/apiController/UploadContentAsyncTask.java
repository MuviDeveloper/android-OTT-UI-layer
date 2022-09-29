package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIController;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.UploadContentInputModel;
import com.home.apisdk.apiModel.UploadContentOutputModel;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MUVI on 19-Sep-18.
 */

public class UploadContentAsyncTask extends AsyncTask<Void, Void, Void> {
    private UploadContentInputModel mUploadContentInputModel;
    private String responseStr;
    private int status;
    private String totalItems;
    private String message;
    private String PACKAGE_NAME;
    private UploadContentListener listener;
    private Context context;
    private UploadContentOutputModel uploadContentOutputModel;
    private APIController apiController;
    JSONObject myJson = null;

    public interface UploadContentListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onUploadCoontentPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param response string  which contain responses. To get that responses we need to call the respective getter methods.
         */

        void onUploadContentPostExecuteCompleted(String response, int status, String message);
    }

    public UploadContentAsyncTask(UploadContentInputModel uploadContentInputModel, UploadContentListener listener, Context context, APIController controller) {
        this.listener = listener;
        this.context = context;
        this.mUploadContentInputModel = uploadContentInputModel;
        PACKAGE_NAME = context.getPackageName();
        this.apiController = controller;
        uploadContentOutputModel = new UploadContentOutputModel();

    }

    @Override
    protected Void doInBackground(Void... params) {
        if (this.mUploadContentInputModel.getPoster_image().length() > 0 || this.mUploadContentInputModel.getBanner_image().length() > 0) {
            int serverResponseCode = 0;
            final HttpURLConnection connection;
            DataOutputStream dataOutputStream = null;
            FileInputStream fileInputStream = null, bannerFileInputStram = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            String posterFileName = null, bannerFileName = null;
            int maxBufferSize = 5 * 1024 * 1024;
            try {
                if (this.mUploadContentInputModel.getPoster_image().length() > 0) {
                    File selectedFile = new File(this.mUploadContentInputModel.getPoster_image());
                    String[] parts = this.mUploadContentInputModel.getPoster_image().split("/");
                    posterFileName = parts[parts.length - 1];
                    fileInputStream = new FileInputStream(selectedFile);
                }
                if (this.mUploadContentInputModel.getBanner_image().length() > 0) {
                    File selectedFile = new File(this.mUploadContentInputModel.getBanner_image());
                    String[] parts = this.mUploadContentInputModel.getBanner_image().split("/");
                    bannerFileName = parts[parts.length - 1];
                    bannerFileInputStram = new FileInputStream(this.mUploadContentInputModel.getBanner_image());
                }

                URL url = new URL(APIUrlConstant.getUploadContentUrl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                connection.setRequestProperty(HeaderConstants.AUTH_TOKEN, this.mUploadContentInputModel.getAuthToken());
                connection.setRequestProperty(HeaderConstants.USER_ID, this.mUploadContentInputModel.getUser_id());
                connection.setRequestProperty(HeaderConstants.COUNTRY, this.mUploadContentInputModel.getCountryCode());
                connection.setRequestProperty(HeaderConstants.LANG_CODE, this.mUploadContentInputModel.getLangCode());
                connection.setRequestProperty(HeaderConstants.CATEGORY_ID, this.mUploadContentInputModel.getCategory_id());
                connection.setRequestProperty(HeaderConstants.DESCRIPTION, this.mUploadContentInputModel.getDescription());
                connection.setRequestProperty(HeaderConstants.CONTENT_NAME, this.mUploadContentInputModel.getContent_name());
                connection.setRequestProperty(HeaderConstants.UPLOAD_CONTENT_TYPE, this.mUploadContentInputModel.getUploaded_content_type());
                connection.setRequestProperty(HeaderConstants.DEVICE_TYPE, "2");
                if (this.mUploadContentInputModel.getPoster_image().length() > 0) {
                    connection.setRequestProperty(HeaderConstants.POSTER_IMG, this.mUploadContentInputModel.getPoster_image());
                }
                if (this.mUploadContentInputModel.getBanner_image().length() > 0) {
                    connection.setRequestProperty(HeaderConstants.BANNER_IMG, this.mUploadContentInputModel.getBanner_image());
                }
                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());
                if (this.mUploadContentInputModel.getPoster_image().length() > 0) {

                    //writing bytes to data outputstream
//                    dataOutputStream.writeBytes(jsonParam.toString());
                    dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                    dataOutputStream.writeBytes("Content-Disposition: form-data ; name=\"poster_image\";filename=\""
                            + posterFileName + "\"" + lineEnd);

                    dataOutputStream.writeBytes(lineEnd);

                    //returns no. of bytes present in fileInputStream
                    int bytesAvailable = fileInputStream.available();
                    //selecting the buffer size as minimum of available bytes or 1 MB
                    int bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    //setting the buffer as byte array of size of bufferSize
                    byte[] buffer = new byte[bufferSize];

                    //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                    int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                    while (bytesRead > 0) {
                        //write the bytes read from inputstream
                        dataOutputStream.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }

                    dataOutputStream.writeBytes(lineEnd);
                    dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                    //closing the input and output streams
                    fileInputStream.close();
                }
                if (this.mUploadContentInputModel.getBanner_image().length() > 0) {
                    dataOutputStream = new DataOutputStream(connection.getOutputStream());


                    //writing bytes to data outputstream
//                    dataOutputStream.writeBytes(jsonParam.toString());
                    dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                    dataOutputStream.writeBytes("Content-Disposition: form-data ; name=\"banner_image\";filename=\""
                            + bannerFileName + "\"" + lineEnd);

                    dataOutputStream.writeBytes(lineEnd);

                    //returns no. of bytes present in fileInputStream
                    int bytesAvailable = bannerFileInputStram.available();
                    //selecting the buffer size as minimum of available bytes or 1 MB
                    int bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    //setting the buffer as byte array of size of bufferSize
                    byte[] buffer = new byte[bufferSize];

                    //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                    int bytesRead = bannerFileInputStram.read(buffer, 0, bufferSize);

                    //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                    while (bytesRead > 0) {
                        //write the bytes read from inputstream
                        dataOutputStream.write(buffer, 0, bufferSize);
                        bytesAvailable = bannerFileInputStram.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = bannerFileInputStram.read(buffer, 0, bufferSize);
                    }

                    dataOutputStream.writeBytes(lineEnd);
                    dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    bannerFileInputStram.close();

                }

                serverResponseCode = connection.getResponseCode();
                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200) {
                    try {
                        InputStream ins = connection.getInputStream();
                        InputStreamReader isr = new InputStreamReader(ins);
                        BufferedReader in = new BufferedReader(isr);

                        String inputLine;

                        while ((inputLine = in.readLine()) != null) {
                            System.out.println(inputLine);
                            responseStr = inputLine;
                        }

                        if (responseStr != null) {
                            myJson = new JSONObject(responseStr);
                            status = Integer.parseInt(myJson.optString("code"));
                            message = myJson.getString("msg");
                            if (status == 200) {
                                this.apiController.insertCachedDataToDB(APIUrlConstant.MY_UPLOADS_URL, "myuploads", null, Utils.getCurrentTimeStamp());
                            }else{
                                status = 0;
                                message = "";
                            }
                        }
                    } catch (Exception e) {
                        Log.v("Exception", "Exception is: " + e.toString());
                    }
                }else{
                    status = 0;
                    message = "";
                }
                //closing the input and output streams

                dataOutputStream.flush();
                dataOutputStream.close();
                connection.disconnect();


            } catch (Exception e) {
                status = 0;
                message = "";
                Log.v("Exception", "Exception 1" + e.toString());

            }

        } else {
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(APIUrlConstant.getUploadContentUrl());
                httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
                httppost.addHeader(HeaderConstants.AUTH_TOKEN, this.mUploadContentInputModel.getAuthToken());
                httppost.addHeader(HeaderConstants.USER_ID, this.mUploadContentInputModel.getUser_id());
                httppost.addHeader(HeaderConstants.COUNTRY, this.mUploadContentInputModel.getCountryCode());
                httppost.addHeader(HeaderConstants.LANG_CODE, this.mUploadContentInputModel.getLangCode());
                httppost.addHeader(HeaderConstants.CATEGORY_ID, this.mUploadContentInputModel.getCategory_id());
                httppost.addHeader(HeaderConstants.DESCRIPTION, this.mUploadContentInputModel.getDescription());
                httppost.addHeader(HeaderConstants.CONTENT_NAME, this.mUploadContentInputModel.getContent_name());
                httppost.addHeader(HeaderConstants.UPLOAD_CONTENT_TYPE, this.mUploadContentInputModel.getUploaded_content_type());
                httppost.addHeader(HeaderConstants.POSTER_IMG, this.mUploadContentInputModel.getPoster_image());
                httppost.addHeader(HeaderConstants.BANNER_IMG, this.mUploadContentInputModel.getBanner_image());
                httppost.addHeader(HeaderConstants.DEVICE_TYPE, "2");
                // Execute HTTP Post Request

              //  responseStr = Utils.requester.setDataInBody(map, APIUrlConstant.getUploadContentUrl());
                HttpResponse response = httpclient.execute(httppost);
                responseStr = EntityUtils.toString(response.getEntity());
                if (responseStr != null) {
                    myJson = new JSONObject(responseStr);
                    status = Integer.parseInt(myJson.optString("code"));
                    message = myJson.getString("msg");
                    if (status == 200) {
                        this.apiController.insertCachedDataToDB(APIUrlConstant.MY_UPLOADS_URL, "myuploads", null, Utils.getCurrentTimeStamp());

                    }else{
                        status = 0;
                        message = "";
                    }
                }


            } catch (Exception e) {
                status = 0;
                message = "";
                Log.v("Exception", "IOException" + e.toString());
            }


        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onUploadCoontentPreExecuteStarted();
        responseStr = "0";
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onUploadContentPostExecuteCompleted(responseStr, status, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onUploadContentPostExecuteCompleted(responseStr, status, message);

        }

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        listener.onUploadContentPostExecuteCompleted(responseStr, status, message);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("uploadContent.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
