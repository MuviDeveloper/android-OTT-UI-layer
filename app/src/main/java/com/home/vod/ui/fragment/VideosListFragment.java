package com.home.vod.ui.fragment;

import static android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_XLARGE;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.NO_CONTENT;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_DETAILS_AVAILABLE;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.TRY_AGAIN;
import static com.home.vod.util.Constant.PERMALINK_INTENT_KEY;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.GridLayout.getGridLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.home.apisdk.APIController;
import com.home.apisdk.APIUrlConstant;
import com.home.apisdk.apiController.GetContentListAsynTask;
import com.home.apisdk.apiController.LoadFilterVideoAsync;
import com.home.apisdk.apiModel.ContentListInput;
import com.home.apisdk.apiModel.ContentListOutput;
import com.home.apisdk.apiModel.LoadFilterVideoInput;
import com.home.apisdk.apiModel.LoadFilterVideoOutput;
import com.home.vod.R;
import com.home.vod.handlers.Episode_Programme_Handler;
import com.home.vod.handlers.VideolistFragmentHandler;
import com.home.vod.model.GridItem;
import com.home.vod.model.ListItem;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.activity.AudioContentDetailsActivity;
import com.home.vod.ui.activity.MainActivity;
import com.home.vod.ui.activity.MovieDetailsActivity;
import com.home.vod.ui.adapter.VideoFilterAdapter;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.Constant;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by user on 28-06-2015.
 */
public class VideosListFragment extends Fragment implements LoadFilterVideoAsync.LoadFilterVideoListner, GetContentListAsynTask.GetContentListListener {

    public static boolean clearClicked = false, isBackClicked = false, navigatedfromFilterPage = false;
    public static boolean firstTimeSelected;
    public static ArrayList<String> genreArray;
    public static String filterOrderByStr = "";

    private FeatureHandler featureHandler;
    private APIController apiController;
    private LoadFilterVideoAsync loadFilterVideoAsync;
    private VideolistFragmentHandler videosListFragment;
    private AsynLOADUI loadUI;
    private GetContentListAsynTask getContentListAsynTask;
    private ProgressBarHandler videoPDialog;
    private PreferenceManager preferenceManager;
    private LanguagePreference languagePreference;
    private GridItem itemToPlay;
    private ProgressBarHandler pDialog;
    private VideoFilterAdapter customGridAdapter;

    private int sampleSize = 100;
    private int mNoOfColumns;
    private int NO_IMAGE_HEIGHT = 500;
    private int NO_IMAGE_WIDTH = 300;
    private String strtext = "";
    private String countryCodeStr;
    private String videoImageStrToHeight;
    private int videoHeight = 185;
    private int videoWidth = 256;
    private boolean firstTime = false;
    private String movieUniqueId;
    private int mLastFirstVisibleItem;
    private boolean mIsScrollingUp;
    private boolean scrolling;

    private Button btnRetry;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private TextView oopsTextView;
    private RelativeLayout noInternetConnectionLayout;
    private RelativeLayout noDataLayout;
    private TextView noDataTextView;
    private TextView noInternetTextView;
    private RecyclerView gridView;
    private RelativeLayout filterView;
    private TextView genereTextView;
    private RelativeLayout footerView;
    private RecyclerView genreListData;

    private ArrayList<String> url_maps;
    private ArrayList<GridItem> itemData = new ArrayList<GridItem>();

    /*The Data to be posted*/
    int offset = 1;
    int limit = 10;
    int listSize = 0;
    int itemsInServer = 0;

    int corePoolSize = 60;
    int maximumPoolSize = 80;
    int keepAliveTime = 10;
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    private Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

    private Context context;

    @Override
    public void onGetContentListPreExecuteStarted() {
        if (MainActivity.internetSpeedDialog != null && MainActivity.internetSpeedDialog.isShowing()) {
            videoPDialog = MainActivity.internetSpeedDialog;
            footerView.setVisibility(View.GONE);

        } else {
            videoPDialog = new ProgressBarHandler(context);
            if (listSize == 0) {

                videoPDialog.show();

                footerView.setVisibility(View.GONE);
            } else {
                // show loader for first time
                if (videoPDialog != null && videoPDialog.isShowing()) {
                    videoPDialog.hide();
                    videoPDialog = null;
                }
                footerView.setVisibility(View.VISIBLE);

            }
        }
    }

