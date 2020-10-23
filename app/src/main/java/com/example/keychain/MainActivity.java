package com.example.keychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    private void register(View v) {
        Intent i = new Intent(this, CreateAccountActivity.class); // create createAccount activity
        startActivity(i); // launch createAccount activity

    }
    private void login(View v) {
        TextView t = findViewById(R.id.emailTextBox); // get text inside email text field and store in inputEmail
        String inputEmail = t.getText().toString();

        CredentialStatus login;

        if(!inputEmail.contains("@") || !inputEmail.contains(".")) { // string validation
            login = CredentialStatus.INVALID_EMAIL;
        } else /*if()*/ {
            // query database for email and password
            // if database doesn't find a match for email
            login = CredentialStatus.INCORRECT_CREDENTIALS;
        } /* else {
            login = LoginStatus.CORRECT_CREDENTIALS;
        } */

        loginResult(login);

        t = findViewById(R.id.passwordTextBox); // get text inside password text field and store in inputPassword
        String inputPassword = t.getText().toString();
    }

    private void loginResult(CredentialStatus loginStatus) {
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