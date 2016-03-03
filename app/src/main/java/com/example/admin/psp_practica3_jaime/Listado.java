package com.example.admin.psp_practica3_jaime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.psp_practica3_jaime.Interfaz.ApiActividades;
import com.example.admin.psp_practica3_jaime.adaptador.Adaptador;
import com.example.admin.psp_practica3_jaime.pojo.Actividad;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Listado extends AppCompatActivity {

    private List<Actividad> lista;
    private RecyclerView view;
    private Adaptador adt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lista=new ArrayList<Actividad>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiActividades api = retrofit.create(ApiActividades.class);

        Call<List<Actividad>> call = api.getActividades();

        call.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Response<List<Actividad>> response, Retrofit retrofit) {
                for (Actividad a : response.body()) {
                    lista.add(a);
                }
                view = (RecyclerView) findViewById(R.id.view);
                adt = new Adaptador((ArrayList<Actividad>) lista, R.layout.item);
                view.setAdapter(adt);
                view.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

    /*****************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            startActivity(new Intent(this,Alta.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /*****************************************************************************/
    @Override
    protected void onRestart() {
        super.onRestart();
        adt.notifyDataSetChanged();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
