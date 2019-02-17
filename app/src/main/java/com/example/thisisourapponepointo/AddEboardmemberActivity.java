package com.example.thisisourapponepointo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;

public class AddEboardmemberActivity extends AppCompatActivity {
    EditText title;
    EditText description;
    Uri image;
    Button gallery;
    Button camera;
    Button submit;
    private String mCurrentPhotoPath;
    Bitmap mImageBitmap;
    byte[] imgArray = null;
    final Activity activity = this;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_eboardmember);
        realm = Realm.getDefaultInstance();
        title = findViewById(R.id.add_eboard_member_title);
        description = findViewById(R.id.add_eboard_member_description);
        gallery = findViewById(R.id.add_eboard_through_gallery);
        camera = findViewById(R.id.add_eboard_through_camera);
        submit = findViewById(R.id.add_eboard_member_add_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                mImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                imgArray = stream.toByteArray();
                EBoardMemberItem eBoardMemberItem = new EBoardMemberItem(title.getText().toString(), description.getText().toString(),imgArray);
                realm.beginTransaction();
                MyApplication.realmList.add(eBoardMemberItem);
                realm.commitTransaction();
                activity.finish();
                //intentReturn.putExtra("Image", image.toString());
                //Toast.makeText(AddEboardmemberActivity.this, "isNull: "+(mImageBitmap==null), Toast.LENGTH_SHORT).show();
            }
        });
        //camera.setVisibility(View.GONE);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
                Toast.makeText(AddEboardmemberActivity.this, "Camera Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
                Toast.makeText(AddEboardmemberActivity.this, "Gallery Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = imageReturnedIntent.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                mImageBitmap = BitmapFactory.decodeStream(imageStream);
                imageStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(AddEboardmemberActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(AddEboardmemberActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
}
