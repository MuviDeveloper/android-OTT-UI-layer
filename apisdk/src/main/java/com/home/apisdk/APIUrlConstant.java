/**
 * SDK initialization, platform and device information classes.
 */

package com.home.apisdk;

/**
 * Created by MUVI on 1/18/2017.
 * Class contains all the APIs end points
 */

public class APIUrlConstant {

    public static String BASE_URl;
    /**
     * endpoint to initialize SDK API
     */
    public static String INITIALIZATION_URL = "initialiseSdk";

    /**
     * endpoint to GetAppHomeFeature API
     */
    public static String GET_APP_HOME_FEATURE = "GetAppHomeFeature";

    /**
     * endpoint to GetAppHomeFeature API
     */
    public static String GET_MEDIA_QUEUE = "getmediaqueue";

    /**
     * endpoint to GetOfflineViewRemainingTime API
     */
    public static String GET_OFFLINE_VIEW_REMAINING_TIME = "GetOfflineViewRemainingTime";

    /**
     * endpoint to getMarlinBBOffline API
     */
    public static String GET_MARLINBB_OFFLINE = "getMarlinBBOffline";

    /**
     * endpoint to get Menu List API
     */
    public static String MENU_LIST_URL = "getMenuList";
    /**
     * endpoint to get Genre List API
     *
     * Desc: modified the api name to getFilterDetails
     */
    public static String GENRE_LIST_URL = "getFilterDetails";

    /*
     * endpoint to get Filter List API
     *
     *  Desc: To get all the filtered contents
     */
    public static String GET_FILTER_LIST_URL = "getFilteredList";
    /**
     * endpoint to get the update version of app
     *
     * Desc: API to get the update version of app
     */
    public static String GET_APPCONFIG_URL = "getAppConfig";

    /**
     * endpoint to get Profile Details API
     */
    public static String GET_PROFILE_DETAILS_URL = "getProfileDetails";
    /**
     * endpoint to Update User Profile API
     */
    public static String UPDATE_PROFILE_URL = "UpdateUserProfileV1";
    /**
     * endpoint to get Logout API
     */
    public static String LOGOUT_URL = "logout";
    /**
     * endpoint to get Video Details API
     */
    public static String VIDEO_DETAILS_URL = "getVideoDetails";

    /**
     * endpoint to get Trailer Details API
     */
    public static String TRAILER_DETAILS_URL = "getTrailerDetails";

    public static String GET_STUDIO_AUTH_TOKEN = "getStudioAuthkey";

    /**
     * endpoint to getMonitization Details API
     */
    public static String GET_MONITIZATION_DETAILS = "GetMonetizationDetails";

    public static String getGetMonitizationDetails() {
        return BASE_URl + GET_MONITIZATION_DETAILS;
    }

    /**
     * endpoint to get Content List API
     */
    public static String GET_CONTENT_LIST_URL = "getContentList";
    /**
     * endpoint to get Forgot Password API
     */
    public static String FORGOT_PASSWORD_URL = "forgotPassword";
    /**
     * endpoint to get Login API
     */

//  public static String LOGIN_URL = "login";
    public static String LOGIN_URL = "LoginV1";
    public static String GET_USER_GROUP = "GetUserGroup";
    public static String SSOAUTHLOGIN_URL = "ssoOauthLogin";


    /**
     * endpoint to get Register User API
     */

//  public static String REGISTER_URL = "registerUser";
    public static String REGISTER_URL = "RegisterUserV1";

    /**
     * endpoint to get Episode Details API
     */
    public static String GET_EPISODE_DETAILS_URL = "episodeDetails";
    /**
     * endpoint to get VideoPlaylist Details API
     */
    public static String GET_VIDEOPLAYLIST_DETAILS_URL = "playListDetails";
    /**
     * endpoint to get Search Data API
     */
    public static String SEARCH_DATA_URL = "searchData";
    /**
     * endpoint to get Content Details API
     */
    public static String CONTENT_DETAILS_URL = "getContentDetails";

    /**
     * This is the api for paypal express payment gateway
     */
    public static String PAYPAL_EXPRESS_PPV = "CreatePpvPayPalPaymentToken";

