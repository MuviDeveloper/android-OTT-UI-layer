package com.home.vod.ui.adapter;

import static com.home.vod.preferences.LanguagePreference.ABOUT_US;
import static com.home.vod.preferences.LanguagePreference.CONTACT_US;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_ABOUT_US;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_CONTACT_US;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_HOME;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MY_DOWNLOAD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MY_LIBRARY;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_MY_UPLOAD;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_SETTINGS;
import static com.home.vod.preferences.LanguagePreference.DEFAULT_WATCH_HISTORY;
import static com.home.vod.preferences.LanguagePreference.HOME;
import static com.home.vod.preferences.LanguagePreference.MY_DOWNLOAD;
import static com.home.vod.preferences.LanguagePreference.MY_LIBRARY;
import static com.home.vod.preferences.LanguagePreference.MY_UPLOAD;
import static com.home.vod.preferences.LanguagePreference.SETTINGS;
import static com.home.vod.preferences.LanguagePreference.WATCH_HISTORY;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.home.vod.R;
import com.home.vod.preferences.LanguagePreference;
import com.home.vod.util.Util;

import java.util.ArrayList;
import java.util.HashMap;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> expandableListTitle;
    private ArrayList<String> idArrayList;
    private HashMap<String, ArrayList<String>> expandableListDetail;

    public ExpandableListAdapter(Context context, ArrayList<String> idArrayList, ArrayList<String> expandableListTitle,
                                 HashMap<String, ArrayList<String>> expandableListDetail
                                ) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.idArrayList=idArrayList;

    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.idArrayList.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item1, null);

        }
        TextView expandedListTextView = convertView.findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.idArrayList.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.idArrayList.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView1, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        final ImageView iconimage;
        View convertView = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView listTitleTextView = convertView.findViewById(R.id.listTitle);
        listTitleTextView.setText(Html.fromHtml(listTitle));
        LanguagePreference languagePreference = LanguagePreference.getLanguagePreference(context);
        if(convertView instanceof LinearLayout){
            LinearLayout l = (LinearLayout) convertView;
            if (listTitleTextView.getText().toString().equals(languagePreference.getTextofLanguage(CONTACT_US,DEFAULT_CONTACT_US)))
                l.setId(R.id.contact_us);
            else  if (listTitleTextView.getText().toString().equals(languagePreference.getTextofLanguage(HOME,DEFAULT_HOME)))
                l.setId(R.id.home);
            else if  (listTitleTextView.getText().toString().equals(languagePreference.getTextofLanguage(ABOUT_US,DEFAULT_ABOUT_US))){
                l.setId(R.id.about_us);
            }else if  (listTitleTextView.getText().toString().equals(languagePreference.getTextofLanguage(WATCH_HISTORY,DEFAULT_WATCH_HISTORY))){
                l.setId(R.id.watch_history);
            }else if  (listTitleTextView.getText().toString().equals(languagePreference.getTextofLanguage(MY_DOWNLOAD,DEFAULT_MY_DOWNLOAD))){
                l.setId(R.id.my_download);
            } if  (listTitleTextView.getText().toString().equals(languagePreference.getTextofLanguage(MY_LIBRARY,DEFAULT_MY_LIBRARY))){
                l.setId(R.id.my_library);
            }else if  (listTitleTextView.getText().toString().equals(languagePreference.getTextofLanguage(MY_UPLOAD,DEFAULT_MY_UPLOAD))){
                l.setId(R.id.my_uploads);
            }else if  (listTitleTextView.getText().toString().equals(languagePreference.getTextofLanguage(SETTINGS,DEFAULT_SETTINGS))){
                l.setId(R.id.settings);
            }
        }


        iconimage = convertView.findViewById(R.id.submenu);
        if (expandableListDetail!=null && expandableListDetail.get(this.idArrayList.get(listPosition)).size() > 0) {
            iconimage.setVisibility(View.VISIBLE);
        }



        //for expand less and expand the child content
        //***for this we have clear drawer_collapse in splashscreen and create a arraylist for imageview which is declare in Util
        //****in NavigationdrawerFragment we have two method ongroupcollapse and ongroupexpand ther we written logic( expnad and less)
        try {
            boolean add_to_array = true;
            for (int k = 0; k < Util.drawer_collapse_expand_imageview.size(); k++) {
                String[] Data = Util.drawer_collapse_expand_imageview.get(k).split(",");
                if (listPosition == Integer.parseInt(Data[0])) {
                    add_to_array = false;
                }
            }
            if (add_to_array) {
                Util.drawer_collapse_expand_imageview.add(listPosition + "," + Util.image_compressed);
            }
            String[] expand_collapse_image_info = Util.drawer_collapse_expand_imageview.get(listPosition).split(",");
            if (listPosition == Integer.parseInt(expand_collapse_image_info[0]) && Integer.parseInt(expand_collapse_image_info[1]) == 1) {
                iconimage.setImageResource(R.drawable.ic_arrow_up); //ic_remove_black_24dp

            } else if (listPosition == Integer.parseInt(expand_collapse_image_info[0]) && Integer.parseInt(expand_collapse_image_info[1]) == 0) {
                iconimage.setImageResource(R.drawable.ic_arrow_down);// ic_add_black_24dp

            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return convertView;

    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}
