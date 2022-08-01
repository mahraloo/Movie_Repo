package com.example.myapplication.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Items {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "director")
    public String director;

    @ColumnInfo(name = "year")
    public String year;

    @ColumnInfo(name = "duration")
    public String duration;

    @ColumnInfo(name = "rate")
    public String rate;

    @ColumnInfo(name = "introduction")
    public String introduction;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "id")
    public int id;


}