    @Override
    public void onGetContentListPostExecuteCompleted(ArrayList<ContentListOutput> contentListOutputArray, int status, int totalItems,
                                                     String message, String category_id, String isfollowed_staus, boolean isCacheData, String apiOffset) {
        String movieGenreStr = "";
        String movieName = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String movieImageStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String moviePermalinkStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String videoTypeIdStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String isEpisodeStr = "";

        if (apiOffset.trim().equals("1") && !isCacheData) {
            resetData();
        }

        if (status == 200) {
            itemsInServer = totalItems;

            /**
             * Adding first content as layout chooser
             */
            boolean add_to_list_orientation_checker = true;


            for (int i = 0; i < contentListOutputArray.size(); i++) {
                movieGenreStr = contentListOutputArray.get(i).getGenre();
                movieName = contentListOutputArray.get(i).getName();
                movieImageStr = contentListOutputArray.get(i).getPosterUrl();
                moviePermalinkStr = contentListOutputArray.get(i).getPermalink();
                videoTypeIdStr = contentListOutputArray.get(i).getContentTypesId();
                isEpisodeStr = contentListOutputArray.get(i).getIsEpisodeStr();

                /**
                 *
                 * desc This method will check whether the application is of VOD type and the content comming from server is also of video type
                 */
                if (Util.isVideoContent(videoTypeIdStr)) {
                    Util.APP_TYPE = Util.VOD;
                } else if (Util.isAudioContent(videoTypeIdStr)) {
                    Util.APP_TYPE = Util.AOD;
                }
                if (Util.getFilterContent(Util.APP_TYPE, videoTypeIdStr))
                    itemData.add(new GridItem(movieImageStr, movieName, "", videoTypeIdStr, "", "", moviePermalinkStr, isEpisodeStr, movieUniqueId, "", 0, 0, 0, "", ""));


                if (add_to_list_orientation_checker) {
                    add_to_list_orientation_checker = false;
                    videoImageStrToHeight = movieImageStr;
                }
            }

        }

        if (itemData.size() <= 0) {
            try {
                if (videoPDialog != null && videoPDialog.isShowing()) {
                    videoPDialog.hide();
                    videoPDialog = null;
                }
            } catch (IllegalArgumentException ex) {

                noDataLayout.setVisibility(View.VISIBLE);
                noInternetConnectionLayout.setVisibility(View.GONE);
                gridView.setVisibility(View.GONE);
                footerView.setVisibility(View.GONE);

            }
            noDataLayout.setVisibility(View.VISIBLE);
            noInternetConnectionLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.GONE);
            footerView.setVisibility(View.GONE);


        } else {
            footerView.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);

            noInternetConnectionLayout.setVisibility(View.GONE);
            noDataLayout.setVisibility(View.GONE);

