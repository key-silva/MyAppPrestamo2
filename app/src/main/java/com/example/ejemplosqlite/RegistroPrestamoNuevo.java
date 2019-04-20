package com.example.ejemplosqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejemplosqlite.entidades.Usuario;
import com.example.ejemplosqlite.utilidades.utilidades;
import com.example.ejemplosqlite.utilidades.utilidadesPretamos;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class RegistroPrestamoNuevo extends AppCompatActivity {
    Spinner comboPersona;
    ArrayList <String> listaPersona;
    ArrayList <Usuario> PersonaList;
    conexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_prestamo_nuevo);
        conn = new conexionSQLiteHelper(getApplicationContext(), "bd_usuario", null, 1);
        comboPersona = (Spinner) findViewById(R.id.spinnercliente);
        consutarListaPersonas();
        ArrayAdapter <Character> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPersona);
        comboPersona.setAdapter(adapter);


        EditText uno = findViewById(R.id.editTextplazo);
        uno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText uno = findViewById(R.id.editTextmonto);
                String monto = uno.getText().toString();

                EditText dos = findViewById(R.id.editTextplazo);
                String plazo = dos.getText().toString();

                EditText tres = findViewById(R.id.editTextfechainicial);
                String FechaInicial = tres.getText().toString();

                EditText cuatro = findViewById(R.id.editTextfechafinalizar);
                String FechaFinal = cuatro.getText().toString();

                TextView cinco = findViewById(R.id.textViewmontoPagar);
                TextView seis = findViewById(R.id.textViewmontoCuotas);

                if (plazo.equals("")) {
                    dos.setError("Ingresar Plazo");
                }
                if (monto.equals("")) {
                    uno.setError("Ingresar Monto");
                }

                if (plazo.equals("") || monto.equals("") || plazo.equals("0")) {
                    final Calendar cc = Calendar.getInstance();
                    int ano1 = cc.get(Calendar.YEAR);
                    int mes1 = cc.get(Calendar.MONDAY);
                    int dia1 = cc.get(Calendar.DAY_OF_MONTH);
                    String inicialCale = String.valueOf(dia1 + "/" + (mes1) + "/" + ano1);
                    String inicialCale2Final = String.valueOf(dia1 + "/" + (mes1 + 1) + "/" + ano1);
                    tres.setText(inicialCale);
                    cuatro.setText(inicialCale2Final);


                } else {
                    int total = Integer.parseInt(plazo);

                    final Calendar cc = Calendar.getInstance();
                    int mes1 = cc.get(Calendar.MONDAY);

                    int total112 = mes1 + total;

                    if (total112 > 1 && total112 < 12) {
                        int total_monto = Integer.parseInt(monto);
                        int total_plazo = Integer.parseInt(plazo);
                        //spinner
                        Spinner spinner = findViewById(R.id.spinnerinteres);
                        String interes = (String) spinner.getSelectedItem();
                        int spinnerCambio = Integer.parseInt(interes);

                        double total_repuesta = ((total_monto * spinnerCambio) / 100) + total_monto;
                        String mostrar = String.valueOf(total_repuesta);
                        cinco.setText(mostrar);

                        double valor_tem = total_monto / total_plazo;
                        seis.setText(String.valueOf(valor_tem));

                        final Calendar cc2 = Calendar.getInstance();
                        int ano11 = cc2.get(Calendar.YEAR);
                        int mes11 = cc2.get(Calendar.MONDAY);
                        int dia11 = cc2.get(Calendar.DAY_OF_MONTH);
                        String inicialCale = String.valueOf(dia11 + "/" + (mes11) + "/" + ano11);
                        String inicialCale2Final = String.valueOf(dia11 + "/" + (mes11 + total_plazo) + "/" + ano11);

                        tres.setText(inicialCale);
                        cuatro.setText(inicialCale2Final);
                    } else {
                        dos.setError("Plazo Menor de 12 Meses");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void consutarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario persona = null;
        PersonaList = new ArrayList <Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.tabla_usuario, null);

        while ( cursor.moveToNext() ) {
            persona = new Usuario();
            persona.setNombre(cursor.getString(0));
            persona.setApellido(cursor.getString(1));

            Log.i("nombre", persona.getNombre().toString());
            Log.i("apellido", persona.getApellido().toString());
            PersonaList.add(persona);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaPersona = new ArrayList <String>();
        listaPersona.add("selecione");
        for (int i = 0; i < PersonaList.size(); i++) {
            listaPersona.add(PersonaList.get(i).getNombre() + " " + PersonaList.get(i).getApellido());
        }
    }

    public void btncancelar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void btnguardar(View view) {
        EditText uno = findViewById(R.id.editTextmonto);
        String monto = uno.getText().toString();
        if (monto.equals("")) {
            uno.setError("Ingresar Monto");
        }

        EditText dos = findViewById(R.id.editTextplazo);
        String plazo = dos.getText().toString();
        if (plazo.equals("")) {
            dos.setError("Ingresar Plazo");
        }

        if (monto.equals("") && plazo.equals("")) {

        } else {
            //spinner
            Spinner spinner = findViewById(R.id.spinnercliente);
            String cliente = (String) spinner.getSelectedItem();
            //spinner
            Spinner spinner2 = findViewById(R.id.spinnerinteres);
            String interes = (String) spinner2.getSelectedItem();

            if (cliente.equals("selecione")) {
                Toast.makeText(RegistroPrestamoNuevo.this, "Selecionar Cliente", Toast.LENGTH_SHORT).show();
            } else {
                if(plazo.equals("")){
                    dos.setError("Ingresar Plazo");
                }else{
                    EditText tres = findViewById(R.id.editTextfechainicial);
                    String FechaInicial = tres.getText().toString();

                    EditText cuatro = findViewById(R.id.editTextfechafinalizar);
                    String FechaFinal = cuatro.getText().toString();

                    TextView cinco = findViewById(R.id.textViewmontoPagar);
                    String MontoPagar = cinco.getText().toString();
                    TextView seis = findViewById(R.id.textViewmontoCuotas);
                    String MontoCuotas = seis.getText().toString();
                    //base de datos
                    conexionSQLiteHelper2 conn = new conexionSQLiteHelper2(this, "bd_prestamo", null, 1);
                    SQLiteDatabase db = conn.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put(utilidadesPretamos.nombre_completo_campo, cliente);
                    values.put(utilidadesPretamos.monto_campo, monto);
                    values.put(utilidadesPretamos.interes_campo, interes);
                    values.put(utilidadesPretamos.plazo_campo, plazo);
                    values.put(utilidadesPretamos.fecha_inicial_campo, FechaInicial);
                    values.put(utilidadesPretamos.fecha_final_campo, FechaFinal);
                    values.put(utilidadesPretamos.monto_total_campo, MontoPagar);
                    values.put(utilidadesPretamos.monto_cuotas_campo, MontoCuotas);

                    Long idResultado = db.insert(utilidadesPretamos.tabla_usuario, utilidadesPretamos.nombre_completo_campo, values);
                    Toast.makeText(getApplicationContext(), "Exito...", Toast.LENGTH_SHORT).show();
                    db.close();
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
            }

        }
    }
}
