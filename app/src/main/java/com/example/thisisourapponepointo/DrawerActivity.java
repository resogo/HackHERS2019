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
    SignUp signUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        fragMan = getFragmentManager();
        signInFragment = new SignIn();
        signUpFragment = new SignUp();
        mainLayout = R.id.content_frame;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navigationView = findViewById(R.id.nav_view);
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
                        switch (id){
                            case R.id.nav_qr_scanner: setupQRScanner();
                                break;
                            case R.id.nav_signin: setupSignIn();
                                break;
                            case R.id.nav_signup: setupSignUp();
                        }
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
