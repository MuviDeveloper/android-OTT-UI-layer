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
import com.home.apisdk.apiModel.CatagoryListOutput;
import com.home.apisdk.apiModel.GenreListInput;
import com.home.apisdk.apiModel.GenreListOutput;
import com.home.apisdk.apiModel.InstructorModel;
import com.home.apisdk.apiModel.InstructorTypeModel;
import com.home.apisdk.apiModel.IntensityModel;
import com.home.apisdk.apiModel.MetadataKeysOutput;
import com.home.apisdk.apiModel.SortByModel;
import com.home.apisdk.apiModel.VariationsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * This Class fetch the genre list from the server.
 *
 * @author MUVI
 */

public class GetGenreListAsynctask extends AsyncTask<GenreListInput, Void, Void> {

    private GenreListInput genreListInput;
    private String PACKAGE_NAME;
    private String status;
    private String responseStr;
    private int code;
    private GenreListListener listener;
    private Context context;
    private boolean isCacheEnabled;
    private APIController apiController;
    String metadataKeyName;
    ArrayList<String> metadataKeysOutputs = new ArrayList<>();
    HashMap<String, ArrayList<String>> all_metadata_info =  new HashMap<>();
    MetadataKeysOutput metadataKeysOutput = new MetadataKeysOutput();

    /**
     * Interface used to allow the caller of a GetGenreListAsynctask to run some code when get
     * responses.
     */


    public interface GenreListListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onGetGenreListPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param genreListOutput A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param code            Response From The Server
         * @param status          Fo Getting the Status
         */

