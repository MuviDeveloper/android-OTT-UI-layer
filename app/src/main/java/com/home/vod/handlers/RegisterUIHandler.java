package com.home.vod.handlers;

import static com.home.vod.preferences.LanguagePreference.AGREE_TERMS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_AGREE_TERMS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ENTER_REGISTER_FIELDS_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NAME_HINT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SORRY_ENTER_NAME;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TERMS;
import static com.home.vod.preferences.LanguagePreference.ENTER_REGISTER_FIELDS_DATA;
import static com.home.vod.preferences.LanguagePreference.NAME_HINT;
import static com.home.vod.preferences.LanguagePreference.SORRY_ENTER_NAME;
import static com.home.vod.preferences.LanguagePreference.TERMS;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.home.vod.R;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.activity.RegisterActivity;
import com.home.vod.util.FontUtls;

/**
 * Created by BISHAL on 21-08-2017.
 */

public class RegisterUIHandler {
    private Activity context;
    private TextView termsTextView, termsTextView1;
    private LinearLayout btnLogin,or_layout;
    private EditText editName,edittEmail,editMobile,edittpwd,edittconfirm_pass;
    String fbUserId = "";
    TextView gmailTest;
    private RelativeLayout googleSignView;
    String fbEmail = "";
    String fbName = "";
    public String selected_Language_Id = "", selected_Country_Id = "", regNameStr, regPhone = "", last_name = "";
    private LanguagePreference languagePreference;
    private Button registerButton;

    public RegisterUIHandler(Activity context) {
        this.context = context;
        termsTextView = (TextView) context.findViewById(R.id.terms);
        termsTextView1 = (TextView) context.findViewById(R.id.termsTextView1);

        or_layout = (LinearLayout) context.findViewById(R.id.or_layout);
        gmailTest=(TextView) context.findViewById(R.id.textView);
        editName = (EditText) context.findViewById(R.id.name);
        edittEmail = (EditText) context.findViewById(R.id.email);
        editMobile = (EditText) context.findViewById(R.id.mobile_number);
        edittpwd = (EditText) context.findViewById(R.id.pwd);
        edittconfirm_pass = (EditText) context.findViewById(R.id.confirm_pass);
        languagePreference = LanguagePreference.getLanguagePreference(context);
    }

    public void setCountryList(PreferenceManager preferenceManager) {

    }

    public void setTermsTextView(LanguagePreference languagePreference) {
        termsTextView1.setText(languagePreference.getTextofLanguage(AGREE_TERMS, DEFAULT_AGREE_TERMS));
        termsTextView.setText(languagePreference.getTextofLanguage(TERMS, DEFAULT_TERMS));
        FontUtls.loadFont(context, context.getResources().getString(R.string.light_fonts), editName);
        editName.setHint(languagePreference.getTextofLanguage(NAME_HINT, DEFAULT_NAME_HINT));

        termsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.plusnights.co.uk/privacy-policy"));
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mobplay.muvi.com/en/terms-privacy-policy"));
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(browserIntent);
            }
        });
    }

    public void getRegisterName() {
        regNameStr = editName.getText().toString().trim();
        if (!regNameStr.equals("")) {
            ((RegisterActivity) context).registerButtonClicked(regNameStr, last_name, regPhone);
        } else {
            Toast.makeText(context, languagePreference.getTextofLanguage(SORRY_ENTER_NAME, DEFAULT_SORRY_ENTER_NAME), Toast.LENGTH_LONG).show();
        }
    }

    public void resetallfield() {
        edittEmail.setText("");
        editMobile.setText("");
        editName.setText("");
        edittpwd.setText("");
        edittconfirm_pass.setText("");
        editName.requestFocus();
    }
}
