package com.org.jaed.epc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jerry on 29/07/2015.
 */
public class MenuEjercicios extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnArrastrar, btnResuelva, btnLenguaje, btnVf, btnOrdena;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_ejercicios);
        toolbar = (Toolbar)findViewById(R.id.appbarMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Menú");
        btnArrastrar = (Button)findViewById(R.id.btnArrastrar);
        btnResuelva = (Button) findViewById(R.id.btnResolver);
        btnLenguaje = (Button) findViewById(R.id.btnLenguaje);
        btnVf = (Button)findViewById(R.id.btnVf);
        btnOrdena = (Button) findViewById(R.id.btnOrdena);
        btnArrastrar.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_menu_presionado));
        btnResuelva.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_menu_presionado));
        btnLenguaje.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_menu_presionado));
        btnVf.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_menu_presionado));
        btnOrdena.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_menu_presionado));
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        btnArrastrar.setTypeface(font);
        btnResuelva.setTypeface(font);
        btnLenguaje.setTypeface(font);
        btnVf.setTypeface(font);
        btnOrdena.setTypeface(font);
        btnArrastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(getApplicationContext(), Actividad3.class));
            }
        });
        btnResuelva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(getApplicationContext(), MainActivity.class));
            }
        });
        btnLenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(getApplicationContext(), ActividadSopa.class));
            }
        });
        btnVf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(getApplicationContext(), actividad4.class));
            }
        });
        btnOrdena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(getApplicationContext(), actividad5.class));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ejercicios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.alEjercicio){
            startActivity(new Intent().setClass(getApplicationContext(), MenuEjercicios.class));
        }
        if(id == R.id.volverPrincipal){
            startActivity(new Intent().setClass(getApplicationContext(), MainMenu.class));
        }
        if(id == R.id.salirE){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
