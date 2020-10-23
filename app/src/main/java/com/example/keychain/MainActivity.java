package com.example.keychain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private enum LoginStatus { // richer return value for describing login status
        CORRECT_CREDENTIALS, INVALID_EMAIL, INCORRECT_CREDENTIALS
    }

    public void createAccount(View v) {
        // load createAccount activity
    }
    public void login(View v) {
        TextView t = findViewById(R.id.emailTextBox); // get text inside email text field and store in inputEmail
        String inputEmail = t.getText().toString();

        LoginStatus login;

        if(!inputEmail.contains("@") || !inputEmail.contains(".")) { // string validation
            login = LoginStatus.INVALID_EMAIL;
        } else /*if()*/ {
            // query database for email and password
            // if database doesn't find a match for email
            login = LoginStatus.INCORRECT_CREDENTIALS;
        } /* else {
            login = LoginStatus.CORRECT_CREDENTIALS;
        } */

        loginResult(login);

        t = findViewById(R.id.passwordTextBox); // get text inside password text field and store in inputPassword
        String inputPassword = t.getText().toString();
    }
    private void loginResult(LoginStatus loginStatus) {
        switch(loginStatus) {
            case CORRECT_CREDENTIALS:
                // load next activity
                break;
            case INVALID_EMAIL:
                // create some text that says email needs @ and period
                // clear password
                break;
            case INCORRECT_CREDENTIALS:
                // create text that says incorrect email or password
                // clear email and password fields
        }
    }
}