package com.example.aoedaggerapp.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aoedaggerapp.R;
import com.example.aoedaggerapp.ui.main.MainActivity;
import com.example.aoedaggerapp.ui.register.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Retrofit;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";

    private TextView email, password;
    private TextView forgotPassword, register;
    private Button btnLogin;

    @Inject
    FirebaseAuth firebaseAuth;

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        email = (TextView) findViewById(R.id.edt_email);
        password = (TextView) findViewById(R.id.edt_password);
        forgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        register = (TextView) findViewById(R.id.tv_register);
        btnLogin = (Button) findViewById(R.id.btn_login);

        forgotPassword.setOnClickListener(this);
        register.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        Log.d(TAG, "onCreate: " + firebaseAuth + " " + firebaseAuth.getCurrentUser());
    }

    private void loggingIn(){
        String emailAccount = email.getText().toString();
        String passwordAccount = password.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(emailAccount, passwordAccount)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(AuthActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AuthActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.tv_forgot_password:
                break;
            case R.id.tv_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login:
                loggingIn();
                break;
        }
    }
}
