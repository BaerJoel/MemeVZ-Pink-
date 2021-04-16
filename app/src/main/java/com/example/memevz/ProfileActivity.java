package com.example.memevz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.database.MemeDB;
import com.database.RoomDB;
import com.database.UserDB;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton btnHome, btnUpload, btnProfile, editProfile;
    private TextView username, email;
    private List likes = new ArrayList<>();
    private List dislikes = new ArrayList<>();
    private LinearLayout imgContainer;
    private UserDB user;
    private RoomDB database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        database = RoomDB.getInstance(this);
        //getUser
        RoomDB database = RoomDB.getInstance(this);
        SharedPreferences s = getSharedPreferences("User", MODE_PRIVATE);
        user = database.userDao().getUserByID(s.getLong("user_id", 1));

        //load User data
        TextView username = findViewById(R.id.profile_username);
        username.setText(user.getUsername());

        //assign profile elements
        imgContainer = (LinearLayout)findViewById(R.id.profile_picture_container);
        editProfile = (ImageButton) findViewById(R.id.profile_edit_button);
        Button logout = findViewById(R.id.btn_logout);


        //edit onClick
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditProfileActivity();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        insertImages();
        inserSCore();
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

    private void inserSCore() {
        TextView scoreView = findViewById(R.id.profile_score);
        String score;
        try {
            score = database.memeDao().getUserScore(user.getId()).toString();
        }
        catch (Exception e) {
             score = "0";
        }
        scoreView.setText(score);
    }

    private void logout() {
        SharedPreferences s = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor se = s.edit();
        se.putBoolean("isLoggedIn", false).apply();
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void insertImages() {
        List<MemeDB> myMemes = database.memeDao().getAllMemesFromUser(user.getId());
        for (int i = 0; i<myMemes.size(); i++) {
            final MemeDB meme = myMemes.get(i);
            final LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams lpLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            ImageView img = new ImageView(this);
            img.setImageBitmap(BitmapFactory.decodeByteArray(meme.getImage(), 0, meme.getImage().length));
            img.setBackground(getResources().getDrawable(R.drawable.button_shape));
            img.setPadding(5,5,5,5);
            LinearLayout.LayoutParams lpImg = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView text = new TextView(this);
            text.setText("likes: " + meme.getLikes() + "\ndislikes: " + meme.getDislikes());
            text.setTextSize(20);
            text.setTextColor(getResources().getColor(R.color.white));
            LinearLayout.LayoutParams lpText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lpText.setMarginStart(20);
            lpText.gravity = Gravity.CENTER_VERTICAL;

            final ImageButton delete = new ImageButton(this);
            delete.setImageDrawable(getResources().getDrawable(R.drawable.ic_delete));
            delete.setBackground(getResources().getDrawable(R.drawable.button_shape));
            LinearLayout.LayoutParams lpdelete = new LinearLayout.LayoutParams(150, 150);
            lpdelete.setMarginStart(40);
            lpdelete.gravity = Gravity.CENTER_VERTICAL;

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                    builder.setTitle("Are you sure you want to delete this meme?");
                    builder.setPositiveButton("Delete!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            imgContainer.removeView(ll);
                            database.memeDao().deleteMeme(meme);
                        }
                    });
                    builder.setNegativeButton("No!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
                    alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
                }
            });


            ll.addView(img, lpImg);
            ll.addView(text, lpText);
            ll.addView(delete, lpdelete);
            imgContainer.addView(ll, lpLayout);
            img.getLayoutParams().height = 700;
            img.getLayoutParams().width = 700;
        }
    }

    private void setNavigationBarColor() {
        btnHome.setBackgroundColor(Color.parseColor("#ba0051"));
        btnUpload.setBackgroundColor(Color.parseColor("#ba0051"));
        btnProfile.setBackgroundColor(Color.parseColor("#a10046"));
    }

    private void openEditProfileActivity() {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void openProfileActivity() {
    }

    private void openUploadActivity() {
        Intent intent = new Intent(this, UploadActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }
}