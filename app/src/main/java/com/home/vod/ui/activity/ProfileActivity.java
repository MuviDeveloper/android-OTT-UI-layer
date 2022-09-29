package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.ALLOW_ADD_EMAIL;
import static com.home.vod.preferences.LanguagePreference.ALLOW_ADD_PHONE_NO;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.CHANGE_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.COUNTRYCODE_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.CURRENTPASSWORD_MATCHES_NEWPASSWORD;
import static com.home.vod.preferences.LanguagePreference.CURRENT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ALLOW_ADD_EMAIL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ALLOW_ADD_PHONE_NO;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CHANGE_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_COUNTRYCODE_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CURRENTPASSWORD_MATCHES_NEWPASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CURRENT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_EMAIL_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ENTER_CURRENT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ENTER_NEW_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ERROR_IN_DATA_FETCHING;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_FAILURE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MOBILE_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NAME_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NEW_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_INVALID_PHONE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PASSWORDS_DO_NOT_MATCH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PASSWORDS_LENGTH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PASSWORD_UPDATE_RESTRICTION_MSG;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PROFILE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PROFILE_PICTURE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PROFILE_UPDATED;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_REGISTER_THROUGH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_EMIAL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_MOBILE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_UPDATE_PROFILE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_UPDATE_PROFILE_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_VALID_CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.EMAIL_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.ENTER_CURRENT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.ENTER_NEW_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.ERROR_IN_DATA_FETCHING;
import static com.home.vod.preferences.LanguagePreference.FAILURE;
import static com.home.vod.preferences.LanguagePreference.MOBILE_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.NAME_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.NEW_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.NO_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS;
import static com.home.vod.preferences.LanguagePreference.OOPS_INVALID_PHONE;
import static com.home.vod.preferences.LanguagePreference.PASSWORDS_DO_NOT_MATCH;
import static com.home.vod.preferences.LanguagePreference.PASSWORDS_LENGTH;
import static com.home.vod.preferences.LanguagePreference.PASSWORD_UPDATE_RESTRICTION_MSG;
import static com.home.vod.preferences.LanguagePreference.PROFILE;
import static com.home.vod.preferences.LanguagePreference.PROFILE_PICTURE;
import static com.home.vod.preferences.LanguagePreference.PROFILE_UPDATED;
import static com.home.vod.preferences.LanguagePreference.REGISTER_THROUGH;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SORRY;
import static com.home.vod.preferences.LanguagePreference.TEXT_EMIAL;
import static com.home.vod.preferences.LanguagePreference.TEXT_MOBILE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.TRY_AGAIN;
import static com.home.vod.preferences.LanguagePreference.UPDATE_PROFILE;
import static com.home.vod.preferences.LanguagePreference.UPDATE_PROFILE_ALERT;
import static com.home.vod.preferences.LanguagePreference.VALID_CONFIRM_PASSWORD;
import static com.home.vod.ui.activity.ImageCroppingActivity.EXTRA_PROFILE_PATH;
import static com.home.vod.util.Constant.authTokenStr;
import static player.utils.Util.ALERT;
import static player.utils.Util.DEFAULT_OOPS_INVALID_EMAIL;
import static player.utils.Util.OOPS_INVALID_EMAIL;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.palette.graphics.Palette;

