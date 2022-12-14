package player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.home.vod.R;
import com.home.vod.util.FontUtls;

import java.util.ArrayList;

import player.utils.Util;

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

        // get the TextView for item name and item description
        TextView title = (TextView)convertView.findViewById(R.id.title);
        ImageView selection = (ImageView)convertView.findViewById(R.id.selected_subtitle);

        //sets the text for item name and item description from the current item object
        title.setText(items.get(position));

        if (items.get(position).contains(Util.DefaultSubtitle)) {
            selection.setVisibility(View.VISIBLE);
        } else {
            selection.setVisibility(View.INVISIBLE);
        }

        //Kushal
        if (convertView instanceof LinearLayout){
            LinearLayout l= (LinearLayout)convertView;
            if(items.get(position).toLowerCase().contains("off")){
                l.setId(R.id.off);
            }
        }

        FontUtls.loadFont(context,context.getResources().getString(R.string.regular_fonts),title);

        /*Typeface typeface = Typeface.createFromAsset(context.getAssets(),context.getResources().getString(R.string.regular_fonts));
        title.setTypeface(typeface);*/


        // returns the view for the current row
        return convertView;
    }
}
