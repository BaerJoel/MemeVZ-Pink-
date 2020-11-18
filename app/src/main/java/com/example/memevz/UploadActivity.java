package com.example.memevz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UploadActivity extends AppCompatActivity {

    private ImageButton btnHome, btnUpload, btnProfile;
    private Image image;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        //assign NavigationBar Buttons
        btnHome = (ImageButton)findViewById(R.id.btn_nav_home);
        btnUpload = (ImageButton)findViewById(R.id.btn_nav_upload);
        btnProfile = (ImageButton)findViewById(R.id.btn_nav_profile);

        //correct the NavigationBar colors
        setNavigationBarColor();

        //NavigationBar onClick Events
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUploadActivity();
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });
    }


    private void setNavigationBarColor() {
        btnHome.setImageResource(R.drawable.home);
        btnUpload.setImageResource(R.drawable.settedupload);
        btnProfile.setImageResource(R.drawable.profile);
    }

    private void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void openUploadActivity() {
        Intent intent = new Intent(this, UploadActivity.class);
        startActivity(intent);
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}