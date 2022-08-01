package com.example.myapplication.fragments;

import static com.example.myapplication.MainActivity.viewModel;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MainActivity2;
import com.example.myapplication.Models.MovieModel;
import com.example.myapplication.R;
import com.example.myapplication.ViewModels.MoviewLisViewModel;
import com.example.myapplication.adapter.MoviesListAdapter;
import com.example.myapplication.adapter.MyClickListner;
import com.example.myapplication.db.Items;

import java.util.List;


public class HomeFragment extends Fragment implements MyClickListner {


    public HomeFragment() {
        // Required empty public constructor
    }

    private List<MovieModel> movieModelList;
    private RecyclerView recyclerView;
    private MoviesListAdapter adapterMovie;


    private View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.recyeler);
//        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterMovie = new MoviesListAdapter(movieModelList,getActivity(), this);
        recyclerView.setAdapter(adapterMovie);

        viewModel.getMovieListObserver().observe(getActivity(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels!=null){
                    movieModelList = movieModels;
                    adapterMovie.setMovieList(movieModels);
                }else{

                    Toast.makeText(getActivity(), "No Result Found :(", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return v;
    }


    @Override
    public void ClickMoveItem(MovieModel model) {
        Intent intent = new Intent(getActivity(), MainActivity2.class);
        intent.putExtra("id",model.getId());
        getActivity().startActivity(intent);
    }

    @Override
    public void ClickFaveItem(Items model){
        //TODO...Nothing
    }
}