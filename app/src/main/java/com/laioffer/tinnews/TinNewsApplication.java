package com.laioffer.tinnews;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ashokvarma.gander.Gander;
import com.ashokvarma.gander.imdb.GanderIMDB;
import com.facebook.stetho.Stetho;

// ？？ 为什么要有这个class
public class TinNewsApplication extends Application {
    // singleton
    private TinNewsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        // Gander is a network logging tool to see HTTP request from the app
        Gander.setGanderStorage(GanderIMDB.getInstance());

        // Stetho is a debugging tool used to view network requests and local database content.
        Stetho.initializeWithDefaults(this);
        database = Room.databaseBuilder(this, TinNewsDatabase.class, "tinnews_db").build();
    }

    public TinNewsDatabase getDatabase() {
        return database;
    }
}
