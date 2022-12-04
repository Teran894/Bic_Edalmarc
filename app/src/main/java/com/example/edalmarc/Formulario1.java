package com.example.edalmarc;
/**
 Equipo de trabajo
 * Aaron Peña Camarena
 * Jesus Leyva Rodriguez
 * Daniel Rios Montoya
 * Jose Luis Teran Soto
 **/
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Formulario1 extends AppCompatActivity {
    private Button gotoformulario2;
    private EditText editextHoraInicial, editextNombreCliente, editextTelefonoCliente, editextDireccionCliente, editextTipoTecnico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario1);

        editextHoraInicial = (EditText) findViewById(R.id.TiempoDeEntrada);
        editextNombreCliente = (EditText) findViewById(R.id.NombreCliente);
        editextTelefonoCliente = (EditText) findViewById(R.id.telefonoCliente);
        editextDireccionCliente = (EditText)findViewById(R.id.direccionCliente);
        editextTipoTecnico = (EditText) findViewById(R.id.tipoTecnico);


        gotoformulario2 = (Button) findViewById(R.id.GotoFormulario2);
        gotoformulario2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInicio();
            }
        });

        editextHoraInicial.addTextChangedListener(loginTextWatcher);
        editextNombreCliente.addTextChangedListener(loginTextWatcher);
        editextTelefonoCliente.addTextChangedListener(loginTextWatcher);
        editextDireccionCliente.addTextChangedListener(loginTextWatcher);
        editextTipoTecnico.addTextChangedListener(loginTextWatcher);


    }


    public void openInicio(){
        Intent intent = new Intent(this, formulario2.class);
        startActivity(intent);
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int count, int after) {
            String HoraInicial = editextHoraInicial.getText().toString().trim();
            String NombreCliente = editextNombreCliente.getText().toString().trim();
            String TelefonoCliente = editextTelefonoCliente.getText().toString().trim();
            String DireccionCliente = editextDireccionCliente.getText().toString().trim();
            String TipoTecnico = editextTipoTecnico.getText().toString().trim();


            gotoformulario2.setEnabled(!HoraInicial.isEmpty() && !NombreCliente.isEmpty() && !TelefonoCliente.isEmpty() && !DireccionCliente.isEmpty() && !TipoTecnico.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

}