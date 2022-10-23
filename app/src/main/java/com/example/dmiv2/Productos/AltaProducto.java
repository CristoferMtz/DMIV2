package com.example.dmiv2.Productos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dmiv2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AltaProducto extends AppCompatActivity {

    EditText txtImg, txtCodigo, txtNombre, txtCantidad, txtCosto, txtPrecio, txtid_categoria;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_producto);

        txtImg = findViewById(R.id.txtImg);
        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtCosto = findViewById(R.id.txtCosto);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtid_categoria = findViewById(R.id.txtid_categoria);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarWs(txtImg.getText().toString(), txtCodigo.getText().toString(), txtNombre.getText().toString(),txtCantidad.getText().toString(), txtCosto.getText().toString(), txtPrecio.getText().toString(), txtid_categoria.getText().toString());
            }
        });
    }

        private void EnviarWs(String txtImg, String txtCodigo, String txtNombre, String txtCantidad, String txtCosto, String txtPrecio, String txtid_categoria) {
            String url = "http://192.168.100.15:8000/api/altap";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error", error.getMessage());

                }
            })
            {
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<>();
                    params.put("img", txtImg);
                    params.put("codigo", txtCodigo);
                    params.put("nombre", txtNombre);
                    params.put("cantidad", txtCantidad);
                    params.put("precio", txtPrecio);
                    params.put("costo", txtCosto);
                    params.put("id_categoria", txtid_categoria);
                    return params;
                }
            };
            Volley.newRequestQueue(this).add(postRequest);
    }
}