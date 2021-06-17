package com.example.tabalumno;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tabalumno.dao.UsuarioDao;
import com.example.tabalumno.model.Usuario;
import com.example.tabalumno.util.Mensajes;

public class UsuarioActivity extends AppCompatActivity {
    private EditText edtNombre, edtUser, edtClave;
    private UsuarioDao usuarioDAO;
    private Usuario usuario;
    private int iduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        usuarioDAO = new UsuarioDao(this);

        edtNombre = (EditText) findViewById(R.id.txtNombre);
        edtUser = (EditText) findViewById(R.id.txtUser);
        edtClave = (EditText) findViewById(R.id.txtClave);

        iduser = getIntent().getIntExtra("USUARIO_ID",0);
        if(iduser > 0){
            Usuario model = usuarioDAO.buscarUsuarioPorID(iduser);
            edtNombre.setText(model.getNombres());
            edtUser.setText(model.getUser());
            edtClave.setText(model.getClave());
            setTitle("Actualizar Usuario");
        }
    }
    @Override
    protected void onDestroy() {
        usuarioDAO.cerrarDB();
        super.onDestroy();
    }
    private void registrar(){
        boolean validar = true;
        String nombre = edtNombre.getText().toString();
        String login = edtUser.getText().toString();
        String clave = edtClave.getText().toString();
        if(nombre == null || nombre.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.Login_validaNombre));
        }
        if(login == null || login.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.Login_validaUsuario));
        }
        if(clave == null || clave.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.Login_validaClave));
        }
        if(validar){
            usuario = new Usuario();
            usuario.setNombres(nombre);
            usuario.setUser(login);
            usuario.setClave(clave);
            if(iduser > 0){
                usuario.set_id(iduser);
            }
            long resultado = usuarioDAO.modificarUsuario(usuario);
            if(resultado != -1){
                if(iduser > 0) {
                    Mensajes.Msg(this, getString(R.string.msg_user_modificado));
                    startActivity(new Intent(this, ListUsuarioActivity.class));
                }else{
                    Mensajes.Msg(this, getString(R.string.msg_user_guardado));
                    startActivity(new Intent(this, ListUsuarioActivity.class));
                }
                finish();
                //startActivity(new Intent(this, MainActivity.class));
            }else{
                Mensajes.Msg(this, getString(R.string.msg_user_error));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_menu_guardar:
                this.registrar();
                break;
            case R.id.action_menu_sair:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
