package com.home.apisdk.apiController;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.home.apisdk.APIController;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.Utils;
import com.home.apisdk.apiModel.GetMenusInputModel;
import com.home.apisdk.apiModel.MenusOutputModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This Class loads the Menus.
 *
 * @author Abhishek
 */

public class GetAppMenuAsync extends AsyncTask<GetMenusInputModel, Void, Void> {

    private GetMenusInputModel getMenusInputModel;
    private String responseStr;
    private String cacheresponseStr;
    private int status;
    private String message;
    private String PACKAGE_NAME;
    private String title;
    private String Permalink;
    private String ID;
    private String TitleChild;
    private String PermalinkChild;
    private String IDChild;
    private String UserTitleChild;
    private String UserIDChild;
    private String UserParentIdChild;
    private String UserPermalinkChild;
    private String UserClasChild;
    private String fdomain;
    private String flink_type;
    private String fid;
    private String fdisplay_name;
    private String fpermalink;
    private String furl;
    private String ParentIdChild;
    private String LinkTypeChild;
    private String ParentId;
    private String Clas;
    private String LinkType;
    private String UserTitle;
    private String UserPermalink;
    private String UserID;
    private String UserParentId;
    private String UserClas;
    private GetMenusListener listener;
    private Context context;
    private String str = "#";
    String value;
    String id_seq;
    String language_id;
    String language_parent_id;
    String child_value;
    String child_id_seq;
    String child_language_id;
    String child_language_parent_id;
    String isSubcategoryPresent;
    String category_id;

    //API Caching

    private APIController apiController;
    private boolean isCacheEnabled, networkStatus;

    /**
     * Interface used to allow the caller of a GetMenusAsynTask to run some code when get
     * responses.
     */


    public interface GetMenusListener {

        /**
         * This method will be invoked before controller start execution.
         * This method to handle pre-execution work.
         */

        void onGetMenusPreExecuteStarted();

        /**
         * This method will be invoked after controller complete execution.
         * This method to handle post-execution work.
         *
         * @param menusOutputModel A Model Class which contain responses. To get that responses we need to call the respective getter methods.
         * @param status           Response Code From The Server
         * @param message          On Success Message
         */

        void onGetMenusPostExecuteCompleted(MenusOutputModel menusOutputModel, int status, String message, boolean isCache);
    }


    MenusOutputModel menusOutputModel;
    MenusOutputModel.MainMenu mainMenu;
    MenusOutputModel.UserMenu userMenu;
    MenusOutputModel.FooterMenu footerMenu;
    MenusOutputModel.MainMenu.MainMenuChild mainMenuChild;
    MenusOutputModel.UserMenu.UserMenuChild userMenuChild;
    ArrayList<MenusOutputModel.MainMenu.MainMenuChild> mainMenuChildArrayList;
    ArrayList<MenusOutputModel.UserMenu.UserMenuChild> userMenuChildArrayList;
    ArrayList<MenusOutputModel.MainMenu> mainMenuArrayList;
    ArrayList<MenusOutputModel.UserMenu> userMenuArrayList;
    ArrayList<MenusOutputModel.FooterMenu> footerMenuArrayList;

    /**
     * Constructor to initialise the private data members.
     *
     * @param getMenusInputModel A Model Class which is use for background task, we need to set all the attributes through setter methods of input model class,
     *                           For Example: to use this API we have to set following attributes:
     *                           setAuthToken(),setLang_code() etc.
     * @param listener           GetMenus Listener
     * @param context            android.content.Context
     */

    public GetAppMenuAsync(GetMenusInputModel getMenusInputModel, GetMenusListener listener, Context context, APIController dataController, boolean isCacheEnabled, boolean netwokStatus) {
        this.listener = listener;
        this.context = context;
        this.getMenusInputModel = getMenusInputModel;
        PACKAGE_NAME = context.getPackageName();
        this.apiController = dataController;
        this.isCacheEnabled = isCacheEnabled;
        this.networkStatus = netwokStatus;
    }

    /**
     * Background thread to execute.
     *
     * @return null
     * @throws org.apache.http.conn.ConnectTimeoutException,IOException
     */

