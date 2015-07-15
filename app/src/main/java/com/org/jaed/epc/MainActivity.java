package com.org.jaed.epc;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button calificar;
    private ViewGroup contenedor;
    private RadioButton opcion1Pregunta1;
    private RadioButton opcion2Pregunta1;
    private RadioButton opcion3Pregunta1;
    private RadioButton opcion4Pregunta1;
    private RadioButton opcion1Pregunta2;
    private RadioButton opcion2Pregunta2;
    private RadioButton opcion3Pregunta2;
    private RadioButton opcion4Pregunta2;
    private RadioButton opcion1Pregunta3;
    private RadioButton opcion2Pregunta3;
    private RadioButton opcion3Pregunta3;
    private RadioButton opcion4Pregunta3;
    private RadioButton opcion1Pregunta4;
    private RadioButton opcion2Pregunta4;
    private RadioButton opcion3Pregunta4;
    private RadioButton opcion4Pregunta4;
    private RadioButton opcion1Pregunta5;
    private RadioButton opcion2Pregunta5;
    private RadioButton opcion3Pregunta5;
    private RadioButton opcion4Pregunta5;
    private int porcentajaAcierto;
    private TextView pregunta1;
    private TextView pregunta2;
    private TextView pregunta3;
    private TextView pregunta4;
    private TextView pregunta5;
    private Context context;
    private ColorStateList colordefecto;
    private Handler handler = new Handler();
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inicialización de la barra de tareas
        toolbar = (Toolbar)findViewById(R.id.appbar);
        //inicializar varaibles
        contenedor = (RelativeLayout)findViewById(R.id.contenedor);
        calificar = (Button)findViewById(R.id.buttonCalificar);
        contenedor = (ViewGroup)findViewById(R.id.contenedor);
        //inicialización items pregunta 1
        opcion1Pregunta1 = (RadioButton)findViewById(R.id.opcion1Pregunta1);
        opcion2Pregunta1 = (RadioButton)findViewById(R.id.opcion2Pregunta1);
        opcion3Pregunta1 = (RadioButton)findViewById(R.id.opcion3Pregunta1);
        opcion4Pregunta1 = (RadioButton)findViewById(R.id.opcion4Preguna1);
        //inicialización items pregunta 2
        opcion1Pregunta2 = (RadioButton)findViewById(R.id.opcion1Pregunta2);
        opcion2Pregunta2 = (RadioButton)findViewById(R.id.opcion2Pregunta2);
        opcion3Pregunta2 = (RadioButton)findViewById(R.id.opcion3Pregunta2);
        opcion4Pregunta2 = (RadioButton)findViewById(R.id.opcion4Pregunta2);
        //inicialización items pregunta 3
        opcion1Pregunta3 = (RadioButton)findViewById(R.id.opcion1Pregunta3);
        opcion2Pregunta3 = (RadioButton)findViewById(R.id.opcion2Pregunta3);
        opcion3Pregunta3 = (RadioButton)findViewById(R.id.opcion3Pregunta3);
        opcion4Pregunta3 = (RadioButton)findViewById(R.id.opcion4Pregunta3);
        //inicialización items pregunta 4
        opcion1Pregunta4 = (RadioButton)findViewById(R.id.opcion1Pregunta4);
        opcion2Pregunta4 = (RadioButton)findViewById(R.id.opcion2Pregunta4);
        opcion3Pregunta4 = (RadioButton)findViewById(R.id.opcion3Pregunta4);
        opcion4Pregunta4 = (RadioButton)findViewById(R.id.opcion4Pregunta4);
        //inicialización items pregunta 5
        opcion1Pregunta5 = (RadioButton)findViewById(R.id.opcion1Pregunta5);
        opcion2Pregunta5 = (RadioButton)findViewById(R.id.opcion2Pregunta5);
        opcion3Pregunta5 = (RadioButton)findViewById(R.id.opcion3Pregunta5);
        opcion4Pregunta5 = (RadioButton)findViewById(R.id.opcion4Pregunta5);
        pregunta1 = (TextView)findViewById(R.id.pregunta1);
        pregunta2 = (TextView)findViewById(R.id.pregunta2);
        pregunta3 = (TextView)findViewById(R.id.pregunta3);
        pregunta4 = (TextView)findViewById(R.id.pregunta4);
        pregunta5 = (TextView)findViewById(R.id.pregunta5);
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
        //obtiene el color por defecto de las preguntas
        colordefecto = pregunta1.getTextColors();
        context = this;
        //si la version de android es mayor o igual que la 21 el boton calificar recibira una animación de color al presionarlo
        if(Build.VERSION.SDK_INT >= 21) {
            calificar.setBackgroundTintList(getResources().getColorStateList(R.color.btn_calificar_presionado));
        }else{
            //color sin animación
            calificar.setBackgroundColor(getResources().getColor(R.color.btn_calificar_presionado));
        }
        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //items pregunta 1
                if(opcion1Pregunta1.isChecked()){
                    pregunta1.setTextColor(colordefecto);
                    porcentajaAcierto = 20;
                }
                if(opcion2Pregunta1.isChecked()){
                    //establece el color de la pregunta en rojo para indicar que en esta pregunta es el error
                    pregunta1.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                if(opcion3Pregunta1.isChecked()){
                    pregunta1.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                if(opcion4Pregunta1.isChecked()){
                    pregunta1.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                //items pregunta 2
                if(opcion1Pregunta2.isChecked()){
                    pregunta2.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                if(opcion2Pregunta2.isChecked()){
                    pregunta2.setTextColor(colordefecto);
                    porcentajaAcierto = porcentajaAcierto + 20;
                }
                if(opcion3Pregunta2.isChecked()){
                    pregunta2.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                if(opcion4Pregunta2.isChecked()){
                    pregunta2.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                //items pregunta 3
                if(opcion1Pregunta3.isChecked()){
                    pregunta3.setTextColor(context.getResources().getColor(R.color.rojo));

                }
                if(opcion2Pregunta3.isChecked()){
                    pregunta3.setTextColor(context.getResources().getColor(R.color.rojo));

                }
                if(opcion3Pregunta3.isChecked()){
                    pregunta3.setTextColor(colordefecto);
                    porcentajaAcierto = porcentajaAcierto +20;
                }
                if(opcion4Pregunta3.isChecked()){
                    pregunta3.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                //items pregunta 4
                if(opcion1Pregunta4.isChecked()){
                    pregunta4.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                if(opcion2Pregunta4.isChecked()){
                    pregunta4.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                if(opcion3Pregunta4.isChecked()){
                    pregunta4.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                if(opcion4Pregunta4.isChecked()){
                    pregunta4.setTextColor(colordefecto);
                    porcentajaAcierto = porcentajaAcierto + 20;
                }
                //items pregunta 5
                if(opcion1Pregunta5.isChecked()){
                    pregunta5.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                if(opcion2Pregunta5.isChecked()){
                    pregunta5.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                if(opcion3Pregunta5.isChecked()){
                    pregunta5.setTextColor(context.getResources().getColor(R.color.rojo));
                }
                if(opcion4Pregunta5.isChecked()){
                    pregunta5.setTextColor(colordefecto);
                    porcentajaAcierto = porcentajaAcierto + 20;
                }
                //se encargará de inflar el toast personalizado
                LayoutInflater inflater = getLayoutInflater();
                //infla el layout del toast, el primer parametro es el id del layout, el segundo es el id que se establece en el xml toast_personalizado
                View toast = inflater.inflate(R.layout.toast_personalizado, (ViewGroup) findViewById(R.id.toast));
                //instancia del toast, recibe el contextp de la app (donde se va a ejecutar)
                Toast t = new Toast(getApplicationContext());
                TextView txtFallo = (TextView) toast.findViewById(R.id.txtFallo);
                TextView txtCorrecto = (TextView) toast.findViewById(R.id.txtCorrecto);
                TextView txtAcierto = (TextView)toast.findViewById(R.id.txtAcierto);
                //si el porcentaje es igual a 100, muestra el texto de correcto, y oculta el de fallo, de lo contrario realiza lo inverso
                //View.VISIBLE, es para indicar que el control se mostrará
                //View.GONE, es para ocultar el control sin ocupar espacio en pantalla
                if(porcentajaAcierto == 100){
                    txtCorrecto.setVisibility(View.VISIBLE);
                    txtFallo.setVisibility(View.GONE);
                }else{
                    txtCorrecto.setVisibility(View.GONE);
                    txtFallo.setVisibility(View.VISIBLE);
                }
                txtAcierto.setText(String.valueOf(porcentajaAcierto) + " %");
                //Duracion del toast
                t.setDuration(Toast.LENGTH_LONG);
                //establece lo que va a contener el toast, en este caso es el layout que se infló (toast)
                t.setView(toast);
                //establece la posición en pantalla donde se mostrará el toast
                t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                porcentajaAcierto = 0;
                //muestra el toast
                t.show();
                cambiarColorPreguntas();

            }
        });
    }

    public void cambiarColorPreguntas(){
        //inicializacion del hilo
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                //antes de cambiar el color el hilo debe dormirse 10 segundos, para que al usuario le de tiempo de ver en que
                //preguntas se equivocó
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //handler es una variable de tipo Handler, en android no se puede cambiar cualquier propiedad de un control desde un hilo que no lo creo
                //el hilo encargado de crear la vista es UIThread, por tanto si se trata de cambiar el color del texto desde este hilo se lanzaria una excepcion
                //para esto es la clase Handler que mediante el método post se tiene acceso al UIThread y dentro del run establecemos el color por defecto del texto
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        pregunta1.setTextColor(colordefecto);
                        pregunta2.setTextColor(colordefecto);
                        pregunta3.setTextColor(colordefecto);
                        pregunta4.setTextColor(colordefecto);
                        pregunta5.setTextColor(colordefecto);
                    }
                });
            }
        });
        //se iniciar el hilo
        th.start();
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
