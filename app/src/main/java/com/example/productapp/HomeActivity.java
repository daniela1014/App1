package com.example.productapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private Button addProductButton, viewProductsButton, logoutButton;
    private SharedPreferences sharedPreferences;

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;
    ImageView logoImage;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);

        addProductButton = findViewById(R.id.addProductButton);
        viewProductsButton = findViewById(R.id.viewProductsButton);
        logoutButton = findViewById(R.id.logoutButton);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);

        addProductButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddProductActivity.class)));
        viewProductsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewProductsActivity.class)));
        logoutButton.setOnClickListener(v -> logoutUser());

        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });

        View headerView = navigationView.getHeaderView(0);
        logoImage = headerView.findViewById(R.id.logoImage);
        TextView textUsername = headerView.findViewById(R.id.textUsername);

        logoImage.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, textUsername.getText(), Toast.LENGTH_SHORT).show();
            }
            });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int iteamId = item.getItemId();

                if (iteamId == R.id.navProduct){
                    Toast.makeText(MainActivity.this, "Product clicked", Toast.LENGTH_SHORT).show();
                }


                drawerLayout.close();

                return false;
            }
        });
    }

    private void logoutUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.putString("email", null);
        editor.apply();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }



}
