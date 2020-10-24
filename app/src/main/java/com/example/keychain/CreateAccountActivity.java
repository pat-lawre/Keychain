package com.example.keychain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
User gets to this activity by clicking the register button on login screen
Handles creating a new Keychain account
 */

public class CreateAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    AccountsDatabase adb = new AccountsDatabase(this); // creates or finds the existing "accounts.db" database
    public void createAccount(View v) { // called when create account button is clicked. Validates credentials and creates a new entry in the account database
        AccountModel am = new AccountModel();

        TextView t = findViewById(R.id.createAccount_nameTextBox); // get text inside name text field and store in account model object
        am.setName(t.getText().toString());
        t = findViewById(R.id.createAccount_emailTextBox); // get text inside email text field and store in account model object
        am.setEmail(t.getText().toString());
        t = findViewById(R.id.createAccount_phoneTextBox); // get text inside phone text field and store in account model object
        am.setPhone(t.getText().toString());
        t = findViewById(R.id.createAccount_passwordTextBox); // get text inside new password text field and store in account model object
        am.setPassword(t.getText().toString());
        t = findViewById(R.id.createAccount_confirmPasswordTextBox); // get text inside confirm password text field and store in confirmPassword
        String confirmPassword = t.getText().toString();

        // email validation
        CredentialStatus createAccountStatus;
        if (am.getEmail().contains("@") && am.getEmail().contains(".")) { // if email has "@" and "." in it then it's valid
            createAccountStatus = CredentialStatus.VALID_EMAIL;
        } else { // if email doesn't have either one of these, then the email is invalid
            createAccountStatus = CredentialStatus.INVALID_EMAIL;
        }

        // password validation
        if (createAccountStatus == CredentialStatus.VALID_EMAIL && am.getPassword().length() > 6) { // if password is greater than 6 chars then it's valid
            createAccountStatus = CredentialStatus.VALID_EMAIL_AND_PASSWORD;
        } else if (createAccountStatus == CredentialStatus.VALID_EMAIL) { // if email is shorter than 6 chars, then it's invalid
            createAccountStatus = CredentialStatus.INVALID_PASSWORD;
        }

        // deciding result of login attempt based on validated login credentials
        switch (createAccountStatus) {
            case VALID_EMAIL_AND_PASSWORD:
                //adb.addAccount(am); // add account
                // load next activity
                break;
            case INVALID_EMAIL:
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show(); // create some text that says email isn't valid
                EditText t1 = findViewById(R.id.createAccount_passwordTextBox); // clear password fields
                EditText t2 = findViewById(R.id.createAccount_confirmPasswordTextBox);
                t1.setText("");
                t2.setText("");
                break;
            case INVALID_PASSWORD:
                Toast.makeText(this, "Password invalid. Make sure both passwords more than 6 characters long and are the same", Toast.LENGTH_SHORT).show(); // create some text that says password needs to be longer
                t1 = findViewById(R.id.createAccount_passwordTextBox); // clear password fields
                t2 = findViewById(R.id.createAccount_confirmPasswordTextBox);
                t1.setText("");
                t2.setText("");
                break;
        }
    }
}