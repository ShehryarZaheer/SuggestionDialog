package com.example.mysuggestiondialog.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mysuggestiondialog.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public abstract class BaseDialogFragment extends DialogFragment {



    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow()

                .getAttributes().windowAnimations = getDialogAnimation();
        int width = getWidth();
        int height = getHeight();
        getDialog().getWindow().setLayout(width, height);
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public int getDialogAnimation() {
        return android.R.style.Animation_Dialog;
    }

    public int getWidth() {
        return MATCH_PARENT;
    }

    public int getHeight() {
        return WRAP_CONTENT;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, BaseDialogFragment.class.toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.rounded_dialog_background);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
