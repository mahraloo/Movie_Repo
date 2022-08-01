package com.example.myapplication.network;

import com.example.myapplication.Models.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("array_json.php")
    Call<List<MovieModel>> getMovieList();

}
