package com.example.admin.psp_practica3_jaime.Interfaz;

import com.example.admin.psp_practica3_jaime.pojo.Actividad;
import com.example.admin.psp_practica3_jaime.pojo.Profesor;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface ApiActividades {
    @GET("restful/api/actividad/jaime")//listado actividades
    Call<List<Actividad>> getActividades();

    @GET("restful/api/profesor")//listado
    Call<List<Profesor>> getProfesores();

    @POST("restful/api/actividad")//nuevo
    Call<String> postActividad(@Body Actividad actividad);

    @PUT("restful/api/actividad")//editar
    Call<String> putActividad(@Body Actividad actividad);

    @DELETE("restful/api/actividad/{id}")//delete
    Call<String> deleteActividad(@Path("id") int id);

}
