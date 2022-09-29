package com.home.vod.ui.adapter;

import static com.home.vod.preferences.LanguagePreference.DEFAULT_SELECTED_LANGUAGE_CODE;
import static com.home.vod.preferences.LanguagePreference.SELECTED_LANGUAGE_CODE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.home.vod.R;
import com.home.vod.model.LanguageModel;
import com.home.vod.preferences.LanguagePreference;

import java.util.ArrayList;

public class LanguageCustomAdapter extends RecyclerView.Adapter<LanguageCustomAdapter.ViewHolder> {

    public Context mContext;
    public ArrayList<LanguageModel> languageModels;
    LanguageSelectListener languageSelectListener;
    int selectedPosition = -1;



    public LanguageCustomAdapter(Context mContext, ArrayList<LanguageModel> languageModelArrayList, LanguageSelectListener languageSelectListener) {
        this.mContext = mContext;
        this.languageModels = languageModelArrayList;
        this.languageSelectListener = languageSelectListener;
        String code= LanguagePreference.getLanguagePreference(mContext).getTextofLanguage(SELECTED_LANGUAGE_CODE,
                DEFAULT_SELECTED_LANGUAGE_CODE);
        for (int i = 0; i < languageModels.size(); i++) {
            if (code.equals(languageModels.get(i).getLanguageId())) {
                languageModels.get(i).setIsSelected(true);
            }
            else {
                languageModels.get(i).setIsSelected(false);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.language_recycler_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.language.setText(languageModels.get(position).getLanguageName());

    if (languageModels.get(position).getIsSelected() == true) {
        holder.imageView.setImageResource(R.drawable.ic_radio_button_selected);
        selectedPosition = position;
        if (languageSelectListener!=null) languageSelectListener.onLanguageSelected(position);
    } else {
        holder.imageView.setImageResource(R.drawable.unselected);
    }

    }

    @Override
    public int getItemCount() {
        return languageModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView language;
        public ImageView imageView;

        public ViewHolder(View v) {

            super(v);
            imageView = v.findViewById(R.id.image);
            language = v.findViewById(R.id.language);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    try {
                        if (position!=selectedPosition) {
                            languageModels.get(position).setIsSelected(true);
                            if (selectedPosition >-1 && selectedPosition <languageModels.size()) {
                                languageModels.get(selectedPosition).setIsSelected(false);
                            }
                            selectedPosition = position;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }


    public interface LanguageSelectListener {
        void onLanguageSelected(int adapterPosition);
    }
}

