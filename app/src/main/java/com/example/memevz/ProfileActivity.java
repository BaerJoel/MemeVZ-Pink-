package com.example.memevz;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton btnHome, btnUpload, btnProfile, editProfile;
    private User user;
    private TextView username, email;
    private List<Image> memes = new ArrayList<>();
    private List likes = new ArrayList<>();
    private List dislikes = new ArrayList<>();
    private LinearLayout imgContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //assign profile elements
        imgContainer = (LinearLayout)findViewById(R.id.profile_picture_container);
        editProfile = (ImageButton) findViewById(R.id.profile_edit_button);

        //edit onClick
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertImages();
            }
        });

        //assign the NavigationBar Buttons
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

    private void insertImages() {
        for (int i = 0; i<3; i++) {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams lpLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            ImageView img = new ImageView(this);
            img.setImageDrawable(getDrawable(R.drawable.memmot));
            LinearLayout.LayoutParams lpImg = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView text = new TextView(this);
            text.setText("likes: 50\ndislikes: 12");
            text.setTextSize(20);
            LinearLayout.LayoutParams lpText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lpText.setMarginStart(20);
            lpText.gravity = Gravity.CENTER_VERTICAL;

            ll.addView(img, lpImg);
            ll.addView(text, lpText);
            imgContainer.addView(ll, lpLayout);
            img.getLayoutParams().height = 700;
            img.getLayoutParams().width = 700;
        }
    }

    private void setNavigationBarColor() {
        btnHome.setImageResource(R.drawable.home);
        btnHome.setBackgroundColor(Color.parseColor("#ba0051"));
        btnUpload.setImageResource(R.drawable.upload);
        btnUpload.setBackgroundColor(Color.parseColor("#ba0051"));
        btnProfile.setImageResource(R.drawable.settedprofile);
        btnProfile.setBackgroundColor(Color.parseColor("#a10046"));
    }


    private void openProfileActivity() {
    }

    private void openUploadActivity() {
        Intent intent = new Intent(this, UploadActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}