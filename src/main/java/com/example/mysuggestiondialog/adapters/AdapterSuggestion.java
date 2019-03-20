package com.example.mysuggestiondialog.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mysuggestiondialog.R;
import com.example.mysuggestiondialog.listeners.OnListItemClickListener;
import com.example.mysuggestiondialog.models.SuggestionListItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdapterSuggestion extends RecyclerView.Adapter<AdapterSuggestion.SuggestionViewholder> {

    Context context;
    ArrayList<? extends SuggestionListItem> suggestionListItems;

    OnListItemClickListener onListItemClickListener;

    public AdapterSuggestion(Context context, ArrayList<? extends SuggestionListItem> suggestionListItems, OnListItemClickListener onListItemClickListener) {
        this.context = context;
        this.suggestionListItems = suggestionListItems;

        this.onListItemClickListener = onListItemClickListener;
    }

    @Override
    public SuggestionViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_suggestion,
                parent, false);
        return new SuggestionViewholder(view);
    }

    @Override
    public void onBindViewHolder(SuggestionViewholder holder, final int position) {
        holder.tvSuggestion.setText(suggestionListItems.get(position).getSuggestionText());
        holder.clMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListItemClickListener.onListItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        int size = suggestionListItems.size();
        return size;
    }

    public class SuggestionViewholder extends RecyclerView.ViewHolder {
        TextView tvSuggestion;
        ConstraintLayout clMainLayout;

        public SuggestionViewholder(View itemView) {
            super(itemView);
            tvSuggestion = itemView.findViewById(R.id.tv_suggestion);
            clMainLayout = itemView.findViewById(R.id.main_layout);
        }
    }

    public void setSuggestionListItems(ArrayList<SuggestionListItem> suggestionListItems) {
        this.suggestionListItems = suggestionListItems;
        notifyDataSetChanged();
    }


}
