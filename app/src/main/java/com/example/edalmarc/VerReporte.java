package com.example.edalmarc;
/**
 Equipo de trabajo
 * Aaron Peña Camarena
 * Jesus Leyva Rodriguez
 * Daniel Rios Montoya
 * Jose Luis Teran Soto
 **/
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.app.Activity;
import android.widget.TextView;

public class VerReporte extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reporte);



        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter  = ArrayAdapter.createFromResource(this, R.array.Filtrar, R.layout.spinner_item);
        //ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.Filtrar,android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        //staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner




    }

}



