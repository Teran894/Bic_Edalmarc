package com.example.edalmarc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private Button button;
    EditText edtUser, edtcorreo, edtfecha, edtnumero, edtcontrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUser= (EditText) findViewById(R.id.editTextUserName);
        edtcorreo= (EditText) findViewById(R.id.editTextCorreo);
        edtfecha= (EditText) findViewById(R.id.editFechaNacimiento);
        edtcontrasena=(EditText) findViewById(R.id.editTextPassword);
        edtnumero= (EditText) findViewById(R.id.editTextCelular);


        button = (Button) findViewById(R.id.btnRegistro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarDatos("http://192.168.100.131:80/bic-edalmarc/insertar_producto.php");
                /*openVerReporte();*/
            }
        });
    }
    private void insertarDatos(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();

                parametros.put("nombre", edtUser.getText().toString());
                parametros.put("correo", edtcorreo.getText().toString());
                parametros.put("numero", edtnumero.getText().toString());
                parametros.put("contrasena", edtcontrasena.getText().toString());
                parametros.put("fecha_registro", edtfecha.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




    /*
    public void openMenuAdmin(){
        Intent intent = new Intent(this, MenuInicioAdmin.class);
        startActivity(intent);
    }
    */

    public void openVerReporte(){
        Intent intent = new Intent(this, MenuInicioAdmin.class);
        startActivity(intent);
    }
}