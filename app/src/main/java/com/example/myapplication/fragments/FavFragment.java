package com.example.myapplication.fragments;

import static com.example.myapplication.MainActivity.viewModel;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Models.MovieModel;
import com.example.myapplication.R;
import com.example.myapplication.adapter.FavoriteListAdapter;
import com.example.myapplication.adapter.MoviesListAdapter;
import com.example.myapplication.adapter.MyClickListner;
import com.example.myapplication.db.Items;

import java.util.List;

public class FavFragment extends Fragment implements MyClickListner {


    public FavFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    TextView result;
    SearchView searchView;
    FavoriteListAdapter adapter;
    private List<Items> movieModelList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        recyclerView = view.findViewById(R.id.recycler_fav);
        result = view.findViewById(R.id.no_result);
        searchView = view.findViewById(R.id.searchbar);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new FavoriteListAdapter(movieModelList,getActivity(), this);
        recyclerView.setAdapter(adapter);

            viewModel.getMovieFavoriteListObserver().observe(getActivity(), new Observer<List<Items>>() {
                @Override
                public void onChanged(List<Items> items) {
                    if(items!=null){
                        movieModelList = items;
                        adapter.setMovieList(items);
                        result.setVisibility(View.GONE);
                    }else{
                        result.setVisibility(View.VISIBLE);
                        //Toast.makeText(getActivity(), "No Result Found :(", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        return view;
    }

    @Override
    public void ClickMoveItem(MovieModel model) {

    }

    @Override
    public void ClickFaveItem(Items model) {

    }
}