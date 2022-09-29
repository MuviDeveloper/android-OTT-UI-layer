package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.ABOUT_US;
import static com.home.vod.preferences.LanguagePreference.BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ABOUT_US;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_EDIT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGGED_IN_AS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGOUT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MANAGE_STREAMING_SETTINGS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PROFILE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SETTINGS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_WARNING;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_STREAM;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TERMS_AND_CONDITIONS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_YES;
import static com.home.vod.preferences.LanguagePreference.EDIT;
import static com.home.vod.preferences.LanguagePreference.LANGUAGE_POPUP_LANGUAGE;
import static com.home.vod.preferences.LanguagePreference.LOGGED_IN_AS;
import static com.home.vod.preferences.LanguagePreference.LOGIN;
import static com.home.vod.preferences.LanguagePreference.LOGOUT;
import static com.home.vod.preferences.LanguagePreference.LOGOUT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.MANAGE_STREAMING_SETTINGS;
import static com.home.vod.preferences.LanguagePreference.NO;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.PROFILE;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SETTINGS;
import static com.home.vod.preferences.LanguagePreference.SIGN_OUT_WARNING;
import static com.home.vod.preferences.LanguagePreference.STREAM;
import static com.home.vod.preferences.LanguagePreference.TERMS_AND_CONDITIONS;
import static com.home.vod.preferences.LanguagePreference.YES;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.FeatureHandler.DEFAULT_IS_REGISTRATION_ENABLED;
import static com.home.vod.util.Util.QUEUE_ARRAY;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.home.apisdk.apiController.LogoutAsynctask;
import com.home.apisdk.apiModel.LogoutInput;
import com.home.vod.R;
import com.home.vod.base.VodApplication;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.service.MusicService;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.AppThreadPoolExecutor;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;

import java.util.concurrent.Executor;

import player.activity.PlayerActivity;

public class SettingsActivity extends AppCompatActivity implements LogoutAsynctask.LogoutListener {


    public ToolbarTitleHandler toolbarTitleHandler;
    private LanguagePreference languagePreference;
    private ProgressBarHandler pDialog;
    private PreferenceManager preferenceManager;

    public Toolbar mActionBarToolbar;
    public ImageView toolbarimage;
    private TextView streamTxtView, txtManageStreamingSettings;
    private TextView txtProfile, txtEdit;
    private TextView txtSignIn, txtSignInHint;
    private TextView txtLanguage, txtSelectedLanguage;
    private TextView txtAboutUs;
    private TextView txtTermsAndPolicy;
    private LinearLayout layoutStream;
    private LinearLayout layoutProfile;
    private LinearLayout layoutSignIn;
    private LinearLayout layoutLanguageChange;
    private LinearLayout layoutAboutUs;
    private LinearLayout layoutTermsAndCondition;
    private View lineUnderSignIn, lineUnderProfile, terms_line, lang_line;
    private ScrollView scrollView;

    private FeatureHandler featureHandler;
    private Executor threadPoolExecutor;
    private Dialog alert;

