package com.example.thisisourapponepointo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.realm.Realm;

public class EBoardLoginFragment extends android.app.Fragment {
    View rootView;
    Realm realm;
    private User user;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_eboard_login, container, false);
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        //realm.beginTransaction();
        //realm.copyToRealmOrUpdate(isEBoard);
        //realm.commitTransaction();

        Button signin = rootView.findViewById(R.id.eboard_member_signin_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button changePassword = rootView.findViewById(R.id.eboard_member_change_password_button);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button setEmail = rootView.findViewById(R.id.eboard_member_set_email_button;
        setEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rootView;
    }
}