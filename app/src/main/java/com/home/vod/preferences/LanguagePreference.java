package com.home.vod.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by alok.acharya on 31/7/17.
 */

public class LanguagePreference {
    private static final String LANGUAGE_SHARED_PRE = "MuviLanguage";
    private SharedPreferences languageSharedPref;
    private SharedPreferences.Editor mEditor;
    private static LanguagePreference languagePreference;

    /**
     * Language Constant default Key
     */
    public static String INVALID_PHONE_NUMBER = "OOPS_INVALID_PHONE_NUMBER";
    public static String DEFAULT_INVALID_PHONE_NUMBER = "Oops ! Invalid Number";
    public static String MOBILE = "MOBILE";
    public static String DEFAULT_MOBILE = "Enter Mobile Number";
    public static String DEFAULT_FIRST_NAME = "First Name";
    public static String DEFAULT_LAST_NAME = "Last Name";
    public static String FIRST_NAME = "FIRST_NAME";
    public static String LAST_NAME = "LAST_NAME";

    public static String STATE = "STATE";
    public static String CITY = "CITY";
    public static String STREET = "STREET";
    public static String ZIP = "ZIP";

    public static String DEFAULT_STATE = "State";
    public static String DEFAULT_CITY = "City";
    public static String DEFAULT_STREET = "Street";
    public static String DEFAULT_ZIP = "ZIP";


    public static String DEFAULT_DELETE_BTN = "Delete";
    public static final String LOGIN_FACEBOOK = "LOGIN_FACEBOOK";
    public static final String DEFAULT_LOGIN_FACEBOOK = "Login with Facebook";
    public static final String DEFAULT_REGISTER_FACEBOOK = "Register with Facebook";
    public static final String SIGN_UP_WITH_EMAIL = "SIGN_UP_WITH_EMAIL";
    public static final String DEFAULT_SIGN_UP_WITH_EMAIL = "Sign Up with Email";
    public static final String REGISTER_FACEBOOK = "REGISTER_FACEBOOK";
    public static String DELETE_BTN = "DELETE_BTN";
    public static String DEFAULT_WANT_TO_DELETE = "Want to Delete";
    public static String WANT_TO_DELETE = "WANT_TO_DELETE";
    public static String MY_DOWNLOAD = "MY_DOWNLOAD";
    public static String MY_UPLOAD = "MY_UPLOAD";
    public static String FOLLOWINGS = "FOLLOWINGS";
    public static String DEFAULT_MY_DOWNLOAD = "My Download";
    public static String DEFAULT_MY_UPLOAD = "My Upload";
    public static String DEFAULT_FOLLOWINGS = "Followings";
    public static String FREE_FOR_COUPON = "FREE_FOR_COUPON";
    public static final String TO_LOGIN = "TO_LOGIN";
    public static final String CLICK_HERE = "CLICK_HERE";
    public static final String SUBMIT_YOUR_RATING_TITLE = "SUBMIT_YOUR_RATING_TITLE";
    public static final String VIEW_LESS = "VIEW_LESS";
    public static final String IS_MYLIBRARY = "IS_MYLIBRARY";
    public static final String IS_RESTRICT_DEVICE = "IS_RESTRICT_DEVICE";
    public static final String IS_ONE_STEP_REGISTRATION = "IS_ONE_STEP_REGISTRATION";
    public static final String SELECTED_LANGUAGE_CODE = "SELECTED_LANGUAGE_CODE";
    public static final String SEARCH_PLACEHOLDER = "SEARCH_PLACEHOLDER";
    public static final String VIEW_TRAILER = "VIEW_TRAILER";
    public static final String TRAILER = "TRAILER";
    public static final String DEFAULT_TRAILER = "DEFAULT_TRAILER";
    public static final String FIRST_SONG = "FIRST_SONG";
    public static final String LAST_SONG = "LAST_SONG";
    public static final String CHOOSE_USER_GROUP = "CHOOSE_USER_GROUP";
    public static final String DEFAULT_CHOOSE_USER_GROUP = "Choose User Group";



    public static final String FOLLOW = "FOLLOW";
    public static final String FOLLOWERS = "FOLLOWRS";
    public static final String MPP_CONTENT = "CONTENT";
    public static final String NO_CATEGORY_FOUND = "no_category_found";
    public static final String NO_CASTCREW_FOUND = "no_castcrew_found";
    public static final String NO_CONTENT_FOUND = "no_content_found";
    public static final String FOLLOWLIST_CATEGORY = "FOLLOWLIST_CATEGORY";
    public static final String DEFAULT_FOLLOWLIST_CATEGORY = "Category";
    public static final String UNFOLLOW = "UNFOLLOW";
    public static final String VIDEO = "VIDEO";
    public static final String AUDIO = "AUDIO";
    public static final String WATCH_NOW = "WATCH_NOW";
    public static final String WATCH = "WATCH";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String GENRE = "GENRE";
    public static final String CENSOR_RATING = "CENSOR_RATING";
    public static final String CAST = "CAST";
    public static final String DIRECTOR = "DIRECTOR";
    public static final String VIEW_MORE = "VIEW_MORE";
    public static final String VIEW_ALL = "VIEW_ALL";
    public static final String FILTER_BY = "FILTER_BY";
    public static final String SORT_BY = "SORT_BY";
    public static final String FORGOT_PASSWORD = "FORGOT_PASSWORD";
    public static final String LOGIN = "LOGIN";
    public static final String CONFIRM_PASSWORD = "CONFIRM_PASSWORD";
    public static final String CURRENT_PASSWORD = "CURRENT_PASSWORD";
    public static final String UPDATE_PROFILE = "UPDATE_PROFILE";
    public static final String APP_ON = "APP_ON";
    public static final String APP_SELECT_LANGUAGE = "APP_SELECT_LANGUAGE";
    public static final String PROFILE = "PROFILE";
    public static final String PURCHASE_HISTORY = "PURCHASE_HISTORY";
    public static final String LOGOUT = "LOGOUT";
    public static final String CHANGE_PASSWORD = "Change Password";
    public static final String TRANSACTION = "TRANSACTION";
    public static final String INVOICE = "INVOICE";
    public static final String TITTLE = "TITTLE";
    public static final String TRANSACTION_DATE = "TRANSACTION_DATE";
    public static final String ORDER = "ORDER";
    public static final String AMOUNT = "AMOUNT";
    public static final String TRANSACTION_STATUS = "TRANSACTION_STATUS";
    public static final String PLAN_NAME = "PLAN_NAME";
    public static final String DURATION = "DURATION";
    public static final String DEFAULT_DURATION = "Duration";

    public static String NEXT = "NEXT";
    public static String SELECT_PURCHASE_TYPE = "SELECT_PURCHASE_TYPE";
    public static String COMPLETE_SEASON = "COMPLETE_SEASON";
    public static final String SELECT_PLAN = "SELECT_PLAN";
    public static final String SKIP_BUTTON_TITLE = "SKIP_BUTTON_TITLE";
    public static final String PURCHASE = "PURCHASE";
    public static String ENTER_VOUCHER_CODE = "ENTER_VOUCHER_CODE";
    public static String VOUCHER_SUCCESS = "VOUCHER_SUCCESS";
    public static String PURCHASE_SUCCESS = "PURCHASE_SUCCESS";
    public static String PLAY_NOW = "PLAY_NOW";
    public static String AUDIO_PLAY_NOW = "AUDIO_PLAY_NOW";
    public static String PLAY_LATER = "PLAY_LATER";
    public static String AUDIO_PLAY_LATER = "AUDIO_PLAY_LATER";
    public static String PURCHASE_SUCCESS_DESC = "PURCHASE_SUCCESS_DESC";
    public static String PURCHASE_SUCCESS_DESC2 = "PURCHASE_SUCCESS_DESC2";
    public static final String CREDIT_CARD_DETAILS = "CREDIT_CARD_DETAILS";
    public static final String CARD_WILL_CHARGE = "CARD_WILL_CHARGE";
    public static final String SAVE_THIS_CARD = "SAVE_THIS_CARD";
    public static final String USE_NEW_CARD = "USE_NEW_CARD";
    public static final String BUTTON_APPLY = "BUTTON_APPLY";
    public static final String BUTTON_RESET = "BUTTON_RESET";
    public static final String RESET_PASSWORD = "RESET_PASSWORD";
    public static final String DEFAULT_BUTTON_RESET = "Reset";
    public static String CHK_OVER_18 = "CHK_OVER_18";
    public static String DEFAULT_CHK_OVER_18 = "By clicking on Register, I agree to";
    public static final String BUTTON_OK = "BUTTON_OK";
    public static final String FEATURE_UNSUPPORTED_MESSAGE = "FEATURE_UNSUPPORTED_MESSAGE";
    public static final String PLAY_DURATION_ALERT = "PLAY_DURATION_ALERT";
    public static final String PAYMENT_LOGOUT_BTN = "PAYMENT_LOGOUT_BTN";
    public static final String AGREE_TERMS = "AGREE_TERMS";
    public static final String TERMS = "TERMS";
    public static final String OOPS_INVALID_EMAIL = "OOPS_INVALID_EMAIL";
    public static final String OOPS_INVALID_PHONE = "OOPS_INVALID_MOBILE_NUMBER";
    public static final String VALID_CONFIRM_PASSWORD = "VALID_CONFIRM_PASSWORD";
    public static final String PASSWORDS_DO_NOT_MATCH = "PASSWORDS_DO_NOT_MATCH";
    public static final String EMAIL_EXISTS = "EMAIL_EXISTS";
    public static final String EMAIL_DOESNOT_EXISTS = "EMAIL_DOESNOT_EXISTS";
    public static final String MOBILE_DOESNOT_EXISTS = "MOBILE_DOESNOT_EXISTS";
    public static final String PASSWORD_RESET_LINK = "PASSWORD_RESET_LINK";
    public static final String YES = "YES";
    public static final String NO = "NO";
    public static final String PROFILE_UPDATED = "PROFILE_UPDATED";
    public static final String INVALID_COUPON = "INVALID_COUPON";
    public static final String DISCOUNT_ON_COUPON = "DISCOUNT_ON_COUPON";
    public static final String HOME = "HOME";

