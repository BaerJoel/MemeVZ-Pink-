package com.example.memevz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditProfileActivity extends AppCompatActivity {

    private User user;
    private String eMail, username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }
}