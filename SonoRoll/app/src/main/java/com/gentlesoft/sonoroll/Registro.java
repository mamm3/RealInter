package com.gentlesoft.sonoroll;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gentlesoft.sonoroll.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity implements View.OnClickListener{
    Usuario usuario = new Usuario();
    List<EditText> cajasTexto = new ArrayList<>();
    List<String> errores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        for (int i = 0; i < ((LinearLayout)findViewById(R.id.layoutLinear)).getChildCount(); i++){
            cajasTexto.add((EditText)((LinearLayout)findViewById(R.id.layoutLinear)).getChildAt(i));
        }


        findViewById(R.id.btnRegistro).setOnClickListener(this);

    }

    public void onClick(final View v){

        switch (v.getId()){
            case R.id.btnRegistro:{
                for (EditText texto:cajasTexto){
                    if (texto.getText().toString().isEmpty()){
                        errores.add("La caja " + texto.getHint().toString() + " esta vacia, favor de llenarla");
                    }
                }
                if(((EditText)findViewById(R.id.txtTelefono)).getText().toString().length() != 10){
                    errores.add("La caja de telefono debe tener por lo menos 10 caracteres de longitud");
                }
                if (!errores.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    String erroresCompleto = "";
                    for (String error: errores){
                        if (!error.isEmpty())
                            erroresCompleto = erroresCompleto + "\n" +error;
                        else erroresCompleto = error;
                    }
                    builder.setMessage(erroresCompleto);
                    builder.setTitle("Errores");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    errores.clear();
                }else{
                    final Intent intent = new Intent(this, SeleccionSucursal.class);
                    intent.putExtra("nombre", ((TextView) findViewById(R.id.txtNombres)).getText().toString() + " " + ((TextView) findViewById(R.id.txtApellidos)).getText().toString());
                    String nombre = ((TextView) findViewById(R.id.txtNombres)).getText().toString();
                    String apePat = ((TextView) findViewById(R.id.txtApellidos)).getText().toString().replace(" ", "_").split("_")[0];
                    String apeMat = ((TextView) findViewById(R.id.txtApellidos)).getText().toString().replace(" ", "_").split("_")[1];
                    String telefono = ((TextView) findViewById(R.id.txtTelefono)).getText().toString();
                    String email = ((TextView) findViewById(R.id.txtCorreo)).getText().toString();
                    String psw = ((TextView) findViewById(R.id.txtPsw)).getText().toString();
                    String url = "http://"+ findViewById(R.id.layPrinReg).getTag().toString() +"/sonroll/usuarioWS.php/withCart/"+ nombre +"/" +
                            apePat+"/" +
                            apeMat+"/" +
                            telefono+"/" +
                            psw+"/" +
                            email+"";
                    RequestQueue queue = Volley.newRequestQueue(this);
                    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                            (url, new Response.Listener<JSONArray>() {

                                @Override
                                public void onResponse(JSONArray response) {
                                    try{
                                        JSONObject jsonObject = response.getJSONObject(0);
                                        int id = jsonObject.getInt("id");
                                        intent.putExtra("id", id);
                                        intent.putExtra("carritoId", jsonObject.getInt("idCarrito"));
                                        startActivity(intent);
                                    }catch (Exception e){
                                    }
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                }
                            });

                    // Access the RequestQueue through your singleton class.
                    queue.add(jsonObjectRequest);


                }
                break;
            }
            default:{
                break;
            }
        }

    }
}
