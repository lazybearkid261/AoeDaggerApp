package com.example.aoedaggerapp.ui.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aoedaggerapp.R;
import com.example.aoedaggerapp.ui.auth.AuthActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Retrofit;

public class RegisterActivity extends DaggerAppCompatActivity implements View.OnClickListener{
    private static final String TAG = "RegisterActivity";

    private EditText fullName, email, password, passwordRepeat;
    private Button confirm, cancel;

    @Inject
    FirebaseAuth firebaseAuth;
    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.edt_register_form_full_name);
        email = findViewById(R.id.edt_register_form_email);
        password = findViewById(R.id.edt_register_form_password);
        passwordRepeat = findViewById(R.id.edt_register_form_password_repeat);

        Log.d(TAG, "onCreate: " + firebaseAuth + " " + retrofit);

        confirm = findViewById(R.id.btn_register_form_confirm);
        cancel = findViewById(R.id.btn_register_form_cancel);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private void createNewAccount(){
        String emailCreate = email.getText().toString();
        String passwordCreate = email.getText().toString();
        String rePasswordCreate = email.getText().toString();

        if (!TextUtils.isEmpty(emailCreate) && !TextUtils.isEmpty(passwordCreate) &&
                !TextUtils.isEmpty(rePasswordCreate)){
            if (passwordCreate.equals(rePasswordCreate) && password.length() >=6 ){
                firebaseAuth.createUserWithEmailAndPassword(emailCreate, passwordCreate)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this,
                                            "Register failed. Make sure that password must contain more than 6 characters." + task.getResult(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    Log.d(TAG, "onComplete: " + user.getEmail());
                                    firebaseAuth.signOut();
                                    Intent intent = new Intent(RegisterActivity.this , AuthActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        })
                        .addOnFailureListener(this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this, "Register failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }

    private void backToAuthActivity(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_form_confirm:
                createNewAccount();
                break;
            case R.id.btn_register_form_cancel:
                backToAuthActivity();
                break;
        }
    }
}