    /**
     * This is the api for Cybersource payment gateway
     */
    public static String CYBERSOURCE_PAYMENT_URL = "initiateCyberSourcePayment";
    public static String PAYPAL_EXPRESS_SUBSCRIPTION = "createSubscriptionPayPalPaymentToken";
    public static String CCAVENUE_SUBSCRIPTION = "ccavenueSubscription";
    public static String CCAVENUE_PPV = "ccAvenuePpv";

    public static String SADAD_SUBSCRIPTION = "sadadSubscription";
    public static String TPAYCAREERBILLING = "makeCareerPayment";
    public static String SADAD_PPV = "sadadPPV";


    public static String PAYTAB_PPV = "ppvHosted";
    public static String PAYTAB_SUBSCRIPTION = "subscriptionHosted";

    public static String MONETBIL_PPV = "ppvHosted";
    public static String MONETBIL_SUBSCRIPTION = "subscriptionHosted";

    public static String OPEN_PPV = "ppvHosted";
    public static String OPEN_SUBSCRIPTION = "subscriptionHosted";

    public static String CODAPAY_PPV = "ppvHosted";
    public static String CODAPAY_SUBSCRIPTION = "subscriptionHosted";

    public static String GET_VIDEO_CARD_URL = "getVideoCards";
    public static String SAVE_VIDEO_CARD_URL = "saveVideoPoll";


    //New Api Added

    /**
     * endpoint to get Studio PLan Lists API
     */
    public static String SUBSCRIPTION_PLAN_LISTS = "getStudioPlanLists";
    /**
     * endpoint to get Home Page API
     */
    // public static String HOMEPAGE_URL = "homePage";

    public static String HOMEPAGE_URL = "getAppHomePage";
    /**
     * endpoint to get Featured Content Details API
     */

    /**
     * endpoint to get EpisodeWatchHistory  API
     */
    public static String GET_WATCH_HISTORY_EPISODE_DETAILS_URL = "EpisodeWatchHistory";

    // public static String GET_FEATURE_CONTENT_URL = "getFeaturedContent";
    public static String GET_FEATURE_CONTENT_URL = "getAPPFeaturedContent";
    /**
     * endpoint to get Image For Download API
     */
    public static String GET_IMAGE_FOR_DOWNLOAD_URL = "GetImageForDownload";
    /**
     * endpoint to get Country Details API
     */
    public static String CHECK_GEO_BLOCK_URL = "checkGeoBlock";
    /**
     * endpoint to get Registration Enable details API
     */
    public static String IS_REGISTRATIONENABLED_URL = "isRegistrationEnabled";

    /**
     * endpoint to get Registration Enable details API
     */
    public static String GENERATEOTP_URL = "generateOTP";

    /**
     * endpoint to get Static Page Details API
     */
    public static String GETSTATICPAGES_URL = "getStaticPagedetails";
    /**
     * endpoint to get Contact Us API
     */
    public static String CONTACT_US_URL = "contactUs";
    /**
     * endpoint to get Celebrity Details API
     */
    public static String GET_CELIBRITY_URL = "getCelibrity";

    /* endpoint to get Notification Count Details API*/
    public static String notificationcount = "GetNoOfUnreadNotification";

    /* endpoint to get Fcmregistration Details API*/
    public static String fcmregistration = "RegisterMyDevice";


    /* endpoint to get Notification Lists Details API*/
    public static String notificationlists = "NotificationLists";


