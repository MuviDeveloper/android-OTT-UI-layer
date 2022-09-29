package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.Util.languageModel;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.home.apisdk.APIController;
import com.home.apisdk.apiController.GetGenreListAsynctask;
import com.home.apisdk.apiController.GetLanguageListAsynTask;
import com.home.apisdk.apiController.GetTranslateLanguageAsync;
import com.home.apisdk.apiModel.GenreListInput;
import com.home.apisdk.apiModel.GenreListOutput;
import com.home.apisdk.apiModel.LanguageListInputModel;
import com.home.apisdk.apiModel.LanguageListOutputModel;
import com.home.apisdk.apiModel.SortByModel;
import com.home.vod.R;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.model.LanguageModel;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.adapter.LanguageCustomAdapter;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LanguageChangeActivity extends AppCompatActivity implements GetLanguageListAsynTask.GetLanguageListListener
        , GetTranslateLanguageAsync.GetTranslateLanguageInfoListener, GetGenreListAsynctask.GenreListListener, LanguageCustomAdapter.LanguageSelectListener {

    public Toolbar mActionBarToolbar;
    public ImageView toolbarimage;
    private RecyclerView recyclerView;
    private Button apply;

    public ToolbarTitleHandler toolbarTitleHandler;
    private PreferenceManager preferenceManager;
    private LanguagePreference languagePreference;
    private LanguageCustomAdapter languageCustomAdapter;
    private GetGenreListAsynctask genreListTask;
    private FeatureHandler featureHandler;
    private ProgressBarHandler pDialog = null;

    private String Default_Language = "";
    private String Previous_Selected_Language = "";
    private boolean doSilently = false;
    private int corePoolSize = 60;
    private int maximumPoolSize = 200;
    private int keepAliveTime = 10;
    private int indexPosition = 0;

    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    private Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

    /*
     * @Desc: This override method is used to called before the onCreate(),
     *       which help the resources to update Activity.
     * */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleLanguageHelper.onAttach(base));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change);

        languagePreference = LanguagePreference.getLanguagePreference(this);
        featureHandler = FeatureHandler.getFeaturePreference(this);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);
        recyclerView = (RecyclerView) findViewById(R.id.language_recycler_view);
        apply = (Button) findViewById(R.id.apply);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setTitle(languagePreference.getTextofLanguage(LANGUAGE_POPUP_LANGUAGE, DEFAULT_LANGUAGE_POPUP_LANGUAGE));
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        setSupportActionBar(mActionBarToolbar);
        /*
         *
         * @Desc: This for setting up the toolbar back arrow icon change
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Configuration config = getResources().getConfiguration();
            if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                //in Right To Left layout
                mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_right));
            } else {
                mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
            }

        } else {
            mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        }

        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //  To set Id to action bar back button
        setIdToActionBarBackButton(mActionBarToolbar);


        Default_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);
        Previous_Selected_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);
        if (languageModel != null && languageModel.size() > 0) {
            ShowLanguagePopup();
        } else {
            LanguageListInputModel languageListInputModel = new LanguageListInputModel();
            languageListInputModel.setAuthToken(authTokenStr);
            //code start: country code and language code added mantish id# 17373 @author :subhadarshani
            languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
            languageListInputModel.setLangCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            //code  end
            GetLanguageListAsynTask asynGetLanguageList = new GetLanguageListAsynTask(languageListInputModel, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
            asynGetLanguageList.executeOnExecutor(threadPoolExecutor);
        }

    }


    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.back);

            }
        }
    }

    public void ShowLanguagePopup() {
        apply.setText(languagePreference.getTextofLanguage(BUTTON_APPLY, DEFAULT_BUTTON_APPLY));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        languageCustomAdapter = new LanguageCustomAdapter(LanguageChangeActivity.this, languageModel, this);

        recyclerView.setAdapter(languageCustomAdapter);

        if (Previous_Selected_Language.equals(Default_Language)) {
            apply.setBackgroundColor(getResources().getColor(R.color.grey_bg_color));
        }
        apply.setOnClickListener(v -> {
            if (!Previous_Selected_Language.equals(Default_Language)) {
                LanguageListInputModel languageListInputModel = new LanguageListInputModel();
                languageListInputModel.setLangCode(Default_Language);
                languageListInputModel.setAuthToken(authTokenStr);
                //code start: country code added mantish id# 17373
                languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());

                stopAutoTranslation();
                GetTranslateLanguageAsync asynGetTransalatedLanguage = new GetTranslateLanguageAsync(languageListInputModel, LanguageChangeActivity.this, LanguageChangeActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(LanguageChangeActivity.this));
                asynGetTransalatedLanguage.executeOnExecutor(threadPoolExecutor);
            }
        });


    }

    @Override
    public void onLanguageSelected(int adapterPosition) {
        indexPosition = adapterPosition;
        try {
            Default_Language = languageModel.get(adapterPosition).getLanguageId();
            if (Previous_Selected_Language.equals(Default_Language))
                apply.setBackgroundColor(getResources().getColor(R.color.grey_bg_color));
            else
                apply.setBackgroundColor(getResources().getColor(R.color.button_background));//colorPrimary
        } catch (Exception e) {
        }
    }

    private void stopAutoTranslation() {
        try {

            if (genreListTask != null) {
                genreListTask.cancel(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        doSilently = false;
    }

    @Override
    public void onGetLanguageListPreExecuteStarted() {
        pDialog = new ProgressBarHandler(LanguageChangeActivity.this);
        pDialog.show();
    }

    @Override
    public void onGetLanguageListPostExecuteCompleted(ArrayList<LanguageListOutputModel> languageListOutputArray, int status, String message, String defaultLanguage) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }
        ArrayList<LanguageModel> languageModels = new ArrayList<LanguageModel>();

        for (int i = 0; i < languageListOutputArray.size(); i++) {
            String language_id = languageListOutputArray.get(i).getLanguageCode();
            String language_name = languageListOutputArray.get(i).getLanguageName();


            LanguageModel languageModel = new LanguageModel();
            languageModel.setLanguageId(language_id);
            languageModel.setLanguageName(language_name);


            if (Default_Language.equalsIgnoreCase(language_id)) {
                languageModel.setIsSelected(true);
            } else {
                languageModel.setIsSelected(false);
            }
            languageModels.add(languageModel);
        }

        languageModel = languageModels;
        ShowLanguagePopup();
    }


    @Override
    public void onGetTranslateLanguagePreExecuteStarted() {
        if (!doSilently) {
            pDialog = new ProgressBarHandler(LanguageChangeActivity.this);
            pDialog.show();
        }
    }

    @Override
    public void onGetTranslateLanguagePostExecuteCompleted(String jsonResponse, int status) {

        if (pDialog != null && pDialog.isShowing() && !doSilently) {
            pDialog.hide();
            pDialog = null;
        }

        if (status > 0 && status == 200) {

            preferenceManager.setSelectedLanguageTexttoPref(languageModel.get(indexPosition).getLanguageName());
            languagePreference.setLanguageSharedPrefernce(SELECTED_LANGUAGE_CODE, languageModel.get(indexPosition).getLanguageId());
            if (featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED)) {
                APIController apiDataController = new DataController(LanguageChangeActivity.this);
                apiDataController.deleteAPICacheData();
            }
            Util.parseLanguage(languagePreference, jsonResponse, Default_Language);
            genre_call(Default_Language);

        }
    }

    public void genre_call(String default_Language) {

        GenreListInput genreListInput = new GenreListInput();
        genreListInput.setAuthToken(authTokenStr);
        genreListInput.setLang_code(default_Language);


        {
            genreListInput.setIs_specific("0"); // for others
        }
        genreListInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        genreListTask = new GetGenreListAsynctask(genreListInput, LanguageChangeActivity.this, LanguageChangeActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(LanguageChangeActivity.this));
        genreListTask.executeOnExecutor(threadPoolExecutor);

    }

    @Override
    public void onGetGenreListPreExecuteStarted() {

        if (!doSilently) {
            pDialog = new ProgressBarHandler(LanguageChangeActivity.this);
            pDialog.show();
        }
    }

    @Override
    public void onGetGenreListPostExecuteCompleted(ArrayList<GenreListOutput> genreListOutput, ArrayList<SortByModel> sortByListOutput, int code, String status) {

        if (pDialog != null && pDialog.isShowing() && !doSilently) {
            pDialog.hide();
            pDialog = null;

        }

        if (doSilently) {
            doSilently = false;
        } else {
            Intent intent = new Intent(LanguageChangeActivity.this, MainActivity.class);//MainActivity
            preferenceManager.setFRAGMENTS_CHANGED("home");
//            /*
//             * @Desc: This is settting up according  we have choosen in local language to update.
//             * */
//            if (languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, "") != null) {
//                LocaleLanguageHelper.setLocale(this, languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, ""));
//            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("updateWithLanguage", "1");
            startActivity(intent);
            finish();


        }
    }


    public static class RecyclerTouchListener1 implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private LanguageChangeActivity.ClickListener1 clickListener;

        public RecyclerTouchListener1(Context context, final RecyclerView recyclerView, final LanguageChangeActivity.ClickListener1 clickListener) {
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
}
