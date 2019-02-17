package com.example.thisisourapponepointo;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setAttendance(1);
                    realm.copyToRealmOrUpdate(user);
                    realm.commitTransaction();
                    MyApplication.myUser = user;

                    getActivity().getFragmentManager().popBackStack();
                    //beginTransaction().replace( new Home(), null).commit();
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
            realm.copyToRealmOrUpdate(user);
            realm.commitTransaction();
            String data = "NAME: "+firstName+ " "+lastName+"EMAIL: "+email;
            System.out.print(data);
            urlInfo = "http://api.qrserver.com/v1/create-qr-code/?data="+data+"&size=100x100";
            GetImage getImage = new GetImage();
            getImage.execute(urlInfo);
            MyApplication.myUser = user;

            return "Successfully signed up!";
        }
    }
    private class GetImage extends  AsyncTask<String,Void, byte[]>{

        @Override
        protected byte[] doInBackground(String... params) {
            Bitmap myBitmap = null;
            byte[] imgArray = null;

            try {
                URL imgURL = new URL(urlInfo);
                HttpURLConnection connection = (HttpURLConnection) imgURL.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imgArray = stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imgArray;
        }

        @Override
        protected void onPostExecute(byte[] img) {
            super.onPostExecute(img);
            //bitmap generated (QRCode = bitmap)

            realm.beginTransaction();
            user.setQRcode(img);
            System.out.print(img);
            realm.copyToRealmOrUpdate(user);
            realm.commitTransaction();

        }
    }
}
