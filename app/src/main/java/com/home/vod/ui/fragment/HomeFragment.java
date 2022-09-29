package com.home.vod.ui.fragment;

import static android.app.Activity.RESULT_OK;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.TRY_AGAIN;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.Util.videoSurfaceView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.home.apisdk.apiController.GetAppHomeFeatureAsyncTask;
import com.home.apisdk.apiController.GetIpAddressAsynTask;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
import com.home.apisdk.apiModel.AppHomeFeatureInputModel;
import com.home.apisdk.apiModel.AppHomeFeatureOutputModel;
import com.home.apisdk.apiModel.GetVideoDetailsInput;
import com.home.apisdk.apiModel.HomeFeaturePageBannerModel;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.vod.BuildConfig;
import com.home.vod.R;
import com.home.vod.async.GetImageSize;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.model.DataModel;
import com.home.vod.model.GetMenuItem;
import com.home.vod.model.SectionDataModel;
import com.home.vod.model.SingleItemModel;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.service.MusicService;
import com.home.vod.ui.activity.GeoblockAlertActivity;
import com.home.vod.ui.activity.LoginActivity;
import com.home.vod.ui.activity.MainActivity;
import com.home.vod.ui.activity.ViewMoreActivity;
import com.home.vod.ui.adapter.RecyclerViewDataAdapter;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.AppThreadPoolExecutor;
import com.home.vod.util.AutoPlayUtil;
import com.home.vod.util.Constant;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;

import player.activity.AdPlayerActivity;
import player.activity.Player;

