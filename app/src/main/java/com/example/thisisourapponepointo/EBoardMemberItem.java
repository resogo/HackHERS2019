package com.example.thisisourapponepointo;

import android.graphics.Bitmap;
import android.net.Uri;

public class EBoardMemberItem {
    private String title;
    private String description;
    private Uri image;

    public EBoardMemberItem(String t, String d, Uri img){
        title = t;
        description = d;
        image = img;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