    /* endpoint to get Read All Notification Details API*/
    public static String readallnotification = "ReadAllNotification";
    /**
     * endpoint to get Purchase History Details API
     */
    public static String PURCHASE_HISTORY_URL = "PurchaseHistory";
    /**
     * endpoint to get Transaction Details API
     */
    public static String TRANSACTION_URL = "Transaction";
    /**
     * endpoint to get Invoice Pdf Details API
     */
    public static String GET_INVOICE_PDF_URL = "GetInvoicePDF";
    /**
     * endpoint to Delete Invoice Pdf Details API
     */
    public static String DELETE_INVOICE_PDF_URL = "DeleteInvoicePath";
    /**
     * endpoint to get Monitization Details API
     */
    public static String GET_MONETIZATION_DETAILS_URL = "GetMonetizationDetails";
    /**
     * endpoint to get Social Auth Details API
     */
    public static String SOCIALAUTH_URL = "socialAuth";
    /**
     * endpoint to get Validate Voucher Details API
     */
    public static String VALIDATE_VOUCHER_URL = "ValidateVoucher";
    /**
     * endpoint to get Voucher Plan Details API
     */
    public static String GET_VOUCHER_PLAN_URL = "GetVoucherPlan";
    /**
     * endpoint to get Voucher Subscription Details API
     */
    public static String VOUCHER_SUBSCRIPTION_URL = "VoucherSubscription";
    public static String SUBSCRIPTION_SDK = "subscriptionSDK";
    /**
     * endpoint to get My Library Details API
     */
    public static String MYLIBRARY_URL = "MyLibrary";
    public static String MyAssignedContent_URL = "MyAssignedContent";

    /**
     * endpoint to get Watch History Details API
     */
    public static String WATCH_HISTORY = "watchHistory";

    /**
     * endpoint to get Clear Watch History Details API
     */
    public static String Clear_WATCH_HISTORY = "clearWatchHistory";
    /**
     * endpoint to get Register User Payment Details API
     */
    public static String REGISTER_USER_PAYMENT_URL = "registerUserPayment";
    /**
     * endpoint to get Auth User Payment Information Details API
     */
    public static String AUTH_USER_PAYMENT_INFO_URL = "authUserPaymentInfo";
    /**
     * endpoint to get Card List For PPV Details API
     */
    public static String GET_CARD_LIST_FOR_PPV_URL = "getCardsListForPPV";
    /**
     * endpoint to get Language Translation Details API
     */
    public static String LanguageTranslation = "textTranslation";
    /**
     * endpoint to Check Device Details API
     */
    public static String CheckDevice = "CheckDevice";
    /**
     * endpoint to Logout All Details API
     */
    public static String LogoutAll = "LogoutAll";
    /**
     * endpoint to get Facebook User Status Details API
     */
    public static String fbUserExistsUrl = "getFbUserStatus";
    /**
     * endpoint to get About Us Details API
     */
    public static String AboutUs = "getStaticPagedetails";
    public static String Third_party_sso = "thirdPartySSO";
    public static String UPDATESTRIPEPAYMENT = "updateStripePayment ";

    /**
     * endpoint to get Gmail Registration Details API
     */
    public static String GmailRegUrl = "socialAuth";

    public static String OpenId = "socialAuth";

    /**
     * Following api is used to get the all the season information mapped under a multipart content.
     */
    public static String seasonList = "seasonList";
    public static String seasonDetails = "seasonDetails";

    public static String getOpenIdUrl() {
        return BASE_URl + OpenId;
    }

    public static String getGmailRegUrl() {
        return BASE_URl + GmailRegUrl;
    }

    /**
     * endpoint to get View Favorite Details API
     */
    public static String ViewFavorite = "ViewFavourite";
    /**
     * endpoint to get Add to Favorite Details API
     */
    public static String AddtoFavlist = "AddtoFavlist";
    /**
     * endpoint to get Add to Follow Details API
     */
    public static String AddtoFollowlist = "addFollow";
    /**
     * endpoint to get Add to Gust User Details API
     */
    public static String AddtoGuestUser = "addGuestUser";
    /**
     * endpoint to get Add to resetPassword Details API
     */
    public static String ResetPassword = "resetPassword";
    /**
     * endpoint to get Add to UnFollow Details API
     */
    public static String AddtoUnFollowlist = "unFollow";
    /**
     * endpoint to get Followlist Details API
     */
    public static String GetFollowList = "getFollowList";
    /**
     * endpoint to get Delete Favorite List Details API
     */
    public static String DeleteFavList = "DeleteFavList";
    /**
     * endpoint to get Menu Details API
     */
    public static String GetMenusUrl = "GetMenus";
    /**
     * endpoint to get Update Google Id Details API
     */
    public static String UpdateGoogleid = "UpdateGoogleid";
    /**
     * endpoint to get Cast Details API
     */
    public static String GetCastDetails = "getCastDetail";
    /**
     * endpoint to get Content Rating View Details API
     */
    public static final String ViewContentRating = "ViewContentRating";
    /**
     * endpoint to get Content Rating Add Details API
     */
    public static final String AddContentRating = "AddContentRating";
    /**
     * endpoint to get PPV Payment Details API
     */
    public static final String addSubscriptionUrl = "ppvpayment";

