package com.example.tomatoleafdisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button loginn;
    EditText temail,pass;
    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login Form");

        temail=(EditText)findViewById(R.id.temail);
        pass=(EditText)findViewById(R.id.pass);
        loginn=(Button)findViewById(R.id.loginn);

        firebaseAuth=FirebaseAuth.getInstance();

        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=temail.getText().toString().trim();
                String password=pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                } else {
                                    Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });





            }
        });
    }

    public void sign(View view) {

        startActivity(new Intent(getApplicationContext(),signup.class));
    }

    public void skip(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void log(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.auto:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                return true;


            default:
                return false;
        }
    }
}
