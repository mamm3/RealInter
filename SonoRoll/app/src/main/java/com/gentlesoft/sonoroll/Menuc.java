package com.gentlesoft.sonoroll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menuc extends AppCompatActivity implements View.OnClickListener {

    int idCarro;
    int idUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuc);


        findViewById(R.id.laySushisMenuC).setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        idCarro = bundle.getInt("carritoId");
        idUsuario = bundle.getInt("id");
    }

    public void onClick(final View v){
        switch (v.getId()){
            case R.id.laySushisMenuC: {
                Intent intent = new Intent(this, Sushi.class);
                intent.putExtra("carritoId", idCarro);
                intent.putExtra("id", idUsuario);
                startActivity(intent);
                break;
            }
            default:{
                break;
            }
        }
    }
}
