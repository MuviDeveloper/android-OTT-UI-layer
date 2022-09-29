package com.home.vod.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.home.apisdk.apiModel.AdvanceContactUsOutput;
import com.home.vod.R;

import java.util.ArrayList;

public class SubQueriesAdapter extends RecyclerView.Adapter<SubQueriesAdapter.SubQueryViewHolder>{
    Context context;
    ArrayList<AdvanceContactUsOutput.SubqueryList> sub_quearyList;
    private int lastSelectedPosition = -1;
    SubQueryListener listener;


    public SubQueriesAdapter(Context context, ArrayList<AdvanceContactUsOutput.SubqueryList> sub_quearyList,SubQueryListener listener) {
        this.context = context;
        this.sub_quearyList = sub_quearyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SubQueryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advance_query_itemlist, parent, false);
        return new SubQueryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubQueryViewHolder holder, int position) {

        AdvanceContactUsOutput.SubqueryList queryList = sub_quearyList.get(position);
        Typeface regularTypeface = Typeface.createFromAsset(context.getAssets(),context.getResources().getString(R.string.fonts_regular));
        holder.quryCheckBox.setTypeface(regularTypeface);
        holder.quryCheckBox.setChecked(lastSelectedPosition == position);
        holder.bind(queryList,listener);
    }

    @Override
    public int getItemCount() {
        return sub_quearyList.size();
    }

    public class SubQueryViewHolder extends RecyclerView.ViewHolder{
        RadioButton quryCheckBox;
        public SubQueryViewHolder(@NonNull View itemView) {
            super(itemView);
            quryCheckBox = itemView.findViewById(R.id.query_name);

        }

        public void bind(AdvanceContactUsOutput.SubqueryList queryList, SubQueryListener listener) {
            quryCheckBox.setText(queryList.getSub_queryName());
            quryCheckBox.setOnClickListener(v -> {
                lastSelectedPosition = getAdapterPosition();
                notifyDataSetChanged();
                listener.onSubqueryExcute(queryList.getSub_queryName(), true);
            });
        }
    }
    public interface SubQueryListener{
        void onSubqueryExcute(String sub_query_type,Boolean validCheckbox);
    }
}