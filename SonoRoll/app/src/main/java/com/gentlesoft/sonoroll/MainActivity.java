package com.gentlesoft.sonoroll;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
            default:{
                break;
            }

        }
    }
}