import com.home.apisdk.apiController.GetUserProfileAsynctask;
import com.home.apisdk.apiController.UpadteUserProfileAsynctask;
import com.home.apisdk.apiModel.Get_UserProfile_Input;
import com.home.apisdk.apiModel.Get_UserProfile_Output;
import com.home.apisdk.apiModel.Update_UserProfile_Input;
import com.home.apisdk.apiModel.Update_UserProfile_Output;
import com.home.vod.BuildConfig;
import com.home.vod.R;
import com.home.vod.handlers.ProfileHandler;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ProfileActivity extends AppCompatActivity implements
        UpadteUserProfileAsynctask.Update_UserProfileListener, GetUserProfileAsynctask.Get_UserProfileListener {

    public Toolbar mActionBarToolbar;
    public ToolbarTitleHandler toolbarTitleHandler;
    public ImageView toolbarimage;

    private ImageView bannerImageView;
    private ProfileHandler profileHandler;
    private EditText editConfirmPassword, editNewPassword, editCurrentPassword;
    private EditText emailAddressEditText, nameEditText, mobileEditText, mobileCountryCode;
    private Button changePassword, update_profile;

    private RelativeLayout noInternetConnectionLayout;
    private RelativeLayout noDataLayout;
    private TextView noDataTextView;
    private TextView noInternetTextView;
    private TextView name_of_user;
    private ProgressBarHandler pDialog;
    private LanguagePreference languagePreference;
    private String last_nameStr = "";

    // profile image
    private RelativeLayout linearLayoutBottomSheet;
    private View viewBottomSheet;

    private ImageView editprofile;
    private boolean bottomSheetViewChk = true;
    private ImageView profile_icon;
    private static final int SELECT_FILE = 2;
    private static final int REQUEST_CAMERA = 1;
    private Uri photoURI;
    private String SelectedPath = "";
    private String[] requests = {"uses-permission android:name=\"android.permission.CAMERA\"", "uses-permission android:name=\"android.permission.WRITE_EXTERNAL_STORAGE\""};
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    private String filename;


    private int corePoolSize = 60;
    private int maximumPoolSize = 80;
    private int keepAliveTime = 10;
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    private Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

    private Spinner country_spinner, language_spinner;
    private ArrayAdapter<String> Language_arrayAdapter, Country_arrayAdapter;

    private String Selected_Language_Id = "", Selected_Country_Id = "";
    boolean change_password_clicked = true;

    private PreferenceManager preferenceManager;
    private List<String> Country_List, Country_Code_List, Language_List, Language_Code_List;
    private FeatureHandler featureHandler;
    /**
     * @description : Retry Button when connection fails and animation on button click
     */

    private Button btnRetry;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private TextView oopsTextView;
    private String register_through, allow_add_email, allow_add_phone_no, otp_expiry_duration;

    /*
     *
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
        setContentView(R.layout.activity_profile);
        View focusedView = ProfileActivity.this.getCurrentFocus();

        if (focusedView != null) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }

        preferenceManager = PreferenceManager.getPreferenceManager(this);
        languagePreference = LanguagePreference.getLanguagePreference(ProfileActivity.this);
        featureHandler = FeatureHandler.getFeaturePreference(ProfileActivity.this);

        bannerImageView = findViewById(R.id.bannerImageView);
        editCurrentPassword = findViewById(R.id.current_pwd);
        editNewPassword = findViewById(R.id.pwd);
        editConfirmPassword = findViewById(R.id.confirm_pass);
        profileHandler = new ProfileHandler(this);

        emailAddressEditText = findViewById(R.id.email);
        nameEditText = findViewById(R.id.name);
        mobileEditText = findViewById(R.id.mobile_no);
        mobileCountryCode = findViewById(R.id.mobile_country_code);

        emailAddressEditText.setHint(languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL));
        mobileEditText.setHint(languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER, DEFAULT_TEXT_MOBILE_NUMBER));
        mobileCountryCode.setHint("1");

        /*
         * @desc : register_through profile update
         * @Note : 1: Registration through email, 2: Registraion through mobile no
         */

        requestPermissions(ProfileActivity.this, requests, 0);

        register_through = languagePreference.getTextofLanguage(REGISTER_THROUGH, DEFAULT_REGISTER_THROUGH);
        allow_add_email = languagePreference.getTextofLanguage(ALLOW_ADD_EMAIL, DEFAULT_ALLOW_ADD_EMAIL);
        allow_add_phone_no = languagePreference.getTextofLanguage(ALLOW_ADD_PHONE_NO, DEFAULT_ALLOW_ADD_PHONE_NO);

        emailAddressEditText.setVisibility(View.GONE);
        mobileEditText.setVisibility(View.GONE);
        mobileCountryCode.setVisibility(View.GONE);

        if (register_through.equals("1")) {
            emailAddressEditText.setVisibility(View.VISIBLE);

            emailAddressEditText.setOnClickListener(null);
            emailAddressEditText.setOnLongClickListener(null);
            emailAddressEditText.setOnTouchListener(null);
            emailAddressEditText.setFocusable(false);
            emailAddressEditText.setFocusableInTouchMode(false);
            emailAddressEditText.setClickable(false);
            emailAddressEditText.setLongClickable(false);

            mobileEditText.setOnClickListener(null);
            mobileEditText.setOnLongClickListener(null);
            mobileEditText.setOnTouchListener(null);
            mobileEditText.setFocusable(true);
            mobileEditText.setFocusableInTouchMode(true);
            mobileEditText.setClickable(true);
            mobileEditText.setLongClickable(true);

            mobileCountryCode.setOnClickListener(null);
            mobileCountryCode.setOnLongClickListener(null);
            mobileCountryCode.setOnTouchListener(null);
            mobileCountryCode.setFocusable(true);
            mobileCountryCode.setFocusableInTouchMode(true);
            mobileCountryCode.setClickable(true);
            mobileCountryCode.setLongClickable(true);

            //Mobile : 2:manadatory 1: optional, 0: not allowed from CMS
            if (allow_add_phone_no.equals("2")) {
                mobileCountryCode.setVisibility(View.VISIBLE);
                mobileEditText.setVisibility(View.VISIBLE);
            } else if (allow_add_phone_no.equals("1")) {
                mobileCountryCode.setVisibility(View.VISIBLE);
                mobileEditText.setVisibility(View.VISIBLE);
            } else {
                mobileCountryCode.setVisibility(View.GONE);
                mobileEditText.setVisibility(View.GONE);
            }
        } else {
            mobileCountryCode.setVisibility(View.VISIBLE);
            mobileEditText.setVisibility(View.VISIBLE);

            mobileCountryCode.setOnClickListener(null);
            mobileCountryCode.setOnLongClickListener(null);
            mobileCountryCode.setOnTouchListener(null);
            mobileCountryCode.setFocusable(false);
            mobileCountryCode.setFocusableInTouchMode(false);
            mobileCountryCode.setClickable(false);
            mobileCountryCode.setLongClickable(false);

            mobileEditText.setOnClickListener(null);
            mobileEditText.setOnLongClickListener(null);
            mobileEditText.setOnTouchListener(null);
            mobileEditText.setFocusable(false);
            mobileEditText.setFocusableInTouchMode(false);
            mobileEditText.setClickable(false);
            mobileEditText.setLongClickable(false);

            emailAddressEditText.setOnClickListener(null);
            emailAddressEditText.setOnLongClickListener(null);
            emailAddressEditText.setOnTouchListener(null);
            emailAddressEditText.setFocusable(true);
            emailAddressEditText.setFocusableInTouchMode(true);
            emailAddressEditText.setClickable(true);
            emailAddressEditText.setLongClickable(true);

            //Email : 2:manadatory 1: optional, 0: not allowed from CMS
            if (allow_add_email.equals("2")) {
                emailAddressEditText.setVisibility(View.VISIBLE);
            } else if (allow_add_email.equals("1")) {
                emailAddressEditText.setVisibility(View.VISIBLE);
            } else {
                emailAddressEditText.setVisibility(View.GONE);
            }
        }

        nameEditText.setEnabled(true);
        mobileEditText.setEnabled(true);
        mobileCountryCode.setEnabled(true);

        changePassword = findViewById(R.id.change_password);
        update_profile = findViewById(R.id.update_profile);
        noInternetConnectionLayout = findViewById(R.id.noInternet);
        noDataLayout = findViewById(R.id.noData);
        noInternetTextView = findViewById(R.id.noInternetTextView);
        noDataTextView = findViewById(R.id.noDataTextView);
        oopsTextView = findViewById(R.id.oopsTextView); // Added by Debashish
        btnRetry = findViewById(R.id.btnRetry); // Added by Debashish
        noInternetTextView.setText(languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION));
        noDataTextView.setText(languagePreference.getTextofLanguage(ERROR_IN_DATA_FETCHING, DEFAULT_ERROR_IN_DATA_FETCHING));

        /**
         *
         * @description : two keys added (1) Try Again ! (2) Oops !
         */

        btnRetry.setText("          " + languagePreference.getTextofLanguage(TRY_AGAIN, DEFAULT_TRY_AGAIN) + "       ");
        oopsTextView.setText(languagePreference.getTextofLanguage(OOPS, DEFAULT_OOPS));

        noInternetConnectionLayout.setVisibility(View.GONE);
        noDataLayout.setVisibility(View.GONE);

        editConfirmPassword.setVisibility(View.GONE);
        editNewPassword.setVisibility(View.GONE);
        editCurrentPassword.setVisibility(View.GONE);
        name_of_user = findViewById(R.id.name_of_user);
        linearLayoutBottomSheet = findViewById(R.id.bottomSheetView);
        linearLayoutBottomSheet.setVisibility(View.GONE);
        viewBottomSheet = findViewById(R.id.viewBottomSheet);
        viewBottomSheet.setVisibility(View.GONE);

        viewBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideDown(linearLayoutBottomSheet);
                viewBottomSheet.setVisibility(View.GONE);
                bottomSheetViewChk = true;
            }
        });

        editprofile = findViewById(R.id.editprofile);
        profile_icon = findViewById(R.id.profile_icon);
        country_spinner = findViewById(R.id.countrySpinner);
        language_spinner = findViewById(R.id.languageSpinner);
        country_spinner.setVisibility(View.GONE);
        language_spinner.setVisibility(View.GONE);

        FontUtls.loadFont(ProfileActivity.this, getResources().getString(R.string.light_fonts), editConfirmPassword);
        FontUtls.loadFont(ProfileActivity.this, getResources().getString(R.string.light_fonts), editNewPassword);
        FontUtls.loadFont(ProfileActivity.this, getResources().getString(R.string.light_fonts), editCurrentPassword);
        FontUtls.loadFont(ProfileActivity.this, getResources().getString(R.string.regular_fonts), changePassword);
        FontUtls.loadFont(ProfileActivity.this, getResources().getString(R.string.regular_fonts), update_profile);

        editCurrentPassword.setHint(languagePreference.getTextofLanguage(CURRENT_PASSWORD, DEFAULT_CURRENT_PASSWORD));
        editConfirmPassword.setHint(languagePreference.getTextofLanguage(CONFIRM_PASSWORD, DEFAULT_CONFIRM_PASSWORD));
        editNewPassword.setHint(languagePreference.getTextofLanguage(NEW_PASSWORD, DEFAULT_NEW_PASSWORD));
        changePassword.setText(languagePreference.getTextofLanguage(CHANGE_PASSWORD, DEFAULT_CHANGE_PASSWORD));
        update_profile.setText(languagePreference.getTextofLanguage(UPDATE_PROFILE, DEFAULT_UPDATE_PROFILE));

        mActionBarToolbar = findViewById(R.id.toolbar);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);
        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setTitle(languagePreference.getTextofLanguage(PROFILE, DEFAULT_PROFILE));
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        /*
         * @Author: Biswa
         * @Desc: This for setting up the toolbar back arrow icon change
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Configuration config = getResources().getConfiguration();
            if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
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

        setIdToActionBarBackButton(mActionBarToolbar);


        Country_List = Arrays.asList(getResources().getStringArray(R.array.country));
        Country_Code_List = Arrays.asList(getResources().getStringArray(R.array.countrycode));
        Language_List = Arrays.asList(getResources().getStringArray(R.array.languages));
        Language_Code_List = Arrays.asList(getResources().getStringArray(R.array.languagesCode));

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(ProfileActivity.this);
                if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(ProfileActivity.this, requests, 0);
                } else {
                    bottomSheetView();
                }
            }
        });

        Language_arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.country_language_spinner, Language_List) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                FontUtls.loadFont(ProfileActivity.this, getResources().getString(R.string.light_fonts), (TextView) v);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                FontUtls.loadFont(ProfileActivity.this, getResources().getString(R.string.light_fonts), (TextView) v);
                return v;
            }

        };

        language_spinner.setAdapter(Language_arrayAdapter);

        Country_arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.country_language_spinner, Country_List) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                FontUtls.loadFont(ProfileActivity.this, getResources().getString(R.string.light_fonts), (TextView) v);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                FontUtls.loadFont(ProfileActivity.this, getResources().getString(R.string.light_fonts), (TextView) v);
                return v;
            }

        };

        country_spinner.setAdapter(Country_arrayAdapter);

        country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Selected_Country_Id = Country_Code_List.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        language_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Selected_Language_Id = Language_Code_List.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (NetworkStatus.getInstance().isConnected(ProfileActivity.this)) {

            Get_UserProfile_Input get_userProfile_input = new Get_UserProfile_Input();
            get_userProfile_input.setAuthToken(authTokenStr);
            get_userProfile_input.setUser_id(preferenceManager.getUseridFromPref());

            if (register_through.equals("1")) {
                get_userProfile_input.setEmail(preferenceManager.getEmailIdFromPref());
            } else {
                get_userProfile_input.setPhone(preferenceManager.getDispPhoneFromPref());
            }

            get_userProfile_input.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
            get_userProfile_input.setCountryCode(preferenceManager.getCountryCodeFromPref());
            GetUserProfileAsynctask asynLoadProfileDetails = new GetUserProfileAsynctask(get_userProfile_input, this, this, false, new DataController(this));
            asynLoadProfileDetails.executeOnExecutor(threadPoolExecutor);
        } else {
            noInternetConnectionLayout.setVisibility(View.VISIBLE);
        }

        if (featureHandler.getFeatureStatus(FeatureHandler.IS_CHANGE_PASSWORD_ENABLED, FeatureHandler.DEFAULT_IS_CHANGE_PASSWORD_ENABLED))
            changePassword.setVisibility(View.VISIBLE);
        else
            changePassword.setVisibility(View.GONE);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (preferenceManager != null && preferenceManager.getSocialLoginFromPref().equals("1")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this, R.style.MyAlertDialogStyle);
                    builder.setTitle(languagePreference.getTextofLanguage(ALERT, DEFAULT_ALERT));
                    builder.setMessage(languagePreference.getTextofLanguage(PASSWORD_UPDATE_RESTRICTION_MSG, DEFAULT_PASSWORD_UPDATE_RESTRICTION_MSG));
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alertDialog = builder.create();

                    // show it
                    alertDialog.show();
                    TextView tv = (TextView) alertDialog.findViewById(android.R.id.message);
                    /*
                     * @Author: Biswa
                     * @Desc: This for checking dialog messsage alignments for RTL support
                     * */
                    Configuration configuration = getResources().getConfiguration();
                    if (configuration.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                        if (tv != null) {
                            tv.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

                        }
                    }

                    return;
                }

                try {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                editConfirmPassword.setText("");
                editNewPassword.setText("");
                editCurrentPassword.setText("");
                editCurrentPassword.setVisibility(View.VISIBLE);
                editConfirmPassword.setVisibility(View.VISIBLE);
                editNewPassword.setVisibility(View.VISIBLE);
                changePassword.setVisibility(View.GONE);
                editCurrentPassword.requestFocus();
                update_profile.setBackgroundResource(R.drawable.button_radious_disable);
                update_profile.setEnabled(false);
                nameEditText.setEnabled(false);
                mobileEditText.setEnabled(false);
                mobileCountryCode.setEnabled(false);
                change_password_clicked = false;

                editCurrentPassword.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        update_profile.setBackgroundResource(R.drawable.button_radious);
                        update_profile.setEnabled(true);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

            }
        });

        mobileCountryCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (allow_add_phone_no.equals("1")) {
                    if (s.length() != 0 && s.length() == 1 && s.toString().equals("0")) {
                        Log.v("MobileCcode", mobileCountryCode.getText().toString());
                        mobileCountryCode.getText().clear();
                    } else if (s.length() == 0 && s.toString().equals("")) {
                        Log.v("MobileCcode", mobileCountryCode.getText().toString());
                    } else {
                        update_profile.setBackgroundResource(R.drawable.button_radious);
                        update_profile.setEnabled(true);
                    }
                } else {
                    update_profile.setBackgroundResource(R.drawable.button_radious);
                    update_profile.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (NetworkStatus.getInstance().isConnected(ProfileActivity.this)) {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    View focusedView = getCurrentFocus();
                    if (focusedView != null) {
                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }

                    String first_nameStr = nameEditText.getText().toString().trim();
                    String email_id = emailAddressEditText.getText().toString().trim();
                    String mobile_no = mobileEditText.getText().toString().trim();
                    String mobileCountry_code = mobileCountryCode.getText().toString().trim();

                    if (first_nameStr.equals("")) {
                        ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(NAME_FIELD_BLANK_ALERT, DEFAULT_NAME_FIELD_BLANK_ALERT));
                        return;
                    }

                    if (register_through.equals("1")) {

                        if (allow_add_phone_no.equals("2")) {

                            if (allow_add_phone_no.equals("2") && mobileCountry_code.equals("")) {
                                ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(COUNTRYCODE_FIELD_BLANK_ALERT, DEFAULT_COUNTRYCODE_FIELD_BLANK_ALERT));
                                return;
                            } else if (allow_add_phone_no.equals("2") && mobile_no.equals("")) {
                                ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(MOBILE_FIELD_BLANK_ALERT, DEFAULT_MOBILE_FIELD_BLANK_ALERT));
                                return;
                            } else {
                                boolean isValidMobile = Util.isValidMobile(mobile_no);
                                if (!isValidMobile) {
                                    Toast.makeText(ProfileActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_PHONE, DEFAULT_OOPS_INVALID_PHONE_NUMBER), Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        } else if (allow_add_phone_no.equals("1")) {

                            if (allow_add_phone_no.equals("1") && mobileCountry_code.equals("") && !mobile_no.equals("")) {
                                ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(COUNTRYCODE_FIELD_BLANK_ALERT, DEFAULT_COUNTRYCODE_FIELD_BLANK_ALERT));
                                return;
                            } else if (allow_add_phone_no.equals("1") && !mobileCountry_code.equals("") && mobile_no.equals("")) {
                                ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(MOBILE_FIELD_BLANK_ALERT, DEFAULT_MOBILE_FIELD_BLANK_ALERT));
                                return;
                            } else if (allow_add_phone_no.equals("1") && mobileCountry_code.equals("") && mobile_no.equals("")) {

                            } else {
                                boolean isValidMobile = Util.isValidMobile(mobile_no);
                                if (!isValidMobile) {
                                    Toast.makeText(ProfileActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_PHONE, DEFAULT_OOPS_INVALID_PHONE_NUMBER), Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        }


                    } else {

                        if (allow_add_email.equals("2")) {

                            if (allow_add_email.equals("2") && email_id.equals("")) {
                                ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(EMAIL_FIELD_BLANK_ALERT, DEFAULT_EMAIL_FIELD_BLANK_ALERT));
                                return;
                            } else if (allow_add_email.equals("2") && !email_id.equals("")) {
                                boolean isValidEmail = Util.isValidMail(email_id);
                                if (!isValidEmail) {
                                    Toast.makeText(ProfileActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_EMAIL, DEFAULT_OOPS_INVALID_EMAIL), Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        } else if (allow_add_email.equals("1")) {

                            if (allow_add_email.equals("1") && email_id.equals("")) {

                            } else if (allow_add_email.equals("1") && !email_id.equals("")) {
                                boolean isValidEmail = Util.isValidMail(email_id);
                                if (!isValidEmail) {
                                    Toast.makeText(ProfileActivity.this, languagePreference.getTextofLanguage(OOPS_INVALID_EMAIL, DEFAULT_OOPS_INVALID_EMAIL), Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }

                        }

                    }

                    if (first_nameStr.equals("")) {
                        ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(NAME_FIELD_BLANK_ALERT, DEFAULT_NAME_FIELD_BLANK_ALERT));
                    } else {
                        UpdateProfile(first_nameStr, last_nameStr, email_id);
                    }
                }

            }
        });


        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(buttonClick);
                restartActivity();
            }
        });
    }

    public void updatebutton(final Button update_profile) {

        update_profile.setBackgroundResource(R.drawable.button_radious_disable);
        update_profile.setEnabled(false);

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                update_profile.setBackgroundResource(R.drawable.button_radious);
                update_profile.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mobileCountryCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if (allow_add_phone_no.equals("1")) {
                    if (s.length() != 0 && s.length() == 1 && s.toString().equals("0")) {
                        Log.v("MobileCcode", mobileCountryCode.getText().toString());
                        mobileCountryCode.getText().clear();
                    } else if (s.length() == 0 && s.toString().equals("")) {
                        Log.v("MobileCcode", mobileCountryCode.getText().toString());
                    } else {
                        update_profile.setBackgroundResource(R.drawable.button_radious);
                        update_profile.setEnabled(true);
                    }
                } else {
                    update_profile.setBackgroundResource(R.drawable.button_radious);
                    update_profile.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mobileEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() != 0 && allow_add_phone_no.equals("1")) {
                    if (charSequence.charAt(0) == '0') {
                        mobileEditText.setError("Number can't starts with 0");
                        mobileEditText.requestFocus();
                        Log.d("Sanjay", "false");
                    } else {
                        update_profile.setBackgroundResource(R.drawable.button_radious);
                        update_profile.setEnabled(true);
                    }
                } else if (charSequence.length() != 0 && allow_add_phone_no.equals("2")) {
                    if (charSequence.charAt(0) == '0') {
                        mobileEditText.setError("Number can't starts with 0");
                        mobileEditText.requestFocus();
                        Log.d("Sanjay", "false");
                    } else {
                        update_profile.setBackgroundResource(R.drawable.button_radious);
                        update_profile.setEnabled(true);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        emailAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                update_profile.setBackgroundResource(R.drawable.button_radious);
                update_profile.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void restartActivity() {
        try {
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);

        } catch (Exception e) {
        }
    }

    public void ShowDialog(String Title, String msg) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ProfileActivity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(msg);
        dlgAlert.setTitle(Title);
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
        dlgAlert.setCancelable(false);
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = dlgAlert.create();
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

    public void UpdateProfile(String first_name, String last_name, String email_Id) {

        if (change_password_clicked) {

            if (editCurrentPassword.getText().toString().equals("")
                    && editNewPassword.getText().toString().equals("")
                    && editConfirmPassword.getText().toString().equals("")) {

                Update_UserProfile_Input update_userProfile_input = new Update_UserProfile_Input();
                update_userProfile_input.setAuthToken(authTokenStr);
                update_userProfile_input.setUser_id(preferenceManager.getUseridFromPref().trim());
                update_userProfile_input.setName(first_name);
                update_userProfile_input.setMobile_country_code(mobileCountryCode.getText().toString().trim());
                update_userProfile_input.setPhone_no(mobileEditText.getText().toString().trim());
                update_userProfile_input.setEmail(email_Id);
                update_userProfile_input.setCustom_last_name(last_name);
                String confirmPasswordStr = editNewPassword.getText().toString().trim();
                String currentPasswordStr = editCurrentPassword.getText().toString().trim();
                update_userProfile_input.setCurrent_password(currentPasswordStr);

                if (!confirmPasswordStr.trim().equalsIgnoreCase("") && !confirmPasswordStr.isEmpty() && !confirmPasswordStr.equalsIgnoreCase("null") && !confirmPasswordStr.equalsIgnoreCase(null) && !confirmPasswordStr.equals(null) && !confirmPasswordStr.matches("")) {
                    update_userProfile_input.setPassword(confirmPasswordStr.trim());
                }

                update_userProfile_input.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                update_userProfile_input.setCustom_country(Selected_Country_Id);
                update_userProfile_input.setCustom_languages(Selected_Language_Id);
                update_userProfile_input.setCountryCode(preferenceManager.getCountryCodeFromPref());
                update_userProfile_input.setImagePath(SelectedPath);

                UpadteUserProfileAsynctask asyncLoadVideos = new UpadteUserProfileAsynctask(update_userProfile_input, this, this);
                asyncLoadVideos.executeOnExecutor(threadPoolExecutor);

            }
        } else {

            if (editCurrentPassword.getText().toString().equals("")) {
                ProfileActivity.this.ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(ENTER_CURRENT_PASSWORD, DEFAULT_ENTER_CURRENT_PASSWORD));
            } else if (editNewPassword.getText().toString().equals("")) {
                ProfileActivity.this.ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(ENTER_NEW_PASSWORD, DEFAULT_ENTER_NEW_PASSWORD));
            } else if (!Util.passwordlengthcheck(editNewPassword)) {
                ProfileActivity.this.ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(PASSWORDS_LENGTH, DEFAULT_PASSWORDS_LENGTH));
            } else if (editCurrentPassword.getText().toString().equals(editNewPassword.getText().toString().equals(""))) {
                ProfileActivity.this.ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(CURRENTPASSWORD_MATCHES_NEWPASSWORD, DEFAULT_CURRENTPASSWORD_MATCHES_NEWPASSWORD));
            } else if (editConfirmPassword.getText().toString().equals("")) {
                ProfileActivity.this.ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(VALID_CONFIRM_PASSWORD, DEFAULT_VALID_CONFIRM_PASSWORD));
            } else if (!passwordMatchValidation()) {
                ProfileActivity.this.ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(PASSWORDS_DO_NOT_MATCH, DEFAULT_PASSWORDS_DO_NOT_MATCH));
            } else {

                Update_UserProfile_Input update_userProfile_input = new Update_UserProfile_Input();
                update_userProfile_input.setAuthToken(authTokenStr);
                update_userProfile_input.setUser_id(preferenceManager.getUseridFromPref().trim());
                update_userProfile_input.setName(first_name);

                update_userProfile_input.setMobile_country_code(mobileCountryCode.getText().toString().trim());
                update_userProfile_input.setPhone_no(mobileEditText.getText().toString().trim());
                update_userProfile_input.setEmail(email_Id);

                update_userProfile_input.setCustom_last_name(last_name);
                String confirmPasswordStr = editNewPassword.getText().toString().trim();
                String currentPasswordStr = editCurrentPassword.getText().toString().trim();
                update_userProfile_input.setCurrent_password(currentPasswordStr);

                if (!confirmPasswordStr.trim().equalsIgnoreCase("") && !confirmPasswordStr.isEmpty() && !confirmPasswordStr.equalsIgnoreCase("null") && !confirmPasswordStr.equalsIgnoreCase(null) && !confirmPasswordStr.equals(null) && !confirmPasswordStr.matches("")) {
                    update_userProfile_input.setPassword(confirmPasswordStr.trim());
                }

                update_userProfile_input.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                update_userProfile_input.setCustom_country(Selected_Country_Id);
                update_userProfile_input.setCustom_languages(Selected_Language_Id);
                update_userProfile_input.setCountryCode(preferenceManager.getCountryCodeFromPref());
                update_userProfile_input.setImagePath(SelectedPath);

                UpadteUserProfileAsynctask asyncLoadVideos = new UpadteUserProfileAsynctask(update_userProfile_input, this, this);
                asyncLoadVideos.executeOnExecutor(threadPoolExecutor);
            }
        }

    }

    @Override
    public void onUpdateUserProfilePreExecuteStarted() {
        pDialog = new ProgressBarHandler(ProfileActivity.this);
        pDialog.show();
    }

    @Override
    public void onUpdateUserProfilePostExecuteCompleted(Update_UserProfile_Output update_userProfile_output, int code, String message) {

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
        } catch (IllegalArgumentException ex) {

        }

        if (code > 0) {

            if (code == 200) {
                change_password_clicked =true;

                if (featureHandler.getFeatureStatus(FeatureHandler.IS_CHANGE_PASSWORD_ENABLED, FeatureHandler.DEFAULT_IS_CHANGE_PASSWORD_ENABLED))
                    changePassword.setVisibility(View.VISIBLE);
                else
                    changePassword.setVisibility(View.GONE);

                editConfirmPassword.setText("");
                editNewPassword.setText("");
                editCurrentPassword.setText("");
                editCurrentPassword.setVisibility(View.GONE);
                editConfirmPassword.setVisibility(View.GONE);
                editNewPassword.setVisibility(View.GONE);

                String confirmPasswordStr = editNewPassword.getText().toString().trim();
                name_of_user.setText(profileHandler.first_nameStr + " " + profileHandler.last_nameStr);

                if (!confirmPasswordStr.trim().equalsIgnoreCase("") &&
                        !confirmPasswordStr.isEmpty() &&
                        !confirmPasswordStr.equalsIgnoreCase("null") &&
                        !confirmPasswordStr.equalsIgnoreCase(null) && !confirmPasswordStr.equals(null) &&
                        !confirmPasswordStr.matches("")) {
                    preferenceManager.setPwdToPref(confirmPasswordStr);
                }
                if (update_userProfile_output != null) {

                    String displayNameStr = update_userProfile_output.getName();
                    preferenceManager.setDispNameToPref(displayNameStr);
                    String displayPhoneNumber = update_userProfile_output.getPhone_no();
                    preferenceManager.setDispPhoneToPref(displayPhoneNumber);
                    preferenceManager.setEmailIdToPref(update_userProfile_output.getEmail());
                    preferenceManager.setLoginProfImgoPref(update_userProfile_output.getProfile_image());
                }
                Util.showToast(ProfileActivity.this, languagePreference.getTextofLanguage(PROFILE_UPDATED, DEFAULT_PROFILE_UPDATED));
                View focusedView = ProfileActivity.this.getCurrentFocus();
                if (focusedView != null) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                }
                if (name_of_user != null) {
                    name_of_user.clearFocus();
                    name_of_user.setCursorVisible(false);
                }

                if (editConfirmPassword != null) {
                    editConfirmPassword.clearFocus();
                }
                if (editNewPassword != null) {
                    editNewPassword.clearFocus();
                }

                if (editCurrentPassword != null) {
                    editCurrentPassword.clearFocus();
                }

                updatebutton(update_profile);

                navigateToHomepage();
            } else {
                Util.showToast(ProfileActivity.this, message);
            }
        } else {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ProfileActivity.this, R.style.MyAlertDialogStyle);
            dlgAlert.setMessage(languagePreference.getTextofLanguage(UPDATE_PROFILE_ALERT, DEFAULT_UPDATE_PROFILE_ALERT));
            dlgAlert.setTitle(languagePreference.getTextofLanguage(SORRY, DEFAULT_SORRY));
            dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), null);
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            editConfirmPassword.setText("");
                            editNewPassword.setText("");
                            editCurrentPassword.setText("");
                        }
                    });
            dlgAlert.create().show();
        }


    }
    public void navigateToHomepage() {

        try {
            Util.image_orentiation.clear();
            Intent in = new Intent(ProfileActivity.this, MainActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void onStart() {
        super.onStart();
        change_password_clicked =true;
    }

    @Override
    public void onGet_UserProfilePreExecuteStarted() {

        pDialog = new ProgressBarHandler(ProfileActivity.this);
        pDialog.show();
    }

    @Override
    public void onGet_UserProfilePostExecuteCompleted(Get_UserProfile_Output get_userProfile_output, int code, String message, String status) {

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
        } catch (IllegalArgumentException ex) {

        }

        if (code == 200) {

            if (get_userProfile_output != null) {
                String profileImage = get_userProfile_output.getProfile_image();

                preferenceManager.setLoginProfImgoPref(get_userProfile_output.getProfile_image());
                if (profileImage != null && profileImage.length() > 0) {
                    int pos = profileImage.lastIndexOf("/");
                    String x = profileImage.substring(pos + 1, profileImage.length());

                    if (x.equals("no-image.png")) {
                        profile_icon.setImageResource(R.drawable.ic_end_user);

                    } else {
                        Picasso.with(ProfileActivity.this)
                                .load(profileImage)
                                .into(profile_icon);                    }
                }

                preferenceManager.setGroupTypePref(get_userProfile_output.getGroup_type());

                if (Selected_Country_Id.equals("0")) {
                    country_spinner.setSelection(224);
                    Selected_Country_Id = Country_Code_List.get(224);
                } else {
                    for (int i = 0; i < Country_Code_List.size(); i++) {
                        if (Selected_Country_Id.trim().equals(Country_Code_List.get(i))) {
                            country_spinner.setSelection(i);
                            Selected_Country_Id = Country_Code_List.get(i);

                        }
                    }
                }
                Country_arrayAdapter.notifyDataSetChanged();

                for (int i = 0; i < Language_Code_List.size(); i++) {
                    if (Selected_Language_Id.trim().equals(Language_Code_List.get(i))) {
                        language_spinner.setSelection(i);
                        Selected_Language_Id = Language_Code_List.get(i);

                    }
                }

                Language_arrayAdapter.notifyDataSetChanged();

                String email = get_userProfile_output.getEmail();
                String phone = get_userProfile_output.getPhone();
                String mobilecountrycode = get_userProfile_output.getMobile_country_code();

                name_of_user.setText(get_userProfile_output.getDisplay_name());
                emailAddressEditText.setText(email);
                mobileEditText.setText(phone);

                if (mobilecountrycode.equals("0")) {
                    mobileCountryCode.setText("");
                } else {
                    mobileCountryCode.setText(mobilecountrycode);
                }

                profileHandler.setNameTxt(get_userProfile_output.getDisplay_name(), get_userProfile_output.getCustom_last_name(), get_userProfile_output.getPhone());

                if (get_userProfile_output.getProfile_image().matches(NO_DATA)) {
                    bannerImageView.setAlpha(0.8f);
                    bannerImageView.setImageResource(R.drawable.logo);
                } else {
                    Picasso.with(ProfileActivity.this)
                            .load(get_userProfile_output.getProfile_image())
                            .placeholder(R.drawable.logo).error(R.drawable.logo).noFade().resize(200, 200).into(bannerImageView, new Callback() {

                        @Override
                        public void onSuccess() {

                            Bitmap bitmapFromPalette = ((BitmapDrawable) bannerImageView.getDrawable()).getBitmap();
                            Palette palette = Palette.generate(bitmapFromPalette);
                        }

                        @Override
                        public void onError() {
                            // reset your views to default colors, etc.
                            bannerImageView.setAlpha(0.8f);
                            bannerImageView.setImageResource(R.drawable.no_image);
                        }

                    });
                    if (get_userProfile_output.getProfile_image() != null && get_userProfile_output.getProfile_image().length() > 0) {
                        int pos = get_userProfile_output.getProfile_image().lastIndexOf("/");
                        String x = get_userProfile_output.getProfile_image().substring(pos + 1);

                        if (x.equalsIgnoreCase("no-user.png")) {
                            bannerImageView.setImageResource(R.drawable.no_image);
                            bannerImageView.setAlpha(0.8f);

                        } else {
                            Picasso.with(ProfileActivity.this)
                                    .load(get_userProfile_output.getProfile_image())
                                    .placeholder(R.drawable.logo).error(R.drawable.logo).noFade().resize(200, 200).into(bannerImageView, new Callback() {

                                @Override
                                public void onSuccess() {
                                    bannerImageView.setAlpha(0.3f);

                                }

                                @Override
                                public void onError() {
                                    bannerImageView.setImageResource(R.drawable.no_image);
                                    bannerImageView.setAlpha(0.8f);
                                }

                            });
                        }
                    }
                }
            }

            updatebutton(update_profile);

        } else {
            noDataLayout.setVisibility(View.VISIBLE);
            noInternetConnectionLayout.setVisibility(View.GONE);
        }
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    public void removeFocusFromViews() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        removeFocusFromViews();

    }

    @Override
    public void onPause() {
        super.onPause();

        removeFocusFromViews();

    }

    public boolean passwordMatchValidation() {
        return editConfirmPassword.getText().toString().trim().matches(editNewPassword.getText().toString().trim());
    }

    /*
 To set id to back button in Action Bar
 */
    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.back);
            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                t.setId(R.id.page_title_profile);
            }
        }
    }

    /**
     * returning image / video
     */
    private File getOutputMediaFile(int type) {
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!storageDir.exists()) {
            if (!storageDir.mkdirs()) {
                return null;
            }
        }
        return new File(storageDir.getPath(), "temp.jpg");
    }

    /* @author Chitra
     * Check whether CAMERA and WRITE_EXTERNAL_STORAGE permission is granted or not.
     */
    void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);


        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                "temp",  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == 1000) {
                SelectedPath = data.getStringExtra(EXTRA_PROFILE_PATH);
                profile_icon.setImageURI(Uri.parse(SelectedPath));
            } else if (requestCode == REQUEST_CAMERA) {
                update_profile.setBackgroundResource(R.drawable.button_radious);
                update_profile.setEnabled(true);

                String imageuri = photoURI.getPath();
                Intent intent = new Intent(ProfileActivity.this, ImageCroppingActivity.class);
                intent.putExtra("URI", currentPhotoPath);
                startActivityForResult(intent, 1000);
            } else if (requestCode == SELECT_FILE) {
                update_profile.setBackgroundResource(R.drawable.button_radious);
                update_profile.setEnabled(true);
                Uri selectedImageUri = data.getData();

                String tempPath = "";
                try {
                    SelectedPath = getImagePath(ProfileActivity.this, selectedImageUri);
                    tempPath = getImagePath(ProfileActivity.this, selectedImageUri);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(ProfileActivity.this, ImageCroppingActivity.class);
                intent.putExtra("URI", tempPath);
                startActivityForResult(intent, 1000);
            }
        }
    }

    public static Bitmap rotate(Bitmap bitmap, int degree) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix mtx = new Matrix();
        mtx.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }

    public String compressImage(String imageUri) {

        String filePath = imageUri;
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        filename = getFilename();
        try {
            out = new FileOutputStream(filename);
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return filename;
    }


    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }

    private String getRealPathFromURI(Uri contentURI) {
        Uri contentUri = contentURI;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public String getPath(Uri uri, Activity activity) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void bottomSheetView() {

        TextView profile_picture_title = findViewById(R.id.profile_picture_title);

        ImageView camera = findViewById(R.id.camera);
        ImageView gallery = findViewById(R.id.gallery);

        profile_picture_title.setText(languagePreference.getTextofLanguage(PROFILE_PICTURE, DEFAULT_PROFILE_PICTURE));

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }

                    if (photoFile != null) {
                        photoURI = FileProvider.getUriForFile(ProfileActivity.this,
                                BuildConfig.APPLICATION_ID + ".provider", photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, REQUEST_CAMERA);
                    }

                    slideDown(linearLayoutBottomSheet);
                    viewBottomSheet.setVisibility(View.GONE);
                    bottomSheetViewChk = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

                    slideDown(linearLayoutBottomSheet);
                    viewBottomSheet.setVisibility(View.GONE);
                    bottomSheetViewChk = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (bottomSheetViewChk) {
            viewBottomSheet.setVisibility(View.VISIBLE);
            slideUp(linearLayoutBottomSheet);
            bottomSheetViewChk = false;
        } else {
            viewBottomSheet.setVisibility(View.GONE);
            slideDown(linearLayoutBottomSheet);
            bottomSheetViewChk = true;
        }
    }

    public void slideUp(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0, view.getHeight(), 0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.VISIBLE);

    }

    public void slideDown(View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0, 0, view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);

    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    /*
     * Gets the file path of the given Uri.
     */
    @SuppressLint("NewApi")
    public static String getImagePath(Context context, Uri uri) throws URISyntaxException {
        final boolean needToCheckUri = Build.VERSION.SDK_INT >= 19;
        String selection = null;
        String[] selectionArgs = null;
        if (needToCheckUri && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{split[1]};
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
