package com.example.bookland.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookland.MainActivity;
import com.example.bookland.R;
import com.example.bookland.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRegister extends AppCompatActivity {
    EditText name, email, password, reEnter;
    TextView txtError;
    Button signUp;

    private String txtName;
    private String txtEmail;
    private String txtPassword;
    private String txtReEnter;

    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.sign_name);
        email = findViewById(R.id.sign_email);
        password = findViewById(R.id.sign_password);
        reEnter = findViewById(R.id.sign_passwordR);
        signUp = findViewById(R.id.sign_btn);
        txtError = findViewById(R.id.error);



        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                txtName = name.getText().toString().trim();
                txtEmail = email.getText().toString().trim();
                txtPassword = password.getText().toString().trim();
                txtReEnter = reEnter.getText().toString().trim();
                if(txtName.isEmpty()){
                    name.setError("Please, fill your name!");
                    name.requestFocus();
                    return;
                }
                if(txtEmail.isEmpty()){
                    email.setError("Please, fill your email!");
                    email.requestFocus();
                    return;
                }
                if(txtPassword.isEmpty()){
                    password.setError("Please, fill your password!");
                    password.requestFocus();
                    return;
                }if(txtReEnter.isEmpty()){
                    reEnter.setError("Please, fill your re-entered password!");
                    reEnter.requestFocus();
                    return;
                }
                if(!(txtPassword.equals(txtReEnter))){
                    txtError.setText("Please check and re-enter password!");
                }else {
                    mAuth.createUserWithEmailAndPassword(txtEmail, txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), LogIn.class));
                                Toast.makeText(ActivityRegister.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ActivityRegister.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });







    }
}