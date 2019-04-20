package com.example.ejemplosqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejemplosqlite.entidades.Prestamos;
import com.example.ejemplosqlite.entidades.Usuario;
import com.example.ejemplosqlite.utilidades.utilidadesPretamos;

import java.util.ArrayList;

public class verprestamo extends AppCompatActivity {
    ArrayList<String> listaInformacion;
    ArrayList<Prestamos> listaUsuario;
    conexionSQLiteHelper2 conn;
    int contador=0;
    int num_final=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verprestamo);
        conn=new conexionSQLiteHelper2(getApplicationContext(),"bd_prestamo",null,1);
        try {
            consultarListaPersonas();
        } catch (Exception e) {
            Toast.makeText(verprestamo.this, "No hay Datos", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Prestamos usuario=null;
        listaUsuario=new ArrayList <Prestamos>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ utilidadesPretamos.tabla_usuario,null);
        while ( cursor.moveToNext()){
            usuario=new Prestamos();
            usuario.setNombre_completo(cursor.getString(0));
            usuario.setMonto(cursor.getString(1));
            usuario.setInteres(cursor.getString(2));
            usuario.setPlazo(cursor.getString(3));
            usuario.setFechainicio(cursor.getString(4));
            usuario.setFechafinal(cursor.getString(5));
            usuario.setMontoPagar(cursor.getString(6));
            usuario.setMontoPlazo(cursor.getString(7));
            listaUsuario.add(usuario);
        }

        obtenerLista();

    }

    private void obtenerLista() {
        num_final=listaUsuario.size();
        String valor1 =listaUsuario.get(contador).getNombre_completo();
        String valor2 =listaUsuario.get(contador).getMonto();
        String valor3 =listaUsuario.get(contador).getInteres();
        String valor4 =listaUsuario.get(contador).getPlazo();
        String valor5 =listaUsuario.get(contador).getFechainicio();
        String valor6 =listaUsuario.get(contador).getFechafinal();
        String valor7 =listaUsuario.get(contador).getMontoPagar();
        String valor8 =listaUsuario.get(contador).getMontoPlazo();

        TextView uno=findViewById(R.id.textView201);
        uno.setText(valor1);
        TextView dos=findViewById(R.id.textView202);
        dos.setText(valor2);
        TextView tres=findViewById(R.id.textView203);
        tres.setText(valor3);
        TextView cuatro=findViewById(R.id.textView204);
        cuatro.setText(valor4);
        TextView cinco=findViewById(R.id.textView205);
        cinco.setText(valor5);
        TextView seis=findViewById(R.id.textView206);
        seis.setText(valor6);
        TextView siete=findViewById(R.id.textView207);
        siete.setText(valor7);
        TextView ocho=findViewById(R.id.textView208);
        ocho.setText(valor8);
    }


    public void btnizquierda(View view) {
        if(contador==0){
            Toast.makeText(verprestamo.this,"Inicio",Toast.LENGTH_SHORT).show();
        }else{
            contador=contador-1;
            String valor1 =listaUsuario.get(contador).getNombre_completo();
            String valor2 =listaUsuario.get(contador).getMonto();
            String valor3 =listaUsuario.get(contador).getInteres();
            String valor4 =listaUsuario.get(contador).getPlazo();
            String valor5 =listaUsuario.get(contador).getFechainicio();
            String valor6 =listaUsuario.get(contador).getFechafinal();
            String valor7 =listaUsuario.get(contador).getMontoPagar();
            String valor8 =listaUsuario.get(contador).getMontoPlazo();

            TextView uno=findViewById(R.id.textView201);
            uno.setText(valor1);
            TextView dos=findViewById(R.id.textView202);
            dos.setText(valor2);
            TextView tres=findViewById(R.id.textView203);
            tres.setText(valor3);
            TextView cuatro=findViewById(R.id.textView204);
            cuatro.setText(valor4);
            TextView cinco=findViewById(R.id.textView205);
            cinco.setText(valor5);
            TextView seis=findViewById(R.id.textView206);
            seis.setText(valor6);
            TextView siete=findViewById(R.id.textView207);
            siete.setText(valor7);
            TextView ocho=findViewById(R.id.textView208);
            ocho.setText(valor8);
        }
    }

    public void btnderecha(View view) {
        if(contador==(num_final-1)){
            Toast.makeText(verprestamo.this,"Final",Toast.LENGTH_SHORT).show();
        }else{
            contador=contador+1;
            String valor1 =listaUsuario.get(contador).getNombre_completo();
            String valor2 =listaUsuario.get(contador).getMonto();
            String valor3 =listaUsuario.get(contador).getInteres();
            String valor4 =listaUsuario.get(contador).getPlazo();
            String valor5 =listaUsuario.get(contador).getFechainicio();
            String valor6 =listaUsuario.get(contador).getFechafinal();
            String valor7 =listaUsuario.get(contador).getMontoPagar();
            String valor8 =listaUsuario.get(contador).getMontoPlazo();

            TextView uno=findViewById(R.id.textView201);
            uno.setText(valor1);
            TextView dos=findViewById(R.id.textView202);
            dos.setText(valor2);
            TextView tres=findViewById(R.id.textView203);
            tres.setText(valor3);
            TextView cuatro=findViewById(R.id.textView204);
            cuatro.setText(valor4);
            TextView cinco=findViewById(R.id.textView205);
            cinco.setText(valor5);
            TextView seis=findViewById(R.id.textView206);
            seis.setText(valor6);
            TextView siete=findViewById(R.id.textView207);
            siete.setText(valor7);
            TextView ocho=findViewById(R.id.textView208);
            ocho.setText(valor8);
        }
    }

    public void btncancelar(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
