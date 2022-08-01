package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.Models.MovieModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.Viewholder>{

    private List<MovieModel> mMovies = new ArrayList<>();
    private Context mContext;
    private MyClickListner clickListner;
    public MoviesListAdapter(List<MovieModel> mMovies, Context mContext,MyClickListner clickListner) {
        this.mMovies = mMovies;
        this.mContext = mContext;
        this.clickListner = clickListner;
    }

    public void setMovieList(List<MovieModel> mMovies){
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_list,parent,false);
        Viewholder vh = new Viewholder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

//        holder.title.setText(mMovies.get(position).getTitle());

        //Glide
        RequestOptions options = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(options)
                .load(mMovies.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.image);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.ClickMoveItem(mMovies.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mMovies!=null){

            return mMovies.size();
        }
        return 0;
    }

    class Viewholder extends RecyclerView.ViewHolder{
        CardView card;
        ImageView image;
        TextView title;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.text);
        }
    }
}