    public static final String GET_LAST_SEEN_DETAILS = "getLastSeenDetails";

    /**
     * endpoint to get Manage Device Details API
     */
    public static final String ManageDevices = "ManageDevices";
    /**
     * endpoint to get Validate Coupon Code Details API
     */
    public static String VALIDATE_COUPON_CODE_URL = "validateCouponCode";
    /**
     * endpoint to get Update Buffer Logs Details API
     */
    public static final String updateBufferLogUrl = "updateBufferLogs";
    /**
     * endpoint to get Video Buffer Logs Details API
     */
    public static String VIDEO_BUFFER_LOGS_URL = "bufferLogs";
    /**
     * endpoint to get Validate User For Content Details API
     */
    public static String VALIDATE_USER_FOR_CONTENT_URL = "isContentAuthorized";
    /**
     * endpoint to get IP Address Details API
     */
    public static String IP_ADDRESS_URL = "https://api.ipify.org/?format=json";
    /**
     * endpoint to get Language LIst Details API
     */
    public static String GET_LANGUAGE_LIST_URL = "getLanguageList";
    /**
     * endpoint to get Video Logs Details API
     */

    public static String VIDEO_LOGS_URL = "VideoLogNew";
    /**
     * endpoint to get Remove Device Details API
     */
    public static final String RemoveDevice = "RemoveDevice";
    /**
     * endpoint to get Check If User Is Logged In Details API
     */
    public static final String CheckIfUserLoggedIn = "CheckIfUserLoggedIn";

    /**
     * endpoint to get all the menu lists including sub menus
     */

    public static final String GetAppMenu = "getAppMenu";

    /**
     * endpoint to get all the Sub Category lists including
     */

    public static final String GetSubCategoryList = "getSubCategoryList";

    /**
     * Endpoint to get coupon activation status.
     */
    public static final String IS_COUPON_EXISTS = "isCouponExists";
    /**
     * Endpoint to get CategoryList
     */
    public static final String GET_CATEGORY_LIST = "getCategoryList";
    /**
     * Endpoint to get uploadcontent url
     */
    public static final String GET_UPLOAD_CONTENT = "assignBroadCastToContent";
    /**
     * Endpoint to get  uploadcontent url
     */
    public static final String DELETE_CONTENT = "deleteContent";
    /**
     * Endpoint to get updatecontent url
     */
    public static final String UPDATE_CONTENT = "updatecontent";
    /**
     * Endpoint to get changevideo url
     */
    public static final String CHANGE_VIDEO = "changeVideo";
    /**
     * Endpoint to get changevideo url
     */
    public static final String MY_UPLOADS_URL = "MyUploads";
    /**
     * Endpoint to get myplans url
     */
    public static String MYPLAN_LIST_URL = "myplans";
    /**
     * Endpoint to get CancelsubscriptionPlan url
     */
    public static String CANCEL_SUBCRIPTION_URL = "CancelsubscriptionPlan";

    /**
     * Endpoint to update kids mode status url
     */
    public static String UPDATE_KIDS_MODE_STATUS = "updateKidsModeStatus";

    public static String getCouponStatus() {
        return BASE_URl + IS_COUPON_EXISTS;
    }

    /**
     * Endpoint to get RavePaymentURL
     */
    public static final String RAVE_URL = "initiateRavePayment";
    public static final String SCA_URL = "makeStripePayment";
    public static final String PAYGATE_URL = "makePaygatePayment";