public class HomeFragment extends Fragment implements
        GetAppHomeFeatureAsyncTask.AppHomeFeature,
        GetIpAddressAsynTask.IpAddressListener,
        VideoDetailsAsynctask.VideoDetailsListener {

    public static SwipeRefreshLayout swipeRefreshLayout;
    private final String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewState;
    public static final int VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE = 8888;
    public static final int PAYMENT_REQUESTCODE = 8889;
    public static final int BANNER_PLAYBUTTON_CLICK = 1010;
    public static final int CHILD_CONTENT_CLICK = 1011;

    private int feature_section_limit = 5;
    private int feature_section_offset;
    private int section_count = 0;
    private int total_section = 0;
    private boolean call_next_data = false;
    public static boolean show_loader = true;
    public boolean start_api_call_after_scrolling = false;
    private boolean isHomePageCacheData = false;
    private Boolean pulltorefresh = false;
    private boolean firstTime = true;
    private boolean clickOnBanner = false;
    private static File mediaStorageDir;

    private String is_livestreamEnabled;
    private String livestream_resume_time;
    private String ipAddres = "";
    private String loggedInIdStr, guestUserld;

    private ArrayList<String> sectionImageList = new ArrayList<>();
    private ArrayList<GetMenuItem> menuList;
    private ArrayList<String> url_maps;
    private ArrayList<String> arrayListFeedUrl;
    private ArrayList<HomeFeaturePageBannerModel> homePageBannerModelArrayList;

    private View rootView;
    private RelativeLayout noInternetLayout;
    private RelativeLayout noDataLayout;
    private TextView noDataTextView;
    private TextView noInternetTextView;
    private RelativeLayout footerView;
    private RelativeLayout sliderRelativeLayout;
    private RecyclerView my_recycler_view;
    private Button btnRetry;
    private TextView oopsTextView;
    private ImageView cc_button;
    private LinearLayout mini_controller_layout;

    private ProgressBarHandler mProgressBarHandler = null;
    private LanguagePreference languagePreference;
    private Context context;
    private RecyclerViewDataAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private PreferenceManager preferenceManager;
    private FeatureHandler featureHandler;
    private Player playerModel;

    private ArrayList<SectionDataModel> allSampleData;
    private GetAppHomeFeatureAsyncTask getAppHomeFeatureAsyncTask = null;

    private final Executor threadPoolExecutor = AppThreadPoolExecutor.getInstance().getThreadPoolExecutor();


    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rootView = v;
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();



        context = getActivity();
        setHasOptionsMenu(true);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            boolean is_home_clicked = bundle.getBoolean("is_home_clicked", false);
            if (is_home_clicked) {
                firstTime = true;
            }
        }


        preferenceManager = PreferenceManager.getPreferenceManager(getActivity());
        languagePreference = LanguagePreference.getLanguagePreference(getActivity());
        featureHandler = FeatureHandler.getFeaturePreference(getActivity());
        mProgressBarHandler = new ProgressBarHandler(getActivity());


        //call ip address API to get device ip address
        GetIpAddressAsynTask getIpAddressAsynTask = new GetIpAddressAsynTask(this, context);
        getIpAddressAsynTask.execute();


        //Check app availability status
        checkGeoBlock();


        Toolbar toolbar = ((MainActivity) getActivity()).mToolbar;
        setIdToActionBarBackButton(toolbar);
        ((MainActivity) getActivity()).toolbarTitleHandler = new ToolbarTitleHandler((MainActivity) getActivity());

        allSampleData = new ArrayList<>();

        footerView = (RelativeLayout) v.findViewById(R.id.loadingPanel);
        my_recycler_view = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        sliderRelativeLayout = (RelativeLayout) v.findViewById(R.id.sliderRelativeLayout);
        Util.recyclerView = my_recycler_view;
        sliderRelativeLayout.setVisibility(View.GONE);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        oopsTextView = (TextView) rootView.findViewById(R.id.oopsTextView); // Added by Debashish
        btnRetry = (Button) rootView.findViewById(R.id.btnRetry); // Added by Debashish
        noInternetLayout = (RelativeLayout) rootView.findViewById(R.id.noInternet);
        noDataLayout = (RelativeLayout) rootView.findViewById(R.id.noData);
        noInternetTextView = (TextView) rootView.findViewById(R.id.noInternetTextView);
        noDataTextView = (TextView) rootView.findViewById(R.id.noDataTextView);
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noDataTextView.setText(languagePreference.getTextofLanguage(NO_CONTENT, DEFAULT_NO_CONTENT));

        btnRetry.setText("          " + languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN) + "       ");
        oopsTextView.setText(languagePreference.getTextofLanguage(OOPS, DEFAULT_OOPS));

        footerView.setVisibility(View.GONE);
        my_recycler_view.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.isScrolling = false;
            if (NetworkStatus.getInstance().isConnected(getActivity())) {

                if (noInternetLayout.getVisibility() == View.VISIBLE) {
                    View view = getView();
                    view.startAnimation(buttonClick);
                    restartFragment();
                } else {
                    //Release Banner Player

                    if (homePageBannerModelArrayList.size() > 0 || allSampleData.size() > 0
                            || url_maps.size() > 0 || homePageBannerModelArrayList.size() > 0) {
                        homePageBannerModelArrayList.clear();
                        allSampleData.clear();
                        url_maps.clear();
                        homePageBannerModelArrayList.clear();
                    }

                    getHomePageDetails(feature_section_limit, 1);

                }
            } else {
                swipeRefreshLayout.setRefreshing(false);
                noInternetLayout.setVisibility(View.VISIBLE);
            }
        });


        if (Util.isTablet(context)) {
            feature_section_limit = 6;
            feature_section_offset = 1;
        } else {
            feature_section_limit = 5;
            feature_section_offset = 1;
        }


        if (NetworkStatus.getInstance().isConnected(getActivity())) {
            menuList = new ArrayList<GetMenuItem>();
            url_maps = new ArrayList<String>();
            arrayListFeedUrl = new ArrayList<>();
            homePageBannerModelArrayList = new ArrayList<HomeFeaturePageBannerModel>();

            my_recycler_view.setLayoutManager(mLayoutManager);
            adapter = new RecyclerViewDataAdapter(context, ipAddres, allSampleData, url_maps, arrayListFeedUrl, homePageBannerModelArrayList, MainActivity.vertical, this,
                    (sectionName, sectionId) -> {
                        Context context = v.getContext();
                        Intent i = new Intent(context, ViewMoreActivity.class);
                        i.putExtra("SectionId", sectionId);
                        i.putExtra("sectionName", sectionName);
                        i.putExtra("ip_address", ipAddres);
                        context.startActivity(i);
                    },
                    model -> {
                        Context mContext = context;
                        childClickItem(mContext, model);
                    },
                    homeFeaturePageBannerModel -> {
                        Context mContext = context;

                        /* @desc:check if audio is playing or not. If audio is playing stop audio player*/
                        if (preferenceManager.getSongStatusFromPref() != null && preferenceManager.getSongStatusFromPref().equals("playing")) {
                            try {
                                Intent Pintent = new Intent(Constant.CLOSE_NOTIFICATION);
                                Pintent.putExtra("closeNotification", "close");
                                mContext.sendBroadcast(Pintent);
                                preferenceManager.setSongStatustoPref("close");
                                if (MusicService.mediaPlayer != null) {
                                    MusicService.mediaPlayer.setPlayWhenReady(false);
                                    MusicService.mediaPlayer.release();
                                }

                                mContext.stopService(new Intent(mContext, MusicService.class));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        checkOnclick(mContext, homeFeaturePageBannerModel);
                    });
            my_recycler_view.setAdapter(adapter);

            getHomePageDetails(feature_section_limit, feature_section_offset);

        } else {
            noInternetLayout.setVisibility(View.VISIBLE);
        }


        my_recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                int firstVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();


                final Rect actualPosition = new Rect();
                try {
                    videoSurfaceView.getGlobalVisibleRect(actualPosition);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final Rect screen = new Rect(0, 0, Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().heightPixels);

                adapter.isScrolling = true;

                if ((total_section > section_count) &&
                        start_api_call_after_scrolling &&
                        call_next_data && newState == 0 && !(swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing())) {
                    call_next_data = false;
                    start_api_call_after_scrolling = false;
                    show_loader = true;
                    feature_section_offset = feature_section_offset + 1;
                    getHomePageDetails(feature_section_limit, feature_section_offset);
                }
                if (newState == 0) {
                    start_api_call_after_scrolling = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    start_api_call_after_scrolling = true;
                }
            }
        });


        btnRetry.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            restartFragment();
        });
        return v;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (my_recycler_view != null) {
            mBundleRecyclerViewState = new Bundle();
            Parcelable listState = mLayoutManager.onSaveInstanceState();
            mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
        }
        super.onSaveInstanceState(outState);
    }


    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.menu);

            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                t.setId(R.id.page_title);
            }
        }
    }


    /**
     * This api result of API clubbing .
     */

    @Override
    public void AppHomeFeaturePreExecute() {
        if (!(swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing())) {
            if (show_loader && mProgressBarHandler != null) {
                mProgressBarHandler.show();
            }
        }
    }

    @Override
    public void AppHomeFeaturePostExecute(AppHomeFeatureOutputModel appHomeFeatureOutputModel, int status) {
        if (appHomeFeatureOutputModel.isGetNextSectionData()) {
            isHomePageCacheData = false;
            getNextSectionData();
            return;
        }

        if (!appHomeFeatureOutputModel.isCacheData()) {
            isHomePageCacheData = false;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (sectionImageList.size() == 0) {
                        timer.cancel();

                        if (appHomeFeatureOutputModel.getOffset() == 1) {
                            Util.image_orentiation.clear();
                            firstTime = true;
                        }
                        renderHomepage(appHomeFeatureOutputModel);
                    }
                }
            }, 0, 400);
        } else {
            isHomePageCacheData = true;
            Util.image_orentiation.clear();
            firstTime = true;
            allSampleData.clear();
            url_maps.clear();
            arrayListFeedUrl.clear();
            homePageBannerModelArrayList.clear();
            feature_section_offset = 1;

            Util.image_orentiation.clear();


            section_count = 0;
            total_section = 0;
            renderHomepage(appHomeFeatureOutputModel);
        }

    }


    public void calculateImageOrientation(String Url) {
        new GetImageSize(getActivity(), Url, new GetImageSize.ImageSizeInterface() {
            @Override
            public void onImageLoaded(int width, int height) {
                if (width > height) {
                    Util.image_orentiation.add(Constant.IMAGE_LANDSCAPE_CONST);
                } else {
                    Util.image_orentiation.add(Constant.IMAGE_PORTAIT_CONST);
                }

                try {
                    sectionImageList.remove(0);
                    if (sectionImageList.size() > 0) {
                        calculateImageOrientation(sectionImageList.get(0));
                    } else {

                        if (mProgressBarHandler != null && mProgressBarHandler.isShowing()) {
                            mProgressBarHandler.hide();
                            mProgressBarHandler = null;
                        }

                        if (adapter != null) {
                            notifyRecyclerView();
                            call_next_data = true;
                        } else {
                            if (pulltorefresh) call_next_data = true;
                            adapter = new RecyclerViewDataAdapter(context, ipAddres, allSampleData, url_maps, arrayListFeedUrl, homePageBannerModelArrayList, MainActivity.vertical, HomeFragment.this,
                                    (sectionName, sectionId) -> {
                                        Intent i = new Intent(context, ViewMoreActivity.class);
                                        i.putExtra("SectionId", sectionId);
                                        i.putExtra("sectionName", sectionName);
                                        i.putExtra("ip_address", ipAddres);
                                        context.startActivity(i);
                                    },
                                    model -> {
                                        Context mContext = context;
                                        childClickItem(mContext, model);
                                    },
                                    homeFeaturePageBannerModel -> {
                                        Context mContext = context;
                                        checkOnclick(mContext, homeFeaturePageBannerModel);
                                    });
                            my_recycler_view.setAdapter(adapter);

                        }

                        /**
                         * Following API method is responsible to get the next section data
                         */
                        getNextSectionData();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onImageLoadFailed(int width, int height) {

            }
        });
    }


    public void getNextSectionData() {
        if (isHomePageCacheData)
            return;

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (isLastVisible()) {

                    if ((total_section > section_count) && call_next_data) {

                        call_next_data = false;
                        show_loader = true;
                        feature_section_offset = feature_section_offset + 1;
                        getHomePageDetails(feature_section_limit, feature_section_offset);
                    }
                }
                timer.cancel();
            }
        }, 300, 1000);
    }

    boolean isLastVisible() {
        int pos = 0;
        int numItems = 0;

        try {
            LinearLayoutManager layoutManager = ((LinearLayoutManager) my_recycler_view.getLayoutManager());
            if (layoutManager != null) {
                pos = layoutManager.findLastVisibleItemPosition();
                numItems = my_recycler_view.getAdapter().getItemCount();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return (pos + 1 == numItems);
    }

    /**
     * This method is responsible to get homepage details API
     */
    private void getHomePageDetails(int featureSectionLimit, int featureSectionOffset) {

        AppHomeFeatureInputModel appHomeFeatureInputModel = new AppHomeFeatureInputModel();
        appHomeFeatureInputModel.setAuthToken(authTokenStr);

        if (preferenceManager.getUseridFromPref() != null && !preferenceManager.getUseridFromPref().equals("")) {
            appHomeFeatureInputModel.setUserId(preferenceManager.getUseridFromPref());
        } else {
            appHomeFeatureInputModel.setUserId("");
        }

        appHomeFeatureInputModel.setFeatureSectionLimit("" + featureSectionLimit);
        appHomeFeatureInputModel.setGetFeatureSectionOffset("" + featureSectionOffset);

        try {
            appHomeFeatureInputModel.setFeatureSectionContentLimit("" + (Integer.parseInt(getResources().getString(R.string.Feature_section_limit)) + 1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        appHomeFeatureInputModel.setGetFeatureSectionContentOffset("1");
        appHomeFeatureInputModel.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        appHomeFeatureInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
        appHomeFeatureInputModel.setState(preferenceManager.getStateFromPref());
        appHomeFeatureInputModel.setCity(preferenceManager.getCityFromPref());
        appHomeFeatureInputModel.setResult_type("1"); // content with Playlist


        /**
         * We have added a new request parameter "season_info" for to get season info
         */
        if (featureHandler.getFeatureStatus(FeatureHandler.IS_SEASON_PAGE_AVAILABLE, FeatureHandler.DEFAULT_IS_SEASON_PAGE_AVAILABLE)) {
            appHomeFeatureInputModel.setSeason_info("1");
        }


        getAppHomeFeatureAsyncTask = new GetAppHomeFeatureAsyncTask(appHomeFeatureInputModel, this, context, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(getActivity()));
        getAppHomeFeatureAsyncTask.executeOnExecutor(threadPoolExecutor);

    }


    public void restartFragment() {
        try {
            Intent mIntent = new Intent(getActivity(), MainActivity.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(mIntent);
            getActivity().finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startLoader() {
        try {
            if (mProgressBarHandler != null && !mProgressBarHandler.isShowing())
                mProgressBarHandler.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopLoader() {
        try {
            if (mProgressBarHandler != null && mProgressBarHandler.isShowing())
                mProgressBarHandler.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (preferenceManager != null) {
                loggedInIdStr = preferenceManager.getUseridFromPref();
                guestUserld = preferenceManager.getGuestUseridFromPref();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (requestCode == BANNER_PLAYBUTTON_CLICK && resultCode == RESULT_OK) {
            getVideoInfo();
        } else if (requestCode == CHILD_CONTENT_CLICK && resultCode == RESULT_OK) {
            getVideoInfo();
        } else if (requestCode == VIDEO_PLAY_BUTTON_CLICK_LOGIN_REG_REQUESTCODE && resultCode == RESULT_OK) {
            getVideoInfo();
        }
    }

    @Override
    public void onIPAddressPreExecuteStarted() {

    }

    @Override
    public void onIPAddressPostExecuteCompleted(String message, int statusCode, String ipAddressStr) {
        ipAddres = ipAddressStr;
        AutoPlayUtil.ipAddress = ipAddres;
    }



    public void renderHomepage(AppHomeFeatureOutputModel appHomeFeatureOutputModel) {

        try {
            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
                firstTime = true;
                allSampleData.clear();
                url_maps.clear();
                arrayListFeedUrl.clear();
                homePageBannerModelArrayList.clear();
                feature_section_offset = 1;

                Util.image_orentiation.clear();


                section_count = 0;
                total_section = 0;
                pulltorefresh = true;
                start_api_call_after_scrolling = false;
                if (adapter != null) {
                    notifyRecyclerView();
                }

            }
            if (homePageBannerModelArrayList != null || url_maps != null || arrayListFeedUrl != null || menuList != null) {
                homePageBannerModelArrayList.clear();
                url_maps.clear();
                arrayListFeedUrl.clear();
                menuList.clear();
            }

            show_loader = false;
            total_section = appHomeFeatureOutputModel.getTotal_section();
            section_count = section_count + appHomeFeatureOutputModel.getHomePageSectionModelArrayList().size();

            if (firstTime) {
                if (allSampleData != null && allSampleData.size() > 0) {
                    allSampleData.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (appHomeFeatureOutputModel == null) {
            if (mProgressBarHandler != null && mProgressBarHandler.isShowing()) {
                mProgressBarHandler.hide();
                mProgressBarHandler = null;
            }
            return;
        }

        for (int i = 0; i < appHomeFeatureOutputModel.getHomePageBannerModelArrayList().size(); i++) {
            if ((appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getImage_path() != null)
                    && (!appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getImage_path().equals(""))) {

                url_maps.add(appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getImage_path().trim());
                arrayListFeedUrl.add(appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getFeed_url().trim());

                try {
                    homePageBannerModelArrayList.add(
                            new HomeFeaturePageBannerModel(
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getImage_path(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getBanner_url(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getBanner_permalink(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getBanner_type(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getOther_sub_type(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getWeb_link(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getBanner_text(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getContent_types_id(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getTitle(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getButton_type(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getVideo_on_banner(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getIs_converted(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getButton_text(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getParent_title(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getStory(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getMuvi_uniq_id(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getMovie_stream_uniq_id(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getSeason_no(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getEpisode_no(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getVideo_duration(),
                                    appHomeFeatureOutputModel.getHomePageBannerModelArrayList().get(i).getPoster_url()));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        if (firstTime) {
            my_recycler_view.setVisibility(View.VISIBLE);
        }


        try {
            for (int j = 0; j < appHomeFeatureOutputModel.getHomePageSectionModelArrayList().size(); j++) {


                menuList.add(new GetMenuItem(appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(j).getTitle(),
                        appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(j).getSection_id(),
                        appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(j).getStudio_id(),
                        appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(j).getLanguage_id()));

            }

            sectionImageList.clear();


            for (int k = 0; k < appHomeFeatureOutputModel.getHomePageSectionModelArrayList().size(); k++) {

                ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();

                /**
                 * @author Bibhu
                 * Adding first content as layout chooser
                 */

                boolean add_to_list_orientation_checker = true;

                String movieImageStr = "";
                for (int l = 0; l < appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().size(); l++) {
                    String movieName = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getName();
                    String videoTypeIdStr = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getContent_types_id();
                    String play_list_type = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getPlay_list_type();
                    String is_play_list = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getIs_play_list();
                    String list_id = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getList_id();
                    String poster_url = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getPoster_url();
                    String name = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getName();
                    String movieGenreStr = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getGenre();
                    String moviePermalinkStr = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getPermalink();
                    String seasonPermalink = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getSeason_permalink();
                    String isEpisodeStr = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getIs_episode();
                    String seasonNo = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getSeasonNo();
                    String episodeNo = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getEpisodeNo();
                    String parent_poster_for_mobile_apps = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getParent_poster_for_mobile_apps();
                    String parent_title = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getParent_title();
                    String video_duration = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getVideo_duration();
                    String story = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getStory();

                    if (seasonNo == null) seasonNo = "";
                    if (episodeNo == null) episodeNo = "";
                    String movieUniqueId = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getMuvi_uniq_id();
                    String streamUniqueId = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getMovie_stream_uniq_id();

                    int isConverted;
                    int is_media_publish;
                    try {
                        isConverted = Integer.parseInt(appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getIs_converted());
                        is_media_publish = Integer.parseInt(appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getIs_media_published());
                    } catch (Exception e) {
                        isConverted = 0;
                        is_media_publish = 0;
                    }

                    int isPPV = 0;
                    int isAPV = 0;


                    /**
                     * desc: This method will check wheather the content comming from server is AOD or  video type.
                     */
                    if (Util.isVideoContent(videoTypeIdStr)) {
                        Util.APP_TYPE = Util.VOD;
                    } else if (Util.isAudioContent(videoTypeIdStr)) {
                        Util.APP_TYPE = Util.AOD;
                    }

                    if ((Util.getFilterContent(Util.APP_TYPE, videoTypeIdStr)) || (play_list_type.equals("1")) || (play_list_type.equals("2"))) {

                        if (add_to_list_orientation_checker) {
                            add_to_list_orientation_checker = false;
                            movieImageStr = appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getPoster_url();
                        }
                        singleItem.add(new SingleItemModel(appHomeFeatureOutputModel.getHomePageSectionModelArrayList().get(k).getHomeFeaturePageSectionDetailsModel().get(l).getPoster_url(),
                                movieName, "", videoTypeIdStr, movieGenreStr, "", moviePermalinkStr, isEpisodeStr, seasonNo, episodeNo, movieUniqueId, streamUniqueId, isConverted, isPPV,
                                isAPV, is_media_publish, play_list_type, is_play_list, list_id, poster_url, name, seasonPermalink, parent_poster_for_mobile_apps, parent_title, video_duration, story));
                    }
                }

                /**
                 * This is done to restrict physical section to display.
                 */
                if (singleItem.size() > 0) {
                    /**
                     *
                     * @description: This block of code is used to restrict duplicate feature section entry (ER:#18467)
                     */
                    if (allSampleData.size() > 0) {
                        boolean isFound = false;
                        for (int x = 0; x < allSampleData.size(); x++) {
                            if (allSampleData.get(x).getHeaderPermalink().equals(menuList.get(k).getSectionId())) {
                                isFound = true;
                                break;
                            }
                        }
                        if (!isFound) {
                            sectionImageList.add(movieImageStr);
                            allSampleData.add(new SectionDataModel(menuList.get(k).getName(), menuList.get(k).getSectionId(), singleItem));

                        }
                    } else {
                        sectionImageList.add(movieImageStr);
                        allSampleData.add(new SectionDataModel(menuList.get(k).getName(), menuList.get(k).getSectionId(), singleItem));

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (NetworkStatus.getInstance().isConnected(getActivity())) {

            if (getActivity() != null && sectionImageList.size() > 0) {

                if (firstTime && Util.image_orentiation.size() > 0) {
                    startPostProcessing();
                } else {
                    calculateImageOrientation(sectionImageList.get(0));
                }
            } else {
                /**
                 *
                 * Desc: To load the banner if the feature section part is null of No feature section part is present
                 */

                try {
                    if (adapter != null)
                        notifyRecyclerView();
                    if (mProgressBarHandler != null && mProgressBarHandler.isShowing()) {
                        mProgressBarHandler.hide();
                        mProgressBarHandler = null;
                    }
                } catch (Exception e) {
                    e.toString();
                }

            }

        } else {
            if (mProgressBarHandler != null && mProgressBarHandler.isShowing()) {
                mProgressBarHandler.hide();
                mProgressBarHandler = null;
            }
        }

        firstTime = false;
    }


    /**
     * Following method is responsible to handle pre-processing before display the content on screen.
     */
    public void startPostProcessing() {

        if (mProgressBarHandler != null && mProgressBarHandler.isShowing()) {
            mProgressBarHandler.hide();
            mProgressBarHandler = null;
        }

        if (adapter != null && !pulltorefresh) {
            call_next_data = true;
            displayData();
        } else {
            if (pulltorefresh) call_next_data = true;
            displayData();
        }

    }


    /**
     * Following method is responsible for the display homepage data.
     */
    public void displayData() {
        try {
            adapter = new RecyclerViewDataAdapter(context, ipAddres, allSampleData, url_maps, arrayListFeedUrl, homePageBannerModelArrayList, MainActivity.vertical, HomeFragment.this,
                    (sectionName, sectionId) -> {
                        Intent i = new Intent(context, ViewMoreActivity.class);
                        i.putExtra("SectionId", sectionId);
                        i.putExtra("sectionName", sectionName);
                        i.putExtra("ip_address", ipAddres);
                        context.startActivity(i);
                    },
                    model -> {
                        Context mContext = context;
                        childClickItem(mContext, model);
                    },
                    homeFeaturePageBannerModel -> {
                        Context mContext = context;
                        checkOnclick(mContext, homeFeaturePageBannerModel);
                    });
            my_recycler_view.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void childClickItem(Context mContext, SingleItemModel singleItemModel) {
        clickOnBanner = false;
        playerModel = new Player();
        playerModel.setStreamUniqueId(singleItemModel.getMovieStreamUniqueId());
        playerModel.setMovieUniqueId(singleItemModel.getMovieUniqueId());
        playerModel.setUserId(preferenceManager.getUseridFromPref());
        playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
        playerModel.setAuthTokenStr(authTokenStr.trim());
        playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH);
        playerModel.setEpisode_id(singleItemModel.getMovieStreamUniqueId());
        playerModel.setVideoTitle(singleItemModel.getTitle());
        playerModel.setParent_name(singleItemModel.getParent_title());
        playerModel.setVideoStory(singleItemModel.getStory());
        playerModel.setVideoDuration(singleItemModel.getVideo_duration());
        playerModel.setContentTypesId(Integer.parseInt(singleItemModel.getVideoTypeId()));
        playerModel.setPosterImageId(singleItemModel.getPoster_url());
        playerModel.setSeries_number(singleItemModel.getSeasonNo());
        playerModel.setEpisode_number(singleItemModel.getEpisodeNo());

        DataModel dbModel = new DataModel();
        dbModel.setIsConverted(singleItemModel.getIsConverted());
        dbModel.setMovieUniqueId(singleItemModel.getMovieUniqueId());
        dbModel.setStreamUniqueId(singleItemModel.getMovieStreamUniqueId());
        dbModel.setVideoTitle(singleItemModel.getParent_title());
        dbModel.setEpisode_title(singleItemModel.getTitle());
        dbModel.setVideoStory(singleItemModel.getStory());
        dbModel.setVideoDuration(singleItemModel.getVideo_duration());
        dbModel.setEpisode_id(singleItemModel.getMovieStreamUniqueId());
        dbModel.setSeason_id(singleItemModel.getSeasonNo());
        dbModel.setPurchase_type("episode");
        dbModel.setPosterImageId(singleItemModel.getPoster_url());
        dbModel.setContentTypesId(Integer.parseInt(singleItemModel.getVideoTypeId()));
        dbModel.setEpisode_series_no(singleItemModel.getSeasonNo());
        dbModel.setEpisode_no(singleItemModel.getEpisodeNo());

        Util.check_for_subscription = 1;
        Util.dataModel = dbModel;

        AutoPlayUtil.callAutoPlayAPI(singleItemModel.getMovieStreamUniqueId(), getContext(), playerModel);

        if (preferenceManager != null) {
            loggedInIdStr = preferenceManager.getUseridFromPref();
            guestUserld = preferenceManager.getGuestUseridFromPref();
        }

        if ((preferenceManager.getLoginFeatureFromPref() == 1)) {
            if (loggedInIdStr == null && guestUserld == null) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("PlayerModel", playerModel);
                intent.putExtra("is_a_guest_user", "is_a_guest_user");
                startActivityForResult(intent, CHILD_CONTENT_CLICK);
            } else {
                if (NetworkStatus.getInstance().isConnected(context)) {
                    getVideoInfo();
                } else {
                    Toast.makeText(getContext(), languagePreference.getTextofLanguage(player.utils.Util.NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            if (NetworkStatus.getInstance().isConnected(context)) {
                getVideoInfo();
            } else {
                Toast.makeText(mContext, languagePreference.getTextofLanguage(player.utils.Util.NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
            }
        }
    }


    private void checkOnclick(Context mContext, HomeFeaturePageBannerModel homeFeaturePageBannerModel) {

        clickOnBanner = true;

        playerModel = new Player();

        playerModel.setStreamUniqueId(homeFeaturePageBannerModel.getMovie_stream_uniq_id());
        playerModel.setMovieUniqueId(homeFeaturePageBannerModel.getMuvi_uniq_id());
        playerModel.setUserId(preferenceManager.getUseridFromPref());
        playerModel.setEmailId(preferenceManager.getEmailIdFromPref());
        playerModel.setAuthTokenStr(authTokenStr.trim());
        playerModel.setRootUrl(BuildConfig.SERVICE_BASE_PATH);
        playerModel.setEpisode_id(homeFeaturePageBannerModel.getMovie_stream_uniq_id());
        playerModel.setVideoTitle(homeFeaturePageBannerModel.getTitle());
        playerModel.setParent_name(homeFeaturePageBannerModel.getParent_title());
        playerModel.setVideoStory(homeFeaturePageBannerModel.getStory());
        playerModel.setVideoDuration(homeFeaturePageBannerModel.getVideo_duration());
        playerModel.setContentTypesId(Integer.parseInt(homeFeaturePageBannerModel.getContent_types_id()));
        playerModel.setPosterImageId(homeFeaturePageBannerModel.getPoster_url());

        playerModel.setSeries_number(homeFeaturePageBannerModel.getSeason_no());
        playerModel.setEpisode_number(homeFeaturePageBannerModel.getEpisode_no());


        DataModel dbModel = new DataModel();
        dbModel.setIsConverted(Integer.parseInt(homeFeaturePageBannerModel.getIs_converted()));
        dbModel.setMovieUniqueId(homeFeaturePageBannerModel.getMuvi_uniq_id());
        dbModel.setStreamUniqueId(homeFeaturePageBannerModel.getMovie_stream_uniq_id());
        dbModel.setVideoTitle(homeFeaturePageBannerModel.getParent_title());
        dbModel.setEpisode_title(homeFeaturePageBannerModel.getTitle());
        dbModel.setVideoStory(homeFeaturePageBannerModel.getStory());
        dbModel.setVideoDuration(homeFeaturePageBannerModel.getVideo_duration());
        dbModel.setEpisode_id(homeFeaturePageBannerModel.getMovie_stream_uniq_id());
        dbModel.setSeason_id(homeFeaturePageBannerModel.getSeason_no());
        dbModel.setPurchase_type("episode");
        dbModel.setPosterImageId(homeFeaturePageBannerModel.getPoster_url());
        dbModel.setContentTypesId(Integer.parseInt(homeFeaturePageBannerModel.getContent_types_id()));
        dbModel.setEpisode_series_no(homeFeaturePageBannerModel.getSeason_no());
        dbModel.setEpisode_no(homeFeaturePageBannerModel.getEpisode_no());

        Util.check_for_subscription = 1;

        Util.dataModel = dbModel;

        AutoPlayUtil.callAutoPlayAPI(homeFeaturePageBannerModel.getMovie_stream_uniq_id(), getContext(), playerModel);

        if (preferenceManager != null) {
            loggedInIdStr = preferenceManager.getUseridFromPref();
            guestUserld = preferenceManager.getGuestUseridFromPref();
        }

        if ((preferenceManager.getLoginFeatureFromPref() == 1)) {
            if (loggedInIdStr == null && guestUserld == null) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("PlayerModel", playerModel);
                intent.putExtra("is_a_guest_user", "is_a_guest_user");
                startActivityForResult(intent, BANNER_PLAYBUTTON_CLICK);
            } else {
                if (NetworkStatus.getInstance().isConnected(context)) {
                    getVideoInfo();
                } else {
                    Toast.makeText(getContext(), languagePreference.getTextofLanguage(player.utils.Util.NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            if (NetworkStatus.getInstance().isConnected(context)) {
                getVideoInfo();
            } else {
                Toast.makeText(mContext, languagePreference.getTextofLanguage(player.utils.Util.NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
            }
        }
    }


    public void getVideoInfo() {
        GetVideoDetailsInput getVideoDetailsInput = new GetVideoDetailsInput();
        getVideoDetailsInput.setAuthToken(authTokenStr);

        /*desc:The below condition used for type of user(guest user or normal user)*/
        if (guestUserld != null) {
            getVideoDetailsInput.setUser_id(guestUserld.trim());
        } else {
            getVideoDetailsInput.setUser_id(loggedInIdStr);
        }

        getVideoDetailsInput.setContent_uniq_id(Util.dataModel.getMovieUniqueId());
        getVideoDetailsInput.setStream_uniq_id(Util.dataModel.getStreamUniqueId());
        getVideoDetailsInput.setInternetSpeed(MainActivity.internetSpeed.trim());
        getVideoDetailsInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        getVideoDetailsInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        getVideoDetailsInput.setAdParameter(Util.getVastAdParameter());
        VideoDetailsAsynctask asynLoadVideoUrls = new VideoDetailsAsynctask(getVideoDetailsInput, HomeFragment.this, context);
        asynLoadVideoUrls.execute(); //threadPoolExecutor
    }

    /**
     * Following method is responsible to check whether the app is available for the country or not.
     */
    public void checkGeoBlock() {

        try {
            if (Util.geoBloackSuccessStatus == 2) {
                Intent intent = new Intent(getActivity(), GeoblockAlertActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onVideoDetailsPreExecuteStarted() {
        mProgressBarHandler = new ProgressBarHandler(getActivity());
        startLoader();
    }

    @Override
    public void onVideoDetailsPostExecuteCompleted(Video_Details_Output _video_details_output, int statusCode, String status, String message) {

        stopLoader();

        if (_video_details_output != null) {

            boolean play_video = true;

            if (featureHandler.getFeatureStatus(FeatureHandler.IS_STREAMING_RESTRICTION, FeatureHandler.DEFAULT_IS_STREAMING_RESTRICTION) && preferenceManager.getUseridFromPref() != null) {

                play_video = !_video_details_output.getStreaming_restriction().trim().equals("0");
            } else {
                play_video = true;
            }
            if (!play_video) {

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(message);
                dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
                dlgAlert.create().show();

                return;
            }
        } else {

        }


        if (statusCode == 200) {
            if (clickOnBanner) {
                clickOnBanner = false;
                Util.playButtonOnBanner = true;
            }

            playerModel.setHas_video_card(Integer.parseInt(_video_details_output.getHas_video_card()));
            playerModel.setIsOffline(_video_details_output.getIs_offline());
            playerModel.setSkipIntro(_video_details_output.getSkipIntroStartPosition(), _video_details_output.getSkipIntroEndPosition());
            playerModel.setDownloadStatus(_video_details_output.getDownload_status());


            playerModel.setNo_of_row(_video_details_output.getNo_of_row());
            playerModel.setNo_of_column(_video_details_output.getNo_of_column());
            playerModel.setThumb_interval(_video_details_output.getThumb_interval());
            playerModel.setVtt_with_sprite(_video_details_output.getVtt_with_sprite());
            playerModel.setVtt_without_sprite(_video_details_output.getVtt_without_sprite());
            playerModel.setMultiple_sprite_HostPrefix(_video_details_output.getMultiple_sprite_HostPrefix());
            playerModel.setSprite_image(_video_details_output.getSprite_image());


            if (_video_details_output.getThirdparty_url() == null || _video_details_output.getThirdparty_url().matches("")) {
                if (_video_details_output.getStudio_approved_url() != null &&
                        !_video_details_output.getStudio_approved_url().isEmpty() &&
                        !_video_details_output.getStudio_approved_url().equals("null") &&
                        !_video_details_output.getStudio_approved_url().matches("")) {
                    playerModel.setVideoUrl(_video_details_output.getStudio_approved_url());


                    if (_video_details_output.getLicenseUrl().trim() != null && !_video_details_output.getLicenseUrl().trim().isEmpty() && !_video_details_output.getLicenseUrl().trim().equals("null") && !_video_details_output.getLicenseUrl().trim().matches("")) {
                        playerModel.setLicenseUrl(_video_details_output.getLicenseUrl());
                    }
                    if (_video_details_output.getVideoUrl().trim() != null && !_video_details_output.getVideoUrl().isEmpty() && !_video_details_output.getVideoUrl().equals("null") && !_video_details_output.getVideoUrl().trim().matches("")) {
                        playerModel.setMpdVideoUrl(_video_details_output.getVideoUrl());

                    } else {
                        playerModel.setMpdVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
                    }
                } else {
                    if (_video_details_output.getVideoUrl() != null || !_video_details_output.getVideoUrl().matches("")) {
                        playerModel.setVideoUrl(_video_details_output.getVideoUrl());
                        playerModel.setThirdPartyPlayer(false);
                    } else {
                        playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
                    }
                }
            } else {
                if (_video_details_output.getThirdparty_url() != null || !_video_details_output.getThirdparty_url().matches("")) {
                    playerModel.setVideoUrl(_video_details_output.getThirdparty_url());
                    playerModel.setThirdPartyPlayer(true);
                } else {
                    playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
                }
            }

            Util.dataModel.setVideoResolution(_video_details_output.getVideoResolution());
            playerModel.setVideoResolution(_video_details_output.getVideoResolution());
            if (_video_details_output.getPlayed_length() != null && !_video_details_output.getPlayed_length().equals(""))
                playerModel.setPlayPos((Util.isDouble(_video_details_output.getPlayed_length())));

            Util.dataModel.setVideoUrl(_video_details_output.getVideoUrl());
            Util.dataModel.setVideoResolution(_video_details_output.getVideoResolution());
            Util.dataModel.setThirdPartyUrl(_video_details_output.getThirdparty_url());
            Util.dataModel.setAdNetworkId(_video_details_output.getAdNetworkId());
            Util.dataModel.setChannel_id(_video_details_output.getChannel_id());
            Util.dataModel.setPreRoll(_video_details_output.getPreRoll());
            Util.dataModel.setPostRoll(_video_details_output.getPostRoll());
            Util.dataModel.setMidRoll(_video_details_output.getMidRoll());
            Util.dataModel.setAdDetails(_video_details_output.getAdDetails());
            Util.dataModel.setPlayPos(Util.isDouble(_video_details_output.getPlayed_length()));

            //player model set
            playerModel.setVastAdTag(_video_details_output.getVast_ad_tag());

            playerModel.setSubTitleName(_video_details_output.getSubTitleName());
            playerModel.setSubTitlePath(_video_details_output.getSubTitlePath());
            playerModel.setResolutionFormat(_video_details_output.getResolutionFormat());
            playerModel.setResolutionUrl(_video_details_output.getResolutionUrl());
            playerModel.setFakeSubTitlePath(_video_details_output.getFakeSubTitlePath());
            playerModel.setVideoResolution(_video_details_output.getVideoResolution());
            playerModel.setSubTitleLanguage(_video_details_output.getSubTitleLanguage());
            playerModel.setOfflineUrl(_video_details_output.getOfflineUrl());
            playerModel.setOfflineLanguage(_video_details_output.getOfflineLanguage());
            playerModel.setPlayPos(Util.isDouble(_video_details_output.getPlayed_length()));

            /**
             * Treat content as Live Stream
             */
            is_livestreamEnabled = _video_details_output.getIs_livestream_enabled();
            livestream_resume_time = _video_details_output.getLivestream_resume_time();
            if (is_livestreamEnabled.equals("1")) {
                playerModel.setContentTypesId(4);
                playerModel.setLivestream_resume_time(Integer.parseInt(livestream_resume_time));
            }

            if (_video_details_output.isWatermark_status()) {
                playerModel.setWaterMark(true);
                if (_video_details_output.isWatermark_email())
                    playerModel.useEmail(true);
                else
                    playerModel.useEmail(false);
                if (_video_details_output.isWatermark_ip())
                    playerModel.useIp(true);
                else
                    playerModel.useIp(false);
                if (_video_details_output.isWatermark_date())
                    playerModel.useDate(true);
                else
                    playerModel.useDate(false);
            } else {
                playerModel.setWaterMark(false);
            }


            if (playerModel.getVideoUrl() == null || playerModel.getVideoUrl().matches("")) {
                Util.showNoDataAlert(context);

            } else {
                // condition for checking if the response has third party url or not.
                if (_video_details_output.getThirdparty_url() == null || _video_details_output.getThirdparty_url().matches("")) {
                    {
                        playerModel.setThirdPartyPlayer(false);

                        final Intent playVideoIntent;
                        if (Util.dataModel.getAdNetworkId() == 3) {

                            playVideoIntent = Util.getPlayerIntent(context);

                        } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                            if (Util.dataModel.getPlayPos() <= 0) {
                                playVideoIntent = new Intent(context, AdPlayerActivity.class);
                            } else {
                                playVideoIntent = Util.getPlayerIntent(context);

                            }


                        } else {
                            playVideoIntent = Util.getPlayerIntent(context);
                        }

                        playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        playVideoIntent.putExtra("PlayerModel", playerModel);
                        ((Activity) context).startActivity(playVideoIntent);
                    }
                } else {
                    final Intent playVideoIntent = Util.getPlayerIntent(context);
                    playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    playVideoIntent.putExtra("PlayerModel", playerModel);
                    ((Activity) context).startActivity(playVideoIntent);
                }
            }

        } else if (statusCode == 436) {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
            dlgAlert.setMessage(message);
            dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
            dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                        }
                    });
            dlgAlert.create().show();

        } else {
            playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
            Util.showNoDataAlert(context);
        }
    }


    /**
     * @param v Desc: Following method is used to initialize the minicontroller.
     */
    public void initMinicontroller(View v) {
        try {
            cc_button = v.findViewById(R.id.cc_button);
            mini_controller_layout = v.findViewById(R.id.mini_controller_layout);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Configuration config = getResources().getConfiguration();
                if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                    //in Right To Left layout
                    mini_controller_layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                }
            } else {
                mini_controller_layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {

            int orientation = getResources().getConfiguration().orientation;

            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        timer.cancel();

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                View decorView = getActivity().getWindow().getDecorView();
                                decorView.setSystemUiVisibility(
                                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                            }
                        });

                    }
                }, 1000, 2000);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            swipeRefreshLayout.setEnabled(false);
            my_recycler_view.suppressLayout(true);
            my_recycler_view.getLayoutManager().scrollToPosition(0);
            my_recycler_view.stopScroll();

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            swipeRefreshLayout.setEnabled(true);
            my_recycler_view.suppressLayout(false);
        }
    }

    public void notifyRecyclerView() {
        getActivity().runOnUiThread(() -> {
            if (adapter != null)
                adapter.notifyDataSetChanged();
        });
    }

}


