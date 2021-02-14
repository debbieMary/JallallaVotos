package com.jallalla.jallallavotos.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.jallalla.jallallavotos.Entities.Militante;

public class LoginResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("data")
    @Expose
    private List<Militante> data = null;

    /**
     * No args constructor for use in serialization
     */
    public LoginResponse() {
    }

    /**
     * @param data
     * @param mensaje
     * @param status
     */
    public LoginResponse(String status, String mensaje, List<Militante> data) {
        super();
        this.status = status;
        this.mensaje = mensaje;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoginResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LoginResponse withMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public List<Militante> getData() {
        return data;
    }

    public void setData(List<Militante> data) {
        this.data = data;
    }

    public LoginResponse withData(List<Militante> data) {
        this.data = data;
        return this;
    }

}