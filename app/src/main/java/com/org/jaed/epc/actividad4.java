package com.org.jaed.epc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jerry on 16/07/2015.
 */
public class actividad4 extends AppCompatActivity {
    private Toolbar toolbar;
    private Button calificar;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9;
    private int aciertos, intentos;
    private View child;
    private RelativeLayout contenedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_4);
        toolbar = (Toolbar)findViewById(R.id.appbar4);
        calificar = (Button)findViewById(R.id.btnCalificar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_atras);
        getSupportActionBar().setTitle("Resuelva");
        rb1 = (RadioButton) findViewById(R.id.rdb1);
        rb2 = (RadioButton)findViewById(R.id.rdb4);
        rb3 = (RadioButton)findViewById(R.id.rdb5);
        rb4 = (RadioButton)findViewById(R.id.rdb8);
        rb5 = (RadioButton)findViewById(R.id.rdb9);
        rb6 = (RadioButton)findViewById(R.id.rdb11);
        rb7 = (RadioButton)findViewById(R.id.rdb14);
        rb8 = (RadioButton)findViewById(R.id.rdb15);
        rb9 = (RadioButton)findViewById(R.id.rdb17);
        contenedor = (RelativeLayout)findViewById(R.id.contenedorActividad4);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localintent = new Intent().setClass(getApplicationContext(), Actividad3.class);
                startActivity(localintent);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                finish();
            }
        });
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            calificar.setBackgroundTintList(getResources().getColorStateList(R.color.btn_calificar_presionado));
        }else{
            calificar.setBackgroundColor(getResources().getColor(R.color.btn_calificar_presionado));
        }
        child = getLayoutInflater().inflate(R.layout.card_resultados, contenedor, false);
        contenedor.addView(child);
        child.setVisibility(View.GONE);
        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intentos<=3){
                    intentos++;
                }
                if(intentos > 3){
                    Toast t = Toast.makeText(getApplicationContext(),"Se te agotaron los intentos",Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    t.show();
                }else{
                    if(rb1.isChecked()){
                        aciertos++;
                    }
                    if(rb2.isChecked()){
                        aciertos++;
                    }
                    if(rb3.isChecked()){
                        aciertos++;
                    }
                    if(rb4.isChecked()){
                        aciertos++;
                    }
                    if(rb5.isChecked()){
                        aciertos++;
                    }
                    if(rb6.isChecked()){
                        aciertos++;
                    }
                    if(rb7.isChecked()){
                        aciertos++;
                    }
                    if(rb8.isChecked()){
                        aciertos++;
                    }if(rb9.isChecked()){
                        aciertos++;
                    }
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
                                "Pregunta 1: Verdadero.\n"+
                                        "Pregunta 2: Falso.\n"+
                                        "Pregunta 3: Verdadero.\n"+
                                        "Pregunta 4: Falso.\n"+
                                        "Pregunta 5: Verdadero.\n"+
                                        "Pregunta 6: Verdadero.\n"+
                                        "Pregunta 7: Falso.\n"+
                                        "Pregunta 8: Verdadero\n"+
                                        "Pregunta 9: Verdadero\n");
                    }
                    if(aciertos == 9){
                        txtFallastes.setVisibility(View.GONE);
                        txtloLograstes.setVisibility(View.VISIBLE);
                    }else{
                        txtloLograstes.setVisibility(View.GONE);
                        txtFallastes.setVisibility(View.VISIBLE);
                    }
                    TextView txtIntentos = (TextView)child.findViewById(R.id.cantidadIntentos);
                    txtIntentos.setText("Intento " + String.valueOf(intentos) + "/" + String.valueOf(3));
                    TextView txtAciertos = (TextView)child.findViewById(R.id.respuestas_correctas);
                    txtAciertos.setText( "Acertastes " + String.valueOf(aciertos) + "/" + String.valueOf(9));
                    aciertos = 0;
                    Button volver =  (Button)child.findViewById(R.id.card_btn_volver);
                    volver.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            calificar.setEnabled(true);
                            Animation salida = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_out);
                            child.startAnimation(salida);
                            child.setVisibility(View.GONE);
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

        }

        return super.onOptionsItemSelected(item);
    }
}
