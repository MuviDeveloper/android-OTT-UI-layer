package com.home.vod.model;

/**
 * Created by Muvi on 10/5/2016.
 */
public class FilterListModel {

    private String title;
    private String permalink;
    private boolean isApplied;
    private String isDefault;
    private boolean isGenre;
    private boolean isSelected;
    private String value;
    private boolean isHeader;
    private boolean isCustomFilter;
    private String categoryName;

    public boolean isCustomFilter() {
        return isCustomFilter;
    }

    public void setCustomFilter(boolean customFilter) {
        isCustomFilter = customFilter;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public FilterListModel() {
    }

    public FilterListModel(String title) {
        this.title = title;
    }

    public void setGenre(boolean genre) {
        isGenre = genre;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isApplied() {
        return isApplied;
    }

    public void setApplied(boolean applied) {
        isApplied = applied;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }


    public boolean isGenre() {
        return isGenre;
    }

    public void setIsGenre(boolean isGenre) {
        this.isGenre = isGenre;
    }

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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
