package com.stevemuindi.firebaseauthenticationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        EditText editResetPasswordEmail = findViewById(R.id.editResetPassword);
        Button btnResetPassword = findViewById(R.id.buttonResetPassword);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String email = editResetPasswordEmail.getText().toString();

                if (email.isEmpty()){
                    editResetPasswordEmail.setError("Email is required");
                    editResetPasswordEmail.requestFocus();
                    return;
                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editResetPasswordEmail.setError("Pleas provide valid email");
                    editResetPasswordEmail.requestFocus();
                    return;
                }
                else{
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(ForgetPasswordActivity.this, task -> {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Check your Email reset", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });

    }
}