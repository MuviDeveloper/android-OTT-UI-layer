package com.home.vod.ui.activity;

import static com.home.vod.preferences.LanguagePreference.BROWSE;
import static com.home.vod.preferences.LanguagePreference.BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.CONTENT_DESCRIPTION;
import static com.home.vod.preferences.LanguagePreference.CONTENT_NAME;
import static com.home.vod.preferences.LanguagePreference.CONTENT_NAME_REQUIRED;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BROWSE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_BUTTON_OK;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONTENT_DESCRIPTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONTENT_NAME;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONTENT_NAME_REQUIRED;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_EDIT_CATEGORY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_OOPS_SOMETHING_WENT_WRONG;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_REMOVE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SAVE_AND_CONTINUE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECT_A_CATEGORY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECT_CATEGORY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SIZE_OF;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SUCCESS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_UPLOAD_BANNER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_UPLOAD_POSTER;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_UPLOAD_VIDEO_WARNING;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_YES;
import static com.home.vod.preferences.LanguagePreference.EDIT_CATEGORY;
import static com.home.vod.preferences.LanguagePreference.NO;
import static com.home.vod.preferences.LanguagePreference.NO_INTERNET_CONNECTION;
import static com.home.vod.preferences.LanguagePreference.OOPS_SOMETHING_WENT_WRONG;
import static com.home.vod.preferences.LanguagePreference.REMOVE;
import static com.home.vod.preferences.LanguagePreference.SAVE_AND_CONTINUE;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SELECT_A_CATEGORY;
import static com.home.vod.preferences.LanguagePreference.SELECT_CATEGORY;
import static com.home.vod.preferences.LanguagePreference.SIZE_OF;
import static com.home.vod.preferences.LanguagePreference.SUCCESS;
import static com.home.vod.preferences.LanguagePreference.UPLOAD_BANNER;
import static com.home.vod.preferences.LanguagePreference.UPLOAD_POSTER;
import static com.home.vod.preferences.LanguagePreference.UPLOAD_VIDEO_WARNING;
import static com.home.vod.preferences.LanguagePreference.YES;
import static com.home.vod.preferences.PreferenceManager.BANNER_HEIGHT;
import static com.home.vod.preferences.PreferenceManager.BANNER_WIDTH;
import static com.home.vod.preferences.PreferenceManager.POSTER_HEIGHT;
import static com.home.vod.preferences.PreferenceManager.POSTER_WIDTH;
import static com.home.vod.util.Constant.authTokenStr;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.home.apisdk.apiController.UpdateContentAsyncTask;
import com.home.apisdk.apiController.UploadContentAsyncTask;
import com.home.apisdk.apiModel.CategoryOutputModel;
import com.home.apisdk.apiModel.UploadContentInputModel;
import com.home.vod.R;
import com.home.vod.handlers.ToolbarTitleHandler;
import com.home.vod.helper.LocaleLanguageHelper;
import com.home.vod.network.NetworkStatus;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.widgets.ProgressBarHandler;
import com.home.vod.util.DataController;
import com.home.vod.util.FeatureHandler;
import com.home.vod.util.KeyboardUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class UploadContentActivity extends AppCompatActivity implements
        View.OnClickListener, View.OnTouchListener, AdapterView.OnItemSelectedListener, UploadContentAsyncTask.UploadContentListener, UpdateContentAsyncTask.UpdateContentListener {


    public Toolbar mActionBarToolbar;
    public ToolbarTitleHandler toolbarTitleHandler;
    PreferenceManager preferenceManager;
    LanguagePreference languagePreference;
    FeatureHandler featureHandler;
    ProgressBarHandler pDialog;
    private static final int MY_PERMISSIONS_REQUEST_CALL = 100;
    private String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private Button browsePoster, browseBanner, removePoster, removeBanner, save;

    private EditText contentName, contentDescription;
    private ImageView imageViewPoster, imageViewBanner;

    public static ArrayList<CategoryOutputModel> categoryOutputModelArrayList;
    private String categoryID = "", default_Language = "", posterFilePath = null, bannerFilePath = null, selectedCategorieIDs = "";
    private RelativeLayout offlineContentLayout;
    private static final int SELECT_REQUEST_POSTER = 6, SELECT_REQUEST_BANNER = 7, CROP_REQUEST_POSTER = 8, CROP_REQUEST_BANNER = 9, SELECT_CATEGORIES = 10, REQUEST_CODE_FOR_WEBVIEW = 101;
    private TextView uploadPosterTv, uploadBannerTv, selectedCategoryTv, selectCategoryTv;
    public static Bitmap posterBitmap, bannerBitMap;
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
        setContentView(R.layout.activity_upload_content);

        View focusedView =this.getCurrentFocus();
        if(focusedView!=null) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        }
        languagePreference = LanguagePreference.getLanguagePreference(this);
        preferenceManager = PreferenceManager.getPreferenceManager(this);
        featureHandler = FeatureHandler.getFeaturePreference(UploadContentActivity.this);
        default_Language = languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        /*Set Toolbar*/
        toolbarTitleHandler = new ToolbarTitleHandler(this);
        mActionBarToolbar.setTitle(getIntent().getStringExtra("sectionName"));
        mActionBarToolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        setSupportActionBar(mActionBarToolbar);
        /*
         * @Desc: This for setting up the toolbar back arrow icon change
         * */
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Configuration config = getResources().getConfiguration();
            if(config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                //in Right To Left layout
                mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_right));
            }else {
                mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
            }

        }else {
            mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        }
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidekeyboard();
                onBackPressed();
            }
        });
        setIdToActionBarBackButton(mActionBarToolbar);
        if (ActivityCompat.checkSelfPermission(UploadContentActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ((Build.VERSION.SDK_INT <= 18) ? (ActivityCompat.checkSelfPermission(UploadContentActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) : false))
            requestPermission();


        //find view id

        selectCategoryTv = findViewById(R.id.category_dropdown);
        browsePoster = findViewById(R.id.browse_poster);
        browseBanner = findViewById(R.id.browse_banner);
        removePoster = findViewById(R.id.remove_poster);
        removeBanner = findViewById(R.id.remove_banner);
        save = findViewById(R.id.save_continue);
        selectedCategoryTv = findViewById(R.id.selected_category_tv);
        offlineContentLayout = findViewById(R.id.offline_parentLayout);
        contentName = findViewById(R.id.content_name_field);
        contentDescription = findViewById(R.id.description);
        //categoryTv = findViewById(R.id.offline_category_title_tv);

        uploadPosterTv = findViewById(R.id.upload_poster);
        uploadBannerTv = findViewById(R.id.upload_banner);
        imageViewPoster = findViewById(R.id.offline_Poster_img);
        imageViewBanner = findViewById(R.id.offline_banner_img);


        //set onclick listner to buttons
        browsePoster.setOnClickListener(this);
        browseBanner.setOnClickListener(this);
        removePoster.setOnClickListener(this);
        removeBanner.setOnClickListener(this);
        selectCategoryTv.setOnClickListener(this);
        save.setOnClickListener(this);

        //set language

        contentName.setHint(languagePreference.getTextofLanguage(CONTENT_NAME, DEFAULT_CONTENT_NAME));
        contentDescription.setHint(languagePreference.getTextofLanguage(CONTENT_DESCRIPTION, DEFAULT_CONTENT_DESCRIPTION));
        selectCategoryTv.setText(languagePreference.getTextofLanguage(SELECT_CATEGORY, DEFAULT_SELECT_CATEGORY));
       // uploadPosterTv.setText(languagePreference.getTextofLanguage(UPLOAD_POSTER, DEFAULT_UPLOAD_POSTER) + " :");
       // uploadBannerTv.setText(languagePreference.getTextofLanguage(UPLOAD_BANNER, DEFAULT_UPLOAD_BANNER) + " :");
        String posterSizeText = languagePreference.getTextofLanguage(UPLOAD_POSTER, DEFAULT_UPLOAD_POSTER) +" ("+languagePreference.getTextofLanguage(SIZE_OF,DEFAULT_SIZE_OF) +" "+ preferenceManager.getPosterWidth(POSTER_WIDTH)+

                "*"+ preferenceManager.getPosterHeight(POSTER_HEIGHT)+") : ";


        String bannerSizeText = languagePreference.getTextofLanguage(UPLOAD_BANNER, DEFAULT_UPLOAD_BANNER) +" ("+languagePreference.getTextofLanguage(SIZE_OF,DEFAULT_SIZE_OF)+" "+ preferenceManager.getPosterWidth(BANNER_WIDTH)+

                "*"+ preferenceManager.getPosterHeight(BANNER_HEIGHT)+") : ";
        uploadPosterTv.setText(posterSizeText);
        uploadBannerTv.setText(bannerSizeText);
        browsePoster.setText(languagePreference.getTextofLanguage(BROWSE, DEFAULT_BROWSE));
        browseBanner.setText(languagePreference.getTextofLanguage(BROWSE, DEFAULT_BROWSE));
        removePoster.setText(languagePreference.getTextofLanguage(REMOVE, DEFAULT_REMOVE));
        removeBanner.setText(languagePreference.getTextofLanguage(REMOVE, DEFAULT_REMOVE));
        save.setText(languagePreference.getTextofLanguage(SAVE_AND_CONTINUE, DEFAULT_SAVE_AND_CONTINUE));

        //fetch category list
        categoryOutputModelArrayList = new ArrayList<CategoryOutputModel>();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("category_bundle");

        categoryOutputModelArrayList = (ArrayList<CategoryOutputModel>) args.getSerializable("category_list");

        contentDescription.setOnTouchListener(this);
        // contentName.setOnTouchListener(this);

        if (getIntent().getStringExtra("upload_type").equalsIgnoreCase("edit")) {
            initUpdateMetaData();
        }
        findViewById(android.R.id.content).getRootView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {

                KeyboardUtils.addKeyboardToggleListener(UploadContentActivity.this, new KeyboardUtils.SoftKeyboardToggleListener() {
                    @Override
                    public void onToggleSoftKeyboard(boolean isVisible) {
                        if (isVisible) {
                            save.setVisibility(View.GONE);
                        } else {
                            save.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });

    }

    /*
   * Description: This method helps to hide the keyboard.
   * **/
    private void hidekeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    /*
  * @author Subhadarshani
  * Description: This method helps prefetch the metadata for edit content.
  * **/
    private void initUpdateMetaData() {


        contentName.setText(getIntent().getStringExtra("content_name"));
        if (getIntent().getStringExtra("story") != null) {
            contentDescription.setText(getIntent().getStringExtra("story"));
        }
        if (getIntent().getStringExtra("poster_url") != null && getIntent().getStringExtra("poster_url").length() > 0) {
            String posterUrl = getIntent().getStringExtra("poster_url");
            Picasso.with(UploadContentActivity.this)
                    .load(posterUrl).error(R.drawable.no_image).placeholder(R.drawable.ic_poster_img)
                    .into(imageViewPoster);
        }
        if (getIntent().getStringExtra("banner_url") != null && getIntent().getStringExtra("banner_url").length() > 0) {
            String bannerUrl = getIntent().getStringExtra("banner_url");
            Picasso.with(UploadContentActivity.this)
                    .load(bannerUrl).error(R.drawable.no_image).placeholder(R.drawable.ic_banner_img)
                    .into(imageViewBanner);
        }
        if (getIntent().getStringExtra("categories") != null) {
            String selectedCategories = getIntent().getStringExtra("categories");
            selectedCategoryTv.setVisibility(View.VISIBLE);
            // selectedCategoryTv.setText();
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder categoryIdStringBuilder = new StringBuilder();

            for (int i = 0; i < categoryOutputModelArrayList.size(); i++) {
                if (selectedCategories.contains(categoryOutputModelArrayList.get(i).getCategoryId())) {
                    categoryOutputModelArrayList.get(i).setSelected(true);
                    stringBuilder.append(categoryOutputModelArrayList.get(i).getCategoryName() + ", ");
                    categoryIdStringBuilder.append(categoryOutputModelArrayList.get(i).getCategoryId() + ",");
                }

            }
            String resultTedString = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 2);
            selectedCategorieIDs = categoryIdStringBuilder.toString().substring(0, categoryIdStringBuilder.toString().length() - 1);
            selectedCategoryTv.setText(resultTedString);
            selectCategoryTv.setText(languagePreference.getTextofLanguage(EDIT_CATEGORY, DEFAULT_EDIT_CATEGORY));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    /*
       * @author Subhadarshani
       * Description: This method helps for runtime permission
       * **/
    public void requestPermission() {
        try {
            ActivityCompat.requestPermissions(this,
                    PERMISSIONS,
                    MY_PERMISSIONS_REQUEST_CALL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Rameswar
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {

            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_CALL:

                    if (grantResults.length >= 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && ((Build.VERSION.SDK_INT <= 18) ? grantResults[1] == PackageManager.PERMISSION_GRANTED : true)) {
                        permissionGranted();

                    }

                    break;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
       * @author Subhadarshani
       * Description: This method helps prefetch the metadata for edit content.
       * **/
    public void checkPermission() {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                if (ActivityCompat.checkSelfPermission(UploadContentActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                        ((Build.VERSION.SDK_INT <= 18) ? (ActivityCompat.checkSelfPermission(UploadContentActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) : true)
                        ||
                        ActivityCompat.checkSelfPermission(UploadContentActivity.this, Manifest.permission.READ_PHONE_STATE)
                                != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == SELECT_REQUEST_POSTER && resultCode == Activity.RESULT_OK && data != null) {
                try {

                    Uri tempUri = data.getData();
                    Intent in = new Intent(UploadContentActivity.this, CropActivity.class);
                    in.putExtra("imageUri", tempUri);
                    in.putExtra("intent_type", "poster");
                    in.putExtra("image_width", preferenceManager.getPosterWidth(POSTER_WIDTH));
                    in.putExtra("image_height", preferenceManager.getPosterWidth(POSTER_HEIGHT));
                    startActivityForResult(in, CROP_REQUEST_POSTER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_REQUEST_BANNER && resultCode == Activity.RESULT_OK && data != null) {
                try {
                    Uri tempUri = data.getData();
                    Intent in = new Intent(UploadContentActivity.this, CropActivity.class);
                    in.putExtra("imageUri", tempUri);
                    in.putExtra("intent_type", "banner");
                    in.putExtra("image_width", preferenceManager.getPosterWidth(BANNER_WIDTH));
                    in.putExtra("image_height", preferenceManager.getPosterWidth(BANNER_HEIGHT));
                    startActivityForResult(in, CROP_REQUEST_BANNER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CROP_REQUEST_POSTER && resultCode == Activity.RESULT_OK && data != null) {
                if (data != null) {
                    if (posterBitmap != null) {
                        setPosterPic(posterBitmap);
                    }
                    posterBitmap = createScaleBitmap(posterBitmap, preferenceManager.getPosterWidth(POSTER_WIDTH), preferenceManager.getPosterWidth(POSTER_HEIGHT));
                    if (posterBitmap != null)
                        posterFilePath = createNewImageFile(posterBitmap);
                }
            } else if (requestCode == CROP_REQUEST_BANNER && resultCode == Activity.RESULT_OK && data != null) {
                if (data != null) {
                    if (bannerBitMap != null) {
                        setBannerPic(bannerBitMap);
                    }
                    bannerBitMap = createScaleBitmap(bannerBitMap, preferenceManager.getPosterWidth(BANNER_WIDTH), preferenceManager.getPosterWidth(BANNER_HEIGHT));
                    if (bannerBitMap != null)
                        bannerFilePath = createNewImageFile(bannerBitMap);
                }
            } else if (requestCode == SELECT_CATEGORIES && resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    String selectedCategories = data.getStringExtra("SelectedCategories");
                    String selectedIds = data.getStringExtra("SelectedCategorieIds");
                    selectedCategorieIDs = selectedIds.toString().substring(0, selectedIds.toString().length() - 1);
                    if (selectedCategories.length() > 0) {
                        selectCategoryTv.setText(languagePreference.getTextofLanguage(EDIT_CATEGORY, DEFAULT_EDIT_CATEGORY));
                        String resulttedString = selectedCategories.substring(0, selectedCategories.length() - 2);
                        selectedCategoryTv.setVisibility(View.VISIBLE);
                        selectedCategoryTv.setText(resulttedString);
                    } else {
                        //  selectedCategoryTv.setText(selectedCategories);
                        selectCategoryTv.setText(languagePreference.getTextofLanguage(SELECT_CATEGORY, DEFAULT_SELECT_CATEGORY));
                        selectedCategoryTv.setVisibility(View.GONE);
                    }
                }


            }

        } catch (Exception e) {
            Log.v("", "" + e.getMessage());
        }

    }

    private String createNewImageFile(Bitmap posterBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        posterBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        //you can create a new file name "test.jpg" in sdcard folder.
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator + "" + System.currentTimeMillis() + ".jpg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            //write the bytes in file
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());

            // remember close de FileOutput
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file.getPath();
    }

    private void permissionGranted() {
        //Nothing to Do
    }

    /*
        Kushal- To set id to back button in Action Bar
    */
    private void setIdToActionBarBackButton(Toolbar mActionBarToolbar) {
        for (int i = 0; i < mActionBarToolbar.getChildCount(); i++) {
            View v = mActionBarToolbar.getChildAt(i);
            if (v instanceof ImageButton) {
                ImageButton b = (ImageButton) v;
                b.setId(R.id.back);
                /*try {
                    if (b.getContentDescription().equals("Open")) {
                        b.setId(R.id.drawer_menu);
                    } else {
                        b.setId(R.id.back_btn);
                    }
                }catch (Exception e){
                    b.setId(R.id.back_btn);
                }*/
            } else if (v instanceof TextView) {
                TextView t = (TextView) v;
                t.setId(R.id.page_title_upload_content);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.browse_poster:
                hideFocus();
                hidekeyboard();
                chosePoster();
                break;
            case R.id.browse_banner:
                hideFocus();
                hidekeyboard();
                choseBanner();
                break;

            case R.id.remove_poster:
                hideFocus();
                hidekeyboard();
                posterFilePath = null;
                imageViewPoster.setImageResource(R.drawable.ic_poster_img);
                break;
            case R.id.remove_banner:
                hideFocus();
                hidekeyboard();
                bannerFilePath = null;
                imageViewBanner.setImageResource(R.drawable.ic_banner_img);
                break;
            case R.id.save_continue:
                hidekeyboard();
                if (contentName.getText().toString().trim().length() == 0) {
                    Toast.makeText(UploadContentActivity.this, languagePreference.getTextofLanguage(CONTENT_NAME_REQUIRED, DEFAULT_CONTENT_NAME_REQUIRED), Toast.LENGTH_LONG).show();
                } else if (selectedCategoryTv.getText().toString().length() == 0) {
                    Toast.makeText(UploadContentActivity.this, languagePreference.getTextofLanguage(SELECT_A_CATEGORY, DEFAULT_SELECT_A_CATEGORY), Toast.LENGTH_LONG).show();
                } else {
                    if (getIntent().getStringExtra("upload_type").equalsIgnoreCase("edit")) {

                        if (NetworkStatus.getInstance().isConnected(UploadContentActivity.this)) {
                            updateOfflineContent();
                        } else {
                            Toast.makeText(UploadContentActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (NetworkStatus.getInstance().isConnected(UploadContentActivity.this)) {
                            uploadOfflineContent();
                        } else {
                            Toast.makeText(UploadContentActivity.this, languagePreference.getTextofLanguage(NO_INTERNET_CONNECTION, DEFAULT_NO_INTERNET_CONNECTION), Toast.LENGTH_LONG).show();
                        }
                    }

                }


                break;
            case R.id.category_dropdown:
                hidekeyboard();
                Intent intent = new Intent(UploadContentActivity.this, CategoryActivity.class);
                startActivityForResult(intent, SELECT_CATEGORIES);

            default:
                break;
        }
    }

    /*
       * @author Subhadarshani
       * Description: This method helps to prepare all the data which will be required as request parameter for updating metadata API.
       * **/
    private void updateOfflineContent() {
        UploadContentInputModel uploadContentInputModel = new UploadContentInputModel();
        uploadContentInputModel.setAuthToken(authTokenStr);
        uploadContentInputModel.setLangCode(default_Language);
        uploadContentInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
        uploadContentInputModel.setContent_name(this.contentName.getText().toString());
        uploadContentInputModel.setPermalink("movie");
        if (this.contentDescription.getText().toString() == null) {
            uploadContentInputModel.setDescription("");
        } else {
            uploadContentInputModel.setDescription(this.contentDescription.getText().toString());

        }
        if (posterFilePath == null) {
            uploadContentInputModel.setPoster_image("");

        } else {
            uploadContentInputModel.setPoster_image(posterFilePath);
        }
        if (bannerFilePath == null) {
            uploadContentInputModel.setBanner_image("");
        } else {
            uploadContentInputModel.setBanner_image(bannerFilePath);
        }

        uploadContentInputModel.setUploaded_content_type("0");
        uploadContentInputModel.setCategory_id(selectedCategorieIDs);
        uploadContentInputModel.setUser_id(preferenceManager.getUseridFromPref());
        uploadContentInputModel.setMuviUniqueId(getIntent().getStringExtra("movie_unique_id"));

        UpdateContentAsyncTask updateContentAsyncTask = new UpdateContentAsyncTask(uploadContentInputModel, this, UploadContentActivity.this, new DataController(this));
        updateContentAsyncTask.execute();
    }

    /*
   * @author Subhadarshani
   * Description: This method helps to prepare all the data which will be required as request parameter for uploading metadata API.
   * **/
    private void uploadOfflineContent() {
        UploadContentInputModel uploadContentInputModel = new UploadContentInputModel();
        uploadContentInputModel.setAuthToken(authTokenStr);
        uploadContentInputModel.setLangCode(default_Language);
        uploadContentInputModel.setCountryCode(preferenceManager.getCountryCodeFromPref());
        //  uploadContentInputModel.setCategory_id(selectedCategoryTv.getText().toString());
        uploadContentInputModel.setContent_name(this.contentName.getText().toString());
        uploadContentInputModel.setPermalink("movie");
        if (this.contentDescription.getText().toString() == null) {
            uploadContentInputModel.setDescription("");
        } else {
            uploadContentInputModel.setDescription(this.contentDescription.getText().toString());

        }
        if (posterFilePath == null) {
            uploadContentInputModel.setPoster_image("");

        } else {
            uploadContentInputModel.setPoster_image(posterFilePath);
        }
        if (bannerFilePath == null) {
            uploadContentInputModel.setBanner_image("");
        } else {
            uploadContentInputModel.setBanner_image(bannerFilePath);
        }

        uploadContentInputModel.setUploaded_content_type("0");
        uploadContentInputModel.setUser_id(preferenceManager.getUseridFromPref());

        //  String resultTedString = selectedCategorieIDs.toString().substring(0, selectedCategorieIDs.length() - 1);
        uploadContentInputModel.setUploaded_content_type("0");
        uploadContentInputModel.setCategory_id(selectedCategorieIDs);
        UploadContentAsyncTask uploadContentAsyncTask = new UploadContentAsyncTask(uploadContentInputModel, this, UploadContentActivity.this, new DataController(UploadContentActivity.this));
        uploadContentAsyncTask.execute();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        TextView tv = view.findViewById(android.R.id.text1);
        if (tv.getTag() != null) {
            if (tv.getTag().toString() != null) {
                categoryID = tv.getTag().toString();
            }
        } else {
            categoryID = null;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onUploadCoontentPreExecuteStarted() {
        pDialog = new ProgressBarHandler(UploadContentActivity.this);
        pDialog.show();
    }

    @Override
    public void onUploadContentPostExecuteCompleted(String response, int status, String message) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }
        if (status == 200) {

            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("upload_video_path") != null) {
                    String videoPath = jsonObject.getString("upload_video_path");
                    showConfirmDialogForUploadingVideo(videoPath);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (status == 0) {
            Toast.makeText(UploadContentActivity.this, languagePreference.getTextofLanguage(OOPS_SOMETHING_WENT_WRONG, DEFAULT_OOPS_SOMETHING_WENT_WRONG), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpdateCoontentPreExecuteStarted() {
        pDialog = new ProgressBarHandler(UploadContentActivity.this);
        pDialog.show();
    }

    @Override
    public void onUpdateContentPostExecuteCompleted(int status, String message) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.hide();
        }
        if (status == 200) {
            showPopUpForUpdateContent(message);
        } else {
            Toast.makeText(UploadContentActivity.this, languagePreference.getTextofLanguage(OOPS_SOMETHING_WENT_WRONG, DEFAULT_OOPS_SOMETHING_WENT_WRONG), Toast.LENGTH_LONG).show();
        }

    }

    /*
   * @author Subhadarshani
   * Description: This method helps toremove the focus from layout.
   * **/
    public void hideFocus() {
        offlineContentLayout.requestFocus();
    }

    /*
     * @author Subhadarshani
     * Description: This method helps to select image for poster from gallery.
     * **/
    private void chosePoster() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_REQUEST_POSTER);
    }

    /*
     * @author Subhadarshani
     * Description: This method helps to select image for banner from gallery.
     * **/
    private void choseBanner() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_REQUEST_BANNER);
    }

    /*
     * @author Subhadarshani
     * Description: This method helps to set poster image in bitmap
     * **/
    private void setPosterPic(Bitmap bitmap) {
        imageViewPoster.setImageBitmap(bitmap);
    }

    /*
    * @author Subhadarshani
    * Description: This method helps to set banner image in bitmap
    * **/
    private void setBannerPic(Bitmap bitmap) {
        imageViewBanner.setImageBitmap(bitmap);
    }

    /*
  * @author Subhadarshani
  * Description: This method helps to show pop-up for uploading video.
  * **/
    private void showConfirmDialogForUploadingVideo(final String response) {
       AlertDialog.Builder dlgAlert = new AlertDialog.Builder(UploadContentActivity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(languagePreference.getTextofLanguage(UPLOAD_VIDEO_WARNING, DEFAULT_UPLOAD_VIDEO_WARNING));
        dlgAlert.setTitle(languagePreference.getTextofLanguage(SUCCESS, DEFAULT_SUCCESS));

        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(YES, DEFAULT_YES), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

                Intent in = new Intent(UploadContentActivity.this, UploadVideoWebViewActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                in.putExtra("upload_video_path", response);
                startActivity(in);
                finish();
            }
        });

        dlgAlert.setNegativeButton(languagePreference.getTextofLanguage(NO, DEFAULT_NO), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
                setResult(Activity.RESULT_OK);
                finish();

            }
        });
        // dlgAlert.setPositiveButton(getResources().getString(R.string.yes_str), null);
        dlgAlert.setCancelable(false);
        dlgAlert.create().show();

    }

    /*
     * @author Subhadarshani
     * Description: This method helps to show pop-up for updating content.
     * **/
    public void showPopUpForUpdateContent(String message) {
       AlertDialog.Builder dlgAlert = new AlertDialog.Builder(UploadContentActivity.this, R.style.MyAlertDialogStyle);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(languagePreference.getTextofLanguage(SUCCESS, DEFAULT_SUCCESS));
        dlgAlert.setPositiveButton(languagePreference.getTextofLanguage(BUTTON_OK, DEFAULT_BUTTON_OK), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                dialog.cancel();
                setResult(RESULT_OK);
                finish();
            }
        });

        dlgAlert.setCancelable(false);

        dlgAlert.create().show();
    }

    /*
    * @author Subhadarshani
    * Description: This method helps to scale the bitmap in required width and height
    * **/
    public Bitmap createScaleBitmap(Bitmap bitmap, int wantedWidth, int wantedHeight) {
        Bitmap output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) wantedWidth / bitmap.getWidth(), (float) wantedHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());

        return output;
    }

   /* public String generateBase64(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, 0);
        return encoded;
    }
*/

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        if (v.getId() == R.id.description) {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.v("", "" + event);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v("", "" + event);
        return super.onKeyDown(keyCode, event);
    }
}


