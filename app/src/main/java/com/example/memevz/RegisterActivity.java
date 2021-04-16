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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private Button btn;
    private TextView signIn;
    private User user;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


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
        if (isInputCorrect()) {
            user.setMail(mail.getText().toString());
            user.setUsername(username.getText().toString());
            user.setPassword(password1.getText().toString());
            RoomDB database = RoomDB.getInstance(this);
            database.userDao().insert(user);
            openSignInActivity();
        }


    }

    public boolean isInputCorrect() {
        boolean mail = isMailCorrect();
        boolean username = isUsernameCorrect();
        boolean password = isPasswordCorrect();
        boolean matchingPasswords = doesPasswordsMatch();

        if ( mail && username && password && matchingPasswords) {
            return true;
        }
        return false;
    }
    public boolean isMailCorrect() {

        TextInputEditText mail = findViewById(R.id.email);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail.getText().toString());
        if (mail.getText().toString().equals("")) {
            mail.setError("Please enter an e-mail!");
        }
        else if (!matcher.find()) {
            mail.setError("This is no valid e-mail address");
        }
        return matcher.find();
    }
    public boolean isUsernameCorrect() {
        TextInputEditText username = findViewById(R.id.username);
            if (username.getText().toString().equals("")) {
                username.setError("Please enter an username!");
                return false;
            }
            else if (username.getText().toString().length() < 3) {
                username.setError("Username must be longer than 3 characters");
                return false;
            }
            else {
                return true;
            }
    }
    public boolean isPasswordCorrect() {
        TextInputEditText password1 = findViewById(R.id.password);
        TextInputEditText password2 = findViewById(R.id.password2);
        if (password1.getText().toString().equals("")) {
            password1.setError("Please enter a password!");
            return false;
        }
        else if(password1.getText().toString().length() < 3) {
            password1.setError("Password must be longer than 3 characters!");
            return false;
        }
        else {
            return true;
        }
    }
    public boolean doesPasswordsMatch() {
        TextInputEditText password1 = findViewById(R.id.password);
        TextInputEditText password2 = findViewById(R.id.password2);
        if (password1.getText().toString().equals(password2.getText().toString())) {
            return true;
        }
        else {
            password2.setError("Passwords does not match");
            return false;
        }

    }

    private void openSignInActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        TextView slogan = findViewById(R.id.slogan);
        TextView change = findViewById(R.id.change);
        Button login = findViewById(R.id.login);
        ImageView logo = findViewById(R.id.logo);
        TextInputLayout password = findViewById(R.id.password_layout);
        TextInputLayout mail = findViewById(R.id.email_layout);
        Pair[] pairs = new Pair[6];
        pairs[0] = new Pair<View, String>((View) login, "login");
        pairs[1] = new Pair<View, String>((View) slogan, "slogan");
        pairs[2] = new Pair<View, String>((View) password, "password");
        pairs[3] = new Pair<View, String>((View) mail, "mail");
        pairs[4] = new Pair<View, String>((View) logo, "logo");
        pairs[5] = new Pair<View, String>((View) change, "change");
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