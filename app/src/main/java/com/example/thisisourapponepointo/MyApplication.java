package com.example.thisisourapponepointo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        realm = Realm.getDefaultInstance();

    }

    public void onTerminate() {
        realm.beginTransaction();

        realm.commitTransaction();

        super.onTerminate();
    }
}
