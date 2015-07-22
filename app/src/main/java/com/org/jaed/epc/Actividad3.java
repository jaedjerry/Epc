package com.org.jaed.epc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    private TextView txt1, txt2, txt3, txtCiego, txtPrincipal, txtSecundario;
    private Context context;
    private RelativeLayout marco;
    private Toolbar toolbar;
    private int xDelta;
    private int yDelta;
    private Button calificar;
    private int aciertos, intentos;
    private View child;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_3);
        txt1 = (TextView)findViewById(R.id.personajeAntagonico);
        txt2 = (TextView)findViewById(R.id.pacoMalo);
        txt3 = (TextView)findViewById(R.id.perro);
        txtCiego = (TextView)findViewById(R.id.ciego);
        txtPrincipal = (TextView)findViewById(R.id.principal);
        txtSecundario = (TextView)findViewById(R.id.secundario);
        marco = (RelativeLayout)findViewById(R.id.marco);
        calificar = (Button)findViewById(R.id.btnCalificar3);
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
        final Drawable original = txt2.getBackground();
        final Drawable original1 = txt1.getBackground();
        final Drawable original2 = txt3.getBackground();
        final Drawable originalCiego = txtCiego.getBackground();
        final Drawable originalPrincipal = txtPrincipal.getBackground();
        final Drawable originalSecundario = txtSecundario.getBackground();
        final RelativeLayout.LayoutParams paramsOriginalestxt1 = (RelativeLayout.LayoutParams)txt1.getLayoutParams();
        final RelativeLayout.LayoutParams paramsOriginalesPrincipal = (RelativeLayout.LayoutParams)txtPrincipal.getLayoutParams();
        final RelativeLayout.LayoutParams paramsOriginalesSecundario = (RelativeLayout.LayoutParams)txtSecundario.getLayoutParams();
        context = this;
        txt1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        txt1.setBackgroundResource(R.drawable.background_al_tocar_actividad3);
                        RelativeLayout.LayoutParams Params =
                                (RelativeLayout.LayoutParams) v.getLayoutParams();
                        xDelta = x - Params.leftMargin;
                        yDelta = y - Params.topMargin;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        txt1.setLayoutParams(paramsOriginalestxt1);
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = -50;
                        layoutParams.bottomMargin = -50;
                        v.setLayoutParams(layoutParams);
                        /*Log.e("posiciones", "posicion en y del target: "+String.valueOf(txt2.getY())+
                        " alto del target con layout params: "+String.valueOf(txt2.getLayoutParams().height)+
                                "Alto con getHeight: "+String.valueOf(txt2.getHeight())+" suma de y + alto: "+String.valueOf(txt2.getY() +txt2.getLayoutParams().height)+
                        " posicion del arrastre: "+ String.valueOf(y));*/
                        if(x>txt2.getX() && y > txt2.getY() + 30 && y < txt2.getY() + 30 + txt2.getLayoutParams().height){
                            txt2.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt2.setBackgroundDrawable(original);
                        }
                        if(x>txt3.getX() &&y > txt3.getY() + 30 && y < txt3.getY()+ 30 + txt3.getLayoutParams().height){
                            txt3.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt3.setBackgroundDrawable(original2);
                        }
                        if(x>txtCiego.getX() &&y>txtCiego.getY()+ 30 && y < txtCiego.getY()+ 30 + txtCiego.getLayoutParams().height){
                            txtCiego.setBackgroundResource(R.drawable.borde);
                        }else{
                            txtCiego.setBackgroundDrawable(originalCiego);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        txt1.setBackgroundDrawable(original1);
                        if(x>txt2.getX() && y >  txt2.getY() + 30 && y < txt2.getY()+ 30 + txt2.getLayoutParams().height){
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt2.getLayoutParams();
                            txt1.setLayoutParams(txtparams);
                            aciertos++;
                        }
                        if(x>txt3.getX() && y > txt3.getY() + 30 && y < txt3.getY()+ 30 + txt3.getLayoutParams().height) {
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt3.getLayoutParams();
                            txt1.setLayoutParams(txtparams);
                        }
                        if(x>txtCiego.getX() && y>txtCiego.getY() + 30 && y < txtCiego.getY()+ 30 + txtCiego.getLayoutParams().height){
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txtCiego.getLayoutParams();
                            txt1.setLayoutParams(txtparams);
                        }
                        break;

                }
                marco.invalidate();
                return true;
            }
        });
        txtPrincipal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        txtPrincipal.setBackgroundResource(R.drawable.background_al_tocar_actividad3);
                        RelativeLayout.LayoutParams Params =
                                (RelativeLayout.LayoutParams) v.getLayoutParams();
                        xDelta = x - Params.leftMargin;
                        yDelta = y - Params.topMargin;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        txtPrincipal.setLayoutParams(paramsOriginalesPrincipal);
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = -50;
                        layoutParams.bottomMargin = -50;
                        v.setLayoutParams(layoutParams);
                        if(x>txt2.getX() && y > txt2.getY() + 30 && y < txt2.getY()+ 30 + txt2.getLayoutParams().height){
                            txt2.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt2.setBackgroundDrawable(original);
                        }
                        if(x> txt3.getX() && y > txt3.getY() + 30 && y < txt3.getY()+ 30 + txt3.getLayoutParams().height){
                            txt3.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt3.setBackgroundDrawable(original2);
                        }
                        if(x>txtCiego.getX() && y>txtCiego.getY()+ 30  && y < txtCiego.getY()+ 30 + txtCiego.getLayoutParams().height){
                            txtCiego.setBackgroundResource(R.drawable.borde);
                        }else{
                            txtCiego.setBackgroundDrawable(originalCiego);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        txtPrincipal.setBackgroundDrawable(originalPrincipal);
                        if(x>txt2.getX() && y >  txt2.getY() + 30 && y < txt2.getY()+ 30 + txt2.getLayoutParams().height){
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt2.getLayoutParams();
                            txtPrincipal.setLayoutParams(txtparams);
                        }
                        if(x>txt3.getX() && y > txt3.getY()+ 30 && y < txt3.getY()+ 30 + txt3.getLayoutParams().height) {
                            Log.e("up", "entre en up de la condicion 2");
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt3.getLayoutParams();
                            txtPrincipal.setLayoutParams(txtparams);
                            aciertos++;
                        }
                        if(x>txtCiego.getX() && y>txtCiego.getY()+ 30 && y < txtCiego.getY()+ 30 + txtCiego.getLayoutParams().height){
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txtCiego.getLayoutParams();
                            txtPrincipal.setLayoutParams(txtparams);
                        }
                        break;

                }
                marco.invalidate();
                return true;
            }
        });
        txtSecundario.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        txtSecundario.setBackgroundResource(R.drawable.background_al_tocar_actividad3);
                        RelativeLayout.LayoutParams Params =
                                (RelativeLayout.LayoutParams) v.getLayoutParams();
                        xDelta = x - Params.leftMargin;
                        yDelta = y - Params.topMargin;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        txtSecundario.setLayoutParams(paramsOriginalesSecundario);
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = -50;
                        layoutParams.bottomMargin = -50;
                        v.setLayoutParams(layoutParams);
                        if(x > txt2.getX() && y >  txt2.getY()+ 30  && y < txt2.getY()+ 30 + txt2.getLayoutParams().height){
                            txt2.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt2.setBackgroundDrawable(original);
                        }
                        if(x > txt3.getX() && y > txt3.getY()+ 30 && y < txt3.getY()+ 30 + txt3.getLayoutParams().height){
                            txt3.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt3.setBackgroundDrawable(original2);
                        }
                        if(x> txtCiego.getX() && y>txtCiego.getY()+ 30 && y < txtCiego.getY()+ 30 + txtCiego.getLayoutParams().height){
                            txtCiego.setBackgroundResource(R.drawable.borde);
                        }else{
                            txtCiego.setBackgroundDrawable(originalCiego);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        txtSecundario.setBackgroundDrawable(originalSecundario);
                        if(x > txt2.getX() && y >  txt2.getY()+ 30   && y < txt2.getY()+ 30 + txt2.getLayoutParams().height){
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt2.getLayoutParams();
                            txtSecundario.setLayoutParams(txtparams);
                        }
                        if(x > txt3.getX() && y > txt3.getY()+ 30 && y < txt3.getY()+ 30 + txt3.getLayoutParams().height) {
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt3.getLayoutParams();
                            txtSecundario.setLayoutParams(txtparams);
                        }
                        if(x> txtCiego.getX() && y>txtCiego.getY() + 30 && y < txtCiego.getY()+ 30 + txtCiego.getLayoutParams().height){
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txtCiego.getLayoutParams();
                            txtSecundario.setLayoutParams(txtparams);
                            aciertos++;
                        }
                        break;

                }
                marco.invalidate();
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
                            if(aciertos != 3){
                                marco.requestLayout();
                                aciertos = 0;
                            }

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
