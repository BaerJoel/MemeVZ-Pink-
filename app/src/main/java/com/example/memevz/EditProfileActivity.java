package com.example.memevz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.database.RoomDB;
import com.database.UserDB;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfileActivity extends AppCompatActivity {

    private UserDB user;
    private ImageButton back;
    private Button saveChanges;
    private TextInputEditText mail, username, newPassword1, newPassword2, oldPassword;
    private RoomDB database = RoomDB.getInstance(this);
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private UserDB updatedUser = new UserDB();

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
        if(isInputCorrect()) {
            database.userDao().updateUser(updatedUser);
            Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    public boolean isInputCorrect() {
        updatedUser.setId(user.getId());
        boolean mail = isMailCorrect();
        boolean username = isUsernameCorrect();
        boolean password;
        boolean newpasswort;
        boolean matchingPasswords;
        if (!oldPassword.getText().toString().equals("") || !newPassword1.getText().toString().equals("") || !newPassword2.getText().toString().equals("") ) {
            password = isOldPasswordCorrect();
            matchingPasswords = doesPasswordsMatch();
            newpasswort = isPasswordCorrect();
            if (password && matchingPasswords && newpasswort && username && mail) {
                updatedUser.setPassword(newPassword1.getText().toString());
                return true;
            }
            Log.i("TAG", "isInputCorrect: ##########################################1");
            return false;
        }
        else {
            if (mail && username) {
                updatedUser.setPassword(user.getPassword());
                return true;
            }
        }
        return false;
    }

    private boolean isOldPasswordCorrect() {
        if(user.getPassword().equals(oldPassword.getText().toString())) {
            return true;
        }
        oldPassword.setError("This is the wrong password!");
        return false;
    }

    public boolean isMailCorrect() {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail.getText().toString());
        if (mail.getText().toString().equals("")) {
            mail.setError("Please enter an e-mail!");
            return false;
        }
        else if (!matcher.find()) {
            mail.setError("This is no valid e-mail address");
            return false;
        }
        else {
            updatedUser.setMail(mail.getText().toString());
            return true;
        }
    }
    public boolean isUsernameCorrect() {
        if (username.getText().toString().equals("")) {
            username.setError("Please enter an username!");
            return false;
        }
        else if (username.getText().toString().length() < 3) {
            username.setError("Username must be longer than 3 characters");
            return false;
        }
        else {

            updatedUser.setUsername(username.getText().toString());
            return true;
        }
    }
    public boolean isPasswordCorrect() {
        if (newPassword1.getText().toString().equals("")) {
            newPassword1.setError("Please enter a password!");
            return false;
        }
        else if(newPassword1.getText().toString().length() <= 3) {
            newPassword1.setError("Password must be longer than 3 characters!");
            return false;
        }
        else {
            return true;
        }
    }
    public boolean doesPasswordsMatch() {
        if (newPassword1.getText().toString().equals(newPassword2.getText().toString())) {
            return true;
        }
        else {
            newPassword2.setError("Passwords does not match");
            return false;
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