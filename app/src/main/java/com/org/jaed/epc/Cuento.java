package com.org.jaed.epc;

import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.RelativeLayout;
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
    private TextView txtPagina12, txtPagina13, txtPagina14, txtPagina15, txtPagina16, txtPagina17, txtPagina18, txtPagina19, txtPagina20, txtPagina21;
    private TextView txtPagina22, txtPagina23, txtPagina24, txtPagina25,txtPagina26,txtPagina27,txtPagina28, txtfin;
    private FloatingActionButton fabAtras, fabSiguiente;
    private Sonido snd;
    private boolean pause;
    private RelativeLayout.LayoutParams paramsVf;
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
        txtPagina15 = (TextView)findViewById(R.id.txtPagina15);
        txtPagina16 = (TextView)findViewById(R.id.txtPagina16);
        txtPagina17 = (TextView)findViewById(R.id.txtPagina17);
        txtPagina18 = (TextView)findViewById(R.id.txtPagina18);
        txtPagina19 = (TextView)findViewById(R.id.txtPagina19);
        txtPagina20 = (TextView)findViewById(R.id.txtPagina20);
        txtPagina21 = (TextView)findViewById(R.id.txtPagina21);
        txtPagina22 = (TextView)findViewById(R.id.txtPagina22);
        txtPagina23 = (TextView)findViewById(R.id.txtPagina23);
        txtPagina24 = (TextView)findViewById(R.id.txtPagina24);
        txtPagina25 = (TextView)findViewById(R.id.txtPagina25);
        txtPagina26 = (TextView)findViewById(R.id.txtPagina26);
        txtPagina27 = (TextView)findViewById(R.id.txtPagina27);
        txtPagina28 = (TextView)findViewById(R.id.txtPagina28);
        txtfin = (TextView)findViewById(R.id.txtFin);
        vf = (ViewFlipper)findViewById(R.id.viewFlipper);
        webView = (WebView)findViewById(R.id.Gifpagina);
        fabAtras = (FloatingActionButton)findViewById(R.id.fabAtras);
        fabSiguiente = (FloatingActionButton)findViewById(R.id.fabSig);
        RelativeLayout contenedorCuento = (RelativeLayout)findViewById(R.id.contenedorCuento);
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
        txtPagina15.setTypeface(font);
        txtPagina16.setTypeface(font);
        txtPagina17.setTypeface(font);
        txtPagina18.setTypeface(font);
        txtPagina19.setTypeface(font);
        txtPagina20.setTypeface(font);
        txtPagina21.setTypeface(font);
        txtPagina22.setTypeface(font);
        txtPagina23.setTypeface(font);
        txtPagina24.setTypeface(font);
        txtPagina25.setTypeface(font);
        txtPagina26.setTypeface(font);
        txtPagina27.setTypeface(font);
        txtPagina28.setTypeface(font);
        txtfin.setTypeface(font);
        paramsVf = (RelativeLayout.LayoutParams)vf.getLayoutParams();
        vf.setOnTouchListener(new pasarPagina());
        webView.setOnTouchListener(new pasarPagina());
        fabAtras.setOnClickListener(new onClickfab());
        fabSiguiente.setOnClickListener(new onClickfab());
        contenedorCuento.setOnTouchListener(new pasarPagina());
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
            if(webView.getVisibility() == View.GONE){
                webView.setVisibility(View.VISIBLE);
                vf.setLayoutParams(paramsVf);
            }
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
        if(id == 14){
            webView.loadUrl("file:///android_asset/anim19.html");
            snd.setResid(R.raw.p15);
            snd.play();
        }
        if(id == 15){
            webView.loadUrl("file:///android_asset/anim20.html");
            snd.setResid(R.raw.p16);
            snd.play();
        }
        if(id == 16){
            webView.loadUrl("file:///android_asset/anim21.html");
            snd.setResid(R.raw.p17);
            snd.play();
        }
        if(id == 17){
            webView.loadUrl("file:///android_asset/anim22.html");
            snd.setResid(R.raw.p18);
            snd.play();
        }
        if(id == 18){
            webView.loadUrl("file:///android_asset/anim23.html");
            snd.setResid(R.raw.p19);
            snd.play();
        }
        if(id == 19){
            webView.loadUrl("file:///android_asset/anim24.html");
            snd.setResid(R.raw.p20);
            snd.play();
        }
        if(id == 20){
            webView.loadUrl("file:///android_asset/anim25.html");
            snd.setResid(R.raw.p21);
            snd.play();
        }
        if(id == 21){
            webView.loadUrl("file:///android_asset/anim26.html");
            snd.setResid(R.raw.p22);
            snd.play();
        }
        if(id == 22){
            webView.loadUrl("file:///android_asset/anim27.html");
            snd.setResid(R.raw.p23);
            snd.play();
        }
        if(id == 23){
            webView.loadUrl("file:///android_asset/anim28.html");
            snd.setResid(R.raw.p24);
            snd.play();
        }
        if(id == 24){
            webView.loadUrl("file:///android_asset/anim29.html");
            snd.setResid(R.raw.p25);
            snd.play();
        }
        if(id == 25){
            webView.loadUrl("file:///android_asset/anim30.html");
            snd.setResid(R.raw.p26);
            snd.play();
        }
        if(id == 26){
            webView.loadUrl("file:///android_asset/anim31.html");
            snd.setResid(R.raw.p27);
            snd.play();
        }
        if(id == 27){
            if(webView.getVisibility() == View.GONE){
                webView.setVisibility(View.VISIBLE);
                vf.setLayoutParams(paramsVf);
            }
            webView.loadUrl("file:///android_asset/anim32.html");
            snd.setResid(R.raw.p28);
            snd.play();
        }
        if(id == 28){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            vf.setLayoutParams(params);
            webView.setVisibility(View.GONE);
            snd.stop();
        }
    }
}
