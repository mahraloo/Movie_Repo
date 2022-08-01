package com.example.myapplication.db;

import android.content.ClipData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoriteList {

    @Query("Select * from Items")
    List<Items> getAllFavorits();

    @Insert
    void insertitem(Items...items);

    @Update
    void updateitem(Items items);

    @Delete
    void deleteitem(Items items);

}
