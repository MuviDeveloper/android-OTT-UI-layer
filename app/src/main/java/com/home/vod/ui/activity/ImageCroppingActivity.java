package com.home.vod.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.home.vod.R;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;

public class ImageCroppingActivity extends AppCompatActivity implements CropImageView.OnCropImageCompleteListener {

    public static final String EXTRA_PROFILE_PATH = "extra_profile_path";
    private CropImageView mCropView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cropping);

        mCropView = findViewById(R.id.crop_image_view);
        mCropView.setAspectRatio(1, 1);
        mCropView.setOnCropImageCompleteListener(this);

        String uri = getIntent().getStringExtra("URI");
        mCropView.setImageUriAsync(Uri.fromFile(new File(uri)));
    }

    File dir;

    public void done(View view) {
        dir = new File(getFilesDir(), "profile");
        if (!dir.exists()) {
            dir.mkdir();
        }

        mCropView.getCroppedImageAsync();
    }

    @Override
    public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {

        String filename = "profile.png";
        File dest = new File(dir, filename);

        Bitmap bitmap = getResizedBitmap(result.getBitmap(), 500);
        try {
            FileOutputStream out = new FileOutputStream(dest);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_PROFILE_PATH, dest.getAbsolutePath());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}