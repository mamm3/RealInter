package com.gentlesoft.sonoroll;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SeleccionSucursal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_sucursal);
        Bundle b = getIntent().getExtras();
        String nombre = b.getString("nombre");

        ((TextView)findViewById(R.id.txtNombreSelec)).setText("Bien venido : " + nombre + "\nSleccione la sucursal en la que desea ordenar:");


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
}
