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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jerry on 26/07/2015.
 */
public class actividad5 extends AppCompatActivity {
    private Toolbar toolbar;
    private RelativeLayout contenedor;
    private Button calificar;
    private Spinner sIdea1, sIdea2, sIdea3, sIdea4, sIdea5, sIdea6;
    private int intentos, aciertos;
    private View child;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_5);
        toolbar = (Toolbar)findViewById(R.id.appbar5);
        calificar = (Button)findViewById(R.id.btnCalificar5);
        sIdea1 = (Spinner)findViewById(R.id.spinner_idea1);
        sIdea2 = (Spinner)findViewById(R.id.spinner_idea2);
        sIdea3 = (Spinner)findViewById(R.id.spinner_idea3);
        sIdea4 = (Spinner)findViewById(R.id.spinner_idea4);
        sIdea5 = (Spinner)findViewById(R.id.spinner_idea5);
        sIdea6 = (Spinner)findViewById(R.id.spinner_idea6);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            toolbar.setNavigationIcon(R.mipmap.ic_atras);
            getSupportActionBar().setTitle("Ordena");
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localintent = new Intent().setClass(getApplicationContext(), actividad4.class);
                startActivity(localintent);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                finish();
            }
        });
        contenedor = (RelativeLayout)findViewById(R.id.contenedorActividad5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.valores_ideas, R.layout.spinner_style);
        for(int i = 0; i<=contenedor.getChildCount(); i++){
            View v = contenedor.getChildAt(i);
            if(v instanceof Spinner){
                ((Spinner) v).setAdapter(adapter);
            }
        }
        if(Build.VERSION.SDK_INT > 20) {
            calificar.setBackgroundTintList(getResources().getColorStateList(R.color.btn_calificar_presionado));
        }else{
            //color sin animación
            calificar.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_calificar_presionado));
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
                if(intentos>3){
                    Toast t = Toast.makeText(getApplicationContext(),"Se te agotaron los intentos",Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    t.show();
                }else{
                    if(sIdea1.getSelectedItem().toString().equals("2")){
                        aciertos++;
                    }
                    if(sIdea2.getSelectedItem().toString().equals("1")){
                        aciertos++;
                    }
                    if(sIdea3.getSelectedItem().toString().equals("4")){
                        aciertos++;
                    }
                    if(sIdea4.getSelectedItem().toString().equals("3")){
                        aciertos++;
                    }
                    if(sIdea5.getSelectedItem().toString().equals("6")){
                        aciertos++;
                    }
                    if(sIdea6.getSelectedItem().toString().equals("5")){
                        aciertos++;
                    }
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
                                        "Pregunta 1: el orden es 2.\n"+
                                        "Pregunta 2: el orden es 1.\n"+
                                        "Pregunta 3: el orden es 4.\n"+
                                        "Pregunta 4: el orden es 3.\n"+
                                        "Pregunta 5: el orden es 6.\n"+
                                        "Pregunta 6: el orden es 5.");
                    }
                    if(aciertos == 6){
                        txtFallastes.setVisibility(View.GONE);
                        txtloLograstes.setVisibility(View.VISIBLE);
                    }else{
                        txtloLograstes.setVisibility(View.GONE);
                        txtFallastes.setVisibility(View.VISIBLE);
                    }
                    TextView txtIntentos = (TextView)child.findViewById(R.id.cantidadIntentos);
                    txtIntentos.setText("Intento " + String.valueOf(intentos) + "/" + String.valueOf(3));
                    TextView txtAciertos = (TextView)child.findViewById(R.id.respuestas_correctas);
                    txtAciertos.setText( "Acertastes " + String.valueOf(aciertos) + "/" + String.valueOf(6));
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
