package com.example.bookland.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookland.MainActivity;
import com.example.bookland.R;
import com.example.bookland.TabLayout.TopFragment;
import com.example.bookland.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LogIn extends AppCompatActivity {
    EditText email, password;
    TextView signUp;
    Button logIn;
    FirebaseAuth fAuth;
    private  String user_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.log_email);
        password = findViewById(R.id.log_password);
        logIn = findViewById(R.id.log_btn);
        signUp = findViewById(R.id.register_btn);
        fAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityRegister.class));
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tEmail = email.getText().toString().trim();
                final String tPassword = password.getText().toString().trim();

                if(tEmail.isEmpty()){
                    email.setError("Please fill your email!");
                    email.requestFocus();
                    return;
                }
                if(tPassword.isEmpty()){
                    password.setError("Invalid password");
                    password.requestFocus();
                    return;
                }
                fAuth.signInWithEmailAndPassword(tEmail, tPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(tEmail, tPassword);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Email").setValue(user);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            SharedPreferences sharedPreferences = getSharedPreferences("userId", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Uid", user_id);
                            editor.apply();
                        }else{
                            Toast.makeText(LogIn.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                /*
                Bundle bundle = new Bundle();
                bundle.putString("user", user_id[0]);
                TopFragment topFragment = new TopFragment();
                topFragment.setArguments(bundle);

                 */

            }
        });
    }

    public String getUser_id() {
        return user_id;
    }
}