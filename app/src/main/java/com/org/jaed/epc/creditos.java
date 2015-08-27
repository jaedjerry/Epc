package com.org.jaed.epc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Jerry on 27/08/2015.
 */
public class creditos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditos);
        Toolbar toolbar = (Toolbar)findViewById(R.id.appbarCreditos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Créditos");
        TextView txtCreditos, txtObjetivo, txtAutores;
        txtCreditos = (TextView)findViewById(R.id.txtcreditos);
        txtObjetivo = (TextView)findViewById(R.id.txtobjetivo);
        txtAutores = (TextView)findViewById(R.id.txtautores);
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        txtCreditos.setTypeface(font);
        txtObjetivo.setTypeface(font);
        txtAutores.setTypeface(font);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ejercicios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.volverPrincipal){
            startActivity(new Intent().setClass(getApplicationContext(), MainMenu.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
