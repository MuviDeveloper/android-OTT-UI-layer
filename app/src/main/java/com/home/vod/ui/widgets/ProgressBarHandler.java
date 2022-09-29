package com.home.vod.ui.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.home.vod.R;


/**
 * Created by User on 10-02-2017.
 */
public class ProgressBarHandler extends Dialog {
    Context context;

    public ProgressBarHandler(Context mContext) {

        /*
        * @Desc: ProgressBar Style added for supporting RTL.
        * */
        super(mContext, R.style.NewDialog);

        context = mContext;
        View view = LayoutInflater.from(mContext).inflate(R.layout.progress_bar_layout, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);

        getWindow().setDimAmount(0f);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        getWindow().setGravity(Gravity.CENTER);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

    }


    public ProgressBarHandler(Context context, int theme) {
        super(context, theme);
    }
  @Override
  public void show() {
        try {
            if (!((Activity) context).isFinishing()) {
                super.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
  }


    @Override
    public boolean isShowing() {
        return super.isShowing();
    }


    public void hide() {
        try {
            if (!((Activity) context).isFinishing()) {
                dismiss();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
