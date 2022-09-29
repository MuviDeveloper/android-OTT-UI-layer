package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.CANCEL_BUTTON;
import static com.home.vod.preferences.LanguagePreference.DEAFULT_CANCEL_BUTTON;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_DETAILS_NOT_FOUND_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_EMAIL_PASSWORD_INVALID;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_FORGOT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_GOOGLE_FCM_TOKEN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NEW_HERE_TITLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_INVALID_EMAIL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_INVALID_PHONE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OR;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_REGISTER_THROUGH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIMULTANEOUS_LOGOUT_SUCCESS_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_EMIAL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_MOBILE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VERIFY_EMAIL_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VERIFY_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.DETAILS_NOT_FOUND_ALERT;
import static com.home.vod.preferences.LanguagePreference.EMAIL_PASSWORD_INVALID;
import static com.home.vod.preferences.LanguagePreference.FORGOT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.GOOGLE_FCM_TOKEN;
import static com.home.vod.preferences.LanguagePreference.LOGIN;
import static com.home.vod.preferences.LanguagePreference.NEW_HERE_TITLE;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS_INVALID_EMAIL;
import static com.home.vod.preferences.LanguagePreference.OOPS_INVALID_PHONE;
import static com.home.vod.preferences.LanguagePreference.OR;
import static com.home.vod.preferences.LanguagePreference.REGISTER_THROUGH;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.SIMULTANEOUS_LOGOUT_SUCCESS_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.TEXT_EMIAL;
import static com.home.vod.preferences.LanguagePreference.TEXT_MOBILE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.TEXT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.VERIFY_EMAIL_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.VERIFY_MESSAGE;
import static com.home.vod.util.Constant.authTokenStr;
import static com.home.vod.util.FeatureHandler.SKIP_SUBSCRIPTION_PROCESS;
import static com.home.vod.util.Util.forceUpdateDialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.home.apisdk.apiController.GetSimultaneousLogoutAsync;
import com.home.apisdk.apiController.GetUserProfileAsynctask;
import com.home.apisdk.apiController.LoginAsynTask;
import com.home.apisdk.apiController.LogoutAsynctask;
import com.home.apisdk.apiModel.Get_UserProfile_Output;
import com.home.apisdk.apiModel.Login_input;
import com.home.apisdk.apiModel.Login_output;
import com.home.apisdk.apiModel.LogoutInput;
import com.home.apisdk.apiModel.SimultaneousLogoutInput;
import com.home.vod.R;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import player.activity.Player;


