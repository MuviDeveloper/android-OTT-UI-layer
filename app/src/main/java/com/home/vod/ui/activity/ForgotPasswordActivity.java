package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.BTN_RE_SEND_OTP;
import static com.home.vod.preferences.LanguagePreference.BTN_SEND_OTP;
import static com.home.vod.preferences.LanguagePreference.BTN_SUBMIT;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_RE_SEND_OTP;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_SEND_OTP;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_SUBMIT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_EMAIL_DOESNOT_EXISTS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_FAILURE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_FORGOT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MOBILE_DOESNOT_EXISTS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NEW_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OTP_EXPIRY_DURATION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_REGISTER_THROUGH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_EMIAL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_MOBILE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_OTP;
import static com.home.vod.preferences.LanguagePreference.EMAIL_DOESNOT_EXISTS;
import static com.home.vod.preferences.LanguagePreference.FAILURE;
import static com.home.vod.preferences.LanguagePreference.FORGOT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.LOGIN;
import static com.home.vod.preferences.LanguagePreference.MOBILE_DOESNOT_EXISTS;
import static com.home.vod.preferences.LanguagePreference.NEW_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OTP_EXPIRY_DURATION;
import static com.home.vod.preferences.LanguagePreference.REGISTER_THROUGH;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.TEXT_EMIAL;
import static com.home.vod.preferences.LanguagePreference.TEXT_MOBILE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.TEXT_OTP;
import static com.home.vod.util.Constant.authTokenStr;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.home.apisdk.apiController.ForgotpassAsynTask;
import com.home.apisdk.apiModel.Forgotpassword_input;
import com.home.apisdk.apiModel.Forgotpassword_output;
import com.home.vod.R;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import player.activity.Player;


public class ForgotPasswordActivity extends AppCompatActivity implements ForgotpassAsynTask.ForgotpassDetailsListener {

    public Toolbar mActionBarToolbar;
    public ImageView toolbarimage;
    public ToolbarTitleHandler toolbarTitleHandler;
    private EditText editEmailStr, editTextMobileStr;
    private TextView logintextView;
    private Button submitButton;
    private ProgressBarHandler pDialog;
    private String loginEmailStr = "", loginMobileStr = "";
    private boolean navigation = false;
    private Player playerModel;
    private int corePoolSize = 60;
    private int maximumPoolSize = 80;
    private int keepAliveTime = 10;
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    private Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
    private LanguagePreference languagePreference;
    private FeatureHandler featureHandler;
    private PreferenceManager preferenceManager;
    private String register_through, loggedInStr;