        void onGetGenreListPostExecuteCompleted(ArrayList<GenreListOutput> genreListOutput, ArrayList<SortByModel> sortByListOutput , int code, String status);
    }


    ArrayList<GenreListOutput> genreListOutput = new ArrayList<GenreListOutput>();
    ArrayList<CatagoryListOutput> catagoryListOutput = new ArrayList<CatagoryListOutput>();
    ArrayList<SortByModel> sortByListOutput = new ArrayList<SortByModel>();
    ArrayList<IntensityModel> intensityListOutput = new ArrayList<IntensityModel>();
    ArrayList<InstructorTypeModel> instructorTypeListOutput = new ArrayList<InstructorTypeModel>();
    ArrayList<VariationsModel> variationListOutput = new ArrayList<VariationsModel>();
    ArrayList<InstructorModel> InstructorListOutput = new ArrayList<InstructorModel>();

    /**
     * Constructor to initialise the private data members.
     *
     * @param genreListInput A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                       For Example: to use this API we have to set following attributes:
     *                       setAuthToken() etc.
     * @param listener       GenreList Listener
     * @param context        android.content.Context
     */

    public GetGenreListAsynctask(GenreListInput genreListInput, GenreListListener listener, Context context, boolean isCacheEnabled, APIController apiController) {
        this.listener = listener;
        this.context = context;
        this.isCacheEnabled = isCacheEnabled;
        this.apiController = apiController;
        this.genreListInput = genreListInput;
        PACKAGE_NAME = context.getPackageName();


    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException
     */

    @Override
    protected Void doInBackground(GenreListInput... params) {

        try {

            if (this.apiController.getAPIData(APIUrlConstant.GENRE_LIST_URL,"", isCacheEnabled) != null){
                responseStr = this.apiController.getAPICacheData(APIUrlConstant.GENRE_LIST_URL, "");
            }else{
                LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
                parameters.put(HeaderConstants.AUTH_TOKEN, this.genreListInput.getAuthToken());
                parameters.put(HeaderConstants.LANG_CODE, this.genreListInput.getLang_code());
                parameters.put(HeaderConstants.COUNTRY, this.genreListInput.getCountryCode());
                parameters.put(HeaderConstants.IS_SPECIFIC, this.genreListInput.getIs_specific());



                try {
                    responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getGenreListUrl());
                    Log.v("MUVISDK", "RES" + responseStr);

                } catch (Exception e) {
                    code = 0;
                    status = "";
                }
            }
            // Execute HTTP Post Request
            JSONObject myJson = null;
            if (responseStr != null) {
                myJson = new JSONObject(responseStr);
                code = Integer.parseInt(myJson.optString("code"));
                status = myJson.optString("status");
                if(code == 200){
                    this.apiController.insertCachedDataToDB(APIUrlConstant.GENRE_LIST_URL, "", responseStr, Utils.getCurrentTimeStamp());
                }
            }

            if (code == 200) {


                JSONArray jsonMainNode = myJson.getJSONArray("genre");

                int lengthJsonArr = jsonMainNode.length();
                for (int i = 0; i < lengthJsonArr; i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) jsonMainNode.get(i);
                        GenreListOutput content = new GenreListOutput();
                        content.setGenre_name(jsonObject.getString("name"));

                        genreListOutput.add(content);
                    } catch (Exception e) {
                        code = 0;
                        status = "";
                    }
                }

                JSONArray jsonCatNode = myJson.getJSONArray("catagory");

                int lengthJsonCatArr = jsonCatNode.length();
                for (int i = 0; i < lengthJsonCatArr; i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) jsonCatNode.get(i);
                        CatagoryListOutput content = new CatagoryListOutput();
                        content.setCatagoryName(jsonObject.getString("name"));
                        content.setPermalink(jsonObject.getString("permalink"));

                        catagoryListOutput.add(content);
                    } catch (Exception e) {
                        code = 0;
                        status = "";
                    }
                }

                JSONArray jsonSortByArray = myJson.getJSONArray("sort_by");
                if(jsonSortByArray!=null){

                    for(int j=0;j<jsonSortByArray.length();j++){
                        try {
                            JSONObject jsonObject = (JSONObject) jsonSortByArray.get(j);
                            SortByModel sortByModel = new SortByModel();
                            sortByModel.setSortByName(jsonObject.getString("name"));
                            sortByModel.setPermalink(jsonObject.getString("permalink"));
                            sortByModel.setIsDefault(jsonObject.getString("is_default"));
                            sortByListOutput.add(sortByModel);
                        } catch (Exception e) {
                            code = 0;
                            status = "";
                        }
                    }
                }

                JSONArray jsonMetadatakeyArray = myJson.getJSONArray("metadata_keys");
                if (jsonMetadatakeyArray !=null) {

                    for (int j = 0; j < jsonMetadatakeyArray.length(); j++){
                        try {
                            metadataKeyName = jsonMetadatakeyArray.getJSONObject(j).optString("key_name").trim();
                            String display_title = myJson.optString(metadataKeyName).trim();
                            metadataKeysOutputs.add(display_title);

                            JSONArray list_array = (myJson.optJSONObject(metadataKeyName)).optJSONArray("list");

                            ArrayList<String> metadata_list = new ArrayList<>();
                            for(int k=0 ; k<list_array.length(); k++){
                                metadata_list.add(list_array.optJSONObject(k).optString("name").trim());
                            }

                            all_metadata_info.put(display_title, metadata_list);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    metadataKeysOutput.setMetadata_name(metadataKeysOutputs);
                    metadataKeysOutput.setAll_metadata_info(all_metadata_info);

                }

                // this are returned only when we send is_specific=1 i.e. for nike customer
                try {
                    IntensityModel intensityModel = new IntensityModel();
                    JSONObject jsonObject = new JSONObject(responseStr);
                    String intensity = jsonObject.optString("intensity");

                    JSONObject jsonObjectIntensity = new JSONObject(intensity);
                    String display_title = jsonObjectIntensity.optString("display_title");
                    intensityModel.setDisplay_title(display_title);
                    Log.d("display_title", display_title );


                    JSONArray jsonArrayintensity = jsonObjectIntensity.getJSONArray("list");
                    if (jsonArrayintensity != null) {

                        String[] intensityname = new String[jsonArrayintensity.length()];
                        for (int j = 0; j < jsonArrayintensity.length(); j++) {
                            try {
                                JSONObject jsonObject1 = (JSONObject) jsonArrayintensity.get(j);
                                intensityname[j] = jsonObject1.getString("name");
                                intensityListOutput.add(intensityModel);
                            } catch (Exception e) {
                                code = 0;
                                status = "";
                            }
                        }
                        intensityModel.setName(intensityname);
                    }
                } catch (Exception e) {
                    code = 0;
                    status = "";
                }

                // this are returned only when we send is_specific=1 i.e. for nike customer
                try {
                    InstructorTypeModel instructorTypeModel = new InstructorTypeModel();
                    JSONObject jsonObject2 = new JSONObject(responseStr);
                    String instructor_type = jsonObject2.optString("instructor_type");

                    JSONObject jsonObjectInstructorType = new JSONObject(instructor_type);
                      String display_title = jsonObjectInstructorType.optString("display_title");
                      instructorTypeModel.setDisplay_title(display_title);


                    JSONArray jsonArrayInstructorType = jsonObjectInstructorType.getJSONArray("list");
                    if (jsonArrayInstructorType != null) {

                        String[] instructorTypeNname = new String[jsonArrayInstructorType.length()];
                        for (int j = 0; j < jsonArrayInstructorType.length(); j++) {
                            try {
                                JSONObject jsonObject1 = (JSONObject) jsonArrayInstructorType.get(j);
                                instructorTypeNname[j] = jsonObject1.getString("name");
                                instructorTypeListOutput.add(instructorTypeModel);
                            } catch (Exception e) {
                                code = 0;
                                status = "";
                            }
                        }
                        instructorTypeModel.setName(instructorTypeNname);

                    }


                } catch (Exception e) {
                    code = 0;
                    status = "";
                }

                // this are returned only when we send is_specific=1 i.e. for nike customer

                try {
                    VariationsModel variationsModel = new VariationsModel();
                    JSONObject jsonObjectv = new JSONObject(responseStr);
                    String variation = jsonObjectv.optString("variations");

                    JSONObject jsonObjectVariation = new JSONObject(variation);
                    String display_title = jsonObjectVariation.optString("display_title");
                    variationsModel.setDisplay_title(display_title);


                    JSONArray jsonArrayVariation = jsonObjectVariation.getJSONArray("list");
                    if (jsonArrayVariation != null) {

                        String[] variationNname = new String[jsonArrayVariation.length()];
                        for (int j = 0; j < jsonArrayVariation.length(); j++) {
                            try {
                                JSONObject jsonObject1 = (JSONObject) jsonArrayVariation.get(j);
                                variationNname[j] = jsonObject1.getString("name");
                                variationListOutput.add(variationsModel);
                            } catch (Exception e) {
                                code = 0;
                                status = "";
                            }
                        }
                        variationsModel.setName(variationNname);

                    }

                } catch (Exception e) {
                    code = 0;
                    status = "";
                }
            }


            // this are returned only when we send is_specific=1 i.e. for nike customer

            try {
                InstructorModel instructorModel = new InstructorModel();
                JSONObject jsonObjectIns = new JSONObject(responseStr);
                String variation = jsonObjectIns.optString("instructor");

                JSONObject jsonObjectInstructor = new JSONObject(variation);
                String display_title = jsonObjectInstructor.optString("display_title");
                instructorModel.setDisplay_title(display_title);
                Log.d("display_title", display_title );


                JSONArray jsonArrayInstructor = jsonObjectInstructor.getJSONArray("list");
                if (jsonArrayInstructor != null) {

                    String[] instructorNname = new String[jsonArrayInstructor.length()];
                    for (int j = 0; j < jsonArrayInstructor.length(); j++) {
                        try {
                            JSONObject jsonObject1 = (JSONObject) jsonArrayInstructor.get(j);
                            instructorNname[j] = jsonObject1.getString("name");
                            InstructorListOutput.add(instructorModel);
                        } catch (Exception e) {
                            code = 0;
                            status = "";
                        }
                    }
                    instructorModel.setName(instructorNname);

                }

            } catch (Exception e) {
                code = 0;
                status = "";
            }

        } catch (Exception e) {
            code = 0;
            status = "";
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetGenreListPreExecuteStarted();
        code = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            status = "Package Name Not Matched";
            listener.onGetGenreListPostExecuteCompleted(genreListOutput, sortByListOutput,code, status);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            status = "Please Initialize The SDK";
            listener.onGetGenreListPostExecuteCompleted(genreListOutput, sortByListOutput,code, status);
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        listener.onGetGenreListPostExecuteCompleted(genreListOutput, sortByListOutput,code, status);
    }
}
