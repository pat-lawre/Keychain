package com.example.keychain;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    private void createAccount(View v) {
        TextView t = findViewById(R.id.nameTextBox); // get text inside name text field and store in name
        String name = t.getText().toString();

        t = findViewById(R.id.newEmailTextBox); // get text inside email text field and store in email
        String email = t.getText().toString();

        t = findViewById(R.id.phoneTextBox); // get text inside phone text field and store in phone
        String phone = t.getText().toString();

        t = findViewById(R.id.createPasswordTextBox); // get text inside new password text field and store in newPassword
        String password = t.getText().toString();

        t = findViewById(R.id.confirmPasswordTextBox); // get text inside confirm password text field and store in confirmPassword
        String confirmPassword = t.getText().toString();

        String firstName, lastName;
        firstName = name.substring(0, name.indexOf(' ')-1);
        lastName = name.substring(name.indexOf(' ')+1);

        CredentialStatus login;

        if(!email.contains("@") || !email.contains(".")) { // email string validation
            login = CredentialStatus.INVALID_EMAIL;
        } else {
            login = CredentialStatus.CORRECT_CREDENTIALS;
        }

        if(password.length() < 6) {
            login = CredentialStatus.INVALID_PASSWORD;
        }

        loginResult(login);
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
            case INVALID_PASSWORD:
                // create some text that says password needs to be longer
                // clear password
}