    public static final String CONTACT_US = "contactus";
    public static final String SUPPORT = "support";
    public static final String ACTIVATE_SUBSCRIPTION_WATCH_VIDEO = "ACTIVATE_SUBSCRIPTION_WATCH_VIDEO";
    public static final String ACTIVATE_SUBSCRIPTION_FROM_WEBSITE = "ACTIVATE_SUBSCRIPTION_FROM_WEBSITE";
    public static final String CROSSED_MAXIMUM_LIMIT = "CROSSED_MAXIMUM_LIMIT";
    public static final String ACCESS_PERIOD_EXPIRED = "ACCESS_PERIOD_EXPIRED";
    public static final String CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY = "CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY";
    public static final String ALREADY_PURCHASE_THIS_CONTENT = "ALREADY_PURCHASE_THIS_CONTENT";
    public static final String SORT_ALPHA_A_Z = "SORT_ALPHA_A_Z";
    public static final String SORT_ALPHA_Z_A = "SORT_ALPHA_Z_A";
    public static final String SORT_LAST_UPLOADED = "SORT_LAST_UPLOADED";
    public static final String SEARCH_HINT = "SEARCH_HINT";
    public static final String GEO_BLOCKED_ALERT = "GEO_BLOCKED_ALERT";
    public static final String APP_NO_LONGER_ACTIVE = "APP_NO_LONGER_ACTIVE";
    public static final String NO_INTERNET_NO_DATA = "NO_INTERNET_NO_DATA";
    public static final String TRY_AGAIN = "TRY_AGAIN";
    public static final String SLOW_INTERNET_CONNECTION = "SLOW_INTERNET_CONNECTION";
    public static final String NO_INTERNET_CONNECTION = "NO_INTERNET_CONNECTION";
    public static final String VIDEOS = "VIDEOS";
    public static final String NEW_HERE_TITLE = "NEW_HERE_TITLE";
    public static final String SIGN_UP_TITLE = "SIGN_UP_TITLE";
    public static final String NAME_HINT = "NAME_HINT";
    public static final String GUESTUSER = "GUESTUSER";
    public static final String PROCEED_PAYMENT = "PROCEED_PAYMENT";
    public static final String DEFAULT_GUESTUSER = "Guest User Details";
    public static final String DEFAULT_PROCEED_PAYMENT = "Proceed to Payment";
    public static final String ALREADY_MEMBER = "ALREADY_MEMBER";
    public static final String LANGUAGE_POPUP_LOGIN = "LANGUAGE_POPUP_LOGIN";
    public static final String LANGUAGE_POPUP_LANGUAGE = "LANGUAGE_POPUP_LANGUAGE";
    public static final String NOTIFICATION = "NOTIFICATION";
    public static final String OLD_PASSWORD = "OLD_PASSWORD";
    public static final String NEW_PASSWORD = "NEW_PASSWORD";
    public static final String TRANSACTION_STATUS_ACTIVE = "TRANSACTION_STATUS_ACTIVE";
    public static final String TRANSACTION_STATUS_EXPIRED = "TRANSACTION_STATUS_EXPIRED";
    public static final String DEFAULT_TRANSACTION_STATUS_EXPIRED = "Expired";
    public static final String TRANSACTION_DETAIL_PURCHASE_DATE = "TRANSACTION_DETAIL_PURCHASE_DATE";
    public static final String DOWNLOAD_BUTTON_TITLE = "DOWNLOAD_BUTTON_TITLE";
    public static final String CAST_CREW_BUTTON_TITLE = "CAST_CREW_BUTTON_TITLE";
    public static final String CAST_CREW_DETAIlS_TITLE = "CAST_CREW_DETAIlS_TITLE";
    public static final String EPISODE_TITLE = "EPISODE_TITLE";
    public static final String ACTIAVTE_PLAN_TITLE = "ACTIAVTE_PLAN_TITLE";
    public static final String SELECT_OPTION_TITLE = "SELECT_OPTION_TITLE";
    public static final String CREDIT_CARD_NAME_HINT = "CREDIT_CARD_NAME_HINT";
    public static final String CREDIT_CARD_NUMBER_HINT = "CREDIT_CARD_NUMBER_HINT";
    public static final String CREDIT_CARD_CVV_HINT = "CREDIT_CARD_CVV_HINT";
    public static final String COUPON_CODE_HINT = "COUPON_CODE_HINT";
    public static final String PAYMENT_OPTIONS_TITLE = "PAYMENT_OPTIONS_TITLE";
    public static final String UPDATE_PROFILE_ALERT = "UPDATE_PROFILE_ALERT";
    public static final String ALERT = "ALERT";
    public static final String DEFAULT_ALERT = "ALERT!";
    public static final String DEFAULT_SOCIALLOGIN_PROFILE_ALERT = "You can't change password as you signed in through 3rd party login.";
    public static final String STORY_TITLE = "STORY_TITLE";
    public static final String NO_DETAILS_AVAILABLE = "NO_DETAILS_AVAILABLE";
    public static final String GUESTUSER_DOWNLOAD_ALERT = "GUESTUSER_DOWNLOAD_ALERT";
    public static final String VIDEO_NOT_PUBLISHED = "VIDEO_NOT_PUBLISHED";
    public static final String PLAYLIST = "PLAYLIST";
    public static final String SORRY = "SORRY";
    public static final String NEW_PLAN = "NEW_PLAN";
    public static final String AMOUNT_PAYABLE = "AMOUNT_PAYABLE";
    public static final String CURRENT_PLAN = "CURRENT_PLAN";
    public static final String PRICE_BREAKUP = "PRICE_BREAKUP";
    public static final String PROFILE_PICTURE = "PROFILE_PICTURE";
    public static final String EFFECTIVE_PRICE = "EFFECTIVE_PRICE";
    public static final String VERIFY_MESSAGE = "VERIFY_MESSAGE";
    public static final String DEFAULT_VERIFY_EMAIL_MESSAGE = "Thank You! An email has been sent to your email address with an activation link. Please activate your account in order to continue.";
    public static final String VERIFY_EMAIL_MESSAGE = "VERIFY_EMAIL_MESSAGE";
    public static final String NO_VIDEO_AVAILABLE = "NO_VIDEO_AVAILABLE";
    public static final String NO_DATA = "NO_DATA";
    public static final String NO_CONTENT = "NO_CONTENT";
    public static final String CLEARED_DATA = "CLEARED_DATA";
    public static final String VIDEO_ISSUE = "VIDEO_ISSUE";
    public static final String ERROR_IN_REGISTRATION = "ERROR_IN_REGISTRATION";
    public static final String LOGOUT_SUCCESS = "LOGOUT_SUCCESS";
    public static final String PAY_WITH_CREDIT_CARD = "PAY_WITH_CREDIT_CARD";
    public static final String ENTER_EMPTY_FIELD = "ENTER_EMPTY_FIELD";
    public static final String OOPS_SOMETHING_WENT_WRONG = "OOPS_SOMETHING_WENT_WRONG";
    public static final String CANCELLED_TRANSACTION = "CANCELLED_TRANSACTION";
    public static final String DEFAULT_CANCELLED_TRANSACTION = "Sorry you have cancelled the payment!";

    public static final String EMAIL_PASSWORD_INVALID = "EMAIL_PASSWORD_INVALID";
    public static final String ADVANCE_PURCHASE = "ADVANCE_PURCHASE";
    public static final String FAILURE = "FAILURE";
    public static final String COUPON_CANCELLED = "COUPON_CANCELLED";
    public static final String VOUCHER_CANCELLED = "VOUCHER_CANCELLED";
    public static final String COUPON_ALERT = "COUPON_ALERT";
    public static final String DETAILS_NOT_FOUND_ALERT = "DETAILS_NOT_FOUND_ALERT";
    public static final String AUDIO_IS_PLAYING = "AUDIO_IS_PLAYING";
    public static final String DEFAULT_AUDIO_IS_PLAYING = "Sorry You can not delete because audio is playing..";

    public static final String PREORDER = "PREORDER";

    public static final String UNPAID = "UNPAID";
    public static final String ERROR_IN_PAYMENT_VALIDATION = "ERROR_IN_PAYMENT_VALIDATION";
    public static final String PURCHASE_SUCCESS_ALERT = "PURCHASE_SUCCESS_ALERT";
    public static final String NO_RECORD = "NO_RECORD";

    public static final String CANCEL_BUTTON = "CANCEL_BUTTON";
    public static final String CANCEL_PLAN_BUTTON = "CANCEL_PLAN_BUTTON";
    public static final String CONTINUE_BUTTON = "CONTINUE_BUTTON";
    public static final String IS_THIRDPARTY = "IS_THIRDPARTY";
    public static final String DEFAULT_IS_THIRDPARTY = "Please disconnect the chromecast before playing the Third party content";
    public static final String IS_CHROMCAST_ON_WHILE_AUDIOPLAY = "IS_CHROMCAST_ON_WHILE_AUDIOPLAY";
    public static final String DEFAULT_IS_CHROMCAST_ON_WHILE_AUDIOPLAY = "Please disconnect the chromecast before playing the audio content";


