package com.gentlesoft.sonoroll;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SeleccionSucursal extends AppCompatActivity implements View.OnClickListener{
    int id;
    int idCarrito;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_sucursal);
        Bundle b = getIntent().getExtras();

        findViewById(R.id.btnCarritoSelecSuc).setOnClickListener(this);
        findViewById(R.id.layGuaymasSerdan).setOnClickListener(this);
        findViewById(R.id.layGuaymasCobacha).setOnClickListener(this);
        findViewById(R.id.layEmpalme).setOnClickListener(this);
        findViewById(R.id.laySanCarlos).setOnClickListener(this);
        findViewById(R.id.btnGuaymasSerdan).setOnClickListener(this);
        findViewById(R.id.btnGuaymasCobacha).setOnClickListener(this);
        findViewById(R.id.btnGuaymasEmpalme).setOnClickListener(this);
        findViewById(R.id.btnGuaymasSanCarlos).setOnClickListener(this);

        nombre = b.getString("nombre");
        id = b.getInt("id");
        idCarrito = b.getInt("carritoId");

        ((TextView)findViewById(R.id.txtNombreSelec)).setText("Bienvenido : " + nombre + "\nSleccione la sucursal en la que desea ordenar:");


        Drawable originalDrawable = getResources().getDrawable(R.drawable.sonora_serdan);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        ImageView imageView = (ImageView) findViewById(R.id.imgSerdan);

        imageView.setImageDrawable(roundedDrawable);

        originalDrawable = getResources().getDrawable(R.drawable.sonora_cobacha);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        imageView = (ImageView) findViewById(R.id.imgCobacha);

        imageView.setImageDrawable(roundedDrawable);

        originalDrawable = getResources().getDrawable(R.drawable.sonora_empalme);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        imageView = (ImageView) findViewById(R.id.imgEmpalme);

        imageView.setImageDrawable(roundedDrawable);

        originalDrawable = getResources().getDrawable(R.drawable.sonora_sc);
        originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        imageView = (ImageView) findViewById(R.id.imgSanCarlos);

        imageView.setImageDrawable(roundedDrawable);
    }
    @Override
    public void onClick(final View v){
        switch (v.getId()){
            case R.id.btnCarritoSelecSuc:{
                Intent intent = new Intent(this, Carrito.class);
                intent.putExtra("id", id);
                intent.putExtra("nombre", nombre);
                intent.putExtra("carritoId", idCarrito);
                startActivity(intent);
                break;
            }

            case R.id.layGuaymasSerdan:{
                Intent intent = new Intent(this, Menuc.class);
                intent.putExtra("id", id);
                intent.putExtra("carritoId", idCarrito);
                startActivity(intent);
                break;
            }

            case R.id.layGuaymasCobacha:{
                Intent intent = new Intent(this, Menuf.class);
                intent.putExtra("id", id);
                intent.putExtra("carritoId", idCarrito);
                startActivity(intent);
                break;
            }
            case R.id.laySanCarlos:{
                Intent intent = new Intent(this, Menuc.class);
                intent.putExtra("id", id);
                intent.putExtra("carritoId", idCarrito);
                startActivity(intent);
                break;
            }
            case R.id.layEmpalme:{
                Intent intent = new Intent(this, Menuf.class);
                intent.putExtra("id", id);
                intent.putExtra("carritoId", idCarrito);
                startActivity(intent);
                break;
            }

            default:{
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String texto = "¿Seguro que desea cerrar sesión?";
        builder.setMessage(texto);
        builder.setTitle("Cerrar sesión");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(SeleccionSucursal.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

}
