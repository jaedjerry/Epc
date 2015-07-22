package com.org.jaed.epc;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    private Button calificar;
    private ViewGroup contenedor, preguntas, contenedorPreguntas;
    private ScrollView scroll;
    private RadioButton opcion1Pregunta1;
    private RadioButton opcion2Pregunta2;
    private RadioButton opcion3Pregunta3;
    private RadioButton opcion4Pregunta4;
    private RadioButton opcion4Pregunta5;
    private int intentos, cantidadCorrectas;
    private Context context;
    private Handler handler = new Handler();
    private Toolbar toolbar;
    private View child;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inicialización de la barra de tareas
        toolbar = (Toolbar)findViewById(R.id.appbar);
        //inicializar varaibles
        calificar = (Button)findViewById(R.id.buttonCalificar);
        contenedor = (ViewGroup)findViewById(R.id.contenedor);
        preguntas = (ViewGroup)findViewById(R.id.preguntas);
        scroll = (ScrollView)preguntas.findViewById(R.id.scroll);
        contenedorPreguntas = (ViewGroup)scroll.findViewById(R.id.contenedorPreguntas);
        //inicialización items pregunta 1
        opcion1Pregunta1 = (RadioButton)findViewById(R.id.opcion1Pregunta1);
        //inicialización items pregunta 2
        opcion2Pregunta2 = (RadioButton)findViewById(R.id.opcion2Pregunta2);
        //inicialización items pregunta 3
        opcion3Pregunta3 = (RadioButton)findViewById(R.id.opcion3Pregunta3);
        //inicialización items pregunta 4
        opcion4Pregunta4 = (RadioButton)findViewById(R.id.opcion4Pregunta4);
        //inicialización items pregunta 5
        opcion4Pregunta5 = (RadioButton)findViewById(R.id.opcion4Pregunta5);
        setSupportActionBar(toolbar);//establece a esta pantalla cual será su toolbar, en este caso la referencia anterior
        getSupportActionBar().setHomeButtonEnabled(true);//habilitamos el boton home, el boton home está ubicado a la izaquierda del título del toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Resuelva");
        toolbar.setNavigationIcon(R.mipmap.ic_atras);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//evento click del boton home del toolbar
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), actividad2.class);//intento para cambiar de actividad, 1er parametro el contexto actual, 2do parametro es la clase que maneja la actividad a donde queremos ir
                startActivityForResult(myIntent, 0);//inicia la actividad mediante el intento anterior
                overridePendingTransition(R.anim.right_in, R.anim.right_out);//animación del cambio de actividad
                finish();
            }
        });
        context = this;
        //si la version de android es mayor o igual que la 21 el boton calificar recibira una animación de color al presionarlo
        if(Build.VERSION.SDK_INT >= 21) {
            calificar.setBackgroundTintList(getResources().getColorStateList(R.color.btn_calificar_presionado));
        }else{
            //color sin animación
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
                    Toast t = Toast.makeText(context,"Se te agotaron los intentos",Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    t.show();
                }else{
                    //items pregunta 1
                    if(opcion1Pregunta1.isChecked()){
                        cantidadCorrectas++;
                    }
                    //items pregunta 2
                    if(opcion2Pregunta2.isChecked()){
                        cantidadCorrectas++;
                    }
                    //items pregunta 3
                    if(opcion3Pregunta3.isChecked()){
                        cantidadCorrectas++;
                    }
                    //items pregunta 4
                    if(opcion4Pregunta4.isChecked()){
                        cantidadCorrectas++;
                    }
                    //items pregunta 5
                    if(opcion4Pregunta5.isChecked()){
                        cantidadCorrectas++;
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
                                "Pregunta 1: Los pellizcaba y daba golpes.\n"+
                                "Pregunta 2: Le lanzaba pedradas.\n"+
                                "Pregunta 3: Les quitaba las plumas.\n"+
                                "Pregunta 4: Le cortó la cola.\n"+
                                "Pregunta 5: León");
                    }
                    if(cantidadCorrectas == 5){
                        txtFallastes.setVisibility(View.GONE);
                        txtloLograstes.setVisibility(View.VISIBLE);
                    }else{
                        txtloLograstes.setVisibility(View.GONE);
                        txtFallastes.setVisibility(View.VISIBLE);
                    }
                    TextView txtIntentos = (TextView)child.findViewById(R.id.cantidadIntentos);
                    txtIntentos.setText("Intento " + String.valueOf(intentos) + "/" + String.valueOf(3));
                    TextView txtAciertos = (TextView)child.findViewById(R.id.respuestas_correctas);
                    txtAciertos.setText( "Acertastes " + String.valueOf(cantidadCorrectas) + "/" + String.valueOf(5));
                    cantidadCorrectas = 0;
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
                    for(int i = 0; i<contenedor.getChildCount(); i++){
                        View hijo = contenedor.getChildAt(i);
                        hijo.setEnabled(false);
                    }
                }
                /*for (int i = 0; i<contenedorPreguntas.getChildCount(); i++){
                    View hijo = contenedorPreguntas.getChildAt(i);
                    Log.e("mis hijos", hijo.toString());
                    if(hijo instanceof RadioGroup){
                        RadioGroup rg = (RadioGroup) hijo;
                        for(int j = 0; j<rg.getChildCount(); j++){
                            rg.getChildAt(j).setEnabled(false);
                        }
                    }else{
                        Log.e("falle", "jamas entre");
                    }
                }*/
                //cambiarColorPreguntas();

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
        //si el id del item de menu que se presionó en pantalla es igual al id del item de menu declarado en menu_main.xml
        if(id == R.id.siguiente){
            Intent localintent = new Intent().setClass(context, Actividad3.class);
            startActivity(localintent);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