    public static final String ADD_TO_FAV = "ADD_TO_FAV";
    public static final String ADDED_TO_FAV = "ADDED_TO_FAV";
    public static final String DELETE_FROM_FAV = "DELETE_TO_FAV";
    public static final String SIGN_OUT_WARNING = "SIGN_OUT_WARNING";
    public static final String CHROMECAST_CONNECT_MESSAGE = "CHROMECAST_CONNECTED";
    public static final String CHROMECAST_CONNECTED_MESSAGE = "CHROMECAST_CONNECTED_SUCCESSFULLY";
    public static final String CONTENT_NOT_PURCHASED = "CONTENT_NOT_PURCHASED";
    public static final String THIRD_PARTY_CONTENT = "THIRD_PARTY_CONTENT";
    public static final String SEARCH_ALERT = "SEARCH_ALERT";
    public static final String TEXT_EMIAL = "TEXT_EMIAL";
    public static final String TEXT_MOBILE_NUMBER = "TEXT_MOBILE_NUMBER";
    public static final String GUEST_USER_TEXT = "GUEST_USER_TEXT";
    public static final String TEXT_PASSWORD = "TEXT_PASSWORD";
    public static final String DOWNLOAD_CANCELED = "DOWNLOAD_CANCELED";
    public static final String DEFAULT_DOWNLOAD_CANCELED = "Download cancelled ";
    public static final String TRANSACTION_DETAILS_ORDER_ID = "TRANSACTION_DETAILS_ORDER_ID";
    public static final String PAY_BY_PAYPAL = "PAY_BY_PAYPAL";
    public static final String BTN_PAYNOW = "BTN_PAYNOW";
    public static final String DEFAULT_BTN_PAYNOW = "Pay Now";
    public static final String BTN_REGISTER = "BTN_REGISTER";
    public static final String GMAIL_SIGNUP = "GMAIL_SIGNUP";
    public static final String GMAIL_SIGNIN = "GMAIL_SIGNIN";
    public static final String SORT_RELEASE_DATE = "SORT_RELEASE_DATE";
    public static final String TEXT_SEARCH_PLACEHOLDER = "TEXT_SEARCH_PLACEHOLDER";
    public static final String SLOW_ISSUE_INTERNET_CONNECTION = "SLOW_ISSUE_INTERNET_CONNECTION";
    public static final String SIGN_OUT_ERROR = "SIGN_OUT_ERROR";
    public static final String BTN_SUBMIT = "BTN_SUBMIT";
    public static final String STOP_CASTING_ALERT = "STOP_CASTING_ALERT";
    public static final String FREE_TRIAL = "FREE_TRIAL";
    public static final String EXPANDED_CONTROL_RESTRICTION_MSG = "EXPANDED_CONTROL_RESTRICTION_MSG";
    public static final String STOP_CHROME_CAST_ALERT = "STOP_CHROME_CAST_ALERT";


    //********Notification***********
    public static String DEFAULT_EXPANDED_CONTROL_RESTRICTION_MSG = "Please wait a while, till player is getting ready.";
    public static String DEFAULT_STOP_CASTING_ALERT = "Please stop casting before log out from app.";
    public static String DEFAULT_STOP_CHROME_CAST_ALERT = "Please disconnect chromecast before enjoying the Video.";
    public static String NO_NOTIFICATION = "NO_NOTIFICATION";
    public static String NOTIFICATION_TITLE = "NOTIFICATION_TITLE";
    public static String KIDS_MODE_TITLE = "KIDS_MODE_TITLE";
    public static String DEFAULT_KIDS_MODE_TITLE = "Kids Mode";
    public static String DEFAULT_NO_NOTIFICATION = "No notification Sent.";
    public static String DEFAULT_NOTIFICATION_TITLE = "Notification";

    public static final String TRANASCTION_DETAIL = "TRANASCTION_DETAIL";
    public static final String SIGN_OUT_ALERT = "SIGN_OUT_ALERT";

    public static final String GOOGLE_FCM_TOKEN = "GOOGLE_FCM_TOKEN";
    public static final String ENTER_REGISTER_FIELDS_DATA = "ENTER_REGISTER_FIELDS_DATA";

    public static final String MY_LIBRARY = "MY_LIBRARY";
    public static final String WATCH_HISTORY = "WATCH_HISTORY";
    public static final String ABOUT_US = "ABOUT_US";
    public static final String FILL_FORM_BELOW = "FILL_FORM_BELOW";
    public static final String MESSAGE = "MESSAGE";
    public static final String SIMULTANEOUS_LOGOUT_SUCCESS_MESSAGE = "SIMULTANEOUS_LOGOUT_SUCCESS_MESSAGE";
    public static final String LOGIN_STATUS_MESSAGE = "LOGIN_STATUS_MESSAGE";
    public static final String DEREGISTER = "DEREGISTER";
    public static final String MANAGE_DEVICE = "MANAGE_DEVICE";
    public static final String YOUR_DEVICE = "YOUR_DEVICE";
    public static final String ANDROID_VERSION = "OS_VERSION";
    public static final String DEFAULT_ANDROID_VERSION = "Android Version";
    public static final String DEFAULT_DEREGISTER = "Deregister";
    public static final String DEFAULT_MANAGE_DEVICE = "Manage Devices";
    public static final String DEFAULT_YOUR_DEVICE = "Your Devices";
    public static final String SUBSCRIPTION_NOT_SUPPORTED = "SUBSCRIPTION_NOT_SUPPORTED";
    public static final String DEFAULT_SUBSCRIPTION_NOT_SUPPORTED = "Subscription is not supported for this store.";

    public static String DEFAULT_VIDEO_ISSUE = "There's a problem with a video or Internet connection";
    public static final String DEFAULT_SIMULTANEOUS_LOGOUT_SUCCESS_MESSAGE = "Logout process has been successfully completed. Now you are authorized to activity_login.";
    public static final String DEFAULT_LOGIN_STATUS_MESSAGE = "You are no longer logged in . Please log in again.";

    public static final String DEFAULT_TRANSACTION_STATUS_ACTIVE = "Transcation status active";
    public static final String DEFAULT_IS_ONE_STEP_REGISTRATION = "0";
    public static String DEFAULT_FREE_FOR_COUPON = "You can watch the video at free of cost.";
    //ADD LATER FOR PURCHASE AND TRANSACTION DETAILS
    public static final String DEFAULT_NEED_LOGIN_TO_REVIEW = "You need to Login to add your review.";
    public static final String NEED_LOGIN_TO_REVIEW = "NEED_LOGIN_TO_REVIEW";
    public static final String DEFAULT_ENTER_REVIEW_HERE = "Enter your Review here...max 50 characters";
    public static final String ENTER_REVIEW_HERE = "ENTER_REVIEW_HERE";
    public static final String DEFAULT_BTN_POST_REVIEW = "Post Review";
    public static final String BTN_POST_REVIEW = "BTN_POST_REVIEW";
    public static final String DEFAULT_VIEW_LESS = "View Less"; // Changed to "Less" from "View Less" .
    public static final String DEFAULT_SUBMIT_YOUR_RATING_TITLE = "Submit Your Rating";

    public static final String DOWNLOAD_INTERRUPTED = "DOWNLOAD_INTERRUPTED";
    public static final String DEFAULT_DOWNLOAD_INTERRUPTED = "Download Interrupted.";
    public static final String DOWNLOAD_COMPLETED = "DOWNLOAD_COMPLETED";
    public static final String DEFAULT_DOWNLOAD_COMPLETED = "Download Completed.";
    public static final String TRANSACTION_TITLE = "TRANSACTION_TITLE";
    public static final String DEFAULT_TRANSACTION_TITLE = "Transaction";
    public static final String FREE = "FREE";
    public static final String DEFAULT_FREE = "FREE";
    public static final String SUBSCRIPTION_COMPLETED = "SUBSCRIPTION_COMPLETED";
    public static final String DEFAULT_SUBSCRIPTION_COMPLETED = "Your subscription process completed successfully.";
    public static final String CVV_ALERT = "CVV_ALERT";
    public static final String DEFAULT_CVV_ALERT = "Please enter your CVV.";
    public static final String DEFAULT_CANCEL_BUTTON = "Cancel";
    public static final String DEFAULT_INVOICE = "Invoice";
    public static final String DEFAULT_TITTLE = "Tittle";

    public static String TAKE_PHOTO= "TAKE_PHOTO";
    public static String DEFAULT_TAKE_PHOTO = "Take Photo";

    public static String TAKE_FROM_GALLARY= "TAKE_FROM_GALLARY";
    public static String DEFAULT_TAKE_FROM_GALLARY = "Take from Gallary";

    public static String ADD_YOUR_PHOTO= "ADD_YOUR_PHOTO";
    public static String DEFAULT_ADD_YOUR_PHOTO = "Add Your Photo";

    public static final String TRANSACTION_ORDER_ID = "TRANSACTION_ORDER_ID";
    public static final String DEFAULT_TRANSACTION_ORDER_ID = "Order Id";
    public static final String DEFAULT_TRANSACTION_DETAIL_PURCHASE_DATE = "Purchase Date";
    public static final String DEFAULT_TRANSACTION_DETAILS_TITLE = "Transaction Details";
    public static final String DEFAULT_TRANSACTION_DATE = "Transaction Date";
    public static final String DEFAULT_ORDER = "Order";
    public static final String DEFAULT_AMOUNT = "Amount";
    public static final String DEFAULT_TRANSACTION_STATUS = "Transaction Status";
    public static final String DEFAULT_PLAN_NAME = "Plan Name";
    public static final String DEFAULT_TRANASCTION_DETAIL = "Transaction Details";
    public static final String DEFAULT_DOWNLOAD_BUTTON_TITLE = "DOWNLOAD";
    public static final String DEFAULT_ACTIAVTE_PLAN_TITLE = "Activate Plan";
    public static final String DEFAULT_SAVE_THIS_CARD = "Save this card for faster checkouts";
    public static final String BUTTON_PAY_NOW = "BUTTON_PAY_NOW";
    public static final String APPLY_COUPON_CODE = "APPLY_COUPON_CODE";
    public static final String DEFAULT_APPLY_COUPON_CODE = "Apply Coupon Code";
    public static final String DEFAULT_CARD_WILL_CHARGE = "Your card will be charged now :";
    public static final String DEFAULT_CREDIT_CARD_NAME_HINT = "Enter your Name on Card";
    public static final String DEFAULT_CREDIT_CARD_NUMBER_HINT = "Enter your Card Number";
    public static final String DEFAULT_COUPON_CANCELLED = "Applied coupon is cancelled.";
    public static final String DEFAULT_VOUCHER_CANCELLED = "Applied voucher is cancelled.";
    public static final String DEFAULT_CREDIT_CARD_DETAILS = "Credit Card Details";
    public static final String DEFAULT_BUTTON_PAY_NOW = "Pay Now";
    public static final String DEFAULT_SELECT_PLAN = "Select Your Plan";
    public static final String DEFAULT_SKIP_BUTTON_TITLE = "Skip";
    public static final String DEFAULT_CONTINUE_BUTTON = "Continue";
    public static final String DEFAULT_USE_NEW_CARD = "Use New Card";
    public static final String DEFAULT_DISCOUNT_ON_COUPON = "Awesome, You just saved";
    public static final String DEFAULT_INVALID_COUPON = "Invalid Coupon!";
    public static final String DEFAULT_ERROR_IN_PAYMENT_VALIDATION = "Error in payment validation";
    public static final String ERROR_IN_SUBSCRIPTION = "ERROR_IN_SUBSCRIPTION";
    public static final String DEFAULT_ERROR_IN_SUBSCRIPTION = "Error in Subscription";
    public static final String ERROR_TRANSACTION_PROCESS = "ERROR_TRANSACTION_PROCESS";
    public static final String DEFAULT_ERROR_TRANSACTION_PROCESS = "Error in Transaction Process";
    public static final String DEFAULT_PURCHASE_SUCCESS_ALERT = "You have successfully purchased the content.";
    public static final String DEFAULT_COUPON_CODE_HINT = "Enter Coupon Code";
    public static final String DEFAULT_PHONE_NUMBER = "Phone number";
    public static final String DEFAULT_PHONE_ALREADY_EXISTS = "Mobile number already exists!";
    public static final String DEFAULT_TEXT_EMAIL_PASSWORD_RESET = "Please enter your email to reset your password";
    public static final String DEFAULT_OOPS_SOMETHING_WENT_WRONG = "Oops something went wrong.Please try again later.";


