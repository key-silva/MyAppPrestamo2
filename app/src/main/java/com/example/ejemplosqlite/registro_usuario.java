package com.example.ejemplosqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ejemplosqlite.utilidades.utilidades;

public class registro_usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
    }

    public void btncancelar(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void btnguardar(View view) {
        EditText uno=findViewById(R.id.editTextnombre);
        String nombre=uno.getText().toString();
        if(nombre.equals("")){
            uno.setError("Ingresar Nombre");
        }
        EditText dos=findViewById(R.id.editTexttelefono);
        String telefono=dos.getText().toString();
        if(nombre.equals("")){
            dos.setError("Ingresar Telefono");
        }
        EditText tres=findViewById(R.id.editTextcedula);
        String cedula=tres.getText().toString();
        if(nombre.equals("")){
            tres.setError("Ingresar cedula");
        }
        EditText cuatro=findViewById(R.id.editTextdireccion);
        String direccion=cuatro.getText().toString();
        if(nombre.equals("")){
            cuatro.setError("Ingresar direccion");
        }

        if(nombre.equals("") && telefono.equals("") && cedula.equals("") && direccion.equals("")){

        }else{
            registarDatosSQL();
        }

    }

    private void registarDatosSQL() {
        EditText uno=findViewById(R.id.editTextnombre);
        String nombre=uno.getText().toString();
        EditText dos=findViewById(R.id.editTextapellido);
        String apellido=dos.getText().toString();
        EditText tres=findViewById(R.id.editTexttelefono);
        String telefono=tres.getText().toString();
        EditText cuatro=findViewById(R.id.editTextcedula);
        String cedula=cuatro.getText().toString();
        EditText cinco=findViewById(R.id.editTextocupacion);
        String ocupacion=cinco.getText().toString();
        EditText seis=findViewById(R.id.editTextdireccion);
        String direccion=seis.getText().toString();
        //spinner
        Spinner spinner = findViewById(R.id.spinnersexo);
        String sexo = (String) spinner.getSelectedItem();

        conexionSQLiteHelper conn=new conexionSQLiteHelper(this,"bd_usuario",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(utilidades.nombre_campo,nombre);
        values.put(utilidades.apellido_campo,apellido);
        values.put(utilidades.sexo_campo,sexo);
        values.put(utilidades.telefono_campo,telefono);
        values.put(utilidades.cedula_campo,cedula);
        values.put(utilidades.ocupacion_campo,ocupacion);
        values.put(utilidades.direccion_campo,direccion);
        Long idResultado=db.insert(utilidades.tabla_usuario,utilidades.nombre_campo,values);
        Toast.makeText(getApplicationContext(),"Exito...",Toast.LENGTH_SHORT).show();
        db.close();

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
