package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private AppCompatButton buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (isValid(username, password)) {


                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                    finish();



                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValid(String username, String password) {
            if (username.isEmpty()){
                editTextUsername.requestFocus();
                editTextUsername.setEnabled(true);
                editTextPassword.setEnabled(true);
                Toast.makeText(MainActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                return false;
            } else if (password.isEmpty()) {
                editTextPassword.requestFocus();
                editTextUsername.setEnabled(true);
                editTextPassword.setEnabled(true);
                Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                return false;
            }else if (password.length()<7) {
                editTextPassword.requestFocus();
                editTextUsername.setEnabled(true);
                editTextPassword.setEnabled(true);
                Toast.makeText(MainActivity.this, "Password must be 7 Characters with 1UpperCase Alphabet and 1SpecialCharacter and Numeric", Toast.LENGTH_SHORT).show();
                return false;
            }else if (username.length()<10) {
                editTextUsername.requestFocus();
                editTextUsername.setEnabled(true);
                editTextPassword.setEnabled(true);
                Toast.makeText(MainActivity.this, "Username must be 10 characters", Toast.LENGTH_SHORT).show();
                return false;
            } else if (username.equals("Fininfocom") && password.equals("Fin@123")){
                 return true;
        } else {
                return false;
            }

    }



    }
