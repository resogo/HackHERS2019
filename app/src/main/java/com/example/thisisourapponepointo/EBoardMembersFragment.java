package com.example.thisisourapponepointo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EBoardMembersFragment extends android.app.Fragment {
    View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_eboard_members, container, false);
        super.onCreate(savedInstanceState);
        return rootView;
    }
}