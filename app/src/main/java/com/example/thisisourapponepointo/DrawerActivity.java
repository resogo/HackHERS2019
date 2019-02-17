package com.example.thisisourapponepointo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class DrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    android.app.FragmentManager fragMan;
    android.app.FragmentTransaction fragTransaction;
    QRReaderFragment qrReaderFragment;
    private int mainLayout;
    SignIn signInFragment;
    Home homeFragment;
    SignUp signUpFragment;
    EBoardMembersFragment eBoardMembersFragment;
    EventsFragment eventsFragment;
    MentorMatchMainFragment mentorMatchMainFragment;
    SuggestionFragment suggestionFragment;
    SocialMediaFragment socialMediaFragment;
    ApparelFragment apparelFragment;
    EBoardLoginFragment eBoardLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        fragMan = getFragmentManager();
        signInFragment = new SignIn();
        signUpFragment = new SignUp();
        qrReaderFragment = new QRReaderFragment();
        eventsFragment = new EventsFragment();
        homeFragment = new Home();
        mainLayout = R.id.content_frame;
        eBoardMembersFragment = new EBoardMembersFragment();
        mentorMatchMainFragment = new MentorMatchMainFragment();
        suggestionFragment = new SuggestionFragment();
        socialMediaFragment = new SocialMediaFragment();
        apparelFragment = new ApparelFragment();
        eBoardLoginFragment = new EBoardLoginFragment();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        fragTransaction = fragMan.beginTransaction();
        fragTransaction.replace(mainLayout, homeFragment, null);
        fragTransaction.commit();
        NavigationView navigationView = findViewById(R.id.nav_view);
        setupHome();
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        fragTransaction = fragMan.beginTransaction();
                        switch (id){
                            case R.id.nav_home: fragTransaction.replace(mainLayout, homeFragment, null);
                                break;
                            case R.id.nav_signup: fragTransaction.replace(mainLayout, signUpFragment, null);
                                break;
                            case R.id.nav_signin: fragTransaction.replace(mainLayout, signInFragment, null);
                                break;
                            case R.id.nav_eboard_list: fragTransaction.replace(mainLayout, eBoardMembersFragment, null);
                                break;
                            case R.id.nav_events: fragTransaction.replace(mainLayout, eventsFragment, null); //incomplete
                                break;
                            case R.id.nav_mentor: fragTransaction.replace(mainLayout, mentorMatchMainFragment, null);//incomplete
                                break;
                            case R.id.nav_suggestions: fragTransaction.replace(mainLayout, suggestionFragment, null);//incomplete
                                break;
                            case R.id.nav_social_media: fragTransaction.replace(mainLayout, socialMediaFragment, null);//incomplete
                                break;
                            case R.id.nav_apparel: fragTransaction.replace(mainLayout, apparelFragment, null);//incomplete
                                break;
                            case R.id.nav_eboard_login: fragTransaction.replace(mainLayout, eBoardLoginFragment, null);//incomplete
                                break;
                            case R.id.nav_qr_scanner: fragTransaction.replace(mainLayout, qrReaderFragment, null);
                                break;
                        }
                        fragTransaction.commit();
                        return true;
                    }
                });


        if(com.example.thisisourapponepointo.MyApplication.myUser != null){
            navigationView.getMenu().findItem(R.id.nav_signin).setEnabled(false);
            navigationView.getMenu().findItem(R.id.nav_signin).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_signup).setEnabled(false);
            navigationView.getMenu().findItem(R.id.nav_signup).setVisible(false);
        }

    }
    public void setupHome(){
        fragTransaction = fragMan.beginTransaction();
        fragTransaction.replace(mainLayout, homeFragment, null);
        fragTransaction.commit();
    }
    public void setupQRScanner(){
        fragTransaction = fragMan.beginTransaction();
        fragTransaction.replace(mainLayout, qrReaderFragment, null);
        fragTransaction.commit();
    }
    public void setupSignIn(){
        fragTransaction = fragMan.beginTransaction();
        fragTransaction.replace(mainLayout, signInFragment, null);
        fragTransaction.commit();
    }

    public void setupSignUp() {
        fragTransaction = fragMan.beginTransaction();
        fragTransaction.replace(mainLayout, signUpFragment, null);
        fragTransaction.commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
