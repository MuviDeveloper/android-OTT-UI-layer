package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.BUTTON_RESET;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_APPLY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_RESET;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_FILTER_BY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECT_A_CATEGORY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECT_CATEGORY;
import static com.home.vod.preferences.LanguagePreference.FILTER_BY;
import static com.home.vod.preferences.LanguagePreference.SELECT_A_CATEGORY;
import static com.home.vod.preferences.LanguagePreference.SELECT_CATEGORY;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.home.vod.R;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.ui.adapter.UGCFilterAdapter;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    public Toolbar mActionBarToolbar;
    public ToolbarTitleHandler toolbarTitleHandler;
    public ImageView toolbarimage;
    private RecyclerView categoryRecyclerView;
    private Button resetButton, applyButton;
    private LanguagePreference languagePreference;
    private UGCFilterAdapter ugcFilterAdapter;
    private String buttonType = "";

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
        setContentView(R.layout.activity_category);
        languagePreference = LanguagePreference.getLanguagePreference(this);
        toolbarimage = (ImageView) findViewById(R.id.toolbarimage);
        mActionBarToolbar = findViewById(R.id.toolbar);

        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setTitle(languagePreference.getTextofLanguage(SELECT_CATEGORY, DEFAULT_SELECT_CATEGORY));
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        setSupportActionBar(mActionBarToolbar);
        /*
         * @Desc: This for setting up the toolbar back arrow icon change
         * */
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
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonType.equalsIgnoreCase("reset")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < UploadContentActivity.categoryOutputModelArrayList.size(); i++) {
                        if (UploadContentActivity.categoryOutputModelArrayList.get(i).isSelected()) {
                            stringBuilder.append(UploadContentActivity.categoryOutputModelArrayList.get(i).getCategoryName() + ",");

                        }
                    }
                    Intent in = new Intent(CategoryActivity.this, UploadContentActivity.class);
                    in.putExtra("SelectedCategories", stringBuilder.toString());
                    setResult(RESULT_OK, in);
                    finish();
                } else {
                    finish();
                }


            }
        });

        //  To set Id to action bar back button
        setIdToActionBarBackButton(mActionBarToolbar);
        resetButton = findViewById(R.id.reset);
        applyButton = findViewById(R.id.apply);
        categoryRecyclerView = findViewById(R.id.demoListView);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        categoryRecyclerView.setLayoutManager(linearLayout);
        categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());

        applyButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        resetButton.setText(languagePreference.getTextofLanguage(BUTTON_RESET, DEFAULT_BUTTON_RESET));
        applyButton.setText(languagePreference.getTextofLanguage(BUTTON_APPLY, DEFAULT_BUTTON_APPLY));
        populateCategory();
    }

    private void populateCategory() {
        categoryRecyclerView.setAdapter(null);
        ugcFilterAdapter = new UGCFilterAdapter(UploadContentActivity.categoryOutputModelArrayList, this);
        categoryRecyclerView.setAdapter(ugcFilterAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.apply:
                buttonType = "apply";
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder categoryIDStringBuilder = new StringBuilder();
                for (int i = 0; i < UploadContentActivity.categoryOutputModelArrayList.size(); i++) {
                    if (UploadContentActivity.categoryOutputModelArrayList.get(i).isSelected()) {
                        stringBuilder.append(UploadContentActivity.categoryOutputModelArrayList.get(i).getCategoryName() + ", ");
                        categoryIDStringBuilder.append(UploadContentActivity.categoryOutputModelArrayList.get(i).getCategoryId() + ",");

                    }
                }
                if (stringBuilder.toString().length() == 0) {
                    Toast.makeText(CategoryActivity.this, languagePreference.getTextofLanguage(SELECT_A_CATEGORY, DEFAULT_SELECT_A_CATEGORY), Toast.LENGTH_LONG).show();
                } else {
                    Intent in = new Intent(CategoryActivity.this, UploadContentActivity.class);
                    in.putExtra("SelectedCategories", stringBuilder.toString());
                    in.putExtra("SelectedCategorieIds", categoryIDStringBuilder.toString());

                    setResult(RESULT_OK, in);
                    finish();
                }

                break;
            case R.id.reset:
                buttonType = "reset";
                for (int i = 0; i < UploadContentActivity.categoryOutputModelArrayList.size(); i++) {
                    UploadContentActivity.categoryOutputModelArrayList.get(i).setSelected(false);
                }
                populateCategory();
                break;
        }
    }

    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.back);

            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                if (t.getText().toString().contains(languagePreference.getTextofLanguage(FILTER_BY, DEFAULT_FILTER_BY))) {
                    t.setId(R.id.page_title_filter);
                }
            }
        }
    }
}
