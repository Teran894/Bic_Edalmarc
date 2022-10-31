package com.example.edalmarc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDataBase extends AppCompatActivity {

    EditText edtNombre, edtCorreo, edtNumero, edtContrasena, edtFecha;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_data_base);

        edtNombre=(EditText) findViewById(R.id.edtNombre);
        edtCorreo=(EditText) findViewById(R.id.edtCorreo);
        edtNumero=(EditText) findViewById(R.id.edtNumero);
        edtContrasena=(EditText) findViewById(R.id.edtContrasena);
        edtFecha=(EditText) findViewById(R.id.edtFechaRegistro);

        btnAgregar=(Button) findViewById(R.id.btnAgregarUsuario);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agregarUsuario();
            }
        });
    }
    public Connection conexionBD() throws SQLException{
        Connection conexion=null;
        try{
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.100.131;databaseName=Bic-Edalmarc;user=sa;password=123;");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "ERROR AL CONECTARSE", Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }

    public void agregarUsuario(){
        try{
            PreparedStatement pst=conexionBD().prepareStatement("insert into Tecnico values(?,?,?,?,?)");
            pst.setString(1, edtNombre.getText().toString());
            pst.setString(2, edtCorreo.getText().toString());
            pst.setString(3, edtNumero.getText().toString());
            pst.setString(4, edtContrasena.getText().toString());
            pst.setString(5, edtFecha.getText().toString());
            pst.executeUpdate();

            Toast.makeText(getApplicationContext(), "REGISTRO AGREGADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(), "ERROR AL ENVIAR LOS DATOS", Toast.LENGTH_SHORT).show();
        }
    }
}