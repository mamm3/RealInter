package com.gentlesoft.sonoroll;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gentlesoft.sonoroll.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DisplayMetrics metrics = new DisplayMetrics();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObjectAnimator animation;
        ObjectAnimator animation2;
        ObjectAnimator animationLa;
        ObjectAnimator animationFL;
        AnimatorSet aS;
        int tamanio = metrics.heightPixels;

        ImageView imgSon = findViewById(R.id.imagenSonora);
        imgSon.setY(tamanio);
        imgSon.setAlpha(0.0f);

        Drawable originalDrawable = getResources().getDrawable(R.drawable.sonora);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        ImageView imageView = (ImageView) findViewById(R.id.imagenSonora);

        imageView.setImageDrawable(roundedDrawable);

        animation = ObjectAnimator.ofFloat(imgSon, "y", 150f);
        animation.setDuration(2000);
        animation2 = ObjectAnimator.ofFloat(imgSon, View.ALPHA, 0.0f, 1.0f);
        animation2.setDuration(2000);
        animationLa = ObjectAnimator.ofFloat(findViewById(R.id.lLET), View.ALPHA, 0.0f, 1.0f);
        animationLa.setDuration(2000);
        animationFL = ObjectAnimator.ofFloat(findViewById(R.id.fLRegistro), View.ALPHA, 0.0f, 1.0f);
        animationFL.setDuration(2000);

        aS = new AnimatorSet();
        aS.playTogether(animation, animation2, animationLa, animationFL);
        aS.start();

        ((Button)findViewById(R.id.btnRegistro)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnInicioS)).setOnClickListener(this);

    }

    public void onClick(final View v){
        switch (v.getId()){
            case R.id.btnRegistro:{
                try{
                    Intent intent = new Intent(this, Registro.class);
                    startActivity(intent);
                    break;

                }catch (Exception e){
                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
            case R.id.btnInicioS:{
                    final Intent intent = new Intent(this, SeleccionSucursal.class);
                    String url = "http://"+findViewById(R.id.layoutPrinMain).getTag().toString()+"/sonroll/usuarioWS.php/loginWithCart/" + ((TextView)findViewById(R.id.txtContraIS)).getText().toString() + "/" + ((TextView)findViewById(R.id.txtCorreoIS)).getText().toString();
                    RequestQueue queue = Volley.newRequestQueue(this);
                    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                            (url, new Response.Listener<JSONArray>() {

                                @Override
                                public void onResponse(JSONArray response) {
                                    try{
                                        JSONObject jsonObject = response.getJSONObject(0);
                                        intent.putExtra("nombre", jsonObject.getString("nombre") + " " + jsonObject.getString("apellidopaterno") + " " + jsonObject.getString("apellidomaterno"));
                                        intent.putExtra("id", jsonObject.getInt("id"));
                                        intent.putExtra("carritoId", jsonObject.getInt("idCarrito"));
                                        startActivity(intent);
                                    }catch (Exception e){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        String erroresCompleto = "Alguno de los datos es erroneo";
                                        builder.setMessage(erroresCompleto);
                                        builder.setTitle("Errores");
                                        builder.show();

                                    }
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    String erroresCompleto = "No se ha podido encontrar el usuario";
                                    builder.setMessage(erroresCompleto);
                                    builder.setTitle("Errores");
                                    builder.show();
                                }
                            });

                    // Access the RequestQueue through your singleton class.
                    queue.add(jsonObjectRequest);
                break;
            }
            default:{
                break;
            }

        }
    }


}
