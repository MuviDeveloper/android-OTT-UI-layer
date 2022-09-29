package com.home.apisdk.apiModel;

import java.util.ArrayList;

public class AdvanceContactUsOutput {
    String queryId ="";
    String queryName ="";
    String queryType ="";
    ArrayList<SubqueryList> subQueries = new ArrayList<>();

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public ArrayList<SubqueryList> getSubQueries() {
        return subQueries;
    }

    public void setSubQueries(ArrayList<SubqueryList> subQueries) {
        this.subQueries = subQueries;
    }

    public class SubqueryList {
        String sub_queryId ="";
        String sub_queryName ="";

        public String getSub_queryId() {
            return sub_queryId;
        }

        public void setSub_queryId(String sub_queryId) {
            this.sub_queryId = sub_queryId;
        }

        public String getSub_queryName() {
            return sub_queryName;
        }

        public void setSub_queryName(String sub_queryName) {
            this.sub_queryName = sub_queryName;
        }
    }
}
