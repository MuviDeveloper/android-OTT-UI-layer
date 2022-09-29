package com.home.vod.ui.adapter;

import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;
import static com.home.vod.util.Constant.authTokenStr;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.home.apisdk.apiController.GetUserProfileAsynctask;
import com.home.apisdk.apiModel.Get_UserProfile_Input;
import com.home.apisdk.apiModel.Get_UserProfile_Output;
import com.home.apisdk.apiModel.HomeFeaturePageBannerModel;
import com.home.vod.R;
import com.home.vod.handlers.ListItemAllignmentHandler;
import com.home.vod.model.SectionDataModel;
import com.home.vod.model.SingleItemModel;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.preferences.PreferenceManager;
import com.home.vod.ui.fragment.HomeFragment;
import com.home.vod.ui.widgets.CustomTextSliderView;
import com.home.vod.util.Constant;
import com.home.vod.util.DataController;
import com.home.vod.util.FontUtls;
import com.home.vod.util.Util;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder>
        implements GetUserProfileAsynctask.Get_UserProfileListener {

    private Context mContext;

    private ArrayList<String> bannerUrls;
    private ArrayList<String> arrayListFeedUrls;
    private ArrayList<HomeFeaturePageBannerModel> homePageBannerModelArrayList;
    private ArrayList<SingleItemModel> singleSectionItems;
    private ArrayList<SectionDataModel> dataList;

    private LanguagePreference languagePreference;
    private PreferenceManager preferenceManager;

    private ListItemAllignmentHandler listItemAllignmentHandler;
    private HomeFragment mFragment;
    private onViewMoreClickListener onViewMoreClick;
    private SectionListDataAdapter.onSectionItemClickListener onSectionItemClick;
    private CustomTextSliderView.PlaybuttonclickListener playbuttonclickListener;

    private String loggedInStr;
    private int planListSize;
    private String isSubscribed = "0";
    private String ipAddress;
    public String messageResponse = "";
    public boolean isScrolling = false;


    public RecyclerViewDataAdapter(Context context, String ipAddress, ArrayList<SectionDataModel> dataList, ArrayList<String> bannerUrls, ArrayList<String> arrayList_FeedUrls,
                                   ArrayList<HomeFeaturePageBannerModel> homePageBannerModelArrayList, int vertical, HomeFragment fragment,
                                   onViewMoreClickListener listener, SectionListDataAdapter.onSectionItemClickListener sectionListener, CustomTextSliderView.PlaybuttonclickListener playbuttonclickListener) {
        this.dataList = dataList;
        this.ipAddress = ipAddress;
        this.mContext = context;
        this.bannerUrls = bannerUrls;
        this.arrayListFeedUrls = arrayList_FeedUrls;
        this.homePageBannerModelArrayList = homePageBannerModelArrayList;
        this.listItemAllignmentHandler = new ListItemAllignmentHandler(mContext);
        this.mFragment = fragment;
        this.onViewMoreClick = listener;
        this.onSectionItemClick = sectionListener;
        this.playbuttonclickListener = playbuttonclickListener;

        preferenceManager = PreferenceManager.getPreferenceManager(mContext);
        languagePreference = LanguagePreference.getLanguagePreference(mContext);

        loggedInStr = preferenceManager.getUseridFromPref();
        if (preferenceManager.getUseridFromPref() != null) {
            callProfileAPI();
        }

    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        float density = mContext.getResources().getDisplayMetrics().density;
        if (density >= 1.5 && density <= 3.0) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_small, null);
            ItemRowHolder mh = new ItemRowHolder(v);
            return mh;

        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
            ItemRowHolder mh = new ItemRowHolder(v);
            return mh;
        }
    }

    @Override
    public void onBindViewHolder(final ItemRowHolder itemRowHolder, final int i) {
        try {

            if (dataList.size() < 1) {
                itemRowHolder.section_title_layout.setVisibility(View.GONE);
                itemRowHolder.recycler_view_list.setVisibility(View.GONE);
            } else {
                itemRowHolder.section_title_layout.setVisibility(View.VISIBLE);
                itemRowHolder.recycler_view_list.setVisibility(View.VISIBLE);
            }
            String sectionName = null;
            String sectionId = null;
            if (dataList != null && dataList.size() > 0) {
                sectionName = dataList.get(i).getHeaderTitle().trim();
                sectionId = dataList.get(i).getHeaderPermalink();

                singleSectionItems = dataList.get(i).getAllItemsInSection();

                FontUtls.loadFont(mContext, mContext.getResources().getString(R.string.regular_fonts), itemRowHolder.itemTitle);
                itemRowHolder.itemTitle.setText(sectionName.trim());
                listItemAllignmentHandler.setAllignment(itemRowHolder.itemTitle);
            }

            SectionListDataAdapter itemListDataAdapter = null;

            if (Util.image_orentiation != null
                    && i < Util.image_orentiation.size()
                    && Util.image_orentiation.get(i) == Constant.IMAGE_PORTAIT_CONST) {
                float density = mContext.getResources().getDisplayMetrics().density;
                if (density >= 3.5 && density <= 4.0) {
                    itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems, R.layout.list_single_card, mFragment, onSectionItemClick);
                } else if (density <= 1.5) {
                    itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems, R.layout.list_single_card_small, mFragment, onSectionItemClick);
                } else {
                    itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems, R.layout.list_single_card_nexus, mFragment, onSectionItemClick);
                }

            } else {
                {
                    itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems, R.layout.home_280_card, mFragment, onSectionItemClick);
                }
            }


            itemRowHolder.recycler_view_list.setHasFixedSize(true);
            itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);

            /*
             * @Desc: Here checking for icon rotation according to RTL support
             * */
            Configuration config = mContext.getResources().getConfiguration();
            if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                Drawable img = itemRowHolder.btnMore.getContext().getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_black_rtl);
                itemRowHolder.btnMore.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
            } else {
                Drawable img = itemRowHolder.btnMore.getContext().getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_black);
                itemRowHolder.btnMore.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
            }


            String finalSectionId1 = sectionId;
            String finalSectionName1 = sectionName;
            itemRowHolder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onViewMoreClick.onItemClick(finalSectionName1, finalSectionId1);
                }
            });

            String finalSectionName2 = sectionName;
            String finalSectionId2 = sectionId;
            itemRowHolder.btnMoreText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onViewMoreClick.onItemClick(finalSectionName2, finalSectionId2);
                }
            });


            if (i == 0) {

                itemRowHolder.mDemoSliderLayout.setVisibility(View.VISIBLE);
                //@desc: this condition is work when i==0 and when scroll refresh is called that time this function called to reload banner section

                if (!isScrolling) {
                    itemRowHolder.loadBanner();
                }
                isScrolling = false;

            } else {
                itemRowHolder.mDemoSliderLayout.setVisibility(View.GONE);
            }

            if (singleSectionItems.size() <= 0) {
                itemRowHolder.itemTitle.setVisibility(View.GONE);
                itemRowHolder.btnMore.setVisibility(View.GONE);

                //As discussed with Abhinav the btn more shoul be visible if more than 5 contents come
            } else if (singleSectionItems.size() <= Integer.parseInt(mContext.getResources().getString(R.string.Feature_section_limit))) {
                itemRowHolder.btnMore.setVisibility(View.GONE);
                itemRowHolder.btnMoreText.setVisibility(View.GONE);
                itemRowHolder.itemTitle.setVisibility(View.VISIBLE);
            } else {

                {
                    itemRowHolder.btnMore.setVisibility(View.VISIBLE);
                    itemRowHolder.itemTitle.setVisibility(View.VISIBLE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getItemCount() {
        if (Util.image_orentiation.size() < 1 && bannerUrls.size() >= 1)
            return 1;
        else
            return this.dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class ItemRowHolder extends RecyclerView.ViewHolder {
        protected TextView itemTitle;
        protected RecyclerView recycler_view_list;
        protected Button btnMore;
        protected TextView btnMoreText;
        private RelativeLayout mDemoSliderLayout, section_title_layout;
        private LinearLayout linearLayoutRecyclerSlider;
        private TextView textViewSeparator;
        private AutoScrollViewPager autoScrollViewPager;
        PageIndicatorView pageIndicatorView;

        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = view.findViewById(R.id.sectiontitle);
            this.section_title_layout = view.findViewById(R.id.section_title_layout);
            this.recycler_view_list = view.findViewById(R.id.featureContent);
            this.btnMore = view.findViewById(R.id.viewall);
            this.btnMoreText = view.findViewById(R.id.viewall_text);

            mDemoSliderLayout = view.findViewById(R.id.sliderRelativeLayout);
            linearLayoutRecyclerSlider = view.findViewById(R.id.linearLayoutRecyclerSlider);
            textViewSeparator = view.findViewById(R.id.lineTextView);
            pageIndicatorView = view.findViewById(R.id.pageIndicatorView);
            autoScrollViewPager = view.findViewById(R.id.auto_scroll_view_pager);

        }

        public void loadBanner() {
            ArrayList<String> arrayListUrlType = new ArrayList<>();
            for (int i = 0; i < arrayListFeedUrls.size(); i++) {
                //1:Image 0:Video
                if (!arrayListFeedUrls.get(i).equals("")) {
                    arrayListUrlType.add("0");
                } else {
                    arrayListUrlType.add("1");
                }
            }

            boolean isLogIn = preferenceManager.getUseridFromPref() != null;

            ViewPager_Adapter myViewPagerAdapter = new ViewPager_Adapter(mContext, bannerUrls, arrayListFeedUrls, arrayListUrlType, initGlide(), ipAddress, homePageBannerModelArrayList.size(), isLogIn, homePageBannerModelArrayList, playbuttonclickListener);
            autoScrollViewPager.setAdapter(myViewPagerAdapter);

            pageIndicatorView.setVisibility(View.VISIBLE);
            pageIndicatorView.setCount(myViewPagerAdapter.getCount());
            pageIndicatorView.setViewPager(autoScrollViewPager);

            autoScrollViewPager.startAutoScroll(3000);
            autoScrollViewPager.setInterval(3000);
            autoScrollViewPager.setCycle(true);

        }

        private RequestManager initGlide() {
            RequestOptions options = new RequestOptions();
            return Glide.with(mContext)
                    .setDefaultRequestOptions(options);
        }

    }

    @Override
    public void onGet_UserProfilePreExecuteStarted() {

    }

    @Override
    public void onGet_UserProfilePostExecuteCompleted(Get_UserProfile_Output get_userProfile_output, int code, String message, String status) {
        if (status == null) {
            isSubscribed = "0";
        }

        if (code == 200) {
            isSubscribed = get_userProfile_output.getIsSubscribed();

            SharedPreferences sharedprefPlanList = mContext.getSharedPreferences("myPlanListPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorPlanList = sharedprefPlanList.edit();
            editorPlanList.putString("isSubscribed", isSubscribed);
            editorPlanList.commit();

        }

    }

    public void callProfileAPI() {
        Get_UserProfile_Input get_userProfile_input = new Get_UserProfile_Input();
        get_userProfile_input.setAuthToken(authTokenStr);
        get_userProfile_input.setUser_id(preferenceManager.getUseridFromPref());
        get_userProfile_input.setEmail(preferenceManager.getEmailIdFromPref());
        get_userProfile_input.setLang_code(languagePreference.getTextofLanguage(SELECTED_LANGUAGE_CODE, DEFAULT_SELECTED_LANGUAGE_CODE));
        //code start: country code and language code added mantish
        get_userProfile_input.setCountryCode(preferenceManager.getCountryCodeFromPref());
        GetUserProfileAsynctask asynLoadProfileDetails
                = new GetUserProfileAsynctask(get_userProfile_input, RecyclerViewDataAdapter.this, mContext, false, new DataController(mContext));
        asynLoadProfileDetails.execute();
    }


    public interface onViewMoreClickListener {
        void onItemClick(String SectionName, String sectionId);
    }

}