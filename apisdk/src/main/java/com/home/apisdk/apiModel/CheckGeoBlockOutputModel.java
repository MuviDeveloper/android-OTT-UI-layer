package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Output Attributes For CheckGeoBlockCountryAsynTask
 *
 * @author MUVI
 */

public class CheckGeoBlockOutputModel {


    String countrycode,state,city;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * This Method is use to Get the Country Code
     *
     * @return countrycode
     */
    public String getCountrycode() {

        return countrycode;
    }

    /**
     * This Method is use to Set the Country Code
     *
     * @param countrycode For Setting The Country Code
     */
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }


}
