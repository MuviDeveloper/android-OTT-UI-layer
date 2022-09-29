package com.home.vod.ui.activity;

import static android.view.View.GONE;
import static com.home.vod.preferences.LanguagePreference.ALLOW_ADD_EMAIL;
import static com.home.vod.preferences.LanguagePreference.ALLOW_ADD_PHONE_NO;
import static com.home.vod.preferences.LanguagePreference.ALREADY_MEMBER;
import static com.home.vod.preferences.LanguagePreference.BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.BTN_RE_SEND_OTP;
import static com.home.vod.preferences.LanguagePreference.BTN_SEND_OTP;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.CHOOSE_USER_GROUP;
import static com.home.vod.preferences.LanguagePreference.CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ALLOW_ADD_EMAIL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ALLOW_ADD_PHONE_NO;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ALREADY_MEMBER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_REGISTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_RE_SEND_OTP;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_SEND_OTP;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CHOOSE_USER_GROUP;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ERROR_IN_REGISTRATION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_FAILURE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_FIRST_NAME;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_GOOGLE_FCM_TOKEN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_IS_OTP_ENABLE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_LOGIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MOBILE_COUNTRY_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NAME_HINT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_INVALID_EMAIL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_INVALID_MOBILE_COUNTRY_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_INVALID_OTP;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_INVALID_PHONE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OR;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OTP_EXPIRY_DURATION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PASSWORDS_DO_NOT_MATCH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PASSWORDS_LENGTH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_REGISTER_THROUGH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_EMIAL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_MOBILE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_OPTIONAL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_OTP;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VALID_CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VERIFY_EMAIL_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VERIFY_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.ERROR_IN_REGISTRATION;
import static com.home.vod.preferences.LanguagePreference.FAILURE;
import static com.home.vod.preferences.LanguagePreference.FIRST_NAME;
import static com.home.vod.preferences.LanguagePreference.GOOGLE_FCM_TOKEN;
import static com.home.vod.preferences.LanguagePreference.INVALID_MOBILE_COUNTRY_CODE;
import static com.home.vod.preferences.LanguagePreference.INVALID_NUMBER_STARTS_WITH_0;
import static com.home.vod.preferences.LanguagePreference.INVALID_OTP;
import static com.home.vod.preferences.LanguagePreference.IS_OTP_ENABLE;
import static com.home.vod.preferences.LanguagePreference.LOGIN;
import static com.home.vod.preferences.LanguagePreference.MOBILE_COUNTRY_CODE;
import static com.home.vod.preferences.LanguagePreference.NAME_HINT;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.NUMBER_STARTS_WITH_0;
import static com.home.vod.preferences.LanguagePreference.OOPS_INVALID_EMAIL;
import static com.home.vod.preferences.LanguagePreference.OOPS_INVALID_PHONE;
import static com.home.vod.preferences.LanguagePreference.OR;
import static com.home.vod.preferences.LanguagePreference.OTP_EXPIRY_DURATION;
import static com.home.vod.preferences.LanguagePreference.PASSWORDS_DO_NOT_MATCH;
import static com.home.vod.preferences.LanguagePreference.PASSWORDS_LENGTH;
import static com.home.vod.preferences.LanguagePreference.REGISTER_THROUGH;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SIGN_OUT_ERROR;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.TEXT_EMIAL;
import static com.home.vod.preferences.LanguagePreference.TEXT_MOBILE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.TEXT_OPTIONAL;
import static com.home.vod.preferences.LanguagePreference.TEXT_OTP;
import static com.home.vod.preferences.LanguagePreference.TEXT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.VALID_CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.VERIFY_EMAIL_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.VERIFY_MESSAGE;
import static com.home.vod.util.Constant.authTokenStr;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.home.apisdk.apiController.GenerateOTPAsync;
import com.home.apisdk.apiController.GetIpAddressAsynTask;
import com.home.apisdk.apiController.LogoutAsynctask;
import com.home.apisdk.apiController.RegistrationAsynTask;
import com.home.apisdk.apiController.VideoDetailsAsynctask;
import com.home.apisdk.apiModel.GenerateOTPInputModel;
import com.home.apisdk.apiModel.GenerateOTPOutputModel;
import com.home.apisdk.apiModel.GetUserGroupOutputModel;
import com.home.apisdk.apiModel.LogoutInput;
import com.home.apisdk.apiModel.Registration_input;
import com.home.apisdk.apiModel.Registration_output;
import com.home.apisdk.apiModel.Video_Details_Output;
import com.home.vod.R;
import com.home.vod.base.VodApplication;
import com.home.vod.handlers.RegisterUIHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import player.activity.AdPlayerActivity;
import player.activity.MyActivity;
import player.activity.Player;


