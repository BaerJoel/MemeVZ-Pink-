package com.example.memevz;

import android.content.Intent;
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

import java.io.IOException;

public class UploadActivity extends AppCompatActivity {

    private ImageButton btnHome, btnUpload, btnProfile;
    private Button upload, chooseBtn;
    private ImageView imageView;
    private Meme meme;
    private User user;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


            private void setNavigationBarColor() {
        btnHome.setImageResource(R.drawable.home);
        btnHome.setBackgroundColor(Color.parseColor("#ba0051"));
        btnUpload.setImageResource(R.drawable.settedupload);
        btnUpload.setBackgroundColor(Color.parseColor("#a10046"));
        btnProfile.setImageResource(R.drawable.profile);
        btnProfile.setBackgroundColor(Color.parseColor("#ba0051"));
    }

    private void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void openUploadActivity() {
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}