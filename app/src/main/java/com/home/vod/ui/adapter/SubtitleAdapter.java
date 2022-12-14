package com.home.vod.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.home.vod.R;
import com.home.vod.util.FontUtls;

import java.util.ArrayList;

/**
 * Created by MUVI on 3/10/2017.
 */


public class SubtitleAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<String> items; //data source of the list adapter

    //public constructor
    public SubtitleAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.subtitile_list_layout, parent, false);
        }

        TextView title = convertView.findViewById(R.id.title);
        title.setText(items.get(position));
        FontUtls.loadFont(context,context.getResources().getString(R.string.regular_fonts),title);
        return convertView;
    }
}
