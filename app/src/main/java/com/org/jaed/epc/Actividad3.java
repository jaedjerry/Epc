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
    private double miNota = 0;
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
                        txt2.setVisibility(View.VISIBLE);
                        txt3.setVisibility(View.VISIBLE);
                        txtCiego.setVisibility(View.VISIBLE);
                        txt1.setLayoutParams(paramsOriginalestxt1);
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = -50;
                        layoutParams.bottomMargin = -50;
                        v.setLayoutParams(layoutParams);
                        if(y > (int) txt2.getY() && y < txt2.getY() + txt2.getHeight()){
                            txt2.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt2.setBackgroundDrawable(original);
                        }
                        if(y > (int)txt3.getY()  && y < txt3.getY() + txt3.getHeight()){
                            txt3.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt3.setBackgroundDrawable(original2);
                        }
                        if(y>(int)txtCiego.getY() && y < txtCiego.getY() + txtCiego.getHeight()){
                            txtCiego.setBackgroundResource(R.drawable.borde);
                        }else{
                            txtCiego.setBackgroundDrawable(originalCiego);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        txt1.setBackgroundDrawable(original1);
                        if(y > (int) txt2.getY() && y < txt2.getY() + txt2.getHeight()){
                            txt2.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt2.getLayoutParams();
                            txt1.setLayoutParams(txtparams);
                            miNota = miNota + (double) 100/3;
                        }
                        if(y > (int)txt3.getY() && y < txt3.getY() + txt3.getHeight()) {
                            Log.e("up", "entre en up de la condicion 2");
                            txt3.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt3.getLayoutParams();
                            txt1.setLayoutParams(txtparams);
                            if(miNota != 0){
                                miNota = miNota - (double)100/3;
                            }
                        }
                        if(y>(int)txtCiego.getY() && y < txtCiego.getY() + txtCiego.getHeight()){
                            txtCiego.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txtCiego.getLayoutParams();
                            txt1.setLayoutParams(txtparams);
                            if(miNota != 0){
                                miNota = miNota - (double)100/3;
                            }
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
                        txt2.setVisibility(View.VISIBLE);
                        txt3.setVisibility(View.VISIBLE);
                        txtCiego.setVisibility(View.VISIBLE);
                        txtPrincipal.setLayoutParams(paramsOriginalesPrincipal);
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = -50;
                        layoutParams.bottomMargin = -50;
                        v.setLayoutParams(layoutParams);
                        if(y > (int) txt2.getY() && y < txt2.getY() + txt2.getHeight()){
                            txt2.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt2.setBackgroundDrawable(original);
                        }
                        if(y > (int)txt3.getY()  && y < txt3.getY() + txt3.getHeight()){
                            txt3.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt3.setBackgroundDrawable(original2);
                        }
                        if(y>(int)txtCiego.getY()  && y < txtCiego.getY() + txtCiego.getHeight()){
                            txtCiego.setBackgroundResource(R.drawable.borde);
                        }else{
                            txtCiego.setBackgroundDrawable(originalCiego);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        txtPrincipal.setBackgroundDrawable(originalPrincipal);
                        if(y > (int) txt2.getY()  && y < txt2.getY() + txt2.getHeight()){
                            txt2.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt2.getLayoutParams();
                            txtPrincipal.setLayoutParams(txtparams);
                            if(miNota != 0){
                                miNota = miNota - (double)100/3;
                            }
                        }
                        if(y > (int)txt3.getY() && y < txt3.getY() + txt3.getHeight()) {
                            Log.e("up", "entre en up de la condicion 2");
                            txt3.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt3.getLayoutParams();
                            txtPrincipal.setLayoutParams(txtparams);

                            miNota = miNota + (double) 100/3;
                        }
                        if(y>(int)txtCiego.getY() && y < txtCiego.getY() + txtCiego.getHeight()){
                            txtCiego.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txtCiego.getLayoutParams();
                            txtPrincipal.setLayoutParams(txtparams);
                            if(miNota != 0){
                                miNota = miNota - (double)100/3;
                            }
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
                        txt2.setVisibility(View.VISIBLE);
                        txt3.setVisibility(View.VISIBLE);
                        txtCiego.setVisibility(View.VISIBLE);
                        txtSecundario.setLayoutParams(paramsOriginalesSecundario);
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams) v.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = -50;
                        layoutParams.bottomMargin = -50;
                        v.setLayoutParams(layoutParams);
                        if(y > (int) txt2.getY()  && y < txt2.getY() + txt2.getHeight()){
                            txt2.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt2.setBackgroundDrawable(original);
                        }
                        if(y > (int)txt3.getY() && y < txt3.getY() + txt3.getHeight()){
                            txt3.setBackgroundResource(R.drawable.borde);
                        }else{
                            txt3.setBackgroundDrawable(original2);
                        }
                        if(y>(int)txtCiego.getY() && y < txtCiego.getY() + txtCiego.getHeight()){
                            txtCiego.setBackgroundResource(R.drawable.borde);
                        }else{
                            txtCiego.setBackgroundDrawable(originalCiego);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        txtSecundario.setBackgroundDrawable(originalSecundario);
                        if(y > (int) txt2.getY()  && y < txt2.getY() + txt2.getHeight()){
                            txt2.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt2.getLayoutParams();
                            txtSecundario.setLayoutParams(txtparams);
                            if(miNota != 0){
                                miNota = miNota - (double)100/3;
                            }
                        }
                        if(y > (int)txt3.getY() && y < txt3.getY() + txt3.getHeight()) {
                            Log.e("up", "entre en up de la condicion 2");
                            txt3.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt3.getLayoutParams();
                            txtSecundario.setLayoutParams(txtparams);
                            if(miNota != 0){
                                miNota = miNota - (double)100/3;
                            }
                        }
                        if(y>(int)txtCiego.getY() && y < txtCiego.getY() + txtCiego.getHeight()){
                            txtCiego.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txtCiego.getLayoutParams();
                            txtSecundario.setLayoutParams(txtparams);
                            miNota = miNota + (double) 100/3;
                        }
                        break;

                }
                marco.invalidate();
                return true;
            }
        });
        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();//mediante esta clase se inflará el toast
                View toast = inflater.inflate(R.layout.toast_personalizado, (ViewGroup) findViewById(R.id.toast));//inflamos el toast, 1er parametro es el nombre del layout, el 2do parametro es de tipo ViewGroup y es el id del toast
                Toast t = new Toast(getApplicationContext());//clase que maneja el toast recibe el contexto donde se ejecutará
                TextView txtFallo = (TextView) toast.findViewById(R.id.txtFallo);//referencia al texto fallo
                TextView txtCorrecto = (TextView) toast.findViewById(R.id.txtCorrecto);//referencia al texto correcto
                TextView txtAcierto = (TextView)toast.findViewById(R.id.txtAcierto);//referencia al texto nota
                if(miNota == 100.0){//si la nota es 100
                    txtCorrecto.setVisibility(View.VISIBLE);//pone visible el txtCorrecto
                    txtFallo.setVisibility(View.GONE);//oculta el txtFallo
                }else{
                    txtCorrecto.setVisibility(View.GONE);//oculta el txtCorrecto
                    txtFallo.setVisibility(View.VISIBLE);//pone visible el txtFallo
                }
                DecimalFormat df = new DecimalFormat("0.0");//formato para mostrar la nota, recibe el patron para el formato 0.0, el primer 0 es para mostrar cualquier cantidad de numeros antes del . y un 0 despues del . significa que mostrará solo 1 decimal
                txtAcierto.setText(String.valueOf(df.format(miNota))+ "%");//establece el texto de txtAcierto y formatea cantidadCorrectas para que solo muestre un decimal
                t.setDuration(Toast.LENGTH_LONG);//tiempo que estará visible el toast
                t.setView(toast);//estable la vista del toast, recibe el layout inflado
                t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);//posicion del toast en la pantalla, Center_Vertical es para decir al centro
                t.show();//se muestra el Toast
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
