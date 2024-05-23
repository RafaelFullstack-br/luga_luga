package com.example.lugaluga.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lugaluga.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private TextInputLayout inputEmail, inputPassword;

    private TextView fazerCadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.outlinedTextField);
        inputPassword = findViewById(R.id.outlinedTextField2);
        buttonLogin = findViewById(R.id.btn_login);
        fazerCadastro = findViewById(R.id.fazer_cadastro);

        Intent intent = new Intent(this, MainActivity2.class);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validaEmailSenha()){
                    startActivity(intent);
                };
            }
        });

        Intent intentCadastro = new Intent(this, activity_login_user.class);
        fazerCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentCadastro);
            }
        });
    }

    public boolean validaEmailSenha(){
        if(inputEmail.getEditText()!= null && inputEmail.getEditText().getText().toString().equals("")){
            inputEmail.setError("Informe o email");
            return false;
        }

        if(inputPassword.getEditText() != null && inputPassword.getEditText().getText().toString().equals("")){
            inputPassword.setError("Informe a senha");
            return false;
        }
        return true;
    }
};
