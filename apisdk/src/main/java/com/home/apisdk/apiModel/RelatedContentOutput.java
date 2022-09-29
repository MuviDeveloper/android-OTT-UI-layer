package com.home.apisdk.apiModel;

import java.util.ArrayList;

/**
 * Created by User on 11-10-2017.
 */
public class RelatedContentOutput {
    String permalink;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    int totalCount;
    private ArrayList<ContentData> contentData;

    public ArrayList<ContentData> getContentData() {
        return contentData;
    }

    public void setContentData(ArrayList<ContentData> contentData) {
        this.contentData = contentData;
    }

    /**
     * This Method is use to Get the Permalink
     *
     * @return permalink
     */

    public String getPermalink() {
        return permalink;
    }
    /**
     * This Method is use to Set the Permalink
     *
     * @param permalink For Setting The Permalink
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }
}
