package com.org.jaed.epc;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jerry on 25/07/2015.
 */
public class splash extends AppCompatActivity {
    private Button entrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        entrar = (Button)findViewById(R.id.btnEntrar);
        entrar.setBackgroundResource(R.drawable.btn_calificar_presionado);
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        entrar.setTypeface(font);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
