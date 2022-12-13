package com.example.restoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restoapp.managers.AccountHandler;
import com.example.restoapp.models.Account;


public class ProfileFragment extends Fragment {

    EditText edit_text_name, edit_text_email, edit_text_password;
    String nameString, emailString, passwordString;
    Button updateButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        edit_text_name = view.findViewById(R.id.name);
        edit_text_email = view.findViewById(R.id.email);
        edit_text_password = view.findViewById(R.id.password);
        Bundle bundle = this.getArguments();
        int accountId = bundle.getInt("accountID");
        Account account = new AccountHandler(getContext()).getAccount(accountId);
        nameString = account.getName();
        edit_text_name.setText(nameString);
        emailString = account.getEmail();
        edit_text_email.setText(emailString);
        passwordString = account.getPassword();
        edit_text_password.setText(passwordString);

        updateButton = view.findViewById(R.id.updatebtn);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = edit_text_name.getText().toString();
                emailString = edit_text_email.getText().toString();
                passwordString = edit_text_password.getText().toString();
                account.setName(nameString);
                account.setEmail(emailString);
                account.setPassword(passwordString);
                new AccountHandler(getContext()).updateAccount(account);
                Toast.makeText(getContext(), "Account updated", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
