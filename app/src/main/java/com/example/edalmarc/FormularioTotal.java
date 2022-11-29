package com.example.edalmarc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormularioTotal extends AppCompatActivity {

    private Button Formulario2, enviarbitacora;
    private EditText editextHoraInicial, editextNombreCliente, editextTelefonoCliente, editextDireccionCliente, editextTipoTecnico, editTextNombreTecnico, editTextDescripcionTrabajo, editTextMaterial, editTextMonto, editTextHoraSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_total);

        editextHoraInicial = (EditText) findViewById(R.id.TiempoDeEntrada);
        editextNombreCliente = (EditText) findViewById(R.id.NombreCliente);
        editextTelefonoCliente = (EditText) findViewById(R.id.telefonoCliente);
        editextDireccionCliente = (EditText)findViewById(R.id.direccionCliente);
        editextTipoTecnico = (EditText) findViewById(R.id.tipoTecnico);
        editTextNombreTecnico = (EditText) findViewById(R.id.nombreTecnico);



        Formulario2 = (Button) findViewById(R.id.GotoFormulario2);
    }
}