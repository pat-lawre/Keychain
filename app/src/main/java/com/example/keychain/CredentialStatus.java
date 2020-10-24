package com.example.keychain;

/*
Object that describes the status of the credentials entered for logging in or registering for an account
 */
public enum CredentialStatus {
    CORRECT_CREDENTIALS, CORRECT_EMAIL, VALID_EMAIL, INVALID_EMAIL, VALID_EMAIL_AND_PASSWORD, CORRECT_PASSWORD, INVALID_PASSWORD, INCORRECT_PASSWORD, CORRECT_PHONE, INVALID_PHONE, INCORRECT_PHONE, INCORRECT_CREDENTIALS
}
