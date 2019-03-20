package com.example.mysuggestiondialog.models;

import java.io.Serializable;

public interface SuggestionListItem extends Serializable {

    public enum SuggestionType {
        Country, StaticData,Specaility,City,GeneralSuggestionItem,State
    }

    public String getSuggestionText();

    public SuggestionListItem getSuggestionListItem();

    SuggestionType getSuggestionType();
}