    public static String PHONE_NUMBER = "PHONE_NUMBER";
    public static String EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static String DEFAULT_EMAIL_ADDRESS = "Email";
    public static String DEFAULT_TERMS = "terms";
    public static String DEFAULT_AGREE_TERMS = "By Clicking on Register,I agree to";
    public static final String DEFAULT_TO_LOGIN = "to Login.";
    public static final String DEFAULT_CLICK_HERE = "Click here";
    public static final String DEFAULT_IS_MYLIBRARY = "0";
    public static final String DEFAULT_IS_RESTRICT_DEVICE = "0";
    public static final String DEFAULT_UPDATE_PROFILE = "Update Profile";
    public static final String DEFAULT_APP_ON = "On";
    public static final String DEFAULT_APP_SELECT_LANGUAGE = "Select Language";
    public static final String DEFAULT_DETAILS_NOT_FOUND_ALERT = "Failed to find details.";
    public static final String DEFAULT_GOOGLE_FCM_TOKEN = "0";
    public static final String DEFAULT_FREE_TRIAL = "free trial";

    //ADD LATER FOR PURCHASE AND TRANSACTION DETAILS

    public static String SAVE = "SAVE";
    public static String DEFAULT_SAVE = "Save";

    public static String SAVE_OFFLINE_VIDEO = "SAVE_OFFLINE_VIDEO";
    public static String DEFAULT_SAVE_OFFLINE_VIDEO = "Download Options";
    public static String SEND = "SEND";
    public static String CONTENT_ADDED_TO_PLAYLIST = "CONTENT_ADDED_TO_PLAYLIST";
    public static String VIDEO_ADDED_TO_PLAYLIST = "VIDEO_ADDED_TO_PLAYLIST";
    public static String DEFAULT_SEND = "Send";
    public static String DEFAULT_CONTENT_ADDED_TO_PLAYLIST = "Song added to playlist";
    public static String DEFAULT_VIDEO_ADDED_TO_PLAYLIST = "Video added to playlist";
    public static String CONFIRM_DELETE_MESSAGE = "Send";
    public static String DEFAULT_CONFIRM_DELETE_MESSAGE = "Are you sure to Delete?";


    public static final String DEFAULT_TRY_AGAIN = "Try Again !";
    public static final String DEFAULT_FILL_FORM_BELOW = "Fill the form below.";
    public static final String DEFAULT_MESSAGE = "Message";

    public static final String DEFAULT_MY_LIBRARY = "My Library";
    public static final String DEFAULT_WATCH_HISTORY = "Watch History";
    public static final String DEFAULT_SELECTED_LANGUAGE_CODE = "en";
    public static final String DEFAULT_RESET_PASSWORD = "Reset Password";
    public static final String DEFAULT_HOME = "Home";
    public static final String DEFAULT_ENTER_REGISTER_FIELDS_DATA = "Fill the empty field(s)";
    public static final String TERMS_AND_CONDITIONS = "TERMS_AND_CONDITIONS";
    public static final String DEFAULT_TERMS_AND_CONDITIONS = "Terms & Conditions";
    public static final String DEFAULT_ABOUT_US = "About Us";
    public static final String DEFAULT_CONTACT_US = "Contact us";
    public static final String DEFAULT_SUPPORT = "Support";

    public static final String DEFAULT_FIRST_SONG = "No previous song available. Current song is first song in playing queue.";
    public static final String DEFAULT_LAST_SONG = "No next song available. Current song is last song in playing queue.";
    public static final String DEFAULT_SEARCH_PLACEHOLDER = "Search";
    public static final String DEFAULT_VIEW_TRAILER = "View Trailer";
    public static final String DEFAULT_FOLLOW = "Follow";
    public static final String DEFAULT_FOLLOWERS = "Followr(s)";
    public static final String DEFAULT_MPP_CONTENT = "Content";
    public static final String DEFAULT_NO_CATEGORY_FOUND = "Oops ! You haven't followed any Category yet";
    public static final String DEFAULT_NO_CONTENT_FOUND = "Oops ! You haven't followed any Content yet";
    public static final String DEFAULT_NO_CASTCREW_FOUND = "Oops ! You haven't followed any Cast and Crew yet";
    public static final String DEFAULT_UNFOLLOW = "Following";
    public static final String DEFAULT_VIDEO = "Video";
    public static final String DEFAULT_AUDIO = "Audio";
    public static final String DEFAULT_VIEW_MORE = "More"; // changed from "View More" to "More" for new UI change
    public static final String DEFAULT_VIEW_ALL = "View All";
    public static final String DEFAULT_FILTER_BY = "Filter By";
    public static final String DEFAULT_SORT_BY = "Sort By";
    public static final String DEFAULT_FORGOT_PASSWORD = "Forgot Password?";
    public static final String DEFAULT_LOGIN = "Login";
    public static final String DEFAULT_CONFIRM_PASSWORD = "Confirm Password";
    public static final String DEFAULT_CURRENT_PASSWORD = "Current Password";
    public static final String DEFAULT_PROFILE = "Profile";
    public static final String DEFAULT_PURCHASE_HISTORY = "Purchase History";
    public static final String DEFAULT_LOGOUT = "Logout";
    public static final String DEFAULT_CHANGE_PASSWORD = "Change Password";

    public static String DEFAULT_NEXT = "Next";
    public static String DEFAULT_COMPLETE_SEASON = "Complete Season";
    public static String DEFAULT_SELECT_PURCHASE_TYPE = "Select Purchase Type";

    public static final String DEFAULT_PURCHASE = "Purchase";
    public static String DEFAULT_ENTER_VOUCHER_CODE = "Enter Voucher Code";
    public static String DEFAULT_VOUCHER_SUCCESS = "Voucher Applied  Successfully.";
    public static String DEFAULT_PURCHASE_SUCCESS = "Thank you for your purchase!";
    public static String DEFAULT_PLAY_NOW = "Watch Now";
    public static String DEFAULT_AUDIO_PLAY_NOW = "Play Now";
    public static String DEFAULT_PLAY_LATER = "Watch Later";
    public static String DEFAULT_PURCHASE_SUCCESS_DESC = "You will receive an email with the download link of the purchased content.";
    public static String DEFAULT_PURCHASE_SUCCESS_DESC2 = "To continue playing on the app, click on Play Now button.";
    public static final String DEFAULT_BUTTON_APPLY = "Apply";
    public static final String DEFAULT_BUTTON_OK = "Ok";
    public static final String DEFAULT_FEATURE_UNSUPPORTED_MESSAGE = "This feature is not supported in the current application. Please contact admin or support for further assistance";
    public static final String DEFAULT_PLAY_DURATION_ALERT = "You have exceeded the total duration limit for playing the content. Please subscribe to watch more!";
    public static final String DEFAULT_PAYMENT_LOGOUT_BTN = "logout";
    public static final String DEFAULT_OOPS_INVALID_EMAIL = "Oops! Invalid email.";
    public static final String DEFAULT_OOPS_INVALID_PHONE_NUMBER = "Oops! Invalid phone number.";
    public static final String DEFAULT_PASSWORDS_DO_NOT_MATCH = "Passwords do not match";
    public static final String DEFAULT_EMAIL_EXISTS = "Email already exists";
    public static final String DEFAULT_EMAIL_DOESNOT_EXISTS = "Email does not exist. Please enter correct email.";
    public static final String DEFAULT_MOBILE_DOESNOT_EXISTS = "Mobile does not exist. Please enter correct mobile.";
    public static final String DEFAULT_PASSWORD_RESET_LINK = "Password Reset link has been emailed to your registered email ID. Please check your email to reset password.";
    public static final String DEFAULT_YES = "Yes";
    public static final String DEFAULT_NO = "No";
    public static final String DEFAULT_PROFILE_UPDATED = "Profile updated successfully.";

    public static final String DEFAULT_ACTIVATE_SUBSCRIPTION_WATCH_VIDEO = "You are not authorised to view this content. Please activate.";
    public static final String DEFAULT_ACTIVATE_SUBSCRIPTION_FROM_WEBSITE = "Welkom by NET Afrikaans, u moet egter eers op die webtuiste u betaling doen. U gaan na https://www.net-afrikaans.com op u rekenaar of selfoon. Sodra u klaar u betaling gemaak het kan u aanteken op die selfoon of tv toep (App)";
    public static final String DEFAULT_CROSSED_MAXIMUM_LIMIT = "Sorry, you have exceeded the maximum number of views for this content.";
    public static final String DEFAULT_ACCESS_PERIOD_EXPIRED = "The watch period for the previous purchase has expired, please continue to purchase again.";
    public static final String DEFAULT_CONTENT_NOT_AVAILABLE_IN_YOUR_COUNTRY = "This content is not available to stream in your country";
    public static final String DEFAULT_ALREADY_PURCHASE_THIS_CONTENT = "Sorry, you have already purchased this content earlier.";
    public static final String DEFAULT_SORT_ALPHA_A_Z = "Alphabetic A-Z";
    public static final String DEFAULT_SORT_ALPHA_Z_A = "Alphabetic Z-A";
    public static final String DEFAULT_SORT_LAST_UPLOADED = "Last Uploaded";
    public static final String DEFAULT_GEO_BLOCKED_ALERT = "Sorry, this app is not available in your country.";
    public static final String DEFAULT_APP_NO_LONGER_ACTIVE = "Sorry, This app is no longer active";
    public static final String DEFAULT_NO_INTERNET_NO_DATA = "No Internet Connection / No Data";
    public static final String DEFAULT_SLOW_INTERNET_CONNECTION = "Slow Internet Connection";
    public static final String DEFAULT_NO_INTERNET_CONNECTION = "No Internet Connection";
    public static final String DEFAULT_VIDEOS = "Videos";
    public static final String DEFAULT_NEW_HERE_TITLE = "New here ?";
    public static final String DEFAULT_SIGN_UP_TITLE = "Sign Up";

