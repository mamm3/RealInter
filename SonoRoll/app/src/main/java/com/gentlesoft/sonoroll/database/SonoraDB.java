package com.gentlesoft.sonoroll.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SonoraDB extends SQLiteOpenHelper {

    public SonoraDB(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE Usuario (id INTEGER PRIMARY KEY, nombre TEXT, apellidos TEXT, telefono TEXT, correo TEXT, contrasena TEXT)");
        } catch (Exception ex) {
            Log.e("Database", "Error al generar la tabla de Usuario");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

