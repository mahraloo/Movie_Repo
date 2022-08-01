package com.example.myapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Items.class} , version = 1)
public abstract class AppDataBese extends RoomDatabase {

    public abstract FavoriteList favoriteList();
    public static  AppDataBese Instance;
    public static AppDataBese getDBInatance(Context context){
        if (Instance == null){
            Instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBese.class,"AppDb")
                    .allowMainThreadQueries()
                    .build();
        }
        return Instance;
    }

}
