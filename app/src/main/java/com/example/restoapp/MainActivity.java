package com.example.restoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restoapp.managers.AccountHandler;
import com.example.restoapp.models.Account;

public class MainActivity extends AppCompatActivity{
    Button registerPageButton;
    EditText email, password;
    Button loginb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerPageButton = findViewById(R.id.btn_register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginb = findViewById(R.id.loginb);


        registerPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getRootView().getContext();
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formElementsView = inflater.inflate(R.layout.account_create_form, null, false);
                final EditText editTextName = (EditText) formElementsView.findViewById(R.id.editTextName);
                final EditText editTextEmail = (EditText) formElementsView.findViewById(R.id.editTextEmail);
                final EditText editTextPassword = (EditText) formElementsView.findViewById(R.id.editTextPassword);
                new AlertDialog.Builder(context)
                        .setView(formElementsView)
                        .setTitle("Create account")
                        .setPositiveButton("Register",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Account account = new Account();
                                        String name = editTextName.getText().toString();
                                        String email = editTextEmail.getText().toString();
                                        String password = editTextPassword.getText().toString();
                                        account.setName(name);
                                        account.setEmail(email);
                                        account.setPassword(password);
                                        int createdAccount = new AccountHandler(context).addAccount(account);
                                        Toast.makeText(context, "Account created for " + name, Toast.LENGTH_SHORT).show();
                                        dialog.cancel();
                                    }
                                }).show();
            }
        });

        loginb.setOnClickListener((View v)->{
            String email = this.email.getText().toString().trim();
            String password = this.password.getText().toString().trim();
            boolean isCredentialsValid = new AccountHandler(this).checkAccount(email, password);
            if (isCredentialsValid) {
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(MainActivity.this, Home.class);
                        int id = new AccountHandler(getApplicationContext()).getAccountId(email);
                        i.putExtra("accountID", id);
                        startActivity(i);
                    }
                },300);
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}