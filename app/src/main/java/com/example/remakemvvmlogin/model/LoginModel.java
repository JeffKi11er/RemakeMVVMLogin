package com.example.remakemvvmlogin.model;

import android.text.TextUtils;

import androidx.annotation.Nullable;

public class LoginModel {
    @Nullable
    private String email,passwords;

    public LoginModel(@Nullable String name, @Nullable String passwords) {
        this.email = name;
        this.passwords = passwords;
    }

    public LoginModel() {
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(@Nullable String passwords) {
        this.passwords = passwords;
    }
    public  boolean hasNumber(){
        boolean hasNum = false;
        char c;
        for (int i = 0; i <passwords.length() ; i++) {
            c = passwords.charAt(i);
            if (Character.isDigit(c)){
                hasNum = true;
            }
            if (hasNum){
                return true;
            }
        }
        return false;
    }
    public boolean has1SpecialSymbol(){
        int specialChar = 0;
        for(int i = 0; i<passwords.length();i++){
            char c = passwords.charAt(i);
            if((c >='a' && c<='z')|| (c >='A'&& c<='Z')||(c>='0'&& c<='9')){
                System.out.println(c);
            }
            else{
                ++specialChar;
            }
        }
        if(specialChar==1){
            return true;
        }
        return false;
    }
    public boolean isValid(){
        return !TextUtils.isEmpty(email)&&
                !TextUtils.isEmpty(passwords)&&
                getPasswords().length()>6 &&
                hasNumber() &&
                has1SpecialSymbol();
    }
}
