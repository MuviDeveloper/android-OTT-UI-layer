package com.home.apisdk;

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
 * This is a helper class for OkHTTP.
 * @author PIYUSH
 * @date 20-Aug-2018
 * */
public class OkHttpRequester {

    private static final String CONTENT_TYPE_ENCODED = "application/x-www-form-urlencoded; charset=UTF-8";
    private static final MediaType MEDIA_URL_ENCODED = MediaType.parse(CONTENT_TYPE_ENCODED);

    private static OkHttpClient okHttpClient;

    public OkHttpRequester() {
        initClient();
    }

    private void initClient() {
        final long timeoutValue = 30L;
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(timeoutValue, TimeUnit.SECONDS);
            builder.readTimeout(timeoutValue, TimeUnit.SECONDS);
            builder.writeTimeout(timeoutValue, TimeUnit.SECONDS);

           if (BuildConfig.DEBUG) {
                builder.addInterceptor(new OkHttpProfilerInterceptor());
            }

            okHttpClient = builder.build();
        }
    }


    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");


    public String addHeaderParams(LinkedHashMap<String, String> parameters, String url) {
        String result = null;
        try {
            RequestBody body = RequestBody.create(MEDIA_URL_ENCODED, "");
            Request.Builder builder = new Request.Builder();
            Set<Map.Entry<String, String>> entrySet = parameters.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {

                if (entry.getValue() == null) {
                    builder.addHeader(entry.getKey(), "");
                } else {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            Request request = builder
                    .url(url)
                    .post(body)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() != 200) {
                throw new IllegalResponseCodeException(response.code());
            }
            result = response.body().string();
        } catch (Exception e) {
            result = null;
        }
        return result;
    }



    public String multiPartPostRequest(LinkedHashMap<String, String> params, String url) {
        String result = "";
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            if (entry.getValue() != null) {
                builder.addFormDataPart(entry.getKey(), entry.getValue().toString());
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = okHttpClient.newCall(request).execute();

            if (response.code() == 200 && response.body() != null) {
                result = response.body().string();
            } else {
                throw new IllegalResponseCodeException(response.code());
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }



    public class IllegalResponseCodeException extends Exception {

        private int responseCode;

        public IllegalResponseCodeException(int responseCode) {
            this.responseCode = responseCode;
        }

        @Override
        public String getMessage() {
            return "Illegal HTTP response code " + responseCode + ".";
        }
    }
}