    @Override
    protected Void doInBackground(GetMenusInputModel... params) {

        try {
            if (this.networkStatus) {

                LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

                parameters.put(HeaderConstants.AUTH_TOKEN, this.getMenusInputModel.getAuthToken());
                parameters.put(HeaderConstants.LANG_CODE, this.getMenusInputModel.getLang_code());
                parameters.put(HeaderConstants.COUNTRY, this.getMenusInputModel.getCountryCode());
                parameters.put(HeaderConstants.STATE, this.getMenusInputModel.getState());
                parameters.put(HeaderConstants.CITY, this.getMenusInputModel.getCity());
                parameters.put(HeaderConstants.USER_ID, this.getMenusInputModel.getUser_id());

                try {
                    responseStr = Utils.requester.addHeaderParams(parameters, APIUrlConstant.getGetAppMenu());
                    parseAPIData();

                } catch (Exception e) {
                    status = 0;
                    message = "Error";
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            status = 0;
            message = "";
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onGetMenusPreExecuteStarted();
        status = 0;
        if (!PACKAGE_NAME.equals(SDKInitializer.getUser_Package_Name_At_Api(context))) {
            this.cancel(true);
            message = "Packge Name Not Matched";
            listener.onGetMenusPostExecuteCompleted(menusOutputModel, status, message, false);
            return;
        }
        if (SDKInitializer.getHashKey(context).equals("")) {
            this.cancel(true);
            message = "Hash Key Is Not Available. Please Initialize The SDK";
            listener.onGetMenusPostExecuteCompleted(menusOutputModel, status, message, false);
            return;
        }

        if (this.apiController.getAPIData(APIUrlConstant.GetAppMenu, "", isCacheEnabled) == null) {
        } else {
            responseStr = this.apiController.getAPICacheData(APIUrlConstant.GetAppMenu, "");
            cacheresponseStr = responseStr;
            parseAPIData();

            listener.onGetMenusPostExecuteCompleted(menusOutputModel, status, message, true);
        }
    }


    @Override
    protected void onPostExecute(Void result) {
        if (cacheresponseStr == null || responseStr == null) {
            listener.onGetMenusPostExecuteCompleted(menusOutputModel, status, message, false);
            return;
        }

        if (!cacheresponseStr.equals(responseStr)) {
            listener.onGetMenusPostExecuteCompleted(menusOutputModel, status, message, false);
        }
    }

    private void parseAPIData() {

        JSONObject myJson = null;

        try {

            if (responseStr != null) {
                myJson = new JSONObject(responseStr);
                status = Integer.parseInt(myJson.optString("code"));
                message = myJson.optString("status");
            }

            if (status > 0) {
                if (status == 200) {
                    this.apiController.insertCachedDataToDB(APIUrlConstant.GetAppMenu, "", responseStr, Utils.getCurrentTimeStamp());
                    menusOutputModel = new MenusOutputModel();

                    if ((myJson.has("msg")) && myJson.optString("msg").trim() != null && !myJson.optString("msg").trim().isEmpty() && !myJson.optString("msg").trim().equals("null") && !myJson.optString("msg").trim().matches("")) {
                        menusOutputModel.setMsg(myJson.optString("msg"));
                    }
                    try {

                        JSONArray jsonMainMenu = myJson.optJSONArray("menu_items");
                        Log.d("sanju", "parseAPIData: "+jsonMainMenu);
                        mainMenuArrayList = new ArrayList<>();
                        footerMenuArrayList = new ArrayList<>();

                        for (int i = 0; i < jsonMainMenu.length(); i++) {
                            mainMenu = new MenusOutputModel.MainMenu();
                            footerMenu = new MenusOutputModel.FooterMenu();
                            footerMenu.setEnable(false);
                            mainMenu.setEnable(true);

                            if (jsonMainMenu.getJSONObject(i).has("title")) {
                                title = jsonMainMenu.getJSONObject(i).optString("title").trim();

                                mainMenu.setTitle(title);
                                footerMenu.setDisplay_name(title);

                            }
                            if (jsonMainMenu.getJSONObject(i).has("permalink")) {
                                Permalink = jsonMainMenu.getJSONObject(i).optString("permalink").trim();

                                mainMenu.setPermalink(Permalink);
                                footerMenu.setPermalink(Permalink);

                            }
                            if (jsonMainMenu.getJSONObject(i).has("id")) {
                                ID = jsonMainMenu.getJSONObject(i).optString("id").trim();

                                mainMenu.setId(ID);
                                footerMenu.setId(ID);

                            }
                            if (jsonMainMenu.getJSONObject(i).has("parent_id")) {
                                ParentId = jsonMainMenu.getJSONObject(i).optString("parent_id").trim();

                                mainMenu.setParent_id(ParentId);
                                footerMenu.setId(ParentId);//added nowSK

                            }
                            if (jsonMainMenu.getJSONObject(i).has("link_type")) {
                                LinkType = jsonMainMenu.getJSONObject(i).optString("link_type").trim();

                                mainMenu.setLink_type(LinkType);
                                footerMenu.setLink_type(LinkType);

                            }
                            if (jsonMainMenu.getJSONObject(i).has("value")) {
                                value = jsonMainMenu.getJSONObject(i).optString("value").trim();

                                mainMenu.setValue(value);

                            }
                            if (jsonMainMenu.getJSONObject(i).has("id_seq")) {
                                id_seq = jsonMainMenu.getJSONObject(i).optString("id_seq").trim();

                                mainMenu.setId_seq(id_seq);
                                footerMenu.setId_seq(id_seq);

                            }
                            if (jsonMainMenu.getJSONObject(i).has("language_id")) {
                                language_id = jsonMainMenu.getJSONObject(i).optString("language_id").trim();

                                mainMenu.setLanguage_id(language_id);
                                footerMenu.setLanguage_id(language_id);

                            }
                            if (jsonMainMenu.getJSONObject(i).has("language_parent_id")) {
                                language_parent_id = jsonMainMenu.getJSONObject(i).optString("language_parent_id").trim();

                                mainMenu.setLanguage_parent_id(language_parent_id);
                                footerMenu.setLanguage_parent_id(language_parent_id);

                            }
                            if (jsonMainMenu.getJSONObject(i).has("category_id")) {
                                category_id = jsonMainMenu.getJSONObject(i).optString("category_id").trim();

                                mainMenu.setCategory_id(category_id);
                                footerMenu.setCategory_id(category_id);

                            }
                            if (jsonMainMenu.getJSONObject(i).has("isSubcategoryPresent")) {
                                isSubcategoryPresent = jsonMainMenu.getJSONObject(i).optString("isSubcategoryPresent").trim();

                                mainMenu.setIsSubcategoryPresent(isSubcategoryPresent);
                                footerMenu.setIsSubcategoryPresent(isSubcategoryPresent);

                            }


                            if (LinkType != null && !LinkType.equals("")) {
                                if (LinkType.equals("0") || LinkType.equals("3")) {
                                    mainMenuArrayList.add(mainMenu);

                                }  else {

                                    footerMenuArrayList.add(footerMenu);

                                }
                            }



                            if (jsonMainMenu.getJSONObject(i).has("child")) {

                                try {
                                    mainMenuChildArrayList = new ArrayList<>();
                                    JSONArray jsonChildNode = jsonMainMenu.getJSONObject(i).getJSONArray("child");
                                    for (int j = 0; j < jsonChildNode.length(); j++) {

                                        mainMenuChild = mainMenu.new MainMenuChild();
                                        if (jsonChildNode.getJSONObject(j).has("title")) {
                                            TitleChild = jsonChildNode.getJSONObject(j).optString("title").trim();
                                            mainMenuChild.setTitle(TitleChild);
                                        }
                                        if (jsonChildNode.getJSONObject(j).has("permalink")) {
                                            PermalinkChild = jsonChildNode.getJSONObject(j).optString("permalink").trim();
                                            mainMenuChild.setPermalink(PermalinkChild);
                                        }
                                        if (jsonChildNode.getJSONObject(j).has("id")) {
                                            IDChild = jsonChildNode.getJSONObject(j).optString("id").trim();
                                            mainMenuChild.setId(IDChild);
                                        }
                                        if (jsonChildNode.getJSONObject(j).has("parent_id")) {
                                            ParentIdChild = jsonChildNode.getJSONObject(j).optString("parent_id").trim();
                                            mainMenuChild.setParent_id(ParentIdChild);
                                        }
                                        if (jsonChildNode.getJSONObject(j).has("link_type")) {
                                            LinkTypeChild = jsonChildNode.getJSONObject(j).optString("link_type").trim();
                                            mainMenuChild.setLink_type(LinkTypeChild);
                                        }
                                        if (jsonChildNode.getJSONObject(j).has("value")) {
                                            child_value = jsonChildNode.getJSONObject(j).optString("value").trim();
                                            mainMenuChild.setValue(child_value);
                                        }
                                        if (jsonChildNode.getJSONObject(j).has("id_seq")) {
                                            child_id_seq = jsonChildNode.getJSONObject(j).optString("id_seq").trim();
                                            mainMenuChild.setId_seq(child_id_seq);
                                        }
                                        if (jsonChildNode.getJSONObject(j).has("language_id")) {
                                            child_language_id = jsonChildNode.getJSONObject(j).optString("language_id").trim();
                                            mainMenuChild.setLanguage_id(child_language_id);
                                        }
                                        if (jsonChildNode.getJSONObject(j).has("language_parent_id")) {
                                            child_language_parent_id = jsonChildNode.getJSONObject(j).optString("language_parent_id").trim();
                                            mainMenuChild.setLanguage_parent_id(child_language_parent_id);
                                        }
                                        mainMenuChildArrayList.add(mainMenuChild);

                                    }

                                    mainMenu.setMainMenuChildModel(mainMenuChildArrayList);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        menusOutputModel.setMainMenuModel(mainMenuArrayList);
                        menusOutputModel.setFooterMenuModel(footerMenuArrayList);

                        JSONArray jsonUserMenu = myJson.optJSONArray("usermenu");
                        userMenuArrayList = new ArrayList<>();

                        if (jsonUserMenu != null) {
                            try {
                                for (int i = 0; i < jsonUserMenu.length(); i++) {
                                    userMenu = new MenusOutputModel.UserMenu();
                                    if (jsonUserMenu.optJSONObject(i).has("title")) {
                                        UserTitle = jsonUserMenu.optJSONObject(i).optString("title").trim();
                                        userMenu.setTitle(UserTitle);
                                    }
                                    if (jsonUserMenu.optJSONObject(i).has("permalink")) {
                                        UserPermalink = jsonUserMenu.optJSONObject(i).optString("permalink").trim();
                                        userMenu.setPermalink(UserPermalink);
                                    }
                                    if (jsonUserMenu.optJSONObject(i).has("parent_id")) {
                                        UserParentId = jsonUserMenu.optJSONObject(i).optString("parent_id").trim();
                                        userMenu.setParent_id(UserParentId);
                                    }
                                    if (UserPermalink.equals(str)) {
                                        try {

                                            JSONArray jsonUserChildNode = jsonUserMenu.getJSONObject(i).getJSONArray("children");
                                            int lengthJsonUserChildArr = jsonUserChildNode.length();
                                            for (int j = 0; j < lengthJsonUserChildArr; j++) {

                                                userMenuChild = userMenu.new UserMenuChild();
                                                UserTitleChild = jsonUserChildNode.optJSONObject(j).optString("title").trim();
                                                userMenuChild.setTitle(UserTitleChild);

                                                UserPermalinkChild = jsonUserChildNode.optJSONObject(j).optString("permalink").trim();
                                                userMenuChild.setPermalink(UserPermalinkChild);

                                                UserIDChild = jsonUserChildNode.optJSONObject(j).optString("id").trim();
                                                userMenuChild.setId(UserIDChild);
                                                UserParentIdChild = jsonUserChildNode.optJSONObject(j).optString("parent_id").trim();
                                                userMenuChild.setParent_id(UserParentIdChild);
                                                userMenuChildArrayList.add(userMenuChild);

                                            }

                                            userMenu.setUserMenuChildModel(userMenuChildArrayList);
                                        } catch (Exception e) {
                                        }
                                    }
                                    userMenuArrayList.add(userMenu);
                                }

                                menusOutputModel.setUserMenuModel(userMenuArrayList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    responseStr = "0";
                    status = 0;
                    message = "";
                }
            }
        } catch (Exception e) {
            status = 0;
            message = "";
        }
    }
}
