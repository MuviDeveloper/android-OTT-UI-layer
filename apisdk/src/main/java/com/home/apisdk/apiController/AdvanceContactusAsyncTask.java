package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.AdvanceContactUsInput;
import com.home.apisdk.apiModel.AdvanceContactUsOutput;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;


/*
* Author: Sweta P
* Desc: This Api call for Support
* */
public class AdvanceContactusAsyncTask extends AsyncTask<AdvanceContactUsInput, Void, Void> {
    AdvanceContactUsInput advanceContactUsInput;
    private String status= "";
    private int code ;
    private Context context;
    private String responseStr;
    AdvanceContactusListener listener;
    AdvanceContactUsOutput queries;
    AdvanceContactUsOutput.SubqueryList subQueries;
    ArrayList<AdvanceContactUsOutput> queryList = new ArrayList<>();
    ArrayList<AdvanceContactUsOutput.SubqueryList> subQueryList;



    public interface AdvanceContactusListener{
        void preAdvanceContactusExecute();
        void postAdvanceContactusExecute(ArrayList<AdvanceContactUsOutput> advanceContactUsOutput, int code, Context context);
    }

    public AdvanceContactusAsyncTask(AdvanceContactUsInput advanceContactUsInput,AdvanceContactusListener listener, Context context) {
        this.advanceContactUsInput = advanceContactUsInput;
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.preAdvanceContactusExecute();
    }

    @Override
    protected Void doInBackground(AdvanceContactUsInput... inputs) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        try {
            parameters.put(HeaderConstants.AUTH_TOKEN, this.advanceContactUsInput.getAuthToken());
            parameters.put(HeaderConstants.LANG_CODE, "20");
            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getAdavanceContactUs());

                JSONObject myJson = null;
                if (responseStr != null){
                    myJson = new JSONObject(responseStr);
                    code = Integer.parseInt(myJson.optString("code"));
                    status = myJson.optString("status");

                    if (code == 200){
                        //subQueries = new AdvanceContactUsOutput.SubqueryList();
                        JSONArray jsonArray = myJson.getJSONArray("lists");
                        JSONObject jsonChildNode,subNode;
                        for (int i=0;i<jsonArray.length();i++){
                            queries = new AdvanceContactUsOutput();
                            jsonChildNode = jsonArray.getJSONObject(i);
                            queries.setQueryId(jsonChildNode.getString("query_id"));
                            queries.setQueryName(jsonChildNode.getString("query_name"));
                            queries.setQueryType(jsonChildNode.getString("query_type"));
                            JSONArray childArray = jsonChildNode.getJSONArray("sub_queries");
                            subQueryList = new ArrayList<>();
                            if (childArray.length() > 0){
                                for (int j =0;j<childArray.length();j++){
                                    subQueries = new AdvanceContactUsOutput().new SubqueryList();
                                    subNode = childArray.getJSONObject(j);
                                    subQueries.setSub_queryId(subNode.getString("sub_query_id"));
                                    subQueries.setSub_queryName(subNode.getString("sub_query_name"));

                                    subQueryList.add(subQueries);
                                }

                            }
                            queries.setSubQueries(subQueryList);
                            queryList.add(queries);
                        }

                    }

                }

            } catch (Exception e) {
                code = 0;
                status = "Error";
                e.printStackTrace();
            }
        }catch (Exception e) {
            code = 0;
            status = "";
            e.printStackTrace();
        }
        return null;
    }

    private void parseAPIData() {
        if (queryList != null){
            queryList.clear();
            subQueryList.clear();}
        JSONObject myJson = null;
        try {
            if (responseStr != null){
                myJson = new JSONObject(responseStr);
                code = Integer.parseInt(myJson.optString("code"));
                status = myJson.optString("status");

                if (code == 200){
                    queries = new AdvanceContactUsOutput();
                    subQueries = new AdvanceContactUsOutput().new SubqueryList();
                    //subQueries = new AdvanceContactUsOutput.SubqueryList();
                    JSONArray jsonArray = myJson.getJSONArray("lists");
                    JSONObject jsonChildNode,subNode;
                    for (int i=0;i<jsonArray.length();i++){
                        jsonChildNode = jsonArray.getJSONObject(i);
                        queries.setQueryId(jsonChildNode.getString("query_id"));
                        queries.setQueryName(jsonChildNode.getString("query_name"));
                        queries.setQueryType(jsonChildNode.getString("query_type"));
                        JSONArray childArray = jsonChildNode.getJSONArray("sub_queries");
                        subQueryList = new ArrayList<>();
                        if (childArray.length() > 0){
                            for (int j =0;j<childArray.length();j++){
                                subNode = childArray.getJSONObject(j);
                                subQueries.setSub_queryId(subNode.getString("sub_query_id"));
                                subQueries.setSub_queryName(subNode.getString("sub_query_name"));

                                subQueryList.add(subQueries);
                            }

                        }
                        queries.setSubQueries(subQueryList);
                        queryList.add(queries);
                    }
                }

            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Void unused) {
        listener.postAdvanceContactusExecute(queryList,code,context);
    }
}
