package com.jallalla.jallallavotos.Entities;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CounterDataBody {

    @SerializedName("datos")
    @Expose
    private List<CounterData> datos = null;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("observaciones")
    @Expose
    private String observaciones;

    /**
     * No args constructor for use in serialization
     *
     */
    public CounterDataBody() {
    }

    /**
     *
     * @param datos
     * @param foto
     * @param observaciones
     */
    public CounterDataBody(List<CounterData> datos, String foto, String observaciones) {
        super();
        this.datos = datos;
        this.foto = foto;
        this.observaciones = observaciones;
    }

    public List<CounterData> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<CounterData> datos) {
        this.datos = datos;
    }

    public CounterDataBody withDatos(List<CounterData> datos) {
        this.datos = datos;
        return this;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public CounterDataBody withFoto(String foto) {
        this.foto = foto;
        return this;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public CounterDataBody withObservaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

}