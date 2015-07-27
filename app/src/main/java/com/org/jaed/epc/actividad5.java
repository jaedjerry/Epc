package com.org.jaed.epc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

/**
 * Created by Jerry on 26/07/2015.
 */
public class actividad5 extends AppCompatActivity {
    private Toolbar toolbar;
    private RelativeLayout contenedor;
    private Button calificar;
    private Spinner sIdea1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_5);
        toolbar = (Toolbar)findViewById(R.id.appbar5);
        calificar = (Button)findViewById(R.id.btnCalificar5);
        sIdea1 = (Spinner)findViewById(R.id.spinner_idea1);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            toolbar.setNavigationIcon(R.mipmap.ic_atras);
            getSupportActionBar().setTitle("Arrastra");
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.valores_ideas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for(int i = 0; i<=contenedor.getChildCount(); i++){
            View v = contenedor.getChildAt(i);
            if(v instanceof Spinner){
                ((Spinner) v).setAdapter(adapter);
            }
        }
        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("seleccione", "seleccione: "+sIdea1.getSelectedItem().toString());
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
