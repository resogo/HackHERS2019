package com.example.thisisourapponepointo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import io.realm.Realm;

public class Home extends android.app.Fragment{
    ImageView qrcodeDisplay;
    Bitmap QRCode;
    Realm realm;
    User user;
    Button signout;
    User myUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home, container, false);
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        qrcodeDisplay = rootView.findViewById(R.id.home_qrcode_display_imageView);
        signout = rootView.findViewById(R.id.home_sign_out_button);
        myUser = com.example.thisisourapponepointo.MyApplication.myUser;
        //get Bitmap from data base if user is logged in
        //use CURRENT user DATA
        //user = realm.where(User.class).equalTo("email", email).findFirst();
        realm.beginTransaction();
        QRCode = myUser.getQRcode();
        qrcodeDisplay.setImageBitmap(QRCode);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myUser != null){
                    com.example.thisisourapponepointo.MyApplication.myUser = null;
                    Toast.makeText(getActivity(), "Signed Out", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Not Signed In", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;

    }
}
