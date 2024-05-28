package com.example.lugaluga.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.lugaluga.R;
import com.example.lugaluga.controller.UsuarioController;
import com.example.lugaluga.model.Usuario;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.TextView;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private Spinner spinnerUf;
    private TextInputLayout inputNome, inputEmail, inputCPF, inputCEP, inputDate,
            inputLogradouro, inputNumber, inputComplemento, inputBairro, inputCidade, inputUf,
            inputSenha;
    private Button btnAcess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_user);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Cadastro");
        setSupportActionBar(myToolbar);

        spinnerUf = (Spinner) findViewById(R.id.uf_user);
        inputEmail = findViewById(R.id.email_user);
        inputCPF = findViewById((R.id.cpf_user));
        inputCEP = findViewById(R.id.cep_user);
        inputDate = findViewById(R.id.data_birthday_user);
        inputNome = findViewById(R.id.nome_user);
        inputLogradouro = findViewById(R.id.logradouro_user);
        inputNumber = findViewById((R.id.num_user));
        inputComplemento = findViewById(R.id.bairro_user);
        inputCidade = findViewById(R.id.cidade_user);
        inputSenha = findViewById(R.id.password_user);
        btnAcess = findViewById(R.id.button_cadastro);

        btnAcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioController crud = new UsuarioController(getApplicationContext());
                Usuario usuario = new Usuario();
                usuario.setNome(inputNome.getEditText().getText().toString());
                usuario.setCpf(inputCPF.getEditText().getText().toString());
                usuario.setDate_birthday(inputDate.getEditText().getText().toString());
                usuario.setCep(inputCEP.getEditText().getText().toString());
                usuario.setCidade(inputCidade.getEditText().getText().toString());
                usuario.setUf(spinnerUf.getSelectedItem().toString());
                usuario.setLogradouro(inputLogradouro.getEditText().getText().toString());
                usuario.setNumero(Integer.parseInt(inputNumber.getEditText().getText().toString()));
                usuario.setComplemento(inputComplemento.getEditText().getText().toString());
                usuario.setBairro(inputBairro.getEditText().getText().toString());
                usuario.setEmail(inputEmail.getEditText().getText().toString());
                usuario.setSenha(inputSenha.getEditText().getText().toString());

                String resultado;

                resultado = crud.insereDados(usuario.getNome(),usuario.getCpf(),
                        usuario.getDate_birthday(),usuario.getCep(),usuario.getCidade(),
                        usuario.getUf(),usuario.getLogradouro(),usuario.getNumero(),
                        usuario.getComplemento(), usuario.getBairro(), 0, usuario.getEmail(),
                        usuario.getSenha());

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.uf_cadastro,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUf.setAdapter(adapter);

        inputDate.getEditText().addTextChangedListener(new TextWatcher() {
            private static final String maskDate = "##/##/####";
            boolean isUpdating;
            String old = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^0-10]*", "");
                String mask = maskDate;
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                inputDate.getEditText().setText(mascara);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCEP.getEditText().addTextChangedListener(new TextWatcher() {
            DecimalFormat df = new DecimalFormat("#####.###");
            private boolean editando =  false;
            private String cep;
            private int tamVelho = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!editando) {
                    cep = s.toString().replace("-", "").toString();
                    editando = !editando;
                    if (cep.length() == 5 && 5 > tamVelho) {

                        cep = cep + "-";
                    } else if (cep.length() >= 6) {

                        String parte1 = cep.substring(0, 5);
                        String parte2 = cep.substring(5);
                        cep = parte1 + "-" + parte2;
                    }
                    tamVelho = cep.length();
                    if(cep.length() > 0){
                        inputCEP.getEditText().setTextKeepState(cep, TextView.BufferType.EDITABLE);
                        inputCEP.getEditText().setSelection(cep.length());
                    }

                    editando = !editando;
                }
            }
        });

        inputCPF.getEditText().addTextChangedListener(new TextWatcher() {

            private static final String maskCNPJ = "##.###.###/####-##";
            private static final String maskCPF = "###.###.###-##";
            boolean isUpdating;
            String old = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^0-9]*", "");
                String mask;
                String defaultMask = maskCPF;
                if (str.length() > 11) {
                    defaultMask = maskCNPJ;
                }
                switch (str.length()) {
                    case 11:
                        mask = maskCPF;
                        break;
                    case 14:
                        mask = maskCNPJ;
                        break;

                    default:
                        mask = defaultMask;
                        break;
                }

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                inputCPF.getEditText().setText(mascara);
                inputCPF.getEditText().setSelection(mascara.length());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Pattern pattern;
                Matcher matcher;
                String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                pattern = Pattern.compile(EMAIL_PATTERN);
                CharSequence cs = (CharSequence) s;
                matcher = pattern.matcher(cs);
                if (!(matcher.matches() == true)) {
                    inputEmail.setError("Invalid email");
                } else {
                    inputEmail.setError("");
                }
            }
        });
    }
}