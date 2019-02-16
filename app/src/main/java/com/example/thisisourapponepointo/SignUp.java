package com.example.thisisourapponepointo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import io.realm.Realm;

public class SignUp extends AppCompatActivity {

    Realm realm;

    private User user;

    private String firstName;
    private String lastName;
    private String email;
    private String major;

    private Button signUpButton;
    private EditText firstNameTxt;
    private EditText lastNameTxt;
    private EditText emailTxt;
    private Spinner majorSpinner;

    private String signUpTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        realm = Realm.getDefaultInstance();

        firstNameTxt = (EditText) findViewById(R.id.input_first_name);
        lastNameTxt = (EditText) findViewById(R.id.input_last_name);
        emailTxt = (EditText) findViewById(R.id.input_email);
        majorSpinner = (Spinner) findViewById(R.id.major_dropdown);


        signUpButton = (Button) findViewById(R.id.button_sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpTxt = signUp();
                Toast.makeText(SignUp.this, signUpTxt,
                        Toast.LENGTH_LONG).show();
                if (signUpTxt.equals("Successfully signed up!")) {
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(user);
                    realm.commitTransaction();
                    Intent intent = new Intent();
                    intent.setClass(SignUp.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    public String signUp() {
        if (isEmpty(firstNameTxt)) {
            firstNameTxt.requestFocus();
            firstNameTxt.selectAll();
            return "Please enter your first name";
        } else {
            firstName = String.valueOf(firstNameTxt.getText());
        }

        if (isEmpty(lastNameTxt)) {
            lastNameTxt.requestFocus();
            lastNameTxt.selectAll();
            return "Please enter your last name";
        } else {
            lastName = String.valueOf(lastNameTxt.getText());
        }

        if (isEmpty(emailTxt)) {
            emailTxt.requestFocus();
            emailTxt.selectAll();
            return "Please enter an email";
        } else {
            email = String.valueOf(emailTxt.getText());
        }

        if(!majorSpinner.isSelected() || majorSpinner.getSelectedItem().toString().equals("Select your major")){
            return "Please select your major";
        } else {
            major = String.valueOf(majorSpinner.getSelectedItem());
        }

        user = realm.where(User.class).equalTo("email", email).findFirst();
        if (user != null) {
            return "User already exists. Please sign in.";
        } else {
            realm.beginTransaction();
            user = new User();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setMajor(major);
            realm.copyToRealmOrUpdate(user);
            realm.commitTransaction();
            return "Successfully signed up!";
        }

        //test
    }




}
