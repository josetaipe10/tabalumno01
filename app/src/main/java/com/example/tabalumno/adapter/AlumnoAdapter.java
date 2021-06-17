package com.example.tabalumno.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tabalumno.R;
import com.example.tabalumno.model.Alumno;

import java.util.List;

public class AlumnoAdapter extends BaseAdapter {
    private Context context;
    private List<Alumno> lista;
    public AlumnoAdapter(Context context, List<Alumno> model){
        this.context = context;
        this.lista = model;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Alumno alumno = lista.get(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.alumnos, null);
        }
        TextView txtNom = (TextView) view.findViewById(R.id.usuario_lista_nombre);
        txtNom.setText((i+1)+".- "+alumno.getNombres());

        return view;
    }
}
