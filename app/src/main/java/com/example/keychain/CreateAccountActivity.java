package com.example.keychain;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    AccountModel am = new AccountModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Database db = new Database(this);
    }

    public void createAccount(View v) {
        TextView t = findViewById(R.id.nameTextBox); // get text inside name text field and store in name
        String name = t.getText().toString();
        am.setName(name);

        t = findViewById(R.id.newEmailTextBox); // get text inside email text field and store in email
        String email = t.getText().toString();
        am.setEmail(email);

        t = findViewById(R.id.phoneTextBox); // get text inside phone text field and store in phone
        String phone = t.getText().toString();
        am.setPhone(phone);

        t = findViewById(R.id.createPasswordTextBox); // get text inside new password text field and store in newPassword
        String password = t.getText().toString();
        am.setPassword(password);

        t = findViewById(R.id.confirmPasswordTextBox); // get text inside confirm password text field and store in confirmPassword
        String confirmPassword = t.getText().toString();

        //String firstName, lastName;
        //firstName = name.substring(0, name.indexOf(' ')-1);
        //lastName = name.substring(name.indexOf(' ')+1);

        CredentialStatus login;

        if(!email.contains("@") || !email.contains(".")) { // email string validation
            login = CredentialStatus.INVALID_EMAIL;
        } else {
            login = CredentialStatus.CORRECT_CREDENTIALS;
        }

        if(password.length() < 6 || password.compareTo(confirmPassword) != 0) {
            login = CredentialStatus.INCORRECT_CREDENTIALS;
        }

        loginResult(login);
    }

    private void loginResult(CredentialStatus loginStatus) {
        switch (loginStatus) {
            case CORRECT_CREDENTIALS:
                Database db = new Database(this); // create database
                db.addAccount(am); // add account
                // load next activity
                break;
            case INVALID_EMAIL:
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show(); // create some text that says email isn't valid
                EditText t1 = findViewById(R.id.createPasswordTextBox); // clear password fields
                t1.setText("");
                EditText t2 = findViewById(R.id.confirmPasswordTextBox);
                t2.setText("");
                break;
            case INCORRECT_CREDENTIALS:
                Toast.makeText(this, "Email or password invalid", Toast.LENGTH_SHORT).show(); // create some text that says password needs to be longer
                t1 = findViewById(R.id.createPasswordTextBox);
                t1.setText("");
                t2 = findViewById(R.id.confirmPasswordTextBox);
                t2.setText("");
                break;
        }
    }
}