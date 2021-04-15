package com.example.memevz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.database.RoomDB;
import com.database.UserDB;
import com.google.android.material.textfield.TextInputEditText;

public class EditProfileActivity extends AppCompatActivity {

    private UserDB user;
    private ImageButton back;
    private Button saveChanges;
    private TextInputEditText mail, username, newPassword1, newPassword2, oldPassword;
    private RoomDB database = RoomDB.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.username);
        mail = findViewById(R.id.email);
        newPassword1 = findViewById(R.id.new_password);
        newPassword2 = findViewById(R.id.new_password2);
        oldPassword = findViewById(R.id.old_password);

        //get User
        SharedPreferences s = getSharedPreferences("User", MODE_PRIVATE);
        user = database.userDao().getUserByID(s.getLong("user_id", 1));

        back = (ImageButton)findViewById(R.id.btn_back_to_profile);
        saveChanges = (Button)findViewById(R.id.btn_save_changes);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
        insertUserData();
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        if ((oldPassword.getText().toString().equals("")) && (newPassword1.getText().toString().equals("") && (newPassword2.getText().toString().equals("")))) {
            UserDB updatedUser = new UserDB();
            updatedUser.setId(user.getId());
            if (!username.getText().toString().equals("")) {
                updatedUser.setUsername(username.getText().toString());
                if (!mail.getText().toString().equals("")) {
                    updatedUser.setMail(mail.getText().toString());
                    updatedUser.setPassword(user.getPassword());
                    database.userDao().updateUser(updatedUser);
                    Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    private void insertUserData() {
        username = findViewById(R.id.username);
        mail = findViewById(R.id.email);
        newPassword1 = findViewById(R.id.new_password);
        newPassword2 = findViewById(R.id.new_password2);
        oldPassword = findViewById(R.id.old_password);
        username.setText(user.getUsername());
        mail.setText(user.getMail());
        /*if ((!newPassword1.getText().equals("")) && (newPassword1.equals(newPassword2)) && oldPassword.equals(user.getPassword())) {

        }*/
    }

    private void openProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}