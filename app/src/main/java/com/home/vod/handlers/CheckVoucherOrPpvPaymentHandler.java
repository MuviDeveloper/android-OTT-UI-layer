package com.home.vod.handlers;

import android.app.Activity;

/**
 * Created by BISHAL on 16-11-2017.
 */

public class CheckVoucherOrPpvPaymentHandler {
    Activity activity;
    public CheckVoucherOrPpvPaymentHandler(Activity activity){
        this.activity=activity;
    }
    public void handleVoucherPaymentOrPpvPayment(){
       /* try {
            if (activity instanceof MovieDetailsActivity)
              //  ((MovieDetailsActivity) activity).getMonitizationDetailsApi();
            if (activity instanceof ShowWithEpisodesActivity)
                ((ShowWithEpisodesActivity) activity).getMonitizationDetailsApi();
            if (activity instanceof SeasonDetailsActivity)
                ((SeasonDetailsActivity) activity).getMonitizationDetailsApi();
               // ((ShowWithEpisodesActivity) activity).getMonitizationDetailsApi();
            if (activity instanceof Episode_list_Activity)
              //  ((Episode_list_Activity) activity).getMonitizationDetailsApi();
           if (activity instanceof AudioContentDetailsActivity)
              //  ((AudioContentDetailsActivity) activity).getMonitizationDetailsApi();

        } catch (ClassCastException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
