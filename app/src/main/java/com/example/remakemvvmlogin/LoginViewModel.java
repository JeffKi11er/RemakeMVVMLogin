package com.example.remakemvvmlogin;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.remakemvvmlogin.model.LoginModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {
    private LoginModel model;
    private SystemCallLogin loginCallBack;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//    public MutableLiveData<String> email = new MutableLiveData<>();
//    public MutableLiveData<String> password = new MutableLiveData<>();
    private Context context;

    public LoginViewModel(LoginModel model,SystemCallLogin loginCallBack) {
        this.model = model;
        this.loginCallBack = loginCallBack;
    }
    public LoginViewModel(LoginModel model,Context context,SystemCallLogin loginCallBack) {
        this.model = model;
        this.context = context;
        this.loginCallBack = loginCallBack;
    }
    public LoginModel getModel() {
        return model;
    }

    public void setModel(LoginModel model) {
        this.model = model;
    }

    public SystemCallLogin getLoginCallBack() {
        return loginCallBack;
    }

    public void setLoginCallBack(SystemCallLogin loginCallBack) {
        this.loginCallBack = loginCallBack;
    }
    public TextWatcher emailTexWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                model.setEmail(s.toString());
            }
        };
    }
    public TextWatcher passTexWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                model.setPasswords(s.toString());
            }
        };
    }
    public void onLogin(final View view){
        if(model.isValid()){
            loginCallBack.onSucceed("Successfully login");
        }
        else {
            loginCallBack.onFailure("Failure to login");
        }
    }
}
