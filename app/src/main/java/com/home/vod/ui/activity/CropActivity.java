package com.home.vod.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.home.vod.R;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CropActivity extends AppCompatActivity {

    public Toolbar mActionBarToolbar;
    private CropImageView cropImageView;
    private Uri uri;

    private int width, height;
    private String intentType;

    private PreferenceManager preferenceManager;
    private LanguagePreference languagePreference;
    public ToolbarTitleHandler toolbarTitleHandler;

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
        setContentView(R.layout.activity_crop);
        mActionBarToolbar = findViewById(R.id.toolbar);
        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setTitle("");
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        setSupportActionBar(mActionBarToolbar);
        /*
         * @Author: Biswa
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
                onBackPressed();
            }
        });
        setIdToActionBarBackButton(mActionBarToolbar);
        languagePreference = LanguagePreference.getLanguagePreference(this);
        cropImageView = findViewById(R.id.cropImageView);
        uri = getIntent().getParcelableExtra("imageUri");
        width = getIntent().getIntExtra("image_width", 0);
        height = getIntent().getIntExtra("image_height", 0);
        intentType = getIntent().getStringExtra("intent_type");
        cropImageView.setAspectRatio(width, height);
        cropImageView.setScaleType(CropImageView.ScaleType.FIT_CENTER);
        cropImageView.setAutoZoomEnabled(true);
        cropImageView.setShowProgressBar(true);
        // cropImageView.setMinCropResultSize(width,height);
        cropImageView.setImageUriAsync(uri);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.crop_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_crop:

                Bitmap cropped = cropImageView.getCroppedImage();
                if (intentType.equalsIgnoreCase("poster")) {
                    UploadContentActivity.posterBitmap = cropped;
                } else if (intentType.equalsIgnoreCase("banner")) {
                    UploadContentActivity.bannerBitMap = cropped;
                }

                Intent in = new Intent(CropActivity.this, UploadContentActivity.class);
                setResult(Activity.RESULT_OK, in);
                finish();
                break;
            case R.id.action_cancel:
                finish();

        }
        return super.onOptionsItemSelected(item);
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
                t.setId(R.id.page_title_crop);
            }
        }
    }
}