    public static final String DEFAULT_NAME_HINT = "Enter your Name";
    public static final String DEFAULT_ALREADY_MEMBER = "Already Member";
    public static final String DEFAULT_LANGUAGE_POPUP_LOGIN = "Log in";
    public static final String DEFAULT_LANGUAGE_POPUP_LANGUAGE = "Language";
    public static final String DEFAULT_NOTIFICATION = "Notification";
    public static final String DEFAULT_OLD_PASSWORD = "New Password";
    public static final String DEFAULT_NEW_PASSWORD = "Confirm Password";
    public static final String DEFAULT_CAST_CREW_BUTTON_TITLE = "Cast and Crew";
    public static final String DEFAULT_CAST_CREW_DETAIlS_TITLE = "Cast and Crew Details";
    public static final String DEFAULT_EPISODE_TITLE = "All Episodes";

    public static final String DEFAULT_UPDATE_PROFILE_ALERT = "We could not be able to update your profile. Please try again.";
    public static final String DEFAULT_NO_DETAILS_AVAILABLE = "No details available";
    public static final String DEFAULT_GUESTUSER_DOWNLOAD_ALERT = "Already a download link has been sent to your given email id.";
    public static final String DEFAULT_VIDEO_NOT_PUBLISHED = "Media is not published yet.";
    public static final String DEFAULT_PLAYLIST = "PlayList";
    public static final String DEFAULT_SORRY = "Sorry !";
    public static final String DEFAULT_NEW_PLAN = "New Plan";
    public static final String DEFAULT_AMOUNT_PAYABLE = "Amount Payable";
    public static final String DEFAULT_CURRENT_PLAN = "Current Plan";
    public static final String DEFAULT_EFFECTIVE_PRICE = "Remaining Balance";
    public static final String DEFAULT_PRICE_BREAKUP = "Price Breakup";
    public static final String DEFAULT_PROFILE_PICTURE = "Profile Picture";
    public static final String DEFAULT_VERIFY_MESSAGE = "Verify";
    public static final String DEFAULT_NO_VIDEO_AVAILABLE = "There's some error. Please try again !";
    public static final String DEFAULT_NO_DATA = "No Data";
    public static final String DEFAULT_NO_CONTENT = "There's no matching content found.";
    public static final String DEFAULT_CLEARED_DATA = "Watch history data has been cleared";
    public static final String DEFAULT_ERROR_IN_REGISTRATION = "Error in registration";
    public static final String DEFAULT_LOGOUT_SUCCESS = "Logout Success";
    public static final String DEFAULT_ENTER_EMPTY_FIELD = "Fill the empty field(s)";

    public static final String DEFAULT_EMAIL_PASSWORD_INVALID = "Email or Password is invalid!";
    public static final String DEFAULT_ADVANCE_PURCHASE = "Advance Purchase";
    public static final String DEFAULT_PREORDER = "Preorder";
    public static final String DEFAULT_FAILURE = "Failure !";
    public static final String DEFAULT_NO_RECORD = "No record found!!!";
    public static final String DEFAULT_SIGN_OUT_WARNING = "Are you sure you want to sign out ?";
    public static final String DEFAULT_CHROMECAST_CONNECT_MESSAGE = "Please wait till chrome cast is getting connected";
    public static final String DEFAULT_CHROMECAST_CONNECTED_MESSAGE = "Chromecast connected";
    public static final String DEFAULT_CONTENT_NOT_PURCHASED = "The content is not purchased";
    public static final String DEFAULT_THIRD_PARTY_CONTENT = "3rd party content cannot be played";
    public static final String DEFAULT_ADDED_TO_FAV = "ADDED_TO_FAV";
    public static final String DEFAULT_ADDED_TO_FOLLOW = "Added to follow list";
    public static final String DEFAULT_REMOVE_FROM_FOLLOW = "Remove from follow list";
    public static final String DEFAULT_DELETE_FROM_FAV = "Content Remove From Fav";
    public static final String DEFAULT_SEARCH_ALERT = "Enter some text to search ...";
    public static final String DEFAULT_TEXT_EMIAL = "Enter your Email Address";
    public static final String DEFAULT_TEXT_MOBILE_NUMBER = "Enter your Mobile Number";
    public static final String OPTIONAL_TEXT_MOBILE_NUMBER = "Mobile Number (Opt)";
    public static final String DEFAULT_GUEST_USER_TEXT = "Continue as a Guest User";
    public static final String DEFAULT_TEXT_PASSWORD = "Enter your Password";
    public static final String DEFAULT_BTN_REGISTER = "Register";
    public static final String DEFAULT_GMAIL_SIGNUP = "Register with Google";
    public static final String DEFAULT_GMAIL_SIGNIN = "Login with Google";
    public static final String DEFAULT_SORT_RELEASE_DATE = "Release Date";
    public static final String DEFAULT_TEXT_SEARCH_PLACEHOLDER = "Search";
    public static final String DEFAULT_SIGN_OUT_ERROR = "Sorry, we can not be able to log out. Try again!.";
    public static final String DEFAULT_BTN_SUBMIT = "Submit";
    public static final String DEAFULT_CANCEL_BUTTON = "Cancel";
    public static final String DEAFULT_CONTINUE_BUTTON = "Continue";

    public static String IS_STREAMING_RESTRICTION = "IS_STREAMING_RESTRICTION";
    public static String DEFAULT_IS_IS_STREAMING_RESTRICTION = "0";

    public static String STOP_SAVING_THIS_VIDEO = "STOP_SAVING_THIS_VIDEO";
    public static String DEFAULT_STOP_SAVING_THIS_VIDEO = "Stop saving this video";
    public static String YOUR_VIDEO_WONT_BE_SAVED = "YOUR_VIDEO_WONT_BE_SAVED";
    public static String DEFAULT_YOUR_VIDEO_WONT_BE_SAVED = "Your video can not be saved";
    public static String BTN_KEEP = "BTN_KEEP";
    public static String DEFAULT_BTN_KEEP = "Keep";
    public static String BTN_DISCARD = "BTN_DISCARD";
    public static String DEFAULT_BTN_DISCARD = "Discard";
    public static String WANT_TO_DOWNLOAD = "WANT_TO_DOWNLOAD";
    public static String DEFAULT_WANT_TO_DOWNLOAD = "Want to Download";
    public static String DOWNLOAD_CANCELLED = "DOWNLOAD_CANCELLED";
    public static String DEFAULT_DOWNLOAD_CANCELLED = "Download Cancelled";
    public static String DEFAULT_BTN_NEXT = "Next";
    public static String BTN_NEXT = "BTN_NEXT";
    public static String DEFAULT_CREDIT_CARD_CVV_HINT = "CVV";
    public static String DEFAULT_VOUCHER_CODE = "Voucher Code";
    public static String DEFAULT_WATCH_NOW = "Watch Now";
    public static String DEFAULT_PAYMENT_OPTIONS_TITLE = "Payment Options";
    public static String VOUCHER_BLANK_MESSAGE = "VOUCHER_BLANK_MESSAGE";
    public static String DEFAULT_VOUCHER_BLANK_MESSAGE = "Please Enter Your Voucher Code";
    public static String DETAILS_TITLE = "DETAILS_TITLE";
    public static String DEFAULT_DETAILS_TITLE = "Details Title";
    public static String BENEFIT_TITLE = "BENEFIT_TITLE";
    public static String DEFAULT_BENEFIT_TITLE = "Benefits";
    public static String DIFFICULTY_TITLE = "DIFFICULTY_TITLE";
    public static String DEFAULT_DIFFICULTY_TITLE = "Difficulty";
    public static String DURATION_TITLE = "DURATION_TITLE";
    public static String DEFAULT_DURATION_TITLE = " Duration";
    public static String RESUME_MESSAGE = "RESUME_MESSAGE";
    public static String DEFAULT_RESUME_MESSAGE = "Continue watching where you left?";
    public static String DEFAULT_ADD_A_REVIEW = "Add a Review";
    public static String DEFAULT_REVIEWS = "Reviews";
    public static String ADD_A_REVIEW = "ADD_A_REVIEW";
    public static String REVIEWS = "REVIEWS";
    public static String NO_PURCHASE_HISTORY = "NO_PURCHASE_HISTORY";
    public static String DEFAULT_NO_PURCHASE_HISTORY = "No Purchase History";
    public static String FILMOGRAPHY = "FILMOGRAPHY";
    public static String DEFAULT_FILMOGRAPHY = "filmography";
    public static String ERROR_IN_DATA_FETCHING = "ERROR_IN_DATA_FETCHING";
    public static String DEFAULT_ERROR_IN_DATA_FETCHING = "Error in data fetching. Please try again";
    public static String ENTER_YOUR_MESSAGE = "ENTER_YOUR_MESSAGE";
    public static String DEFAULT_ENTER_YOUR_MESSAGE = "Enter Your Message";
    public static String DEFAULT_VALID_CONFIRM_PASSWORD = "Enter confirm password";
    public static String WANT_DOWNLOAD_CANCEL = "WANT_DOWNLOAD_CANCEL";
    public static String DEFAULT_WANT_DOWNLOAD_CANCEL = "Do you want to cancel the download ?";
    public static String DOWNLOAD_CANCEL = "DOWNLOAD_CANCEL";
    public static String DEFAULT_DOWNLOAD_CANCEL = "Cancel Download ";
    public static String NO_DOWNLOADED_VIDEOS = "NO_DOWNLOADED_VIDEOS";
    public static String DEFAULT_NO_DOWNLOADED_VIDEOS = "No downloaded video(s) available";
    public static String DOWNLOADED_ACCESS_EXPIRED = "DOWNLOADED_ACCESS_EXPIRED";
    public static String DEFAULT_DOWNLOADED_ACCESS_EXPIRED = "You don't have access to play this video";
    public static String RESUME = "RESUME";
    public static String DEFAULT_RESUME = "Resume";
    public static String NO_RESULT_FOUND_REFINE_YOUR_SEARCH = "NO_RESULT_FOUND_REFINE_YOUR_SEARCH";
    public static String DEFAULT_NO_RESULT_FOUND_REFINE_YOUR_SEARCH = "No result found. Please refine your search.";
    public static String NO_DEVICE_AVAILABE = "NO_DEVICE_AVAILABE";
    public static String DEFAULT_NO_DEVICE_AVAILABE = "No devices available for this user.";
    public static String REMOVE_DEVICE_SUCCESS = "REMOVE_DEVICE_SUCCESS";
    public static String DEFAULT_REMOVE_DEVICE_SUCCESS = "Remove device request successful.";

