package com.example.ezyfood3.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ezyfood3.R;
import com.example.ezyfood3.models.CartItem;
import com.example.ezyfood3.viewmodels.DrinkViewModel;

import java.util.List;

public class drink extends AppCompatActivity {

    private static final String TAG = "drink";
    NavController navController;
    DrinkViewModel drinkViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        navController = Navigation.findNavController(this,R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this,navController);
        drinkViewModel = new ViewModelProvider(this).get(DrinkViewModel.class);
        drinkViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                Log.d(TAG, "onChanged: " + cartItems.size());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController)|| super.onOptionsItemSelected(item);
    }
}