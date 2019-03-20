package com.example.mysuggestiondialog.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mysuggestiondialog.R;
import com.example.mysuggestiondialog.adapters.AdapterSuggestion;
import com.example.mysuggestiondialog.listeners.OnListItemClickListener;
import com.example.mysuggestiondialog.models.SuggestionListItem;
import com.example.mysuggestiondialog.utils.GenericUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentSuggestionDialog extends BaseDialogFragment implements OnListItemClickListener {

    RecyclerView rvSuggestions;

    TextView tvDialogTitle;

    EditText etSearch;

    String dialogTitle;


    public static final String SUGGESTIONS = "suggestions";
    public static final String DIALOG_TITLE = "dialog_title";
    public static final String HIDE_SEARCH_BAR = "hide_search_bar";


    FragmentSuggestionDialogListener fragmentSuggestionDialogListener;

    boolean hideSearchBar;
    ArrayList<SuggestionListItem> suggestionListItems;
    ArrayList<SuggestionListItem> filteredSuggestions;
    AdapterSuggestion adapterSuggestion;

    public FragmentSuggestionDialogListener getFragmentSuggestionDialogListener() {
        return fragmentSuggestionDialogListener;
    }

    public void setFragmentSuggestionDialogListener(FragmentSuggestionDialogListener fragmentSuggestionDialogListener) {
        this.fragmentSuggestionDialogListener = fragmentSuggestionDialogListener;
    }


    public static FragmentSuggestionDialog newInstance(String dialogTitle, ArrayList<? extends SuggestionListItem> suggestionListItems, boolean hideSearchBar, FragmentSuggestionDialogListener fragmentSuggestionDialogListener) {
        FragmentSuggestionDialog fragmentSuggestionDialog = new FragmentSuggestionDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SUGGESTIONS, suggestionListItems);
        bundle.putString(DIALOG_TITLE, dialogTitle);
        bundle.putBoolean(HIDE_SEARCH_BAR, hideSearchBar);
        fragmentSuggestionDialog.setFragmentSuggestionDialogListener(fragmentSuggestionDialogListener);

        fragmentSuggestionDialog.setArguments(bundle);
        return fragmentSuggestionDialog;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            suggestionListItems = (ArrayList<SuggestionListItem>) getArguments().getSerializable(SUGGESTIONS);
            dialogTitle = getArguments().getString(DIALOG_TITLE);
            hideSearchBar = getArguments().getBoolean(HIDE_SEARCH_BAR);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suggestion, container, false);
        ButterKnife.bind(this, view);

        initializeViews(view);

        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.rounded_dialog_background);
        if (suggestionListItems != null)
            filteredSuggestions = new ArrayList<>(suggestionListItems);
        else
            filteredSuggestions = new ArrayList<>();
        tvDialogTitle.setText(dialogTitle);

        if (hideSearchBar)
            etSearch.setVisibility(View.GONE);

        rvSuggestions.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterSuggestion = new AdapterSuggestion(getActivity(), suggestionListItems, this);
        rvSuggestions.setAdapter(adapterSuggestion);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filteredSuggestions = GenericUtils.getFilteredSuggestionListItems(suggestionListItems, etSearch.getText().toString());
                adapterSuggestion.setSuggestionListItems(filteredSuggestions);


            }
        });


        return view;
    }

    private void initializeViews(View view) {
        etSearch = view.findViewById(R.id.et_search);
        tvDialogTitle = view.findViewById(R.id.tv_dialog_title);
        rvSuggestions = view.findViewById(R.id.rv_suggestions);
    }

    @Override
    public void onListItemClicked(int index) {

        GenericUtils.hideKeyboard(getActivity(), getView());
        FragmentSuggestionDialog.this.dismiss();
        try {
            fragmentSuggestionDialogListener.onSuggestionSelected(index, filteredSuggestions.get(index).getSuggestionListItem());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface FragmentSuggestionDialogListener {

        public void onSuggestionSelected(int index, SuggestionListItem suggestionListItem
        );


    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, FragmentSuggestionDialog.this.toString());
    }


}
