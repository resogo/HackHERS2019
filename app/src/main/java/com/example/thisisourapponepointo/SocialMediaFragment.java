package com.example.thisisourapponepointo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SocialMediaFragment extends android.app.Fragment {
    View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_social_media, container, false);
        super.onCreate(savedInstanceState);

        Button site = rootView.findViewById(R.id.button_site);
        site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.swe.rutgers.edu/"));
                startActivity(intent);
            }
        });

        Button facebook = rootView.findViewById(R.id.button_facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/SWERutgers/"));
                startActivity(intent);
            }
        });

        Button insta = rootView.findViewById(R.id.button_insta);
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/swerutgers/"));
                startActivity(intent);
            }
        });

        Button twitter = rootView.findViewById(R.id.button_twitter);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://twitter.com/SWERutgers"));
                startActivity(intent);
            }
        });

        Button groupme = rootView.findViewById(R.id.button_groupme);
        groupme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://web.groupme.com/join_group/43167231/Atb6oR"));
                startActivity(intent);
            }
        });


        return rootView;
    }
}