    public static String CLEAR_HISTORY = "CLEAR_HISTORY";
    public static String DEFAULT_CLEAR_HISTORY = "Clear History";
    public static String SORRY_ENTER_NAME = "SORRY_ENTER_NAME";
    public static String DEFAULT_SORRY_ENTER_NAME = "Enter your name";

    public static String PASSWORDS_LENGTH = "Password length should be minimum 6 character.";
    public static String DEFAULT_PASSWORDS_LENGTH = "Password length should be minimum 6 character.";
    public static String ENTER_NEW_PASSWORD = "Enter new password";
    public static String DEFAULT_ENTER_NEW_PASSWORD = "Enter new password";
    public static String ENTER_CURRENT_PASSWORD = "Enter current password";
    public static String DEFAULT_ENTER_CURRENT_PASSWORD = "Enter current password";
    public static String CURRENTPASSWORD_MATCHES_NEWPASSWORD = "Current password matches with new password";
    public static String DEFAULT_CURRENTPASSWORD_MATCHES_NEWPASSWORD = "Current password matches with new password";

    // Force Update

    public static String FORCE_UPDATE_DIALOG_TITLE = "new_version_available";
    public static String FORCE_UPDATE_DIALOG_MESSAGE = "new_version_available_update_latest_version";
    public static String FORCE_UPDATE_DIALOG_UPDATE_BUTTON = "update";
    public static String FORCE_UPDATE_DIALOG_LATER_BUTTON = "later";

    public static String DEFAULT_FORCE_UPDATE_DIALOG_TITLE = "New Version Available!";
    public static String DEFAULT_FORCE_UPDATE_DIALOG_MESSAGE = " A new version of your app is available,Please update to latest version";
    public static String DEFAULT_FORCE_UPDATE_DIALOG_UPDATE_BUTTON = "Update";
    public static String DEFAULT_FORCE_UPDATE_DIALOG_LATER_BUTTON = "Later";

    // Device not supported
    public static String DEVICE_NOT_SUPPORTED = "device_not_suported";
    public static String DEFAULT_DEVICE_NOT_SUPPORTED = "Device not supported";
    public static String SEARCH_RESTRICTION_MSG = "SEARCH_RESTRICTION_MSG";
    public static String DEFAULT_SEARCH_RESTRICTION_MSG = "You should not enter any special character";
    public static String REVIEW_DISPLAY_NAME = "Unknown User";
    public static String DEFAULT_REVIEW_DISPLAY_NAME = "Unknown User";
    public static String NAME_FIELD_BLANK_ALERT = "Name field should not be blank";
    public static String EMAIL_FIELD_BLANK_ALERT = "Email field should not be blank";
    public static String MOBILE_FIELD_BLANK_ALERT = "Mobile field should not be blank";
    public static String DEFAULT_EMAIL_FIELD_BLANK_ALERT = "Email field should not be blank";
    public static String DEFAULT_NAME_FIELD_BLANK_ALERT = "Name field should not be blank";
    public static String DEFAULT_MOBILE_FIELD_BLANK_ALERT = "Mobile field should not be blank";


    //UGC
    public static final String DEFAULT_UPLOAD_VIDEO_WARNING = "Content created successfully. Do you want to upload video ?";
    public static final String UPLOAD_VIDEO_WARNING = "UPLOAD_VIDEO_WARNING";


    public static final String DEFAULT_DELETE_CONTENT_WARNING = "Are you sure want to delete this content ?";
    public static final String DELETE_CONTENT_WARNING = "DELETE_CONTENT_WARNING";

    public static final String UPLOAD_CONTENT = "UPLOAD_CONTENT";
    public static final String DEFAULT_UPLOAD_CONTENT = "Upload Content";
    public static final String LIVE_CONTENT = "LIVE_CONTENT";
    public static final String DEFAULT_LIVE_CONTENT = "Live Content";
    public static final String CONTENT_NAME = "CONTENT_NAME";
    public static final String DEFAULT_CONTENT_NAME = "Content Name";
    public static final String CONTENT_DESCRIPTION = "CONTENT_DESCRIPTION";
    public static final String DEFAULT_CONTENT_DESCRIPTION = "Story/Description";
    public static final String CATEGORY = "CATEGORY";
    public static final String DEFAULT_CATEGORY = "Category : ";
    public static final String DEFAULT_GENRE = "Genre: ";
    public static final String UPLOAD_POSTER = "UPLOAD_POSTER";
    public static final String DEFAULT_UPLOAD_POSTER = "Upload Poster : ";

    public static final String UPLOAD_BANNER = "UPLOAD_BANNER";
    public static final String DEFAULT_UPLOAD_BANNER = "Upload Banner : ";
    public static final String BROWSE = "BROWSE";
    public static final String DEFAULT_BROWSE = "Browse";
    public static final String REMOVE = "REMOVE";
    public static final String DEFAULT_REMOVE = "Remove";
    public static final String SAVE_AND_CONTINUE = "SAVE_AND_CONTINUE";
    public static final String DEFAULT_SAVE_AND_CONTINUE = "Save and Continue";
    public static final String SHARE = "SHARE";
    public static final String DEFAULT_SHARE = "Share";
    public static final String CHANGE_VIDEO = "CHANGE_VIDEO";
    public static final String DEFAULT_CHANGE_VIDEO = "Change Video";
    public static final String UPLOAD_SUCCESS = "UPLOAD_SUCCESS";
    public static final String DEFAULT_UPLOAD_SUCCESS = "Upload Success";
    public static final String ADD_VIDEO = "ADD_VIDEO";
    public static final String DEFAULT_ADD_VIDEO = "Add Video";
    public static final String TAP_TO_CHOOSE = "TAP_TO_CHOOSE";
    public static final String DEFAULT_TAP_TO_CHOOSE = "Tap to choose";
    public static final String SELECT_CATEGORY = "SELECT_CATEGORY";
    public static final String DEFAULT_SELECT_CATEGORY = "Select category(s)";
    public static final String EDIT_CATEGORY = "EDIT_CATEGORY";
    public static final String DEFAULT_EDIT_CATEGORY = "Edit category(s)";
    public static final String SELECT_A_CATEGORY = "SELECT_A_CATEGORY";
    public static final String DEFAULT_SELECT_A_CATEGORY = "Select atleast one category";

    public static final String CONTENT_NAME_REQUIRED = "CONTENT_NAME_REQUIRED";
    public static final String DEFAULT_CONTENT_NAME_REQUIRED = "Content name required";

    public static final String ACTION = "ACTION";
    public static final String DEFAULT_ACTION = "Action";
    public static final String EDIT = "Edit";
    public static final String DEFAULT_EDIT = "Edit";

    public static final String EDIT_CONTENT = "EDIT_CONTENT";
    public static final String DEFAULT_EDIT_CONTENT = "Edit Content";
    public static final String ADD_VIDEO_WARNING = "ADD_VIDEO_WARNING";
    public static final String DEFAULT_ADD_VIDEO_WARNING = "Do you want to add a video?";

    public static final String CHANGE_VIDEO_WARNING = "CHANGE_VIDEO_WARNING";
    public static final String DEFAULT_CHANGE_VIDEO_WARNING = "Do you want to change the video?";


    public static final String UPLOAD_VIDEO = "UPLOAD_VIDEO";
    public static final String DEFAULT_UPLOAD_VIDEO = "Upload Video";

    public static final String UPLOAD_VIDEO_COMPLETE_MESSAGE = "UPLOAD_VIDEO_COMPLETE_MESSAGE";
    public static final String DEFAULT_UPLOAD_VIDEO_COMPLETE_MESSAGE = "Video uploaded successfully.";

    public static final String UPLOAD_VIDEO_INTERUPT_MESSAGE = "UPLOAD_VIDEO_INTERUPT_MESSAGE";
    public static final String DEFAULT_UPLOAD_VIDEO_INTERUPT_MESSAGE = "Video upload is in progress. Do you want to cancel the upload?";

    public static final String SUCCESS = "SUCCESS";
    public static final String DEFAULT_SUCCESS = "Success";

    public static final String VIDEO_UPLOADING_CANCELLED = "VIDEO_UPLOADING_CANCELLED";
    public static final String DEFAULT_VIDEO_UPLOADING_CANCELLED = "Video Uploading has been canceled.";

    public static final String SHARING_MESSAGE = "SHARING_MESSAGE";
    public static final String DEFAULT_SHARING_MESSAGE = "Check this app out:";

    public static final String LANGUAGE_MISMATCH_MESSAGE = "LANGUAGE_MISMATCH_MESSAGE";
    public static final String DEFAULT_LANGUAGE_MISMATCH_MESSAGE = "Sorry! This content can only be edited in";

    public static final String PENDING = "PENDING";
    public static final String DEFAULT_PENDING = "Pending";

    // uniform payment flow

    public static final String EXIT = "EXIT";
    public static final String DEFAULT_EXIT = "Are you sure want to exit?";

    public static final String EXIT_KIDS_MODE = "EXIT_KIDS_MODE";
    public static final String DEFAULT_EXIT_KIDS_MODE = "Exit Kids Mode?";

    public static final String PAYMENT_LOGOUT_MSG = "PAYMENT_LOGOUT_MSG";
    public static final String DEFAULT_PAYMENT_LOGOUT_MSG = "If you want to leave the app, have to logout first.";


    //No internet
    public static final String OOPS = "OOPS";
    public static final String DEFAULT_OOPS = "Oops !";


    public static String OR = "OR";
    public static String DEFAULT_OR = "OR";


    // Settings options in left menu

