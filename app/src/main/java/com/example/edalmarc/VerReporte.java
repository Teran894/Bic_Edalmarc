package com.example.edalmarc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

public class VerReporte extends AppCompatActivity {

    private Button Cerrapplicacion, crearNuevaBitacora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reporte);

        Cerrapplicacion = (Button) findViewById(R.id.CerrarAPP);
        crearNuevaBitacora = (Button) findViewById(R.id.OtroReporte);

        Cerrapplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CerrarApp();
            }
        });

        crearNuevaBitacora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NuevoReporte();
            }
        });


    }

    public void NuevoReporte(){
        Intent intent = new Intent(this, FormularioTotal.class);
        startActivity(intent);
    }

    public void CerrarApp(){
        AlertDialog.Builder confirmar = new AlertDialog.Builder(VerReporte.this);
        confirmar.setTitle("Bic-Edalmarc - Cerrar applicacion");
        confirmar.setMessage("Estas seguro de cerrar la app?").setCancelable(false);
        confirmar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(VerReporte.this, "Has salido de la applicacion", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        });
        confirmar.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        confirmar.show();
    }

}



