package com.example.edalmarc;

import androidx.annotation.Nullable;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class FormularioTotal extends AppCompatActivity {

    private Button enviarbitacora,imageButton, firma;
    private EditText editextHoraInicial, editextNombreCliente, editextTelefonoCliente, editextDireccionCliente, editextTipoTecnico, editTextNombreTecnico, editTextDescripcionTrabajo, editTextMaterial, editTextMonto, editTextHoraSalida;
    private ImageView imagenasubir;

    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_total);


        setTitle("Formulario de jornada");

        editextHoraInicial = (EditText) findViewById(R.id.TiempoDeEntrada);
        editextNombreCliente = (EditText) findViewById(R.id.NombreCliente);
        editextTelefonoCliente = (EditText) findViewById(R.id.telefonoCliente);
        editextDireccionCliente = (EditText)findViewById(R.id.direccionCliente);
        editextTipoTecnico = (EditText) findViewById(R.id.tipoTecnico);
        editTextNombreTecnico = (EditText) findViewById(R.id.nombreTecnico);
        editTextDescripcionTrabajo = (EditText) findViewById(R.id.DescripcionTrabajo);
        editTextMaterial = (EditText) findViewById(R.id.MaterialDesc);
        editTextMonto = (EditText) findViewById(R.id.MaterialDinero);
        editTextHoraSalida = (EditText) findViewById(R.id.TiempoDeSalida);

        editextHoraInicial.addTextChangedListener(loginTextWatcher);
        editextNombreCliente.addTextChangedListener(loginTextWatcher);
        editextTelefonoCliente.addTextChangedListener(loginTextWatcher);
        editextDireccionCliente.addTextChangedListener(loginTextWatcher);
        editextTipoTecnico.addTextChangedListener(loginTextWatcher);
        editTextNombreTecnico.addTextChangedListener(loginTextWatcher);
        editTextDescripcionTrabajo.addTextChangedListener(loginTextWatcher);
        editTextMaterial.addTextChangedListener(loginTextWatcher);
        editTextMonto.addTextChangedListener(loginTextWatcher);
        editTextHoraSalida.addTextChangedListener(loginTextWatcher);


        enviarbitacora = (Button) findViewById(R.id.EnviarReporte);

        imageButton = (Button) findViewById(R.id.imagebutton);
        imagenasubir = (ImageView) findViewById(R.id.imagenasubir);

        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent subirimagen = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(subirimagen, 3);
            }
        });

        enviarbitacora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarreporter();
                insertarReporte("http://192.168.100.131:80/Bic-Edalmarc/insertar_reporte.php");
            }
        });

        Ponerhorainicial();

    }

    public void Ponerhorainicial(){
        Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);

        EditText edt_time=(EditText) findViewById(R.id.TiempoDeEntrada);
        edt_time.setText(mHour +":"+ mMinute);

    }

    public String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void enviarreporter(){
        Intent intent = new Intent(this, FirmaPagina.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "REPORTE ENVIADO", Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "REPORTE COMPLETADO - FIRME PARA CONFIRMAR EL TRABAJO", Toast.LENGTH_SHORT).show();


    }
    public void insertarReporte(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "REPORTE ENVIADO", Toast.LENGTH_SHORT).show();
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
                String imagen = getStringImagen(bitmap);

                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("nombre_cliente", editextNombreCliente.getText().toString());
                parametros.put("telefono", editextTelefonoCliente.getText().toString());
                parametros.put("direccion_cliente", editextDireccionCliente.getText().toString());
                parametros.put("nombre_tecnico", editTextNombreTecnico.getText().toString());
                parametros.put("tipo_tecnico", editextTipoTecnico.getText().toString());
                parametros.put("descripcion", editTextDescripcionTrabajo.getText().toString());
                parametros.put("materiales", editTextMaterial.getText().toString());
                parametros.put("imagen", imagen);
                parametros.put("importe", editTextMonto.getText().toString());
                parametros.put("hora_inicial", editextHoraInicial.getText().toString());
                parametros.put("hora_final", editTextHoraSalida.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== RESULT_OK && data !=null){
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                imagenasubir.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int count, int after) {
            String HoraInicioInput = editextHoraInicial.getText().toString().trim();

            String NombreClienteInput = editextNombreCliente.getText().toString().trim();
            String TelefonoClienteInput = editextTelefonoCliente.getText().toString().trim();
            String DireccionClienteInput = editextDireccionCliente.getText().toString().trim();

            String TipoTecnicoInput = editextTipoTecnico.getText().toString().trim();
            String NombreTecnicoInput = editTextNombreTecnico.getText().toString().trim();

            String HoraFinalInput = editTextHoraSalida.getText().toString().trim();

            String DescripcionTrabajoInput = editTextDescripcionTrabajo.getText().toString().trim();
/*
            enviarbitacora.setEnabled(!DescripcionTrabajoInput.isEmpty() && DescripcionTrabajoInput.length() > 25 && !HoraInicioInput.isEmpty() &&
                    !NombreClienteInput.isEmpty() && !TelefonoClienteInput.isEmpty() && !DireccionClienteInput.isEmpty() && !TipoTecnicoInput.isEmpty() && !NombreTecnicoInput.isEmpty() &&
                    !HoraFinalInput.isEmpty());*/
        }

        @Override
        public void afterTextChanged(Editable s) {
            /*
            String DescripcionTrabajoInput = editTextDescripcionTrabajo.getText().toString().trim();
            if (DescripcionTrabajoInput.isEmpty() || DescripcionTrabajoInput.length() < 25) {
                Toast.makeText(FormularioTotal.this, "Su descripcion es muy corta", Toast.LENGTH_SHORT).show();
            }
            */
        }
    };
}