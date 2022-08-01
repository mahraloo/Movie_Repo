package com.example.myapplication;

import static com.example.myapplication.MainActivity.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.Models.MovieModel;
import com.example.myapplication.ViewModels.MoviewLisViewModel;
import com.example.myapplication.db.Items;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;
//import com.skyhope.showmoretextview.ShowMoreTextView;

public class MainActivity2 extends AppCompatActivity {

    CardView back;
    TextView duration,year,rate,title,director,des;
    ShapeableImageView imageView;
    ImageView fav;
//    ShowMoreTextView introduction;
    RelativeLayout btn;

    private int id;
    boolean dark=true;
    boolean fav_check=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        back = findViewById(R.id.back);
        duration = findViewById(R.id.text_duration);
        year = findViewById(R.id.text_year);

        rate = findViewById(R.id.text_rate);
        title = findViewById(R.id.title);
        director = findViewById(R.id.director);
        imageView = findViewById(R.id.imagecover);
        fav = findViewById(R.id.fav);
        des = findViewById(R.id.text_introduction);

        back.setOnClickListener(v->{
            finish();
        });

        id = getIntent().getIntExtra("id",0);
        MovieModel model = viewModel.getMovieDetailWithId(id);
        Items items = viewModel.getFavWithId(id);
        if(model!=null){
            duration.setText(model.getDuration());
            rate.setText(model.getRate());
            year.setText(model.getYear());
            title.setText(model.getTitle());
            director.setText(model.getDirector());
            des.setText(model.getIntroduction());

            Glide.with(getBaseContext())
                    .setDefaultRequestOptions(RequestOptions.errorOf(R.drawable.ic_launcher_background))
                    .load(model.getImage())
                    .apply(RequestOptions.centerCropTransform())
                    .into(imageView);



            int nightModeFlags =
                    this.getResources().getConfiguration().uiMode &
                            Configuration.UI_MODE_NIGHT_MASK;
            switch (nightModeFlags) {
                case Configuration.UI_MODE_NIGHT_YES:
                    dark = true;
                    break;

                case Configuration.UI_MODE_NIGHT_NO:
                    dark = false;
                    break;

                case Configuration.UI_MODE_NIGHT_UNDEFINED:
                    dark = false;
                    break;
            }
            if (items!=null){
                    fav.setImageResource(R.drawable.fav_light_2);
                    fav_check = true;

            }
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dark){
                        if (fav_check){
                            fav.setImageResource(R.drawable.fav_dark_1);
                            viewModel.DeletefromFavorite(model);
                            fav_check = false;
                        }else{
                            fav.setImageResource(R.drawable.fav_light_2);
                            viewModel.insertFavoriteDB(model);
                            fav_check = true;
                        }
                    }else{
                        if (fav_check){
                            fav.setImageResource(R.drawable.fav_light_1);
                            viewModel.DeletefromFavorite(model);
                            fav_check = false;
                        }else{
                            fav.setImageResource(R.drawable.fav_light_2);
                            viewModel.insertFavoriteDB(model);
                            fav_check = true;
                        }
                    }
                }
            });

        }



    }

}