package com.example.memevz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private ImageButton btnHome, btnProfile, btnUpload, like, dislike;
    private ImageView memeView;
    private Image meme;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //assign HomeScreen elements
        memeView = (ImageView) findViewById(R.id.image);
        like = (ImageButton)findViewById(R.id.like_button);
        dislike = (ImageButton) findViewById(R.id.dislike_button);


        //(dis)like onClick Events
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like();
            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dislike();
            }
        });

        //swipe action
        memeView.setOnTouchListener(new OnSwipeTouchListener(HomeActivity.this) {
            @Override
            public void onSwipeRight() {
                like();
            }
            @Override
            public void onSwipeLeft() {
                dislike();
            }
        });

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


    private void like() {
        if(like.getTag() == "dislike") {
            like.setImageResource(R.drawable.like);
            like.setTag("like");
        }
        else {
            like.setImageResource(R.drawable.dislike);
            like.setTag("dislike");
        }
    }


    private void dislike() {
        if(dislike.getTag() == "like") {
            dislike.setImageResource(R.drawable.dislike);
            dislike.setTag("dislike");
        }
        else {
            dislike.setImageResource(R.drawable.like);
            dislike.setTag("like");
        }
    }

    private void setNavigationBarColor() {
        btnHome.setImageResource(R.drawable.settedhome);
        btnHome.setBackgroundColor(Color.parseColor("#a10046"));
        btnUpload.setImageResource(R.drawable.upload);
        btnUpload.setBackgroundColor(Color.parseColor("#ba0051"));
        btnProfile.setImageResource(R.drawable.profile);
        btnProfile.setBackgroundColor(Color.parseColor("#ba0051"));
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