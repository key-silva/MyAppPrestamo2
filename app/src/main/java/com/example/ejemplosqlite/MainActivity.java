package com.example.ejemplosqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ejemplosqlite.entidades.Usuario;
import com.example.ejemplosqlite.utilidades.utilidades;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuario;
    conexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn=new conexionSQLiteHelper(getApplicationContext(),"bd_usuario",null,1);
        listViewPersonas=(ListView)findViewById(R.id.listViewPersonas);
        consultarListaPersonas();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adapter);
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario usuario=null;
        listaUsuario=new ArrayList <Usuario>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ utilidades.tabla_usuario,null);

        while ( cursor.moveToNext()){
            usuario=new Usuario();
            usuario.setNombre(cursor.getString(0));
            usuario.setApellido(cursor.getString(1));
            usuario.setSexo(cursor.getString(2));
            usuario.setTelefono(cursor.getString(3));
            usuario.setCedula(cursor.getString(4));
            usuario.setOcupacion(cursor.getString(5));
            usuario.setDireccion(cursor.getString(6));
            listaUsuario.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList <String>();
        for (int i=0;i<listaUsuario.size();i++){
            listaInformacion.add(listaUsuario.get(i).getNombre()+"  "+listaUsuario.get(i).getApellido());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent1=new Intent(this,RegistroPrestamoNuevo.class);
                startActivity(intent1);
                return true;

                case R.id.item2:
                Intent intent2=new Intent(this,registro_usuario.class);
                startActivity(intent2);
                return true;

                case R.id.item3:
                Intent intent3=new Intent(this,vercliente.class);
                startActivity(intent3);
                return true;

                case R.id.item4:
                Intent intent4=new Intent(this,verprestamo.class);
                startActivity(intent4);
                return true;

                case R.id.item5:
                Intent intent5=new Intent(this,acerca.class);
                startActivity(intent5);
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}

