package com.home.vod.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.home.apisdk.apiModel.CategoryOutputModel;
import com.home.vod.R;

import java.util.ArrayList;

/**
 * Created by MUVI on 13-Sep-18.
 */

public class UGCFilterAdapter  extends RecyclerView.Adapter<UGCFilterAdapter.MyViewHolder>  {
    public static int prevPosition = 0;
    private Context context;

    private ArrayList<CategoryOutputModel> categoryList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView filterTextName;
        private ImageView selectButton;
        private RelativeLayout listviewLay1;

        public MyViewHolder(View view) {
            super(view);
            filterTextName =  view.findViewById(R.id.listTextView);
            selectButton =  view.findViewById(R.id.listCheckBox);
            listviewLay1 = view.findViewById(R.id.listviewLay1);

        }
    }


    public UGCFilterAdapter(ArrayList<CategoryOutputModel> categoryList, Context context) {
        this.context = context;
        this.categoryList = categoryList;

    }

    @Override
    public UGCFilterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_row_layout, parent, false);

        return new UGCFilterAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UGCFilterAdapter.MyViewHolder holder, final int position) {

        CategoryOutputModel categoryOutputModel = categoryList.get(position);

        holder.filterTextName.setText(categoryOutputModel.getCategoryName());
        if(categoryOutputModel.isSelected()){
            holder.selectButton.setImageResource(R.drawable.ic_check_box_selected);
        }else{
            holder.selectButton.setImageResource(R.drawable.ic_check_box_default);
        }

        holder.filterTextName.setTextColor(context.getResources().getColor(R.color.filterTextColor));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


}
