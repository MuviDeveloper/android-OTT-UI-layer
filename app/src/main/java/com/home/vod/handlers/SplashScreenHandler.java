package com.home.vod.handlers;

import android.widget.ImageView;

import com.home.vod.util.FeatureHandler;


/**
 * Created by Android on 12/19/2017.
 */

public class SplashScreenHandler {


    public SplashScreenHandler() {
    }

    public void handleSplashscreen(ImageView imageResize) {
        imageResize.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    public void changeFeatureProperties(FeatureHandler featureHandler) {
        featureHandler.setFeatureFlag(FeatureHandler.APP_LOGO_ON_PLAYERPAGE_ENABLED, "1");
    }

}
