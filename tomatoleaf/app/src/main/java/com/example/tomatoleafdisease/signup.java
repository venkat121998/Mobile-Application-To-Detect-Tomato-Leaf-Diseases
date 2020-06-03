package com.example.tomatoleafdisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    EditText temail,pass,cpass;
    Button reg;
    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("SignUp Form");

        temail=(EditText)findViewById(R.id.temail);
        pass=(EditText)findViewById(R.id.pass);
        cpass=(EditText)findViewById(R.id.cpass);
        reg=(Button)findViewById(R.id.reg);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);

        firebaseAuth=FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=temail.getText().toString().trim();
                String password=pass.getText().toString().trim();
                String confirmpassword=cpass.getText().toString().trim();
                
                if(TextUtils.isEmpty(email)){
               Toast.makeText(signup.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(signup.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmpassword)){
                    Toast.makeText(signup.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if(password.length()<=8){
                    Toast.makeText(signup.this, "PassWord Too Short", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);

                if(password.equals(confirmpassword))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(signup.this, "Authentication Failed", Toast.LENGTH_SHORT).show();


                                    }

                                    // ...
                                }
                            });

                }
                else
                {
                    Toast.makeText(signup.this, "Password Does not Match", Toast.LENGTH_SHORT).show();
                }



        }
        });

    }

    public void btn_login(View view) {
        startActivity(new Intent(getApplicationContext(),login.class));
    }
}
