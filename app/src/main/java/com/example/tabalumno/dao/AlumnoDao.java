package com.example.tabalumno.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tabalumno.config.DBHelper;
import com.example.tabalumno.model.Alumno;

import java.util.ArrayList;
import java.util.List;


public class AlumnoDao {
    private DBHelper helper;
    private SQLiteDatabase database;
    public AlumnoDao(Context context){
        helper = new DBHelper(context);
    }
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }



    private Alumno crearAlumno(Cursor cursor){
        Alumno alumno = new Alumno(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Alumnos._ID)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Alumnos.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Alumnos.TELEFONO)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Alumnos.DNI)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Alumnos.CORREO))

        );
        return alumno;
    }

    //Agreegar la lista alumnos
    public List<Alumno> listarAlumnos(){
        Cursor cursor = getDatabase().query(DBHelper.Alumnos.TABLE,DBHelper.Alumnos.COLUMNAS, null, null, null, null, null);
        List<Alumno> lista = new ArrayList<Alumno>();
        while(cursor.moveToNext()){
            Alumno modelo = crearAlumno(cursor);
            lista.add(modelo);
        }
        return lista;
    }


    public long modificarAlumno(Alumno alumno){
        ContentValues values = new ContentValues();
        values.put(DBHelper.Alumnos.NOMBRE, alumno.getNombres());
        values.put(DBHelper.Alumnos.TELEFONO, alumno.getTelefono());
        values.put(DBHelper.Alumnos.DNI, alumno.getDni());
        values.put(DBHelper.Alumnos.CORREO, alumno.getCorreo());


        if(alumno.get_id() != null){
            return database.update(DBHelper.Alumnos.TABLE, values,
                    "_id = ?", new String[]{alumno.get_id().toString()});
        }
        return getDatabase().insert(DBHelper.Alumnos.TABLE,null,values);
    }





    public boolean eliminarAlumno(int id){
        return getDatabase().delete(DBHelper.Alumnos.TABLE,"_id = ?", new String[]{Integer.toString(id)}) > 0;
    }



    public Alumno buscarAlumnoPorID(int id){
        Cursor cursor = getDatabase().query(DBHelper.Alumnos.TABLE,
                DBHelper.Alumnos.COLUMNAS, "_id = ?", new String[]{ Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Alumno model = crearAlumno(cursor);
            cursor.close();
            return model;
        }
        return null;
    }



   // public boolean logueoUser(String user, String pass){
   //     Cursor cursor = getDatabase().query(DBHelper.Usuarios.TABLE,null,
   //             "LOGIN = ? AND CLAVE = ?", new String[]{user, pass}, null, null,null);
   //     if(cursor.moveToNext()){
   //         return true;
   //     }
   //     return false;
   // }
        public void cerrarDB(){
         helper.close();
        database = null;
    }
}
