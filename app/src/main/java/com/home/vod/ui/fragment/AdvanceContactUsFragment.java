package com.home.vod.ui.fragment;

import static com.home.vod.preferences.LanguagePreference.DEFAULT_ENTER_REGISTER_FIELDS_DATA;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_INVALID_EMAIL;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.ENTER_REGISTER_FIELDS_DATA;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS_INVALID_EMAIL;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.util.Constant.authTokenStr;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.home.apisdk.apiController.AdvanceContactusAsyncTask;
import com.home.apisdk.apiController.SupportAsynTask;
import com.home.apisdk.apiModel.AdvanceContactUsInput;
import com.home.apisdk.apiModel.AdvanceContactUsOutput;
import com.home.apisdk.apiModel.SupportInputModel;
import com.home.apisdk.apiModel.SupportOutputModel;
import com.home.vod.R;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.activity.MainActivity;
import com.home.vod.ui.activity.SuccessMessageActivity;
import com.home.vod.ui.adapter.QueriesAdapter;
import com.home.vod.ui.adapter.SubQueriesAdapter;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.Util;

import java.util.ArrayList;

public class AdvanceContactUsFragment extends Fragment implements AdvanceContactusAsyncTask.AdvanceContactusListener, QueriesAdapter.QueryListener, SupportAsynTask.SupportListener, SubQueriesAdapter.SubQueryListener {

    private Context context;
    private boolean validate = true;
    private String regEmailStr, regNameStr, regMessageStr;
    private String parentQuery;
    private String subQuery = null;
    private Boolean validCheckbox = false;
    private Boolean validSubQuery = false;
    private String loggedInUserName = null;
    private String emailIdStr = null;

    private LanguagePreference languagePreference;
    private PreferenceManager preferenceManager;
    private ProgressBarHandler pDialog;
    private Dialog alert;
    private QueriesAdapter adapter;
    private SubQueriesAdapter subAdapter;

    private EditText edtName, edtMessage, edtMail;
    private TextView logQryTv, needTV, optTv, issTv, detailTV, emailTv, emailError, queryError, subqueryError, otherqueryError, fille_details;
    private RecyclerView queryRecyclerView, sub_query_recycler_view;
    private Button submitCont;
    private LinearLayout subquery_ll, advance_layout;


    private AdvanceContactusAsyncTask advanceContactusAsyncTask;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setBackgroundDrawableResource(R.drawable.app_background);
        View v = inflater.inflate(R.layout.advance_fragment_contact_us, container, false);
        context = getActivity();
        languagePreference = LanguagePreference.getLanguagePreference(context);
        preferenceManager = PreferenceManager.getPreferenceManager(context);
        emailIdStr = preferenceManager.getEmailIdFromPref();
        loggedInUserName = preferenceManager.getDispNameFromPref();

