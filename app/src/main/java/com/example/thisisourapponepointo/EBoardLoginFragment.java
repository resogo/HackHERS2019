package com.example.thisisourapponepointo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EBoardLoginFragment extends android.app.Fragment {
    View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_eboard_login, container, false);
        super.onCreate(savedInstanceState);
        return rootView;
    }
}