    /**
     * endpoint to get Contact Us API
     */
    public static final String ADAVANCE_CONTACT_US ="GetAdvanceContactUsQueries";
    public static final String SUPPORT_URL ="GetAdvanceContactUs";


    public static String getGetSubCategoryList() {
        return GetSubCategoryList;
    }

    public static String getWatchHistoryEpisodeDetailsUrl() {
        return BASE_URl + GET_WATCH_HISTORY_EPISODE_DETAILS_URL;

    }

    public static String getGetAppMenu() {
        return BASE_URl + GetAppMenu;
    }


    public static String getMenuListUrl() {
        return BASE_URl + MENU_LIST_URL;
    }

    public static String getRemoveDevice() {
        return BASE_URl + RemoveDevice;
    }

    public static String getCheckIfUserLoggedIn() {
        return BASE_URl + CheckIfUserLoggedIn;
    }


    public static String getGetCastDetails() {
        return BASE_URl + GetCastDetails;
    }


    public static String getUpdateGoogleid() {
        return BASE_URl + UpdateGoogleid;
    }


    public static String getGetMenusUrl() {
        return BASE_URl + GetMenusUrl;
    }

    public static String getViewFavorite() {
        return BASE_URl + ViewFavorite;
    }

    public static String getAddtoFavlist() {
        return BASE_URl + AddtoFavlist;
    }

    public static String getAddtoFollowlist() {
        return BASE_URl + AddtoFollowlist;
    }

    public static String getAddtoGuestUser() {
        return BASE_URl + AddtoGuestUser;
    }

    public static String getResetPassword() {
        return BASE_URl + ResetPassword;
    }

    public static String getAddtoUnFollowlist() {
        return BASE_URl + AddtoUnFollowlist;
    }

    public static String getFollowList() {
        return BASE_URl + GetFollowList;
    }

    public static String getDeleteFavList() {
        return BASE_URl + DeleteFavList;
    }

    public static String getAddContentRating() {
        return BASE_URl + AddContentRating;
    }

    public static String getViewContentRating() {
        return BASE_URl + ViewContentRating;
    }

    public static String getAddSubscriptionUrl() {
        return BASE_URl + addSubscriptionUrl;
    }

    public static String getManageDevices() {
        return BASE_URl + ManageDevices;
    }

    public static String getAboutUs() {
        return BASE_URl + AboutUs;
    }
    public static String getThirdPartySSO() {
        return BASE_URl + Third_party_sso;
    }

    public static String getUpdateStripePayment() {
        return BASE_URl + UPDATESTRIPEPAYMENT;
    }

    public static String getFbUserExistsUrl() {
        return BASE_URl + fbUserExistsUrl;
    }


    public static String getLogoutAll() {
        return BASE_URl + LogoutAll;
    }

    public static String getCheckDevice() {
        return BASE_URl + CheckDevice;
    }

    public static String getLanguageTranslation() {
        return BASE_URl + LanguageTranslation;
    }


    public static String getInitializationUrl() {
        return BASE_URl + INITIALIZATION_URL;
    }

    public static String getGenreListUrl() {
        return BASE_URl + GENRE_LIST_URL;
    }

    public static String getFilterListUrl() {
        return BASE_URl + GET_FILTER_LIST_URL;
    }

    public static String getAppConfigUrl() {
        return BASE_URl + GET_APPCONFIG_URL;
    }

    public static String getGetProfileDetailsUrl() {
        return BASE_URl + GET_PROFILE_DETAILS_URL;
    }

    public static String getUpdateProfileUrl() {
        return BASE_URl + UPDATE_PROFILE_URL;
    }

    public static String getLogoutUrl() {
        return BASE_URl + LOGOUT_URL;
    }

    public static String getVideoDetailsUrl() {
        return BASE_URl + VIDEO_DETAILS_URL;
    }

    public static String getTrailerDetailsUrl() {
        return BASE_URl + TRAILER_DETAILS_URL;
    }

    public static String getGetContentListUrl() {
        return BASE_URl + GET_CONTENT_LIST_URL;
    }