public class LoginActivity extends AppCompatActivity implements
        LoginAsynTask.LoinDetailsListener,
        LogoutAsynctask.LogoutListener,
        GetSimultaneousLogoutAsync.SimultaneousLogoutAsyncListener,
        GetUserProfileAsynctask.Get_UserProfileListener {

    public static final int GUESTUSER_RESULT = 10002;
    public static Activity loginA;
    public static ProgressBarHandler progressBarHandler;


    private TextView or_text;
    private RelativeLayout registrationLayout;
    private EditText editEmailStr, editPasswordStr, editTextMobileStr;
    private TextView forgotPassword, loginNewUser, signUpTextView;
    private Button loginButton;
    private TextView logout_text;
    private Button ok, cancel;
    private AlertDialog logout_alert;
    private ImageView cross;


    private String deviceRestrictionMessage = "";
    private String register_through;
    private int corePoolSize = 60;
    private int maximumPoolSize = 80;
    private int keepAliveTime = 10;
    private String regEmailStr = "", regPasswordStr = "", mobileStr = "";

    private ProgressDialog progressDialog1;
    private ProgressBarHandler pDialog;
    private Player playerModel;
    private LanguagePreference languagePreference;
    private PreferenceManager preferenceManager;
    private FeatureHandler featureHandler;

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
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().setBackgroundDrawableResource(R.drawable.app_background);

        loginA = this;
        pDialog = new ProgressBarHandler(LoginActivity.this);
        languagePreference = LanguagePreference.getLanguagePreference((this));
        featureHandler = FeatureHandler.getFeaturePreference(LoginActivity.this);

        /*
         * @desc:The below condition will execute when only registration enabled from cms*/

        if ((!featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN, FeatureHandler.DEFAULT_IS_LOGIN))
                && (featureHandler.getFeatureStatus(FeatureHandler.IS_REGISTRATION_ENABLED, FeatureHandler.DEFAULT_IS_REGISTRATION_ENABLED))) {
            final Intent detailsIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            detailsIntent.putExtra("PlayerModel", playerModel);
            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            startActivity(detailsIntent);
            finish();
        }

        /*
         * @desc:The function will execute for force update dialog */

        forceUpdateDialog(LoginActivity.this);
        register_through = languagePreference.getTextofLanguage(REGISTER_THROUGH, DEFAULT_REGISTER_THROUGH);

        /**
         * Here we are checking app avaibility status
         */
        checkGeoBlock();

        /**
         * Following block has been used to restrict user Registration, if registration is disabled at CMS-Level
         */

        try {
            registrationLayout = findViewById(R.id.newUserLinearLayout);
            if (!featureHandler.getFeatureStatus(FeatureHandler.IS_REGISTRATION_ENABLED, FeatureHandler.DEFAULT_IS_REGISTRATION_ENABLED)) {
                registrationLayout.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        /**
         * @description for new UI change mantis-id: 19619
         */

        if (getIntent().hasExtra("PlayerModel")) {
            playerModel = (Player) getIntent().getSerializableExtra("PlayerModel");
            if (playerModel != null) {
                playerModel.setIsstreaming_restricted(Util.getStreamingRestriction(languagePreference));
            }
        }


        /**
         * @author Debashish
         * @description ImageView button backpress functionality
         */
        cross = findViewById(R.id.dismiss);

        if (Util.isColorDark(getResources().getColor(R.color.appBackgroundColor))) {
            cross.setAlpha(0.5f);
        }

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        editEmailStr = findViewById(R.id.email);
        editTextMobileStr = findViewById(R.id.login_mobile_number);

        FontUtls.loadFont(LoginActivity.this, getResources().getString(R.string.light_fonts), editEmailStr);
        FontUtls.loadFont(LoginActivity.this, getResources().getString(R.string.light_fonts), editTextMobileStr);

        editEmailStr.setHint(languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL));
        editTextMobileStr.setHint(languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER));

        editEmailStr.setVisibility(View.GONE);
        editTextMobileStr.setVisibility(View.GONE);

        if (register_through.equals("1")) {
            editEmailStr.setVisibility(View.VISIBLE);
        } else {
            editTextMobileStr.setVisibility(View.VISIBLE);
        }

        editPasswordStr = findViewById(R.id.pwd);


        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (source.charAt(i) == '\n') {
                        return " ";
                    }
                }
                return null;
            }
        };

        editEmailStr.setFilters(new InputFilter[]{filter});
        editPasswordStr.setFilters(new InputFilter[]{filter});

        FontUtls.loadFont(LoginActivity.this, getResources().getString(R.string.light_fonts), editPasswordStr);

        editPasswordStr.setHint(languagePreference.getTextofLanguage(TEXT_PASSWORD, DEFAULT_TEXT_PASSWORD));
        forgotPassword = findViewById(R.id.forgot);
        FontUtls.loadFont(LoginActivity.this, getResources().getString(R.string.light_fonts), forgotPassword);
        forgotPassword.setText(languagePreference.getTextofLanguage(FORGOT_PASSWORD, DEFAULT_FORGOT_PASSWORD));


        loginNewUser = findViewById(R.id.loginNewUser);
        FontUtls.loadFont(LoginActivity.this, getResources().getString(R.string.light_fonts), loginNewUser);

        loginNewUser.setText(languagePreference.getTextofLanguage(NEW_HERE_TITLE, DEFAULT_NEW_HERE_TITLE));

        signUpTextView = findViewById(R.id.register);
        FontUtls.loadFont(LoginActivity.this, getResources().getString(R.string.light_fonts), signUpTextView);

        signUpTextView.setText(languagePreference.getTextofLanguage(BTN_REGISTER, DEFAULT_BTN_REGISTER));

        loginButton = findViewById(R.id.login);
        FontUtls.loadFont(LoginActivity.this, getResources().getString(R.string.regular_fonts), loginButton);

        loginButton.setText(languagePreference.getTextofLanguage(LOGIN, DEFAULT_LOGIN));
        preferenceManager = PreferenceManager.getPreferenceManager(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.hideKeyboard(LoginActivity.this);
                loginButtonClicked();

            }
        });


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent detailsIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                detailsIntent.putExtra("PlayerModel", playerModel);
                detailsIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(detailsIntent);
                finish();
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.favorite_clicked) {
                    Util.favorite_clicked = true;
                }

                if (Util.followed_clicked) {
                    Util.followed_clicked = true;
                }


                final Intent detailsIntent = new Intent(LoginActivity.this, RegisterActivity.class);

                if (getIntent().getStringExtra("from") != null) {
                    detailsIntent.putExtra("from", getIntent().getStringExtra("from"));
                }
                detailsIntent.putExtra("PlayerModel", playerModel);
                detailsIntent.putExtra("banner_subscription_login_check", getIntent().getStringExtra("banner_subscription_login_check"));
                detailsIntent.putExtra("is_a_guest_user", getIntent().getStringExtra("is_a_guest_user"));
                detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                detailsIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(detailsIntent);
                finish();

            }
        });



        or_text = findViewById(R.id.or_text);
        or_text.setText(languagePreference.getTextofLanguage(OR, DEFAULT_OR));

        /* desc:Below condition use for visibility of guest user button */

    }


    public void loginButtonClicked() {
        regEmailStr = editEmailStr.getText().toString().trim();
        regPasswordStr = editPasswordStr.getText().toString();
        mobileStr = editTextMobileStr.getText().toString();

        if (NetworkStatus.getInstance().isConnected(this)) {

            if (register_through.equals("1")) {

                if ((!regEmailStr.equals("")) && (!regPasswordStr.equals(""))) {

                    if (!Util.isValidMail(regEmailStr)) { // invalid email
                        Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_EMAIL, DEFAULT_OOPS_INVALID_EMAIL), Toast.LENGTH_LONG).show();
                        return;
                    }

                    Login_input login_input = new Login_input();
                    login_input.setAuthToken(authTokenStr);

                    if (register_through.equals("1")) {
                        login_input.setEmail(regEmailStr);
                    } else {
                        login_input.setMobile(mobileStr);
                    }

                    login_input.setPassword(regPasswordStr);
                    login_input.setDevice_id(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
                    login_input.setGoogle_id(languagePreference.getTextofLanguage(GOOGLE_FCM_TOKEN, DEFAULT_GOOGLE_FCM_TOKEN));
                    login_input.setDevice_type("1");
                    login_input.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    login_input.setCountryCode(preferenceManager.getCountryCodeFromPref());
                    LoginAsynTask asyncReg =
                            new LoginAsynTask(login_input, this, this, new DataController(LoginActivity.this));
                    asyncReg.executeOnExecutor(threadPoolExecutor);
                } else {
                    if (regEmailStr.equals("")) {
                        Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL), Toast.LENGTH_LONG).show();
                    } else if (regPasswordStr.equals("")) {
                        Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(TEXT_PASSWORD, DEFAULT_TEXT_PASSWORD), Toast.LENGTH_LONG).show();
                    }
                }
            } else {

                if ((!mobileStr.equals("")) && (!regPasswordStr.equals(""))) {

                    if (Util.isValidMobile(mobileStr)) {
                        Login_input login_input = new Login_input();
                        login_input.setAuthToken(authTokenStr);
                        login_input.setEmail(regEmailStr);

                        if (register_through.equals("1")) {
                            login_input.setEmail(regEmailStr);
                        } else {
                            login_input.setMobile(mobileStr);
                        }

                        login_input.setPassword(regPasswordStr);
                        login_input.setDevice_id(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
                        login_input.setGoogle_id(languagePreference.getTextofLanguage(GOOGLE_FCM_TOKEN, DEFAULT_GOOGLE_FCM_TOKEN));
                        login_input.setDevice_type("1");
                        login_input.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                        login_input.setCountryCode(preferenceManager.getCountryCodeFromPref());
                        LoginAsynTask asyncReg = new LoginAsynTask(login_input, this, this, new DataController(LoginActivity.this
                        ));
                        asyncReg.executeOnExecutor(threadPoolExecutor);
                    } else {
                        Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_PHONE, DEFAULT_OOPS_INVALID_PHONE_NUMBER), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (mobileStr.equals("")) {
                        Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER), Toast.LENGTH_LONG).show();
                    } else if (regPasswordStr.equals("")) {
                        Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(TEXT_PASSWORD, DEFAULT_TEXT_PASSWORD), Toast.LENGTH_LONG).show();
                    }
                }
            }

        } else {
            Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onBackPressed() {

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        super.onBackPressed();
    }


    public void removeFocusFromViewsAndFinish() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GUESTUSER_RESULT && resultCode == RESULT_OK) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void show_logout_popup(String msg) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this, R.style.MyAlertDialogStyle);
        LayoutInflater inflater = (LayoutInflater) LoginActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View convertView = inflater.inflate(R.layout.logout_popup, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("");

        logout_text = convertView.findViewById(R.id.logout_text);
        ok = convertView.findViewById(R.id.ok);
        cancel = convertView.findViewById(R.id.cancel);


        // Font implemented Here//
        FontUtls.loadFont(LoginActivity.this, getResources().getString(R.string.regular_fonts), logout_text);
        FontUtls.loadFont(LoginActivity.this, getResources().getString(R.string.regular_fonts), ok);
        FontUtls.loadFont(LoginActivity.this, getResources().getString(R.string.regular_fonts), cancel);

        logout_text.setText(msg);
        ok.setText(" " + languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK));
        cancel.setText(" " + languagePreference.getTextofLanguage(CANCEL_BUTTON, DEAFULT_CANCEL_BUTTON));

        ok.setOnClickListener(v -> {
            if (NetworkStatus.getInstance().isConnected(LoginActivity.this)) {

                // Call Api For Simultaneous Logout
                SimultaneousLogoutInput simultaneousLogoutInput = new SimultaneousLogoutInput();
                simultaneousLogoutInput.setAuthToken(authTokenStr);
                simultaneousLogoutInput.setDevice_type("1");
                simultaneousLogoutInput.setEmail_id(regEmailStr);
                simultaneousLogoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
                //code end
                simultaneousLogoutInput.setLaguageCode(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                GetSimultaneousLogoutAsync asynSimultaneousLogout = new GetSimultaneousLogoutAsync(simultaneousLogoutInput, LoginActivity.this, LoginActivity.this);
                asynSimultaneousLogout.executeOnExecutor(threadPoolExecutor);
            } else {
                Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
            }

        });

        cancel.setOnClickListener(v -> logout_alert.dismiss());
        logout_alert = alertDialog.show();

        if (logout_alert.getCurrentFocus() != null) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        }
    }

    @Override
    public void onSimultaneousLogoutPreExecuteStarted() {
        progressDialog1 = new ProgressDialog(LoginActivity.this, R.style.MyTheme);
        progressDialog1.setCancelable(false);
        progressDialog1.setProgressStyle(android.R.style.Widget_ProgressBar_Large_Inverse);
        progressDialog1.setIndeterminate(false);
        progressDialog1.setIndeterminateDrawable(getResources().getDrawable(R.drawable.dialog_progress_rawable));
        progressDialog1.show();
    }

    @Override
    public void onSimultaneousLogoutPostExecuteCompleted(int code) {
        if (progressDialog1 != null && progressDialog1.isShowing()) {
            progressDialog1.hide();
            progressDialog1 = null;
        }
        if (code == 200) {
            // Allow the user to activity_login
            if (logout_alert.isShowing()) {
                logout_alert.dismiss();
            }

            Toast.makeText(getApplicationContext(), languagePreference.getTextofLanguage(SIMULTANEOUS_LOGOUT_SUCCESS_MESSAGE, DEFAULT_SIMULTANEOUS_LOGOUT_SUCCESS_MESSAGE), Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN), Toast.LENGTH_LONG).show();
        }
    }


    public void LogOut() {
        String loginHistoryIdStr = preferenceManager.getLoginHistIdFromPref();
        LogoutInput logoutInput = new LogoutInput();
        logoutInput.setAuthToken(authTokenStr);
        logoutInput.setLogin_history_id(loginHistoryIdStr);
        logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
        asynLogoutDetails.executeOnExecutor(threadPoolExecutor);
    }


    public void setResultAtFinishActivity() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void sendBroadCast() {
        Intent Sintent = new Intent("LOGIN_SUCCESS");
        sendBroadcast(Sintent);
    }


    /**
     * Following method is responsible to navigte to homepge
     */
    public void navigateToHomepage() {

        try {
            Util.image_orentiation.clear();
            Intent in = new Intent(LoginActivity.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * Following method is responsible to check whether the app is available for the country or not.
     */
    public void checkGeoBlock() {
        try {
            if (Util.geoBloackSuccessStatus == 2) {
                Intent intent = new Intent(LoginActivity.this, GeoblockAlertActivity.class);
                startActivity(intent);
                finish();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onGet_UserProfilePreExecuteStarted() {
        pDialog = new ProgressBarHandler(LoginActivity.this);
        pDialog.show();
    }

    @Override
    public void onGet_UserProfilePostExecuteCompleted(Get_UserProfile_Output get_userProfile_output, int code, String message, String status) {
        dismissProgressDialog();

        if (code == 200) {


            try {
                SharedPreferences sharedprefPlanList = getSharedPreferences("myPlanListPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorPlanList = sharedprefPlanList.edit();
                editorPlanList.putString("isSubscribed", get_userProfile_output.getIsSubscribed());
                editorPlanList.putString("isFree", get_userProfile_output.getIsFree());
                editorPlanList.apply();
                editorPlanList.commit();
            } catch (Exception e) {
                e.toString();
            }

            preferenceManager.setLogInStatusToPref("1");
            preferenceManager.setPwdToPref("");
            preferenceManager.setDispPhoneToPref(get_userProfile_output.getPhone());
            preferenceManager.setDispNameToPref(get_userProfile_output.getDisplay_name());
            preferenceManager.setLoginProfImgoPref(get_userProfile_output.getProfile_image());
            preferenceManager.setIsSubscribedToPref(get_userProfile_output.getIsSubscribed());
            preferenceManager.setLoginHistIdPref("");
            preferenceManager.setFreeUserPref(get_userProfile_output.getIsFree());

            if (preferenceManager != null && preferenceManager.getGuestUseridFromPref() != null) {
                preferenceManager.setGuestUserIdToPref(null);
            }

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }

            if (Util.review_instance_available) {
                finish();
                return;
            }

            if (Util.favorite_clicked) {
                finish();
                return;
            }
            if (Util.followed_clicked) {
                finish();
                return;
            }


            if (getIntent().getStringExtra("from") != null) {
                removeFocusFromViewsAndFinish();
            } else {
                navigateToHomepage();
            }
        }
    }

    private void dismissProgressDialog() {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onLoginPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing()) {
            pDialog.show();
        }
    }

    @Override
    public void onLoginPostExecuteCompleted(Login_output login_output, int status, String message) {

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }


        if (status > 0) {
            if (status == 200) {
                regEmailStr = login_output.getEmail();
                loginPostExecute(login_output);

            } else if (status == 202) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(LoginActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setTitle(languagePreference.getTextofLanguage(VERIFY_MESSAGE, DEFAULT_VERIFY_MESSAGE));
                dlgAlert.setMessage(languagePreference.getTextofLanguage(VERIFY_EMAIL_MESSAGE, DEFAULT_VERIFY_EMAIL_MESSAGE));
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                editEmailStr.setText("");
                                editTextMobileStr.setText("");
                                editPasswordStr.setText("");
                                editEmailStr.requestFocus();
                                editTextMobileStr.requestFocus();
                            }
                        });
                dlgAlert.create().show();
            } else if (status == 300) {
                // Show Popup For the Simultaneous Logout
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.hide();
                }
                show_logout_popup(login_output.getMsg());
            } else {
                try {
                    if (pDialog != null && pDialog.isShowing()) {
                        pDialog.hide();
                    }
                } catch (IllegalArgumentException ex) {
                    status = 0;
                }

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(LoginActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(languagePreference.getTextofLanguage(EMAIL_PASSWORD_INVALID, DEFAULT_EMAIL_PASSWORD_INVALID));
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
            }
        } else {
            try {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.hide();
                }
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(LoginActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(languagePreference.getTextofLanguage(DETAILS_NOT_FOUND_ALERT, DEFAULT_DETAILS_NOT_FOUND_ALERT));
                dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                        (dialog, id) -> dialog.cancel());
                dlgAlert.create().show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param login_output
     */
    public void loginPostExecute(Login_output login_output) {
        preferenceManager.setLogInStatusToPref("1");
        preferenceManager.setUserIdToPref(login_output.getId());
        preferenceManager.setPwdToPref("");
        preferenceManager.setEmailIdToPref(login_output.getEmail());
        preferenceManager.setDispPhoneToPref(login_output.getMobile());
        preferenceManager.setDispNameToPref(login_output.getDisplay_name());
        preferenceManager.setLoginProfImgoPref(login_output.getProfile_image());
        preferenceManager.setIsSubscribedToPref(login_output.getIsSubscribed());
        preferenceManager.setLoginHistIdPref(login_output.getLogin_history_id());
        preferenceManager.setFreeUserPref(login_output.getIsFree());
        preferenceManager.setSocialLoginPref("0");
        preferenceManager.setGroupTypePref(login_output.getGroup_type());


        if (preferenceManager != null && preferenceManager.getGuestUseridFromPref() != null) {
            preferenceManager.setGuestUserIdToPref(null);
        }

        try {
            SharedPreferences sharedprefPlanList = getSharedPreferences("myPlanListPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorPlanList = sharedprefPlanList.edit();
            editorPlanList.putString("isSubscribed", login_output.getIsSubscribed());
            editorPlanList.putString("isFree", login_output.getIsFree());

            editorPlanList.apply();
            editorPlanList.commit();
        } catch (Exception e) {
            e.toString();
        }

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }


        if (Util.review_instance_available) {
            finish();
            return;
        }

        if (Util.favorite_clicked) {
            finish();
            return;
        }
        if (Util.followed_clicked) {
            finish();
            return;
        }

        if (getIntent().getStringExtra("from") != null) {
            removeFocusFromViewsAndFinish();
        } else {
            if (Util.check_for_subscription == 1) {
                setResultAtFinishActivity();

            } else {
                navigateToHomepage();

            }
        }
    }


    @Override
    public void onLogoutPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing()) {
            pDialog.show();
        }
    }

    @Override
    public void onLogoutPostExecuteCompleted(int code, String status, String message) {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }
        } catch (IllegalArgumentException ex) {
            Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (status == null) {
            Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code == 0) {
            Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code > 0) {
            if (code == 200) {
                preferenceManager.clearLoginPref();

                SharedPreferences sharedprefPlanList = getSharedPreferences("myPlanListPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorPlanList = sharedprefPlanList.edit();
                editorPlanList.putString("isSubscribed", "0");
                editorPlanList.putString("isFree", "0");

                editorPlanList.apply();
                editorPlanList.commit();


                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(LoginActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setMessage(deviceRestrictionMessage);
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
                Toast.makeText(LoginActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

            }
        }
    }


}



