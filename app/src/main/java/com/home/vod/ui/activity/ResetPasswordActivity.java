package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.BTN_SUBMIT;
import static com.home.vod.preferences.LanguagePreference.CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_SUBMIT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONFIRM_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ENTER_NEW_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PASSWORDS_DO_NOT_MATCH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_PASSWORDS_LENGTH;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_RESET_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.ENTER_NEW_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.PASSWORDS_DO_NOT_MATCH;
import static com.home.vod.preferences.LanguagePreference.PASSWORDS_LENGTH;
import static com.home.vod.preferences.LanguagePreference.RESET_PASSWORD;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.TEXT_PASSWORD;
import static com.home.vod.util.Constant.authTokenStr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.home.apisdk.apiController.ResetPasswordAsync;
import com.home.apisdk.apiModel.ResetPasswordInputModel;
import com.home.apisdk.apiModel.ResetPasswordOutputModel;
import com.home.vod.R;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordAsync.ResetPasswordListener {

    EditText new_password,confirm_password;
    TextView reset_pwd;
    Button submit;
    ImageView dismiss;
    String email,userid;
    LanguagePreference languagePreference;
    PreferenceManager preferenceManager;
    ProgressBarHandler pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        if (Intent.ACTION_VIEW.equals(action) && data != null) {

             email = data.getQueryParameter("email");
             userid = data.getQueryParameter("user_id");

        }

        Log.v("data", String.valueOf(data));
        Log.v("email", data.getQueryParameter("email"));


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        languagePreference = LanguagePreference.getLanguagePreference(this);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        new_password = findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);
        reset_pwd = findViewById(R.id.reset_pwd);
        submit = findViewById(R.id.submit);
        dismiss = findViewById(R.id.dismiss);
        dismiss.setAlpha(0.5f);

        new_password.setHint(languagePreference.getTextofLanguage(TEXT_PASSWORD, DEFAULT_TEXT_PASSWORD));
        confirm_password.setHint(languagePreference.getTextofLanguage(CONFIRM_PASSWORD, DEFAULT_CONFIRM_PASSWORD));

        submit.setText(languagePreference.getTextofLanguage(BTN_SUBMIT, DEFAULT_BTN_SUBMIT));
        FontUtls.loadFont(ResetPasswordActivity.this, getResources().getString(R.string.regular_fonts), submit);


        reset_pwd.setText(languagePreference.getTextofLanguage(RESET_PASSWORD, DEFAULT_RESET_PASSWORD));

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean ischeckpasswordlength = Util.passwordlengthcheck(new_password);

                if (new_password.getText().toString().equals("")){
                    Toast.makeText(ResetPasswordActivity.this, languagePreference.getTextofLanguage(ENTER_NEW_PASSWORD, DEFAULT_ENTER_NEW_PASSWORD), Toast.LENGTH_LONG).show();
                } else if (confirm_password.getText().toString().equals("")){
                    Toast.makeText(ResetPasswordActivity.this, languagePreference.getTextofLanguage(CONFIRM_PASSWORD, DEFAULT_CONFIRM_PASSWORD), Toast.LENGTH_LONG).show();
                } else if (ischeckpasswordlength != true) {
                    Toast.makeText(ResetPasswordActivity.this, languagePreference.getTextofLanguage(PASSWORDS_LENGTH, DEFAULT_PASSWORDS_LENGTH), Toast.LENGTH_LONG).show();
                    return;
                }else if (!new_password.getText().toString().equals(confirm_password.getText().toString())){
                    Toast.makeText(ResetPasswordActivity.this, languagePreference.getTextofLanguage(PASSWORDS_DO_NOT_MATCH, DEFAULT_PASSWORDS_DO_NOT_MATCH), Toast.LENGTH_LONG).show();
                } else {

                    Util.hideKeyboard(ResetPasswordActivity.this);

                    ResetPasswordInputModel resetPasswordInputModel = new ResetPasswordInputModel();
                    resetPasswordInputModel.setEmail(email);
                    resetPasswordInputModel.setAuthToken(authTokenStr);
                    resetPasswordInputModel.setPassword(confirm_password.getText().toString());
                    resetPasswordInputModel.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                    resetPasswordInputModel.setCountry(preferenceManager.getCountryCodeFromPref());

                    ResetPasswordAsync resetPasswordAsync = new ResetPasswordAsync(resetPasswordInputModel, ResetPasswordActivity.this,ResetPasswordActivity.this);
                    resetPasswordAsync.execute();

                }
            }
        });


    }

    @Override
    public void onResetPasswordPreExecuteStarted() {

        pDialog = new ProgressBarHandler(ResetPasswordActivity.this);
        pDialog.show();

    }

    @Override
    public void onResetPasswordPostExecuteCompleted(ResetPasswordOutputModel addToFollowOutputModel, int status, String sucessMsg) {
        try {
            if (pDialog != null && pDialog.isShowing())
                pDialog.hide();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(status == 200){
            Toast.makeText(ResetPasswordActivity.this, sucessMsg, Toast.LENGTH_LONG).show();
            finish();


        } else{
            Toast.makeText(ResetPasswordActivity.this, sucessMsg, Toast.LENGTH_LONG).show();
        }
    }

}