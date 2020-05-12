package com.rssoft.intern_task0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) startActivity(new Intent(this,
                WelcomeActivity.class));
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final FirebaseAuth auth = FirebaseAuth.getInstance();

        final EditText mail = findViewById(R.id.login_mail);
        final EditText pwd = findViewById(R.id.login_pwd);
        final Button login = findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mail.getText().toString().contains("@")) {
                    Toast.makeText(LoginActivity.this, "Invalid Mail Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.getText().toString().isEmpty() || pwd.getText().toString().length() < 6) {
                    Toast.makeText(LoginActivity.this, "Password too short !!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(mail.getText().toString(),
                        pwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Failed! Try Again!!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        findViewById(R.id.open_reg_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
        findViewById(R.id.forgot_pwd_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mail.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter mail id first!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.sendPasswordResetEmail(mail.getText().toString());
                Toast.makeText(LoginActivity.this,
                        "A password reset mail has been sent at" + mail.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
