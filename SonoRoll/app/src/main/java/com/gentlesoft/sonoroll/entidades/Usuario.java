package com.gentlesoft.sonoroll.entidades;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.gentlesoft.sonoroll.database.SonoraDB;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Usuario {

    Context context;
    public Usuario(Context context) {
        this.context = context;
    }


    private int id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String correo;
    private String contrasena;

    public Usuario() {

    }


    public void guardar(int id, String nombre, String apellidos, String telefono, String correo, String contrasena) {

        try {
            SonoraDB db = new SonoraDB(context, "SonoraDB", null, 1);

            SQLiteDatabase baseDatos = db.getWritableDatabase();
            baseDatos.execSQL("INSERT INTO Usuario(id, nombre, apellidos,telefono,correo, contrasena) VALUES('" + id +"','" + nombre + "','"+ apellidos + "','"+ telefono +"','"+ correo +"','"+contrasena+"')");
            baseDatos.close();


        } catch (Exception ex) {
            Log.e("Basedatos","Error al insertar registros");
        }
    }
        public List<Usuario> obtenerTodos(){
        List<Usuario> usuarios = new ArrayList<>();
        SonoraDB db = new SonoraDB(context,"SonoraDB",null, 1);
            SQLiteDatabase baseDatos = db.getReadableDatabase();
        Cursor cursor = baseDatos.rawQuery("SELECT id,nombre, apellidos,telefono,correo, contrasena  FROM Usuario",null);
        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setApellidos(cursor.getString(2));
            usuario.setTelefono(cursor.getString(3));
            usuario.setCorreo(cursor.getString(4));
            usuario.setContrasena(cursor.getString(5));

            usuarios.add(usuario);
        }
        return usuarios;
        }

        public Usuario obtenerUno(String correo, String contrasenia, final Context context){
            Usuario usuario = null;
            SonoraDB db = new SonoraDB(context,"SonoraDB",null, 1);
            SQLiteDatabase baseDatos = db.getReadableDatabase();
            try{
                Cursor cursor = baseDatos.rawQuery("SELECT id, nombre, apellidos, telefono FROM Usuario WHERE correo = " + ""+correo+"AND contrasenia = " + contrasenia, null);
                while (cursor.moveToNext()){
                    usuario = new Usuario();
                    usuario.setId(cursor.getInt(0));
                    usuario.setNombre(cursor.getString(1));
                    usuario.setApellidos(cursor.getString(2));
                    usuario.setTelefono(cursor.getString(3));
                }

            }catch (Exception e){

            }
            return usuario;
        }

        public String getNombre () {
            return nombre;
        }

        public void setNombre (String nombre){
            this.nombre = nombre;
        }

        public String getTelefono () {
            return telefono;
        }

        public void setTelefono (String telefono){
            this.telefono = telefono;
        }

        public String getCorreo () {
            return correo;
        }

        public void setCorreo (String correo){
            this.correo = correo;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public String getApellidos(){
        return this.apellidos;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public String getContrasena(){
        return this.contrasena;
    }
}

