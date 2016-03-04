package com.example.admin.psp_practica3_jaime.adaptador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.psp_practica3_jaime.Editar;
import com.example.admin.psp_practica3_jaime.Interfaz.ApiActividades;
import com.example.admin.psp_practica3_jaime.R;
import com.example.admin.psp_practica3_jaime.pojo.Actividad;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Admin on 23/02/2016.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private ArrayList<Actividad> datos;
    private int resources;
    private View view;
    private Context c;

    public Adaptador(ArrayList<Actividad> datos, int resources){
        this.datos=datos;
        this.resources=resources;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        LayoutInflater i = (LayoutInflater) parent.getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        view = i.inflate(resources, null);

        c=parent.getContext();

        ViewHolder tvh = new ViewHolder(view);

        return tvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Actividad ac=datos.get(position);
        holder.bindRegister(ac);

        if(position%2==0)
            view.setBackgroundColor(c.getResources().getColor(R.color.adaptador));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, Editar.class);
                i.putExtra("actividad", ac);
                lanzar(i);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(c);
                b.setMessage("¿Borrar?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                /****/
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://ieszv.x10.bz/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                ApiActividades api = retrofit.create(ApiActividades.class);

                                final Call<String> call = api.deleteActividad(Integer.parseInt(ac.getId()));

                                call.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Response<String> response, Retrofit retrofit) {
                                        datos.remove(position);
                                        notifyItemChanged(position);
                                        notifyItemRemoved(position);
                                        notifyDataSetChanged();

                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        t.getLocalizedMessage();
                                    }

                                });

                                /****/
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

                return true;
            }
        });
    }

    /*************************************************/

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void lanzar(Intent i){
        c.startActivity(i);
    }




    /************************************************/

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tipo;
        private TextView descripcion;
        private TextView fecha;

        public ViewHolder(View itemView) {
            super(itemView);
            tipo = (TextView)itemView.findViewById(R.id.textView2);
            descripcion = (TextView)itemView.findViewById(R.id.textView3);
            fecha = (TextView)itemView.findViewById(R.id.textView4);
        }

        public void bindRegister(Actividad t) {
            tipo.setText(t.getTipo());
            descripcion.setText(t.getDescripcion());
            fecha.setText(t.getFechai()+" - "+t.getFechaf());
        }
    }
}