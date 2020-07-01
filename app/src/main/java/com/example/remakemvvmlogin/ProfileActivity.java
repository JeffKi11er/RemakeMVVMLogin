package com.example.remakemvvmlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.remakemvvmlogin.databinding.ActivityProfileBinding;
import com.example.remakemvvmlogin.model.Person;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding bindingP = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            if(user.getEmail()!=null){
                person = new Person(user.getEmail());
                person.setNameUser(user.getEmail());
                bindingP.setPerson(person);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
