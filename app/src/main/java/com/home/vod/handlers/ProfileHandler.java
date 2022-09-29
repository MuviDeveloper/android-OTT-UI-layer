package com.home.vod.handlers;

import static android.content.Context.MODE_PRIVATE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_FAILURE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MOBILE_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NAME_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NAME_HINT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_INVALID_PHONE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_MOBILE_NUMBER;
import static com.home.vod.preferences.LanguagePreference.FAILURE;
import static com.home.vod.preferences.LanguagePreference.MOBILE_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.NAME_FIELD_BLANK_ALERT;
import static com.home.vod.preferences.LanguagePreference.NAME_HINT;
import static com.home.vod.preferences.LanguagePreference.OOPS_INVALID_PHONE;
import static com.home.vod.preferences.LanguagePreference.TEXT_MOBILE_NUMBER;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.home.vod.R;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.ui.activity.ProfileActivity;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;

/**
 * Created by MUVI on 10/27/2017.
 */

public class ProfileHandler {
    private Activity context;
    EditText editProfileNameEditText,mobileEditText;
    LanguagePreference languagePreference;
    public String first_nameStr="",last_nameStr="",mobile_number ="";
    public String final_name = "";
    public String phoneStr="";
    int mobile_no_required;



    public ProfileHandler(Activity context){
        this.context=context;
        editProfileNameEditText = (EditText) context.findViewById(R.id.name);
        mobileEditText = (EditText) context.findViewById(R.id.mobile_no);
        FontUtls.loadFont(context, context.getResources().getString(R.string.light_fonts), editProfileNameEditText);
        FontUtls.loadFont(context, context.getResources().getString(R.string.light_fonts), mobileEditText);
        languagePreference = LanguagePreference.getLanguagePreference(context);
        editProfileNameEditText.setHint(languagePreference.getTextofLanguage(NAME_HINT,DEFAULT_NAME_HINT));
        mobileEditText.setHint(languagePreference.getTextofLanguage(TEXT_MOBILE_NUMBER,DEFAULT_TEXT_MOBILE_NUMBER));

    }
    public void updateProfileHandler() {

    /*    String name_text = editProfileNameEditText.getText().toString();
        if (!((ProfileActivity) context).passwordMatchValidation()) {
            ((ProfileActivity) context).ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(PASSWORDS_DO_NOT_MATCH, DEFAULT_PASSWORDS_DO_NOT_MATCH));

        } else {
        }
*/
                if (NetworkStatus.getInstance().isConnected(context)) {
                    InputMethodManager inputManager = (InputMethodManager)
                            context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    View focusedView =context.getCurrentFocus();
                    if(focusedView!=null){
                        inputManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                    }

                    first_nameStr = editProfileNameEditText.getText().toString().trim();
                    mobile_number = mobileEditText.getText().toString().trim();

                    /*
                     * @desc : Hide/Show editTextMobileNumber ac to "mobile_no_required" : 1/0 (1-Show and 0-Hide) .
                     * @Note : mobile_no_required fetched from isRegistrationEnabled API in SplashScreen
                     */

                    SharedPreferences prefs = context.getSharedPreferences("Mobile_no_required_File", MODE_PRIVATE);
                    mobile_no_required = prefs.getInt("mobile_no_required", 0);

                    if (first_nameStr.equals("")) {
                        ((ProfileActivity) context).ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(NAME_FIELD_BLANK_ALERT, DEFAULT_NAME_FIELD_BLANK_ALERT));
                    } else if (mobile_no_required == 1) {

                        if (mobile_number.trim().equals("")) {
                            ((ProfileActivity) context).ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(MOBILE_FIELD_BLANK_ALERT, DEFAULT_MOBILE_FIELD_BLANK_ALERT));
                            return;
                        }

                        boolean isValidMobile = Util.isValidMobile(mobile_number);
                        if (!isValidMobile) {
                            ((ProfileActivity) context).ShowDialog(languagePreference.getTextofLanguage(FAILURE, DEFAULT_FAILURE), languagePreference.getTextofLanguage(OOPS_INVALID_PHONE, DEFAULT_OOPS_INVALID_PHONE_NUMBER));
                            return;
                        }

                        ((ProfileActivity) context).UpdateProfile(first_nameStr, last_nameStr, mobile_number);

                    } else {
//                        ((ProfileActivity) context).UpdateProfile(first_nameStr, last_nameStr, phoneStr);
                        ((ProfileActivity) context).UpdateProfile(first_nameStr, last_nameStr, mobile_number);
                    }
                }

    }


    public void setNameTxt(String nameString,String last_name,String phoneNumber){
        editProfileNameEditText.setText(nameString.trim());
        int textLength = editProfileNameEditText.getText().length();
        editProfileNameEditText.setSelection(textLength, textLength);
        mobileEditText.setText(phoneNumber.trim());
    }

    //Added by Abhishek for disable and enable update button

    public void updatebutton(final Button update_profile){

        update_profile.setBackgroundResource(R.drawable.button_radious_disable);
        update_profile.setEnabled(false);


        editProfileNameEditText.addTextChangedListener(new TextWatcher() {
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

        mobileEditText.addTextChangedListener(new TextWatcher() {
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

}
