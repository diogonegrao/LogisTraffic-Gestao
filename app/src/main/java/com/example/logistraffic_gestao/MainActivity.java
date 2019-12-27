package com.example.logistraffic_gestao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private int REQUEST_CODE_OP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
