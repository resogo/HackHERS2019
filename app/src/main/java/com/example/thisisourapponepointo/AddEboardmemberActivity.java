package com.example.thisisourapponepointo;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEboardmemberActivity extends AppCompatActivity {
    EditText title;
    EditText description;
    Uri image;
    Button gallery;
    Button camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_eboardmember);
        title = findViewById(R.id.add_eboard_member_title);
        description = findViewById(R.id.add_eboard_member_description);
        gallery = findViewById(R.id.add_eboard_through_gallery);
        camera = findViewById(R.id.add_eboard_through_camera);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    image = imageReturnedIntent.getData();
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    image = imageReturnedIntent.getData();
                }
                break;
        }
        Intent intentReturn = new Intent();
        intentReturn.putExtra("Title", title.getText().toString());
        intentReturn.putExtra("Description", description.getText().toString());
        intentReturn.putExtra("Image", image.toString());
        setResult(RESULT_OK, intentReturn);
    }
}
