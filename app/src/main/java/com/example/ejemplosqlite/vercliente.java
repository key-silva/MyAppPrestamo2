package com.example.ejemplosqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejemplosqlite.entidades.Usuario;
import com.example.ejemplosqlite.utilidades.utilidades;

import java.util.ArrayList;

public class vercliente extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList <String> listaInformacion;
    ArrayList <Usuario> listaUsuario;
    conexionSQLiteHelper conn;
    int contador = 0;
    int num_final = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vercliente);

        conn = new conexionSQLiteHelper(getApplicationContext(), "bd_usuario", null, 1);
        try {
            consultarListaPersonas();
        } catch (Exception e) {
            Toast.makeText(vercliente.this, "No hay Datos", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;
        listaUsuario = new ArrayList <Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.tabla_usuario, null);
        while ( cursor.moveToNext() ) {
            usuario = new Usuario();
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
        num_final = listaUsuario.size();
        String valor1 = listaUsuario.get(contador).getNombre();
        String valor2 = listaUsuario.get(contador).getApellido();
        String valor3 = listaUsuario.get(contador).getSexo();
        String valor4 = listaUsuario.get(contador).getTelefono();
        String valor5 = listaUsuario.get(contador).getCedula();
        String valor6 = listaUsuario.get(contador).getOcupacion();
        String valor7 = listaUsuario.get(contador).getDireccion();

        TextView uno = findViewById(R.id.textView101);
        uno.setText(valor1);
        TextView dos = findViewById(R.id.textView102);
        dos.setText(valor2);
        TextView tres = findViewById(R.id.textView103);
        tres.setText(valor3);
        TextView cuatro = findViewById(R.id.textView104);
        cuatro.setText(valor4);
        TextView cinco = findViewById(R.id.textView105);
        cinco.setText(valor5);
        TextView seis = findViewById(R.id.textView106);
        seis.setText(valor6);
        TextView siete = findViewById(R.id.textView107);
        siete.setText(valor7);

    }

    public void btnprestamo(View view) {
        Intent intent = new Intent(this, RegistroPrestamoNuevo.class);
        startActivity(intent);
    }

    public void btnderecha(View view) {
        if (contador == (num_final - 1)) {
            Toast.makeText(vercliente.this, "Final", Toast.LENGTH_SHORT).show();
        } else {
            contador = contador + 1;
            String valor1 = listaUsuario.get(contador).getNombre();
            String valor2 = listaUsuario.get(contador).getApellido();
            String valor3 = listaUsuario.get(contador).getSexo();
            String valor4 = listaUsuario.get(contador).getTelefono();
            String valor5 = listaUsuario.get(contador).getCedula();
            String valor6 = listaUsuario.get(contador).getOcupacion();
            String valor7 = listaUsuario.get(contador).getDireccion();

            TextView uno = findViewById(R.id.textView101);
            uno.setText(valor1);
            TextView dos = findViewById(R.id.textView102);
            dos.setText(valor2);
            TextView tres = findViewById(R.id.textView103);
            tres.setText(valor3);
            TextView cuatro = findViewById(R.id.textView104);
            cuatro.setText(valor4);
            TextView cinco = findViewById(R.id.textView105);
            cinco.setText(valor5);
            TextView seis = findViewById(R.id.textView106);
            seis.setText(valor6);
            TextView siete = findViewById(R.id.textView107);
            siete.setText(valor7);
        }
    }

    public void btnizquierda(View view) {
        if (contador == 0) {
            Toast.makeText(vercliente.this, "Inicio", Toast.LENGTH_SHORT).show();
        } else {
            contador = contador - 1;
            String valor1 = listaUsuario.get(contador).getNombre();
            String valor2 = listaUsuario.get(contador).getApellido();
            String valor3 = listaUsuario.get(contador).getSexo();
            String valor4 = listaUsuario.get(contador).getTelefono();
            String valor5 = listaUsuario.get(contador).getCedula();
            String valor6 = listaUsuario.get(contador).getOcupacion();
            String valor7 = listaUsuario.get(contador).getDireccion();

            TextView uno = findViewById(R.id.textView101);
            uno.setText(valor1);
            TextView dos = findViewById(R.id.textView102);
            dos.setText(valor2);
            TextView tres = findViewById(R.id.textView103);
            tres.setText(valor3);
            TextView cuatro = findViewById(R.id.textView104);
            cuatro.setText(valor4);
            TextView cinco = findViewById(R.id.textView105);
            cinco.setText(valor5);
            TextView seis = findViewById(R.id.textView106);
            seis.setText(valor6);
            TextView siete = findViewById(R.id.textView107);
            siete.setText(valor7);
        }
    }

    public void btncancelar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
