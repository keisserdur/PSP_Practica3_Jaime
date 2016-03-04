package com.example.admin.psp_practica3_jaime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private Call<List<Actividad>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        Log.v("LOGV", "oncreate");

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
            startActivityForResult(new Intent(this, Alta.class), 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }



    /*****************************************************************************/

    private void cargarLista(){
        lista=new ArrayList<Actividad>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiActividades api = retrofit.create(ApiActividades.class);

        call = api.getActividades();

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
                Log.v("LOGV", "fin call");
            }

            @Override
            public void onFailure(Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }


    /*****************************************************************************/

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("LOGV","onresume");
        cargarLista();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("LOGV", "onrestart");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.v("LOGV", "onpause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("LOGV", "onstart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("LOGV", "onStop");
    }
}
