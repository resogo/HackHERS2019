package com.example.thisisourapponepointo;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;


public class DrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    android.app.FragmentManager fragMan;
    android.app.FragmentTransaction fragTransaction;
    QRReaderFragment qrReaderFragment;
    private int mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        fragMan = getFragmentManager();
        mainLayout = R.id.content_frame;


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
                        }


                        return true;
                    }
                });
    }
    public void setupQRScanner(){
        fragTransaction = fragMan.beginTransaction();
        fragTransaction.replace(mainLayout, qrReaderFragment, null);
        fragTransaction.commit();
    }
}
