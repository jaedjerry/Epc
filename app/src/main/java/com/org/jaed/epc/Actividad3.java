package com.org.jaed.epc;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
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
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by Jerry on 13/07/2015.
 */
public class Actividad3 extends AppCompatActivity {
    private TextView txtPersonajeAntagonico, txtPacoMalo, txtPerro, txtCiego, txtPrincipal, txtSecundario;
    private  Context context;
    private RelativeLayout marco;
    private Toolbar toolbar;
    private Button calificar;
    private int aciertos, intentos;
    private View child;
    private boolean ocupado1, ocupado2, ocupado3;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_3);
        txtPacoMalo = (TextView)findViewById(R.id.pacoMalo);
        txtPerro = (TextView)findViewById(R.id.perro);
        txtCiego = (TextView)findViewById(R.id.ciego);
        txtPersonajeAntagonico = (TextView)findViewById(R.id.personajeAntagonico);
        txtPrincipal = (TextView)findViewById(R.id.principal);
        txtSecundario = (TextView)findViewById(R.id.secundario);
        marco = (RelativeLayout)findViewById(R.id.marco);
        calificar = (Button)findViewById(R.id.btnCalificar3);
        context = this;
        toolbar = (Toolbar)findViewById(R.id.appbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_atras);
        getSupportActionBar().setTitle("Arrastra");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            calificar.setBackgroundTintList(getResources().getColorStateList(R.color.btn_calificar_presionado));
        }else{
            calificar.setBackgroundColor(getResources().getColor(R.color.btn_calificar_presionado));
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localintent = new Intent().setClass(context, MainActivity.class);
                startActivity(localintent);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                finish();
            }
        });
        //Datos de arriba
        final RelativeLayout.LayoutParams paramsOriginalesPaco = (RelativeLayout.LayoutParams)txtPacoMalo.getLayoutParams();
        final Drawable bgPaco = txtPacoMalo.getBackground();
        final RelativeLayout.LayoutParams paramsOriginalesPerro = (RelativeLayout.LayoutParams)txtPerro.getLayoutParams();
        final Drawable bgPerro = txtPerro.getBackground();
        final RelativeLayout.LayoutParams paramsOriginalesCiego = (RelativeLayout.LayoutParams)txtCiego.getLayoutParams();
        final Drawable bgCiego = txtCiego.getBackground();

        //Datos de abajo
        final RelativeLayout.LayoutParams paramsOriginaleAntagonico = (RelativeLayout.LayoutParams)txtPersonajeAntagonico.getLayoutParams();
        final RelativeLayout.LayoutParams paramsOriginalesPrincipal = (RelativeLayout.LayoutParams)txtPrincipal.getLayoutParams();
        final RelativeLayout.LayoutParams paramsOriginalesSecundario = (RelativeLayout.LayoutParams)txtSecundario.getLayoutParams();

        txtPersonajeAntagonico.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsOriginalesPaco){
                            ocupado1 = false;
                            v.setLayoutParams(paramsOriginaleAntagonico);
                        }
                        if (v.getLayoutParams() == paramsOriginalesPerro) {
                            ocupado2 = false;
                            v.setLayoutParams(paramsOriginaleAntagonico);
                        }
                        if (v.getLayoutParams() == paramsOriginalesCiego) {
                            ocupado3 = false;
                            v.setLayoutParams(paramsOriginaleAntagonico);
                        }
                        ClipData clipData = ClipData.newPlainText("antagonico", "antagonico");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if (txtPacoMalo.getVisibility() == View.GONE && !ocupado1) {
                            txtPacoMalo.setVisibility(View.VISIBLE);
                            txtPacoMalo.setBackgroundDrawable(bgPaco);
                        }
                        if (txtPerro.getVisibility() == View.GONE && !ocupado2) {
                            txtPerro.setVisibility(View.VISIBLE);
                            txtPerro.setBackgroundDrawable(bgPerro);
                        }
                        if (txtCiego.getVisibility() == View.GONE && !ocupado3) {
                            txtCiego.setVisibility(View.VISIBLE);
                            txtCiego.setBackgroundDrawable(bgCiego);
                        }
                        break;
                }
                return true;
            }
        });
        txtPrincipal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsOriginalesPaco){
                            ocupado1 = false;
                            v.setLayoutParams(paramsOriginalesPrincipal);
                        }
                        if (v.getLayoutParams() == paramsOriginalesPerro) {
                            ocupado2 = false;
                            v.setLayoutParams(paramsOriginalesPrincipal);
                        }
                        if (v.getLayoutParams() == paramsOriginalesCiego) {
                            ocupado3 = false;
                            v.setLayoutParams(paramsOriginalesPrincipal);
                        }
                        ClipData clipData = ClipData.newPlainText("principal", "principal");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if (txtPacoMalo.getVisibility() == View.GONE && !ocupado1) {
                            txtPacoMalo.setVisibility(View.VISIBLE);
                            txtPacoMalo.setBackgroundDrawable(bgPaco);
                        }
                        if (txtPerro.getVisibility() == View.GONE && !ocupado2) {
                            txtPerro.setVisibility(View.VISIBLE);
                            txtPerro.setBackgroundDrawable(bgPerro);
                        }
                        if (txtCiego.getVisibility() == View.GONE && !ocupado3) {
                            txtCiego.setVisibility(View.VISIBLE);
                            txtCiego.setBackgroundDrawable(bgCiego);
                        }
                        break;
                }
                return true;
            }
        });
        txtSecundario.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsOriginalesPaco){
                            ocupado1 = false;
                            v.setLayoutParams(paramsOriginalesSecundario);
                        }
                        if (v.getLayoutParams() == paramsOriginalesPerro) {
                            ocupado2 = false;
                            v.setLayoutParams(paramsOriginalesSecundario);
                        }
                        if (v.getLayoutParams() == paramsOriginalesCiego) {
                            ocupado3 = false;
                            v.setLayoutParams(paramsOriginalesSecundario);
                        }
                        ClipData clipData = ClipData.newPlainText("secundario", "secundario");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if (txtPacoMalo.getVisibility() == View.GONE && !ocupado1) {
                            txtPacoMalo.setVisibility(View.VISIBLE);
                            txtPacoMalo.setBackgroundDrawable(bgPaco);
                        }
                        if (txtPerro.getVisibility() == View.GONE && !ocupado2) {
                            txtPerro.setVisibility(View.VISIBLE);
                            txtPerro.setBackgroundDrawable(bgPerro);
                        }
                        if (txtCiego.getVisibility() == View.GONE && !ocupado3) {
                            txtCiego.setVisibility(View.VISIBLE);
                            txtCiego.setBackgroundDrawable(bgCiego);
                        }
                        break;
                }
                return true;
            }
        });
        txtPacoMalo.setOnDragListener(new View.OnDragListener() {
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
                        v.setBackgroundDrawable(bgPaco);
                        break;
                    case DragEvent.ACTION_DROP:
                        ocupado1 = true;
                        if (ocupado1) {
                            Log.e("ocupado", "estoy ocupado");
                        } else {
                            Log.e("libre", "estoy libre");
                        }
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("antagonico")) {
                            Log.e("acerte","arrastre antagonico con paco malo");
                            aciertos++;
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsOriginalesPaco);
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
        txtPerro.setOnDragListener(new View.OnDragListener() {
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
                        v.setBackgroundDrawable(bgPerro);
                        break;
                    case DragEvent.ACTION_DROP:
                        ocupado2 = true;
                        if(ocupado2){
                            Log.e("ocupado", "estoy ocupado");
                        }else{
                            Log.e("libre", "estoy libre");
                        }
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("principal")) {
                            Log.e("acerte", "arrastre principal con perro");
                            aciertos++;
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsOriginalesPerro);
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
        txtCiego.setOnDragListener(new View.OnDragListener() {
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
                        v.setBackgroundDrawable(bgCiego);
                        break;
                    case DragEvent.ACTION_DROP:
                        ocupado1 = true;
                        if(ocupado3){
                            Log.e("ocupado", "estoy ocupado");
                        }else{
                            Log.e("libre", "estoy libre");
                        }
                        // Dropped, reassign View to ViewGroup
                        if (event.getClipData().getItemAt(0).getText().equals("secundario")) {
                            Log.e("acerte", "arrastre secunfario con ciego");
                            aciertos++;
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsOriginalesCiego);
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
        child = getLayoutInflater().inflate(R.layout.card_resultados, marco, false);
        marco.addView(child);
        child.setVisibility(View.GONE);
        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intentos<=3){
                    intentos++;
                }
                if(intentos > 3){
                    Toast t = Toast.makeText(context,"Se te agotaron los intentos",Toast.LENGTH_SHORT);
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
                                "Personaje antagónico: Paco: Niño cruel.\n"+
                                        "Personaje principal: El perro: Lazarillo de 4 patas.\n"+
                                        "Personaje secundario: El ciego dueño del perro.");
                    }
                    if(aciertos == 3){
                        txtFallastes.setVisibility(View.GONE);
                        txtloLograstes.setVisibility(View.VISIBLE);
                    }else{
                        txtloLograstes.setVisibility(View.GONE);
                        txtFallastes.setVisibility(View.VISIBLE);
                    }
                    TextView txtIntentos = (TextView)child.findViewById(R.id.cantidadIntentos);
                    txtIntentos.setText("Intento " + String.valueOf(intentos) + "/" + String.valueOf(3));
                    TextView txtAciertos = (TextView)child.findViewById(R.id.respuestas_correctas);
                    txtAciertos.setText("Acertastes " + String.valueOf(aciertos) + "/" + String.valueOf(3));
                    Button volver =  (Button)child.findViewById(R.id.card_btn_volver);
                    volver.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            calificar.setEnabled(true);
                            Animation salida = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_out);
                            child.startAnimation(salida);
                            child.setVisibility(View.GONE);
                            txtPersonajeAntagonico.setLayoutParams(paramsOriginaleAntagonico);
                            txtPrincipal.setLayoutParams(paramsOriginalesPrincipal);
                            txtSecundario.setLayoutParams(paramsOriginalesSecundario);
                            txtPacoMalo.setVisibility(View.VISIBLE);
                            txtPerro.setVisibility(View.VISIBLE);
                            txtCiego.setVisibility(View.VISIBLE);
                            txtPacoMalo.setBackgroundDrawable(bgPaco);
                            txtPerro.setBackgroundDrawable(bgPerro);
                            txtCiego.setBackgroundDrawable(bgCiego);
                            ocupado1 = false;
                            ocupado2 = false;
                            ocupado3 = false;
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
        //si el id del item de menu que se presiono en pantalla es igual al id del item de menu declarado en menu_main.xml
        if(id == R.id.siguiente){
            Intent intent = new Intent().setClass(getApplicationContext(),actividad4.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
