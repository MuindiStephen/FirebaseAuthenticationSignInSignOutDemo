package com.stevemuindi.firebaseauthenticationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;



public class SignUpActivity extends AppCompatActivity {

     EditText editEmail;
     EditText editPassword;
     Button buttonSignUp;
     TextView tvAlreadyAccount;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //views
        editEmail = findViewById(R.id.reg_Email);
        editPassword = findViewById(R.id.reg_Password);
        buttonSignUp = findViewById(R.id.btn_SignUp);
        tvAlreadyAccount = findViewById(R.id.already_account);

        //initiate firebase authentication
        auth = FirebaseAuth.getInstance();

         tvAlreadyAccount.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                 startActivity(intent);
                 finish();
             }
         });


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //taking user data from input fields
                String userEmail = editEmail.getText().toString();
                String userPassword = editPassword.getText().toString();

                if (userEmail.isEmpty()){
                    editEmail.setError("Email is required");
                    editEmail.requestFocus();
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                    editEmail.setError("Please enter a valid email");
                    editEmail.requestFocus();
                    return;
                }
                else if (userPassword.isEmpty()){
                    editPassword.setError("Please enter password");
                    editPassword.requestFocus();
                    return;
                }
                else if (userPassword.length() < 8){
                    editPassword.setError("Password does not meet 8 characters requirements.");
                    editPassword.requestFocus();
                    return;
                }
                else {
                    auth.createUserWithEmailAndPassword(editEmail.getText().toString(),
                            editPassword.getText().toString()).addOnCompleteListener( task -> {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Sign Up is successful ", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        //Toast.makeText(getApplicationContext(), "Unsuccessful,please try again", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                    }
                            }); //.addOnFailureListener(e -> Toast.makeText(SignUpActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show());
                                //can be used in place of --task.getException().getLocalizedMessage()--

                }

            }
        });

    }
}//end of main activity class