package com.home.vod.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.RequestManager;
import com.home.apisdk.apiModel.HomeFeaturePageBannerModel;
import com.home.vod.R;
import com.home.vod.ui.widgets.CustomTextSliderView;

import java.util.ArrayList;

public class ViewPager_Adapter extends PagerAdapter {

    Context context;
    ArrayList<String> arrayListBannerUrl;
    ArrayList<String> arrayListFeedUrl;
    LayoutInflater inflater;
    ArrayList<String> arrayListUrlType;
    int currentContentType = 0;
    private RequestManager requestManager;

    int size;
    boolean isLogIn;
    String ipAddres = "";
    CustomTextSliderView.PlaybuttonclickListener playbuttonclickListener;

    public ViewPager_Adapter(@NonNull Context mContext, @NonNull ArrayList<String> banner_Url, ArrayList<String> arrayListFeed_Url, ArrayList<String> arrayList_UrlType, RequestManager requestManager, String ipAddres, int size, boolean is_Login, ArrayList<HomeFeaturePageBannerModel> homePageBannerModel_ArrayList, CustomTextSliderView.PlaybuttonclickListener playbuttonclickListener) {
        this.context = mContext;
        this.arrayListBannerUrl = banner_Url;
        this.arrayListFeedUrl = arrayListFeed_Url;
        this.inflater = LayoutInflater.from(context);
        this.arrayListUrlType = arrayList_UrlType;
        this.requestManager = requestManager;

        this.size = size;
        this.ipAddres = ipAddres;
        this.isLogIn = is_Login;
        this.playbuttonclickListener = playbuttonclickListener;

        currentContentType = Integer.parseInt(arrayListUrlType.get(0));

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.banner_layout, container, false);
        ImageView thumbnail = layout.findViewById(R.id.appCompatIvBannerHls);
        requestManager
                .load(arrayListBannerUrl.get(position))
                .into(thumbnail);

        container.addView(layout);
        return layout;
    }


    @Override
    public int getCount() {
        return arrayListBannerUrl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}