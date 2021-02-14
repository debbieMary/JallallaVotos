package com.jallalla.jallallavotos.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CounterDataResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("resultaldo")
    @Expose
    private String resultaldo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    /**
     * No args constructor for use in serialization
     *
     */
    public CounterDataResponse() {
    }

    /**
     *
     * @param resultaldo
     * @param mensaje
     * @param status
     */
    public CounterDataResponse(String status, String resultaldo, String mensaje) {
        super();
        this.status = status;
        this.resultaldo = resultaldo;
        this.mensaje = mensaje;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CounterDataResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getResultaldo() {
        return resultaldo;
    }

    public void setResultaldo(String resultaldo) {
        this.resultaldo = resultaldo;
    }

    public CounterDataResponse withResultaldo(String resultaldo) {
        this.resultaldo = resultaldo;
        return this;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public CounterDataResponse withMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

}