    public static String getFilterList() {
        return BASE_URl + "getFilteredList";
    }

    public static String getForgotPasswordUrl() {
        return BASE_URl + FORGOT_PASSWORD_URL;
    }

    public static String getLoginUrl() {
        return BASE_URl + LOGIN_URL;
    }
    public static String getSsoAuthLoginUrl() {
        return BASE_URl + SSOAUTHLOGIN_URL;
    }
    public static String getUserGroup() {
        return BASE_URl + GET_USER_GROUP;
    }

    public static String getRegisterUrl() {
        return BASE_URl + REGISTER_URL;
    }

    public static String getGetEpisodeDetailsUrl() {
        return BASE_URl + GET_EPISODE_DETAILS_URL;
    }

    public static String getSearchDataUrl() {
        return BASE_URl + SEARCH_DATA_URL;
    }

    public static String getContentDetailsUrl() {
        return BASE_URl + CONTENT_DETAILS_URL;
    }

    public static String getSubscriptionPlanLists() {
        return BASE_URl + SUBSCRIPTION_PLAN_LISTS;
    }

    public static String getHomepageUrl() {
        return BASE_URl + HOMEPAGE_URL;
    }

    public static String getGetFeatureContentUrl() {
        return BASE_URl + GET_FEATURE_CONTENT_URL;
    }

    public static String getGetImageForDownloadUrl() {
        return BASE_URl + GET_IMAGE_FOR_DOWNLOAD_URL;
    }

    public static String getCheckGeoBlockUrl() {
        return BASE_URl + CHECK_GEO_BLOCK_URL;
    }

    public static String getIsRegistrationenabledUrl() {
        return BASE_URl + IS_REGISTRATIONENABLED_URL;
    }

    public static String getGenerateOtpUrl() {
        return BASE_URl + GENERATEOTP_URL;
    }

    public static String getGetstaticpagesUrl() {
        return BASE_URl + GETSTATICPAGES_URL;
    }

    public static String getContactUsUrl() {
        return BASE_URl + CONTACT_US_URL;
    }

    public static String getGetCelibrityUrl() {
        return BASE_URl + GET_CELIBRITY_URL;
    }

    public static String getNotificationcount() {
        return BASE_URl + notificationcount;
    }

    public static String getFcmregistration() {
        return BASE_URl + fcmregistration;
    }

    public static String getNotificationLists() {
        return BASE_URl + notificationlists;
    }

    public static String getReadallnotification() {
        return BASE_URl + readallnotification;
    }

    public static String getPurchaseHistoryUrl() {
        return BASE_URl + PURCHASE_HISTORY_URL;
    }

    public static String getTransactionUrl() {
        return BASE_URl + TRANSACTION_URL;
    }

    public static String getGetInvoicePdfUrl() {
        return BASE_URl + GET_INVOICE_PDF_URL;
    }

    public static String getDeleteInvoicePdfUrl() {
        return BASE_URl + DELETE_INVOICE_PDF_URL;
    }

    public static String getGetMonetizationDetailsUrl() {
        return BASE_URl + GET_MONETIZATION_DETAILS_URL;
    }

    public static String getVideoPlayListDetailsUrl() {
        return BASE_URl + GET_VIDEOPLAYLIST_DETAILS_URL;
    }

    public static String getSocialauthUrl() {
        return BASE_URl + SOCIALAUTH_URL;
    }

    public static String getValidateVoucherUrl() {
        return BASE_URl + VALIDATE_VOUCHER_URL;
    }

    public static String getGetVoucherPlanUrl() {
        return BASE_URl + GET_VOUCHER_PLAN_URL;
    }

    public static String getVoucherSubscriptionUrl() {
        return BASE_URl + VOUCHER_SUBSCRIPTION_URL;
    }
    public static String getsubscriptionSDKUrl() {
        return BASE_URl + SUBSCRIPTION_SDK;
    }

    public static String getMylibraryUrl() {
        return BASE_URl + MYLIBRARY_URL;
    }

    public static String getMyAssignedContentUrl() {
        return BASE_URl + MyAssignedContent_URL;
    }

