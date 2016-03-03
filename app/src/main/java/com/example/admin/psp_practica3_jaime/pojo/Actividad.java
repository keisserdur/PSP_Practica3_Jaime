package com.example.admin.psp_practica3_jaime.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 22/02/2016.
 */
public class Actividad implements Parcelable {
    private String id,idprofesor;
    private String lugari,lugarf,descripcion,alumno;
    private String fechai,fechaf;
    private String tipo;enum aux{
        complementaria,extraexcolar
    }


    protected Actividad(Parcel in) {
        id = in.readString();
        idprofesor = in.readString();
        lugari = in.readString();
        lugarf = in.readString();
        descripcion = in.readString();
        alumno = in.readString();
        fechai = in.readString();
        fechaf = in.readString();
        tipo = in.readString();
    }

    public Actividad() {

    }
    public Actividad(String idprofesor, String lugari, String lugarf, String descripcion, String alumno, String fechai, String fechaf, String tipo) {
        this(null,idprofesor,lugari,lugarf,descripcion,alumno,fechai,fechaf,tipo);
    }

    public Actividad(String id,String idprofesor, String lugari, String lugarf, String descripcion, String alumno, String fechai, String fechaf, String tipo) {
        this.id=id;
        this.idprofesor = idprofesor;
        this.lugari = lugari;
        this.lugarf = lugarf;
        this.descripcion = descripcion;
        this.alumno = alumno;
        this.fechai = fechai;
        this.fechaf = fechaf;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(String idprofesor) {
        this.idprofesor = idprofesor;
    }

    public String getLugari() {
        return lugari;
    }

    public void setLugari(String lugari) {
        this.lugari = lugari;
    }

    public String getLugarf() {
        return lugarf;
    }

    public void setLugarf(String lugarf) {
        this.lugarf = lugarf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descricion) {
        this.descripcion = descricion;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getFechai() {
        return fechai;
    }

    public void setFechai(String fechai) {
        this.fechai = fechai;
    }

    public String getFechaf() {
        return fechaf;
    }

    public void setFechaf(String fechaf) {
        this.fechaf = fechaf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public static final Creator<Actividad> CREATOR = new Creator<Actividad>() {
        @Override
        public Actividad createFromParcel(Parcel in) {
            return new Actividad(in);
        }

        @Override
        public Actividad[] newArray(int size) {
            return new Actividad[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(idprofesor);
        dest.writeString(lugari);
        dest.writeString(lugarf);
        dest.writeString(descripcion);
        dest.writeString(alumno);
        dest.writeString(fechai);
        dest.writeString(fechaf);
        dest.writeString(tipo);
    }


    @Override
    public String toString() {
        return "Actividad{" +
                "id='" + id + '\'' +
                ", idprofesor='" + idprofesor + '\'' +
                ", lugari='" + lugari + '\'' +
                ", lugarf='" + lugarf + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", alumno='" + alumno + '\'' +
                ", fechai='" + fechai + '\'' +
                ", fechaf='" + fechaf + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
