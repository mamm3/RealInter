package com.gentlesoft.sonoroll;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class Sushi extends AppCompatActivity implements View.OnClickListener {

    int idCarrito;
    int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         Bundle bundle = getIntent().getExtras();
         idCarrito = bundle.getInt("carritoId");
         idUsuario = bundle.getInt("id");

        setContentView(R.layout.activity_sushi);
        findViewById(R.id.layCalifornia).setOnClickListener(this);
        findViewById(R.id.btnCalifornia).setOnClickListener(this);
        findViewById(R.id.layVaquero).setOnClickListener(this);
        findViewById(R.id.btnVaquero).setOnClickListener(this);
        findViewById(R.id.layPhiladelphia).setOnClickListener(this);
        findViewById(R.id.btnPhiladelphia).setOnClickListener(this);
        findViewById(R.id.layCielo).setOnClickListener(this);
        findViewById(R.id.btnCielo).setOnClickListener(this);

        String url = "http://"+ findViewById(R.id.layoutSushis ).getTag().toString() + "/sonroll/comidaWS.php/searchByUserIdWithCart/" + idUsuario;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest stringRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray rsponse) {
                        // Display the first 500 characters of the response string.
                        try{
                            JSONObject jsonObject = rsponse.getJSONObject(0);
                            if (jsonObject.getInt("activo") == 1){
                                ((LinearLayout)findViewById(R.id.layCalifornia)).setClickable(false);
                                ((Button)findViewById(R.id.btnCalifornia)).setVisibility(View.INVISIBLE);
                                ((LinearLayout)findViewById(R.id.layVaquero)).setClickable(false);
                                ((Button)findViewById(R.id.btnVaquero)).setVisibility(View.INVISIBLE);
                                ((LinearLayout)findViewById(R.id.layPhiladelphia)).setClickable(false);
                                ((Button)findViewById(R.id.btnPhiladelphia)).setVisibility(View.INVISIBLE);
                                ((LinearLayout)findViewById(R.id.layCielo)).setClickable(false);
                                ((Button)findViewById(R.id.btnCielo)).setVisibility(View.INVISIBLE);
                            }

                        }catch (Exception e){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Access the RequestQueue through your singleton class.
        queue.add(stringRequest);
    }

    public void onClick(final View v){
        switch (v.getId()){
            case R.id.layCalifornia:{
                String url = "http://"+findViewById(R.id.layoutSushis).getTag().toString()+"/sonroll/comidaWS.php/addComidaToCart/" + idCarrito + "/" + 1;
                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Toast.makeText(Sushi.this, "Se ha hagregado California al carrito", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                // Access the RequestQueue through your singleton class.
                queue.add(stringRequest);
                break;
            }

            case R.id.btnCalifornia:{
                String url = "http://"+findViewById(R.id.layoutSushis).getTag().toString()+"/sonroll/comidaWS.php/addComidaToCart/" + idCarrito + "/" + 1;
                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Toast.makeText(Sushi.this, "Se ha hagregado California al carrito", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                // Access the RequestQueue through your singleton class.
                queue.add(stringRequest);
                break;
            }

            case R.id.layVaquero:{
                break;
            }

            case R.id.btnVaquero:{
                break;
            }

            case R.id.layPhiladelphia:{
                break;
            }

            case R.id.btnPhiladelphia:{
                break;
            }

            case R.id.layCielo:{
                break;
            }

            case R.id.btnCielo:{
                break;
            }


            default:{
                break;
            }
        }
    }
}
