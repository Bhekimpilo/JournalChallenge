package com.example.iis.journalchallenge;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    EditText user, pass;
    Button login;
    TextView signUp;
    ProgressBar bar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.editUsername);
        pass = findViewById(R.id.editPassword);
        login = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.signupText);
        bar = findViewById(R.id.progressBar2);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });


        auth = FirebaseAuth.getInstance();


        if (auth.getCurrentUser() != null){
            moveToList();
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(user.getText().toString(), pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                bar.setVisibility(View.INVISIBLE);
                                if (task.isSuccessful()){
                                    moveToList();
                                    Toast.makeText(LoginActivity.this, "Welcome" , Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    private void moveToList() {
        Intent proceed = new Intent(getApplicationContext(), MainActivity.class);
        proceed.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        proceed.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(proceed);
    }
}
