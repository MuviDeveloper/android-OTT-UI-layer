package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Output Attributes For GetGenreListAsynctask
 *
 * @author MUVI
 */

public class CatagoryListOutput {

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    String catagoryName;
    String permalink;


}
