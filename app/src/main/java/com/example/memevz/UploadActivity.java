package com.example.memevz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.database.MemeDB;
import com.database.RoomDB;
import com.database.UserDB;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadActivity extends AppCompatActivity {

    private ImageButton btnHome, btnUpload, btnProfile;
    private Button upload, chooseBtn;
    private ImageView imageView;
    private Meme meme;
    private UserDB user;
    private Uri imageUri;
    private byte[] bArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        //get User
        RoomDB database = RoomDB.getInstance(this);
        SharedPreferences s = getSharedPreferences("User", MODE_PRIVATE);
        user = database.userDao().getUserByID(s.getLong("user_id", 1));

        //assign Upload Elements
        chooseBtn = (Button)findViewById(R.id.choose_img_btn);
        upload = (Button)findViewById(R.id.upload_btn);
        imageView = (ImageView)findViewById(R.id.upload_img_preview);

        //onClick events for Buttons
        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadMeme();
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

    private void uploadMeme() {
        try {
            RoomDB database = RoomDB.getInstance(this);
            MemeDB meme = new MemeDB();
            meme.setImage(bArray);
            meme.setDislikes(new Long(0));
            meme.setLikes(new Long(0));
            meme.setUploader_id(user.getId());
            database.memeDao().addMeme(meme);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.successful));
        }
        catch (Exception e) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.notsuccessful));
        }
    }

    private void selectImage() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
                btnUpload.setEnabled(true);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bos);
                bArray = bos.toByteArray();
                upload.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void setNavigationBarColor() {
        btnHome.setBackgroundColor(Color.parseColor("#ba0051"));
        btnUpload.setBackgroundColor(Color.parseColor("#a10046"));
        btnProfile.setBackgroundColor(Color.parseColor("#ba0051"));
    }

    private void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    private void openUploadActivity() {
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }
}