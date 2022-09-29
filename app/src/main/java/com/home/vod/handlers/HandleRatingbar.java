package com.home.vod.handlers;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by MUVI on 10/20/2017.
 */

public class HandleRatingbar {
    private Activity context;
    String rating;
    public HandleRatingbar(Activity context){
        this.context=context;
    }
    public void handleVisibleUnvisibleRating(RatingBar ratingBar){
        ratingBar.setVisibility(View.VISIBLE);

    }
    public void handleVisibleUnvisibleRatingTextView(TextView viewRatingTextView){

        /**
         * @author Debashish
         * @description Commented as it is not required in new UI
         */
       // viewRatingTextView.setVisibility(View.VISIBLE);

    }
    public void handleVisibleUnvisibleFavicon(ImageView favorite_view){
        favorite_view.setVisibility(View.VISIBLE);
    }
}
