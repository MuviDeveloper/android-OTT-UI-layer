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

public class QueriesAdapter extends RecyclerView.Adapter<QueriesAdapter.QueryViewHolder>{
    Context context;
    ArrayList<AdvanceContactUsOutput> quearyList;
    QueryListener listener;
    private int lastSelectedPosition = -1;


    public QueriesAdapter(Context context, ArrayList<AdvanceContactUsOutput> quearyList, QueryListener listener) {
        this.context = context;
        this.quearyList = quearyList;
        this.listener= listener;
    }

    @NonNull
    @Override
    public QueryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advance_query_itemlist, parent, false);
        return new QueryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueryViewHolder holder, int position) {

        AdvanceContactUsOutput query = quearyList.get(position);
        holder.quryCheckBox.setChecked(lastSelectedPosition == position);
        holder.bind(query,listener);

    }

    @Override
    public int getItemCount() {
        return quearyList.size();
    }

    public class QueryViewHolder extends RecyclerView.ViewHolder{
        RadioButton quryCheckBox;
        public QueryViewHolder(@NonNull View itemView) {
            super(itemView);
            quryCheckBox = itemView.findViewById(R.id.query_name);

        }

        public void bind(AdvanceContactUsOutput query, QueryListener listener) {
            Typeface regularTypeface = Typeface.createFromAsset(context.getAssets(),context.getResources().getString(R.string.fonts_regular));
            quryCheckBox.setTypeface(regularTypeface);
            quryCheckBox.setText(query.getQueryName());
            quryCheckBox.setOnClickListener(v -> {
                lastSelectedPosition = getAdapterPosition();
                notifyDataSetChanged();
                listener.radioButtonExcute(query.getSubQueries(),query.getQueryType(),true);
            });

        }
    }

    public interface QueryListener{
        void radioButtonExcute(ArrayList<AdvanceContactUsOutput.SubqueryList> subqueryLists,String query_type,Boolean validCheckbox);
    }
}
