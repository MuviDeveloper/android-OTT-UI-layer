package com.home.vod.ui.fragment;

import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.CONTACT_US;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONTACT_US;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_FEATURE_UNSUPPORTED_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_HOME;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MY_DOWNLOAD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MY_LIBRARY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SETTINGS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_WATCH_HISTORY;
import static com.home.vod.preferences.LanguagePreference.FEATURE_UNSUPPORTED_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.HOME;
import static com.home.vod.preferences.LanguagePreference.MY_DOWNLOAD;
import static com.home.vod.preferences.LanguagePreference.MY_LIBRARY;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SETTINGS;
import static com.home.vod.preferences.LanguagePreference.WATCH_HISTORY;
import static com.home.vod.util.Constant.authTokenStr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.home.apisdk.apiController.GetAppMenuAsync;
import com.home.apisdk.apiController.GetUserProfileAsynctask;
import com.home.apisdk.apiModel.GetMenusInputModel;
import com.home.apisdk.apiModel.Get_UserProfile_Output;
import com.home.apisdk.apiModel.MenusOutputModel;
import com.home.vod.R;
import com.home.vod.model.NavDrawerItem;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.activity.AboutUsActivity;
import com.home.vod.ui.activity.LoginActivity;
import com.home.vod.ui.activity.ProfileActivity;
import com.home.vod.ui.activity.SettingsActivity;
import com.home.vod.ui.adapter.ExpandableListAdapter;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment implements GetAppMenuAsync.GetMenusListener, GetUserProfileAsynctask.Get_UserProfileListener {

    private ImageView profile_icon,backArrow;
    private TextView name;
    private String profileImage;

    private NavigationDrawerCallbacks mCallbacks;
    public static ArrayList<NavDrawerItem> menuList;
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerListView;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = 0;
    private FeatureHandler featureHandler;

    public HashMap<String, ArrayList<String>> expandableListDetail = new LinkedHashMap<String, ArrayList<String>>();
    private ArrayList<String> titleArray = new ArrayList<>();
    private ArrayList<String> idArray = new ArrayList<>();
    private ExpandableListAdapter adapter;
    private PreferenceManager preferenceManager;
    private LanguagePreference languagePreference;
    private GetAppMenuAsync asynLoadMenuItems = null;
    private ProgressBarHandler progressDialog;

    private MenusOutputModel menusOutputModelLocal = new MenusOutputModel();
    private MenusOutputModel menusOutputModelFromAPI = new MenusOutputModel();
    private int status;
    private String message;
    private String loggedInStr = null;
    private Fragment fragment = null;
    private int corePoolSize = 60;
    private int maximumPoolSize = 80;
    private int keepAliveTime = 10;
    private ArrayList<Integer> arrayList;


    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    private Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
    private boolean opened = false;
    private DataController dataController;

    private ExpandableListView list_slidermenu;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = PreferenceManager.getPreferenceManager(getActivity());
        languagePreference = LanguagePreference.getLanguagePreference(getActivity());
        loggedInStr = preferenceManager.getUseridFromPref();
        featureHandler = FeatureHandler.getFeaturePreference(getActivity());
        dataController = new DataController(getActivity());

        getMenus();

    }

    public void getMenus() {
        boolean isCacheEnabled = featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED);
        boolean networkStatus = NetworkStatus.getInstance().isConnected(getActivity());
        GetMenusInputModel menuListInput = new GetMenusInputModel();
        menuListInput.setAuthToken(authTokenStr);
        menuListInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        menuListInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        menuListInput.setState(preferenceManager.getStateFromPref());
        menuListInput.setCity(preferenceManager.getCityFromPref());

        if (preferenceManager.getUseridFromPref() != null && !preferenceManager.getUseridFromPref().equals("")) {
            menuListInput.setUser_id(preferenceManager.getUseridFromPref());
        } else {
            menuListInput.setUser_id("");
        }

        asynLoadMenuItems = new GetAppMenuAsync(menuListInput,
                NavigationDrawerFragment.this, getActivity(), dataController, isCacheEnabled, networkStatus);
        asynLoadMenuItems.executeOnExecutor(threadPoolExecutor);
    }

    @Override
    public void onGetMenusPreExecuteStarted() {
        progressDialog = new ProgressBarHandler(getActivity());
        progressDialog.show();
    }

    boolean menu_called = false;

    @Override
    public void onGetMenusPostExecuteCompleted(MenusOutputModel menusOutputModel, int status, String message, boolean isCache) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
            progressDialog = null;
        }
        this.menusOutputModelLocal = menusOutputModel;
        this.menusOutputModelFromAPI = menusOutputModel;

        Util.main_menu_list_size = -2;
        checkUserFeatureExists(this.menusOutputModelLocal, this.menusOutputModelFromAPI, languagePreference);

        this.status = status;
        this.message = message;


        if (!menu_called) {
            menu_called = true;
            setMenuItemsInDrawer(true);
        } else {
            setMenuItemsInDrawer(false);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);

    }
    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Configuration config = getResources().getConfiguration();
            if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                //in Right To Left layout
                backArrow.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));
            }
        } else {
            backArrow.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_arrow_right));
        }
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDrawerListView = (ExpandableListView) inflater.inflate(R.layout.drawer_drawer, container, false);
        mDrawerListView.setDivider(getResources().getDrawable(R.color.drawer_divider_color));
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);

            }
        });

        list_slidermenu = mDrawerListView.findViewById(R.id.list_slidermenu);
        adapter = new ExpandableListAdapter(getActivity(),
                idArray,
                titleArray,
                expandableListDetail);

        mDrawerListView.setAdapter(adapter);

        //for expand the child content in navigation
        mDrawerListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

                try {
                    Util.drawer_collapse_expand_imageview.remove(groupPosition);
                    Util.drawer_collapse_expand_imageview.add(groupPosition, groupPosition + "," + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        //for expand less the child content in navigation
        mDrawerListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                try {
                    Util.drawer_collapse_expand_imageview.remove(groupPosition);
                    Util.drawer_collapse_expand_imageview.add(groupPosition, groupPosition + "," + 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        mDrawerListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int listPosition, long id) {
                boolean retVal = true;
                boolean mylibrary_title_added = false;


                if (menusOutputModelLocal.getMainMenuModel().size() > listPosition) {

                    for (int l = 0; l < menusOutputModelLocal.getMainMenuModel().get(listPosition).getMainMenuChildModel().size(); l++) {
                        if (menusOutputModelLocal.getMainMenuModel().get(listPosition).getId().equals
                                (menusOutputModelLocal.getMainMenuModel().get(listPosition).getMainMenuChildModel().get(l).getParent_id())) {
                            retVal = false;
                        }

                    }
                }

                for (int i = 0; i < menusOutputModelLocal.getFooterMenuModel().size(); i++) {

                    if (menusOutputModelLocal.getFooterMenuModel().get(i).getDisplay_name().trim().equals(titleArray.get(listPosition))) {

                        if (menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink().equals("contactus")) {                         //   isNavigated = 1;

                            fragment = new ContactUsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("title", menusOutputModelLocal.getFooterMenuModel().get(i).getDisplay_name());
                            fragment.setArguments(bundle);
                            preferenceManager.setFRAGMENTS_CHANGED("contact_us");
                            preferenceManager.setFRAGMENTS_POSITION(i);
                            preferenceManager.setFRAGMENTS_PERMALINK("footer");
                            assert getFragmentManager() != null;
                            getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.CONTACT_US_FRAGMENT_TAG)).commitAllowingStateLoss();
                            getFragmentManager().executePendingTransactions();
                            mDrawerLayout.closeDrawers();
                        } else if (menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink().equals("support")) {                         //   isNavigated = 1;

                            fragment = new AdvanceContactUsFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("title", menusOutputModelLocal.getFooterMenuModel().get(i).getDisplay_name());
                            fragment.setArguments(bundle);
                            preferenceManager.setFRAGMENTS_CHANGED("support_to");
                            preferenceManager.setFRAGMENTS_POSITION(i);
                            preferenceManager.setFRAGMENTS_PERMALINK("footer");
                            // preferenceManager.setFRAGMENTS_TITLE(languagePreference.getTextofLanguage(MY_FAVOURITE, DEFAULT_MY_FAVOURITE));
                            assert getFragmentManager() != null;
                            getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.ADVANCE_CONTACT_US_FRAGMENT_TAG)).commitAllowingStateLoss();
                            getFragmentManager().executePendingTransactions();
                            mDrawerLayout.closeDrawers();
                        } else {
                            if (menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink().equals("my_plan")) // for Purchase history
                            {
                                mDrawerLayout.closeDrawers();


                            } else if (menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink().equals("purchase_history")) // for Purchase history
                            {
                                mDrawerLayout.closeDrawers();


                            } else if (menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink().equals("followings")) // for Purchase history
                            {

                            } else if (menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink().equals("myplaylist")) {

                            } else if (menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink().equals("settings")) // for Settings
                            {
                                mDrawerLayout.closeDrawers();
                                String termsprivacytitle = "";
                                for (int k = 0; k < menusOutputModelLocal.getFooterMenuModel().size(); k++) {
                                    if (menusOutputModelLocal.getFooterMenuModel().get(k).getPermalink().equals("terms-privacy-policy")) {
                                        termsprivacytitle = menusOutputModelLocal.getFooterMenuModel().get(k).getDisplay_name();
                                        break;
                                    }
                                }
                                Intent intent = new Intent(getContext(), SettingsActivity.class);
                                intent.putExtra("termstitle", termsprivacytitle);
                                //   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            } else if (menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink().trim().equalsIgnoreCase("2")) {
                                String externalLink = menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink().trim();
                                if (externalLink.equals("#"))
                                    externalLink = "http://";
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                                browserIntent.setData(Uri.parse(externalLink));
                                getActivity().startActivity(browserIntent);

                            }
                            /*
                             * @Desc: Here checking for the linkType==2 then opening a webview to see the content.
                             *        For handling null of permakink, i have used such type of procces instead.
                             * */
                            else if ("2".equals(menusOutputModelLocal.getFooterMenuModel().get(i).getLink_type())) {

                                mDrawerLayout.closeDrawers();

                                fragment = new WebViewFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("item", menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink());
                                bundle.putString("title", menusOutputModelLocal.getFooterMenuModel().get(i).getDisplay_name());
                                fragment.setArguments(bundle);
                                preferenceManager.setFRAGMENTS_CHANGED("webview_links");
                                preferenceManager.setFRAGMENTS_POSITION(i);
                                preferenceManager.setFRAGMENTS_PERMALINK("footer");
                                assert getFragmentManager() != null;
                                getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.WebView_FRAGMENT_TAG)).commitAllowingStateLoss();
                                getFragmentManager().executePendingTransactions();

                            } else {
                                // isNavigated = 1;
                                fragment = new AboutUsFragment();
                                Bundle bundle = new Bundle();
                                Log.v("SUBHA", "footerMenuModelArrayList.get(i).getPermalink()" + menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink());
                                bundle.putString("item", menusOutputModelLocal.getFooterMenuModel().get(i).getPermalink());
                                bundle.putString("title", menusOutputModelLocal.getFooterMenuModel().get(i).getDisplay_name());
                                fragment.setArguments(bundle);
                                preferenceManager.setFRAGMENTS_CHANGED("about_us");
                                preferenceManager.setFRAGMENTS_POSITION(i);
                                preferenceManager.setFRAGMENTS_PERMALINK("footer");
                                // preferenceManager.setFRAGMENTS_TITLE(languagePreference.getTextofLanguage(MY_FAVOURITE, DEFAULT_MY_FAVOURITE));
                                assert getFragmentManager() != null;
                                getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.ABOUT_US_FRAGMENT_TAG)).commitAllowingStateLoss();
                                getFragmentManager().executePendingTransactions();
                                mDrawerLayout.closeDrawers();

                            }

                        }

                    }
                }


                if (listPosition == 0) {
                    Fragment fragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("is_home_clicked", true);
                    fragment.setArguments(bundle);

                    preferenceManager.setFRAGMENTS_CHANGED("home");
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.HOME_FRAGMENT_TAG)).commitAllowingStateLoss();
                    getFragmentManager().executePendingTransactions();
                    mDrawerLayout.closeDrawers();
                }
                // this is for if child is not there then another fragment open
                else {
                    if (retVal) {

                        if (menusOutputModelLocal.getMainMenuModel().size() > listPosition) {
                            if (menusOutputModelLocal.getMainMenuModel().get(listPosition).getTitle().equals(languagePreference.getTextofLanguage(MY_LIBRARY, DEFAULT_MY_LIBRARY))) {
                                // isNavigated = 1;


                            } else if (menusOutputModelLocal.getMainMenuModel().get(listPosition).getTitle().equals(languagePreference.getTextofLanguage(WATCH_HISTORY, DEFAULT_WATCH_HISTORY))) {

                            } else if (menusOutputModelLocal.getMainMenuModel().get(listPosition).getTitle().equals(languagePreference.getTextofLanguage(MY_DOWNLOAD, DEFAULT_MY_DOWNLOAD))) {


                            }
                            /*
                             * @desc:The below condition is for checking Blog type and Help center type using linktype=3.
                             * */

                            else if ("3".equals(menusOutputModelLocal.getMainMenuModel().get(listPosition).getLink_type())) {
                                // Condition for BLOG Permalink
                                if (menusOutputModelLocal.getMainMenuModel().get(listPosition).getPermalink().equals("blog")) {
                                    mDrawerLayout.closeDrawers();
                                    String web_link = "https://" + getResources().getString(R.string.host_name) + "/" +
                                            languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE) + "/blog";

                                    fragment = new WebViewFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("item", web_link);
                                    bundle.putString("title", menusOutputModelLocal.getMainMenuModel().get(listPosition).getTitle());
                                    fragment.setArguments(bundle);
                                    preferenceManager.setFRAGMENTS_CHANGED("webview_links");
                                    preferenceManager.setFRAGMENTS_POSITION(listPosition);
                                    preferenceManager.setFRAGMENTS_PERMALINK("menu");
                                    assert getFragmentManager() != null;
                                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.WebView_FRAGMENT_TAG)).commitAllowingStateLoss();
                                    getFragmentManager().executePendingTransactions();
                                }
                                //Condition for MUVIKART permalink
                                else if (menusOutputModelLocal.getMainMenuModel().get(listPosition).getPermalink().equals("faqs")) {
                                    mDrawerLayout.closeDrawers();
                                    String web_link = "https://" + getResources().getString(R.string.host_name) + "/" +
                                            languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE) + "/faqs";

                                    fragment = new WebViewFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("item", web_link);
                                    bundle.putString("title", menusOutputModelLocal.getMainMenuModel().get(listPosition).getTitle());
                                    fragment.setArguments(bundle);
                                    preferenceManager.setFRAGMENTS_CHANGED("webview_links");
                                    preferenceManager.setFRAGMENTS_POSITION(listPosition);
                                    preferenceManager.setFRAGMENTS_PERMALINK("menu");
                                    assert getFragmentManager() != null;
                                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.WebView_FRAGMENT_TAG)).commitAllowingStateLoss();
                                    getFragmentManager().executePendingTransactions();

                                } else if (menusOutputModelLocal.getMainMenuModel().get(listPosition).getPermalink().equals("muvikart") ||
                                        menusOutputModelLocal.getMainMenuModel().get(listPosition).getPermalink().equals("playout")) {

                                    mDrawerLayout.closeDrawers();

                                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
                                    dlgAlert.setMessage(languagePreference.getTextofLanguage(FEATURE_UNSUPPORTED_MESSAGE, DEFAULT_FEATURE_UNSUPPORTED_MESSAGE));
                                    dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                                    dlgAlert.setCancelable(false);
                                    dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();

                                                }
                                            });
                                    dlgAlert.create().show();
                                }
                            } else {

                                if (menusOutputModelLocal.getMainMenuModel().get(listPosition).getLink_type() != null && menusOutputModelLocal.getMainMenuModel().get(listPosition).getLink_type().equalsIgnoreCase("2")) {
                                    //  isNavigated = 1;

                                    // getFragmentManager().beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss();
                                    getFragmentManager().executePendingTransactions();
                                    mDrawerLayout.openDrawer(Gravity.LEFT);
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(menusOutputModelLocal.getMainMenuModel().get(listPosition).getPermalink().trim()));
                                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(browserIntent);
                                    return retVal;


                                } else {
                                    // isNavigated = 1;
                                    // isNavigated = 1;
                                    if (getContext().getPackageName().equals("com.release.alerttheglobe") && menusOutputModelLocal.getMainMenuModel().get(listPosition).getLink_type().equals("1")) {
                                        String title = menusOutputModelLocal.getMainMenuModel().get(listPosition).getTitle();
                                        String permalink = menusOutputModelLocal.getMainMenuModel().get(listPosition).getPermalink();
                                        if (permalink.equals("about-us")) {
                                            mDrawerLayout.closeDrawers();
                                            Intent intent = new Intent(getContext(), AboutUsActivity.class);
                                            intent.putExtra("title", title);
                                            intent.putExtra("permalink", permalink);
                                            startActivity(intent);
                                        }
                                        return true;
                                    }


                                    /**
                                     * @desc: ER-62156 | For Walden Customer requirements we have added this
                                     */
                                    if (getActivity().getPackageName().equals("edu.waldenu.stream")) {
                                        try {
                                            // String permalink = getArguments().getString("item");
                                            //Toast.makeText(getActivity(), permalink, Toast.LENGTH_SHORT).show();
                                            String permalink = menusOutputModelLocal.getMainMenuModel().get(preferenceManager.getFRAGMENTS_POSITION()).getPermalink();
                                            if (!permalink.contains("public-videos") && loggedInStr == null) {
                                                final Intent startIntent = new Intent(getActivity(), LoginActivity.class);
                                                startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                startActivity(startIntent);

                                                return true;
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }


                                    fragment = getFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", menusOutputModelLocal.getMainMenuModel().get(listPosition).getTitle());
                                    bundle.putString("item", menusOutputModelLocal.getMainMenuModel().get(listPosition).getPermalink());
                                    fragment.setArguments(bundle);
                                    preferenceManager.setFRAGMENTS_POSITION(listPosition);
                                    preferenceManager.setFRAGMENTS_CHANGED("video");
                                    preferenceManager.setFRAGMENTS_PERMALINK("menu");
                                    preferenceManager.setFRAGMENTS_SELECTED_PERMALINK(menusOutputModelLocal.getMainMenuModel().get(listPosition).getTitle());
                                    assert getFragmentManager() != null;
                                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.VIDEO_LIST_FRAGMENT_TAG)).commitAllowingStateLoss();
                                    getFragmentManager().executePendingTransactions();
                                    mDrawerLayout.closeDrawers();

                                }
                            }
                        }
                    }
                }

                return retVal;
            }


        });
        mDrawerListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int listPosition, int childPosition, long id) {

                String ParentId = menusOutputModelLocal.getMainMenuModel().get(listPosition).getId();
                arrayList = new ArrayList<Integer>();

                for (int i = 0; i < menusOutputModelLocal.getMainMenuModel().get(listPosition).getMainMenuChildModel().size(); i++) {
                    if (menusOutputModelLocal.getMainMenuModel().get(listPosition).getMainMenuChildModel().get(i).getParent_id().equals(ParentId)) {
                        arrayList.add(i);
                    }
                }


                Fragment fragment = getFragment();
                Bundle args = new Bundle();
                args.putString("title", menusOutputModelLocal.getMainMenuModel().get(listPosition).getMainMenuChildModel().get(arrayList.get(childPosition)).getTitle());
                args.putString("item", menusOutputModelLocal.getMainMenuModel().get(listPosition).getMainMenuChildModel().get(arrayList.get(childPosition)).getPermalink());
                preferenceManager.setFRAGMENTS_CHANGED("video");
                preferenceManager.setFRAGMENTS_POSITION(childPosition);
                Log.d("cchild", String.valueOf(childPosition));
                preferenceManager.setFRAGMENTS_TITLE("" + listPosition);
                preferenceManager.setFRAGMENTS_PERMALINK("sub_menu");
                fragment.setArguments(args);


                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.VIDEO_LIST_FRAGMENT_TAG)).commitAllowingStateLoss();
                getFragmentManager().executePendingTransactions();
                mDrawerLayout.closeDrawers();

                return true;
            }
        });
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);


        View header = inflater.inflate(R.layout.drawer_header, null);
        mDrawerListView.addHeaderView(header);
        ImageView drawerlogo = mDrawerListView.findViewById(R.id.drawerlogo);
        profile_icon = mDrawerListView.findViewById(R.id.profile);
        LinearLayout logout_header = mDrawerListView.findViewById(R.id.logout_header);
        LinearLayout loggedin_header = mDrawerListView.findViewById(R.id.loggedin_header);
        name = mDrawerListView.findViewById(R.id.name);
        TextView manage_profile = mDrawerListView.findViewById(R.id.manage_profile);
        LinearLayout arrow = mDrawerListView.findViewById(R.id.arrow);
        backArrow = mDrawerListView.findViewById(R.id.backArrow);

        if (loggedInStr != null) {
            loggedin_header.setVisibility(View.VISIBLE);
            logout_header.setVisibility(View.GONE);
        } else {
            loggedin_header.setVisibility(View.GONE);
            logout_header.setVisibility(View.VISIBLE);
        }

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
                mDrawerLayout.closeDrawers();

            }
        });

        manage_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
                mDrawerLayout.closeDrawers();

            }
        });

        return mDrawerListView;
    }

    private Fragment getFragment() {

        return new VideosListFragment();
    }


    private String getTag(int value) {
        return getResources().getString(value);
    }


    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        try {
            ActionBar actionBar = getActionBar();

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        } catch (Exception e) {
        }
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                if (!isAdded()) {
                    return;
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                name.setText(preferenceManager.getDispNameFromPref());

                if (preferenceManager.getLoginProfImgFromPref() != null) {
                    profileImage = preferenceManager.getLoginProfImgFromPref();
                    if (profileImage != null && profileImage.length() > 0) {
                        int pos = profileImage.lastIndexOf("/");
                        String x = profileImage.substring(pos + 1, preferenceManager.getLoginProfImgFromPref().length());

                        if (x.equals("no-image.png") || x.equals("no-user.png")) {
                            profile_icon.setImageResource(R.drawable.ic_end_user);

                        } else {
                            Picasso.with(getActivity())
                                    .load(profileImage)
                                    .placeholder(R.drawable.ic_end_user)
                                    .into(profile_icon);
                        }
                    }
                }
                setMenuItemsInDrawer(false);
            }
        };


        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }

        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        if (mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.menu_main, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle == null) {
            return false;
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onGet_UserProfilePreExecuteStarted() {

    }

    @Override
    public void onGet_UserProfilePostExecuteCompleted(Get_UserProfile_Output get_userProfile_output, int code, String message, String status) {
        if (code == 200) {

            if (get_userProfile_output != null) {
                profileImage = get_userProfile_output.getProfile_image();
                String display_name = get_userProfile_output.getDisplay_name();
                name.setText(display_name);

                if (profileImage != null && profileImage.length() > 0) {
                    int pos = profileImage.lastIndexOf("/");
                    String x = profileImage.substring(pos + 1, profileImage.length());

                    if (x.equals("no-image.png")) {
                        profile_icon.setImageResource(R.drawable.ic_end_user);

                    } else {
                        Picasso.with(getActivity())
                                .load(profileImage)
                                .placeholder(R.drawable.ic_end_user)
                                .into(profile_icon);
                    }
                }
            }
        }

    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }


    public void setMenuItemsInDrawer(boolean loadHomeFragment) {
        featureHandler = FeatureHandler.getFeaturePreference(getActivity());

        try {
            if (!loadHomeFragment)
                Util.main_menu_list_size = menusOutputModelLocal.getMainMenuModel().size();

        } catch (Exception e) {
            Util.main_menu_list_size = -2;
        }

        loggedInStr = preferenceManager.getUseridFromPref();
        expandableListDetail.clear();
        menusOutputModelLocal = new MenusOutputModel();
        try {
            menusOutputModelLocal.getMainMenuModel().addAll(menusOutputModelFromAPI.getMainMenuModel());
            menusOutputModelLocal.getFooterMenuModel().addAll(menusOutputModelFromAPI.getFooterMenuModel());
            titleArray.clear();
            idArray.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Adding Home Menu*/
        MenusOutputModel.MainMenu mainMenuHome = new MenusOutputModel.MainMenu();
        mainMenuHome.setTitle(languagePreference.getTextofLanguage(HOME, DEFAULT_HOME));
        menusOutputModelLocal.getMainMenuModel().add(0, mainMenuHome);

        /**
         * @description For adding settings option
         */
        try {
            MenusOutputModel.FooterMenu footerMenu = new MenusOutputModel.FooterMenu();
            footerMenu.setDisplay_name(languagePreference.getTextofLanguage(SETTINGS, DEFAULT_SETTINGS));
            footerMenu.setPermalink("settings");

            menusOutputModelLocal.getFooterMenuModel().add(1, footerMenu);
        } catch (Exception e) {

        }


        /* Adding Contact Us Menu*/
        try {
            MenusOutputModel.FooterMenu footerMenu = new MenusOutputModel.FooterMenu();
            footerMenu.setDisplay_name(languagePreference.getTextofLanguage(CONTACT_US, DEFAULT_CONTACT_US));
            footerMenu.setPermalink("contactus");

            menusOutputModelLocal.getFooterMenuModel().add(2, footerMenu);


        } catch (Exception e) {

        }


        if (menusOutputModelLocal.getMainMenuModel() != null && menusOutputModelLocal.getMainMenuModel().size() > 0) {

            for (int i = 0; i < menusOutputModelLocal.getMainMenuModel().size(); i++) {
                titleArray.add(menusOutputModelLocal.getMainMenuModel().get(i).getTitle());
                idArray.add(menusOutputModelLocal.getMainMenuModel().get(i).getId());
                ArrayList<String> childArray = new ArrayList<>();

                for (int j = 0; j < menusOutputModelLocal.getMainMenuModel().get(i).getMainMenuChildModel().size(); j++) {
                    if (menusOutputModelLocal.getMainMenuModel().get(i).getId().equals(menusOutputModelLocal.getMainMenuModel().get(i).getMainMenuChildModel().get(j).getParent_id())) {
                        childArray.add(menusOutputModelLocal.getMainMenuModel().get(i).getMainMenuChildModel().get(j).getTitle());
                    }
                }
                expandableListDetail.put(menusOutputModelLocal.getMainMenuModel().get(i).getId(), childArray);
            }
        }


        for (int k = 0; k < menusOutputModelLocal.getFooterMenuModel().size(); k++) {
            /**
             * @author Debashish
             * @description Filtering out Footer items for permalinks "about-us" &  "terms-privacy-policy"
             */
            try {
                MenusOutputModel.FooterMenu footerMenu = menusOutputModelLocal.getFooterMenuModel().get(k);
                if (footerMenu != null &&
                        !footerMenu.getPermalink().equals("about-us") &&
                        !footerMenu.getPermalink().equals("terms-privacy-policy")) {
                    titleArray.add(menusOutputModelLocal.getFooterMenuModel().get(k).getDisplay_name());
                    idArray.add(menusOutputModelLocal.getFooterMenuModel().get(k).getId());
                    ArrayList<String> childArray = new ArrayList<>();

                    expandableListDetail.put(menusOutputModelLocal.getFooterMenuModel().get(k).getId(), childArray);

                } else {


                    if (footerMenu != null && footerMenu.getPermalink().equals("terms-privacy-policy")) {
                        featureHandler.setFeatureFlag(FeatureHandler.IS_TERMS_PRIVACY_AVAILABLE, "1");
                    }

                    if (footerMenu != null && footerMenu.getPermalink().equals("about-us")) {
                        featureHandler.setFeatureFlag(FeatureHandler.IS_ABOUT_US_AVAILABLE, "1");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }


        try {
            Util.main_menu_list_size = menusOutputModelLocal.getMainMenuModel().size();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }

            /**
             * @description For right aligning the arrrow indicator
             */

            setGroupIndicatorToRight();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (loadHomeFragment) {
            if (!opened) {
                if (preferenceManager.getFRAGMENTS_CHANGED().equalsIgnoreCase("home")) {
                    Fragment fragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.HOME_FRAGMENT_TAG)).commitAllowingStateLoss();
                    getFragmentManager().executePendingTransactions();
                } else if (preferenceManager.getFRAGMENTS_CHANGED().equalsIgnoreCase("video")) {
                    if (preferenceManager.getFRAGMENTS_PERMALINK().equalsIgnoreCase("menu")) {

                        fragment = getFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("title", menusOutputModelLocal.getMainMenuModel().get(preferenceManager.getFRAGMENTS_POSITION()).getTitle());
                        bundle.putString("item", menusOutputModelLocal.getMainMenuModel().get(preferenceManager.getFRAGMENTS_POSITION()).getPermalink());


                        fragment.setArguments(bundle);
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.VIDEO_LIST_FRAGMENT_TAG)).commitAllowingStateLoss();
                        getFragmentManager().executePendingTransactions();
                    } else if (preferenceManager.getFRAGMENTS_PERMALINK().equalsIgnoreCase("sub_menu")) {
                        arrayList = new ArrayList<>();
                        String ParentId = menusOutputModelLocal.getMainMenuModel().get(Integer.parseInt(preferenceManager.getFRAGMENTS_TITLE())).getId();
                        arrayList = new ArrayList<Integer>();
                        for (int i = 0; i < menusOutputModelLocal.getMainMenuModel().get(Integer.parseInt(preferenceManager.getFRAGMENTS_TITLE())).getMainMenuChildModel().size(); i++) {
                            if (menusOutputModelLocal.getMainMenuModel().get(Integer.parseInt(preferenceManager.getFRAGMENTS_TITLE())).getMainMenuChildModel().get(i).getParent_id().equals(ParentId)) {
                                arrayList.add(i);
                            }
                        }

                        fragment = getFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("title", menusOutputModelLocal.getMainMenuModel().get(Integer.parseInt(preferenceManager.getFRAGMENTS_TITLE())).getMainMenuChildModel().get(arrayList.get(preferenceManager.getFRAGMENTS_POSITION())).getTitle());
                        bundle.putString("item", menusOutputModelLocal.getMainMenuModel().get(Integer.parseInt(preferenceManager.getFRAGMENTS_TITLE())).getMainMenuChildModel().get(arrayList.get(preferenceManager.getFRAGMENTS_POSITION())).getPermalink());
                        String permalink = menusOutputModelLocal.getMainMenuModel().get(preferenceManager.getFRAGMENTS_POSITION()).getPermalink();
                        preferenceManager.setFRAGMENTS_PERMALINK(permalink);
                        fragment.setArguments(bundle);
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.VIDEO_LIST_FRAGMENT_TAG)).commitAllowingStateLoss();
                        getFragmentManager().executePendingTransactions();
                    }
                } else {
                    Fragment fragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    fragment.setArguments(bundle);
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, getTag(R.string.HOME_FRAGMENT_TAG)).commitAllowingStateLoss();
                    getFragmentManager().executePendingTransactions();
                }


            }
        }


    }

    /**
     * @description For Downdrown indicator setting right
     */
    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        mDrawerListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }


    public static void checkUserFeatureExists(MenusOutputModel menusOutputModelLocal,
                                              MenusOutputModel menusOutputModelFromAPI, LanguagePreference languagePreference) {



    }

}