            if (firstTime == true) {

                /*
                 * desc: if first image appears no-image,then all the image will be in vertical size.*/

                if (Util.is_contain_noimage(videoImageStrToHeight)) {
                    videoHeight = NO_IMAGE_HEIGHT;
                    videoWidth = NO_IMAGE_WIDTH;

                    AsynLOADUI loadUI = new AsynLOADUI();
                    loadUI.execute("content");

                } else {
                    new RetrieveFeedTask().execute(videoImageStrToHeight, "content");

                }
            } else {
                loadUI = new AsynLOADUI();
                loadUI.execute("content");
            }
        }


    }


    public VideosListFragment() {
        // Required empty public constructor
    }

    public static boolean isLoading = false;

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_videos, container, false);
        firstTimeSelected = true;
        context = getActivity();
        apiController = new DataController(context);
        featureHandler = FeatureHandler.getFeaturePreference(context);
        //for search for each activity
        setHasOptionsMenu(true);
        filterOrderByStr = "";
        isBackClicked = false;


        Constant.genereFilteredMap = null;
        final ArrayList<ListItem> mdata = new ArrayList<ListItem>();
        genreArray = new ArrayList<String>();

        androidx.appcompat.widget.Toolbar toolbar = ((MainActivity) getActivity()).mToolbar;
        setIdToActionBarBackButton(toolbar);

        ((MainActivity) getActivity()).toolbarimage.setVisibility(View.GONE);
        ((MainActivity) getActivity()).mToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("");


        videosListFragment = new VideolistFragmentHandler(getActivity());

        languagePreference = LanguagePreference.getLanguagePreference(getActivity());
        Typeface castDescriptionTypeface = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.regular_fonts));

        genreListData = (RecyclerView) rootView.findViewById(R.id.demoListView);
        genereTextView = rootView.findViewById(R.id.genereTitleTv);

        LinearLayoutManager linearLayout = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        genreListData.setLayoutManager(linearLayout);
        genreListData.setItemAnimator(new DefaultItemAnimator());
        preferenceManager = PreferenceManager.getPreferenceManager(getActivity());
        languagePreference = LanguagePreference.getLanguagePreference(getActivity());

        gridView = (RecyclerView) rootView.findViewById(R.id.imagesGridView);
        Util.recyclerView = gridView;
        footerView = (RelativeLayout) rootView.findViewById(R.id.loadingPanel);

        oopsTextView = (TextView) rootView.findViewById(R.id.oopsTextView); // Added by Debashish
        btnRetry = (Button) rootView.findViewById(R.id.btnRetry); // Added by Debashish
        noInternetConnectionLayout = (RelativeLayout) rootView.findViewById(R.id.noInternet);
        noDataLayout = (RelativeLayout) rootView.findViewById(R.id.noData);
        noInternetTextView = (TextView) rootView.findViewById(R.id.noInternetTextView);
        noDataTextView = (TextView) rootView.findViewById(R.id.noDataTextView);
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noDataTextView.setText(languagePreference.getTextofLanguage(NO_CONTENT, DEFAULT_NO_CONTENT));

        noInternetConnectionLayout.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.GONE);


        btnRetry.setText("          " + languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN) + "       ");
        oopsTextView.setText(languagePreference.getTextofLanguage(OOPS, DEFAULT_OOPS));


        btnRetry.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            restartFragment();
        });


        footerView.setVisibility(View.GONE);
        gridView.setVisibility(View.VISIBLE);
        gridView.setAdapter(customGridAdapter);

        resetData();
        strtext = getArguments().getString("item");

        countryCodeStr = preferenceManager.getCountryCodeFromPref();

        if (!NetworkStatus.getInstance().isConnected(getActivity())) {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
            noDataLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.GONE);
            footerView.setVisibility(View.GONE);
        } else {

            ContentListInput contentListInput = new ContentListInput();
            contentListInput.setAuthToken(authTokenStr);
            contentListInput.setPermalink(strtext.trim());
            contentListInput.setLimit(String.valueOf(limit));
            contentListInput.setOffset(String.valueOf(offset));
            contentListInput.setOrderby("");
            contentListInput.setUserId(preferenceManager.getUseridFromPref());

            if (countryCodeStr != null) {
                contentListInput.setCountry(countryCodeStr);
            } else {
                contentListInput.setCountry("IN");
            }
            contentListInput.setState(preferenceManager.getStateFromPref());
            contentListInput.setCity(preferenceManager.getCityFromPref());

            contentListInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));

            getContentListAsynTask = new GetContentListAsynTask(contentListInput, VideosListFragment.this, context, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(getActivity()));
            getContentListAsynTask.executeOnExecutor(threadPoolExecutor);

        }

        gridView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {
                super.onScrollStateChanged(view, scrollState);
                GridLayoutManager layoutManager = ((GridLayoutManager) gridView.getLayoutManager());
                int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                if (firstVisiblePosition >= itemsInServer - 1) {
                    footerView.setVisibility(View.GONE);
                    return;
                }

                if (view.getId() == gridView.getId()) {
                    final int currentFirstVisibleItem = lastVisiblePosition;

                    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                        mIsScrollingUp = false;

                    } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                        mIsScrollingUp = true;
                    }

                    mLastFirstVisibleItem = currentFirstVisibleItem;
                }
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    scrolling = false;

                } else if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    scrolling = true;

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = ((GridLayoutManager) gridView.getLayoutManager());
                int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();
                int visibleItemCount = layoutManager.getChildCount();


                if (scrolling == true && mIsScrollingUp == false) {

                    if (firstVisiblePosition + visibleItemCount >= totalItemCount) {
                        listSize = itemData.size();
                        if (lastVisiblePosition >= itemsInServer - 1) {
                            return;

                        }
                        offset += 1;
                        if (NetworkStatus.getInstance().isConnected(getActivity())) {

                            if ((filterOrderByStr != null && !filterOrderByStr.equalsIgnoreCase("")) || (genreArray != null && genreArray.size() > 0)) {

                                LoadFilterVideoInput loadFilterVideoInput = new LoadFilterVideoInput();
                                loadFilterVideoInput.setGenreArray(genreArray);
                                loadFilterVideoInput.setAuthToken(authTokenStr);
                                loadFilterVideoInput.setPermalink(strtext.trim());
                                loadFilterVideoInput.setLimit(String.valueOf(limit));
                                loadFilterVideoInput.setOffset(String.valueOf(offset));
                                loadFilterVideoInput.setOrderby(filterOrderByStr);
                                loadFilterVideoInput.setUserId(preferenceManager.getUseridFromPref());


                                if (countryCodeStr != null) {
                                    loadFilterVideoInput.setCountry(countryCodeStr);
                                } else {
                                    loadFilterVideoInput.setCountry("IN");
                                }
                                loadFilterVideoInput.setState(preferenceManager.getStateFromPref());
                                loadFilterVideoInput.setCity(preferenceManager.getCityFromPref());

                                loadFilterVideoInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                                loadFilterVideoAsync = new LoadFilterVideoAsync(loadFilterVideoInput, VideosListFragment.this, context);
                                loadFilterVideoAsync.executeOnExecutor(threadPoolExecutor);


                            } else {


                                ContentListInput contentListInput = new ContentListInput();
                                contentListInput.setAuthToken(authTokenStr);
                                contentListInput.setPermalink(strtext.trim());
                                contentListInput.setLimit(String.valueOf(limit));
                                contentListInput.setOffset(String.valueOf(offset));
                                contentListInput.setOrderby("");
                                contentListInput.setUserId(preferenceManager.getUseridFromPref());
                                if (countryCodeStr != null) {
                                    contentListInput.setCountry(countryCodeStr);
                                } else {
                                    contentListInput.setCountry("IN");
                                }
                                contentListInput.setState(preferenceManager.getStateFromPref());
                                contentListInput.setCity(preferenceManager.getCityFromPref());
                                contentListInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                                getContentListAsynTask = new GetContentListAsynTask(contentListInput, VideosListFragment.this, context, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(getActivity()));
                                getContentListAsynTask.executeOnExecutor(threadPoolExecutor);
                            }

                            scrolling = false;

                        }

                    }

                }
            }
        });


        gridView.addOnItemTouchListener(new RecyclerTouchListener1(context, gridView, new ClickListener1() {
            @Override
            public void onClick(View view, int position) {
                if (itemData != null && itemData.size() > 0) {
                    GridItem item = itemData.get(position);
                    itemToPlay = item;
                    String posterUrl = item.getImage();
                    String movieName = item.getTitle();
                    String movieGenre = item.getMovieGenre();
                    String moviePermalink = item.getPermalink();
                    String movieTypeId = item.getVideoTypeId();
                    String isEpisode = item.getIsEpisode();
                    movieUniqueId = item.getMovieUniqueId();


                    if (moviePermalink != null && moviePermalink.matches(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA))) {
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                        dlgAlert.setMessage(languagePreference.getTextofLanguage(NO_DETAILS_AVAILABLE, DEFAULT_NO_DETAILS_AVAILABLE));
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
                        if ((movieTypeId.trim().equalsIgnoreCase("1")) || (movieTypeId.trim().equalsIgnoreCase("2")) || (movieTypeId.trim().equalsIgnoreCase("4"))) {
                            final Intent movieDetailsIntent = new Intent(context, MovieDetailsActivity.class);
                            movieDetailsIntent.putExtra(PERMALINK_INTENT_KEY, moviePermalink);
                            movieDetailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    movieDetailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    context.startActivity(movieDetailsIntent);
                                }
                            });


                        } else if ((movieTypeId.trim().equalsIgnoreCase("3"))) {
                            new Episode_Programme_Handler(getActivity()).handleIntent(PERMALINK_INTENT_KEY, moviePermalink);
                        } else if (movieTypeId.equals("5") || movieTypeId.equals("6")) {
                            //Single part
                            final Intent detailsIntent = new Intent(context, AudioContentDetailsActivity.class);
                            detailsIntent.putExtra("PERMALINK", moviePermalink);
                            detailsIntent.putExtra("CONTENT_TYPE", movieTypeId);
                            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            context.startActivity(detailsIntent);


                        }
                    }
                }


            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));


        filterView = (RelativeLayout) rootView.findViewById(R.id.filterBg);
        filterView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                filterView.setVisibility(View.GONE);
                gridView.setEnabled(true);

                if ((filterOrderByStr != null && !filterOrderByStr.equalsIgnoreCase("")) || (genreArray != null && genreArray.size() > 0)) {
                    firstTime = true;


                    offset = 1;
                    listSize = 0;
                    if (((context.getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) || ((context.getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_XLARGE)) {
                        limit = 20;
                    } else {
                        //limit = 15;
                        limit = 12;
                    }
                    itemsInServer = 0;
                    scrolling = false;
                    if (itemData != null && itemData.size() > 0) {
                        itemData.clear();
                    }

                    if (!NetworkStatus.getInstance().isConnected(getActivity())) {
                        noInternetConnectionLayout.setVisibility(View.VISIBLE);
                        gridView.setVisibility(View.GONE);



                    } else {
                        if (pDialog != null && pDialog.isShowing()) {
                            pDialog.hide();
                            pDialog = null;
                        }
                        if (videoPDialog != null && videoPDialog.isShowing()) {
                            videoPDialog.hide();
                            videoPDialog = null;
                        }
                        if (getContentListAsynTask != null) {
                            getContentListAsynTask.cancel(true);
                        }
                        if (loadUI != null) {
                            loadUI.cancel(true);
                        }

                        LoadFilterVideoInput loadFilterVideoInput = new LoadFilterVideoInput();
                        loadFilterVideoInput.setGenreArray(genreArray);
                        loadFilterVideoInput.setAuthToken(authTokenStr);
                        loadFilterVideoInput.setPermalink(strtext.trim());
                        loadFilterVideoInput.setLimit(String.valueOf(limit));
                        loadFilterVideoInput.setOffset(String.valueOf(offset));
                        loadFilterVideoInput.setOrderby(filterOrderByStr);
                        loadFilterVideoInput.setUserId(preferenceManager.getUseridFromPref());
                        if (countryCodeStr != null) {
                            loadFilterVideoInput.setCountry(countryCodeStr);
                        } else {
                            loadFilterVideoInput.setCountry("IN");
                        }
                        loadFilterVideoInput.setState(preferenceManager.getStateFromPref());
                        loadFilterVideoInput.setCity(preferenceManager.getCityFromPref());

                        loadFilterVideoInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                        loadFilterVideoAsync = new LoadFilterVideoAsync(loadFilterVideoInput, VideosListFragment.this, context);
                        loadFilterVideoAsync.executeOnExecutor(threadPoolExecutor);

                    }
                }
                return false;
            }
        });

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

                        if (MainActivity.audioCustomMiniController.bootomsheet_open) {
                            MainActivity.audioCustomMiniController.collapse_buttomsheet();

                        } else {
                            final Intent startIntent = new Intent(context, MainActivity.class);
                            preferenceManager.setFRAGMENTS_CHANGED("home");

                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(startIntent);

                                    getActivity().finish();

                                }
                            });
                        }

                    }
                }
                return false;
            }
        });

        return rootView;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (videoPDialog != null && videoPDialog.isShowing()) {
            videoPDialog.hide();
            videoPDialog = null;
        }

    }
    // on device configuration change , the grid numbers need to be changed
    public void onResume() {

        //added by subhadarshani to get filtered data if default sort by is set from CMS side
        if (filterOrderByStr.length() == 0) {
            String filteredData = this.apiController.getAPIData(APIUrlConstant.GENRE_LIST_URL, "", true);
            if (filteredData != null) {
                try {
                    JSONObject myJson = new JSONObject(filteredData);
                    JSONArray jsonSortByArray = myJson.getJSONArray("sort_by");
                    for (int i = 0; i < jsonSortByArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonSortByArray.get(i);
                        if (jsonObject.getString("is_default").equalsIgnoreCase("1")) {
                            filterOrderByStr = jsonObject.getString("permalink");
                            break;
                        }
                    }
                } catch (Exception e) {

                }
            }
        }
        if (!isBackClicked && navigatedfromFilterPage) {

            navigatedfromFilterPage = false;

            if ((filterOrderByStr != null && !filterOrderByStr.equalsIgnoreCase("")) || (genreArray != null && genreArray.size() > 0)) {
                firstTime = true;
                isBackClicked = false;

                if (itemData != null && itemData.size() > 0) {
                    itemData.clear();
                }

                isLoading = false;

                offset = 1;
                listSize = 0;
                if (((context.getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) || ((context.getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_XLARGE)) {
                    limit = 20;
                } else {
                    limit = 12;
                }
                itemsInServer = 0;

                if (!NetworkStatus.getInstance().isConnected(getActivity())) {
                    noInternetConnectionLayout.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.GONE);

                } else {

                    if (pDialog != null && pDialog.isShowing()) {
                        pDialog.hide();
                        pDialog = null;
                    }
                    if (videoPDialog != null && videoPDialog.isShowing()) {
                        videoPDialog.hide();
                        videoPDialog = null;
                    }
                    if (getContentListAsynTask != null) {
                        getContentListAsynTask.cancel(true);
                    }
                    if (loadUI != null) {
                        loadUI.cancel(true);
                    }

                    LoadFilterVideoInput loadFilterVideoInput = new LoadFilterVideoInput();
                    loadFilterVideoInput.setGenreArray(genreArray);
                    loadFilterVideoInput.setAuthToken(authTokenStr);
                    loadFilterVideoInput.setPermalink(strtext.trim());
                    loadFilterVideoInput.setLimit(String.valueOf(limit));
                    loadFilterVideoInput.setOffset(String.valueOf(offset));
                    loadFilterVideoInput.setOrderby(filterOrderByStr);
                    loadFilterVideoInput.setUserId(preferenceManager.getUseridFromPref());
                    if (countryCodeStr != null) {
                        loadFilterVideoInput.setCountry(countryCodeStr);
                    } else {
                        loadFilterVideoInput.setCountry("IN");
                    }
                    loadFilterVideoInput.setState(preferenceManager.getStateFromPref());
                    loadFilterVideoInput.setCity(preferenceManager.getCityFromPref());

                    loadFilterVideoInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    loadFilterVideoAsync = new LoadFilterVideoAsync(loadFilterVideoInput, VideosListFragment.this, context);
                    loadFilterVideoAsync.execute();

                }
            }
        }


        if (pDialog != null) {
            pDialog.hide();
            pDialog = null;
        }


        if (clearClicked) {
            if (!NetworkStatus.getInstance().isConnected(getActivity())) {
                noInternetConnectionLayout.setVisibility(View.VISIBLE);
                noDataLayout.setVisibility(View.GONE);
                gridView.setVisibility(View.GONE);
                footerView.setVisibility(View.GONE);
            }
            resetData();
            clearClicked = false;

            if (itemData != null && itemData.size() > 0) {
                itemData.clear();
            }

            ContentListInput contentListInput = new ContentListInput();
            contentListInput.setAuthToken(authTokenStr);
            contentListInput.setPermalink(strtext.trim());
            contentListInput.setLimit(String.valueOf(limit));
            contentListInput.setOffset(String.valueOf(offset));
            contentListInput.setOrderby("");
            contentListInput.setUserId(preferenceManager.getUseridFromPref());
            if (countryCodeStr != null) {
                contentListInput.setCountry(countryCodeStr);
            } else {
                contentListInput.setCountry("IN");
            }
            contentListInput.setState(preferenceManager.getStateFromPref());
            contentListInput.setCity(preferenceManager.getCityFromPref());
            contentListInput.setLanguage(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            getContentListAsynTask = new GetContentListAsynTask(contentListInput, VideosListFragment.this, context, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(getActivity()));
            getContentListAsynTask.executeOnExecutor(threadPoolExecutor);
        }

        if (url_maps != null && url_maps.size() > 0) {
            url_maps.clear();
        }

        getActivity().invalidateOptionsMenu();
        super.onResume();

        if (getView() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        View focusedView = getActivity().getCurrentFocus();
        if (focusedView != null) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }

        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT; //this is in pixels
        gridView.setLayoutParams(layoutParams);
        mNoOfColumns = Util.calculateNoOfColumns(getContext());
        if (videoWidth > videoHeight && mNoOfColumns == 4) {
            mNoOfColumns = 3;

        }

        gridView.setLayoutManager(new GridLayoutManager(getContext(), mNoOfColumns));

        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onLoadFilterVideoPreExecuteStarted() {

    }

    @Override
    public void onLoadFilterVideoPostExecuteCompleted(ArrayList<LoadFilterVideoOutput> loadFilterVideoOutputArrayList, int status, int totalItems, String message, String category_id, String isFollowed) {
        String movieGenreStr = "";
        String movieName = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String movieImageStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String moviePermalinkStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String videoTypeIdStr = languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA);
        String isEpisodeStr = "";
        int isAPV = 0;
        int isPPV = 0;
        int isConverted = 0;

        boolean add_to_list_orientation_checker = true;


        if (status == 200) {
            itemsInServer = totalItems;
            for (int i = 0; i < loadFilterVideoOutputArrayList.size(); i++) {
                movieGenreStr = loadFilterVideoOutputArrayList.get(i).getGenre();
                movieName = loadFilterVideoOutputArrayList.get(i).getName();

                //    String video_duration = loadFilterVideoOutputArrayList.get(i).getVideo_duration();
                moviePermalinkStr = loadFilterVideoOutputArrayList.get(i).getPermalink();
                videoTypeIdStr = loadFilterVideoOutputArrayList.get(i).getContentTypesId();

                if (videoTypeIdStr.equals("9"))
                    movieImageStr = loadFilterVideoOutputArrayList.get(i).getMiniPosterUrl();
                else movieImageStr = loadFilterVideoOutputArrayList.get(i).getPosterUrl();

                isConverted = loadFilterVideoOutputArrayList.get(i).getIsConverted();
                isAPV = loadFilterVideoOutputArrayList.get(i).getIsAPV();
                isPPV = loadFilterVideoOutputArrayList.get(i).getIsPPV();
                isEpisodeStr = loadFilterVideoOutputArrayList.get(i).getIsEpisodeStr();
                String muviUniqId = loadFilterVideoOutputArrayList.get(i).getMovieUniqueid();

                if (Util.isVideoContent(videoTypeIdStr)) {
                    Util.APP_TYPE = Util.VOD;

                } else if (Util.isAudioContent(videoTypeIdStr)) {
                    Util.APP_TYPE = Util.AOD;
                }

                if (Util.getFilterContent(Util.APP_TYPE, videoTypeIdStr)) {

                    if (Util.getFilterContent(Util.APP_TYPE, videoTypeIdStr))
                        itemData.add(new GridItem(movieImageStr, movieName, "", videoTypeIdStr, "", "", moviePermalinkStr, isEpisodeStr, muviUniqId, "", 0, 0, 0, "", ""));

                    if (add_to_list_orientation_checker) {
                        add_to_list_orientation_checker = false;
                        videoImageStrToHeight = movieImageStr;
                    }

                }
            }




            if (itemData.size() <= 0) {
                try {
                    if (videoPDialog != null && videoPDialog.isShowing()) {
                        videoPDialog.hide();
                        videoPDialog = null;
                    }
                } catch (IllegalArgumentException ex) {

                    noDataLayout.setVisibility(View.VISIBLE);
                    noDataTextView.setVisibility(View.VISIBLE);

                    noInternetConnectionLayout.setVisibility(View.GONE);
                    gridView.setVisibility(View.GONE);
                    footerView.setVisibility(View.GONE);

                }
                noDataLayout.setVisibility(View.VISIBLE);
                noDataTextView.setVisibility(View.VISIBLE);
                noInternetConnectionLayout.setVisibility(View.GONE);
                gridView.setVisibility(View.GONE);
                footerView.setVisibility(View.GONE);
                if (((MainActivity) getActivity()).getSupportActionBar() != null)
                    ((MainActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("title"));

            } else {
                footerView.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);

                noInternetConnectionLayout.setVisibility(View.GONE);
                noDataLayout.setVisibility(View.GONE);
                noDataTextView.setVisibility(View.GONE);

                if (firstTime == true) {

                    /*Author:chitra
                     * desc: if first image appears no-image,then all the image will be in vertical size.*/

                    if (Util.is_contain_noimage(videoImageStrToHeight)) {
                        videoHeight = NO_IMAGE_HEIGHT;
                        videoWidth = NO_IMAGE_WIDTH;

                        AsynLOADUI loadUI = new AsynLOADUI();
                        loadUI.execute("content");

                    } else {
                        Log.v("videoTypeIdStr", "" + videoTypeIdStr);
                        if (videoTypeIdStr.equals("9")) {
                            videoHeight = NO_IMAGE_HEIGHT;
                            videoWidth = NO_IMAGE_WIDTH;

                            AsynLOADUI loadUI = new AsynLOADUI();
                            loadUI.execute("content");
                        } else {
                            new RetrieveFeedTask().execute(videoImageStrToHeight, "content");

                        }

                    }

                } else {
                    if (videoTypeIdStr.equals("9")) {
                        videoHeight = NO_IMAGE_HEIGHT;
                        videoWidth = NO_IMAGE_WIDTH;
                    }
                    loadUI = new AsynLOADUI();
                    loadUI.execute("content");
                }
            }


        } else {
            try {
                if (videoPDialog != null && videoPDialog.isShowing()) {
                    videoPDialog.hide();
                    videoPDialog = null;
                }
            } catch (IllegalArgumentException ex) {

                noDataLayout.setVisibility(View.VISIBLE);
                noInternetConnectionLayout.setVisibility(View.GONE);
                gridView.setVisibility(View.GONE);
                footerView.setVisibility(View.GONE);

            }
            noDataLayout.setVisibility(View.VISIBLE);
            noInternetConnectionLayout.setVisibility(View.GONE);
            gridView.setVisibility(View.GONE);
            footerView.setVisibility(View.GONE);

        }


    }

    private class AsynLOADUI extends AsyncTask<String, Void, Void> {
        String content_type;


        @Override
        protected Void doInBackground(String... strings) {
            content_type = strings[0];
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        protected void onPostExecute(Void result) {

            String loggedInStr = preferenceManager.getLoginStatusFromPref();
            float density = context.getResources().getDisplayMetrics().density;
            if (firstTime == true) {
                try {
                    if (videoPDialog != null && videoPDialog.isShowing()) {
                        videoPDialog.hide();
                        videoPDialog = null;
                    }
                } catch (IllegalArgumentException ex) {

                    noDataLayout.setVisibility(View.VISIBLE);
                    noInternetConnectionLayout.setVisibility(View.GONE);
                    gridView.setVisibility(View.GONE);

                    footerView.setVisibility(View.GONE);
                }

                firstTime = false;
                ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
                layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT; //this is in pixels
                gridView.setLayoutParams(layoutParams);
                mNoOfColumns = Util.calculateNoOfColumns(getContext());
                if (videoWidth > videoHeight) {
                    if (!Util.isTablet(getActivity())) {
                        mNoOfColumns = 2;
                    }
                } else {
                    if (!Util.isTablet(getActivity())) {
                        mNoOfColumns = 3;
                    } else {
                        if (mNoOfColumns <= 3) {
                            mNoOfColumns = 4;
                        }
                    }
                }
                gridView.setLayoutManager(new GridLayoutManager(getContext(), mNoOfColumns));
                customGridAdapter = new VideoFilterAdapter(getContext(), getGridLayout(videoWidth, videoHeight, density), itemData, Util.posterType(videoWidth, videoHeight));
                gridView.setAdapter(customGridAdapter);
            } else {
                customGridAdapter.notifyItemRangeInserted(customGridAdapter.getItemCount(), listSize - 1);

            }


            try {
                    genereTextView.setVisibility(View.VISIBLE);
                    genereTextView.setText(getArguments().getString("title"));
                Util.followed_clicked = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void resetData() {
        if (itemData != null && itemData.size() > 0) {
            itemData.clear();
        }
        firstTime = true;
        offset = 1;
        isLoading = false;
        listSize = 0;
        limit = 20;
        itemsInServer = 0;

    }


    class RetrieveFeedTask extends AsyncTask<String, Void, Void> {
        String content_type = "";

        protected Void doInBackground(String... urls) {
            try {
                if (urls[1] != null) {
                    content_type = urls[1];
                }
                if (content_type.equals("content")) {
                    URL url = new URL(urls[0]);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = sampleSize;
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options);
                    videoHeight = bmp.getHeight() * sampleSize;
                    videoWidth = bmp.getWidth() * sampleSize;
                } else if (content_type.equals("playlist")) {
                    URL url = new URL(urls[0]);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = sampleSize;
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options);
                }

                return null;
            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute(Void feed) {
            try {
                if (content_type.equals("content")) {
                    loadUI = new AsynLOADUI();
                    loadUI.execute("content");
                } else if (content_type.equals("playlist")) {
                    loadUI = new AsynLOADUI();
                    loadUI.execute("playlist");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    private void setIdToActionBarBackButton(androidx.appcompat.widget.Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.menu);
            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                t.setId(R.id.page_title_video_list);
            }
        }

    }


    public static class RecyclerTouchListener1 implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener1 clickListener;

        public RecyclerTouchListener1(Context context, final RecyclerView recyclerView, final ClickListener1 clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface ClickListener1 {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    public void restartFragment() {

        try {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.detach(this).attach(this).commit();

        } catch (Exception e) {
        }
    }

}
