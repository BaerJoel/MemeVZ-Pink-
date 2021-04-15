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

import com.database.RoomDB;
import com.database.UserDB;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private Button btn;
    private TextView signIn;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signIn = (TextView) findViewById(R.id.change);
        btn = (Button) findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openHomeActivity();
                register();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignInActivity();
            }
        });
    }

    private void register() {
        UserDB user = new UserDB();
        TextInputEditText mail = findViewById(R.id.email);
        TextInputEditText username = findViewById(R.id.username);
        TextInputEditText password1 = findViewById(R.id.password);
        TextInputEditText password2 = findViewById(R.id.password2);
        if (mail.getText().toString() != "") {
            user.setMail(mail.getText().toString());
        }
        if (username.getText().toString() != "") {
            user.setUsername(username.getText().toString());
        }
        if ((password1.getText().toString() != "") && (password1.getText().toString().equals(password2.getText().toString()))) {
            user.setPassword(password1.getText().toString());
        }

        RoomDB database = RoomDB.getInstance(this);
        database.userDao().insert(user);

    }

    private void openSignInActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        TextView slogan = findViewById(R.id.slogan);
        Button login = findViewById(R.id.login);
        ImageView logo = findViewById(R.id.logo);
        TextInputLayout password = findViewById(R.id.password_layout);
        TextInputLayout mail = findViewById(R.id.email_layout);
        Pair[] pairs = new Pair[5];
        pairs[0] = new Pair<View, String>((View) login, "login");
        pairs[1] = new Pair<View, String>((View) slogan, "slogan");
        pairs[2] = new Pair<View, String>((View) password, "password");
        pairs[3] = new Pair<View, String>((View) mail, "mail");
        pairs[4] = new Pair<View, String>((View) logo, "logo");
        //pairs[3] = new Pair<View, String>((View) password, "password_layout");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this, pairs);
        startActivity(intent, options.toBundle());
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}