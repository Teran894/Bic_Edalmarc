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
import android.view.View;
import android.widget.Button;

public class MenuInicioTrabajador extends AppCompatActivity {
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);

        button1 = (Button) findViewById(R.id.BotonTrabajador);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCrearBitacora();
            }
        });
    }
    public void openCrearBitacora(){
        Intent intent = new Intent(this, CrearBitacora.class);
        startActivity(intent);
    }
}