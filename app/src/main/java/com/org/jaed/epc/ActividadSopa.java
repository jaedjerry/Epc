package com.org.jaed.epc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jerry on 30/07/2015.
 */
public class ActividadSopa extends AppCompatActivity{
    private Toolbar toolbar;
    private TableLayout sopa;
    private TableRow row;
    private static int posicion =  -1;
    private TextView amable1, amable2, amable3, amable4, amable5, amable6;
    private TextView gabriel1, gabriel2, gabriel3, gabriel4, gabriel5, gabriel6, gabriel7;
    private TextView leon2;
    private TextView paco1, paco2, paco3;
    private TextView pajarito1, pajarito2, pajarito3, pajarito4, pajarito5, pajarito6, pajarito7, pajarito8;
    private TextView perro1, perro2, perro3, perro4, perro5;
    private TextView picaro1, picaro2, picaro3, picaro4, picaro5, picaro6;
    private TextView pillin2,pillin3,pillin4,pillin5,pillin6,pillin1;
    private TextView dios1, dios2,dios3, dios4;
    private TextView objetivo;
    private static ArrayList<Integer> posiciones = new ArrayList<>();
    private Drawable bgOriginal;
    private static TextView txt1 , txt2;
    private static  int contador = 0;
    private static int aciertos;
    private int intentos;
    private View child;
    private CardView card;
    private Button btnSopa;
    private RelativeLayout contenedor;
    private CheckBox chAmable, chGabriel, chDios, chPaco, chPajarito, chPerro, chPicaro, chPillin;
    private RelativeLayout cardContenedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sopa_letras);
        contenedor = (RelativeLayout)findViewById(R.id.contenedorSopa);
        amable1 = (TextView)findViewById(R.id.amable1);
        amable2 = (TextView)findViewById(R.id.amable2);
        amable3 = (TextView)findViewById(R.id.amable3);
        amable4 = (TextView)findViewById(R.id.amable4);
        amable5 = (TextView)findViewById(R.id.amable5);
        amable6 = (TextView)findViewById(R.id.amable6);
        gabriel1 = (TextView)findViewById(R.id.gabriel1);
        gabriel2 = (TextView)findViewById(R.id.gabriel2);
        gabriel3 = (TextView)findViewById(R.id.gabriel3);
        gabriel4 = (TextView)findViewById(R.id.gabriel4);
        gabriel5 = (TextView)findViewById(R.id.gabriel5);
        gabriel6 = (TextView)findViewById(R.id.gabriel6);
        gabriel7 = (TextView)findViewById(R.id.gabriel7);
        leon2 = (TextView)findViewById(R.id.leon2);
        paco1 = (TextView)findViewById(R.id.paco1);
        paco2 = (TextView)findViewById(R.id.paco2);
        paco3 = (TextView)findViewById(R.id.paco3);
        pajarito1 = (TextView)findViewById(R.id.pajarito1);
        pajarito2 = (TextView)findViewById(R.id.pajarito2);
        pajarito3 = (TextView)findViewById(R.id.pajarito3);
        pajarito4 = (TextView)findViewById(R.id.pajarito4);
        pajarito5 = (TextView)findViewById(R.id.pajarito5);
        pajarito6 = (TextView)findViewById(R.id.pajarito6);
        pajarito7 = (TextView)findViewById(R.id.pajarito7);
        pajarito8 = (TextView)findViewById(R.id.pajarito8);
        perro1 = (TextView) findViewById(R.id.perro1);
        perro2 = (TextView) findViewById(R.id.perro2);
        perro3 = (TextView) findViewById(R.id.perro3);
        perro4 = (TextView) findViewById(R.id.perro4);
        perro5 = (TextView) findViewById(R.id.perro5);
        picaro1 = (TextView) findViewById(R.id.picaro1);
        picaro2 = (TextView) findViewById(R.id.picaro2);
        picaro3 = (TextView) findViewById(R.id.picaro3);
        picaro4 = (TextView) findViewById(R.id.picaro4);
        picaro5 = (TextView) findViewById(R.id.picaro5);
        picaro6 = (TextView) findViewById(R.id.picaro6);
        pillin1 = (TextView)findViewById(R.id.pillin1);
        pillin2 = (TextView)findViewById(R.id.pillin2);
        pillin3 = (TextView)findViewById(R.id.pillin3);
        pillin4 = (TextView)findViewById(R.id.pillin4);
        pillin5 = (TextView)findViewById(R.id.pillin5);
        pillin6 = (TextView)findViewById(R.id.pillin6);
        dios1 = (TextView)findViewById(R.id.dios1);
        dios2 = (TextView)findViewById(R.id.dios2);
        dios3 = (TextView)findViewById(R.id.dios3);
        dios4 = (TextView)findViewById(R.id.dios4);
        btnSopa = (Button)findViewById(R.id.btnCalificarSopa);
        toolbar = (Toolbar)findViewById(R.id.appbarSopa);
        sopa = (TableLayout)findViewById(R.id.sopa);
        chAmable = (CheckBox)findViewById(R.id.chAmable);
        chGabriel = (CheckBox)findViewById(R.id.chGabriel);
        chDios = (CheckBox)findViewById(R.id.chDios);
        chPaco = (CheckBox)findViewById(R.id.chPaco);
        chPajarito = (CheckBox)findViewById(R.id.chPajarito);
        chPerro = (CheckBox)findViewById(R.id.chPerro);
        chPicaro = (CheckBox)findViewById(R.id.chPicaro);
        chPillin = (CheckBox)findViewById(R.id.chPillin);
        objetivo = (TextView)findViewById(R.id.txtObjetivoSopa);
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        objetivo.setTypeface(font);
        chGabriel.setTypeface(font);
        chDios.setTypeface(font);
        chPaco.setTypeface(font);
        chPajarito.setTypeface(font);
        chPerro.setTypeface(font);
        chPicaro.setTypeface(font);
        chPillin.setTypeface(font);
        bgOriginal = amable1.getBackground();
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle("Encuentra");
        }
        final WebView web = new WebView(getApplicationContext());
        btnSopa.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_calificar_presionado));
        child = getLayoutInflater().inflate(R.layout.card_resultados, contenedor, false);
        card = (CardView) child;
        cardContenedor = (RelativeLayout)card.findViewById(R.id.cardContenedor);
        for (int i = 0; i<=cardContenedor.getChildCount(); i++){
            View v = cardContenedor.getChildAt(i);
            if(v instanceof TextView){
                ((TextView) v).setTypeface(font);
            }
            if(v instanceof Button){
                ((Button) v).setTypeface(font);
            }
        }
        contenedor.addView(card);
        card.setVisibility(View.GONE);
        for(int i = 0; i<sopa.getChildCount(); i++){
             row = (TableRow)sopa.getChildAt(i);
            for(int j = 0; j<row.getChildCount(); j++){
                posicion++;
                TextView t = (TextView)row.getChildAt(j);
                if(t!=null) {
                    t.setTypeface(font);
                    t.setOnClickListener(new ListenerClick(this, posicion));
                }
            }
        }
        btnSopa.setTypeface(font);
        btnSopa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intentos<=3){
                    intentos++;
                }
                if(intentos>3){
                    Toast t = Toast.makeText(getApplicationContext(),"Se te agotaron los intentos",Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    t.show();
                }
                if(intentos<=3){
                    TextView txtFallastes, txtloLograstes, txtlasRespuestas;
                    txtFallastes = (TextView)card.findViewById(R.id.fallastes);
                    txtloLograstes = (TextView)card.findViewById(R.id.felicidades);
                    txtlasRespuestas = (TextView)card.findViewById(R.id.lasRespuestas);
                    TextView txtTitleRespuestas = (TextView)card.findViewById(R.id.titleRespuestas);
                    if(intentos != 3){
                        txtlasRespuestas.setVisibility(View.GONE);
                        txtTitleRespuestas.setVisibility(View.GONE);
                    }else{
                        txtTitleRespuestas.setVisibility(View.VISIBLE);
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                        params.addRule(RelativeLayout.BELOW, R.id.titleRespuestas);
                        params.addRule(RelativeLayout.ABOVE, R.id.card_btn_volver);
                        web.setLayoutParams(params);
                        web.loadUrl("file:///android_asset/tabla.html");
                        cardContenedor.addView(web);
                    }
                    if(aciertos == 8){
                        txtFallastes.setVisibility(View.GONE);
                        txtloLograstes.setVisibility(View.VISIBLE);
                    }else{
                        txtloLograstes.setVisibility(View.GONE);
                        txtFallastes.setVisibility(View.VISIBLE);
                    }
                    TextView txtIntentos = (TextView)card.findViewById(R.id.cantidadIntentos);
                    txtIntentos.setText("Intento " + String.valueOf(intentos) + "/" + String.valueOf(3));
                    TextView txtAciertos = (TextView)card.findViewById(R.id.respuestas_correctas);
                    txtAciertos.setText( "Acertastes " + String.valueOf(aciertos) + "/" + String.valueOf(8));
                    aciertos = 0;
                    Button volver =  (Button)child.findViewById(R.id.card_btn_volver);
                    volver.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Animation salida = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_out);
                            for(int i = 0; i<sopa.getChildCount(); i++){
                                TableRow t = (TableRow)sopa.getChildAt(i);
                                for(int j=0;j<t.getChildCount(); j++){
                                    t.getChildAt(j).setBackgroundDrawable(bgOriginal);
                                    if(!t.getChildAt(j).isEnabled()){
                                        t.getChildAt(j).setEnabled(true);
                                    }
                                }
                            }
                            for(int i = 0; i<=contenedor.getChildCount(); i++){
                                if(contenedor.getChildAt(i) instanceof CheckBox){
                                    CheckBox ch = (CheckBox)contenedor.getChildAt(i);
                                    if(ch.isChecked()){
                                        ch.setChecked(false);
                                    }
                                }
                            }
                            card.startAnimation(salida);
                            card.setVisibility(View.GONE);
                        }
                    });
                    card.setVisibility(View.VISIBLE);
                    Animation entrada;
                    entrada = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_in);
                    card.startAnimation(entrada);
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        posicion = -1;
        posiciones.clear();
        contador = 0;
        txt1 = null;
        txt2 = null;
        aciertos = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        posicion = -1;
        posiciones.clear();
        contador = 0;
        txt1 = null;
        txt2 = null;
        aciertos = 0;
        for(int i = 0; i<sopa.getChildCount(); i++){
            TableRow t = (TableRow)sopa.getChildAt(i);
            for(int j=0;j<t.getChildCount(); j++){
                t.getChildAt(j).setBackgroundDrawable(bgOriginal);
                if(!t.getChildAt(j).isEnabled()){
                    t.getChildAt(j).setEnabled(true);
                }
            }
        }
        for(int i = 0; i<=contenedor.getChildCount(); i++){
            if(contenedor.getChildAt(i) instanceof CheckBox){
                CheckBox ch = (CheckBox)contenedor.getChildAt(i);
                if(ch.isChecked()){
                    ch.setChecked(false);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.alEjercicio){
            startActivity(new Intent().setClass(getApplicationContext(), MenuEjercicios.class));
        }
        if(id == R.id.alPrincipal){
            startActivity(new Intent().setClass(getApplicationContext(),MainMenu.class));
        }
        if(id == R.id.salir){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public class ListenerClick implements View.OnClickListener{
        private int position;
        private Context mContext;
        public ListenerClick(Context c, int pos){
            mContext = c;
            position = pos;
        }
        @Override
        public void onClick(View v) {
            contador++;
            posiciones.add(position);
            if(contador<2){
                txt1 = (TextView)v;
            }
            v.setBackgroundColor(getResources().getColor(R.color.rojo));
            if(contador == 2){
                txt2 = (TextView)v;
                 txt1.setBackgroundDrawable(bgOriginal);
                 txt2.setBackgroundDrawable(bgOriginal);
                if((posiciones.get(0) == 0 && posiciones.get(1) == 35)||(posiciones.get(0) == 35 && posiciones.get(1) == 0)){
                    amable1.setBackgroundColor(getResources().getColor(R.color.rojo));
                    amable2.setBackgroundColor(getResources().getColor(R.color.rojo));
                    amable3.setBackgroundColor(getResources().getColor(R.color.rojo));
                    amable4.setBackgroundColor(getResources().getColor(R.color.rojo));
                    amable5.setBackgroundColor(getResources().getColor(R.color.rojo));
                    amable6.setBackgroundColor(getResources().getColor(R.color.rojo));
                    chAmable.setChecked(true);
                    amable1.setEnabled(false);
                    amable2.setEnabled(false);
                    amable3.setEnabled(false);
                    amable4.setEnabled(false);
                    amable5.setEnabled(false);
                    amable6.setEnabled(false);
                    aciertos++;
                }
                if((posiciones.get(0) == 8 && posiciones.get(1) ==50)||(posiciones.get(0) == 50 && posiciones.get(1) == 8)){
                    gabriel1.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    gabriel2.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    gabriel3.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    gabriel4.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    gabriel5.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    gabriel6.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    gabriel7.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    chGabriel.setChecked(true);
                    gabriel1.setEnabled(false);
                    gabriel2.setEnabled(false);
                    gabriel3.setEnabled(false);
                    gabriel4.setEnabled(false);
                    gabriel5.setEnabled(false);
                    gabriel6.setEnabled(false);
                    gabriel7.setEnabled(false);
                    aciertos++;

                }
                if((posiciones.get(0) == 31 && posiciones.get(1) == 52)||(posiciones.get(0) == 52 && posiciones.get(1) == 31)){
                    paco1.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    paco2.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    paco3.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    leon2.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    chPaco.setChecked(true);
                    paco1.setEnabled(false);
                    paco2.setEnabled(false);
                    paco3.setEnabled(false);
                    leon2.setEnabled(false);
                    aciertos++;
                }
                if((posiciones.get(0) == 6 && posiciones.get(1) == 55)|| (posiciones.get(0) == 55 && posiciones.get(1) == 6)){
                    pajarito1.setBackgroundColor(getResources().getColor(R.color.rojo));
                    pajarito2.setBackgroundColor(getResources().getColor(R.color.rojo));
                    pajarito3.setBackgroundColor(getResources().getColor(R.color.rojo));
                    pajarito4.setBackgroundColor(getResources().getColor(R.color.rojo));
                    pajarito5.setBackgroundColor(getResources().getColor(R.color.rojo));
                    pajarito6.setBackgroundColor(getResources().getColor(R.color.rojo));
                    pajarito7.setBackgroundColor(getResources().getColor(R.color.rojo));
                    pajarito8.setBackgroundColor(getResources().getColor(R.color.rojo));
                    chPajarito.setChecked(true);
                    pajarito1.setEnabled(false);
                    pajarito2.setEnabled(false);
                    pajarito3.setEnabled(false);
                    pajarito4.setEnabled(false);
                    pajarito5.setEnabled(false);
                    pajarito6.setEnabled(false);
                    pajarito7.setEnabled(false);
                    pajarito8.setEnabled(false);
                    aciertos++;
                }
                if((posiciones.get(0) == 33 && posiciones.get(1) == 5)||(posiciones.get(0) == 5 && posiciones.get(1) == 33)){
                    perro1.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    perro2.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    perro3.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    perro4.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    perro5.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    perro1.setEnabled(false);
                    perro2.setEnabled(false);
                    perro3.setEnabled(false);
                    perro4.setEnabled(false);
                    perro5.setEnabled(false);
                    chPerro.setChecked(true);
                    aciertos++;
                }
                if((posiciones.get(0) == 46 && posiciones.get(1) == 11)||(posiciones.get(0) == 11 && posiciones.get(1) == 46)){
                    picaro1.setBackgroundColor(getResources().getColor(R.color.rojo));
                    picaro2.setBackgroundColor(getResources().getColor(R.color.rojo));
                    picaro3.setBackgroundColor(getResources().getColor(R.color.rojo));
                    picaro4.setBackgroundColor(getResources().getColor(R.color.rojo));
                    picaro5.setBackgroundColor(getResources().getColor(R.color.rojo));
                    picaro6.setBackgroundColor(getResources().getColor(R.color.rojo));
                    chPicaro.setChecked(true);
                    picaro1.setEnabled(false);
                    picaro2.setEnabled(false);
                    picaro3.setEnabled(false);
                    picaro4.setEnabled(false);
                    picaro5.setEnabled(false);
                    picaro6.setEnabled(false);
                    aciertos++;
                }
                if((posiciones.get(0) == 9 && posiciones.get(1) == 44)||(posiciones.get(0) == 44 && posiciones.get(1) == 9)){
                    pillin1.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    pillin2.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    pillin3.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    pillin4.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    pillin5.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    pillin6.setBackgroundColor(getResources().getColor(R.color.btn_normal));
                    chPillin.setChecked(true);
                    pillin1.setEnabled(false);
                    pillin2.setEnabled(false);
                    pillin2.setEnabled(false);
                    pillin3.setEnabled(false);
                    pillin4.setEnabled(false);
                    pillin5.setEnabled(false);
                    pillin6.setEnabled(false);
                    aciertos++;
                }
                if((posiciones.get(0) == 3 && posiciones.get(1)==24)||(posiciones.get(0) == 24 && posiciones.get(1)==3)){
                    dios1.setBackgroundColor(getResources().getColor(R.color.rojo));
                    dios2.setBackgroundColor(getResources().getColor(R.color.rojo));
                    dios3.setBackgroundColor(getResources().getColor(R.color.rojo));
                    dios4.setBackgroundColor(getResources().getColor(R.color.rojo));
                    chDios.setChecked(true);
                    dios1.setEnabled(false);
                    dios2.setEnabled(false);
                    dios3.setEnabled(false);
                    dios4.setEnabled(false);
                    aciertos++;
                }
                contador = 0;
                posiciones.clear();
            }
        }
    }
}
