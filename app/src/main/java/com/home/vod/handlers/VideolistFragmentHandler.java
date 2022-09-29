package com.home.vod.handlers;

import android.app.Activity;

import com.home.apisdk.APIController;
import com.home.vod.util.FeatureHandler;

/**
 * Created by MUVI on 10/27/2017.
 */

public class VideolistFragmentHandler {
    private Activity context;
    FeatureHandler featureHandler;
    APIController apiController;

    public VideolistFragmentHandler(Activity context){
        this.context=context;
        featureHandler = FeatureHandler.getFeaturePreference(context);

    }


}