    private LinearLayout email_layout, otp_layout, otp_send_ll, password_reset_ll, otp_ll;
    private TextView mobile_tv, otp_tv, psw_tv, log_here, new_psw_tv, confirm_psw_tv, mobile_otptimmer;
    private EditText mobile_mtn, enter_otp, new_psw, confirm_psw;
    private Spinner spiner_cc;
    private String otp_expiry_duration;
    private Button send_otp, submit_otp, change_psw;

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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_forgot_password);

        getWindow().setBackgroundDrawableResource(R.drawable.app_background);
        languagePreference = LanguagePreference.getLanguagePreference(this);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        loggedInStr = preferenceManager.getUseridFromPref();
        featureHandler = FeatureHandler.getFeaturePreference(this);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        email_layout = findViewById(R.id.email_layout);
        otp_layout = findViewById(R.id.otp_layout);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);

        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        {
            email_layout.setVisibility(View.VISIBLE);
            otp_layout.setVisibility(View.GONE);
            mActionBarToolbar.setTitle(languagePreference.getTextofLanguage(FORGOT_PASSWORD, DEFAULT_FORGOT_PASSWORD));
            mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        }

        setSupportActionBar(mActionBarToolbar);
        playerModel = (Player) getIntent().getSerializableExtra("PlayerModel");

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
        //  set id to the Action bar back button
        setIdToActionBarBackButton(mActionBarToolbar);

        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /*
         * Desc:this function is calling pangea UI*/
        pangeaUIfun();

        editEmailStr = (EditText) findViewById(R.id.email);
        editTextMobileStr = (EditText) findViewById(R.id.mobile);
        logintextView = (TextView) findViewById(R.id.login);
        submitButton = (Button) findViewById(R.id.submit);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.regular_fonts), submitButton);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), editEmailStr);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), editTextMobileStr);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), logintextView);

        editEmailStr.setHint(languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL));
        editTextMobileStr.setHint(languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER));
        submitButton.setText(languagePreference.getTextofLanguage(BTN_SUBMIT, DEFAULT_BTN_SUBMIT));
        logintextView.setText(languagePreference.getTextofLanguage(LOGIN, DEFAULT_LOGIN));
        logintextView.setVisibility(View.GONE);

        editEmailStr.setVisibility(View.GONE);
        editTextMobileStr.setVisibility(View.GONE);

        register_through = languagePreference.getTextofLanguage(REGISTER_THROUGH, DEFAULT_REGISTER_THROUGH);

        if (register_through.equals("1")) {
            editEmailStr.setVisibility(View.VISIBLE);
        } else {
            editTextMobileStr.setVisibility(View.VISIBLE);
        }

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
        editTextMobileStr.setFilters(new InputFilter[]{filter});
        logintextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent detailsIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                detailsIntent.putExtra("PlayerModel", playerModel);
                detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                detailsIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(detailsIntent);
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    View focusedView = ForgotPasswordActivity.this.getCurrentFocus();
                    if (focusedView != null) {
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }


                    forgotPasswordButtonClicked();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*
     */
    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.back);

            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                if (t.getText().toString().contains(languagePreference.getTextofLanguage(FORGOT_PASSWORD, DEFAULT_FORGOT_PASSWORD))) {
                    t.setId(R.id.page_title_forgot_password);
                }
            }
        }
    }

    /*
     * @Desc: If the value of "generate_pwd" is "1" then backend service will create a new password
     *        for that user and will send that password to the requested Email.
     *        Else the API will behave like previous.
     *
     * generate_pwd, value : "1/0"

     * */

    public void forgotPasswordButtonClicked() {

        loginEmailStr = editEmailStr.getText().toString().trim();
        loginMobileStr = editTextMobileStr.getText().toString().trim();
        boolean isValidEmail = Util.isValidMail(loginEmailStr);
        boolean isValidMobile = Util.isValidMobile(loginMobileStr);

        if (NetworkStatus.getInstance().isConnected(this)) {

            if (register_through.equals("1")) {
                if (!loginEmailStr.equals("")) {
                    if (isValidEmail == true) {

                        Forgotpassword_input forgotpassword_input = new Forgotpassword_input();
                        forgotpassword_input.setAuthToken(authTokenStr);
                        forgotpassword_input.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                        forgotpassword_input.setEmail(loginEmailStr);
                        forgotpassword_input.setMobile(loginMobileStr);
                        forgotpassword_input.setCountryCode(preferenceManager.getCountryCodeFromPref());
                        forgotpassword_input.setGeneratepwd("1");
                        ForgotpassAsynTask asyncPasswordForgot = new ForgotpassAsynTask(forgotpassword_input, this, this);
                        asyncPasswordForgot.executeOnExecutor(threadPoolExecutor);

                    } else {
                        ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(EMAIL_DOESNOT_EXISTS, DEFAULT_EMAIL_DOESNOT_EXISTS));
                    }
                } else {
                    ShowDialog(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY), languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL));
                }

            } else {
                if (!loginMobileStr.equals("")) {
                    if (isValidMobile == true) {

                        Forgotpassword_input forgotpassword_input = new Forgotpassword_input();
                        forgotpassword_input.setAuthToken(authTokenStr);
                        forgotpassword_input.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                        forgotpassword_input.setEmail(loginEmailStr);
                        forgotpassword_input.setMobile(loginMobileStr);
                        forgotpassword_input.setCountryCode(preferenceManager.getCountryCodeFromPref());
                        forgotpassword_input.setGeneratepwd("1");
                        ForgotpassAsynTask asyncPasswordForgot = new ForgotpassAsynTask(forgotpassword_input, this, this);
                        asyncPasswordForgot.executeOnExecutor(threadPoolExecutor);

                    } else {
                        ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(MOBILE_DOESNOT_EXISTS, DEFAULT_MOBILE_DOESNOT_EXISTS));
                    }
                } else {
                    ShowDialog(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY), languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER));
                }

            }

        } else {
            ShowDialog(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY), languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        }

    }

    @Override
    public void onForgotpassDetailsPreExecuteStarted() {

        pDialog = new ProgressBarHandler(ForgotPasswordActivity.this);
        pDialog.show();
    }

    @Override
    public void onForgotpassDetailsPostExecuteCompleted(Forgotpassword_output forgotpassword_output, int status, String message) {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
        } catch (IllegalArgumentException ex) {

        }

        if (status > 0) {
            if (status == 200) {
                navigation = true;
                ShowDialog("", message);

            } else {
                ShowDialog("", message);
            }
        } else {
            ShowDialog("", message);
        }

    }


    @Override
    public void onBackPressed() {

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


        if (loggedInStr != null) {
            finish();
        } else {
            final Intent detailsIntent;
            detailsIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            detailsIntent.putExtra("PlayerModel", playerModel);
            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            startActivity(detailsIntent);
            finish();

        }

        overridePendingTransition(0, 0);
        super.onBackPressed();

    }

    public void ShowDialog(String Title, String msg) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ForgotPasswordActivity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(msg);
        dlgAlert.setTitle(Title);
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
        dlgAlert.setCancelable(false);
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (navigation) {


                            if (loggedInStr != null) {
                                finish();
                            } else {
                                Intent in = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                                in.putExtra("PlayerModel", playerModel);
                                in.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                                startActivity(in);
                                finish();
                            }
                            dialog.cancel();
                        } else {
                            dialog.cancel();
                        }
                    }
                });
        dlgAlert.create().show();
    }

    private void pangeaUIfun() {
        otp_send_ll = findViewById(R.id.otp_send_ll);
        password_reset_ll = findViewById(R.id.password_reset_ll);
        otp_ll = findViewById(R.id.otp_ll);

        mobile_tv = findViewById(R.id.mobile_tv);
        otp_tv = findViewById(R.id.otp_tv);
        psw_tv = findViewById(R.id.psw_tv);
        log_here = findViewById(R.id.log_here);
        new_psw_tv = findViewById(R.id.new_psw_tv);
        confirm_psw_tv = findViewById(R.id.confirm_psw_tv);
        mobile_otptimmer = findViewById(R.id.mobile_otptimmer);

        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), mobile_tv);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), otp_tv);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), psw_tv);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), log_here);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), new_psw_tv);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), confirm_psw_tv);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), mobile_otptimmer);

        mobile_tv.setText(languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER));
        psw_tv.setText(languagePreference.getTextofLanguage(TEXT_OTP, DEFAULT_TEXT_OTP));
        new_psw_tv.setText(languagePreference.getTextofLanguage(NEW_PASSWORD, DEFAULT_NEW_PASSWORD));
        confirm_psw_tv.setText(languagePreference.getTextofLanguage(CONFIRM_PASSWORD, DEFAULT_CONFIRM_PASSWORD));
        otp_expiry_duration = languagePreference.getTextofLanguage(OTP_EXPIRY_DURATION, DEFAULT_OTP_EXPIRY_DURATION);

        mobile_mtn = findViewById(R.id.mobile_mtn);
        enter_otp = findViewById(R.id.enter_otp);
        new_psw = findViewById(R.id.new_psw);
        confirm_psw = findViewById(R.id.confirm_psw);
        spiner_cc = findViewById(R.id.spiner_cc);

        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), mobile_mtn);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), enter_otp);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), new_psw);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.light_fonts), confirm_psw);

        mobile_mtn.setHint(languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER));
        enter_otp.setHint(languagePreference.getTextofLanguage(TEXT_OTP, DEFAULT_TEXT_OTP));
        new_psw.setHint(languagePreference.getTextofLanguage(NEW_PASSWORD, DEFAULT_NEW_PASSWORD));
        confirm_psw.setHint(languagePreference.getTextofLanguage(NEW_PASSWORD, DEFAULT_NEW_PASSWORD));

        send_otp = findViewById(R.id.send_otp);
        submit_otp = findViewById(R.id.submit_otp);
        change_psw = findViewById(R.id.change_psw);

        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.regular_fonts), send_otp);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.regular_fonts), submit_otp);
        FontUtls.loadFont(ForgotPasswordActivity.this, getResources().getString(R.string.regular_fonts), change_psw);

        send_otp.setText(languagePreference.getTextofLanguage(BTN_SEND_OTP, DEFAULT_BTN_SEND_OTP));
        submit_otp.setText(languagePreference.getTextofLanguage(BTN_SUBMIT, DEFAULT_BTN_SUBMIT));
        change_psw.setText(languagePreference.getTextofLanguage(BTN_SUBMIT, DEFAULT_BTN_SUBMIT));

        send_otp.setOnClickListener(v -> {

            mobile_otptimmer.setVisibility(View.VISIBLE);
            reverseTimer(Integer.parseInt(otp_expiry_duration), enter_otp, mobile_otptimmer);

            if (otp_ll.getVisibility() == View.GONE)
                otp_ll.setVisibility(View.VISIBLE);
        });
        submit_otp.setOnClickListener(v -> {
            otp_send_ll.setVisibility(View.GONE);
            password_reset_ll.setVisibility(View.VISIBLE);
        });
        change_psw.setOnClickListener(v -> {
            otp_send_ll.setVisibility(View.VISIBLE);
            password_reset_ll.setVisibility(View.GONE);
        });

        List<String> countryList = new ArrayList<>();
        countryList.add("+233");
        countryList.add("+27");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.billing_spinner, countryList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_cc.setAdapter(dataAdapter);

        spiner_cc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void reverseTimer(int Minute, final EditText etOtp, final TextView etTimmer) {

        //int inSeconds = Minute * 60;

        int inSeconds = 20;

        etOtp.setVisibility(View.VISIBLE);
        etTimmer.setVisibility(View.VISIBLE);
        etOtp.requestFocus();
        etTimmer.requestFocus();


        LinearLayout.LayoutParams paramotp = new LinearLayout.LayoutParams(0, WindowManager.LayoutParams.WRAP_CONTENT, 0.7f);
        LinearLayout.LayoutParams paramtimmer = new LinearLayout.LayoutParams(0, WindowManager.LayoutParams.WRAP_CONTENT, 0.3f);
        int data = convertDpToPx(13);
        paramtimmer.setMargins(25, data, 0, 0);
        paramotp.setMargins(10, data, 0, 0);
        etOtp.setLayoutParams(paramotp);
        etTimmer.setLayoutParams(paramtimmer);

         new CountDownTimer(inSeconds * 1000 + 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                etTimmer.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT, 1.0f);
                etTimmer.setVisibility(View.INVISIBLE);
                etOtp.setLayoutParams(param);
                etOtp.getText().clear();

                send_otp.setEnabled(true);
                send_otp.setAlpha(1.0f);
                send_otp.setText(languagePreference.getTextofLanguage(BTN_RE_SEND_OTP, DEFAULT_BTN_RE_SEND_OTP));
            }
        }.start();
    }

    private int convertDpToPx(int dp) {
        return Math.round(dp * (getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));

    }
}
