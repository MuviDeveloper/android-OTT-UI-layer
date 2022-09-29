package com.home.apisdk;

/**
 * Created by MUVI on 16-Aug-18.
 */

public abstract class APIController {
    abstract public String getAPIData(String apiName,String permalink,boolean isCachingEnabled);
    abstract public String   getAPICacheData(String apiName,String permalink);
    abstract public void insertCachedDataToDB(String apiName,String permalink,String response,String timeStamp);
    abstract  public void deleteAPICacheData();
}
