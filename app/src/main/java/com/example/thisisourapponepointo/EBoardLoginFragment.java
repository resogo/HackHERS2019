package com.example.thisisourapponepointo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class EBoardLoginFragment extends android.app.Fragment {
    View rootView;
    Realm realm;

    private User user = MyApplication.myUser;

    Button signin;
    Button changePassword;
    Button setEmail;

    EditText passwordTxt;
    EditText newPasswordTxt;
    EditText emailTxt;

    private String signInTxt;
    private String password;
    private String newPassword;
    private String email;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_eboard_login, container, false);
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();

        passwordTxt = rootView.findViewById(R.id.editText_eboardmember_enter_password);
        newPasswordTxt = rootView.findViewById(R.id.editText2_eboard_member_change_password);
        emailTxt = rootView.findViewById(R.id.editText3_eboard_member_set_email);

        signin = rootView.findViewById(R.id.eboard_member_signin_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                    signInTxt = signIn();
                    Toast.makeText(getActivity(), signInTxt, Toast.LENGTH_LONG).show();
                    if (signInTxt.equals("Successfully signed in as E-Board Member!")) {
                        realm.beginTransaction();
                        user.setEBoard(true);
                        realm.copyToRealmOrUpdate(user);
                        realm.commitTransaction();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please sign in as a general member first", Toast.LENGTH_LONG).show();
                }

            }
        });
        changePassword = rootView.findViewById(R.id.eboard_member_change_password_button);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                    if(user.isEBoard){
                        Toast.makeText(getActivity(), "Successfully changed password. Please sign in for access.", Toast.LENGTH_LONG).show();
                        realm.beginTransaction();
                        MyApplication.eboard.setPassword(newPassword);
                        realm.copyToRealmOrUpdate(MyApplication.eboard);
                        realm.commitTransaction();
                    } else {
                        Toast.makeText(getActivity(), "Please sign in to E-Board first", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please sign in as a general member first", Toast.LENGTH_LONG).show();
                }
            }
        });
        setEmail = rootView.findViewById(R.id.eboard_member_set_email_button);
        setEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                    if(user.isEBoard){
                        Toast.makeText(getActivity(), "Successfully set email. Please sign in for access.", Toast.LENGTH_LONG).show();
                        realm.beginTransaction();
                        MyApplication.eboard.setEmail(email);
                        realm.copyToRealmOrUpdate(MyApplication.eboard);
                        realm.commitTransaction();
                    } else {
                        Toast.makeText(getActivity(), "Please sign in to E-Board first", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please sign in as a general member first", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    public String signIn() {

        if (isEmpty(passwordTxt)) {
            return "Please enter a password";
        } else {
             password = String.valueOf(passwordTxt.getText());
        }

        MyApplication.eboard = realm.where(EboardInfo.class).equalTo("password", password).findFirst();
        if (MyApplication.eboard != null) {
            return "Successfully signed in as E-Board Member!";
        } else {
            return "Password is incorrect";
        }
    }
}