public class RegisterActivity extends AppCompatActivity implements
        RegistrationAsynTask.RegistrationDetailsListener,
        VideoDetailsAsynctask.VideoDetailsListener,
        LogoutAsynctask.LogoutListener,
        GetIpAddressAsynTask.IpAddressListener,
        GenerateOTPAsync.GenerateOtpListener {

    public ProgressBarHandler progressBarHandler;

    int register_type;   // 1= Normal login, 2= Facebook Login, 3= Google Login, 4= thirdparty Login

    Registration_output REGISTRATION_OUTPUT;
    ProgressBarHandler pDialog;
    LanguagePreference languagePreference;
    PreferenceManager preferenceManager;
    FeatureHandler featureHandler;
    RegisterUIHandler registerUIHandler;
    Player playerModel;
    RegistrationAsynTask asyncReg;
    VideoDetailsAsynctask asynLoadVideoUrls;

    LinearLayout group_linear;
    ImageView cross;
    Spinner groupSpinner;
    RelativeLayout alreadymemberRelativeLayout;
    CountDownTimer waitTimer;
    private EditText editEmail, editTextCountryCode, editMobileNumber, editName, editPassword,
            editConfirmPassword, editName_first, editName_last, editemail_otp, editmobile_otp;
    private Button registerButton;
    private TextView alreadyMemmberText, loginTextView, or_text, email_otptimmer, mobile_otptimmer;

    String user_group_type_id = "";
    String UniversalErrorMessage = "";
    String deviceName = "";
    String register_through, is_otp_enable, allow_add_email, allow_add_phone_no, otp_expiry_duration, user_group_name;
    String emailotpStr, mobileotpStr;
    String ipAddressStr = "";
    boolean isRegisterButton = false;
    boolean isOTP = false;
    boolean isincorectOtp = false;
    boolean email_mobile_changed = false;
    boolean isReSend = false;
    String nameStr, regCCodeStr, regPhoneStr, regPasswordStr, regConfirmPasswordStr, otpStr;
    String regEmailStr = "";
    int corePoolSize = 60;
    int maximumPoolSize = 80;
    String isSubscribedStr = "";
    int keepAliveTime = 10;

    ArrayList<GetUserGroupOutputModel> groups = new ArrayList<GetUserGroupOutputModel>();
    ArrayList<String> groups_name = new ArrayList<>();
    ArrayList<String> user_group_type = new ArrayList<>();
    ArrayList<String> SubTitleName = new ArrayList<>();
    ArrayList<String> SubTitlePath = new ArrayList<>();
    ArrayList<String> FakeSubTitlePath = new ArrayList<>();
    ArrayList<String> ResolutionFormat = new ArrayList<>();
    ArrayList<String> ResolutionUrl = new ArrayList<>();
    ArrayList<String> SubTitleLanguage = new ArrayList<>();

    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);


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

        getWindow().setBackgroundDrawableResource(R.drawable.app_background);
        setContentView(R.layout.activity_register);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        pDialog = new ProgressBarHandler(RegisterActivity.this);
        languagePreference = LanguagePreference.getLanguagePreference(RegisterActivity.this);
        featureHandler = FeatureHandler.getFeaturePreference(RegisterActivity.this);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        alreadymemberRelativeLayout = findViewById(R.id.alreadymemberRelativeLayout);

        /**
         * Here we are checking app avaibility status.
         */
        checkGeoBlock();


        try {
            BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
            deviceName = myDevice.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * @description commented by Debashish for new UI change mantis-id: 19619
         */

        playerModel = (Player) getIntent().getSerializableExtra("PlayerModel");
        if (playerModel != null)
            playerModel.setIsstreaming_restricted(Util.getStreamingRestriction(languagePreference));


        cross = findViewById(R.id.dismiss);
        group_linear = findViewById(R.id.group_linear);
        String group_responses = preferenceManager.getGroupArrayFromPref();
        groups_name.add(languagePreference.getTextofLanguage(CHOOSE_USER_GROUP, DEFAULT_CHOOSE_USER_GROUP));
        user_group_type.add("");

        JSONObject mainJson = null;
        if (group_responses != null) {
            try {
                mainJson = new JSONObject(group_responses);

                JSONArray jsonArray = mainJson.getJSONArray("data");
                int lengthJsonArray = jsonArray.length();

                if ((lengthJsonArray > 1) && ((featureHandler.getFeatureFlag(FeatureHandler.GROUP_CATEGORY, FeatureHandler.DEFAULT_GROUP_CATEGORY))).equals("2")) {
                    group_linear.setVisibility(View.VISIBLE);
                }

                for (int i = 0; i < lengthJsonArray; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    GetUserGroupOutputModel groupOutputModel = new GetUserGroupOutputModel();

                    if ((jsonObject.has("group_name")) && jsonObject.optString("group_name").trim() != null && !jsonObject.optString("group_name").trim().isEmpty() && !jsonObject.optString("group_name").trim().equals("null") && !jsonObject.optString("group_name").trim().matches("")) {
                        groupOutputModel.setGroup_name(jsonObject.optString("group_name"));
                        groups_name.add(jsonObject.optString("group_name"));
                    }

                    if ((jsonObject.has("user_group_type")) && jsonObject.optString("user_group_type").trim() != null && !jsonObject.optString("user_group_type").trim().isEmpty() && !jsonObject.optString("user_group_type").trim().equals("null") && !jsonObject.optString("user_group_type").trim().matches("")) {
                        groupOutputModel.setUser_group_type(jsonObject.optString("user_group_type"));
                        user_group_type.add(jsonObject.optString("user_group_type"));

                        if (lengthJsonArray == 1) {
                            user_group_type_id = jsonObject.optString("user_group_type");
                        }

                    }

                    groups.add(groupOutputModel);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        groupSpinner = findViewById(R.id.groupSpinner);

        ArrayAdapter adapter = new ArrayAdapter(RegisterActivity.this, R.layout.register_dropdown, groups_name);
        groupSpinner.setAdapter(adapter);

        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Configuration configuration = getResources().getConfiguration();
                if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                    groupSpinner.setRotation(360);
                }

                user_group_type_id = user_group_type.get(position);
                user_group_name = groups_name.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        if (Util.isColorDark(getResources().getColor(R.color.appBackgroundColor))) {
            cross.setAlpha(0.5f);
        }

        if ((!featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN, FeatureHandler.DEFAULT_IS_LOGIN)) && (featureHandler.getFeatureStatus(FeatureHandler.IS_REGISTRATION_ENABLED, FeatureHandler.DEFAULT_IS_REGISTRATION_ENABLED))) {
            alreadymemberRelativeLayout.setVisibility(GONE);
        } else {
            alreadymemberRelativeLayout.setVisibility(View.VISIBLE);
        }

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        editName = findViewById(R.id.name);
        editEmail = findViewById(R.id.email);
        editMobileNumber = findViewById(R.id.mobile_number);
        editTextCountryCode = findViewById(R.id.mobile_country_code);
        editemail_otp = findViewById(R.id.email_otp);
        email_otptimmer = findViewById(R.id.email_otptimmer);
        editmobile_otp = findViewById(R.id.mobile_otp);
        mobile_otptimmer = findViewById(R.id.mobile_otptimmer);
        editPassword = findViewById(R.id.pwd);
        editConfirmPassword = findViewById(R.id.confirm_pass);
        registerButton = findViewById(R.id.register);
        alreadyMemmberText = findViewById(R.id.alreadyMemberText);
        loginTextView = findViewById(R.id.login);
        or_text = findViewById(R.id.or_text);

        register_through = languagePreference.getTextofLanguage(REGISTER_THROUGH, DEFAULT_REGISTER_THROUGH);
        is_otp_enable = languagePreference.getTextofLanguage(IS_OTP_ENABLE, DEFAULT_IS_OTP_ENABLE);
        allow_add_email = languagePreference.getTextofLanguage(ALLOW_ADD_EMAIL, DEFAULT_ALLOW_ADD_EMAIL);
        allow_add_phone_no = languagePreference.getTextofLanguage(ALLOW_ADD_PHONE_NO, DEFAULT_ALLOW_ADD_PHONE_NO);
        otp_expiry_duration = languagePreference.getTextofLanguage(OTP_EXPIRY_DURATION, DEFAULT_OTP_EXPIRY_DURATION);


        editEmail.setVisibility(GONE);
        editMobileNumber.setVisibility(GONE);
        editTextCountryCode.setVisibility(GONE);
        editemail_otp.setVisibility(GONE);
        editmobile_otp.setVisibility(GONE);
        email_otptimmer.setVisibility(GONE);
        mobile_otptimmer.setVisibility(GONE);
        registerButton.setEnabled(false);
        registerButton.setAlpha(0.4f);

        //Register_through - 1: through email, 2: through mobile no
        if (register_through.equals("1")) {
            editEmail.setVisibility(View.VISIBLE);
            editEmail.setHint(languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL));
            editMobileNumber.setVisibility(View.VISIBLE);
            editTextCountryCode.setVisibility(View.VISIBLE);
            editMobileNumber.setHint(languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER) + "(opt)");
            //Otp
            if (is_otp_enable.equals("1")) {
                editemail_otp.setVisibility(View.VISIBLE);
            } else {
                editemail_otp.setVisibility(GONE);
                registerButton.setEnabled(true);
                registerButton.setAlpha(1.0f);
            }

            //Mobile : 2:manadatory 1: optional, 0: not allowed from CMS
            if (allow_add_phone_no.equals("2")) {
                editMobileNumber.setVisibility(View.VISIBLE);
                editTextCountryCode.setVisibility(View.VISIBLE);
                editMobileNumber.setHint(languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER));
            } else if (allow_add_phone_no.equals("1")) {

                editMobileNumber.setHint(languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER) + " (" + languagePreference.getTextofLanguage(TEXT_OPTIONAL, DEFAULT_TEXT_OPTIONAL) + ")");
                editMobileNumber.setVisibility(View.VISIBLE);
                editTextCountryCode.setVisibility(View.VISIBLE);
            } else {
                editMobileNumber.setVisibility(GONE);
                editTextCountryCode.setVisibility(GONE);
            }

        } else {
            editMobileNumber.setVisibility(View.VISIBLE);
            editTextCountryCode.setVisibility(View.VISIBLE);
            editMobileNumber.setHint(languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER));
            //Otp
            if (is_otp_enable.equals("1")) {
                editmobile_otp.setVisibility(View.VISIBLE);
            } else {
                editmobile_otp.setVisibility(GONE);
                registerButton.setEnabled(true);
                registerButton.setAlpha(1.0f);
            }

            //Email : 2:manadatory 1: optional, 0: not allowed from CMS
            if (allow_add_email.equals("2")) {
                editEmail.setVisibility(View.VISIBLE);
                editEmail.setHint(languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL));
            } else if (allow_add_email.equals("1")) {
                editEmail.setHint(languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL) + " (" + languagePreference.getTextofLanguage(TEXT_OPTIONAL, DEFAULT_TEXT_OPTIONAL) + ")");
                editEmail.setVisibility(View.VISIBLE);
            } else {
                editEmail.setVisibility(GONE);
            }
        }

        editEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /**
                 * 1. Check if register with email enabled
                 * 2. Check if otp is enabled
                 * 3. check if email entered is valid
                 *
                 * Note: register_through = 1: email  2: phone
                 */

                boolean isEmailValid = Util.isValidMail(editEmail.getText().toString().trim());

                if (isEmailValid) {

                    if (register_through.equals("1") && is_otp_enable.equals("1")) {

                        registerButton.setText(languagePreference.getTextofLanguage(BTN_SEND_OTP, DEFAULT_BTN_SEND_OTP));
                        registerButton.setEnabled(true);
                        registerButton.setAlpha(1.0f);

                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1.0f);
                        email_otptimmer.setVisibility(GONE);
                        editemail_otp.setLayoutParams(param);
                        editemail_otp.getText().clear();

                        if (waitTimer != null) {
                            waitTimer.cancel();
                            waitTimer = null;
                        }
                        isRegisterButton = false;

                    }

                } else {
                    if (register_through.equals("1") && is_otp_enable.equals("1")) {
                        registerButton.setEnabled(false);
                        registerButton.setAlpha(0.4f);
                    }
                }


