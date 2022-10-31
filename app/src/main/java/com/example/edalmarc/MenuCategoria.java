package com.example.edalmarc;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuCategoria extends AppCompatActivity{
    private Button button1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_categoria);

        button1 = (Button) findViewById(R.id.BotonMantenimiento);
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
