package com.example.edalmarc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.Manifest;
import android.content.pm.PackageManager;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;

public class FirmaPagina extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma_pagina);
        SignaturePad signaturePad = (SignaturePad) findViewById(R.id.EspacioFirmar);
        Button button1 = (Button) findViewById(R.id.GuardarFirma);
        Button button2 = (Button) findViewById(R.id.LimpiarFirma);
        ImageView imageView = (ImageView) findViewById(R.id.firmapreview);

        setTitle("Firma para confirmar");

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder confirmar = new AlertDialog.Builder(FirmaPagina.this);
                confirmar.setTitle("Bic-Edalmarc - Guardar Firmar");
                confirmar.setMessage("Estas seguro de tu firma?").setCancelable(false);
                confirmar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Bitmap bitmap = signaturePad.getSignatureBitmap();
                        imageView.setImageBitmap(bitmap);
                        Toast.makeText(FirmaPagina.this, "Guardaste la firma", Toast.LENGTH_SHORT).show();
                        signaturePad.clear();
                        FirmaPagina.this.finish();
                    }
                });
                confirmar.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(FirmaPagina.this, "Regrese a firmar",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                confirmar.show();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signaturePad.clear();
            }
        });

    }

}