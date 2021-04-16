package com.example.memevz;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.database.RoomDB;
import com.database.UserDB;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
private Button btn;
private TextView register, usernameEditText, passwordEditText;
private User user;
private StartUp startUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

    }

    private void login() {
        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        TextInputEditText mail = findViewById(R.id.username);
        TextInputEditText password = findViewById(R.id.password);
        RoomDB database = RoomDB.getInstance(this);
        UserDB user = database.userDao().getUser(mail.getText().toString());
        TextView errors = findViewById(R.id.username_error);
        if (user != null) {
            if (user.getPassword().equals(password.getText().toString())) {
                SharedPreferences s = getSharedPreferences("User", MODE_PRIVATE);
                SharedPreferences.Editor se = s.edit();
                se.putLong("user_id", user.getId()).apply();
                se.putBoolean("isLoggedIn", true).apply();
                openHomeActivity();
            }
            else {
                password.setError("Password does not match the user!");
            }

        }
        else {
            mail.setError("This user does not exist!");
        }
        findViewById(R.id.progress).setVisibility(View.INVISIBLE);
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        TextView slogan = findViewById(R.id.slogan);
        ImageView logo = findViewById(R.id.login_logo);
        Button login = findViewById(R.id.login);
        TextInputLayout password = findViewById(R.id.password_layout);
        TextInputLayout mail = findViewById(R.id.username_layout);
        TextView change = findViewById(R.id.change);

        Pair[] pairs = new Pair[6];
        pairs[0] = new Pair<View, String>((View) login, "login");
        pairs[1] = new Pair<View, String>((View) slogan, "slogan");
        pairs[2] = new Pair<View, String>((View) logo, "logo");
        pairs[3] = new Pair<View, String>((View) password, "password");
        pairs[4] = new Pair<View, String>((View) mail, "mail");
        pairs[5] = new Pair<View, String>((View) change, "change");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
        startActivity(intent, options.toBundle());
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}