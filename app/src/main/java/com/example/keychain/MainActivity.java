package com.example.keychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
Starting login screen
Handles logging into your Keychain account
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register(View v) { // called when register button is clicked
        Intent i = new Intent(this, CreateAccountActivity.class); // create createAccount activity
        startActivity(i); // launch createAccount activity

    }

    AccountsDatabase adb = new AccountsDatabase(this); // creates or finds the existing "accounts.db" database
    public void login(View v) { // called when login button is clicked. Contains logic for credential validation (database querying included)
        AccountModel am = new AccountModel();

        TextView t = findViewById(R.id.Main_emailTextBox); // get text inside email text field and store in an account model object
        am.setEmail(t.getText().toString());
        t = findViewById(R.id.Main_passwordTextBox); // get text inside password text field and store in an account model object
        am.setPassword(t.getText().toString());

        // email validation
        CredentialStatus loginStatus;
        if(am.getEmail().contains("@") && am.getEmail().contains(".")) { // if email has "@" and "." in it then it's valid
            loginStatus = CredentialStatus.VALID_EMAIL;
        } else { // if email doesn't have either one of these, then the email is invalid
            loginStatus = CredentialStatus.INVALID_EMAIL;
        }

        // password validation
        if(loginStatus == CredentialStatus.VALID_EMAIL && am.getPassword().length() > 6) { // if password is greater than 6 chars then it's valid
            loginStatus = CredentialStatus.VALID_EMAIL_AND_PASSWORD;
        } else if(loginStatus == CredentialStatus.VALID_EMAIL) { // if email is shorter than 6 chars, then it's invalid
            loginStatus = CredentialStatus.INVALID_PASSWORD;
        }

        // deciding result of login attempt based on validated login credentials
        switch(loginStatus) {
            case VALID_EMAIL_AND_PASSWORD:
                // query database to see if there is an account in the database
                Log.d("Login Status", "Valid email and password");
                break;
            case INVALID_EMAIL:
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show(); // create some text that says email isn't valid
                EditText t1 = findViewById(R.id.createAccount_passwordTextBox); // clear password fields
                EditText t2 = findViewById(R.id.createAccount_confirmPasswordTextBox);
                t1.setText("");
                t2.setText("");
                Log.d("Login Status", "Invalid email");
                break;
            case INVALID_PASSWORD:
                Toast.makeText(this, "Password invalid", Toast.LENGTH_SHORT).show(); // create some text that says password needs to be longer
                t1 = findViewById(R.id.createAccount_passwordTextBox); // clear password fields
                t2 = findViewById(R.id.createAccount_confirmPasswordTextBox);
                t1.setText("");
                t2.setText("");
                Log.d("Login Status", "Invalid password");
                break;
        }
    }
}