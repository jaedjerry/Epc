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
public class MainMenu extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnLeer, btnEjercicios, btnCreditos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        toolbar = (Toolbar)findViewById(R.id.appbarMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Menú");
        btnLeer = (Button)findViewById(R.id.btnLeer);
        btnEjercicios = (Button) findViewById(R.id.btnEjercicios);
        btnCreditos = (Button) findViewById(R.id.btnCreditos);
        btnLeer.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_menu_presionado));
        btnEjercicios.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_menu_presionado));
        btnCreditos.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_menu_presionado));
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        btnLeer.setTypeface(font);
        btnEjercicios.setTypeface(font);
        btnCreditos.setTypeface(font);
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent().setClass(getApplicationContext(), Cuento.class);
                startActivity(intent);
            }
        });
        btnEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(),MenuEjercicios.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_secundario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.salirM){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
