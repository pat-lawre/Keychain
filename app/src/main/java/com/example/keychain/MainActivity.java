package com.example.keychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AccountModel am = new AccountModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database db = new Database(this);
    }

    public void register(View v) {
        Intent i = new Intent(this, CreateAccountActivity.class); // create createAccount activity
        startActivity(i); // launch createAccount activity

    }
    public void login(View v) {
        TextView t = findViewById(R.id.emailTextBox); // get text inside email text field and store in inputEmail
        am.setEmail(t.getText().toString());

        CredentialStatus login;

        if(!am.getEmail().contains("@") || !am.getEmail().contains(".")) { // string validation
            login = CredentialStatus.INVALID_EMAIL;
        } else {
                Database db = new Database(this);
                if(db.checkEmail(am)) { // query database for email
                    if(db.checkPassword(am)) { // query database for password
                        login = CredentialStatus.CORRECT_CREDENTIALS;
                    }
                    login = CredentialStatus.INCORRECT_CREDENTIALS;
                }

                // if database doesn't find a match for email
            login = CredentialStatus.INCORRECT_CREDENTIALS;
        } /* else {
            login = LoginStatus.CORRECT_CREDENTIALS;
        } */

        loginResult(login);

        t = findViewById(R.id.passwordTextBox); // get text inside password text field and store in inputPassword
        am.setPassword(t.getText().toString());
    }

    private void loginResult(CredentialStatus loginStatus) {
        switch(loginStatus) {
            case CORRECT_CREDENTIALS:
                // load next activity
                Log.d("login Status", "LOGGED IN");
                break;
            case INVALID_EMAIL:
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show(); // create some text that says email isn't valid
                EditText t1 = findViewById(R.id.createPasswordTextBox); // clear password fields
                t1.setText("");
                EditText t2 = findViewById(R.id.confirmPasswordTextBox);
                t2.setText("");
                Log.d("login Status", "Invalid email");
                break;
            case INCORRECT_CREDENTIALS:
                Toast.makeText(this, "Password invalid. Make sure both passwords more than 6 characters long and are the same", Toast.LENGTH_SHORT).show(); // create some text that says password needs to be longer
                t1 = findViewById(R.id.createPasswordTextBox);
                t1.setText("");
                t2 = findViewById(R.id.confirmPasswordTextBox);
                t2.setText("");
                Log.d("login Status", "Invalid credentials");
                break;
        }
    }
}