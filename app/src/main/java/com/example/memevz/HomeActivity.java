package com.example.memevz;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.database.MemeDB;
import com.database.RoomDB;

import java.util.List;


public class HomeActivity extends AppCompatActivity {

    private int count = 0;

    private ImageButton btnHome, btnProfile, btnUpload, like, dislike;
    private ImageView memeView;
    private MemeDB meme;
    private User user;
    private RoomDB database;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = RoomDB.getInstance(this);
        //assign HomeScreen elements
        memeView = (ImageView) findViewById(R.id.image);
        meme = database.memeDao().getAllMemes().get(0);
        memeView.setImageBitmap(BitmapFactory.decodeByteArray(database.memeDao().getAllMemes().get(0).getImage(), 0, database.memeDao().getAllMemes().get(0).getImage().length));

        setupLikeOrDislikeActions();

        setupNavigation();
    }

    private void setupLikeOrDislikeActions() {
        like = (ImageButton)findViewById(R.id.like_button);
        dislike = (ImageButton) findViewById(R.id.dislike_button);

        //(dis)like onClick Events
        like.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    like.setImageResource(R.drawable.like5);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    like.setImageResource(R.drawable.like3);
                    like();
                }
                return false;
            }
        });
        dislike.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dislike.setImageResource(R.drawable.dislike5);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    dislike.setImageResource(R.drawable.dislike3);
                    dislike();
                }
                return false;
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
    }

    private void setupNavigation() {
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

    private void insertImage() {
        List<MemeDB> memes = database.memeDao().getAllMemes();
        count++;
        if (count <= memes.size()-1) {
            meme = memes.get(count);
            memeView.setImageBitmap(BitmapFactory.decodeByteArray(meme.getImage(), 0, meme.getImage().length));
        }
        else {
            meme = null;
            memeView.setImageDrawable(getResources().getDrawable(R.drawable.nomemes));
        }

    }

    private void like() {
        try {
            meme.setLikes(meme.getLikes() + 1);
            database.memeDao().updateMeme(meme);
        }
        catch (Exception e) {

        }
        ObjectAnimator animation = ObjectAnimator.ofFloat(memeView, "translationX", 1500f);
        animation.setDuration(100);
        animation.start();
        memeView.animate().alpha(1f).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        insertImage();
                        memeView.setX(0);
                    }
        }).start();
    }


    private void dislike() {
        try {
            meme.setDislikes(meme.getDislikes() + 1);
            database.memeDao().updateMeme(meme);
        }
        catch (Exception e) {

        }
        ObjectAnimator animation = ObjectAnimator.ofFloat(memeView, "translationX", -1500f);
        animation.setDuration(130);
        animation.start();
        memeView.animate().alpha(1f).withEndAction(new Runnable() {
            @Override
            public void run() {
                insertImage();
                memeView.setX(0);
            }
        }).start();
    }

    private void setNavigationBarColor() {
        btnHome.setBackgroundColor(Color.parseColor("#a10046"));
        btnUpload.setBackgroundColor(Color.parseColor("#ba0051"));
        btnProfile.setBackgroundColor(Color.parseColor("#ba0051"));
    }

    private void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    private void openUploadActivity() {
        Intent intent = new Intent(this, UploadActivity.class);
        /*intent.putExtra("userId", user.getUserId());
        intent.putExtra("userName", user.getUsername());*/
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    private void openHomeActivity() {
    }
}