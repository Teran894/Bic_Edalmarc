package com.example.edalmarc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

public class MainActivity extends AppCompatActivity {
    private Button login, showHideBtn;
    private EditText editextUsername;
    private EditText editextPassword;
    private CheckBox showPSSWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editextUsername = findViewById(R.id.editTextUserName);
        editextPassword = findViewById(R.id.editTextPassword);

        showPSSWD = (CheckBox) findViewById(R.id.showPSSWD);
        showPSSWD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    editextPassword.setTransformationMethod((HideReturnsTransformationMethod.getInstance()));
                } else {
                    editextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        login = (Button) findViewById(R.id.btnInicioSesion);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInicio();
            }
        });
        editextUsername.addTextChangedListener(loginTextWatcher);
        editextPassword.addTextChangedListener(loginTextWatcher);
    }
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int count, int after) {
            String usernameInput = editextUsername.getText().toString().trim();
            String passwordInput = editextPassword.getText().toString().trim();

            login.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void openInicio(){
        Intent intent = new Intent(this, Formulario1.class);
        startActivity(intent);
    }
}