package com.example.myapplication.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Models.MovieModel;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.Retroinstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MoviewLisViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> movieslist;
    public MoviewLisViewModel() {
        this.movieslist = new MutableLiveData<>();
    }

    public MutableLiveData<List<MovieModel>> getMovieListObserver(){
        return movieslist;
    }
    public MovieModel getMovieDetailWithId(int id){
        if (movieslist.getValue()!=null){
            for(int i =0 ; i<movieslist.getValue().size();i++){
                if (id == movieslist.getValue().get(i).getId()){
                    return movieslist.getValue().get(i);
                }
            }
        }

        return null;
    }
    public void makeApiCall(){
        ApiService apiService = Retroinstance.getRetrofitClient().create(ApiService.class);
        Call<List<MovieModel>> call = apiService.getMovieList();
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                movieslist.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                movieslist.postValue(null);
            }
        });
    }
}
