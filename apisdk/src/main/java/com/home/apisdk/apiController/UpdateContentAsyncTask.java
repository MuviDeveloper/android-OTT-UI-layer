package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIController;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.UploadContentInputModel;

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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MUVI on 28-Sep-18.
 */

public class UpdateContentAsyncTask extends AsyncTask<Void, Void, Void> {
    private UploadContentInputModel mUploadContentInputModel;
    private String responseStr;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private UpdateContentListener listener;
    private Context context;
    private APIController apiController;
    JSONObject myJson = null;

    public interface UpdateContentListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onUpdateCoontentPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         */

        void onUpdateContentPostExecuteCompleted(int status, String message);
    }

    public UpdateContentAsyncTask(UploadContentInputModel inputParamModelForUploadContent, UpdateContentListener updateContentListener, Context targetContext, APIController apiController) {
        this.listener = updateContentListener;
        this.context = targetContext;
        this.mUploadContentInputModel = inputParamModelForUploadContent;
        PACKAGE_NAME = context.getPackageName();
        this.apiController = apiController;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onUpdateCoontentPreExecuteStarted();
        responseStr = "0";
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onUpdateContentPostExecuteCompleted(status, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onUpdateContentPostExecuteCompleted(status, message);

        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
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

                URL url = new URL(APIUrlConstant.getUpdateContentUrl());
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
                connection.setRequestProperty(HeaderConstants.MOVIE_STREAM_UNIQUE_ID, this.mUploadContentInputModel.getMuviUniqueId());

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
                //String serverResponseMessage = connection.getResponseMessage();
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
                            }
                        }
                    } catch (Exception e) {
                        Log.v("BIBHU3", "Exception is: " + e.toString());
                        status = 0;
                        message = "";
                    }
                } else {
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

            }

        } else {
            try {
                // LinkedHashMap<String, String> map = new LinkedHashMap<>();
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(APIUrlConstant.getUpdateContentUrl());
                httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");
                httppost.setHeader(HeaderConstants.AUTH_TOKEN, this.mUploadContentInputModel.getAuthToken());
                httppost.setHeader(HeaderConstants.USER_ID, this.mUploadContentInputModel.getUser_id());
                httppost.setHeader(HeaderConstants.COUNTRY, this.mUploadContentInputModel.getCountryCode());
                httppost.setHeader(HeaderConstants.LANG_CODE, this.mUploadContentInputModel.getLangCode());
                httppost.setHeader(HeaderConstants.CATEGORY_ID, this.mUploadContentInputModel.getCategory_id());
                httppost.setHeader(HeaderConstants.DESCRIPTION, this.mUploadContentInputModel.getDescription());
                httppost.setHeader(HeaderConstants.CONTENT_NAME, this.mUploadContentInputModel.getContent_name());
                httppost.setHeader(HeaderConstants.UPLOAD_CONTENT_TYPE, this.mUploadContentInputModel.getUploaded_content_type());
                if (this.mUploadContentInputModel.getPoster_image().length() > 0) {
                    httppost.setHeader(HeaderConstants.POSTER_IMG, this.mUploadContentInputModel.getPoster_image().trim());
                }
                if (this.mUploadContentInputModel.getBanner_image().length() > 0) {
                    httppost.setHeader(HeaderConstants.BANNER_IMG, this.mUploadContentInputModel.getBanner_image());
                }

                httppost.setHeader(HeaderConstants.MOVIE_STREAM_UNIQUE_ID, this.mUploadContentInputModel.getMuviUniqueId());
                //responseStr = Utils.requester.setDataInBody(map, APIUrlConstant.getUpdateContentUrl());
                HttpResponse response = httpclient.execute(httppost);
                responseStr = EntityUtils.toString(response.getEntity());
                if (responseStr != null) {
                    myJson = new JSONObject(responseStr);
                    status = Integer.parseInt(myJson.optString("code"));
                    message = myJson.getString("msg");
                    if (status == 200) {
                        this.apiController.insertCachedDataToDB(APIUrlConstant.MY_UPLOADS_URL, "myuploads", null, Utils.getCurrentTimeStamp());
                    } else {
                        status = 0;
                        message = "";
                    }
                }


            } catch (Exception e) {
                status = 0;
                message = "";
                Log.v("MUVISDK", "IOException" + e.toString());
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        listener.onUpdateContentPostExecuteCompleted(status, message);
    }
}
