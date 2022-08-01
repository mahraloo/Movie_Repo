package com.example.myapplication.ViewModels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Models.MovieModel;
import com.example.myapplication.db.AppDataBese;
import com.example.myapplication.db.Items;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.Retroinstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MoviewLisViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> movieslist;
    private MutableLiveData<List<Items>> movieslistFavorite;
    private AppDataBese appDataBese;
    public MoviewLisViewModel() {
        this.movieslist = new MutableLiveData<>();
        this.movieslistFavorite = new MutableLiveData<>();
    }
    public void InitDB(Context context){
                appDataBese = AppDataBese.getDBInatance(context);

    }
    //Api server
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

    // Local Favorite
    public MutableLiveData<List<Items>> getMovieFavoriteListObserver(){
        getAllFavorite();
        return movieslistFavorite;}

    public void getAllFavorite(){
        List<Items> listofFavorite = appDataBese.favoriteList().getAllFavorits();
        if (listofFavorite.size()>0){
            movieslistFavorite.postValue(listofFavorite);
        }else{
            movieslistFavorite.postValue(null);
        }
    }
    public Items getFavWithId(int id){
        if (movieslistFavorite.getValue()!=null){
            for(int i =0 ; i<movieslistFavorite.getValue().size();i++){
                if (id == movieslistFavorite.getValue().get(i).id){
                    return movieslistFavorite.getValue().get(i);
                }
            }
        }

        return null;
    }
    public void insertFavoriteDB(MovieModel model){
        Items items = new Items();
        items.title = model.getTitle();
        items.director = model.getDirector();
        items.duration = model.getDuration();
        items.image = model.getImage();
        items.introduction = model.getIntroduction();
        items.rate = model.getRate();
        items.year = model.getYear();
        items.id = model.getId();
        appDataBese.favoriteList().insertitem(items);
        getAllFavorite();
    }

    public void DeletefromFavorite(MovieModel model){
        Items items = getFavWithId(model.getId());
        appDataBese.favoriteList().deleteitem(items);
        getAllFavorite();
    }


}
