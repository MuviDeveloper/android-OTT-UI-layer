package com.home.vod.ui.fragment;


import static com.home.vod.preferences.LanguagePreference.BTN_SUBMIT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BTN_SUBMIT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ENTER_REGISTER_FIELDS_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ENTER_YOUR_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_FILL_FORM_BELOW;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NAME_HINT;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_INVALID_EMAIL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_TEXT_EMIAL;
import static com.home.vod.preferences.LanguagePreference.ENTER_REGISTER_FIELDS_DATA;
import static com.home.vod.preferences.LanguagePreference.ENTER_YOUR_MESSAGE;
import static com.home.vod.preferences.LanguagePreference.FILL_FORM_BELOW;
import static com.home.vod.preferences.LanguagePreference.NAME_HINT;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS_INVALID_EMAIL;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.TEXT_EMIAL;
import static com.home.vod.util.Constant.authTokenStr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.home.apisdk.apiController.ContactUsAsynTask;
import com.home.apisdk.apiModel.ContactUsInputModel;
import com.home.apisdk.apiModel.ContactUsOutputModel;
import com.home.vod.R;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.activity.MainActivity;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment implements ContactUsAsynTask.ContactUsListener {

    private Context context;
    private String regEmailStr, regNameStr, regMessageStr;

    private EditText editEmailStr, editNameStr, editMessageStr;
    private TextView contactFormTitle;
    private Button submit;

    private boolean validate = true;

    private LanguagePreference languagePreference;
    private PreferenceManager preferenceManager;
    private ProgressBarHandler pDialog;


    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().getWindow().setBackgroundDrawableResource(R.drawable.app_background);
        View v = inflater.inflate(R.layout.fragment_contact_us, container, false);
        context = getActivity();
        languagePreference = LanguagePreference.getLanguagePreference(context);
        preferenceManager = PreferenceManager.getPreferenceManager(context);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("title"));
        Toolbar toolbar = ((MainActivity) getActivity()).mToolbar;
        setIdToActionBarBackButton(toolbar);
        ((MainActivity) getActivity()).toolbarimage.setVisibility(View.GONE);

        contactFormTitle = (TextView) v.findViewById(R.id.contactFormTitle);
        Typeface contactFormTitleTypeface = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.fonts));
        contactFormTitle.setTypeface(contactFormTitleTypeface);
        contactFormTitle.setText(languagePreference.getTextofLanguage(FILL_FORM_BELOW, DEFAULT_FILL_FORM_BELOW));

        editEmailStr = (EditText) v.findViewById(R.id.contact_email);
        Typeface editEmailStrTypeface = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.fonts));
        editEmailStr.setTypeface(editEmailStrTypeface);
        editEmailStr.setHint(languagePreference.getTextofLanguage(TEXT_EMIAL, DEFAULT_TEXT_EMIAL));

        editNameStr = (EditText) v.findViewById(R.id.contact_name);
        Typeface editNameStrTypeface = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.fonts));
        editNameStr.setTypeface(editNameStrTypeface);
        editNameStr.setHint(languagePreference.getTextofLanguage(NAME_HINT, DEFAULT_NAME_HINT));
        editNameStr.requestFocus();

        editMessageStr = (EditText) v.findViewById(R.id.contact_msg);
        Typeface editMessageStrTypeface = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.fonts));
        editMessageStr.setTypeface(editMessageStrTypeface);
        editMessageStr.setHint(languagePreference.getTextofLanguage(ENTER_YOUR_MESSAGE, DEFAULT_ENTER_YOUR_MESSAGE));

        submit = (Button) v.findViewById(R.id.submit_cont);
        Typeface submitTypeface = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.fonts));
        submit.setTypeface(submitTypeface);
        submit.setText(languagePreference.getTextofLanguage(BTN_SUBMIT, DEFAULT_BTN_SUBMIT));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmmitClicked();
            }
        });

        editMessageStr.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        leaveCurrentPage();
                    }
                }
                return false;
            }
        });

        editNameStr.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        leaveCurrentPage();
                    }
                }
                return false;
            }
        });

        editEmailStr.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        leaveCurrentPage();
                    }
                }
                return false;
            }
        });

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.option).setVisible(false);
        menu.findItem(R.id.search).setVisible(false);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    public void SubmmitClicked() {
        regEmailStr = editEmailStr.getText().toString().trim();
        regNameStr = editNameStr.getText().toString().trim();
        regMessageStr = editMessageStr.getText().toString().trim();
        regMessageStr = regMessageStr.replaceAll("(\r\n|\n\r|\r|\n|<br />)", " ");

        boolean isNetwork = NetworkStatus.getInstance().isConnected(context);
        if (isNetwork) {
            if (!regNameStr.matches("") && (!regEmailStr.matches("")) && (!regMessageStr.matches(""))) {
                boolean isValidEmail = Util.isValidMail(regEmailStr);
                if (isValidEmail) {
                    if (validate) {
                        ContactUsInputModel contactUsInputModel = new ContactUsInputModel();
                        contactUsInputModel.setAuthToken(authTokenStr);
                        contactUsInputModel.setEmail(String.valueOf(regEmailStr));
                        contactUsInputModel.setName(String.valueOf(regNameStr));
                        contactUsInputModel.setMessage(String.valueOf(regMessageStr));
                        contactUsInputModel.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                        contactUsInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                        ContactUsAsynTask asynContactUs = new ContactUsAsynTask(contactUsInputModel, this, context);
                        asynContactUs.execute();

                    } else {
                        validate = true;
                        return;
                    }

                } else {
                    Toast.makeText(context, languagePreference.getTextofLanguage(OOPS_INVALID_EMAIL, DEFAULT_OOPS_INVALID_EMAIL), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(context, languagePreference.getTextofLanguage(ENTER_REGISTER_FIELDS_DATA, DEFAULT_ENTER_REGISTER_FIELDS_DATA), Toast.LENGTH_LONG).show();

            }
        } else {
            Toast.makeText(context, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onContactUsPreExecuteStarted() {
        pDialog = new ProgressBarHandler(context);
        pDialog.show();

    }

    @Override
    public void onContactUsPostExecuteCompleted(ContactUsOutputModel contactUsOutputModel, int code, String message, String status) {
        if (code == 200) {
            Toast.makeText(getActivity(), contactUsOutputModel.getSuccess_msg(), Toast.LENGTH_LONG).show();
            leaveCurrentPage();
        } else {
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
        } catch (IllegalArgumentException ex) {

        }

        editMessageStr.setText("");
        editNameStr.setText("");
        editEmailStr.setText("");
        editMessageStr.setError(null);
        editNameStr.setError(null);
        editEmailStr.setError(null);

    }


    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.menu);

            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                t.setId(R.id.page_title_contact_us);
            }
        }

    }

    public void leaveCurrentPage() {
        PreferenceManager preferenceManager;
        preferenceManager = PreferenceManager.getPreferenceManager(getActivity());
        preferenceManager.setFRAGMENTS_CHANGED("home");
        final Intent startIntent = new Intent(getActivity(), MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().startActivity(startIntent);
        getActivity().finish();
    }
}
