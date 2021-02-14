package com.jallalla.jallallavotos.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CounterData {

    @SerializedName("id_mesa")
    @Expose
    private String idMesa;
    @SerializedName("id_militante")
    @Expose
    private String idMilitante;
    @SerializedName("sigla")
    @Expose
    private String sigla;
    @SerializedName("votos_alcalde")
    @Expose
    private String votosAlcalde;
    @SerializedName("votos_concejal")
    @Expose
    private String votosConcejal;
    @SerializedName("fecha_alta")
    @Expose
    private String fechaAlta;
    @SerializedName("id_partido_politico")
    @Expose
    private String idPartidoPolitico;

    /**
     * No args constructor for use in serialization
     *
     */
    public CounterData() {
    }

    /**
     *
     * @param idMilitante
     * @param votosAlcalde
     * @param idMesa
     * @param sigla
     * @param fechaAlta
     * @param votosConcejal
     * @param idPartidoPolitico
     */
    public CounterData(String idMesa, String idMilitante, String sigla, String votosAlcalde, String votosConcejal, String fechaAlta, String idPartidoPolitico) {
        super();
        this.idMesa = idMesa;
        this.idMilitante = idMilitante;
        this.sigla = sigla;
        this.votosAlcalde = votosAlcalde;
        this.votosConcejal = votosConcejal;
        this.fechaAlta = fechaAlta;
        this.idPartidoPolitico = idPartidoPolitico;
    }

    public String getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(String idMesa) {
        this.idMesa = idMesa;
    }

    public CounterData withIdMesa(String idMesa) {
        this.idMesa = idMesa;
        return this;
    }

    public String getIdMilitante() {
        return idMilitante;
    }

    public void setIdMilitante(String idMilitante) {
        this.idMilitante = idMilitante;
    }

    public CounterData withIdMilitante(String idMilitante) {
        this.idMilitante = idMilitante;
        return this;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public CounterData withSigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public String getVotosAlcalde() {
        return votosAlcalde;
    }

    public void setVotosAlcalde(String votosAlcalde) {
        this.votosAlcalde = votosAlcalde;
    }

    public CounterData withVotosAlcalde(String votosAlcalde) {
        this.votosAlcalde = votosAlcalde;
        return this;
    }

    public String getVotosConcejal() {
        return votosConcejal;
    }

    public void setVotosConcejal(String votosConcejal) {
        this.votosConcejal = votosConcejal;
    }

    public CounterData withVotosConcejal(String votosConcejal) {
        this.votosConcejal = votosConcejal;
        return this;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public CounterData withFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
        return this;
    }

    public String getIdPartidoPolitico() {
        return idPartidoPolitico;
    }

    public void setIdPartidoPolitico(String idPartidoPolitico) {
        this.idPartidoPolitico = idPartidoPolitico;
    }

    public CounterData withIdPartidoPolitico(String idPartidoPolitico) {
        this.idPartidoPolitico = idPartidoPolitico;
        return this;
    }

}