//                if (isOTP && register_through.equals("1")) {
//                    email_mobile_changed = true;
//                    registerButton.setText(languagePreference.getTextofLanguage(BTN_SEND_OTP, DEFAULT_BTN_SEND_OTP));
//                    registerButton.setEnabled(true);
//                    registerButton.setAlpha(1.0f);
//                    isRegisterButton = false;
//
//                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1.0f);
//                    email_otptimmer.setVisibility(GONE);
//                    editemail_otp.setLayoutParams(param);
//                    editemail_otp.getText().clear();
//
//                    if (waitTimer != null) {
//                        waitTimer.cancel();
//                        waitTimer = null;
//                    }
//                }
//
//                if (s.length() != 0 && register_through.equals("2")) {
//                    if (!isEmailValid) {
//                        editEmail.requestFocus();
//                    }
//                }
//                if (s.length() != 0 && register_through.equals("1")) {
//                    if (isEmailValid) {
//                        registerButton.setEnabled(true);
//                        registerButton.setAlpha(1.0f);
//                        return;
//                    }
//
//                } else if (s.length() == 0 && !isOTP && register_through.equals("1")) {
//                    if (is_otp_enable.equals("1")) {
//                        registerButton.setEnabled(false);
//                        registerButton.setAlpha(0.4f);
//                    }
//                }
//
//
            }

        });

        editTextCountryCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && s.length() == 1 && s.toString().equals("0")) {
                    editTextCountryCode.getText().clear();
                }

                if (editTextCountryCode.getText().length() == 0) {
                    if (register_through.equals("2") && is_otp_enable.equals("1")) {
                        registerButton.setEnabled(false);
                        registerButton.setAlpha(0.4f);
                    }
                    return;
                }

                if (s.length() > 0 && editMobileNumber.getText().length() > 0) {

                    if (register_through.equals("2") && is_otp_enable.equals("1")) {
                        registerButton.setText(languagePreference.getTextofLanguage(BTN_SEND_OTP, DEFAULT_BTN_SEND_OTP));
                        registerButton.setEnabled(true);
                        registerButton.setAlpha(1.0f);
                        isRegisterButton = false;

                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1.0f);
                        mobile_otptimmer.setVisibility(GONE);
                        editmobile_otp.setLayoutParams(param);
                        editmobile_otp.getText().clear();

                        if (waitTimer != null) {
                            waitTimer.cancel();
                            waitTimer = null;
                        }
                    }

                }
            }
            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


        editMobileNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /**
                 * @description: The code is used to mobile number validation
                 */

                if (s.length() > 6 && editTextCountryCode.getText().length() > 0) {

                    if (register_through.equals("2") && is_otp_enable.equals("1")) {
                        registerButton.setText(languagePreference.getTextofLanguage(BTN_SEND_OTP, DEFAULT_BTN_SEND_OTP));
                        registerButton.setEnabled(true);
                        registerButton.setAlpha(1.0f);
                        isRegisterButton = false;

                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1.0f);
                        mobile_otptimmer.setVisibility(GONE);
                        editmobile_otp.setLayoutParams(param);
                        editmobile_otp.getText().clear();

                        if (waitTimer != null) {
                            waitTimer.cancel();
                            waitTimer = null;
                        }

                        if (s.length() > 0 && s.charAt(0) == '0') {
                            editMobileNumber.setError(languagePreference.getTextofLanguage(NUMBER_STARTS_WITH_0, INVALID_NUMBER_STARTS_WITH_0));
                            editMobileNumber.requestFocus();
                            registerButton.setEnabled(false);
                            registerButton.setAlpha(0.4f);
                            isRegisterButton = false;

                        } else {
                            editMobileNumber.setError(null);

                        }
                    }

                } else {
                    if (register_through.equals("2") && is_otp_enable.equals("1")) {
                        registerButton.setEnabled(false);
                        registerButton.setAlpha(0.4f);
                    }
                }


