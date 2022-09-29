package com.home.vod.ui.activity;

import static com.home.apisdk.apiController.HeaderConstants.RATING;
import static com.home.vod.preferences.LanguagePreference.ALERT;
import static com.home.vod.preferences.LanguagePreference.ALLOW_ADD_EMAIL;
import static com.home.vod.preferences.LanguagePreference.ALLOW_ADD_PHONE_NO;
import static com.home.vod.preferences.LanguagePreference.APP_NO_LONGER_ACTIVE;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_APP_NO_LONGER_ACTIVE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_DEVICE_NOT_SUPPORTED;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_GEO_BLOCKED_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_SOMETHING_WENT_WRONG;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.DEVICE_NOT_SUPPORTED;
import static com.home.vod.preferences.LanguagePreference.GEO_BLOCKED_ALERT;
import static com.home.vod.preferences.LanguagePreference.IS_MYLIBRARY;
import static com.home.vod.preferences.LanguagePreference.IS_ONE_STEP_REGISTRATION;
import static com.home.vod.preferences.LanguagePreference.IS_OTP_ENABLE;
import static com.home.vod.preferences.LanguagePreference.IS_RESTRICT_DEVICE;
import static com.home.vod.preferences.LanguagePreference.IS_STREAMING_RESTRICTION;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS;
import static com.home.vod.preferences.LanguagePreference.OOPS_SOMETHING_WENT_WRONG;
import static com.home.vod.preferences.LanguagePreference.OTP_EXPIRY_DURATION;
import static com.home.vod.preferences.LanguagePreference.REGISTER_THROUGH;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.TRY_AGAIN;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.FeatureHandler.DEFAULT_IS_ABOUT_US_AVAILABLE;
import static com.home.vod.util.FeatureHandler.DEFAULT_IS_LOGIN;
import static com.home.vod.util.FeatureHandler.DEFAULT_IS_TERMS_PRIVACY_AVAILABLE;
import static com.home.vod.util.FeatureHandler.IS_REGISTRATION_ENABLED;
import static com.home.vod.util.Util.decodeSampledBitmapFromResource;
import static com.home.vod.util.Util.forceUpdate_version;
import static player.utils.Util.IS_CHROMECAST;
import static player.utils.Util.IS_OFFLINE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.home.apisdk.apiController.AppConfigAsynctask;
import com.home.apisdk.apiController.CheckGeoBlockCountryAsynTask;
import com.home.apisdk.apiController.GetIpAddressAsynTask;
import com.home.apisdk.apiController.GetLanguageListAsynTask;
import com.home.apisdk.apiController.GetUserGroupAsynTask;
import com.home.apisdk.apiController.GetUserProfileAsynctask;
import com.home.apisdk.apiController.IsRegistrationEnabledAsynTask;
import com.home.apisdk.apiController.SDKInitializer;
import com.home.apisdk.apiModel.AppConfigInputModel;
import com.home.apisdk.apiModel.AppConfigOutputModel;
import com.home.apisdk.apiModel.CheckGeoBlockInputModel;
import com.home.apisdk.apiModel.CheckGeoBlockOutputModel;
import com.home.apisdk.apiModel.GetUserGroupInputModel;
import com.home.apisdk.apiModel.Get_UserProfile_Input;
import com.home.apisdk.apiModel.Get_UserProfile_Output;
import com.home.apisdk.apiModel.IsRegistrationEnabledInputModel;
import com.home.apisdk.apiModel.IsRegistrationEnabledOutputModel;
import com.home.apisdk.apiModel.LanguageListInputModel;
import com.home.apisdk.apiModel.LanguageListOutputModel;
import com.home.apisdk.apiModel.SubscriptionPlanInputModel;
import com.home.apisdk.apiModel.SubscriptionPlanOutputModel;
import com.home.vod.R;
import com.home.vod.apis.UpdateCheck;
import com.home.vod.base.VodApplication;
import com.home.vod.handlers.SplashScreenHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.model.LanguageModel;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.AppThreadPoolExecutor;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.Util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

