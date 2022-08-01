 package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myapplication.Models.MovieModel;
import com.example.myapplication.ViewModels.MoviewLisViewModel;
import com.example.myapplication.adapter.MoviesListAdapter;
import com.example.myapplication.fragments.ExploreFragment;
import com.example.myapplication.fragments.FavFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

 public class MainActivity extends AppCompatActivity {
     BottomNavigationView navigationView;
     public static MoviewLisViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container,new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);
        viewModel = new ViewModelProvider(this).get(MoviewLisViewModel.class);
        viewModel.InitDB(this);
        viewModel.makeApiCall();
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_explore:
                        fragment = new ExploreFragment();
                        break;
                    case R.id.nav_fav:
                        fragment = new FavFragment();
                        break;
                    case R.id.nav_search:
                        fragment = new SearchFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container,fragment).commit();
                return true;
            }
        });
    }
}