package com.example.myapplication.adapter;

import com.example.myapplication.Models.MovieModel;
import com.example.myapplication.db.Items;

public interface MyClickListner {
    public void ClickMoveItem(MovieModel model);
    public void ClickFaveItem(Items model);
}