    public static final String SETTINGS = "SETTINGS";
    public static final String DEFAULT_SETTINGS = "Settings";
    public static final String ON = "ON";
    public static final String DEFAULT_ON = "On";
    public static final String OFF = "OFF";
    public static final String DEFAULT_OFF = "Off";
    public static final String LOGGED_IN_AS = "LOGGED_IN_AS";
    public static final String DEFAULT_LOGGED_IN_AS = "Logged in as";
    public static final String SIGN_IN = "SIGN_IN";
    public static final String DEFAULT_SIGN_IN = "Sign in";
    public static final String ACTION_WATCH_HISTORY = "ACTION_WATCH_HISTORY";
    public static final String DEFAULT_ACTION_WATCH_HISTORY = "Are you sure you want to clear watch history?";
    public static final String STREAM = "STREAM";
    public static final String DEFAULT_STREAM = "Stream";
    public static final String MANAGE_STREAMING_SETTINGS = "MANAGE_STREAMING_SETTINGS";
    public static final String DEFAULT_MANAGE_STREAMING_SETTINGS = "Manage Streaming Settings";
    public static final String DOWNLOAD = "DOWNLOAD";
    public static final String DEFAULT_DOWNLOAD = "Download";
    public static final String MANAGE_DOWNLOADS_SETTINGS = "MANAGE_DOWNLOADS_SETTINGS";
    public static final String DEFAULT_MANAGE_DOWNLOADS_SETTINGS = "Manage Downloads Settings";
    /* public static final String NOTIFICATIONS = "NOTIFICATIONS";
     public static final String DEFAULT_NOTIFICATIONS="Notifications";*/
    public static final String SEE_ALL_REGISTERED_DEVICES = "SEE_ALL_REGISTERED_DEVICES";
    public static final String DEFAULT_SEE_ALL_REGISTERED_DEVICES = "See all registered devices";
    public static final String CLEAR_WATCH_HISTORY = "CLEAR_WATCH_HISTORY";
    public static final String DEFAULT_CLEAR_WATCH_HISTORY = "Clear Watch History";
    public static final String SIGN_IN_HINT = "SIGN_IN_HINT";
    public static final String DEFAULT_SIGN_IN_HINT = "Log in with a different account";
    public static final String CONNECTED_TO_MOBILE_NETWORK = "CONNECTED_TO_MOBILE_NETWORK";
    public static final String DEFAULT_CONNECTED_TO_MOBILE_NETWORK = "connected to mobile data";
    public static final String DOWNLOAD_ON_WIFI_ONLY = "DOWNLOAD_ON_WIFI_ONLY";
    public static final String DEFAULT_DOWNLOAD_ON_WIFI_ONLY = "Download on wifi only";
    public static final String ALWAYS_PLAY_BEST_RESOLUTION = "ALWAYS_PLAY_BEST_RESOLUTION";
    public static final String DEFAULT_ALWAYS_PLAY_BEST_RESOLUTION = "Always Play Best Resolution";
    public static final String NOTIFY_WHEN_STREAMING_ON_MOBILE_DATA = "NOTIFY_WHEN_STREAMING_ON_MOBILE_DATA";
    public static final String DEFAULT_NOTIFY_WHEN_STREAMING_ON_MOBILE_DATA = "Notify When Streaming on Mobile Data";

    public static final String DOWNLOAD_ON_WIFI_ONLY_MSG = "DOWNLOAD_ON_WIFI_ONLY_MSG";
    public static final String DEFAULT_DOWNLOAD_ON_WIFI_ONLY_MSG = "You are allowed to download the video over wifi only.";

    public static final String DOWNLOAD_CANCEl_WARNING_MESSAGE = "DOWNLOAD_CANCEl_WARNING_MESSAGE";
    public static final String DEFAULT_DOWNLOAD_CANCEl_WARNING_MESSAGE = "Download is already completed.";
    public static final String SIZE_OF = "SIZE_OF";
    public static final String DEFAULT_SIZE_OF = "Size of";
    /*
     * @desc: Translation key added for plan
     * */
    public static String PLAN = "Plan";
    public static String DEFAULT_PLAN = "My Plan";
    public static String NO_RECORD_FOUND = "NO_RECORD_FOUND";
    public static String DEFAULT_NO_RECORD_FOUND = "No Record found!!!";
    public static String DEFAULT_NO_PLAN_RECORD_FOUND = "It looks like you’re not a subscriber at the moment. As soon as you subscribe, you’ll find details of your subscription plan right here";
    public static String RENEW_ON_VALID_TILL = "RENEW_ON_VALID_TILL";
    public static String DEFAULT_RENEW_ON_VALID_TILL = "Renews On / Valid Till";

    public static String ACTIVE = "ACTIVE";
    public static String DEFAULT_ACTIVE = "Active";

    public static String ACTIVATE = "ACTIVATE";
    public static String DEFAULT_ACTIVATE = "Activate";

    public static String CANCELLED = "CANCELLED";
    public static String DEFAULT_CANCELLED = "Cancelled";
    public static String EXPIRY_DATE = "EXPIRY_DATE";
    public static String DEFAULT_EXPIRY_DATE = "Expiry Date";
    public static String CANCEL_NOTE = "CANCEL_NO TE";
    public static String DEFAULT_CANCEL_NOTE = "Please give your cancellation note";
    public static final String ENTER_FISRT_NAME = "ENTER_FISRT_NAME";
    public static final String DEFAULT_ENTER_FISRT_NAME = "Please enter your first name.";
    public static final String DEFAULT_CVV_VALIDATION_MESSAGE = "Please enter minimum 3 digit or maximum 4 digit cvv number";
    public static final String CVV_VALIDATION_MESSAGE = "CVV_VALIDATION_MESSAGE";

    public static final String ENTER_LAST_NAME = "ENTER_LAST_NAME";
    public static final String DEFAULT_ENTER_LAST_NAME = "Please enter your last name.";

    public static final String ENTER_MOBILE_NO = "ENTER_MOBILE_NO";
    public static final String DEFAULT_ENTER_MOBILE_NO = "Please enter your mobile no.";

    public static final String STREET_ADDRESS_VALIDATE = "STREET_ADDRESS_VALIDATE";
    public static final String DEFAULT_STREET_ADDRESS_VALIDATE = "Please enter a valid street address.";

    public static final String CITY_ADDRESS = "CITY_ADDRESS";
    public static final String DEFAULT_CITY_ADDRESS = "Please enter your city.";

    public static final String STATE_REGION_PROVINCE_MESSAGE = "STATE_REGION_PROVINCE_MESSAGE";
    public static final String DEFAULT_STATE_REGION_PROVINCE_MESSAGE = "Please enter your state.";

    public static final String ENTER_ZIPCODE = "ENTER_ZIPCODE";
    public static final String DEFAULT_ENTER_ZIPCODE = "Please enter your ZIP code.";

    public static String MY_PLAN = "PLAN";
    public static String DEFAULT_MY_PLAN = "Plan";

    public static String PRICE = "PRICE";
    public static String DEFAULT_PRICE = "Price";

    //Link to Banner
    public static final String BANNER_LOGIN = "BANNER_LOGIN";
    public static final String DEFAULT_BANNER_LOGIN = "You have already Logged In. Log out if you want to Log-In with different account";
    public static final String BANNER_REGISTER = "BANNER_REGISTER";
    public static final String DEFAULT_BANNER_REGISTER = "You have already Logged In . Please Log out. Before Registering";
    public static final String BANNER_SUBSCRIPTION = "BANNER_SUBSCRIPTION";
    public static final String FREEUSER_MESSAGE = "FREEUSER_MESSAGE";
    public static final String DEFAULT_BANNER_SUBSCRIPTION = "You have already subscribed";
    public static final String DEFAULT_FREEUSER_MESSAGE = "Payment gateway(Subscription) is not available for free user";

    //Generic Issue in Offline viewing feature in Android Apps | ER - 26302
    public static final String LOGOUT_WHILE_DOWNLOADING = "LOGOUT_WHILE_DOWNLOADING";
    public static final String DEFAULT_LOGOUT_WHILE_DOWNLOADING = "Some task are downloading or in Queue. Logging Out will cancel all of them. Do you want to Log Out?";

    public static final String CONFIRM_CANCEL_TRANSACTION = "CONFIRM_CANCEL_TRANSACTION";
    public static final String DEFAULT_CONFIRM_CANCEL_TRANSACTION = "Do you want to cancel the transaction?";
    public static final String SUBSCRIBE = "SUBSCRIBE";
    public static final String DEFAULT_SUBSCRIBE = "Subscribe Now";


    /*
     *
     * */
    public static final String IMEI_NUMBER = "IMEI_NUMBER";
    public static final String DEFAULT_IMEI_NUMBER = "Show My IMEI";

    public static final String LOGIN_WITH = "LOGIN_WITH";
    public static final String DEFAULT_LOGIN_WITH = " Login With";

    public static final String PROFILE_UPDATE_RESTRICTION_MSG= "profile_update_restriction_msg";
    public static final String DEFAULT_PROFILE_UPDATE_RESTRICTION_MSG= "You can't change/update your details as you have logged in through a third party SSO.";

    public static final String PASSWORD_UPDATE_RESTRICTION_MSG= "password_update_restriction_msg ";
    public static final String DEFAULT_PASSWORD_UPDATE_RESTRICTION_MSG= "You can't change your password as you have logged in through a third party SSO.";


    public static final String NO_IMEI_FOUND = "NO_IMEI_FOUND";
    public static final String DEFAULT_NO_IMEI_FOUND = "Oops! No IMEI number is associated with this device.";

    public static final String MY_IMEI_NUMBER = "MY_IMEI_NUMBER";
    public static final String DEFAULT_MY_IMEI_NUMBER = "My IMEI Number";

    /*
     * desc: playback failed message added
     **/
    public static final String PLAYBACK_FAILED_MESSAGE = "PLAYBACK_FAILED_MESSAGE";
    public static final String DEFAULT_PLAYBACK_FAILED_MESSAGE = "It seems there is some issue with your network. Please check your internet connection and try again.";


    public static final String MY_SERIAL_NUMBER = "MY_SERIAL_NUMBER";
    public static final String DEFAULT_MY_SERIAL_NUMBER = "Device Info";