        Typeface castDescriptionTypeface = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.fonts));
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("title"));
        Toolbar toolbar = ((MainActivity) getActivity()).mToolbar;
        setIdToActionBarBackButton(toolbar);
        ((MainActivity) getActivity()).toolbarimage.setVisibility(View.GONE);

        inits(v);
        callContacusApi();
        setHasOptionsMenu(true);

        return v;
    }

    private void inits(View v) {
        RecyclerView.LayoutManager layoutManager, layoutManager1;
        languagePreference = LanguagePreference.getLanguagePreference(getActivity());
        edtName = v.findViewById(R.id.contact_name);
        edtMessage = v.findViewById(R.id.edt_message);
        edtMail = v.findViewById(R.id.edt_mail);
        fille_details = v.findViewById(R.id.fille_details);
        subquery_ll = v.findViewById(R.id.subquery_ll);
        advance_layout = v.findViewById(R.id.advance_layout);
        queryRecyclerView = v.findViewById(R.id.query_recycler_view);
        sub_query_recycler_view = v.findViewById(R.id.sub_query_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        queryRecyclerView.setLayoutManager(layoutManager);
        queryRecyclerView.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(getContext());
        sub_query_recycler_view.setLayoutManager(layoutManager1);
        sub_query_recycler_view.setHasFixedSize(true);
        submitCont = v.findViewById(R.id.submit_cont);

        logQryTv = v.findViewById(R.id.logQryTv);
        needTV = v.findViewById(R.id.needTV);
        optTv = v.findViewById(R.id.optTv);
        issTv = v.findViewById(R.id.issTv);
        detailTV = v.findViewById(R.id.detailTV);
        emailTv = v.findViewById(R.id.emailTv);
        emailError = v.findViewById(R.id.emailError);
        queryError = v.findViewById(R.id.queryError);
        subqueryError = v.findViewById(R.id.subqueryError);
        otherqueryError = v.findViewById(R.id.otherqueryError);


        Typeface submitTypeface = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.regular_fonts));
        Typeface regularTypeface = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.light_fonts));
        submitCont.setTypeface(submitTypeface);
        submitCont.setText("Submit Query");
        submitCont.setOnClickListener(v1 -> validateField());

        edtName.setTypeface(regularTypeface);
        edtMail.setTypeface(regularTypeface);
        edtMessage.setTypeface(regularTypeface);
        edtMessage.setSelection(0);
        edtName.setHint("Enter your name");
        edtName.setText(loggedInUserName);
        edtMail.setText(emailIdStr);

        logQryTv.setTypeface(submitTypeface);
        logQryTv.setText("Log a new query");
        needTV.setTypeface(regularTypeface);
        needTV.setText("What do you need help with?");
        optTv.setTypeface(regularTypeface);
        optTv.setText("Optional");
        issTv.setTypeface(regularTypeface);
        issTv.setText("What technical issue did you experience?");
        detailTV.setTypeface(regularTypeface);
        detailTV.setText("Can you give us more details about the issue that you are experiencing?");
        emailTv.setTypeface(regularTypeface);
        fille_details.setTypeface(regularTypeface);
        emailTv.setText("Email Address");
        fille_details.setText("Fill in your details so that we can respond to your query");

        emailError.setTypeface(regularTypeface);
        emailError.setText("Email address must be completed");
        queryError.setTypeface(regularTypeface);
        queryError.setText("Please select one of the issues listed.");
        subqueryError.setTypeface(regularTypeface);
        subqueryError.setText("Please select one of the issues listed.");
        otherqueryError.setTypeface(regularTypeface);
        otherqueryError.setText("This field should not be blank.");

        editChange();
    }

    private void editChange() {
        edtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtMail.setBackgroundResource(R.drawable.edit_outer_line);
                if (emailError.getVisibility() == View.VISIBLE) {
                    emailError.setVisibility(View.GONE);
                    emailTv.setTextColor(context.getResources().getColor(R.color.editTextColor));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edtMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtMessage.setBackgroundResource(R.drawable.edit_outer_line);
                if (otherqueryError.getVisibility() == View.VISIBLE) {
                    otherqueryError.setVisibility(View.GONE);
                    detailTV.setTextColor(context.getResources().getColor(R.color.editTextColor));


                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void validateField() {
        if (!validCheckbox) {
            queryError.setVisibility(View.VISIBLE);
            needTV.requestFocus();

        } else if (subquery_ll.getVisibility() == View.VISIBLE && !validSubQuery) {
            queryError.setVisibility(View.VISIBLE);
            edtMessage.setBackgroundResource(R.drawable.edit_outer_line);
            otherqueryError.setVisibility(View.GONE);
            detailTV.setTextColor(context.getResources().getColor(R.color.editTextColor));
            if (otherqueryError.getVisibility() == View.VISIBLE) {
                otherqueryError.setVisibility(View.GONE);
                edtMessage.setBackgroundResource(R.drawable.edit_outer_line);
            }
        } else if (parentQuery.equals("Other") && edtMessage.getText().toString().trim().isEmpty()) {
            edtMessage.setBackgroundResource(R.drawable.edit_error_outer_line);
            edtMessage.requestFocus();
            otherqueryError.setVisibility(View.VISIBLE);
            detailTV.setTextColor(context.getResources().getColor(R.color.languagePopupViewBackgroundColor));


        } else if (edtMail.getText().toString().trim().isEmpty()) {
            edtMail.setBackgroundResource(R.drawable.edit_error_outer_line);
            emailError.setVisibility(View.VISIBLE);
            edtMail.requestFocus();
            emailTv.setTextColor(context.getResources().getColor(R.color.languagePopupViewBackgroundColor));
        } else {
            SubmmitClicked();
        }
    }

    private void callContacusApi() {
        if (NetworkStatus.getInstance().isConnected(context)) {
            AdvanceContactUsInput contactUsInput = new AdvanceContactUsInput();
            contactUsInput.setAuthToken(authTokenStr);
            contactUsInput.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));

            advanceContactusAsyncTask = new AdvanceContactusAsyncTask(contactUsInput, this, getContext());
            advanceContactusAsyncTask.execute();
        } else {
            //stopLoader();
            Toast.makeText(context, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
        }
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem  item1, item2, item3;
        item1 = menu.findItem(R.id.option);
        item2 = menu.findItem(R.id.search);
        item1.setVisible(false);
        item2.setVisible(false);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void preAdvanceContactusExecute() {
        pDialog = new ProgressBarHandler(context);
        pDialog.show();
    }

    @Override
    public void postAdvanceContactusExecute(ArrayList<AdvanceContactUsOutput> advanceContactUsOutput, int code, Context context) {
        if (code == 200) {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
            if (advanceContactUsOutput != null && advanceContactUsOutput.size() > 0) {
                Log.e("querylist", advanceContactUsOutput + "");
                advance_layout.setVisibility(View.VISIBLE);
                adapter = new QueriesAdapter(getContext(), advanceContactUsOutput, this);
                queryRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void radioButtonExcute(ArrayList<AdvanceContactUsOutput.SubqueryList> subqueryLists, String parentQuery, Boolean validCheckbox) {
        this.parentQuery = parentQuery;
        this.subQuery = null;
        queryError.setVisibility(View.GONE);
        this.validCheckbox = validCheckbox;
        if (subqueryLists.size() > 0) {
            subquery_ll.setVisibility(View.VISIBLE);
            subAdapter = new SubQueriesAdapter(getContext(), subqueryLists, this);
            sub_query_recycler_view.setAdapter(subAdapter);
            subAdapter.notifyDataSetChanged();
        } else {
            subquery_ll.setVisibility(View.GONE);
        }

        if (subquery_ll.getVisibility() == View.VISIBLE) {
            edtMessage.setBackgroundResource(R.drawable.edit_outer_line);
            otherqueryError.setVisibility(View.GONE);
            queryError.setVisibility(View.GONE);
            detailTV.setTextColor(context.getResources().getColor(R.color.editTextColor));

        }

    }

    @Override
    public void onSupportPreExecuteStarted() {
        pDialog = new ProgressBarHandler(context);
        pDialog.show();
    }

    @Override
    public void onSupportPostExecuteCompleted(SupportOutputModel supportOutputModel, int code, String message, String status) {
        if (code == 200) {
            edtMessage.setText("");
            leaveCurrentPage(supportOutputModel.getSuccess_msg());
        } else {
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
            pDialog = null;
        }
    }


    public void leaveCurrentPage(String message) {
        final Intent startIntent = new Intent(getActivity(), SuccessMessageActivity.class);
        startIntent.putExtra("message", message);
        startIntent.putExtra("name", edtName.getText().toString().trim());
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().startActivity(startIntent);
        getActivity().finish();
        // alert.dismiss();
    }

    public void SubmmitClicked() {

        regEmailStr = edtMail.getText().toString().trim();
        regNameStr = edtName.getText().toString().trim();
        regMessageStr = edtMessage.getText().toString().trim();
        regMessageStr = regMessageStr.replaceAll("(\r\n|\n\r|\r|\n|<br />)", " ");

        boolean isNetwork = NetworkStatus.getInstance().isConnected(context);
        if (isNetwork) {
            if (/*!regNameStr.matches("") &&*/ (!regEmailStr.matches("")) /*&& (!regMessageStr.matches(""))*/) {
                boolean isValidEmail = Util.isValidMail(regEmailStr);
                if (isValidEmail) {
                    if (validate) {
                        SupportInputModel supportInputModel = new SupportInputModel();
                        supportInputModel.setAuthToken(authTokenStr);
                        supportInputModel.setEmail(String.valueOf(regEmailStr));
                        supportInputModel.setName(String.valueOf(regNameStr));
                        supportInputModel.setMessage(String.valueOf(regMessageStr));
                        supportInputModel.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
                        //contactUsInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
                        supportInputModel.setParentQuery(String.valueOf(parentQuery));
                        supportInputModel.setSubQuery(String.valueOf(subQuery));
                        SupportAsynTask asynSupport = new SupportAsynTask(supportInputModel, this, context);
                        asynSupport.execute();

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
    public void onSubqueryExcute(String subQuery, Boolean validCheckbox) {
        this.subQuery = subQuery;
        this.validSubQuery = validCheckbox;
        queryError.setVisibility(View.GONE);
    }


}
