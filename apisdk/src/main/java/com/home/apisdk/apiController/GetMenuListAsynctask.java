/**
 * SDK initialization, platform and device information classes.
 */


package com.home.apisdk.apiController;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.MenuListInput;
import com.home.apisdk.apiModel.MenuListOutput;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This Class loads the menu list.
 *
 * @author Abhishek
 */

public class GetMenuListAsynctask extends AsyncTask<MenuListInput, Void, Void> {

    private MenuListInput menuListInput;
    private String PACKAGE_NAME;
    private String message;
    private String responseStr;
    private int code;
    private GetMenuListListener listener;
    private Context context;

    /**
     * Interface used to allow the caller of a GetMenuListAsynctask to run some code when get
     * responses.
     */

    public interface GetMenuListListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onGetMenuListPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param menuListOutput       A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param footermenuListOutput A Array List which contains footer menu List. We need to call the respective getter methods to get the values.
         * @param status               Response Code From The Server
         * @param message              On Success Message
         */

        void onGetMenuListPostExecuteCompleted(ArrayList<MenuListOutput> menuListOutput, ArrayList<MenuListOutput> footermenuListOutput, int status, String message);
    }


    ArrayList<MenuListOutput> menuListOutput = new ArrayList<MenuListOutput>();
    ArrayList<MenuListOutput> footermenuListOutput = new ArrayList<MenuListOutput>();

    /**
     * Constructor to initialise the private data members.
     *
     * @param menuListInput A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                      For Example: to use this API we have to set following attributes:
     *                      setAuthToken(),setLang_code() etc.
     * @param listener      GetMenuList Listener
     * @param context       android.content.Context
     */

    public GetMenuListAsynctask(MenuListInput menuListInput, GetMenuListListener listener, Context context) {
        this.listener = listener;
        this.context = context;


        this.menuListInput = menuListInput;
        PACKAGE_NAME = context.getPackageName();


    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException
     */

    @Override
    protected Void doInBackground(MenuListInput... params) {

        try {


            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put(HeaderConstants.AUTH_TOKEN, this.menuListInput.getAuthToken());
            parameters.put(HeaderConstants.COUNTRY, this.menuListInput.getCountry());
            parameters.put(HeaderConstants.LANG_CODE, this.menuListInput.getLang_code());


            // Execute HTTP Post Request
            try {
                responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getMenuListUrl());


            } catch (Exception e) {
                code = 0;
                message = "";
            }
            JSONObject myJson = null;
            if (responseStr != null) {
                myJson = new JSONObject(responseStr);
                code = Integer.parseInt(myJson.optString("code"));
                message = myJson.optString("msg");
            }

            if (code == 200) {

                JSONArray jsonMainNode = myJson.getJSONArray("menu");
                JSONArray jsonFooterNode = myJson.getJSONArray("footer_menu");
                int jsonFooterNodeArr = jsonFooterNode.length();
                int lengthJsonArr = jsonMainNode.length();
                for (int i = 0; i < lengthJsonArr; i++) {
                    JSONObject jsonChildNode;
                    try {
                        jsonChildNode = jsonMainNode.getJSONObject(i);
                        MenuListOutput content = new MenuListOutput();
                        content.setLink_type(jsonChildNode.optString("link_type"));
                        content.setDisplay_name(jsonChildNode.optString("display_name"));
                        content.setPermalink(jsonChildNode.optString("permalink"));
                        content.setEnable(true);
                        menuListOutput.add(content);
                    } catch (Exception e) {
                        code = 0;
                        message = "";
                    }
                }


                /*** footer menu******/
                for (int i = 0; i < jsonFooterNodeArr; i++) {
                    JSONObject jsonChildNode;
                    try {
                        jsonChildNode = jsonFooterNode.getJSONObject(i);
                        MenuListOutput content = new MenuListOutput();
                        content.setDisplay_name(jsonChildNode.optString("display_name"));
                        content.setPermalink(jsonChildNode.optString("permalink"));
                        content.setUrl(jsonChildNode.optString("url"));
                        content.setEnable(false);
                        footermenuListOutput.add(content);

                    } catch (Exception e) {
                        code = 0;
                        message = "";
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        } catch (Exception e) {
            code = 0;
            message = "";
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetMenuListPreExecuteStarted();
        code = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGetMenuListPostExecuteCompleted(menuListOutput, footermenuListOutput, code, message);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGetMenuListPostExecuteCompleted(menuListOutput, footermenuListOutput, code, message);
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        listener.onGetMenuListPostExecuteCompleted(menuListOutput, footermenuListOutput, code, message);
    }
}