    private String loggedInStr = null;
    private String loggedInUserName = null;

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
        setContentView(R.layout.activity_settings);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        languagePreference = LanguagePreference.getLanguagePreference(this);
        loggedInStr = preferenceManager.getUseridFromPref();
        loggedInUserName = preferenceManager.getDispNameFromPref();
        alert = new Dialog(this);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);


        threadPoolExecutor = AppThreadPoolExecutor.getInstance().getThreadPoolExecutor();
        featureHandler = FeatureHandler.getFeaturePreference(SettingsActivity.this);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);
        mActionBarToolbar = findViewById(R.id.toolbar);
        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setTitle(languagePreference.getTextofLanguage(SETTINGS, DEFAULT_SETTINGS));
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));


        setSupportActionBar(mActionBarToolbar);


        /*
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
                onBackPressed();
            }
        });
        // To set Id to action bar back button
        setIdToActionBarBackButton(mActionBarToolbar);

        pDialog = new ProgressBarHandler(SettingsActivity.this);
        scrollView = findViewById(R.id.scrollView);
        streamTxtView = findViewById(R.id.stream);
        streamTxtView.setText(languagePreference.getTextofLanguage(STREAM, DEFAULT_STREAM));

        txtManageStreamingSettings = findViewById(R.id.manage_stream_settings);
        txtManageStreamingSettings.setText(languagePreference.getTextofLanguage(MANAGE_STREAMING_SETTINGS, DEFAULT_MANAGE_STREAMING_SETTINGS));

        layoutStream = findViewById(R.id.stream_settings);
        layoutStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, StreamSettingsActivity.class);
                startActivity(intent);
            }
        });


        txtProfile = findViewById(R.id.profile);
        txtProfile.setText(languagePreference.getTextofLanguage(PROFILE, DEFAULT_PROFILE));

        txtEdit = findViewById(R.id.edit);
        txtEdit.setText(languagePreference.getTextofLanguage(EDIT, DEFAULT_EDIT));

        layoutProfile = findViewById(R.id.profile_edit_settings);
        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        lineUnderProfile = findViewById(R.id.lineUnderProfile);
        terms_line = findViewById(R.id.terms_line);
        lang_line = findViewById(R.id.lang_view);

        layoutSignIn = findViewById(R.id.login);
        layoutSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loggedInStr == null) {
                    Util.check_for_subscription = 0;
                    Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    logOutPopUp();
                }

            }
        });

        txtSignIn = findViewById(R.id.logged_in_as);

        txtSignIn.setText(languagePreference.getTextofLanguage(LOGIN, DEFAULT_LOGIN) + " / " +
                languagePreference.getTextofLanguage(BTN_REGISTER, DEFAULT_BTN_REGISTER));

        txtSignInHint = findViewById(R.id.login_diff_acc);
        lineUnderSignIn = findViewById(R.id.lineUnderSignIn);
        txtLanguage = findViewById(R.id.language);
        txtLanguage.setText(languagePreference.getTextofLanguage(LANGUAGE_POPUP_LANGUAGE, DEFAULT_LANGUAGE_POPUP_LANGUAGE));
        txtSelectedLanguage = findViewById(R.id.selected_language);
        txtSelectedLanguage.setText(preferenceManager.getSelectedLanguageTextFromPref());

        layoutLanguageChange = findViewById(R.id.language_settings);
        layoutLanguageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, LanguageChangeActivity.class);
                startActivity(intent);
            }
        });


        txtAboutUs = findViewById(R.id.txtAboutUs);
        txtAboutUs.setText(languagePreference.getTextofLanguage(ABOUT_US, DEFAULT_ABOUT_US));
        layoutAboutUs = findViewById(R.id.about_us);
        layoutAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, AboutUsActivity.class);
                intent.putExtra("title", languagePreference.getTextofLanguage(ABOUT_US, DEFAULT_ABOUT_US));
                intent.putExtra("permalink", "about-us");
                startActivity(intent);
            }
        });

        txtTermsAndPolicy = findViewById(R.id.txtTermsAndPolicy);
        if (getIntent().getStringExtra("termstitle") != null && !getIntent().getStringExtra("termstitle").equals("")) {
            txtTermsAndPolicy.setText(Html.fromHtml(getIntent().getStringExtra("termstitle")).toString());
        } else {
            txtTermsAndPolicy.setText(languagePreference.getTextofLanguage(TERMS_AND_CONDITIONS, DEFAULT_TERMS_AND_CONDITIONS));
        }

        layoutTermsAndCondition = findViewById(R.id.terms);
        layoutTermsAndCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(SettingsActivity.this, AboutUsActivity.class);

                    String termsTitle;
                    if (getIntent().getStringExtra("termstitle") != null && !getIntent().getStringExtra("termstitle").equals("")) {
                        termsTitle = (Html.fromHtml(getIntent().getStringExtra("termstitle")).toString());
                    } else {
                        termsTitle = (languagePreference.getTextofLanguage(TERMS_AND_CONDITIONS, DEFAULT_TERMS_AND_CONDITIONS));
                    }

                    intent.putExtra("title", termsTitle);
                    intent.putExtra("permalink", "terms-privacy-policy");
                    startActivity(intent);
                } catch (Exception W) {
                    W.printStackTrace();
                }

            }
        });


        if (featureHandler.getFeatureStatus(FeatureHandler.IS_ABOUT_US_AVAILABLE, FeatureHandler.DEFAULT_IS_ABOUT_US_AVAILABLE)) {
            layoutAboutUs.setVisibility(View.VISIBLE);
            lang_line.setVisibility(View.VISIBLE);
        } else {
            layoutAboutUs.setVisibility(View.GONE);
            lang_line.setVisibility(View.GONE);
        }


        if (featureHandler.getFeatureStatus(FeatureHandler.IS_TERMS_PRIVACY_AVAILABLE, FeatureHandler.DEFAULT_IS_TERMS_PRIVACY_AVAILABLE)) {
            layoutTermsAndCondition.setVisibility(View.VISIBLE);
            terms_line.setVisibility(View.VISIBLE);
        } else {
            layoutTermsAndCondition.setVisibility(View.GONE);
            terms_line.setVisibility(View.GONE);
        }


        //Feature handler checking CMS values for on/off
        if (!featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN, FeatureHandler.DEFAULT_IS_LOGIN)) {

            layoutSignIn.setVisibility(View.GONE);
            lineUnderSignIn.setVisibility(View.GONE);

            layoutProfile.setVisibility(View.GONE);
            lineUnderProfile.setVisibility(View.GONE);

        }

        if (featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN, FeatureHandler.DEFAULT_IS_LOGIN)
                && !featureHandler.getFeatureStatus(FeatureHandler.IS_REGISTRATION_ENABLED, DEFAULT_IS_REGISTRATION_ENABLED)) {

            txtSignIn.setText(languagePreference.getTextofLanguage(LOGIN, DEFAULT_LOGIN));
        }


        if (loggedInStr == null) { // means not logged in then disable the feature

            layoutProfile.setVisibility(View.GONE);
            txtSignInHint.setVisibility(View.GONE);//INVISIBLE


        }
        if (loggedInStr != null) {
            txtSignIn.setText(languagePreference.getTextofLanguage(LOGOUT, DEFAULT_LOGOUT));

            txtSignInHint.setVisibility(View.VISIBLE);//INVISIBLE
            txtSignInHint.setText(languagePreference.getTextofLanguage(LOGGED_IN_AS, DEFAULT_LOGGED_IN_AS) + " " + loggedInUserName);


        }


    }


    private void logOutPopUp() {
        /*
         * @desc:check if audio is playing or not. If audio is playing stop audio player before logout*/
        if (preferenceManager.getSongStatusFromPref() != null && preferenceManager.getSongStatusFromPref().equals("playing")) {
            preferenceManager.setSongStatustoPref("pause");
            QUEUE_ARRAY.clear();
            MusicService.mediaPlayer.setPlayWhenReady(false);
            Intent Pintent = new Intent("SONG_STATUS");
            Pintent.putExtra("songStatus", "pause");
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(Pintent);

        }
        if (preferenceManager.getSongStatusFromPref() != null && preferenceManager.getSongStatusFromPref().equals("playing")) {
            preferenceManager.setSongStatustoPref("pause");
            MusicService.mediaPlayer.setPlayWhenReady(false);
            Intent Pintent = new Intent("SONG_STATUS");
            Pintent.putExtra("songStatus", "pause");
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(Pintent);

        }
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SettingsActivity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(languagePreference.getTextofLanguage(SIGN_OUT_WARNING, DEFAULT_SIGN_OUT_WARNING));
        dlgAlert.setTitle("");
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if (NetworkStatus.getInstance().isConnected(SettingsActivity.this)) {
                    LogoutInput logoutInput = new LogoutInput();
                    logoutInput.setAuthToken(authTokenStr);

                    String loginHistoryIdStr = preferenceManager.getLoginHistIdFromPref();
                    logoutInput.setLogin_history_id(loginHistoryIdStr);
                    logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, SettingsActivity.this, SettingsActivity.this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(SettingsActivity.this));
                    asynLogoutDetails.executeOnExecutor(threadPoolExecutor);

                    dialog.dismiss();
                } else {
                    Toast.makeText(SettingsActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                }
            }
        });

        dlgAlert.setNegativeButton(languagePreference.getTextofLanguage(NO, DEFAULT_NO), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });
        // dlgAlert.setPositiveButton(getResources().getString(R.string.yes_str), null);
        dlgAlert.setCancelable(false);

        AlertDialog alertDialog = dlgAlert.create();

        // show it
        alertDialog.show();
        TextView tv = (TextView) alertDialog.findViewById(android.R.id.message);

        /*
         * @Desc: This for checking dialog messsage alignments for RTL support
         * */
        Configuration configuration = getResources().getConfiguration();
        if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            if (tv != null) {
                tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

            }
        }
        FontUtls.loadFont(SettingsActivity.this, getResources().getString(R.string.regular_fonts), tv);


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


    @Override
    public void onLogoutPreExecuteStarted() {
        pDialog = new ProgressBarHandler(SettingsActivity.this);
        pDialog.show();
    }

    @Override
    public void onLogoutPostExecuteCompleted(int code, String status, String message) {
        Util.check_for_subscription = 0;

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;

        }

        try {
            try {
                if (PlayerActivity.player != null) {
                    PlayerActivity.player.release();
                    PlayerActivity.playerActivity.finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            preferenceManager.clearLoginPref();
            preferenceManager.setSocialLoginPref("0");

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.cancelAll();

            SharedPreferences sharedprefPlanList = getSharedPreferences("myPlanListPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorPlanList = sharedprefPlanList.edit();
            editorPlanList.putString("isSubscribed", "0");
            editorPlanList.putString("isFree", "0");
            editorPlanList.apply();


            try {
                Util.image_orentiation.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }


            final Intent startIntent = new Intent(SettingsActivity.this, MainActivity.class);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(startIntent);
            Toast.makeText(SettingsActivity.this, languagePreference.getTextofLanguage(LOGOUT_SUCCESS, DEFAULT_LOGOUT_SUCCESS), Toast.LENGTH_LONG).show();

            final VodApplication globalVariable = (VodApplication) getApplicationContext();
            globalVariable.setISSUBSCRIBED("0");

            finish();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {
        loggedInUserName = preferenceManager.getDispNameFromPref();

        /*
         * @Desc: This for update the value of profile
         * */
        if (loggedInUserName != null && loggedInStr != null) {
            txtSignIn.setText(languagePreference.getTextofLanguage(LOGOUT, DEFAULT_LOGOUT));
            txtSignInHint.setText(languagePreference.getTextofLanguage(LOGGED_IN_AS, DEFAULT_LOGGED_IN_AS) + " " + loggedInUserName);

        } else {
            //LOGIN 1 , IS_REGISTRAION=0
            if (featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN, FeatureHandler.DEFAULT_IS_LOGIN)
                    && !featureHandler.getFeatureStatus(FeatureHandler.IS_REGISTRATION_ENABLED, DEFAULT_IS_REGISTRATION_ENABLED)) {

                txtSignIn.setText(languagePreference.getTextofLanguage(LOGIN, DEFAULT_LOGIN));
            } else {
                txtSignIn.setText(languagePreference.getTextofLanguage(LOGIN, DEFAULT_LOGIN) + " / " +
                        languagePreference.getTextofLanguage(BTN_REGISTER, DEFAULT_BTN_REGISTER));
            }

        }

        super.onResume();

    }
}
