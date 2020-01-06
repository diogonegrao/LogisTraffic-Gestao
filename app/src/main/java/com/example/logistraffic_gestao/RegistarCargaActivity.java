package com.example.logistraffic_gestao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class RegistarCargaActivity extends AppCompatActivity {
  String  Matricula;
    String id_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_carga);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_registo_carga, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cancelar:
                finish();
                return true;

            case R.id.check:
                final EditText nome_carga = findViewById(R.id.nomeCarga2);
                final EditText tipo_carga = findViewById(R.id.tipoCarga2);
                final EditText cais = findViewById(R.id.cais2);
                //final EditText matricula = findViewById(R.id.matricula2);
                String nomeC = nome_carga.getText().toString();
                String tipoC = tipo_carga.getText().toString();
                String Cais = cais.getText().toString();

                String url = "http://bdias.000webhostapp.com/myslim/api/insercarga";


                Map<String, String> jsonParams = new HashMap<String, String>();
                //inserção de dados
                jsonParams.put("nome", nomeC);
                jsonParams.put("tipo", tipoC);
                jsonParams.put("cais", Cais);
                jsonParams.put("matricula", Matricula);
                jsonParams.put("motorista_id",id_1);
                jsonParams.put("loja_id", "1");
                //jsonParams.put("data_carga",);


                //pedido do tipo post com inserção dos parametros em cima introduzidos
                JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //se conseguir inserir imprime um aviso
                            if (response.getBoolean("status")) {

                                Toast.makeText(RegistarCargaActivity.this, response.getString("MSG"), Toast.LENGTH_LONG).show();


                            } else {
                                Toast.makeText(RegistarCargaActivity.this, response.getString("MSG"), Toast.LENGTH_LONG).show();

                            }

                        } catch (JSONException ex) {
                        }


                    }
                    // em caso de erro imprime um erro
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistarCargaActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }) {

                    //especificção dos headers do pedido
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("User-agent", System.getProperty("http.agent"));
                        return headers;
                    }


                };

                //colocação do pedido na fila
                MySingleton.getInstance(RegistarCargaActivity.this).addToRequestQueue(postRequest);
                finish();

            default:
                return super.onOptionsItemSelected(item);


        }

    }


public void Pesquisa(View view){
    final EditText matricula = findViewById(R.id.matricula2);
    Matricula = matricula.getText().toString();

    String url = "http://bdias.000webhostapp.com/myslim/api/pesquisamat/" + Matricula;

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {

            try {
                id_1=response.getString("id");

                Toast.makeText(RegistarCargaActivity.this, id_1, Toast.LENGTH_LONG).show();



            } catch (JSONException e) {

            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(RegistarCargaActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            Log.d("Erro", error.toString());
        }
    });
    MySingleton.getInstance(RegistarCargaActivity.this).addToRequestQueue(jsonObjectRequest);

}





}















