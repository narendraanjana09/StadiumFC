package com.nsa.stadiumfc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.nsa.stadiumfc.databinding.ActivityMainBinding;
import com.nsa.stadiumfc.databinding.DrawerLayoutBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        navController= Navigation.findNavController(this,R.id.navHostFragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if(navDestination.getId()==R.id.mainFragment){
                    binding.logoTop.setVisibility(View.GONE);
                }else{
                    binding.logoTop.setVisibility(View.VISIBLE  );
                }

            }
        });

        binding.drawerMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.drawerLayout.isDrawerOpen(GravityCompat. START )) {
                    closeDrawer(null);
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });



    }
    @Override
    public void onBackPressed () {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat. START )) {
           closeDrawer(null);
        } else if(navController.getCurrentDestination().getId()==R.id.mainFragment){
            finish();
        }else{
            navController.navigate(R.id.mainFragment);
        }
    }


    public void closeDrawer(View view) {
        binding.drawerLayout.closeDrawer(GravityCompat. START ) ;
    }

    public void profile(View view) {
        closeDrawer(null);

        navController.navigate(R.id.profileFragment);
    }
    public void viewAtten(View view) {
        closeDrawer(null);
        navController.navigate(R.id.viewAttendanceFragment);
    }
    public void takeAtten(View view) {
        closeDrawer(null);
        navController.navigate(R.id.takeAttendanceFragment);
    }
}