package com.example.admin.psp_practica3_jaime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.admin.psp_practica3_jaime.Interfaz.ApiActividades;
import com.example.admin.psp_practica3_jaime.pojo.Actividad;
import com.example.admin.psp_practica3_jaime.pojo.Profesor;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Editar extends AppCompatActivity {

    private final String ALUMNO="jaime";
    private Actividad editadaActividad;
    private List<Profesor> lista;
    private EditText descripcion,fechai,fechaf,lugari,lugarf;

    private RadioGroup rg;

    //{"id":"853","idprofesor": "2","tipo": "complementaria","fechai": "2016-02-24 09:15:00","fechaf": "2016-02-24 01:30:00","lugari": "Granada","lugarf": "Granada","descripcion": "Visita a la Alhambra","alumno": "jaime"}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        editadaActividad= (Actividad) getIntent().getExtras().get("actividad");

        ini();

    }

    public void ini() {
        cargarSniper();

        descripcion= (EditText) findViewById(R.id.editText5);
        lugari= (EditText) findViewById(R.id.editText);
        lugarf= (EditText) findViewById(R.id.editText2);
        fechai= (EditText) findViewById(R.id.editText4);
        fechaf= (EditText) findViewById(R.id.editText3);
        rg = (RadioGroup)findViewById(R.id.group);

        descripcion.setText(editadaActividad.getDescripcion());
        lugarf.setText(editadaActividad.getLugarf());
        lugari.setText(editadaActividad.getLugari());
        fechai.setText(editadaActividad.getFechai());
        fechaf.setText(editadaActividad.getFechaf());

        if(editadaActividad.getTipo().equals("complementaria"))
            rg.check(R.id.radioButton);
        else
            rg.check(R.id.radioButton2);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton:
                        editadaActividad.setTipo("complementaria");
                        break;
                    case R.id.radioButton2:
                        editadaActividad.setTipo("extraescolar");
                        break;
                }

            }
        });
    }

    /********************************************************************/

    private void cargarSniper(){

        lista=new ArrayList<Profesor>();

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiActividades api = retrofit.create(ApiActividades.class);

        Call<List<Profesor>> call = api.getProfesores();

        call.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Response<List<Profesor>> response, Retrofit retrofit) {
                for (Profesor a : response.body()) {
                    lista.add(a);
                }
                ArrayList<String> nombres=new ArrayList<String>();
                for (Profesor nombre:lista){
                    nombres.add(nombre.getNombre());
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nombres);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(dataAdapter);

            }

            @Override
            public void onFailure(Throwable t) {
                t.getLocalizedMessage();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editadaActividad.setIdprofesor(lista.get(position).getId() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /********************************************************************/

    public void aceptar(View v){
        if(!(descripcion.getText().toString().isEmpty() && lugari.getText().toString().isEmpty() && lugarf.getText().toString().isEmpty() &&
                fechai.getText().toString().isEmpty() && fechaf.getText().toString().isEmpty())){

            editadaActividad.setDescripcion(descripcion.getText().toString());
            editadaActividad.setLugari(lugari.getText().toString());
            editadaActividad.setLugarf(lugarf.getText().toString());
            editadaActividad.setFechai(fechai.getText().toString());
            editadaActividad.setFechaf(fechaf.getText().toString());
            editadaActividad.setAlumno(ALUMNO);

            editarAlumno();

            finish();
        }

    }
    public void cancelar(View v){
        finish();
    }

    private void editarAlumno(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiActividades api = retrofit.create(ApiActividades.class);

        Call<String> call=api.putActividad(editadaActividad);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
            }

            @Override
            public void onFailure(Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

}