import player.utils.DBHelper;

public class SplashScreen extends Activity implements
        CheckGeoBlockCountryAsynTask.CheckGeoBlockForCountryListener,
        IsRegistrationEnabledAsynTask.IsRegistrationenabledListener,
        GetLanguageListAsynTask.GetLanguageListListener,
        GetUserProfileAsynctask.Get_UserProfileListener,
        SDKInitializer.SDKInitializerListner,
        GetUserGroupAsynTask.GetUserGroupListener {

    public static boolean force_update_called = false;
    private static final int RC_SETTINGS = 6739;

    private ImageView imageResize;
    private RelativeLayout noInternetLayout;
    private RelativeLayout geoBlockedLayout;
    private Button tryagainbutton;
    private TextView noInternetTextView, geoTextView;
    private LinearLayout videolayout, mainlayout;
    private VideoView videoView;

    private ProgressBarHandler pDialog;
    private Executor threadPoolExecutor;
    private PreferenceManager preferenceManager;
    private LanguagePreference languagePreference;
    private FeatureHandler featureHandler;
    private DBHelper dbHelper;
    private SplashScreenHandler splashScreenHandler;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private TextView oopsTextView;

    private String default_Language = "";
    private ArrayList<LanguageModel> languageModels = new ArrayList<>();
    private String user_Id = "", email_Id = "", isSubscribed = "0", IsFree = "0";
    private boolean nextPageNavigation;
    private boolean sdkInitializerCalled = false;
    private int sdkInitializerSuccessStatus = 0;
    private boolean ipAdressCalled = false;
    private int ipAdressSuccessStatus = 0;
    private String ipAdressMsg = "";
    private boolean geoBloackCalled = false;
    private boolean isRegistrationEnabledCalled = false;
    private int isRegistrationEnabledSuccessStatus = 0;
    private String isRegistrationEnabledMsg = "";
    private boolean languageListCalled = false;
    private int languageListSuccessStatus = 0;
    private boolean profileCalled = false;
    private int profileSuccessStatus = 0;
    private boolean deeplink = false;
    private String isSubscribedStr = "";


    @Override
    protected void onStart() {
        super.onStart();
        Util.check_for_subscription = 0;
    }

    /**
     * @Desc: This override method is used to called before the onCreate(),
     * which help the resources to update Activity.
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleLanguageHelper.onAttach(base));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Util.image_orentiation.clear();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);


        SDKInitializer.getInstance().init(this, this, authTokenStr, true);
        SDKInitializer.setData(this);


        /*
         * @desc:The below line is for getting content sharing info here*/

        try {
            Intent intent = getIntent();
            String action = intent.getAction();
            Uri data = intent.getData();
            if (Intent.ACTION_VIEW.equals(action) && data != null) {

                String content_types_id = data.getQueryParameter("content_types_id");
                String permalink = data.getQueryParameter("permalink");
                deeplink = true;
                Intent mIntent = new Intent(SplashScreen.this, MainActivity.class);
                mIntent.putExtra("share_permalink", permalink);
                mIntent.putExtra("share_content_types_id", content_types_id);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(mIntent);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        splashScreenHandler = new SplashScreenHandler();
        featureHandler = FeatureHandler.getFeaturePreference(SplashScreen.this);
        languagePreference = LanguagePreference.getLanguagePreference(this);
        splashScreenHandler.changeFeatureProperties(featureHandler);

        /*
         * desc:The below condition is for when title will show on the poster bydefault overlay will show*/
        if (featureHandler.getFeatureStatus(FeatureHandler.IS_TITLE_ENABLED, FeatureHandler.DEFAULT_IS_TITLE_ENABLED))
            featureHandler.setFeatureFlag(FeatureHandler.IS_OVERLAY_DISPLAY, "1");

        _init();


        //-- START --
        /**
         1. Check if emulator restriction is enabled or not.
         2. If enabled, then check the device is Emulator or real device.
         3. if device is emulator then show a dialog and close the app.
         */
        if (featureHandler.getFeatureStatus(FeatureHandler.IS_EMULATOR_RESTRICTION_ENABLED, FeatureHandler.DEFAULT_IS_EMULATOR_RESTRICTION_ENABLED)) {

            if (Util.isEmulator()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getThemedContext());
                builder.setCancelable(false);
                builder.setMessage("The app is not supported in virtual devices.");
                builder.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), (dialog, id) -> finish());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return;
            }
        }

        // --- END --

        if (!NetworkStatus.getInstance().isConnected(SplashScreen.this)) {
            email_Id = preferenceManager.getEmailIdFromPref();
            noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
            noInternetLayout.setVisibility(View.VISIBLE);
            imageResize.setVisibility(View.GONE);
            videoView.setVisibility(View.GONE);
            videolayout.setVisibility(View.GONE);
            return;
        }

        if (android.os.Build.VERSION.SDK_INT < 23) {
            showDeviceNotSupportedDialog();
            return;
        }

        /**
         *
         * @description: Calling AppConfig API will check in the Intranet that if there is any Force Update Version is set or not.
         * If anyone set any version for any customer they API should return the version number with respect to device_type otherwise return blanks.
         */
        callAppConfigAPI();
        checkAppUpdate();
        callGetIpAddressApi();

    }

    private void callGetIpAddressApi() {
        GetIpAddressAsynTask getIpAddressAsynTask = new GetIpAddressAsynTask(new GetIpAddressAsynTask.IpAddressListener() {
            @Override
            public void onIPAddressPreExecuteStarted() {

            }

            @Override
            public void onIPAddressPostExecuteCompleted(String message, int statusCode, String ipAddressStr) {
                SharedPreferences sharedPreferences = getSharedPreferences("ipaddress", Context.MODE_PRIVATE);
                SharedPreferences.Editor ip_address = sharedPreferences.edit();
                ip_address.putString("ip_adress", ipAddressStr);
                ip_address.apply();
                ip_address.commit();

                ipAdressCalled = true;

                if (ipAddressStr.equals("")) {
                    ipAdressMsg = "Could not detect your ip";
                    ipAdressSuccessStatus = 5;

                } else {
                    ipAdressSuccessStatus = 1;
//                  this.ipAddressStr = ipAddressStr;
                    CheckGeoBlockInputModel checkGeoBlockInputModel = new CheckGeoBlockInputModel();
                    checkGeoBlockInputModel.setAuthToken(authTokenStr);
                    checkGeoBlockInputModel.setIp(ipAddressStr);
                    CheckGeoBlockCountryAsynTask asynGetCountry = new CheckGeoBlockCountryAsynTask(checkGeoBlockInputModel, SplashScreen.this, SplashScreen.this);
                    asynGetCountry.executeOnExecutor(threadPoolExecutor);
                }
            }
        }, this);
        getIpAddressAsynTask.executeOnExecutor(threadPoolExecutor);
    }


    private void _init() {
        pDialog = new ProgressBarHandler(SplashScreen.this);
        dbHelper = new DBHelper(SplashScreen.this);
        dbHelper.getWritableDatabase();


        threadPoolExecutor = AppThreadPoolExecutor.getInstance().getThreadPoolExecutor();
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        preferenceManager.setFRAGMENTS_CHANGED("home");
        languagePreference = LanguagePreference.getLanguagePreference(this);
        featureHandler = FeatureHandler.getFeaturePreference(SplashScreen.this);

        noInternetLayout = findViewById(R.id.noInternet);
        geoBlockedLayout = findViewById(R.id.geoBlocked);
        oopsTextView = findViewById(R.id.oopsTextView); // Added by Debashish


        featureHandler.setFeatureFlag(FeatureHandler.IS_TERMS_PRIVACY_AVAILABLE, DEFAULT_IS_TERMS_PRIVACY_AVAILABLE);
        featureHandler.setFeatureFlag(FeatureHandler.IS_ABOUT_US_AVAILABLE, DEFAULT_IS_ABOUT_US_AVAILABLE);
        Util.check_for_subscription = 0;

        /*@Author :Bishal
         *If there is no internet connection then we show try agin button and call again apicall method in the click listner
         */

        tryagainbutton = findViewById(R.id.tryAgainButton);
        tryagainbutton.setText(" " + languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN) + " ");

        /**
         * @description : two keys added (1)  Oops !
         */

        oopsTextView.setText(languagePreference.getTextofLanguage(OOPS, DEFAULT_OOPS));

        tryagainbutton.setOnClickListener(v -> {
            v.startAnimation(buttonClick);
            noInternetLayout.setVisibility(View.GONE);
            pDialog.show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    pDialog.dismiss();
                    if (NetworkStatus.getInstance().isConnected(SplashScreen.this)) {
                        /*imageResize.setVisibility(View.VISIBLE);
                        noInternetLayout.setVisibility(View.GONE);
                        checkAppUpadate();*/

                        Intent intent = new Intent(SplashScreen.this, SplashScreen.class);
                        startActivity(intent);
                        finish();

                    } else {
                        noInternetLayout.setVisibility(View.VISIBLE);
                        imageResize.setVisibility(View.GONE);
                        videoView.setVisibility(View.GONE);

                    }
                }
            }, 1000);
        });

        noInternetTextView = findViewById(R.id.noInternetTextView);
        geoTextView = findViewById(R.id.geoBlockedTextView);
        imageResize = findViewById(R.id.splash_screen);
        videolayout = findViewById(R.id.videolayout);
        mainlayout = findViewById(R.id.mainlayout);
        videoView = findViewById(R.id.videoView);

        Display display = getWindowManager().getDefaultDisplay();
        float dpHeight = display.getHeight();
        float dpWidth = display.getWidth();
        imageResize.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.splash_screen, dpWidth, dpHeight));
        splashScreenHandler.handleSplashscreen(imageResize);

        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        geoTextView.setText(languagePreference.getTextofLanguage(GEO_BLOCKED_ALERT, DEFAULT_GEO_BLOCKED_ALERT));

        noInternetLayout.setVisibility(View.GONE);
        geoBlockedLayout.setVisibility(View.GONE);


    }

    private void apiCall() {
        if (android.os.Build.VERSION.SDK_INT < 23) {
            // Do something for lollipop and above versions
            noInternetTextView.setText("The app is not compatible for this OS.");
            noInternetLayout.setVisibility(View.VISIBLE);
            imageResize.setVisibility(View.GONE);
            videoView.setVisibility(View.GONE);
            videolayout.setVisibility(View.GONE);
            return;
        }

        if (NetworkStatus.getInstance().isConnected(this)) {


            /**
             * @description: Calling profile during app opening time to get user subscription status if the user is already logged in.
             */
            callProfileAPI();


            /*Calling isRegistrationEnabled API*/
            IsRegistrationEnabledInputModel isRegistrationEnabledInputModel = new IsRegistrationEnabledInputModel();
            isRegistrationEnabledInputModel.setAuthToken(authTokenStr);
            isRegistrationEnabledInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
            isRegistrationEnabledInputModel.setLanguageCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            IsRegistrationEnabledAsynTask asynIsRegistrationEnabled = new IsRegistrationEnabledAsynTask(isRegistrationEnabledInputModel, this, this);
            asynIsRegistrationEnabled.executeOnExecutor(threadPoolExecutor);

            /*Calling languageList API*/
            LanguageListInputModel languageListInputModel = new LanguageListInputModel();
            languageListInputModel.setAuthToken(authTokenStr);
            languageListInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
            languageListInputModel.setLangCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            GetLanguageListAsynTask asynGetLanguageList = new GetLanguageListAsynTask(languageListInputModel, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
            asynGetLanguageList.executeOnExecutor(threadPoolExecutor);

            /*Calling Group API*/
            GetUserGroupInputModel userGroupInputModel = new GetUserGroupInputModel();
            userGroupInputModel.setAuthToken(authTokenStr);
            GetUserGroupAsynTask userGroupAsynTask = new GetUserGroupAsynTask(userGroupInputModel, this, this);
            userGroupAsynTask.executeOnExecutor(threadPoolExecutor);


        } else {
            noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
            noInternetLayout.setVisibility(View.VISIBLE);
            geoBlockedLayout.setVisibility(View.GONE);
            imageResize.setVisibility(View.GONE);
            videoView.setVisibility(View.GONE);
            videolayout.setVisibility(View.GONE);
        }
    }

    private void callingApis() {
        apiCall();
    }

    private Context getThemedContext() {
        ContextThemeWrapper themedContext;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            themedContext = new ContextThemeWrapper(SplashScreen.this, R.style.MyAlertDialogStyle);
        } else {
            themedContext = new ContextThemeWrapper(SplashScreen.this, android.R.style.Theme_Light_NoTitleBar);
        }
        return themedContext;
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 111: {
                for (String permission : permissions) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                        //denied
                        finish();
                        Log.e("denied", permission);
                    } else {
                        if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                            //allowed
                            {
                                apiCall();
                            }

                            Log.e("allowed", permission);
                        } else {
                            //set to never ask again
                            goTosettings();
                        }
                    }
                }

            }
        }
    }


    private void goTosettings() {
        new AlertDialog.Builder(getThemedContext()).setTitle(getResources().getString(R.string.permissionsRequired))
                .setMessage(getResources().getString(R.string.permissionsRequiredMessage))
                .setPositiveButton("settings", new DialogInterface.OnClickListener() {
                    @Override
                    @SuppressWarnings("InlinedAPI")
                    public void onClick(DialogInterface dialog, int which) {
                        openSettings();

                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                        finish();
                    }
                }).create().show();

    }

    public void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", getPackageName(), null));
        startActivityForResult(intent, RC_SETTINGS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SETTINGS) {
            callingApis();
        }
    }


    /**
     * @author Kushal
     * Check for update, google play service and permission and minimum SDK
     */
    public void checkAppUpdate() {
        if (featureHandler.getFeatureStatus(FeatureHandler.IS_FORCE_UPDATE_ENABLE,
                FeatureHandler.DEFAULT_IS_FORCE_UPDATE_ENABLE)) {
            final String packageName = getApplicationContext().getPackageName();

            imageResize.setVisibility(View.VISIBLE);
            noInternetLayout.setVisibility(View.GONE);

            new UpdateCheck().execute(packageName);
        }

        callingApis();
    }


    private void showDeviceNotSupportedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getThemedContext());
        builder.setCancelable(false);
        builder.setMessage(languagePreference.getTextofLanguage(DEVICE_NOT_SUPPORTED, DEFAULT_DEVICE_NOT_SUPPORTED))
                .setTitle(languagePreference.getTextofLanguage(ALERT, DEFAULT_ALERT)); // set a title

        builder.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
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
    }

    @Override
    public void onGetUserGroupPreExecuteStarted() {

    }

    @Override
    public void onGetUserGroupPostExecuteCompleted(String responseStr, int code, String message) {
        if (code == 200) {
            preferenceManager.setGroupArrayToPref(responseStr);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onCheckGeoBlockCountryPreExecuteStarted() {
    }

    @Override
    public void onCheckGeoBlockCountryPostExecuteCompleted(CheckGeoBlockOutputModel checkGeoBlockOutputModel, int status, String message) {
        geoBloackCalled = true;

        if (checkGeoBlockOutputModel == null) {
            Util.geoBloackSuccessStatus = 3;

        } else {
            if (status > 0 && status == 200) {
                preferenceManager.setCountryCodeToPref(checkGeoBlockOutputModel.getCountrycode().trim());
                preferenceManager.setStateFromPref(checkGeoBlockOutputModel.getState().trim());
                preferenceManager.setCityFromPref(checkGeoBlockOutputModel.getCity().trim());
                Util.geoBloackSuccessStatus = 1;

            } else if (status == 454) {
                Util.geoBloackSuccessStatus = 2;
            } else {
                Util.geoBloackSuccessStatus = 5;
            }
        }


        apiCheck();

    }

    @Override
    public void onIsRegistrationenabledPreExecuteStarted() {

    }

    @Override
    public void onIsRegistrationenabledPostExecuteCompleted(IsRegistrationEnabledOutputModel
                                                                    isRegistrationEnabledOutputModel, int status, String message, String response) {
        isRegistrationEnabledCalled = true;
        isRegistrationEnabledSuccessStatus = 1;
        try {
            featureHandler.setDefaultFeaturePref(response, isRegistrationEnabledOutputModel);
            //store image width and height for UGC
            preferenceManager.setImageWidthAndHeightForUGC(isRegistrationEnabledOutputModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*@BISHAL
         */
        if (status == 200) {
            languagePreference.setLanguageSharedPrefernce(RATING, "" + isRegistrationEnabledOutputModel.getRating());

            languagePreference.setLanguageSharedPrefernce(RATING, "" + isRegistrationEnabledOutputModel.getRating());

            languagePreference.setLanguageSharedPrefernce(IS_RESTRICT_DEVICE, "" + isRegistrationEnabledOutputModel.getIsRestrictDevice());
            languagePreference.setLanguageSharedPrefernce(IS_ONE_STEP_REGISTRATION, "" + isRegistrationEnabledOutputModel.getSignup_step());
            languagePreference.setLanguageSharedPrefernce(IS_MYLIBRARY, "" + isRegistrationEnabledOutputModel.getIsMylibrary());

            languagePreference.setLanguageSharedPrefernce(IS_STREAMING_RESTRICTION, "" + isRegistrationEnabledOutputModel.getIs_streaming_restriction());
            languagePreference.setLanguageSharedPrefernce(IS_OFFLINE, "" + isRegistrationEnabledOutputModel.getIs_offline());
            languagePreference.setLanguageSharedPrefernce(IS_CHROMECAST, "" + isRegistrationEnabledOutputModel.getChromecast());
            languagePreference.setLanguageSharedPrefernce(IS_REGISTRATION_ENABLED, "" + isRegistrationEnabledOutputModel.getIs_registration_enabled());

            languagePreference.setLanguageSharedPrefernce(REGISTER_THROUGH, "" + isRegistrationEnabledOutputModel.getRegister_through());
            languagePreference.setLanguageSharedPrefernce(IS_OTP_ENABLE, "" + isRegistrationEnabledOutputModel.getIs_otp_enabled());
            languagePreference.setLanguageSharedPrefernce(ALLOW_ADD_EMAIL, "" + isRegistrationEnabledOutputModel.getAllow_add_email());
            languagePreference.setLanguageSharedPrefernce(ALLOW_ADD_PHONE_NO, "" + isRegistrationEnabledOutputModel.getAllow_add_phone_no());
            languagePreference.setLanguageSharedPrefernce(OTP_EXPIRY_DURATION, "" + isRegistrationEnabledOutputModel.getOtp_expiry_duration());

            preferenceManager.setLoginFeatureToPref(isRegistrationEnabledOutputModel.getIs_login());

            preferenceManager.setssoLoginFeatureToPref(isRegistrationEnabledOutputModel.getSso_identity_provider());


            featureHandler.setFeatureFlag(FeatureHandler.IS_LOGIN_REGISTRATION_REQUIRE, "1");

        } else if (status == 455) {
            preferenceManager.setLoginFeatureToPref(isRegistrationEnabledOutputModel.getIs_login());
            featureHandler.setFeatureFlag(FeatureHandler.IS_LOGIN_REGISTRATION_REQUIRE, "0");
        } else {
            isRegistrationEnabledMsg = languagePreference.getTextofLanguage(OOPS_SOMETHING_WENT_WRONG, DEFAULT_OOPS_SOMETHING_WENT_WRONG);
            isRegistrationEnabledSuccessStatus = 5;
        }
        apiCheck();

    }

    @Override
    public void onGetLanguageListPreExecuteStarted() {

    }

    @Override
    public void onGetLanguageListPostExecuteCompleted
            (ArrayList<LanguageListOutputModel> languageListOutputArray, int status, String
                    message, String defaultLanguage) {

        languageListCalled = true;
        languageListSuccessStatus = 1;


        if (languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, "").equalsIgnoreCase("")) {
            languagePreference.setLanguageSharedPrefernce(SELECTED_LANGUAGE_CODE, defaultLanguage);
            default_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);
            if (languageListOutputArray != null && languageListOutputArray.size() > 0) {
                preferenceManager.setSelectedLanguageTexttoPref(languageListOutputArray.get(0).getLanguageName());
            }


            /*
             * @Desc: If the user selected as their daufault language in CMS it would affect the UI accordingly
             *        else it would set default as english, when newly install app.
             * */
            setDefaultLanguage(defaultLanguage);

        } else {
            default_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, defaultLanguage);

            /*
             * @Desc: If the user selected as their daufault language in CMS it would affect the UI accordingly
             *        else it would set default as english.
             * */
            setDefaultLanguage(default_Language);
        }

        for (int i = 0; i < languageListOutputArray.size(); i++) {

            LanguageModel languageModel = new LanguageModel();
            languageModel.setLanguageId(languageListOutputArray.get(i).getLanguageCode());
            languageModel.setLanguageName(languageListOutputArray.get(i).getLanguageName());
            if (default_Language.equalsIgnoreCase(languageListOutputArray.get(i).getLanguageCode())) {
                languageModel.setIsSelected(true);

            } else {
                languageModel.setIsSelected(false);
            }

            languageModels.add(languageModel);
        }

        Util.languageModel = languageModels;

        if (languageModels.size() == 1) {
            preferenceManager.setLanguageListToPref("1");
        } else {
            preferenceManager.setLanguageListToPref("" + languageModels.size());
        }

    }

    /*
     * @Desc: Setting up the default language.
     * */
    private void setDefaultLanguage(String defaultLanguage) {
//        LocaleLanguageHelper.setLocale(this, defaultLanguage);
    }

    @Override
    public void onGet_UserProfilePreExecuteStarted() {

    }

    @Override
    public void onGet_UserProfilePostExecuteCompleted(Get_UserProfile_Output get_userProfile_output, int code, String message, String status) {

        profileCalled = true;
        profileSuccessStatus = 1;

        if (status == null) {
            isSubscribed = "0";
        }
        if (code == 200) {
            isSubscribed = get_userProfile_output.getIsSubscribed();
            IsFree = get_userProfile_output.getIsFree();
            preferenceManager.setLoginProfImgoPref(get_userProfile_output.getProfile_image());

            final VodApplication globalVariable = (VodApplication) getApplicationContext();
            globalVariable.setISSUBSCRIBED(isSubscribed);

            SharedPreferences sharedprefPlanList = getSharedPreferences("myPlanListPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorPlanList = sharedprefPlanList.edit();
            editorPlanList.putString("isSubscribed", isSubscribed);
            editorPlanList.putString("isFree", IsFree);
            editorPlanList.apply();
            editorPlanList.commit();

        }

        apiCheck();

    }

    /*
     * @desc: To check whether all apis has been called successfully
     * */
    private void apiCheck() {

        if (sdkInitializerCalled && isRegistrationEnabledCalled ) {

            if (sdkInitializerSuccessStatus == 2) {
                geoTextView.setText(languagePreference.getTextofLanguage(APP_NO_LONGER_ACTIVE, DEFAULT_APP_NO_LONGER_ACTIVE));
                noInternetLayout.setVisibility(View.GONE);
                geoBlockedLayout.setVisibility(View.VISIBLE);
                return;
            }

            if (sdkInitializerSuccessStatus == 5) {
                noInternetTextView.setText(languagePreference.getTextofLanguage(OOPS_SOMETHING_WENT_WRONG, DEFAULT_OOPS_SOMETHING_WENT_WRONG));
                noInternetLayout.setVisibility(View.VISIBLE);
                geoBlockedLayout.setVisibility(View.GONE);
                imageResize.setVisibility(View.GONE);
                videoView.setVisibility(View.GONE);
                videolayout.setVisibility(View.GONE);
                return;
            }

            if (isRegistrationEnabledSuccessStatus == 5) {
                noInternetTextView.setText(languagePreference.getTextofLanguage(OOPS_SOMETHING_WENT_WRONG, DEFAULT_OOPS_SOMETHING_WENT_WRONG));
                noInternetLayout.setVisibility(View.VISIBLE);
                geoBlockedLayout.setVisibility(View.GONE);
                imageResize.setVisibility(View.GONE);
                videoView.setVisibility(View.GONE);
                videolayout.setVisibility(View.GONE);
                return;
            }

            if ((!featureHandler.getFeatureStatus(FeatureHandler.PAYMENT_INTIGRATION_PHASE, FeatureHandler.DEFAULT_PAYMENT_INTIGRATION_PHASE))
                    && preferenceManager.getUseridFromPref() != null) {

                if (!profileCalled) {
                    return;
                }
            }

            // This Code Is Done For The One Step Registration.
            //    Call_One_Step_Procedure();
            CallRegistrationProcedure();


        }
    }


    public void CallRegistrationProcedure() {
        if (nextPageNavigation)
            return;

        nextPageNavigation = true;
        Intent mIntent = new Intent(SplashScreen.this, MainActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(mIntent);
        finish();
        overridePendingTransition(0, 0);

    }


    @Override
    public void onPreExexuteListner() {

    }

    @Override
    public void onPostExecuteListner(int status) {
        SDKInitializer.setData(this);
        sdkInitializerCalled = true;

        if (status == 200) {
            sdkInitializerSuccessStatus = 1;
        } else if (status == Util.ERROR_CODE_EXPIRED_AUTHTOKEN) {
            sdkInitializerSuccessStatus = 2;
        } else {
            sdkInitializerSuccessStatus = 5;
        }

    }


    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    public void callProfileAPI() {
        if (preferenceManager != null) {
            user_Id = preferenceManager.getUseridFromPref();
            email_Id = preferenceManager.getEmailIdFromPref();

            if (user_Id != null && email_Id != null) {
                Get_UserProfile_Input get_userProfile_input = new Get_UserProfile_Input();
                get_userProfile_input.setAuthToken(authTokenStr);
                get_userProfile_input.setEmail(email_Id);
                get_userProfile_input.setUser_id(user_Id);
                get_userProfile_input.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                get_userProfile_input.setCountryCode(preferenceManager.getCountryCodeFromPref());
                GetUserProfileAsynctask asynLoadProfileDetails = new GetUserProfileAsynctask(get_userProfile_input, this, this, false, new DataController(this));
                asynLoadProfileDetails.executeOnExecutor(threadPoolExecutor);
            }
        }
    }


    public void callAppConfigAPI() {
        AppConfigInputModel appConfigInputModel = new AppConfigInputModel();
        appConfigInputModel.setAuthToken(authTokenStr);
        appConfigInputModel.setDeviceType("1"); //1-Android, 2-IOS,3-Roku,4-Apple TV,5-Fire TV
        appConfigInputModel.setLanguageCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        AppConfigAsynctask appConfigAsynctask = new AppConfigAsynctask(appConfigInputModel, new AppConfigAsynctask.AppConfigListener() {
            @Override
            public void onAppConfigPreExecuteStarted() {

            }

            @Override
            public void onAppConfigPostExecuteCompleted(AppConfigOutputModel appConfigOutputModel, int code, String status, String message, String force_update_version) {
                forceUpdate_version = force_update_version;

            }
        }, this, new DataController(this));
        appConfigAsynctask.executeOnExecutor(threadPoolExecutor);
    }


    /**
     * @author Sanjay
     * @desc Checking guest user
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String guestUserld = null;

        if (preferenceManager != null) {
            guestUserld = preferenceManager.getGuestUseridFromPref();
        }

        if (guestUserld != null) {
            preferenceManager.setGuestUserIdToPref(null);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("my_pid", android.os.Process.myPid());
    }


}