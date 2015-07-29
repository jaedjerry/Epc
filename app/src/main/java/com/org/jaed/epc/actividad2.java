package com.org.jaed.epc;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Jerry on 21/06/2015.
 */
public class actividad2 extends AppCompatActivity {
    private Button calificar;
    private TextView cruel, odioso, angustia, perro, caer, cascabeleando, amiguito;
    private TextView compañero, alborotando, tropezar, lazarillo, sufrimiento, iracundo, malo;
    private RelativeLayout contenedor;
    private View view;
    private int aciertos, intentos;
    private View child;
    private boolean ocupado1, ocupado2, ocupado3, ocupado4, ocupado5, ocupado6, ocupado7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_2);
        contenedor = (RelativeLayout)findViewById(R.id.contenedorActividad2);//referencia al RelativeLayout del archivo actividad_2.xml
        Toolbar toolbar = (Toolbar)findViewById(R.id.appbar2);//referancia al toolbar (barra de titulo)
        cruel = (TextView)findViewById(R.id.cruel);
        odioso = (TextView)findViewById(R.id.odioso);
        angustia = (TextView)findViewById(R.id.angustia);
        perro = (TextView)findViewById(R.id.Perro);
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
        if(Build.VERSION.SDK_INT > 20){//si la version del dispositivo es mayor o igual que la 21 (android 5 y superiores)
            calificar.setBackgroundTintList(getResources().getColorStateList(R.color.btn_calificar_presionado));//establecemos el background con efecto a calificar
        }else{
            calificar.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_calificar_presionado));
        }
        //Datos por default de la columna A
        final RelativeLayout.LayoutParams paramsCruel = (RelativeLayout.LayoutParams)cruel.getLayoutParams();
        final RelativeLayout.LayoutParams paramsOdioso = (RelativeLayout.LayoutParams)odioso.getLayoutParams();
        final RelativeLayout.LayoutParams paramsAngustia = (RelativeLayout.LayoutParams)angustia.getLayoutParams();
        final RelativeLayout.LayoutParams paramsPerro = (RelativeLayout.LayoutParams) perro.getLayoutParams();
        final RelativeLayout.LayoutParams paramsCaer = (RelativeLayout.LayoutParams)caer.getLayoutParams();
        final RelativeLayout.LayoutParams paramsCascabeleando = (RelativeLayout.LayoutParams)cascabeleando.getLayoutParams();
        final RelativeLayout.LayoutParams paramsAmiguito = (RelativeLayout.LayoutParams)amiguito.getLayoutParams();
        //Datos por default de la columna B
        final RelativeLayout.LayoutParams paramsMalo = (RelativeLayout.LayoutParams)malo.getLayoutParams();
        final Drawable bgMalo = malo.getBackground();
        final RelativeLayout.LayoutParams paramsIracundo = (RelativeLayout.LayoutParams)iracundo.getLayoutParams();
        final Drawable bgIracundo = iracundo.getBackground();
        final RelativeLayout.LayoutParams paramsSufrimiento = (RelativeLayout.LayoutParams)sufrimiento.getLayoutParams();
        final Drawable bgSufrimiento = sufrimiento.getBackground();
        final RelativeLayout.LayoutParams paramsLazarillo = (RelativeLayout.LayoutParams)lazarillo.getLayoutParams();
        final Drawable bgLazarillo = lazarillo.getBackground();
        final RelativeLayout.LayoutParams paramsTropezar = (RelativeLayout.LayoutParams)tropezar.getLayoutParams();
        final Drawable bgTropezar = tropezar.getBackground();
        final RelativeLayout.LayoutParams paramsAlborotando = (RelativeLayout.LayoutParams)alborotando.getLayoutParams();
        final Drawable bgAlborotando = alborotando.getBackground();
        final RelativeLayout.LayoutParams paramsCompañero = (RelativeLayout.LayoutParams)compañero.getLayoutParams();
        final Drawable bgCompañero = compañero.getBackground();
        cruel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsMalo){
                            ocupado1 = false;
                            if(aciertos>0){
                                aciertos--;
                            }
                        }
                        if (v.getLayoutParams() == paramsIracundo) {
                            ocupado2 = false;
                        }
                        if (v.getLayoutParams() == paramsSufrimiento) {
                            ocupado3 = false;
                        }
                        if (v.getLayoutParams() == paramsLazarillo) {
                            ocupado4 = false;
                        }
                        if (v.getLayoutParams() == paramsTropezar) {
                            ocupado5 = false;
                        }
                        if (v.getLayoutParams() == paramsAlborotando) {
                            ocupado6 = false;
                        }
                        if (v.getLayoutParams() == paramsCompañero) {
                            ocupado7 = false;
                        }
                        ClipData clipData = ClipData.newPlainText("cruel", "cruel");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if (malo.getVisibility() == View.GONE && !ocupado1) {
                            malo.setVisibility(View.VISIBLE);
                            malo.setBackgroundDrawable(bgMalo);
                            Log.e("malo", "malo estaba oculto y ahora es visible");
                        }
                        if (iracundo.getVisibility() == View.GONE && !ocupado2) {
                            iracundo.setVisibility(View.VISIBLE);
                            iracundo.setBackgroundDrawable(bgIracundo);
                        }
                        if (sufrimiento.getVisibility() == View.GONE && !ocupado3) {
                            sufrimiento.setVisibility(View.VISIBLE);
                            sufrimiento.setBackgroundDrawable(bgSufrimiento);
                        }
                        if (lazarillo.getVisibility() == View.GONE && !ocupado4) {
                            lazarillo.setVisibility(View.VISIBLE);
                            lazarillo.setBackgroundDrawable(bgLazarillo);
                        }
                        if (tropezar.getVisibility() == View.GONE && !ocupado5) {
                            tropezar.setVisibility(View.VISIBLE);
                            tropezar.setBackgroundDrawable(bgTropezar);
                        }
                        if (alborotando.getVisibility() == View.GONE && !ocupado6) {
                            alborotando.setVisibility(View.VISIBLE);
                            alborotando.setBackgroundDrawable(bgAlborotando);
                        }
                        if (compañero.getVisibility() == View.GONE && !ocupado7) {
                            compañero.setVisibility(View.VISIBLE);
                            compañero.setBackgroundDrawable(bgCompañero);
                        }
                        cruel.setLayoutParams(paramsCruel);
                        break;
                }
                return true;
            }
        });
        odioso.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsMalo)
                            ocupado1 = false;
                        if (v.getLayoutParams() == paramsIracundo) {
                            ocupado2 = false;
                            if(aciertos>0){
                                aciertos--;
                            }
                        }
                        if (v.getLayoutParams() == paramsSufrimiento) {
                            ocupado3 = false;
                        }
                        if (v.getLayoutParams() == paramsLazarillo) {
                            ocupado4 = false;
                        }
                        if (v.getLayoutParams() == paramsTropezar) {
                            ocupado5 = false;
                        }
                        if (v.getLayoutParams() == paramsAlborotando) {
                            ocupado6 = false;
                        }
                        if (v.getLayoutParams() == paramsCompañero) {
                            ocupado7 = false;
                        }
                        ClipData clipData = ClipData.newPlainText("odioso","odioso");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if(malo.getVisibility() == View.GONE && !ocupado1){
                            malo.setVisibility(View.VISIBLE);
                            malo.setBackgroundDrawable(bgMalo);
                            Log.e("malo", "malo estaba oculto y ahora es visible");
                        }
                        if(iracundo.getVisibility() == View.GONE && !ocupado2){
                            iracundo.setVisibility(View.VISIBLE);
                            iracundo.setBackgroundDrawable(bgIracundo);
                        }
                        if(sufrimiento.getVisibility() == View.GONE && !ocupado3){
                            sufrimiento.setVisibility(View.VISIBLE);
                            sufrimiento.setBackgroundDrawable(bgSufrimiento);
                        }
                        if(lazarillo.getVisibility() == View.GONE && !ocupado4){
                            lazarillo.setVisibility(View.VISIBLE);
                            lazarillo.setBackgroundDrawable(bgLazarillo);
                        }
                        if(tropezar.getVisibility() == View.GONE && !ocupado5){
                            tropezar.setVisibility(View.VISIBLE);
                            tropezar.setBackgroundDrawable(bgTropezar);
                        }
                        if(alborotando.getVisibility() == View.GONE && !ocupado6){
                            alborotando.setVisibility(View.VISIBLE);
                            alborotando.setBackgroundDrawable(bgAlborotando);
                        }
                        if(compañero.getVisibility() == View.GONE && !ocupado7){
                            compañero.setVisibility(View.VISIBLE);
                            compañero.setBackgroundDrawable(bgCompañero);
                        }
                        odioso.setLayoutParams(paramsOdioso);
                        break;
                }
                return true;
            }
        });
        angustia.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsMalo)
                            ocupado1 = false;
                        if (v.getLayoutParams() == paramsIracundo) {
                            ocupado2 = false;
                        }
                        if (v.getLayoutParams() == paramsSufrimiento) {
                            ocupado3 = false;
                            if(aciertos>0){
                                aciertos--;
                            }
                        }
                        if (v.getLayoutParams() == paramsLazarillo) {
                            ocupado4 = false;
                        }
                        if (v.getLayoutParams() == paramsTropezar) {
                            ocupado5 = false;
                        }
                        if (v.getLayoutParams() == paramsAlborotando) {
                            ocupado6 = false;
                        }
                        if (v.getLayoutParams() == paramsCompañero) {
                            ocupado7 = false;
                        }
                        ClipData clipData = ClipData.newPlainText("angustia", "angustia");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if(malo.getVisibility() == View.GONE && !ocupado1){
                            malo.setVisibility(View.VISIBLE);
                            malo.setBackgroundDrawable(bgMalo);
                            Log.e("malo", "malo estaba oculto y ahora es visible");
                        }
                        if(iracundo.getVisibility() == View.GONE && !ocupado2){
                            iracundo.setVisibility(View.VISIBLE);
                            iracundo.setBackgroundDrawable(bgIracundo);
                        }
                        if(sufrimiento.getVisibility() == View.GONE && !ocupado3){
                            sufrimiento.setVisibility(View.VISIBLE);
                            sufrimiento.setBackgroundDrawable(bgSufrimiento);
                        }
                        if(lazarillo.getVisibility() == View.GONE && !ocupado4){
                            lazarillo.setVisibility(View.VISIBLE);
                            lazarillo.setBackgroundDrawable(bgLazarillo);
                        }
                        if(tropezar.getVisibility() == View.GONE && !ocupado5){
                            tropezar.setVisibility(View.VISIBLE);
                            tropezar.setBackgroundDrawable(bgTropezar);
                        }
                        if(alborotando.getVisibility() == View.GONE && !ocupado6){
                            alborotando.setVisibility(View.VISIBLE);
                            alborotando.setBackgroundDrawable(bgAlborotando);
                        }
                        if(compañero.getVisibility() == View.GONE && !ocupado7){
                            compañero.setVisibility(View.VISIBLE);
                            compañero.setBackgroundDrawable(bgCompañero);
                        }
                        angustia.setLayoutParams(paramsAngustia);
                        break;
                }
                return true;
            }
        });
        perro.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsMalo)
                            ocupado1 = false;
                        if (v.getLayoutParams() == paramsIracundo) {
                            ocupado2 = false;
                        }
                        if (v.getLayoutParams() == paramsSufrimiento) {
                            ocupado3 = false;
                        }
                        if (v.getLayoutParams() == paramsLazarillo) {
                            ocupado4 = false;
                            if(aciertos>0){
                                aciertos--;
                            }
                        }
                        if (v.getLayoutParams() == paramsTropezar) {
                            ocupado5 = false;
                        }
                        if (v.getLayoutParams() == paramsAlborotando) {
                            ocupado6 = false;
                        }
                        if (v.getLayoutParams() == paramsCompañero) {
                            ocupado7 = false;
                        }
                        ClipData clipData = ClipData.newPlainText("perro","perro");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if(malo.getVisibility() == View.GONE && !ocupado1){
                            malo.setVisibility(View.VISIBLE);
                            malo.setBackgroundDrawable(bgMalo);
                            Log.e("malo", "malo estaba oculto y ahora es visible");
                        }
                        if(iracundo.getVisibility() == View.GONE && !ocupado2){
                            iracundo.setVisibility(View.VISIBLE);
                            iracundo.setBackgroundDrawable(bgIracundo);
                        }
                        if(sufrimiento.getVisibility() == View.GONE && !ocupado3){
                            sufrimiento.setVisibility(View.VISIBLE);
                            sufrimiento.setBackgroundDrawable(bgSufrimiento);
                        }
                        if(lazarillo.getVisibility() == View.GONE && !ocupado4){
                            lazarillo.setVisibility(View.VISIBLE);
                            lazarillo.setBackgroundDrawable(bgLazarillo);
                        }
                        if(tropezar.getVisibility() == View.GONE && !ocupado5){
                            tropezar.setVisibility(View.VISIBLE);
                            tropezar.setBackgroundDrawable(bgTropezar);
                        }
                        if(alborotando.getVisibility() == View.GONE && !ocupado6){
                            alborotando.setVisibility(View.VISIBLE);
                            alborotando.setBackgroundDrawable(bgAlborotando);
                        }
                        if(compañero.getVisibility() == View.GONE && !ocupado7){
                            compañero.setVisibility(View.VISIBLE);
                            compañero.setBackgroundDrawable(bgCompañero);
                        }
                        perro.setLayoutParams(paramsPerro);
                        break;
                }
                return true;
            }
        });
        caer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsMalo)
                            ocupado1 = false;
                        if (v.getLayoutParams() == paramsIracundo) {
                            ocupado2 = false;
                        }
                        if (v.getLayoutParams() == paramsSufrimiento) {
                            ocupado3 = false;
                        }
                        if (v.getLayoutParams() == paramsLazarillo) {
                            ocupado4 = false;
                        }
                        if (v.getLayoutParams() == paramsTropezar) {
                            ocupado5 = false;
                            if(aciertos>0){
                                aciertos--;
                            }
                        }
                        if (v.getLayoutParams() == paramsAlborotando) {
                            ocupado6 = false;
                        }
                        if (v.getLayoutParams() == paramsCompañero) {
                            ocupado7 = false;
                        }
                        ClipData clipData = ClipData.newPlainText("caer","caer");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if(malo.getVisibility() == View.GONE && !ocupado1){
                            malo.setVisibility(View.VISIBLE);
                            malo.setBackgroundDrawable(bgMalo);
                            Log.e("malo", "malo estaba oculto y ahora es visible");
                        }
                        if(iracundo.getVisibility() == View.GONE && !ocupado2){
                            iracundo.setVisibility(View.VISIBLE);
                            iracundo.setBackgroundDrawable(bgIracundo);
                        }
                        if(sufrimiento.getVisibility() == View.GONE && !ocupado3){
                            sufrimiento.setVisibility(View.VISIBLE);
                            sufrimiento.setBackgroundDrawable(bgSufrimiento);
                        }
                        if(lazarillo.getVisibility() == View.GONE && !ocupado4){
                            lazarillo.setVisibility(View.VISIBLE);
                            lazarillo.setBackgroundDrawable(bgLazarillo);
                        }
                        if(tropezar.getVisibility() == View.GONE && !ocupado5){
                            tropezar.setVisibility(View.VISIBLE);
                            tropezar.setBackgroundDrawable(bgTropezar);
                        }
                        if(alborotando.getVisibility() == View.GONE && !ocupado6){
                            alborotando.setVisibility(View.VISIBLE);
                            alborotando.setBackgroundDrawable(bgAlborotando);
                        }
                        if(compañero.getVisibility() == View.GONE && !ocupado7){
                            compañero.setVisibility(View.VISIBLE);
                            compañero.setBackgroundDrawable(bgCompañero);
                        }
                        caer.setLayoutParams(paramsCaer);
                        break;
                }
                return true;
            }
        });
        cascabeleando.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsMalo)
                            ocupado1 = false;
                        if (v.getLayoutParams() == paramsIracundo) {
                            ocupado2 = false;
                        }
                        if (v.getLayoutParams() == paramsSufrimiento) {
                            ocupado3 = false;
                        }
                        if (v.getLayoutParams() == paramsLazarillo) {
                            ocupado4 = false;
                        }
                        if (v.getLayoutParams() == paramsTropezar) {
                            ocupado5 = false;
                        }
                        if (v.getLayoutParams() == paramsAlborotando) {
                            ocupado6 = false;
                            if(aciertos>0){
                                aciertos--;
                            }
                        }
                        if (v.getLayoutParams() == paramsCompañero) {
                            ocupado7 = false;
                        }
                        ClipData clipData = ClipData.newPlainText("cascabeleando","cascabeleando");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if(malo.getVisibility() == View.GONE && !ocupado1){
                            malo.setVisibility(View.VISIBLE);
                            malo.setBackgroundDrawable(bgMalo);
                            Log.e("malo", "malo estaba oculto y ahora es visible");
                        }
                        if(iracundo.getVisibility() == View.GONE && !ocupado2){
                            iracundo.setVisibility(View.VISIBLE);
                            iracundo.setBackgroundDrawable(bgIracundo);
                        }
                        if(sufrimiento.getVisibility() == View.GONE && !ocupado3){
                            sufrimiento.setVisibility(View.VISIBLE);
                            sufrimiento.setBackgroundDrawable(bgSufrimiento);
                        }
                        if(lazarillo.getVisibility() == View.GONE && !ocupado4){
                            lazarillo.setVisibility(View.VISIBLE);
                            lazarillo.setBackgroundDrawable(bgLazarillo);
                        }
                        if(tropezar.getVisibility() == View.GONE && !ocupado5){
                            tropezar.setVisibility(View.VISIBLE);
                            tropezar.setBackgroundDrawable(bgTropezar);
                        }
                        if(alborotando.getVisibility() == View.GONE && !ocupado6){
                            alborotando.setVisibility(View.VISIBLE);
                            alborotando.setBackgroundDrawable(bgAlborotando);
                        }
                        if(compañero.getVisibility() == View.GONE && !ocupado7){
                            compañero.setVisibility(View.VISIBLE);
                            compañero.setBackgroundDrawable(bgCompañero);
                        }
                        cascabeleando.setLayoutParams(paramsCascabeleando);
                        break;
                }
                return true;
            }
        });
        amiguito.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsMalo)
                            ocupado1 = false;
                        if (v.getLayoutParams() == paramsIracundo) {
                            ocupado2 = false;
                        }
                        if (v.getLayoutParams() == paramsSufrimiento) {
                            ocupado3 = false;
                        }
                        if (v.getLayoutParams() == paramsLazarillo) {
                            ocupado4 = false;
                        }
                        if (v.getLayoutParams() == paramsTropezar) {
                            ocupado5 = false;
                        }
                        if (v.getLayoutParams() == paramsAlborotando) {
                            ocupado6 = false;
                        }
                        if (v.getLayoutParams() == paramsCompañero) {
                            ocupado7 = false;
                            if(aciertos>0){
                                aciertos--;
                            }
                        }
                        ClipData clipData = ClipData.newPlainText("amiguito","amiguito");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if(malo.getVisibility() == View.GONE && !ocupado1){
                            malo.setVisibility(View.VISIBLE);
                            malo.setBackgroundDrawable(bgMalo);
                            Log.e("malo", "malo estaba oculto y ahora es visible");
                        }
                        if(iracundo.getVisibility() == View.GONE && !ocupado2){
                            iracundo.setVisibility(View.VISIBLE);
                            iracundo.setBackgroundDrawable(bgIracundo);
                        }
                        if(sufrimiento.getVisibility() == View.GONE && !ocupado3){
                            sufrimiento.setVisibility(View.VISIBLE);
                            sufrimiento.setBackgroundDrawable(bgSufrimiento);
                        }
                        if(lazarillo.getVisibility() == View.GONE && !ocupado4){
                            lazarillo.setVisibility(View.VISIBLE);
                            lazarillo.setBackgroundDrawable(bgLazarillo);
                        }
                        if(tropezar.getVisibility() == View.GONE && !ocupado5){
                            tropezar.setVisibility(View.VISIBLE);
                            tropezar.setBackgroundDrawable(bgTropezar);
                        }
                        if(alborotando.getVisibility() == View.GONE && !ocupado6){
                            alborotando.setVisibility(View.VISIBLE);
                            alborotando.setBackgroundDrawable(bgAlborotando);
                        }
                        if(compañero.getVisibility() == View.GONE && !ocupado7){
                            compañero.setVisibility(View.VISIBLE);
                            compañero.setBackgroundDrawable(bgCompañero);
                        }
                        amiguito.setLayoutParams(paramsAmiguito);
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
                        ocupado1 = true;
                        if(ocupado1){
                            Log.e("ocupado", "estoy ocupado");
                        }else{
                            Log.e("libre", "estoy libre");
                        }
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("cruel")) {
                            Log.e("arrastre", "arrastre a Cruel con malo");
                            aciertos++;
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
                        ocupado2 = true;
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("odioso")) {
                            Log.e("arrastre", "arrastre a odioso con iracundo");
                            aciertos++;
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
        sufrimiento.setOnDragListener(new View.OnDragListener() {
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
                        v.setBackgroundDrawable(bgSufrimiento);
                        break;
                    case DragEvent.ACTION_DROP:
                        ocupado3 = true;
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("angustia")) {
                            Log.e("arrastre", "arrastre a angustia con sufrimiento");
                            aciertos++;
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsSufrimiento);
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
        lazarillo.setOnDragListener(new View.OnDragListener() {
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
                        v.setBackgroundDrawable(bgLazarillo);
                        break;
                    case DragEvent.ACTION_DROP:
                        ocupado4 = true;
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("perro")) {
                            Log.e("arrastre", "arrastre a perro con lazarillo");
                            aciertos++;
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsLazarillo);
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
        tropezar.setOnDragListener(new View.OnDragListener() {
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
                        v.setBackgroundDrawable(bgTropezar);
                        break;
                    case DragEvent.ACTION_DROP:
                        ocupado5 = true;
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("caer")) {
                            Log.e("arrastre", "arrastre a caer con tropezar");
                            aciertos++;
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsTropezar);
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
        alborotando.setOnDragListener(new View.OnDragListener() {
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
                        v.setBackgroundDrawable(bgAlborotando);
                        break;
                    case DragEvent.ACTION_DROP:
                        ocupado6 = true;
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("cascabeleando")) {
                            Log.e("arrastre", "arrastre a cascabeleando con alborotando");
                            aciertos++;
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsAlborotando);
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
        compañero.setOnDragListener(new View.OnDragListener() {
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
                        v.setBackgroundDrawable(bgCompañero);
                        break;
                    case DragEvent.ACTION_DROP:
                        ocupado7 = true;
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("amiguito")) {
                            Log.e("arrastre", "arrastre a amiguito con compañero");
                            aciertos++;
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsCompañero);
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
        child = getLayoutInflater().inflate(R.layout.card_resultados, contenedor, false);
        contenedor.addView(child);
        child.setVisibility(View.GONE);
        calificar.setOnClickListener(new View.OnClickListener() {//evento boton calificar
            @Override
            public void onClick(View v) {
                if(intentos<=3){
                    intentos++;
                }
                if(intentos > 3){
                    Toast t = Toast.makeText(getApplicationContext(),"Se te agotaron los intentos",Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    t.show();
                }
                if(intentos<=3){
                    TextView txtFallastes, txtloLograstes, txtlasRespuestas;
                    txtFallastes = (TextView)child.findViewById(R.id.fallastes);
                    txtloLograstes = (TextView)child.findViewById(R.id.felicidades);
                    txtlasRespuestas = (TextView)child.findViewById(R.id.lasRespuestas);
                    TextView txtTitleRespuestas = (TextView)child.findViewById(R.id.titleRespuestas);
                    if(intentos != 3){
                        txtlasRespuestas.setVisibility(View.GONE);
                        txtTitleRespuestas.setVisibility(View.GONE);
                    }else{
                        txtlasRespuestas.setVisibility(View.VISIBLE);
                        txtTitleRespuestas.setVisibility(View.VISIBLE);
                        txtlasRespuestas.setText(
                                "cruel- malo\n" +
                                        "odioso-iracundo\n" +
                                        "angustia-sufrimiento\n" +
                                        "perro-lazarillo\n" +
                                        "caer- tropezar\n" +
                                        "cascabeleando- alborotando\n" +
                                        "amiguito-compañero");
                    }
                    if(aciertos == 7){
                        txtFallastes.setVisibility(View.GONE);
                        txtloLograstes.setVisibility(View.VISIBLE);
                    }else{
                        txtloLograstes.setVisibility(View.GONE);
                        txtFallastes.setVisibility(View.VISIBLE);
                    }
                    TextView txtIntentos = (TextView)child.findViewById(R.id.cantidadIntentos);
                    txtIntentos.setText("Intento " + String.valueOf(intentos) + "/" + String.valueOf(3));
                    TextView txtAciertos = (TextView)child.findViewById(R.id.respuestas_correctas);
                    txtAciertos.setText("Acertastes " + String.valueOf(aciertos) + "/" + String.valueOf(7));
                    Button volver =  (Button)child.findViewById(R.id.card_btn_volver);
                    volver.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            calificar.setEnabled(true);
                            Animation salida = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_out);
                            child.startAnimation(salida);
                            child.setVisibility(View.GONE);
                            cruel.setLayoutParams(paramsCruel);
                            odioso.setLayoutParams(paramsOdioso);
                            angustia.setLayoutParams(paramsAngustia);
                            perro.setLayoutParams(paramsPerro);
                            caer.setLayoutParams(paramsCaer);
                            cascabeleando.setLayoutParams(paramsCascabeleando);
                            amiguito.setLayoutParams(paramsAmiguito);
                            malo.setVisibility(View.VISIBLE);
                            iracundo.setVisibility(View.VISIBLE);
                            sufrimiento.setVisibility(View.VISIBLE);
                            lazarillo.setVisibility(View.VISIBLE);
                            tropezar.setVisibility(View.VISIBLE);
                            alborotando.setVisibility(View.VISIBLE);
                            compañero.setVisibility(View.VISIBLE);
                            malo.setBackgroundDrawable(bgMalo);
                            iracundo.setBackgroundDrawable(bgIracundo);
                            sufrimiento.setBackgroundDrawable(bgSufrimiento);
                            lazarillo.setBackgroundDrawable(bgLazarillo);
                            tropezar.setBackgroundDrawable(bgTropezar);
                            alborotando.setBackgroundDrawable(bgAlborotando);
                            compañero.setBackgroundDrawable(bgCompañero);
                            ocupado1 = false;
                            ocupado2 = false;
                            ocupado3 = false;
                            ocupado4 = false;
                            ocupado5 = false;
                            ocupado6 = false;
                            ocupado7 = false;
                            aciertos = 0;

                        }
                    });
                    child.setVisibility(View.VISIBLE);
                    Animation entrada;
                    entrada = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_in);
                    child.startAnimation(entrada);
                }
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