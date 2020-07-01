package com.example.remakemvvmlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.remakemvvmlogin.databinding.ActivityMainBinding;
import com.example.remakemvvmlogin.factory.ViewModelBinding;
import com.example.remakemvvmlogin.model.LoginModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements SystemCallLogin {
    private FirebaseAuth firebaseAuth;
    private LoginModel model;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        model = new LoginModel();
        binding.setViewModel(ViewModelProviders.of(this, new ViewModelBinding(this,model,this)).get(LoginViewModel.class));
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,ProfileActivity.class));
        }
    }

    @Override
    public void onSucceed(String mess) {
        Toast.makeText(this,"Wait a sec ...",Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(this,"Successfully login",Toast.LENGTH_LONG).show();
        firebaseAuth.createUserWithEmailAndPassword(model.getEmail(),model.getPasswords()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    finish();
                    startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                }else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_LONG).show();
                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onFailure(String mess) {
        Toast.makeText(this," ! Passwords must be more than 6 characters, 1 special character and at least 1 number",Toast.LENGTH_LONG).show();
    }
}
