package com.example.remakemvvmlogin.factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.remakemvvmlogin.LoginViewModel;
import com.example.remakemvvmlogin.SystemCallLogin;
import com.example.remakemvvmlogin.model.LoginModel;

public class ViewModelBinding extends ViewModelProvider.NewInstanceFactory {
    private SystemCallLogin systemCallLogin ;
    private LoginModel model;
    private Context context;

    public ViewModelBinding(SystemCallLogin systemCallLogin, LoginModel model, Context context) {
        this.systemCallLogin = systemCallLogin;
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(model,context,systemCallLogin);
    }
}