    public static String getWatchHistory() {
        return BASE_URl + WATCH_HISTORY;
    }

    public static String getClearWatchHistory() {
        return BASE_URl + Clear_WATCH_HISTORY;
    }

    public static String getRegisterUserPaymentUrl() {
        return BASE_URl + REGISTER_USER_PAYMENT_URL;
    }

    public static String getAuthUserPaymentInfoUrl() {
        return BASE_URl + AUTH_USER_PAYMENT_INFO_URL;
    }

    public static String getGetCardListForPpvUrl() {
        return BASE_URl + GET_CARD_LIST_FOR_PPV_URL;
    }

    public static String getGetAppHomeFeature() {
        return BASE_URl + GET_APP_HOME_FEATURE;
    }

    public static String getMediaQueue() {
        return BASE_URl + GET_MEDIA_QUEUE;
    }

    public static String getValidateCouponCodeUrl() {
        return BASE_URl + VALIDATE_COUPON_CODE_URL;
    }

    public static String getIpAddressUrl() {
        return IP_ADDRESS_URL;
    }

    public static String getGetLanguageListUrl() {
        return BASE_URl + GET_LANGUAGE_LIST_URL;
    }

    public static String getVideoLogsUrl() {
        return BASE_URl + VIDEO_LOGS_URL;
    }

    public static String getVideoBufferLogsUrl() {
        return BASE_URl + VIDEO_BUFFER_LOGS_URL;
    }

    public static String getValidateUserForContentUrl() {
        return BASE_URl + VALIDATE_USER_FOR_CONTENT_URL;
    }


    public static String getUpdateBufferLogUrl() {
        return BASE_URl + updateBufferLogUrl;
    }

    //Get categorylist for ugc
    public static String getCategoryListUrl() {
        return BASE_URl + GET_CATEGORY_LIST;
    }

    //upload content for ugc
    public static String getUploadContentUrl() {
        return BASE_URl + GET_UPLOAD_CONTENT;
    }

    public static String GetOfflineViewRemainingTime() {
        return BASE_URl + GET_OFFLINE_VIEW_REMAINING_TIME;
    }

    /**
     * endpoint to get all the menu lists including sub menus
     */

    public static final String GetRelatedContent = "RelatedContent";

    public static String getRelatedContent() {
        return BASE_URl + GetRelatedContent;
    }

    public static String getMarlinBBOffline() {
        return BASE_URl + GET_MARLINBB_OFFLINE;
    }


    public static String getDeleteContentUrl() {
        return BASE_URl + DELETE_CONTENT;
    }

    public static String getUpdateContentUrl() {
        return BASE_URl + UPDATE_CONTENT;
    }

    public static String getChangeVideoUrl() {
        return BASE_URl + CHANGE_VIDEO;
    }

    public static String getMyUploadsUrl() {
        return BASE_URl + MY_UPLOADS_URL;
    }

    public static String getLastSeenUrl() {
        return BASE_URl + GET_LAST_SEEN_DETAILS;
    }

    //AOD
    public static String GetAudioUserPlayListDetailURL() {
        return BASE_URl + AudioUserPlayListDetail;
    }

    public static final String AudioUserPlayListDetail = "AudioUserPlayListDetail";
    public static final String AllUserPlaylist = "AudioAllUserPlayList";
    public static final String DeleteContent = "DeleteContent";
    public static final String AddToPlaylist = "addToPlaylist";
    public static final String PlayListNameEdit = "PlayListNameEdit";
    public static final String DeletePlaylist = "DeletePlaylist";

    public static String GetAllUserPlaylist() {
        return BASE_URl + AllUserPlaylist;
    }

    public static String GetDeleteContentUrl() {
        return BASE_URl + DeleteContent;
    }

    public static String GetAddToPlaylistUrl() {
        return BASE_URl + AddToPlaylist;
    }

    /*
     * @author:Subhadarshani
     * @desc:methods to myplan and cancel subscription API url*/
    public static String getMyPlanListUrl() {
        return BASE_URl + MYPLAN_LIST_URL;
    }

