package com.gentlesoft.sonoroll;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.widget.TableLayout.LayoutParams.*;

public class Carrito extends AppCompatActivity implements View.OnClickListener{
    int idCarrito;
    int id;
    String nombre;
    final List<Button> botones = new ArrayList<>();
    final double[] total = {0.0};
    LinearLayout layoutPrincipal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        idCarrito = bundle.getInt("carritoId");
        nombre = bundle.getString("nombre");
        findViewById(R.id.btnEnviarCarrito).setOnClickListener(this);
        layoutPrincipal = findViewById(R.id.layoutComidadCarrito);

        traerComida();


    }

    public void onClick(final View v){
        switch (v.getId()){
            case R.id.btnEnviarCarrito:{
                String url = "http://"+findViewById(R.id.layoutPrinCarrito).getTag().toString()+"/sonroll/carroWS.php/activar/" + idCarrito + "/1";
                RequestQueue queue = Volley.newRequestQueue(this);
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                        (url, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                try{
                                    ((Button) findViewById(R.id.btnEnviarCarrito)).setClickable(false);
                                    ((TextView) findViewById(R.id.txtCarrito)).setText("Tu orden ha sido recibida");
                                    ((TextView) findViewById(R.id.txtCarrito)).setTextColor(Color.BLACK);
                                    ((Button) findViewById(R.id.btnEnviarCarrito)).setClickable(false);
                                }catch (Exception e){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Carrito.this);
                                    String erroresCompleto = "Algo malo paso";
                                    builder.setMessage(erroresCompleto);
                                    builder.setTitle("Errores");
                                    builder.show();

                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                AlertDialog.Builder builder = new AlertDialog.Builder(Carrito.this);
                                String erroresCompleto = error.toString();
                                builder.setMessage(erroresCompleto);
                                builder.setTitle("Errores");
                                builder.show();
                            }
                        });

                // Access the RequestQueue through your singleton class.
                queue.add(jsonObjectRequest);
                for (Button buton:botones){
                    buton.setClickable(false);
                    buton.setVisibility(View.INVISIBLE);
                }
                break;
            }
            default:{
                break;
            }
        }
    }


    public void traerComida(){
        String url = "http://"+ findViewById(R.id.layoutPrinCarrito).getTag().toString() + "/sonroll/comidaWS.php/searchByUserIdWithCart/" + id;
        layoutPrincipal.removeAllViews();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (url, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            LinearLayout layoutnuevo;
                            total[0] = 0.0;
                            if (response.isNull(0)){
                                final Intent intent = new Intent(Carrito.this, SeleccionSucursal.class);
                                intent.putExtra("id", id);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("carritoId", idCarrito);

                                AlertDialog.Builder builder = new AlertDialog.Builder(Carrito.this);
                                String texto = "No tiene ningun producto en su lista";
                                builder.setMessage(texto);
                                builder.setTitle("Inormación");
                                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            for (int i = 0; i < response.length(); i++){
                                JSONObject jsonObject = response.getJSONObject(i);
                                layoutnuevo = new LinearLayout(Carrito.this);
                                LayoutParams lapa = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                                lapa.setMargins(0,0,0, 10);
                                layoutnuevo.setLayoutParams(lapa);
                                layoutnuevo.setOrientation(LinearLayout.VERTICAL);
                                layoutnuevo.setBackgroundResource(R.drawable.borde_circular);
                                layoutnuevo.setPadding(10,10,10,10);

                                TextView texto1 = new TextView(Carrito.this);
                                texto1.setLayoutParams(new LinearLayoutCompat.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                                texto1.setTextSize(24);
                                texto1.setText(jsonObject.getString("nombre"));
                                layoutnuevo.addView(texto1);

                                TextView texto2 = new TextView(Carrito.this);
                                texto2.setLayoutParams(new LinearLayoutCompat.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                                texto2.setTextSize(24);
                                texto2.setText(jsonObject.getString("descripcion"));
                                layoutnuevo.addView(texto2);

                                TextView texto3 = new TextView(Carrito.this);
                                texto3.setLayoutParams(new LinearLayoutCompat.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                                texto3.setTextSize(24);
                                texto3.setText("$ " + jsonObject.getDouble("precio"));
                                layoutnuevo.addView(texto3);
                                FrameLayout layFrameNuevo = new FrameLayout(Carrito.this);
                                LayoutParams lapar = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                                layFrameNuevo.setLayoutParams(lapar);
                                Button nuevoButton = new Button(Carrito.this);
                                LayoutParams lapara = new LayoutParams(360, LayoutParams.WRAP_CONTENT);
                                nuevoButton.setLayoutParams(lapara);
                                nuevoButton.setText("Eliminar del carrito");
                                nuevoButton.setBackgroundResource(R.drawable.borde_circular);
                                nuevoButton.setTag(jsonObject.getInt("id"));
                                nuevoButton.setId(jsonObject.getInt("id"));

                                layFrameNuevo.addView(nuevoButton);
                                layoutnuevo.addView(layFrameNuevo);
                                botones.add(nuevoButton);

                                total[0] += jsonObject.getDouble("precio");
                                ((TextView)findViewById(R.id.txtTotalCarrito)).setText("Total: $" + total[0]);

                                layoutPrincipal.addView(layoutnuevo);
                            }
                            JSONObject jsonObject = response.getJSONObject(0);

                            for (final Button button:botones){
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String url = "http://"+findViewById(R.id.layoutPrinCarrito).getTag().toString()+"/sonroll/comidaWS.php/delete/" + idCarrito + "/" + button.getTag().toString();
                                        RequestQueue queue = Volley.newRequestQueue(Carrito.this);
                                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        Toast.makeText(Carrito.this, "Se ha eliminado su seleccion", Toast.LENGTH_SHORT).show();
                                                        traerComida();
                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        });

                                        // Access the RequestQueue through your singleton class.
                                        queue.add(stringRequest);
                                    }
                                });
                            }
                            if (jsonObject.getInt("activo") == 1){
                                ((TextView) findViewById(R.id.txtCarrito)).setText("Tu orden ha sido recibida");
                                ((TextView) findViewById(R.id.txtCarrito)).setTextColor(Color.BLACK);
                                ((Button) findViewById(R.id.btnEnviarCarrito)).setClickable(false);
                                ((Button) findViewById(R.id.btnEnviarCarrito)).setVisibility(View.VISIBLE);

                                for (Button buton:botones){
                                    buton.setClickable(false);
                                    buton.setVisibility(View.INVISIBLE);
                                }
                            }else{
                                ((TextView) findViewById(R.id.txtCarrito)).setText("Aun no envias tu orden");
                                ((TextView) findViewById(R.id.txtCarrito)).setTextColor(Color.RED);
                                ((Button) findViewById(R.id.btnEnviarCarrito)).setClickable(true);
                                ((Button) findViewById(R.id.btnEnviarCarrito)).setVisibility(View.VISIBLE);
                            }
                        }catch (Exception e){

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        ((TextView) findViewById(R.id.txtCarrito)).setText(error.toString());

                    }
                });

        // Access the RequestQueue through your singleton class.

        queue.add(jsonObjectRequest);
    }

}
