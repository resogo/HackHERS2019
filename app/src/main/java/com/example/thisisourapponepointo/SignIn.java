package com.example.thisisourapponepointo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class SignIn extends AppCompatActivity {

    Realm realm;

    private User user;

    private String email;

    private Button signInButton;
    private EditText emailTxt;

    private String signInTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        realm = Realm.getDefaultInstance();

        emailTxt = (EditText) findViewById(R.id.input_email);


        signInButton = (Button) findViewById(R.id.button_sign_up);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInTxt = signIn();
                Toast.makeText(SignIn.this, signInTxt,
                        Toast.LENGTH_LONG).show();
                if (signInTxt.equals("Successfully signed in!")) {
                    realm.beginTransaction();
                    user.setAttendance(user.getAttendance() + 1);
                    realm.copyToRealmOrUpdate(user);
                    realm.commitTransaction();
                    Intent intent = new Intent();
                    intent.setClass(SignIn.this, DrawerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    public String signIn() {

        if (isEmpty(emailTxt)) {
            emailTxt.requestFocus();
            emailTxt.selectAll();
            return "Please enter an email";
        }

        user = realm.where(User.class).equalTo("email", email).findFirst();
        if (user != null) {
            return "Successfully signed in!";
        } else {
            emailTxt.requestFocus();
            emailTxt.selectAll();
            return "Email is incorrect or user does not exist. Please try again or sign up.";
        }
    }

}


