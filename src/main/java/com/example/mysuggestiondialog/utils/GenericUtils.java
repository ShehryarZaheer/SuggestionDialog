package com.example.mysuggestiondialog.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.mysuggestiondialog.models.SuggestionListItem;

import java.util.ArrayList;

public class GenericUtils {


    public static ArrayList<SuggestionListItem> getFilteredSuggestionListItems(ArrayList<SuggestionListItem> suggestionListItems, String searchQuery) {
        ArrayList<SuggestionListItem> filteredResults = new ArrayList<>();
        for (SuggestionListItem suggestionListItem : suggestionListItems) {
            if (suggestionListItem.getSuggestionText().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredResults.add(suggestionListItem);
            }
        }

        return filteredResults;
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
