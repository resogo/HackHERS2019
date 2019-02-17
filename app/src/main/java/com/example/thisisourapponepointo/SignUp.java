package com.example.thisisourapponepointo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import io.realm.Realm;

public class SignUp extends android.app.Fragment {

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
    private String urlInfo;

    private String signUpTxt;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sign_up, container, false);
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();

        firstNameTxt = (EditText) rootView.findViewById(R.id.input_first_name);
        lastNameTxt = (EditText) rootView.findViewById(R.id.input_last_name);
        emailTxt = (EditText) rootView.findViewById(R.id.input_email_signup);
        majorSpinner = (Spinner) rootView.findViewById(R.id.major_dropdown);


        signUpButton = (Button) rootView.findViewById(R.id.button_sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpTxt = signUp();
                Toast.makeText(getActivity(), signUpTxt,
                        Toast.LENGTH_LONG).show();
                if (signUpTxt.equals("Successfully signed up!")) {
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

    public String signUp() {
        if (isEmpty(firstNameTxt)) {
            return "Please enter your first name";
        } else {
            firstName = String.valueOf(firstNameTxt.getText());
        }

        if (isEmpty(lastNameTxt)) {
            return "Please enter your last name";
        } else {
            lastName = String.valueOf(lastNameTxt.getText());
        }

        if (isEmpty(emailTxt)) {
            return "Please enter an email";
        } else {
            email = String.valueOf(emailTxt.getText());
        }

        if(majorSpinner.getSelectedItem().toString().equals("Select your major")){
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
            String data = "NAME: "+firstName+ " "+lastName+"EMAIL: "+email;
            urlInfo = "http://api.qrserver.com/v1/create-qr-code/?data="+data+"&size=100x100";
            GetImage getImage = new GetImage();
            getImage.execute(urlInfo);
            realm.copyToRealmOrUpdate(user);
            realm.commitTransaction();
            MyApplication.myUser = user;

            return "Successfully signed up!";
        }
    }
    private class GetImage extends  AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap myBitmap = null;

            try {
                URL imgURL = new URL(urlInfo);
                HttpURLConnection connection = (HttpURLConnection) imgURL.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return myBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //bitmap generated (QRCode = bitmap)

            user.setQRcode(bitmap);

        }
    }
}
