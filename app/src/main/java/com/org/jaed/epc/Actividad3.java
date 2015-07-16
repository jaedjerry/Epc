package com.org.jaed.epc;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jerry on 13/07/2015.
 */
public class Actividad3 extends AppCompatActivity {
    private TextView txt1, txt2, txt3, txtCiego;
    private Context context;
    private RelativeLayout marco;
    private Toolbar toolbar;
    private int xDelta;
    private int yDelta;
    private Button calificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_3);
        txt1 = (TextView)findViewById(R.id.personajeAntagonico);
        txt2 = (TextView)findViewById(R.id.pacoMalo);
        txt3 = (TextView)findViewById(R.id.perro);
        txtCiego = (TextView)findViewById(R.id.ciego);
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
        final RelativeLayout.LayoutParams paramsOriginalestxt1 = (RelativeLayout.LayoutParams)txt1.getLayoutParams();
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
                        if(y > (int) txt2.getY() + 10 && y < txt2.getY() + 75){
                            txt2.setBackgroundResource(R.drawable.borde);
                            //Log.e("move", "posicion en x del target: "+String.valueOf(txt2.getX())+", "+"posicion en x del arrastre"+String.valueOf(x));
                        }else{
                            txt2.setBackgroundDrawable(original);
                            //Log.e("falle en move","posicion en x del target"+String.valueOf(txt2.getX())+", "+"posicion en x del arrastre" + String.valueOf(x));
                        }
                        if(y > (int)txt3.getY() + 10 && y < txt3.getY() + 75){
                            txt3.setBackgroundResource(R.drawable.borde);
                            Log.e("move", "condicion 2 posicion en x del target: " + String.valueOf(txt3.getX()) + ", " + "posicion en x del arrastre" + String.valueOf(x));
                        }else{
                            txt3.setBackgroundDrawable(original2);
                            Log.e("falle en move", "condicion 2 posicion en x del target" + String.valueOf(txt3.getX()) + ", " + "posicion en x del arrastre" + String.valueOf(x));
                        }
                        if(y>(int)txtCiego.getY() + 10 && y < txtCiego.getY() + 75){
                            txtCiego.setBackgroundResource(R.drawable.borde);
                        }else{
                            txtCiego.setBackgroundDrawable(originalCiego);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        txt1.setBackgroundDrawable(original1);
                        if(y > (int) txt2.getY() + 10 && y < txt2.getY() + 75){
                            txt2.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt2.getLayoutParams();
                            txt1.setLayoutParams(txtparams);
                            //txt1.setX(txt2.getX());
                            //txt1.setY(txt2.getY());
                        }
                        if(y > (int)txt3.getY() + 10 && y < txt3.getY() + 75) {
                            Log.e("up", "entre en up de la condicion 2");
                            txt3.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txt3.getLayoutParams();
                            txt1.setLayoutParams(txtparams);
                            //txt1.setX(txt3.getX());
                            //txt1.setY(txt3.getY());
                        }
                        if(y>(int)txtCiego.getY() + 10 && y < txtCiego.getY() + 75){
                            txtCiego.setVisibility(View.GONE);
                            RelativeLayout.LayoutParams txtparams = (RelativeLayout.LayoutParams)txtCiego.getLayoutParams();
                            txt1.setLayoutParams(txtparams);
                        }
                        break;

                }
                marco.invalidate();
                return true;
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
