package com.org.jaed.epc;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Jerry on 21/06/2015.
 */
public class actividad2 extends AppCompatActivity {
    private Button calificar;
    private TextView cruel, odioso, angustia, perro, caer, cascabeleando, amiguito;
    private TextView compañero, alborotando, tropezar, lazarillo, sufrimiento, iracundo, malo;
    private RelativeLayout contenedor;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_2);
        contenedor = (RelativeLayout)findViewById(R.id.contenedorActividad2);//referencia al RelativeLayout del archivo actividad_2.xml
        Toolbar toolbar = (Toolbar)findViewById(R.id.appbar2);//referancia al toolbar (barra de titulo)
        cruel = (TextView)findViewById(R.id.cruel);
        odioso = (TextView)findViewById(R.id.odioso);
        angustia = (TextView)findViewById(R.id.angustia);
        perro = (TextView)findViewById(R.id.perro);
        caer = (TextView)findViewById(R.id.caer);
        cascabeleando = (TextView)findViewById(R.id.cascabeleando);
        amiguito = (TextView)findViewById(R.id.amiguito);
        compañero = (TextView)findViewById(R.id.compañero);
        alborotando = (TextView)findViewById(R.id.alborotando);
        tropezar = (TextView)findViewById(R.id.tropezar);
        lazarillo = (TextView)findViewById(R.id.lazarillo);
        sufrimiento = (TextView)findViewById(R.id.sufrimiento);
        iracundo = (TextView)findViewById(R.id.iracundo);
        malo = (TextView)findViewById(R.id.malo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Arrastra de A a B");
        calificar = (Button)findViewById(R.id.btnCalificar2);//referencia al boton calificar
        if(Build.VERSION.SDK_INT >=21){//si la version del dispositivo es mayor o igual que la 21 (android 5 y superiores)
            calificar.setBackgroundTintList(getResources().getColorStateList(R.color.btn_calificar_presionado));//establecemos el background con efecto a calificar
        }else{
            calificar.setBackgroundColor(getResources().getColor(R.color.btn_calificar_presionado));//establecemos solamente un background sin efecto para dispositivos menores a android 5
        }
        final RelativeLayout.LayoutParams paramsCruel = (RelativeLayout.LayoutParams)cruel.getLayoutParams();
        final RelativeLayout.LayoutParams paramsMalo = (RelativeLayout.LayoutParams)malo.getLayoutParams();
        final Drawable bgMalo = malo.getBackground();
        final RelativeLayout.LayoutParams paramsIracundo = (RelativeLayout.LayoutParams)iracundo.getLayoutParams();
        final Drawable bgIracundo = iracundo.getBackground();
        cruel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        ClipData clipData = ClipData.newPlainText("cruel","cruel");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if(malo.getVisibility() == View.GONE){
                            malo.setVisibility(View.VISIBLE);
                            malo.setBackgroundDrawable(bgMalo);
                            Log.e("malo", "malo estaba oculto y ahora es visible");
                        }
                        if(iracundo.getVisibility() == View.GONE){
                            iracundo.setVisibility(View.VISIBLE);
                            iracundo.setBackgroundDrawable(bgIracundo);
                        }
                        cruel.setLayoutParams(paramsCruel);
                        break;
                }
                return true;
            }
        });
        malo.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                view = (View) event.getLocalState();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundResource(R.drawable.borde_2);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundDrawable(bgMalo);
                        break;
                    case DragEvent.ACTION_DROP:
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("cruel")) {
                            Log.e("arrastre", "arrastre a Cruel con malo");
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsMalo);
                            view.setVisibility(View.VISIBLE);
                        } else {
                            Log.e("null drop", "el view es null");
                        }
                        v.setVisibility(View.GONE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if (view != null) {
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    view.setVisibility(View.VISIBLE);
                                }
                            });
                        } else {
                            Log.e("null ended", "el view es null");
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        iracundo.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                view = (View) event.getLocalState();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundResource(R.drawable.borde_2);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundDrawable(bgIracundo);
                        break;
                    case DragEvent.ACTION_DROP:
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("odioso")) {
                            Log.e("arrastre", "arrastre a Cruel con malo");
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsIracundo);
                            view.setVisibility(View.VISIBLE);
                        } else {
                            Log.e("null drop", "el view es null");
                        }
                        v.setVisibility(View.GONE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if (view != null) {
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    view.setVisibility(View.VISIBLE);
                                }
                            });
                        } else {
                            Log.e("null ended", "el view es null");
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        calificar.setOnClickListener(new View.OnClickListener() {//evento boton calificar
            @Override
            public void onClick(View v) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.siguiente){
            //crea una variable de tipo Intent, en android el cambio de una pantalla se realiza mediante Intent y recibe de donde y hacia donde va a ir
            //por eso los parametros son el contexto de esta actividad y la clase que maneja la segunda actividad
            Intent i = new Intent().setClass(getApplicationContext(),MainActivity.class);
            //inicia la actividad y recibe el intent creado
            startActivity(i);
            //animación de trancision para el cambio de actividad (pantalla)
            //la siguiente pantalla entra por la izquirda y la que se está viendo sale por la izquierda
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
