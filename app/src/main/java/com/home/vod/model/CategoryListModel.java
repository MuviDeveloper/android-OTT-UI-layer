package com.home.vod.model;

/**
 * Created by Muvi on 10/5/2016.
 */
public class CategoryListModel {

    private String title;




    public boolean isApplied() {
        return isApplied;
    }

    public void setApplied(boolean applied) {
        isApplied = applied;
    }

    private boolean isApplied;

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    private String permalink ;

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    private String isDefault;

    public boolean isGenre() {
        return isGenre;
    }

    public void setIsGenre(boolean isGenre) {
        this.isGenre = isGenre;
    }

    private boolean isGenre;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSectionType() {
        return value;
    }

    public void setSectionType(String sectionType) {
        this.value = sectionType;
    }

    private String value;


    public CategoryListModel(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public CategoryListModel(String title) {
        this.title = title;
    }

    public CategoryListModel(String title, boolean isSelected) {
        this.title = title;
        this.isSelected = isSelected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public CategoryListModel() {

    }
}
