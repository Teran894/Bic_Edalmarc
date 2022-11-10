package com.example.edalmarc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Formulario1 extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario1);
        button = (Button) findViewById(R.id.btnInicioSesion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInicio();
            }
        });
    }
    public void openInicio(){
        Intent intent = new Intent(this, formulario2.class);
        startActivity(intent);
    }
}