package com.example.dmiv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText txtId, txtImg, txtCodigo, txtNombre, txtCantidad, txtCosto, txtPrecio, txtid_categoria;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtId = findViewById(R.id.txtId);
        txtImg = findViewById(R.id.txtImg);
        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtCosto = findViewById(R.id.txtCosto);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtid_categoria = findViewById(R.id.txtid_categoria);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LeerWs();
                EnviarWs(txtId.getText().toString(), txtImg.getText().toString(), txtCodigo.getText().toString(), txtNombre.getText().toString(),txtCantidad.getText().toString(), txtCosto.getText().toString(), txtPrecio.getText().toString(), txtid_categoria.getText().toString());
                //Actulizar();
            }
        });
    }

    private void LeerWs(){
        String url = "http://192.168.100.15:8000/api/getProductoId/1";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String id = "id";
                    txtId.setText(jsonObject.getString("id"));
                    String img = "img";
                    txtImg.setText(jsonObject.getString("img"));
                    String codigo = "codigo";
                    txtCodigo.setText(jsonObject.getString("codigo"));
                    String nombre = "nombre";
                    txtNombre.setText(jsonObject.getString("nombre"));
                    String cantidad = "cantidad";
                    txtCantidad.setText(jsonObject.getString("cantidad"));
                    String costo = "costo";
                    txtCosto.setText(jsonObject.getString("costo"));
                    String precio = "precio";
                    txtPrecio.setText(jsonObject.getString("precio"));
                    String id_categoria = "id_categoria";
                    txtid_categoria.setText(jsonObject.getString("id_categoria"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());

            }
        });
        Volley.newRequestQueue(this).add(postRequest);
    }

    private void EnviarWs(String txtId, String txtImg, String txtCodigo, String txtNombre, String txtCantidad, String txtCosto, String txtPrecio, String txtid_categoria) {
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
                params.put("id", txtId);
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

    private  void Actulizar(){

    }


}