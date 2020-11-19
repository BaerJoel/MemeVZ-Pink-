package com.example.memevz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private ImageButton btnHome, btnProfile, btnUpload, like, dislike;
    private ImageView memeView;
    private Meme meme;
    private User user;

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
        ObjectAnimator animation = ObjectAnimator.ofFloat(memeView, "translationX", 1500f);
        animation.setDuration(100);
        animation.start();
        memeView.animate().alpha(1f).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        memeView.setX(0);
                    }
        }).start();
    }


    private void dislike() {
        ObjectAnimator animation = ObjectAnimator.ofFloat(memeView, "translationX", -1500f);
        animation.setDuration(130);
        animation.start();
        memeView.animate().alpha(1f).withEndAction(new Runnable() {
            @Override
            public void run() {
                memeView.setX(0);
            }
        }).start();
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
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void openUploadActivity() {
        Intent intent = new Intent(this, UploadActivity.class);
        /*intent.putExtra("userId", user.getUserId());
        intent.putExtra("userName", user.getUsername());*/
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void openHomeActivity() {
    }
}