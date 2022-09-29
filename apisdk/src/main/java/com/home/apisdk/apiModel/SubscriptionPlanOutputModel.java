package com.home.apisdk.apiModel;

/**
 * This Model Class Holds All The Input Attributes For GetPlanListAsynctask
 *
 * @author MUVI
 */

public class SubscriptionPlanOutputModel {

    private String id;
    private String is_upgrdable = "";
    private String prorated_adjustment = "";

    public String getIs_upgrdable() {
        return is_upgrdable;
    }

    public void setIs_upgrdable(String is_upgrdable) {
        this.is_upgrdable = is_upgrdable;
    }

    public String getProrated_adjustment() {
        return prorated_adjustment;
    }

    public void setProrated_adjustment(String prorated_adjustment) {
        this.prorated_adjustment = prorated_adjustment;
    }

    public String getPayable_amount() {
        return payable_amount;
    }

    public void setPayable_amount(String payable_amount) {
        this.payable_amount = payable_amount;
    }

    private String payable_amount = "";

    public String getIs_upgradable() {
        return is_upgradable;
    }

    public void setIs_upgradable(String is_upgradable) {
        this.is_upgradable = is_upgradable;
    }

    private String name;
    private String is_upgradable = "";
    private String recurrence;
    private String frequency;
    private String studio_id;
    private String status;
    private String language_id;
    private String price;
    private String trial_period;
    private String trial_recurrence;
    private String is_plan_taken;

    public String getIs_plan_taken() {
        return is_plan_taken;
    }

    public void setIs_plan_taken(String is_plan_taken) {
        this.is_plan_taken = is_plan_taken;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    private String short_desc="";

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    private boolean isDefault;

    /**
     * This Method  is use to Get the Currency Details
     *
     * @return currencyDetails
     */

    public CurrencyModel getCurrencyDetails() {
        return currencyDetails;
    }

    /**
     * This Method  is use to Set the Currency Details
     *
     * @param currencyDetails For Setting The Currency Details
     */

    public void setCurrencyDetails(CurrencyModel currencyDetails) {
        this.currencyDetails = currencyDetails;
    }

    CurrencyModel currencyDetails;

    /**
     * This Method  is use to Get the Trail Recurrence
     *
     * @return trial_recurrence
     */

    public String getTrial_recurrence() {
        return trial_recurrence;
    }

    /**
     * This Method  is use to Set the Trail Recurrence
     *
     * @param trial_recurrence For Setting The Trail Recurrence
     */

    public void setTrial_recurrence(String trial_recurrence) {
        this.trial_recurrence = trial_recurrence;
    }

    /**
     * This Method  is use to Get the ID
     *
     * @return id
     */

    public String getId() {
        return id;
    }

    /**
     * This Method  is use to Set the ID
     *
     * @param id For Setting The Id
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * This Method  is use to Get the Name
     *
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * This Method  is use to Set the Name
     *
     * @param name For Setting The Name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This Method  is use to Get the Recurrence
     *
     * @return recurrence
     */
    public String getRecurrence() {
        return recurrence;
    }

    /**
     * This Method  is use to Set the Recurrence
     *
     * @param recurrence For Setting The Recurrence
     */

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    /**
     * This Method  is use to Get the Frequency
     *
     * @return frequency
     */

    public String getFrequency() {
        return frequency;
    }

    /**
     * This Method  is use to Set the Frequency
     *
     * @param frequency For Setting The Frequency
     */

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * This Method  is use to Get the Studio Id
     *
     * @return studio_id
     */

    public String getStudio_id() {
        return studio_id;
    }

    /**
     * This Method  is use to Set the Studio Id
     *
     * @param studio_id For Setting The Studio Id
     */

    public void setStudio_id(String studio_id) {
        this.studio_id = studio_id;
    }

    /**
     * This Method  is use to Get the Status
     *
     * @return status
     */

    public String getStatus() {
        return status;
    }

    /**
     * This Method  is use to Set the Status
     *
     * @param status For Setting The Status
     */

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This Method  is use to Get the Language Id
     *
     * @return language_id
     */

    public String getLanguage_id() {
        return language_id;
    }

    /**
     * This Method  is use to Set the Language Id
     *
     * @param language_id For Setting The Language Id
     */

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    /**
     * This Method  is use to Get the Price
     *
     * @return price
     */

    public String getPrice() {
        return price;
    }

    /**
     * This Method  is use to Set the Price
     *
     * @param price For Setting The Price
     */

    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * This Method  is use to Get the Trail Period
     *
     * @return trial_period
     */
    public String getTrial_period() {
        return trial_period;
    }

    /**
     * This Method  is use to Set the Trail Period
     *
     * @param trial_period For Setting The Trail Period
     */

    public void setTrial_period(String trial_period) {
        this.trial_period = trial_period;
    }


}