    public static final String SURVEY_TITLE = "SURVEY_TITLE";
    public static final String DEFAULT_SURVEY_TITLE = "Survey";

    public static final String PLAYBACK_SPEED = "PLAYBACK_SPEED";
    public static final String DEFAULT_PLAYBACK_SPEED = "Playback speed";

    /**
     * @author : Debashish
     * @desc : btn_play using the key for Play Button | ER 35458
     */
    public static final String PLAY = "PLAY";
    public static final String DEFAULT_PLAY = "Play";


    public static final String BUY_NOW = "BUY_NOW";
    public static final String DEFAULT_BUY_NOW = "Buy Now";

    public static final String BTN_SKIP = "BTN_SKIP";
    public static final String DEFAULT_BTN_SKIP = "Skip";


    /**
     * @param mContext
     */

    private LanguagePreference(Context mContext) {
        languageSharedPref = mContext.getSharedPreferences(LANGUAGE_SHARED_PRE, Context.MODE_PRIVATE);
        mEditor = languageSharedPref.edit();
    }

    public static LanguagePreference getLanguagePreference(Context mContext) {
        if (languagePreference == null) {
            return new LanguagePreference(mContext);
        }
        return languagePreference;
    }


    public void setLanguageSharedPrefernce(String Key, String Value) {
        mEditor.putString(Key, Value);
        mEditor.commit();
    }

    public String getTextofLanguage(String tempKey, String defaultValue) {

        if (languageSharedPref.getString(tempKey, defaultValue) != null)
            return languageSharedPref.getString(tempKey, defaultValue);
        else
            return defaultValue;
    }

    //AOD
    public static String BLANK_PLAYLIST_NAME = "BLANK_PLAYLIST_NAME";
    public static String DEFAULT_BLANK_PLAYLIST_NAME = " Playlist Name Cannot be blank";
    public static String PLAY_ALL = "PLAY_ALL";
    public static String DEFAULT_PLAY_ALL = "Play All";
    public static final String ISPLAYLIST = "ISPLAYLIST";
    public static final String DEFAULT_ISPLAYLIST = "0";
    public static final String ISQUEUE = "ISQUEUE";
    public static final String DEFAULT_ISQUEUE = "0";
    public static final String ADDED_QUEUE = "ADDED_QUEUE";
    public static final String DEFAULT_ADDED_QUEUE = "Added to Queue";
    public static String RELATED_CONTENT_VIDEO_TITLE = "RELATED_CONTENT_SINGLE_PART_TITLE";
    public static String DEFAULT_RELATED_CONTENT_VIDEO_TITLE = "Related Movies";

    public static String RELATED_CONTENT_AUDIO_TITLE = "RELATED_CONTENT_AUDIO_TITLE";
    public static String DEFAULT_RELATED_CONTENT_AUDIO_TITLE = "Related Audios";
    public static String DEFAULT_PLAY_LIST = "My Playlist";
    public static String PLAY_LIST = "PLAY_LIST";
    public static String NO_TRACKS_FOUND = "NO_TRACKS_FOUND";
    public static String DEFAULT_NO_TRACKS_FOUND = "No tracks found";
    public static String ADD_TO_PLAYLIST = "ADD_TO_PLAYLIST";
    public static String DEFAULT_ADD_TO_PLAYLIST = "Add To Playlist";
    public static String ADD_TO_QUEUE = "ADD_TO_QUEUE";
    public static String DEFAULT_ADD_TO_QUEUE = "Add To Queue";
    public static String NEW_PLAYLIST = "NEW_PLAYLIST";
    public static String DEFAULT_NEW_PLAYLIST = "New PlayList";
    public static String CREATE_YOUR_PLAYLIST = "CREATE_YOUR_PLAYLIST";
    public static String DEFAULT_CREATE_YOUR_PLAYLIST = "Create Your PlayList";
    public static String PLAYLIST_NAME = "PLAYLIST_NAME";
    public static String DEFAULT_PLAYLIST_NAME = "Enter PlayList Name";
    public static String TRACKS = "TRACKS";
    public static String DEFAULT_TRACKS = "Tracks";
    public static String TRACK = "TRACK";
    public static String DEFAULT_TRACK = "Track";
    public static String ADDED_TO_QUEUE = "ADDED_TO_QUEUE";
    public static String DEFAULT_ADDED_TO_QUEUE = "Added To queue successfully";
    public static String ALREADY_ADDED_TO_QUEUE = "ALREADY_ADDED_TO_QUEUE";
    public static String DEFAULT_ALREADY_ADDED_TO_QUEUE = "Already Added To queue";
    public static String DELETE_PLAYLIST = "DELETE_PLAYLIST";
    public static String DEFAULT_DELETE_PLAYLIST = "Are you sure you want to delete the playlist?";
    public static String CREATE_PLAYLIST = "CREATE_PLAYLIST";
    public static String DEFAULT_CREATE_PLAYLIST = "Create New Playlist";

    public static String DELETE_PLAYLIST_MESSAGE = "DELETE_PLAYLIST_MESSAGE";
    public static String DEFAULT_DELETE_PLAYLIST_MESSAGE = "Playlist Deleted Successfully.";
    public static String CLEAR_QUEUE = "CLEAR_QUEUE";
    public static String DEFAULT_CLEAR_QUEUE = "Clear Queue";

    public static String PLAYLIST_WARNING_MSG = "PLAYLIST_WARNING_MSG";
    public static String DEFAULT_PLAYLIST_WARNING_MSG = "Please purchase the content before adding it to playlist";
    public static String QUEUE_WARNING_MSG = "QUEUE_WARNING_MSG";
    public static String DEFAULT_QUEUE_WARNING_MSG = "Please purchase the content before adding it to queue";

    public static String YOUR_PLAYLIST = "YOUR_PLAYLIST";
    public static String DEFAULT_YOUR_PLAYLIST = "Your Playlists";
    public static String EDIT_PLAYLIST = "EDIT_PLAYLIST";
    public static String DEFAULT_EDIT_PLAYLIST = "Edit Your Playlist Name";
    public static String EPISODES;
    public static String DEFAULT_EPISODES = "Episodes";


    public static String UNAUTHORIZED_MESSAGE = "UNAUTHORIZED_MESSAGE";
    public static String DEFAULT_UNAUTHORIZED_MESSAGE = " You are not authorized to view this content.Please activate your subscription to watch video.";

    public static String CHOOSE_PLAN = "CHOOSE_PLAN";
    public static String DEFAULT_CHOOSE_PLAN = "Please select a plan";

    public static String SHOW = "SHOW";
    public static String DEFAULT_SHOW = "Show";

    public static String SEASON = "SEASON";
    public static final String DEFAULT_SEASON = "Season";

    public static String EPISODE = "EPISODE";
    public static String DEFAULT_EPISODE = "Episode";

    public static String ADDRESS_TEXT = "ADDRESS_TEXT";
    public static String DEFAULT_ADDRESS_TEXT = "Address";

    public static String CITY_TEXT = "CITY_TEXT";
    public static String DEFAULT_CITY_TEXT = "City";

    public static String COUNTRY_TEXT = "COUNTRY_TEXT";
    public static String DEFAULT_COUNTRY_TEXT = "Country";

    public static String TELEPHONE_TEXT = "TELEPHONE_TEXT";
    public static String DEFAULT_TELEPHONE_TEXT = "Telephone";

    public static String BILLING_ADDRESS_TITLE = "BILLING_ADDRESS_TITLE";
    public static String DEFAULT_BILLING_ADDRESS_TITLE = "Billing Address";

    public static String SELECT_COUNTRY = "SELECT_COUNTRY";
    public static String DEFAULT_SELECT_COUNTRY = "-- Select Country --";

    public static String FILL_ALL_THE_FIELDS = "FILL_ALL_THE_FIELDS";
    public static String DEFAULT_FILL_ALL_THE_FIELDS = "Please fill all the empty field(s).";


    public static final String BTN_SEND_OTP = "SEND_OTP";
    public static final String DEFAULT_BTN_SEND_OTP = "Send OTP";

    public static final String BTN_RE_SEND_OTP = "RESEND_OTP";
    public static final String DEFAULT_BTN_RE_SEND_OTP = "Resend OTP";

    public static final String TEXT_OTP = "OTP";
    public static final String DEFAULT_TEXT_OTP = "Enter your OTP";

    public static final String REGISTER_THROUGH = "register_through";
    public static final String DEFAULT_REGISTER_THROUGH = "0";

    public static final String IS_OTP_ENABLE = "is_otp_enabled";
    public static final String DEFAULT_IS_OTP_ENABLE = "0";

    public static final String ALLOW_ADD_EMAIL = "allow_add_email";
    public static final String DEFAULT_ALLOW_ADD_EMAIL = "0";

    public static final String ALLOW_ADD_PHONE_NO = "allow_add_phone_no";
    public static final String DEFAULT_ALLOW_ADD_PHONE_NO = "0";

    public static final String OTP_EXPIRY_DURATION = "otp_expiry_duration";
    public static final String DEFAULT_OTP_EXPIRY_DURATION = "0";

    public static final String MOBILE_COUNTRY_CODE = "Code";
    public static final String DEFAULT_MOBILE_COUNTRY_CODE = "Enter Country Code";

    public static String INVALID_MOBILE_COUNTRY_CODE = "OOPS_INVALID_COUNTRY_CODE";
    public static final String DEFAULT_OOPS_INVALID_MOBILE_COUNTRY_CODE = "Oops! Invalid Country Code.";

    public static String INVALID_OTP = "OOPS_INVALID_OTP";
    public static final String DEFAULT_OOPS_INVALID_OTP = "Oops! Invalid OTP.";

    public static final String TEXT_OPTIONAL = "Optional";
    public static final String DEFAULT_TEXT_OPTIONAL = "Optional";

    public static String COUNTRYCODE_FIELD_BLANK_ALERT = "Country Code field should not be blank";
    public static String DEFAULT_COUNTRYCODE_FIELD_BLANK_ALERT = "Country Code field should not be blank";

    public static final String NUMBER_STARTS_WITH_0 = "INVALID_NUMBER_STARTS_WITH_0";
    public static String INVALID_NUMBER_STARTS_WITH_0 = "Number can't starts with 0";

    public static final String EMAIL_REQUIRED = "@_REQUIRED";
    public static String INVALID_EMAIL_REQUIRED = "@ required";

}