//                if (isOTP && register_through.equals("2")) {
//                    email_mobile_changed = true;
//                    registerButton.setText(languagePreference.getTextofLanguage(BTN_SEND_OTP, DEFAULT_BTN_SEND_OTP));
//                    registerButton.setEnabled(true);
//                    registerButton.setAlpha(1.0f);
//                    isRegisterButton = false;
//
//                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 1.0f);
//                    mobile_otptimmer.setVisibility(GONE);
//                    editmobile_otp.setLayoutParams(param);
//                    editmobile_otp.getText().clear();
//
//                    if (waitTimer != null) {
//                        waitTimer.cancel();
//                        waitTimer = null;
//                    }
//                }
//                if (s.length() != 0 && register_through.equals("2")) {
//                    if (s.charAt(0) == '0') {
//                        editMobileNumber.setError(languagePreference.getTextofLanguage(NUMBER_STARTS_WITH_0, INVALID_NUMBER_STARTS_WITH_0));
//                        editMobileNumber.requestFocus();
//                        registerButton.setEnabled(false);
//                        registerButton.setAlpha(0.4f);
//                    } else {
//                        registerButton.setText(languagePreference.getTextofLanguage(BTN_SEND_OTP, DEFAULT_BTN_SEND_OTP));
//                        registerButton.setEnabled(true);
//                        registerButton.setAlpha(1.0f);
//                    }
//                } else if (s.length() == 0 && !isOTP && register_through.equals("2")) {
//                    if (is_otp_enable.equals("1")) {
//                        registerButton.setEnabled(false);
//                        registerButton.setAlpha(0.4f);
//                    }
//                }
//
//                if (s.length() != 0 && register_through.equals("1")) {
//                    if (s.charAt(0) == '0') {
//                        editMobileNumber.setError(languagePreference.getTextofLanguage(NUMBER_STARTS_WITH_0, INVALID_NUMBER_STARTS_WITH_0));
//                        editMobileNumber.requestFocus();
//                        registerButton.setEnabled(true);
//                        registerButton.setAlpha(1.0f);
//                        Log.d("Sanjay", "false");
//                    }
//                } else if (s.length() == 0 && !isOTP && register_through.equals("1")) {
//                    if (is_otp_enable.equals("1")) {
//                        registerButton.setEnabled(false);
//                        registerButton.setAlpha(0.4f);
//                    }
//                }


            }
        });

        editemail_otp.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && s.length() >= 4 && isOTP && !isReSend) {
                    registerButton.setEnabled(true);
                    registerButton.setAlpha(1.0f);
                    isRegisterButton = true;
                    isincorectOtp = false;
                } else {
                    if (!isincorectOtp && !email_mobile_changed && isOTP && !isReSend) {
                        registerButton.setEnabled(false);
                        registerButton.setAlpha(0.4f);
                        isRegisterButton = false;
                    }
                }
            }
        });

        editmobile_otp.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && s.length() >= 4 && isOTP && !isReSend) {
                    registerButton.setEnabled(true);
                    registerButton.setAlpha(1.0f);
                    isRegisterButton = true;
                    isincorectOtp = false;
                } else {
                    if (!isincorectOtp && !email_mobile_changed && isOTP && !isReSend) {
                        registerButton.setEnabled(false);
                        registerButton.setAlpha(0.4f);
                        isRegisterButton = false;
                    }
                }
            }
        });


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

        editEmail.setFilters(new InputFilter[]{filter});
        editConfirmPassword.setFilters(new InputFilter[]{filter});
        editPassword.setFilters(new InputFilter[]{filter});

        FontUtls.loadFont(RegisterActivity.this, getResources().getString(R.string.light_fonts), editEmail);
        FontUtls.loadFont(RegisterActivity.this, getResources().getString(R.string.light_fonts), editMobileNumber);
        FontUtls.loadFont(RegisterActivity.this, getResources().getString(R.string.light_fonts), editTextCountryCode);
        FontUtls.loadFont(RegisterActivity.this, getResources().getString(R.string.light_fonts), editemail_otp);
        FontUtls.loadFont(RegisterActivity.this, getResources().getString(R.string.light_fonts), editmobile_otp);
        FontUtls.loadFont(RegisterActivity.this, getResources().getString(R.string.light_fonts), editPassword);
        FontUtls.loadFont(RegisterActivity.this, getResources().getString(R.string.light_fonts), editConfirmPassword);
        FontUtls.loadFont(RegisterActivity.this, getResources().getString(R.string.light_fonts), registerButton);
        FontUtls.loadFont(RegisterActivity.this, getResources().getString(R.string.light_fonts), alreadyMemmberText);
        FontUtls.loadFont(RegisterActivity.this, getResources().getString(R.string.light_fonts), loginTextView);


        or_text.setText(languagePreference.getTextofLanguage(OR, DEFAULT_OR));

        editName.setHint(languagePreference.getTextofLanguage(NAME_HINT, DEFAULT_NAME_HINT));
        editTextCountryCode.setHint("1");
        editemail_otp.setHint(languagePreference.getTextofLanguage(TEXT_OTP, DEFAULT_TEXT_OTP));
        editmobile_otp.setHint(languagePreference.getTextofLanguage(TEXT_OTP, DEFAULT_TEXT_OTP));
        editPassword.setHint(languagePreference.getTextofLanguage(TEXT_PASSWORD, DEFAULT_TEXT_PASSWORD));
        editConfirmPassword.setHint(languagePreference.getTextofLanguage(CONFIRM_PASSWORD, DEFAULT_CONFIRM_PASSWORD));
        alreadyMemmberText.setText(languagePreference.getTextofLanguage(ALREADY_MEMBER, DEFAULT_ALREADY_MEMBER));
        loginTextView.setText(languagePreference.getTextofLanguage(LOGIN, DEFAULT_LOGIN));

        //Otp
        if (is_otp_enable.equals("1")) {
            registerButton.setText(languagePreference.getTextofLanguage(BTN_SEND_OTP, DEFAULT_BTN_SEND_OTP));
            isRegisterButton = false;
        } else {
            registerButton.setText(languagePreference.getTextofLanguage(BTN_REGISTER, DEFAULT_BTN_REGISTER));
            isRegisterButton = true;
        }




        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {
                    final Intent detailsIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    if (getIntent().getStringExtra("from") != null) {
                        detailsIntent.putExtra("from", getIntent().getStringExtra("from"));
                    }
                    if (getIntent().getStringExtra("is_a_guest_user") != null) {
                        detailsIntent.putExtra("is_a_guest_user", getIntent().getStringExtra("is_a_guest_user"));
                    }


                    if (Util.check_for_subscription == 1) {
                        Util.check_for_subscription = 1;
                        detailsIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                    }

                    detailsIntent.putExtra("PlayerModel", playerModel);
                    detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(detailsIntent);
                    finish();
                    overridePendingTransition(0, 0);
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.hideKeyboard(RegisterActivity.this);
                registerUIHandler.getRegisterName();
            }
        });


        registerUIHandler = new RegisterUIHandler(this);
        registerUIHandler.setCountryList(preferenceManager);
        registerUIHandler.setTermsTextView(languagePreference);
    }


    @Override
    public void onRegistrationDetailsPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing()) {
            pDialog.show();
        }
    }

    @Override
    public void onRegistrationDetailsPostExecuteCompleted(Registration_output registration_output, int status, String message) {

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }
        } catch (IllegalArgumentException ex) {
            status = 0;

        }

        if (status == 0) {

            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegisterActivity.this, R.style.MyAlertDialogStyle);
            dlgAlert.setMessage(languagePreference.getTextofLanguage(ERROR_IN_REGISTRATION, DEFAULT_ERROR_IN_REGISTRATION));
            dlgAlert.setTitle(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE));
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
        if (status > 0) {
            if (status == 202) {

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegisterActivity.this, R.style.MyAlertDialogStyle);
                dlgAlert.setTitle(languagePreference.getTextofLanguage(VERIFY_MESSAGE, DEFAULT_VERIFY_MESSAGE));
                dlgAlert.setMessage(languagePreference.getTextofLanguage(VERIFY_EMAIL_MESSAGE, DEFAULT_VERIFY_EMAIL_MESSAGE));
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                registerUIHandler.resetallfield();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                dlgAlert.create().show();

            } else if (status == 200) {

                register_type = 1;
                REGISTRATION_OUTPUT = registration_output;

                regEmailStr = registration_output.getEmail();
                registerPostExecute(REGISTRATION_OUTPUT);

            } else {

                /**
                 * Following code is responsible  for clear otp field in incorrect otp .
                 */
                if (status == 406) {
                    isincorectOtp = true;
                    registerButton.setEnabled(false);
                    registerButton.setAlpha(0.4f);
                    if (register_through.equals("1")) {
                        editemail_otp.setText("");
                    } else if (register_through.equals("2")) {
                        editmobile_otp.setText("");
                    }
                }

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegisterActivity.this, R.style.MyAlertDialogStyle);
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

            }
        }
    }

    @Override
    public void onVideoDetailsPreExecuteStarted() {
        SubTitleName.clear();
        SubTitlePath.clear();
        ResolutionUrl.clear();
        ResolutionFormat.clear();
        if (pDialog != null && !pDialog.isShowing()) {
            pDialog.show();
        }
    }

    @Override
    public void onVideoDetailsPostExecuteCompleted(Video_Details_Output _video_details_output, int statusCode, String stus, String message) {

     /*check if status code 200 then set the video url before this it check it is thirdparty url or normal if third party
        then set thirdpartyurl true here and assign the url to videourl*/
        boolean play_video = true;
        if (featureHandler.getFeatureStatus(FeatureHandler.IS_STREAMING_RESTRICTION, FeatureHandler.DEFAULT_IS_STREAMING_RESTRICTION) && preferenceManager.getUseridFromPref() != null) {
            play_video = !_video_details_output.getStreaming_restriction().trim().equals("0");
        } else {
            play_video = true;
        }
        if (!play_video) {

            try {
                if (pDialog.isShowing())
                    pDialog.hide();
            } catch (IllegalArgumentException ex) {
            }

            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegisterActivity.this, R.style.MyAlertDialogStyle);
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


        if (statusCode == 200) {
            if (_video_details_output.getThirdparty_url() == null || _video_details_output.getThirdparty_url().matches("")) {
                playerModel.setIsOffline(_video_details_output.getIs_offline());
                playerModel.setSkipIntro(_video_details_output.getSkipIntroStartPosition(), _video_details_output.getSkipIntroEndPosition());
                playerModel.setDownloadStatus(_video_details_output.getDownload_status());

                /**
                 * for drm player below condition added
                 * if studio_approved_url is there in api then set the videourl from this other wise goto 2nd one
                 */

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
                        //  Util.dataModel.setVideoUrl(translatedLanuage.getNoData());
                        playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));

                    }
                }
            } else {
                if (_video_details_output.getThirdparty_url() != null || !_video_details_output.getThirdparty_url().matches("")) {
                    playerModel.setVideoUrl(_video_details_output.getThirdparty_url());
                    playerModel.setThirdPartyPlayer(true);

                } else {
                    //  Util.dataModel.setVideoUrl(translatedLanuage.getNoData());
                    playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));

                }
            }

            playerModel.setNo_of_row(_video_details_output.getNo_of_row());
            playerModel.setNo_of_column(_video_details_output.getNo_of_column());
            playerModel.setThumb_interval(_video_details_output.getThumb_interval());
            playerModel.setVtt_with_sprite(_video_details_output.getVtt_with_sprite());
            playerModel.setVtt_without_sprite(_video_details_output.getVtt_without_sprite());
            playerModel.setMultiple_sprite_HostPrefix(_video_details_output.getMultiple_sprite_HostPrefix());
            playerModel.setSprite_image(_video_details_output.getSprite_image());

            Util.dataModel.setVideoResolution(_video_details_output.getVideoResolution());

            playerModel.setVideoResolution(_video_details_output.getVideoResolution());
            if (_video_details_output.getPlayed_length() != null && !_video_details_output.getPlayed_length().equals(""))
                playerModel.setPlayPos((Util.isDouble(_video_details_output.getPlayed_length())));

            SubTitleName = _video_details_output.getSubTitleName();
            SubTitleLanguage = _video_details_output.getSubTitleLanguage();

            //dependency for datamodel
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
            FakeSubTitlePath = _video_details_output.getFakeSubTitlePath();
            playerModel.setPlayPos(Util.isDouble(_video_details_output.getPlayed_length()));
            playerModel.setSubTitleLanguage(_video_details_output.getSubTitleLanguage());


            if (playerModel.getVideoUrl() == null ||
                    playerModel.getVideoUrl().matches("")) {
                try {
                    if (pDialog != null && pDialog.isShowing()) {
                        pDialog.hide();
                    }
                } catch (IllegalArgumentException ex) {
                    playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
                }
                Util.showNoDataAlert(RegisterActivity.this);

            } else {
                try {
                    if (pDialog != null && pDialog.isShowing()) {
                        pDialog.hide();
                    }
                } catch (IllegalArgumentException ex) {
                    playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
                }


                // condition for checking if the response has third party url or not.
                if (_video_details_output.getThirdparty_url() == null ||
                        _video_details_output.getThirdparty_url().matches("")
                ) {

                    {
                        playerModel.setThirdPartyPlayer(false);

                        final Intent playVideoIntent;
                        if (Util.dataModel.getAdNetworkId() == 3) {

                            playVideoIntent = new Intent(RegisterActivity.this, MyActivity.class);

                        } else if (Util.dataModel.getAdNetworkId() == 1 && Util.dataModel.getPreRoll() == 1) {
                            if (Util.dataModel.getPlayPos() <= 0) {
                                playVideoIntent = new Intent(RegisterActivity.this, AdPlayerActivity.class);
                            } else {
                                playVideoIntent = Util.getPlayerIntent(RegisterActivity.this);
                            }
                        } else {
                            playVideoIntent = Util.getPlayerIntent(RegisterActivity.this);

                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                if (FakeSubTitlePath.size() > 0) {
                                    // This Portion Will Be changed Later.

                                    File dir = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getApplicationContext().getPackageName().trim() + "/SubTitleList/");
                                    if (dir.isDirectory()) {
                                        String[] children = dir.list();
                                        for (int i = 0; i < children.length; i++) {
                                            new File(dir, children[i]).delete();
                                        }
                                    }

                                    progressBarHandler = new ProgressBarHandler(RegisterActivity.this);
                                    progressBarHandler.show();
                                } else {
                                    playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                                    playVideoIntent.putExtra("PlayerModel", playerModel);
                                    startActivity(playVideoIntent);
                                    if (LoginActivity.loginA != null) {
                                        LoginActivity.loginA.finish();
                                    }
                                    finish();
                                }

                            }
                        });
                    }
                } else {
                    final Intent playVideoIntent = Util.getPlayerIntent(RegisterActivity.this);
                    playVideoIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                    playVideoIntent.putExtra("PlayerModel", playerModel);
                    startActivity(playVideoIntent);
                    if (LoginActivity.loginA != null) {
                        LoginActivity.loginA.finish();
                    }

                }
            }

        } else {

            playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
            try {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.hide();
                }
            } catch (IllegalArgumentException ex) {
                playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
            }
            playerModel.setVideoUrl(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));

            Util.showNoDataAlert(RegisterActivity.this);
        }


    }


    @Override
    public void onBackPressed() {


        if (asynLoadVideoUrls != null) {
            asynLoadVideoUrls.cancel(true);
        }
        if (asyncReg != null) {
            asyncReg.cancel(true);
        }
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        // Calling VodApplication class (see application tag in AndroidManifest.xml)
        final VodApplication globalVariable = (VodApplication) getApplicationContext();

        // Get the value of
        final String ifClickedBanner = globalVariable.getCLICKED_TO_BANNER_LINK();

        if (ifClickedBanner.equals("1") || ((!featureHandler.getFeatureStatus(FeatureHandler.IS_LOGIN, FeatureHandler.DEFAULT_IS_LOGIN)) && (featureHandler.getFeatureStatus(FeatureHandler.IS_REGISTRATION_ENABLED, FeatureHandler.DEFAULT_IS_REGISTRATION_ENABLED)))) {
            globalVariable.setCLICKED_TO_BANNER_LINK("0");
            finish();
        } else {
            /**
             * @description: check if payment flow is 1step start then show warning message to user.
             */
            RegisterActivity.super.onBackPressed();
            overridePendingTransition(0, 0);
            final Intent detailsIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            if (getIntent().getStringExtra("is_a_guest_user") != null)
                detailsIntent.putExtra("is_a_guest_user", getIntent().getStringExtra("is_a_guest_user"));
            startActivity(detailsIntent);
        }
    }

    public void removeFocusFromViews() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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


    public void LogOut() {

        LogoutInput logoutInput = new LogoutInput();
        logoutInput.setAuthToken(authTokenStr);
        logoutInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        logoutInput.setLogin_history_id(preferenceManager.getLoginHistIdFromPref());
        logoutInput.setCountryCode(preferenceManager.getCountryCodeFromPref());
        LogoutAsynctask asynLogoutDetails = new LogoutAsynctask(logoutInput, this, this, featureHandler.getFeatureStatus(FeatureHandler.IS_CACHING_ENABLED, FeatureHandler.DEFAULT_IS_CACHING_ENABLED), new DataController(this));
        asynLogoutDetails.execute(); //threadPoolExecutor
    }


    @Override
    public void onIPAddressPreExecuteStarted() {

    }

    @Override
    public void onIPAddressPostExecuteCompleted(String message, int statusCode, String ipAddressStr) {
        this.ipAddressStr = ipAddressStr;
        return;
    }


    @Override
    public void onGenerateOtpPreExecuteStarted() {
        if (pDialog != null && !pDialog.isShowing()) {
            pDialog.show();
        }
    }

    @Override
    public void onGenerateOTPPostExecuteCompleted(GenerateOTPOutputModel generateOTPOutputModel, String status, String message, String response, int code, String otp) {
        Log.v("Otp", response + "");
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
            }
        } catch (IllegalArgumentException ex) {

        }

        Toast.makeText(RegisterActivity.this, message+"", Toast.LENGTH_LONG).show();

        if (code == 200) {
            registerButton.setText(languagePreference.getTextofLanguage(BTN_REGISTER, DEFAULT_BTN_REGISTER));
            registerButton.setEnabled(false);
            registerButton.setAlpha(0.4f);
            isRegisterButton = true;
            isOTP = true;
            isReSend = false;
            email_mobile_changed = false;

            //Clear otp fields
            editemail_otp.getText().clear();
            editmobile_otp.getText().clear();

            if (register_through.equals("1") && otp_expiry_duration.trim() != null && !otp_expiry_duration.trim().isEmpty() && !otp_expiry_duration.trim().equals("null") && !otp_expiry_duration.trim().matches("")) {
                email_otptimmer.setVisibility(View.VISIBLE);
                reverseTimer(Integer.parseInt(otp_expiry_duration), editemail_otp, email_otptimmer);
            } else if (register_through.equals("2") && otp_expiry_duration.trim() != null && !otp_expiry_duration.trim().isEmpty() && !otp_expiry_duration.trim().equals("null") && !otp_expiry_duration.trim().matches("")) {
                mobile_otptimmer.setVisibility(View.VISIBLE);
                reverseTimer(Integer.parseInt(otp_expiry_duration), editmobile_otp, mobile_otptimmer);
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
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }

        if (status == null) {
            Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code == 0) {
            Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

        }
        if (code > 0) {
            if (code == 200) {
                try {
                    preferenceManager.clearLoginPref();

                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegisterActivity.this, R.style.MyAlertDialogStyle);
                    dlgAlert.setMessage(UniversalErrorMessage);
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(SIGN_OUT_ERROR, DEFAULT_SIGN_OUT_ERROR), Toast.LENGTH_LONG).show();

            }
        }
    }


    public void setResultAtFinishActivity() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


    /**
     * Following method is responsible to navigte to homepge
     */
    public void navigateToHomepage() {

        try {
            Util.image_orentiation.clear();

            Intent in = new Intent(RegisterActivity.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            if (LoginActivity.loginA != null) {
                LoginActivity.loginA.finish();
            }
            finish();

        } catch (Exception e) {
            e.toString();
        }


    }


    /**
     * Following method is responsible to check whether the app is available for the country or not.
     */
    public void checkGeoBlock() {

        try {
            if (Util.geoBloackSuccessStatus == 2) {
                Intent intent = new Intent(RegisterActivity.this, GeoblockAlertActivity.class);
                startActivity(intent);
                finish();

            }
        } catch (Exception e) {
            e.toString();
        }
    }


    /**
     * Following methods are added to handle biometric flow.
     */

    public void registerPostExecute(Registration_output registration_output) {

        isSubscribedStr = registration_output.getIsSubscribed();
        preferenceManager.setLogInStatusToPref("1");
        preferenceManager.setIsSubscribedToPref(registration_output.getIsSubscribed());
        preferenceManager.setUserIdToPref(registration_output.getId());
        preferenceManager.setPwdToPref(editPassword.getText().toString().trim());
        preferenceManager.setEmailIdToPref(registration_output.getEmail());
        preferenceManager.setDispPhoneToPref(registration_output.getMobile());
        preferenceManager.setDispNameToPref(registration_output.getDisplay_name());
        preferenceManager.setLoginProfImgoPref(registration_output.getProfile_image());
        preferenceManager.setIsSubscribedToPref(isSubscribedStr);
        preferenceManager.setLoginHistIdPref(registration_output.getLogin_history_id());
        preferenceManager.setSocialLoginPref("0");
        preferenceManager.setGroupTypePref(registration_output.getGroup_type());

        try {
            SharedPreferences sharedprefPlanList = getSharedPreferences("myPlanListPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorPlanList = sharedprefPlanList.edit();
            editorPlanList.putString("isSubscribed", isSubscribedStr);
            editorPlanList.putString("isFree", "0");
            editorPlanList.apply();
            editorPlanList.commit();
        } catch (Exception e) {
            e.toString();
        }

        if (preferenceManager != null && preferenceManager.getGuestUseridFromPref() != null) {
            preferenceManager.setGuestUserIdToPref(null);
        }


        Date todayDate = new Date();
        String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(todayDate);
        preferenceManager.setLoginDatePref(todayStr);

        if (Util.review_instance_available) {
            navigateToHomepage();
            return;
        }

        if (Util.favorite_clicked) {
            navigateToHomepage();
            return;
        }
        if (Util.followed_clicked) {
            setResultAtFinishActivity();
            return;
        }

        if (getIntent().getStringExtra("from") != null) {
            removeFocusFromViewsAndFinish();
        } else {
            navigateToHomepage();
        }
    }

    public void reverseTimer(int Minute, final EditText etOtp, final TextView etTimmer) {

        int inSeconds = Minute * 60;
        etOtp.setVisibility(View.VISIBLE);
        etTimmer.setVisibility(View.VISIBLE);
        etOtp.requestFocus();
        etTimmer.requestFocus();


        LinearLayout.LayoutParams paramotp = new LinearLayout.LayoutParams(0, WindowManager.LayoutParams.WRAP_CONTENT, 0.7f);
        LinearLayout.LayoutParams paramtimmer = new LinearLayout.LayoutParams(0, WindowManager.LayoutParams.WRAP_CONTENT, 0.3f);
        int data = convertDpToPx(13);
        paramtimmer.setMargins(-25, data, 0, 0);
        paramotp.setMargins(0, data, 0, 0);
        etOtp.setLayoutParams(paramotp);
        etTimmer.setLayoutParams(paramtimmer);

        waitTimer = new CountDownTimer(inSeconds * 1000 + 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                etTimmer.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT, 1.0f);
                etTimmer.setVisibility(GONE);
                etOtp.setLayoutParams(param);
                etOtp.getText().clear();

                registerButton.setEnabled(true);
                registerButton.setAlpha(1.0f);
                registerButton.setText(languagePreference.getTextofLanguage(BTN_RE_SEND_OTP, DEFAULT_BTN_RE_SEND_OTP));
                isRegisterButton = false;
                isReSend = true;
            }
        }.start();
    }

    private int convertDpToPx(int dp) {
        return Math.round(dp * (getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));

    }

    public void otpApiCall() {

        if (register_through.equals("1")) {
            if (regEmailStr.trim().equals("")) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL), Toast.LENGTH_LONG).show();
                return;
            }
            boolean isValidEmail = Util.isValidMail(regEmailStr);
            if (!isValidEmail) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_EMAIL, DEFAULT_OOPS_INVALID_EMAIL), Toast.LENGTH_LONG).show();
                return;
            }
        } else {

            if (regCCodeStr.trim().equals("")) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(MOBILE_COUNTRY_CODE, DEFAULT_MOBILE_COUNTRY_CODE), Toast.LENGTH_LONG).show();
                return;
            }

            if (!Util.isValidMobileCountryCode(regCCodeStr)) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(INVALID_MOBILE_COUNTRY_CODE, DEFAULT_OOPS_INVALID_MOBILE_COUNTRY_CODE), Toast.LENGTH_LONG).show();
                return;
            }

            if (regPhoneStr.trim().equals("")) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER), Toast.LENGTH_LONG).show();
                return;
            }

            boolean isValidMobile = Util.isValidMobile(regPhoneStr);
            if (!isValidMobile) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_PHONE, DEFAULT_OOPS_INVALID_PHONE_NUMBER), Toast.LENGTH_LONG).show();
                return;
            }
        }

        GenerateOTPInputModel generateOTPInputModel = new GenerateOTPInputModel();
        generateOTPInputModel.setAuthToken(authTokenStr);
        if (register_through.equals("1")) {
            generateOTPInputModel.setEmail(regEmailStr);
        } else {
            generateOTPInputModel.setMobile_country_code(regCCodeStr);
            generateOTPInputModel.setMobile_number(regPhoneStr);
        }
        generateOTPInputModel.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        GenerateOTPAsync generateOTPAsync = new GenerateOTPAsync(generateOTPInputModel, this, this);
        generateOTPAsync.executeOnExecutor(threadPoolExecutor);
    }

    public void registerButtonClicked(String first_name, String last_name, String phone) {

        nameStr = editName.getText().toString().trim();
        regEmailStr = editEmail.getText().toString().trim();
        regPhoneStr = editMobileNumber.getText().toString().trim();
        regCCodeStr = editTextCountryCode.getText().toString().trim();
        regPasswordStr = editPassword.getText().toString();
        regConfirmPasswordStr = editConfirmPassword.getText().toString();
        emailotpStr = editemail_otp.getText().toString();
        mobileotpStr = editmobile_otp.getText().toString();


        if (NetworkStatus.getInstance().isConnected(RegisterActivity.this)) {

            if (!isRegisterButton) {
                otpApiCall();
                return;
            }

            if (nameStr.trim().equals("")) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(FIRST_NAME, DEFAULT_FIRST_NAME), Toast.LENGTH_LONG).show();
                return;
            }

            //Through Email
            if (register_through.equals("1")) {
                if (regEmailStr.trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL), Toast.LENGTH_LONG).show();
                    return;
                }
                boolean isValidEmail = Util.isValidMail(regEmailStr);
                if (!isValidEmail) {
                    Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_EMAIL, DEFAULT_OOPS_INVALID_EMAIL), Toast.LENGTH_LONG).show();
                    return;
                }

                if (allow_add_phone_no.equals("2")) {

                    if (regCCodeStr.trim().equals("")) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(MOBILE_COUNTRY_CODE, DEFAULT_MOBILE_COUNTRY_CODE), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (!Util.isValidMobileCountryCode(regCCodeStr)) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(INVALID_MOBILE_COUNTRY_CODE, DEFAULT_OOPS_INVALID_MOBILE_COUNTRY_CODE), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (regPhoneStr.trim().equals("")) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER), Toast.LENGTH_LONG).show();
                        return;
                    }

                    boolean isValidMobile = Util.isValidMobile(regPhoneStr);
                    if (!isValidMobile) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_PHONE, DEFAULT_OOPS_INVALID_PHONE_NUMBER), Toast.LENGTH_LONG).show();
                        return;
                    }
                } else if (allow_add_phone_no.equals("1")) {
                    if (regCCodeStr.trim().equals("") && regPhoneStr.trim().equals("")) {

                    } else if (!regCCodeStr.trim().equals("") && regPhoneStr.trim().equals("")) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER), Toast.LENGTH_LONG).show();
                        return;
                    } else if (regCCodeStr.trim().equals("") && !regPhoneStr.trim().equals("")) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(MOBILE_COUNTRY_CODE, DEFAULT_MOBILE_COUNTRY_CODE), Toast.LENGTH_LONG).show();
                        return;
                    } else if (!regPhoneStr.trim().equals("")) {
                        boolean isValidMobile = Util.isValidMobile(regPhoneStr);
                        if (!isValidMobile) {
                            Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_PHONE, DEFAULT_OOPS_INVALID_PHONE_NUMBER), Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }

                if (is_otp_enable.equals("1")) {
                    if (emailotpStr.trim().equals("")) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(TEXT_OTP, DEFAULT_TEXT_OTP), Toast.LENGTH_LONG).show();
                        return;
                    } else if (!Util.isValidOTP(emailotpStr)) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(INVALID_OTP, DEFAULT_OOPS_INVALID_OTP), Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        otpStr = emailotpStr;
                    }
                }

            } else {

                if (regCCodeStr.trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(MOBILE_COUNTRY_CODE, DEFAULT_MOBILE_COUNTRY_CODE), Toast.LENGTH_LONG).show();
                    return;
                }

                if (!Util.isValidMobileCountryCode(regCCodeStr)) {
                    Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(INVALID_MOBILE_COUNTRY_CODE, DEFAULT_OOPS_INVALID_MOBILE_COUNTRY_CODE), Toast.LENGTH_LONG).show();
                    return;
                }

                if (regPhoneStr.trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER), Toast.LENGTH_LONG).show();
                    return;
                }

                if (regPhoneStr.length() > 0 && regPhoneStr.charAt(0) == '0') {
                    Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(NUMBER_STARTS_WITH_0, INVALID_NUMBER_STARTS_WITH_0), Toast.LENGTH_LONG).show();
                    return;
                }
                boolean isValidMobile = Util.isValidMobile(regPhoneStr);
                if (!isValidMobile) {
                    Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_PHONE, DEFAULT_OOPS_INVALID_PHONE_NUMBER), Toast.LENGTH_LONG).show();
                    return;
                }

                if (allow_add_email.equals("2")) {
                    if (regEmailStr.trim().equals("")) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL), Toast.LENGTH_LONG).show();
                        return;
                    }
                    boolean isValidEmail = Util.isValidMail(regEmailStr);
                    if (!isValidEmail) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_EMAIL, DEFAULT_OOPS_INVALID_EMAIL), Toast.LENGTH_LONG).show();
                        return;
                    }
                } else if (allow_add_email.equals("1")) {
                    if (regEmailStr.trim().equals("")) {

                    } else if (!regEmailStr.trim().equals("")) {
                        boolean isValidEmail = Util.isValidMail(regEmailStr);
                        if (!isValidEmail) {
                            Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_EMAIL, DEFAULT_OOPS_INVALID_EMAIL), Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }

                if (is_otp_enable.equals("1")) {
                    if (mobileotpStr.trim().equals("")) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(TEXT_OTP, DEFAULT_TEXT_OTP), Toast.LENGTH_LONG).show();
                        return;
                    } else if (!Util.isValidOTP(mobileotpStr)) {
                        Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(INVALID_OTP, DEFAULT_OOPS_INVALID_OTP), Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        otpStr = mobileotpStr;
                    }
                }

            }

            if (regPasswordStr.equals("")) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(TEXT_PASSWORD, DEFAULT_TEXT_PASSWORD), Toast.LENGTH_LONG).show();
                return;
            }

            boolean ischeckpasswordlength = Util.passwordlengthcheck(editPassword);
            if (ischeckpasswordlength != true) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(PASSWORDS_LENGTH, DEFAULT_PASSWORDS_LENGTH), Toast.LENGTH_LONG).show();
                return;
            }

            if (regConfirmPasswordStr.equals("")) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(VALID_CONFIRM_PASSWORD, DEFAULT_VALID_CONFIRM_PASSWORD), Toast.LENGTH_LONG).show();
                return;
            }
            if (!regPasswordStr.equals(regConfirmPasswordStr)) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(PASSWORDS_DO_NOT_MATCH, DEFAULT_PASSWORDS_DO_NOT_MATCH), Toast.LENGTH_LONG).show();
                return;
            }

            if (group_linear.getVisibility() == View.VISIBLE && user_group_name.equals(languagePreference.getTextofLanguage(CHOOSE_USER_GROUP, DEFAULT_CHOOSE_USER_GROUP))) {
                Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(CHOOSE_USER_GROUP, DEFAULT_CHOOSE_USER_GROUP), Toast.LENGTH_LONG).show();
                return;
            }


            Registration_input registration_input = new Registration_input();
            registration_input.setAuthToken(authTokenStr);
            registration_input.setName(nameStr);
            registration_input.setCustom_last_name("");

            registration_input.setEmail(regEmailStr);
            registration_input.setMobile_country_code(regCCodeStr);
            registration_input.setPhone(regPhoneStr);
            registration_input.setOtp(otpStr);

            registration_input.setPassword(regPasswordStr);
            registration_input.setCustom_country(registerUIHandler.selected_Country_Id);
            registration_input.setCustom_languages(registerUIHandler.selected_Language_Id);
            registration_input.setDevice_id(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
            registration_input.setGoogle_id(languagePreference.getTextofLanguage(GOOGLE_FCM_TOKEN, DEFAULT_GOOGLE_FCM_TOKEN));
            registration_input.setDevice_type("1");
            registration_input.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            registration_input.setCountryCode(preferenceManager.getCountryCodeFromPref());
            registration_input.setGroup_type(user_group_type_id);

            asyncReg = new RegistrationAsynTask(registration_input, RegisterActivity.this, RegisterActivity.this);
            asyncReg.execute();

        } else {
            Toast.makeText(RegisterActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
        }
    }
}