package com.android.intern.socialx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class signup extends AppCompatActivity {

    EditText name,rmail,rPhoneNO,rPassword;
    Button bSignUp;

    private FirebaseAuth auth;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        rmail = findViewById(R.id.rEmail);
        rPhoneNO = findViewById(R.id.phone);
        rPassword = findViewById(R.id.rPassword);
        bSignUp = findViewById(R.id.signUpButton);

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gName = name.getText().toString();
                String gMail = rmail.getText().toString();
                String gPhone = rPhoneNO.getText().toString();
                String gPassword = name.getText().toString();

                if (TextUtils.isEmpty(gName)){
                    Toast.makeText(signup.this,"please Enter your Name",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(gMail)){
                    Toast.makeText(signup.this,"please Enter your email address",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(gPhone)){
                    Toast.makeText(signup.this,"please Enter your Phone No.",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(gPassword)){
                    Toast.makeText(signup.this,"please Enter your Password",Toast.LENGTH_LONG).show();
                    return;
                }
//                if (gPassword.length() < 8){
//                    Toast.makeText(signup.this,"Password should be 8 Character",Toast.LENGTH_LONG).show();
//                    return;
//                }
                mAuth.createUserWithEmailAndPassword(gMail,gPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    private final String TAG =null ;

                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"You are register!!\nlog in Now",Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(signup.this,signIn.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }

                    }
                });

            }
        });
    }

    public void signIn(View view){
        Intent intent = new Intent(signup.this,signIn.class);
        startActivity(intent);
        overridePendingTransition(0,0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }
}