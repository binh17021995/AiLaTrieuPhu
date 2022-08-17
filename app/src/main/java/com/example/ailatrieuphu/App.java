package com.example.ailatrieuphu;

import android.app.Application;

import androidx.room.Room;

import com.example.ailatrieuphu.db.question.AppDB;

public class App extends Application {
    private static App instance;
    private AppDB db;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(this,
                AppDB.class, "Question")
                .createFromAsset("db/Question").build();
    }

    public AppDB getDb() {
        return db;
    }

    public static App getInstance() {
        return instance;
    }
}
