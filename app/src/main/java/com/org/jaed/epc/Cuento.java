package com.org.jaed.epc;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by Jerry on 05/08/2015.
 */
public class Cuento extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView webView;
    private ViewFlipper vf;
    private TextView txtPagina1, txtPagina2, txtPagina3, txtPagina4, txtPagina5, txtPagina6, txtPagina7, txtPagina8, txtPagina9, txtPagina10, txtPagina11;
    private TextView txtPagina12, txtPagina13, txtPagina14;
    private FloatingActionButton fabAtras, fabSiguiente;
    private Sonido snd;
    private boolean pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuento_main);
        toolbar = (Toolbar) findViewById(R.id.appbarCuento);
        setSupportActionBar(toolbar);
        txtPagina1 = (TextView)findViewById(R.id.txtPagina1);
        txtPagina2 = (TextView)findViewById(R.id.txtPagina2);
        txtPagina3 = (TextView)findViewById(R.id.txtPagina3);
        txtPagina4 = (TextView)findViewById(R.id.txtPagina4);
        txtPagina5 = (TextView)findViewById(R.id.txtPagina5);
        txtPagina6 = (TextView)findViewById(R.id.txtPagina6);
        txtPagina7 = (TextView)findViewById(R.id.txtPagina7);
        txtPagina8 = (TextView)findViewById(R.id.txtPagina8);
        txtPagina9 = (TextView)findViewById(R.id.txtP9);
        txtPagina10 = (TextView)findViewById(R.id.txtPagina10);
        txtPagina11 = (TextView)findViewById(R.id.txtPagina11);
        txtPagina12 = (TextView)findViewById(R.id.txtPagina12);
        txtPagina13 = (TextView)findViewById(R.id.txtPagina13);
        txtPagina14 = (TextView)findViewById(R.id.txtPagina14);
        vf = (ViewFlipper)findViewById(R.id.viewFlipper);
        webView = (WebView)findViewById(R.id.Gifpagina);
        fabAtras = (FloatingActionButton)findViewById(R.id.fabAtras);
        fabSiguiente = (FloatingActionButton)findViewById(R.id.fabSig);
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        txtPagina1.setTypeface(font);
        txtPagina2.setTypeface(font);
        txtPagina3.setTypeface(font);
        txtPagina4.setTypeface(font);
        txtPagina5.setTypeface(font);
        txtPagina6.setTypeface(font);
        txtPagina7.setTypeface(font);
        txtPagina8.setTypeface(font);
        txtPagina9.setTypeface(font);
        txtPagina10.setTypeface(font);
        txtPagina11.setTypeface(font);
        txtPagina12.setTypeface(font);
        txtPagina13.setTypeface(font);
        txtPagina14.setTypeface(font);
        vf.setOnTouchListener(new pasarPagina());
        webView.setOnTouchListener(new pasarPagina());
        fabAtras.setOnClickListener(new onClickfab());
        fabSiguiente.setOnClickListener(new onClickfab());
        fabAtras.setBackgroundTintList(getResources().getColorStateList(R.color.fab_presionado));
        fabSiguiente.setBackgroundTintList(getResources().getColorStateList(R.color.fab_presionado));
        snd = new Sonido(getApplicationContext());
        this.setAnimPaginaActual(0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        snd.stop();
        pause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(pause){
            snd.play();
        }
        pause = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.alEjercicio){
            snd.stop();
            startActivity(new Intent().setClass(getApplicationContext(), MenuEjercicios.class));
        }
        if(id == R.id.alPrincipal){
            snd.stop();
            startActivity(new Intent().setClass(getApplicationContext(), MainMenu.class));
        }
        if(id == R.id.salir){
        }
        return super.onOptionsItemSelected(item);
    }
    private class onClickfab implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.fabAtras){
                vf.setInAnimation(inFromRightAnimation());
                vf.setOutAnimation(outToRightAnimation());
                vf.showPrevious();
                setAnimPaginaActual(vf.getDisplayedChild());
            }
            if(v.getId() == R.id.fabSig){
                vf.setInAnimation(inFromLeftAnimation());
                vf.setOutAnimation(outToLeftAnimation());
                vf.showNext();
                setAnimPaginaActual(vf.getDisplayedChild());
            }
        }
    }
    public class pasarPagina implements View.OnTouchListener {
        private float init_x;
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    init_x = event.getX();
                    return true;
                case MotionEvent.ACTION_UP:
                    float distancia = this.init_x-event.getX();
                    if(distancia>0){
                        vf.setInAnimation(inFromLeftAnimation());
                        vf.setOutAnimation(outToLeftAnimation());
                        vf.showNext();
                        setAnimPaginaActual(vf.getDisplayedChild());
                    }
                    if(distancia<0){
                        vf.setInAnimation(inFromRightAnimation());
                        vf.setOutAnimation(outToRightAnimation());
                        vf.showPrevious();
                        setAnimPaginaActual(vf.getDisplayedChild());
                    }
                default:
                    break;
            }
            return true;
        }
    }
    private Animation inFromRightAnimation(){
        return AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_in);
    }
    private Animation outToLeftAnimation(){
        return AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_out);
    }
    private Animation inFromLeftAnimation(){
        return AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_in);
    }
    private Animation outToRightAnimation(){
        return AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_out);
    }
    public void setAnimPaginaActual(int id){
        if(id == 0){
            webView.loadUrl("file:///android_asset/anim5.html");
            snd.setResid(R.raw.p1);
            snd.play();
        }
        if(id == 1){
            webView.loadUrl("file:///android_asset/anim6.html");
            snd.setResid(R.raw.p2);
            snd.play();
        }
        if(id == 2){
            webView.loadUrl("file:///android_asset/anim7.html");
            snd.setResid(R.raw.p3);
            snd.play();
        }
        if(id == 3){
            webView.loadUrl("file:///android_asset/anim8.html");
            snd.setResid(R.raw.p4);
            snd.play();
        }
        if(id == 4){
            webView.loadUrl("file:///android_asset/anim9.html");
            snd.setResid(R.raw.p5);
            snd.play();
        }
        if(id == 5){
            webView.loadUrl("file:///android_asset/anim10.html");
            snd.setResid(R.raw.p6);
            snd.play();
        }
        if(id == 6){
            webView.loadUrl("file:///android_asset/anim11.html");
            snd.setResid(R.raw.p7);
            snd.play();
        }
        if (id == 7){
            webView.loadUrl("file:///android_asset/anim12.html");
            snd.setResid(R.raw.p8);
            snd.play();
        }
        if(id == 8){
            webView.loadUrl("file:///android_asset/anim13.html");
            snd.setResid(R.raw.p9);
            snd.play();
        }
        if(id == 9){
            webView.loadUrl("file:///android_asset/anim14.html");
            snd.setResid(R.raw.p10);
            snd.play();
        }
        if(id == 10){
            webView.loadUrl("file:///android_asset/anim15.html");
            snd.setResid(R.raw.p11);
            snd.play();
        }
        if(id == 11){
            webView.loadUrl("file:///android_asset/anim16.html");
            snd.setResid(R.raw.p12);
            snd.play();
        }
        if(id == 12){
            webView.loadUrl("file:///android_asset/anim17.html");
            snd.setResid(R.raw.p13);
            snd.play();
        }
        if(id == 13){
            webView.loadUrl("file:///android_asset/anim18.html");
            snd.setResid(R.raw.p14);
            snd.play();
        }
    }
}
