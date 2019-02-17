package com.example.thisisourapponepointo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class SignIn extends android.app.Fragment {

    Realm realm;

    private User user;

    private String email;

    private Button signInButton;
    private EditText emailTxt;

    private String signInTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sign_in, container, false);
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();

        emailTxt = (EditText) rootView.findViewById(R.id.input_email_signin);


        signInButton = (Button) rootView.findViewById(R.id.button_sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInTxt = signIn();
                Toast.makeText(getActivity(), signInTxt, Toast.LENGTH_LONG).show();
                if (signInTxt.equals("Successfully signed in!")) {
                    realm.beginTransaction();
                    user.setAttendance(user.getAttendance() + 1);
                    realm.copyToRealmOrUpdate(user);
                    realm.commitTransaction();
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), DrawerActivity.class);
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    public String signIn() {

        if (isEmpty(emailTxt)) {
            return "Please enter an email";
        } else {
            email = email = String.valueOf(emailTxt.getText());
        }

        user = realm.where(User.class).equalTo("email", email).findFirst();
        if (user != null) {
            return "Successfully signed in!";
        } else {
            return "Email is incorrect or user does not exist. Please try again or sign up.";
        }
    }

}


