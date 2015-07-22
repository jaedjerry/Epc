package com.org.jaed.epc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
    public Button calificar;
    public FloatingActionButton fab, fab2;
    private double cantidadCorrectas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_2);
        fab = (FloatingActionButton)findViewById(R.id.btnLimpiar);//referencia al boton limpiar
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.btn_limpiar_presionado));//background del boton limpiar con efecto
        fab2 = (FloatingActionButton)findViewById(R.id.btnDeshacer);
        fab2.setBackgroundTintList(getResources().getColorStateList(R.color.btn_limpiar_presionado));
        SpecialView specialView = new SpecialView(this);//instancia de canvas
        RelativeLayout contenedor = (RelativeLayout)findViewById(R.id.contenedorActividad2);//referencia al RelativeLayout del archivo actividad_2.xml
        Toolbar toolbar = (Toolbar)findViewById(R.id.appbar2);//referancia al toolbar (barra de titulo)
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Une de A a B");
        calificar = (Button)findViewById(R.id.btnCalificar2);//referencia al boton calificar
        if(Build.VERSION.SDK_INT >=21){//si la version del dispositivo es mayor o igual que la 21 (android 5 y superiores)
            calificar.setBackgroundTintList(getResources().getColorStateList(R.color.btn_calificar_presionado));//establecemos el background con efecto a calificar
        }else{
            calificar.setBackgroundColor(getResources().getColor(R.color.btn_calificar_presionado));//establecemos solamente un background sin efecto para dispositivos menores a android 5
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);//parametros que seran necesarios para el canvas, recibe en el constructor el ancho y el alto del canvas
        layoutParams.addRule(RelativeLayout.BELOW, R.id.toolbar_shadow1);//añadimos al parametro una nueva regla, el primer parametro es el tipo de regla de un RelativeLayout en este caso Below, que es para ubicar el control debajo de la sombra del toolbar, por eso el segundo parametro que es el id de la sombra
        layoutParams.addRule(RelativeLayout.ABOVE, R.id.btnLimpiar);//añadimos al parametro una nueva regla, el primer parametro es el tipo de regla de un RelativeLayout en este caso Above, que es para ubicar el control encima del boton calificar, por eso el segundo parametro que es el id del boton
        specialView.setLayoutParams(layoutParams);//establecemos los parametros anteriores al canvas
        contenedor.addView(specialView);//añadimos al contenedor el canvas
        calificar.setOnClickListener(new View.OnClickListener() {//evento boton calificar
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();//mediante esta clase se inflará el toast
                View toast = inflater.inflate(R.layout.toast_personalizado, (ViewGroup) findViewById(R.id.toast));//inflamos el toast, 1er parametro es el nombre del layout, el 2do parametro es de tipo ViewGroup y es el id del toast
                Toast t = new Toast(getApplicationContext());//clase que maneja el toast recibe el contexto donde se ejecutará
                TextView txtFallo = (TextView) toast.findViewById(R.id.txtFallo);//referencia al texto fallo
                TextView txtCorrecto = (TextView) toast.findViewById(R.id.txtCorrecto);//referencia al texto correcto
                TextView txtAcierto = (TextView)toast.findViewById(R.id.txtAcierto);//referencia al texto nota
                if(cantidadCorrectas == 100.00000000000001){//si la nota es 100
                    txtCorrecto.setVisibility(View.VISIBLE);//pone visible el txtCorrecto
                    txtFallo.setVisibility(View.GONE);//oculta el txtFallo
                }else{
                    txtCorrecto.setVisibility(View.GONE);//oculta el txtCorrecto
                    txtFallo.setVisibility(View.VISIBLE);//pone visible el txtFallo
                }
                DecimalFormat df = new DecimalFormat("0.0");//formato para mostrar la nota, recibe el patron para el formato 0.0, el primer 0 es para mostrar cualquier cantidad de numeros antes del . y un 0 despues del . significa que mostrará solo 1 decimal
                txtAcierto.setText(String.valueOf(df.format(cantidadCorrectas))+ "%");//establece el texto de txtAcierto y formatea cantidadCorrectas para que solo muestre un decimal
                t.setDuration(Toast.LENGTH_LONG);//tiempo que estará visible el toast
                t.setView(toast);//estable la vista del toast, recibe el layout inflado
                t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);//posición del toast en la pantalla, Center_Vertical es para decir al centro
                t.show();//se muestra el Toast
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
    class SpecialView extends View{//clase del canvas
        float x;//posicion en x inicial de la linea
        float y;//posicion en y inicial de la linea
        float xF;//posicion x Final de la linea
        float yF;//posicion y Final de la linea
        int xFinal;//posicion en x de los textos en la columna B
        ArrayList<Linea> lineas = new ArrayList<>();//almacenará las lineas que se dibujen, solo aceptara objetos de tipo Linea
        Paint paint;//para pintar en el canvas
        String estado;
        int tamaño = getResources().getDimensionPixelSize(R.dimen.canvasTextSize);
        public SpecialView(Context context) {
            super(context);
            paint= new Paint();//instancia de paint
            fab.setOnClickListener(new View.OnClickListener() {//evento click del boton limpiar
                @Override
                public void onClick(View v) {
                    lineas.clear();//limpia el ArrayList que guarda las lineas
                    x = 0;
                    y = 0;
                    xF = 0;
                    yF= 0;
                    cantidadCorrectas = 0.0;
                    invalidate();//refresca la vista
                }
            });
            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!lineas.isEmpty()){
                        try{
                            if(lineas.get(lineas.size()-1).getEstado().equals("correcta")){
                                cantidadCorrectas = cantidadCorrectas - (double)100/7;
                            }
                        }catch (NullPointerException e){

                        }
                        lineas.remove(lineas.size()-1);
                        x = 0;
                        y = 0;
                        xF = 0;
                        yF= 0;
                        invalidate();
                    }

                }
            });
        }

        @Override
        protected void onDraw(Canvas canvas) {//metodo que dibuja en la pantalla
            super.onDraw(canvas);
            xFinal = canvas.getWidth() - 150;//xFinal es igual al ancho del canvas - 150, esto para ubicar la columna B automáticamente y hacer un poco responsiva la app
            canvas.drawColor(Color.parseColor("#EEEEEE"));//color de fondo del canvas
            paint.setAntiAlias(true) ;
            paint.setColor(Color.parseColor("#e91e63")) ;
            canvas.drawLine(x, y, xF, yF, paint);//dibuja una linea, esto para que se vea el efecto en tiempo real de que se está dibujando la linea, cada ves que se llama a invalidate es eliminada
            if(!lineas.isEmpty()){//si el arreglo no esta vacio
                for(int i =0; i<lineas.size(); i++){
                    //Log.e("linea "+String.valueOf(i),String.valueOf(lineas.get(i).getX())+","+String.valueOf(lineas.get(i).getY())+","+String.valueOf(lineas.get(i).getxF())+","+String.valueOf(lineas.get(i).getyF()));
                    canvas.drawLine(lineas.get(i).getX(), lineas.get(i).getY(), lineas.get(i).getxF(), lineas.get(i).getyF(), paint);//dibuja las lineas guardadas en el arreglo, recibe como parametro x inicial, y inicial, x Final, y Final, y paint (dibujará la linea)
                }

            }
            paint.setTextSize(tamaño) ;//tamaño del texto
            paint.setColor(Color.BLACK);//color del texto
            canvas.drawText("A", 25, 30, paint);//dibuja el texto, recibe el texto, la posicion en x, y, de ultimo paint
            paint.setColor(Color.BLUE);
            canvas.drawText("Cruel", 10, 50, paint);
            canvas.drawText("Odioso", 10, 100, paint);
            canvas.drawText("Angustia", 10, 150, paint);
            canvas.drawText("Perro", 10, 200, paint);
            canvas.drawText("Caer", 10, 250, paint);
            canvas.drawText("Cascabeleando", 10, 300, paint);
            canvas.drawText("Amiguito", 10, 350, paint);
            paint.setColor(Color.BLACK);
            canvas.drawText("B", xFinal + 40, 30, paint);
            paint.setColor(Color.parseColor("#bf360c"));
            canvas.drawText("Compañero", xFinal, 50, paint);
            canvas.drawText("Alborotando", xFinal, 100, paint);
            canvas.drawText("Tropezar", xFinal, 150, paint);
            canvas.drawText("Lazarillo", xFinal, 200, paint);
            canvas.drawText("Sufrimiendo", xFinal, 250, paint);
            canvas.drawText("Iracundo", xFinal, 300, paint);
            canvas.drawText("Malo", xFinal, 350, paint);
            canvas.restore();
        }

        @Override
        public boolean onTouchEvent(MotionEvent evento) {//evento touch del canvas
            if (evento.getAction() ==MotionEvent.ACTION_DOWN) {//si se presiono la pantalla
                x=evento.getX() ;//establece en x la posicion en x donde se presiono la pantalla
                y=evento.getY() ;//establec en y la posicion en y donde se presiono la pantalla
            }
            if (evento.getAction() ==MotionEvent.ACTION_UP) {//si se dejo de presionar

                //Log.e("posicion xFinal", String.valueOf(xFinal));
                if(x>-70 && x<150 && y>0 && y<65 && xF>xFinal - 30 && xF<xFinal + 70 && yF>310 && yF<370){//si X está entre -70 y 150 e Y esta entre 0 y 65 y XF está entre xFinal - 30 y xFinal + 70 (xFinal se obtiene según la resolución del dispositivo) y YF esta entre 310 y 370, es por que se unio a cruel con malo,
                                                                                                         //la misma lógica en las demas condiciones, solo es un rango en donde debe de estar dibujada la linea
                    x=62;
                    y=33;
                    xF=xFinal;
                    yF=334;
                    estado = "correcta";
                    cantidadCorrectas = cantidadCorrectas + (double)100/7;//establece cantidadCorrectas con lo que tiene cantidadCorrectas + 100/4
                }else{
                    //Log.e("falle", "falle en la linea 0");
                }
                if(x>-10 && x<150 && y>70 && y<110 && xF>xFinal - 30 && xF<xFinal + 90 && yF>190 && yF<330){
                    x=84;
                    y=91;
                    xF=xFinal;
                    yF=290;
                    estado = "correcta";
                    cantidadCorrectas = cantidadCorrectas + (double)100/7;
                }else{
                    //Log.e("falle", "falle en la linea 1");
                }
                if(x>-10 && x<150 && y>115 && y<155 && xF>xFinal -30 && xF<xFinal +90 && yF>220 && yF<280){
                    x=105;
                    y=143;
                    xF=xFinal;
                    yF=240;
                    estado = "correcta";
                    cantidadCorrectas = cantidadCorrectas + (double)100/7;
                }else{
                    //Log.e("falle", "falle en la linea 2");
                }
                if(x>-10 && x<150 && y>160 && y<200 && xF>xFinal -30 && xF<xFinal +90 && yF>170 && yF<220){
                    x=65;
                    y=191;
                    xF=xFinal;
                    yF=190;
                    estado = "correcta";
                    cantidadCorrectas = cantidadCorrectas + (double)100/7;
                }else{
                    //Log.e("falle", "falle en la linea 3");
                }
                if(x>-10 && x<150 && y>205 && y<260 && xF>xFinal -30 && xF<xFinal +90 && yF>110 && yF<165){
                    x=57;
                    y=244;
                    xF=xFinal;
                    yF=145;
                    estado = "correcta";
                    cantidadCorrectas = cantidadCorrectas + (double)100/7;
                }else{
                    //Log.e("falle", "falle en la linea 4");
                }
                if(x>-10 && x<180 && y>265 && y<310 && xF>xFinal -30 && xF<xFinal +90 && yF>70 && yF<115){
                    x=179;
                    y=291;
                    xF=xFinal;
                    yF=97;
                    estado = "correcta";
                    cantidadCorrectas = cantidadCorrectas + (double)100/7;
                }else{
                    //Log.e("falle", "falle en la linea 5");
                }
                if(x>-10 && x<150 && y>315 && y<370 && xF>xFinal -30 && xF<xFinal +90 && yF>30 && yF<70){
                    x=107;
                    y=343;
                    xF=xFinal;
                    yF=51;
                    estado = "correcta";
                    cantidadCorrectas = cantidadCorrectas + (double)100/7;
                }else{
                    //Log.e("falle", "falle en la linea 6");
                }
                lineas.add(new Linea(x, y, xF, yF,estado));//añade un nueva linea al arreglo con su posición en x, y, xF, yF y su estado
                estado = "";
            }
            if(evento.getAction()==MotionEvent.ACTION_MOVE) {//si se mueve el dedo por la pantalla
                xF=  evento.getX();//xF se va actualizando en la posicion en x por la que se desplaza el dedo
                yF = evento.getY();//yF se va actualizando en la posicion en y por la que se desplaza el dedo
            }
            invalidate();//refresca la vista
            return true;
        }
    }
    class Linea{//clase que guardará las posiciones en x, y, xF, yF de la linea que el usuario vaya dibujando
        private  float x;//x inicial
        private float y;//y inicial
        private float xF;//x final
        private float yF;//y final
        private String estado;//estado de la lines puede ser correcta, o incorrecta
        public Linea(float x, float y, float xF, float yF, String e){
            this.setX(x);
            this.setY(y);
            this.setxF(xF);
            this.setyF(yF);
            this.setEstado(e);
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getxF() {
            return xF;
        }

        public void setxF(float xF) {
            this.xF = xF;
        }

        public float getyF() {
            return yF;
        }

        public void setyF(float yF) {
            this.yF = yF;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
}
