package com.home.apisdk.apiModel;

import java.io.Serializable;

/**
 * Created by MUVI on 15-Sep-18.
 */

public class CategoryOutputModel implements Serializable {
    String categoryId,categoryName,permalink,categoryImgUrl;
    boolean isSelected;
    boolean isApplied;
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isApplied() {
        return isApplied;
    }

    public void setApplied(boolean applied) {
        isApplied = applied;
    }



    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getCategoryImgUrl() {
        return categoryImgUrl;
    }

    public void setCategoryImgUrl(String categoryImgUrl) {
        this.categoryImgUrl = categoryImgUrl;
    }

}
