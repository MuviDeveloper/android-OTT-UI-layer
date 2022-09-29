package com.home.vod.handlers;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.mediarouter.app.MediaRouteButton;

/**
 * Created by MUVI on 10/20/2017.
 */

public class HandleOfflineInExoplayer {
    private Activity context;
    public HandleOfflineInExoplayer(Activity context){
        this.context=context;
    }
    public void handleVisibelUnvisibleDownload(RelativeLayout download_layout){

        download_layout.setVisibility(View.VISIBLE);
    }
    public void handleVisibleUnvisibleChromcast(MediaRouteButton mediaRouteButton){
        mediaRouteButton.setVisibility(View.VISIBLE);
    }
}
