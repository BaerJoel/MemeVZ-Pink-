package com.example.memevz;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
private Button btn;
private TextView register, usernameEditText, passwordEditText;
private User user;
private StartUp startUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

    }

    private void openRegisterActivity() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        TextView slogan = findViewById(R.id.slogan);
        ImageView logo = findViewById(R.id.login_logo);
        Button login = findViewById(R.id.login);
        TextInputLayout password = findViewById(R.id.password_layout);
        TextInputLayout mail = findViewById(R.id.username_layout);

        Pair[] pairs = new Pair[5];
        pairs[0] = new Pair<View, String>((View) login, "login");
        pairs[1] = new Pair<View, String>((View) slogan, "slogan");
        pairs[2] = new Pair<View, String>((View) logo, "logo");
        pairs[3] = new Pair<View, String>((View) password, "password");
        pairs[4] = new Pair<View, String>((View) mail, "mail");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
        startActivity(intent, options.toBundle());
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}