    public static String getCancelSubscriptiontUrl() {
        return BASE_URl + CANCEL_SUBCRIPTION_URL;
    }

    public static String getPlayListNameEditUrl() {
        return BASE_URl + PlayListNameEdit;
    }

    public static String getDeletePlaylistUrl() {
        return BASE_URl + DeletePlaylist;
    }

    public static String getPayPalUrl() {
        return BASE_URl + PAYPAL_EXPRESS_PPV;
    }

    public static String getVideocardsUrl() {
        return BASE_URl + GET_VIDEO_CARD_URL;
    }

    public static String getSaveCardsUrl() {
        return BASE_URl + SAVE_VIDEO_CARD_URL;
    }

    //New monitization design
    public static String PPV_PLAN_LISTS = "GetPpvPlan";
    public static String PREORDER_PLAN_LISTS = "GetPreOrderPlan";
    public static String PPV_Bundle_LISTS = "GetPpvBundlePlan";
    public static String LICENSE_TOKEN_URL = "getOfflineLicenseToken";

    public static String getPPVPlanListUrl() {
        return BASE_URl + PPV_PLAN_LISTS;
    }

    public static String getPreorderPlanListUrl() {
        return BASE_URl + PREORDER_PLAN_LISTS;
    }

    public static String getPPVBundleListUrl() {
        return BASE_URl + PPV_Bundle_LISTS;
    }

    public static String getCybersourcePaymentUrl() {
        return BASE_URl + CYBERSOURCE_PAYMENT_URL;
    }

    public static String getSeasonUrl() {
        return BASE_URl + seasonList;
    }

    public static String getSeasonDetils() {
        return BASE_URl + seasonDetails;
    }

    public static String getPaypalExpressSubscriptionUrl() {
        return BASE_URl + PAYPAL_EXPRESS_SUBSCRIPTION;
    }

    public static String getCcavenueSubscriptionUrl() {
        return BASE_URl + CCAVENUE_SUBSCRIPTION;
    }

    public static String getCcavenuePPVUrl() {
        return BASE_URl + CCAVENUE_PPV;
    }

    public static String getSadadSubscriptionUrl() {
        return BASE_URl + SADAD_SUBSCRIPTION;
    }
    public static String getmakeCareerPayment() {
        return BASE_URl + TPAYCAREERBILLING;
    }
    public static String getSadadPpvUrl() {
        return BASE_URl + SADAD_PPV;
    }

    public static String getMyRaveUrl() {
        return BASE_URl + RAVE_URL;
    }

    public static String getStripeScaUrl() {
        return BASE_URl + SCA_URL;
    }
    public static String getPaygateUrl() {
        return BASE_URl + PAYGATE_URL;
    }


    public static String getCodapaySubscriptionUrl() {
        return BASE_URl + CODAPAY_SUBSCRIPTION;
    }

    public static String getCodapayPpvUrl() {
        return BASE_URl + CODAPAY_PPV;
    }

    public static String getPayTabPPVUrl() {
        return BASE_URl + PAYTAB_PPV;
    }

    public static String getPayTabSubscriptionUrl() {
        return BASE_URl + PAYTAB_SUBSCRIPTION;
    }


    public static String getMonetbilSubscriptionUrl() {
        return BASE_URl + MONETBIL_SUBSCRIPTION;
    }

    public static String getOpenPPVUrl() {
        return BASE_URl + OPEN_PPV;
    }

    public static String getOpenSubscriptionUrl() {
        return BASE_URl + OPEN_SUBSCRIPTION;
    }

    public static String getLicenseTokenUrl() {
        return BASE_URl + LICENSE_TOKEN_URL;
    }

    public static String getStudioAuthToken() {
        return BASE_URl + GET_STUDIO_AUTH_TOKEN;
    }
    public static String getUpdateKidsModeStatusUrl() {
        return BASE_URl + UPDATE_KIDS_MODE_STATUS;
    }
    public static String getAdavanceContactUs() {
        return BASE_URl + ADAVANCE_CONTACT_US;
    }

    public static String getSupportUrl() {
        return BASE_URl + SUPPORT_URL;
    }
}

