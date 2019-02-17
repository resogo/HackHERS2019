package com.example.thisisourapponepointo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.realm.RealmList;

import static android.app.Activity.RESULT_OK;

public class EBoardMembersFragment extends android.app.Fragment {
    View rootView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RealmList<EBoardMemberItem> eboardMembers;

    FloatingActionButton add;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_eboard_members, container, false);
        super.onCreate(savedInstanceState);
        //check if in dataBase if not:
        eboardMembers = MyApplication.realmList;

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.eboard_recycler_view);
        add = rootView.findViewById(R.id.eboard_add_to_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(eboardMembers, getContext());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),AddEboardmemberActivity.class);
                getActivity().startActivityForResult(intent,0);
            }
        });
        return rootView;
    }
    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if(resultCode == RESULT_OK) {
                String title = data.getStringExtra("Title");
                String description = data.getStringExtra("Description");
                Uri img = Uri.parse(data.getStringExtra("Image"));
            }
        }
    }*/
}