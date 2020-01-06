package com.example.logistraffic_gestao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.logistraffic_gestao.adapter.CustomArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int REQUEST_CODE_OP = 1;
    ArrayList<Carga> arrayCarga = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!arrayCarga.isEmpty()) {
            arrayCarga.clear();
        }
        String url = "http://bdias.000webhostapp.com/myslim/api/cargal";
        // pedido do tipo get que devolve um arrray com todos os contactos
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                    //recebe valores do array
                    JSONArray arr = response.getJSONArray("DATA");

                    for (int i = 0; i < arr.length(); i++) {


                        //cada posição do array passa para um json object para podermos extrair informação
                        JSONObject obj = arr.getJSONObject(i);
                        //colocação da info no array
                        Carga c = new Carga(obj.getInt("id"), obj.getString("nome"), obj.getString("tipo"), obj.getString("cais"), obj.getString("matricula"),
                                obj.getInt("motorista_id"), obj.getInt("loja_id"));

                        //adicion contacto ao array
                        arrayCarga.add(c);


                    }


                    //lista contacto
                    CustomArrayAdapter itemsAdapter = new CustomArrayAdapter(MainActivity.this,arrayCarga);
                    ((ListView) findViewById(R.id.lista)).setAdapter(itemsAdapter);
                } catch (JSONException e) {
                }
                //em caso de erro imprime um erro
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });


        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.sobre:
                Intent j = new Intent(MainActivity.this, SobreActivity.class);
                startActivity(j);
                return true;

            case R.id.adicionar:
                Intent i = new Intent(MainActivity.this, RegistarCargaActivity.class);
                startActivityForResult(i, REQUEST_CODE_OP);
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
