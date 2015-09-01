package com.org.jaed.epc;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Jerry on 13/07/2015.
 */
public class Actividad3 extends AppCompatActivity {
    private TextView txtPersonajeAntagonico, txtPacoMalo, txtPerro, txtCiego, txtPrincipal, txtSecundario;
    private  Context context;
    private RelativeLayout marco;
    private Toolbar toolbar;
    private Button calificar;
    private int aciertos, intentos;
    private View child;
    private boolean ocupado1, ocupado2, ocupado3;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_3);
        txtPacoMalo = (TextView)findViewById(R.id.pacoMalo);
        txtPerro = (TextView)findViewById(R.id.perro);
        txtCiego = (TextView)findViewById(R.id.ciego);
        txtPersonajeAntagonico = (TextView)findViewById(R.id.personajeAntagonico);
        txtPrincipal = (TextView)findViewById(R.id.principal);
        txtSecundario = (TextView)findViewById(R.id.secundario);
        marco = (RelativeLayout)findViewById(R.id.marco);
        calificar = (Button)findViewById(R.id.btnCalificar3);
        context = this;
        toolbar = (Toolbar)findViewById(R.id.appbar3);// referencia al toolbar
        setSupportActionBar(toolbar);//establecemos el toolbar de la actividad con nuestro toolbar personalizado
        getSupportActionBar().setDisplayShowTitleEnabled(true);//habilitamos el titulo de la actividad
        getSupportActionBar().setTitle("Arrastra");//titulo de la actividad
        calificar.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_calificar_presionado));
        //Datos de arriba
        final RelativeLayout.LayoutParams paramsOriginalesPaco = (RelativeLayout.LayoutParams)txtPacoMalo.getLayoutParams();//parametros originales de txtPacoMalo
        final Drawable bgPaco = txtPacoMalo.getBackground();//background original de txtPacoMalo
        final RelativeLayout.LayoutParams paramsOriginalesPerro = (RelativeLayout.LayoutParams)txtPerro.getLayoutParams();
        final Drawable bgPerro = txtPerro.getBackground();
        final RelativeLayout.LayoutParams paramsOriginalesCiego = (RelativeLayout.LayoutParams)txtCiego.getLayoutParams();
        final Drawable bgCiego = txtCiego.getBackground();

        //Datos de abajo
        final RelativeLayout.LayoutParams paramsOriginaleAntagonico = (RelativeLayout.LayoutParams)txtPersonajeAntagonico.getLayoutParams();
        final RelativeLayout.LayoutParams paramsOriginalesPrincipal = (RelativeLayout.LayoutParams)txtPrincipal.getLayoutParams();
        final RelativeLayout.LayoutParams paramsOriginalesSecundario = (RelativeLayout.LayoutParams)txtSecundario.getLayoutParams();
        Typeface font = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
        for(int i = 0; i<=marco.getChildCount();i++){
            View v = marco.getChildAt(i);
            if(v instanceof TextView){
                ((TextView) v).setTypeface(font);
            }
            if(v instanceof Button){
                ((Button) v).setTypeface(font);
            }
        }
        txtPersonajeAntagonico.setOnTouchListener(new View.OnTouchListener() {//habilitamos la escuha del evento onTouch para txtPersonajeAntagonico (el que se va a arrastrar)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //v es txtPersonajeAntagonico
                switch (event.getAction() & MotionEvent.ACTION_MASK) {//segun sea la accion del evento
                    case MotionEvent.ACTION_DOWN://en caso de presionar txtPersonajeAntagonico
                        if (v.getLayoutParams() == paramsOriginalesPaco) {//si los parametros de txtPersonajeAntagonico son iguales a los de txtPacoMalo, es por que txtPersonajeAntagonico no esta en su poción inicial y esta sobre el target, y por tanto
                            ocupado1 = false;                            //al momento de que se ejecuta el action down se esta saliendo del target y pone ocupado en false, que indica que el target está vacio.
                            v.setLayoutParams(paramsOriginaleAntagonico);//establecemos los parametros de txtPersonajeAntagonico a sus valores originales
                            if (aciertos > 0) {
                                aciertos--;//restamos los aciertos ya que se salio de su target
                            }
                        }
                        if (v.getLayoutParams() == paramsOriginalesPerro) {
                            ocupado2 = false;
                            v.setLayoutParams(paramsOriginaleAntagonico);
                        }
                        if (v.getLayoutParams() == paramsOriginalesCiego) {
                            ocupado3 = false;
                            v.setLayoutParams(paramsOriginaleAntagonico);
                        }
                        ClipData clipData = ClipData.newPlainText("antagonico", "antagonico");//para identificar el textview que esta sobre el target por medio de un texto en este caso antagonico.
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);//Crea una copia identica del control durante el proceso de drag and drop
                        v.startDrag(clipData, dragShadowBuilder, v, 0);//iniciamos el arrastrado, recibe el clip data, la copia del control, la vista que llama a starDrag y un 0
                        v.setVisibility(View.INVISIBLE);//ocultamos txtPersonajeAntagonico
                        if (txtPacoMalo.getVisibility() == View.GONE && !ocupado1) {//si el target esta oculto y no esta ocupado
                            txtPacoMalo.setVisibility(View.VISIBLE);//hacemos visible el target
                            txtPacoMalo.setBackgroundDrawable(bgPaco);//establecmos su background original
                        }
                        if (txtPerro.getVisibility() == View.GONE && !ocupado2) {
                            txtPerro.setVisibility(View.VISIBLE);
                            txtPerro.setBackgroundDrawable(bgPerro);
                        }
                        if (txtCiego.getVisibility() == View.GONE && !ocupado3) {
                            txtCiego.setVisibility(View.VISIBLE);
                            txtCiego.setBackgroundDrawable(bgCiego);
                        }
                        break;
                }
                return true;
            }
        });
        txtPrincipal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsOriginalesPaco) {
                            ocupado1 = false;
                            v.setLayoutParams(paramsOriginalesPrincipal);
                        }
                        if (v.getLayoutParams() == paramsOriginalesPerro) {
                            ocupado2 = false;
                            v.setLayoutParams(paramsOriginalesPrincipal);
                            if (aciertos > 0) {
                                aciertos--;
                            }
                        }
                        if (v.getLayoutParams() == paramsOriginalesCiego) {
                            ocupado3 = false;
                            v.setLayoutParams(paramsOriginalesPrincipal);
                        }
                        ClipData clipData = ClipData.newPlainText("principal", "principal");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if (txtPacoMalo.getVisibility() == View.GONE && !ocupado1) {
                            txtPacoMalo.setVisibility(View.VISIBLE);
                            txtPacoMalo.setBackgroundDrawable(bgPaco);
                        }
                        if (txtPerro.getVisibility() == View.GONE && !ocupado2) {
                            txtPerro.setVisibility(View.VISIBLE);
                            txtPerro.setBackgroundDrawable(bgPerro);
                        }
                        if (txtCiego.getVisibility() == View.GONE && !ocupado3) {
                            txtCiego.setVisibility(View.VISIBLE);
                            txtCiego.setBackgroundDrawable(bgCiego);
                        }
                        break;
                }
                return true;
            }
        });
        txtSecundario.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if (v.getLayoutParams() == paramsOriginalesPaco) {
                            ocupado1 = false;
                            v.setLayoutParams(paramsOriginalesSecundario);
                        }
                        if (v.getLayoutParams() == paramsOriginalesPerro) {
                            ocupado2 = false;
                            v.setLayoutParams(paramsOriginalesSecundario);
                        }
                        if (v.getLayoutParams() == paramsOriginalesCiego) {
                            ocupado3 = false;
                            v.setLayoutParams(paramsOriginalesSecundario);
                            if (aciertos > 0) {
                                aciertos--;
                            }
                        }
                        ClipData clipData = ClipData.newPlainText("secundario", "secundario");
                        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(clipData, dragShadowBuilder, v, 0);
                        v.setVisibility(View.INVISIBLE);
                        if (txtPacoMalo.getVisibility() == View.GONE && !ocupado1) {
                            txtPacoMalo.setVisibility(View.VISIBLE);
                            txtPacoMalo.setBackgroundDrawable(bgPaco);
                        }
                        if (txtPerro.getVisibility() == View.GONE && !ocupado2) {
                            txtPerro.setVisibility(View.VISIBLE);
                            txtPerro.setBackgroundDrawable(bgPerro);
                        }
                        if (txtCiego.getVisibility() == View.GONE && !ocupado3) {
                            txtCiego.setVisibility(View.VISIBLE);
                            txtCiego.setBackgroundDrawable(bgCiego);
                        }
                        break;
                }
                return true;
            }
        });
        txtPacoMalo.setOnDragListener(new View.OnDragListener() {//habilitamos la escucha del evento onDrag para el target
            @Override
            public boolean onDrag(View v, DragEvent event) {
                //v es txtPacoMalo
                view = (View) event.getLocalState();//obtenemos el TextView que llamó al método startDrag
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED://no se ocupa
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED://en caso de que el textView que se este arrastrando entre dentro del target
                        v.setBackgroundResource(R.drawable.borde_2);//establecemos el borde punteado
                        break;
                    case DragEvent.ACTION_DRAG_EXITED://en caso de que el textView que se este arrastrando salga del target
                        v.setBackgroundDrawable(bgPaco);//establecemos su background original
                        break;
                    case DragEvent.ACTION_DROP://en caso de que el textView que se este arrastrando se solto sobre el target
                        ocupado1 = true;//indicamos que esta ocupado
                        if (event.getClipData().getItemAt(0).getText().equals("antagonico")) {//si el texto del clip data que se recibe es igual a antagonico
                            aciertos++;//aumentamos los aciertos en 1
                        }
                        if (view != null) {//si el textView es distinto de null
                            view.setLayoutParams(paramsOriginalesPaco);//establecemos los parametros con los del target
                            view.setVisibility(View.VISIBLE);//lo volvemos visible
                        }
                        v.setVisibility(View.GONE);//ocultamos el target
                        break;
                    case DragEvent.ACTION_DRAG_ENDED://en caso de que el textView del arrastre se solto antes de llegar al target
                        if (view != null) {
                            view.post(new Runnable() {//es necesario hacer cualquier cambio por medio del metodo post para que no lance una excepcion, post accede al ui thread
                                @Override
                                public void run() {
                                    view.setVisibility(View.VISIBLE);//volvemos visible el textView
                                }
                            });
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        txtPerro.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                view = (View) event.getLocalState();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundResource(R.drawable.borde_2);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundDrawable(bgPerro);
                        break;
                    case DragEvent.ACTION_DROP:
                        ocupado2 = true;
                        if (event.getClipData().getItemAt(0).getText().equals("principal")) {
                            aciertos++;
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsOriginalesPerro);
                            view.setVisibility(View.VISIBLE);
                        }
                        v.setVisibility(View.GONE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if (view != null) {
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    view.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        txtCiego.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                view = (View) event.getLocalState();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundResource(R.drawable.borde_2);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundDrawable(bgCiego);
                        break;
                    case DragEvent.ACTION_DROP:
                        ocupado3 = true;
                        if (event.getClipData().getItemAt(0).getText().equals("secundario")) {
                            aciertos++;
                        }
                        if (view != null) {
                            view.setLayoutParams(paramsOriginalesCiego);
                            view.setVisibility(View.VISIBLE);
                        }
                        v.setVisibility(View.GONE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if (view != null) {
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    view.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        child = getLayoutInflater().inflate(R.layout.card_resultados, marco, false);//inflamos el card (ventana flotante) desde un recurso xml, recibe el recurso xml, el viewGroup padre.
        final RelativeLayout cardContenedor = (RelativeLayout)child.findViewById(R.id.cardContenedor);
        for (int i = 0; i<=cardContenedor.getChildCount(); i++){
            View v = cardContenedor.getChildAt(i);
            if(v instanceof TextView){
                ((TextView) v).setTypeface(font);
            }
            if(v instanceof Button){
                ((Button) v).setTypeface(font);
            }
        }
        marco.addView(child);//añadimos al marco (viewGroup padre) el card inflado
        child.setVisibility(View.GONE);//ocultamos el card
        calificar.setOnClickListener(new View.OnClickListener() {//habilitamos la escucha del evento onClick para el boton calificar
            @Override
            public void onClick(View v) {
                if(intentos<=3){//si intentos es menor o igual a 3
                    intentos++;//incrementamos los intentos en 1
                }
                if(intentos > 3){//si intentos es mayor a 3
                    Toast t = Toast.makeText(context,"Se te agotaron los intentos",Toast.LENGTH_SHORT);//creamos un toast, que le avise al usuario que se le agotaron los intentos
                    t.setGravity(Gravity.CENTER_VERTICAL,0,0);//establecemos la locacion en donde aparecera el toast, en este caso en el centro
                    t.show();//mostramos el toast
                }
                if(intentos<=3){//si intentos es menos o igual que 3
                    TextView txtFallastes, txtloLograstes, txtlasRespuestas;
                    //referencias a los textView dentro de la ventan flotante
                    txtFallastes = (TextView)child.findViewById(R.id.fallastes);
                    txtloLograstes = (TextView)child.findViewById(R.id.felicidades);
                    txtlasRespuestas = (TextView)child.findViewById(R.id.lasRespuestas);
                    TextView txtTitleRespuestas = (TextView)child.findViewById(R.id.titleRespuestas);
                    TextView txtIntentos = (TextView)child.findViewById(R.id.cantidadIntentos);
                    TextView txtAciertos = (TextView)child.findViewById(R.id.respuestas_correctas);
                    if(intentos != 3){//si intenos es distinto de 3
                        txtlasRespuestas.setVisibility(View.GONE);//ocultamos el textview que contiene las respuestas correctas
                        txtTitleRespuestas.setVisibility(View.GONE);//ocultamos el textview del titulo de las respuestas
                    }else{
                        txtlasRespuestas.setVisibility(View.VISIBLE);//hacemos visibles las respuestas
                        txtTitleRespuestas.setVisibility(View.VISIBLE);//hacemos visibles el titulo
                        txtlasRespuestas.setText(
                                "Personaje antagónico: Paco: Niño cruel.\n"+
                                        "Personaje principal: El perro: Lazarillo de 4 patas.\n"+
                                        "Personaje secundario: El ciego dueño del perro.");//establecemos las respuestas
                    }
                    if(aciertos == 3){//si acierto es igual a 3
                        txtFallastes.setVisibility(View.GONE);//ocultamos el texto de fallastes
                        txtloLograstes.setVisibility(View.VISIBLE);//hacemos visible el texto lo lograstes
                    }else{
                        txtloLograstes.setVisibility(View.GONE);//ocultamos el texto lo lograstes
                        txtFallastes.setVisibility(View.VISIBLE);//hacemos visible el texto fallastes
                    }
                    txtIntentos.setText("Intento " + String.valueOf(intentos) + "/" + String.valueOf(3));//establecemos el texto de los intentos, para indicar cuantos intentos lleva
                    txtAciertos.setText("Acertastes " + String.valueOf(aciertos) + "/" + String.valueOf(3));//establecemos la cantidad de aciertos
                    Button volver =  (Button)child.findViewById(R.id.card_btn_volver);//boton volver del card
                    volver.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Animation salida = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_out);//cargamos la animacion desde un recurso xml
                            child.startAnimation(salida);//iniciamos la animacion de salidad con la animacion que cargamos
                            child.setVisibility(View.GONE);//ocultamos el card
                            //los textview arrastrados vuelven a su posición original.
                            txtPersonajeAntagonico.setLayoutParams(paramsOriginaleAntagonico);
                            txtPrincipal.setLayoutParams(paramsOriginalesPrincipal);
                            txtSecundario.setLayoutParams(paramsOriginalesSecundario);
                            //volvemos visibles los textview targets y establecemos su background original
                            txtPacoMalo.setVisibility(View.VISIBLE);
                            txtPerro.setVisibility(View.VISIBLE);
                            txtCiego.setVisibility(View.VISIBLE);
                            txtPacoMalo.setBackgroundDrawable(bgPaco);
                            txtPerro.setBackgroundDrawable(bgPerro);
                            txtCiego.setBackgroundDrawable(bgCiego);
                            //indicamos quelos target se desocuparon
                            ocupado1 = false;
                            ocupado2 = false;
                            ocupado3 = false;
                            aciertos = 0;//reestablecmos los aciertos
                        }
                    });
                    child.setVisibility(View.VISIBLE);//volvemos visible el card
                    Animation entrada;
                    entrada = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_in);//cargamos la animacion de entrada desde un recurso xml
                    child.startAnimation(entrada);//inicamos la animacion
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

        if(id == R.id.alEjercicio){
            startActivity(new Intent().setClass(getApplicationContext(), MenuEjercicios.class));
        }
        if(id == R.id.alPrincipal) {
            startActivity(new Intent().setClass(getApplicationContext(), MainMenu.class));
        }
        if(id == R.id.salir){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
