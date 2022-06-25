package com.stevemuindi.firebaseauthenticationdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    EditText editEmailAddress;
    EditText editPassWord;
    Button buttonLogin;
    TextView tvForgotPassword;
    TextView tvSignUp;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();

        editEmailAddress = findViewById(R.id.log_EmailAddress);
        editPassWord = findViewById(R.id.log_Password);
        buttonLogin = findViewById(R.id.btn_LogIn);
        tvForgotPassword = findViewById(R.id.log_Tv_ForgotPassword);
        tvSignUp = findViewById(R.id.sign_up_text);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });



        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this,ForgetPasswordActivity.class);
                startActivity(i);
                finish();
            }
        });

//        //checks if user is signed in
//         if (auth.getCurrentUser() !=null){
//           startActivity(new Intent(this,MainActivity.class));
//           finish();
//         }

         //button login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmailAddress.getText().toString();
                String password = editPassWord.getText().toString();

                if (email.isEmpty()){
                    editEmailAddress.setError("Empty email! Please enter email");
                    editEmailAddress.requestFocus();
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editEmailAddress.setError("Please enter valid email");
                    editEmailAddress.requestFocus();
                    return;
                }
                else if (password.isEmpty()){
                    editPassWord.setError("Password is Required !");
                    editPassWord.requestFocus();
                    return;
                }
                else if (password.length() < 8){
                    editPassWord.setError("Minimum password length, 8 characters required !");
                    editPassWord.requestFocus();
                    return;
                }
                else {
                      auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful()){
                                  Toast.makeText(getApplicationContext(), "You signed in Successfully", Toast.LENGTH_SHORT).show();

                                  Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                  startActivity(intent);
                                  finish();
                              }
                              else {
                                  Toast.makeText(getApplicationContext(), "Unsuccessful login! Please provide correct credentials", Toast.LENGTH_SHORT).show();
                              }
                          }
                      });
                }
            }

        });

    }

}