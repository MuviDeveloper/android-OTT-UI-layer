package com.home.apisdk.apiModel;

import java.util.ArrayList;

/**
 * This Model Class Holds All The Input Attributes For ContactUsAsynTask
 *
 * @author MUVI
 */



public class AppHomeFeatureOutputModel {

    ArrayList<HomeFeaturePageSectionModel> homePageSectionModelArrayList = new ArrayList<HomeFeaturePageSectionModel>();
    ArrayList<HomeFeaturePageBannerModel> homePageBannerModelArrayList = new ArrayList<HomeFeaturePageBannerModel>();
    int total_section = 0;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    int offset = 1;

    public int getTotal_section() {
        return total_section;
    }

    public void setTotal_section(int total_section) {
        this.total_section = total_section;
    }


    public boolean isCacheData() {
        return isCacheData;
    }

    public void setCacheData(boolean cacheData) {
        isCacheData = cacheData;
    }

    boolean isCacheData;
    boolean getNextSectionData;


    public boolean isGetNextSectionData() {
        return getNextSectionData;
    }

    public void setGetNextSectionData(boolean getNextSectionData) {
        this.getNextSectionData = getNextSectionData;
    }




    public ArrayList<HomeFeaturePageSectionModel> getHomePageSectionModelArrayList() {
        return homePageSectionModelArrayList;
    }

    public void setHomePageSectionModelArrayList(ArrayList<HomeFeaturePageSectionModel> homePageSectionModelArrayList) {
        this.homePageSectionModelArrayList = homePageSectionModelArrayList;
    }

    public ArrayList<HomeFeaturePageBannerModel> getHomePageBannerModelArrayList() {
        return homePageBannerModelArrayList;
    }

    public void setHomePageBannerModelArrayList(ArrayList<HomeFeaturePageBannerModel> homePageBannerModelArrayList) {
        this.homePageBannerModelArrayList = homePageBannerModelArrayList;
    }



}
