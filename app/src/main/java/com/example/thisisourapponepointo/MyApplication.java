package com.example.thisisourapponepointo;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

public class MyApplication extends Application {

    public static User myUser;
    public static EboardInfo eboard = new EboardInfo();
    public static RealmList<EBoardMemberItem> realmList = new RealmList<>();

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

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        realm.beginTransaction();
        eboard.setPassword("sweasel");
        realm.copyToRealmOrUpdate(eboard);
        realm.commitTransaction();

    }

    public void onTerminate() {

        super.onTerminate();
    }
}
